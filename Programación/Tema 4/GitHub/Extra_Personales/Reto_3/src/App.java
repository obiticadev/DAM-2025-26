/*
Reto 3: El Gestor de "Stock" con Alertas y Ranking
(Basado en Arrays Unidimensionales)
Contexto: Tienes un almacén con 20 productos distintos (identificados por su índice 0-19).
El Reto: Crea un sistema que gestione las existencias y detecte problemas.
Función 1: int[] generarStock() -> Llena un array de 20 posiciones con números aleatorios entre 0 y 50.
Función 2: void mostrarAlertas(int[] stock, int minimo) -> Recorre el array y muestra el índice de los productos que están por debajo del mínimo.
Función 3: int[] obtenerTop3(int[] stock) -> Esta es la parte difícil. Debe devolver un array con los índices de los 3 productos que tienen más existencias, ordenados de mayor a menor.
Lógica de pensamiento: No puedes simplemente ordenar el array, porque perderías el índice original del producto. Necesitas pensar cómo mantener la relación "Producto-Cantidad".
*/

import Utilidades.Funciones;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        final int MAX = 20;
        final int MIN_PERMITIDO = 30;

        int[] producto = new int[MAX];

        System.out.println("Reto 3: El Gestor de \"Stock\" con Alertas y Ranking\n");

        Funciones.generarStock(producto);
        System.out.println("Productos bajo mínimos:\n");
        Funciones.mostrarAlertas(producto, MIN_PERMITIDO);
        Funciones.obtenerTop3(producto);

    }
}
