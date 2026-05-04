<a id="indice"></a>
# Guía de Estudio — Flujo de trabajo Java I/O
### UT10 · Programación — DAM 2025-26

> **Cómo usar este documento:**
> Está pensado para leerlo de arriba a abajo la primera vez, como si fuera una historia.
> Cada bloque explica UNA sola idea, con ejemplos reales y un ejercicio para consolidarla.
> Si los ficheros y los flujos de datos no sois muy amigos, empezad por el principio — cada bloque construye sobre el anterior.
> El índice te sirve como mapa: de un vistazo sabes exactamente dónde estás y qué queda.

---

## Índice — el mapa completo

| # | Bloque | Parte |
|---|--------|-------|
| [Bloque 0](#bloque-0) | ¿Qué es esto y por qué existe? — La idea de fondo | Contexto |
| **PARTE I — REFERENCIAR EL SISTEMA DE FICHEROS** | *`File` (IO clásico) vs `Path`/`Files` (NIO): dos formas de apuntar a una ruta y operar sobre ella* | |
| [Bloque 1](#bloque-1) | `File` — apuntar a una ruta con la API clásica | PARTE I |
| [Bloque 2](#bloque-2) | `Path` y `Files` NIO — la forma moderna de trabajar con rutas | PARTE I |
| **PARTE II — FLUJOS DE TEXTO** | *Leer y escribir ficheros de texto carácter a carácter con `FileReader` y `FileWriter`* | |
| [Bloque 3](#bloque-3) | `FileWriter` — escribir texto en un fichero | PARTE II |
| [Bloque 4](#bloque-4) | `FileReader` y `BufferedReader` — leer texto de un fichero | PARTE II |
| **PARTE III — DATOS BINARIOS Y SERIALIZACIÓN** | *Guardar primitivos con `DataOutputStream` y objetos enteros con `ObjectOutputStream`* | |
| [Bloque 5](#bloque-5) | `DataOutputStream` / `DataInputStream` — guardar primitivos en binario | PARTE III |
| [Bloque 6](#bloque-6) | Serialización — persistir objetos Java completos | PARTE III |
| [Apéndice](#apendice) | Errores típicos y trampas de examen | |

---

<a id="bloque-0"></a>
## Bloque 0 — ¿Qué es esto y por qué existe?

[↑ Volver al índice](#indice)

### Teoría

Imagina que tu programa tiene datos en memoria: una lista de productos, los resultados de unas elecciones, los alumnos de un curso. Cuando el programa termina, esa memoria se borra. La próxima vez que arranques el programa, los datos han desaparecido. El problema que resuelve Java I/O es exactamente ese: **persistir datos en disco** para que sobrevivan entre ejecuciones.

Pero guardar datos en disco implica decisiones: ¿los guardo como texto legible (para que un humano pueda abrirlo con el Bloc de notas)? ¿Los guardo en binario (más compacto y eficiente)? ¿Los guardo como objetos Java completos (para no tener que reconstruirlos manualmente)? Cada respuesta corresponde a una herramienta diferente en Java.

Java ofrece dos familias de clases para esto. La primera es **IO clásico** (`java.io`), que existe desde Java 1.0 y cubre casos como leer y escribir caracteres o primitivos. La segunda es **NIO** (`java.nio`), añadida en Java 7 y mejorada en Java 11, que aporta una API más limpia para crear ficheros, copiarlos, moverlos y leer su contenido completo de golpe. Ambas coexisten y se complementan: puedes pasar de una a la otra con `file.toPath()` y `path.toFile()`.

```
Problema: los datos en memoria desaparecen al cerrar el programa
        │
        ▼
¿Qué tipo de dato quiero guardar?
        │
        ├─► Texto legible (Strings, líneas)
        │       ├─► Escribir: FileWriter
        │       └─► Leer:    FileReader / BufferedReader
        │
        ├─► Primitivos (int, double, String UTF...)
        │       ├─► Escribir: DataOutputStream
        │       └─► Leer:    DataInputStream  (¡mismo orden que la escritura!)
        │
        └─► Objetos Java completos
                ├─► Escribir: ObjectOutputStream
                └─► Leer:    ObjectInputStream
                             (la clase debe implementar Serializable)

Operaciones sobre rutas (crear, copiar, mover, verificar existencia):
    IO clásico → File
    NIO moderno → Path + Files
```

> **La clave mental:** en Java I/O, la pregunta no es "¿cómo guardo datos?" sino "¿qué tipo de datos guardo?" — la respuesta determina qué clase usar.

---

<a id="bloque-1"></a>
## Bloque 1 — `File`: apuntar a una ruta con la API clásica

[↑ Volver al índice](#indice)

### Teoría

La clase `File` de `java.io` es, ante todo, una **referencia a una ruta**: puede apuntar a un fichero que existe, a uno que no existe todavía, o a un directorio. No lee ni escribe contenido por sí sola; simplemente representa la dirección postal de un fichero en el sistema operativo.

Piénsalo como la ficha de un apartamento en una agencia inmobiliaria: la ficha tiene la dirección, el tamaño, si está disponible y quién puede entrar… pero no es el apartamento en sí. `File` es esa ficha.

Para crear un objeto `File` tienes dos opciones principales: ruta relativa (respecto al directorio de trabajo del programa) o ruta absoluta:

```java
import java.io.File;

File relativo = new File("datos.txt");                      // relativo al directorio actual
File absoluto = new File("C:/ruta/al/proyecto/datos.txt");  // absoluto
```

Una vez tienes la referencia, puedes interrogarla con sus métodos:

| Método | Qué hace | Devuelve |
|--------|----------|----------|
| `exists()` | ¿Existe en disco? | `boolean` |
| `isFile()` | ¿Es un fichero (no directorio)? | `boolean` |
| `isDirectory()` | ¿Es un directorio? | `boolean` |
| `getName()` | Nombre del fichero con extensión | `String` |
| `getAbsolutePath()` | Ruta completa desde la raíz | `String` |
| `length()` | Tamaño en bytes | `long` |
| `canRead()` / `canWrite()` | Permisos de lectura/escritura | `boolean` |
| `list()` | Nombres de los contenidos (si es dir.) | `String[]` |
| `listFiles()` | Contenidos como objetos `File` | `File[]` |
| `mkdir()` | Crea el directorio | `boolean` |
| `createNewFile()` | Crea fichero vacío (lanza `IOException`) | `boolean` |
| `delete()` | Elimina el fichero o directorio vacío | `boolean` |
| `renameTo(File dest)` | Mueve/renombra | `boolean` |

El método más útil en ejercicios de listado es `listFiles()`, que devuelve un array de objetos `File` y te permite llamar a `isFile()` e `isDirectory()` sobre cada elemento:

```java
File dir = new File(".");
File[] contenidos = dir.listFiles();

if (contenidos != null) {
    for (File f : contenidos) {
        System.out.printf("%-30s | fichero: %b | dir: %b%n",
            f.getName(), f.isFile(), f.isDirectory());
    }
}
```

> **Error típico:** `listFiles()` devuelve `null` si la ruta no es un directorio o si ocurrió un error de E/S — no un array vacío. Comprueba siempre `!= null` antes de iterar.

### Ejercicio 1.1 — Explorar un directorio con `File`

*(Ejercicio de elaboración propia basado en el contenido teórico)*

Tienes una carpeta llamada `almacen` en el directorio actual. Implementa el método `explorarDirectorio(String ruta)` que:

- **a)** Comprueba si la ruta recibida existe y es un directorio. Si no lo es, imprime `"No es un directorio válido"` y termina.
- **b)** Lista todos los elementos del directorio imprimiendo: nombre, si es fichero o directorio, y tamaño en bytes (solo para ficheros; los directorios muestran `"-"`).
- **c)** Al final, imprime el número total de ficheros y de subdirectorios encontrados.

### Solución 1.1

```java
import java.io.File;

public static void explorarDirectorio(String ruta) {
    File dir = new File(ruta);

    // a)
    if (!dir.exists() || !dir.isDirectory()) {
        System.out.println("No es un directorio válido");
        return;
    }

    File[] contenidos = dir.listFiles();
    if (contenidos == null) return;

    int totalFicheros = 0;
    int totalDirs     = 0;

    // b)
    for (File f : contenidos) {
        String tipo    = f.isFile() ? "fichero" : "dir";
        String tamanio = f.isFile() ? f.length() + " bytes" : "-";
        System.out.printf("%-30s | %-7s | %s%n", f.getName(), tipo, tamanio);

        if (f.isFile()) totalFicheros++;
        else            totalDirs++;
    }

    // c)
    System.out.printf("%nTotal: %d fichero(s), %d directorio(s)%n", totalFicheros, totalDirs);
}
```

> **Razonamiento:** usamos `listFiles()` en vez de `list()` porque necesitamos llamar a `isFile()` y `length()` sobre cada elemento — `list()` solo devuelve `String[]` con nombres, insuficiente aquí. La segunda guarda `if (contenidos == null)` es defensa en profundidad: aunque ya comprobamos `isDirectory()`, `listFiles()` puede devolver `null` también por errores de permisos del SO.

---

<a id="bloque-2"></a>
## Bloque 2 — `Path` y `Files` NIO: la forma moderna de trabajar con rutas

[↑ Volver al índice](#indice)

### Teoría

`Path` (de `java.nio.file`) es el equivalente moderno de `File`. Representa una ruta en el sistema de ficheros, pero está mejor diseñado: es inmutable, trabaja bien con el sistema operativo subyacente y se usa junto a la clase utilitaria `Files`, que concentra todas las operaciones en un solo lugar.

Si `File` era la ficha del apartamento, `Path` es la dirección GPS y `Files` es la empresa de mudanzas y reformas que actúa sobre esa dirección.

Tienes tres formas de crear un `Path`, todas equivalentes:

```java
import java.nio.file.Path;
import java.nio.file.Paths;

Path p1 = Paths.get("datos.txt");                                        // relativo
Path p2 = Path.of("datos.txt");                                          // Java 11+, preferida
Path p3 = Paths.get(System.getProperty("user.home"), "docs", "a.txt");  // concatena segmentos
```

> **Diferencia crucial entre `Paths.get()` y `Path.of()`:** son funcionalmente idénticos desde Java 11. `Path.of()` es la forma canónica moderna. En Java 8–10 solo existe `Paths.get()`.

La clase `Files` proporciona métodos estáticos para todo lo que necesitas hacer con una ruta:

| Método | Qué hace |
|--------|----------|
| `Files.exists(path)` | Comprueba si la ruta existe |
| `Files.isRegularFile(path)` | ¿Es fichero regular (no directorio)? |
| `Files.isDirectory(path)` | ¿Es directorio? |
| `Files.createFile(path)` | Crea fichero vacío (falla si ya existe) |
| `Files.createDirectory(path)` | Crea un directorio (falla si falta algún intermedio) |
| `Files.createDirectories(path)` | Crea toda la cadena de directorios |
| `Files.readString(path)` | Lee todo el contenido como `String` (Java 11+) |
| `Files.readAllLines(path)` | Lee todas las líneas como `List<String>` |
| `Files.writeString(path, texto)` | Escribe un `String` en el fichero |
| `Files.write(path, bytes)` | Escribe bytes |
| `Files.copy(orig, dest, opciones)` | Copia fichero |
| `Files.move(orig, dest)` | Mueve o renombra |
| `Files.delete(path)` | Elimina |
| `Files.list(path)` | Devuelve `Stream<Path>` con los contenidos del directorio |

El flujo más habitual con NIO para verificar y leer un fichero es:

```java
import java.nio.file.*;
import java.io.IOException;

Path ruta = Path.of("datos.txt");

if (Files.exists(ruta)) {
    try {
        String contenido = Files.readString(ruta);   // lee TODO el fichero de golpe
        System.out.println(contenido);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

Para copiar un fichero reemplazando el destino si ya existe:

```java
Path origen  = Path.of("original.txt");
Path destino = Path.of("copia.txt");

Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);
```

> **Truco para el examen:** NIO es la herramienta correcta para operaciones sobre la ruta (crear, copiar, mover, verificar). IO clásico (`FileReader`, `DataOutputStream`...) es la herramienta correcta para leer/escribir el CONTENIDO del fichero carácter a carácter o primitivo a primitivo. No mezcles los roles.

### Ejercicio 2.1 — Gestionar una estructura de directorios con NIO

*(Ejercicio de elaboración propia basado en el contenido teórico)*

Implementa el método `inicializarEstructura()` que usa exclusivamente NIO para:

- **a)** Crear la ruta `datos/productos/` (toda la cadena) si no existe. No debe fallar si ya existe.
- **b)** Crear el fichero `datos/productos/catalogo.txt` solo si no existe, y escribir en él el texto `"# Catálogo de productos\n"`.
- **c)** Leer el contenido completo de `datos/productos/catalogo.txt` e imprimirlo por pantalla.
- **d)** Listar solo los ficheros regulares de `datos/productos/` imprimiendo su nombre.

### Solución 2.1

```java
import java.nio.file.*;
import java.io.IOException;

public static void inicializarEstructura() throws IOException {
    Path dirProductos = Path.of("datos", "productos");
    Path catalogo     = dirProductos.resolve("catalogo.txt");

    // a) createDirectories no falla si la cadena ya existe
    Files.createDirectories(dirProductos);

    // b)
    if (!Files.exists(catalogo)) {
        Files.createFile(catalogo);
        Files.writeString(catalogo, "# Catálogo de productos\n");
    }

    // c)
    String contenido = Files.readString(catalogo);
    System.out.println(contenido);

    // d)
    Files.list(dirProductos)
         .filter(Files::isRegularFile)
         .forEach(p -> System.out.println(p.getFileName()));
}
```

> **Razonamiento:** usamos `createDirectories` (plural) en a) porque crea toda la cadena de directorios intermedios en un solo paso y no lanza excepción si ya existen — `createDirectory` (singular) fallaría con `FileAlreadyExistsException`. En d), `Files.list()` devuelve un `Stream<Path>` que incluye tanto ficheros como subdirectorios, por eso filtramos con `Files::isRegularFile` para no mostrar las carpetas.

---

<a id="bloque-3"></a>
## Bloque 3 — `FileWriter`: escribir texto en un fichero

[↑ Volver al índice](#indice)

### Teoría

`FileWriter` es un flujo de **escritura de caracteres**: envía texto a un fichero carácter a carácter (o por cadenas enteras). Hereda de la clase abstracta `Writer` y es la pieza central cuando necesitas crear o actualizar un fichero de texto con contenido generado por tu programa.

Piénsalo como un bolígrafo conectado a un papel: una vez que lo abres (`new FileWriter(fichero)`), todo lo que escribas va al papel. Cuando lo cierras (`close()`), el papel queda sellado y listo para leer.

La forma correcta de usarlo es siempre con **try-with-resources**, que garantiza que el fichero se cierre aunque se produzca una excepción:

```java
import java.io.*;

File fichero = new File("salida.txt");

try (FileWriter fw = new FileWriter(fichero)) {          // sobrescribe si ya existe
    fw.write("Primera línea\n");
    fw.append("Segunda línea\n");
    fw.append("Usuario: ").append("María").append("\n"); // append es encadenable
} catch (IOException e) {
    e.printStackTrace();
}
// fw.close() se llama automáticamente al salir del bloque try
```

Si quieres **añadir al final** sin borrar el contenido previo, usa el constructor con `true`:

```java
try (FileWriter fw = new FileWriter(fichero, true)) {    // modo append
    fw.write("Esta línea se añade al final\n");
}
```

Los métodos principales que heredas de `Writer`:

| Método | Qué hace |
|--------|----------|
| `write(int c)` | Escribe un carácter (su código Unicode) |
| `write(String str)` | Escribe una cadena completa |
| `write(char[] cbuf)` | Escribe un array de caracteres |
| `append(char c)` | Añade un carácter; devuelve `this` (encadenable) |
| `append(CharSequence csq)` | Añade un `String`, `StringBuilder`, etc. |
| `flush()` | Fuerza la escritura al disco sin cerrar el flujo |
| `close()` | Cierra el flujo (llama `flush()` automáticamente antes) |

La diferencia entre `write` y `append` es principalmente de estilo: `append` devuelve `this` (el propio `Writer`), lo que permite encadenar llamadas como `fw.append("a").append("b").append("c")`. Funcionalmente, `write(String)` y `append(String)` escriben exactamente lo mismo.

> **Error típico:** `new FileWriter(fichero)` **sobrescribe** el fichero si ya existe. Si quieres conservar el contenido previo, SIEMPRE pasa `true` como segundo argumento: `new FileWriter(fichero, true)`.

### Ejercicio 3.1 — Escribir un catálogo de productos en texto

*(Ejercicio de elaboración propia basado en el contenido teórico)*

Tienes una lista de productos representados como arrays de `String` con formato `{"id", "nombre", "precio", "stock"}`. Implementa el método `guardarCatalogo(List<String[]> productos, String rutaFichero, boolean append)` que:

- **a)** Abre el fichero en modo normal o append según el parámetro recibido.
- **b)** Escribe la cabecera `"id;nombre;precio;stock\n"` solo si el fichero está vacío o no es modo append.
- **c)** Escribe cada producto en una línea con formato CSV separado por `;`.

### Solución 3.1

```java
import java.io.*;
import java.util.List;

public static void guardarCatalogo(List<String[]> productos,
                                   String rutaFichero,
                                   boolean append) {
    File fichero = new File(rutaFichero);

    try (FileWriter fw = new FileWriter(fichero, append)) {

        // b) cabecera solo si no es append o el fichero estaba vacío
        if (!append || fichero.length() == 0) {
            fw.write("id;nombre;precio;stock\n");
        }

        // c) una línea CSV por producto
        for (String[] p : productos) {
            fw.write(p[0] + ";" + p[1] + ";" + p[2] + ";" + p[3] + "\n");
        }

    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

> **Razonamiento:** el constructor `new FileWriter(fichero, append)` es el único punto de control entre sobrescribir y añadir. Comprobamos también `fichero.length() == 0` para escribir la cabecera en el caso especial en que `append` sea `true` pero el fichero esté vacío (primera ejecución del programa). Usamos `fw.write(String)` en vez de `fw.append()` porque aquí no necesitamos encadenamiento y `write` resulta más claro semánticamente.

---

<a id="bloque-4"></a>
## Bloque 4 — `FileReader` y `BufferedReader`: leer texto de un fichero

[↑ Volver al índice](#indice)

### Teoría

`FileReader` es el complemento de `FileWriter`: lee caracteres de un fichero. La mecánica es la misma — un flujo que avanza de principio a fin — pero en este caso extraes caracteres en vez de escribirlos.

El método fundamental es `read()`, que devuelve **un carácter como `int`** (su código Unicode). Cuando llega al final del fichero devuelve `-1`. Ese `-1` es la señal de parada del bucle:

```java
import java.io.*;

File fichero = new File("salida.txt");
StringBuilder sb = new StringBuilder();

try (FileReader fr = new FileReader(fichero)) {
    int c;
    while ((c = fr.read()) != -1) {    // -1 = EOF
        sb.append((char) c);           // cast obligatorio: int → char
    }
} catch (FileNotFoundException e) {
    System.out.println("El fichero no existe: " + fichero.getName());
} catch (IOException e) {
    e.printStackTrace();
}

System.out.println(sb.toString());
```

> **Error típico:** `fr.read()` devuelve `int`, no `char`. El cast `(char) c` es obligatorio. Si lo omites, `sb.append(c)` añadirá el número entero (p. ej., `"72101108108111"`) en vez del texto.

En la práctica, leer carácter a carácter es lento e incómodo para ficheros de texto con líneas. La solución es envolver `FileReader` en un `BufferedReader`, que añade un buffer interno y el método `readLine()`:

```java
import java.io.*;

File fichero = new File("catalogo.csv");

try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
    br.readLine(); // saltar la cabecera
    String linea;
    while ((linea = br.readLine()) != null) {    // null = EOF en BufferedReader
        String[] partes = linea.split(";");
        System.out.println("Producto: " + partes[1]);
    }
} catch (IOException e) {
    e.printStackTrace();
}
```

> **Diferencia crucial entre `FileReader.read()` y `BufferedReader.readLine()`:** `read()` devuelve `-1` (int) al llegar al EOF; `readLine()` devuelve `null` (String) al llegar al EOF. Son convenciones distintas — no las mezcles.

Los métodos principales de `FileReader` (hereda de `Reader`):

| Método | Qué hace | Señal de EOF |
|--------|----------|-------------|
| `read()` | Lee un carácter | Devuelve `-1` |
| `read(char[] cbuf)` | Llena un array de chars | Devuelve `-1` |
| `ready()` | ¿Listo para leer sin bloquear? | — |

El patrón completo **leer CSV → crear objetos** con `BufferedReader`:

```java
import java.io.*;
import java.util.*;

public static List<String[]> leerCSV(String ruta) {
    List<String[]> resultado = new ArrayList<>();
    File fichero = new File(ruta);

    try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
        br.readLine(); // cabecera
        String linea;
        while ((linea = br.readLine()) != null) {
            resultado.add(linea.split(";"));
        }
    } catch (FileNotFoundException e) {
        System.out.println("Fichero no encontrado: " + ruta);
    } catch (IOException e) {
        e.printStackTrace();
    }

    return resultado;
}
```

### Ejercicio 4.1 — Leer y filtrar un CSV de productos

*(Ejercicio de elaboración propia basado en el contenido teórico)*

Dado el fichero `productos.csv` con cabecera `id;nombre;precio;stock`, implementa el método `cargarProductosConStockBajo(String ruta, int umbral)` que:

- **a)** Lee todas las líneas del CSV saltando la cabecera.
- **b)** Filtra los productos cuyo stock (columna índice 3) sea menor o igual al umbral recibido.
- **c)** Devuelve una `List<String>` con los nombres de esos productos.
- **d)** Si el fichero no existe, imprime un mensaje descriptivo y devuelve una lista vacía sin propagar la excepción.

### Solución 4.1

```java
import java.io.*;
import java.util.*;

public static List<String> cargarProductosConStockBajo(String ruta, int umbral) {
    List<String> resultado = new ArrayList<>();
    File fichero = new File(ruta);

    try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
        br.readLine(); // a) salta cabecera
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split(";");
            int stock = Integer.parseInt(partes[3]);
            if (stock <= umbral) {       // b) filtro
                resultado.add(partes[1]); // c) guarda el nombre
            }
        }
    } catch (FileNotFoundException e) {
        // d)
        System.out.println("Fichero no encontrado: " + ruta);
    } catch (IOException e) {
        e.printStackTrace();
    }

    return resultado;
}
```

> **Razonamiento:** separamos `FileNotFoundException` de `IOException` porque la semántica es diferente. El fichero que no existe es un caso esperado (primera ejecución, ruta incorrecta) que manejamos con un mensaje amable y lista vacía. Cualquier otro `IOException` es un error inesperado que mostramos con la traza completa. Si usáramos solo `catch (IOException e)`, perderíamos esa distinción y asustarías al usuario con una excepción técnica cuando en realidad todo va bien.

---

<a id="bloque-5"></a>
## Bloque 5 — `DataOutputStream` / `DataInputStream`: guardar primitivos en binario

[↑ Volver al índice](#indice)

### Teoría

Hasta ahora hemos guardado texto: cadenas legibles que un humano puede abrir con el Bloc de notas. Pero a veces necesitas guardar datos de forma más eficiente: un `int` ocupa exactamente 4 bytes en binario, mientras que el texto `"2147483647"` ocupa 10 bytes. Además, en binario no hay ambigüedad de codificación ni necesitas parsear con `Integer.parseInt`.

`DataOutputStream` envuelve un `FileOutputStream` (que maneja los bytes brutos del fichero) y te ofrece métodos de alto nivel para escribir primitivos Java directamente:

```
Tu código (int, double, String...)
        │
        ▼
DataOutputStream  ← traduce cada primitivo a sus bytes
        │
        ▼
FileOutputStream  ← transporta los bytes al disco
        │
        ▼
fichero.bin
```

La regla cardinal de los Data streams es: **el orden en que escribes es el orden en que debes leer**. No hay cabecera, no hay etiquetas, no hay separadores. El fichero es una secuencia de bytes; si escribiste `int, String, double, int`, tienes que leer exactamente `int, String, double, int`.

```java
import java.io.*;

// ESCRITURA
File fichero = new File("productos.bin");

try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fichero))) {
    dos.writeInt(1);           // id
    dos.writeUTF("Teclado");   // nombre (String con longitud prefijada en 2 bytes)
    dos.writeDouble(29.99);    // precio
    dos.writeInt(15);          // stock
} catch (IOException e) {
    e.printStackTrace();
}

// LECTURA — exactamente el mismo orden
try (DataInputStream dis = new DataInputStream(new FileInputStream(fichero))) {
    while (dis.available() > 0) {
        int    id     = dis.readInt();
        String nombre = dis.readUTF();
        double precio = dis.readDouble();
        int    stock  = dis.readInt();
        System.out.printf("[%d] %s — %.2f € (stock: %d)%n", id, nombre, precio, stock);
    }
} catch (IOException e) {
    e.printStackTrace();
}
```

Tabla completa de métodos:

| Escritura | Lectura | Tipo Java | Bytes |
|-----------|---------|-----------|-------|
| `writeInt(int v)` | `readInt()` | `int` | 4 |
| `writeDouble(double v)` | `readDouble()` | `double` | 8 |
| `writeFloat(float v)` | `readFloat()` | `float` | 4 |
| `writeLong(long v)` | `readLong()` | `long` | 8 |
| `writeBoolean(boolean v)` | `readBoolean()` | `boolean` | 1 |
| `writeByte(int v)` | `readByte()` | `byte` | 1 |
| `writeChar(int v)` | `readChar()` | `char` | 2 |
| `writeUTF(String s)` | `readUTF()` | `String` | variable |

> **Para el examen:** `writeUTF` / `readUTF` es el único par correcto para `String` en Data streams. Internamente, escribe primero 2 bytes con la longitud de la cadena y luego los bytes UTF-8 del texto. No uses `writeBytes` ni `writeChars` para cadenas que luego vas a leer con `readUTF` — son formatos incompatibles.

El bucle `while (dis.available() > 0)` funciona porque `available()` devuelve los bytes pendientes de leer. Una alternativa equivalente es capturar `EOFException`:

```java
try (DataInputStream dis = new DataInputStream(new FileInputStream(fichero))) {
    while (true) {
        int    id     = dis.readInt();
        String nombre = dis.readUTF();
        double precio = dis.readDouble();
        int    stock  = dis.readInt();
        // procesar...
    }
} catch (EOFException e) {
    // fin normal del fichero — no es un error
} catch (IOException e) {
    e.printStackTrace();
}
```

### Ejercicio 5.1 — Ciclo completo: CSV → binario → recuperar

*(Ejercicio de elaboración propia basado en el contenido teórico)*

Partiendo de un CSV `productos.csv` (id;nombre;precio;stock), implementa dos métodos:

- **a)** `void guardarBinario(List<String[]> filas, String rutaBin)` — convierte las filas a objetos y los guarda en binario con el orden: `writeInt(id)`, `writeUTF(nombre)`, `writeDouble(precio)`, `writeInt(stock)`.
- **b)** `void leerBinario(String rutaBin)` — lee e imprime todos los registros del fichero binario con el formato `[id] nombre — precio € (stock: N)`.
- **c)** En un `main`, encadena la lectura del CSV (usa el método `leerCSV` del Bloque 4) con `guardarBinario` y luego con `leerBinario`.

### Solución 5.1

```java
import java.io.*;
import java.util.*;

// a)
public static void guardarBinario(List<String[]> filas, String rutaBin) {
    try (DataOutputStream dos = new DataOutputStream(
            new FileOutputStream(new File(rutaBin)))) {

        for (String[] p : filas) {
            dos.writeInt(Integer.parseInt(p[0]));       // id
            dos.writeUTF(p[1]);                         // nombre
            dos.writeDouble(Double.parseDouble(p[2]));  // precio
            dos.writeInt(Integer.parseInt(p[3]));       // stock
        }

    } catch (IOException e) {
        e.printStackTrace();
    }
}

// b)
public static void leerBinario(String rutaBin) {
    try (DataInputStream dis = new DataInputStream(
            new FileInputStream(new File(rutaBin)))) {

        while (dis.available() > 0) {
            int    id     = dis.readInt();
            String nombre = dis.readUTF();
            double precio = dis.readDouble();
            int    stock  = dis.readInt();
            System.out.printf("[%d] %-20s %.2f € (stock: %d)%n",
                              id, nombre, precio, stock);
        }

    } catch (IOException e) {
        e.printStackTrace();
    }
}

// c)
public static void main(String[] args) {
    List<String[]> filas = leerCSV("productos.csv");   // Bloque 4
    guardarBinario(filas, "productos.bin");
    leerBinario("productos.bin");
}
```

> **Razonamiento:** el orden en `guardarBinario` — `writeInt, writeUTF, writeDouble, writeInt` — debe coincidir byte a byte con el orden en `leerBinario` — `readInt, readUTF, readDouble, readInt`. Si cambias el orden en uno y no en el otro, los bytes se interpretan como el tipo equivocado y obtienes datos corruptos o una excepción. Esto es diferente de la serialización (Bloque 6), donde Java gestiona el orden automáticamente.

---

<a id="bloque-6"></a>
## Bloque 6 — Serialización: persistir objetos Java completos

[↑ Volver al índice](#indice)

### Teoría

Con `DataOutputStream` tenías que gestionar manualmente el orden de los campos. Con la serialización, Java hace ese trabajo por ti: convierte un objeto entero (con todos sus campos) en bytes y lo vuelve a reconstruir exactamente igual. Es como hacer una fotocopia de un objeto y guardarla en un cajón: la sacas y tienes exactamente el mismo objeto de antes.

Para que una clase sea serializable, solo necesita **implementar la interfaz `Serializable`**. Esta interfaz es una *interfaz marcador* — no tiene métodos que implementar. Es simplemente una etiqueta que le dice a Java "este tipo de objeto puede convertirse en bytes":

```java
import java.io.Serializable;

public class Producto implements Serializable {
    private int    id;
    private String nombre;
    private double precio;
    private int    stock;

    // campo que NO debe serializarse (cachés, tokens, conexiones...):
    private transient String cacheHash;  // transient → se ignora en la serialización

    public Producto(int id, String nombre, double precio, int stock) {
        this.id     = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock  = stock;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s — %.2f € (stock: %d)", id, nombre, precio, stock);
    }
}
```

La palabra clave `transient` excluye un campo de la serialización. Úsala para campos derivados (cachés, hashes temporales), contraseñas, conexiones a bases de datos, o cualquier dato que no tenga sentido fuera de la ejecución actual. Al deserializar, los campos `transient` quedan a su valor por defecto (`null`, `0`, `false`).

**Escribir una lista de objetos:**

```java
import java.io.*;
import java.util.*;

List<Producto> productos = new ArrayList<>();
productos.add(new Producto(1, "Teclado", 29.99, 15));
productos.add(new Producto(2, "Ratón",   14.50, 30));

try (ObjectOutputStream oos = new ObjectOutputStream(
        new FileOutputStream("productos.ser"))) {

    oos.writeObject(productos);   // serializa la lista ENTERA de una vez

} catch (IOException e) {
    e.printStackTrace();
}
```

**Leer la lista de objetos:**

```java
List<Producto> productos = new ArrayList<>();

try (ObjectInputStream ois = new ObjectInputStream(
        new FileInputStream("productos.ser"))) {

    @SuppressWarnings("unchecked")                            // obligatorio en cast genérico
    List<Producto> cargados = (List<Producto>) ois.readObject();
    productos = cargados;

} catch (FileNotFoundException e) {
    System.out.println("No hay datos previos. Empezando de cero."); // primera ejecución
} catch (IOException | ClassNotFoundException e) {
    e.printStackTrace();
}
```

> **Para el examen:** `FileNotFoundException` se captura **por separado** y **antes** que `IOException` porque significa "el fichero aún no existe" — es el comportamiento normal en la primera ejecución. Si lo mezclas con `IOException`, tu programa imprimirá una traza de error aterradora la primera vez que alguien lo use, cuando en realidad todo va bien.

El patrón de uso más completo en una aplicación real es **cargar al inicio + guardar al salir**:

```java
public class GestorProductos {

    private static final String FICHERO = "productos.ser";
    private List<Producto> productos = new ArrayList<>();

    public void cargar() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(FICHERO))) {
            @SuppressWarnings("unchecked")
            List<Producto> cargados = (List<Producto>) ois.readObject();
            productos = cargados;
        } catch (FileNotFoundException e) {
            // primera ejecución — lista vacía, es normal
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void guardar() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(FICHERO))) {
            oos.writeObject(productos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ejecutar() {
        cargar();
        // ... lógica de menú con Scanner ...
        guardar();  // siempre al salir
    }
}
```

> **La clave mental:** con serialización, Java gestiona el orden y la estructura de los campos automáticamente. Con `DataOutputStream`, eres tú quien gestiona ese orden. Elige serialización cuando trabajas con objetos Java completos; elige Data streams cuando la portabilidad del formato binario importa (otros programas, otras plataformas, formato fijo documentado).

### Ejercicio 6.1 — Implementar el ciclo completo de serialización

*(Ejercicio de elaboración propia basado en el contenido teórico)*

Completa la clase `AppProductos` con los siguientes requisitos:

- **a)** Haz que `Producto` (con campos `id`, `nombre`, `precio`, `stock`, `cacheHash`) sea serializable, marcando `cacheHash` como no serializable.
- **b)** Implementa `cargar()`: carga la lista desde `"productos.ser"`. Si el fichero no existe, la lista queda vacía sin mostrar error.
- **c)** Implementa `guardar()`: persiste la lista completa en `"productos.ser"`.
- **d)** En `ejecutar()`, carga la lista, añade un producto con id = `productos.size() + 1`, imprime todos los productos y guarda antes de terminar.

### Solución 6.1

```java
import java.io.*;
import java.util.*;

// a)
class Producto implements Serializable {
    private int    id;
    private String nombre;
    private double precio;
    private int    stock;
    private transient String cacheHash;   // no se serializa

    public Producto(int id, String nombre, double precio, int stock) {
        this.id     = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock  = stock;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s — %.2f € (stock: %d)", id, nombre, precio, stock);
    }
}

public class AppProductos {

    private static final String FICHERO = "productos.ser";
    private List<Producto> productos = new ArrayList<>();

    // b)
    public void cargar() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(FICHERO))) {
            @SuppressWarnings("unchecked")
            List<Producto> cargados = (List<Producto>) ois.readObject();
            productos = cargados;
        } catch (FileNotFoundException e) {
            // primera ejecución — normal, la lista queda vacía
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // c)
    public void guardar() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(FICHERO))) {
            oos.writeObject(productos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // d)
    public void ejecutar() {
        cargar();
        productos.add(new Producto(productos.size() + 1, "Monitor", 199.99, 5));
        productos.forEach(System.out::println);
        guardar();
    }
}
```

> **Razonamiento:** el `@SuppressWarnings("unchecked")` en el cast `(List<Producto>)` es obligatorio porque Java no puede verificar en tiempo de ejecución que el objeto deserializado es realmente una `List<Producto>` — el tipo genérico se borra en bytecode. Si omites la anotación, el compilador emite una advertencia que en algunos entornos (y en exámenes con checkstyle) se trata como error. Ponlo siempre en la línea exacta del cast, no sobre el método entero, para acotar el alcance de la supresión.

---

<a id="apendice"></a>
## Apéndice — Errores típicos y trampas de examen

[↑ Volver al índice](#indice)

### PARTE I — IO vs NIO: errores frecuentes

| Error | Qué está mal | Corrección |
|-------|-------------|------------|
| `Files.createDirectory(path)` cuando falta algún directorio intermedio | Lanza `NoSuchFileException` | Usa `Files.createDirectories(path)` para crear toda la cadena |
| `dir.listFiles()` sin comprobar `null` | Si la ruta no es directorio o hay error de permisos, devuelve `null` → `NullPointerException` al iterar | Guarda el resultado y comprueba `!= null` antes del bucle |
| `new File("C:\\ruta\\fichero")` en código multiplataforma | Las barras invertidas solo funcionan en Windows | Usa `"C:/ruta/fichero"` o `Paths.get("C:", "ruta", "fichero")` |
| Confundir `Path.of()` con `Paths.get()` como si fueran distintos | Son equivalentes desde Java 11 | Usa `Path.of()` en proyectos Java 11+; `Paths.get()` si necesitas compatibilidad con Java 8 |

### PARTE II — Flujos de texto: errores frecuentes

| Error | Qué está mal | Corrección |
|-------|-------------|------------|
| `new FileWriter(f)` cuando quieres append | Sobrescribe el fichero completo | Usa `new FileWriter(f, true)` |
| Comparar `fr.read()` con `null` | `read()` devuelve `int`, nunca `null` | Compara con `-1`: `while ((c = fr.read()) != -1)` |
| Comparar `br.readLine()` con `""` para detectar EOF | `readLine()` devuelve `null` al final, no cadena vacía | Compara con `null`: `while ((linea = br.readLine()) != null)` |
| Olvidar el cast `(char) c` en `FileReader.read()` | `sb.append(c)` añade el código numérico del carácter, no el carácter | `sb.append((char) c)` siempre |
| No usar try-with-resources | El fichero puede quedar abierto si hay una excepción antes del `close()` | Siempre `try (FileReader fr = new FileReader(f)) { ... }` |

### PARTE III — Binario y Serialización: errores frecuentes

| Error | Qué está mal | Corrección |
|-------|-------------|------------|
| Leer en orden distinto al de escritura con `DataInputStream` | Los bytes se interpretan como el tipo equivocado → datos corruptos o excepción | El orden `writeXxx` debe coincidir exactamente con el orden `readXxx` |
| Usar `writeBytes(String)` en vez de `writeUTF(String)` | `writeBytes` escribe solo el byte bajo de cada char; `readUTF` no puede leerlo | Usa siempre el par `writeUTF` / `readUTF` para Strings en Data streams |
| Olvidar `implements Serializable` en la clase | Lanza `NotSerializableException` en tiempo de ejecución | Añade `implements Serializable` a la clase (y también a la interfaz si la hay) |
| No separar `FileNotFoundException` de `IOException` al deserializar | La primera ejecución imprime una traza de error innecesaria | Captura `FileNotFoundException` primero con un mensaje informativo |
| Cast `(List<T>)` sin `@SuppressWarnings("unchecked")` | Warning del compilador (o error en entornos estrictos) | Añade `@SuppressWarnings("unchecked")` en la línea exacta del cast |
| Esperar que un campo `transient` conserve su valor tras deserializar | Los campos `transient` vuelven a su valor por defecto (`null`, `0`, `false`) | Recalcula o reinicializa los campos `transient` después de la deserialización |

### Tabla resumen — ¿qué herramienta para qué?

| Necesito... | Herramienta |
|-------------|-------------|
| Verificar si un fichero o directorio existe | `File.exists()` o `Files.exists(path)` |
| Listar contenido de un directorio | `File.listFiles()` o `Files.list(path)` |
| Crear directorios (con todos los intermedios) | `Files.createDirectories(path)` |
| Copiar un fichero | `Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING)` |
| Mover o renombrar un fichero | `Files.move(origen, destino)` |
| Escribir texto en un fichero (sobrescribir) | `new FileWriter(f)` o `Files.writeString(path, texto)` |
| Escribir texto añadiendo al final | `new FileWriter(f, true)` |
| Leer texto línea a línea | `BufferedReader` + `FileReader` → `readLine() != null` |
| Leer texto completo de golpe | `Files.readString(path)` (NIO, Java 11+) |
| Guardar primitivos en binario | `DataOutputStream` → `FileOutputStream` |
| Leer primitivos de un binario | `DataInputStream` → `FileInputStream` (mismo orden) |
| Guardar objetos Java completos | `ObjectOutputStream` → `FileOutputStream` (clase con `Serializable`) |
| Cargar objetos Java completos | `ObjectInputStream` → `FileInputStream` + cast + `@SuppressWarnings` |

---

## Referencia rápida — ¿cuándo uso qué?

```
¿Qué quiero hacer?
│
├─► Operación SOBRE la ruta (crear, copiar, mover, verificar existencia)
│       │
│       ├─► ¿Código moderno (Java 11+)?
│       │       → Path + Files
│       │         (Files.createDirectories, Files.copy, Files.exists, Files.list...)
│       │
│       └─► ¿Compatibilidad con APIs antiguas o APIs que piden File?
│               → File
│                 (file.exists(), file.mkdir(), file.listFiles(), file.toPath()...)
│
└─► Operación de CONTENIDO (leer/escribir datos dentro del fichero)
        │
        ├─► ¿Texto legible por humanos?
        │       │
        │       ├─► Escribir
        │       │     → FileWriter  (new FileWriter(f) / new FileWriter(f, true))
        │       │
        │       ├─► Leer carácter a carácter
        │       │     → FileReader  → while ((c = fr.read()) != -1)
        │       │
        │       ├─► Leer línea a línea  ← lo más habitual con CSV
        │       │     → BufferedReader + FileReader  → while ((l = br.readLine()) != null)
        │       │
        │       └─► Leer todo el contenido de golpe
        │             → Files.readString(path)  [NIO, Java 11+]
        │
        ├─► ¿Primitivos en binario? (int, double, String UTF...)
        │       ├─► Escribir → DataOutputStream  (writeInt, writeUTF, writeDouble...)
        │       └─► Leer    → DataInputStream   (readInt, readUTF... en el MISMO orden)
        │
        └─► ¿Objetos Java completos?
                ├─► La clase DEBE implementar Serializable
                ├─► Campos sensibles o derivados → marcarlos transient
                ├─► Escribir → ObjectOutputStream  (oos.writeObject(lista))
                └─► Leer    → ObjectInputStream   (cast + @SuppressWarnings("unchecked"))
                              FileNotFoundException separado = primera ejecución, es normal
```

---

*Fin de la guía — UT10 Flujo de trabajo Java I/O · Programación DAM 2025-26*
