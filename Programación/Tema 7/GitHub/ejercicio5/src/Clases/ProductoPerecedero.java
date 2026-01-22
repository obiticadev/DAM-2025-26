package Clases;

import java.time.LocalDate;

public class ProductoPerecedero extends Producto {
    protected int diasParaCaducar;

    public ProductoPerecedero(String nombre, double precio, int diasParaCaducar) {
        super.nombre = nombre;
        super.precio = precio;
        this.diasParaCaducar = diasParaCaducar;
    }

    public LocalDate devolverFechaCaducidad() {
        LocalDate fechaCaducidad = LocalDate.now().plusDays(diasParaCaducar);

        return fechaCaducidad;

    }

    public double calcularPrecioFinal() {
        if (this.diasParaCaducar < 1) {
            return super.precio * 0.75;
        }
        if (this.diasParaCaducar < 3) {
            return super.precio * 0.5;
        } else {
            return super.precio * 1;
        }
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Fecha de caducidad: " + devolverFechaCaducidad().toString() + ", quedan " + diasParaCaducar
                + " días para caducar");
        System.out.println("\nPrecio final: " + calcularPrecioFinal() + " €\n");
    }

}
