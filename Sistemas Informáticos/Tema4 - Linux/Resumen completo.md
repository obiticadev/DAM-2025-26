# Examen Sistemas Tema 4-5-6


# **TEMA 4**

# 🐚 Comandos Básicos en GNU/Linux

## 1. Ayuda y Navegación

### **A. `man` (Manual)**

Sirve para ver la documentación y el manual de uso de cualquier comando del sistema.

- **Ejemplo:** `man pwd` (Muestra cómo usar el comando pwd). Para salir del manual, pulsa `q`.

### **B. `pwd` (Print Working Directory)**

Sirve para mostrar la ruta completa del directorio en el que te encuentras actualmente.

- **Ejemplo:** `pwd`

### **C. `cd` (Change Directory)**

Sirve para cambiar de un directorio a otro.

- **Ejemplos:**
    - `cd`: Te lleva directamente al *home* del usuario.
    - `cd ~`: Igual que el anterior (el símbolo `~` representa el *home*).
    - `cd /`: Te lleva a la raíz del sistema.
    - `cd ..`: Retrocede un nivel (va al directorio padre).
    - `cd /home/ruben`: Va a una ruta específica.

---

## 2. Gestión de Archivos y Carpetas

### **A. `ls` (List)**

Sirve para listar los archivos y carpetas dentro de un directorio.

- **Opciones:**
    - `a`: Muestra todos los archivos, incluidos los ocultos (los que empiezan por punto `.`).
    - `l`: Formato largo (muestra permisos, dueño, tamaño y fecha).
    - `al`: Combina ambas (listado largo de archivos ocultos).
    - `1`: Muestra la lista en una sola columna.
- **Ejemplo:** `ls -al /etc`

### **B. `mkdir` (Make Directory)**

Sirve para crear nuevas carpetas o directorios.

- **Ejemplo:** `mkdir daw` (Crea la carpeta "daw" en el lugar actual).

### **C. `cat` (Concatenate)**

Sirve para ver el contenido de un archivo de texto en la pantalla o para crear archivos nuevos mediante redirección.

- **Ejemplos:**
    - `cat /etc/passwd`: Muestra el contenido de ese archivo.
    - `cat > documento.txt`: Permite escribir texto en un archivo nuevo. (Para guardar y salir, pulsa `Ctrl + D` en una línea nueva).

### **D. `rm` / `rmdir` (Remove)**

Sirven para borrar archivos o directorios.

- **Opciones:**
    - `r`: Borrado **recursivo**, necesario para borrar carpetas que no están vacías.
- **Ejemplos:**
    - `rm archivo.txt`: Borra un archivo.
    - `rmdir carpeta_vacia`: Borra una carpeta solo si está vacía.
    - `rm -r carpeta`: Borra la carpeta y todo lo que tenga dentro.

### **E. `cp` (Copy)**

Sirve para copiar archivos o directorios de un lugar a otro.

- **Ejemplos:**
    - `cp doc1.txt /home/ruben/copseg/`: Copia el archivo a esa ruta.
    - `cp -r carpeta_original carpeta_copia`: Copia un directorio entero.

### **F. `mv` (Move / Rename)**

Sirve tanto para **mover** un archivo a otro lugar como para **renombrarlo**.

- **Ejemplos:**
    - `mv doc1 doc1.txt`: Cambia el nombre de "doc1" a "doc1.txt".
    - `mv sistemas /home/ruben/Escritorio`: Mueve la carpeta sistemas al Escritorio.

---

### 💡 Notas Importantes para el Examen:

- **Sensibilidad:** Linux distingue entre mayúsculas y minúsculas (`Documento.txt` no es lo mismo que `documento.txt`).
- **Redirección `>`:** El símbolo `>` sirve para enviar el resultado de un comando a un archivo (creándolo o sobrescribiéndolo).
- **Comodín :** Se usa para representar "cualquier cosa". Ejemplo: `ls *.txt` muestra todos los archivos que terminen en .txt.

# **TEMA 5**

# 🔍 Comandos de Manipulación y Búsqueda

## 1. Historial de Comandos

### **A. `history`**

Sirve para ver la lista de todos los comandos que has ejecutado anteriormente en la terminal.

- **Opciones y uso:**
    - `history [n]`: Muestra solo los últimos `n` comandos.
    - `history -c`: Borra todo el historial de la sesión.
    - `!n`: Ejecuta automáticamente el comando que tiene el número `n` en el historial.
    - `!!`: Ejecuta el último comando ejecutado.

---

## 2. Visualización de Ficheros

### **A. `cat` (Concatenar)**

Sirve para volcar todo el contenido de un fichero en la pantalla de golpe.

- **Ejemplo:** `cat /etc/passwd`
- **Truco:** También sirve para unir archivos: `cat f1 f2 > f_final`.

### **B. `more` / `less` (Paginadores)**

Sirven para leer archivos largos pantalla a pantalla.

- **`more`**: Paginación simple (solo hacia adelante).
- **`less`**: Paginación avanzada (permite subir, bajar y buscar texto). **Es mejor que `more`.**

### **C. `head` / `tail` (Cabecera y Cola)**

Sirven para ver solo el principio o el final de un archivo (10 líneas por defecto).

- **Opciones:**
    - `n`: Especifica el número de líneas a ver.
- **Ejemplos:**
    - `head -5 archivo.txt`: Muestra las 5 primeras líneas.
    - `tail -3 archivo.txt`: Muestra las 3 últimas líneas.

---

## 3\. Procesamiento de Texto
### **A. `sort` (Ordenar)**
Sirve para ordenar las líneas de un archivo de texto alfabética o numéricamente.
-   **Opciones:**
    -   `n`: Ordena de forma numérica.  
    -   `r`: Ordena en sentido inverso (descendente).
    -   `k[n]`: Ordena por una columna específica (la número `n`). 
    -   `o [fichero]`: Guarda el resultado directamente en un archivo.  
-   **Ejemplo:** `sort -r nombres.txt`

### **B. `wc` (Word Count)**

Sirve para contar cuántas líneas, palabras o bytes tiene un archivo.
-   **Opciones:**
    -   `l`: Cuenta solo las líneas.   
    -   `w`: Cuenta solo las palabras.    
    -   `L`: Muestra la longitud de la línea más larga.    
-   **Ejemplo:** `wc -l lista.txt`

### **C. `cut` (Cortar)**
Sirve para extraer columnas o partes específicas de cada línea de un archivo.
-   **Opciones:**
    -   `c`: Corta por caracteres (ej: `c1-5`).  
    -   `d`: Define el delimitador (ej: `d":"` para usar los dos puntos como separador).  
    -   `f`: Indica qué campo o columna quieres extraer (ej: `f1`).
    
-   **Ejemplo:** `cut -d":" -f1 /etc/passwd` (Muestra solo los nombres de usuario). 

### **D. Combinación de Comandos (`sort` + `cut`)**

Permite filtrar información específica de un archivo y presentarla de manera ordenada mediante el uso de tuberías (`|`).
-   **Metodología:** 
    -   Se utiliza `cut` para extraer la columna deseada.   
    -   Se pasa la salida mediante `|` al comando `sort` para organizarla.     
-   **Ejemplo:** `cut -d":" -f1 /etc/passwd | sort` (Extrae los nombres de usuario y los muestra ordenados alfabéticamente).  

### **E. Uso avanzado de `sort -k` (El fin de la confusión)**
El problema aparece cuando queremos ordenar por una columna específica. Aquí hay dos escenarios clave:

#### **1\. Si usas `cut` antes del `sort`**

Si con `cut` extraes **varias** columnas, `sort` recibirá esa nueva "tabla" pequeña. La `-k` de `sort` se referirá a las columnas que han sobrevivido al corte
-   **Ejemplo:** `cut -d":" -f1,3 /etc/passwd | sort -t":" -k2 -n`
    -   `cut -d":" -f1,3`: Extrae la columna 1 (usuario) y la 3 (UID).    
    -   `sort -t":"`: Le decimos a sort que el separador es `:`.    
    -   `-k2 -n`: Le decimos que ordene por la **segunda** columna de lo que ha quedado (el UID) de forma numérica. 

#### **2\. El truco: Usar `sort` sin `cut`**

A veces no necesitas `cut`. `sort` puede buscar la columna directamente usando `-t` (delimitador) y `-k` (columna). Es más limpio si quieres ver toda la línea pero ordenada por un campo interno.

-   **Opciones equivalentes:**
    -   En `cut` usas `-d`. En `sort` usas `-t`. Ambos sirven para definir el separador. 
    -   En `cut` usas `-f`. En `sort` usas `-k`. Ambos sirven para elegir la columna.
        
-   **Ejemplo:** `sort -t":" -k3 -n /etc/passwd` (Ordena todo el archivo de usuarios por el UID —columna 3— sin necesidad de cortar nada).

---

## 4. Búsqueda de Ficheros

### **A. `find` (Buscar)**

Sirve para localizar archivos o directorios en el sistema basándose en distintos criterios.

- **Opciones:**
    - `name`: Busca por el nombre exacto (distingue mayúsculas).
    - `iname`: Busca por el nombre ignorando mayúsculas/minúsculas.
    - `size`: Busca por tamaño (ej: `+5M` para más de 5 Megabytes).
- **Ejemplo:** `find /home/ruben -name "*.txt"` (Busca todos los .txt de ruben).

---

### 💡 Tip para el examen: Las Tuberías (`|`)

Este PDF menciona las tuberías. Sirven para pasar el resultado de un comando a otro.

- **Ejemplo:** `sort ruben.txt | head -1`
    - *(Primero ordena el archivo y luego el `head` se queda solo con la primera línea del resultado).*

---

# 📦 Empaquetado y Compresión (tar y gzip)

## 1. El comando `tar` (Tape Archive)

Sirve para **juntar** muchos archivos o carpetas en un solo archivo (llamado "paquete" o "tarball"). Por sí solo, `tar` **no reduce el tamaño**, solo agrupa.

### **Las 3 Operaciones Principales:**

Para usar `tar`, siempre debes elegir una de estas tres letras primero:

1. **`c` (Create):** Para **crear** un paquete nuevo.
2. **`x` (eXtract):** Para **extraer** (desempaquetar) el contenido.
3. **`t` (List):** Para **ver** qué hay dentro sin abrirlo.

### **Opciones obligatorias y útiles:**

- **`f` (File):** **Obligatoria**. Indica que vas a dar el nombre del archivo. Siempre va al final de las letras (ej: `cvf`).
- **`v` (Verbose):** Opcional. Te muestra en pantalla qué archivos se están procesando (es el "progreso").
- **`r` (Append):** Sirve para **añadir** un archivo a un paquete que ya existe.

### **Ejemplos prácticos para Ruben:**

- **Crear un paquete:** `tar cvf backup.tar /home/ruben/documentos`*(Crea un archivo llamado backup.tar con la carpeta de documentos).*
- **Añadir un archivo nuevo:** `tar rf backup.tar foto.jpg`
- **Ver el contenido:** `tar tf backup.tar`
- **Extraer el contenido:** `tar xvf backup.tar`
- **Extraer en una carpeta específica (`C`):** `tar xvf paquete.tar -C /home/ruben/Escritorio`*(La opción `C` cambia el destino de la extracción).*

---

## 2. El comando `gzip` (Compresión)

Sirve para **reducir el tamaño** de los archivos.

- **Ojo:** `gzip` solo comprime archivos sueltos. Si intentas comprimir una carpeta, te dará error. Por eso se usa junto con `tar`.

### **Funcionamiento:**

- **Comprimir:** `gzip ruben.txt`*(Resultado: el archivo pasa a llamarse `ruben.txt.gz` y el original desaparece).*
- **Descomprimir:** `gunzip ruben.txt.gz` o también `gzip -d ruben.txt.gz`.

---

## 3. El "Todo en Uno" (Empaquetar + Comprimir)

En el examen te pedirán crear archivos `.tar.gz` o `.tgz`. Estos archivos están agrupados y comprimidos a la vez. Para ello, añadimos la letra **`-z`** al comando `tar`.

### **Los comandos que más caen:**

- **Crear paquete comprimido:** `tar cvzf todo.tar.gz /home/ruben/fotos`
    - `c`: Crear.
    - `v`: Ver progreso.
    - `z`: Comprimir (gzip).
    - `f`: Archivo.
- **Extraer paquete comprimido:** `tar xvzf todo.tar.gz`
    - *(Basta con cambiar la `c` por la `x`)*.

---

## 💡 Resumen de letras para no liarse:

Para no fallar en el examen, apréndete estas combinaciones como "palabras":

1. **`cvf`**: Crear paquete normal.
2. **`xvf`**: Extraer paquete normal.
3. **`cvzf`**: Crear paquete comprimido (el más común).
4. **`xvzf`**: Extraer paquete comprimido.

**¿Qué pasa con `-j`?**
Si en lugar de `.gz` el archivo termina en `.bz2` (otro tipo de compresión), simplemente cambia la **`z`** por la **`j`**.

- Ejemplo: `tar cvjf archivo.tar.bz2 /carpeta`

### ⚠️ El error más común:

**El orden de los archivos:** Siempre va primero el nombre del **paquete que quieres crear** y después lo que quieres meter dentro.

- ✅ BIEN: `tar cf paquete.tar archivo1 archivo2`
- ❌ MAL: `tar cf archivo1 archivo2 paquete.tar` (Esto borraría tus archivos).

---

# 📦 Gestión de Software (APT y DPKG)

## 1. El Gestor APT (Advanced Packaging Tool)

Es la herramienta moderna que descarga paquetes de internet y **gestiona automáticamente las dependencias** (instala lo que el programa necesita para funcionar).

### **Comandos de Gestión Básica**

- **`apt update`**: Actualiza la lista de paquetes disponibles en los repositorios. **Siempre se hace antes de instalar nada.**
- **`apt install [paquete]`**: Descarga e instala un programa.
    - *Ejemplo:* `sudo apt install vlc`
- **`apt upgrade`**: Actualiza todos los programas instalados a su última versión.
    - *Nota:* A diferencia de `apt-get`, `apt upgrade` sí instala nuevas dependencias si es necesario.
- **`apt remove [paquete]`**: Desinstala un programa pero **mantiene** sus archivos de configuración.
- **`apt purge [paquete]`**: Desinstala el programa y **borra también** sus archivos de configuración. (Limpieza total).

### **Comandos de Búsqueda e Información**

- **`apt search [texto]`**: Busca programas relacionados con una descripción.
    - *Ejemplo:* `apt search "editor html"`
- **`apt list --installed`**: Muestra una lista de todos los programas que **ruben** tiene instalados.
- **`apt policy [paquete]`**: Sirve para ver qué versión tenemos instalada y cuáles hay disponibles en los repositorios.

---

## 2. Gestión de Repositorios

Los repositorios son los "servidores" o "tiendas" de donde el sistema baja el software.

- **Fichero principal:** `/etc/apt/sources.list` (Aquí se guardan las direcciones de los servidores).
- **`add-apt-repository`**: Sirve para añadir nuevas fuentes de software (PPAs).
    - **Opción `r`**: Sirve para eliminar un repositorio previamente añadido.
    - *Ejemplo:* `sudo add-apt-repository ppa:webupd8team/java`

---

## 3. El Comando `dpkg` (Debian Package)

Es el gestor de bajo nivel. Se usa para archivos que ya tenemos descargados (extensión **.deb**). **No descarga nada de internet ni arregla dependencias solo.**

- **Opciones principales:**
    - **`i` (Install)**: Instala un archivo .deb que ya tengamos en el equipo.
        - *Ejemplo:* `sudo dpkg -i google-chrome.deb`
    - **`r` (Remove)**: Desinstala el paquete.
    - **`P` (Purge)**: Desinstala y borra configuración.
    - **`l` (List)**: Lista los paquetes instalados (se suele usar con `grep`).
        - *Ejemplo:* `dpkg -l | grep ruben`

---

## 4. Diferencias Clave para el Examen

| Característica | APT | DPKG |
| --- | --- | --- |
| **¿Descarga de internet?** | **Sí**, usa repositorios. | **No**, necesita el archivo .deb local. |
| **¿Gestiona dependencias?** | **Sí**, las busca e instala solo. | **No**, si falta algo, da error. |
| **Uso principal** | Instalación diaria de programas. | Instalar paquetes descargados a mano. |

---

## 💡 Tips de "Actividades" (Página 4 del PDF)

1. **Buscar y descargar sin instalar:** Si quieres solo bajar el archivo sin instalarlo, se usa `sudo apt-get install -d [paquete]`. El archivo se guardará en `/var/cache/apt/archives/`.
2. **Reparar dependencias:** Si `dpkg` te da un error de dependencias, el comando "mágico" para arreglarlo suele ser `sudo apt install -f`.
3. **Localización de archivos:** Recuerda que los paquetes descargados por APT se almacenan temporalmente en la carpeta `/var/cache/apt/archives/`.

---

# 📜 Introducción a Shellscript

## 1. Conceptos Básicos

Un **script** es un fichero de texto que contiene una serie de órdenes y estructuras (bucles, condiciones) que Linux ejecuta de forma secuencial. Sirve para automatizar tareas.

- **Extensión:** Normalmente usan `.sh` (ej. `tarea_ruben.sh`), pero en Linux no es obligatorio; es solo una convención para que nosotros sepamos que es un script.

---

## 2. Métodos de Ejecución

Hay dos formas principales de hacer que un script funcione:

### **A. Como ejecutable (Con permisos)**

Para que el sistema trate al script como un programa, primero hay que darle permisos de ejecución.

1. **Dar permiso:** `chmod a+x ruben.sh` (o `chmod 755 ruben.sh`).
2. **Lanzarlo:** `./ruben.sh`
    - *Nota:* El `./` indica que el archivo está en el directorio actual. Sin esto, Linux lo buscará en las carpetas del sistema y no lo encontrará.

### **B. Usando el intérprete (Sin permisos)**

Si no quieres o no puedes cambiar los permisos, puedes llamar directamente al intérprete (bash o sh) y pasarle el archivo como argumento.

- **Comando:** `bash ruben.sh` o `sh ruben.sh`
- **Para qué sirve:** Ejecuta el contenido del archivo aunque no tenga el bit `x` de ejecución activado.

---

## 3. El Shebang (`#!`)

Es la línea más importante de un script. Debe ser siempre la **primera línea** del archivo.

- **Para qué sirve:** Indica al sistema operativo qué intérprete debe usar para leer el resto del archivo. Evita ambigüedades.
- **Sintaxis:** `#!` seguido de la ruta del programa.
- **Ejemplos:**
    - `#!/bin/bash` (Usa el intérprete Bash, el más común).
    - `#!/bin/sh` (Usa el intérprete de shell estándar, más antiguo).

---

## 4. Consultas del Sistema

### **A. `/etc/shells`**

Es un fichero de configuración del sistema.

- **Para qué sirve:** Lista todas las "shells" (intérpretes de comandos) que están instaladas y son válidas en el sistema actual.
- **Ejemplo para verlo:** `cat /etc/shells`

---

## 💡 Resumen rápido para el examen:

1. **Si el script de ruben no tiene permisos:** Se ejecuta con `bash ruben.sh`.
2. **Si el script de ruben tiene permisos:** Se ejecuta con `./ruben.sh`.
3. **¿Qué va primero en un script?** El shebang: `#!/bin/bash`.
4. **¿Cómo sé qué shells tengo?** Mirando el archivo `/etc/shells`.
5. **¿Para qué sirve Shellscript?** Para automatizar tareas, administrar procesos y configurar el sistema de forma rápida.

---

**Recuerda:** Para los ejercicios de examen, si te piden "hacer ejecutable el script", el comando que esperan es `chmod +x nombre_del_script`.

# **TEMA 6**

# 📝 Usuarios y Grupos en Linux

## 1. Gestión de Usuarios

### **A. Crear Usuarios**

Existen dos comandos principales. La diferencia es que `useradd` es un binario puro y `adduser` es un script más sencillo e interactivo.

- **`useradd`**: Crea un usuario desde la consola.
    - **Opciones:**
        - `g`: Define el grupo principal (debe existir).
        - `d`: Especifica la ruta del directorio de inicio.
        - `m`: Crea el directorio de inicio si no existe.
        - `s`: Define el intérprete de comandos (shell), ej: `/bin/bash`.
    - **Ejemplo:** `useradd -g profesores -d /home/juanjo -m -s /bin/bash juanjo`
- **`adduser`**: Versión más amigable e interactiva que `useradd`.
    - **Ejemplo:** `sudo adduser nombre_usuario`

### **B. Eliminar Usuarios**

- **`userdel`**: Elimina la cuenta de usuario.
    - **Opciones:**
        - `r`: Borra también el directorio de inicio y la cola de correo.
    - **Ejemplo:** `userdel -r beatriz`
- **`deluser`**: Comando alternativo (script) para borrar usuarios.

### **C. Modificar Usuarios**

- **`usermod`**: Cambia los parámetros de una cuenta ya existente.
    - **Opciones:**
        - `d`: Cambia el directorio de inicio.
        - `m`: (Usada con -d) Mueve el contenido del directorio antiguo al nuevo.
        - `g`: Cambia el grupo primario.
        - `l`: Cambia el nombre de la cuenta (login).
        - `G`: Asigna grupos adicionales (¡Cuidado! Sustituye los anteriores si no se usa con -a).
        - `a`: Se usa con `G` para **añadir** el grupo sin borrar los que ya tiene.
    - **Ejemplos:**
        - Cambiar home: `usermod -d /home/alumnos alumno01`
        - Cambiar nombre: `usermod -l nuevo_nombre viejo_nombre`

---

## 2. Gestión de Grupos

### **A. Crear Grupos**

- **`groupadd`** o **`addgroup`**: Crean un nuevo grupo en el sistema.
    - **Ejemplo:** `groupadd nombre-del-grupo`

### **B. Eliminar Grupos**

- **`groupdel`** o **`delgroup`**: Borran un grupo existente.
    - **Ejemplo:** `groupdel a37`

### **C. Modificar Grupos**

- **`groupmod`**: Modifica las propiedades del grupo.
    - **Opciones:**
        - `n`: Cambia el nombre del grupo.
    - **Ejemplo:** `groupmod -n nuevo_nombre antiguo_nombre`

---

## 3. Relación Usuario-Grupo (Añadir/Quitar)

Para gestionar quién pertenece a qué grupo, tienes varias opciones:

### **A. Añadir un usuario a un grupo (3 formas)**

1. **`usermod`**: `usermod -a -G nombre_grupo usuario` (La `a` es vital para no borrar sus otros grupos).
2. **`adduser`**: `adduser usuario nombre_grupo` (Muy sencillo de recordar).
3. **`gpasswd`**: `gpasswd -a usuario nombre_grupo`
    - **Opción:** `a` significa "add" (añadir).

### **B. Quitar un usuario de un grupo**

- **`deluser`**: Elimina a un usuario de un grupo específico sin borrar al usuario del sistema.
    - **Formato:** `deluser usuario grupo`
    - **Ejemplo:** `deluser ana a35`

---

## 4. Archivos de Configuración (Teoría rápida)

Si te preguntan dónde se guarda la configuración de estas herramientas:

- `/etc/adduser.conf`: Configuración del comando adduser.
- `/etc/deluser.conf`: Configuración del comando deluser.
- `/etc/group`: Donde se listan todos los grupos y sus miembros.

---

### 💡 Tip para el examen:

Si en un ejercicio te piden que un usuario pertenezca a varios grupos (como en la tabla de la página 6), el comando más seguro es:
`usermod -G grupo1,grupo2,grupo3 usuario`
(Esto sobrescribe la lista y lo deja exactamente en esos grupos). Si solo quieres añadir uno nuevo a los que ya tiene: `usermod -a -G grupo_nuevo usuario`.

---

# 🛡️ Permisos y Propiedad (chmod, chown, chgrp, umask)

## 1. Conceptos Clave (Referencia Rápida)

- **Usuarios:** `u` (dueño), `g` (grupo), `o` (otros), `a` (todos/all).
- **Permisos:** `r` (lectura), `w` (escritura), `x` (ejecución).
- **Valores Octales:** `r = 4`, `w = 2`, `x = 1`. (Ejemplo: `rwx` = 4+2+1 = **7**).

---

## 2. Comando `chmod` (Cambiar Permisos)

Sirve para modificar los permisos de acceso de un archivo o directorio.

- **Opciones:**
    - `R`: **Recursivo**. Aplica los cambios a la carpeta y a todo su contenido (subcarpetas y ficheros) de golpe.
- **Ejemplos (Modo Simbólico):**
    - `chmod u+x instalacion`: Da permiso de **ejecución** al **dueño**.
    - `chmod go+r archivo`: Da permiso de **lectura** al **grupo** y a **otros**.
    - `chmod g-x archivo`: Quita permiso de **ejecución** al **grupo**.
    - `chmod a+x archivo`: Da ejecución a **todos** (dueño, grupo y otros).
- **Ejemplo (Modo Octal):**
    - `chmod 750 directorio`:
        - 7 (rwx) para el dueño.
        - 5 (r-x) para el grupo.
        - 0 (---) para otros.

---

## 3. Comando `chown` (Cambiar Dueño)

Permite cambiar el usuario propietario y/o el grupo de un archivo. **Requiere `sudo`**.

- **Uso y Ejemplos:**
    - **Solo cambiar dueño:** `sudo chown ana documento.txt` (Ana pasa a ser la dueña).
    - **Cambiar dueño y grupo a la vez:** `sudo chown ana:alumnos documento.txt` (Usa `:` o `.` para separar usuario de grupo).
    - **Solo cambiar grupo:** `sudo chown :alumnos documento.txt` (Equivale a usar `chgrp`).

---

## 4. Comando `chgrp` (Cambiar Grupo)

Sirve específicamente para cambiar el grupo propietario de un archivo o carpeta.

- **Ejemplo:**
    - `sudo chgrp profesores examen.pdf`: Cambia el grupo del archivo a "profesores".

---

## 5. Comando `umask` (Máscara de Permisos)

Establece los permisos que tendrán por defecto los archivos y carpetas nuevos al ser creados.

- **Cómo funciona:** Resta el valor de la máscara a los permisos base del sistema.
    - **Base Ficheros:** 666 | **Base Directorios:** 777.
- **Cálculo (Ejemplo con umask 002):**
    - Ficheros: 666 - 002 = **664** (`rw-rw-r--`).
    - Directorios: 777 - 002 = **775** (`rwxrwxr-x`).
- **Cómo cambiarla:**
    - `umask 0022`: Cambia la máscara usando notación octal.
    - `umask u=rwx,g=rx,o=rx`: Cambia la máscara usando notación simbólica.
- **Nota importante:** Los cambios con `umask` solo duran mientras la terminal esté abierta (sesión actual).

---

### 💡 Tabla de Ayuda Octal para el Examen

| Valor | Binario | Permiso | Significado |
| --- | --- | --- | --- |
| **7** | 111 | `rwx` | Lectura, escritura y ejecución |
| **6** | 110 | `rw-` | Lectura y escritura |
| **5** | 101 | `r-x` | Lectura y ejecución |
| **4** | 100 | `r--` | Solo lectura |
| **0** | 000 | `---` | Ningún permiso |

---

# 🚀 Permisos Especiales en Linux

Estos permisos añaden un **cuarto dígito** (al principio) al código octal o letras específicas a la representación simbólica.

## 1. SUID (Set User ID) - Valor Octal: 4

Sirve para que un archivo binario se ejecute con los permisos del **propietario** del archivo, en lugar de los permisos del usuario que lo lanza.

- **Para qué sirve:** Permite que usuarios normales ejecuten tareas que requieren privilegios elevados (ej. cambiar su propia contraseña con el comando `passwd`).
- **Opciones / Simbología:**
    - Se representa con una **`s`** (o **`S`**) en la posición de ejecución del **dueño**.
    - `s` minúscula: El dueño ya tenía permiso `x`.
    - `S` mayúscula: El dueño NO tenía permiso `x`.
- **Ejemplos:**
    - **Modo Octal:** `chmod 4755 binario` (El 4 activa SUID).
    - **Modo Simbólico:** `chmod u+s binario`

## 2. SGID (Set Group ID) - Valor Octal: 2

Tiene dos funciones según dónde se aplique:

1. **En archivos:** Se ejecuta con los privilegios del **grupo** dueño.
2. **En directorios (más común):** Cualquier archivo o carpeta creado dentro **hereda el grupo** del directorio padre automáticamente.
- **Para qué sirve:** Fundamental para carpetas compartidas por un equipo de trabajo; asegura que todos los archivos nuevos pertenezcan al mismo grupo.
- **Opciones / Simbología:**
    - Se representa con una **`s`** (o **`S`**) en la posición de ejecución del **grupo**.
- **Ejemplos:**
    - **Modo Octal:** `chmod 2775 directorio` (El 2 activa SGID).
    - **Modo Simbólico:** `chmod g+s directorio`

## 3. Sticky Bit (Bit de Permanencia) - Valor Octal: 1

Se aplica casi exclusivamente a directorios donde muchos usuarios tienen permiso de escritura.

- **Para qué sirve:** Evita que un usuario pueda borrar o renombrar archivos de **otros usuarios**. Solo el dueño del archivo o el root pueden borrarlo. Es típico de la carpeta `/tmp`.
- **Opciones / Simbología:**
    - Se representa con una **`t`** (o **`T`**) en la posición de ejecución de **otros**.
- **Ejemplos:**
    - **Modo Octal:** `chmod 1777 /carpeta_publica` (El 1 activa Sticky Bit).
    - **Modo Simbólico:** `chmod +t /directorio` (también se puede usar `o+t`).

---

## 💡 Reglas de Oro para el Examen (Página 2 del PDF)

1. **Suma de valores:** Si quieres activar varios a la vez, suma los valores.
    - Ejemplo: SUID (4) + SGID (2) = **6**. Comando: `chmod 6755 archivo`.
2. **El dígito "fantasma":**
    - Si usas **3 dígitos** (ej. `chmod 755`), el sistema asume que el primer dígito es **0** y quita cualquier permiso especial previo.
    - Para mantener o poner permisos especiales, **siempre usa 4 dígitos**.
3. **Visualización en la terminal (`ls -l`):**
    - **Minúscula (`s`, `t`):** Todo bien, el archivo es ejecutable.
    - **Mayúscula (`S`, `T`):** Error o advertencia; el bit especial está puesto pero el archivo no tiene permiso de ejecución (`x`) en esa sección.

---

**Recuerda la jerarquía de los 4 dígitos:**`[Especial] [Dueño] [Grupo] [Otros]`*(Ejemplo: 4750 -> SUID activo, dueño rwx, grupo r-x, otros nada)*

---

# 📂 Sistema de Ficheros en Linux

## 1. Conceptos Fundamentales

El sistema de ficheros de Linux (como **ext3**) se organiza en tres partes:

1. **Superbloque:** Contiene la información general del sistema de ficheros.
2. **Tabla de inodos:** El "índice" del disco. Cada archivo tiene un inodo.
3. **Bloques de datos:** Donde realmente se guarda el contenido (múltiplos de 512 bytes).

### **El Inodo (El DNI del archivo)**

Es un número que identifica cada archivo. **No contiene el nombre**, pero sí contiene:

- ID de dispositivo y número de inodo.
- Propietario y Grupo.
- Permisos (modo de acceso).
- Marcas de tiempo (**atime** - acceso, **mtime** - modificación, **ctime** - alteración inodo).
- **Número de enlaces:** Cuántos nombres apuntan a ese inodo.

---

## 2. Estándar Jerárquico (FHS)

Linux no usa unidades (C:, D:), todo cuelga de la raíz `/`.

| Directorio | Para qué sirve (Explicación rápida) |
| --- | --- |
| **`/`** | **Raíz (Root):** El contenedor de todo el sistema. |
| **`/home`** | Carpetas personales de los usuarios. |
| **`/etc`** | Ficheros de **configuración** del sistema. |
| **`/bin`** | Aplicaciones binarias esenciales (comandos básicos para todos). |
| **`/sbin`** | Binarios del sistema exclusivos para el **superusuario** (root). |
| **`/media`** | Puntos de montaje para dispositivos extraíbles (USB, CD-ROM). |
| **`/mnt`** | Montaje temporal de sistemas de ficheros. |

---

## 3. Comandos de Gestión de Ficheros y Enlaces

### **A. `ln` (Crear enlaces)**

Sirve para crear un "acceso" o segundo nombre para un archivo.

- **Opciones:**
    - *(Sin opción)*: Crea un **Enlace Duro (Hard Link)**. Apunta al mismo inodo. Si borras el original, los datos siguen vivos.
    - `s`: Crea un **Enlace Simbólico (Soft Link)**. Es como un acceso directo. Si borras el original, el enlace se rompe.
- **Ejemplos:**
    - `ln archivo.txt enlace_duro`: Crea un nombre alternativo para el mismo archivo.
    - `ln -s archivo.txt acceso_directo`: Crea un puntero al nombre del archivo original.

### **B. `ls -i` (Ver Inodos)**

Sirve para listar archivos mostrando su número de identificación único (inodo).

- **Opciones:**
    - `i`: Muestra el número de inodo a la izquierda del nombre.
    - `l`: Listado largo (permisos, dueño, etc).
- **Ejemplo:**
    - `ls -li`: Útil para comprobar si dos archivos son enlaces duros (tendrán el mismo número de inodo).

### **C. `mkdir` (Crear directorios)**

Sirve para crear carpetas nuevas.

- **Ejemplo:**
    - `mkdir Enlaces`: Crea una carpeta llamada Enlaces.

---

## 4. Comparativa: Enlaces Físicos vs. Simbólicos

Esto suele ser **pregunta fija** de examen:

| Característica | Enlace Físico (Hard Link) | Enlace Simbólico (Soft Link / `-s`) |
| --- | --- | --- |
| **¿A qué apunta?** | Al **Inodo** directamente. | Al **Nombre** del archivo original. |
| **Si borras el original...** | Los datos **NO** se borran (mientras quede un enlace). | El enlace se rompe (puntero queda huérfano). |
| **Número de Inodo** | Es **igual** al del original. | Es **distinto** al del original. |
| **Visualización** | Parece un archivo normal. | En `ls -l` aparece como `enlace -> original`. |

---

### 💡 Tip para la actividad 1.4 del PDF:

Si ejecutas `rm doc1.txt` teniendo dos enlaces:

1. El **enlace duro** (`doc1.ln`) seguirá funcionando y mostrando el contenido porque el inodo sigue vivo.
2. El **enlace simbólico** (`doc1.lns`) dejará de funcionar y el sistema dirá: *"No existe el archivo o el directorio"*.

---

# ⏰ Programación de Tareas (Crontab)

## 1. El Comando `crontab`

Sirve para gestionar el fichero donde se programan las tareas automáticas (el "demon" cron las ejecuta en segundo plano).

- **Opciones:**
    - `e`: **Edita** el fichero crontab del usuario actual (abre un editor de texto).
    - `l`: **Lista** (muestra) el contenido del crontab actual.
    - `r`: **Elimina** el fichero crontab completo.
    - `u [usuario]`: Permite al root gestionar el crontab de **otro usuario**.
- **Ejemplos:**
    - `crontab -e` (Para programar tus tareas).
    - `sudo crontab -u ana -e` (El administrador edita las tareas de Ana).

---

## 2. Funcionamiento y Permisos

El sistema controla quién puede programar tareas mediante dos ficheros:

1. **`/etc/cron.allow`**: Si existe, **solo** los usuarios listados aquí pueden usar crontab.
2. **`/etc/cron.deny`**: Si el anterior no existe, los usuarios aquí listados tienen **prohibido** usarlo.

---

## 3. Cómo Programar (Sintaxis de los 5 Campos)

Cada línea de crontab tiene **5 campos de tiempo** seguidos del **comando** a ejecutar:

| Campo | Significado | Rango |
| --- | --- | --- |
| **1º** | Minuto | 0 - 59 |
| **2º** | Hora | 0 - 23 |
| **3º** | Día del Mes | 1 - 31 |
| **4º** | Mes | 1 - 12 (o jan, feb...) |
| **5º** | Día de la semana | 0 - 7 (0 y 7 son domingo) |

### **Caracteres Especiales:**

- (Asterisco): **Cualquier** valor (ej. "todos los minutos").
- (Guion): **Rango** de valores (ej. `1-5` en el 5º campo es de lunes a viernes).
- `,` (Coma): **Lista** de valores (ej. `1,15` en el 3º campo es el día 1 y el 15).
- `/` (Barra): **Intervalos** (ej. `/10` en el 1º campo es "cada 10 minutos").

---

## 4. Cadenas Especiales (Atajos)

En lugar de los 5 campos, puedes usar estas etiquetas:

- `@reboot`: Ejecuta una vez al arrancar el sistema.
- `@daily` o `@midnight`: Una vez al día (00:00).
- `@hourly`: Una vez cada hora (al minuto 0).
- `@weekly`: Una vez a la semana (domingo medianoche).

---

## 5. Ejemplos Prácticos de Programación

- **Ejecutar cada día a las 16:30:**`30 16 * * * /usr/bin/comando`
- **Cada 5 minutos entre las 12h y las 13h:**`/5 12 * * * /usr/bin/comando`
- **Todos los lunes a las 8:00 de la mañana:**`0 8 * * 1 /usr/bin/comando`
- **Días 1 y 15 de cada mes a las 22:00:**`0 22 1,15 * * /usr/bin/comando`

---

## 6. Directorios Predeterminados

Si no quieres usar `crontab -e`, puedes meter guiones (scripts) directamente en carpetas del sistema para que se ejecuten automáticamente:

- `/etc/cron.hourly/` (Cada hora)
- `/etc/cron.daily/` (Cada día)
- `/etc/cron.weekly/` (Cada semana)
- `/etc/cron.monthly/` (Cada mes)

### 💡 Tip para el examen:

Si el ejercicio te pide "Apagar el sistema a las 18:00", recuerda que el comando es `/sbin/shutdown -h now` y que debe ir en el crontab del **root** (o con sudo), porque un usuario normal no puede apagar el equipo.

- Línea: `0 18 * * * /sbin/shutdown -h now`

---

# 💾 Cuotas de Disco en Linux

## 1. Conceptos Fundamentales

Las cuotas permiten limitar el espacio en disco para que un usuario (como **ruben**) no llene el servidor.

- **Tipos de cuotas:**
    - **Por Bloques:** Limita el **tamaño/espacio** (MB/GB). 1 bloque = 1 KB.
    - **Por Inodos:** Limita la **cantidad de archivos** (ficheros/carpetas).
- **Tipos de límites:**
    - **SOFT (Suave):** Límite de advertencia. Se puede superar temporalmente.
    - **HARD (Duro):** Límite absoluto. Es imposible superarlo.

---

## 2. Preparación del Sistema

### **A. Editar `/etc/fstab`**

Sirve para indicar al sistema qué particiones deben tener cuotas activas.

- **Opciones:**
    - `usrquota`: Habilita cuotas para usuarios individuales.
    - `grpquota`: Habilita cuotas para grupos.
- **Ejemplo de línea en el fichero:**
    - `UUID=... /home ext4 defaults,usrquota,grpquota 0 2`

### **B. `mount -o remount` (Remontar)**

Sirve para aplicar los cambios realizados en `/etc/fstab` sin tener que apagar y encender el equipo.

- **Ejemplo:** `sudo mount -o remount /home`

---

## 3. Comandos de Activación y Gestión

### **A. `quotacheck` (Escanear y crear ficheros)**

Sirve para analizar la partición y crear los archivos de control donde se guardará la información de las cuotas (`aquota.user` y `aquota.group`).

- **Opciones:**
    - `c`: Crea los ficheros de cuota.
    - `u`: Escanea cuotas de usuario.
    - `m`: Fuerza el escaneo aunque la partición esté en uso.
- **Ejemplo:** `sudo quotacheck -cum /home`

### **B. `quotaon` / `quotaoff` (Activar/Desactivar)**

Sirve para encender o apagar el motor de cuotas una vez configurado.

- **Ejemplo:** `sudo quotaon /home`

### **C. `edquota` (Establecer límites)**

Sirve para asignar cuánto espacio o cuántos archivos puede usar un usuario concreto. Abre un editor de texto con los límites.

- **Ejemplo:** `sudo edquota ruben`

### **D. `quota` (Consultar usuario)**

Sirve para ver el estado actual del consumo de un usuario.

- **Opciones:**
    - `u`: Consultar por usuario.
- **Ejemplo:** `sudo quota -u ruben`

### **E. `repquota` (Informe general)**

Sirve para generar un reporte de todos los usuarios y grupos de una partición a la vez.

- **Ejemplo:** `sudo repquota /home`

---

## 💡 Tips para el examen:

1. **Cálculo de bloques:** Si te piden limitar a **ruben** a 1 GB, recuerda que 1 bloque = 1 KB. Por tanto, 1 GB son aproximadamente **1.048.576 bloques**.
2. **Tiempo de gracia:** Si **ruben** supera su límite **SOFT**, tiene un tiempo (normalmente 7 días) para borrar archivos antes de que el sistema le bloquee como si hubiera llegado al límite **HARD**.
3. **Archivos resultantes:** Al configurar cuotas, siempre deben aparecer en la raíz de la partición los archivos `aquota.user` y `aquota.group`.

---

# 🛠️ Herramientas de Sistema

## 1. Gestión y Visualización de Procesos

### **A. `ps` (Process Status)**

Sirve para informar sobre el estado de los procesos actuales.

- **Opciones:**
    - *(Sin opciones)*: Solo muestra procesos de la terminal actual.
    - `e`: Muestra **todos** los procesos del sistema.
    - `l`: Formato largo (muestra más detalles como el PID, PPID y prioridad NI).
- **Ejemplo:** `ps -le` (Muestra todos los procesos con detalles técnicos).

### **B. `pstree` (Process Tree)**

Sirve para mostrar los procesos en ejecución de forma jerárquica (en forma de árbol).

- **Ejemplo:** `pstree`

### **C. `jobs` (Trabajos)**

Sirve para listar los procesos que el usuario ha lanzado en segundo plano o que están detenidos.

- **Símbolos:** `+` (el más reciente), `-` (el segundo más reciente).
- **Ejemplo:** `jobs`

### **D. `top` (Table of Processes)**

Sirve para monitorizar en tiempo real los procesos, el uso de CPU y la memoria. Se refresca automáticamente.

- **Ejemplo:** `top`

---

## 2. Control de Ejecución (Primer y Segundo Plano)

### **A. `&` (Ampersand)**

Sirve para lanzar un proceso directamente en **segundo plano** (background), permitiendo seguir usando la terminal.

- **Ejemplo:** `sleep 100 &`

### **B. `fg` (Foreground)**

Sirve para traer al **primer plano** un proceso que estaba en segundo plano o detenido.

- **Ejemplo:** `fg 1` (Trae al primer plano el trabajo número 1).

### **C. `bg` (Background)**

Sirve para reanudar en **segundo plano** un proceso que ha sido detenido (con Ctrl+Z).

- **Ejemplo:** `bg 2` (Reanuda el trabajo número 2 en segundo plano).

---

## 3. Señales y Finalización de Procesos

### **A. `kill` (Enviar señales)**

Sirve para enviar señales a los procesos, generalmente para terminarlos.

- **Señales comunes:**
    - `15` (SIGTERM): Finalización controlada (por defecto).
    - `9` (SIGKILL): Mata el proceso de forma inmediata (forzoso).
    - `19` (SIGSTOP): Detiene/pausa el proceso (como Ctrl+Z).
    - `18` (SIGCONT): Continúa un proceso detenido (como bg).
- **Ejemplo:** `kill -9 18434` (Mata forzosamente el proceso con PID 18434).
- **Listar señales:** `kill -l`

---

## 4. Prioridad de Procesos (Niceness)

### **A. `nice` (Lanzar con prioridad)**

Sirve para ejecutar un programa con una prioridad específica (Rango: -20 el más prioritario a 20 el menos prioritario).

- **Nota:** Un usuario normal solo puede bajar la prioridad (valores > 0).
- **Ejemplo:** `nice -n 15 sleep 200 &` (Lanza el proceso con muy baja prioridad).

### **B. `renice` (Cambiar prioridad en ejecución)**

Sirve para modificar la prioridad de un proceso que ya se está ejecutando.

- **Ejemplo:** `renice 19 4447` (Cambia el proceso 4447 a la mínima prioridad).

---

## 5. Uso de Disco y Memoria

### **A. `df` (Disk Free)**

Sirve para ver el espacio disponible y usado en las unidades de disco montadas.

- **Opciones:**
    - `m`: Muestra el espacio en Megabytes.
    - `h`: Muestra el espacio en formato "humano" (Gigas, Megas).
- **Ejemplo:** `df -m`

### **B. `du` (Disk Usage)**

Sirve para ver cuánto espacio ocupa un directorio específico y sus subdirectorios.

- **Ejemplo:** `du /home/ruben`

### **C. `free` (Memoria libre)**

Sirve para ver el estado de la memoria RAM y el espacio de intercambio (Swap).

- **Ejemplo:** `free`

---

## 💡 Conceptos Teóricos Rápidos

- **Estados de un proceso:** **R** (ejecutando), **S** (durmiendo/esperando), **T** (detenido), **Z** (zombie - terminó mal).
- **PID:** Identificador único del proceso.
- **PPID:** PID del proceso padre que lo creó.
- **Atajos de teclado:**
    - `Ctrl + C`: Mata el proceso en primer plano (SIGINT).
    - `Ctrl + Z`: Detiene el proceso en primer plano (SIGSTOP).