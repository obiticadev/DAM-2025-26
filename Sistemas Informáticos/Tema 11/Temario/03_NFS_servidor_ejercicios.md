# Ejercicios: Servidor NFS — Práctica de Clase
### Fuente: Servidor NFS.pdf

> Configuración completa de un servidor NFS con tres tipos de exportación y montaje NFSv4.
> **Red utilizada:** `192.168.100.0/24` · **Servidor:** `192.168.100.100` · **Cliente:** `192.168.100.101`

---

## Índice

| Bloque | Proceso |
|--------|---------|
| [Bloque 1](#bloque-1) | Copia de seguridad del fichero de exportaciones `/etc/exports` |
| [Bloque 2](#bloque-2) | Crear y exportar `/nfs/lectura` con acceso de solo lectura |
| [Bloque 3](#bloque-3) | Crear y exportar `/nfs/escritura` con lectura/escritura y aplastamiento |
| [Bloque 4](#bloque-4) | Crear y exportar `/nfs/squash` con lectura/escritura y aplastamiento |
| [Bloque 5](#bloque-5) | Montaje temporal de los tres recursos en el equipo cliente |
| [Bloque 6](#bloque-6) | Configurar el montaje permanente de `/nfs/squash` en el cliente vía fstab |
| [Bloque 7](#bloque-7) | NFSv4: crear árbol de exportaciones con raíz `/exports`, subdirectorios `/Datos` y `/Pub` |

---

<a id="bloque-1"></a>
## Bloque 1: Copia de seguridad del fichero de exportaciones `/etc/exports`

> **ENUNCIADO**
> Crear una copia del fichero `/etc/exports` como `/etc/exports.bak`.

### Solución — EN EL SERVIDOR

```bash
sudo cp /etc/exports /etc/exports.bak
```
> **Anotación:**
> - `sudo` — Necesario porque `/etc/exports` pertenece al sistema y requiere permisos de administrador.
> - `cp` — Comando de copia (copy). Crea un duplicado exacto del archivo en el destino especificado.
> - `/etc/exports.bak` — Nombre del archivo de respaldo. La extensión `.bak` es convención estándar para backups.
>
> **Por qué es el primer paso siempre:** Si cometemos un error en la configuración de los ejercicios siguientes (una línea mal escrita, un espacio de más, opciones incorrectas), podemos restaurar el archivo original en segundos con `sudo cp /etc/exports.bak /etc/exports`. Sin este backup, habría que reconstruir la configuración de memoria.

---

<a id="bloque-2"></a>
## Bloque 2: Crear y exportar `/nfs/lectura` con acceso de solo lectura

> **ENUNCIADO**
> Exportar el directorio `/nfs/lectura` con acceso de "solo lectura" para todos los equipos de la red `192.168.100.0/24`. El punto de montaje en el cliente será `/mnt/nfs/lectura`.

### Solución — EN EL SERVIDOR

**Paso 1: Instalar el servidor NFS**
```bash
sudo apt install nfs-kernel-server
sudo systemctl status nfs-kernel-server
```
> **Anotación:** Verificar que el servicio está activo antes de continuar. Si no está activo: `sudo systemctl start nfs-kernel-server`.

**Paso 2: Crear el directorio físico**
```bash
sudo mkdir -p /nfs/lectura
```
> **Anotación:** `-p` crea todos los directorios intermedios necesarios. En este caso, crea primero `/nfs` y luego `/nfs/lectura` si no existen. Si no se usa `-p` y `/nfs` no existe, el comando falla.

**Paso 3: Permisos de Linux para el directorio de lectura**
```bash
# Para solo lectura no se necesita chmod adicional.
# El directorio tendrá los permisos por defecto (755), que ya son correctos.
ls -la /nfs/
```
> **Anotación:** Para una exportación de solo lectura, los permisos por defecto (`755`: rwxr-xr-x) son suficientes. El propietario (root) puede leer y ejecutar. Los demás solo pueden leer y entrar. No hace falta `chmod 777` porque nadie escribirá aquí.

**Paso 4: Editar /etc/exports y añadir la exportación de lectura**
```bash
sudo nano /etc/exports
```
Añadir:
```text
/nfs/lectura    192.168.100.0/24(ro,sync,no_subtree_check)
```
> **Anotación, opción por opción:**
> - `192.168.100.0/24` — Solo los equipos de esta subred pueden montar el recurso. Es mucho más seguro que `*`.
> - `ro` — Read Only (solo lectura). Los clientes pueden ver y copiar archivos pero no crear, modificar ni eliminar nada. Es el **valor por defecto** de NFS si no se especifica `rw`.
> - `sync` — El servidor confirma la escritura solo cuando los datos están físicamente en disco. Aunque este recurso sea de solo lectura y las escrituras no apliquen directamente, `sync` también afecta a otras operaciones internas del servidor NFS.
> - `no_subtree_check` — Desactiva la comprobación de subárbol. Recomendado por razones de rendimiento y compatibilidad.
> - **Crítico — Sin espacio antes del paréntesis:** La IP y el paréntesis deben estar juntos (`192.168.100.0/24(ro,...)`). Con un espacio entre ellos, el comportamiento cambia completamente.

**Paso 5: Aplicar los cambios**
```bash
sudo exportfs -ra
```
> **Anotación:** Fuerza al demonio NFS a releer `/etc/exports` sin reiniciar el servicio. Siempre ejecutar después de cada cambio en el archivo.

---

<a id="bloque-3"></a>
## Bloque 3: Crear y exportar `/nfs/escritura` con lectura/escritura y aplastamiento

> **ENUNCIADO**
> Exportar `/nfs/escritura` con permisos de escritura para todos los equipos de la red `192.168.100.0/24` y con aplastamiento para todos los usuarios. Punto de montaje en el cliente: `/mnt/nfs/escritura`.

### Solución — EN EL SERVIDOR

**Paso 1: Crear el directorio físico**
```bash
sudo mkdir -p /nfs/escritura
```

**Paso 2: Dar permisos de escritura en Linux**
```bash
sudo chmod 777 /nfs/escritura
```
> **Anotación:** Para que los clientes puedan escribir, el directorio debe tener permisos de escritura a nivel del sistema de archivos Linux. Cuando se usa `all_squash`, todas las operaciones del cliente llegan al servidor como el usuario anónimo `nobody`. Si el directorio no permite que `nobody` escriba, los clientes no podrán crear archivos aunque NFS diga `rw`. `chmod 777` da permisos a todos, incluyendo `nobody`.

**Paso 3: Añadir la exportación a /etc/exports**
```bash
sudo nano /etc/exports
```
Añadir debajo de la línea anterior:
```text
/nfs/escritura  192.168.100.0/24(rw,sync,all_squash,no_subtree_check)
```
> **Anotación, opción nueva — `all_squash`:**
> - `rw` — Lectura y escritura.
> - `all_squash` — **Aplastamiento total**. Degrada a absolutamente todos los usuarios que acceden desde el cliente (incluyendo root) al usuario anónimo `nobody`. No se mantiene el UID ni el GID de ningún usuario. Es la opción más restrictiva de squash.
>   - Diferencia con `root_squash` (defecto): `root_squash` solo degrada al root, el resto de usuarios mantiene su UID. Con `all_squash`, todos son `nobody`, independientemente de quién sean en el cliente.
> - Esto es útil para carpetas públicas donde no importa quién escribe porque todos son tratados como el mismo usuario anónimo.

**Paso 4: Aplicar los cambios**
```bash
sudo exportfs -ra
```

---

<a id="bloque-4"></a>
## Bloque 4: Crear y exportar `/nfs/squash` con lectura/escritura y aplastamiento

> **ENUNCIADO**
> Exportar `/nfs/squash` con permisos de escritura para todos los equipos de la red `192.168.100.0/24` y aplastamiento para todos los usuarios. Punto de montaje en el cliente: `/mnt/nfs/squash`.

### Solución — EN EL SERVIDOR

**Paso 1: Crear el directorio físico**
```bash
sudo mkdir -p /nfs/squash
```

**Paso 2: Dar permisos de escritura**
```bash
sudo chmod 777 /nfs/squash
```
> **Anotación:** Mismo razonamiento que en el bloque anterior: con `all_squash`, todas las operaciones llegan como `nobody`. El directorio necesita permisos `777` para que `nobody` pueda escribir.

**Paso 3: Añadir la exportación a /etc/exports**

El archivo `/etc/exports` debe quedar así con las tres exportaciones:
```text
/nfs/lectura    192.168.100.0/24(ro,sync,no_subtree_check)
/nfs/escritura  192.168.100.0/24(rw,sync,all_squash,no_subtree_check)
/nfs/squash     192.168.100.0/24(rw,sync,all_squash,no_subtree_check)
```
> **Anotación:** `/nfs/escritura` y `/nfs/squash` tienen la misma configuración en este ejercicio. La diferencia es conceptual: el ejercicio los distingue como dos recursos distintos para practicar que ambos tienen `all_squash`. En un entorno real, podrían tener configuraciones diferentes o servir a diferentes grupos de usuarios.

**Paso 4: Aplicar todos los cambios de una vez**
```bash
sudo exportfs -ra
```

**Paso 5: Verificar las tres exportaciones activas**
```bash
exportfs -s
```
> **Anotación:** Debe mostrar las tres rutas con sus opciones. También se puede verificar desde el cliente con `showmount -e 192.168.100.100`.

---

<a id="bloque-5"></a>
## Bloque 5: Montaje temporal de los tres recursos en el equipo cliente

> **ENUNCIADO**
> En el equipo cliente, realiza el montaje temporal de los tres recursos exportados por el servidor.

### Solución — EN EL CLIENTE

**Paso 1: Instalar el software cliente NFS**
```bash
sudo apt install nfs-common
```
> **Anotación:** Solo necesario si no estaba instalado. `nfs-common` proporciona las herramientas para montar recursos NFS y el comando `showmount`.

**Paso 2: Verificar la conectividad con el servidor**
```bash
ping 192.168.100.100
showmount -e 192.168.100.100
```
> **Anotación:** `showmount -e` lista las exportaciones del servidor. Si aparecen las tres rutas (`/nfs/lectura`, `/nfs/escritura`, `/nfs/squash`), el servidor está correctamente configurado y el cliente puede alcanzarlo.

**Paso 3: Crear los puntos de montaje locales**
```bash
sudo mkdir -p /mnt/nfs/lectura /mnt/nfs/escritura /mnt/nfs/squash
```
> **Anotación:** `mkdir -p` con varias rutas crea los tres directorios de una vez. Los puntos de montaje deben estar vacíos antes de montar. Si hubiera archivos dentro, quedarían tapados por el recurso montado y no serían accesibles mientras el montaje esté activo.

**Paso 4: Montar los tres recursos**
```bash
sudo mount -t nfs 192.168.100.100:/nfs/lectura   /mnt/nfs/lectura
sudo mount -t nfs 192.168.100.100:/nfs/escritura  /mnt/nfs/escritura
sudo mount -t nfs 192.168.100.100:/nfs/squash     /mnt/nfs/squash
```
> **Anotación:** Cada `mount` establece una conexión independiente con el servidor. `-t nfs` especifica el tipo de sistema de archivos. La sintaxis `IP:ruta_remota` indica el servidor y la carpeta que se monta.

**Paso 5: Verificar los tres montajes**
```bash
df -h | grep nfs
```
> **Anotación:** Si los tres recursos aparecen en la lista, los montajes están activos. El espacio que muestra `df` corresponde al disco físico del servidor, no al del cliente.

**Paso 6: Probar el comportamiento de cada recurso**
```bash
# Intentar escribir en lectura (debe fallar):
touch /mnt/nfs/lectura/test.txt
# Error esperado: "Read-only file system"

# Escribir en escritura y squash (debe funcionar):
touch /mnt/nfs/escritura/test.txt
touch /mnt/nfs/squash/test.txt
```
> **Anotación:** El intento de escritura en `/mnt/nfs/lectura` debe fallar con "Read-only file system" porque el servidor tiene esa exportación configurada como `ro`. Los otros dos deben permitir escritura. En el servidor, los archivos creados en `/nfs/escritura` y `/nfs/squash` pertenecerán a `nobody` (por `all_squash`).

---

<a id="bloque-6"></a>
## Bloque 6: Configurar el montaje permanente de `/nfs/squash` en el cliente vía fstab

> **ENUNCIADO**
> Configura el equipo cliente para disponer de forma permanente de acceso a la exportación `/nfs/squash` del servidor. El montaje debe realizarse automáticamente en cada inicio del sistema.

### Solución — EN EL CLIENTE

**Paso 1: Editar el fichero /etc/fstab**
```bash
sudo nano /etc/fstab
```
> **Anotación:** `/etc/fstab` es el registro de sistemas de archivos que Linux monta automáticamente al arrancar. Es un archivo crítico: un error de sintaxis puede impedir el arranque. Por eso siempre se verifica con `mount -a` antes de reiniciar.

**Paso 2: Añadir la línea al final del archivo**
```text
192.168.100.100:/nfs/squash  /mnt/nfs/squash  nfs  defaults  0  0
```
> **Anotación, campo por campo:**
> - `192.168.100.100:/nfs/squash` — Fuente del montaje: IP del servidor y ruta del recurso remoto.
> - `/mnt/nfs/squash` — Destino local (punto de montaje). Debe existir ya como directorio.
> - `nfs` — Tipo de sistema de archivos.
> - `defaults` — Opciones de montaje estándar. Para NFS en red interna suele ser suficiente.
> - `0` (primer cero) — No hacer dump automático. Para NFS siempre 0.
> - `0` (segundo cero) — No ejecutar `fsck` al arrancar. Para NFS siempre 0 (fsck es para discos físicos locales).

**Paso 3: Desmontar el montaje temporal activo y verificar con mount -a**
```bash
sudo umount /mnt/nfs/squash
sudo mount -a
```
> **Anotación:** Primero se desmonta el montaje temporal que se hizo en el Bloque 5, luego `mount -a` intenta montar todo lo que hay en fstab. Si la línea añadida tiene algún error o el servidor no responde, `mount -a` reporta el error aquí en lugar de durante el próximo arranque del sistema. Es la verificación más importante antes de reiniciar.

**Paso 4: Verificar que el montaje está activo**
```bash
df -h | grep squash
```
> El recurso debe aparecer como montado. Al reiniciar el sistema, se montará automáticamente sin intervención.

---

<a id="bloque-7"></a>
## Bloque 7: NFSv4 — Crear árbol de exportaciones con raíz `/exports`, subdirectorios `/Datos` y `/Pub`

> **ENUNCIADO**
> La versión NFSv4 permite crear un árbol de exportaciones desde un punto raíz. Configura el servidor NFS para crear un sistema de exportaciones para todos los equipos de la red con:
> - **Punto raíz:** `/exports`
> - **Directorios exportados:** `/Datos` (lectura y escritura) y `/Pub` (solo lectura)

### Solución — EN EL SERVIDOR

**Paso 1: Crear la estructura física de directorios**
```bash
sudo mkdir -p /exports/Datos /exports/Pub
```
> **Anotación:** Se crea la raíz `/exports` y dentro de ella los dos subdirectorios. En NFSv4, todos los directorios exportados deben estar dentro del directorio raíz (el que tiene `fsid=0`).

**Paso 2: Asignar permisos de sistema operativo Linux**
```bash
sudo chmod 777 /exports/Datos   # Para permitir escritura a cualquiera
sudo chmod 755 /exports/Pub     # Solo lectura para invitados
```
> **Anotación:**
> - `/Datos` necesita `777` porque los clientes escribirán en él.
> - `/Pub` con `755` permite a root (propietario) leer/escribir/ejecutar, y al resto solo leer y entrar. Los clientes solo podrán leer el contenido.

**Paso 3: Configurar /etc/exports con la estructura NFSv4**
```bash
sudo nano /etc/exports
```
```text
/exports        192.168.100.0/24(rw,sync,fsid=0,crossmnt,no_subtree_check)
/exports/Datos  192.168.100.0/24(rw,sync,no_subtree_check)
/exports/Pub    192.168.100.0/24(ro,sync,no_subtree_check)
```
> **Anotación, parámetros clave de NFSv4:**
> - **`fsid=0`** en `/exports`: Este es el parámetro más importante de NFSv4. Declara esa carpeta como el "punto cero" o raíz virtual de todas las exportaciones del servidor. Solo puede haber un directorio con `fsid=0` en el servidor. Cuando el cliente monta `IP:/`, el servidor le da esta carpeta.
> - **`crossmnt`** en `/exports`: "Cross mount". Permite que el cliente, al montar la raíz, pueda "saltar" automáticamente a los subdirectorios exportados (`Datos` y `Pub`) sin tener que montarlos por separado. Sin `crossmnt`, el cliente vería la raíz vacía.
> - `/exports/Datos` y `/exports/Pub` se exportan como subdirectorios del árbol. No necesitan `fsid` especial.
> - **Ventaja de seguridad:** El cliente monta `IP:/` (raíz virtual), no `IP:/exports`. Esto oculta la estructura real de directorios del servidor.

**Paso 4: Aplicar los cambios**
```bash
sudo exportfs -ra
exportfs -s
```

---

**CLIENTE:**

**Paso 5: Crear punto de montaje y montar la raíz NFSv4**
```bash
sudo mkdir -p /mnt/nfs4
sudo mount -t nfs4 192.168.100.100:/ /mnt/nfs4
```
> **Anotación:**
> - `-t nfs4` especifica NFSv4 explícitamente (en lugar de NFSv3 con `-t nfs`).
> - Se monta `IP:/` (barra sola), no `IP:/exports`. El servidor sabe que `/` en NFSv4 significa el directorio marcado con `fsid=0`.
> - Gracias a `crossmnt`, automáticamente se puede acceder a `/mnt/nfs4/Datos` y `/mnt/nfs4/Pub`.

**Paso 6: Verificar el árbol de exportaciones**
```bash
ls /mnt/nfs4/
# Debe mostrar: Datos  Pub

# Probar escritura en Datos (debe funcionar):
touch /mnt/nfs4/Datos/dato0.txt

# Probar escritura en Pub (debe fallar — solo lectura):
touch /mnt/nfs4/Pub/pub.txt
# Error esperado: "touch: no se puede efectuar 'touch' sobre './Pub/pub.txt': Sistema de archivos de solo lectura"
```
> **Anotación:** El comportamiento esperado confirma que el árbol NFSv4 funciona: `Datos` permite escritura y `Pub` es de solo lectura, exactamente como se configuró en el servidor. El cliente solo ve `Datos` y `Pub`, sin saber que en el servidor están en `/exports/`.
