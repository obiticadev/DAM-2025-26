package bloque5;

import java.util.ArrayList;

/**
 * EJERCICIO 28 — Simulacro: Colegio
 * Teoria: teoria/05_Simulacros.md
 *
 * Gestion de clases de un colegio. Cada clase tiene filas x columnas de pupitres.
 * Cada pupitre almacena nombre del alumno (String) o null si vacio.
 */
public class Ej28_Colegio {

    public static class Clase {
        private String[][] pupitres;
        private String nombreClase;
        private int curso;

        // TODO 1: Constructor(String nombre, int curso, int filas, int cols).
        //         Valida: nombre no vacio, curso entre 1 y 6, filas/cols > 0.
        public Clase(String nombreClase, int curso, int filas, int cols) {
            throw new UnsupportedOperationException("Implementa constructor");
        }

        // TODO 2: matricular(int fila, int col, String alumno) — valida rango y libre.
        public boolean matricular(int fila, int col, String alumno) { return false; }

        // TODO 3: darDeBaja(String alumno) — busca y elimina. Devuelve boolean.
        public boolean darDeBaja(String alumno) { return false; }

        // TODO 4: alumnosMatriculados() — cuenta no-nulls.
        public int alumnosMatriculados() { return 0; }

        public int plazasLibres() {
            int c = 0;
            for (String[] f : pupitres) for (String s : f) if (s == null) c++;
            return c;
        }

        public String getNombreClase() { return nombreClase; }
        public int getCurso() { return curso; }
        public int getFilas() { return pupitres.length; }
        public int getColumnas() { return pupitres[0].length; }
    }

    private ArrayList<Clase> clases;

    // TODO 5: Constructor. Carga datos con al menos 3 clases.
    public Ej28_Colegio() {
        throw new UnsupportedOperationException("Implementa constructor");
    }

    private void cargarDatos() { }

    // TODO 6: buscarAlumno(String nombre) — busca en todas las clases.
    //         Devuelve "Clase X, Fila Y, Col Z" o null.
    public String buscarAlumno(String nombre) { return null; }

    // TODO 7: claseConMasAlumnos() — devuelve la Clase con mas alumnosMatriculados().
    public Clase claseConMasAlumnos() { return null; }

    public ArrayList<Clase> listar() { return new ArrayList<>(clases); }

    public static void main(String[] args) {
        System.out.println("=== Ejercicio 28: Colegio ===\n");
        Ej28_Colegio cole = new Ej28_Colegio();
        for (var c : cole.listar()) {
            System.out.println(c.getNombreClase() + ": " + c.alumnosMatriculados() + " alumnos");
        }
    }
}
