# 📦 Cheatsheet: Empaquetado y Compresión en Linux

## 1. El comando `tar` (Archivar)
El comando `tar` permite agrupar varios ficheros o directorios en un solo paquete (archivo). Por sí mismo, solo empaqueta, pero puede combinarse con compresores.

### Flags / Opciones comunes
| Opción | Descripción |
| :---: | :--- |
| **`-c`** | **C**rea un nuevo archivo (paquete). |
| **`-x`** | E**x**trae el contenido de un paquete. |
| **`-t`** | Lis**t**a el contenido (muestra qué hay dentro sin extraer). |
| **`-f`** | Indica el nombre del **f**ichero (archivo tar) a usar. |
| **`-v`** | **V**erbose: Muestra en pantalla el progreso de la operación. |
| **`-r`** | Añade (append) ficheros a un paquete ya existente. |
| **`-z`** | Comprime/Descomprime usando **gzip**. |
| **`-j`** | Comprime/Descomprime usando **bzip2**. |

---

## 2. Operaciones Básicas con `tar`

### Crear un paquete (`.tar`)
Agrupa archivos sin comprimir.
```bash
# Sintaxis: tar cvf [nombre_paquete] [archivos_origen]
$ tar cvf paquete.tar archivo1.txt archivo2.txt

# Empaquetar un directorio completo
$ tar cf escritorio.tar /home/usuario/Escritorio
```

### Listar contenido
Ver qué hay dentro del paquete sin descomprimirlo.
```bash
$ tar tf escritorio.tar
```

### Añadir archivos a un paquete existente
Si olvidaste incluir un archivo, puedes agregarlo después.
```bash
# Añade archivo3.txt a paquete.tar
$ tar rf paquete.tar archivo3.txt
```

### Extraer contenido
Recuperar los archivos del paquete.
```bash
# Extrae en el directorio actual
$ tar xvf escritorio.tar

# Extraer en una ruta específica (visto en Actividades)
$ tar xvf paquete.tar -C ~/Escritorio
```

---

## 3. El Compresor `gzip`
`gzip` (GNU ZIP) es una herramienta de software libre para comprimir archivos.
*   ⚠️ **Importante:** `gzip` solo comprime **archivos individuales**, no directorios. Para comprimir directorios, primero se deben empaquetar con `tar`.
*   **Extensión habitual:** `.gz`

### Comandos básicos
```bash
# Comprimir un fichero (el original se reemplaza por fichero.gz)
$ gzip fichero

# Descomprimir un fichero
$ gzip -d fichero.gz
# O alternativamente:
$ gunzip fichero.gz
```

---

## 4. `tar` + `gzip` (Los "Tarballs")
Es la combinación más común en Linux/Unix. Se crea un archivo empaquetado y comprimido a la vez.
*   **Extensiones:** `.tar.gz` o `.tgz`

### Crear un paquete comprimido
Se añade la opción **`-z`** al comando.
```bash
# Crear (c), Verbose (v), con Gzip (z), Archivo (f)
$ tar cvzf escritorio.tgz /home/usuario/Escritorio
```

### Extraer un paquete comprimido
```bash
# Extraer (x), Verbose (v), con Gzip (z), Archivo (f)
$ tar xvzf escritorio.tgz
```

> **Nota:** Si quisieras usar el formato **bzip2** (otra compresión popular), simplemente cambia la `z` por una `j`.
> *   Ejemplo: `tar cvjf archivo.tar.bz2 carpeta/`