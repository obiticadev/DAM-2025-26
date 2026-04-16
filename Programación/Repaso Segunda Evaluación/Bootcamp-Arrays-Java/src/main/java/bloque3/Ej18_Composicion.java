package bloque3;

/**
 * EJERCICIO 18 — Composicion: array bidimensional de objetos
 * Teoria: teoria/03_Clases_Con_Arrays.md (seccion 4)
 *
 * Clase Sala que contiene un array bidi de Ej18_Celda.
 * Este es el patron exacto del ejercicio del cine.
 */
public class Ej18_Composicion {

    private Ej18_Celda[][] celdas;
    private double precioEntrada;

    // TODO 4: Implementa constructor Ej18_Composicion(int filas, int columnas, double precio).
    //         Crea el array y llena con new Ej18_Celda() en CADA posicion (bucle doble).
    //         NUNCA uses Arrays.fill.
    public Ej18_Composicion(int filas, int columnas, double precioEntrada) {
        throw new UnsupportedOperationException("Implementa el constructor");
    }

    // TODO 5: Implementa reservar(int fila, int col) — devuelve boolean.
    //         Valida rango, comprueba estado 'L', llama a celda.ocupar().
    public boolean reservar(int fila, int col) { return false; }

    // TODO 6: Implementa liberar(int idReserva) — busca en TODO el array la celda
    //         con ese id. Si la encuentra, llama a celda.liberar() y devuelve true.
    public boolean liberar(int idReserva) { return false; }

    // TODO 7: Implementa recaudacion() — cuenta celdas ocupadas * precioEntrada.
    public double recaudacion() { return 0.0; }

    public String mostrar() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < celdas.length; i++) {
            for (int j = 0; j < celdas[i].length; j++) {
                sb.append(celdas[i][j].getEstado()).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public int getFilas() { return celdas.length; }
    public int getColumnas() { return celdas[0].length; }

    public static void main(String[] args) {
        System.out.println("=== Ejercicio 18: Composicion ===\n");
        Ej18_Celda.resetContador();
        Ej18_Composicion sala = new Ej18_Composicion(3, 4, 5.25);
        System.out.println(sala.mostrar());
        sala.reservar(0, 0);
        sala.reservar(1, 2);
        System.out.println("Tras 2 reservas:");
        System.out.println(sala.mostrar());
        System.out.println("Recaudacion: " + sala.recaudacion());
        sala.liberar(1);
        System.out.println("Tras liberar id 1:");
        System.out.println(sala.mostrar());
    }
}
