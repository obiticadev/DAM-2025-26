# Actividades: NFS — Teoría y Fundamentos
### Fuente: NFS.pdf

---

## Índice

| Bloque | Proceso |
|--------|---------|
| [Bloque 1](#bloque-1) | Interpretar los permisos r, w, x sobre un directorio de Linux |
| [Bloque 2](#bloque-2) | Analizar las opciones de exportación `*(rw,async,no_subtree_check)` |
| [Bloque 3](#bloque-3) | Crear la exportación `/exportaciones` con acceso de escritura para todos |
| [Bloque 4](#bloque-4) | Montar el recurso en el cliente en `/mnt/enmv` con permiso de escritura |
| [Bloque 5](#bloque-5) | Comprobar el efecto de root_squash creando ficheros como root y como usuario |
| [Bloque 6](#bloque-6) | Desactivar root squashing y justificar el nuevo comportamiento |
| [Bloque 7](#bloque-7) | Configurar el montaje automático en cada inicio del sistema vía fstab |
| [Bloque 8](#bloque-8) | NFSv4: exportar `/home` del servidor como pseudo sistema de archivos unificado |

---

<a id="bloque-1"></a>
## Bloque 1: Interpretar los permisos r, w, x sobre un directorio de Linux

> **ENUNCIADO**
> Indique el significado de los permisos de lectura (r), escritura (w) y ejecución (x) sobre un **directorio** de Linux.

### Solución

Los permisos en Linux tienen un significado diferente cuando se aplican a **directorios** en comparación con archivos:

**`r` — Lectura sobre un directorio:**
Permite **listar el contenido** del directorio. Con este permiso, el usuario puede ejecutar `ls` dentro del directorio y ver los nombres de los archivos que contiene. Sin él, aunque el directorio exista y el usuario pueda entrar, no verá su contenido.

**`w` — Escritura sobre un directorio:**
Permite **crear, renombrar y eliminar archivos** dentro del directorio. No afecta al contenido de los archivos en sí (eso depende de los permisos del archivo), sino a las entradas del directorio: crear nuevas, modificar sus nombres o eliminarlas.

**`x` — Ejecución sobre un directorio:**
Permite **entrar al directorio** (hacer `cd` hacia él) y **acceder a los archivos que contiene** si se conoce su nombre. Es el permiso más fundamental de los tres: sin él, los permisos `r` y `w` no tienen utilidad práctica.

> **Anotación — La diferencia sutil entre `r` y `x`:** Con `r` sin `x` puedes ver que los archivos existen (sus nombres en el listado) pero no puedes abrirlos ni entrar al directorio. Con `x` sin `r` puedes entrar y acceder a los archivos si conoces exactamente sus nombres, pero no puedes listar el contenido. Para el uso normal de un directorio se necesitan ambos. El permiso `x` también es el que permite a los usuarios "atravesar" un directorio en su camino hacia una ruta más profunda.

---

<a id="bloque-2"></a>
## Bloque 2: Analizar las opciones de exportación `*(rw,async,no_subtree_check)`

> **ENUNCIADO**
> Explique el significado completo de la siguiente línea de opciones tal como aparecería en `/etc/exports`:
> `*(rw,async,no_subtree_check)`

### Solución

La línea completa en `/etc/exports` tendría este aspecto:
```text
/directorio *(rw,async,no_subtree_check)
```

Desglose de cada elemento:

**`*` — Comodín de cliente (¿quién puede acceder?):**
El asterisco `*` significa que **cualquier equipo** con acceso de red al servidor puede montar este recurso, independientemente de su dirección IP. Es el identificador de host más permisivo. No hay restricción de origen.

> **Anotación de seguridad:** Usar `*` en producción es arriesgado porque significa que cualquier equipo que llegue al servidor puede montar el recurso. En redes internas completamente controladas puede ser aceptable, pero lo correcto es limitar por subred: `192.168.100.0/24` en lugar de `*`.

**`rw` — Lectura y escritura:**
El directorio se comparte con permisos de **lectura y escritura**. Los clientes pueden crear, modificar y eliminar archivos. La alternativa es `ro` (read only), que es el valor por defecto si no se especifica nada.

**`async` — Escritura asíncrona:**
El servidor confirma que la escritura se ha completado **antes** de que los datos estén realmente guardados en el disco físico. Esto mejora el rendimiento porque el servidor no bloquea la respuesta esperando la escritura en disco. Sin embargo, si el servidor falla en el momento exacto en que los datos están en memoria pero aún no en disco, esos datos se pierden o corrompen.

> **Anotación:** La alternativa recomendada es `sync`. Con `sync`, el servidor solo confirma la escritura cuando los datos están físicamente en disco. Es más lento pero completamente seguro. En entornos de producción donde la integridad de los datos es crítica, siempre se usa `sync`.

**`no_subtree_check` — Sin comprobación de subárbol:**
Desactiva una verificación que el servidor NFS realiza para confirmar que el archivo al que accede el cliente está realmente dentro del directorio exportado (y no en un directorio padre al que no tiene acceso). Esta comprobación puede causar problemas cuando los archivos cambian de número de inodo durante operaciones normales, y tiene un impacto negativo en el rendimiento. La documentación oficial recomienda incluir siempre esta opción.

---

<a id="bloque-3"></a>
## Bloque 3: Crear la exportación `/exportaciones` con acceso de escritura para todos

> **ENUNCIADO**
> En el servidor, cree la exportación del directorio `/exportaciones` permitiendo la escritura por parte de todos los usuarios.

### Solución — EN EL SERVIDOR

**Paso 1: Instalar el servidor NFS si no está instalado**
```bash
sudo apt install nfs-kernel-server
sudo systemctl status nfs-kernel-server
```
> **Anotación:** El paquete `nfs-kernel-server` instala el servidor NFS. `systemctl status` confirma que el servicio está activo. Si no está activo, se arranca con `sudo systemctl start nfs-kernel-server`.

**Paso 2: Crear el directorio físico en el disco del servidor**
```bash
sudo mkdir /exportaciones
```
> **Anotación:** El directorio debe existir físicamente en el disco antes de configurar la exportación. NFS no lo crea automáticamente. Si el directorio no existe cuando se aplica la exportación, los clientes no podrán montarlo.

**Paso 3: Dar permisos de escritura en Linux**
```bash
sudo chmod 777 /exportaciones
```
> **Anotación:** Aquí está el error más frecuente de los estudiantes: configuran `rw` en `/etc/exports` pero olvidan dar permisos de escritura a nivel del sistema de archivos Linux. Hay **dos capas de permisos independientes**:
> 1. Permisos Linux (`chmod`/`chown`): controlan qué puede hacer el proceso `nfsd` con el directorio.
> 2. Permisos NFS (opciones en `/etc/exports`): controlan qué puede hacer el cliente remoto.
> Si Linux dice que el directorio es de solo lectura, los clientes no pueden escribir aunque NFS diga `rw`. **Siempre prevalece el más restrictivo.**
> `chmod 777` da permisos completos (rwxrwxrwx) a propietario, grupo y otros. En producción se usaría `chown` para asignar el propietario correcto y permisos más restrictivos.

**Paso 4: Hacer copia de seguridad de /etc/exports**
```bash
sudo cp /etc/exports /etc/exports.bak
```
> **Anotación:** Antes de modificar cualquier archivo de configuración del sistema, siempre se hace un backup. Si la configuración nueva es incorrecta, se puede restaurar con `sudo cp /etc/exports.bak /etc/exports`.

**Paso 5: Editar /etc/exports y añadir la exportación**
```bash
sudo nano /etc/exports
```
Añadir al final del archivo:
```text
/exportaciones *(rw,sync,no_subtree_check)
```
> **Anotación — Atención al espacio:** NO debe haber espacio entre el `*` y el paréntesis. Si se escribe `/exportaciones * (rw,...)` (con espacio antes del paréntesis), el sistema interpreta que `*` tiene permisos por defecto (`ro`) y que las opciones del paréntesis se aplican a cualquier otro cliente distinto de `*`, lo que cambia completamente el comportamiento.

**Paso 6: Aplicar los cambios sin reiniciar el servicio**
```bash
sudo exportfs -ra
```
> **Anotación:** `exportfs -ra` le dice al demonio NFS que vuelva a leer el archivo `/etc/exports` y aplique los cambios. La opción `-r` es "re-exportar" y `-a` es "all" (todos). No interrumpe las conexiones activas. Si se reiniciara el servicio completo, los clientes conectados perderían el montaje.

**Paso 7: Verificar las exportaciones activas**
```bash
exportfs -s
```
> **Anotación:** Muestra la lista de exportaciones activas y sus opciones. Debe aparecer `/exportaciones` con `rw,sync,no_subtree_check`.

---

<a id="bloque-4"></a>
## Bloque 4: Montar el recurso en el cliente en `/mnt/enmv` con permiso de escritura

> **ENUNCIADO**
> En el equipo cliente, realiza el montaje en `/mnt/enmv` del directorio `/exportaciones` del servidor asignando permiso de escritura a todos los usuarios.

### Solución — EN EL CLIENTE

**Paso 1: Instalar el software cliente NFS**
```bash
sudo apt install nfs-common
```
> **Anotación:** El cliente NFS necesita el paquete `nfs-common`. Es completamente distinto del paquete servidor `nfs-kernel-server`. El cliente no comparte nada; solo monta recursos de otros servidores.

**Paso 2: Verificar conectividad con el servidor**
```bash
ping 192.168.100.100
```
> **Anotación:** Antes de intentar cualquier montaje NFS, confirmar que hay conectividad de red básica con el servidor. Si el `ping` falla, el problema es de red, no de NFS.

**Paso 3: Consultar las exportaciones disponibles en el servidor**
```bash
showmount -e 192.168.100.100
```
> **Anotación:** `showmount -e` (export) se conecta al portmapper del servidor (puerto 111) y lista todas las carpetas que el servidor está exportando. Si aparece `/exportaciones`, el servidor está correctamente configurado. Si no aparece o da error, el servidor tiene un problema o el firewall bloquea el acceso.

**Paso 4: Crear el directorio punto de montaje**
```bash
sudo mkdir -p /mnt/enmv
sudo chmod 777 /mnt/enmv
```
> **Anotación:** El punto de montaje es la carpeta local vacía donde "aparecerá" el contenido del servidor. Debe existir antes de montar. `chmod 777` da permisos de escritura a todos los usuarios locales en el punto de montaje, para no añadir una restricción adicional por encima de los permisos NFS.

**Paso 5: Realizar el montaje temporal**
```bash
sudo mount -t nfs 192.168.100.100:/exportaciones /mnt/enmv
```
> **Anotación, argumento por argumento:**
> - `mount` — Comando para montar sistemas de archivos.
> - `-t nfs` — Tipo de sistema de archivos (NFS).
> - `192.168.100.100:/exportaciones` — Servidor (IP) y ruta remota, separados por `:`.
> - `/mnt/enmv` — Punto de montaje local donde aparecerán los archivos.
>
> Este montaje es **temporal**: desaparece cuando el sistema se reinicia. Para hacerlo permanente ver Bloque 7.

**Paso 6: Verificar que el montaje está activo**
```bash
df -h | grep nfs
```
> **Anotación:** `df -h` muestra el espacio en disco de todos los sistemas de archivos montados, en formato legible (human-readable). Si el recurso NFS aparece en la lista, el montaje es correcto y funcional. El espacio mostrado corresponde al disco del servidor, no al del cliente.

---

<a id="bloque-5"></a>
## Bloque 5: Comprobar el efecto de root_squash creando ficheros como root y como usuario

> **ENUNCIADO**
> Acceda como root y como el usuario de la máquina cliente al punto de montaje `/mnt/enmv` y agregue los ficheros `deroot2.txt` y `deusuario.txt`. Justifique los resultados.

### Solución

La configuración actual del servidor usa `*(rw,sync,no_subtree_check)` sin especificar ninguna opción de squash, lo que activa **`root_squash` por defecto** en NFS. Este es el comportamiento que se observa.

**Paso 1: Crear un archivo como usuario normal del cliente**
```bash
touch /mnt/enmv/deusuario.txt
ls -la /mnt/enmv/
```
> **Anotación:** Como usuario normal (no root), la creación funciona porque el directorio tiene permisos `777`. El archivo creado pertenecerá al UID del usuario del cliente. En el servidor, el archivo aparecerá con ese mismo UID. Si el servidor tiene un usuario con ese mismo UID, se mostrará su nombre; si no coincide ningún usuario, se mostrará el número UID.

**Paso 2: Crear un archivo como root del cliente**
```bash
sudo touch /mnt/enmv/deroot2.txt
ls -la /mnt/enmv/
```
> **Anotación — Aquí entra en juego root_squash:** Aunque en el cliente somos root, el servidor NFS "degrada" automáticamente nuestro acceso al usuario anónimo `nobody` (UID 65534). El resultado es que el archivo `deroot2.txt` se crea (porque el directorio tiene permisos `777`, que permite a `nobody` escribir), pero el propietario del archivo en el servidor es `nobody`, no `root`.

**Paso 3: Verificar en el servidor quién es el propietario de los archivos**
```bash
# En el servidor:
ls -la /exportaciones/
```
> **Anotación:** Se puede observar claramente que `deusuario.txt` pertenece al UID del usuario del cliente, mientras que `deroot2.txt` pertenece a `nobody`. Esta es la evidencia visual del efecto de `root_squash`.

**Justificación del comportamiento:**
- Con `root_squash` (comportamiento **por defecto**): El root del cliente se convierte en `nobody` en el servidor. Puede escribir solo si el directorio lo permite a `nobody`, pero no tiene privilegios especiales.
- Los usuarios normales mantienen su UID/GID original en el servidor.
- **El objetivo de `root_squash` es proteger el servidor:** Sin esta protección, cualquier máquina cliente cuyo administrador tenga root local tendría también root en el servidor NFS, pudiendo borrar, modificar o corromper cualquier archivo del servidor.

---

<a id="bloque-6"></a>
## Bloque 6: Desactivar root squashing y justificar el nuevo comportamiento

> **ENUNCIADO**
> Modifique la configuración de la exportación anterior desactivando el "root squashing". Justifique el comportamiento resultante.

### Solución — EN EL SERVIDOR

**Paso 1: Editar /etc/exports y añadir no_root_squash**
```bash
sudo nano /etc/exports
```
Cambiar la línea a:
```text
/exportaciones *(rw,sync,no_subtree_check,no_root_squash)
```
> **Anotación:** `no_root_squash` desactiva la protección `root_squash`. Con esta opción activa, el usuario root del cliente tiene privilegios de **root también en el servidor NFS**. Es una opción extremadamente peligrosa y solo se usa en casos muy específicos, como la instalación de sistemas operativos por red (PXE boot), donde el proceso instalador necesita ser root en el sistema de archivos remoto.

**Paso 2: Aplicar los cambios**
```bash
sudo exportfs -ra
```

**Paso 3: Verificar el nuevo comportamiento en el cliente**
```bash
# En el cliente:
sudo touch /mnt/enmv/deroot_sin_squash.txt
ls -la /mnt/enmv/
```
> **Anotación:** Ahora el archivo creado como root pertenecerá a `root` (UID 0) en el servidor, no a `nobody`. Esto significa que el cliente tiene control total sobre el servidor: podría borrar archivos de sistema, modificar configuraciones del servidor, etc. Por eso `no_root_squash` es tan peligroso.

**Justificación comparativa:**
- Con `root_squash` (defecto): root del cliente → `nobody` (UID 65534) en el servidor.
- Con `no_root_squash`: root del cliente → `root` (UID 0) en el servidor.
- La diferencia es visible en la columna de propietario de `ls -la`: antes era `nobody`, ahora es `root`.

---

<a id="bloque-7"></a>
## Bloque 7: Configurar el montaje automático en cada inicio del sistema vía fstab

> **ENUNCIADO**
> Modifique la configuración del cliente para que el montaje de `/exportaciones` del servidor en `/mnt/enmv` se realice automáticamente en cada inicio del sistema.

### Solución — EN EL CLIENTE

**Paso 1: Abrir el fichero /etc/fstab**
```bash
sudo nano /etc/fstab
```
> **Anotación:** `/etc/fstab` (File System TABle) es el archivo que lee el sistema operativo durante el arranque para montar automáticamente los sistemas de archivos. Es uno de los archivos más críticos del sistema: un error de sintaxis aquí puede impedir que el equipo arranque correctamente. Por eso siempre se hace backup antes de editarlo y se verifica con `mount -a` antes de reiniciar.

**Paso 2: Añadir la línea al final del archivo**
```text
192.168.100.100:/exportaciones  /mnt/enmv  nfs  defaults  0  0
```
> **Anotación, campo por campo (separados por tabuladores o espacios):**
> - `192.168.100.100:/exportaciones` — Dispositivo/origen: IP del servidor y ruta del recurso remoto.
> - `/mnt/enmv` — Punto de montaje local donde se montará el recurso.
> - `nfs` — Tipo de sistema de archivos.
> - `defaults` — Conjunto de opciones estándar de montaje (rw, suid, exec, auto, nouser, async). Para personalizar se especifican opciones separadas por comas: ej. `nfs,defaults,_netdev`.
> - `0` (primer cero) — No realizar volcado de seguridad automático (`dump`). Para NFS siempre 0 porque hacer dump de un recurso de red no tiene sentido.
> - `0` (segundo cero) — No ejecutar `fsck` al arrancar. Para NFS siempre 0 porque `fsck` es para discos físicos locales, no recursos de red.

**Paso 3: Verificar sin reiniciar**
```bash
sudo umount /mnt/enmv
sudo mount -a
```
> **Anotación — Por qué hacer esto antes de reiniciar:** `mount -a` lee el fstab e intenta montar todo lo que encuentra ahí. Si hay un error en la línea añadida (IP incorrecta, ruta incorrecta, servidor no disponible), el comando reporta el error inmediatamente, sin reiniciar. Si se reiniciara sin verificar y hubiera un error, el sistema podría quedar en un estado inconsistente o tardar mucho en arrancar intentando montar un recurso que no existe.

**Paso 4: Verificar que el montaje automático funciona**
```bash
df -h | grep nfs
```
> El recurso NFS debe aparecer en la lista. Al reiniciar el sistema, se montará automáticamente sin intervención.

---

<a id="bloque-8"></a>
## Bloque 8: NFSv4 — Exportar `/home` del servidor como pseudo sistema de archivos unificado

> **ENUNCIADO**
> La versión NFSv4 permite montar todas las exportaciones de un servidor como un pseudo sistema de ficheros. Investiga cómo funciona y realiza la exportación conjunta de `/home` del servidor.

### Solución

NFSv4 introduce el concepto de **árbol de exportaciones unificado**: en lugar de exportar carpetas individuales independientes, se define un directorio raíz con el parámetro especial `fsid=0` y todas las exportaciones cuelgan de él como ramas de un árbol. El cliente solo necesita montar la raíz (`/`) y puede navegar hacia todos los subdirectorios automáticamente.

**Ventaja principal:** el cliente monta `IP:/` (raíz virtual) en lugar de `IP:/ruta/real`. La estructura real de directorios del servidor queda oculta, lo que mejora la seguridad.

---

**SERVIDOR:**

**Paso 1: Crear la estructura del árbol de exportaciones**
```bash
sudo mkdir -p /exports
sudo mkdir -p /exports/home
```
> **Anotación:** `/exports` será la raíz virtual del árbol NFSv4. Dentro de ella se crean los puntos donde se "engancharán" los directorios reales del servidor.

**Paso 2: Hacer un bind mount para incluir /home en el árbol**
```bash
sudo mount --bind /home /exports/home
```
> **Anotación:** `mount --bind` monta un directorio existente en otro punto del árbol de directorios. No mueve ni copia nada: `/home` y `/exports/home` apuntan exactamente al mismo contenido. Cualquier cambio en uno se refleja instantáneamente en el otro. Este mecanismo permite incluir `/home` en el árbol NFSv4 sin moverlo físicamente.

**Paso 3: Hacer el bind mount permanente (para que sobreviva reinicios)**
```bash
sudo nano /etc/fstab
```
Añadir:
```text
/home  /exports/home  none  bind  0  0
```

**Paso 4: Configurar /etc/exports para NFSv4**
```bash
sudo nano /etc/exports
```
```text
/exports       192.168.100.0/24(rw,sync,fsid=0,crossmnt,no_subtree_check)
/exports/home  192.168.100.0/24(rw,sync,no_subtree_check)
```
> **Anotación, parámetros clave de NFSv4:**
> - `fsid=0` — Marca esta carpeta como el "kilómetro cero" de las exportaciones NFSv4. Solo puede haber un `fsid=0` en todo el servidor. Cuando el cliente monta `IP:/`, el servidor le da esta carpeta como raíz.
> - `crossmnt` — "Cross mount". Permite que el cliente "salte" de la raíz a los subdirectorios exportados de forma completamente transparente, sin tener que montar cada subdirectorio por separado.
> - `/exports/home` se exporta como subdirectorio del árbol, sin necesitar `fsid` especial.

**Paso 5: Aplicar los cambios**
```bash
sudo exportfs -ra
```

---

**CLIENTE:**

**Paso 6: Crear punto de montaje y montar la raíz NFSv4**
```bash
sudo mkdir -p /mnt/nfs4
sudo mount -t nfs4 192.168.100.100:/ /mnt/nfs4
```
> **Anotación:**
> - `-t nfs4` especifica NFSv4 explícitamente.
> - `IP:/` monta la raíz virtual del árbol, no una ruta real del servidor.
> - Al montar la raíz, el cliente puede acceder a `/mnt/nfs4/home` automáticamente gracias a `crossmnt`.
> - El cliente no sabe que en el servidor el directorio real es `/exports/home`: solo ve `/mnt/nfs4/home`.

**Paso 7: Verificar el acceso**
```bash
ls /mnt/nfs4/
ls /mnt/nfs4/home/
```
> Debe aparecer el directorio `home` con los directorios de usuario del servidor, todo accesible desde un único punto de montaje.
