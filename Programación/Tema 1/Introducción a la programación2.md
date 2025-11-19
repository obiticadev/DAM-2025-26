# UT1: Introducción a la Programación

## 1.1 Datos, Algoritmos y Programas

El objetivo fundamental de un programa es procesar datos para obtener información. Este flujo se puede resumir en:

**ENTRADA (Datos) → PROCESO → SALIDA (Información)**

### Pasos para Crear un Programa

La creación de software sigue un ciclo de vida bien definido:

1.  **Fase de Resolución del Problema:**
    *   **Planificación:** Entender qué se quiere resolver y plantear posibles soluciones.
    *   **Análisis:** Conocer a fondo los requisitos y el proceso a informatizar.
    *   **Diseño del Algoritmo:** Definir los pasos lógicos para resolver el problema, incluyendo las entradas y salidas necesarias.

2.  **Fase de Implementación:**
    *   **Codificación:** Traducir el algoritmo a un lenguaje de programación específico (escribir el código fuente).
    *   **Compilación y Depuración:** Traducir el código fuente a un lenguaje que la máquina entienda y corregir los errores detectados.
    *   **Ejecución y Validación:** Ejecutar el programa y verificar que produce los resultados esperados correctamente (testing).

---

## 1.2 Lenguajes de Programación

Un lenguaje de programación es un sistema de comunicación estructurado que permite a los programadores dar instrucciones a un ordenador.

### 1. Lenguaje Máquina

Es el lenguaje fundamental, entendido directamente por el procesador del ordenador.
*   **Características:** Consiste en secuencias de ceros y unos (código binario).
*   **Inconvenientes:**
    *   **Dependiente del hardware:** Un programa solo funcionaba en un tipo específico de procesador.
    *   **Complejo y difícil de leer:** Era casi imposible de interpretar o modificar por un humano.
    *   **Propenso a errores:** Introducir largas secuencias de bits manualmente era una tarea tediosa y con alta probabilidad de fallos.

### 2. Lenguaje Ensamblador

Es la primera evolución del lenguaje máquina. Sustituye las secuencias binarias por **mnemónicos** (palabras clave cortas) más fáciles de recordar (`MUL` para multiplicar, `MOV` para mover datos).
*   **Nivel de Abstracción:** Sigue siendo un **lenguaje de bajo nivel**, ya que sus instrucciones tienen una correspondencia casi directa con las del lenguaje máquina y depende del hardware.
*   **Traducción:** Requiere un programa llamado **ensamblador** que traduce el código fuente (en ensamblador) a código objeto (lenguaje máquina).

### 3. Lenguajes de Alto Nivel

Nacen para facilitar la programación, usando un lenguaje más cercano al humano, con palabras y signos reconocibles (como el inglés).
*   **Ventajas:**
    *   **Más fáciles de aprender y usar.**
    *   **Reducen el tiempo y coste de desarrollo.**
    *   **Independientes del hardware:** Un mismo programa puede ejecutarse en diferentes máquinas (portabilidad).
    *   **Más fáciles de leer y modificar.**
*   **Traducción:** Necesitan un programa traductor (compilador o intérprete) para convertir el código de alto nivel a lenguaje máquina.

---

## 1.3 Clasificación de los Lenguajes de Programación

Los lenguajes se pueden clasificar según tres criterios principales:

1.  **El Paradigma de Programación.**
2.  **El Nivel de Abstracción.**
3.  **La Forma de Ejecución.**

### 1.3.1. Paradigmas de Programación

Un paradigma es un estilo o una "filosofía" para construir programas. Define la manera en que el programador estructura y piensa la solución a un problema.

#### Paradigma Declarativo
*   **Enfoque:** Se centra en **qué** se quiere lograr, sin especificar el **cómo**.
*   Describe el resultado final deseado, y el motor del lenguaje se encarga de encontrar la forma de alcanzarlo.
*   **Sub-paradigmas:**
    *   **Funcional:** Basado en el uso de funciones matemáticas puras. (Ej: Haskell, Lisp, Scala).
    *   **Lógico:** Basado en la lógica de predicados. El programa deduce soluciones a partir de premisas y reglas. (Ej: Prolog). Muy usado en Inteligencia Artificial.

#### Paradigma Imperativo
*   **Enfoque:** Se centra en **cómo** resolver el problema, describiendo paso a paso la secuencia de instrucciones que el ordenador debe seguir.
*   El programador da órdenes directas que modifican el estado del programa (el valor de las variables).
*   **Sub-paradigmas:**
    *   **Estructurado:** Utiliza un conjunto limitado de estructuras de control (`if`, `while`, `for`) para mejorar la claridad y reducir errores.
    *   **Orientado a Objetos (POO):** Organiza el código en "objetos" que encapsulan datos y las operaciones que los manipulan. Fomenta la reutilización. (Ej: Java, C++, Python).
    *   **Modular:** Divide un problema complejo en subproblemas o módulos más pequeños e independientes.

### 1.3.2. Nivel de Abstracción

Mide qué tan alejado está un lenguaje del código máquina y qué tan cercano está al lenguaje humano.

*   **Lenguajes de Bajo Nivel:** Ofrecen poca o ninguna abstracción del hardware. (Ej: Lenguaje Máquina, Ensamblador).
*   **Lenguajes de Alto Nivel:** Ofrecen una alta abstracción, ocultando los detalles complejos del hardware y permitiendo al programador centrarse en la lógica del problema. (Ej: Java, Python, C#).

#### Generaciones de Lenguajes
*   **1GL:** Lenguaje Máquina.
*   **2GL:** Lenguaje Ensamblador.
*   **3GL:** Lenguajes de Alto Nivel Estructurados (Ej: C, Pascal, COBOL).
*   **4GL:** Lenguajes Orientados a Objetos y de propósito específico, más cercanos al lenguaje natural (Ej: SQL, Python, Java).
*   **5GL:** Lenguajes usados en Inteligencia Artificial, basados en resolver problemas usando restricciones (Ej: Prolog).

### 1.3.3. Forma de Ejecución

#### Lenguajes Compilados
El código fuente es traducido **completamente** a código máquina antes de su ejecución.
1.  **Código Fuente:** El código escrito por el programador.
2.  **Compilador:** Traduce el código fuente a **código objeto**.
3.  **Enlazador (Linker):** Une el código objeto con las librerías necesarias para crear un **archivo ejecutable**.
*   **Ventaja:** La ejecución es muy rápida, ya que el programa ya está en lenguaje máquina.
*   **Ejemplos:** C, C++, Go.

#### Lenguajes Interpretados
El código fuente es traducido y ejecutado **línea por línea** por un programa llamado **intérprete**.
*   No se genera un archivo ejecutable independiente. Se necesita el intérprete cada vez que se ejecuta el programa.
*   **Ventaja:** Ciclo de desarrollo más rápido (no hay que compilar todo el tiempo) y mayor portabilidad si el intérprete está disponible en varias plataformas.
*   **Desventaja:** La ejecución es más lenta que en los lenguajes compilados.
*   **Ejemplos:** Python, JavaScript, Ruby.

#### Lenguajes Intermedios (o Virtuales)
Combinan ambos enfoques. El caso más famoso es **Java**.
1.  El código fuente (`.java`) es **compilado** a un código intermedio llamado **bytecode** (`.class`).
2.  Este `bytecode` no es específico de un hardware, sino que es **interpretado** por una **Máquina Virtual de Java (JVM)**.
*   **Ventaja:** "Escribe una vez, ejecuta en cualquier lugar" (*Write Once, Run Anywhere*). El mismo archivo `.class` puede ejecutarse en Windows, macOS o Linux, siempre que tengan una JVM instalada.

---

## 1.4. Herramientas y Entornos de Desarrollo

Un **IDE (Integrated Development Environment)** es una aplicación que proporciona un conjunto completo de herramientas para facilitar el desarrollo de software.

*   **Ejemplos populares para Java:** Eclipse, NetBeans, IntelliJ IDEA.
*   **Funciones principales:**
    *   **Editor de código:** Con resaltado de sintaxis, autocompletado, etc.
    *   **Compilador y/o Intérprete:** Para traducir y ejecutar el código.
    *   **Depurador (Debugger):** Para encontrar y corregir errores ejecutando el programa paso a paso.
    *   **Herramientas de construcción y automatización.**

---

## 1.5. Errores y Calidad de los Programas

El **testing de software** es el proceso de comprobar que un programa funciona correctamente y cumple con los requisitos para los que fue diseñado.

### Tipos de Errores

*   **Errores de Codificación (Bugs o Errores de Sintaxis):** Errores en la gramática del lenguaje (ej. `Syste.out` en lugar de `System.out`). Son detectados por el compilador.
*   **Errores de Ejecución (Runtime Errors):** Ocurren mientras el programa se está ejecutando, normalmente por una operación imposible (ej. dividir por cero, intentar acceder a un archivo que no existe).
*   **Errores Lógicos:** El programa se ejecuta sin fallar, pero el resultado es incorrecto. El error está en la lógica del algoritmo. Son los más difíciles de detectar.
*   **Errores de Especificación:** El programa funciona según se programó, pero no cumple con lo que el cliente o usuario realmente necesitaba, debido a un malentendido en la fase de análisis.

### Características de un Programa de Calidad

*   **Legibilidad:** El código debe ser claro, sencillo y fácil de entender.
*   **Fiabilidad:** Debe funcionar correctamente y contemplar todas las situaciones posibles.
*   **Portabilidad:** Debe poder ejecutarse en diferentes plataformas con un mínimo de cambios.
*   **Modularidad:** Debe estar dividido en partes o módulos independientes.
*   **Eficiencia:** Debe aprovechar al máximo los recursos (memoria, tiempo de ejecución).
*   **Buena Documentación:** Tanto interna (comentarios en el código) como externa (manuales de usuario/técnicos).