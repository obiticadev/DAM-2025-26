package Clases;

public class Articulo {
    private String nombre;
    private double precioSinIva;
    private final double iva = 0.21;
    private int stockInicial;
    private int cuantosQuedan;
    private int vendidos = 0;

    

    public Articulo(String nombre, double precioSinIva, int stockInicial, int vendidos) {
        this.nombre = nombre;
        this.precioSinIva = precioSinIva;
        this.stockInicial = stockInicial;
        this.vendidos = vendidos;
    }

    public double precioConIva(){

        double precioConIva;
        
        precioConIva = precioSinIva + precioSinIva * iva;
        
        return precioConIva;
    }

    public int cuantosQuedan(){

        cuantosQuedan = stockInicial - vendidos;
        
        return cuantosQuedan;
    }

    public String resumenDeEstado(){
        int formateoIVA = (int) (iva * 100);
        return "Nombre del producto: " + nombre + " - Precio: " + precioSinIva + "EUR - IVA: " + formateoIVA + "% - PVP: " + precioConIva() + "EUR";
    }
    

    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecioSinIva(double precioSinIva) {
        this.precioSinIva = precioSinIva;
    }

    public void setStockInicial(int stockInicial) {
        this.stockInicial = stockInicial;
    }


    public String getNombre() {
        return nombre;
    }

    public double getPrecioSinIva() {
        return precioSinIva;
    }

    public double getIva() {
        return iva;
    }

    public int getStockInicial() {
        return stockInicial;
    }

    public int getCuantosQuedan() {
        return cuantosQuedan;
    }

    public int getVendidos() {
        return vendidos;
    }

    public void setVendidos(int vendidos) {
        this.vendidos = vendidos;
    }
}
