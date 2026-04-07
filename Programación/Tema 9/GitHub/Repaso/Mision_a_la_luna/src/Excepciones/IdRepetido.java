package Excepciones;

public class IdRepetido extends Exception {
    private int IdRepetido;

    public IdRepetido(String message, int idRepetido) {
        super(message);
        IdRepetido = idRepetido;
    }

    public int getIdRepetido() {
        return IdRepetido;
    }

}
