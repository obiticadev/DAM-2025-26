


# Bootcamp Samba: De Novato a Pro - Parte 1: Fundamentos, Instalación y Arquitectura

Bienvenido al bootcamp de Samba. A lo largo de estas entregas, desglosaremos este servicio desde su instalación más básica hasta configuraciones avanzadas de rendimiento, seguridad y auditoría.

En esta **Parte 1**, nos centraremos en la arquitectura de Samba, los comandos de instalación, la gestión de sus demonios (servicios) y la estructura inicial del archivo de configuración.

---

## 1. Arquitectura Base: ¿Qué es Samba?

Samba no es un solo programa, sino una suite de herramientas que implementa el protocolo SMB/CIFS en sistemas Unix/Linux. Se apoya principalmente en dos demonios (servicios en segundo plano):

*   **`smbd`**: Es el núcleo. Proporciona los servicios de compartición de archivos e impresoras, y gestiona la autenticación y autorización de los clientes. Utiliza el puerto TCP 139 y 445.
*   **`nmbd`**: Proporciona el servicio de nombres NetBIOS sobre IP (resolución de nombres para que tu equipo aparezca en el "Entorno de red" de Windows). Utiliza los puertos UDP 137 y 138.

---

## 2. Comandos de Instalación

Dependiendo de la familia de tu distribución Linux, la paquetería varía. Aquí tienes los comandos exhaustivos para una instalación completa (incluyendo herramientas de cliente y utilidades adicionales):

**Debian / Ubuntu / Mint:**
```bash
# Actualizar repositorios e instalar Samba, cliente y utilidades
sudo apt update
sudo apt install samba smbclient cifs-utils
```

**RHEL / CentOS / AlmaLinux / Fedora:**
```bash
# Instalar los paquetes principales y herramientas de cliente
sudo dnf install samba samba-client cifs-utils
```

**Arch Linux / Manjaro:**
```bash
sudo pacman -S samba smbclient cifs-utils
```
*(Nota en Arch: El archivo de configuración base no se crea automáticamente, hay que copiarlo: `sudo cp /etc/samba/smb.conf.default /etc/samba/smb.conf`)*.

---

## 3. Gestión de los Servicios (Demonios)

Una vez instalado, debes saber cómo controlar el ciclo de vida de los servicios de Samba mediante `systemctl`. 

**Para sistemas basados en Debian/Ubuntu (los nombres de servicio terminan en 'd'):**
```bash
sudo systemctl start smbd nmbd      # Inicia los servicios ahora
sudo systemctl enable smbd nmbd     # Habilita el inicio automático al arrancar el sistema
sudo systemctl status smbd nmbd     # Comprueba si están corriendo y si hay errores recientes
sudo systemctl restart smbd nmbd    # Reinicia completamente los servicios (corta conexiones)
sudo systemctl reload smbd nmbd     # Recarga la configuración de smb.conf sin cortar conexiones (recomendado tras cambios)
```

**Para sistemas basados en RHEL/Fedora/Arch (nombres sin la 'd' final):**
```bash
sudo systemctl start smb nmb
sudo systemctl enable smb nmb
sudo systemctl status smb nmb
sudo systemctl restart smb nmb
sudo systemctl reload smb nmb
```

---

## 4. Configuración del Firewall (Apertura de puertos)

Samba no funcionará si el cortafuegos bloquea sus puertos.

**Con UFW (Ubuntu/Debian):**
```bash
# UFW tiene un perfil preconfigurado para Samba
sudo ufw allow Samba
sudo ufw status
```

**Con Firewalld (RHEL/Fedora/CentOS):**
```bash
# Añade el servicio permanentemente y recarga
sudo firewall-cmd --permanent --add-service=samba
sudo firewall-cmd --reload
sudo firewall-cmd --list-services
```

**Con iptables (Universal, si no usas los anteriores):**
```bash
sudo iptables -A INPUT -p tcp --dport 139 -j ACCEPT
sudo iptables -A INPUT -p tcp --dport 445 -j ACCEPT
sudo iptables -A INPUT -p udp --dport 137 -j ACCEPT
sudo iptables -A INPUT -p udp --dport 138 -j ACCEPT
```

---

## 5. El archivo maestro: `/etc/samba/smb.conf`

Este es el cerebro de Samba. Antes de tocar nada, **siempre** debes hacer una copia de seguridad:

```bash
sudo cp /etc/samba/smb.conf /etc/samba/smb.conf.backup-$(date +%F)
```

El archivo se estructura en bloques marcados por corchetes `[ ]`. El más importante de inicio es `[global]`, que afecta a todo el servidor.

**Ejemplo de un `[global]` limpio y optimizado para iniciar:**

```ini
[global]
   # --- Identificación de Red ---
   workgroup = WORKGROUP
   # Nombre NetBIOS (cómo se ve en la red, máx 15 caracteres)
   netbios name = ServidorLinux
   # Descripción del equipo
   server string = Servidor de Archivos Samba

   # --- Red y Seguridad ---
   # Limitar por seguridad a qué interfaces escucha Samba (ej. solo red local)
   interfaces = lo eth0 192.168.1.0/24
   bind interfaces only = yes
   # Rechazar conexiones de IPs no deseadas (opcional pero seguro)
   hosts allow = 127. 192.168.1.
   hosts deny = ALL

   # --- Autenticación ---
   security = user
   # Qué hacer con usuarios no válidos: mapearlos a la cuenta de invitado
   map to guest = bad user
   # Rechazar el protocolo SMBv1 (muy inseguro, vulnerable a WannaCry)
   server min protocol = SMB2

   # --- Logs y Depuración ---
   # %m crea un log separado por cada máquina que se conecta
   log file = /var/log/samba/log.%m
   # Tamaño máximo del log en KB
   max log size = 1000
   # Nivel de detalle del log (0 es poco, 3 es detallado, 10 es debug total)
   log level = 1
```

### Comprobación de errores básicos
Siempre que modifiques el archivo de configuración, ejecuta este comando antes de recargar el servicio:
```bash
testparm -s
```
*El parámetro `-s` omite la pausa de "Presione Enter para ver el volcado de configuración", mostrando el resultado directo y si hay errores de sintaxis.*

---