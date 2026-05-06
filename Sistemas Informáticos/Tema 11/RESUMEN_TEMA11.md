### 🕒 PLAN DE ESTUDIO "UNA TARDE" (2.5 Horas en total)
1. **Bloque 1 (45 min) - Netplan y SSH:** Son los más cortos y lógicos. Léelos, entiende la mnemotecnia y escríbelos de memoria 2 veces en un folio en blanco.
2. **Bloque 2 (45 min) - NFS y Samba:** Son los más largos. Concéntrate en la diferencia entre ambos (NFS es IPs, Samba es Usuarios). Haz lo mismo: lee y escribe de memoria los pasos guiándote por la mnemotecnia.
3. **Descanso (15 min):** No mires pantallas.
4. **Bloque 3 (45 min) - Simulacro:** Coge folios en blanco y escribe la instalación/configuración de los 4 servicios de golpe usando solo las reglas mnemotécnicas como pista.

---

### 📝 RESUMEN PARA EL EXAMEN (Pasos y Comandos)

#### 1. NETPLAN (Red en Ubuntu)
**Objetivo:** Configurar la IP del equipo editando un archivo YAML.
1. `nano /etc/netplan/01-netcfg.yaml` ➔ Editar fichero definiendo IP (`addresses`), gateway y DNS (`nameservers`).
2. `netplan try` ➔ Prueba la configuración (revierte en 120s si te equivocas y pierdes conexión).
3. `netplan apply` ➔ Aplica y guarda los cambios de forma permanente.

🧠 **Regla Mnemotécnica: E.T.A.** 
* **E**dita (yaml) ➔ **T**ry (prueba) ➔ **A**pply (aplica).

---

#### 2. NFS (Compartir carpetas Linux ↔ Linux)
**Objetivo:** Compartir una carpeta validando por la IP del cliente.
* **En el Servidor:**
  1. `apt install nfs-kernel-server` ➔ Instala el servicio.
  2. `mkdir /carpeta && chmod 777 /carpeta` ➔ Crea el directorio y da permisos Linux.
  3. `nano /etc/exports` ➔ Añades la línea de exportación: `/carpeta 192.168.1.0/24(rw,sync,no_subtree_check)`. *(¡Ojo! Sin espacio entre la IP y el paréntesis).*
  4. `exportfs -ra` ➔ Aplica los cambios sin reiniciar el demonio.
* **En el Cliente:**
  5. `apt install nfs-common` ➔ Instala el cliente.
  6. `mount -t nfs IP:/carpeta /mnt` ➔ Monta la carpeta remota como si fuera local.

🧠 **Regla Mnemotécnica: I.C.E.E.M. ("INStituto CREativo EDIta El AProbado MONumental")**
* **I**nstala ➔ **C**rea carpeta ➔ **E**xports (edita) ➔ **E**xportfs (aplica) ➔ **M**onta (cliente).

---

#### 3. SAMBA (Compartir carpetas Linux ↔ Windows)
**Objetivo:** Compartir archivos validando por Usuario y Contraseña.
1. `apt install samba` ➔ Instala el servicio.
2. `useradd -M -s /usr/sbin/nologin user1` ➔ Crea el usuario en Linux primero (sin acceso a terminal).
3. `smbpasswd -a user1` ➔ Añade el usuario a Samba y le asigna contraseña.
4. `nano /etc/samba/smb.conf` ➔ Creas el recurso al final del fichero: `[recurso] path=/carpeta`, `valid users=user1`, `read only=no`.
5. `testparm -s` ➔ Verifica que la sintaxis no tiene errores.
6. `systemctl restart smbd nmbd` ➔ Reinicia los servicios para aplicar los cambios.

🧠 **Regla Mnemotécnica: I.U.S.C.T.R. ("Incluso Un Simio Conoce Tu Red")**
* **I**nstala ➔ **U**suario Linux ➔ **S**amba password ➔ **C**onfigura (smb.conf) ➔ **T**estea (testparm) ➔ **R**einicia.

---

#### 4. SSH (Acceso remoto seguro)
**Objetivo:** Conectarse remotamente a un servidor sin usar contraseña (usando par de claves).
* **En el Servidor:**
  1. `apt install openssh-server` ➔ Instala el servicio para aceptar conexiones.
* **En el Cliente:**
  2. `ssh-keygen` ➔ Genera el par de claves (pública y privada).
  3. `ssh-copy-id usuario@IP` ➔ Copia tu clave pública al fichero `authorized_keys` del servidor.
  4. `ssh usuario@IP` ➔ Te conectas automáticamente sin contraseña.

🧠 **Regla Mnemotécnica: I.G.C.C. ("Igual Gano Con Claves")**
* **I**nstala (server) ➔ **G**enera (keygen) ➔ **C**opia (copy-id) ➔ **C**onecta (ssh).

---

### 🚨 "TRAMPAS" QUE DEBES ESCRIBIR (Suman puntos extras)
Si añades estas mini-notas en el examen al lado de tus pasos, el profesor verá que dominas el tema:
* **NFS:** "Cuidado en `/etc/exports`, si pones un espacio entre la IP y el paréntesis `IP (rw)`, le das permiso de lectura a la IP y de escritura a *todo el mundo*".
* **Samba:** "Regla de oro: El usuario *siempre* debe existir primero en Linux (`useradd`) antes de meterlo en Samba (`smbpasswd`)".
* **Samba 2:** "Los permisos que prevalecen son siempre los más restrictivos entre Linux (`chmod`) y Samba (`smb.conf`)".
* **SSH:** "La clave que se envía al servidor es *siempre* la pública (`.pub`). La privada nunca sale del cliente".