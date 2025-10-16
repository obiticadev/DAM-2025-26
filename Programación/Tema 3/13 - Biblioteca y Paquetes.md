# Guía de Bibliotecas y Paquetes en Java

En Java, las bibliotecas y los paquetes son conceptos fundamentales para la organización, reutilización y distribución de código. Permiten a los desarrolladores estructurar proyectos de manera lógica y aprovechar funcionalidades ya existentes.

---

## 1. ¿Qué es una Biblioteca (Library)?

Una **biblioteca** en Java es una colección de código precompilado (clases, interfaces, etc.) que proporciona funcionalidades específicas listas para ser utilizadas. Se distribuyen comúnmente en archivos `.jar` (Java Archive).

El objetivo de una biblioteca es extender las capacidades de un programa sin que el desarrollador tenga que reinventar la rueda.

*   **Bibliotecas Estándar de Java (Java Standard Library):** Es el conjunto de bibliotecas que vienen incluidas con el JDK (Java Development Kit). Contiene miles de clases para tareas comunes.
*   **Bibliotecas de Terceros:** Creadas por otras organizaciones o desarrolladores para propósitos específicos (ej. `JUnit` para pruebas, `Log4j` para logging, `Spring Framework` para desarrollo de aplicaciones empresariales).

---

## 2. ¿Qué es un Paquete (Package)?

Un **paquete** es un mecanismo para **organizar** clases e interfaces relacionadas en un espacio de nombres (namespace). Actúa como una carpeta o directorio que agrupa el código de manera lógica.

### Ventajas de Usar Paquetes
*   **Organización:** Ayuda a estructurar proyectos grandes, agrupando clases por funcionalidad (ej. `com.empresa.ui`, `com.empresa.data`).
*   **Prevención de Conflictos de Nombres:** Permite que existan dos clases con el mismo nombre siempre que estén en paquetes diferentes (ej. `java.util.Date` y `java.sql.Date`).
*   **Control de Acceso:** Permite controlar la visibilidad de clases y sus miembros (métodos y atributos) usando modificadores de acceso.
*   **Reutilización de Código:** Facilita la importación y uso de módulos de código en diferentes proyectos.

---

## 3. Declaración y Estructura de un Paquete

La declaración de un paquete debe ser la **primera línea** de un archivo de código fuente Java.

### Sintaxis
```java
package nombre.del.paquete;
```

### Convención de Nomenclatura
Para garantizar que los nombres de los paquetes sean únicos a nivel mundial, la convención es usar el nombre de dominio de internet de la organización, pero invertido.

*   **Dominio:** `miempresa.com`
*   **Nombre del Paquete:** `com.miempresa.miproyecto`

Esta declaración se corresponde con una estructura de directorios en el sistema de archivos:
```
src/
└── com/
    └── miempresa/
        └── miproyecto/
            ├── ClaseA.java
            └── ClaseB.java
```

---

## 4. Uso de Paquetes y la Sentencia `import`

Para utilizar una clase que pertenece a otro paquete, debes **importarla**. La sentencia `import` se coloca después de la declaración del `package` y antes de la declaración de la clase.

### Formas de Importar

*   **Importar una clase específica (recomendado):**
    ```java
    import java.util.Scanner; // Importa solo la clase Scanner
    ```
*   **Importar todas las clases de un paquete:**
    ```java
    import java.util.*; // Importa todas las clases del paquete java.util
    ```

> **Nota Importante:** El paquete `java.lang` se importa automáticamente en todos los programas de Java, ya que contiene clases fundamentales como `String`, `Object`, `Math`, y `System`.

### Ejemplo de Creación y Uso de un Paquete Personalizado

**1. Archivo `Calculadora.java`:**
```java
// Se declara que esta clase pertenece al paquete com.miempresa
package com.miempresa;

public class Calculadora {
    public int sumar(int a, int b) {
        return a + b;
    }
}
```
**Estructura de directorios:** `src/com/miempresa/Calculadora.java`

**2. Archivo `Main.java` (en el paquete por defecto o en otro):**
```java
// Se importa la clase Calculadora para poder usarla
import com.miempresa.Calculadora;

public class Main {
    public static void main(String[] args) {
        // Se crea una instancia de la clase importada
        Calculadora calc = new Calculadora();
        int resultado = calc.sumar(5, 10);
        System.out.println("Suma: " + resultado); // Imprime: Suma: 15
    }
}
```
**Estructura de directorios:** `src/Main.java`

---

## 5. Modificadores de Acceso y Paquetes

Los modificadores de acceso definen la visibilidad de una clase o sus miembros fuera de su paquete.

| Modificador | Misma Clase | Mismo Paquete | Subclase (Otro Paquete) | Otro Paquete (Mundo) |
| :--- | :---: | :---: | :---: | :---: |
| `public` | Sí | Sí | Sí | Sí |
| `protected` | Sí | Sí | Sí | No |
| `default` (sin modificador) | Sí | Sí | No | No |
| `private` | Sí | No | No | No |