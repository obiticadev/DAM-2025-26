# Introducción a la Programación

La programación está presente en casi todo lo que nos rodea: la alarma del móvil, el microondas, la televisión digital, un ascensor. Aprender a programar es aprender a resolver problemas utilizando el ordenador como herramienta.

## El Proceso de Resolución de Problemas

La búsqueda de una solución a un problema mediante la programación sigue una serie de pasos fundamentales, muy similares a los que seguiríamos en la vida real.

| En la vida real | En programación | Descripción |
| :--- | :--- | :--- |
| **Observar el problema** | **Análisis del problema** | Se requiere que el problema sea definido y comprendido claramente para poder analizarlo con todo detalle. |
| **Pensar en soluciones** | **Diseño del algoritmo** | Se define un procedimiento, paso a paso, para solucionar el problema. |
| **Aplicar la mejor solución** | **Implementación en la computadora** | Se convierte el algoritmo en un programa (código), se ejecuta y se comprueba que soluciona el problema correctamente. |

### Características de una Buena Solución

Una solución informática ideal debe tener las siguientes virtudes:

*   **Corrección y Eficacia:** La solución debe resolver el problema para el que fue diseñada de manera adecuada.
*   **Eficiencia:** Debe resolver el problema utilizando la menor cantidad de tiempo y recursos (memoria, procesador) posible.

### Principios Clave para Diseñar Soluciones

Para construir soluciones robustas y mantenibles, se aplican varios conceptos fundamentales:

1.  **Abstracción:** Consiste en analizar un problema complejo y descomponerlo en problemas más pequeños y manejables. Es la estrategia de **"divide y vencerás"**.
2.  **Encapsulación:** Se basa en ocultar la complejidad interna de un componente, mostrando solo lo necesario para interactuar con él. Esto permite cambiar la implementación interna sin afectar al resto del sistema.
3.  **Modularidad:** Implica estructurar la solución en módulos o partes independientes, donde cada uno tiene una función específica y bien definida.

---

## Algoritmos y Programas

### ¿Qué es un Algoritmo?

Un **algoritmo** es una secuencia ordenada y finita de pasos que conducen a la solución de un problema.

*   Es **independiente** del lenguaje de programación. Un mismo algoritmo puede escribirse en Java, Python, C++, etc.
*   Es **independiente** de la computadora donde se ejecuta.

#### Características Fundamentales de un Algoritmo
*   **Preciso:** Debe indicar el orden exacto de cada paso, sin ambigüedades.
*   **Definido:** Si se ejecuta varias veces con los mismos datos de entrada, debe producir siempre el mismo resultado.
*   **Finito:** Debe tener un número limitado de pasos y terminar en algún momento.

### Del Algoritmo al Programa

Un **programa** es la implementación de un algoritmo en un **lenguaje de programación** específico. El lenguaje de programación es el medio que usamos para comunicarle al ordenador los pasos que debe ejecutar.

---

## Herramientas para el Diseño de Algoritmos

Para diseñar y representar algoritmos antes de escribirlos en código, existen varias herramientas.

### 1. Diagramas de Flujo

Es una técnica que utiliza símbolos gráficos para representar la secuencia de pasos de un algoritmo. Son muy visuales y útiles en las fases iniciales de análisis.

**Símbolos Fundamentales:**

| Símbolo | Nombre | Función |
| :--- | :--- | :--- |
| ![Inicio/Fin](https://i.imgur.com/8aZ3sMv.png) | Inicio / Final | Representa el comienzo y el fin de un proceso. |
| ![Línea de Flujo](https://i.imgur.com/GgV7o3k.png) | Línea de Flujo | Indica el orden de ejecución y conecta los símbolos. |
| ![Entrada/Salida](https://i.imgur.com/gJ6Z1d2.png) | Entrada / Salida | Representa la lectura de datos (ej. del teclado) o la impresión de resultados (ej. en pantalla). |
| ![Proceso](https://i.imgur.com/6a9yq4D.png) | Proceso | Representa cualquier tipo de operación, cálculo o asignación. |
| ![Decisión](https://i.imgur.com/M6F2G6A.png) | Decisión | Permite analizar una condición para tomar un camino u otro (basado en verdadero o falso). |

**Reglas de Construcción:**
1.  Todos los símbolos deben estar conectados por líneas de flujo.
2.  A un símbolo de decisión solo pueden salir dos líneas (una para el camino "Sí/Verdadero" y otra para "No/Falso").
3.  El símbolo de inicio no tiene líneas de entrada.
4.  El símbolo de fin no tiene líneas de salida.

### 2. Pseudocódigo

Es la técnica más utilizada actualmente. Se basa en el uso de un lenguaje natural simplificado con palabras clave, instrucciones y estructuras de programación para describir un algoritmo de forma escrita.

**Ejemplo: Determinar si un número es positivo.**

**Diagrama de Flujo:**
![Diagrama de flujo de número positivo](https://i.imgur.com/2sX3K2T.png)

**Pseudocódigo correspondiente:**
```
Algoritmo numero_positivo
    Escribir "Dame un número"
    Leer n

    Si n > 0 Entonces
        Escribir "El número es positivo"
    SiNo
        Escribir "El número es negativo"
    FinSi
FinAlgoritmo
```

### 3. Tablas de Decisión

Es una herramienta tabular que ayuda a representar la lógica de decisiones complejas. Mapea todas las combinaciones posibles de condiciones con las acciones que se deben realizar en cada caso. Son muy útiles para verificar que se han considerado todos los escenarios posibles.