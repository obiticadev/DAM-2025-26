# 🐧 Guía de Referencia de Comandos Linux

## 1. Gestión de Archivos y Espacio
Herramientas para crear archivos vacíos o reservar espacio en disco rápidamente.

| Comando | Opción | Descripción |
| :--- | :--- | :--- |
| **`fallocate`** | `-l [tamaño]` | Reserva espacio real en disco para un archivo sin escribir datos (muy rápido). |
| | | *Ej: `fallocate -l 1G archivo_grande.img` (Crea un archivo de 1GB).* |
| | | *Ej: `fallocate -l +6M archivo` (Aumenta el tamaño en 6MB).* |

---

## 2. Búsqueda Avanzada (Find)
El comando `find` es extremadamente potente. Aquí se amplían sus capacidades más allá del nombre.

**Sintaxis:** `find [ruta] [criterios] [acción]`

| Criterio | Sintaxis / Ejemplo | Descripción |
| :--- | :--- | :--- |
| **Por Nombre** | `-name "archivo.txt"` | Búsqueda exacta (sensible a mayúsculas). |
| | `-iname "archivo.txt"` | Búsqueda insensible a mayúsculas/minúsculas. |
| **Por Tamaño** | `-size +10M` | Busca ficheros de **más** de 10 Megabytes. |
| | `-size -50k` | Busca ficheros de **menos** de 50 Kilobytes. |
| | `-size 1G` | Busca ficheros de **exactamente** 1 Gigabyte. |
| | *(Unidades: `c`=bytes, `k`=Kb, `M`=Mb, `G`=Gb)* | |
| **Por Tipo** | `-type f` | Busca solo **ficheros** normales. |
| | `-type d` | Busca solo **directorios**. |
| **Por Usuario** | `-user [nombre]` | Busca ficheros pertenecientes a un usuario específico. |
| **Por Tiempo** | `-mtime -7` | Modificados en los últimos 7 días. |
| **Acción** | `-delete` | **¡Cuidado!** Borra directamente lo que encuentra. |
| | `-exec [comando] {} \;` | Ejecuta un comando sobre cada archivo encontrado. |

---

## 3. Historial y Navegación (History)
Comandos para gestionar la memoria de la terminal.

| Comando / Tecla | Descripción |
| :--- | :--- |
| **`history`** | Muestra la lista numerada de comandos pasados. |
| **`history -c`** | Borra el historial actual de la sesión. |
| **`history -d [n]`** | Borra una línea específica (*n*) del historial. |
| **`!n`** | Ejecuta el comando número *n*. |
| **`!!`** | Repite el último comando. |
| **`CTRL + R`** | **Búsqueda inversa**: Permite buscar en el historial escribiendo partes del comando. |

---

## 4. Visualización de Ficheros
Herramientas para leer contenido.

| Comando | Opción Extra | Descripción |
| :--- | :--- | :--- |
| **`cat`** | `-n` | Muestra el contenido numerando todas las líneas. |
| | `-b` | Numera solo las líneas que tienen texto (ignora las vacías). |
| **`more`** | *`ESPACIO`* | Avanza una página. |
| **`less`** | *`q`* | Sale del modo visualización. |
| | *`/palabra`* | Busca una palabra dentro del archivo. |

---

## 5. Cabecera y Pie (Head & Tail)
Ideales para logs o archivos muy grandes.

| Comando | Opción Avanzada | Descripción |
| :--- | :--- | :--- |
| **`head`** | `-n [número]` | Muestra las primeras *n* líneas. |
| | `-c [bytes]` | Muestra los primeros *n* bytes del archivo. |
| **`tail`** | `-n [número]` | Muestra las últimas *n* líneas. |
| | **`-f`** | **(Follow)**: Muestra en tiempo real las nuevas líneas que se añaden al archivo (esencial para logs). |

---

## 6. Ordenación y Filtrado (Sort)
Organización de datos de texto.

| Opción | Descripción | Ejemplo |
| :--- | :--- | :--- |
| **`-n`** | Orden numérico (1, 2, 10) en lugar de alfabético (1, 10, 2). | `sort -n` |
| **`-r`** | Orden inverso (descendente). | `sort -r` |
| **`-u`** | **(Unique)**: Elimina las líneas duplicadas en la salida. | `sort -u` |
| **`-k [n]`** | Ordena por la columna número *n*. | `sort -k 3` |
| **`-t "[char]"`** | Define un separador de columnas distinto al espacio (útil para CSV). | `sort -t "," -k 2` |
| **`-o [file]`** | Guarda la salida ordenada en el mismo archivo (o uno nuevo). | `sort archivo -o archivo` |

---

## 7. Conteo Estadístico (WC)
Métricas sobre el contenido.

| Opción | Descripción |
| :--- | :--- |
| **(sin opción)** | Muestra líneas, palabras y bytes. |
| **`-l`** | Muestra solo líneas (Lines). |
| **`-w`** | Muestra solo palabras (Words). |
| **`-c`** | Muestra el número de **bytes**. |
| **`-m`** | Muestra el número de **caracteres** (útil si hay tildes/UTF-8). |
| **`-L`** | Longitud de la línea más larga del archivo. |

---

## 8. Recorte de Texto (Cut)
Extracción vertical de datos.

| Opción | Descripción | Ejemplo |
| :--- | :--- | :--- |
| **`-d "[sep]"`** | Define el delimitador (por defecto es tabulador). | `cut -d ":"` |
| **`-f [n]`** | Selecciona campos (Fields). Admite listas y rangos. | `cut -f 1,3` (Campos 1 y 3) <br> `cut -f 1-5` (Del 1 al 5) |
| **`-c [n]`** | Recorta por posición de carácter, ignorando delimitadores. | `cut -c 1-4` (Primeros 4 chars) |
| **`--complement`** | Muestra todo *excepto* lo seleccionado. | `cut -f 1 --complement` |

---

## 9. Redirecciones y Tuberías
Conectando la entrada y salida de comandos.

| Símbolo | Nombre | Descripción Completa |
| :--- | :--- | :--- |
| **`>`** | Redirección (Sobrescribir) | Guarda la salida en un fichero. Si existe, **lo borra** y escribe encima. |
| **`>>`** | Redirección (Añadir) | Añade la salida al **final** del fichero sin borrar lo anterior (Append). |
| **`\|`** | Tubería (Pipe) | Pasa la salida del comando izquierdo como entrada del comando derecho. |
| **`2>`** | Redirección de Errores | Guarda solo los mensajes de error en un fichero. |