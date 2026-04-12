package bloque3;

/**
 * EJERCICIO 15 — Clase Mapa: busqueda y modificacion
 * Teoria: teoria/03_Clases_Con_Arrays.md (seccion 5)
 *
 * Un mapa de terreno donde cada celda es un tipo (0=agua, 1=tierra, 2=montaña).
 * Practica metodos de busqueda y modificacion con validacion.
 */
public class Ej15_Mapa {

    public static final int AGUA = 0;
    public static final int TIERRA = 1;
    public static final int MONTANA = 2;

    private int[][] terreno;

    // TODO 1: Implementa el constructor Ej15_Mapa(int[][] terrenoInicial).
    //         Debe hacer una COPIA PROFUNDA del array recibido (no guardar la referencia).
    public Ej15_Mapa(int[][] terrenoInicial) {
        throw new UnsupportedOperationException("Implementa el constructor");
    }

    // TODO 2: Implementa buscarPrimero(int tipo) que devuelve {fila, col} de la primera
    //         ocurrencia del tipo dado, o null si no existe.
    public int[] buscarPrimero(int tipo) { return null; }

    // TODO 3: Implementa contarTipo(int tipo) que cuenta cuantas celdas tienen ese tipo.
    public int contarTipo(int tipo) { return 0; }

    // TODO 4: Implementa cambiarTipo(int fila, int col, int nuevoTipo) que devuelve boolean.
    //         Valida rango Y que nuevoTipo sea 0, 1 o 2.
    public boolean cambiarTipo(int fila, int col, int nuevoTipo) { return false; }

    // TODO 5: Implementa inundar(int fila, int col) que cambia la celda y sus 4 vecinos
    //         directos (arriba, abajo, izq, der) a AGUA. Ignora vecinos fuera de rango.
    //         Devuelve cuantas celdas cambio (las que no eran ya AGUA).
    public int inundar(int fila, int col) { return 0; }

    // TODO 6: Implementa mostrar() que devuelve String con simbolos:
    //         AGUA="~", TIERRA=".", MONTANA="^". Separados por espacio, filas por newline.
    public String mostrar() { return ""; }

    // TODO 7: Implementa getFilas(), getColumnas() y getTerreno() (copia profunda).
    public int getFilas() { return 0; }
    public int getColumnas() { return 0; }
    public int[][] getTerreno() { return null; }

    public static void main(String[] args) {
        System.out.println("=== Ejercicio 15: Mapa ===\n");
        int[][] t = {{0, 1, 1}, {1, 2, 1}, {0, 1, 0}};
        Ej15_Mapa mapa = new Ej15_Mapa(t);
        System.out.println(mapa.mostrar());
        System.out.println("Tierra: " + mapa.contarTipo(TIERRA));
        int[] pos = mapa.buscarPrimero(MONTANA);
        System.out.println("Primera montaña: " + (pos != null ? "[" + pos[0] + "," + pos[1] + "]" : "null"));
    }
}
