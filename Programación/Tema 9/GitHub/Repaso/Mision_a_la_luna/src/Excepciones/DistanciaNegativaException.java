package Excepciones;

public class DistanciaNegativaException extends Exception {
    private double distanciaNegativa;

    public DistanciaNegativaException(String message, double distanciaNegativa) {
        super(message);
        this.distanciaNegativa = distanciaNegativa;
    }

    public double getDistanciaNegativa() {
        return distanciaNegativa;
    }

}
