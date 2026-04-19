# LECTURA Y ESCRITURA DE INFORMACIÓN EN JAVA

<a id="indice"></a>
## ÍNDICE DINÁMICO
- [3. Buffers en Java (BufferedReader y BufferedWriter)](#sec3)
  - [3.1 Introducción a los Buffers](#sec3_1)
  - [3.2 Clases Principales de Buffers](#sec3_2)
    - [3.2.1 BufferedReader](#sec3_2_1)
    - [3.2.2 BufferedWriter](#sec3_2_2)
  - [3.3 Lectura de Archivos con BufferedReader](#sec3_3)
  - [3.4 Escritura de Archivos con BufferedWriter](#sec3_4)
  - [3.5 Lectura y Escritura Simultánea con Buffers](#sec3_5)
  - [3.6 Comparación con FileReader y FileWriter](#sec3_6)
  - [3.7 Optimización del Tamaño del Buffer](#sec3_7)
  - [3.8 Manejo de Excepciones y Cierre de Recursos](#sec3_8)
  - [3.9 Conclusión](#sec3_9)
  - [3.10 Ejercicios Prácticos](#sec3_10)

---

<a id="sec3"></a>
# 3. Buffers en Java (BufferedReader y BufferedWriter)

<a id="sec3_1"></a>
## 3.1 Introducción a los Buffers

Los **buffers** en Java son estructuras intermedias que mejoran el rendimiento de lectura y escritura en archivos. Su función principal es **reducir la cantidad de accesos al sistema de archivos**, lo que optimiza el procesamiento de datos.

```mermaid
%%{init: {'theme': 'base', 'themeVariables': { 'primaryColor': '#1e293b', 'primaryTextColor': '#f8fafc', 'primaryBorderColor': '#38bdf8', 'lineColor': '#94a3b8', 'mainBkg': '#0f172a', 'nodeBorder': '#38bdf8', 'clusterBkg': '#1e293b', 'clusterBorder': '#38bdf8'}}}%%
graph LR
    A[Disco <br/> Archivo] <-->|"Bloques grandes\n(buffer)"| B["Buffer\n(Memoria RAM)"]
    B <-->|"byte / char\npor operación"| C[Tu programa Java]
    style B fill:#1e293b,stroke:#38bdf8
```

En `java.io`, los buffers más utilizados para archivos de texto son:

- **`BufferedReader`**: Permite leer líneas completas de un archivo en lugar de leer carácter por carácter.
- **`BufferedWriter`**: Permite escribir líneas completas en un archivo de manera eficiente.

Ambos pertenecen al paquete `java.io` y deben utilizarse en combinación con `FileReader` y `FileWriter`.

> 💡 **TIPS Prácticos:**
> Piensa en el buffer como en un camión de reparto: si cada byte fuera un paquete individual, sería muy ineficiente enviar un camión por paquete. El buffer agrupa miles de bytes en un "cargamento" y realiza una sola operación de E/S en el disco, reduciendo drásticamente el tiempo de acceso.

[🏠 Volver al Índice](#indice)

---

<a id="sec3_2"></a>
## 3.2 Clases Principales de Buffers

<a id="sec3_2_1"></a>
### 3.2.1 BufferedReader

Permite leer archivos de texto de manera eficiente. Se usa con `FileReader` para leer **línea por línea**.

<a id="sec3_2_2"></a>
### 3.2.2 BufferedWriter

Permite escribir archivos de texto con mayor rendimiento. Se usa con `FileWriter` para escribir en el archivo por líneas.

**Ventajas de BufferedReader y BufferedWriter:**

| Ventaja | Descripción |
| :--- | :--- |
| **Mayor rendimiento** | En lugar de leer/escribir carácter por carácter, usa un buffer interno que reduce las operaciones de E/S. |
| **Facilidad de uso** | Proporciona métodos como `readLine()` y `newLine()` que facilitan el manejo de archivos. |
| **Eficiencia** | Reduce la carga sobre el sistema operativo al disminuir las llamadas al sistema de archivos. |

[🏠 Volver al Índice](#indice)

---

<a id="sec3_3"></a>
## 3.3 Lectura de Archivos con BufferedReader

Para leer un archivo de texto línea por línea, utilizamos `BufferedReader` en combinación con `FileReader`.

**Ejemplo 1: Leer un archivo línea por línea con BufferedReader**

```java
import java.io.*;

public class LeerConBufferedReader {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("ejemplo.txt"))) {
            String linea;
            // readLine() devuelve null al llegar al final del archivo
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

**Explicación:**
- Se usa `BufferedReader` con `FileReader` para abrir el archivo.
- `readLine()` lee **línea por línea** hasta que devuelve `null` (fin del archivo).
- El uso de `try-with-resources` evita fugas de memoria y cierra el archivo automáticamente.

> 💡 **TIPS Prácticos:**
> En Java 8+, puedes leer todas las líneas de un archivo con `Files.lines(path)`, que devuelve un `Stream<String>` funcional. Sin embargo, `BufferedReader` sigue siendo la opción más didáctica y controlada para el temario.

[🏠 Volver al Índice](#indice)

---

<a id="sec3_4"></a>
## 3.4 Escritura de Archivos con BufferedWriter

Para escribir archivos de manera eficiente, utilizamos `BufferedWriter` en combinación con `FileWriter`.

**Ejemplo 2: Escribir un archivo línea por línea con BufferedWriter**

```java
import java.io.*;

public class EscribirConBufferedWriter {
    public static void main(String[] args) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("salida.txt"))) {
            bw.write("Hola, este es un archivo de prueba.");
            bw.newLine(); // Inserta un salto de línea compatible con el SO
            bw.write("Segunda línea del archivo.");
            System.out.println("Archivo escrito con éxito.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

**Explicación:**
- Se usa `BufferedWriter` con `FileWriter` para escribir en el archivo.
- `newLine()` se utiliza para agregar saltos de línea.
- `try-with-resources` se encarga de cerrar el archivo al finalizar.

[🏠 Volver al Índice](#indice)

---

<a id="sec3_5"></a>
## 3.5 Lectura y Escritura Simultánea con Buffers

En algunos casos, es necesario leer y escribir archivos simultáneamente. Podemos hacerlo declarando ambos recursos en el mismo `try-with-resources`.

**Ejemplo 3: Leer un archivo y escribirlo en otro usando Buffers**

```java
import java.io.*;

public class CopiarArchivoConBuffers {
    public static void main(String[] args) {
        // Podemos declarar múltiples recursos en un solo try-with-resources
        try (BufferedReader br = new BufferedReader(new FileReader("entrada.txt"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("copia.txt"))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                bw.write(linea);
                bw.newLine(); // Preserva los saltos de línea originales
            }
            System.out.println("Archivo copiado con éxito.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

**Explicación:**
- `BufferedReader` lee el archivo original.
- `BufferedWriter` escribe en un nuevo archivo.
- Se usa `newLine()` para respetar los saltos de línea originales.

> 🚀 **COMPLEMENTO (Fuera de temario):**
> Con Java NIO, copiar un archivo es una sola línea: `Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING)`. Internamente, NIO usa canales nativos del SO (*zero-copy*), lo que puede ser hasta 10 veces más rápido que la copia byte a byte con `java.io`.

[🏠 Volver al Índice](#indice)

---

<a id="sec3_6"></a>
## 3.6 Comparación con FileReader y FileWriter

| Característica | `FileReader` / `FileWriter` | `BufferedReader` / `BufferedWriter` |
| :--- | :--- | :--- |
| **Eficiencia** | Lento (lee/escribe carácter por carácter). | Rápido (usa un buffer interno). |
| **Manejo de líneas** | Se debe leer carácter a carácter manualmente. | `readLine()` y `newLine()` facilitan la manipulación de líneas. |
| **Adecuado para** | Archivos pequeños o acceso puntual. | Archivos grandes o lectura/escritura intensiva. |

> 💡 **TIPS Prácticos:**
> En la práctica, **casi nunca** se usa `FileReader` o `FileWriter` directamente sin envolver en un `Buffered`. La regla general es: si usas `FileReader` → envuélvelo en `BufferedReader`. Si usas `FileWriter` → envuélvelo en `BufferedWriter`. Así siempre tendrás el mejor rendimiento.

[🏠 Volver al Índice](#indice)

---

<a id="sec3_7"></a>
## 3.7 Optimización del Tamaño del Buffer

Por defecto, `BufferedReader` y `BufferedWriter` usan un buffer de tamaño predeterminado (8192 bytes = 8 KB), pero se puede personalizar pasando un segundo argumento al constructor.

**Ejemplo 4: Especificar el tamaño del buffer**

```java
import java.io.*;

public class BufferPersonalizado {
    public static void main(String[] args) {
        // Buffer de 8 KB para ambos flujos (ideal para archivos grandes)
        try (BufferedReader br = new BufferedReader(new FileReader("entrada.txt"), 8192);
             BufferedWriter bw = new BufferedWriter(new FileWriter("salida.txt"), 8192)) {

            String linea;
            while ((linea = br.readLine()) != null) {
                bw.write(linea);
                bw.newLine();
            }
            System.out.println("Archivo procesado con buffer personalizado.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

**Explicación:**
- Se usa un buffer de **8192 bytes** para optimizar el rendimiento en archivos grandes.

> 🚀 **COMPLEMENTO (Fuera de temario):**
> El tamaño óptimo de buffer depende del sistema. Generalmente, un múltiplo del tamaño de sector del disco (512 B, 4 KB, 8 KB) da mejores resultados. Para archivos SSD modernos, buffers entre **64 KB y 1 MB** pueden dar ganancias de rendimiento significativas.

[🏠 Volver al Índice](#indice)

---

<a id="sec3_8"></a>
## 3.8 Manejo de Excepciones y Cierre de Recursos

Para evitar fugas de memoria, siempre se deben cerrar los flujos de entrada y salida. Esto se puede hacer de dos formas:

**1. Bloques try-finally (forma antigua):**

```java
BufferedReader br = null;
try {
    br = new BufferedReader(new FileReader("archivo.txt"));
    // Procesar archivo...
} catch (IOException e) {
    e.printStackTrace();
} finally {
    try {
        if (br != null) br.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

**2. try-with-resources (forma recomendada):**

```java
try (BufferedReader br = new BufferedReader(new FileReader("archivo.txt"))) {
    // Procesar archivo...
} catch (IOException e) {
    e.printStackTrace();
}
```

> 💡 **TIPS Prácticos:**
> En cualquier examen o práctica, **usa siempre `try-with-resources`**. Es más limpio, más seguro y demuestra conocimiento de las buenas prácticas de Java moderno. El bloque `try-finally` solo deberías saber identificarlo para entender código legacy.

[🏠 Volver al Índice](#indice)

---

<a id="sec3_9"></a>
## 3.9 Conclusión

*   **Usar siempre `BufferedReader` y `BufferedWriter`** para mejorar el rendimiento en la lectura y escritura de archivos de texto.
*   **Utilizar `readLine()` y `newLine()`** para manipular archivos de texto de manera eficiente, evitando bucles carácter a carácter.
*   **Especificar un tamaño de buffer adecuado** cuando se manejan archivos grandes, ajustándolo a múltiplos del tamaño de sector del disco.
*   **Usar `try-with-resources`** para evitar fugas de memoria y garantizar el cierre del archivo incluso si ocurre una excepción.

[🏠 Volver al Índice](#indice)

---

<a id="sec3_10"></a>
## 3.10 Ejercicios Prácticos

> 💡 **TIPS Prácticos:**
> El **Ejercicio 1** es el más desafiante: necesitarás leer un archivo remoto de internet (investiga `URL` y `InputStreamReader`) y procesar las palabras con una estructura `Map` (`HashMap` para conteo, `TreeMap` o `LinkedHashMap` ordenado para presentar los resultados). Los ejercicios 2–10 son progresivos; intenta resolverlos sin mirar la teoría para consolidar el aprendizaje.

**Ejercicio 1: El Quijote — 100 palabras más repetidas**
*   **Enlace:** [El Quijote en Gutenberg Library](https://www.gutenberg.org/cache/epub/2000/pg2000.txt)
*   **Enunciado:** Realiza una lista de las 100 palabras más repetidas en el libro. Para contar las palabras, usa la colección o colecciones más óptimas y optimiza la lectura del fichero usando `BufferedReader`.

> **Nota:** Puedes suponer que las palabras junto a signos de puntuación o acentos son diferentes (por ejemplo, `que` y `¿que` pueden ser palabras diferentes; `como` y `cómo` pueden ser diferentes).

**Ejercicio 2: Leer un Archivo y Contar las Palabras**
*   **Enunciado:** Escribe un programa que lea un archivo de texto `entrada.txt` y cuente cuántas palabras contiene.

**Ejercicio 3: Reemplazar una Palabra en un Archivo**
*   **Enunciado:** Escribe un programa que reemplace todas las apariciones de la palabra `"Java"` por `"Python"` en `entrada.txt` y guarde el resultado en `modificado.txt`.

**Ejercicio 4: Copiar Solo Líneas que Contienen una Palabra Específica**
*   **Enunciado:** Crea un programa que copie las líneas de `entrada.txt` a `filtrado.txt` solo si contienen la palabra `"importante"`.

**Ejercicio 5: Agregar Texto a un Archivo sin Sobrescribir**
*   **Enunciado:** Escribe un programa que agregue una línea de texto al final de `notas.txt` sin borrar su contenido previo.

**Ejercicio 6: Fusionar Dos Archivos de Texto**
*   **Enunciado:** Escribe un programa que lea `archivo1.txt` y `archivo2.txt` y los fusione en `fusionado.txt`.

**Ejercicio 7: Contar la Frecuencia de Cada Palabra**
*   **Enunciado:** Escribe un programa que cuente cuántas veces aparece cada palabra en `entrada.txt`.

**Ejercicio 8: Leer Solo las Primeras N Líneas**
*   **Enunciado:** Escribe un programa que lea solo las primeras 5 líneas de `entrada.txt`.

**Ejercicio 9: Invertir el Orden de las Líneas de un Archivo**
*   **Enunciado:** Escribe un programa que lea `entrada.txt` e invierta el orden de sus líneas en `invertido.txt`.

**Ejercicio 10: Eliminar Líneas en Blanco de un Archivo**
*   **Enunciado:** Escribe un programa que elimine todas las líneas vacías de `entrada.txt` y guarde el resultado en `limpio.txt`.

[🏠 Volver al Índice](#indice)
