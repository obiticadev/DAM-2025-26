package Clases;

public class ProductoNoPerecedero extends Producto {
    protected double descuento;
    protected Tipo tipo;
    
    public double aplicarPromocion(){
        this.descuento = this.descuento * descuento;
        return this.descuento;
    }

    @Override
    protected void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Descuento: " + this.descuento);
        System.out.println("Tipo: " + this.tipo.toString());
    }

    
    
}
