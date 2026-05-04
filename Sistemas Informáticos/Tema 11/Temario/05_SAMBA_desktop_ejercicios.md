# Ejercicios: Samba en Ubuntu Desktop
### Fuente: Samba en desktop 2025.pdf

> Práctica con dos máquinas virtuales Ubuntu Desktop.
> **smb1** (servidor, `192.168.puesto.100/24`) · **smb2** (cliente, `192.168.puesto.200/24`)
> El número de puesto lo indica el profesor (ej: puesto 12 → `192.168.12.100` y `192.168.12.200`)

---

## Índice

| Bloque | Proceso |
|--------|---------|
| [Bloque 1](#bloque-1) | Crear la carpeta "Compartida" e intentar compartirla — descubrir que falta el servicio |
| [Bloque 2](#bloque-2) | Instalar el servicio Samba desde el asistente gráfico |
| [Bloque 3](#bloque-3) | Configurar el recurso compartido con acceso de invitados y permisos de escritura |
| [Bloque 4](#bloque-4) | Verificar que Samba está instalado y comprobar el estado del servicio |
| [Bloque 5](#bloque-5) | Configurar la IP estática de ambas máquinas en Red Interna de VirtualBox |
| [Bloque 6](#bloque-6) | Verificar la conectividad de red entre smb1 y smb2 |
| [Bloque 7](#bloque-7) | Explorar el recurso compartido desde el cliente smb2 en el entorno gráfico |
| [Bloque 8](#bloque-8) | Crear carpetas "deanonimo" y "deusuario" en la carpeta compartida desde smb2 |
| [Bloque 9](#bloque-9) | Comprobar propietarios y permisos del contenido compartido desde la terminal |
| [Bloque 10](#bloque-10) | Modificar el recurso para denegar el acceso de invitados y explicar los efectos |
| [Bloque 11](#bloque-11) | Determinar si smb2 necesita instalar Samba para acceder a recursos compartidos |
| [Bloque 12](#bloque-12) | Configurar smb1 para permitir el acceso de usuarios identificados con contraseña |
| [Bloque 13](#bloque-13) | Instalar el cliente Samba en terminal (smbclient) en smb2 |
| [Bloque 14](#bloque-14) | Listar los recursos compartidos del servidor desde la terminal |
| [Bloque 15](#bloque-15) | Conectarse al recurso compartido como usuario Samba desde la terminal |

---

<a id="bloque-1"></a>
## Bloque 1: Crear la carpeta "Compartida" e intentar compartirla — descubrir que falta el servicio

> **ENUNCIADO**
> En la máquina `smb1`, cree una carpeta llamada "Compartida" e intente compartirla desde el entorno gráfico. El sistema avisará que el servicio de compartición no está instalado.

### Solución — EN smb1

**Paso 1: Crear la carpeta desde el gestor de archivos**
> En el escritorio de Ubuntu Desktop, abrir el gestor de archivos (Nautilus), navegar al directorio home del usuario (`/home/usuario`) y crear una nueva carpeta llamada "Compartida" (clic derecho → Nueva carpeta).

**Paso 2: Intentar compartir la carpeta**
> Clic derecho sobre la carpeta "Compartida" → Propiedades → pestaña "Recurso compartido de red local".

**Resultado esperado:**
> Aparece un diálogo que indica: "El servicio de compartición no está instalado. Necesita instalar el servicio de compartición de redes Windows para poder compartir sus carpetas."
>
> Con dos botones: **Cerrar** e **Instalar el servicio**.

> **Anotación:** Este mensaje es la forma que tiene Ubuntu Desktop de decirte que Samba no está instalado. El protocolo SMB/CIFS (el protocolo que usa Windows para compartir archivos) necesita el paquete Samba para funcionar en Linux. Sin Samba, el sistema operativo puede ver la configuración gráfica de compartición, pero no tiene el motor que la hace funcionar en la red.

---

<a id="bloque-2"></a>
## Bloque 2: Instalar el servicio Samba desde el asistente gráfico

> **ENUNCIADO**
> Instala el servicio Samba usando el botón "Instalar el servicio" que apareció en el Bloque 1.

### Solución — EN smb1

**Opción A: Desde el diálogo gráfico**
> En el diálogo del Bloque 1, hacer clic en **"Instalar el servicio"**. Ubuntu abrirá el gestor de paquetes y descargará e instalará el paquete Samba automáticamente. Se pedirá la contraseña de administrador.

**Opción B: Desde la terminal (equivalente)**
```bash
sudo apt update
sudo apt install samba
```
> **Anotación:** Ambos métodos instalan exactamente el mismo paquete. La instalación gráfica simplemente llama a `apt` por debajo. Para la parte de servidores en terminal (Bloque Servidor Samba), siempre se usa la terminal.

**¿Qué instala Samba?**
> Ubuntu Desktop nos muestra una descripción del paquete durante la instalación:
> - *"Samba es una implementación del protocolo SMB/CIFS para sistemas UNIX, proporcionando la posibilidad de compartir archivos e impresoras entre plataformas como Microsoft Windows, OS X y otros sistemas UNIX."*
> - *"Este paquete no es necesario para conectarse a los servidores de SMB/CIFS existentes (véase smbclient) o para montar sistemas de archivos remotos (véase cifs-utils)."*

> **Anotación de la segunda nota:** Este punto es fundamental para el examen: el paquete `samba` (servidor) no es necesario en el **cliente**. Los clientes que solo van a **acceder** a recursos Samba solo necesitan `smbclient` (para terminal) o `cifs-utils` (para montar). Ver Bloque 11.

---

<a id="bloque-3"></a>
## Bloque 3: Configurar el recurso compartido con acceso de invitados y permisos de escritura

> **ENUNCIADO**
> Realiza la compartición de la carpeta "Compartida" de smb1 permitiendo el acceso a invitados y a otras personas crear y eliminar archivos en la carpeta.

### Solución — EN smb1

**Paso 1: Abrir las propiedades de la carpeta de nuevo**
> Clic derecho sobre "Compartida" → Propiedades → pestaña "Recurso compartido de red local".

**Paso 2: Configurar las opciones de compartición**
> Ahora que Samba está instalado, el diálogo muestra las opciones completas:
> - ☑ **Compartir esta carpeta** — Marcar para activar el recurso compartido.
> - **Nombre compartido:** `Compartida` (se rellena automáticamente con el nombre de la carpeta, editable).
> - ☑ **Permitir a otras personas crear y eliminar archivos en esta carpeta** — Activa los permisos de escritura.
> - ☑ **Acceso invitado (para personas sin una cuenta de usuario)** — Permite que cualquiera acceda sin contraseña.

**Paso 3: Hacer clic en "Crear compartición"**

> **Anotación — Qué configura esto en smb.conf:**
> El entorno gráfico modifica automáticamente `/etc/samba/smb.conf` añadiendo una sección equivalente a:
```ini
[Compartida]
   path = /home/usuario/Compartida
   read only = no
   guest ok = yes
```
> - `read only = no` corresponde a "Permitir crear y eliminar archivos".
> - `guest ok = yes` corresponde a "Acceso invitado".

---

<a id="bloque-4"></a>
## Bloque 4: Verificar que Samba está instalado y comprobar el estado del servicio

> **ENUNCIADO**
> Comprueba desde la terminal que Samba se ha instalado correctamente y verifica el estado en que se encuentra el servicio.

### Solución — EN smb1

**Comprobar si los paquetes están instalados**
```bash
dpkg -l | grep samba
```
> **Anotación:** `dpkg -l` lista todos los paquetes instalados en el sistema. Con `| grep samba` filtramos solo los relacionados con Samba. Si están instalados correctamente aparecen con el prefijo `ii` (installed) en la primera columna.

**Comprobar el estado del servicio smbd**
```bash
sudo systemctl status smbd
```
> **Anotación:**
> - `smbd` es el demonio principal de Samba que gestiona la compartición de archivos y la autenticación. Si el estado es `Active: active (running)`, el servicio está funcionando.
> - El segundo demonio, `nmbd`, gestiona la resolución de nombres NetBIOS (para aparecer en el entorno de red de Windows). Se puede comprobar también con `sudo systemctl status nmbd`.

**Comprobar ambos servicios de una vez**
```bash
sudo systemctl status smbd nmbd
```

---

<a id="bloque-5"></a>
## Bloque 5: Configurar la IP estática de ambas máquinas en Red Interna de VirtualBox

> **ENUNCIADO**
> Configura la red de ambas máquinas virtuales en VirtualBox como "Red Interna" y asigna IPs estáticas:
> - **smb1:** `192.168.puesto.100/24`
> - **smb2:** `192.168.puesto.200/24`

### Solución

**Paso 1: Cambiar el adaptador de red en VirtualBox**
> En VirtualBox, con la máquina apagada:
> 1. Configuración → Red → Adaptador 1 → Conectado a: **Red Interna**.
> 2. Nombre: `intnet` (o el nombre que use el profesor para que ambas estén en la misma red interna).
> 3. Repetir para la segunda máquina.

> **Anotación — Por qué Red Interna:** La Red Interna de VirtualBox crea una red local entre las máquinas virtuales sin acceso al exterior. Es el modo ideal para prácticas porque las máquinas se "ven" entre sí pero no necesitan internet.

**Paso 2: Configurar la IP estática en smb1 mediante Netplan**
```bash
sudo nano /etc/netplan/01-network-manager-all.yaml
```
```yaml
network:
  version: 2
  renderer: NetworkManager
  ethernets:
    enp0s3:
      addresses:
        - 192.168.12.100/24
```
> **Anotación:** En Ubuntu Desktop se usa `renderer: NetworkManager`. El número de puesto (12 en el ejemplo) se sustituye por el del puesto real del laboratorio.

```bash
sudo netplan apply
```

**Paso 3: Configurar la IP estática en smb2**
```bash
sudo nano /etc/netplan/01-network-manager-all.yaml
```
```yaml
network:
  version: 2
  renderer: NetworkManager
  ethernets:
    enp0s3:
      addresses:
        - 192.168.12.200/24
```
```bash
sudo netplan apply
```

**Paso 4: Verificar las IPs asignadas en ambas máquinas**
```bash
ip address show enp0s3
```

---

<a id="bloque-6"></a>
## Bloque 6: Verificar la conectividad de red entre smb1 y smb2

> **ENUNCIADO**
> Comprueba la conexión entre las dos máquinas.

### Solución

**Desde smb1 hacia smb2**
```bash
ping 192.168.12.200
```

**Desde smb2 hacia smb1**
```bash
ping 192.168.12.100
```
> **Anotación:** Si el ping funciona en ambas direcciones, hay conectividad de red correcta. Si no funciona:
> 1. Verificar que ambas máquinas están en la misma Red Interna de VirtualBox con el mismo nombre de red.
> 2. Verificar que las IPs están bien configuradas con `ip address`.
> 3. Comprobar que el firewall no bloquea ICMP: `sudo ufw status`.

---

<a id="bloque-7"></a>
## Bloque 7: Explorar el recurso compartido desde el cliente smb2 en el entorno gráfico

> **ENUNCIADO**
> Desde el entorno gráfico de la máquina smb2, comprueba las condiciones de acceso al recurso compartido de smb1.

### Solución — EN smb2

**Paso 1: Abrir el gestor de archivos en smb2 y acceder a la red**
> En el gestor de archivos (Nautilus), en el panel izquierdo hacer clic en **"Otras ubicaciones"** o usar la barra de dirección y escribir:
> `smb://192.168.12.100`

> **Anotación:** La sintaxis `smb://IP` le dice al gestor de archivos que acceda a recursos Samba en esa IP. En el entorno de red de Windows, esto equivale a escribir `\\IP` en el explorador.

**Paso 2: Ver los recursos disponibles**
> Debe aparecer la carpeta "Compartida" (y posiblemente otras como "print$" para impresoras).

**Paso 3: Acceder con acceso de invitado**
> Hacer doble clic en "Compartida". Si tiene acceso de invitado configurado (`guest ok = yes`), entra directamente sin pedir credenciales.

> **Anotación — Qué condiciones se pueden verificar:**
> - Si pide usuario/contraseña → el recurso NO tiene acceso de invitado.
> - Si entra directamente → tiene `guest ok = yes`.
> - Si el recurso no aparece en la lista → `browseable = no` en smb.conf.

---

<a id="bloque-8"></a>
## Bloque 8: Crear carpetas "deanonimo" y "deusuario" en la carpeta compartida desde smb2

> **ENUNCIADO**
> Desde el entorno gráfico de smb2, crea las siguientes carpetas dentro de la carpeta "Compartida" de smb1:
> - `"deanonimo"` usando el acceso de usuario "Anónimo".
> - `"deusuario"` usando el usuario del cliente smb2 (ej: bea).

### Solución — EN smb2

**Crear "deanonimo" como invitado anónimo**
> En el gestor de archivos de smb2, con la carpeta "Compartida" de smb1 abierta (acceso como invitado, sin credenciales):
> Clic derecho en el área vacía → "Nueva carpeta" → Nombre: `deanonimo`.

> **Anotación:** Al crear esta carpeta como invitado anónimo, el sistema operativo del servidor la creará con un propietario especial. El entorno gráfico de Ubuntu Desktop autentica la conexión anónima usando la cuenta `nobody` o el usuario de la sesión local de smb2 mapeado como anónimo.

**Crear "deusuario" usando el usuario de smb2**
> En el gestor de archivos, desconectar o abrir una nueva conexión autenticada:
> Usar la barra de dirección: `smb://bea@192.168.12.100/Compartida`
> Introduce las credenciales del usuario `bea` (del sistema de smb2 o Samba). Crear la carpeta `deusuario` de la misma forma.

> **Anotación:** En este punto, Samba en smb1 puede mapear el usuario `bea` del cliente smb2 a un usuario local de smb1 o tratarlo según la configuración de `security = user` / `map to guest`.

---

<a id="bloque-9"></a>
## Bloque 9: Comprobar propietarios y permisos del contenido compartido desde la terminal

> **ENUNCIADO**
> Desde la terminal de **ambas** máquinas, comprueba los permisos y dueños del contenido de la carpeta "Compartida".

### Solución

**EN smb1 (servidor) — ver propietarios reales en el disco**
```bash
ls -la ~/Compartida/
```
> **Anotación:** En el servidor se ven los propietarios reales a nivel del sistema de archivos. Se puede observar:
> - La carpeta `deanonimo` probablemente pertenece al usuario `nobody` (si se creó como invitado anónimo).
> - La carpeta `deusuario` pertenece al usuario autenticado de smb2 (si el usuario existe en smb1 con el mismo UID) o a `nobody` (si Samba no pudo mapear el usuario).

**EN smb2 (cliente) — ver los archivos desde la conexión de red**
```bash
smbclient //192.168.12.100/Compartida
smb: \> ls
```
> **Anotación:** Desde `smbclient` se ven los archivos tal como los presenta Samba. Los permisos mostrados aquí son los que Samba aplica según su configuración, que pueden diferir de los permisos reales en el disco del servidor.

**Interpretar la diferencia:**
> La diferencia entre lo que muestra `ls -la` en el servidor y lo que muestra `smbclient` en el cliente ilustra perfectamente el concepto de **doble capa de permisos** de Samba:
> - Permisos Linux (lo que ve el servidor en el disco) → siempre la verdad absoluta.
> - Permisos Samba (lo que permite smb.conf) → filtro adicional por encima.
> El más restrictivo de los dos es el que prevalece.

---

<a id="bloque-10"></a>
## Bloque 10: Modificar el recurso para denegar el acceso de invitados y explicar los efectos

> **ENUNCIADO**
> Modifica las propiedades del recurso compartido en smb1 para no permitir el acceso de invitados. Explica los efectos del cambio.

### Solución — EN smb1

**Opción A: Desde el entorno gráfico**
> Clic derecho en "Compartida" → Propiedades → "Recurso compartido de red local" → desmarcar "Acceso invitado" → "Modificar compartición".

**Opción B: Desde la terminal, editando smb.conf**
```bash
sudo nano /etc/samba/smb.conf
```
Buscar la sección `[Compartida]` y cambiar:
```ini
[Compartida]
   path = /home/usuario/Compartida
   read only = no
   guest ok = no    # Cambiar de yes a no
```
```bash
sudo systemctl reload smbd
```
> **Anotación — `reload` vs `restart`:** `reload` recarga la configuración sin cortar las conexiones activas. `restart` corta todas las conexiones y reinicia el servicio. Para cambios de configuración, siempre preferir `reload`.

**Efectos del cambio:**
> Desde smb2, al intentar acceder a `smb://192.168.12.100`:
> - El gestor de archivos ahora pedirá usuario y contraseña.
> - Sin credenciales válidas de Samba, el acceso será denegado.
> - El usuario `bea` (u otro usuario) solo podrá acceder si tiene cuenta en Samba de smb1 configurada con `smbpasswd -a`.
>
> **Anotación:** Este cambio muestra claramente la diferencia entre `guest ok = yes` (cualquiera entra) y `guest ok = no` (necesitas credenciales). Es el parámetro que controla si el recurso es público o privado.

---

<a id="bloque-11"></a>
## Bloque 11: Determinar si smb2 necesita instalar Samba para acceder a recursos compartidos

> **ENUNCIADO**
> ¿Hemos tenido que instalar algún servicio en la máquina smb2 para tener acceso a los recursos compartidos en smb1?

### Solución

**Respuesta:** NO. El cliente smb2 **no necesita** instalar el paquete `samba` para acceder a recursos compartidos de smb1.

**Explicación:**

El paquete `samba` instala el **servidor** SMB (los demonios `smbd` y `nmbd`). Solo lo necesita quien va a **ofrecer** recursos compartidos.

Para **acceder** a recursos de otro servidor Samba, Ubuntu Desktop ya incluye soporte nativo en el gestor de archivos Nautilus a través de `gvfs-backends`, que incluye soporte SMB.

Para acceso desde terminal, solo se necesita `smbclient`:
```bash
sudo apt install smbclient
```
> **Anotación:** `smbclient` es el cliente de línea de comandos para Samba. Permite listar recursos, conectarse a ellos de forma interactiva (como un cliente FTP), descargar y subir archivos. No instala el servidor.

Para montar recursos Samba en el sistema de archivos local (como si fuera un disco local), se usa `cifs-utils`:
```bash
sudo apt install cifs-utils
# Luego:
sudo mount -t cifs //192.168.12.100/Compartida /mnt/samba -o guest
```
> **Resumen para el examen:**
> - `samba` → para hacer de **servidor**.
> - `smbclient` → para **acceder por terminal** a recursos Samba.
> - `cifs-utils` → para **montar** recursos Samba en el sistema de archivos local.

---

<a id="bloque-12"></a>
## Bloque 12: Configurar smb1 para permitir el acceso de usuarios identificados con contraseña

> **ENUNCIADO**
> Configura smb1 para permitir el acceso al recurso compartido por parte de usuarios identificados.

### Solución — EN smb1

**Paso 1: Verificar que el usuario existe en Linux**
```bash
id ana
```
> **Anotación:** En Samba la regla de oro es: **primero el usuario debe existir en Linux, luego en Samba**. Si no existe en Linux, no se puede añadir a Samba.

**Paso 2: Si el usuario no existe, crearlo en Linux**
```bash
sudo useradd -M -s /usr/sbin/nologin ana
```
> **Anotación:** `-M` evita crear el directorio home (no necesario para usuarios solo-Samba). `-s /usr/sbin/nologin` asigna un shell que rechaza sesiones interactivas: el usuario puede autenticarse en Samba pero no iniciar sesión SSH o en la terminal.

**Paso 3: Añadir el usuario a Samba y asignar contraseña Samba**
```bash
sudo smbpasswd -a ana
```
```
[sudo] contraseña para ana:
New SMB password:
Retype new SMB password:
Added user ana.
```
> **Anotación:** `smbpasswd -a` (add) añade el usuario a la base de datos de contraseñas de Samba. La contraseña Samba es **independiente** de la contraseña Linux del mismo usuario. Pueden ser diferentes. Samba guarda sus contraseñas en su propia base de datos (`/var/lib/samba/private/passdb.tdb` o en `tdbsam`).

**Paso 4: Verificar que el usuario se ha añadido a Samba**
```bash
sudo pdbedit -L
```
> **Anotación:** `pdbedit -L` lista todos los usuarios registrados en la base de datos de Samba. Debe aparecer `ana`.

---

<a id="bloque-13"></a>
## Bloque 13: Instalar el cliente Samba en terminal (smbclient) en smb2

> **ENUNCIADO**
> Instala `smbclient` en la máquina smb2 para poder acceder a los recursos compartidos de smb1 desde la terminal.

### Solución — EN smb2

```bash
sudo apt install smbclient
```
> **Anotación:** `smbclient` es el cliente de Samba para la línea de comandos. Funciona de forma similar a un cliente FTP: se conecta al servidor y permite hacer operaciones de forma interactiva. Comandos disponibles dentro de una sesión `smbclient`:
> - `ls` — Listar el contenido del recurso.
> - `get archivo.txt` — Descargar un archivo al directorio local.
> - `put archivo.txt` — Subir un archivo al recurso.
> - `mkdir nombre` — Crear un directorio.
> - `help` — Ver lista de comandos disponibles.
> - `exit` — Cerrar la sesión.

---

<a id="bloque-14"></a>
## Bloque 14: Listar los recursos compartidos del servidor desde la terminal

> **ENUNCIADO**
> Desde la terminal de smb2, lista los recursos compartidos disponibles en el servidor smb1.

### Solución — EN smb2

**Listar recursos con acceso anónimo**
```bash
smbclient -L //192.168.12.100
```
> Al pedir contraseña, pulsar **Enter** sin escribir nada (acceso anónimo).

**Listar recursos como usuario autenticado**
```bash
smbclient -L //192.168.12.100 -U ana
```
> **Anotación:** La salida muestra una tabla con las columnas `Sharename`, `Type` y `Comment`:
> - Los recursos de tipo `Disk` son carpetas compartidas.
> - `IPC$` es un recurso administrativo interno de Samba (no es una carpeta real).
> - `print$` es el recurso de impresoras.
>
> Solo aparecen los recursos con `browseable = yes` (o sin el parámetro, ya que el defecto es `yes`).

---

<a id="bloque-15"></a>
## Bloque 15: Conectarse al recurso compartido como usuario Samba desde la terminal

> **ENUNCIADO**
> Desde la terminal de smb2, establece una conexión interactiva al recurso "Compartida" de smb1 usando el usuario Samba `ana`.

### Solución — EN smb2

**Conectarse al recurso como usuario autenticado**
```bash
smbclient //192.168.12.100/Compartida -U ana
```
> Introducir la contraseña Samba de `ana` cuando se solicite.

**Confirmar la conexión y explorar el contenido**
```
Enter WORKGROUP\ana's password:
Try "help" to get a list of possible commands.
smb: \>
```
```bash
smb: \> ls
```
> **Anotación:** El prompt `smb: \>` indica que estamos dentro de una sesión Samba interactiva. El directorio actual es la raíz del recurso compartido en el servidor. Los comandos aquí son los de `smbclient`, no los del shell de Linux.

**Descargar un archivo del servidor al cliente**
```bash
smb: \> get archivo.txt
```
> **Anotación:** El archivo se descarga al directorio local del cliente desde el que se lanzó `smbclient`.

**Subir un archivo del cliente al servidor**
```bash
smb: \> put mi_documento.txt
```

**Crear una carpeta en el recurso**
```bash
smb: \> mkdir nueva_carpeta
```

**Salir de la sesión Samba**
```bash
smb: \> exit
```
