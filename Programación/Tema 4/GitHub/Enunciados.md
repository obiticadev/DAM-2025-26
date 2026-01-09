# Relación de Ejercicios de Programación (Java)

**Nota:** Ninguna función mostrará nada por pantalla a no ser que se diga lo contrario.

---

### Bloque 1: Funciones Básicas y Secuenciales

1.  **Multiplicación:** Escribe un programa que pida dos números reales por teclado y muestre por pantalla el resultado de multiplicarlos. Implementa y utiliza la función: `double multiplica(double a, double b)`.
2.  **Mayoría de Edad:** Escribe un programa que pida la edad por teclado y muestre por pantalla si eres mayor de edad o no. Implementa y utiliza la función: `boolean esMayorEdad(int a)`.
3.  **Mínimo:** Escribe un programa que pida dos números enteros por teclado y muestre por pantalla cuál es el mínimo. Implementa y utiliza la función: `int minimo(int a, int b)`.
4.  **Signo:** Escribe un programa que pida un número entero por teclado y muestre por pantalla si es positivo, negativo o cero. Implementa y utiliza la función: `int dimeSigno(int a)` (Devuelve -1 si es negativo, 0 si es 0, 1 si es positivo).
5.  **Conversión Millas:** Escribe un programa que pida un valor entero en millas y muestre su equivalente en kilómetros (1 milla = 1,60934 km). Implementa y utiliza la función: `double millas_a_kilometros(int millas)`.
6.  **IVA:** Escribe un programa que pida cinco precios y muestre por pantalla el precio de venta de cada uno tras aplicarle un 21% de IVA. Implementa y utiliza la función: `double precioConIVA(double precio)`.
7.  **Rectángulo:** Escribe un programa que pida el ancho y alto de un rectángulo y muestre su área y su perímetro. Implementa y utiliza: `double perimetroRectangulo(double ancho, double alto)` y `double areaRectangulo(double ancho, double alto)`.
8.  **Sumatorio, Productorio e Intermedio:** Escribe un programa que pida un valor N entero y muestre: el sumatorio de 1 a N, el productorio de 1 a N y el valor intermedio. Implementa: `int suma1aN(int n)`, `int producto1aN(int n)` y `double intermedio1aN(int n)`.

---

### Bloque 2: Lógica y Estructuras de Control

9.  **Máximo de tres:** Pide tres valores enteros y di cuál es el mayor usando únicamente una función que compare dos valores: `int maximo(int a, int b)`.
10. **Validar Fecha:** Lee día, mes y año por separado y di si la fecha es correcta (asumiendo meses de 30 días). Crea una función que valide los datos.
11. **Tabla de Multiplicar:** Escribe la tabla de multiplicar de un número introducido. Implementa una función que reciba el entero y muestre la tabla por pantalla.
12. **Kilómetros a Millas:** Realiza un programa que traduzca kilómetros a millas mediante una función.
13. **Cálculo de Descuento:** Pide la cantidad sin descuento y la cantidad pagada. Crea una función que devuelva el % de descuento aplicado.
14. **Triángulo de Caracteres:** Escribe una función que reciba un carácter y un número de líneas para mostrar por pantalla un triángulo invertido/relleno.
15. **Array 1-100:** Crea un array con los primeros 100 números naturales. Muestra la suma y la media usando funciones específicas para cada cálculo.
16. **Relleno Aleatorio:** Crea un array del tamaño indicado por teclado y rellénalo con valores aleatorios usando `Math.random()`.
17. **Primos:** Pide números hasta que se introduzca un 0. Indica para cada uno si es primo mediante la función: `boolean esPrimo(int n)`.
18. **NIF/DNI:** Pide el DNI y muestra su letra asociada.
    *   *Cálculo:* Resto de `DNI / 23`.
    *   *Equivalencia:* 0:T, 1:R, 2:W, 3:A, 4:G, 5:M, 6:Y, 7:F, 8:P, 9:D, 10:X, 11:B, 12:N, 13:J, 14:Z, 15:S, 16:Q, 17:V, 18:H, 19:L, 20:C, 21:K, 22:E.
19. **Pitágoras:** Comprueba si tres valores (x, y, z) cumplen $x^2 + y^2 = z^2$ mediante una función.
20. **Tablas del 1 al 10:** Imprime todas las tablas de multiplicar del 1 al 10 reutilizando la función del ejercicio 11.
21. **Menú de Geometría:** Crea un menú interactivo (1. Circunferencia, 2. Área, 3. Volumen, 4. Todas, 5. Salir). Implementa funciones para el `menu()`, `pideRadio()` y los cálculos respectivos.

---

### Bloque 3: Arrays Unidimensionales

22. **Suma de elementos:** Almacena 10 números en un array y calcula su suma total.
23. **Valor máximo y mínimo:** En un array de 15 enteros, encuentra el valor máximo, el mínimo y sus posiciones.
24. **Contar pares e impares:** Rellena un array de 20 enteros aleatorios (1-100) y cuenta cuántos hay de cada tipo.
25. **Invertir un array:** Pide 8 números y muéstralos en orden inverso al introducido.
26. **Rotación de elementos:** Rota los elementos de un array de 10 números una posición a la derecha.
27. **Eliminación de un elemento:** Crea un array de 10 enteros, pide un índice (0-9) y elimina ese elemento desplazando el resto a la izquierda.
28. **Buscar un valor:** Busca un nombre en un array de 12 nombres y devuelve su posición.
29. **Mezclar dos arrays:** Dados dos arrays (A y B) de 10 elementos, genera un tercero (C) alternando sus valores.
30. **Contar ocurrencias:** Crea un array de 30 números (rango 1-5) e indica cuántas veces aparece cada número.
31. **Array de temperaturas:** Pide las temperaturas de una semana y calcula: media, día más frío y día más caluroso.

---

### Bloque 4: Arrays Bidimensionales (Matrices)

32. **Suma total:** Crea una matriz 4x4 aleatoria y suma todos sus elementos.
33. **Suma por filas y columnas:** En una matriz 3x5, muestra la suma independiente de cada fila y cada columna.
34. **Matriz identidad:** Genera una matriz identidad de tamaño NxN.
35. **Matriz transpuesta:** Rellena una matriz 3x3 y genera su transpuesta.
36. **Buscar un elemento:** Busca un carácter en una matriz 5x5 y devuelve sus coordenadas.
37. **Máximo y mínimo con posiciones:** Encuentra el Max y Min con sus coordenadas en una matriz 4x6.
38. **Suma diagonal principal y secundaria:** En una matriz cuadrada, suma ambas diagonales e indica cuál es mayor.
39. **Matriz de multiplicación:** Genera una matriz 10x10 donde cada celda sea `mat[i][j] = i * j`.
40. **Contador de vecinos:** En una matriz 6x6 de ceros y unos, cuenta para cada celda cuántos "unos" tiene alrededor.
41. **Rotación de matriz 90°:** Crea una matriz 4x4 y genera una nueva versión rotada 90° en sentido horario.

---

### Bloque 5: Práctica Final - Encuesta Docente

42. **Simulación de Encuesta (Parte 1):**
    *   Muestra 10 preguntas (clase `Utilidades`).
    *   Pide valoración del 1 al 5.
    *   Valida la entrada con la función `private static int introduceNumero()` (caracteres no numéricos o fuera de rango = 0).
    *   Muestra resultados finales: cada pregunta con su nota y la media final (solo de notas válidas).

43. **Análisis Multidimensional (Parte 2):**
    *   Obtén los datos de 10 encuestas mediante `Utilidades.getEncuestas()`.
    *   Calcula y muestra la nota media de cada una de las 10 preguntas sumando los resultados de todas las encuestas disponibles.

---

### Apéndice: Clase Utilidades

```java
public class Utilidades {
    private static String[] preguntas = new String[10];

    public static String[] devolverPreguntas() {
        preguntas[0] = "Al comienzo de cada unidad la profesora presenta lo que se va a trabajar en la misma";
        preguntas[1] = "La profesora utiliza diferentes recursos para explicar los contenidos";
        preguntas[2] = "Las explicaciones son claras y ordenadas";
        preguntas[3] = "La profesora responde a las preguntas de los estudiantes sobre la materia";
        preguntas[4] = "La profesora utiliza ejemplos prácticos para facilitar el aprendizaje";
        preguntas[5] = "La profesora fomenta la participación activa de los estudiantes";
        preguntas[6] = "La profesora proporciona retroalimentación constructiva";
        preguntas[7] = "La profesora utiliza evaluaciones para medir el progreso";
        preguntas[8] = "La profesora adapta las actividades a las necesidades del grupo";
        preguntas[9] = "La profesora crea un ambiente de aprendizaje positivo";
        return preguntas;
    }

    public static int[][] getEncuestas() {
        if (preguntas == null) devolverPreguntas();
        int[][] notas = {
            { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
            { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
            { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
            { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
            { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
            { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
            { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
            { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
            { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
            { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }
        };
        return notas;
    }
}
```