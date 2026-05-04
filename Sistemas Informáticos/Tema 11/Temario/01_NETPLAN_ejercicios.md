# Práctica: Configuración de Red con Netplan
### Fuente: Configuración de red. Netplan.pdf

> Ejercicios prácticos derivados del contenido del documento. El PDF es un documento de referencia; estos bloques cubren todas las tareas que se practican con Netplan y el comando `ip`.

---

## Índice

| Bloque | Proceso |
|--------|---------|
| [Bloque 1](#bloque-1) | Verificar el estado actual de la red e identificar el archivo de configuración Netplan activo |
| [Bloque 2](#bloque-2) | Configurar IP dinámica (DHCP) mediante Netplan |
| [Bloque 3](#bloque-3) | Configurar IP estática mediante Netplan |
| [Bloque 4](#bloque-4) | Comprobar la configuración con netplan try y aplicarla con netplan apply |
| [Bloque 5](#bloque-5) | Diagnóstico de red completo con el comando ip |

---

<a id="bloque-1"></a>
## Bloque 1: Verificar el estado actual de la red e identificar el archivo de configuración Netplan activo

> **ENUNCIADO**
> Antes de modificar nada, identifica qué interfaces de red tiene el equipo, cuál es su IP actual, y localiza el archivo de configuración Netplan activo en el sistema.

### Solución

**Paso 1: Ver todas las interfaces de red y sus IPs asignadas**
```bash
ip address
```
> **Anotación:** `ip address` (o su forma corta `ip ad`) muestra todas las interfaces del sistema junto con sus estados (UP/DOWN) y sus direcciones IP. Es el primer comando que ejecuta cualquier administrador al diagnosticar la red. Reemplaza al antiguo `ifconfig`, que ya está obsoleto en distribuciones modernas de Linux.

**Paso 2: Localizar el archivo de configuración de Netplan**
```bash
ls /etc/netplan/
```
> **Anotación:** Netplan guarda sus archivos de configuración en `/etc/netplan/`. El nombre del archivo varía según la versión de Ubuntu:
> - **Ubuntu Server:** `01-netcfg.yaml`
> - **Ubuntu Desktop:** `01-network-manager-all.yaml`
> - **Ubuntu Cloud:** `50-cloud-init.yaml`
>
> El número al principio del nombre (01, 50…) define el orden de lectura cuando hay varios archivos. El más bajo se procesa primero.

**Paso 3: Ver el contenido del archivo de configuración**
```bash
cat /etc/netplan/01-netcfg.yaml
```
> **Anotación:** Esto muestra la configuración actual. Se puede ver qué backend (renderer) está usando y si la IP es dinámica o estática.

**Paso 4: Identificar el backend activo**
```bash
cat /etc/netplan/*.yaml | grep renderer
```
> **Anotación:** El parámetro `renderer` indica qué motor gestiona la red:
> - `networkd`: motor de systemd, minimalista, para servidores y sistemas sin entorno gráfico.
> - `NetworkManager`: más completo, para escritorio. Tiene GUI, gestiona WiFi, etc.

---

<a id="bloque-2"></a>
## Bloque 2: Configurar IP dinámica (DHCP) mediante Netplan

> **ENUNCIADO**
> Configura la interfaz de red del equipo para que obtenga su dirección IP automáticamente del servidor DHCP de la red, editando el archivo de configuración de Netplan.

### Solución

**Paso 1: Abrir el archivo de configuración con el editor**
```bash
sudo nano /etc/netplan/01-netcfg.yaml
```
> **Anotación:** Se necesita `sudo` porque `/etc/netplan/` es un directorio del sistema, con permisos restringidos. `nano` es el editor de terminal más sencillo. Para guardar: `Ctrl+O` y Enter. Para salir: `Ctrl+X`.

**Paso 2: Escribir la configuración DHCP**
```yaml
network:
  version: 2
  renderer: networkd
  ethernets:
    enp0s3:
      dhcp4: true
```
> **Anotación, campo por campo:**
> - `network:` — Nodo raíz del documento. Todo lo demás cuelga de aquí.
> - `version: 2` — Versión del esquema de configuración de Netplan. Actualmente siempre es 2.
> - `renderer: networkd` — Motor de red a usar. `networkd` es para servidores. En Desktop sería `NetworkManager`.
> - `ethernets:` — Sección donde se configuran las interfaces de red por cable (Ethernet).
> - `enp0s3:` — Nombre de la interfaz que queremos configurar. Hay que ajustar este nombre al que muestra `ip address` en el sistema concreto.
> - `dhcp4: true` — Activa DHCP para IPv4. El equipo enviará una petición a la red para que un servidor DHCP (normalmente el router) le asigne una IP automáticamente.
>
> **CRÍTICO — Indentación en YAML:** La indentación con espacios es obligatoria y significativa. Un espacio de más o de menos hace que el archivo sea inválido y Netplan no lo aplica. Nunca usar tabuladores.

---

<a id="bloque-3"></a>
## Bloque 3: Configurar IP estática mediante Netplan

> **ENUNCIADO**
> Configura el equipo smb1 con la IP estática `192.168.100.100/24`, puerta de enlace `192.168.100.1` y servidores DNS `192.168.100.1` y `1.1.1.1`. Configura smb2 con `192.168.100.200/24` y la misma puerta de enlace y DNS.

### Solución

**Paso 1: Abrir el archivo de configuración**
```bash
sudo nano /etc/netplan/01-netcfg.yaml
```

**Paso 2: Escribir la configuración de IP estática (smb1)**
```yaml
network:
  version: 2
  renderer: networkd
  ethernets:
    enp0s3:
      addresses:
        - 192.168.100.100/24
      gateway4: 192.168.100.1
      nameservers:
        addresses: [192.168.100.1, 1.1.1.1]
```
> **Anotación, campo por campo:**
> - `addresses:` — Lista de direcciones IP a asignar a la interfaz. Es una lista (por eso el guion `-` antes de la IP). La dirección incluye la máscara de red en notación CIDR: `/24` significa que los primeros 24 bits identifican la red, lo que permite 254 hosts distintos (de .1 a .254) en la misma subred.
> - `gateway4:` — Puerta de enlace predeterminada. Es la IP del router. Los paquetes que van fuera de la red local (`192.168.100.0/24`) se envían a esta dirección para que los reenvíe.
> - `nameservers: addresses:` — Lista de servidores DNS. Se pueden usar IPs del router local o servidores públicos como `1.1.1.1` (Cloudflare) o `8.8.8.8` (Google). Sin DNS configurado, no se pueden resolver nombres de dominio (aunque sí haya conectividad por IP).
>
> **Por qué IP estática para servidores:** Los clientes (NFS, Samba, SSH) necesitan saber de antemano la dirección del servidor para conectarse. Si la IP cambiara con cada arranque (DHCP), la configuración de los clientes quedaría obsoleta.

**Configuración equivalente para smb2 (`192.168.100.200/24`)**
```yaml
network:
  version: 2
  renderer: networkd
  ethernets:
    enp0s3:
      addresses:
        - 192.168.100.200/24
      gateway4: 192.168.100.1
      nameservers:
        addresses: [192.168.100.1, 1.1.1.1]
```

---

<a id="bloque-4"></a>
## Bloque 4: Comprobar la configuración con netplan try y aplicarla con netplan apply

> **ENUNCIADO**
> Tras modificar el archivo YAML, comprueba que la configuración es correcta sin riesgo usando `netplan try`. Confirma los cambios y aplícalos de forma permanente.

### Solución

**Paso 1: Probar la configuración con reversión automática**
```bash
sudo netplan try
```
> **Anotación:** `netplan try` aplica la configuración de forma temporal y espera confirmación del administrador durante **120 segundos**. Si en ese tiempo no se confirma, el sistema revierte automáticamente a la configuración anterior. Esto es fundamental para servidores administrados en remoto por SSH: si la nueva config rompe la conectividad, el sistema se "cura solo" y puedes volver a conectarte. Si la configuración es correcta, se confirma pulsando **Enter**.

**Paso 2: Aplicar los cambios de forma permanente**
```bash
sudo netplan apply
```
> **Anotación:** `netplan apply` aplica los cambios de forma inmediata y permanente, sin período de prueba ni reversión automática. Si la nueva configuración es incorrecta y pierdes conectividad en un servidor remoto sin acceso físico, no hay vuelta atrás automática. Por eso en producción siempre se usa primero `netplan try`.

**Paso 3: Verificar que la configuración se ha aplicado**
```bash
ip address show enp0s3
ip route
```
> **Anotación:** Comprobamos que la interfaz tiene la dirección IP correcta y que la tabla de rutas muestra la puerta de enlace configurada (`default via 192.168.100.1`).

---

<a id="bloque-5"></a>
## Bloque 5: Diagnóstico de red completo con el comando ip

> **ENUNCIADO**
> Utilizando el comando `ip`, realiza las siguientes operaciones de diagnóstico: ver todas las interfaces, ver solo una interfaz concreta, consultar la tabla de enrutamiento, verificar el camino a `8.8.8.8`, deshabilitar y habilitar la interfaz, ver estadísticas de tráfico y comprobar la tabla ARP.

### Solución

**Ver todas las interfaces y sus IPs**
```bash
ip address
# Forma corta equivalente:
ip ad
```
> **Anotación:** Muestra todas las interfaces de red (Ethernet, WiFi, loopback…) con sus estados (UP = activa, DOWN = inactiva) y las IPs asignadas. La interfaz `lo` es el loopback, siempre presente, con IP `127.0.0.1`.

**Ver solo una interfaz concreta**
```bash
ip address show enp0s3
# Forma corta:
ip ad show enp0s3
```
> **Anotación:** Útil cuando el sistema tiene muchas interfaces y solo interesa una. Muestra la dirección MAC, el estado, y todas las IPs asignadas a esa interfaz.

**Ver la tabla de enrutamiento (dónde van los paquetes)**
```bash
ip route
# Formas equivalentes:
ip ro
ip route show
ip ro show
```
> **Anotación:** La tabla de rutas dice al sistema operativo por dónde enviar los paquetes según su destino. La línea `default via X.X.X.X` es la puerta de enlace predeterminada: todo lo que no tenga ruta específica se envía al router.

**Comprobar qué ruta usa el sistema para llegar a una IP concreta**
```bash
ip route get 8.8.8.8
```
> **Anotación:** Muy útil para depurar conectividad. Muestra exactamente qué interfaz y qué puerta de enlace usaría el sistema para llegar a `8.8.8.8` (o cualquier IP que se indique). Si el resultado dice "Network unreachable", hay un problema de configuración de rutas.

**Deshabilitar una interfaz de red**
```bash
sudo ip link set enp0s3 down
```
> **Anotación:** Desactiva la interfaz. El equipo deja de enviar y recibir tráfico por ella. El estado en `ip address` cambia a DOWN. Útil para forzar una reconexión o aislar la interfaz para pruebas.

**Volver a habilitar la interfaz**
```bash
sudo ip link set enp0s3 up
```
> **Anotación:** Reactiva la interfaz. Si hay configuración DHCP, vuelve a pedir IP al router. Si hay IP estática, la recupera directamente.

**Ver estadísticas de tráfico de una interfaz**
```bash
ip -s link show enp0s3
# Para todas las interfaces:
ip -s link
```
> **Anotación:** La opción `-s` (statistics) añade contadores: paquetes enviados/recibidos, bytes transferidos, errores y descartes. Útil para detectar problemas de rendimiento, colisiones o interfaces con errores.

**Comprobar la tabla ARP (equipos conocidos en la red local)**
```bash
ip neighbour
# Forma corta:
ip neigh
```
> **Anotación:** La tabla ARP (Address Resolution Protocol) asocia IPs de la red local con sus direcciones MAC (la dirección física del hardware de red). Cuando el equipo quiere comunicarse con otro en la misma subred, primero consulta esta tabla. Si un equipo aparece aquí con estado `REACHABLE`, hay comunicación activa con él. Si aparece `STALE`, la entrada puede estar desactualizada.
