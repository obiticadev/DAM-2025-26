package Clases;

public class Calendario {
    private int numDia;
    private String sorpresa;
    private boolean estado;

    

    public Calendario(int numDia, String sorpresa, boolean estado) {
        this.numDia = numDia;
        this.sorpresa = sorpresa;
        this.estado = estado;
    }

    public Calendario() {
    }

    public int getNumDia() {
        return numDia;
    }

    public String getSorpresa() {
        return sorpresa;
    }

    public boolean getEstado() {
        return estado;
    }

}
