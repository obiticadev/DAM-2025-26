package bloque3;

/**
 * EJERCICIO 14 — Excepciones Personalizadas
 * Teoria: teoria/03_Clases_Con_Arrays.md (seccion 3)
 *
 * Clase Cuadricula que usa Ej14_DimensionException para validar dimensiones.
 * Practica lanzar, propagar y capturar excepciones custom.
 */
public class Ej14_Excepciones {

    private int[][] grid;
    private int filas;
    private int columnas;

    /**
     * Constructor. Lanza Ej14_DimensionException si:
     * - filas <= 0: mensaje "Filas no puede ser X"
     * - columnas <= 0: mensaje "Columnas no puede ser X"
     * - filas > 20: mensaje "Maximo 20 filas, recibido X"
     * - columnas > 20: mensaje "Maximo 20 columnas, recibido X"
     */
    // TODO 2: Implementa el constructor con las 4 validaciones.
    //         Usa throw new Ej14_DimensionException("mensaje").
    //         Despues de validar, crea el array y asigna atributos.
    public Ej14_Excepciones(int filas, int columnas) throws Ej14_DimensionException {
        throw new UnsupportedOperationException("Implementa el constructor");
    }

    /**
     * Metodo estatico de fabrica que intenta crear una Cuadricula.
     * Si falla, devuelve null en vez de propagar la excepcion.
     */
    // TODO 3: Implementa crearSeguro(int filas, int columnas).
    //         Usa try-catch. Si lanza Ej14_DimensionException, devuelve null.
    public static Ej14_Excepciones crearSeguro(int filas, int columnas) {
        return null;
    }

    /**
     * Establece valor con validacion. Si esta fuera de rango,
     * lanza Ej14_DimensionException con mensaje "Posicion [f][c] fuera de rango".
     */
    // TODO 4: Implementa setValorEstricto(int f, int c, int v).
    //         Si fuera de rango, throw. Si dentro, asigna.
    public void setValorEstricto(int f, int c, int v) throws Ej14_DimensionException {
        throw new UnsupportedOperationException("Implementa setValorEstricto");
    }

    /**
     * Establece valor con validacion suave. Si esta fuera de rango, devuelve false.
     */
    // TODO 5: Implementa setValorSuave(int f, int c, int v).
    //         Sin excepciones, solo boolean.
    public boolean setValorSuave(int f, int c, int v) {
        return false;
    }

    /**
     * Devuelve la representacion del tablero.
     */
    // TODO 6: Implementa mostrar() con StringBuilder.
    public String mostrar() {
        return "";
    }

    /**
     * Devuelve filas y columnas.
     */
    // TODO 7: Implementa getFilas() y getColumnas().
    public int getFilas() { return filas; }
    public int getColumnas() { return columnas; }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION MASTER
    // ══════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 14: Excepciones Personalizadas ===\n");

        try {
            Ej14_Excepciones c = new Ej14_Excepciones(3, 3);
            System.out.println("Cuadricula creada");
        } catch (Ej14_DimensionException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            new Ej14_Excepciones(-1, 5);
        } catch (Ej14_DimensionException e) {
            System.out.println("Esperado: " + e.getMessage());
        }

        Ej14_Excepciones seguro = crearSeguro(0, 5);
        System.out.println("crearSeguro(0,5): " + (seguro == null ? "null (correcto)" : "no null"));
    }
}
