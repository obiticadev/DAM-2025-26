package Clases;

import Interfaz.Alumno;

public class AlumnoOnline implements Alumno {
    private int PRECIO_BASE_MATRICULA = 20;
    private String nombre;
    private String curso;
    private double notaMedia;
    private int numModulos;
    private double factorParticipacion;

    public AlumnoOnline(String nombre, String curso, double notaMedia, int numModulos, double factorParticipacion)
            throws IllegalArgumentException {
        if (factorParticipacion < 0 || factorParticipacion > 1) {
            throw new IllegalArgumentException(
                    "No se puede introducir " + factorParticipacion + "como factor de participación, solo de [0..1]");
        }
        this.nombre = nombre;
        this.curso = curso;
        this.notaMedia = notaMedia;
        this.numModulos = numModulos;
        this.factorParticipacion = factorParticipacion;
    }

    @Override
    public String getNombre() {
        return this.nombre;
    }

    @Override
    public String getCurso() {
        return this.curso;
    }

    @Override
    public double getNotaMedia() {
        return this.notaMedia * this.factorParticipacion;
    }

    @Override
    public double getMatricula() {
        return this.numModulos * PRECIO_BASE_MATRICULA;
    }

    @Override
    public String getResumen() {
        return "AlumnoOnline [PRECIO_BASE_MATRICULA=" + PRECIO_BASE_MATRICULA + ", nombre=" + nombre + ", curso="
                + curso + ", notaMedia=" + notaMedia + ", numModulos=" + numModulos + ", factorParticipacion="
                + factorParticipacion + "]";
    }

}
