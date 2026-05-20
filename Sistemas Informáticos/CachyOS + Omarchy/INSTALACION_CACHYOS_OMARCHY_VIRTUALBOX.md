# Guía de instalación: CachyOS + Omarchy en VirtualBox

> Documento elaborado a partir de fuentes contrastadas: wiki oficial de CachyOS, manual oficial de Omarchy (omarchy.org / learn.omacom.io), repositorio `mroboff/omarchy-on-cachyos`, discusiones en `basecamp/omarchy` (especialmente la #176 dedicada a VirtualBox), foro `discuss.cachyos.org`, y tutoriales de YouTube/blogs publicados entre noviembre 2025 y mayo 2026.
>
> **Objetivo:** instalar CachyOS dentro de VirtualBox y, sobre él, el conjunto Omarchy (Hyprland + dotfiles de DHH) sin que falle ningún paso.

---

## 0. Resumen del proceso

1. Descargar la ISO de CachyOS.
2. Crear y configurar la VM en VirtualBox con los parámetros exactos de esta guía.
3. Arrancar el ISO live y ejecutar el instalador Calamares con las opciones requeridas por Omarchy.
4. Tras el primer reinicio, actualizar el sistema y aplicar los ajustes de vídeo para VirtualBox.
5. Ejecutar el script `omarchy-on-cachyos` para superponer Omarchy.
6. Aplicar las correcciones de escalado y entorno gráfico de Hyprland dentro de la VM.

> **Aviso clave (muy importante):** la comunidad de CachyOS advierte que la instalación en VM "no siempre funciona a la primera". El truco está en **no usar VMSVGA** (rompe Alacritty/Ghostty), **mantener EFI activado** (requerido por Omarchy y por Limine/GRUB con UEFI) y **no actualizar el sistema antes** del primer arranque gráfico estable.

---

## 1. Requisitos previos en el host

| Componente | Mínimo | Recomendado |
|---|---|---|
| RAM host | 8 GB | 16 GB |
| CPU | x86_64 con VT-x/AMD-V habilitado en BIOS | 4 cores libres para la VM |
| Disco libre | 50 GB | 80 GB |
| VirtualBox | 7.1.x | 7.1.12 o superior |
| VirtualBox Extension Pack | Recomendado (no imprescindible) | Sí |

**Compruebe en la BIOS del host que VT-x/AMD-V está activado.** Si no, VirtualBox arrancará pero con rendimiento inutilizable para Hyprland.

---

## 2. Descarga y verificación de la ISO de CachyOS

1. Descargue la ISO **Desktop** desde la página oficial:
   - URL: <https://cachyos.org/download/>
   - Carpeta: `ISO/desktop/<fecha-build>/`
2. Verifique el SHA-256 (en el mismo directorio hay un `.sha256`):

   ```bash
   sha256sum -c cachyos-desktop-linux-*.iso.sha256
   ```

   - `sha256sum -c` lee el fichero `.sha256` y comprueba que el hash del ISO coincide. Si imprime `OK`, la ISO está íntegra.

> No use la ISO "Handheld". Para esta guía se necesita la ISO **Desktop** estándar.

---

## 3. Creación de la VM en VirtualBox

### 3.1. Parámetros exactos a configurar

Abra VirtualBox → **Nueva** y configure así:

| Sección | Parámetro | Valor obligatorio |
|---|---|---|
| Nombre | — | `CachyOS-Omarchy` |
| Tipo | — | Linux |
| Versión | — | Arch Linux (64-bit) |
| **Sistema → Placa base** | RAM | **8192 MB** (mínimo 6144) |
| Sistema → Placa base | Orden de arranque | Óptico, Disco duro |
| **Sistema → Placa base** | **EFI (Enable EFI - special OSes only)** | **MARCADO ✅** |
| Sistema → Procesador | CPU | **4 vCPU** (mínimo 2) |
| Sistema → Procesador | PAE/NX | Marcado |
| Sistema → Procesador | Nested VT-x/AMD-V | Marcado si la CPU lo soporta |
| **Pantalla → Pantalla** | **Memoria de vídeo** | **128 MB** (máximo) |
| **Pantalla → Pantalla** | **Controlador gráfico** | **VBoxSVGA** (NO VMSVGA, NO VBoxVGA si tu VBox 7.1 lo permite). Si VBoxSVGA da problemas con Hyprland, caer a **VBoxVGA**. |
| Pantalla → Pantalla | **Aceleración 3D** | **Desmarcado** (ver §8 si Hyprland no arranca) |
| Almacenamiento | Disco duro virtual | **40 GB VDI**, dinámicamente asignado |
| Red | Adaptador 1 | NAT (o Bridged si se desea acceso desde host) |
| USB | Controlador | USB 3.0 (xHCI) si Extension Pack instalado, si no USB 2.0 |
| Audio | Controlador | Intel HD Audio |

### 3.2. Por qué estos valores (justificación)

- **EFI activado:** Omarchy 3.x usa el bootloader Limine (o GRUB en CachyOS) en modo UEFI. Sin EFI no arranca tras instalar. La discusión `basecamp/omarchy#176` y el manual oficial lo confirman.
- **VBoxSVGA / VBoxVGA, nunca VMSVGA:** con VMSVGA, las terminales Alacritty y Ghostty (las que usa Omarchy por defecto) no arrancan en absoluto. Confirmado por usuarios en la discusión #176 y en `basecamp/omarchy#72`.
- **128 MB VRAM:** mínimo para que Hyprland renderice sin artefactos.
- **3D desmarcado por defecto:** la combinación VBoxSVGA + 3D ON suele romper Hyprland. Algunos usuarios reportan lo contrario; si tras instalar Hyprland no arranca, pruebe a activarlo (ver §8).
- **40 GB de disco:** CachyOS + Hyprland DE + paquetes de Omarchy ocupan ~12-15 GB; deja margen para snapshots de Snapper.

### 3.3. Montar la ISO

1. En la VM creada → **Configuración → Almacenamiento**.
2. Bajo el controlador IDE/SATA, seleccione la unidad óptica vacía.
3. En el panel derecho pulse el icono del disco → **Choose a disk file…** → seleccione la ISO de CachyOS.
4. Marque **Live CD/DVD**.

---

## 4. Arranque del Live ISO de CachyOS

1. Arranque la VM. En el menú GRUB del live elija **CachyOS Linux (open source drivers)**.
   - *No* elija el de drivers propietarios NVIDIA; en VM no son útiles y pueden colgar el arranque.
2. Espere al escritorio live (KDE por defecto en la ISO).
3. Si la pantalla queda en negro tras el logo y no aparece el escritorio en ~60 s:
   - Apague la VM (`Cerrar → Apagar`).
   - Vuelva a **Configuración → Pantalla**, cambie el controlador gráfico (alterne VBoxSVGA ↔ VBoxVGA) y reintente.

---

## 5. Instalación con Calamares (opciones obligatorias para Omarchy)

Lance el instalador desde el icono **Install CachyOS** del escritorio live.

| Pantalla | Opción a elegir |
|---|---|
| Idioma | Español (o el que prefiera) |
| Región/Hora | Europe / Madrid |
| Teclado | Spanish (Spain) |
| **Particionado** | **Borrar disco** (Erase disk). El instalador creará automáticamente: 1 partición EFI FAT32 + 1 partición raíz BTRFS con subvolúmenes para Snapper. |
| **Sistema de ficheros** | **BTRFS** *(obligatorio para que funcione `omarchy-on-cachyos` y Snapper)* |
| Swap | "Swap (with hibernate)" o "Swap to file" — cualquiera vale |
| Cifrado | **Sin cifrado** en VM (LUKS funciona pero complica recuperar snapshots) |
| Bootloader | **GRUB** (más compatible en VBox que systemd-boot) |
| Kernel | **linux-cachyos** (por defecto) |
| **Desktop Environment** | **Hyprland** *o* **No Desktop / Minimal**. **No instale GNOME ni KDE.** El script de Omarchy fallará si detecta esos DE. |
| Display Manager | Se instalará **SDDM** automáticamente con Hyprland (correcto). |
| **Shell por defecto** | **Fish** *(obligatorio: el script `omarchy-on-cachyos` lo asume)* |
| Paquetes opcionales | Marque `base-devel`. Desmarque `office`, `gaming` y otros bloats si no los necesita. |
| Usuario | Nombre / contraseña. **Active "Use same password for admin"**. |

Pulse **Install** y espere (12-20 min según host).

> **Si Calamares se cierra/cuelga a mitad de instalación:** suele ser por mirror lento. Reabra el instalador, en la pantalla de bienvenida elija "Rate mirrors" antes de relanzar.

Al terminar: **NO marque "Reboot now"** todavía. Primero apague la VM desde el menú.

---

## 6. Primer arranque tras la instalación

### 6.1. Desmontar la ISO

Antes de encender:

1. VirtualBox → **Configuración → Almacenamiento**.
2. Seleccione la ISO bajo el controlador óptico → icono disco → **Remove disk from virtual drive**.
3. Guarde y arranque la VM.

### 6.2. Pantalla de carga negra en el primer login (bug conocido VBox 7.1.x)

Síntoma documentado en `discuss.cachyos.org/t/black-loading-screen-on-first-login-on-vm-in-virtualbox-7-1-12`: tras el logo de CachyOS, queda en negro antes de mostrar SDDM.

**Solución (probada por la comunidad):**

1. Espere 90-120 segundos; en muchos casos termina apareciendo SDDM solo.
2. Si no aparece, pulse `Host + F2` (por defecto **Ctrl Derecho + F2**) para abrir una TTY.
3. Inicie sesión con su usuario.
4. Edite el GRUB:

   ```bash
   sudo nano /etc/default/grub
   ```

   Localice `GRUB_CMDLINE_LINUX_DEFAULT="..."` y añada al final, **antes de la comilla de cierre**:

   ```
   nomodeset module_blacklist=vboxdrv,vboxnetflt,vboxnetadp,vboxpci
   ```

   - `nomodeset`: desactiva el cambio de modo de vídeo del kernel; fuerza el framebuffer básico, evitando el cuelgue.
   - `module_blacklist=...`: impide cargar los módulos *host* de VirtualBox dentro del guest (no se necesitan, y a veces se cargan por error tras el primer update e impiden arrancar).
5. Regenere GRUB y reinicie:

   ```bash
   sudo grub-mkconfig -o /boot/grub/grub.cfg
   sudo reboot
   ```

   - `grub-mkconfig`: regenera el menú de arranque incorporando los parámetros nuevos.

### 6.3. Primera actualización y mirrors

Una vez en el escritorio (SDDM → sesión Hyprland o TTY si eligió minimal), abra una terminal:

```bash
sudo cachyos-rate-mirrors
sudo pacman -Syu --noconfirm
```

- `cachyos-rate-mirrors`: prueba todos los mirrors de CachyOS y reordena `/etc/pacman.d/cachyos-mirrorlist` por latencia. Acelera y estabiliza descargas posteriores.
- `pacman -Syu`: sincroniza la base de datos (`-S y`), refresca repos forzando (`-y`) y actualiza todo el sistema (`-u`). `--noconfirm` salta los prompts de confirmación.

Reinicie: `sudo reboot`.

### 6.4. Guest Additions

CachyOS detecta VirtualBox y suele instalar `virtualbox-guest-utils` automáticamente. Verifique:

```bash
pacman -Q virtualbox-guest-utils
sudo systemctl enable --now vboxservice
```

- `pacman -Q`: consulta si un paquete está instalado.
- `systemctl enable --now`: activa el servicio (`vboxservice` da portapapeles, ajuste de resolución y carpetas compartidas) y lo arranca inmediatamente.

Si NO está instalado:

```bash
sudo pacman -S virtualbox-guest-utils
sudo systemctl enable --now vboxservice
```

> **No instale `virtualbox-guest-utils-nox`** salvo que esté en una instalación *sin* X/Wayland; la versión completa es la correcta para Hyprland.

### 6.5. Resolución personalizada (opcional)

Si quiere 1080p exacto, en el **host** (PowerShell):

```powershell
& "C:\Program Files\Oracle\VirtualBox\VBoxManage.exe" setextradata "CachyOS-Omarchy" "CustomVideoMode1" "1920x1080x24"
```

- `VBoxManage setextradata`: inyecta una entrada `CustomVideoMode` que el guest podrá seleccionar con `xrandr` o `hyprctl`.

---

## 7. Instalación de Omarchy sobre CachyOS

Existen varios scripts comunitarios; el más mantenido y referenciado en Hacker News es **`mroboff/omarchy-on-cachyos`**.

### 7.1. Pre-requisitos que ya debe cumplir su instalación

- [x] Filesystem BTRFS + Snapper.
- [x] Shell `fish`.
- [x] DE: Hyprland de CachyOS **o** sistema minimal sin DE.
- [x] No tener instalado GNOME ni KDE.
- [x] Sistema actualizado (`pacman -Syu`).

### 7.2. Clonar y ejecutar el script

Abra una terminal (en Hyprland: **Super + Return**; en minimal: TTY directa):

```bash
cd ~
git clone https://github.com/mroboff/omarchy-on-cachyos.git
cd omarchy-on-cachyos/bin
chmod +x install-omarchy-on-cachyos.sh
./install-omarchy-on-cachyos.sh
```

Explicación comando por comando:

- `cd ~`: nos coloca en el home del usuario (evita clonar en `/root` por accidente).
- `git clone …`: descarga el repositorio del script. Si falla por DNS, ejecute antes `sudo systemctl restart systemd-resolved`.
- `cd omarchy-on-cachyos/bin`: entra al directorio donde vive el script principal.
- `chmod +x install-omarchy-on-cachyos.sh`: marca el script como ejecutable.
- `./install-omarchy-on-cachyos.sh`: lanza el instalador. El script:
  1. Clona el repo upstream de Omarchy (`basecamp/omarchy`).
  2. Parchea los scripts de Omarchy para que sean compatibles con CachyOS (sustituye `paru` por `yay`, mantiene fish, etc.).
  3. Ejecuta el instalador de Omarchy.

Durará entre 15 y 40 minutos según ancho de banda. **No interrumpa el script**; si se corta, vuelva a ejecutarlo: es idempotente en su mayor parte.

### 7.3. Posibles errores durante el script y soluciones

| Error | Causa | Solución |
|---|---|---|
| `yay: command not found` durante una sub-tarea | Yay no se instaló antes de su uso | Ejecutar manualmente: `sudo pacman -S --needed git base-devel && git clone https://aur.archlinux.org/yay-bin.git && cd yay-bin && makepkg -si` |
| `error: failed retrieving file from <mirror>` | Mirror caído | `sudo cachyos-rate-mirrors && sudo pacman -Syyu` y reintentar el script |
| Conflictos `paru` vs `yay` | Ambos AUR helpers presentes | `sudo pacman -Rns paru` y volver a lanzar el script |
| `failed to commit transaction (conflicting files)` | Paquetes de Hyprland CachyOS chocan con los que pide Omarchy | `sudo pacman -Syu --overwrite '*'` y reintentar (sólo en VM, no en bare metal sin entender qué sobrescribe) |
| Aborta por `chaotic-aur` no disponible | Repo opcional no añadido | Es seguro ignorar; CachyOS ya provee lo necesario |

### 7.4. Reinicio final

Al terminar el script:

```bash
sudo reboot
```

SDDM debe presentar la sesión **"Hyprland"** (o **"omarchy"** según versión). Inicie sesión.

---

## 8. Ajustes obligatorios post-Omarchy dentro de la VM

Sin estos ajustes Hyprland arranca con escalado roto y/o la terminal no se abre.

### 8.1. Arreglar escalado de monitor (problema 2x → 1x)

Omarchy asume pantalla retina 2x; en VirtualBox esto rompe la mitad de la pantalla.

Edite el fichero de monitores:

```bash
nano ~/.config/hypr/monitors.conf
```

Busque líneas tipo:

```
# 1x Scaling
# monitor=,preferred,auto,1
# 2x Retina Scaling
monitor=,preferred,auto,2
```

Déjelo así (comente la 2x, descomente la 1x):

```
# 1x Scaling
monitor=,preferred,auto,1
# 2x Retina Scaling
# monitor=,preferred,auto,2
```

Guarde (`Ctrl+O`, `Enter`, `Ctrl+X`).

A continuación edite `hyprland.conf` y baje `GDK_SCALE`:

```bash
nano ~/.config/hypr/hyprland.conf
```

Busque `env = GDK_SCALE,2` y cámbielo a:

```
env = GDK_SCALE,1
```

- `GDK_SCALE`: variable que usan apps GTK para multiplicar tamaños. En 2 todo aparece gigante en una VM 1080p; en 1 queda nativo.

### 8.2. Forzar render por software si la terminal no abre

Si pulsa **Super + Return** y no pasa nada, o solo abre el navegador/Firefox pero Alacritty/Ghostty no:

```bash
nano ~/.config/hypr/envs.conf
```

Añada al final:

```
env = LIBGL_ALWAYS_SOFTWARE,1
env = WLR_RENDERER_ALLOW_SOFTWARE,1
env = WLR_NO_HARDWARE_CURSORS,1
```

- `LIBGL_ALWAYS_SOFTWARE=1`: obliga a Mesa a usar el renderer software (llvmpipe). Resuelve el caso documentado en `basecamp/omarchy#72`.
- `WLR_RENDERER_ALLOW_SOFTWARE=1`: permite a wlroots (la librería sobre la que se construye Hyprland) usar el renderer software.
- `WLR_NO_HARDWARE_CURSORS=1`: desactiva cursores hardware (en VBox suelen quedar invisibles).

Recargue Hyprland sin reiniciar: **Super + Esc** (algunas versiones) o cierre sesión desde el menú de Omarchy y vuelva a entrar.

### 8.3. Alternativa: activar aceleración 3D en VirtualBox

Si lo anterior no resuelve y prefiere usar aceleración real:

1. Apague la VM.
2. **Configuración → Pantalla → Aceleración 3D: marcado**.
3. Reinicie. Si Hyprland arranca con cursor visible y terminal funcional, mantenga así.
4. Si tras activarlo se rompe, vuelva a desmarcarlo y use la solución 8.2.

> No existe una combinación universal: depende de la versión exacta de VirtualBox, del host y de la build de Mesa que tenga CachyOS ese día. Pruebe ambos caminos.

### 8.4. Cambio de TTY dentro de la VM

`Ctrl + Alt + Fn` no funciona desde dentro de VirtualBox (lo intercepta el host). Use **`Host + Fn`** → por defecto **Ctrl Derecho + F2 / F3…**. F1 es el SDDM o la sesión gráfica.

---

## 9. Snapshots y rollback (red de seguridad)

Como instaló sobre BTRFS + Snapper, antes de tocar nada experimental haga una snapshot:

```bash
sudo snapper -c root create --description "post-omarchy-fresh"
```

- `snapper -c root create`: crea un snapshot del subvolumen raíz etiquetado.

Para listarlas:

```bash
sudo snapper -c root list
```

Si una actualización rompe el arranque: desde GRUB elija **Snapshots** → seleccione una anterior → arranque → si funciona, hágala permanente con `sudo snapper rollback <id>`.

---

## 10. Checklist final de verificación

Después del primer login en Omarchy compruebe, en orden:

```bash
hyprctl monitors          # debe listar al menos un monitor con scale 1.0
echo $XDG_SESSION_TYPE    # debe imprimir "wayland"
echo $SHELL               # debe imprimir /usr/bin/fish
which yay && yay --version
lsmod | grep vboxguest    # debe aparecer cargado
```

- `hyprctl monitors`: pregunta a Hyprland por los monitores activos. Sirve para confirmar que el escalado quedó correcto.
- `lsmod | grep vboxguest`: verifica que los Guest Additions están cargados.

Si los cinco comandos responden como se espera, la instalación está **completa y estable**.

---

## 11. Tabla resumen de posibles fallos y comando-solución

| Fallo | Comando(s) de rescate | Qué hace |
|---|---|---|
| Pantalla negra tras logo CachyOS (live) | Apagar, cambiar VBoxSVGA ↔ VBoxVGA en Pantalla | Alterna controlador gráfico |
| Pantalla negra tras primer reboot | TTY (Ctrl Der + F2) → editar `/etc/default/grub` añadiendo `nomodeset module_blacklist=vboxdrv,vboxnetflt,vboxnetadp,vboxpci` → `sudo grub-mkconfig -o /boot/grub/grub.cfg && sudo reboot` | Desactiva KMS y módulos host VBox erróneamente cargados |
| Mirrors lentos o caídos | `sudo cachyos-rate-mirrors && sudo pacman -Syyu` | Reordena mirrors por latencia y refuerza re-descarga |
| Terminal no abre en Hyprland | Añadir `LIBGL_ALWAYS_SOFTWARE,1` a `~/.config/hypr/envs.conf` | Render software, evita crash GL |
| Todo enorme / cortado | `GDK_SCALE,1` en `hyprland.conf` + monitor a `,preferred,auto,1` | Quita el escalado retina 2x |
| Lockout tras fallar contraseña | TTY → `sudo faillock --reset --user <usuario>` | Resetea contador de fallos PAM |
| Hyprland se reinicia en bucle | Sesión TTY → `omarchy-reinstall` | Restaura dotfiles por defecto de Omarchy |
| Update rompe el sistema | GRUB → Snapshots → arrancar uno previo → `sudo snapper rollback <id>` | Rollback BTRFS al estado previo |
| Yay falta | `sudo pacman -S --needed git base-devel && git clone https://aur.archlinux.org/yay-bin.git && cd yay-bin && makepkg -si` | Compila e instala yay desde AUR |
| Audio sin salida | Click icono altavoz en waybar (top-right) → elegir salida correcta | Selecciona sink de PipeWire |
| Portapapeles host↔guest no va | `sudo systemctl restart vboxservice` y en VBox: Dispositivos → Portapapeles compartido → Bidireccional | Reinicia servicio Guest Additions |

---

## 12. Fuentes consultadas y validadas

- **CachyOS wiki oficial** — Pre-instalación: <https://wiki.cachyos.org/installation/installation_prepare/>
- **CachyOS — Instalador GUI (Calamares)**: <https://wiki.cachyos.org/cachyos_basic/changelogs/gui_installer/>
- **ComputingForGeeks — How to Install CachyOS step-by-step (UEFI)**: <https://computingforgeeks.com/install-cachyos-step-by-step/>
- **ComputingForGeeks — Install Omarchy (Arch + Hyprland)**: <https://computingforgeeks.com/install-omarchy-arch-hyprland-linux/>
- **Omarchy oficial**: <https://omarchy.org/>
- **Manual oficial de Omarchy — Troubleshooting**: <https://learn.omacom.io/2/the-omarchy-manual/88/troubleshooting>
- **GitHub — `mroboff/omarchy-on-cachyos`** (script principal): <https://github.com/mroboff/omarchy-on-cachyos>
- **GitHub Discussion — Omarchy on VirtualBox (#176)**: <https://github.com/basecamp/omarchy/discussions/176>
- **GitHub Issue — Some apps do not function (VirtualBox)**: <https://github.com/basecamp/omarchy/issues/72>
- **CachyOS Forum — Black loading screen on VBox 7.1.12**: <https://discuss.cachyos.org/t/black-loading-screen-on-first-login-on-vm-in-virtualbox-7-1-12/11676>
- **CachyOS Forum — Live boot blank screen**: <https://discuss.cachyos.org/t/live-boot-environment-blank-screen/16271>
- **CachyOS Forum — Cachy as client of VirtualBox 7.1.4**: <https://discuss.cachyos.org/t/cachyos-as-client-of-virtualbox-7-1-4/5555>
- **Baeldung — VBoxVGA vs VMSVGA vs VBoxSVGA**: <https://www.baeldung.com/linux/vboxvga-vmsvga-vboxsvga-virtualbox>
- **YouTube — CachyOS Installation on VirtualBox, Step-by-Step (2026)**: <https://www.youtube.com/watch?v=el-yGdxZOJQ>
- **YouTube — Installing CachyOS on VirtualBox**: <https://www.youtube.com/watch?v=Io0nEtEegaA>
- **YouTube — Install CachyOS in VirtualBox (tutorial beginners)**: <https://www.youtube.com/watch?v=7IFy-SNZDYk>
- **Hacker News — Show HN: Omarchy on CachyOS**: <https://news.ycombinator.com/item?id=45246325>
- **RobWillis.info — Installing Omarchy on VMware (referencia cruzada de ajustes VM)**: <https://www.robwillis.info/2025/11/installing-omarchy-on-vmware-workstation/>

---

### Última recomendación

Antes de aplicar cualquier paso destructivo (reinstalar, `pacman -Syu --overwrite`, rollback) **haga snapshot tanto de VirtualBox** (botón "Tomar instantánea" en el menú Máquina) **como de Snapper** (§9). En VM no cuesta nada y permite volver atrás en segundos si algo sale mal.
