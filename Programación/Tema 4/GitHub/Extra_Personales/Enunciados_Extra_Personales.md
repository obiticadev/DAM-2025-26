### Reto 1: El Simulador de Viaje Interestelar
**(Basado en Funciones Básicas y Secuenciales)**

**Contexto:** Quieres calcular los costes y tiempos de una misión espacial entre diferentes planetas.
**El Reto:** Crea un programa que pida la distancia en **Años Luz**, la velocidad de la nave como **porcentaje de la velocidad de la luz (0.1 a 0.99)** y el consumo de combustible por año luz.

*   **Función 1:** `double calcularTiempo(double distancia, double velocidad)` -> Devuelve los años terrestres que tardará el viaje.
*   **Función 2:** `double calcularDilatacionTemporal(double tiempoEfectivo, double velocidad)` -> Investiga la fórmula de la relatividad básica y devuelve cuánto tiempo habrá pasado en la Tierra mientras los astronautas viajan (el tiempo en la Tierra pasa más rápido).
*   **Función 3:** `double costeCombustible(double distancia, double precioLitro)` -> Devuelve el coste total.
*   **Objetivo:** El programa debe mostrar un informe detallado: Tiempo para los tripulantes vs Tiempo para los familiares en la Tierra, y el coste total de la misión.

---

### Reto 2: El Validador de Criptografía Básica (Cifrado César)
**(Basado en Lógica y Estructuras de Control)**

**Contexto:** Ya sabes convertir Hexadecimal y Binario. Ahora vamos a manipular texto a nivel lógico.
**El Reto:** Implementa un sistema de cifrado y descifrado por desplazamiento.

*   **Función 1:** `String cifrar(String texto, int desplazamiento)` -> Recibe una palabra y mueve cada letra *n* posiciones en el abecedario. Si llega a la 'Z', debe volver a la 'A'.
*   **Función 2:** `String descifrar(String texto, int desplazamiento)` -> Realiza el proceso inverso.
*   **Lógica de pensamiento:** ¿Cómo manejas los espacios? ¿Cómo te aseguras de que si el usuario introduce un desplazamiento de 30 (más que las letras del abecedario) el programa no falle? (Pista: usa el operador módulo `%`).

---

### Reto 3: El Gestor de "Stock" con Alertas y Ranking
**(Basado en Arrays Unidimensionales)**

**Contexto:** Tienes un almacén con 20 productos distintos (identificados por su índice 0-19).
**El Reto:** Crea un sistema que gestione las existencias y detecte problemas.

*   **Función 1:** `int[] generarStock()` -> Llena un array de 20 posiciones con números aleatorios entre 0 y 50.
*   **Función 2:** `void mostrarAlertas(int[] stock, int minimo)` -> Recorre el array y muestra el índice de los productos que están por debajo del mínimo.
*   **Función 3:** `int[] obtenerTop3(int[] stock)` -> Esta es la parte difícil. Debe devolver un array con los índices de los 3 productos que tienen más existencias, ordenados de mayor a menor.
*   **Lógica de pensamiento:** No puedes simplemente ordenar el array, porque perderías el índice original del producto. Necesitas pensar cómo mantener la relación "Producto-Cantidad".

---

### Reto 4: El Radar de Proximidad (Simulación de Juego)
**(Basado en Arrays Bidimensionales)**

**Contexto:** Tienes un mapa de 10x10. En él hay "obstáculos" (representados por el número 1) y "espacio vacío" (0).
**El Reto:** El usuario introduce una coordenada `(f, c)`.

*   **Función 1:** `void generarMapa(int[][] mapa, int densidad)` -> Rellena la matriz con obstáculos de forma aleatoria según la densidad (ej. 20% de obstáculos).
*   **Función 2:** `int escanearArea(int[][] mapa, int f, int c)` -> El "radar" debe mirar en un radio de 2 celdas alrededor de la posición del usuario y contar cuántos obstáculos hay en total en ese cuadrado de 5x5.
*   **Función 3:** `boolean caminoDespejado(int[][] mapa, int fila)` -> Comprueba si existe alguna fila que esté completamente vacía (todo ceros) para que una nave pueda cruzar de izquierda a derecha.
*   **Lógica de pensamiento:** Debes controlar los límites de la matriz. Si el usuario está en la posición (0,0), el radar no puede buscar en la fila -1 porque el programa lanzará un error.

---

### Proyecto Final Integrador: El Cajero Automático Inteligente
**El Reto:** Combina todo. El programa tiene un array de billetes disponibles `{50, 20, 10, 5}` y una matriz que guarda los movimientos del día `[id_cliente][cantidad_retirada]`.

1.  Pide al usuario cuánto dinero quiere sacar.
2.  **Lógica:** Calcula la cantidad **mínima** de billetes para dar esa cifra (ej. 85€ = 1x50, 1x20, 1x10, 1x5).
3.  **Validación:** Si no tienes billetes suficientes o la cifra no es múltiplo de 5, da un error.
4.  **Matriz:** Registra la operación en la matriz de clientes.
5.  **Conversión:** Al final, muestra el total retirado en el día pero convertido a **Sistema Hexadecimal** (reutilizando tu lógica de conversión).