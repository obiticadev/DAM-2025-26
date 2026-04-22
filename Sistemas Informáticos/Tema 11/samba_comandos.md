El archivo `/etc/samba/smb.conf` se divide principalmente en dos partes: la sección **`[global]`** (que define el comportamiento de todo el servidor) y las secciones de **recursos compartidos** (que definen carpetas o impresoras específicas).

Aquí tienes las configuraciones más comunes explicadas:

### 1. Sección Global `[global]`
Controla la identidad del servidor y la seguridad general.

*   **`workgroup = WORKGROUP`**: El nombre del grupo de trabajo. Debe coincidir con el de los equipos Windows (por defecto es `WORKGROUP`).
*   **`server string = %h server`**: Una descripción que aparecerá junto al nombre del servidor en la red. `%h` se sustituye por el nombre de tu equipo.
*   **`netbios name = NOMBRE`**: El nombre con el que se verá el servidor en la red (máximo 15 caracteres).
*   **`security = user`**: Indica que Samba requerirá un usuario y contraseña válidos para acceder. Es la opción más común y segura.
*   **`map to guest = bad user`**: Si alguien intenta entrar con un usuario que no existe, Samba lo tratará como un "invitado" (útil para carpetas públicas).
*   **`interfaces = 127.0.0.0/8 eth0`**: Define en qué tarjetas de red debe escuchar Samba. Muy útil por seguridad para que no sea visible desde internet.
*   **`bind interfaces only = yes`**: Obliga a Samba a usar solo las interfaces definidas arriba.

### 2. Configuración de Carpetas (Shares)
Cada sección nueva (ej. `[Fotos]`) define una carpeta compartida.

*   **`path = /ruta/a/la/carpeta`**: La ubicación real en tu disco duro Linux.
*   **`browseable = yes`**: Si es `yes`, la carpeta aparecerá en la lista de recursos al buscar el servidor. Si es `no`, estará "oculta" y habrá que escribir la ruta completa para entrar.
*   **`read only = no`**: Si es `no`, los usuarios pueden escribir y borrar (siempre que tengan permisos en Linux). También puedes usar `writable = yes`.
*   **`guest ok = yes`**: Permite entrar a la carpeta sin pedir contraseña (acceso público). Es sinónimo de `public = yes`.
*   **`valid users = usuario1, @grupo1`**: Restringe el acceso solo a ciertos usuarios o grupos (el `@` indica un grupo de Linux).
*   **`force user = nombre_usuario`**: Útil para que todos los archivos que se creen en la carpeta pertenezcan a un usuario específico de Linux, evitando problemas de permisos entre varios usuarios.

### 3. Permisos de Archivos y Carpetas (Máscaras)
Samba puede "forzar" ciertos permisos cuando se crea un archivo nuevo desde Windows:

*   **`create mask = 0664`**: Define los permisos para archivos nuevos (ej. lectura/escritura para dueño y grupo).
*   **`directory mask = 0775`**: Define los permisos para carpetas nuevas.

### 4. Secciones Especiales
*   **`[homes]`**: Es una sección mágica. Si está activa, cada usuario de Linux verá automáticamente su propia carpeta personal (`/home/usuario`) como un recurso compartido privado al conectarse.
*   **`[printers]`**: Configuración automática para compartir las impresoras instaladas en el sistema (vía CUPS).

### Ejemplo de una configuración equilibrada:
```ini
[global]
   workgroup = MIRED
   server string = Servidor de Archivos
   security = user
   map to guest = Bad User
   log file = /var/log/samba/log.%m
   max log size = 1000

[Privado]
   comment = Solo para personal
   path = /srv/samba/privado
   valid users = @empleados
   guest ok = no
   writable = yes
   browseable = yes

[Publico]
   comment = Carpeta para todos
   path = /srv/samba/publico
   guest ok = yes
   read only = no
   force user = nobody
```

**Consejo:** Cada vez que edites este archivo, ejecuta el comando `testparm`. Este revisará si cometiste algún error de escritura antes de que reinicies el servicio.