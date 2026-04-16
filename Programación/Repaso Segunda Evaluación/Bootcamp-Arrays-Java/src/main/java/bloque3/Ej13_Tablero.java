package bloque3;

/**
 * EJERCICIO 13 — Clase Tablero con array estatico + constructor con validacion
 * Teoria: teoria/03_Clases_Con_Arrays.md (secciones 1-2)
 *
 * Crea una clase Tablero que encapsule un array bidimensional de enteros.
 * El constructor valida dimensiones y lanza IllegalArgumentException si son invalidas.
 */
public class Ej13_Tablero {

    // TODO 1: Declara los atributos privados:
    //         - int[][] celdas
    //         - int filas
    //         - int columnas

    /**
     * Constructor. Crea un tablero de filas x columnas con todos los valores a 0.
     * Si filas <= 0 o columnas <= 0, lanza IllegalArgumentException con mensaje descriptivo.
     * Si filas > 10 o columnas > 10, lanza IllegalArgumentException.
     */
    // TODO 2: Implementa el constructor con TODAS las validaciones ANTES de crear el array.
    //         Orden: validar negativo/cero, validar maximo, crear array, asignar atributos.

    /**
     * Devuelve el valor en la posicion [fila][columna].
     * Si esta fuera de rango, devuelve -1.
     */
    // TODO 3: Implementa getValor(int fila, int columna).
    //         Valida rango antes de acceder.

    /**
     * Establece un valor en la posicion [fila][columna].
     * Devuelve true si se pudo, false si esta fuera de rango.
     */
    // TODO 4: Implementa setValor(int fila, int columna, int valor).
    //         Valida rango. Asigna. Devuelve boolean.

    /**
     * Devuelve un String con la representacion visual del tablero.
     * Valores separados por espacio, filas por salto de linea.
     */
    // TODO 5: Implementa mostrar() usando StringBuilder.

    /**
     * Devuelve el numero de filas.
     */
    // TODO 6: Implementa getFilas() y getColumnas().

    /**
     * Devuelve true si todas las celdas valen 0.
     */
    // TODO 7: Implementa estaVacio(). Recorre todo el array.

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION MASTER
    // ══════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 13: Tablero ===\n");

        try {
            Ej13_Tablero t = new Ej13_Tablero(3, 4);
            System.out.println("Tablero 3x4 creado");
            System.out.println(t.mostrar());
            System.out.println("Vacio: " + t.estaVacio());
            t.setValor(1, 2, 5);
            System.out.println("Tras setValor(1,2,5):");
            System.out.println(t.mostrar());
            System.out.println("getValor(1,2): " + t.getValor(1, 2));
            System.out.println("getValor(9,9): " + t.getValor(9, 9));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            Ej13_Tablero invalido = new Ej13_Tablero(-1, 5);
        } catch (IllegalArgumentException e) {
            System.out.println("Excepcion esperada: " + e.getMessage());
        }
    }

    // Placeholder para que compile - BORRA ESTO cuando implementes los TODOs
    public Ej13_Tablero(int filas, int columnas) { throw new UnsupportedOperationException("Implementa el constructor"); }
    public int getValor(int f, int c) { return -1; }
    public boolean setValor(int f, int c, int v) { return false; }
    public String mostrar() { return ""; }
    public int getFilas() { return 0; }
    public int getColumnas() { return 0; }
    public boolean estaVacio() { return false; }
}
