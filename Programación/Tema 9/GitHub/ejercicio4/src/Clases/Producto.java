package Clases;

public class Producto implements Comparable<Producto> {
    private final int INFERIOR_ALL = 2;
    private final int SUPERIOR_ALTO = 30;
    private final int SUPERIOR_ANCHO_LARGO = 10;

    private int alto;
    private int ancho;
    private int largo;

    public Producto(int alto, int ancho, int largo) {
        this.alto = filtrarParamtros(alto, INFERIOR_ALL, SUPERIOR_ALTO);
        this.ancho = filtrarParamtros(ancho, INFERIOR_ALL, SUPERIOR_ANCHO_LARGO);
        this.largo = filtrarParamtros(largo, INFERIOR_ALL, SUPERIOR_ANCHO_LARGO);
    }

    public Producto() {
        this.alto = INFERIOR_ALL;
        this.ancho = INFERIOR_ALL;
        this.largo = INFERIOR_ALL;
    }

    private int filtrarParamtros(int num, int limiteInferior, int limiteSuperior) {
        if (num < limiteInferior) {
            return limiteInferior;
        } else if (num > limiteSuperior) {
            return limiteSuperior;
        } else {
            return num;
        }
    }

    @Override
    public int compareTo(Producto o) {
        return Integer.compare(this.alto, o.alto);
    }

}
