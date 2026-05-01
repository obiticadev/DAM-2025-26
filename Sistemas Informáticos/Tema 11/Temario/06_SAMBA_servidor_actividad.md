# Actividad: Servidor Samba — Configuración de Recursos Compartidos
### Fuente: Servidor Samba 2025.pdf

> Configuración completa de un servidor Samba editando directamente `smb.conf`.
> **Servidor Ubuntu 22.04** con cuatro recursos compartidos: `pub`, `privado`, `padron` y `homes`.

---

## Índice

| Bloque | Proceso |
|--------|---------|
| [Bloque 1](#bloque-1) | Instalación y verificación del servidor Samba |
| [Bloque 2](#bloque-2) | Copia de seguridad y estructura del fichero smb.conf |
| [Bloque 3](#bloque-3) | Crear el recurso `pub` — público, visible, de solo lectura con acceso de invitados |
| [Bloque 4](#bloque-4) | Crear grupos y usuarios Linux para el recurso privado |
| [Bloque 5](#bloque-5) | Crear el recurso `privado` — lectura/escritura, solo para el grupo gprivado |
| [Bloque 6](#bloque-6) | Crear el recurso `padron` — solo lectura, bloqueando al usuario pica |
| [Bloque 7](#bloque-7) | Habilitar el acceso al directorio home del usuario `usmbh` con `[homes]` |
| [Bloque 8](#bloque-8) | Verificar la configuración con testparm y reiniciar el servicio |
| [Bloque 9](#bloque-9) | Comprobar el acceso a todos los recursos desde el cliente |

---

<a id="bloque-1"></a>
## Bloque 1: Instalación y verificación del servidor Samba

> **ENUNCIADO**
> Comprueba si los paquetes de Samba están instalados. Si no lo están, instálalos. Verifica el estado del servicio.

### Solución — EN EL SERVIDOR

**Paso 1: Comprobar si Samba está instalado**
```bash
dpkg -l | grep samba
```
> **Anotación:** `dpkg -l` lista todos los paquetes instalados. Con `| grep samba` filtramos los relacionados con Samba. Si aparecen líneas con `ii` al inicio (installed), los paquetes están instalados. Si no aparece nada, hay que instalarlo.

**Paso 2: Instalar Samba si no está instalado**
```bash
sudo apt update
sudo apt install samba
```
> **Anotación:** `apt update` actualiza la lista de repositorios antes de instalar. Esto garantiza que se instala la versión más reciente disponible. El paquete `samba` incluye tanto el servidor (`smbd` y `nmbd`) como las herramientas de configuración.

**Paso 3: Verificar el estado del servicio**
```bash
sudo systemctl status smbd
```
> **Anotación:** Si el servicio está activo (`Active: active (running)`), Samba está funcionando. Los dos demonios clave de Samba son:
> - `smbd` — Gestiona la compartición de archivos y la autenticación de usuarios. Puertos TCP 139 y 445.
> - `nmbd` — Gestiona la resolución de nombres NetBIOS (para aparecer en el entorno de red de Windows). Puertos UDP 137 y 138.

---

<a id="bloque-2"></a>
## Bloque 2: Copia de seguridad y estructura del fichero smb.conf

> **ENUNCIADO**
> Antes de modificar la configuración, guarda una copia de seguridad de `smb.conf`. Comprende la estructura del fichero.

### Solución — EN EL SERVIDOR

**Paso 1: Hacer copia de seguridad**
```bash
sudo cp /etc/samba/smb.conf /etc/samba/smb.conf.bak
```
> **Anotación:** El primer paso siempre antes de tocar `smb.conf`. Si la nueva configuración rompe algo, se restaura con `sudo cp /etc/samba/smb.conf.bak /etc/samba/smb.conf`.

**Estructura de smb.conf:**

El fichero está dividido en **secciones** identificadas por nombres entre corchetes `[ ]`. Hay tres secciones especiales y el resto son recursos compartidos:

```ini
[global]
# Configuración global del servidor (workgroup, seguridad, logs, etc.)
# Se aplica a todo el servidor.

[homes]
# Sección especial: crea automáticamente un recurso compartido
# para cada usuario que se conecta, apuntando a su /home/usuario.

[printers]
# Sección especial: comparte automáticamente las impresoras del sistema.

[nombre_del_recurso]
# Recurso compartido personalizado. El nombre entre corchetes es el
# nombre que verán los clientes en la red.
   path = /ruta/al/directorio
   opciones...
```

> **Anotación sobre sensibilidad a mayúsculas:** Las secciones y los nombres de los parámetros en smb.conf **no distinguen mayúsculas de minúsculas**. `Read Only = yes` es exactamente igual que `read only = yes` o `READ ONLY = yes`.

**Paso 2: Ver el archivo original**
```bash
cat /etc/samba/smb.conf | grep -v "^#" | grep -v "^;" | grep -v "^$"
```
> **Anotación:** Este comando muestra el archivo sin líneas de comentarios (las que empiezan por `#` o `;`) ni líneas vacías, para ver solo la configuración activa.

---

<a id="bloque-3"></a>
## Bloque 3: Crear el recurso `pub` — público, visible, de solo lectura con acceso de invitados

> **ENUNCIADO**
> Crea el recurso compartido `pub` con las siguientes características:
> - Path: `/samba/publico`
> - Debe aparecer listado en los exploradores de los clientes
> - Debe permitir el acceso de invitados (sin contraseña)
> - Debe ser de solo lectura

### Solución — EN EL SERVIDOR

**Paso 1: Crear el directorio físico**
```bash
sudo mkdir -p /samba/publico
```
> **Anotación:** El directorio debe existir antes de que Samba lo pueda compartir. `-p` crea todos los directorios intermedios necesarios.

**Paso 2: Establecer permisos Linux apropiados**
```bash
sudo chmod 755 /samba/publico
sudo chown root:root /samba/publico
```
> **Anotación:** Para un recurso de solo lectura público:
> - `755` (rwxr-xr-x): el propietario (root) puede leer/escribir/ejecutar; el resto solo leer y entrar. Nadie más escribirá aquí porque la configuración Samba dice `read only = yes`.
> - `chown root:root`: el directorio pertenece a root, lo que es apropiado para contenido gestionado por el administrador.

**Paso 3: Editar smb.conf y añadir la sección [pub]**
```bash
sudo nano /etc/samba/smb.conf
```
Añadir al final del archivo:
```ini
[pub]
   path = /samba/publico
   browseable = yes
   guest ok = yes
   read only = yes
```
> **Anotación, parámetro por parámetro:**
> - `path` — Ruta absoluta en el disco del servidor. Es el único parámetro estrictamente obligatorio en cualquier recurso.
> - `browseable = yes` — El recurso **aparece** en la lista cuando los clientes exploran los recursos disponibles del servidor. Si fuera `no`, existiría pero habría que saber su nombre para acceder.
> - `guest ok = yes` — Permite el acceso sin contraseña. Cualquier cliente puede conectarse como "invitado". Equivalente a `public = yes`. Sin esto, el cliente necesitaría credenciales válidas.
> - `read only = yes` — Solo se puede leer el contenido. Los clientes no pueden crear, modificar ni borrar archivos. Equivalente a `writable = no`.

---

<a id="bloque-4"></a>
## Bloque 4: Crear grupos y usuarios Linux para el recurso privado

> **ENUNCIADO**
> El recurso `privado` debe ser accesible solo a los usuarios `usmb1` y `usmb2`, que pertenecen al grupo `gprivado`. Crea los usuarios y el grupo necesarios.

### Solución — EN EL SERVIDOR

**Paso 1: Crear el grupo Linux `gprivado`**
```bash
sudo groupadd gprivado
```
> **Anotación:** Los grupos en Linux sirven para agrupar usuarios con los mismos permisos. Al usar `@gprivado` en `valid users` de Samba, cualquier miembro del grupo tendrá acceso.

**Paso 2: Crear los usuarios Linux (sin acceso a terminal)**
```bash
sudo useradd -M -s /usr/sbin/nologin -G gprivado usmb1
sudo useradd -M -s /usr/sbin/nologin -G gprivado usmb2
```
> **Anotación, opciones de `useradd`:**
> - `-M` — No crear directorio home. No necesario para usuarios que solo usan Samba.
> - `-s /usr/sbin/nologin` — Shell de no-login. El usuario existe en el sistema pero no puede iniciar sesión interactiva (SSH, terminal). Solo sirve para autenticar en Samba.
> - `-G gprivado` — Añadir al grupo `gprivado` como grupo secundario.

**Paso 3: Verificar que los usuarios pertenecen al grupo**
```bash
getent group gprivado
```
> **Anotación:** `getent group nombre` muestra los miembros del grupo. Debe mostrar: `gprivado:x:XXXX:usmb1,usmb2`.

**Paso 4: Añadir los usuarios a Samba**
```bash
sudo smbpasswd -a usmb1
sudo smbpasswd -a usmb2
```
> **Anotación — Regla de oro de Samba:** Un usuario debe existir primero en Linux (con `useradd`) y luego se añade a Samba (con `smbpasswd -a`). No se puede hacer en orden inverso. Samba no crea usuarios Linux.

**Paso 5: Verificar los usuarios Samba**
```bash
sudo pdbedit -L
```
> Deben aparecer `usmb1` y `usmb2` en la lista.

---

<a id="bloque-5"></a>
## Bloque 5: Crear el recurso `privado` — lectura/escritura, solo para el grupo gprivado

> **ENUNCIADO**
> Crea el recurso compartido `privado` con las siguientes características:
> - Path: `/samba/privado`
> - Debe aparecer listado en los exploradores
> - No debe permitir el acceso de invitados
> - Debe ser de lectura-escritura
> - Solo pueden acceder los usuarios del grupo `gprivado` (`usmb1` y `usmb2`)

### Solución — EN EL SERVIDOR

**Paso 1: Crear el directorio físico**
```bash
sudo mkdir -p /samba/privado
```

**Paso 2: Asignar permisos Linux correctos**
```bash
sudo chown root:gprivado /samba/privado
sudo chmod 770 /samba/privado
```
> **Anotación — Por qué estas opciones:**
> - `chown root:gprivado` — El directorio pertenece al usuario `root` y al grupo `gprivado`. Los miembros de `gprivado` tienen acceso de grupo.
> - `chmod 770` (rwxrwx---): propietario y grupo tienen todos los permisos; los demás no tienen ninguno. Esto garantiza que solo `root` y los miembros de `gprivado` pueden entrar y escribir en la carpeta a nivel Linux.
> - Recuerda la doble capa: Linux debe permitir el acceso además de Samba. Si `chmod` no da permisos al grupo, aunque Samba diga `valid users = @gprivado`, no podrán escribir.

**Paso 3: Añadir la sección [privado] a smb.conf**
```bash
sudo nano /etc/samba/smb.conf
```
```ini
[privado]
   path = /samba/privado
   browseable = yes
   guest ok = no
   valid users = @gprivado
   writable = yes
```
> **Anotación, parámetros nuevos:**
> - `guest ok = no` — No se admiten invitados. Cualquiera que intente conectarse necesita credenciales Samba válidas.
> - `valid users = @gprivado` — Solo pueden acceder los usuarios que pertenezcan al grupo `gprivado`. El `@` delante del nombre indica que es un grupo (no un usuario). Sin el `@`, Samba buscaría un usuario llamado literalmente `gprivado`.
> - `writable = yes` — Sinónimo exacto de `read only = no`. El recurso es de lectura y escritura.
>
> **Relación `valid users` + `guest ok = no`:** El efecto combinado es: solo pueden acceder usuarios autenticados Y que pertenezcan a `gprivado`. Si un usuario está en Samba pero no en `gprivado`, no puede acceder.

---

<a id="bloque-6"></a>
## Bloque 6: Crear el recurso `padron` — solo lectura, bloqueando al usuario pica

> **ENUNCIADO**
> Crea el recurso compartido `padron` con las siguientes características:
> - Path: `/samba/padron`
> - Debe impedir el acceso al usuario `pica`
> - El resto de usuarios puede acceder en modo solo lectura

### Solución — EN EL SERVIDOR

**Paso 1: Crear el usuario `pica` (si no existe) y añadirlo a Samba**
```bash
sudo useradd -M -s /usr/sbin/nologin pica
sudo smbpasswd -a pica
```

**Paso 2: Crear otros usuarios que sí podrán acceder (ejemplo)**
```bash
sudo useradd -M -s /usr/sbin/nologin usmbh
sudo smbpasswd -a usmbh
```

**Paso 3: Crear el directorio físico**
```bash
sudo mkdir -p /samba/padron
sudo chmod 755 /samba/padron
```
> **Anotación:** Permisos `755` para lectura general. El acceso de escritura no es necesario porque el recurso es de solo lectura.

**Paso 4: Añadir la sección [padron] a smb.conf**
```bash
sudo nano /etc/samba/smb.conf
```
```ini
[padron]
   path = /samba/padron
   browseable = yes
   invalid users = pica
   read only = yes
```
> **Anotación, parámetro clave:**
> - `invalid users = pica` — El usuario `pica` tiene **prohibido** el acceso a este recurso. Aunque `pica` tenga credenciales válidas de Samba, al intentar conectarse a `padron` recibirá un error de acceso denegado.
> - **Prioridad de `invalid users`:** Si un usuario aparece tanto en `valid users` como en `invalid users`, prevalece `invalid users`. La prohibición tiene prioridad sobre la autorización.
> - Sin `valid users` especificado y sin `guest ok = no`, el resto de usuarios autenticados puede acceder en modo lectura (por `read only = yes`).
>
> **Diferencia entre `invalid users` y `valid users`:**
> - `valid users` = lista blanca: solo entran los listados.
> - `invalid users` = lista negra: entran todos excepto los listados.
> En `padron` usamos `invalid users` porque queremos bloquear a `pica` específicamente pero permitir al resto.

---

<a id="bloque-7"></a>
## Bloque 7: Habilitar el acceso al directorio home del usuario `usmbh` con `[homes]`

> **ENUNCIADO**
> Habilita el acceso al directorio de trabajo del usuario `usmbh` a través de la sección especial `[homes]`.

### Solución — EN EL SERVIDOR

**Paso 1: Crear el usuario `usmbh` con directorio home**
```bash
sudo useradd -m -s /usr/sbin/nologin usmbh
sudo smbpasswd -a usmbh
```
> **Anotación:** Esta vez usamos `-m` (con `m` minúscula) en lugar de `-M`. La `-m` **sí crea** el directorio home (`/home/usmbh`). Necesitamos que exista el directorio home para que `[homes]` pueda apuntar a él.

**Paso 2: Comprobar que el directorio home existe**
```bash
ls -la /home/usmbh
```

**Paso 3: Añadir la sección [homes] a smb.conf**
```bash
sudo nano /etc/samba/smb.conf
```
```ini
[homes]
   browseable = no
   read only = no
   create mask = 0700
   directory mask = 0700
```
> **Anotación — Cómo funciona [homes]:**
> La sección `[homes]` es una sección especial de Samba. Cuando el usuario `usmbh` se conecta al servidor Samba, Samba crea automáticamente un recurso compartido llamado `usmbh` que apunta exactamente a `/home/usmbh`. El usuario solo ve su propio directorio home, no los de otros usuarios.
>
> **Parámetros:**
> - `browseable = no` — El recurso `[homes]` en sí no aparece en la lista de recursos del servidor. Cuando el usuario `usmbh` se conecta, Samba crea dinámicamente el recurso `usmbh` que sí verá en la lista. Esto evita que aparezca una entrada `homes` genérica en la lista.
> - `read only = no` — El usuario puede leer y escribir en su propio home.
> - `create mask = 0700` — Los archivos que cree el usuario tendrán como máximo permisos `rwx------`. Solo el propietario puede leerlos.
> - `directory mask = 0700` — Las carpetas que cree el usuario tendrán como máximo permisos `rwx------`. Solo el propietario puede acceder.
>
> **Por qué `0700` y no `0644` o `0755`:** Para que los directorios home sean privados. Solo el propietario accede a su home. Otros usuarios del sistema no pueden ver el contenido del home de `usmbh`.

---

<a id="bloque-8"></a>
## Bloque 8: Verificar la configuración con testparm y reiniciar el servicio

> **ENUNCIADO**
> Verifica que no hay errores de sintaxis en la configuración y aplica los cambios al servidor.

### Solución — EN EL SERVIDOR

**El archivo smb.conf completo con todos los recursos debería quedar así:**
```ini
[global]
   workgroup = WORKGROUP
   security = user
   map to guest = bad user
   passdb backend = tdbsam

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

**Paso 1: Verificar la sintaxis con testparm**
```bash
testparm -s
```
> **Anotación:** `testparm` es la herramienta de diagnóstico de Samba. Analiza `smb.conf`, reporta cualquier error de sintaxis o parámetros desconocidos, y muestra la configuración resultante con todos los valores (incluyendo los por defecto que no se especificaron). La opción `-s` (suppress) evita que pida confirmación interactiva.
>
> **Siempre ejecutar `testparm` antes de reiniciar el servicio.** Si hay un error en smb.conf y se reinicia sin verificar, el servicio puede fallar al arrancar o cargarse con configuración incorrecta.

**Paso 2: Aplicar los cambios**
```bash
sudo systemctl reload smbd nmbd
```
> **Anotación — `reload` vs `restart`:**
> - `reload` — Recarga la configuración sin detener el servicio ni cortar conexiones activas. Es la opción preferida cuando hay usuarios conectados.
> - `restart` — Detiene completamente el servicio y lo vuelve a arrancar. Corta todas las conexiones activas. Úsalo solo si el `reload` no es suficiente (por ejemplo, cuando hay cambios estructurales graves).

**Paso 3: Ver las conexiones activas**
```bash
smbstatus
```
> **Anotación:** `smbstatus` muestra en tiempo real qué usuarios están conectados al servidor Samba, desde qué IPs, y qué archivos tienen abiertos. Útil para verificar que las conexiones funcionan y para diagnosticar problemas.

---

<a id="bloque-9"></a>
## Bloque 9: Comprobar el acceso a todos los recursos desde el cliente

> **ENUNCIADO**
> Desde el cliente, verifica que cada recurso compartido se comporta según su configuración.

### Solución — EN EL CLIENTE

**Paso 1: Instalar smbclient si no está instalado**
```bash
sudo apt install smbclient
```

**Paso 2: Listar todos los recursos disponibles en el servidor**
```bash
smbclient -L //IP_SERVIDOR -U usmb1
```
> **Anotación:** Debe mostrar: `pub` (Disk), `privado` (Disk), `padron` (Disk). La sección `[homes]` aparece cuando el usuario se conecta como su propio recurso personal (`usmb1`), no como `homes`.

**Paso 3: Comprobar acceso a `pub` como invitado (sin contraseña)**
```bash
smbclient //IP_SERVIDOR/pub
# Pulsar Enter sin contraseña
smb: \> ls     # Debe listar el contenido
smb: \> put archivo.txt   # Debe fallar (solo lectura)
smb: \> exit
```
> **Anotación esperada:** El acceso sin contraseña debe funcionar. Intentar subir un archivo debe dar "NT_STATUS_ACCESS_DENIED" porque el recurso es `read only = yes`.

**Paso 4: Comprobar acceso a `privado` con usuario del grupo gprivado**
```bash
smbclient //IP_SERVIDOR/privado -U usmb1
# Introducir contraseña de usmb1
smb: \> put archivo.txt   # Debe funcionar (rw)
smb: \> exit
```

**Paso 5: Comprobar que pica no puede acceder a `padron`**
```bash
smbclient //IP_SERVIDOR/padron -U pica
# Introducir contraseña de pica
```
> **Anotación esperada:** Debe dar error de acceso denegado: `NT_STATUS_ACCESS_DENIED` o `tree connect failed`. El usuario `pica` está en `invalid users` de `padron` y Samba rechaza su conexión independientemente de si las credenciales son correctas.

**Paso 6: Comprobar acceso de usmbh a su home**
```bash
smbclient //IP_SERVIDOR/usmbh -U usmbh
# Introducir contraseña de usmbh
smb: \> ls   # Debe mostrar el contenido de /home/usmbh
smb: \> exit
```
> **Anotación:** Gracias a `[homes]`, cuando `usmbh` se conecta al recurso `usmbh`, Samba lo redirige automáticamente a `/home/usmbh`. El usuario puede leer y escribir en su propio directorio home a través de la red.
