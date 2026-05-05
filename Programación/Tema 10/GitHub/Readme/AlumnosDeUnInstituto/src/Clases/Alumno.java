package Clases;

import java.io.Serializable;

public abstract class Alumno implements Serializable {
    protected String nombre;
    protected String curso;
    protected double notaMedia;
    protected int numModulo;

    public Alumno(String nombre, String curso, double notaMedia, int numModulo) {
        this.nombre = nombre;
        this.curso = curso;
        this.notaMedia = notaMedia;
        this.numModulo = numModulo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCurso() {
        return curso;
    }

    public double getNotaMedia() {
        return notaMedia;
    };

    public int getNumModulo() {
        return numModulo;
    }

    public abstract double getMatricula();

    public abstract String getResumen();

}
