package Clases;

import java.util.Arrays;

public class CajaTienda {
    private String[] nombre;
    private double[] precio;

    public CajaTienda() {
        this.nombre = new String[100];
        this.precio = new double[100];
        Arrays.fill(this.nombre, "");
        Arrays.fill(this.precio, 0);

    }

    private int encontrarPrimeroNombre(String[] matriz, String valorBuscado) {

        int i = 0;

        for (int j = 0; j < matriz.length; j++) {
            if (matriz[j].equals(valorBuscado)) {
                return j;
            }
        }
        return i;
    }

    private int encontrarPrimeroPrecio(double[] matriz, double valorBuscado) {
        int i = 0;

        for (int j = 0; j < matriz.length; j++) {
            if (matriz[j] == valorBuscado) {
                return j;
            }
        }

        return i;
    }

    public void registrarProducto(String nombre, double precio) {
        int posicionNombre = encontrarPrimeroNombre(this.nombre, "");
        int posicionPrecio = encontrarPrimeroPrecio(this.precio, 0);

        this.nombre[posicionNombre] = nombre;
        this.precio[posicionPrecio] = precio;
    }

    public String mostrarProductos(){
        String salida = "";

        int posicionNombre = encontrarPrimeroNombre(this.nombre, "");

        for (int i = 0; i < posicionNombre; i++) {
            salida += this.nombre[i] + ", " + this.precio[i] + "€\n";
        }
        
        return salida;
    }

    public double totalVenta(){
        double total = 0;

        int posicionPrecio = encontrarPrimeroPrecio(this.precio, 0);

        for (int i = 0; i < posicionPrecio; i++) {
            total += this.precio[i];
        }
        return total;

    }

    public void reiniciarCaja(){
        Arrays.fill(this.nombre, "");
        Arrays.fill(this.precio, 0);
    }
}
