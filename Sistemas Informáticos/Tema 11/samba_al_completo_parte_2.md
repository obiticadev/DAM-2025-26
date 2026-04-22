


# Bootcamp Samba: De Novato a Pro - Parte 2: Gestión Exhaustiva de Usuarios, Contraseñas y Permisos

En esta segunda etapa, profundizaremos en el sistema de autenticación de Samba. Una regla de oro: **Samba mantiene su propia base de datos de contraseñas**, pero para que un usuario exista en Samba, **primero debe existir en el sistema Linux**.

---

## 1. El Backend de Contraseñas (passdb)

Por defecto, Samba moderna utiliza una base de datos local llamada `tdbsam` (Trivial Database SAM) para almacenar usuarios y hashes de contraseñas. Esto se define en el bloque `[global]` de tu `/etc/samba/smb.conf`:

```ini
[global]
   passdb backend = tdbsam
   # Obliga a que las contraseñas se transmitan encriptadas (por defecto hoy en día)
   encrypt passwords = yes
```

---

## 2. Gestión de Usuarios: Herramientas `smbpasswd` y `pdbedit`

Existen dos herramientas para gestionar usuarios en Samba. `smbpasswd` es la tradicional (más sencilla), y `pdbedit` es la herramienta profesional para administradores (permite gestionar políticas de contraseñas, ver SIDs, etc.).

### Preparación del sistema Linux
Siempre debes crear el usuario en Linux primero. Si es un usuario que solo accederá por red a Samba (no necesita iniciar sesión en la terminal del servidor), se recomienda crearlo sin shell:

```bash
sudo useradd -M -s /usr/sbin/nologin juan
```

### Método 1: Uso de `smbpasswd` (Básico)
```bash
# Añadir el usuario a Samba y establecer su contraseña
sudo smbpasswd -a juan

# Deshabilitar temporalmente a un usuario (no podrá acceder)
sudo smbpasswd -d juan

# Volver a habilitar al usuario
sudo smbpasswd -e juan

# Eliminar un usuario de la base de datos de Samba (no lo borra de Linux)
sudo smbpasswd -x juan

# Cambiar la contraseña de un usuario existente (sin el flag -a)
sudo smbpasswd juan
```

### Método 2: Uso de `pdbedit` (Modo Pro / Administrador)
`pdbedit` interactúa directamente con la base de datos SAM y ofrece opciones mucho más avanzadas.

```bash
# Añadir un usuario
sudo pdbedit -a -u juan

# Listar todos los usuarios de Samba (formato corto)
sudo pdbedit -L

# Listar usuarios con todo el detalle técnico (SID, nombre completo, bad password count)
sudo pdbedit -L -v

# Eliminar un usuario
sudo pdbedit -x -u juan

# Ver e imprimir las políticas de contraseñas globales de Samba
sudo pdbedit -P "bad lockout attempt"
```

---

## 3. Mapeo de Usuarios (User Mapping)

A menudo, los usuarios en Windows tienen nombres diferentes a los de Linux (por ejemplo, en Windows se llama "Administrator" o "Admin", pero en Linux es "root" o "sysadmin"). Samba permite mapear estos nombres.

**1. Activar el mapeo en `[global]`:**
```ini
[global]
   username map = /etc/samba/smbusers
```

**2. Crear y editar el archivo `/etc/samba/smbusers`:**
El formato es: `usuario_linux = usuario_windows1 usuario_windows2`

```bash
sudo nano /etc/samba/smbusers
```
*Contenido del archivo:*
```text
root = Administrator Admin Administrador
juan = j.perez juanito
```

---

## 4. Control de Acceso en Recursos Compartidos (Shares)

Cuando creas una sección para compartir una carpeta en `smb.conf`, puedes aplicar directivas granulares para controlar quién entra y qué puede hacer.

```ini
[Finanzas]
   path = /srv/samba/finanzas
   browseable = yes
   guest ok = no
   
   # --- Control de Acceso ---
   # Solo estos usuarios o grupos (con @) pueden ver e intentar entrar
   valid users = @contabilidad, director
   
   # Prohibir explícitamente la entrada a estos usuarios (tiene prioridad sobre valid users)
   invalid users = becario
   
   # --- Control de Lectura / Escritura ---
   # Por defecto todos los "valid users" tendrán el permiso base definido aquí:
   read only = yes
   
   # Lista de excepciones que sí podrán ESCRIBIR (aunque read only sea yes)
   write list = director, @jefes_contabilidad
   
   # Lista de usuarios que tendrán privilegios de ROOT sobre esta carpeta
   # (Peligroso, usar solo en casos muy específicos)
   admin users = sysadmin
```

---

## 5. Máscaras y Forzado de Permisos (El mayor dolor de cabeza resuelto)

Cuando un usuario de Windows crea un archivo en Samba, Windows no entiende de permisos Linux (chmod). Samba traduce esto creando el archivo con unos permisos por defecto, lo que a veces provoca que un usuario cree un archivo y otro usuario del mismo grupo no pueda modificarlo.

Para solucionar esto, usamos máscaras y forzado de propiedades.

**Ejemplo de configuración Pro para trabajo colaborativo:**
Supongamos un directorio donde todo el grupo `@desarrolladores` debe poder leer y modificar los archivos de los demás.

```bash
# En la terminal de Linux:
sudo mkdir /srv/samba/proyecto
sudo chown root:desarrolladores /srv/samba/proyecto
# SetGID (el 2 inicial): Fuerza a que todo lo que se cree dentro herede el grupo "desarrolladores"
sudo chmod 2770 /srv/samba/proyecto
```

**En `/etc/samba/smb.conf`:**
```ini
[Proyecto_Dev]
   path = /srv/samba/proyecto
   valid users = @desarrolladores
   read only = no
   
   # Forzar el grupo en Samba (complementa al SetGID de Linux)
   force group = desarrolladores
   
   # --- Máscaras de Archivos ---
   # Permisos MÁXIMOS que puede tener un archivo nuevo (0660 = rw-rw----)
   create mask = 0660
   # Permisos MÍNIMOS que tendrá el archivo nuevo sí o sí (fuerza rw para dueño y grupo)
   force create mode = 0660
   
   # --- Máscaras de Directorios ---
   # Permisos MÁXIMOS para carpetas nuevas (0770 = rwxrwx---)
   directory mask = 0770
   # Permisos MÍNIMOS forzados para carpetas nuevas
   force directory mode = 0770
```

*Con esta configuración te aseguras al 100% de que ningún usuario creará archivos "bloqueados" para el resto de sus compañeros de equipo.*

---