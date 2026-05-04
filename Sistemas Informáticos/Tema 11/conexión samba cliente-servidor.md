


# Guía Rápida: Gestión de Usuarios y Permisos en Samba

### 1. Crear usuarios y contraseñas
El cliente debe existir en el sistema Linux y en la base de datos de Samba.

```bash
# Crear usuario cliente en Linux (sin acceso a consola por seguridad)
sudo useradd -M -s /usr/sbin/nologin cliente1

# Añadir cliente a Samba y asignar contraseña
sudo smbpasswd -a cliente1

# Añadir tu usuario actual (propietario) a Samba y asignar contraseña
sudo smbpasswd -a $USER
```

### 2. Configurar la carpeta y permisos de Linux
Preparar el directorio base y restringir accesos a nivel de sistema de archivos.

```bash
# Crear el directorio a compartir
sudo mkdir -p /srv/samba/proyecto

# Hacerte propietario de la carpeta y asignar grupo del cliente
sudo chown $USER:cliente1 /srv/samba/proyecto

# Permisos: Propietario controla todo (7), Grupo lee/ejecuta (5), Resto nada (0)
sudo chmod 750 /srv/samba/proyecto
```

### 3. Configurar el recurso en `smb.conf`
Definir los permisos a nivel del servicio Samba.

```bash
# Editar el archivo de configuración
sudo nano /etc/samba/smb.conf
```

Añadir al final del archivo:
```ini
[Proyecto]
   path = /srv/samba/proyecto
   browseable = yes
   guest ok = no
   # Quién puede acceder:
   valid users = @cliente1, tu_usuario
   # Quién puede modificar (propietario):
   write list = tu_usuario
   # Quién puede solo leer (cliente):
   read list = @cliente1
```
*(Nota: Cambia `tu_usuario` por tu nombre de usuario real de Linux).*

### 4. Verificar y reiniciar el servicio
Validar que no haya errores e iniciar/reiniciar los demonios.

```bash
# Comprobar si hay errores de sintaxis en el archivo
testparm -s

# Reiniciar servicios en Debian/Ubuntu
sudo systemctl restart smbd nmbd

# Reiniciar servicios en RHEL/Fedora/Arch
sudo systemctl restart smb nmb
```

### 5. Probar el acceso
Comandos para verificar la conexión rápidamente desde el cliente.

```bash
# Listar recursos compartidos del servidor
smbclient -L //IP_SERVIDOR -U cliente1

# Conectarse a la carpeta
smbclient //IP_SERVIDOR/Proyecto -U cliente1
```