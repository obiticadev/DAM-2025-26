package Clases;

public class ProductoNoPerecedero extends Producto {
    protected double descuento;
    protected Tipo tipo;

    public ProductoNoPerecedero(String nombre, double precio, double descuento, Tipo tipo) {
        super.nombre = nombre;
        super.precio = precio;
        this.descuento = descuento;
        this.tipo = tipo;
    }

    public double aplicarPromocion() {
        this.precio = this.precio - (this.precio * this.descuento / 100);
        return this.precio;
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Descuento: " + this.descuento);
        System.out.println("Tipo: " + this.tipo.toString());
        System.out.println("\nPrecio final con promoción aplicada: " + aplicarPromocion() + " €\n");
    }

}
