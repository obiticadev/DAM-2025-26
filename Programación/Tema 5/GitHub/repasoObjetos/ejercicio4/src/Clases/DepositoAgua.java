package Clases;

public class DepositoAgua {
    private double capacidadMaxima;
    private double nivelActual;

    public DepositoAgua(double capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
        this.nivelActual = 0;
    }

    public void llenar(double litros) {
        double entradaFinal = 0;
        if (this.nivelActual < this.capacidadMaxima) {
            if ((this.nivelActual + litros) <= this.capacidadMaxima) {
                this.nivelActual += litros;
                entradaFinal = litros;
                System.out.println("Listo");
            } else {
                entradaFinal = litros - (this.nivelActual + litros - this.capacidadMaxima);
                this.nivelActual = this.capacidadMaxima;
                System.out.println("Hemos llenado cuanto hemos podido");
            }
            System.out.println("Se han introducido " + entradaFinal + "L");
        } else {
            System.out.println("Ya se encuentra lleno el depósito");
        }
    }

    public void vaciar(double litros) {
        double salidaFinal = 0;
        if (this.nivelActual > 0) {
            if ((this.nivelActual - litros) >= 0) {
                this.nivelActual -= litros;
                salidaFinal = litros;
                System.out.println("Listo");
            } else {
                salidaFinal = litros - (Math.abs(this.nivelActual - litros));
                this.nivelActual = 0;
                System.out.println("Hemos vaciado cuanto hemos podido");
            }
            System.out.println("Se ha vaciado " + salidaFinal + "L");
        } else {
            System.out.println("Ya se encuentra vacío el depósito");
        }
    }

    public double nivelActual() {
        return this.nivelActual;
    }

    public boolean estaVacio(){
        boolean estaVacio;
        if (this.nivelActual == 0) {
            estaVacio = true;
        } else {
            estaVacio = false;
        }
        return estaVacio;
    }

    public boolean estaLleno(){
        boolean estaLleno;
        if (this.nivelActual == this.capacidadMaxima) {
            estaLleno = true;
        } else {
            estaLleno = false;
        }
        return estaLleno;
    }

    public void vaciarCompleto(){
        this.nivelActual = 0;
    }

}
