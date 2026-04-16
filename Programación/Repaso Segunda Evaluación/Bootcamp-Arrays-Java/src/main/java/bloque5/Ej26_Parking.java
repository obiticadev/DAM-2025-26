package bloque5;

import java.util.ArrayList;

/**
 * EJERCICIO 26 — Simulacro: Parking
 * Teoria: teoria/05_Simulacros.md
 *
 * Parking con plantas y plazas. Cada plaza almacena matricula (String) o null si libre.
 */
public class Ej26_Parking {

    public static class Planta {
        private String[][] plazas;
        private int numPlanta;

        // TODO 1: Constructor(int numPlanta, int filas, int cols).
        //         Inicializa todas las plazas a null (libres).
        public Planta(int numPlanta, int filas, int cols) {
            throw new UnsupportedOperationException("Implementa constructor");
        }

        // TODO 2: aparcar(int fila, int col, String matricula) — valida rango,
        //         comprueba libre (null). Devuelve boolean.
        public boolean aparcar(int fila, int col, String matricula) { return false; }

        // TODO 3: sacar(String matricula) — busca en todo el array. Si encuentra,
        //         pone a null y devuelve true.
        public boolean sacar(String matricula) { return false; }

        // TODO 4: plazasLibres() — cuenta nulls.
        public int plazasLibres() { return 0; }

        public int getNumPlanta() { return numPlanta; }
        public int getFilas() { return plazas.length; }
        public int getColumnas() { return plazas[0].length; }

        public String buscarMatricula(String matricula) {
            for (int i = 0; i < plazas.length; i++)
                for (int j = 0; j < plazas[i].length; j++)
                    if (matricula.equals(plazas[i][j]))
                        return "Planta " + numPlanta + ", Fila " + i + ", Plaza " + j;
            return null;
        }

        public String mostrar() {
            StringBuilder sb = new StringBuilder();
            for (String[] fila : plazas) {
                for (int j = 0; j < fila.length; j++) {
                    sb.append(fila[j] == null ? "[    ]" : "[" + fila[j] + "]");
                    if (j < fila.length - 1) sb.append(" ");
                }
                sb.append("\n");
            }
            return sb.toString();
        }
    }

    private ArrayList<Planta> plantas;

    // TODO 5: Constructor. Carga datos con al menos 2 plantas.
    public Ej26_Parking() {
        throw new UnsupportedOperationException("Implementa constructor");
    }

    private void cargarDatos() { }

    // TODO 6: buscarCoche(String matricula) — busca en todas las plantas.
    //         Devuelve String con ubicacion o null si no encontrado.
    public String buscarCoche(String matricula) { return null; }

    // TODO 7: totalPlazasLibres() — suma de plazas libres de todas las plantas.
    public int totalPlazasLibres() { return 0; }

    public ArrayList<Planta> listar() { return new ArrayList<>(plantas); }

    public static void main(String[] args) {
        System.out.println("=== Ejercicio 26: Parking ===\n");
        Ej26_Parking parking = new Ej26_Parking();
        System.out.println("Plazas libres: " + parking.totalPlazasLibres());
    }
}
