package Clases;

import java.time.LocalDate;

public class ProductoPerecedero extends Producto {
    protected int diasParaCaducar;

    public LocalDate devolverFechaCaducidad() {
        LocalDate fechaCaducidad = LocalDate.now().plusDays(diasParaCaducar);

        return fechaCaducidad;

    }

    public double calcularPrecioFinal() {
        if (this.diasParaCaducar < 1) {
            return 0.75;
        }
        if (this.diasParaCaducar < 3) {
            return 0.5;
        } else {
            return 1;
        }
    }   
}
