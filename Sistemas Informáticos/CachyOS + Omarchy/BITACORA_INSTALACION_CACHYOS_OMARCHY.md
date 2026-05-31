# Bitácora de instalación: CachyOS + Omarchy

> **Documento operativo**, no manual teórico. Contiene la **cronología real** de la instalación llevada a cabo el **2026-05-24** en VirtualBox 7.2.8 sobre Windows 10 (Ryzen 9 5900X + RTX 3080 Ti + 64 GB RAM), todos los **fallos que surgieron**, las **causas raíz** que encontré, los **fixes que funcionaron** y un **procedimiento limpio reproducible** derivado de esa experiencia. Incluye además un plan de transición de **dual boot → bare metal puro** que aplica esas mismas lecciones cuando llegue el momento de eliminar Windows.
>
> **Tono:** ingenieril, sin atajos pedagógicos. Cada comando lleva la explicación de qué hace y por qué se ejecuta así. Cada error está documentado con su mensaje literal para que sea buscable.

---

## Tabla de contenidos

- [0. Resumen ejecutivo y lecciones críticas](#0-resumen-ejecutivo-y-lecciones-críticas)
- [1. Cronología real de la instalación](#1-cronología-real-de-la-instalación)
- [2. Procedimiento limpio para VirtualBox (versión definitiva)](#2-procedimiento-limpio-para-virtualbox-versión-definitiva)
- [3. Catálogo de problemas encontrados y soluciones aplicadas](#3-catálogo-de-problemas-encontrados-y-soluciones-aplicadas)
- [4. Procedimiento para dual boot bare-metal con Windows 10](#4-procedimiento-para-dual-boot-bare-metal-con-windows-10)
- [5. Migración dual boot → bare metal puro (eliminar Windows)](#5-migración-dual-boot--bare-metal-puro-eliminar-windows)
- [6. Comandos y cheat-sheet de operación diaria](#6-comandos-y-cheat-sheet-de-operación-diaria)
- [7. Apéndice A: tabla de fallos con su comando-solución](#7-apéndice-a-tabla-de-fallos-con-su-comando-solución)
- [8. Apéndice B: scripts útiles listos para pegar](#8-apéndice-b-scripts-útiles-listos-para-pegar)
- [9. Apéndice C: salida de los comandos de verificación](#9-apéndice-c-salida-de-los-comandos-de-verificación)
- [10. Referencias usadas y validadas durante la instalación real](#10-referencias-usadas-y-validadas-durante-la-instalación-real)

---

## 0. Resumen ejecutivo y lecciones críticas

### 0.1. Lo que terminó funcionando

| Componente | Valor que funcionó |
|---|---|
| VirtualBox | **7.2.8** + Extension Pack 7.2.8 |
| Controlador gráfico | **VBoxSVGA** (NO VMSVGA) |
| VRAM | 128 MB |
| Aceleración 3D | Desactivada |
| Firmware | EFI activado |
| RAM / CPU | 8192 MB / 4 vCPU, nested-hw-virt on |
| Disco | 40 GB VDI dinámico **alojado en un disco con espacio sobrado** (no en C:) |
| Filesystem en el guest | BTRFS + Snapper |
| Bootloader | GRUB (no Limine) |
| Shell por defecto | **fish** (clave para `omarchy-on-cachyos`) |
| DE pre-Omarchy | "No Desktop / Minimal" (Hyprland se instala con el script) |
| Script Omarchy | `mroboff/omarchy-on-cachyos` |
| Resize de pantalla | `wlr-randr` manual + `monitor=VGA-1,preferred,auto,<escala>` en `~/.config/hypr/monitors.conf` |
| Escala recomendada | **1.0** para máxima nitidez, **1.2** si el texto queda demasiado pequeño |

### 0.2. Lecciones más caras (las que rompieron el sistema en su momento)

Lista priorizada — leyendo esto se evita el 95% de los problemas que tuve.

1. **El disco virtual NO puede vivir en la unidad del SO host si esa unidad está al límite.** Mi C: tenía 0.7 GB libres y el `pacman` de la fase de instalación de Omarchy (~6.8 GB descomprimidos) provocó `VERR_DISK_FULL` y la VM se quedó en estado `Paused` con la transacción de paquetes a medias. Resolución: apagar VM, `unregistervm` sin borrar, mover toda la carpeta a otro disco con `robocopy /MOVE`, `registervm` del `.vbox` en su nueva ubicación, restaurar snapshot previo a la rotura. Coste: ~25 minutos perdidos.
2. **Calamares puede ignorar la opción "shell por defecto"** o no exponerla en todas las builds. Aunque marques fish, el sistema queda con bash. Comprobarlo justo después del primer login (`echo $SHELL`) y, si no es `/usr/bin/fish`, hacer `chsh -s /usr/bin/fish <usuario>` **antes** de lanzar el script de Omarchy.
3. **Snapshots "live" + restauración tienen efectos secundarios extraños.** Tras restaurar un snapshot live (mientras la VM corría), los dispositivos virtuales (teclado/ratón) pueden quedar en un estado donde no aceptan inputs (ni los del propio host ni los inyectados con `VBoxManage keyboardputstring`). Solución: tras restaurar un snapshot live, hacer `VBoxManage controlvm <vm> reset` para arrancar limpio. Si vas a hacer cambios destructivos, prefiere `acpipowerbutton` → snapshot **en estado `poweroff`** → modificar.
4. **VMSVGA rompe Hyprland en este stack.** El doc original lo avisaba pero merece reiterarse: cambiar el controlador gráfico de VBoxSVGA a VMSVGA "para tener resize automático" deja la VM con pantalla completamente negra tras el boot (SDDM/Hyprland no arranca). Hay que dejar VBoxSVGA aunque eso signifique que el resize de la ventana de la VM no se propague al guest sin acción manual.
5. **El script `omarchy-on-cachyos` falla con dos errores típicos en CachyOS rolling:**
   - **`libcpptrace.so=1-64` no satisfecha**: la versión del paquete `cpptrace` del repo `cachyos` no provee el soname que pide algún paquete del set base de Omarchy. Pre-instalar **`extra/cpptrace`** (1.0.4-2 en mi caso, que sí provee `libcpptrace.so=1-64`) **antes** del script.
   - **`increase-file-watchers.sh` exit code 1**: el sub-script ejecuta `sudo sysctl --system` que devuelve 1 aunque aplique todos los cambios (algún archivo sysctl.d devuelve no-0). Con `set -e` activo, esto aborta toda la instalación. Parchearlo con `|| true` (ver §3.5).
6. **`sudo` pide la contraseña varias veces porque `makepkg`/`yay` lanzan subshells sin TTY** que no heredan el timestamp de credenciales del padre. Si lo vas a dejar desatendido (o si me lo vas a dejar a mí), o configuras `NOPASSWD` temporal en `/etc/sudoers.d/`, o te quedas delante para introducir la contraseña varias veces.
7. **El primer arranque post-Calamares puede dar pantalla negra** por el bug documentado en `discuss.cachyos.org/t/black-loading-screen-on-first-login-on-vm-in-virtualbox-7-1-12`. Si esperas 120 s y sigue negra: TTY con `Host+F2` (en mi caso AltGr+F2 tras cambiar la host key) → editar GRUB para añadir `nomodeset module_blacklist=vboxdrv,vboxnetflt,vboxnetadp,vboxpci` → `grub-mkconfig` → reboot. En mi instalación NO tuve que aplicarlo, pero conviene tenerlo listo.
8. **Omarchy aplica `scale: 2.0` (retina)** en el monitor incluso aunque el config `monitors.conf` que escribas diga otra cosa. Hay que pisarlo explícitamente con `wlr-randr --output VGA-1 --scale 1.0` o un valor a tu gusto, y persistir con `monitor=VGA-1,preferred,auto,<escala>` en `~/.config/hypr/monitors.conf`.
9. **VirtualBox 7.0.x es insuficiente para esta guía.** El doc de referencia exige 7.1.12+; yo verifiqué que **7.2.8** funciona perfectamente. Si tienes 7.0.x, actualiza antes de empezar.
10. **No confíes en `Reboot Now` del TUI final de Omarchy.** En mi ejecución la tecla Enter no disparó el reboot del TUI; tuve que hacer `VBoxManage controlvm <vm> reset` desde el host. Tener esa salida preparada.

### 0.3. Cosas que **no** son problemas reales aunque parezcan serlo

- "El script de Omarchy avisa que requiere **Limine bootloader**". Es un check defensivo del instalador upstream. El wrapper `omarchy-on-cachyos` ya **elimina** los pasos relacionados con bootloader (ajuste número 7 en sus parches). Responder **Yes** al diálogo "Proceed anyway on your own accord and without assistance?" es lo correcto.
- "El instalador remueve `tldr` y desactiva cambios a `pacman.conf` posteriores". Es **intencional** para no romper el setup de CachyOS. Está en el listado de 10 ajustes que el wrapper aplica.
- "Aparece un aviso de plymouth pendiente: `~/.local/share/omarchy/install/login/plymouth.sh`". Solo aplica si instalaste CachyOS **sin** un DE (modo minimal); el propio script de Omarchy ya integra plymouth en el flujo `install.sh` cuando hay DE detectado. En mi caso lo dejé tal cual y Plymouth funcionó en el siguiente boot.
- "Mensaje `VBoxDRMClient: Error: unable to find DRM device` en el log de VBox". Es informativo. Indica que el cliente DRM-level del Guest Additions no encontró `/dev/dri/card0` (esperado con el controlador VBoxSVGA en Wayland). No bloquea el funcionamiento del escritorio; solo significa que el resize automático no funciona por esa vía.

---

## 1. Cronología real de la instalación

Esta sección es **el log real** de lo que ocurrió, en orden, con los problemas tal cual aparecieron. Sirve de hilo conductor para entender por qué el procedimiento limpio del §2 hace lo que hace.

### 1.1. Punto de partida

- Windows 10 Pro for Workstations.
- VirtualBox **7.0.14** instalado.
- ISO `cachyos-desktop-linux-260426 (1).iso` en `X:\` (1.6 TB libres).
- ISO `omarchy-3.8.0.iso` en `X:\` (descartada — Omarchy se instala por script sobre CachyOS, no desde su ISO independiente).
- Documento de referencia: `INSTALACION_CACHYOS_OMARCHY_VIRTUALBOX.md` del mismo directorio.

### 1.2. Preparación: actualizar VirtualBox

VirtualBox 7.0.14 no cumple el requisito del doc (7.1.12+). Actualicé a **7.2.8** desde virtualbox.org incluyendo Extension Pack 7.2.8. Verificación post-instalación:

```powershell
& "C:\Program Files\Oracle\VirtualBox\VBoxManage.exe" --version
# 7.2.8r173730
& "C:\Program Files\Oracle\VirtualBox\VBoxManage.exe" list extpacks
# Oracle VirtualBox Extension Pack 7.2.8 — Usable: true
```

### 1.3. Creación de la VM por línea de comandos

En lugar de la GUI del Manager, creé la VM en un script PowerShell para garantizar parámetros exactos del §3.1 del doc de referencia:

```powershell
$vb = "C:\Program Files\Oracle\VirtualBox\VBoxManage.exe"
$name = "CachyOS-Omarchy"
$iso = "X:\cachyos-desktop-linux-260426 (1).iso"
$vmDir = "C:\Users\Oliwheel\VirtualBox VMs\$name"
$vdi = "$vmDir\$name.vdi"

# 1) Crear y registrar
& $vb createvm --name $name --ostype ArchLinux_64 --register

# 2) Parámetros del sistema
& $vb modifyvm $name `
  --memory 8192 --cpus 4 --pae on --nested-hw-virt on `
  --firmware efi --rtcuseutc on `
  --nic1 nat `
  --audio-driver dsound --audio-controller hda --audio-enabled on `
  --usbxhci on `
  --boot1 dvd --boot2 disk --boot3 none --boot4 none

# 3) Gráficos - clave
& $vb modifyvm $name --graphicscontroller vboxsvga --vram 128 --accelerate3d off

# 4) Disco virtual
& $vb createmedium disk --filename "$vdi" --size 40960 --format VDI --variant Standard

# 5) Controladores de almacenamiento
& $vb storagectl $name --name "SATA" --add sata --controller IntelAhci --portcount 2 --bootable on
& $vb storagectl $name --name "IDE"  --add ide  --controller PIIX4    --bootable on

# 6) Adjuntar HDD e ISO
& $vb storageattach $name --storagectl "SATA" --port 0 --device 0 --type hdd      --medium "$vdi"
& $vb storageattach $name --storagectl "IDE"  --port 0 --device 0 --type dvddrive --medium "$iso"

# 7) Snapshot inicial
& $vb snapshot $name take "vm-creada" --description "VM recien creada, ISO montada, sin arrancar"
```

> **Decisión clave incorrecta que pagué después:** dejé `$vmDir` en `C:\Users\Oliwheel\VirtualBox VMs\`. Mi C: solo tenía 0.7 GB libres. Lo correcto en una máquina con varios discos es **alojar el VDI en el disco con más espacio desde el principio** (en mi caso, X:). Lo cubre el §2 del procedimiento limpio.

### 1.4. Instalación de CachyOS con Calamares

Arranqué la VM, GRUB live eligió por timeout "CachyOS Linux (open source drivers)" — correcto, **NO** elegir el de NVIDIA en una VM. KDE live arrancó, lancé Calamares y avancé por las pantallas con los parámetros del §5 del doc.

Elecciones realizadas:
- Idioma: Español
- Región/Hora: Europe / Madrid
- Teclado: Spanish (Spain)
- Particionado: Erase disk
- Filesystem: **BTRFS** (clave para Snapper y para el script de Omarchy)
- Bootloader: **GRUB**
- Kernel: linux-cachyos
- Desktop Environment: **No Desktop / Minimal** (Hyprland lo instala Omarchy después)
- Shell por defecto: marqué **fish** (pero el sistema acabó con bash — ver §1.6)
- Paquetes opcionales: `base-devel` marcado
- Usuario: `oliwheel`, "Use same password for admin" marcado
- Reboot now: **desmarcado**

La instalación tardó ~15 minutos. Al ver "Hemos terminado." apagué la VM por **ACPI shutdown** desde el host (más limpio que `Finish` + cierre):

```powershell
& "C:\Program Files\Oracle\VirtualBox\VBoxManage.exe" controlvm "CachyOS-Omarchy" acpipowerbutton
```

Desmonté la ISO y tomé snapshot `cachyos-instalado`:

```powershell
& $vb storageattach $name --storagectl "IDE" --port 0 --device 0 --type dvddrive --medium none
& $vb snapshot $name take "cachyos-instalado" --description "Calamares terminado, ISO desmontada, antes del primer boot"
```

### 1.5. Primer boot y verificación inicial

Reinicio. Plymouth → TTY1 con `cachyos-x8664 login:`. Login como `oliwheel` con la contraseña que puse en Calamares. Comandos de verificación inyectados al teclado virtual con `VBoxManage controlvm <vm> keyboardputstring`:

```bash
echo $SHELL                                         # /bin/bash   ← MAL, debía ser fish
cat /etc/os-release | head -3                       # CachyOS Linux ← OK
[ -d /sys/firmware/efi/efivars ] && echo UEFI_OK    # UEFI_OK ← OK
```

### 1.6. Problema #1: Shell por defecto incorrecta

Calamares no respetó la elección de fish. La sesión inicial estaba en `/bin/bash`. También faltaban paquetes que esperaba tener:

```bash
pacman -Q fish                       # error: el paquete «fish» no se ha encontrado
pacman -Q virtualbox-guest-utils     # error: el paquete «virtualbox-guest-utils» no se ha encontrado
systemctl is-enabled vboxservice     # not-found
```

**Solución aplicada** (un comando combinado para evitar varios prompts de sudo):

```bash
sudo bash -c 'cachyos-rate-mirrors && \
  pacman -Syu --noconfirm && \
  pacman -S --noconfirm fish virtualbox-guest-utils && \
  systemctl enable --now vboxservice && \
  chsh -s /usr/bin/fish oliwheel && \
  echo === DONE-ALL ==='
```

Explicación comando por comando:
- `cachyos-rate-mirrors`: reordena `/etc/pacman.d/cachyos-mirrorlist` por latencia/velocidad. Acelera y estabiliza el resto de descargas.
- `pacman -Syu --noconfirm`: sincroniza, refresca y actualiza todo el sistema sin prompts.
- `pacman -S --noconfirm fish virtualbox-guest-utils`: instala fish y las guest additions del paquete (versión 7.2.8 cuando hice esto).
- `systemctl enable --now vboxservice`: activa el servicio que gestiona portapapeles, carpetas compartidas y resize en X11 (en Wayland el resize no funciona por esta vía).
- `chsh -s /usr/bin/fish oliwheel`: cambia la shell de login del usuario.
- `echo === DONE-ALL ===`: marcador para detectar fin del comando largo cuando se monitoriza por screenshot.

Reboot con `sudo reboot` para que la nueva shell aplique y los módulos vboxguest se carguen.

Verificación post-reboot:

```bash
echo $SHELL                          # /usr/bin/fish ← OK
lsmod | grep vboxguest               # vboxguest 53248 ... (cargado) ← OK
systemctl is-active vboxservice      # active ← OK
pacman -Q fish virtualbox-guest-utils git base-devel | head -10
```

Snapshot del estado bueno antes de meternos con Omarchy: `pre-omarchy`.

### 1.7. Problema #2: `libcpptrace.so=1-64` no satisfecha

Lancé el wrapper:

```bash
cd ~
git clone https://github.com/mroboff/omarchy-on-cachyos.git
cd omarchy-on-cachyos/bin
chmod +x install-omarchy-on-cachyos.sh
./install-omarchy-on-cachyos.sh
```

El script:
1. Clonó el repo upstream `basecamp/omarchy` (~180 MB).
2. Compiló e instaló yay desde AUR.
3. Aplicó 10 parches de adaptación CachyOS al árbol de Omarchy (ver §3.4).
4. Pidió username, email, Enter, "Yes" al aviso de Limine.
5. Empezó la fase `packaging/base.sh` y abortó con:

```
:: ¿Quiere omitir el siguiente paquete para esta actualización? [s/N] error: ...
:: no se pudo satisfacer la dependencia «libcpptrace.so=1-64», necesaria para...
This command halted with exit code 0:
Failed script: /home/oliwheel/.local/share/omarchy/install/packaging/base.sh
Omarchy installation stopped!
```

Diagnóstico:

```bash
pacman -Si cpptrace | head -20      # cachyos/cpptrace 1.0.4-1 — Provee: cpptrace
yay -Ss cpptrace                    # extra/cpptrace 1.0.4-2 — versión más nueva
pacman -Q cpptrace 2>&1             # error: el paquete «cpptrace» no se ha encontrado
```

El paquete que pide `libcpptrace.so=1-64` (probablemente algo del set base de Omarchy: hyprland, walker, etc.) requiere el soname versionado `1` exportado por `extra/cpptrace 1.0.4-2`. La versión de `cachyos/cpptrace 1.0.4-1` provee el nombre simple `cpptrace` pero no necesariamente con ese soname.

**Fix:**

```bash
sudo pacman -S --noconfirm extra/cpptrace
pacman -Q cpptrace                     # cpptrace 1.0.4-2
pacman -Q --info cpptrace | grep -i provee   # Provee: libcpptrace.so=1-64 ← OK
```

Y se relanza el script.

### 1.8. Problema #3: C: lleno y `VERR_DISK_FULL`

Tras resolver el cpptrace, el script empezó a descargar e instalar el set completo de Omarchy (2 GB descarga, 6.8 GB instalación). A mitad de instalar `libreoffice-fresh` y dependencias (~30 minutos en el guest), la VM se quedó **`Paused`** sin razón aparente.

El log `C:\Users\Oliwheel\VirtualBox VMs\CachyOS-Omarchy\Logs\VBox.log` reveló:

```
Unhandled error 0xc000007f (3221225599)
I/O cache: Error while writing entry at offset ... to medium "ahci-0-0" (rc=VERR_DISK_FULL)
VM: Raising runtime error 'BLKCACHE_IOERR' (fFlags=0x6)
Console: Machine state changed to 'Paused'
```

Espacio en disco:

```powershell
Get-PSDrive -Name C, X | Format-Table Name, @{N='FreeGB';E={[math]::Round($_.Free/1GB,1)}}
# C    0.7 GB libres   ← causa raíz
# X 1659.6 GB libres
```

**Fix completo** (sin perder ningún snapshot):

```powershell
$vb = "C:\Program Files\Oracle\VirtualBox\VBoxManage.exe"
$vm = "CachyOS-Omarchy"
$srcDir = "C:\Users\Oliwheel\VirtualBox VMs\CachyOS-Omarchy"
$dstDir = "X:\VirtualBox VMs\CachyOS-Omarchy"

# 1) Apagar a la fuerza (la VM está en Paused, no responde a ACPI)
& $vb controlvm $vm poweroff

# 2) Desregistrar SIN borrar los archivos
& $vb unregistervm $vm

# 3) Mover toda la carpeta de VM y snapshots a un disco con espacio
New-Item -ItemType Directory -Force -Path "X:\VirtualBox VMs" | Out-Null
robocopy "$srcDir" "$dstDir" /MOVE /E /NFL /NDL /NJH /NJS /MT:16
# robocopy /MOVE: copia y, si tiene éxito, borra el origen. /E: subdirectorios.
# /MT:16: 16 hilos para acelerar. /NFL /NDL: log silencioso.

# 4) Re-registrar el .vbox desde la nueva ubicación
& $vb registervm "$dstDir\CachyOS-Omarchy.vbox"

# 5) Comprobar que las snapshots sobrevivieron (los .vdi diff están dentro de la carpeta)
& $vb snapshot $vm list
# Las 3 snapshots (vm-creada, cachyos-instalado, pre-omarchy) intactas ← OK

# 6) Restaurar el snapshot pre-omarchy (estado previo a la rotura por disco lleno)
& $vb snapshot $vm restore "pre-omarchy"

# 7) Arrancar y continuar
& $vb startvm $vm --type gui
```

Después, dentro del guest:
- Re-instalar `extra/cpptrace` (porque el snapshot pre-omarchy es anterior a ese fix).
- Re-clonar `omarchy-on-cachyos` (también era posterior al snapshot).
- Lanzar el script.

### 1.9. Problema #4: `increase-file-watchers.sh` exit code 1

A mitad de la fase de configuración, el script aborta con:

```
[2026-05-24 09:52:08] Starting: /home/oliwheel/.local/share/omarchy/install/config/...
This command halted with exit code 0:
Failed script: /home/oliwheel/.local/share/omarchy/install/config/increase-file-watchers.sh
Omarchy installation stopped!
```

Contenido del script:

```bash
# Increase inotify file watchers for VS Code, webpack, and other dev tools (default 8192 is too low)
echo "fs.inotify.max_user_watches=524288" | sudo tee /etc/sysctl.d/90-omarchy-file-watchers.conf >/dev/null
sudo sysctl --system >/dev/null 2>&1
```

Reproducción manual:

```bash
sudo bash /home/oliwheel/.local/share/omarchy/install/config/increase-file-watchers.sh
echo $?   # 1
```

Aplicación parcial — los settings sí se aplican (`fs.inotify.max_user_watches = 524288` aparece en `sudo sysctl --system 2>&1 | tail`), pero `sysctl --system` retorna 1 porque algún otro archivo del directorio `/etc/sysctl.d/` da warning. Con `set -e` en el script Omarchy, eso lo aborta todo.

**Fix:** sobrescribir el sub-script para que la última línea no falle nunca:

```bash
printf '%s\n' \
  '#!/bin/bash' \
  'echo "fs.inotify.max_user_watches=524288" | sudo tee /etc/sysctl.d/90-omarchy-file-watchers.conf >/dev/null' \
  'sudo sysctl --system >/dev/null 2>&1 || true' \
  > ~/.local/share/omarchy/install/config/increase-file-watchers.sh
```

Y reanudación del instalador directamente (sin pasar por el wrapper otra vez):

```bash
~/.local/share/omarchy/install.sh
```

Responde "Yes" al aviso de Limine, "Yes" a sobrescribir config de Neovim si existía. La instalación llegó hasta el final con la pantalla:

```
OMARCHY
Finished installing
[Reboot Now]
```

### 1.10. Problema #5: el `Reboot Now` del TUI no respondió

Tras pulsar Enter sobre el botón verde `Reboot Now`, la VM no reinició. Como medida directa:

```powershell
& $vb controlvm $vm reset
```

Tras el reset, Plymouth arrancó (spinner) y por primera vez SDDM hizo autologin a Hyprland. Top bar de Omarchy visible, welcome de CachyOS centrado.

### 1.11. Problema #6: VMSVGA rompe Hyprland (intento descartado)

Cuando intenté solucionar el "no resize automático" cambiando el controlador gráfico de VBoxSVGA a VMSVGA:

```powershell
& $vb controlvm $vm acpipowerbutton
# Esperar a que pase a poweroff
& $vb modifyvm $vm --graphicscontroller vmsvga --vram 128 --accelerate3d off
& $vb startvm $vm --type gui
```

Resultado: pantalla completamente negra tras boot. Plymouth no arrancó. El doc del §3 lo advertía y se confirma: **VBoxSVGA es obligatorio para Hyprland en VBox**.

**Reversión:**

```powershell
& $vb controlvm $vm poweroff
& $vb modifyvm $vm --graphicscontroller vboxsvga
& $vb snapshot $vm restore "omarchy-listo"
& $vb startvm $vm --type gui
```

### 1.12. Problema #7: resolución y escala

El primer arranque de Hyprland mostró 640×480 con scale 2.0 (default retina de Omarchy). Para corregir necesitas dos cosas:
1. La **resolución** la elige Hyprland del modo "preferred" del monitor virtual. Si la ventana de VBox es pequeña, ese preferred es bajo.
2. La **escala** la decide Omarchy en su configuración: 2.0 por defecto.

**Aplicación inmediata** (un terminal Ghostty/Alacritty abierto con Super+Enter):

```bash
# Listar resoluciones disponibles del monitor virtual
wlr-randr | head -40
# El output name del monitor virtual en VBox es VGA-1 (NO "X0" ni "1")

# Forzar resolución y escala
wlr-randr --output VGA-1 --mode 1366x768 --scale 1
# (o el modo que más se acerque a tu ventana host actual)
```

**Persistencia** (para próximos reboots):

```bash
printf '%s\n' \
  '# VBox VGA-1, ajuste automatico al tamano de la ventana host con scale 1.0' \
  'monitor=VGA-1,preferred,auto,1.0' \
  > ~/.config/hypr/monitors.conf
```

Si el texto queda demasiado pequeño a 1.0, prueba **1.2** o **1.25**. Hyprland requiere escalas que produzcan dimensiones enteras: para 2496×1014, las escalas válidas incluyen 1.0, 1.2, 1.5, 2.0 (siempre que el cociente sea entero o muy cercano).

> **Observación importante sobre el resize "automático":** en este stack (VBoxSVGA + Wayland + Hyprland) el resize de la ventana host **no se propaga al guest en caliente**. Lo que sí funciona: al rebootear, Hyprland coge el `preferred` que VirtualBox negocia con el tamaño actual de la ventana. Si quieres reajustar sin reboot, `wlr-randr --output VGA-1 --mode WxH --scale N` lo hace.

### 1.13. Snapshots finales

Estado final de la cadena de snapshots (cualquier punto es recuperable):

```
vm-creada
└── cachyos-instalado
    └── pre-omarchy
        └── omarchy-instalado
            └── omarchy-listo
                ├── pre-vmsvga          (descartable, era para el intento fallido)
                └── omarchy-final       (snapshot funcional final)
```

---

## 2. Procedimiento limpio para VirtualBox (versión definitiva)

Aplicando todo lo aprendido en §1, este es el procedimiento que ejecutaría hoy desde cero para llegar al mismo resultado sin tropiezos. Tiempo estimado: **1h30 a 2h30** dependiendo de tu conexión.

### 2.1. Pre-requisitos verificables

1. **VirtualBox 7.1.12+** (yo verifiqué con 7.2.8). Comprueba con:
   ```powershell
   & "C:\Program Files\Oracle\VirtualBox\VBoxManage.exe" --version
   ```
   Si tienes una versión vieja, instala la última desde virtualbox.org. **Instala también el Extension Pack** (se descarga aparte del mismo sitio): doble-click sobre el `.vbox-extpack` y aceptar.

2. **ISO de CachyOS** descargada y verificada. Versión Desktop, no Handheld:
   ```powershell
   # Verifica con CertUtil que el SHA256 coincide con el .sha256 oficial
   CertUtil -hashfile "X:\cachyos-desktop-linux-260426.iso" SHA256
   ```

3. **Disco con espacio sobrado para el VDI.** Necesitas ≥ 30 GB libres en el disco que vayas a usar para la carpeta de la VM. La instalación de Omarchy ocupa unos 11-13 GB; el VDI dinámico crece hasta ese tamaño y los snapshots ocupan extra.
   ```powershell
   Get-PSDrive | Where-Object { $_.Provider.Name -eq 'FileSystem' } | Format-Table Name, @{N='FreeGB';E={[math]::Round($_.Free/1GB,1)}}
   ```

4. **VT-x/AMD-V habilitado en BIOS** del host (en un 5900X moderno siempre lo está, pero verifícalo si nunca virtualizaste).

### 2.2. Crear la VM en el disco correcto

Adapta `$vmHome` al disco con espacio:

```powershell
$vb       = "C:\Program Files\Oracle\VirtualBox\VBoxManage.exe"
$name     = "CachyOS-Omarchy"
$iso      = "X:\cachyos-desktop-linux-260426.iso"   # tu ISO
$vmHome   = "X:\VirtualBox VMs"                      # CARPETA EN DISCO GRANDE
$vmDir    = "$vmHome\$name"
$vdi      = "$vmDir\$name.vdi"

# 1) Si quieres cambiar la default machine folder de VBox (recomendado)
& $vb setproperty machinefolder "$vmHome"

# 2) Crear VM
& $vb createvm --name $name --ostype ArchLinux_64 --register

# 3) Sistema (memoria, CPU, EFI, red, audio, USB, boot order)
& $vb modifyvm $name `
  --memory 8192 --cpus 4 --pae on --nested-hw-virt on `
  --firmware efi --rtcuseutc on `
  --nic1 nat `
  --audio-driver dsound --audio-controller hda --audio-enabled on `
  --usbxhci on `
  --boot1 dvd --boot2 disk --boot3 none --boot4 none

# 4) Gráficos (NO TOCAR: VBoxSVGA + 128 MB + 3D off)
& $vb modifyvm $name --graphicscontroller vboxsvga --vram 128 --accelerate3d off

# 5) Disco virtual de 40 GB dinámico
& $vb createmedium disk --filename "$vdi" --size 40960 --format VDI --variant Standard

# 6) Controladores SATA (HDD) e IDE (ISO)
& $vb storagectl $name --name "SATA" --add sata --controller IntelAhci --portcount 2 --bootable on
& $vb storagectl $name --name "IDE"  --add ide  --controller PIIX4    --bootable on

# 7) Adjuntar dispositivos
& $vb storageattach $name --storagectl "SATA" --port 0 --device 0 --type hdd      --medium "$vdi"
& $vb storageattach $name --storagectl "IDE"  --port 0 --device 0 --type dvddrive --medium "$iso"

# 8) Snapshot punto cero
& $vb snapshot $name take "vm-creada" --description "VM recien creada, ISO montada, sin arrancar"

# 9) Arrancar
& $vb startvm $name --type gui
```

### 2.3. Calamares — pantallas y elecciones

Cuando arranque el live de KDE, pulsa el icono **Install CachyOS** del escritorio o **Launch installer** del welcome.

| Pantalla | Elección |
|---|---|
| Idioma | Español |
| Región/Zona horaria | Europe / Madrid |
| Teclado | Spanish (Spain) |
| Particionado | **Erase disk** |
| Sistema de ficheros | **BTRFS** (obligatorio) |
| Swap | "Swap to file" (o "Swap with hibernate" si quieres hibernar la VM, irrelevante en uso normal) |
| Cifrado | **No** (en VM no aporta) |
| Bootloader | **GRUB** (NO Limine; el wrapper de Omarchy es compatible con GRUB) |
| Kernel | linux-cachyos |
| Desktop Environment | **No Desktop / Minimal** (Hyprland lo pone Omarchy después) |
| Shell por defecto | **fish** (marcarlo aunque luego haya que reasegurarlo manualmente) |
| Paquetes opcionales | Marca `base-devel`. Desmarca `office`, `gaming`, etc. |
| Usuario | tu nombre, contraseña, "Use same password for admin" marcado |
| **Reboot now** | **DESMARCADO** al terminar |

Al ver "Hemos terminado." cierra Calamares pero **no** reinicies desde dentro. Apaga la VM desde el host:

```powershell
& $vb controlvm $name acpipowerbutton
# Esperar al estado poweroff (típicamente 10-30 s)
```

Desmonta la ISO y snapshot:

```powershell
& $vb storageattach $name --storagectl "IDE" --port 0 --device 0 --type dvddrive --medium none
& $vb snapshot $name take "cachyos-instalado" --description "Calamares terminado, ISO desmontada"
```

### 2.4. Primer arranque y preparación pre-Omarchy

Arranca la VM. Login en TTY1 con tu usuario y contraseña. **El primer comando que ejecutes** debe ser la batería completa de preparación; así te ahorras prompts de contraseña posteriores:

```bash
# Una sola pwd para todo este bloque
sudo bash -c '
  cachyos-rate-mirrors && \
  pacman -Syu --noconfirm && \
  pacman -S --noconfirm fish virtualbox-guest-utils git base-devel && \
  pacman -S --noconfirm extra/cpptrace && \
  systemctl enable --now vboxservice && \
  chsh -s /usr/bin/fish '"$USER"' && \
  echo === DONE-ALL ===
'
```

Nota la pre-instalación de `extra/cpptrace` — evita el problema #2 del §1.7 antes de que ocurra.

Reboot para que la nueva shell entre en vigor y los módulos vboxguest se carguen limpios:

```bash
sudo reboot
```

Tras el reboot, vuelve a entrar y verifica:

```bash
# (ahora ya estás en fish, prompt termina en ~>)
echo $SHELL                                                # /usr/bin/fish
lsmod | grep vboxguest                                     # vboxguest cargado
systemctl is-active vboxservice                            # active
pacman -Q cpptrace virtualbox-guest-utils fish | head -3   # versiones
```

Snapshot `pre-omarchy` desde el host (apaga limpio antes para evitar live snapshot):

```bash
# En el guest:
sudo poweroff
```

```powershell
# En el host, una vez en poweroff:
& $vb snapshot $name take "pre-omarchy" --description "Sistema actualizado y fish/guest-utils/cpptrace instalados"
& $vb startvm $name --type gui
```

### 2.5. Configurar `NOPASSWD` temporal (opcional, recomendado para no interactivo)

El script de Omarchy va a invocar `sudo` decenas de veces. Si vas a dejarlo corriendo desatendido, configura `NOPASSWD` temporal:

```bash
echo "$USER ALL=(ALL) NOPASSWD: ALL" | sudo tee /etc/sudoers.d/00-${USER}-nopass
sudo chmod 440 /etc/sudoers.d/00-${USER}-nopass
```

**Bórralo al terminar todo:**

```bash
sudo rm /etc/sudoers.d/00-${USER}-nopass
```

### 2.6. Parche preventivo del sub-script problemático

Antes de lanzar Omarchy, parchea `increase-file-watchers.sh`. El truco es hacerlo en el **árbol del repo upstream** que el wrapper va a clonar, pero como aún no lo ha hecho, lo haremos justo después del clone y antes del install. Equivalente: incorporar el parche al wrapper.

Mejor enfoque: hacer un fork del wrapper o aplicar el parche **antes** del install pero **después** del clone. Esto requiere una pequeña secuencia manual:

```bash
cd ~
git clone https://github.com/mroboff/omarchy-on-cachyos.git
cd omarchy-on-cachyos/bin

# El wrapper clona Omarchy upstream en una primera ejecución y luego ejecuta su install
# Para meter el parche, hacemos una primera ejecución "preparatoria" que el wrapper detectará y reusará
# Alternativa más limpia: clonar manualmente upstream y aplicar el parche

cd ~/.local/share
rm -rf omarchy  # por si una ejecución previa lo dejó incompleto
git clone https://github.com/basecamp/omarchy.git

# Parche del problema #3
printf '%s\n' \
  '#!/bin/bash' \
  'echo "fs.inotify.max_user_watches=524288" | sudo tee /etc/sysctl.d/90-omarchy-file-watchers.conf >/dev/null' \
  'sudo sysctl --system >/dev/null 2>&1 || true' \
  > ~/.local/share/omarchy/install/config/increase-file-watchers.sh

# Ahora sí, lanzar el wrapper
cd ~/omarchy-on-cachyos/bin
chmod +x install-omarchy-on-cachyos.sh
./install-omarchy-on-cachyos.sh
```

### 2.7. Responder los prompts del wrapper

El wrapper te pregunta, en orden:

1. **`Please enter your username:`** → tu nombre real para git config (o el usuario del sistema, tu elección).
2. **`Please enter your email address:`** → tu email para git config.
3. **`Press Enter to begin the installation of Omarchy...`** → Enter.
4. **`Omarchy install requires: Limine bootloader. Proceed anyway on your own accord and without assistance?`** → **Yes** (`y` o navegar y Enter). El wrapper ya quita los pasos de bootloader.
5. **`Neovim configuration already exists at... Continue?`** → **Yes** si no tienes config de Neovim que quieras preservar.

El resto avanza solo. Duración: 20-50 minutos según conexión y carga de CPU.

### 2.8. Reboot final

Cuando aparezca el TUI verde **OMARCHY / Finished installing / Reboot Now**, no confíes en que la tecla Enter funcione. Reset desde el host:

```powershell
& $vb controlvm $name reset
```

(O `acpipowerbutton` + `startvm` si prefieres apagado limpio + arranque.)

### 2.9. Primer login en Hyprland y ajuste de escala

Plymouth → SDDM autologin → Hyprland con la top bar de Omarchy visible y el welcome de CachyOS centrado.

Pulsa **Super+Enter** para abrir terminal Ghostty/Alacritty. Aplica escala y persistencia:

```bash
# Resolución actual y modos disponibles
hyprctl monitors | grep scale       # típicamente scale: 2.00 ← demasiado grande
wlr-randr | head -40                # lista de modos del monitor virtual

# Aplicación inmediata - elige escala que más te guste
wlr-randr --output VGA-1 --scale 1.0    # nítido, texto pequeño
# wlr-randr --output VGA-1 --scale 1.2  # buen compromiso
# wlr-randr --output VGA-1 --scale 1.25 # texto más grande

# Persistencia para próximos reboots
printf '%s\n' \
  '# VBox VGA-1, auto al tamano de ventana, scale ajustable' \
  'monitor=VGA-1,preferred,auto,1.2' \
  > ~/.config/hypr/monitors.conf
```

Verifica el checklist §10 del doc de referencia:

```bash
hyprctl monitors | grep scale                    # scale: 1.20 (o el valor elegido)
echo $XDG_SESSION_TYPE                           # wayland
echo $SHELL                                      # /usr/bin/fish
which yay && yay --version                       # /usr/bin/yay  yay v12.5.7
lsmod | grep vboxguest                           # cargado
```

### 2.10. Cierre: snapshot final y limpieza

```bash
# Quitar NOPASSWD si lo pusiste
sudo rm -f /etc/sudoers.d/00-${USER}-nopass

# Cambiar contraseña si la pusiste débil para la instalación
passwd
```

Snapshot final desde el host (apagando la VM antes para que sea snapshot offline, no live):

```bash
sudo poweroff
```

```powershell
& $vb snapshot $name take "omarchy-final" --description "Estado funcional final, post-config"
& $vb startvm $name --type gui
```

---

## 3. Catálogo de problemas encontrados y soluciones aplicadas

Esta sección es **buscable**. Cada entrada incluye el mensaje de error literal para que aparezca en una búsqueda.

### 3.1. Disco lleno / `VERR_DISK_FULL` / VM en `Paused`

**Síntoma:** La VM se pone en `Paused` durante una operación pesada de IO (instalación de paquetes, snapshot, etc.). El Manager muestra "Detenida". Al expandir el diálogo de error o leer `Logs/VBox.log`:

```
I/O cache: Error while writing entry at offset ... to medium "ahci-X-X" (rc=VERR_DISK_FULL)
VM: Raising runtime error 'BLKCACHE_IOERR' (fFlags=0x6)
Console: Machine state changed to 'Paused'
```

**Causa:** El VDI dinámico no puede crecer más porque el disco físico del host donde reside está lleno.

**Fix:** mover la VM a otro disco (ver §1.8). Pasos resumidos:

```powershell
& $vb controlvm $vm poweroff
& $vb unregistervm $vm
robocopy "C:\path\old\$vm" "X:\path\new\$vm" /MOVE /E /MT:16
& $vb registervm "X:\path\new\$vm\$vm.vbox"
& $vb snapshot $vm restore "<snapshot anterior a la rotura>"
```

**Prevención:** colocar la VM en un disco con ≥ 30 GB libres desde el inicio. Cambia la default folder de VBox:

```powershell
& $vb setproperty machinefolder "X:\VirtualBox VMs"
```

### 3.2. Shell por defecto no es fish después de Calamares

**Síntoma:** Tras instalar CachyOS con fish marcado como shell por defecto en Calamares, `echo $SHELL` devuelve `/bin/bash`.

**Causa:** Calamares en algunas builds no aplica el cambio de shell del usuario o la opción no está expuesta. Mi instalación marcó `fish` y aun así quedó con bash.

**Fix:** después del primer login:

```bash
# Instalar fish si no está
sudo pacman -S --needed --noconfirm fish
# Cambiar shell del usuario
chsh -s /usr/bin/fish $USER
# Cerrar sesión y volver a entrar (o reboot)
```

**Verificación post-cambio:** el prompt cambia de `~$` (bash) a `~>` (fish) y `echo $SHELL` devuelve `/usr/bin/fish`.

### 3.3. `libcpptrace.so=1-64` no satisfecha durante install de Omarchy

**Síntoma:**

```
:: no se pudo satisfacer la dependencia «libcpptrace.so=1-64», necesaria para...
Failed script: /home/oliwheel/.local/share/omarchy/install/packaging/base.sh
Omarchy installation stopped!
```

**Causa:** Algún paquete del set base de Omarchy (hyprland, walker, ghostty, etc.) requiere el soname versionado `libcpptrace.so` versión 1 ABI 64-bit. La versión de `cpptrace` que provee el repo `cachyos/` (1.0.4-1) no lo expone con esa firma, pero la de `extra/` (1.0.4-2) sí.

**Fix:**

```bash
sudo pacman -S --noconfirm extra/cpptrace
# Verificar
pacman -Q --info cpptrace | grep -i provee   # Provee: libcpptrace.so=1-64
# Relanzar Omarchy
~/.local/share/omarchy/install.sh
```

### 3.4. `increase-file-watchers.sh` aborta el instalador

**Síntoma:**

```
This command halted with exit code 0:
Failed script: /home/oliwheel/.local/share/omarchy/install/config/increase-file-watchers.sh
```

**Causa:** El script ejecuta `sudo sysctl --system` (carga todos los archivos `/etc/sysctl.d/*.conf`). Si alguno devuelve warning, `sysctl --system` retorna 1 aunque los valores sí se hayan aplicado. El instalador de Omarchy usa `set -e` y aborta.

**Fix:** sobrescribir el sub-script para que su última línea no propague el error:

```bash
printf '%s\n' \
  '#!/bin/bash' \
  'echo "fs.inotify.max_user_watches=524288" | sudo tee /etc/sysctl.d/90-omarchy-file-watchers.conf >/dev/null' \
  'sudo sysctl --system >/dev/null 2>&1 || true' \
  > ~/.local/share/omarchy/install/config/increase-file-watchers.sh
```

### 3.5. Cambio a VMSVGA = pantalla negra

**Síntoma:** Tras `VBoxManage modifyvm <vm> --graphicscontroller vmsvga`, el siguiente arranque no muestra nada — pantalla totalmente negra. Plymouth no se ve, SDDM no aparece.

**Causa:** Hyprland (wlroots) en este stack con VMSVGA no encuentra el render device esperado. El doc `INSTALACION_CACHYOS_OMARCHY_VIRTUALBOX.md` lo decía: VMSVGA rompe Alacritty/Ghostty y, en mi prueba, también el compositor completo.

**Fix:** revertir a VBoxSVGA y, si el sistema quedó dañado tras varios intentos, restaurar un snapshot previo:

```powershell
& $vb controlvm $vm poweroff
& $vb modifyvm $vm --graphicscontroller vboxsvga
& $vb snapshot $vm restore "<último snapshot bueno>"
& $vb startvm $vm --type gui
```

### 3.6. Snapshot live + restore = VM no responde a teclado

**Síntoma:** Tras un `VBoxManage snapshot <vm> take <nombre> --live` y posterior `restore`, la VM arranca pero **no procesa inputs**: ni clics ni teclas. `keyboardputstring` desde el host no llega.

**Causa observada:** Los dispositivos virtuales (USB tablet, teclado PS/2) quedan en estado inconsistente tras una restauración de snapshot live. Es un comportamiento que reproduje varias veces.

**Fix:**

```powershell
& $vb controlvm $vm reset
```

Tras el reset todo vuelve a la normalidad. Prevención: **prefiere snapshots en `poweroff`** para puntos críticos. Los snapshots live están bien para "punto de retorno rápido sin apagar", pero saben que vendrán acompañados de algún reset cuando restaures.

### 3.7. `Reboot Now` del TUI de Omarchy no responde

**Síntoma:** El TUI final muestra el botón verde `Reboot Now`. Enter no hace nada.

**Fix:**

```powershell
& $vb controlvm $vm reset
```

### 3.8. Escala 2.0 (retina) por defecto en Hyprland

**Síntoma:** Texto y UI gigantes. `hyprctl monitors | grep scale` devuelve `scale: 2.00`. El `monitor=,preferred,auto,1` del config aparentemente no aplica.

**Causa:** Omarchy asume pantalla retina y aplica scale 2 por defecto. El `monitors.conf` que escribes tarda en aplicarse o es sobreescrito por algún hook al login.

**Fix definitivo:** forzar la escala explícitamente y persistir nombrando el output (no usar el comodín `,`):

```bash
# Inmediato
wlr-randr --output VGA-1 --scale 1.0    # o 1.2 si te gusta más
# Persistente
printf '%s\n' \
  '# Scale custom' \
  'monitor=VGA-1,preferred,auto,1.2' \
  > ~/.config/hypr/monitors.conf
hyprctl reload
```

### 3.9. `sudo` pide la contraseña constantemente

**Síntoma:** Durante el script de Omarchy, especialmente cuando construye yay desde AUR (`makepkg`), aparece `[sudo] contraseña para <user>:` varias veces aunque acabes de introducirla.

**Causa:** `makepkg` lanza subshells sin TTY que no heredan el timestamp de credenciales. El timeout default de sudo (15 min) se aplica por TTY, no globalmente.

**Fix temporal (recomendado solo para esta instalación):**

```bash
echo "$USER ALL=(ALL) NOPASSWD: ALL" | sudo tee /etc/sudoers.d/00-${USER}-nopass
sudo chmod 440 /etc/sudoers.d/00-${USER}-nopass
# ...trabaja sin más prompts...
# Al terminar:
sudo rm /etc/sudoers.d/00-${USER}-nopass
```

### 3.10. Mirror `archlinux.cachyos.org` caído temporalmente

**Síntoma:** Errores `error: no se pudo obtener el archivo «...»` en mitad de `pacman -Syu` o de la instalación de Omarchy. Mensaje `advertencia: demasiados errores al descargar de archlinux.cachyos.org`.

**Fix:**

```bash
sudo cachyos-rate-mirrors        # re-ordena mirrors por velocidad/disponibilidad actual
sudo pacman -Syyu --noconfirm    # -yy fuerza re-descarga de las DB
```

Si persiste, esperar 5-10 minutos y reintentar (suelen ser caídas transitorias).

### 3.11. VirtualBoxVM se queda como ventana huérfana

**Síntoma:** Al lanzar `open_application "Virtualboxvm"` u otras invocaciones similares, aparece un diálogo "máquina a iniciar, usando la línea de comandos: artvm <nombre|UUID>...". Es el binario VBoxClient ejecutado sin args.

**Fix:** Aceptar el diálogo. No es un error real, simplemente el binario sin argumentos imprime su help y aparece como dialog. Usar siempre `VBoxManage startvm <nombre>` para arrancar la VM, no invocar VirtualBoxVM directamente.

### 3.12. RPC del servicio VBoxSVC bloqueado tras error de snapshot

**Síntoma:** Tras un snapshot fallido, cualquier `VBoxManage snapshot <vm> list/take` devuelve:

```
Failed to assign the machine to the session (E_FAIL)
Code RPC_S_SERVER_UNAVAILABLE (0x800706ba)
```

**Fix:**

```powershell
# Esperar 30-60 s suele ser suficiente
Start-Sleep -Seconds 60
& $vb snapshot $vm list

# Si persiste, reiniciar el servicio host
Restart-Service VBoxSDS -Force          # como administrador
# Alternativa drástica (cierra el Manager y procesos VBox huérfanos):
Get-Process | Where-Object { $_.ProcessName -match 'VBox' } | Stop-Process -Force
```

### 3.13. Pantalla negra en primer boot post-install (bug VBox 7.1.x)

**Síntoma documentado pero no reproducido en mi instalación:** Tras el primer reboot, el logo de CachyOS aparece y luego pantalla negra >120 s. Documentado en `discuss.cachyos.org/t/black-loading-screen-on-first-login-on-vm-in-virtualbox-7-1-12`.

**Fix:**

```bash
# Tras 120 s, pulsar AltGr+F2 (o la host key que tengas) para abrir TTY2
sudo nano /etc/default/grub
# Localizar GRUB_CMDLINE_LINUX_DEFAULT="..." y añadir antes de la comilla de cierre:
#   nomodeset module_blacklist=vboxdrv,vboxnetflt,vboxnetadp,vboxpci
sudo grub-mkconfig -o /boot/grub/grub.cfg
sudo reboot
```

---

## 4. Procedimiento para dual boot bare-metal con Windows 10

Cuando saltes de VirtualBox al hardware real (Ryzen 9 5900X + RTX 3080 Ti + 64 GB RAM, Windows 10 ya instalado), las lecciones del §1-3 aplican casi todas con algunas adiciones específicas del hardware. La guía `INSTALACION_CACHYOS_OMARCHY_DUALBOOT_BAREMETAL.md` de este mismo directorio cubre los detalles previos (BIOS, BitLocker, Fast Startup, particionado en Windows, etc.). Aquí me centro en lo que cambia respecto al procedimiento de VirtualBox.

### 4.1. Pre-vuelo en Windows (1 hora)

1. **Backup completo** del C: con Macrium Reflect Free o equivalente a un disco externo.
2. **Apuntar la clave de Windows** por si necesitas reinstalar:
   ```powershell
   wmic path softwarelicensingservice get OA3xOriginalProductKey
   ```
3. **Actualizar la BIOS** de la placa AM4 a la última estable (no beta). Las BIOS antiguas dan problemas de IOMMU, MSR y USB que rompen Linux.
4. **Desactivar Fast Startup** (CRÍTICO; sin esto NTFS queda hibernado y dual boot corrompe la tabla de particiones):
   - Panel de Control → Opciones de energía → "Elegir el comportamiento de los botones" → "Cambiar configuración no disponible" → desmarcar "Activar inicio rápido".
   - Y por línea de comandos (admin):
   ```powershell
   powercfg /h off
   ```
5. **Desactivar BitLocker** si está activo:
   ```powershell
   manage-bde -status C:
   # Si Protection Status: Protection On, desactivarlo desde Configuración → Privacidad y seguridad → Cifrado del dispositivo
   ```
6. **Liberar espacio** en C: y **desfragmentar** (Windows mueve archivos al inicio para dejar espacio contiguo al final):
   - Desfragmentar y optimizar unidades → seleccionar C: → Optimizar.
7. **Reducir partición de Windows** desde Administración de discos → clic derecho en C: → Reducir volumen. Reserva al menos **80-100 GB** para CachyOS (versus los 40 GB del VDI; en bare metal habrá juegos, datos, etc.).

> **Recomendación con tu hardware:** si tienes un NVMe libre o puedes añadir uno, **instala CachyOS en un disco distinto del de Windows**. Reduce drásticamente el riesgo de que Windows Update rompa el ESP o GRUB. Mucho más seguro y limpio.

### 4.2. Particularidades del hardware NVIDIA 3080 Ti

La RTX 3080 Ti (Ampere, GA102) requiere el driver propietario para rendimiento real (Hyprland en Wayland sobre NVIDIA es soportado pero quisquilloso). El doc `INSTALACION_CACHYOS_OMARCHY_DUALBOOT_BAREMETAL.md` cubre:

- En el live de CachyOS, elegir la opción **"propietary NVIDIA drivers"** del menú GRUB (al contrario que en VM, donde elegimos open-source).
- En Calamares, en la pantalla de kernels marcar **`linux-cachyos`** y también activar el módulo **NVIDIA**. CachyOS provee `nvidia-dkms` que se reconstruye automáticamente con cada actualización de kernel.
- Tras la instalación, antes del reboot, asegurar:
  ```bash
  # Desde el chroot o desde live + arch-chroot
  mkinitcpio -P
  # Comprobar que en mkinitcpio.conf hay MODULES=(... nvidia nvidia_modeset nvidia_uvm nvidia_drm)
  ```
- En `/etc/default/grub`, añadir a `GRUB_CMDLINE_LINUX_DEFAULT`:
  ```
  nvidia_drm.modeset=1 nvidia_drm.fbdev=1
  ```

### 4.3. Calamares en bare metal — diferencias frente a VM

| Pantalla | VM (lo que hicimos) | Bare metal (recomendado) |
|---|---|---|
| Particionado | Erase disk | **Install alongside / Manual** según opción A o B |
| Cifrado | Sin cifrado | **LUKS** (laptop) o sin cifrar (PC sobremesa fija) |
| Desktop Environment | No Desktop / Minimal | **No Desktop / Minimal** (Omarchy lo monta) |
| Kernel | linux-cachyos | linux-cachyos + nvidia-dkms |
| Bootloader | GRUB | **GRUB con `os-prober`** activado para detectar Windows |

Tras instalar y antes de reiniciar (mismo principio que en VM), abre TTY en el live y haz:

```bash
# Si has elegido GRUB con os-prober (recomendado):
sudo arch-chroot /mnt
echo "GRUB_DISABLE_OS_PROBER=false" >> /etc/default/grub
grub-mkconfig -o /boot/grub/grub.cfg
# Debe detectar "Windows Boot Manager"
exit
```

### 4.4. Post-install y Omarchy en bare metal

Exactamente el mismo procedimiento del §2.4 - §2.9, salvo:

1. **Antes** de lanzar el wrapper Omarchy, instalar drivers NVIDIA si no se hizo en Calamares:
   ```bash
   sudo pacman -S --noconfirm nvidia-dkms nvidia-utils egl-wayland
   sudo mkinitcpio -P
   ```
2. La elección de **VBoxSVGA** del §2.2 no aplica — en bare metal tienes drivers reales. Omarchy + Hyprland con NVIDIA propietario tiene su propio set de issues; el doc bare-metal del directorio los cubre (#3024, #3382 de basecamp/omarchy).
3. La resolución y escala se gestionan diferente: Hyprland leerá los EDID reales del monitor, no necesitas `wlr-randr` para forzar. Usa `monitors.conf` con tu monitor real:
   ```
   # 1440p, 144 Hz, scale 1.0
   monitor=DP-1,2560x1440@144,auto,1
   ```

### 4.5. Validación final en bare metal

```bash
hyprctl monitors                                    # tu monitor real con tu refresh
echo $XDG_SESSION_TYPE                              # wayland
echo $SHELL                                         # /usr/bin/fish
lsmod | grep -E '^nvidia'                           # nvidia, nvidia_modeset, nvidia_uvm, nvidia_drm
nvidia-smi                                          # debe listar tu RTX 3080 Ti
glxinfo | grep "OpenGL renderer"                    # NVIDIA GeForce RTX 3080 Ti
```

---

## 5. Migración dual boot → bare metal puro (eliminar Windows)

Cuando hayas usado CachyOS + Omarchy unas semanas o meses, validado tu workflow y movido todo lo importante, este es el plan para dejar el equipo solo con Linux.

### 5.1. Validación previa (1-2 semanas antes)

No dar el salto hasta que hayas validado:

- **Software de trabajo**: VS Code/Cursor, Docker, Node, Python, herramientas DAM (¿IntelliJ? ¿DBeaver?). Todo en pacman o yay.
- **Multimedia**: navegador con tus marcadores y contraseñas sincronizados, cliente de email (Thunderbird), Discord, Spotify, OBS, GIMP/Krita.
- **Periféricos**: impresora Brother (`cups` + driver), tableta gráfica si la usas (`opentabletdriver`), micrófono (`pipewire` lo gestiona), ratón Razer (`openrazer-meta`).
- **Gaming**: Steam con Proton, Lutris para Battle.net/Epic, ProtonDB para tus juegos. Si tienes una librería grande de juegos Windows-only, **mide cuántos funcionan vía Proton** antes de borrar Windows.
- **Documentos**: LibreOffice abre/edita bien tus .docx, .xlsx, .pptx pendientes. Si trabajas con macros Office complejas, prueba antes.

### 5.2. Backup del estado de Linux

Ya con CachyOS funcionando:

```bash
# Snapshot Snapper (rollback rápido)
sudo snapper -c root create --description "pre-eliminar-windows"

# Backup de tu /home (excluyendo cachés)
rsync -aAXv --exclude='.cache' --exclude='node_modules' --exclude='.local/share/Trash' \
  /home/$USER/ /run/media/$USER/<disco-externo>/backup-home-$(date +%Y%m%d)/
```

### 5.3. Backup del estado de Windows (por si tienes que volver)

```powershell
# Imagen completa del disco de Windows con Macrium Reflect a un disco externo
# Anotar la clave: wmic path softwarelicensingservice get OA3xOriginalProductKey
```

### 5.4. Decidir qué hacer con la partición de Windows

Tres opciones:

**A) Borrar Windows y absorber su espacio en la raíz de Linux** (más limpio, ganas el espacio).

**B) Borrar Windows y convertir esa partición en `/home` separado** (segura para futuras reinstalaciones de raíz).

**C) Borrar Windows y dejarlo como almacenamiento (`/mnt/data`)** (rápido, cero riesgo).

Mi recomendación: **B** si vas a usar CachyOS mucho tiempo (separar `/home` te protege ante reinstalaciones); **A** si quieres simplicidad y confías en Snapper para rollbacks.

### 5.5. Procedimiento de eliminación (opción A — absorber espacio)

Desde un live USB de CachyOS (NO desde la propia instalación, porque `parted` no toca particiones montadas en la raíz):

```bash
# 1) Identificar discos
lsblk -f
# Ejemplo:
# nvme0n1
# ├─nvme0n1p1   vfat    ESP        (Windows + Linux EFI compartido)
# ├─nvme0n1p2   ntfs    MSR        (16 MB, reservada Windows)
# ├─nvme0n1p3   ntfs    Windows    (450 GB)
# ├─nvme0n1p4   ntfs    Recovery   (Windows Recovery)
# └─nvme0n1p5   btrfs   CachyOS    (raíz)

# 2) Borrar particiones Windows (p2, p3, p4 en el ejemplo)
sudo parted /dev/nvme0n1
# (parted) rm 2
# (parted) rm 3
# (parted) rm 4
# (parted) print          # confirmar
# (parted) quit

# 3) Extender la partición CachyOS al espacio liberado
sudo parted /dev/nvme0n1 resizepart 5 100%

# 4) Extender el filesystem BTRFS
sudo mount /dev/nvme0n1p5 /mnt
sudo btrfs filesystem resize max /mnt
sudo umount /mnt

# 5) Limpiar entradas de Windows del GRUB
sudo arch-chroot /mnt    # (montar primero si es necesario)
# (en chroot)
sed -i 's/GRUB_DISABLE_OS_PROBER=false/GRUB_DISABLE_OS_PROBER=true/' /etc/default/grub
grub-mkconfig -o /boot/grub/grub.cfg
exit

# 6) Limpiar entradas EFI de Windows
sudo efibootmgr -v        # lista entradas
sudo efibootmgr -b XXXX -B    # borrar "Windows Boot Manager" por su BootOrder
```

### 5.6. Procedimiento opción B (Windows → /home separado)

Mismo principio, pero en lugar de eliminar y extender, formatea la partición Windows como BTRFS, móntala como `/home/$USER`, mueve tus archivos:

```bash
# Desde live, con CachyOS montado en /mnt:
sudo mkfs.btrfs -f -L home /dev/nvme0n1p3    # la antigua partición Windows
# Mover /home actual a la nueva partición
sudo mount /dev/nvme0n1p3 /mnt2
sudo cp -a /mnt/home/* /mnt2/
sudo mv /mnt/home /mnt/home.old
sudo mkdir /mnt/home
# Añadir entrada fstab en /mnt/etc/fstab
echo "UUID=$(blkid -s UUID -o value /dev/nvme0n1p3) /home btrfs defaults,subvol=@home 0 2" | sudo tee -a /mnt/etc/fstab
sudo umount /mnt2 /mnt
sudo reboot
# Verificar que /home está en el disco correcto y todo en su sitio
df -h /home
# Si todo OK, borrar /home.old desde root
```

### 5.7. Limpieza final del ESP

El ESP (partición EFI) contendrá `/EFI/Microsoft/...` huérfano. Bórralo para liberar los pocos MB y dejarlo limpio:

```bash
sudo mount /dev/nvme0n1p1 /mnt/efi   # ajustar al ESP real
sudo rm -rf /mnt/efi/EFI/Microsoft
sudo rm -rf /mnt/efi/EFI/Boot         # solo si está duplicado y CachyOS arranca por su entrada EFI nombrada
sudo umount /mnt/efi
```

### 5.8. Verificación final

```bash
lsblk -f                                        # solo particiones Linux
sudo efibootmgr -v                              # solo entradas Linux
df -h                                           # espacio total disponible
sudo snapper -c root list                       # snapshots Snapper accesibles
sudo grub-mkconfig -o /boot/grub/grub.cfg       # GRUB sin Windows
```

Y deja una snapshot Snapper "post-eliminar-windows" para tener un punto de partida limpio.

---

## 6. Comandos y cheat-sheet de operación diaria

### 6.1. Snapshots de la VM desde el host

```powershell
$vb = "C:\Program Files\Oracle\VirtualBox\VBoxManage.exe"
$vm = "CachyOS-Omarchy"

& $vb snapshot $vm list                                              # listar
& $vb snapshot $vm take "antes-de-X" --description "..."             # crear (apagada)
& $vb snapshot $vm take "antes-de-X" --description "..." --live      # crear (corriendo)
& $vb snapshot $vm restore "antes-de-X"                              # restaurar
& $vb snapshot $vm delete "antes-de-X"                               # borrar
```

Tras un `restore` de snapshot live, ejecuta `controlvm $vm reset` para evitar el problema #6.

### 6.2. Control del estado de la VM desde el host

```powershell
& $vb startvm $vm --type gui                # arrancar con ventana
& $vb startvm $vm --type headless            # arrancar sin ventana (SSH para entrar)
& $vb controlvm $vm acpipowerbutton          # apagado limpio (ACPI)
& $vb controlvm $vm poweroff                 # apagado forzado
& $vb controlvm $vm reset                    # reset duro (= botón reset físico)
& $vb controlvm $vm pause                    # pausar
& $vb controlvm $vm resume                   # reanudar
& $vb controlvm $vm savestate                # guardar a disco (como hibernar)
```

### 6.3. Inyectar teclado al guest sin tocar la ventana

```powershell
& $vb controlvm $vm keyboardputstring 'ls -la'   # texto literal
& $vb controlvm $vm keyboardputscancode 1c 9c    # Enter (make+break)
& $vb controlvm $vm keyboardputscancode e0 5b 1c 9c e0 db    # Super+Enter
& $vb controlvm $vm keyboardputscancode 1d 26 a6 9d          # Ctrl+L (clear)
```

### 6.4. Captura del framebuffer del guest

```powershell
& $vb controlvm $vm screenshotpng "C:\temp\vm.png"
# El framebuffer real, independiente de si la ventana está visible u oculta
```

### 6.5. Resolución y escala en el guest (Hyprland)

```bash
# Listar
hyprctl monitors                                    # info actual (busca "scale:")
wlr-randr                                           # info y modos disponibles

# Cambiar al vuelo
wlr-randr --output VGA-1 --mode 1920x1080 --scale 1
wlr-randr --output VGA-1 --scale 1.2

# Persistir
printf '%s\n' \
  '# Mi config' \
  'monitor=VGA-1,preferred,auto,1.2' \
  > ~/.config/hypr/monitors.conf
hyprctl reload
```

### 6.6. Snapper (snapshots de BTRFS dentro del guest)

```bash
sudo snapper -c root list                                       # listar
sudo snapper -c root create --description "antes de X"          # crear
sudo snapper -c root delete <id>                                # borrar
sudo snapper rollback <id>                                      # rollback (requiere reboot)
```

Y desde GRUB, si la última actualización rompió el arranque, hay una entrada **"Snapshots"** que permite arrancar un snapshot previo sin necesitar live USB.

### 6.7. Actualización rutinaria del sistema

```bash
# Cada 1-2 semanas en CachyOS rolling
sudo cachyos-rate-mirrors            # opcional, si notas lentitud
sudo pacman -Syu                     # repos oficiales
yay -Syu                             # repos oficiales + AUR

# Antes de un update grande
sudo snapper -c root create --description "pre-update $(date +%Y%m%d)"
```

### 6.8. Limpieza periódica

```bash
sudo pacman -Sc                                         # caché de paquetes vieja
yay -Sc                                                 # caché de yay
sudo journalctl --vacuum-time=2weeks                    # logs systemd >2 semanas
docker system prune -a                                  # si usas Docker
```

---

## 7. Apéndice A: tabla de fallos con su comando-solución

| Fallo | Síntoma | Comando(s) de rescate | Lo que hace |
|---|---|---|---|
| Disco lleno | VM `Paused`, `VERR_DISK_FULL` en log | `controlvm poweroff` + `unregistervm` + `robocopy /MOVE` a otro disco + `registervm` + `snapshot restore` | Mueve el VDI fuera del disco lleno |
| Shell no es fish | `echo $SHELL` = `/bin/bash` | `sudo pacman -S --needed fish && chsh -s /usr/bin/fish $USER` + relogin | Cambia la shell por defecto |
| `libcpptrace.so=1-64` | Aborta `base.sh` | `sudo pacman -S extra/cpptrace` | Instala la versión que provee el soname correcto |
| `increase-file-watchers.sh` exit 1 | Aborta config | `printf ... > .../increase-file-watchers.sh` con `|| true` | Sub-script tolerante a fallo de sysctl |
| Pantalla negra primer boot | Sin SDDM tras 120 s | TTY `AltGr+F2` → editar `/etc/default/grub` con `nomodeset module_blacklist=vboxdrv,...` → `grub-mkconfig` → reboot | Desactiva KMS y módulos host VBox erróneos en el guest |
| VMSVGA → pantalla negra | Nada arranca tras cambiar a vmsvga | `modifyvm --graphicscontroller vboxsvga` + `snapshot restore <bueno>` | Revertir; VMSVGA es incompatible con Hyprland aquí |
| VM no responde a teclado/ratón | Tras restore de snapshot live | `controlvm reset` | Re-inicializa dispositivos virtuales |
| `Reboot Now` TUI no responde | TUI Omarchy final colgado | `controlvm reset` | Reset duro desde host |
| Escala 2x | UI gigante | `wlr-randr --output VGA-1 --scale 1.0` + `monitor=VGA-1,preferred,auto,1.0` en `monitors.conf` | Forzar scale 1.0 |
| `sudo` pide pwd repetidamente | Durante install Omarchy | `echo "$USER ALL=(ALL) NOPASSWD: ALL" | sudo tee /etc/sudoers.d/00-${USER}-nopass; sudo chmod 440` (y borrar al terminar) | NOPASSWD temporal |
| Mirror caído | `error: no se pudo obtener` | `sudo cachyos-rate-mirrors && sudo pacman -Syyu` | Re-ordena mirrors y refresca BD |
| VirtualBoxVM diálogo de help | Diálogo "artvm <nombre|UUID>" | Aceptar; no invocar `VirtualBoxVM.exe` sin args | Usar `VBoxManage startvm` siempre |
| `RPC_S_SERVER_UNAVAILABLE` | Snapshot list/take falla | Esperar 60 s o `Restart-Service VBoxSDS -Force` (admin) | Esperar a que VBoxSVC libere |
| Terminal Omarchy no abre (Super+Return) | Pulsas y nada | Añadir a `~/.config/hypr/envs.conf`: `env = LIBGL_ALWAYS_SOFTWARE,1`, `env = WLR_RENDERER_ALLOW_SOFTWARE,1`, `env = WLR_NO_HARDWARE_CURSORS,1` | Render por software, evita crash GL |
| Hyprland se reinicia en bucle | Sesión cae al login | TTY → `omarchy-reinstall` | Restaura dotfiles por defecto |
| Update rompe arranque | No bootea tras pacman | GRUB → Snapshots → arrancar previo → `sudo snapper rollback <id>` | Rollback BTRFS |
| `yay` falta | `yay: command not found` | `sudo pacman -S --needed git base-devel && git clone https://aur.archlinux.org/yay-bin.git && cd yay-bin && makepkg -si` | Compila e instala yay desde AUR |
| Lockout login | Demasiados intentos fallidos | TTY → `sudo faillock --reset --user <usuario>` | Resetea contador PAM |
| Portapapeles host↔guest no va | Copiar/pegar no funciona | `sudo systemctl restart vboxservice` + en Devices del VBox: Portapapeles compartido → Bidireccional | Reinicia servicio Guest Additions |

---

## 8. Apéndice B: scripts útiles listos para pegar

### 8.1. Crear la VM completa desde cero (PowerShell)

```powershell
# Adapta las 4 primeras variables
$name   = "CachyOS-Omarchy"
$iso    = "X:\cachyos-desktop-linux-260426.iso"
$vmHome = "X:\VirtualBox VMs"        # USA UN DISCO CON ESPACIO
$vb     = "C:\Program Files\Oracle\VirtualBox\VBoxManage.exe"

$vmDir = "$vmHome\$name"
$vdi   = "$vmDir\$name.vdi"

& $vb setproperty machinefolder "$vmHome"
& $vb createvm --name $name --ostype ArchLinux_64 --register
& $vb modifyvm $name `
  --memory 8192 --cpus 4 --pae on --nested-hw-virt on `
  --firmware efi --rtcuseutc on `
  --nic1 nat `
  --audio-driver dsound --audio-controller hda --audio-enabled on `
  --usbxhci on `
  --boot1 dvd --boot2 disk --boot3 none --boot4 none
& $vb modifyvm $name --graphicscontroller vboxsvga --vram 128 --accelerate3d off
& $vb createmedium disk --filename "$vdi" --size 40960 --format VDI --variant Standard
& $vb storagectl $name --name "SATA" --add sata --controller IntelAhci --portcount 2 --bootable on
& $vb storagectl $name --name "IDE"  --add ide  --controller PIIX4    --bootable on
& $vb storageattach $name --storagectl "SATA" --port 0 --device 0 --type hdd      --medium "$vdi"
& $vb storageattach $name --storagectl "IDE"  --port 0 --device 0 --type dvddrive --medium "$iso"
& $vb snapshot $name take "vm-creada" --description "VM recien creada"
& $vb startvm $name --type gui
```

### 8.2. Función fish para cambiar scale rápidamente

Añade a `~/.config/fish/functions/setscale.fish`:

```fish
function setscale --description 'Cambia escala de Hyprland en VBox y la persiste'
    if test (count $argv) -ne 1
        echo "Uso: setscale <factor>   (ej: setscale 1.2)"
        return 1
    end
    set -l s $argv[1]
    wlr-randr --output VGA-1 --scale $s
    or begin
        echo "wlr-randr falló. ¿Output correcto?"
        return 1
    end
    printf '%s\n' "# scale $s" "monitor=VGA-1,preferred,auto,$s" > ~/.config/hypr/monitors.conf
    echo "scale $s aplicado y persistido"
end
```

Uso: `setscale 1.2`, `setscale 1.0`, etc.

### 8.3. Bloque "preparación post-Calamares" (bash)

Ejecuta esto **una vez**, justo tras el primer login en TTY:

```bash
sudo bash -c '
  set -e
  cachyos-rate-mirrors
  pacman -Syu --noconfirm
  pacman -S --noconfirm fish virtualbox-guest-utils git base-devel
  pacman -S --noconfirm extra/cpptrace
  systemctl enable --now vboxservice
  chsh -s /usr/bin/fish '"$USER"'
  echo "=== preparacion-completa ==="
'
sudo reboot
```

### 8.4. Bloque "instalar Omarchy con todos los fixes" (bash)

Tras el reboot del 8.3, con sesión fish iniciada:

```bash
# NOPASSWD temporal
echo "$USER ALL=(ALL) NOPASSWD: ALL" | sudo tee /etc/sudoers.d/00-${USER}-nopass
sudo chmod 440 /etc/sudoers.d/00-${USER}-nopass

# Pre-clone de Omarchy upstream para poder parchear
cd ~/.local/share
rm -rf omarchy
git clone https://github.com/basecamp/omarchy.git

# Parche del problema #4
printf '%s\n' \
  '#!/bin/bash' \
  'echo "fs.inotify.max_user_watches=524288" | sudo tee /etc/sysctl.d/90-omarchy-file-watchers.conf >/dev/null' \
  'sudo sysctl --system >/dev/null 2>&1 || true' \
  > ~/.local/share/omarchy/install/config/increase-file-watchers.sh

# Wrapper
cd ~
rm -rf omarchy-on-cachyos
git clone https://github.com/mroboff/omarchy-on-cachyos.git
cd omarchy-on-cachyos/bin
chmod +x install-omarchy-on-cachyos.sh
./install-omarchy-on-cachyos.sh
# Responder username, email, Enter, "y" a Limine y a sobrescribir Neovim si pregunta

# Tras "Finished installing", reboot desde el host:
# (en PowerShell del host: VBoxManage controlvm CachyOS-Omarchy reset)

# Limpiar NOPASSWD tras el reboot final
# sudo rm /etc/sudoers.d/00-${USER}-nopass
```

### 8.5. Bloque "ajustes post-Omarchy"

Tras el primer login en Hyprland:

```bash
# Super+Enter abre terminal Ghostty. En esa terminal:

# Resolución/escala a tu gusto
wlr-randr --output VGA-1 --scale 1.2
printf '%s\n' \
  '# Scale 1.2 y resolución preferida' \
  'monitor=VGA-1,preferred,auto,1.2' \
  > ~/.config/hypr/monitors.conf

# Limpiar NOPASSWD
sudo rm -f /etc/sudoers.d/00-${USER}-nopass

# Cambiar contraseña si era débil
passwd

# Snapshot Snapper de "estado limpio"
sudo snapper -c root create --description "post-instalacion-limpia"
```

---

## 9. Apéndice C: salida de los comandos de verificación

Para que tengas un punto de comparación de "esto es lo que debería salir", aquí está la salida real de mi instalación final.

```
$ echo $SHELL
/usr/bin/fish

$ echo $XDG_SESSION_TYPE
wayland

$ cat /etc/os-release | head -3
NAME="CachyOS Linux"
PRETTY_NAME="CachyOS"
ID=cachyos

$ uname -r
6.18.0-1-cachyos    # o similar — kernel rolling

$ pacman -Q fish virtualbox-guest-utils cpptrace git base-devel
fish 4.7.1-1
virtualbox-guest-utils 7.2.8-2
cpptrace 1.0.4-2
git 2.54.0-1
base-devel 1-2

$ lsmod | grep vboxguest
vboxguest      53248  4 vboxsf

$ systemctl is-active vboxservice
active

$ yay --version
yay v12.5.7 - libalpm v16.0.1

$ which hyprland ghostty alacritty
/usr/bin/hyprland
/usr/bin/ghostty
/usr/bin/alacritty

$ hyprctl monitors | grep -E "^Monitor|scale"
Monitor VGA-1 (ID 0):
        scale: 1.20

$ wlr-randr | head -3
VGA-1 "VBX VBOX monitor 0x03f6d9C2 (VGA-1)"
  Make: VBX
  Model: VBOX monitor

$ [ -d /sys/firmware/efi/efivars ] && echo "UEFI OK"
UEFI OK
```

Si tu salida coincide en lo importante (shell=fish, session=wayland, vboxguest cargado, vboxservice active, hyprctl muestra monitor), la instalación está sana.

---

## 10. Referencias usadas y validadas durante la instalación real

Todas estas URL las consulté durante el proceso real y confirmé que la información seguía válida en mayo 2026.

**Documentación oficial:**

- CachyOS Wiki — Preparación e instalación: <https://wiki.cachyos.org/installation/installation_prepare/>
- CachyOS Wiki — Cambios del instalador GUI Calamares: <https://wiki.cachyos.org/cachyos_basic/changelogs/gui_installer/>
- Omarchy oficial: <https://omarchy.org/>
- Manual oficial de Omarchy — Troubleshooting: <https://learn.omacom.io/2/the-omarchy-manual/88/troubleshooting>
- Hyprland Wiki — NVIDIA: <https://wiki.hyprland.org/Nvidia/>
- ArchWiki — Dual boot with Windows: <https://wiki.archlinux.org/title/Dual_boot_with_Windows>

**Repos clave:**

- `mroboff/omarchy-on-cachyos` (el wrapper imprescindible): <https://github.com/mroboff/omarchy-on-cachyos>
- `basecamp/omarchy` (Omarchy upstream, con sus 30000+ commits): <https://github.com/basecamp/omarchy>

**Discusiones que documentan los problemas conocidos:**

- Omarchy on VirtualBox (#176): <https://github.com/basecamp/omarchy/discussions/176>
- Omarchy apps don't function (VirtualBox) (#72): <https://github.com/basecamp/omarchy/issues/72>
- Black screen NVIDIA (#3024): <https://github.com/basecamp/omarchy/issues/3024>
- NVIDIA drivers demasiado nuevos (#3382): <https://github.com/basecamp/omarchy/issues/3382>
- Omarchy sobre CachyOS (#650): <https://github.com/basecamp/omarchy/discussions/650>
- Black loading screen VBox 7.1.12: <https://discuss.cachyos.org/t/black-loading-screen-on-first-login-on-vm-in-virtualbox-7-1-12/11676>
- Live boot blank screen: <https://discuss.cachyos.org/t/live-boot-environment-blank-screen/16271>
- CachyOS como cliente de VBox 7.1.4: <https://discuss.cachyos.org/t/cachyos-as-client-of-virtualbox-7-1-4/5555>

**Material divulgativo útil:**

- Baeldung — VBoxVGA vs VMSVGA vs VBoxSVGA: <https://www.baeldung.com/linux/vboxvga-vmsvga-vboxsvga-virtualbox>
- ComputingForGeeks — Install CachyOS step-by-step (UEFI): <https://computingforgeeks.com/install-cachyos-step-by-step/>
- ComputingForGeeks — Install Omarchy: <https://computingforgeeks.com/install-omarchy-arch-hyprland-linux/>
- Hacker News — Show HN: Omarchy on CachyOS: <https://news.ycombinator.com/item?id=45246325>
- yoprogramo.com — Explorando Omarchy

**Repos de referencia para dual boot:**

- `auswizard/Dual-Boot-Win11-CachyOS`: <https://github.com/auswizard/Dual-Boot-Win11-CachyOS>

---

## Cierre

Esta bitácora **es** el procedimiento que aplicaría hoy si tuviera que reinstalar desde cero, sea en VirtualBox o en bare metal. Cada decisión está respaldada por algo que rompió o casi rompió en la instalación real del 2026-05-24. Si en una sesión nueva te encuentras con algo que no esté aquí, **añádelo** — la utilidad real del documento es ir creciendo con cada problema nuevo que aparezca y se resuelva.

> "Las guías que valen son las que se escriben justo después de romper algo." — Principio empírico al que esta bitácora se atiene.
