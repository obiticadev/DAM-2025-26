package Clases;

public class Nota {
    private TipoNota tipo;
    private double nota;

    public Nota(TipoNota tipo, double nota) {
        this.tipo = tipo;
        this.nota = nota;
    }

    public TipoNota getTipo() {
        return tipo;
    }

    public double getNota() {
        return nota;
    }

}
