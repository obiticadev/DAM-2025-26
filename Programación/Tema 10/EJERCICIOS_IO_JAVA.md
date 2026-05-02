<a id="indice"></a>
# Ejercicios — Flujo de trabajo Java I/O
### UT10 · Programación — DAM 2025-26

> **Contexto del enunciado:**
> Todos los ejercicios giran en torno a una aplicación de gestión de una **biblioteca**.
> La entidad central es `Libro`, con los campos: `int id`, `String titulo`, `String autor`, `double precio`, `boolean disponible`.
> Los ficheros de datos se llaman `libros.csv`, `libros.bin` y `libros.ser` según el bloque.
> El formato CSV es: `id;titulo;autor;precio;disponible`

---

## Índice — el mapa completo

| # | Ejercicio | Parte |
|---|-----------|-------|
| [Ejercicio 0](#ejercicio-0) | Clase `Libro` — base común para todos los ejercicios | Preparación |
| **PARTE I — REFERENCIAR EL SISTEMA DE FICHEROS** | *`File` y `Path`/`Files`: explorar, crear y gestionar rutas* | |
| [Ejercicio 1.1](#ejercicio-11) | Explorar el directorio de la biblioteca con `File` | PARTE I |
| [Ejercicio 1.2](#ejercicio-12) | Crear la estructura de directorios con `File` | PARTE I |
| [Ejercicio 2.1](#ejercicio-21) | Verificar y copiar ficheros con NIO | PARTE I |
| [Ejercicio 2.2](#ejercicio-22) | Listar y clasificar contenidos con `Files.list()` | PARTE I |
| **PARTE II — FLUJOS DE TEXTO** | *`FileWriter` y `FileReader`/`BufferedReader`: leer y escribir texto* | |
| [Ejercicio 3.1](#ejercicio-31) | Exportar la biblioteca a CSV con `FileWriter` | PARTE II |
| [Ejercicio 3.2](#ejercicio-32) | Registro de operaciones (log) con `FileWriter` en modo append | PARTE II |
| [Ejercicio 4.1](#ejercicio-41) | Importar libros desde CSV con `BufferedReader` | PARTE II |
| [Ejercicio 4.2](#ejercicio-42) | Buscar libros por autor leyendo el CSV | PARTE II |
| **PARTE III — DATOS BINARIOS Y SERIALIZACIÓN** | *`DataOutputStream` y `ObjectOutputStream`: persistencia binaria* | |
| [Ejercicio 5.1](#ejercicio-51) | Guardar y recuperar libros en binario con Data streams | PARTE III |
| [Ejercicio 5.2](#ejercicio-52) | Ciclo completo: CSV → binario → recuperar | PARTE III |
| [Ejercicio 6.1](#ejercicio-61) | Serializar y deserializar la lista de libros | PARTE III |
| [Ejercicio 6.2](#ejercicio-62) | App completa con patrón cargar al inicio / guardar al salir | PARTE III |

---

<a id="ejercicio-0"></a>
## Ejercicio 0 — Clase `Libro`: la base de todos los ejercicios

[↑ Volver al índice](#indice)

Antes de empezar, crea la clase `Libro` que usarás en todos los ejercicios siguientes.

- **a)** Define la clase `Libro` con los campos: `int id`, `String titulo`, `String autor`, `double precio`, `boolean disponible`.
- **b)** Añade un constructor con los cinco parámetros en ese orden.
- **c)** Añade getters para todos los campos.
- **d)** Sobreescribe `toString()` para que devuelva una cadena con el formato:
  `[id] titulo — autor | precio € | disponible: true/false`
- **e)** Añade un método estático `desdeCSV(String linea)` que reciba una línea CSV (`id;titulo;autor;precio;disponible`) y devuelva un objeto `Libro`. Usa `Boolean.parseBoolean()` para el último campo.

---

<a id="ejercicio-11"></a>
## Ejercicio 1.1 — Explorar el directorio de la biblioteca con `File`

[↑ Volver al índice](#indice)

Tienes un directorio llamado `biblioteca/` en el directorio actual que puede contener ficheros de datos y subdirectorios.

Implementa el método `void explorarBiblioteca(String ruta)` que:

- **a)** Comprueba si la ruta recibida existe y es un directorio. Si no cumple ambas condiciones, imprime `"Directorio no válido: " + ruta` y termina el método.
- **b)** Lista todos los elementos del directorio. Para cada elemento imprime una línea con el formato:
  `[FICHERO]  nombre_fichero  (tamaño bytes)` o `[DIR]     nombre_directorio`
- **c)** Imprime al final un resumen con el número total de ficheros, el número total de subdirectorios y el tamaño acumulado de todos los ficheros en bytes.
- **d)** Si el directorio está vacío (ningún elemento), imprime `"El directorio está vacío."` en lugar del listado.

---

<a id="ejercicio-12"></a>
## Ejercicio 1.2 — Crear la estructura de directorios con `File`

[↑ Volver al índice](#indice)

Implementa el método `void inicializarDirectorios()` que usa **exclusivamente la clase `File`** (sin NIO) para:

- **a)** Comprobar si existe el directorio `datos/`. Si no existe, crearlo. Imprime `"Creado: datos/"` o `"Ya existe: datos/"` según corresponda.
- **b)** Hacer lo mismo para `datos/exportaciones/` y `datos/copias/`.
- **c)** Dentro de `datos/`, crear el fichero vacío `libros.csv` solo si no existe previamente. Captura la `IOException` que puede lanzar `createNewFile()`.
- **d)** Listar el contenido de `datos/` al final, mostrando nombre y tipo (fichero/directorio) de cada elemento.

---

<a id="ejercicio-21"></a>
## Ejercicio 2.1 — Verificar y copiar ficheros con NIO

[↑ Volver al índice](#indice)

Implementa el método `void hacerCopiaSeguridad(String rutaOrigen, String rutaDestino)` que:

- **a)** Comprueba con NIO si `rutaOrigen` existe y es un fichero regular. Si no lo es, imprime un mensaje y termina.
- **b)** Comprueba si el directorio padre de `rutaDestino` existe; si no, créalo con `Files.createDirectories()`.
- **c)** Copia el fichero de origen al destino. Si el fichero destino ya existe, lo reemplaza sin error.
- **d)** Tras la copia, verifica que el fichero destino existe y que su tamaño en bytes coincide con el origen. Imprime `"Copia OK: X bytes"` o `"Error: tamaños no coinciden"`.

> Pista: `Files.size(path)` devuelve el tamaño de un fichero en bytes.

---

<a id="ejercicio-22"></a>
## Ejercicio 2.2 — Listar y clasificar contenidos con `Files.list()`

[↑ Volver al índice](#indice)

Implementa el método `void clasificarContenido(String rutaDirectorio)` que usa NIO para:

- **a)** Verificar que `rutaDirectorio` existe y es un directorio. Si no, lanzar (o imprimir) un aviso y terminar.
- **b)** Obtener todos los elementos del directorio con `Files.list()` y separarlos en dos listas: una de ficheros regulares y otra de subdirectorios.
- **c)** Imprimir primero la sección `=== FICHEROS ===` con el nombre y el tamaño de cada fichero, y luego `=== SUBDIRECTORIOS ===` con el nombre de cada subdirectorio.
- **d)** Filtrar e imprimir por separado solo los ficheros cuya extensión sea `.csv`.

> Recuerda cerrar el `Stream<Path>` que devuelve `Files.list()` — usa try-with-resources: `try (Stream<Path> stream = Files.list(...))`.

---

<a id="ejercicio-31"></a>
## Ejercicio 3.1 — Exportar la biblioteca a CSV con `FileWriter`

[↑ Volver al índice](#indice)

Implementa el método `void exportarCSV(List<Libro> libros, String ruta, boolean sobrescribir)` que:

- **a)** Si `sobrescribir` es `true`, abre el fichero en modo normal (borra el contenido previo) y escribe primero la cabecera `id;titulo;autor;precio;disponible`.  
  Si `sobrescribir` es `false`, abre en modo append y **no** escribe la cabecera (se asume que ya existe).
- **b)** Escribe cada libro de la lista como una línea CSV. El campo `disponible` debe escribirse como `"true"` o `"false"`.
- **c)** Al terminar, imprime cuántas líneas se han escrito (sin contar la cabecera).
- **d)** Si la lista recibida está vacía, no abras el fichero ni escribas nada; imprime `"Lista vacía, no se exporta."` y termina.

---

<a id="ejercicio-32"></a>
## Ejercicio 3.2 — Registro de operaciones (log) con `FileWriter` en modo append

[↑ Volver al índice](#indice)

Implementa la clase `Logger` con un único método estático `void registrar(String operacion, String detalle)` que:

- **a)** Abre el fichero `datos/operaciones.log` en modo **append** (sin borrar entradas previas).
- **b)** Escribe una línea con el formato: `[YYYY-MM-DD HH:mm:ss] OPERACION | detalle`  
  Usa `LocalDateTime.now()` y `DateTimeFormatter` para obtener la fecha/hora con ese formato exacto.
- **c)** Cierra el fichero correctamente después de cada llamada (cada registro es una apertura/escritura/cierre independiente).
- **d)** Escribe un `main` que llame a `registrar` tres veces con distintas operaciones (`"ALTA"`, `"BAJA"`, `"CONSULTA"`) y luego imprima el contenido completo del log usando `Files.readString()`.

---

<a id="ejercicio-41"></a>
## Ejercicio 4.1 — Importar libros desde CSV con `BufferedReader`

[↑ Volver al índice](#indice)

Implementa el método `List<Libro> importarCSV(String ruta)` que:

- **a)** Abre el fichero CSV con `BufferedReader` + `FileReader`, salta la primera línea (cabecera) y lee el resto.
- **b)** Por cada línea, crea un objeto `Libro` usando el método estático `Libro.desdeCSV(linea)` del Ejercicio 0.
- **c)** Devuelve la lista de libros cargados. Si el fichero no existe, imprime `"No se encontró el fichero: " + ruta` y devuelve una lista vacía.
- **d)** Después de importar, imprime por consola cuántos libros están disponibles y cuántos no, de entre los cargados.

---

<a id="ejercicio-42"></a>
## Ejercicio 4.2 — Buscar libros por autor leyendo el CSV

[↑ Volver al índice](#indice)

Implementa el método `List<String> buscarPorAutor(String ruta, String autor)` que:

- **a)** Lee el CSV línea a línea con `BufferedReader`, saltando la cabecera.
- **b)** Para cada línea, extrae el campo `autor` (índice 2) y compara con el parámetro `autor` de forma **insensible a mayúsculas** (`equalsIgnoreCase`).
- **c)** Si coincide, añade el `titulo` del libro (índice 1) a la lista resultado.
- **d)** Devuelve la lista de títulos encontrados. Si no se encuentra ninguno, devuelve lista vacía.
- **e)** Escribe un `main` que llame al método con un autor concreto e imprima los títulos encontrados o `"No se encontraron libros de: " + autor` si la lista está vacía.

---

<a id="ejercicio-51"></a>
## Ejercicio 5.1 — Guardar y recuperar libros en binario con Data streams

[↑ Volver al índice](#indice)

Implementa dos métodos que trabajen con el fichero `libros.bin`:

- **a)** `void guardarBinario(List<Libro> libros, String ruta)` — escribe cada libro en binario con este orden exacto de campos:
  `writeInt(id)` → `writeUTF(titulo)` → `writeUTF(autor)` → `writeDouble(precio)` → `writeBoolean(disponible)`
- **b)** `List<Libro> leerBinario(String ruta)` — lee el fichero binario reconstruyendo cada `Libro` en el mismo orden. Usa `while (dis.available() > 0)` como condición del bucle. Devuelve la lista completa.
- **c)** Escribe un `main` que cree una lista de 3 libros, los guarde con `guardarBinario`, los recupere con `leerBinario` y los imprima con `toString()`.
- **d)** ¿Qué ocurriría si en `leerBinario` cambiaras `readUTF()` por `readDouble()` en la segunda lectura? Responde en un comentario en el código.

---

<a id="ejercicio-52"></a>
## Ejercicio 5.2 — Ciclo completo: CSV → binario → recuperar

[↑ Volver al índice](#indice)

Encadena los métodos de los bloques anteriores para implementar `void cicloCompletoBinario()`:

- **a)** Lee el fichero `libros.csv` usando `importarCSV` del Ejercicio 4.1.
- **b)** Guarda la lista obtenida en `libros.bin` usando `guardarBinario` del Ejercicio 5.1.
- **c)** Recupera la lista desde `libros.bin` usando `leerBinario` del Ejercicio 5.1.
- **d)** Compara el tamaño de la lista original (leída del CSV) con la recuperada del binario. Imprime `"OK: X libros guardados y recuperados correctamente."` si coinciden, o `"ERROR: se guardaron X pero se recuperaron Y."` si no.
- **e)** Compara el tamaño en bytes de `libros.csv` y `libros.bin` usando `Files.size()`. Imprime qué fichero ocupa más y por cuántos bytes.

---

<a id="ejercicio-61"></a>
## Ejercicio 6.1 — Serializar y deserializar la lista de libros

[↑ Volver al índice](#indice)

- **a)** Modifica la clase `Libro` del Ejercicio 0 para que implemente `Serializable`. Añade un campo `transient String resumenCache` que represente un resumen precalculado y que **no** deba persistirse.
- **b)** Implementa `void serializar(List<Libro> libros, String ruta)` que guarda la lista completa en el fichero indicado con `ObjectOutputStream`.
- **c)** Implementa `List<Libro> deserializar(String ruta)` que carga la lista desde el fichero. Separa `FileNotFoundException` (primera ejecución, devuelve lista vacía) de los demás `IOException` y `ClassNotFoundException`.
- **d)** Tras deserializar, comprueba que el campo `resumenCache` de cada libro es `null` (por ser `transient`) e imprime `"Campo transient correctamente ignorado."` si es así.
- **e)** Escribe un `main` que serialice una lista, la deserialice y verifique que el primer libro recuperado tiene los mismos valores de `titulo` y `precio` que el original.

---

<a id="ejercicio-62"></a>
## Ejercicio 6.2 — App completa con patrón cargar al inicio / guardar al salir

[↑ Volver al índice](#indice)

Implementa la clase `AppBiblioteca` que gestiona la lista de libros con persistencia por serialización:

- **a)** La clase tiene un atributo `List<Libro> catalogo` y una constante `FICHERO = "libros.ser"`. Implementa `cargar()` con el patrón descrito en el Bloque 6 de la guía.
- **b)** Implementa `guardar()` que persiste el catálogo completo.
- **c)** Implementa `void altaLibro(String titulo, String autor, double precio)` que añade un libro nuevo a la lista con un id autoincremental (`catalogo.size() + 1`) y disponibilidad `true`.
- **d)** Implementa `void bajaLibro(int id)` que elimina de la lista el libro con ese id usando `removeIf`. Si no existe ningún libro con ese id, imprime `"Libro no encontrado."`.
- **e)** Implementa `ejecutar()` que: carga el catálogo, da de alta 2 libros nuevos, lista todos los libros del catálogo con `toString()`, elimina el primero por id, vuelve a listar y guarda antes de terminar.

---

*Fin del cuaderno de ejercicios — UT10 Flujo de trabajo Java I/O · Programación DAM 2025-26*
