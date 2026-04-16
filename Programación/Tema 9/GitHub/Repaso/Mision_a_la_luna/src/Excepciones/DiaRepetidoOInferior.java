package Excepciones;

public class DiaRepetidoOInferior extends Exception {
    private int dia;

    public DiaRepetidoOInferior(String message, int dia) {
        super(message);
        this.dia = dia;
    }

    public int getDia() {
        return dia;
    }

}
