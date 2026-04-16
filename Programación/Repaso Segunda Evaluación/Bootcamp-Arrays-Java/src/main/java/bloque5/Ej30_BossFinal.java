package bloque5;

import java.util.ArrayList;

/**
 * EJERCICIO 30 — BOSS FINAL: Cine Multisala Completo
 * Teoria: teoria/05_Simulacros.md + TODOS los bloques anteriores.
 *
 * Reproduce el ejercicio real del cine de la segunda evaluacion.
 * Integra: Clase con array bidi de objetos, excepcion custom, DAO, logica completa.
 *
 * Este es tu examen de practica definitivo.
 */
public class Ej30_BossFinal {

    // --- Excepcion personalizada ---
    public static class ErrorAsientos extends IllegalArgumentException {
        public ErrorAsientos(String msg) { super(msg); }
    }

    // --- Clase Butaca ---
    public static class Butaca {
        private static int contadorReservas = 0;
        private char disponibilidad; // 'L' libre, 'O' ocupado
        private int idReserva;

        // TODO 1: Constructor — disponibilidad = 'L', idReserva = 0.
        public Butaca() {
            throw new UnsupportedOperationException("Implementa constructor Butaca");
        }

        // TODO 2: reservar() — cambia a 'O', asigna id = ++contadorReservas.
        public void reservar() { }

        // TODO 3: liberar() — cambia a 'L', idReserva = 0.
        public void liberar() { }

        public char getDisponibilidad() { return disponibilidad; }
        public int getIdReserva() { return idReserva; }
        public static void resetContador() { contadorReservas = 0; }
        public static int getContador() { return contadorReservas; }
    }

    // --- Clase SesionCine ---
    public static class SesionCine {
        private int numSesion;
        private String pelicula;
        private Butaca[][] butacas;
        private double precioEntrada;

        // TODO 4: Constructor(int numSesion, String peli, int filas, int cols, double precio).
        //         Valida: filas entre 1 y 10, cols entre 1 y 15.
        //         Si no, lanza ErrorAsientos. Inicializa CADA butaca con new en bucle.
        public SesionCine(int numSesion, String pelicula, int filas, int cols, double precioEntrada) {
            throw new UnsupportedOperationException("Implementa constructor SesionCine");
        }

        // TODO 5: reservarButaca(int fila, int col) — valida rango, comprueba 'L',
        //         llama butaca.reservar(). Devuelve boolean.
        public boolean reservarButaca(int fila, int col) { return false; }

        // TODO 6: liberarButaca(int idReserva) — busca en TODO el array.
        //         Si encuentra, llama butaca.liberar(). Devuelve boolean.
        public boolean liberarButaca(int idReserva) { return false; }

        public double recaudacion() {
            int ocupadas = 0;
            for (Butaca[] fila : butacas)
                for (Butaca b : fila) if (b.getDisponibilidad() == 'O') ocupadas++;
            return ocupadas * precioEntrada;
        }

        public String mostrarSala() {
            StringBuilder sb = new StringBuilder();
            for (Butaca[] fila : butacas) {
                for (Butaca b : fila)
                    sb.append(b.getDisponibilidad()).append(" ");
                sb.append("\n");
            }
            return sb.toString();
        }

        public int getNumSesion() { return numSesion; }
        public String getPelicula() { return pelicula; }
        public int getFilas() { return butacas.length; }
        public int getColumnas() { return butacas[0].length; }
        public double getPrecioEntrada() { return precioEntrada; }
    }

    // --- DAO ---
    private ArrayList<SesionCine> sesiones;

    public Ej30_BossFinal() {
        sesiones = new ArrayList<>();
        cargarDatos();
    }

    // TODO 7: Implementa cargarDatos() con los siguientes datos:
    //         Sesion 1: "Inception",      5 filas, 10 cols, 8.50€
    //         Sesion 2: "Matrix",         4 filas, 8 cols,  7.00€
    //         Sesion 3: "Interstellar",   6 filas, 12 cols, 9.00€
    private void cargarDatos() { }

    public SesionCine buscarSesion(int numSesion) {
        for (SesionCine s : sesiones) if (s.getNumSesion() == numSesion) return s;
        return null;
    }

    public boolean liberarGlobal(int idReserva) {
        for (SesionCine s : sesiones) if (s.liberarButaca(idReserva)) return true;
        return false;
    }

    public double recaudacionTotal() {
        double total = 0;
        for (SesionCine s : sesiones) total += s.recaudacion();
        return total;
    }

    public ArrayList<SesionCine> listar() { return new ArrayList<>(sesiones); }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION MASTER
    // ══════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   BOSS FINAL: Cine Multisala        ║");
        System.out.println("╚══════════════════════════════════════╝\n");

        Butaca.resetContador();
        Ej30_BossFinal cine = new Ej30_BossFinal();

        for (SesionCine s : cine.listar()) {
            System.out.println("Sesion " + s.getNumSesion() + ": " + s.getPelicula());
            System.out.println(s.mostrarSala());
        }

        // Prueba reservas
        SesionCine s1 = cine.buscarSesion(1);
        if (s1 != null) {
            s1.reservarButaca(0, 0);
            s1.reservarButaca(0, 1);
            s1.reservarButaca(2, 3);
            System.out.println("Tras 3 reservas en sesion 1:");
            System.out.println(s1.mostrarSala());
            System.out.println("Recaudacion sesion 1: " + s1.recaudacion());
        }

        System.out.println("Recaudacion total: " + cine.recaudacionTotal());

        // Liberar global
        cine.liberarGlobal(1);
        System.out.println("Tras liberar reserva 1:");
        if (s1 != null) System.out.println(s1.mostrarSala());
    }
}
