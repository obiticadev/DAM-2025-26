# Informe de Solución: Configuración de Servidor y Cliente NFS

### Configuración previa de infraestructura (Obligatorio)
Para que los servicios de red sean estables, se ha configurado una dirección IP estática en ambos equipos bajo el rango de red local detectado.

*   **Red:** `192.168.88.0/24`
*   **Servidor NFS:** `192.168.88.100`
*   **Cliente NFS:** `192.168.88.101`

---

### 1. Copia de seguridad
Crear una copia del fichero `/etc/exports` como `/etc/exports.bak`.

> #### 💡 SOLUCIÓN - COMANDOS EN EL SERVIDOR
> 
> ```bash
> sudo cp /etc/exports /etc/exports.bak
> ```
> 
> **Explicación técnica:**
> *   **`sudo`**: Ejecuta el comando con privilegios de administrador, necesario para acceder a la carpeta `/etc`.
> *   **`cp`**: Comando de copiado (*copy*).
> *   **`target` y `destination`**: Se crea un duplicado exacto.
> *   **Finalidad**: Disponer de un punto de retorno seguro. Si la configuración de los siguientes ejercicios falla o corrompe el archivo, podemos restaurar el original simplemente renombrando el archivo `.bak`.

---

### 2. Configuración de exportaciones
Realizar las siguientes exportaciones en el servidor para la red `192.168.88.0/24`:

**A. Recurso: `/nfs/lectura`** (Solo lectura)
**B. Recurso: `/nfs/escritura`** (Lectura/Escritura + Aplastamiento)
**C. Recurso: `/nfs/squash`** (Lectura/Escritura + Aplastamiento)

> #### 💡 SOLUCIÓN - CONFIGURACIÓN DEL SERVIDOR
> 
> **Paso 1: Creación física de directorios y permisos de sistema:**
> ```bash
> # Creamos las carpetas en el disco duro
> sudo mkdir -p /nfs/lectura /nfs/escritura /nfs/squash
> 
> # Otorgamos permisos totales en Linux para permitir la escritura vía red
> sudo chmod 777 /nfs/escritura /nfs/squash
> ```
> 
> **Paso 2: Edición del fichero `/etc/exports`:**
> ```text
> # Contenido añadido al final del fichero:
> /nfs/lectura    192.168.88.0/24(ro,sync,no_subtree_check)
> /nfs/escritura  192.168.88.0/24(rw,sync,all_squash,no_subtree_check)
> /nfs/squash     192.168.88.0/24(rw,sync,all_squash,no_subtree_check)
> ```
> 
> **Paso 3: Aplicar los cambios:**
> ```bash
> sudo exportfs -ra
> ```
> 
> **Anotaciones de parámetros:**
> *   **`ro` (Read Only)**: El cliente solo puede ver archivos, no crearlos ni borrarlos.
> *   **`rw` (Read Write)**: Permite modificar el contenido del directorio.
> *   **`sync`**: El servidor no confirma la escritura hasta que los datos están físicamente en el disco (mayor seguridad).
> *   **`all_squash`**: El "Aplastamiento". Cualquier usuario que se conecte (incluso root) será tratado como un usuario anónimo (`nobody`). Esto evita que un cliente tome control del servidor.
> *   **`exportfs -ra`**: Comando para que el servicio NFS lea los cambios del archivo sin necesidad de reiniciar el servidor entero.

---

### 3. Montaje temporal
Realiza el montaje temporal de los tres recursos en el equipo cliente.

> #### 💡 SOLUCIÓN - COMANDOS EN EL CLIENTE
> 
> **Paso 1: Preparación de puntos de montaje:**
> ```bash
> sudo mkdir -p /mnt/nfs/lectura /mnt/nfs/escritura /mnt/nfs/squash
> ```
> 
> **Paso 2: Ejecución de los montajes:**
> ```bash
> sudo mount -t nfs 192.168.88.100:/nfs/lectura /mnt/nfs/lectura
> sudo mount -t nfs 192.168.88.100:/nfs/escritura /mnt/nfs/escritura
> sudo mount -t nfs 192.168.88.100:/nfs/squash /mnt/nfs/squash
> ```
> 
> **Paso 3: Verificación:**
> ```bash
> df -h | grep nfs
> ```
> 
> **Anotaciones técnica:**
> *   **`mount -t nfs`**: Indica que vamos a montar un sistema de archivos de red tipo NFS.
> *   **`IP:/ruta_remota`**: Especifica dónde está el servidor y qué carpeta ofrece.
> *   **`/mnt/...`**: Es la ruta local donde el cliente "verá" los archivos.
> *   **Fin del comando**: El uso de `df -h` nos permite confirmar que la conexión es real y ver el espacio disponible en el disco duro remoto del servidor.

Aquí tienes la segunda parte y final del documento, completando la configuración permanente, el árbol de exportaciones NFSv4 y la comparativa técnica solicitada.

---

### 4. Configuración permanente
Configura el equipo cliente para disponer de forma permanente de acceso a la exportación `/nfs/squash` del servidor.

> #### 💡 SOLUCIÓN - CONFIGURACIÓN DEL CLIENTE
> 
> **Paso 1: Edición del fichero de sistemas de archivos:**
> ```bash
> sudo nano /etc/fstab
> ```
> 
> **Paso 2: Inserción de la línea de montaje automático (al final del archivo):**
> ```text
> 192.168.88.100:/nfs/squash  /mnt/nfs/squash  nfs  defaults  0  0
> ```
> 
> **Paso 3: Verificación del montaje sin reiniciar:**
> ```bash
> sudo umount /mnt/nfs/squash
> sudo mount -a
> ```
> 
> **Anotaciones técnicas:**
> *   **`/etc/fstab`**: Es el archivo que lee Linux durante el arranque para montar particiones y recursos de red automáticamente.
> *   **`defaults`**: Aplica un conjunto de opciones estándar (lectura/escritura, ejecución de binarios, etc.).
> *   **`0 0`**: El primer cero evita que el sistema intente hacer un volcado de seguridad (dump) y el segundo evita que `fsck` revise el disco al arrancar (ya que es un recurso de red, no un disco físico).
> *   **`mount -a`**: Es fundamental. Intenta montar todo lo que hay en el `fstab`. Si hay un error de escritura en el archivo, este comando nos avisará antes de que reiniciemos y el sistema falle.

---

### 5. Configuración avanzada: NFSv4 (Árbol de exportaciones)
La versión NFS4 permite crear un árbol de exportaciones desde un punto raíz. Configura el servidor NFS para crear un sistema de exportaciones para todos los equipos de la red `192.168.88.0/24`.

*   **Punto raíz:** `/exports`
*   **Directorios:** `/Datos` (rw) y `/Pub` (ro)

> #### 💡 SOLUCIÓN - CONFIGURACIÓN DEL SERVIDOR
> 
> **Paso 1: Creación de la estructura física:**
> ```bash
> sudo mkdir -p /exports/Datos /exports/Pub
> sudo chmod 777 /exports/Datos  # Para permitir escritura
> sudo chmod 755 /exports/Pub    # Solo lectura para invitados
> ```
> 
> **Paso 2: Configuración de la "pseudo-raíz" en `/etc/exports`:**
> ```text
> # Definición de la raíz virtual (fsid=0)
> /exports        192.168.88.0/24(rw,sync,fsid=0,crossmnt,no_subtree_check)
> 
> # Definición de los subdirectorios del árbol
> /exports/Datos  192.168.88.0/24(rw,sync,no_subtree_check)
> /exports/Pub    192.168.88.0/24(ro,sync,no_subtree_check)
> ```
> 
> **Paso 3: Aplicar cambios:**
> ```bash
> sudo exportfs -ra
> ```
> 
> #### 💡 SOLUCIÓN - CONFIGURACIÓN DEL CLIENTE
> 
> **Paso 1: Montaje de la raíz (simplificado en NFSv4):**
> ```bash
> sudo mkdir -p /mnt/nfs4
> sudo mount -t nfs4 192.168.88.100:/ /mnt/nfs4
> ```
> 
> **Anotaciones técnicas:**
> *   **`fsid=0`**: Es el parámetro más importante de NFSv4. Indica que esa carpeta es el "kilómetro cero" de las exportaciones. El cliente no necesita conocer la ruta real del servidor, solo apunta a la raíz (`/`).
> *   **`crossmnt`**: Permite que el cliente salte de la carpeta raíz a las subcarpetas de forma transparente sin tener que montarlas una por una.
> *   **Diferencia de montaje**: Fíjate que en el cliente montamos `IP:/`, no `IP:/exports`. Esto oculta la estructura real de carpetas del servidor, mejorando la seguridad.

---

### 🏁 Comparativa Final de Protocolos

A continuación, se detallan las diferencias clave entre las tecnologías trabajadas:

| Característica | NFS (v2/v3) | NFSv4 | Samba (SMB/CIFS) |
| :--- | :--- | :--- | :--- |
| **Arquitectura** | Sin estado (Stateless). | Con estado (Stateful). | Con estado (Stateful). |
| **Puertos** | Múltiples y aleatorios (difícil para firewalls). | Un solo puerto (TCP 2049). | Un solo puerto (TCP 445). |
| **Exportación** | Carpetas individuales y rutas completas. | Árbol de exportación único (`fsid=0`). | Recursos compartidos con nombres. |
| **Seguridad** | Basada en IP del host. | Basada en IP y soporte avanzado Kerberos. | Basada en Usuarios y Contraseñas. |
| **Compatibilidad** | Principalmente sistemas Unix/Linux. | Unix/Linux y soporte mejorado. | Universal (Windows, Linux, macOS). |
| **Uso ideal** | Servidores Linux en redes locales seguras. | Redes modernas Linux con firewalls activos. | Redes mixtas con usuarios Windows. |