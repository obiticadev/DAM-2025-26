package Clases;

import Interfaz.Alumno;

public class AlumnoPresencial implements Alumno {
    private int PRECIO_BASE_MATRICULA = 30;
    private double DESCUENTO_FAMILIA_NUMEROSA = 0.5;
    private String nombre;
    private String curso;
    private double notaMedia;
    private int numModulos;
    private boolean familiaNumerosa;

    public AlumnoPresencial(String nombre, String curso, double notaMedia, int numModulos, boolean familiaNumerosa) {
        this.nombre = nombre;
        this.curso = curso;
        this.notaMedia = notaMedia;
        this.numModulos = numModulos;
        this.familiaNumerosa = familiaNumerosa;
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
        return this.notaMedia;
    }

    @Override
    public double getMatricula() {
        if (familiaNumerosa) {
            return this.numModulos * PRECIO_BASE_MATRICULA * DESCUENTO_FAMILIA_NUMEROSA;
        }
        return this.numModulos * PRECIO_BASE_MATRICULA;
    }

    @Override
    public String getResumen() {
        return "AlumnoPresencial [PRECIO_BASE_MATRICULA=" + PRECIO_BASE_MATRICULA + ", DESCUENTO_FAMILIA_NUMEROSA="
                + DESCUENTO_FAMILIA_NUMEROSA + ", nombre=" + nombre + ", curso=" + curso + ", notaMedia=" + notaMedia
                + ", numModulos=" + numModulos + ", familiaNumerosa=" + familiaNumerosa + "]";
    }

}
