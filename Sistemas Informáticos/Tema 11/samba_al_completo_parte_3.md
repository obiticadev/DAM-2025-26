


# Bootcamp Samba: De Novato a Pro - Parte 3: VFS, Auditoría, Seguridad Avanzada y Rendimiento

En esta última entrega, llevaremos la configuración de Samba al nivel de producción empresarial. Abordaremos cómo proteger los datos de borrados accidentales, cómo auditar qué usuario hace qué, directivas de seguridad adicionales y cómo exprimir el rendimiento de la red.

---

## 1. Módulos VFS (Virtual File System)

Samba permite "apilar" módulos VFS para añadir funcionalidades transparentes al sistema de archivos. Se definen con la directiva `vfs objects`.

### A. Papelera de Reciclaje en Red (`recycle`)
Por defecto, cuando un usuario de Windows borra un archivo en una red compartida, este desaparece para siempre. El módulo `recycle` intercepta el borrado y lo mueve a una carpeta oculta.

Añade esto a una sección de recurso (ej. `[Proyecto]`):

```ini
[Proyecto]
   path = /srv/samba/proyecto
   vfs objects = recycle
   
   # Carpeta donde irán los archivos borrados (se creará automáticamente en la raíz del recurso)
   recycle:repository = .papelera
   # Mantener la estructura de directorios original del archivo borrado
   recycle:keeptree = yes
   # Si se borran dos archivos con el mismo nombre, versionarlos (ej. archivo(1).txt)
   recycle:versions = yes
   # No enviar a la papelera archivos temporales o de ciertos tamaños
   recycle:exclude = *.tmp, *.log, ~$*
   recycle:exclude_dir = /tmp, /cache
```

### B. Auditoría de Accesos (`full_audit`)
Ideal para servidores donde necesitas saber exactamente quién borró, modificó o accedió a un archivo.

```ini
[Finanzas]
   path = /srv/samba/finanzas
   # Si usas papelera y auditoría, sepáralos por espacios
   vfs objects = recycle full_audit
   
   # Formato del log: Usuario | IP Cliente | Nombre Máquina | Nombre Recurso
   full_audit:prefix = %u|%I|%m|%S
   
   # Operaciones a registrar si tienen ÉXITO
   # (open, read, write, rename, unlink = borrar, mkdir, rmdir)
   full_audit:success = mkdir rmdir write rename unlink
   
   # Operaciones a registrar si FALLAN (ej. por falta de permisos)
   full_audit:failure = connect open
   
   # Enviar los logs al demonio syslog (local7 es una facilidad común)
   full_audit:facility = local7
   full_audit:priority = notice
```
*(Nota: Necesitarás configurar el servicio `rsyslog` de tu Linux para que capture `local7` y lo envíe a un archivo específico como `/var/log/samba-audit.log`).*

---

## 2. Ocultación y Restricción de Archivos

Los sistemas Windows y Mac crean archivos ocultos molestos (`Thumbs.db`, `.DS_Store`). Samba puede ocultarlos, denegar su lectura o incluso impedir que se escriban en el servidor.

Dentro de `[global]` (para todo el servidor) o en un recurso específico:

```ini
   # Ocultar todos los archivos que empiezan por punto (comportamiento estándar de Linux)
   hide dot files = yes
   
   # Archivos que se marcan con el atributo "Oculto" de Windows (los usuarios pueden verlos si configuran Windows para ver archivos ocultos)
   hide files = /desktop.ini/$RECYCLE.BIN/
   
   # VETO: Bloqueo absoluto. Samba niega la existencia de estos archivos. No se pueden leer ni escribir.
   veto files = /Thumbs.db/.DS_Store/._*/
   
   # Permitir que Samba borre directorios que contengan archivos vetados
   delete veto files = yes
```

---

## 3. Gestión de Enlaces Simbólicos (Symlinks)

Por motivos de seguridad, las versiones modernas de Samba previenen ataques de salto de directorio (Directory Traversal) bloqueando los "wide links" (enlaces simbólicos que apuntan fuera de la carpeta compartida).

Si **necesitas** compartir una carpeta y dentro poner un enlace simbólico que apunte a `/mnt/otro_disco`, debes configurarlo así en el bloque `[global]`:

```ini[global]
   # Permitir seguir enlaces simbólicos (por defecto es yes)
   follow symlinks = yes
   
   # Permitir enlaces que salgan fuera de la ruta compartida
   wide links = yes
   
   # IMPORTANTE: Para que 'wide links' funcione por razones de seguridad, 
   # debes desactivar las extensiones UNIX (afecta a clientes Linux/Mac)
   unix extensions = no
```
*Advertencia: Habilitar `wide links` supone un riesgo de seguridad si los usuarios tienen permisos para crear sus propios enlaces simbólicos en el servidor mediante SSH.*

---

## 4. Tuning: Optimización de Rendimiento de Red y Disco

Aunque Samba viene preconfigurado con valores razonables, en redes Gigabit, 10GbE y con discos SSD, puedes exprimir el rendimiento ajustando parámetros en el bloque `[global]`.

```ini
[global]
   # --- Protocolos de Red ---
   # Forzar uso exclusivo de SMB2 y SMB3 (Aumenta seguridad y rendimiento drásticamente)
   server min protocol = SMB2_10
   server max protocol = SMB3
   
   # --- Ajustes de Socket ---
   # TCP_NODELAY: Envía paquetes inmediatamente (reduce latencia)
   # SO_RCVBUF / SO_SNDBUF: Aumenta el tamaño del búfer (obsoleto en kernels nuevos de Linux, pero útil en sistemas antiguos)
   socket options = TCP_NODELAY IPTOS_LOWDELAY
   
   # --- Asynchronous I/O (AIO) ---
   # Permite a Samba leer y escribir en disco usando hilos asíncronos sin bloquear el servicio.
   # El valor define a partir de cuántos bytes (ej. 1MB = 1048576, o usar '1M' en versiones nuevas) 
   # se usa el modo asíncrono. Muy beneficioso para archivos grandes (video, ISOs).
   aio read size = 1M
   aio write size = 1M
   
   # --- Optimización de Disco ---
   # Asigna el espacio del archivo instantáneamente sin escribir ceros (depende del sistema de archivos, ideal en ext4 y xfs)
   strict allocate = yes
   
   # Acelera la transferencia de lectura saltándose el copiado de datos al espacio de usuario de Samba (directo de disco a tarjeta de red)
   use sendfile = yes
   
   # --- Caché de Directorios ---
   # Mantener nombres en caché al listar directorios masivos
   getwd cache = yes
   dir cache = yes
```

---

## 5. Recapitulación: Checklist de Producción

Antes de dar un servidor Samba por finalizado en un entorno de producción, revisa este checklist:

1. [ ] **Firewall:** ¿Solo puertos TCP 139, 445 abiertos desde subredes confiables?
2. [ ] **smb.conf:** ¿Has ejecutado `testparm` sin errores?
3. [ ] **Seguridad SMB:** ¿`server min protocol = SMB2` configurado?
4. [ ] **Permisos:** ¿Las carpetas tienen en Linux los grupos correctos (`chown`) y en Samba `valid users` especificados?
5. [ ] **Servicios:** ¿`systemctl enable smbd nmbd` aplicados para reinicios del servidor?