package bloque5;

import java.util.ArrayList;

/**
 * EJERCICIO 29 — Simulacro: Restaurante
 * Teoria: teoria/05_Simulacros.md
 *
 * Gestion de mesas de un restaurante. Cada sala tiene filas x columnas de mesas.
 * Cada mesa: 0 = libre, >0 = num comensales.
 */
public class Ej29_Restaurante {

    public static class Sala {
        private int[][] mesas;
        private String nombreSala;
        private int capacidadMaxMesa;

        // TODO 1: Constructor(String nombre, int filas, int cols, int capMax).
        //         Valida params. Inicializa mesas a 0.
        public Sala(String nombreSala, int filas, int cols, int capacidadMaxMesa) {
            throw new UnsupportedOperationException("Implementa constructor");
        }

        // TODO 2: sentar(int fila, int col, int comensales) — valida rango,
        //         comprueba libre y comensales > 0 && <= capacidadMaxMesa.
        public boolean sentar(int fila, int col, int comensales) { return false; }

        // TODO 3: liberar(int fila, int col) — valida rango, pone a 0. Devuelve boolean.
        public boolean liberar(int fila, int col) { return false; }

        // TODO 4: totalComensales() — suma de todas las mesas.
        public int totalComensales() { return 0; }

        public int mesasOcupadas() {
            int c = 0;
            for (int[] f : mesas) for (int m : f) if (m > 0) c++;
            return c;
        }

        public int mesasLibres() {
            int c = 0;
            for (int[] f : mesas) for (int m : f) if (m == 0) c++;
            return c;
        }

        public String getNombreSala() { return nombreSala; }
        public int getFilas() { return mesas.length; }
        public int getColumnas() { return mesas[0].length; }
        public int getCapacidadMaxMesa() { return capacidadMaxMesa; }
    }

    private ArrayList<Sala> salas;

    // TODO 5: Constructor. Carga datos con al menos 2 salas.
    public Ej29_Restaurante() {
        throw new UnsupportedOperationException("Implementa constructor");
    }

    private void cargarDatos() { }

    // TODO 6: salaConMasCapacidad() — devuelve la Sala donde caben mas comensales
    //         (mesasLibres * capacidadMaxMesa).
    public Sala salaConMasCapacidad() { return null; }

    // TODO 7: totalComensalesGlobal() — suma totalComensales() de todas las salas.
    public int totalComensalesGlobal() { return 0; }

    public ArrayList<Sala> listar() { return new ArrayList<>(salas); }

    public static void main(String[] args) {
        System.out.println("=== Ejercicio 29: Restaurante ===\n");
        Ej29_Restaurante r = new Ej29_Restaurante();
        for (var s : r.listar()) {
            System.out.println(s.getNombreSala() + ": " + s.mesasLibres() + " mesas libres");
        }
    }
}
