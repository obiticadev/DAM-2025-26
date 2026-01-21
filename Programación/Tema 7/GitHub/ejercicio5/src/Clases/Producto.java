package Clases;

public class Producto {
    protected String nombre;
    protected double precio;

    protected void mostrarInformacion(){
        System.out.println("Nombre: " + this.nombre);
        System.out.println("Precio: " + this.precio);
    }
    
}
