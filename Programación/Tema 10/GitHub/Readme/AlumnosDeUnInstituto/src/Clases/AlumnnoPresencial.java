package Clases;

public class AlumnnoPresencial extends Alumno {
    private final int PRECIO_BASE = 30;
    private final double DESCUENTO_FAMILIA_NUMEROSA = 0.5;

    private boolean familiaNumerosa;

    public AlumnnoPresencial(String nombre, String curso, double notaMedia, int numModulo, boolean familiaNumerosa) {
        super(nombre, curso, notaMedia, numModulo);
        this.familiaNumerosa = familiaNumerosa;
    }

    @Override
    public double getMatricula() {
        if (familiaNumerosa) {
            return PRECIO_BASE * DESCUENTO_FAMILIA_NUMEROSA;
        }
        return PRECIO_BASE;
    }

    @Override
    public String getResumen() {
        return "AlumnnoPresencial [nombre=" + nombre + ", curso=" + curso + ", notaMedia=" + notaMedia
                + ", familiaNumerosa=" + familiaNumerosa + ", numModulo=" + numModulo + "]";
    }

}
