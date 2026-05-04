# Práctica: SSH — Acceso Remoto Seguro
### Fuente: Ejecutar scripts en remoto. SSH.pdf

> El PDF es un tutorial guiado. Cada sección corresponde a una práctica que hay que realizar. Los bloques siguientes cubren todo el proceso de instalación, conexión, ejecución de comandos y scripts remotos, generación de claves y copia de archivos.
> **Máquinas de ejemplo:** `smb1` (servidor, IP `192.168.100.100`) · `smb2` (cliente, IP `192.168.100.101`)

---

## Índice

| Bloque | Proceso |
|--------|---------|
| [Bloque 1](#bloque-1) | Instalación del servidor SSH y verificación del servicio |
| [Bloque 2](#bloque-2) | Conexión básica entre dos máquinas con ssh |
| [Bloque 3](#bloque-3) | Ejecución de comandos remotos sin abrir sesión interactiva |
| [Bloque 4](#bloque-4) | Ejecutar comandos interactivos que requieren terminal con ssh -t |
| [Bloque 5](#bloque-5) | Ejecutar un script que reside en el servidor |
| [Bloque 6](#bloque-6) | Ejecutar desde el cliente un script que está en la máquina local |
| [Bloque 7](#bloque-7) | Generar par de claves ed25519 en el cliente |
| [Bloque 8](#bloque-8) | Copiar la clave pública al servidor con ssh-copy-id |
| [Bloque 9](#bloque-9) | Verificar la autenticación sin contraseña y comprobar authorized_keys |
| [Bloque 10](#bloque-10) | Copiar archivos entre máquinas con scp |

---

<a id="bloque-1"></a>
## Bloque 1: Instalación del servidor SSH y verificación del servicio

> **ENUNCIADO**
> Instala el servidor SSH en la máquina que actuará como servidor (`smb1`) y el cliente SSH en la que actuará como cliente (`smb2`). Comprueba que el servicio está activo.

### Solución — EN EL SERVIDOR (smb1)

**Paso 1: Instalar el servidor OpenSSH**
```bash
sudo apt update
sudo apt install openssh-server
```
> **Anotación:** `openssh-server` instala el demonio `sshd` (SSH Daemon), que escucha conexiones entrantes en el puerto TCP 22. OpenSSH es la implementación libre y de código abierto del protocolo SSH. Incluye: `sshd` (servidor), `ssh` (cliente), `scp` (copia de archivos), `ssh-keygen` (generación de claves).

**Paso 2: Comprobar el estado del servicio**
```bash
sudo systemctl status ssh
```
> **Anotación:** Si aparece `Active: active (running)`, el servidor SSH está funcionando y aceptando conexiones. Si no está activo: `sudo systemctl start ssh`. Para que arranque automáticamente con el sistema: `sudo systemctl enable ssh`.

**Paso 3: Verificar en qué puerto está escuchando**
```bash
ss -tlnp | grep ssh
```
> **Anotación:** Confirma que el proceso `sshd` está escuchando en el puerto 22. `ss -tlnp` muestra los sockets TCP en escucha (l=listen, n=numérico, p=proceso).

---

**EN EL CLIENTE (smb2):**

**Paso 4: Instalar el cliente OpenSSH (normalmente ya viene instalado)**
```bash
sudo apt install openssh-client
```
> **Anotación:** El paquete `openssh-client` incluye los comandos `ssh`, `scp`, `ssh-keygen` y `ssh-copy-id`. En la mayoría de instalaciones de Ubuntu ya viene instalado por defecto.

---

<a id="bloque-2"></a>
## Bloque 2: Conexión básica entre dos máquinas con ssh

> **ENUNCIADO**
> Desde el cliente `smb2`, establece una conexión SSH con el servidor `smb1` (`192.168.100.100`) usando el usuario `ana`.

### Solución — EN EL CLIENTE (smb2)

**Paso 1: Conectarse al servidor por SSH**
```bash
ssh ana@192.168.100.100
```
> **Anotación:**
> - `ssh` — Comando cliente de SSH.
> - `ana` — Usuario con el que queremos iniciar sesión en el servidor. Debe existir en el servidor.
> - `192.168.100.100` — IP del servidor. También se puede usar el nombre de host si hay DNS: `ssh ana@smb1`.

**Paso 2: Primera conexión — aceptar la clave del servidor**
```
The authenticity of host '192.168.100.100' can't be established.
ED25519 key fingerprint is SHA256:...
Are you sure you want to continue connecting (yes/no/[fingerprint])?
```
Escribir `yes` y pulsar Enter.
> **Anotación:** En la primera conexión, SSH no conoce la clave del servidor y pregunta si confiamos en él. Al escribir `yes`, la clave pública del servidor se guarda en el archivo `~/.ssh/known_hosts` del cliente. En futuras conexiones, SSH compara la clave del servidor con la guardada: si coincide, conecta sin preguntar; si no coincide, lanza una advertencia de posible ataque **man-in-the-middle**.

**Paso 3: Introducir la contraseña del usuario en el servidor**
```
ana@192.168.100.100's password:
```
> **Anotación:** La contraseña es la del usuario `ana` en el servidor `smb1`, no en el cliente. Una vez introducida, se abre una sesión de terminal en el servidor: el prompt cambia para indicar que ahora estamos en `smb1`.

**Paso 4: Comprobar que estamos en el servidor remoto**
```bash
hostname
whoami
```
> Deben mostrar el nombre del servidor (`smb1` o `ana@smb1`) confirmando que el terminal está en la máquina remota.

**Paso 5: Cerrar la sesión SSH**
```bash
exit
```
> **Anotación:** `exit` cierra la sesión SSH y devuelve el control al terminal del cliente. Equivalente a `Ctrl+D`.

---

<a id="bloque-3"></a>
## Bloque 3: Ejecución de comandos remotos sin abrir sesión interactiva

> **ENUNCIADO**
> Desde el cliente `smb2`, ejecuta comandos en el servidor `smb1` sin abrir una sesión interactiva: un solo comando, varios comandos con `;` (siempre se ejecutan) y varios comandos con `&&` (el segundo solo si el primero tiene éxito).

### Solución — EN EL CLIENTE (smb2)

**Ejecutar un solo comando remoto**
```bash
ssh ana@192.168.100.100 "ls -la"
```
> **Anotación:** Al añadir el comando entre comillas al final, SSH lo ejecuta en el servidor y devuelve la salida al terminal del cliente, sin abrir una sesión interactiva. El comando se ejecuta en el contexto del usuario `ana` en el servidor.

**Ejecutar varios comandos en secuencia (siempre se ejecutan todos)**
```bash
ssh ana@192.168.100.100 "comando1; comando2; comando3"
```
> **Anotación:** El punto y coma `;` es el separador de comandos en bash. Los comandos se ejecutan uno tras otro independientemente de si el anterior tuvo éxito o falló. Si `comando1` falla, `comando2` se ejecuta igualmente.

**Ejecutar varios comandos en cadena (el siguiente solo si el anterior tiene éxito)**
```bash
ssh ana@192.168.100.100 "mkdir /tmp/prueba && touch /tmp/prueba/archivo.txt"
```
> **Anotación:** El operador `&&` (AND lógico) hace que `comando2` solo se ejecute si `comando1` terminó con éxito (código de retorno 0). Si `mkdir` falla (por ejemplo porque el directorio ya existe), `touch` no se ejecuta. Esto es útil para encadenar operaciones que dependen unas de otras.

**Ejemplo práctico: actualizar el sistema en el servidor desde el cliente**
```bash
ssh ana@192.168.100.100 "sudo apt update && sudo apt upgrade -y"
```
> **Anotación:** Ejecuta la actualización de repositorios en el servidor. Si `apt update` falla, no intenta `apt upgrade`. El `-y` responde "sí" automáticamente a las preguntas de confirmación.

---

<a id="bloque-4"></a>
## Bloque 4: Ejecutar comandos interactivos que requieren terminal con ssh -t

> **ENUNCIADO**
> Algunos comandos requieren una terminal asignada para funcionar. Usa `ssh -t` para ejecutarlos en el servidor desde el cliente.

### Solución — EN EL CLIENTE (smb2)

**Ejecutar un comando interactivo con terminal asignada**
```bash
ssh -t ana@192.168.100.100 "sudo apt update"
```
> **Anotación:** La opción `-t` crea una **pseudo-terminal** (pseudo-TTY) en la sesión SSH. Algunos comandos requieren una terminal real para funcionar, por ejemplo:
> - `sudo` en ciertos modos (pide contraseña y necesita terminal).
> - Editores de texto (`nano`, `vim`).
> - Comandos que usan colores o interfaces de texto interactivas (`top`, `htop`).
>
> Sin `-t`, estos comandos pueden fallar con errores como "sudo: no tty present" o comportarse de forma incorrecta.

**Ejecutar un editor de texto remotamente**
```bash
ssh -t ana@192.168.100.100 "nano /etc/hosts"
```
> **Anotación:** `nano` necesita terminal. Con `-t` se puede editar archivos remotos directamente desde el cliente sin necesidad de copiarlos primero.

---

<a id="bloque-5"></a>
## Bloque 5: Ejecutar un script que reside en el servidor

> **ENUNCIADO**
> El archivo `script.sh` existe en el directorio `/home/ana/` del servidor. Ejecútalo desde el cliente sin abrir sesión interactiva.

### Solución — EN EL CLIENTE (smb2)

**Paso 1: Verificar que el script existe en el servidor**
```bash
ssh ana@192.168.100.100 "ls -la /home/ana/script.sh"
```

**Paso 2: Ejecutar el script remoto**
```bash
ssh ana@192.168.100.100 "bash /home/ana/script.sh"
```
> **Anotación:**
> - `bash` — Intérprete que ejecuta el script. Se puede usar también `sh`.
> - `/home/ana/script.sh` — Ruta absoluta al script en el servidor.
> - La salida del script aparece en el terminal del cliente.
>
> **Alternativa si el script tiene permiso de ejecución (`+x`):**
```bash
ssh ana@192.168.100.100 "/home/ana/script.sh"
```

---

<a id="bloque-6"></a>
## Bloque 6: Ejecutar desde el cliente un script que está en la máquina local

> **ENUNCIADO**
> Tienes el archivo `script.sh` en el directorio actual de tu máquina cliente. Ejecútalo en el servidor sin copiarlo previamente.

### Solución — EN EL CLIENTE (smb2)

**Paso 1: Verificar que el script existe en el cliente**
```bash
ls -la script.sh
cat script.sh
```

**Paso 2: Ejecutar el script local en el servidor remoto**
```bash
ssh ana@192.168.100.100 'bash -s' < script.sh
```
> **Anotación — Cómo funciona esta sintaxis:**
> - `< script.sh` es una **redirección de entrada**. El contenido del archivo `script.sh` del cliente se envía como entrada estándar (stdin) a través de la conexión SSH.
> - `'bash -s'` le dice al intérprete `bash` del servidor que lea el script de la entrada estándar (`-s` = stdin) en lugar de un archivo local.
> - El script se ejecuta completamente en el servidor, pero nunca se guarda en el servidor. Es como si el cliente "transmitiera en directo" el script al servidor.
> - Se usan comillas simples `'bash -s'` para evitar que el shell del cliente interprete el comando antes de enviarlo al servidor.

**Comparativa de los dos métodos:**
| Situación | Comando |
|-----------|---------|
| Script en el servidor | `ssh usuario@ip "bash /ruta/script.sh"` |
| Script en el cliente | `ssh usuario@ip 'bash -s' < script.sh` |

---

<a id="bloque-7"></a>
## Bloque 7: Generar par de claves ed25519 en el cliente

> **ENUNCIADO**
> En la máquina cliente `smb2`, genera un par de claves SSH usando el algoritmo ed25519. Verifica los archivos generados.

### Solución — EN EL CLIENTE (smb2)

**Paso 1: Generar el par de claves**
```bash
ssh-keygen -t ed25519
```
> **Anotación:**
> - `ssh-keygen` — Herramienta de generación de claves criptográficas SSH.
> - `-t ed25519` — Especifica el algoritmo de clave. `ed25519` es el más moderno y seguro actualmente. Alternativas más antiguas: `rsa` (2048 o 4096 bits), `ecdsa`. Para nuevas instalaciones siempre se recomienda `ed25519`.

**Paso 2: Responder a las preguntas del generador**
```
Enter file in which to save the key (/home/bea/.ssh/id_ed25519):
```
> Pulsar **Enter** para aceptar la ubicación por defecto (`~/.ssh/id_ed25519`).

```
Enter passphrase (empty for no passphrase):
Enter same passphrase again:
```
> **Anotación sobre la passphrase:** La passphrase es una contraseña adicional que protege la clave privada. Si alguien obtiene el archivo `id_ed25519`, no puede usarlo sin conocer también la passphrase. Para uso interactivo se recomienda una passphrase fuerte. Para automatización (scripts, cron), se deja vacía porque no habría nadie para escribirla.

**Paso 3: Verificar los archivos generados**
```bash
ls -la ~/.ssh/
```
> **Anotación — Archivos creados:**
> - `id_ed25519` — La **clave privada**. Nunca debe salir del cliente. Si alguien la obtiene, puede suplantar tu identidad. Permisos: `600` (solo el propietario puede leerla).
> - `id_ed25519.pub` — La **clave pública**. Es el par matemático de la privada. Esta es la que se copia al servidor. Se puede compartir libremente; sin la clave privada correspondiente, no sirve para nada.

**Paso 4: Ver el contenido de la clave pública (para entender qué se copiará al servidor)**
```bash
cat ~/.ssh/id_ed25519.pub
```
> El formato es: `algoritmo clave_en_base64 comentario`. El comentario suele ser `usuario@nombre_máquina` para identificar de dónde viene la clave.

---

<a id="bloque-8"></a>
## Bloque 8: Copiar la clave pública al servidor con ssh-copy-id

> **ENUNCIADO**
> Copia la clave pública generada en el Bloque 7 al servidor `smb1` para que el cliente `smb2` pueda conectarse sin contraseña.

### Solución — EN EL CLIENTE (smb2)

**Paso 1: Copiar la clave pública al servidor**
```bash
ssh-copy-id ana@192.168.100.100
```
> **Anotación — Qué hace `ssh-copy-id` exactamente:**
> 1. Se conecta al servidor con la contraseña del usuario (por última vez).
> 2. Crea el directorio `~/.ssh/` en el servidor si no existe (con permisos `700`).
> 3. Añade el contenido de `~/.ssh/id_ed25519.pub` al final del archivo `~/.ssh/authorized_keys` en el servidor.
> 4. Ajusta los permisos de `authorized_keys` a `600`.
>
> `ssh-copy-id` automatiza un proceso que también se puede hacer manualmente copiando la clave pública y añadiéndola al archivo `authorized_keys`.

**Paso 2: Confirmar con la contraseña (última vez que se necesitará)**
```
ana@192.168.100.100's password:
```
> A partir de este momento, esta contraseña ya no será necesaria para conectarse desde este cliente.

---

<a id="bloque-9"></a>
## Bloque 9: Verificar la autenticación sin contraseña y comprobar authorized_keys

> **ENUNCIADO**
> Verifica que la autenticación por clave funciona conectándose sin contraseña. Comprueba en el servidor que la clave pública se ha registrado correctamente.

### Solución

**Paso 1: Conectarse al servidor sin contraseña desde el cliente**
```bash
ssh ana@192.168.100.100
```
> **Anotación:** Si el proceso funciona correctamente, esta vez no pedirá contraseña. La sesión se abrirá directamente. Si sigue pidiendo contraseña, revisar los permisos de `~/.ssh/` (deben ser `700`) y de `authorized_keys` (deben ser `600`).

**Paso 2: Verificar en el servidor que la clave está registrada**
```bash
# En el servidor:
ls ~/.ssh/
cat ~/.ssh/authorized_keys
```
> **Anotación:** En `authorized_keys` debe aparecer la clave pública del cliente (el contenido de `id_ed25519.pub` del cliente). Cada línea de `authorized_keys` representa una clave autorizada. Se pueden añadir múltiples claves (de diferentes clientes) en líneas separadas.

**Cómo funciona la autenticación por clave (resumen del mecanismo):**

1. El cliente inicia la conexión SSH.
2. El servidor genera un **mensaje aleatorio** (desafío) y lo cifra con la clave pública del cliente (que tiene en `authorized_keys`).
3. Envía el mensaje cifrado al cliente.
4. El cliente usa su **clave privada** (`id_ed25519`) para descifrar el mensaje.
5. Envía la respuesta al servidor.
6. El servidor verifica que la respuesta es correcta → acceso concedido.

> **Clave del mecanismo:** La clave privada **nunca sale del cliente**. Lo que demuestra la identidad es ser capaz de descifrar algo que solo la clave privada puede descifrar. Es matemáticamente imposible derivar la clave privada a partir de la pública.

**Archivos del directorio ~/.ssh/ y su función:**

| Archivo | Dónde vive | Función |
|---------|-----------|---------|
| `id_ed25519` | **Cliente** | Clave privada. Nunca se comparte. |
| `id_ed25519.pub` | **Cliente** | Clave pública. Se copia al servidor. |
| `authorized_keys` | **Servidor** | Lista de claves públicas autorizadas a conectarse. |
| `known_hosts` | **Cliente** | Claves públicas de los servidores conocidos. Protege contra man-in-the-middle. |

---

<a id="bloque-10"></a>
## Bloque 10: Copiar archivos entre máquinas con scp

> **ENUNCIADO**
> Usa `scp` para copiar archivos del cliente al servidor, del servidor al cliente y carpetas completas de forma recursiva.

### Solución

**Copiar un archivo del cliente al servidor**
```bash
scp fichero.txt ana@192.168.100.100:/home/ana/
```
> **Anotación:**
> - `scp` (Secure Copy Protocol) copia archivos usando SSH como canal cifrado. La sintaxis es similar al comando `cp` local, pero el origen o destino puede ser remoto.
> - `fichero.txt` — Archivo en el directorio actual del cliente.
> - `ana@192.168.100.100:/home/ana/` — Destino remoto: usuario, IP del servidor y ruta de destino.
> - El archivo quedará en `/home/ana/fichero.txt` en el servidor.

**Copiar un archivo del servidor al cliente**
```bash
scp ana@192.168.100.100:/home/ana/fichero.txt ~/Descargas/
```
> **Anotación:** El orden se invierte: primero el origen remoto (`servidor:ruta`), luego el destino local. El archivo se descarga al directorio `~/Descargas/` del cliente.

**Copiar una carpeta completa de forma recursiva**
```bash
scp -r mi_proyecto ana@192.168.100.100:/var/www/
```
> **Anotación:** La opción `-r` (recursivo) copia el directorio y todo su contenido, incluyendo subdirectorios. Sin `-r`, `scp` falla al intentar copiar un directorio.

**Diferencia entre scp y rsync:**
> `scp` copia siempre el archivo completo aunque ya exista en el destino. `rsync` es más inteligente: solo transfiere las partes que han cambiado. Para copias únicas, `scp` es suficiente; para sincronizaciones repetidas, `rsync` es más eficiente.

**Ejemplo práctico completo: desplegar un script en el servidor y ejecutarlo**
```bash
# 1. Copiar el script al servidor
scp deploy.sh ana@192.168.100.100:/tmp/

# 2. Ejecutarlo en el servidor
ssh ana@192.168.100.100 "bash /tmp/deploy.sh"
```
