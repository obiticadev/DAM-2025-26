# Guía de instalación: CachyOS + Omarchy en dual boot con Windows 10 (bare metal)

> **Hardware objetivo de esta guía**
> - CPU: AMD Ryzen 9 **5900X** (Zen 3)
> - GPU: **NVIDIA RTX 3080 Ti** (Ampere, GA102)
> - RAM: **64 GB DDR4**
> - Sistema actual: **Windows 10** ya instalado
> - Objetivo: dual boot CachyOS + Omarchy, eliminando Windows en el futuro
>
> Documento construido a partir de: wiki oficial de CachyOS, manual oficial de Omarchy, Hyprland Wiki (sección NVIDIA), repositorio `mroboff/omarchy-on-cachyos` (script comunitario que es lo que permite dual boot real), discusiones en `basecamp/omarchy` (#3024 black screen NVIDIA, #3382 drivers demasiado nuevos, #176 VirtualBox, #650 Omarchy sobre CachyOS), foro `discuss.cachyos.org`, ArchWiki "Dual boot with Windows", repo `auswizard/Dual-Boot-Win11-CachyOS`, video y reacciones a la experiencia de Nate Gentile, y blog "Explorando Omarchy" (yoprogramo.com).

---

## 0. Punto crítico que cambia toda la estrategia

> **El instalador oficial de Omarchy requiere un disco entero**. NO permite dual boot real en una sola unidad. Esto es exactamente lo que tropezó a Nate Gentile y a muchos otros usuarios.
>
> **La solución correcta y validada por la comunidad: NO usar el instalador de Omarchy. En su lugar:**
> 1. Instalar **CachyOS** en dual boot (CachyOS sí soporta dual boot perfectamente).
> 2. Sobre ese CachyOS, ejecutar el script **`mroboff/omarchy-on-cachyos`** que aplica encima toda la configuración de Omarchy (Hyprland + dotfiles de DHH).
>
> Resultado: el mismo entorno Omarchy que muestra Nate Gentile, pero conviviendo con Windows 10 en el mismo SSD.

---

## 1. Antes de tocar nada: backup y verificaciones

### 1.1. Backup obligatorio
- Documentos, fotos, claves de Steam/Origin, configuraciones de juegos en `%APPDATA%`, claves SSH, navegadores (exportar marcadores y contraseñas).
- Imagen completa del disco con Macrium Reflect Free o Clonezilla en un disco externo. Si algo sale mal con las particiones, esto te salva.
- Anota la clave de licencia de Windows 10 (`wmic path softwarelicensingservice get OA3xOriginalProductKey` en CMD admin) por si necesitas reinstalar.

### 1.2. Verificar tu hardware y firmware
En PowerShell (admin):

```powershell
systeminfo | findstr /B /C:"BIOS"   # versión actual de BIOS
msinfo32                             # comprueba "BIOS Mode": debe ser UEFI
manage-bde -status C:                # estado de BitLocker
```

- `systeminfo`: muestra info del sistema y la BIOS. Apunta la versión para saber si te conviene actualizarla.
- `msinfo32`: la línea "BIOS Mode" debe poner **UEFI**. Si pusiera "Legacy", no podrás dual-bootear sin convertir el disco a GPT (vía `mbr2gpt` de Windows). En un 5900X moderno siempre será UEFI.
- `manage-bde -status C:`: si `Protection Status: Protection On`, BitLocker está activo. Hay que desactivarlo (§2.3).

### 1.3. Identifica tus discos físicos
```powershell
Get-PhysicalDisk | Format-Table FriendlyName, MediaType, Size
```

Decisión clave:
- **Opción A — un solo SSD/NVMe:** dual boot en la misma unidad. Mayor riesgo si el ESP es pequeño.
- **Opción B — dos discos:** Windows en uno, CachyOS en otro. **Mucho más seguro y la que recomiendo con tu hardware.** Si tienes un NVMe extra o lo añades, úsalo.

A lo largo de la guía indicaré donde diverja el procedimiento entre A y B.

---

## 2. Preparación de Windows 10 (1-2 horas)

### 2.1. Actualizar BIOS de la placa
Antes de nada actualiza el firmware de la placa base a la última versión estable (no beta). En AM4 con un 5900X, las BIOS antiguas dan problemas de IOMMU, USB y MSR que rompen Linux. Hazlo desde la utilidad del fabricante en Windows o desde el Q-Flash/BIOS Flashback.

### 2.2. Desactivar Fast Startup (CRÍTICO)
Sin esto, Windows hiberna el sistema de ficheros NTFS y Linux no podrá leer/escribir en él, y la tabla de particiones puede corromperse.

1. Panel de Control → Hardware y sonido → Opciones de energía.
2. **"Elegir el comportamiento de los botones de inicio/apagado"**.
3. Click en **"Cambiar la configuración actualmente no disponible"**.
4. Desmarca **"Activar inicio rápido"**.
5. Guarda cambios.

Verifica también desactivando la hibernación completamente:

```powershell
powercfg /h off
```

- `powercfg /h off`: elimina `hiberfil.sys` y desactiva la hibernación. Imprescindible para que Linux acceda al NTFS sin riesgo.

### 2.3. Desactivar/Suspender BitLocker
Si tu Windows 10 Pro tiene BitLocker activo en C: hay que desactivarlo **completamente** (no basta suspender) antes de redimensionar.

1. Panel de Control → Cifrado de unidad BitLocker.
2. En la unidad C: → **"Desactivar BitLocker"**.
3. Espera a que descifre la unidad entera (puede llevar horas según el tamaño).

Verifica con `manage-bde -status C:` → debe decir `Protection Off` y `Fully Decrypted`.

### 2.4. Verificar el tamaño del ESP (EFI System Partition)
Este es el detalle exacto que rompe la instalación en `auswizard/Dual-Boot-Win11-CachyOS`: si Windows creó un ESP de 100/128/260 MB, CachyOS no podrá meter su bootloader.

```powershell
diskpart
> list disk
> select disk 0   # (o el que sea tu disco con Windows)
> list partition
> exit
```

- `diskpart`: utilidad de gestión de discos por consola.
- `list partition`: te lista las particiones. Localiza la del tipo "System" (es el ESP). Anota su tamaño.

**Si el ESP < 500 MB → riesgo alto.** Opciones:
- **Opción A (un disco) ESP pequeño:** la opción más segura es **reinstalar Windows desde cero** creando manualmente un ESP de 2 GB ANTES de Windows (proceso descrito en `auswizard/Dual-Boot-Win11-CachyOS`). Aprovecha que vas a hacer backup de todos modos.
- **Opción B (dos discos):** no importa, CachyOS creará su propio ESP en el segundo disco.

Si el ESP es ≥ 500 MB, sigue adelante.

### 2.5. Reducir la partición de Windows (solo Opción A — un disco)
**Hazlo DESDE WINDOWS, no desde el live de Linux.** Reducir NTFS desde GParted con un Windows que ha hecho fast startup o tiene Volume Shadow Copy corrupta es la receta del desastre.

1. `Win + X` → **"Administración de discos"** (`diskmgmt.msc`).
2. Click derecho sobre C: → **"Reducir volumen"**.
3. En "Espacio que se va a reducir en MB" introduce el espacio para Linux. Con 64 GB de RAM y juegos en Linux, **recomiendo 300-500 GB**. Mínimo absoluto: 100 GB.
4. **Reducir**.
5. **NO crees nada** en el espacio liberado. Déjalo como "No asignado".

Si Windows no te deja reducir tanto, hay ficheros inamovibles (pagefile, hibernation, restore points). Mitigaciones:
```powershell
# Como admin
Disable-ComputerRestore -Drive "C:\"
powercfg /h off
# Mueve el pagefile temporalmente: Configuración avanzada del sistema → Rendimiento → Opciones avanzadas → Memoria virtual → Sin archivo de paginación → Reiniciar
```
Tras reiniciar repite el "Reducir volumen". Después puedes reactivar pagefile y restore points.

### 2.6. Apagado total
**Apaga, no reinicies, no suspendas.** Cierra Windows con un apagado completo (Inicio → Apagar) para que ningún bloqueo del SO quede sobre el disco.

---

## 3. Configuración de la BIOS/UEFI

Entra a la BIOS al arrancar (`Supr` o `F2` en la mayoría de placas AM4). Cambia:

| Opción | Valor | Por qué |
|---|---|---|
| **Boot Mode** | UEFI (no Legacy/CSM) | Windows ya está en UEFI; Linux también. CSM puede ensuciar el orden de boot. |
| **Secure Boot** | **Disabled** | Los kernels custom de CachyOS/Hyprland y los módulos NVIDIA no están firmados para Secure Boot por defecto. Documentado en `omarchy.org` y todos los blogs. |
| **TPM** | Puedes dejarlo activo, pero si BitLocker estaba atado a TPM, mejor desactivar temporalmente para no romper la cadena | — |
| **Fast Boot** (firmware) | Disabled | Permite que se reconozcan los USB en POST. |
| **CSM** | Disabled | Conflicto con UEFI puro. |
| **SVM / IOMMU** | **Enabled** | Necesario para virtualización futura (VFIO, KVM) y para algunos drivers. |
| **AMD CBS / PCIe Resizable BAR / Re-Size BAR** | **Enabled** | Mejora notable de rendimiento de la RTX 3080 Ti en Linux. |
| **Above 4G Decoding** | **Enabled** | Requerido por Resizable BAR. |
| **XMP / DOCP / EXPO** | **Enabled** (perfil 1) | Para que tus 64 GB DDR4 corran a su velocidad anunciada. |
| **NVMe / SATA Mode** | **AHCI** (no RAID) | RAID rompe la detección del NVMe en Linux. Si estaba en RAID, cambia y reinstala Windows; no se puede convertir sin reinstalar. |
| **Boot order temporal** | USB primero | Para que arranque el ISO de CachyOS. |

Guarda y apaga.

---

## 4. Crear USB de CachyOS

### 4.1. Descarga e integridad
1. Descarga la ISO **Desktop** desde <https://cachyos.org/download/> (carpeta `ISO/desktop/<fecha>/`).
2. Descarga el `.sha256` del mismo directorio.
3. Verifica en PowerShell:

   ```powershell
   Get-FileHash .\cachyos-desktop-linux-*.iso -Algorithm SHA256
   ```
   Compara la salida con el contenido del fichero `.sha256`. Deben coincidir carácter por carácter.

### 4.2. Grabar el USB
Usa un USB de ≥ 8 GB. Dos opciones:

**Opción recomendada — Ventoy** (permite reusar el USB para más ISOs):
1. Instala Ventoy desde <https://www.ventoy.net/>.
2. Formatea el USB con Ventoy2Disk.
3. Copia la ISO de CachyOS al USB como un archivo más.

**Opción alternativa — Rufus**:
1. Rufus 4.x → seleccionar ISO → modo **DD** (no ISO image). Tipo de partición: **GPT**. Sistema destino: **UEFI**.

> **Evita Etcher en sistemas UEFI con CachyOS reciente:** algunos usuarios reportan ISOs que no arrancan. Rufus DD-mode o Ventoy son los probados.

---

## 5. Arranque del live y comprobaciones previas a instalar

1. Inserta el USB, enciende, F12/F11 (boot menu) → arranca el USB.
2. En el menú GRUB del live elige **"CachyOS Linux (proprietary nvidia drivers)"** porque tienes una RTX 3080 Ti. *No* el de drivers open source.
3. Espera al escritorio live de KDE.

### 5.1. Verifica que la RTX 3080 Ti y la red funcionan en el live
Abre una terminal (Konsole) y ejecuta:

```bash
lspci -k | grep -EA3 'VGA|3D'   # debe listar tu RTX 3080 Ti con driver "nvidia" en uso
nvidia-smi                       # debe mostrar la GPU y el driver
ping -c 3 archlinux.org          # red
```

- `lspci -k`: enumera dispositivos PCI con el kernel module asociado. Confirma que el driver propietario está cargado.
- `nvidia-smi`: utilidad del driver propietario. Si responde con la GPU, todo bien.
- `ping`: comprueba conectividad. Si falla, ve a la bandeja del live y configura la conexión.

Si `nvidia-smi` no funciona en el live: reinicia y elige el live con drivers open-source para hacer la instalación; luego ya cambiamos.

---

## 6. Instalación de CachyOS con Calamares (dual boot)

Lanza **"Install CachyOS"** desde el escritorio live.

### 6.1. Pasos comunes
- Idioma: Español.
- Región: Europe/Madrid.
- Teclado: Spanish (Spain).

### 6.2. Pantalla de particionado — LA PANTALLA CRÍTICA

> **Importante**: la opción **"Instalar junto con otros sistemas"** (Install alongside) de Calamares es la causa #1 de fallos en dual boot. Funciona a veces, falla otras. Toda la comunidad (itsfoss, CachyOS forum) recomienda **particionado manual**.

#### Opción A — un solo disco (Windows + CachyOS)

1. Selecciona **"Manual partitioning"**.
2. Verás algo como:
   - `/dev/nvme0n1p1` FAT32 ~500 MB-2 GB → **ESP existente de Windows**
   - `/dev/nvme0n1p2` NTFS pequeña → Microsoft Reserved
   - `/dev/nvme0n1p3` NTFS grande → C: de Windows
   - `/dev/nvme0n1p4` NTFS pequeña → Recovery
   - **Espacio libre** ~300-500 GB → el que liberaste

3. Sobre el ESP (`p1`):
   - **Edit** → Mount point: `/boot/efi` → **NO marques "Format"** → flags: `boot`, `esp`.

4. En el espacio libre crea una sola partición:
   - **Create** → File System: **btrfs** → Mount point: `/` → Size: todo el espacio.
   - Etiqueta: `cachy_root` (opcional pero útil).

5. **NO crees /home separado**. CachyOS usa subvolúmenes BTRFS (`@`, `@home`, `@snapshots`) dentro de la misma partición; Calamares lo configura solo si dejas todo en `/`.

6. **NO crees swap como partición.** Con 64 GB de RAM no la necesitas; Calamares creará swapfile o zram si lo eliges en una pantalla posterior.

#### Opción B — dos discos (recomendado para ti)

Asumimos `nvme0n1` = Windows, `nvme1n1` = nuevo para Linux.

1. Sobre `nvme1n1` (el disco vacío para Linux):
   - **Create New Partition Table** → **GPT**.
2. Crea partición ESP en `nvme1n1`:
   - **Create** → 1 GB → FileSystem **fat32** → Mount point `/boot/efi` → flags: `boot`, `esp`.
   - **No toques el ESP del disco de Windows.**
3. Crea la raíz:
   - **Create** → resto del espacio → **btrfs** → Mount point `/`.

> Con Opción B tendrás un ESP por disco. Para conmutar entre Windows y Linux usarás el menú de arranque de la placa (F12), no GRUB. Es más limpio y a prueba de actualizaciones de Windows que rompen el bootloader.

### 6.3. Resto de opciones de Calamares (obligatorias para Omarchy después)

| Opción | Valor |
|---|---|
| Bootloader | **GRUB** (opción A) o **systemd-boot** (opción B si quieres usar F12 sin GRUB) |
| File System cifrado | **No** en VM... aquí en bare metal, **opcional**. Si tienes el equipo en sitio físicamente seguro, déjalo sin cifrar para no complicar Snapper. Si vas con el portátil/torre por ahí, marca LUKS — pero usa teclado **cableado**, no Bluetooth (Nate Gentile y otros confirman que en la pantalla de unlock el BT no responde). |
| Sistema de ficheros | **BTRFS** (obligatorio para Omarchy-on-CachyOS) |
| Snapshots | **Snapper** (auto-configurado) |
| Kernel | **linux-cachyos** |
| Desktop Environment | **Hyprland** (la opción "CachyOS Hyprland") o **No Desktop**. Nunca GNOME/KDE. |
| Shell | **Fish** (obligatorio) |
| Paquetes opcionales | `base-devel`. Desmarca todo gaming/office si quieres minimal. |
| Usuario | Tu nombre, hostname (ej. `cachy-ryzen`). Activa "Use same password for admin". |

### 6.4. Instala
Pulsa Install, espera 15-25 minutos. **No marques "Reboot now"** al terminar. Apaga la VM... perdón, apaga la máquina (Inicio → Apagar) y retira el USB físicamente.

---

## 7. Primer arranque y verificación de dual boot

### 7.1. Arrancar la primera vez
Enciende. Verás:

- **Opción A (un disco):** menú GRUB con "CachyOS Linux" y "Windows Boot Manager".
- **Opción B (dos discos):** GRUB con CachyOS por defecto. Para entrar a Windows pulsa F12 (menú de arranque) y elige el SSD de Windows.

### 7.2. Si Windows NO aparece en GRUB (Opción A)
Es un clásico, lo documenta itsfoss. Solución:

```bash
sudo nano /etc/default/grub
```
Busca y descomenta (quitando `#`):
```
GRUB_DISABLE_OS_PROBER=false
```
Guarda. Luego:
```bash
sudo pacman -S os-prober ntfs-3g
sudo grub-mkconfig -o /boot/grub/grub.cfg
```

- `os-prober`: escanea otros sistemas operativos en los discos.
- `ntfs-3g`: driver NTFS necesario para que os-prober lea la partición Windows.
- `grub-mkconfig`: regenera el menú GRUB con las entradas detectadas.

Reinicia: ya debe salir Windows.

### 7.3. Pantalla negra tras logo CachyOS (NVIDIA)
La RTX 3080 Ti con drivers ≥570 a veces queda en negro tras el splash. Si te pasa:

1. En GRUB, posicionate sobre "CachyOS Linux" → pulsa `e` para editar.
2. En la línea `linux ... quiet splash` añade al final, antes del salto de línea:
   ```
   nvidia_drm.modeset=1 nvidia.NVreg_PreserveVideoMemoryAllocations=1
   ```
3. `Ctrl+X` para arrancar.

Si funciona, hazlo permanente:
```bash
sudo nano /etc/default/grub
# En GRUB_CMDLINE_LINUX_DEFAULT añade lo mismo
sudo grub-mkconfig -o /boot/grub/grub.cfg
```

- `nvidia_drm.modeset=1`: activa Direct Rendering Manager mode-setting; **imprescindible para Wayland/Hyprland con NVIDIA**.
- `NVreg_PreserveVideoMemoryAllocations=1`: preserva memoria de la GPU al suspender/resumir. Sin esto, suspender congela la sesión.

### 7.4. Sincronización de hora con Windows
Windows usa hora local, Linux usa UTC → el reloj baila al alternar. Solución más simple (en CachyOS):

```bash
sudo timedatectl set-local-rtc 1 --adjust-system-clock
timedatectl status
```

- `set-local-rtc 1`: indica a Linux que el reloj de la BIOS está en hora local (como Windows).
- `--adjust-system-clock`: corrige inmediatamente.
- `timedatectl status`: confirma. Debe poner "RTC in local TZ: yes".

---

## 8. Configuración de NVIDIA en CachyOS antes de instalar Omarchy

Lo que sigue es **lo que se salta el script de Omarchy y por eso falla a Nate y a tantos otros**. Hazlo TÚ a mano y de forma controlada.

### 8.1. Comprueba qué driver tienes
```bash
nvidia-smi
pacman -Q | grep nvidia
```

Deberías ver `nvidia-dkms` o `nvidia` (módulo) + `nvidia-utils` (userland) + `lib32-nvidia-utils`. La versión del driver será 565.x, 570.x o 580.x (a fecha de mayo 2026, **580.xx es la rama estable recomendada para Ampere**).

### 8.2. Para RTX 3080 Ti (Ampere): driver propietario o `nvidia-open`
Hay debate. La Hyprland Wiki recomienda **`nvidia-open-dkms`** para Turing y superior (RTX 20/30/40), pero la realidad es que para Ampere el propietario `nvidia-dkms` 580.x funciona perfectamente con Hyprland en CachyOS y es lo que el script de Omarchy fuerza.

**Recomendación pragmática (probada):** quédate con el propietario `nvidia-dkms` 580.x que ya trae CachyOS si lo instalaste con la ISO propietaria. Si te pasas a `nvidia-open-dkms`:

```bash
sudo pacman -S linux-cachyos-headers nvidia-open-dkms nvidia-utils lib32-nvidia-utils libva-nvidia-driver egl-wayland
```

- `linux-cachyos-headers`: headers del kernel necesarios para DKMS (compila el módulo contra tu kernel).
- `nvidia-open-dkms`: módulo kernel open-source de NVIDIA (sigue siendo propietario en userland; "open" se refiere al kernel module, no al driver completo).
- `nvidia-utils`: bibliotecas userland (libGL, libcuda, etc.).
- `lib32-nvidia-utils`: equivalentes 32-bit, imprescindibles para Steam/Wine/Proton.
- `libva-nvidia-driver`: backend VA-API para decodificar vídeo por hardware en navegadores.
- `egl-wayland`: puente EGL ↔ Wayland, imprescindible para Hyprland.

### 8.3. Configurar mkinitcpio para cargar los módulos pronto
```bash
sudo nano /etc/mkinitcpio.conf
```
Localiza la línea `MODULES=(...)` y déjala así:
```
MODULES=(nvidia nvidia_modeset nvidia_uvm nvidia_drm)
```
Localiza `HOOKS=(...)` y **quita `kms`** si está (causa conflicto con KMS de NVIDIA).

Regenera el initramfs:
```bash
sudo mkinitcpio -P
```

- `mkinitcpio -P`: regenera **todos** los presets de initramfs. Tras esto los módulos NVIDIA se cargan antes que el resto, evitando flickering al inicio.

### 8.4. Configurar modprobe
```bash
sudo nano /etc/modprobe.d/nvidia.conf
```
Contenido:
```
options nvidia_drm modeset=1 fbdev=1
options nvidia NVreg_PreserveVideoMemoryAllocations=1
options nvidia NVreg_UsePageAttributeTable=1
options nvidia NVreg_EnableGpuFirmware=1
```

- `nvidia_drm modeset=1`: ya explicado. Esencial para Wayland.
- `fbdev=1`: framebuffer device; mejora el handover de TTY a Wayland (sin texto roto).
- `NVreg_PreserveVideoMemoryAllocations=1`: para suspender/hibernar.
- `NVreg_UsePageAttributeTable=1`: mejora de rendimiento documentada para Ampere.
- `NVreg_EnableGpuFirmware=1`: habilita GSP firmware, requerido por drivers 555+.

Regenera de nuevo y activa los servicios de suspensión:
```bash
sudo mkinitcpio -P
sudo systemctl enable nvidia-suspend.service nvidia-hibernate.service nvidia-resume.service
```

- `systemctl enable`: activa esos servicios al arranque. Manejan la persistencia de VRAM al dormir/despertar el equipo.

### 8.5. Reinicia y valida
```bash
sudo reboot
```
Al volver:
```bash
nvidia-smi
glxinfo | grep "OpenGL renderer"
journalctl -b -p 3   # logs de errores del arranque
```

- `glxinfo`: debe imprimir tu RTX 3080 Ti como renderer.
- `journalctl -b -p 3`: muestra mensajes de prioridad ≤ error desde el último arranque. Idealmente, nada relevante. Si aparece "NVRM: GPU has fallen off the bus" o similar, hay problema de PCIe/BAR (revisa Resizable BAR/Above 4G en BIOS).

---

## 9. Instalación de Omarchy sobre CachyOS

Con NVIDIA ya funcionando estable, lanzar el script de mroboff es la parte fácil.

### 9.1. Pre-condiciones
- [x] BTRFS + Snapper.
- [x] Shell Fish.
- [x] DE: Hyprland CachyOS o minimal sin DE.
- [x] **NVIDIA funcionando antes de empezar.** ESTE es el orden que evita el `nvidia.sh failed` reportado en `mroboff/omarchy-on-cachyos#39`.
- [x] Sistema actualizado: `sudo pacman -Syu`.

### 9.2. Ejecutar el script
```bash
cd ~
git clone https://github.com/mroboff/omarchy-on-cachyos.git
cd omarchy-on-cachyos/bin
chmod +x install-omarchy-on-cachyos.sh
./install-omarchy-on-cachyos.sh
```

Detalle de cada comando: ver §7.2 del documento de VirtualBox (mismo contenido). Lo crítico aquí:

- **Si el script aborta en `nvidia.sh`** (issue #39): edita `install-omarchy-on-cachyos.sh` y comenta la línea que invoca `nvidia.sh`. Ya hiciste la configuración NVIDIA tú a mano en §8, no es necesaria la del script. Luego relanza.
- **Si pide conflictos con paquetes ya existentes**: `sudo pacman -Syu --overwrite '*'` y reintenta. Esto es seguro en una instalación nueva.

### 9.3. Tras el script
```bash
sudo reboot
```
En SDDM elige sesión **Hyprland** (o **omarchy**).

---

## 10. Ajustes post-Omarchy en bare metal con RTX 3080 Ti

### 10.1. Variables de entorno NVIDIA en Hyprland
Edita el fichero de entornos de Hyprland (Omarchy lo separa de `hyprland.conf`):

```bash
nano ~/.config/hypr/envs.conf
```
Añade al final:
```
env = LIBVA_DRIVER_NAME,nvidia
env = __GLX_VENDOR_LIBRARY_NAME,nvidia
env = GBM_BACKEND,nvidia-drm
env = NVD_BACKEND,direct
env = MOZ_ENABLE_WAYLAND,1
env = ELECTRON_OZONE_PLATFORM_HINT,auto
env = __GL_MaxFramesAllowed,1
env = __GL_VRR_ALLOWED,0
env = VDPAU_DRIVER,nvidia
```

Significado de cada una:

- `LIBVA_DRIVER_NAME=nvidia`: aceleración de vídeo VA-API en navegadores (con `libva-nvidia-driver` que instalaste).
- `__GLX_VENDOR_LIBRARY_NAME=nvidia`: que GLX use el ICD de NVIDIA.
- `GBM_BACKEND=nvidia-drm`: backend GBM (Generic Buffer Management) que usa Wayland. **Atención: puede crashear Firefox; si pasa, comenta esta línea.**
- `NVD_BACKEND=direct`: backend de NVDEC.
- `MOZ_ENABLE_WAYLAND=1`: Firefox nativo Wayland.
- `ELECTRON_OZONE_PLATFORM_HINT=auto`: VSCode, Discord, Slack en Wayland nativo.
- `__GL_MaxFramesAllowed=1`: reduce input lag.
- `__GL_VRR_ALLOWED=0`: desactiva VRR; en Hyprland+NVIDIA causa flickering en muchos monitores. Si tu monitor lo soporta y quieres VRR, ponlo a 1 y prueba.
- `VDPAU_DRIVER=nvidia`: aceleración VDPAU.

### 10.2. Tearing y flickering — Explicit Sync
Hyprland desde la versión que usa Omarchy 3.x soporta **Explicit Sync**, que es lo que arregla los problemas históricos NVIDIA. Verifica que está activo en `hyprland.conf`:

```bash
grep explicit_sync ~/.config/hypr/hyprland.conf
```
Debe aparecer (en `render { }`):
```
render {
  explicit_sync = 2
  explicit_sync_kms = 2
}
```
Si está a `0`, ponlo a `2` (auto). Con driver 555+ y Explicit Sync 2 el flickering documentado en `hyprland#3946` y `omarchy#3024` desaparece.

### 10.3. Gaming: Steam y Proton
```bash
sudo pacman -S steam lib32-vulkan-icd-loader vulkan-icd-loader gamemode lib32-gamemode mangohud lib32-mangohud
```

- `steam`: cliente.
- `vulkan-icd-loader` (+ lib32): cargador Vulkan necesario por Proton/DXVK.
- `gamemode`: ajusta CPU/IO scheduler durante el juego. Para usarlo en Steam: en las "Launch options" pon `gamemoderun %command%`.
- `mangohud`: overlay de FPS/usage.

Para juegos con flickering específico en NVIDIA + Wayland, Gamescope es el comodín:
```bash
sudo pacman -S gamescope
```
Launch option en Steam:
```
gamescope -W 2560 -H 1440 -r 144 -f -- %command%
```
- `gamescope`: micro-compositor que aísla el juego del compositor (Hyprland) y suele eliminar tearing en NVIDIA. Ajusta `-W -H -r` a tu monitor.

### 10.4. Audio (PipeWire ya viene)
Verifica:
```bash
systemctl --user status pipewire wireplumber pipewire-pulse
```
Todos deben estar `active (running)`. Si no:
```bash
systemctl --user enable --now pipewire wireplumber pipewire-pulse
```

### 10.5. Snapshot inicial Snapper
Antes de tocar nada más, crea snapshot:
```bash
sudo snapper -c root create --description "post-omarchy-nvidia-ok"
sudo snapper -c root list
```
Si una actualización futura rompe el sistema, GRUB → "Snapshots" → arrancar uno previo → `sudo snapper rollback <id>`.

---

## 11. Plan futuro: eliminar Windows y dar todo el disco a CachyOS

Cuando estés cómodo en CachyOS y decidas eliminar Windows:

### 11.1. Backup del ESP por si acaso
```bash
sudo cp -r /boot/efi /root/efi-backup
```

### 11.2. Borrar las particiones de Windows

#### Opción A (un disco):
1. Arranca el live de CachyOS (USB) o, en el sistema instalado, abre GParted como root.
   ```bash
   sudo pacman -S gparted
   sudo gparted
   ```
2. En GParted, identifica las particiones de Windows (NTFS grande, Microsoft Reserved, Recovery).
3. **Desmonta cualquier partición de Windows si está montada** (botón derecho → unmount).
4. Borra todas las NTFS y la Microsoft Reserved. **NO toques el ESP** (`/boot/efi`).
5. Aplica cambios.
6. Ahora redimensiona la partición BTRFS para ocupar el espacio liberado:
   - Click derecho sobre `/dev/nvme0n1pX` (BTRFS) → Resize/Move → arrastra el borde derecho al máximo → Apply.

Tras reiniciar, expande el sistema de ficheros BTRFS online:
```bash
sudo btrfs filesystem resize max /
```
- `btrfs filesystem resize max /`: hace que BTRFS use todo el espacio de su partición. Es online, no requiere unmount.

Limpia las entradas EFI de Windows:
```bash
sudo efibootmgr -v
# localiza "Windows Boot Manager" y su Boot####
sudo efibootmgr -b XXXX -B   # XXXX = número de la entrada Windows
```
- `efibootmgr -v`: lista las entradas de boot UEFI.
- `efibootmgr -b XXXX -B`: borra la entrada XXXX.

Regenera GRUB para que ya no busque Windows:
```bash
sudo grub-mkconfig -o /boot/grub/grub.cfg
```

#### Opción B (dos discos):
Más simple aún:
1. Formatea el disco completo de Windows desde GParted.
2. Úsalo como `/home` separado o como almacén de juegos:
   ```bash
   sudo mkfs.btrfs /dev/nvme0n1
   sudo blkid /dev/nvme0n1   # apunta el UUID
   sudo nano /etc/fstab
   # Añade: UUID=xxx  /mnt/games  btrfs  defaults,compress=zstd  0 0
   sudo mkdir /mnt/games
   sudo mount -a
   ```
3. Elimina la entrada EFI de Windows con `efibootmgr` como en la opción A.

### 11.3. (Opcional) Quitar `set-local-rtc`
Si ya no usas Windows:
```bash
sudo timedatectl set-local-rtc 0 --adjust-system-clock
```
Vuelves a UTC, que es lo "correcto" en Linux.

---

## 12. Tabla resumen de fallos esperables y solución comando-por-comando

| Síntoma | Comando(s) | Qué hace |
|---|---|---|
| Windows pierde particiones tras instalar | `sudo pacman -S os-prober ntfs-3g && sudo sed -i 's/^#GRUB_DISABLE_OS_PROBER=false/GRUB_DISABLE_OS_PROBER=false/' /etc/default/grub && sudo grub-mkconfig -o /boot/grub/grub.cfg` | Activa la detección de otros SO en GRUB y regenera el menú |
| Pantalla negra tras logo CachyOS (NVIDIA) | En GRUB, `e` y añadir `nvidia_drm.modeset=1 nvidia.NVreg_PreserveVideoMemoryAllocations=1`; luego permanente en `/etc/default/grub` + `grub-mkconfig` | Activa KMS de NVIDIA y conserva VRAM en suspensión |
| `nvidia.sh failed` del script Omarchy (issue #39) | Comentar la línea `bash nvidia.sh` en `install-omarchy-on-cachyos.sh` y relanzar | Saltar pasos NVIDIA del script porque ya están hechos manualmente |
| Conflictos de paquetes al instalar Omarchy | `sudo pacman -Syu --overwrite '*'` | Permite sobrescribir ficheros que Omarchy quiere reemplazar |
| Reloj baila entre Win y Linux | `sudo timedatectl set-local-rtc 1 --adjust-system-clock` | Linux acepta hora local como Windows |
| Hyprland flickering en monitor externo | Añadir `env = __GL_VRR_ALLOWED,0` y `render { explicit_sync = 2; explicit_sync_kms = 2 }` | Desactiva VRR y activa Explicit Sync (driver 555+) |
| Tearing en juegos | Lanzar el juego con `gamescope -W ... -H ... -r ... -f -- %command%` | Compositor aislado entre el juego y Hyprland |
| Suspend congela el equipo | `sudo systemctl enable nvidia-suspend.service nvidia-hibernate.service nvidia-resume.service` + `NVreg_PreserveVideoMemoryAllocations=1` | Servicios oficiales NVIDIA para suspend/resume |
| Hyprland se reinicia en bucle | `omarchy-reinstall` desde TTY | Restaura dotfiles por defecto de Omarchy |
| Lockout por contraseña fallida | TTY (`Ctrl+Alt+F2`) → `sudo faillock --reset --user <user>` | Resetea contador PAM |
| Update rompe arranque | GRUB → Snapshots → arrancar uno previo → `sudo snapper rollback <id>` | Rollback BTRFS al estado previo |
| Resizable BAR no funciona | BIOS: Above 4G Decoding ON + Re-Size BAR ON + CSM OFF | Requisito de hardware/firmware para Resizable BAR |
| BT no responde en pantalla LUKS | Conectar teclado USB para descifrar; cambiar a BT después del unlock | El initramfs no incluye stack BT |
| Audio sin salida | Click en waybar (icono altavoz) → elegir sink correcto. Si nada: `systemctl --user restart pipewire wireplumber pipewire-pulse` | Cambia sink default / reinicia stack PipeWire |
| Tras Windows Update se pierde GRUB | Live USB CachyOS → `arch-chroot` → `grub-install --target=x86_64-efi --efi-directory=/boot/efi --bootloader-id=CachyOS` + `grub-mkconfig` | Reinstala GRUB en el ESP que Windows machacó |
| `GPU has fallen off the bus` en `dmesg` | BIOS: PCIe slot → Gen3 (no Auto), Re-Size BAR ON, fuente >850W estable; cable PCIe 8+8 dedicado | Problema de estabilidad de PCIe/alimentación de la GPU |

---

## 13. Lo que aprendimos de la experiencia de Nate Gentile

Los problemas que comparte Nate en sus vídeos (y que esta guía neutraliza):

1. **Omarchy oficial pide disco completo** → resuelto usando CachyOS como base + script de mroboff (§9).
2. **Drivers NVIDIA conflictivos** → resuelto haciendo la configuración NVIDIA manualmente ANTES de Omarchy (§8) y saltando `nvidia.sh` del script si falla (§9.2).
3. **Teclado Bluetooth no funciona en la pantalla de cifrado** → usar teclado USB cableado al menos hasta entrar en sesión (§6.3).
4. **Limine/GRUB no detecta Windows** → activar `os-prober` (§7.2) o usar la **Opción B con dos discos y F12** que evita este problema entero.
5. **Curva de aprendizaje Hyprland** → Omarchy mitiga esto con `Super + K` (cheatsheet) y `Super + Alt + Space` (menú Omarchy). Léelos.

---

## 14. Fuentes consultadas y validadas

- **CachyOS wiki — Instalación Desktop/Laptop:** <https://wiki.cachyos.org/installation/installation_on_root/>
- **CachyOS wiki — Gaming Guide (NVIDIA):** <https://wiki.cachyos.org/configuration/gaming/>
- **CachyOS wiki — Dual GPU setup:** <https://wiki.cachyos.org/configuration/dual_gpu/>
- **itsfoss — Dual Booting CachyOS and Windows:** <https://itsfoss.com/dual-boot-cachyos-windows/>
- **itsfoss — Wrong Time in Windows 10 After Dual Boot:** <https://itsfoss.com/wrong-time-dual-boot/>
- **GitHub — auswizard/Dual-Boot-Win11-CachyOS (ESP de 2 GB):** <https://github.com/auswizard/Dual-Boot-Win11-CachyOS>
- **CachyOS Forum — CachyOS with NVIDIA and Gaming:** <https://discuss.cachyos.org/t/cachyos-with-nvidia-and-gaming/13055>
- **CachyOS Forum — Cachyos w/NVIDIA drivers and Hyprland:** <https://discuss.cachyos.org/t/cachyos-w-nvidia-drivers-and-hyprland/592>
- **CachyOS Forum — Dual boot Windows 10 issues:** <https://discuss.cachyos.org/t/need-assistance-with-dual-booting-windows-10-and-cachyos/16589>
- **ArchWiki — Dual boot with Windows:** <https://wiki.archlinux.org/title/Dual_boot_with_Windows>
- **Hyprland Wiki — NVIDIA:** <https://wiki.hypr.land/Nvidia/>
- **Hyprland DeepWiki — NVIDIA Configuration:** <https://deepwiki.com/hyprwm/hyprland-wiki/7.2-nvidia-configuration>
- **GitHub gist — Hyprland Env Vars NVIDIA:** <https://gist.github.com/kRHYME7/1d2574e8f3a4b7ad4059535503ce1eaa>
- **GitHub — mroboff/omarchy-on-cachyos:** <https://github.com/mroboff/omarchy-on-cachyos>
- **GitHub Issue — nvidia drivers problem (#39):** <https://github.com/mroboff/omarchy-on-cachyos/issues/39>
- **GitHub Discussion — Omarchy black screen after NVIDIA update (#3024):** <https://github.com/basecamp/omarchy/discussions/3024>
- **GitHub Discussion — Too-new NVIDIA drivers preinstalled (#3382):** <https://github.com/basecamp/omarchy/discussions/3382>
- **GitHub Issue — Hyprland random black screen on startup (#2845):** <https://github.com/basecamp/omarchy/issues/2845>
- **GitHub Issue — Hyprland NVIDIA external monitor flickering (#9691):** <https://github.com/hyprwm/Hyprland/issues/9691>
- **GitHub Issue — Hyprland NVIDIA screen flickering (#3946):** <https://github.com/hyprwm/Hyprland/issues/3946>
- **Manual oficial Omarchy — Troubleshooting:** <https://learn.omacom.io/2/the-omarchy-manual/88/troubleshooting>
- **Blog yoprogramo — Explorando Omarchy (experiencia hispana):** <https://yoprogramo.com/2026/05/15/explorando-omarchy/>
- **Forocoches — Nate Gentile abandona Windows:** <https://forocoches.com/foro/showthread.php?t=10677224>
- **YouTube — Nate Gentile se pasa a Linux:** <https://www.youtube.com/watch?v=Tu8EFvsiGqY>
- **YouTube — Problemas con Tarjetas Nvidia (respuesta a Omarchy/CachyOS):** <https://www.youtube.com/watch?v=xFNd0CVpXrQ>
- **ArchLinux Forums — NVIDIA screen flicker fix:** <https://bbs.archlinux.org/viewtopic.php?id=290084>
- **Kextcache — Wayland NVIDIA complete fix 2025:** <https://kextcache.com/wayland-nvidia-a-definite-2025-guide/>

---

## 15. Orden recomendado de ejecución (checklist final)

1. [ ] Backup completo de Windows (imagen + datos personales).
2. [ ] BIOS actualizada, ajustes de §3 aplicados.
3. [ ] Windows: Fast Startup OFF, hibernación OFF, BitLocker OFF, restore OFF temporal.
4. [ ] Verificar ESP ≥ 500 MB. Si no → reinstalar Windows con ESP 2 GB (auswizard).
5. [ ] Reducir C: desde Disk Management de Windows (Opción A) o añadir 2º disco (Opción B).
6. [ ] Apagar Windows con apagado total.
7. [ ] Crear USB con Rufus DD-mode o Ventoy.
8. [ ] Arrancar live de CachyOS (propietario NVIDIA).
9. [ ] Verificar GPU/red en live.
10. [ ] Calamares con particionado manual: ESP existente (no formatear) + BTRFS en espacio libre / disco vacío.
11. [ ] Hyprland + Fish + Snapper + GRUB.
12. [ ] Reiniciar, retirar USB.
13. [ ] Restaurar Windows en GRUB si falta (`os-prober`).
14. [ ] `set-local-rtc 1` para hora.
15. [ ] Configurar NVIDIA a mano (§8): modprobe, mkinitcpio, servicios suspend.
16. [ ] Reiniciar, validar con `nvidia-smi` y `journalctl -b -p 3`.
17. [ ] Lanzar `omarchy-on-cachyos` (saltar `nvidia.sh` si falla).
18. [ ] Reiniciar a sesión Hyprland.
19. [ ] Aplicar `envs.conf` con vars NVIDIA + `explicit_sync = 2`.
20. [ ] Instalar Steam, gamescope, gamemode.
21. [ ] Snapshot Snapper inicial.
22. [ ] Disfrutar.
23. [ ] (Futuro) Eliminar Windows (§11) cuando estés cómodo.

> **Regla de oro:** después de cada uno de los pasos 11, 16, 18, 20, **crea un snapshot Snapper** (`sudo snapper -c root create -d "descripción"`). Si algo se rompe, vuelves al estado anterior desde GRUB en 30 segundos. Es la red de seguridad que NO tiene Windows y que es la mayor ventaja real de esta instalación.
