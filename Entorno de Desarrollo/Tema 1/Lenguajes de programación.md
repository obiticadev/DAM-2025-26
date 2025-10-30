# Lenguajes de Programación

### Concepto de Lenguaje de Programación

Un lenguaje de programación es un conjunto de instrucciones, operadores y reglas de sintaxis y semántica que permiten al programador comunicarse con el hardware y software. Su objetivo es facilitar la creación de programas con un nivel de abstracción superior al código máquina.

### Clasificación de los Lenguajes de Programación

Se pueden clasificar según tres criterios principales:

1.  **Nivel de abstracción:** El grado en que se alejan del código máquina y se acercan al lenguaje humano.
2.  **Forma de ejecución:** Cómo se ejecuta el programa en el sistema.
3.  **Paradigma de programación:** El estilo de programación utilizado.

#### 1. Según el Nivel de Abstracción

*   **Bajo Nivel (Primera Generación):** Es el **código máquina**, compuesto por secuencias de 1 y 0 que el hardware entiende directamente.
*   **Medio Nivel (Segunda Generación):** Es el **lenguaje ensamblador**. Utiliza instrucciones para operaciones sencillas y tiene una alta dependencia de la máquina.
*   **Alto Nivel:** Se dividen en tres generaciones:
    *   **Tercera Generación:** La mayoría de los lenguajes actuales, muchos de ellos orientados a objetos. (Ej: Java, Python).
    *   **Cuarta Generación:** Creados con un propósito específico para reducir la cantidad de código. (Ej: MatLab, SQL).
    *   **Quinta Generación:** Lenguajes naturales que utilizan bases de conocimiento. Usados en inteligencia artificial. (Ej: Prolog, Lisp).

#### 2. Según la Forma de Ejecución

*   **Lenguajes Compilados:** Un compilador traduce el código fuente a código objeto. Luego, un enlazador une el código objeto con las librerías necesarias para crear un programa ejecutable. (Ej: C, C++).
*   **Lenguajes Interpretados:** El código fuente es ejecutado directamente por un intérprete. Traduce en tiempo real solo las instrucciones necesarias en cada ejecución. (Ej: Python).
*   **Lenguajes Intermedios o Virtuales:** Una mezcla de los anteriores. Se genera un **bytecode** que puede ser interpretado por cualquier máquina que tenga el intérprete adecuado. Son más lentos pero ofrecen la ventaja de ser multisistema. (Ej: Java).

#### 3. Según el Paradigma de Programación

*   **Imperativo:** El código se compone de una secuencia de instrucciones.
*   **Declarativo:** Especifica qué hay que hacer, pero no necesariamente cómo hacerlo.
*   **Procedimental:** El programa se divide en funciones y procedimientos que se comunican entre sí.
*   **Orientado a Objetos:** Encapsula estado y operaciones en objetos, emulando un modelo del mundo real.
*   **Funcional:** Evalúa el problema mediante funciones recursivas, enfatizando la composición de funciones.
*   **Lógico:** Define un conjunto de reglas lógicas que son interpretadas para resolver problemas.

### Tipos de Código

*   **Código Fuente:** Conjunto de instrucciones escritas en un lenguaje de programación.
*   **Código Objeto:** Resultado de compilar el código fuente. En lenguajes virtuales, se le llama **bytecode**.
*   **Código Ejecutable:** Resultado de enlazar el código objeto con las librerías necesarias.

### Fases de la Compilación

1.  **Análisis Lexicográfico:** Lee el código fuente y agrupa los caracteres en componentes léxicos (lexemas).
2.  **Análisis Sintáctico-Semántico:** Agrupa los lexemas en frases gramaticales y revisa su coherencia y significado.
3.  **Generación de Código Intermedio:** Crea una representación en pseudo-ensamblador para facilitar la traducción.
4.  **Optimización de Código:** Revisa y mejora el código intermedio para que sea más rápido y fácil de interpretar.
5.  **Generación de Código:** Genera el código objeto en lenguaje máquina.
6.  **Enlazador de Librerías:** Enlaza el código objeto con las librerías para producir el código ejecutable final.

### Actividad: Tabla de Clasificación de Lenguajes

| Lenguaje | Nivel de Abstracción | Forma de Ejecución | Paradigma de Programación |
| :--- | :--- | :--- | :--- |
| **C** | Alto Nivel (3ª Gen) | Compilado | Imperativo, Procedimental |
| **C++** | Alto Nivel (3ª Gen) | Compilado | Orientado a Objetos, Procedimental |
| **Java** | Alto Nivel (3ª Gen) | Intermedio (Virtual) | Orientado a Objetos |
| **Python** | Alto Nivel (3ª Gen) | Interpretado | Orientado a Objetos, Imperativo, Funcional |
| **Ruby** | Alto Nivel (3ª Gen) | Interpretado | Orientado a Objetos, Funcional, Imperativo |
| **Go** | Alto Nivel (3ª Gen) | Compilado | Procedimental, Imperativo |
| **Javascript** | Alto Nivel (3ª Gen) | Interpretado | Orientado a Objetos, Funcional, Imperativo |
| **SQL** | Alto Nivel (4ª Gen) | Interpretado | Declarativo |
| **PHP** | Alto Nivel (3ª Gen) | Interpretado | Orientado a Objetos, Procedimental |
| **Prolog** | Alto Nivel (5ª Gen) | Interpretado | Lógico |

### Recursos Adicionales

*   [Vídeo: Lenguajes de programación y Paradigmas](https://aulavirtual3.educa.madrid.org/ies.alonsodeavellan.alcala/pluginfile.php/246679/mod_resource/content/1/1.2.%20Lenguajes%20de%20programacio%CC%81n-Paradigmas.mp4)