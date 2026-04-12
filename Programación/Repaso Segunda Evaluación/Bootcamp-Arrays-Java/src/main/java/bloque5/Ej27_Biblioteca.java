package bloque5;

import java.util.ArrayList;

/**
 * EJERCICIO 27 — Simulacro: Biblioteca
 * Teoria: teoria/05_Simulacros.md
 *
 * Sistema de estanterias. Cada estanteria tiene filas (baldas) y columnas (posiciones).
 * Cada posicion almacena un codigo de libro (String) o null.
 */
public class Ej27_Biblioteca {

    public static class Estanteria {
        private String[][] baldas;
        private int numEstanteria;

        // TODO 1: Constructor(int num, int filas, int cols). Inicializa a null.
        public Estanteria(int numEstanteria, int filas, int cols) {
            throw new UnsupportedOperationException("Implementa constructor");
        }

        // TODO 2: colocar(int fila, int col, String codigoLibro) — valida rango,
        //         comprueba libre. Devuelve boolean.
        public boolean colocar(int fila, int col, String codigoLibro) { return false; }

        // TODO 3: retirar(String codigoLibro) — busca y retira. Devuelve boolean.
        public boolean retirar(String codigoLibro) { return false; }

        // TODO 4: buscar(String codigoLibro) — devuelve {fila, col} o null.
        public int[] buscar(String codigoLibro) { return null; }

        public int posicionesLibres() {
            int c = 0;
            for (String[] b : baldas) for (String s : b) if (s == null) c++;
            return c;
        }

        public int librosColocados() {
            int c = 0;
            for (String[] b : baldas) for (String s : b) if (s != null) c++;
            return c;
        }

        public int getNumEstanteria() { return numEstanteria; }
        public int getFilas() { return baldas.length; }
        public int getColumnas() { return baldas[0].length; }
    }

    private ArrayList<Estanteria> estanterias;

    // TODO 5: Constructor. Carga datos con al menos 3 estanterias.
    public Ej27_Biblioteca() {
        throw new UnsupportedOperationException("Implementa constructor");
    }

    private void cargarDatos() { }

    // TODO 6: buscarLibro(String codigo) — busca en todas las estanterias.
    //         Devuelve "Estanteria X, Balda Y, Pos Z" o null.
    public String buscarLibro(String codigo) { return null; }

    // TODO 7: totalLibros() — suma de librosColocados() de todas las estanterias.
    public int totalLibros() { return 0; }

    public ArrayList<Estanteria> listar() { return new ArrayList<>(estanterias); }

    public static void main(String[] args) {
        System.out.println("=== Ejercicio 27: Biblioteca ===\n");
        Ej27_Biblioteca bib = new Ej27_Biblioteca();
        System.out.println("Total libros: " + bib.totalLibros());
    }
}
