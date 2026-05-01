# UT10 — Flujo de trabajo Java I/O

---

## Índice

1. [IO vs NIO — cuándo usar cada uno](#1-io-vs-nio)
2. [IO Clásico — File, FileReader, FileWriter](#2-io-clásico)
3. [NIO — Path, Paths, Files](#3-nio)
4. [Ficheros Binarios — DataOutputStream / DataInputStream](#4-ficheros-binarios)
5. [Serialización — ObjectOutputStream / ObjectInputStream](#5-serialización)

---

## 1. IO vs NIO

| | IO (java.io) | NIO (java.nio) |
|---|---|---|
| Núcleo | `File`, `FileReader`, `FileWriter` | `Path`, `Paths`, `Files` |
| Streams | bloqueantes, byte/char | no bloqueantes posibles |
| Uso típico | leer/escribir ficheros de texto o binarios | operaciones modernas de fichero, copias, checks |
| Acceso aleatorio | `RandomAccessFile` (**no entra**) | — |

**Regla práctica:** usa NIO (`Files`, `Path`) para crear, verificar, copiar, mover. Usa IO (`FileReader`, `DataOutputStream`, etc.) cuando necesitas leer/escribir el contenido carácter a carácter o primitivo a primitivo.

---

## 2. IO Clásico

### 2.1 File — referencia a ruta

```java
File f = new File("datos.txt");            // relativo al directorio actual
File f = new File("C:/ruta/datos.txt");    // absoluto

f.exists()          // ¿existe?
f.isFile()          // ¿es fichero?
f.isDirectory()     // ¿es directorio?
f.getName()         // nombre del fichero
f.getAbsolutePath() // ruta absoluta completa
f.length()          // tamaño en bytes
f.canRead()
f.canWrite()
f.mkdir()           // crea directorio
f.createNewFile()   // crea fichero vacío (lanza IOException)
f.delete()
f.renameTo(new File("nuevo.txt"))
f.list()            // String[] con nombres del directorio
f.listFiles()       // File[] con objetos File del directorio
```

```java
// Flujo: verificar y listar directorio
File dir = new File(".");
File[] archivos = dir.listFiles();
for (File archivo : archivos) {
    System.out.printf("%s | fichero: %b | dir: %b%n",
        archivo.getName(), archivo.isFile(), archivo.isDirectory());
}
```

---

### 2.2 FileReader / FileWriter — flujos de caracteres

**Flujo ESCRITURA:**

```java
// 1. Crear File
File fichero = new File("salida.txt");

// 2. Abrir FileWriter con try-with-resources
try (FileWriter fw = new FileWriter(fichero)) {          // sobrescribe
// try (FileWriter fw = new FileWriter(fichero, true)) { // append
    fw.write("Hola mundo\n");
    fw.append("Segunda línea\n");
    fw.append("Usuario:").append(nombre).append("\n");
}
// el try-with-resources llama fw.close() automáticamente
```

**Flujo LECTURA:**

```java
// 1. Crear File
File fichero = new File("salida.txt");

// 2. Abrir FileReader con try-with-resources
StringBuilder sb = new StringBuilder();
try (FileReader fr = new FileReader(fichero)) {
    int c;
    while ((c = fr.read()) != -1) {   // lee UN carácter, devuelve -1 al final
        sb.append((char) c);
    }
} catch (FileNotFoundException e) {
    System.out.println("El fichero no existe");
} catch (IOException e) {
    e.printStackTrace();
}
String contenido = sb.toString();
```

**Métodos clave de FileReader (hereda de Reader):**

```java
int read()                          // un carácter, -1 si EOF
int read(char[] cbuf)               // llena el array, devuelve chars leídos
int read(char[] cbuf, int off, int len)
boolean ready()                     // ¿listo para leer?
```

**Métodos clave de FileWriter (hereda de Writer):**

```java
void write(int c)                   // un carácter
void write(String str)              // cadena completa
void write(char[] cbuf)
Writer append(char c)
Writer append(CharSequence csq)     // String, StringBuilder, etc.
void flush()                        // fuerza escritura al disco
void close()
```

---

## 3. NIO

### Crear un Path

```java
// Tres formas equivalentes:
Path p1 = Paths.get("java/temario.txt");
Path p2 = Path.of("java/temario.txt");                        // desde Java 11
Path p3 = Paths.get(System.getProperty("user.home"), "docs", "temario.txt");

p1.toAbsolutePath()   // Path con ruta absoluta completa
p1.toFile()           // convierte a File (puente IO ↔ NIO)
```

### Files — operaciones de fichero

```java
// Existencia y tipo
Files.exists(path)
Files.isDirectory(path)
Files.isRegularFile(path)

// Crear
Files.createFile(path)
Files.createDirectory(path)
Files.createDirectories(path)       // crea toda la cadena si no existe

// Leer todo el contenido (ficheros pequeños)
String contenido = Files.readString(path);                   // Java 11+
List<String> lineas = Files.readAllLines(path);

// Escribir
Files.writeString(path, "contenido");
Files.write(path, bytes);

// Copiar / mover / borrar
Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);
Files.move(origen, destino);
Files.delete(path);

// Listar directorio
Files.list(directoryPath).forEach(p -> System.out.println(p));
```

```java
// Flujo típico NIO: verificar ruta y leer
Path ruta = Paths.get("datos.txt");
if (Files.exists(ruta)) {
    String texto = Files.readString(ruta);
    System.out.println(texto);
}
```

---

## 4. Ficheros Binarios

Usa `DataOutputStream` para guardar primitivos (`int`, `double`, `String UTF`) en binario.
Usa `DataInputStream` para leerlos **en el mismo orden** en que fueron escritos. Ese orden es la única "clave" del fichero.

### Flujo ESCRITURA con DataOutputStream

```java
File fichero = new File("datos.bin");

try (DataOutputStream dos = new DataOutputStream(
        new FileOutputStream(fichero))) {

    // escribe EN ORDEN — este orden define la estructura del fichero
    dos.writeInt(mesa.getIdMesa());
    dos.writeUTF(mesa.getLocalidad());
    dos.writeInt(mesa.getVotosA());
    dos.writeInt(mesa.getVotosB());
    dos.writeInt(mesa.getVotosC());
    dos.writeInt(mesa.getBlancos());
    dos.writeInt(mesa.getNulos());

} catch (IOException e) {
    e.printStackTrace();
}
```

### Flujo LECTURA con DataInputStream

```java
File fichero = new File("datos.bin");
List<Mesa> mesas = new ArrayList<>();

try (DataInputStream dis = new DataInputStream(
        new FileInputStream(fichero))) {

    // lee EN EL MISMO ORDEN que se escribió
    while (dis.available() > 0) {           // available() > 0 → aún hay datos
        int idMesa       = dis.readInt();
        String localidad = dis.readUTF();
        int votosA       = dis.readInt();
        int votosB       = dis.readInt();
        int votosC       = dis.readInt();
        int blancos      = dis.readInt();
        int nulos        = dis.readInt();

        mesas.add(new Mesa(idMesa, localidad, votosA, votosB, votosC, blancos, nulos));
    }

} catch (EOFException e) {
    // fin de fichero alcanzado — normal si usas readXxx sin available()
} catch (IOException e) {
    e.printStackTrace();
}
```

### Tabla de métodos Data streams

| Escritura | Lectura | Tipo Java |
|---|---|---|
| `writeInt(int v)` | `readInt()` | `int` (4 bytes) |
| `writeDouble(double v)` | `readDouble()` | `double` (8 bytes) |
| `writeFloat(float v)` | `readFloat()` | `float` (4 bytes) |
| `writeLong(long v)` | `readLong()` | `long` (8 bytes) |
| `writeBoolean(boolean v)` | `readBoolean()` | `boolean` (1 byte) |
| `writeByte(int v)` | `readByte()` | `byte` (1 byte) |
| `writeChar(int v)` | `readChar()` | `char` (2 bytes) |
| `writeUTF(String s)` | `readUTF()` | `String` codificado UTF-8 |

### Patrón completo: leer CSV → guardar binario → leer binario

```java
// PASO 1: leer CSV y crear objetos
//   idMesa;localidad;votosA;votosB;votosC;blancos;nulos
List<Mesa> mesas = new ArrayList<>();
try (BufferedReader br = new BufferedReader(new FileReader("mesa1.csv"))) {
    br.readLine(); // saltar cabecera
    String linea;
    while ((linea = br.readLine()) != null) {
        String[] partes = linea.split(";");
        mesas.add(new Mesa(
            Integer.parseInt(partes[0]),  // idMesa
            partes[1],                    // localidad
            Integer.parseInt(partes[2]),  // votosA
            Integer.parseInt(partes[3]),  // votosB
            Integer.parseInt(partes[4]),  // votosC
            Integer.parseInt(partes[5]),  // blancos
            Integer.parseInt(partes[6])   // nulos
        ));
    }
}

// PASO 2: guardar en binario
try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("mesas.bin"))) {
    for (Mesa m : mesas) {
        dos.writeInt(m.getIdMesa());
        dos.writeUTF(m.getLocalidad());
        dos.writeInt(m.getVotosA());
        dos.writeInt(m.getVotosB());
        dos.writeInt(m.getVotosC());
        dos.writeInt(m.getBlancos());
        dos.writeInt(m.getNulos());
    }
}

// PASO 3: leer desde binario
List<Mesa> mesasRecuperadas = new ArrayList<>();
try (DataInputStream dis = new DataInputStream(new FileInputStream("mesas.bin"))) {
    while (dis.available() > 0) {
        mesasRecuperadas.add(new Mesa(
            dis.readInt(),
            dis.readUTF(),
            dis.readInt(),
            dis.readInt(),
            dis.readInt(),
            dis.readInt(),
            dis.readInt()
        ));
    }
}
```

---

## 5. Serialización

Serializa objetos completos directamente a fichero. El objeto entero se convierte en bytes y se recupera como objeto.

### Requisito: implementar Serializable

```java
import java.io.Serializable;

public class AlumnoPresencial implements Alumno, Serializable {
    // Serializable es una interfaz marcador — no tiene métodos
    // Todos los campos se serializan automáticamente

    private String nombre;
    private String curso;
    private double notaMedia;
    private int numModulos;
    private boolean familaNumerosa;

    // campo que NO debe serializarse (contraseñas, cachés, conexiones):
    private transient String tokenTemp;   // transient → se ignora en serialización
}
```

### Flujo ESCRITURA con ObjectOutputStream

```java
List<Alumno> alumnos = new ArrayList<>();
// ... (alumnos cargados en memoria)

try (ObjectOutputStream oos = new ObjectOutputStream(
        new FileOutputStream("alumnos.ser"))) {

    oos.writeObject(alumnos);    // serializa la lista entera de una vez

} catch (IOException e) {
    e.printStackTrace();
}
```

### Flujo LECTURA con ObjectInputStream

```java
List<Alumno> alumnos = new ArrayList<>();

try (ObjectInputStream ois = new ObjectInputStream(
        new FileInputStream("alumnos.ser"))) {

    @SuppressWarnings("unchecked")              // obligatorio al hacer cast genérico
    List<Alumno> cargados = (List<Alumno>) ois.readObject();
    alumnos = cargados;

} catch (FileNotFoundException e) {
    // primera ejecución — fichero aún no existe, lista vacía
    System.out.println("No hay datos previos. Empezando de cero.");
} catch (IOException | ClassNotFoundException e) {
    e.printStackTrace();
}
```

### Patrón completo: carga al inicio + guardado al salir

```java
public class App {

    private static final String FICHERO = "alumnos.ser";
    private List<Alumno> alumnos = new ArrayList<>();

    public void cargar() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(FICHERO))) {
            @SuppressWarnings("unchecked")
            List<Alumno> cargados = (List<Alumno>) ois.readObject();
            alumnos = cargados;
        } catch (FileNotFoundException e) {
            // primera vez — normal
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void guardar() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(FICHERO))) {
            oos.writeObject(alumnos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ejecutar() {
        cargar();

        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("1. Añadir presencial  2. Añadir online  3. Listar  0. Salir");
            int opcion = sc.nextInt(); sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Nombre: "); String nombre = sc.nextLine();
                    System.out.print("Curso: ");  String curso  = sc.nextLine();
                    System.out.print("Nota: ");   double nota   = sc.nextDouble();
                    System.out.print("Módulos: "); int mods     = sc.nextInt();
                    System.out.print("F.Numerosa (s/n): ");
                    boolean fn = sc.next().equalsIgnoreCase("s");
                    sc.nextLine();
                    alumnos.add(new AlumnoPresencial(nombre, curso, nota, mods, fn));
                }
                case 2 -> {
                    System.out.print("Nombre: ");  String nombre = sc.nextLine();
                    System.out.print("Curso: ");   String curso  = sc.nextLine();
                    System.out.print("Nota: ");    double nota   = sc.nextDouble();
                    System.out.print("Módulos: "); int mods      = sc.nextInt();
                    System.out.print("Factor participación (0.0-1.0): ");
                    double factor = sc.nextDouble();
                    sc.nextLine();
                    alumnos.add(new AlumnoOnline(nombre, curso, nota, mods, factor));
                }
                case 3 -> alumnos.forEach(a -> System.out.println(a.getResumen()));
                case 0 -> salir = true;
            }
        }

        guardar();
    }
}
```

### Interfaz Alumno con Serializable

```java
import java.io.Serializable;

public interface Alumno extends Serializable {
    String getNombre();
    String getCurso();
    double getNotaMedia();
    double getMatricula();
    String getResumen();
}
```

### AlumnoPresencial completo

```java
public class AlumnoPresencial implements Alumno {
    private String nombre, curso;
    private double notaMedia;
    private int numModulos;
    private boolean familaNumerosa;

    public AlumnoPresencial(String nombre, String curso, double notaMedia,
                            int numModulos, boolean familaNumerosa) {
        this.nombre = nombre;
        this.curso = curso;
        this.notaMedia = notaMedia;
        this.numModulos = numModulos;
        this.familaNumerosa = familaNumerosa;
    }

    @Override public String getNombre()    { return nombre; }
    @Override public String getCurso()     { return curso; }
    @Override public double getNotaMedia() { return notaMedia; }

    @Override
    public double getMatricula() {
        double base = numModulos * 30.0;
        return familaNumerosa ? base * 0.5 : base;
    }

    @Override
    public String getResumen() {
        return String.format("[PRESENCIAL] %s | %s | Nota: %.2f | Módulos: %d | Matrícula: %.2f €",
            nombre, curso, notaMedia, numModulos, getMatricula());
    }
}
```

### AlumnoOnline completo

```java
public class AlumnoOnline implements Alumno {
    private String nombre, curso;
    private double notaMedia;
    private int numModulos;
    private double factorParticipacion;

    public AlumnoOnline(String nombre, String curso, double notaMedia,
                        int numModulos, double factorParticipacion) {
        this.nombre = nombre;
        this.curso = curso;
        this.notaMedia = notaMedia;
        this.numModulos = numModulos;
        this.factorParticipacion = factorParticipacion;
    }

    @Override public String getNombre() { return nombre; }
    @Override public String getCurso()  { return curso; }

    @Override
    public double getNotaMedia() {
        return notaMedia * factorParticipacion;   // ponderada
    }

    @Override
    public double getMatricula() {
        return numModulos * 20.0;
    }

    @Override
    public String getResumen() {
        return String.format("[ONLINE]     %s | %s | Nota: %.2f | Módulos: %d | Matrícula: %.2f €",
            nombre, curso, getNotaMedia(), numModulos, getMatricula());
    }
}
```

---

## Resumen de reglas clave

```
DataOutputStream / DataInputStream
  → orden de escritura = orden de lectura (siempre)
  → available() > 0  para leer en bucle
  → writeUTF / readUTF para Strings

ObjectOutputStream / ObjectInputStream
  → la clase DEBE implementar Serializable
  → transient excluye campos de la serialización
  → @SuppressWarnings("unchecked") al hacer cast genérico
  → FileNotFoundException separado: primera ejecución sin fichero
  → try-with-resources en TODA operación de I/O

NIO (Path / Files)
  → Paths.get(...) o Path.of(...) para construir rutas
  → Files.exists(), Files.readString(), Files.writeString() para operaciones rápidas
  → path.toFile() / file.toPath() para convertir entre IO y NIO
```
