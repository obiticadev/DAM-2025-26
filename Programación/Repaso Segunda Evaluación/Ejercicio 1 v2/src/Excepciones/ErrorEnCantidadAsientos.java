package Excepciones;

public class ErrorEnCantidadAsientos extends IllegalArgumentException {
    private int numIllegal;

    public ErrorEnCantidadAsientos(String s, int numIllegal) {
        super(s);
        this.numIllegal = numIllegal;
    }

    public int getNumIllegal() {
        return numIllegal;
    }

}
