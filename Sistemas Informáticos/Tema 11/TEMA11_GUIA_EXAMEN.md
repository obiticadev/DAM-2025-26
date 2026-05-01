# Tema 11 — Servicios de Red: Guía de Examen
### Netplan · NFS · Samba · SSH

> Organizado por herramienta: qué es, cómo se instala, cómo se configura, comandos clave y trampas de examen.

---

## Índice

| Sección | Contenido |
|---------|-----------|
| [1. Netplan](#netplan) | Configuración de IP estática/dinámica en Ubuntu |
| [2. NFS](#nfs) | Compartir carpetas entre Linux (servidor y cliente) |
| [3. Samba](#samba) | Compartir carpetas en redes mixtas Linux/Windows |
| [4. SSH](#ssh) | Acceso remoto, ejecución de scripts, claves |
| [5. Comparativa final](#comparativa) | NFS vs Samba vs SSH — qué cae en test |

---

<a id="netplan"></a>
## 1. Netplan — Configuración de Red

**¿Qué es?** Herramienta de Ubuntu (desde 17.04) para configurar interfaces de red mediante archivos YAML. Reemplaza a `/etc/network/interfaces`.

**Archivo de configuración:** `/etc/netplan/`
- Server: `01-netcfg.yaml`
- Desktop: `01-network-manager-all.yaml`
- Cloud: `50-cloud-init.yaml`

**Backends:** `NetworkManager` (desktop) o `networkd` (server/headless).

### IP Dinámica (DHCP)
```yaml
network:
  version: 2
  renderer: networkd
  ethernets:
    enp3s0:
      dhcp4: true
```

### IP Estática
```yaml
network:
  version: 2
  renderer: networkd
  ethernets:
    enp3s0:
      addresses:
        - 10.10.10.2/24
      gateway4: 10.10.10.1
      nameservers:
        addresses: [10.10.10.1, 1.1.1.1]
```

### Comandos Netplan

| Comando | Qué hace |
|---------|----------|
| `sudo netplan try` | Prueba la config (revierte si no confirmas en 120s) |
| `sudo netplan apply` | Aplica los cambios de forma permanente |

### Comandos `ip` — Diagnóstico de red

| Comando | Qué hace |
|---------|----------|
| `ip address` / `ip ad` | Ver todas las interfaces y sus IPs |
| `ip address show enp0s3` | Ver solo una interfaz concreta |
| `ip route` / `ip ro` | Ver la tabla de enrutamiento (puerta de enlace) |
| `ip route get 8.8.8.8` | Ver qué puerta de enlace usa para llegar a una IP |
| `ip link set enp0s3 up` | Activar una interfaz |
| `ip link set enp0s3 down` | Desactivar una interfaz |
| `ip -s link show enp0s3` | Estadísticas de tráfico de la interfaz |
| `ip neighbour` / `ip neigh` | Ver tabla ARP (equipos conocidos en la red local) |

---

<a id="nfs"></a>
## 2. NFS — Network File System

**¿Qué es?** Sistema nativo de Linux para compartir carpetas en red. Un equipo monta la carpeta remota como si fuera local. Desarrollado por Sun Microsystems en 1984.

**Protocolo subyacente:** RPC (Remote Procedure Call). Usa sockets (IP+puerto).
**Puertos:** NFS usa el puerto **2049** (TCP/UDP). El portmapper usa el **puerto 111**.
> NFSv3 y anteriores usan múltiples puertos dinámicos (difícil con firewall). **NFSv4 solo necesita el 2049 y el 111**, lo que facilita el filtrado.

**Seguridad:** basada en IP del host (no en usuario/contraseña como Samba).
**Estado:** NFSv3 es *stateless* (sin estado). NFSv4 es *stateful* (con estado).

---

### 2.1 Instalación

**En el servidor:**
```bash
sudo apt install nfs-kernel-server
sudo systemctl status nfs-kernel-server
```

**En el cliente:**
```bash
sudo apt install nfs-common
```

**Comprobar servicios RPC registrados:**
```bash
rpcinfo -p
```

**Firewall (ufw):**
```bash
sudo ufw allow from 192.168.100.0/24
sudo ufw allow from 192.168.100.0/24 to any port nfs
```

---

### 2.2 Configuración del servidor — `/etc/exports`

**Siempre hacer copia de seguridad primero:**
```bash
sudo cp /etc/exports /etc/exports.bak
```

**Estructura de una línea en `/etc/exports`:**
```
/ruta/directorio   equipo_o_red(opcion1,opcion2,...)
```

**Ejemplos:**
```text
/nfs/lectura    192.168.100.0/24(ro,sync,no_subtree_check)
/nfs/escritura  192.168.100.0/24(rw,sync,all_squash,no_subtree_check)
/alumnos        192.168.100.5(rw,sync,no_subtree_check)
/alumnos        *(rw,async,no_subtree_check)
```

> **TRAMPA CRÍTICA de examen — los espacios en blanco:**
> - `/alumnos 192.168.100.5(rw)` → solo esa IP tiene rw.
> - `/alumnos 192.168.100.5 (rw)` → esa IP tiene ro (defecto) y **TODOS** los demás tienen rw.
> El espacio antes del paréntesis cambia completamente el significado.

### Opciones de exportación

| Opción | Significado |
|--------|-------------|
| `ro` | Solo lectura (valor por defecto) |
| `rw` | Lectura y escritura |
| `sync` | Confirma escritura solo cuando los datos están en disco (seguro, recomendado) |
| `async` | Confirma antes de escribir en disco (más rápido pero puede corromper archivos si el servidor falla) |
| `no_subtree_check` | No comprueba el camino hasta el directorio exportado. Evita problemas de permisos. Recomendado. |
| `root_squash` | El usuario root del cliente actúa como anónimo (`nobody`) en el servidor. **Por defecto.** |
| `no_root_squash` | El root del cliente es root en el servidor también. Peligroso. |
| `all_squash` | **Todos** los usuarios (incluyendo root) actúan como anónimos. Útil para carpetas públicas. |
| `anonuid=1002` | UID del usuario anónimo cuando se usa squash |
| `anongid=1002` | GID del grupo anónimo cuando se usa squash |

**Ejemplo con squash personalizado:**
```text
/nfs/squash  192.168.100.0/24(rw,sync,all_squash,anonuid=1002,anongid=1002)
```

### Comandos del servidor

| Comando | Qué hace |
|---------|----------|
| `sudo exportfs -ra` | Fuerza releer `/etc/exports` sin reiniciar el servicio |
| `sudo exportfs` o `exportfs -s` | Lista las exportaciones activas en el servidor |
| `showmount -e IP_servidor` | Consulta desde el cliente qué exportaciones ofrece el servidor |

---

### 2.3 Proceso completo — Servidor NFS (paso a paso)

```bash
# 1. Instalar
sudo apt install nfs-kernel-server

# 2. Crear directorios
sudo mkdir -p /nfs/lectura /nfs/escritura /nfs/squash

# 3. Permisos de Linux (solo para los que admiten escritura)
sudo chmod 777 /nfs/escritura /nfs/squash

# 4. Editar /etc/exports
sudo nano /etc/exports
# Añadir:
# /nfs/lectura    192.168.88.0/24(ro,sync,no_subtree_check)
# /nfs/escritura  192.168.88.0/24(rw,sync,all_squash,no_subtree_check)
# /nfs/squash     192.168.88.0/24(rw,sync,all_squash,no_subtree_check)

# 5. Aplicar cambios
sudo exportfs -ra

# 6. Verificar
exportfs -s
```

---

### 2.4 Proceso completo — Cliente NFS (paso a paso)

**Montaje temporal:**
```bash
# 1. Instalar cliente
sudo apt install nfs-common

# 2. Crear puntos de montaje
sudo mkdir -p /mnt/nfs/lectura /mnt/nfs/escritura /mnt/nfs/squash

# 3. Montar
sudo mount -t nfs 192.168.88.100:/nfs/lectura  /mnt/nfs/lectura
sudo mount -t nfs 192.168.88.100:/nfs/escritura /mnt/nfs/escritura
sudo mount -t nfs 192.168.88.100:/nfs/squash   /mnt/nfs/squash

# 4. Verificar
df -h | grep nfs
showmount -e 192.168.88.100
```

**Montaje permanente (`/etc/fstab`):**
```bash
sudo nano /etc/fstab
```
Añadir al final:
```text
192.168.88.100:/nfs/squash  /mnt/nfs/squash  nfs  defaults  0  0
```
```bash
# Verificar sin reiniciar
sudo umount /mnt/nfs/squash
sudo mount -a
```

> **`/etc/fstab` — los dos ceros del final:**
> - Primer `0`: no hacer dump (volcado de seguridad).
> - Segundo `0`: no ejecutar fsck al arrancar (es un recurso de red, no un disco físico).

> **`mount -a`:** monta todo lo que hay en fstab. Úsalo siempre para verificar antes de reiniciar.

---

### 2.5 NFSv4 — Árbol de exportaciones

NFSv4 permite crear un árbol unificado de exportaciones desde un **punto raíz** con `fsid=0`. El cliente monta solo `/` y accede a todos los subdirectorios automáticamente.

**Servidor — `/etc/exports`:**
```text
/exports        192.168.88.0/24(rw,sync,fsid=0,crossmnt,no_subtree_check)
/exports/Datos  192.168.88.0/24(rw,sync,no_subtree_check)
/exports/Pub    192.168.88.0/24(ro,sync,no_subtree_check)
```

**Servidor — crear estructura:**
```bash
sudo mkdir -p /exports/Datos /exports/Pub
sudo chmod 777 /exports/Datos
sudo chmod 755 /exports/Pub
sudo exportfs -ra
```

**Cliente — montaje NFSv4:**
```bash
sudo mkdir -p /mnt/nfs4
sudo mount -t nfs4 192.168.88.100:/ /mnt/nfs4
# El cliente ve /mnt/nfs4/Datos y /mnt/nfs4/Pub automáticamente
```

> **Opciones clave de NFSv4:**
> - `fsid=0`: marca esa carpeta como la raíz virtual (kilómetro cero). Solo puede haber una.
> - `crossmnt`: permite saltar de la raíz a los subdirectorios de forma transparente.
> - El cliente monta `IP:/` (no `IP:/exports`), lo que oculta la estructura real del servidor.

---

<a id="samba"></a>
## 3. Samba — Compartición en Redes Mixtas (SMB/CIFS)

**¿Qué es?** Implementación libre del protocolo SMB/CIFS de Windows para Linux. Permite que Linux actúe como servidor de archivos para redes con clientes Windows, Linux y macOS.

**Servicios que ofrece:** compartir ficheros, compartir impresoras, autenticar usuarios, servidor de dominio.

**Dos demonios principales:**

| Demonio | Función | Puertos |
|---------|---------|---------|
| `smbd` | Compartición de archivos, autenticación | TCP 139, 445 |
| `nmbd` | Resolución de nombres NetBIOS (aparece en "Entorno de red") | UDP 137, 138 |

---

### 3.1 Instalación

```bash
sudo apt update
sudo apt install samba smbclient cifs-utils

# Comprobar estado
sudo systemctl status smbd
dpkg -l | grep samba
```

**Gestión del servicio:**
```bash
sudo systemctl start smbd nmbd      # Iniciar
sudo systemctl enable smbd nmbd     # Habilitar en arranque
sudo systemctl restart smbd nmbd    # Reiniciar (corta conexiones)
sudo systemctl reload smbd nmbd     # Recargar config sin cortar conexiones (mejor opción tras cambios)
sudo systemctl status smbd nmbd     # Ver estado
```

**Firewall:**
```bash
sudo ufw allow Samba    # UFW tiene perfil preconfigurado
```

---

### 3.2 Archivo de configuración — `/etc/samba/smb.conf`

**Siempre hacer copia de seguridad:**
```bash
sudo cp /etc/samba/smb.conf /etc/samba/smb.conf.bak
```

**Siempre verificar tras editar:**
```bash
testparm -s    # Comprueba errores de sintaxis y muestra la config resultante
```

**Estructura del fichero:**
- Cada sección `[nombre]` = un recurso compartido.
- Excepciones especiales: `[global]`, `[homes]`, `[printers]`.
- Los parámetros no son case-sensitive: `Read Only = yes` es igual que `read only = yes`.

#### Sección `[global]` — Parámetros principales

| Parámetro | Descripción |
|-----------|-------------|
| `workgroup = WORKGROUP` | Grupo de trabajo. Debe coincidir con los clientes Windows. |
| `netbios name = NOMBRE` | Nombre con el que aparece en la red (máx 15 caracteres) |
| `server string = %h server` | Descripción del servidor (`%h` = hostname) |
| `security = user` | Requiere usuario y contraseña para acceder (modo más común) |
| `map to guest = bad user` | Usuarios no válidos se tratan como invitados |
| `interfaces = lo eth0` | Interfaces de red en las que escucha Samba |
| `bind interfaces only = yes` | Solo escucha en las interfaces definidas |
| `hosts allow = 192.168.1.` | IPs que pueden conectarse |
| `hosts deny = ALL` | Bloquea el resto |
| `server min protocol = SMB2` | Rechaza SMBv1 (vulnerable a WannaCry) |
| `log file = /var/log/samba/log.%m` | Log separado por máquina cliente |
| `passdb backend = tdbsam` | Base de datos local de contraseñas Samba |

#### Secciones especiales

| Sección | Qué hace |
|---------|----------|
| `[homes]` | Crea automáticamente un recurso compartido para cada usuario que se conecta, apuntando a su `/home/usuario` |
| `[printers]` | Comparte automáticamente las impresoras instaladas en el sistema |

#### Parámetros de un recurso compartido (share)

| Parámetro | Descripción |
|-----------|-------------|
| `path = /ruta` | Ruta real en el disco del servidor |
| `browseable = yes/no` | `yes`: aparece en la lista de recursos. `no`: oculto (hay que escribir la ruta) |
| `read only = yes/no` | `yes`: solo lectura. `no`: lectura-escritura |
| `writable = yes` | Sinónimo de `read only = no` |
| `guest ok = yes` | Permite acceso sin contraseña (= `public = yes`) |
| `valid users = user1, @grupo` | Solo estos usuarios pueden acceder (`@` = grupo Linux) |
| `invalid users = user2` | Estos usuarios NO pueden acceder (prioridad sobre valid users) |
| `write list = user1, @jefes` | Pueden escribir aunque `read only = yes` |
| `read list = user2` | Solo lectura aunque el recurso sea writable |
| `force user = nobody` | Todos los archivos creados pertenecen a este usuario de Linux |
| `force group = grupo` | Todos los archivos creados pertenecen a este grupo de Linux |
| `create mask = 0664` | Permisos máximos para archivos nuevos |
| `directory mask = 0775` | Permisos máximos para carpetas nuevas |
| `force create mode = 0660` | Permisos mínimos forzados para archivos nuevos |
| `force directory mode = 0770` | Permisos mínimos forzados para carpetas nuevas |
| `admin users = sysadmin` | Este usuario tiene privilegios de root sobre el recurso |

---

### 3.3 Proceso completo — Servidor Samba (paso a paso)

```bash
# 1. Instalar
sudo apt install samba

# 2. Backup de configuración
sudo cp /etc/samba/smb.conf /etc/samba/smb.conf.bak

# 3. Crear directorios
sudo mkdir -p /samba/publico /samba/privado

# 4. Crear usuario Linux (sin shell, solo para Samba)
sudo useradd -M -s /usr/sbin/nologin usuario1

# 5. Añadir usuario a Samba (contraseña independiente de Linux)
sudo smbpasswd -a usuario1

# 6. Asignar permisos Linux a la carpeta
sudo chown root:usuario1 /samba/privado
sudo chmod 770 /samba/privado

# 7. Editar smb.conf
sudo nano /etc/samba/smb.conf

# 8. Verificar configuración
testparm -s

# 9. Reiniciar servicios
sudo systemctl restart smbd nmbd
```

**Ejemplo de `smb.conf` con recursos público y privado:**
```ini
[global]
   workgroup = WORKGROUP
   security = user
   map to guest = bad user

[pub]
   path = /samba/publico
   browseable = yes
   guest ok = yes
   read only = yes

[privado]
   path = /samba/privado
   browseable = yes
   guest ok = no
   valid users = @gprivado
   writable = yes

[padron]
   path = /samba/padron
   browseable = yes
   invalid users = pica
   read only = yes

[homes]
   browseable = no
   read only = no
   create mask = 0700
   directory mask = 0700
```

---

### 3.4 Gestión de usuarios Samba

**Regla de oro: el usuario debe existir primero en Linux, luego en Samba.**

```bash
# Crear usuario Linux sin acceso a terminal (solo para Samba)
sudo useradd -M -s /usr/sbin/nologin juan

# Añadir a Samba y poner contraseña
sudo smbpasswd -a juan

# Deshabilitar temporalmente
sudo smbpasswd -d juan

# Volver a habilitar
sudo smbpasswd -e juan

# Eliminar de Samba (no borra el usuario Linux)
sudo smbpasswd -x juan

# Listar usuarios Samba
sudo pdbedit -L

# Ver conexiones activas
smbstatus
```

---

### 3.5 Cliente Samba — desde terminal

```bash
# Instalar cliente
sudo apt install smbclient cifs-utils

# Listar recursos compartidos de un servidor
smbclient -L //192.168.1.100
smbclient -L //192.168.1.100 -U usuario1

# Conectarse a un recurso (abre una sesión interactiva similar a FTP)
smbclient //192.168.1.100/privado -U usuario1

# Dentro del cliente SMB, comandos útiles:
# ls       → listar
# get fichero.txt  → descargar
# put fichero.txt  → subir
# exit     → salir
```

**Montar recurso Samba permanentemente en Linux (como unidad de red):**
```bash
# Montaje temporal
sudo mount -t cifs //192.168.1.100/privado /mnt/samba -o username=usuario1,password=pass

# Montaje permanente en /etc/fstab
//192.168.1.100/privado  /mnt/samba  cifs  username=usuario1,password=pass  0  0
```

> **¿Necesita el cliente Samba instalar algo para acceder a recursos compartidos?**
> NO necesita instalar `samba`. Solo necesita `smbclient` para acceso por terminal, o `cifs-utils` para montar desde el explorador de archivos. El cliente solo lee; el servidor es el que tiene `samba` instalado.

---

### 3.6 Permisos: Linux vs Samba

Hay dos capas de permisos que actúan simultáneamente:

1. **Permisos Linux** (`chmod`, `chown`): controlan qué puede hacer el sistema operativo con el archivo.
2. **Permisos Samba** (`smb.conf`): controlan qué permite el servicio Samba.

**Prevalece siempre el más restrictivo de los dos.**

Ejemplo: si Samba dice `writable = yes` pero Linux tiene `chmod 444` en la carpeta, el cliente NO podrá escribir.

**SetGID en carpetas colaborativas** (`chmod 2770`):
```bash
sudo chmod 2770 /srv/samba/proyecto
```
El `2` inicial (SetGID) hace que todos los archivos creados dentro hereden el grupo de la carpeta, no el grupo del usuario que los crea. Muy útil para trabajo en equipo.

---

<a id="ssh"></a>
## 4. SSH — Secure Shell

**¿Qué es?** Protocolo que permite conectarse de forma **cifrada** a otro equipo. Reemplaza a Telnet (que enviaba todo en texto plano).

**Usos principales:** administrar servidores remotamente, transferir archivos, ejecutar comandos/scripts en remoto.

**OpenSSH incluye:**
| Programa | Función |
|---------|---------|
| `ssh` | Cliente: conectarse a otro equipo |
| `sshd` | Servidor: acepta conexiones entrantes |
| `scp` | Copiar archivos de forma segura |
| `ssh-keygen` | Generar par de claves (pública/privada) |
| `ssh-copy-id` | Copiar la clave pública al servidor |

---

### 4.1 Instalación

```bash
# Cliente (normalmente ya instalado)
sudo apt install openssh-client

# Servidor
sudo apt install openssh-server
sudo systemctl status ssh
```

---

### 4.2 Conexión básica

```bash
# Conexión interactiva (abre una terminal remota)
ssh usuario@192.168.100.100
```

---

### 4.3 Ejecutar comandos remotos sin abrir sesión

```bash
# Un solo comando
ssh usuario@ip "ls -la"

# Varios comandos seguidos (siempre se ejecutan)
ssh usuario@ip "comando1; comando2"

# Varios comandos encadenados (el segundo solo si el primero tiene éxito)
ssh usuario@ip "comando1 && comando2"

# Comando interactivo (requiere terminal asignada)
ssh -t usuario@ip "sudo apt update"
```

---

### 4.4 Ejecutar scripts remotamente

```bash
# Ejecutar un script que ya existe EN el servidor
ssh usuario@ip "bash /ruta/script.sh"

# Ejecutar un script que está EN TU MÁQUINA (cliente) en el servidor
ssh usuario@ip 'bash -s' < script.sh
```

---

### 4.5 Copiar archivos — `scp`

```bash
# De cliente → servidor
scp fichero.txt usuario@192.168.1.1:/home/usuario/

# De servidor → cliente
scp usuario@192.168.1.1:/home/usuario/fichero.txt ~/Descargas/

# Copiar carpeta completa (flag -r)
scp -r mi_proyecto usuario@192.168.1.1:/var/www/
```

---

### 4.6 Autenticación con claves (sin contraseña)

En lugar de usar contraseña, SSH puede usar un par de claves criptográficas.

**Archivos en `~/.ssh/`:**

| Archivo | Dónde está | Qué contiene |
|---------|-----------|-------------|
| `id_rsa` o `id_ed25519` | **Cliente** | Clave **privada** — nunca se comparte |
| `id_rsa.pub` o `id_ed25519.pub` | **Cliente** | Clave **pública** — se copia al servidor |
| `authorized_keys` | **Servidor** (`~/.ssh/`) | Lista de claves públicas autorizadas |
| `known_hosts` | **Cliente** | Claves públicas de servidores conocidos (para detectar suplantación) |

**`known_hosts` — para qué sirve:** cuando te conectas a un servidor por primera vez, su clave pública se guarda aquí. En conexiones futuras, SSH compara. Si no coincide, avisa de un posible ataque **man-in-the-middle**.

**Proceso de autenticación por claves (cómo funciona internamente):**

1. El servidor genera un mensaje aleatorio ("desafío") y lo cifra con la clave pública del cliente.
2. Envía el mensaje cifrado al cliente.
3. El cliente descifra el mensaje usando su clave **privada**.
4. Envía la respuesta al servidor.
5. El servidor verifica que la respuesta es correcta → acceso concedido.

> La clave privada **nunca sale del cliente**. Lo que demuestra que tienes la clave es que puedes descifrar lo que solo tu clave pública pudo cifrar.

**Generar par de claves:**
```bash
# En el cliente — genera id_ed25519 e id_ed25519.pub en ~/.ssh/
ssh-keygen -t ed25519
```

**Copiar clave pública al servidor:**
```bash
ssh-copy-id usuario@192.168.100.100
# Esto añade la clave pública a ~/.ssh/authorized_keys del servidor
```

**Comprobar en el servidor:**
```bash
cat ~/.ssh/authorized_keys   # Debe aparecer la clave pública del cliente
```

Tras esto, `ssh usuario@192.168.100.100` conecta sin pedir contraseña.

---

<a id="comparativa"></a>
## 5. Comparativa Final — Lo que cae en test

### NFS vs Samba vs SSH

| Característica | NFS (v3) | NFSv4 | Samba (SMB) |
|----------------|----------|-------|-------------|
| **Sistema objetivo** | Linux↔Linux | Linux↔Linux | Linux↔Windows↔macOS |
| **Protocolo** | RPC | RPC | SMB/CIFS |
| **Puerto principal** | 2049 + múltiples dinámicos | Solo 2049 + 111 | 445 (+ 139 NetBIOS) |
| **Seguridad** | Basada en IP | IP + Kerberos | Usuario + Contraseña |
| **Estado** | Sin estado (stateless) | Con estado (stateful) | Con estado (stateful) |
| **Autenticación** | Por host/red | Por host/red + avanzada | Por usuario |
| **Firewall** | Difícil (puertos dinámicos) | Fácil (solo 2049) | Fácil (445) |
| **Uso ideal** | Redes Linux seguras | Redes Linux con firewall | Redes mixtas con Windows |

### Tabla de comandos clave por herramienta

| Acción | NFS | Samba |
|--------|-----|-------|
| Aplicar cambios sin reiniciar | `exportfs -ra` | `systemctl reload smbd nmbd` |
| Ver recursos activos en servidor | `exportfs -s` | `smbstatus` |
| Ver recursos desde el cliente | `showmount -e IP` | `smbclient -L //IP` |
| Verificar configuración | — | `testparm -s` |
| Archivo de configuración | `/etc/exports` | `/etc/samba/smb.conf` |
| Montaje permanente | `/etc/fstab` (tipo `nfs`) | `/etc/fstab` (tipo `cifs`) |

### Respuestas directas para test

**¿Qué hace `all_squash`?** Trata a todos los clientes (incluyendo root) como usuario anónimo. Impide que el cliente tome privilegios en el servidor.

**¿Qué hace `root_squash`?** Solo degrada al root del cliente a usuario anónimo. El resto de usuarios mantienen su UID/GID. Es el comportamiento **por defecto** en NFS.

**¿Qué hace `no_root_squash`?** El root del cliente es root también en el servidor. Peligroso.

**¿Qué diferencia hay entre `ro` y `rw` en NFS?** `ro` = solo lectura (valor por defecto). `rw` = lectura y escritura.

**¿Qué diferencia hay entre `sync` y `async`?** `sync` confirma la escritura solo cuando los datos están físicamente en disco (seguro). `async` confirma antes de escribir en disco (más rápido pero puede corromper datos).

**¿Qué hace `exportfs -ra`?** Fuerza al demonio NFS a releer `/etc/exports` sin reiniciar el servicio.

**¿Qué hace `mount -a`?** Monta todos los sistemas de archivos definidos en `/etc/fstab`. Se usa para verificar sin reiniciar.

**¿Qué hace `testparm`?** Verifica la sintaxis de `/etc/samba/smb.conf` y muestra la configuración resultante. Siempre ejecutar antes de reiniciar Samba.

**¿Qué diferencia hay entre `smbd` y `nmbd`?** `smbd` gestiona la compartición y la autenticación. `nmbd` gestiona la resolución de nombres NetBIOS (para aparecer en el "Entorno de red" de Windows).

**¿Qué hace `browseable = no`?** El recurso no aparece en la lista de recursos del servidor, pero es accesible si se escribe la ruta directamente.

**¿Qué hace `map to guest = bad user`?** Cuando alguien intenta conectarse con un usuario que no existe en Samba, en lugar de rechazarlo se le trata como invitado.

**¿Qué significa `fsid=0` en NFSv4?** Marca esa carpeta como el punto raíz de todas las exportaciones. El cliente monta `/` y accede a todo el árbol desde ahí.

**¿Qué hace `crossmnt` en NFSv4?** Permite que el cliente acceda a los subdirectorios del árbol de exportaciones sin tener que montarlos uno por uno.

**¿Qué hace `ssh -t`?** Asigna una pseudo-terminal al comando remoto. Necesario para comandos interactivos como `sudo` que requieren terminal.

**¿Qué contiene `authorized_keys`?** La lista de claves públicas de los clientes que pueden conectarse sin contraseña al servidor.

**¿Qué contiene `known_hosts`?** Las claves públicas de los servidores a los que el cliente se ha conectado antes. Protege contra ataques man-in-the-middle.

**¿Qué diferencia hay entre `scp` y `ssh`?** `ssh` abre una sesión de terminal remota. `scp` copia archivos entre máquinas usando SSH como canal cifrado.

**¿En NFS, qué prevalece si los permisos de Linux y de la exportación son distintos?** Prevalecen los más restrictivos de los dos.

**¿Necesita el cliente instalar `samba` para acceder a recursos Samba?** No. Solo necesita `smbclient` (terminal) o `cifs-utils` (para montar). El paquete `samba` solo lo necesita quien hace de servidor.
