package bloque5;

import java.util.ArrayList;

/**
 * EJERCICIO 25 — Simulacro: Hotel
 * Teoria: teoria/05_Simulacros.md
 *
 * Sistema de gestion de un hotel con plantas (filas) y habitaciones (columnas).
 * Cada habitacion: 0 = libre, >0 = id huesped.
 * Integra: Clase con array bidi + DAO + logica.
 */
public class Ej25_Hotel {

    public static class Habitacion {
        private static int contadorHuespedes = 0;
        private int[][] planta; // planta[piso][numHab]
        private String nombreHotel;
        private double precioPorNoche;

        // TODO 1: Constructor(String nombre, int pisos, int habsPorPiso, double precio).
        //         Valida todo. Inicializa habitaciones a 0.
        public Habitacion(String nombreHotel, int pisos, int habsPorPiso, double precioPorNoche) {
            throw new UnsupportedOperationException("Implementa constructor");
        }

        // TODO 2: checkin(int piso, int hab) — si libre, asigna ++contadorHuespedes.
        //         Devuelve id asignado o -1.
        public int checkin(int piso, int hab) { return -1; }

        // TODO 3: checkout(int idHuesped) — busca en todo el array.
        //         Si lo encuentra, pone a 0. Devuelve boolean.
        public boolean checkout(int idHuesped) { return false; }

        // TODO 4: ocupacion() — porcentaje de habitaciones ocupadas (0.0 a 100.0).
        public double ocupacion() { return 0.0; }

        public int habitacionesLibres() {
            int c = 0;
            for (int[] piso : planta) for (int h : piso) if (h == 0) c++;
            return c;
        }

        public String getNombre() { return nombreHotel; }
        public int getPisos() { return planta.length; }
        public int getHabsPorPiso() { return planta[0].length; }
        public double getPrecioPorNoche() { return precioPorNoche; }
        public static void resetContador() { contadorHuespedes = 0; }

        public String mostrar() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < planta.length; i++) {
                sb.append("Piso ").append(i).append(": ");
                for (int j = 0; j < planta[i].length; j++) {
                    sb.append(planta[i][j] == 0 ? "[  ]" : String.format("[%2d]", planta[i][j]));
                }
                sb.append("\n");
            }
            return sb.toString();
        }
    }

    // DAO
    private ArrayList<Habitacion> hoteles;

    // TODO 5: Constructor. Inicializa lista y carga datos.
    public Ej25_Hotel() {
        throw new UnsupportedOperationException("Implementa constructor DAO");
    }

    private void cargarDatos() { }

    // TODO 6: buscarHotel(String nombre) — busca por nombre exacto.
    public Habitacion buscarHotel(String nombre) { return null; }

    // TODO 7: hotelConMasDisponibilidad() — devuelve el hotel con mas hab libres.
    public Habitacion hotelConMasDisponibilidad() { return null; }

    public ArrayList<Habitacion> listar() { return new ArrayList<>(hoteles); }

    public static void main(String[] args) {
        System.out.println("=== Ejercicio 25: Hotel ===\n");
        Habitacion.resetContador();
        Ej25_Hotel sistema = new Ej25_Hotel();
        for (var h : sistema.listar()) {
            System.out.println(h.getNombre() + " - Ocupacion: " + h.ocupacion() + "%");
        }
    }
}
