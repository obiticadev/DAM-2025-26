# Sistemas Informáticos — Tema 11: Servicios de Red
## Documento completo para estudio en audio (optimizado para NotebookLM)
### Netplan · NFS · Samba · SSH

---

## Introducción: Por qué existen estos cuatro servicios

Imagina una empresa con veinte ordenadores. Cada uno tiene su propio disco duro con sus propios archivos. Si un empleado quiere enviarle un documento a un compañero, tiene que sacarlo en un USB, ir al otro puesto y copiarlo manualmente. Esto es lo que había antes de los servicios de red de archivos, y es exactamente el problema que vienen a resolver NFS y Samba.

Pero antes de poder hablar de compartir archivos, los ordenadores tienen que poder encontrarse en la red. Para eso existe Netplan, que es la herramienta que configura cómo un ordenador Ubuntu obtiene su dirección en la red, ya sea preguntándosela a un servidor automático o con una dirección fija que el administrador le asigna manualmente.

Una vez que los ordenadores se encuentran en la red y comparten archivos, surge otra necesidad: administrarlos desde lejos. No siempre puedes ir físicamente a cada servidor a arreglarlo. Para eso existe SSH, que permite conectarte al terminal de otro ordenador desde el tuyo, como si estuvieras sentado delante de él, pero a través de un canal completamente cifrado y seguro.

Estos cuatro servicios, Netplan, NFS, Samba y SSH, forman el núcleo básico de la administración de una red Linux. No son opcionales en entornos reales. Son las herramientas que usa un administrador de sistemas cada día.

---

## Parte 1: Netplan — Cómo le decimos a Ubuntu dónde está en la red

### Qué es Netplan y por qué existe

Cuando Ubuntu arranca, sus interfaces de red están vacías. No saben si son el ordenador número cinco o el número cien de la red. Alguien tiene que decirles cuál es su dirección IP, cuál es la puerta de enlace para salir a internet, y qué servidor DNS deben usar para convertir nombres como "google.com" en direcciones.

Antes de la versión 17.04 de Ubuntu, todo eso se configuraba en un archivo llamado `/etc/network/interfaces`. Era funcional pero arcaico. Desde Ubuntu 17.04, la herramienta oficial para configurar la red es Netplan, que usa un formato de archivo llamado YAML.

El formato YAML es como JSON pero más legible para humanos. En lugar de llaves y corchetes, usa espacios e indentación para estructurar los datos. Es importante entender que los espacios en YAML son sagrados: si metes un espacio de más o de menos, el archivo deja de funcionar.

### Dónde vive la configuración de Netplan

Los archivos de configuración de Netplan están en el directorio `/etc/netplan/`. Dentro de ese directorio puede haber uno o varios archivos con extensión `.yaml`. En servidores suele llamarse `01-netcfg.yaml`. En entornos de escritorio suele llamarse `01-network-manager-all.yaml`. En instancias de nube, lo más común es `50-cloud-init.yaml`.

El número al principio del nombre (01, 50…) define el orden de lectura cuando hay varios archivos. El que tiene el número más bajo se procesa primero.

### Los dos motores que hay detrás: NetworkManager y networkd

Netplan no gestiona la red directamente por sí mismo. Es una especie de capa de configuración que delega el trabajo a uno de dos motores. Estos motores se llaman "renderers" o backends.

El primero es `networkd`, que es el motor de systemd. Es minimalista, muy fiable, y es el que se usa en servidores y en entornos sin entorno gráfico.

El segundo es `NetworkManager`, que es más sofisticado y pensado para entornos de escritorio. Tiene una interfaz gráfica, gestiona de forma inteligente el cambio entre redes WiFi, etc. Es el que se usa cuando installas Ubuntu Desktop.

En los archivos YAML, el renderer se especifica con la línea `renderer: networkd` o `renderer: NetworkManager`.

### Cómo se configura una IP dinámica con DHCP

Cuando queremos que el ordenador obtenga su IP automáticamente del router o del servidor DHCP de la red, la configuración es muy sencilla. El archivo solo necesita indicar el nombre de la interfaz de red y la opción `dhcp4: true`.

Un ejemplo típico tendría este aspecto: se abre con `network:`, en la siguiente línea con dos espacios de sangría se pone `version: 2`, luego `renderer: networkd`, luego `ethernets:`, y dentro de esa sección el nombre de la interfaz de red (por ejemplo `enp3s0`) con la opción `dhcp4: true`. Los nombres de interfaz como `enp3s0` o `enp0s3` son generados automáticamente por el kernel de Linux y varían según el hardware.

### Cómo se configura una IP estática

Cuando queremos que el ordenador tenga siempre la misma dirección IP, usamos configuración estática. Esto es fundamental para los servidores, porque los clientes tienen que saber de antemano a qué dirección conectarse, y no pueden estar buscando cada vez dónde se ha movido el servidor.

En la configuración estática, en lugar de `dhcp4: true`, se especifican cuatro cosas: la dirección IP con su máscara de red en notación CIDR (por ejemplo `10.10.10.2/24`), la puerta de enlace predeterminada con `gateway4` (que es la IP del router), y los servidores DNS con `nameservers`. La dirección se escribe bajo la clave `addresses`, que es una lista, por eso lleva un guion antes de la IP.

El sufijo `/24` después de la IP significa que los primeros 24 bits de la dirección identifican la red. En una red de tipo `/24`, pueden existir hasta 254 equipos distintos. Es la configuración más común en redes locales pequeñas y medianas.

### Cómo se aplican los cambios

Netplan tiene dos comandos para aplicar cambios. El primero es `netplan try`, que prueba la configuración. Si el sistema detecta que la nueva configuración rompe la conectividad de red y no recibes confirmación en 120 segundos, revierte automáticamente a la configuración anterior. Esto es extremadamente útil cuando estás administrando un servidor remoto por SSH: si la nueva config te desconecta, el sistema se arregla solo y puedes volver a conectarte.

El segundo comando es `netplan apply`, que aplica los cambios de forma permanente e inmediata, sin red de seguridad.

En un entorno profesional, siempre se usa primero `netplan try` para verificar, y solo si todo funciona correctamente se confirma con Enter o se usa `netplan apply`.

### Cómo diagnosticamos la red con el comando `ip`

El comando `ip` es la herramienta principal de diagnóstico de red en Linux moderno. Reemplaza a comandos más antiguos como `ifconfig`, que ya está obsoleto.

Con `ip address`, o su versión corta `ip ad`, vemos todas las interfaces de red del sistema con sus direcciones IP asignadas. Con `ip address show` seguido del nombre de una interfaz concreta, vemos solo esa interfaz.

Con `ip route`, o `ip ro`, vemos la tabla de enrutamiento del sistema. Ahí aparece la puerta de enlace predeterminada, que es el camino que siguen los paquetes cuando van hacia fuera de la red local.

Con `ip route get` seguido de una IP de destino, el sistema nos dice exactamente qué camino usaría para llegar a esa dirección. Es muy útil para depurar problemas de conectividad.

Con `ip link set` seguido del nombre de la interfaz y la opción `up` o `down`, activamos o desactivamos una interfaz de red respectivamente.

Con `ip -s link show` seguido de la interfaz, vemos estadísticas de tráfico: cuántos paquetes se han enviado y recibido, cuántos errores ha habido, etc.

Con `ip neighbour`, o `ip neigh`, vemos la tabla ARP. La tabla ARP guarda las asociaciones entre direcciones IP y direcciones MAC (la dirección física del hardware de red). Cuando un equipo quiere enviar un paquete a otro en la misma red local, primero consulta esta tabla para saber a qué dirección MAC enviarlo.

---

## Parte 2: NFS — Compartir carpetas entre sistemas Linux

### Qué es NFS y qué problema resuelve

NFS son las siglas de Network File System, que en español significa Sistema de Archivos de Red. Fue desarrollado por Sun Microsystems en 1984 y desde entonces se ha convertido en el estándar de facto para compartir carpetas entre sistemas Unix y Linux.

La idea central de NFS es elegante: un servidor Linux tiene una carpeta en su disco duro. Gracias a NFS, esa carpeta puede "aparecer" en el sistema de archivos de otro Linux como si fuera una carpeta local. El cliente no sabe ni le importa que esa carpeta está físicamente en otro ordenador. Para él es transparente: puede crear archivos, borrarlos, leerlos, exactamente igual que si estuvieran en su propio disco duro.

Esto tiene aplicaciones enormes en entornos de producción. Imagina un clúster de veinte servidores web que todos necesitan acceder a los mismos archivos de la aplicación. En lugar de duplicar esos archivos en los veinte servidores, se guardan en un servidor de archivos NFS y los veinte servidores los montan de forma remota. Cuando hay que actualizar un archivo, solo se actualiza en un sitio y todos los servidores ven el cambio instantáneamente.

### Cómo funciona NFS por dentro: el protocolo RPC

NFS no funciona de forma independiente. Se apoya en un mecanismo llamado RPC, que son las siglas de Remote Procedure Call o Llamada a Procedimiento Remoto.

La idea del RPC es que un programa en el ordenador cliente puede ejecutar una función que realmente está en el servidor, de forma transparente. Por ejemplo, cuando el cliente hace una operación de "leer archivo", el sistema operativo convierte esa operación en una llamada RPC que viaja por la red al servidor, el servidor ejecuta la lectura real y devuelve los datos. Todo esto ocurre de forma transparente para la aplicación.

Para que esto funcione, hay un proceso especial llamado portmapper o rpcbind que escucha en el puerto 111. Su trabajo es llevar un registro de qué servicios RPC están disponibles y en qué puertos. Cuando el cliente NFS quiere conectarse, primero pregunta al portmapper cuál es el puerto del servidor NFS.

El servidor NFS en sí mismo usa el puerto 2049, tanto para TCP como para UDP. Este es el puerto principal del protocolo NFS.

### NFSv3 vs NFSv4: las diferencias importantes

Existen varias versiones del protocolo NFS. Las más relevantes para el examen son la versión 3 y la versión 4.

NFS versión 3 y anteriores son lo que se llama "stateless" en inglés, o sin estado en español. Esto significa que el servidor no guarda información sobre qué clientes están conectados ni qué archivos tienen abiertos. Cada petición que llega al servidor es tratada de forma independiente. Esto tiene ventajas, como la sencillez y la capacidad de recuperarse de caídas sin que los clientes pierdan el estado, pero también desventajas: es menos eficiente para operaciones que requieren coherencia.

Además, NFS versión 3 usa múltiples puertos dinámicos además del 2049. Esto hace muy difícil configurar firewalls correctamente, porque los puertos cambian.

NFS versión 4 es "stateful", con estado. El servidor sí mantiene información sobre las conexiones activas. Esto permite mejores optimizaciones de rendimiento y mejor soporte para bloqueo de archivos. Pero lo más importante para la administración práctica es que NFSv4 consolida toda la comunicación en un único puerto: el 2049. Solo necesita además el puerto 111 del portmapper. Esto simplifica enormemente la configuración de firewalls.

NFSv4 también introduce el concepto de árbol de exportaciones, que permite organizar todos los recursos compartidos bajo un único punto raíz, algo que veremos en detalle más adelante.

En cuanto a seguridad, NFS en todas sus versiones basa la autenticación en la dirección IP del host que se conecta. No hay usuarios ni contraseñas como en Samba. Si una IP está autorizada en el servidor, esa máquina puede acceder. Esto lo hace apropiado para redes locales internas de confianza, pero no para acceso desde internet.

### Instalación del servidor y del cliente

Para instalar el servidor NFS en Ubuntu o Debian, se ejecuta el comando `sudo apt install nfs-kernel-server`. Tras la instalación, el servicio se llama `nfs-kernel-server` y se gestiona con systemctl. Para comprobar que está activo, se usa `sudo systemctl status nfs-kernel-server`.

Para el cliente, solo hace falta instalar el paquete `nfs-common` con `sudo apt install nfs-common`. Este paquete proporciona las herramientas necesarias para montar sistemas de archivos NFS desde el cliente.

Para configurar el firewall y permitir que los clientes de la red local accedan al servidor NFS, se usa el comando `sudo ufw allow from 192.168.100.0/24` para permitir tráfico de toda la subred, o de forma más específica `sudo ufw allow from 192.168.100.0/24 to any port nfs` para permitir solo el tráfico al puerto NFS.

Para comprobar qué servicios RPC están registrados y funcionando en el servidor, se usa el comando `rpcinfo -p`.

### El archivo `/etc/exports`: el corazón de la configuración del servidor

El archivo `/etc/exports` es donde el administrador define qué carpetas exporta el servidor y a quién las exporta. Es el archivo de configuración más importante del servidor NFS.

Antes de tocarlo, siempre se hace una copia de seguridad con el comando `sudo cp /etc/exports /etc/exports.bak`. Esta práctica es fundamental: si cometemos un error en la configuración, podemos restaurar el archivo original simplemente copiando el backup.

La estructura de cada línea en el archivo es simple pero precisa. Primero va la ruta de la carpeta que queremos exportar, luego un espacio, luego la dirección del cliente o de la red que puede acceder, y finalmente, pegado sin espacios al cliente, entre paréntesis, las opciones de acceso.

Por ejemplo, para exportar la carpeta `/nfs/lectura` a toda la red `192.168.100.0/24` en modo de solo lectura y con escritura sincronizada, la línea sería: `/nfs/lectura 192.168.100.0/24(ro,sync,no_subtree_check)`.

La notación `192.168.100.0/24` significa "cualquier equipo cuya IP empiece por 192.168.100". El asterisco `*` en lugar de una IP o red significa "cualquier cliente", lo que sería totalmente abierto y se usaría solo en entornos muy controlados.

### La trampa del espacio en `/etc/exports`: el detalle de examen más importante

Hay un detalle crítico en la sintaxis de `/etc/exports` que aparece frecuentemente en los exámenes y que confunde a muchos estudiantes. Es la diferencia entre tener o no un espacio antes del paréntesis de opciones.

Si escribimos `/nfs/lectura 192.168.100.5(rw)`, la interpretación es: la IP `192.168.100.5` puede acceder con permisos de lectura y escritura.

Pero si escribimos `/nfs/lectura 192.168.100.5 (rw)` — con un espacio entre la IP y el paréntesis — la interpretación cambia radicalmente. El sistema entiende que a `192.168.100.5` se le aplican los permisos por defecto (que son solo lectura, `ro`), y que las opciones del paréntesis `(rw)` se aplican a cualquier otro cliente del mundo. Es decir, el espacio convierte la IP en un cliente sin opciones y el paréntesis en opciones para todos los demás.

Este es uno de los errores más peligrosos en la configuración NFS porque puede resultar en que todo internet tenga permisos de escritura en tu servidor sin que lo pretendas.

### Las opciones de exportación: qué significa cada una

Las opciones entre paréntesis controlan exactamente qué puede hacer el cliente con la carpeta exportada.

La opción `ro` significa "read only" o solo lectura. Es el valor por defecto. El cliente puede ver y copiar archivos, pero no puede crear, modificar ni eliminar nada.

La opción `rw` significa "read write" o lectura y escritura. El cliente puede hacer cualquier operación con los archivos.

La opción `sync` significa que el servidor no confirma que una escritura se ha completado hasta que los datos están físicamente guardados en el disco. Es más lenta pero completamente segura. Si el servidor se apaga inesperadamente, los datos no se pierden ni se corrompen.

La opción `async` es lo contrario. El servidor confirma la escritura antes de que los datos lleguen físicamente al disco. Esto es más rápido, pero si el servidor falla en el momento equivocado, los datos pueden corromperse o perderse. Solo se usa cuando la velocidad es más importante que la integridad de los datos.

La opción `no_subtree_check` desactiva una comprobación de seguridad que el servidor hace para verificar que el archivo al que accede el cliente realmente está dentro de la carpeta exportada. Esta comprobación causa problemas de rendimiento y de fiabilidad cuando los archivos dentro de la carpeta exportada cambian de número de inodo (lo que ocurre en muchas operaciones normales). La recomendación actual es siempre incluir `no_subtree_check`.

La opción `root_squash` es el comportamiento por defecto en NFS, aunque muchos administradores la incluyen explícitamente para dejar claro que está activa. Lo que hace es "degradar" al usuario root del cliente. Cuando el sistema operativo del cliente es root e intenta hacer algo en la carpeta NFS, el servidor no lo trata como root sino como el usuario anónimo `nobody`. Esto es una medida de seguridad crucial: sin ella, cualquier equipo que tuviera acceso NFS y root local podría hacer lo que quisiera en el servidor.

La opción `no_root_squash` hace lo contrario: el root del cliente es root en el servidor también. Es extremadamente peligrosa y solo se usa en casos muy específicos como la instalación de sistemas operativos por red.

La opción `all_squash` va más lejos que `root_squash`. No solo degrada al root sino a absolutamente todos los usuarios. Cualquier operación que llegue del cliente, independientemente del usuario que la envíe, será tratada por el servidor como si viniera del usuario anónimo `nobody`. Esto es útil para carpetas públicas donde no importa quién escribe porque todos son anónimos.

Las opciones `anonuid` y `anongid` permiten especificar exactamente qué usuario y grupo de Linux representa el usuario anónimo cuando se usa squash. Por ejemplo, `anonuid=1002,anongid=1002` hace que todas las operaciones anónimas se ejecuten como el usuario de Linux con UID 1002.

### Los comandos del servidor NFS

Una vez editado el archivo `/etc/exports`, hay que decirle al servidor que lo vuelva a leer. Para eso se usa el comando `sudo exportfs -ra`. La opción `-r` significa "re-exportar" (releer el archivo), y la opción `-a` significa "all" o todos. Este comando es fundamental porque permite aplicar cambios de configuración sin reiniciar el servicio NFS, lo que sería mucho más disruptivo.

Para ver qué carpetas está exportando actualmente el servidor, se usa el comando `exportfs` sin opciones, o `exportfs -s` para ver las opciones detalladas de cada exportación.

Desde el cliente, para saber qué está ofreciendo un servidor NFS antes de intentar montar, se usa el comando `showmount -e` seguido de la IP del servidor. Este comando conecta al servidor y lista todas sus exportaciones disponibles junto con las opciones de acceso.

### El proceso completo para configurar el servidor NFS paso a paso

El proceso para poner en marcha un servidor NFS desde cero sigue siempre el mismo orden. Primero se instala el paquete `nfs-kernel-server`. Luego se crean los directorios físicos que vamos a exportar con `sudo mkdir -p` seguido de las rutas. Si alguno de esos directorios va a ser de escritura, hay que dar permisos a nivel del sistema operativo Linux con `chmod 777` o con `chown` para asignar propietario y grupo. Después se edita el archivo `/etc/exports` con `sudo nano /etc/exports` y se añaden las líneas correspondientes a cada exportación. Finalmente se aplican los cambios con `sudo exportfs -ra` y se verifica con `exportfs -s`.

Es muy importante el orden: primero crear los directorios físicamente, luego configurar las exportaciones. Si el directorio no existe cuando el servicio NFS intenta exportarlo, el montaje en el cliente fallará.

### El proceso completo para configurar el cliente NFS paso a paso

En el cliente, el proceso también tiene un orden claro. Primero se instala `nfs-common`. Luego se crean los puntos de montaje, que son las carpetas vacías donde "aparecerán" los recursos del servidor. Normalmente se crean dentro de `/mnt/nfs/` con `sudo mkdir -p`.

Para hacer un montaje temporal, se usa el comando `sudo mount -t nfs` seguido de la IP del servidor, dos puntos, la ruta remota, y finalmente la ruta local del punto de montaje. Por ejemplo: `sudo mount -t nfs 192.168.88.100:/nfs/lectura /mnt/nfs/lectura`. Este montaje desaparece cuando el sistema se reinicia.

Para verificar que el montaje funciona, se usa `df -h | grep nfs`. El comando `df -h` muestra el espacio en disco de todos los sistemas de archivos montados, y con `grep nfs` filtramos solo los que son de tipo NFS. Si aparecen en la lista, el montaje está activo y funcionando.

Para hacer un montaje permanente que sobreviva a los reinicios, hay que editar el archivo `/etc/fstab`. Este archivo es el registro que lee Linux durante el arranque para montar automáticamente los sistemas de archivos. La línea que hay que añadir para NFS tiene este formato: primero la IP del servidor y la ruta remota separadas por dos puntos, luego la ruta local del punto de montaje, luego el tipo de sistema de archivos que es `nfs`, luego la palabra `defaults`, y finalmente dos ceros separados por espacios.

Los dos ceros finales tienen un significado específico. El primer cero indica que no se debe hacer un volcado de seguridad automático de este sistema de archivos. El segundo cero indica que `fsck`, el programa que comprueba la integridad de los discos, no debe comprobar este sistema de archivos al arrancar. Ambos son ceros para los recursos NFS porque no son discos físicos: intentar hacer un fsck en un recurso de red no tiene sentido y causaría errores.

Después de editar fstab, para verificar que la configuración es correcta sin reiniciar el sistema, primero se desmonta manualmente el recurso con `sudo umount /mnt/nfs/squash` y luego se ejecuta `sudo mount -a`. El comando `mount -a` lee el fstab e intenta montar todo lo que encuentra ahí. Si hay un error de sintaxis o el servidor no está disponible, el comando lo reportará inmediatamente, permitiendo corrección antes de que el sistema arranque con esa configuración incorrecta.

### NFSv4: el árbol de exportaciones con fsid=0

NFSv4 introduce una funcionalidad muy elegante que no existe en versiones anteriores: el árbol de exportaciones unificado. En lugar de exportar carpetas independientes, el servidor define una carpeta raíz y todas las exportaciones cuelgan de ella como ramas de un árbol.

Para definir esta raíz, se usa el parámetro especial `fsid=0`. El número cero es el identificador reservado para el punto raíz. Solo puede haber un `fsid=0` en todo el servidor.

Además del `fsid=0`, la carpeta raíz necesita el parámetro `crossmnt`, que viene de "cross mount" o "cruzar montajes". Este parámetro es lo que permite que el cliente, una vez que monta la raíz, pueda navegar transparentemente hacia las subcarpetas sin necesidad de montar cada una por separado.

En la práctica, la configuración del servidor en `/etc/exports` quedaría así: primero una línea para la raíz `/exports` con las opciones `rw,sync,fsid=0,crossmnt,no_subtree_check`, y luego líneas para cada subdirectorio como `/exports/Datos` con `rw,sync,no_subtree_check` y `/exports/Pub` con `ro,sync,no_subtree_check`.

El cliente, en lugar de montar `IP:/exports/Datos` e `IP:/exports/Pub` por separado, simplemente monta `IP:/` y desde ahí puede acceder a `Datos` y `Pub` como subdirectorios. La gran ventaja de esto es que la estructura real de carpetas del servidor queda oculta para el cliente. El cliente ve una raíz virtual, no sabe que físicamente es `/exports` en el servidor. Esto añade una capa de seguridad.

El comando de montaje en el cliente usa `nfs4` como tipo de sistema de archivos en lugar de `nfs`: `sudo mount -t nfs4 192.168.88.100:/ /mnt/nfs4`.

---

## Parte 3: Samba — Compartir en redes donde conviven Windows y Linux

### Qué es Samba y por qué es diferente de NFS

Samba es la solución cuando en la red hay una mezcla de sistemas operativos: Windows, Linux y macOS. Mientras que NFS es una tecnología exclusiva del mundo Unix y Linux, Samba implementa el protocolo SMB (Server Message Block), que es el protocolo nativo de Windows para compartir archivos, impresoras y otros recursos en red.

La historia es interesante: Microsoft desarrolló SMB para que los ordenadores Windows pudieran comunicarse entre ellos. Samba es una implementación libre y de código abierto de ese mismo protocolo, desarrollada para que Linux pudiera hablar el mismo idioma que Windows. El resultado es que desde un ordenador Windows se puede acceder a una carpeta en un servidor Linux con Samba exactamente igual que si fuera un servidor Windows. No hay diferencia visible para el usuario.

El protocolo SMB ha tenido varias versiones. SMBv1 es la versión original y es notoriamente insegura: fue la vulnerabilidad explotada por el ransomware WannaCry en 2017. En instalaciones modernas siempre se configura SMBv2 como versión mínima.

CIFS (Common Internet File System) es básicamente un sinónimo antiguo de SMB. Cuando ves referencias a "cifs-utils" en Linux, se refiere a las herramientas de cliente para trabajar con recursos SMB/Samba.

### Los dos demonios: smbd y nmbd

Samba funciona con dos programas que corren en segundo plano, llamados demonios. Entender qué hace cada uno es importante.

El primero es `smbd`, que gestiona todo lo relacionado con la compartición de archivos e impresoras, y también la autenticación de usuarios. Escucha en los puertos TCP 139 y TCP 445. El puerto 445 es el moderno y el que usa SMBv2 y v3. El puerto 139 existe por compatibilidad con versiones antiguas de NetBIOS.

El segundo es `nmbd`, que gestiona la resolución de nombres NetBIOS. NetBIOS es el sistema de nombres que usa Windows para que los equipos aparezcan con nombres amigables en el "Entorno de red" o "Red" de Windows Explorer. `nmbd` permite que el servidor Samba aparezca con su nombre de equipo en lugar de solo por IP. Escucha en los puertos UDP 137 y 138.

Ambos demonios se gestionan con systemctl. Para iniciarlos: `sudo systemctl start smbd nmbd`. Para habilitarlos y que arranquen automáticamente con el sistema: `sudo systemctl enable smbd nmbd`. Para recargar la configuración sin cortar las conexiones activas: `sudo systemctl reload smbd nmbd`. Esta última opción es mucho mejor que reiniciar, porque si hay usuarios conectados en ese momento, el reinicio les cortaría la sesión.

### Instalación de Samba

Para instalar Samba en Ubuntu o Debian, se usa el comando `sudo apt install samba smbclient cifs-utils`. El paquete `samba` instala el servidor. `smbclient` instala el cliente de línea de comandos para probar conexiones. `cifs-utils` proporciona las herramientas necesarias para montar recursos Samba en Linux.

Para el firewall, UFW tiene un perfil predefinido para Samba que abre automáticamente todos los puertos necesarios. Se activa con `sudo ufw allow Samba`.

Para verificar si Samba está instalado, se puede usar `dpkg -l | grep samba`, que lista todos los paquetes instalados relacionados con Samba.

### El archivo smb.conf: la configuración central de Samba

Toda la configuración de Samba vive en el archivo `/etc/samba/smb.conf`. Este archivo se divide en secciones. Cada sección se identifica por un nombre entre corchetes. Hay secciones especiales con significado reservado y secciones personalizadas para cada recurso compartido.

Como siempre al tocar archivos de configuración importantes, lo primero es hacer una copia de seguridad: `sudo cp /etc/samba/smb.conf /etc/samba/smb.conf.bak`.

Después de cualquier modificación, hay que verificar que no hay errores de sintaxis antes de aplicar los cambios. Para eso existe el comando `testparm -s`. Este comando analiza el archivo smb.conf, reporta cualquier error y muestra la configuración resultante. Si hay algún error en la sintaxis, `testparm` lo señalará con exactitud. Es absolutamente imprescindible ejecutarlo antes de reiniciar el servicio.

### La sección `[global]`: configuración general del servidor

La sección `[global]` contiene los parámetros que afectan al comportamiento global del servidor Samba, no a un recurso compartido concreto.

El parámetro `workgroup` define el nombre del grupo de trabajo. En redes Windows domésticas y de pequeña empresa, el valor predeterminado suele ser `WORKGROUP`. Este nombre debe coincidir con el que tienen configurado los clientes Windows para que se puedan ver en la red.

El parámetro `netbios name` define el nombre con el que el servidor aparece en la red. Puede ser como máximo 15 caracteres. Es el nombre que ven los usuarios de Windows en el Explorador de red.

El parámetro `server string` es la descripción del servidor que aparece cuando alguien lo selecciona en el entorno de red. Puede usar variables como `%h`, que se sustituye automáticamente por el hostname del servidor.

El parámetro `security = user` es el modo de seguridad más común. Significa que para acceder a cualquier recurso compartido hay que proporcionar un usuario y contraseña válidos. Este es el modo que simula el comportamiento de un servidor Windows en un dominio.

El parámetro `map to guest = bad user` complementa al anterior. Cuando alguien intenta conectarse con un nombre de usuario que no existe en Samba, en lugar de rechazar directamente la conexión, el sistema lo trata como un "invitado" o guest. Esto permite tener recursos públicos accesibles sin cuenta mientras otros recursos permanecen protegidos.

El parámetro `interfaces` define en qué interfaces de red escucha Samba. Si se quiere que solo escuche en la red local y no en internet, se especifican solo las interfaces locales.

El parámetro `bind interfaces only = yes` complementa al anterior: Samba rechazará conexiones que lleguen por interfaces no listadas.

El parámetro `hosts allow` permite listar las IPs o rangos de red que pueden conectarse. Combinado con `hosts deny = ALL`, crea una lista blanca: solo los hosts explícitamente permitidos pueden conectarse.

El parámetro `server min protocol = SMB2` es una medida de seguridad fundamental. Hace que Samba rechace conexiones usando SMBv1, que es la versión vulnerable. Nunca debería faltar en una configuración de producción.

El parámetro `passdb backend = tdbsam` define cómo almacena Samba la base de datos de contraseñas de usuarios. `tdbsam` es el backend local más común para servidores de pequeña y mediana escala.

### Las secciones especiales: `[homes]` y `[printers]`

La sección `[homes]` tiene un comportamiento mágico: cuando un usuario se conecta al servidor Samba con su nombre de usuario, Samba crea automáticamente un recurso compartido con ese nombre que apunta a su directorio home en Linux. Es decir, si el usuario "juan" se conecta, Samba le presenta un recurso compartido llamado "juan" que es su carpeta `/home/juan`. Esto es muy cómodo para dar a cada usuario acceso a su espacio personal sin tener que crear una sección en smb.conf por cada usuario.

La sección `[printers]` hace lo análogo para impresoras: comparte automáticamente todas las impresoras que tenga instaladas el servidor Linux.

### Los parámetros de los recursos compartidos

Cada recurso compartido (share) tiene su propia sección en smb.conf con el nombre que se quiera darle. Dentro de esa sección hay una serie de parámetros que controlan el comportamiento.

El parámetro `path` es el único estrictamente obligatorio. Indica la ruta real en el disco del servidor donde están los archivos.

El parámetro `browseable` controla si el recurso aparece cuando un cliente lista los recursos disponibles en el servidor. Con `browseable = yes`, el recurso es visible en la lista. Con `browseable = no`, el recurso existe y es accesible si escribes la ruta, pero no aparece en la lista. Esto se usa para recursos que quieres que solo accedan quienes ya saben que existen, como el recurso `[homes]` que normalmente está configurado como `browseable = no`.

El parámetro `read only` controla si se puede escribir en el recurso. `read only = yes` significa solo lectura. `read only = no` significa lectura y escritura. El parámetro `writable = yes` es exactamente lo mismo que `read only = no`, es un sinónimo.

El parámetro `guest ok = yes` permite el acceso sin contraseña, como invitado. Se usa para recursos públicos. También puede escribirse como `public = yes`.

El parámetro `valid users` es una lista de usuarios o grupos que pueden acceder al recurso. Solo los que estén en esta lista pueden entrar. Los grupos se especifican con un `@` delante del nombre del grupo. Por ejemplo, `valid users = ana, @marketing` permite acceso al usuario `ana` y a todos los miembros del grupo `marketing`.

El parámetro `invalid users` hace lo contrario: lista los usuarios que definitivamente no pueden acceder, aunque cumplieran otras condiciones. Tiene prioridad sobre `valid users`: si un usuario aparece en ambas listas, se le niega el acceso.

El parámetro `write list` permite especificar usuarios o grupos que pueden escribir en el recurso incluso aunque el recurso tenga `read only = yes`. Esto permite crear recursos de solo lectura general donde ciertos usuarios privilegiados sí pueden modificar cosas.

El parámetro `read list` hace lo contrario: usuarios que solo pueden leer aunque el recurso sea de escritura.

El parámetro `force user` hace que todos los archivos que se creen en ese recurso, independientemente del usuario que los crea, pertenezcan al usuario de Linux especificado. Es útil para mantener consistencia de propiedad en carpetas compartidas.

El parámetro `force group` hace lo mismo pero para el grupo.

Los parámetros `create mask` y `directory mask` definen los permisos máximos que pueden tener los archivos y carpetas creados en el recurso. Por ejemplo, `create mask = 0664` significa que ningún archivo creado puede tener permisos más amplios que `rw-rw-r--`.

Los parámetros `force create mode` y `force directory mode` definen los permisos mínimos. Mientras que `mask` limita hacia arriba (no puede tener más permisos), `force mode` garantiza hacia abajo (siempre tendrá al menos estos permisos).

El parámetro `admin users` concede privilegios de root sobre el recurso al usuario especificado. Aunque el usuario no sea root en Linux, tendrá acceso total al contenido del recurso compartido.

### La gestión de usuarios en Samba: la regla de oro

Samba tiene su propia base de datos de usuarios, separada de la de Linux. Esta separación es intencional: las contraseñas de Samba están guardadas en un formato diferente y pueden ser distintas de las contraseñas de Linux.

La regla de oro de la gestión de usuarios Samba es siempre en dos pasos: primero hay que crear el usuario en Linux, y solo entonces se puede añadir a Samba. No se puede añadir a Samba un usuario que no existe en Linux.

Para crear un usuario de Linux que solo va a ser usado para Samba (sin acceso a terminal), se usa el comando `sudo useradd -M -s /usr/sbin/nologin nombre_usuario`. La opción `-M` evita que se cree el directorio home. La opción `-s /usr/sbin/nologin` asigna un shell especial que rechaza cualquier intento de inicio de sesión interactiva. Este usuario existe en Linux pero no puede iniciar sesión en el sistema, solo puede acceder a Samba.

Para añadir ese usuario a Samba y asignarle una contraseña, se usa `sudo smbpasswd -a nombre_usuario`. El sistema pedirá una contraseña dos veces. A partir de ese momento, el usuario puede autenticarse en Samba con esa contraseña.

Para deshabilitar temporalmente un usuario de Samba sin borrarlo, se usa `sudo smbpasswd -d nombre_usuario`. Para volver a habilitarlo, `sudo smbpasswd -e nombre_usuario`. Para eliminarlo completamente de Samba (sin borrar el usuario Linux), `sudo smbpasswd -x nombre_usuario`.

El comando `sudo pdbedit -L` lista todos los usuarios registrados en la base de datos de Samba.

El comando `smbstatus` muestra las conexiones activas al servidor en ese momento: qué usuarios están conectados, desde qué IPs, y qué archivos tienen abiertos.

### El proceso completo para configurar el servidor Samba paso a paso

El proceso completo de configuración de un servidor Samba desde cero sigue siempre el mismo orden. Primero se instala el paquete `samba`. Luego se hace una copia de seguridad del archivo de configuración original. Después se crean los directorios físicos que se van a compartir. Luego se crean los usuarios de Linux con `useradd` y se añaden a Samba con `smbpasswd -a`. Se asignan los permisos de Linux a los directorios con `chown` y `chmod`. A continuación se edita el archivo `/etc/samba/smb.conf` con las secciones correspondientes. Se verifica la configuración con `testparm -s`. Y finalmente se reinicia o recarga el servicio con `systemctl reload smbd nmbd`.

### Un ejemplo real: servidor Samba con recursos público, privado, con restricciones y homes

Un servidor Samba típico de clase podría tener cuatro recursos compartidos.

El primero, llamado `pub`, es un recurso público. Cualquiera puede acceder sin contraseña y puede ver los archivos, pero no modificarlos. La configuración incluye `guest ok = yes` para acceso sin autenticación y `read only = yes` para que sea de solo lectura.

El segundo, llamado `privado`, es un recurso protegido. Solo los miembros del grupo `gprivado` pueden acceder, y pueden leer y escribir. La configuración incluye `valid users = @gprivado` y `writable = yes`. En Linux hay que asegurarse de que el directorio pertenece al grupo correcto y tiene permisos `770`.

El tercero, llamado `padron`, tiene una lista de usuarios prohibidos. Todos pueden acceder en modo lectura excepto el usuario `pica`, que está en `invalid users`. Aunque `pica` tuviera la contraseña correcta, se le denegaría el acceso.

El cuarto, `[homes]`, da acceso a cada usuario a su carpeta personal. Está configurado como `browseable = no` para que no aparezca en la lista de recursos (cada usuario solo ve su propio home cuando se conecta). Con `create mask = 0700` y `directory mask = 0700`, los archivos que cree el usuario solo los puede ver él.

### El cliente Samba desde la línea de comandos

Para acceder a recursos Samba desde la terminal de Linux, se usa el programa `smbclient`. Es similar a un cliente FTP: se conecta al servidor y permite hacer operaciones de forma interactiva.

Para listar los recursos disponibles en un servidor, se usa `smbclient -L //IP_servidor`. Si el servidor requiere autenticación, se añade `-U nombre_usuario`. El sistema pedirá la contraseña y luego mostrará la lista de recursos.

Para conectarse a un recurso concreto, se usa `smbclient //IP_servidor/nombre_recurso -U nombre_usuario`. Esto abre una sesión interactiva. Dentro de esa sesión, los comandos más importantes son `ls` para listar el contenido, `get nombre_archivo` para descargar un archivo al directorio local, `put nombre_archivo` para subir un archivo desde el directorio local, y `exit` para cerrar la sesión.

Para montar un recurso Samba de forma permanente en Linux como si fuera un disco local, se usa el tipo de sistema de archivos `cifs`. En un montaje temporal: `sudo mount -t cifs //IP/recurso /mnt/punto -o username=usuario,password=contraseña`. En el fstab para montaje permanente: la línea tiene el mismo formato pero con las credenciales especificadas en la opción `-o`.

### La doble capa de permisos: Linux y Samba

Uno de los conceptos más importantes de Samba, y uno que aparece repetidamente en los exámenes, es que hay dos capas de permisos que actúan simultáneamente y de forma independiente.

La primera capa son los permisos de Linux: los que gestiona el propio sistema operativo con `chmod` y `chown`. Estos controlan qué puede hacer el proceso `smbd` con los archivos a nivel del sistema de archivos Linux.

La segunda capa son los permisos de Samba: los que se definen en `smb.conf` con parámetros como `read only`, `valid users`, `write list`, etc.

La regla es que siempre prevalece el más restrictivo de los dos. Si Samba dice que un usuario puede escribir (`writable = yes`) pero Linux tiene los permisos del directorio como solo lectura para el proceso Samba (`chmod 444`), el usuario no podrá escribir porque Linux lo bloquea antes de que Samba pueda autorizarlo.

Esto significa que una configuración Samba correcta siempre tiene que considerar ambas capas y asegurarse de que son coherentes. Es muy común el error de configurar bien Samba pero olvidar dar los permisos Linux necesarios, o viceversa.

### SetGID: el permiso especial para carpetas de trabajo en equipo

Cuando varios usuarios de un mismo grupo trabajan en una carpeta compartida en Samba, surge un problema común. Cuando el usuario `ana` crea un archivo, ese archivo pertenece a `ana` y a su grupo principal en Linux. Si el usuario `pedro`, aunque sea del mismo grupo, intenta modificar ese archivo, puede encontrarse con que los permisos no se lo permiten.

La solución es el bit SetGID. Cuando se activa en un directorio con el comando `sudo chmod 2770 /ruta/directorio`, cualquier archivo que se cree dentro de ese directorio heredará automáticamente el grupo del directorio, no el grupo del usuario que lo crea. El `2` al principio activa el SetGID, y `770` da permisos completos al propietario y al grupo, y ninguno para otros.

Combinado con `force group = grupo` en smb.conf, esto garantiza que todos los archivos del recurso compartido siempre pertenezcan al grupo correcto, y todos los miembros del grupo pueden trabajar sobre ellos sin problemas de permisos.

### Módulos VFS: funcionalidades avanzadas de Samba

Samba permite añadir funcionalidades adicionales mediante módulos VFS (Virtual File System). Se configuran en smb.conf con el parámetro `vfs objects`.

El módulo `recycle` implementa una papelera de reciclaje para el recurso compartido. Cuando un usuario de Windows borra un archivo en una carpeta de red normal, ese archivo desaparece para siempre sin pasar por la papelera de Windows. Con el módulo `recycle`, Samba intercepta el borrado y mueve el archivo a una carpeta oculta llamada `.papelera` (o el nombre que se configure). Los parámetros `recycle:keeptree = yes` mantienen la estructura de subcarpetas original, `recycle:versions = yes` añade números si hay archivos con el mismo nombre, y `recycle:exclude` permite excluir ciertos tipos de archivos como temporales o logs.

El módulo `full_audit` registra cada operación que ocurre en el recurso compartido: quién entró, qué archivo abrió, qué modificó, qué borró. Es fundamental en entornos corporativos donde hay obligaciones legales de auditoría. Los logs se envían al servicio `syslog` del sistema y pueden redirigirse a un archivo específico para su análisis.

---

## Parte 4: SSH — Acceso seguro y remoto a otros sistemas

### Qué es SSH y por qué reemplazó a Telnet

SSH son las siglas de Secure Shell. Es un protocolo que permite conectarse de forma cifrada al terminal de otro ordenador, ejecutar comandos remotamente, transferir archivos y mucho más.

Antes de SSH existía Telnet, que hacía lo mismo pero con un defecto crítico: enviaba todo, incluyendo las contraseñas, en texto plano por la red. Cualquier persona que estuviera "escuchando" el tráfico de red podía capturar las contraseñas. SSH surgió como respuesta directa a este problema: cifra toda la comunicación de extremo a extremo, por lo que aunque alguien capture el tráfico, solo verá datos ininteligibles.

El cifrado de SSH funciona con criptografía asimétrica para el establecimiento inicial de la conexión y criptografía simétrica para el resto de la comunicación. El resultado es una comunicación completamente opaca para cualquiera que la intercepte.

SSH usa por defecto el puerto TCP 22. Cuando se habla de "abrir SSH", se refiere a permitir conexiones a ese puerto.

### Los componentes de OpenSSH

OpenSSH es la implementación libre y de código abierto del protocolo SSH, y es la que viene por defecto en Ubuntu y la mayoría de distribuciones Linux.

OpenSSH incluye varios programas. El programa `ssh` es el cliente: permite conectarse a servidores SSH remotos. El programa `sshd` es el demonio servidor: escucha conexiones entrantes y las gestiona. El programa `scp` permite copiar archivos de forma segura entre dos máquinas usando SSH como canal de transporte. El programa `ssh-keygen` permite generar pares de claves criptográficas. El programa `ssh-copy-id` facilita copiar la clave pública al servidor.

Para instalar el servidor SSH en Ubuntu: `sudo apt install openssh-server`. El cliente normalmente ya viene instalado, pero puede instalarse con `sudo apt install openssh-client`. Para ver el estado del servicio: `sudo systemctl status ssh`.

### Conexión básica y ejecución de comandos remotos

La forma más básica de usar SSH es abrir una sesión de terminal remota. El comando es simplemente `ssh usuario@IP_del_servidor`. Si es la primera vez que nos conectamos a ese servidor, SSH nos advertirá que no conoce ese servidor y nos pedirá confirmación. Al confirmar, guarda la clave pública del servidor en el archivo `~/.ssh/known_hosts` del cliente para futuras verificaciones.

Una vez conectados, estamos en el terminal del servidor remoto. Podemos ejecutar cualquier comando como si estuviéramos físicamente delante de él.

Pero SSH también permite ejecutar comandos remotos sin abrir una sesión interactiva. Esto es muy útil para scripts de automatización. La sintaxis es `ssh usuario@ip "comando"`. Las comillas son importantes: todo lo que está entre comillas se ejecuta en el servidor remoto.

Si queremos ejecutar varios comandos seguidos, los separamos con punto y coma: `ssh usuario@ip "comando1; comando2"`. El punto y coma ejecuta ambos comandos independientemente de si el primero falla o tiene éxito.

Si queremos que el segundo comando solo se ejecute si el primero tuvo éxito, usamos `&&`: `ssh usuario@ip "comando1 && comando2"`.

Algunos comandos requieren que exista una terminal asignada para funcionar, como `sudo` en ciertos casos. Para esos casos, SSH tiene la opción `-t` que crea una pseudo-terminal: `ssh -t usuario@ip "sudo apt update"`.

### Ejecutar scripts remotamente

SSH permite dos formas de ejecutar scripts remotamente, dependiendo de dónde esté el script.

Si el script ya existe en el servidor, simplemente se ejecuta con `ssh usuario@ip "bash /ruta/del/script.sh"`.

Si el script está en tu máquina local (el cliente) y quieres ejecutarlo en el servidor sin copiarlo antes, se usa una redirección especial: `ssh usuario@ip 'bash -s' < script.sh`. La redirección `< script.sh` envía el contenido del script por la entrada estándar, y `bash -s` le dice a bash en el servidor que lea el script de la entrada estándar. El resultado es que el script se ejecuta en el servidor sin haberse guardado allí.

### Copiar archivos con scp

El comando `scp` permite copiar archivos entre máquinas usando el cifrado de SSH. Funciona de forma similar al comando `cp` local pero especificando origen y destino con el formato `usuario@ip:/ruta`.

Para copiar un archivo de tu máquina al servidor: `scp fichero.txt usuario@192.168.1.1:/home/usuario/`. El archivo `fichero.txt` que está en tu directorio actual se copia al directorio `/home/usuario/` del servidor.

Para copiar un archivo del servidor a tu máquina: `scp usuario@192.168.1.1:/home/usuario/fichero.txt ~/Descargas/`. El archivo remoto se descarga a tu carpeta de Descargas local.

Para copiar una carpeta completa con todo su contenido, se añade la opción `-r` (recursivo): `scp -r mi_proyecto usuario@192.168.1.1:/var/www/`.

### Autenticación con claves: cómo funciona y por qué es más segura

La autenticación por contraseña en SSH tiene un inconveniente: la contraseña viaja por la red (aunque cifrada) y puede ser atacada por fuerza bruta. La autenticación por claves es mucho más segura y además permite automatizar conexiones sin contraseña.

El mecanismo se basa en criptografía asimétrica. Se generan dos claves matemáticamente relacionadas: una clave pública y una clave privada. Lo que se cifra con la clave pública solo se puede descifrar con la clave privada, y viceversa. La clave privada nunca sale del cliente; la clave pública se puede compartir libremente.

El proceso de autenticación funciona así: el servidor toma la clave pública del cliente (que tiene guardada en su archivo `authorized_keys`) y la usa para cifrar un mensaje aleatorio. Envía ese mensaje cifrado al cliente. El cliente usa su clave privada para descifrar el mensaje y envía la respuesta al servidor. El servidor verifica que la respuesta es correcta. Si lo es, significa que el cliente tiene la clave privada correspondiente a la clave pública registrada, y por tanto es quien dice ser. La clave privada nunca salió del cliente en ningún momento del proceso.

Para generar un par de claves, se usa el comando `ssh-keygen -t ed25519`. El algoritmo `ed25519` es el más moderno y seguro. El sistema preguntará dónde guardar las claves (por defecto en `~/.ssh/id_ed25519` y `~/.ssh/id_ed25519.pub`) y si quieres proteger la clave privada con una passphrase (recomendable, aunque para automatización se suele dejar vacía).

Una vez generadas las claves, hay que copiar la clave pública al servidor. El comando `ssh-copy-id usuario@IP_servidor` hace exactamente eso: se conecta al servidor con contraseña, crea el directorio `~/.ssh/` si no existe, y añade la clave pública al archivo `authorized_keys`. A partir de ese momento, las futuras conexiones no pedirán contraseña.

Para verificar que todo está bien, desde el servidor se puede ejecutar `cat ~/.ssh/authorized_keys` y comprobar que aparece la clave pública del cliente.

### Los archivos clave del directorio `~/.ssh/`

El directorio `~/.ssh/` guarda archivos fundamentales para el funcionamiento de SSH tanto en cliente como en servidor.

En el cliente, los archivos más importantes son `id_ed25519` o `id_rsa`, que es la clave privada y jamás debe compartirse con nadie ni copiarse fuera del sistema. El archivo `id_ed25519.pub` o `id_rsa.pub` es la clave pública, el par de la privada, y es la que se copia al servidor. El archivo `known_hosts` guarda las claves públicas de todos los servidores a los que el cliente se ha conectado previamente.

En el servidor, el archivo más importante en el directorio home del usuario es `authorized_keys`. Contiene la lista de claves públicas de los clientes autorizados a conectarse sin contraseña a esa cuenta.

El archivo `known_hosts` del cliente tiene un papel de seguridad crítico. Cuando te conectas a un servidor por primera vez, SSH te muestra su "huella digital" (fingerprint) y te pide confirmación. Al confirmar, guarda esa huella en `known_hosts`. En futuras conexiones, SSH compara la huella del servidor con la guardada. Si no coinciden, SSH lanza una advertencia de seguridad indicando que el servidor puede haber cambiado o que alguien está intentando suplantar su identidad (ataque man-in-the-middle). Es una protección fundamental contra este tipo de ataques.

---

## Parte 5: Comparativa y relaciones entre los cuatro servicios

### Cuándo usar cada herramienta y por qué

NFS es la herramienta adecuada cuando todos los equipos de la red son Linux o Unix. Es más rápido que Samba para operaciones entre sistemas Linux y su configuración es más simple una vez que se entiende `/etc/exports`. Es ideal para redes internas de confianza donde la seguridad se basa en el control de acceso por IP.

Samba es la herramienta adecuada cuando en la red hay ordenadores Windows, o una mezcla de Windows y Linux. Desde un Windows se accede a un servidor Samba exactamente igual que a cualquier carpeta compartida de otro Windows. Samba también es más adecuado cuando se necesita autenticación por usuario y contraseña en lugar de por IP.

SSH no es estrictamente un sistema de compartición de archivos como NFS o Samba, aunque con `scp` permite transferir archivos. Su función principal es la administración remota: ejecutar comandos en servidores remotos, gestionar configuraciones, depurar problemas. Cualquier administrador de sistemas Linux usa SSH constantemente.

### La diferencia fundamental de seguridad

NFS identifica a los clientes por su dirección IP. Si una IP está autorizada, cualquier equipo que tenga esa IP puede acceder. No importa qué usuario está usando el equipo ni qué contraseña tiene. Esta es la razón por la que NFS solo se recomienda en redes locales seguras donde controlas qué equipos tienen qué IPs.

Samba identifica a los clientes por nombre de usuario y contraseña. No importa desde qué IP se conecta el usuario: si tiene las credenciales correctas y la IP no está bloqueada, accede. Este modelo es más flexible y más adecuado para redes donde los usuarios se mueven entre diferentes equipos.

SSH puede usar tanto contraseñas como claves criptográficas para autenticar. La autenticación por claves es más segura y permite automatización.

### Los puertos de cada servicio

Los puertos son datos que suelen aparecer en preguntas de test. NFS usa principalmente el puerto 2049 para el servicio principal y el 111 para el portmapper. En versiones anteriores a NFSv4, también usaba puertos dinámicos adicionales que hacían difícil la configuración de firewalls. NFSv4 consolida todo en esos dos puertos, lo que es una ventaja operativa significativa.

Samba usa el puerto TCP 445 para SMBv2 y v3 (el protocolo moderno) y el TCP 139 para NetBIOS (compatibilidad con versiones antiguas). Además usa los UDP 137 y 138 para el servicio de nombres NetBIOS que gestiona nmbd.

SSH usa el puerto TCP 22. Solo ese.

### El concepto de estado: stateless vs stateful

NFS versiones 2 y 3 son stateless, sin estado. El servidor no guarda información sobre las sesiones activas. Cada petición es tratada de forma independiente. Esto tiene la ventaja de que si el servidor se reinicia, los clientes simplemente reintentan la operación y continúan. La desventaja es que no puede garantizar la coherencia en operaciones de bloqueo de archivos.

NFSv4 y Samba son stateful, con estado. El servidor mantiene información sobre qué clientes están conectados, qué archivos tienen abiertos, etc. Esto permite mejores optimizaciones y gestión correcta de bloqueos de archivo, pero si el servidor se reinicia, los clientes pierden el estado y a veces necesitan reconectarse.

---

## Preguntas y respuestas clave para el examen

Esta sección aborda directamente las preguntas más habituales en los exámenes tipo test y de desarrollo sobre este tema.

### Sobre NFS

La pregunta más importante sobre NFS es qué hace la opción `all_squash`. La respuesta es que trata a absolutamente todos los usuarios que se conectan como si fueran el usuario anónimo `nobody`, incluyendo al root. Impide que ningún usuario del cliente tenga privilegios en el servidor.

La diferencia entre `root_squash` y `all_squash` es de alcance. `root_squash` solo degrada al usuario root del cliente, el resto de usuarios mantienen su identidad. `all_squash` degrada a todos los usuarios sin excepción. `root_squash` es el comportamiento por defecto.

La opción `no_root_squash` hace que el root del cliente sea root también en el servidor. Es peligrosa y se usa solo en circunstancias muy controladas.

La diferencia entre `sync` y `async` es de seguridad versus velocidad. `sync` confirma la escritura solo cuando los datos están físicamente en el disco, lo que garantiza que no se perderán datos si el servidor falla. `async` confirma antes de que los datos lleguen al disco, lo que es más rápido pero puede causar corrupción si el servidor falla en el momento equivocado.

El comando para aplicar cambios en `/etc/exports` sin reiniciar el servicio es `sudo exportfs -ra`.

El comando para ver qué exportaciones ofrece un servidor NFS desde el cliente es `showmount -e IP_del_servidor`.

En `/etc/fstab` para un montaje NFS, los dos ceros del final significan: el primero que no se haga volcado de seguridad automático, y el segundo que `fsck` no compruebe este sistema de archivos al arrancar. Son cero en ambos casos porque un recurso de red no es un disco físico y esas operaciones no tienen sentido sobre él.

El parámetro `fsid=0` en NFSv4 marca la carpeta como el punto raíz de todas las exportaciones. Solo puede haber un `fsid=0` y todos los demás directorios exportados deben estar dentro de esa raíz.

El parámetro `crossmnt` permite que el cliente acceda a los subdirectorios del árbol de exportaciones NFSv4 de forma transparente sin tener que montarlos por separado.

La diferencia de montaje entre NFSv3 y NFSv4 en el cliente es que con NFSv3 se usa `-t nfs` y se especifica la ruta exacta, mientras que con NFSv4 se usa `-t nfs4` y se monta la raíz virtual con `IP:/`.

El error del espacio en blanco antes del paréntesis en `/etc/exports` es uno de los más peligrosos. Sin espacio, las opciones aplican al cliente especificado. Con espacio, el cliente especificado obtiene opciones por defecto (solo lectura) y las opciones del paréntesis aplican a cualquier otro cliente del mundo.

### Sobre Samba

La diferencia entre `smbd` y `nmbd` es que `smbd` gestiona la compartición de archivos y la autenticación, mientras que `nmbd` gestiona la resolución de nombres NetBIOS para que los equipos aparezcan en el entorno de red de Windows.

El comando `testparm -s` verifica la sintaxis de `/etc/samba/smb.conf` y muestra la configuración. Siempre debe ejecutarse antes de reiniciar Samba.

En Samba, el parámetro `browseable = no` hace que el recurso no aparezca en la lista de recursos disponibles, pero es accesible si se escribe la ruta directamente.

El parámetro `map to guest = bad user` hace que los usuarios que no existan en la base de datos de Samba sean tratados como invitados en lugar de recibir un error de autenticación.

En la gestión de usuarios Samba, el orden obligatorio es: primero crear el usuario en Linux con `useradd`, y solo después añadirlo a Samba con `smbpasswd -a`. No se puede añadir a Samba un usuario que no existe en Linux.

Un cliente Linux no necesita instalar el paquete `samba` para acceder a recursos Samba. Solo necesita `smbclient` para acceso desde terminal, o `cifs-utils` para montar el recurso. El paquete `samba` completo solo lo necesita quien actúa como servidor.

La doble capa de permisos en Samba funciona así: Samba aplica sus propias reglas de acceso definidas en smb.conf, y Linux aplica los permisos del sistema de archivos. Siempre prevalece el más restrictivo de los dos.

El bit SetGID aplicado a una carpeta colaborativa de Samba con `chmod 2770` garantiza que todos los archivos creados dentro heredarán el grupo de la carpeta, no el grupo personal del usuario que los crea. Esto evita problemas de permisos cuando varios usuarios del mismo grupo trabajan en la misma carpeta.

El parámetro `server min protocol = SMB2` en la sección global de smb.conf hace que Samba rechace conexiones que usen SMBv1, que es la versión vulnerable explotada por WannaCry.

El comando `smbstatus` muestra las conexiones activas al servidor Samba en ese momento.

### Sobre SSH

El comando para generar un par de claves SSH con el algoritmo más moderno es `ssh-keygen -t ed25519`. Genera dos archivos: `id_ed25519` (clave privada, nunca sale del cliente) y `id_ed25519.pub` (clave pública, que se copia al servidor).

El comando `ssh-copy-id usuario@IP` copia la clave pública del cliente al archivo `authorized_keys` del servidor. Después de este paso, la conexión no requiere contraseña.

El archivo `authorized_keys` en el servidor contiene la lista de claves públicas de los clientes autorizados a conectarse sin contraseña.

El archivo `known_hosts` en el cliente guarda las claves públicas de los servidores a los que se ha conectado. Protege contra ataques man-in-the-middle: si la clave de un servidor cambia inesperadamente, SSH lanza una advertencia.

La diferencia entre ejecutar un script que está en el servidor y uno que está en el cliente es la sintaxis. Para un script en el servidor: `ssh usuario@ip "bash /ruta/script.sh"`. Para un script en el cliente que se ejecuta en el servidor: `ssh usuario@ip 'bash -s' < script.sh`.

La opción `-t` en SSH (`ssh -t usuario@ip "comando"`) asigna una pseudo-terminal al comando remoto. Es necesaria para comandos que requieren una terminal, como ciertos usos de `sudo`.

La diferencia entre `ssh` y `scp` es que `ssh` abre una sesión de terminal remota para ejecutar comandos, mientras que `scp` copia archivos entre máquinas usando SSH como canal cifrado.

### Sobre Netplan

Netplan es la herramienta de configuración de red de Ubuntu desde la versión 17.04. Reemplaza al antiguo `/etc/network/interfaces`.

Los dos backends o renderers de Netplan son `networkd` (para servidores y sistemas sin entorno gráfico) y `NetworkManager` (para escritorio).

La diferencia entre `netplan try` y `netplan apply` es que `netplan try` prueba la configuración con reversión automática si no se confirma en 120 segundos, mientras que `netplan apply` aplica los cambios de forma permanente e inmediata.

La notación CIDR `/24` en una dirección IP como `192.168.1.0/24` significa que los 24 primeros bits identifican la red. En una red `/24` pueden existir hasta 254 equipos distintos (de .1 a .254, ya que .0 es la dirección de red y .255 es la de broadcast).

---

## Resumen narrativo para repasar

Cuando una empresa monta su infraestructura de red Linux, todo empieza con Netplan: cada máquina necesita tener una dirección IP fija y conocida para que el resto pueda encontrarla. El servidor NFS, el servidor Samba, el servidor SSH: todos necesitan direcciones fijas configuradas en Netplan.

Luego vienen los servicios de compartición de archivos. Si la red es exclusivamente Linux, NFS es la opción natural: rápida, integrada, sin overhead. El administrador edita `/etc/exports`, define qué carpetas exporta y a qué redes, aplica los cambios con `exportfs -ra`, y los clientes montan esas carpetas con `mount -t nfs`. Si necesita que esos montajes sobrevivan a reinicios, los añade al `/etc/fstab` de los clientes.

Si hay ordenadores Windows en la red, o si necesita autenticación por usuario y contraseña, usa Samba. Configura `smb.conf` definiendo un workgroup, los recursos compartidos con sus rutas y permisos, crea los usuarios con `useradd` y `smbpasswd -a`, ajusta los permisos de Linux con `chown` y `chmod` para que sean coherentes con lo que Samba permite, verifica con `testparm` y recarga el servicio.

Para todo lo que necesita administración remota, SSH. El administrador genera su par de claves con `ssh-keygen`, copia la pública a cada servidor con `ssh-copy-id`, y a partir de ese momento puede conectarse a cualquier servidor sin contraseña y ejecutar comandos o scripts de forma completamente automática y segura.

Estos cuatro servicios juntos forman la columna vertebral de cualquier red Linux bien administrada.
