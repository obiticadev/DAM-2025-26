package bloque4;

import java.util.Scanner;

/**
 * EJERCICIO 20 — Menu con Scanner
 * Teoria: teoria/04_DAO_Menu.md (seccion 2)
 *
 * Menu interactivo basico que opera sobre un array bidimensional.
 * No usa DAO todavia, solo Scanner + switch.
 */
public class Ej20_Menu {

    private int[][] tablero;
    private Scanner sc;

    // TODO 1: Implementa constructor que reciba filas y columnas.
    //         Crea el tablero y el Scanner(System.in).
    public Ej20_Menu(int filas, int columnas) {
        this.tablero = new int[filas][columnas];
        this.sc = new Scanner(System.in);
    }

    // TODO 2: Implementa mostrarMenu() que imprime las opciones:
    //         1. Mostrar tablero
    //         2. Establecer valor
    //         3. Mostrar estadisticas
    //         0. Salir
    public void mostrarMenu() { }

    // TODO 3: Implementa pedirEntero(String prompt) — muestra el prompt,
    //         lee con sc.nextLine(), convierte con Integer.parseInt, retorna el int.
    //         Si falla, imprime error y devuelve -1.
    public int pedirEntero(String prompt) { return -1; }

    // TODO 4: Implementa mostrarTablero() — imprime el tablero con indices.
    public void mostrarTablero() { }

    // TODO 5: Implementa establecerValor() — pide fila, columna y valor al usuario.
    //         Valida rango. Si es valido, lo establece. Si no, mensaje de error.
    public void establecerValor() { }

    // TODO 6: Implementa mostrarEstadisticas() — imprime la suma total,
    //         el valor maximo y la media del tablero.
    public void mostrarEstadisticas() { }

    // TODO 7: Implementa ejecutar() — bucle do-while con switch que llama
    //         a los metodos segun la opcion elegida. Sale con 0.
    public void ejecutar() { }

    public int[][] getTablero() { return tablero; }

    public static void main(String[] args) {
        System.out.println("=== Ejercicio 20: Menu ===\n");
        Ej20_Menu menu = new Ej20_Menu(3, 4);
        menu.ejecutar();
    }
}
