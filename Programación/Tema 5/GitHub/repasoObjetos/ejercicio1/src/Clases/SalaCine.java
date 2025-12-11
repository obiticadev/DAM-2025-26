package Clases;

public class SalaCine {
    private int numEntradas;
    private int entradasVendidas;
    private double precio;
    private double recuentoTotal;
    private int numEntradasInicial;
    private double precioInicial;

    public SalaCine(int numEntradas, double precio) {
        this.numEntradas = numEntradas;
        this.precio = precio;
        this.entradasVendidas = 0;
        this.recuentoTotal = 0;
        this.numEntradasInicial = numEntradas;
        this.precioInicial = precio;

    }

    public boolean venderEntrada() {
        boolean vende;

        if (this.numEntradas != 0) {
            this.numEntradas--;
            this.entradasVendidas++;
            this.recuentoTotal += this.precio;
            vende = true;

        } else {
            vende = false;
        }
        return vende;
    }

    public void cambiarPrecioEntrada(double precio) {
        this.precio = precio;
        System.out.println("Precio cambiado de " + precio + "€ a " + this.precio + "€");
    }

    public int mostrarEntradasVendidas() {
        return this.entradasVendidas;
    }

    public int mostrarEntradasDisponibles() {
        return this.numEntradas;
    }

    public double totalRecaudado() {
        return this.recuentoTotal;
    }

    public void reiniciarSala() {
        this.numEntradas = this.numEntradasInicial;
        this.precio = this.precioInicial;
    }

    public int getNumEntradas() {
        return numEntradas;
    }

    public int getEntradasVendidas() {
        return entradasVendidas;
    }

    public double getPrecio() {
        return precio;
    }

}
