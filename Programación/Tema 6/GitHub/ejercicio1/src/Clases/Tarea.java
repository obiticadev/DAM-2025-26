package Clases;

import java.time.LocalDate;

public class Tarea {
    private String nombre;
    private String descripcion;
    private LocalDate fechaVencimiento;

    public Tarea(String nombre, String descripcion, int addDays) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaVencimiento = LocalDate.now().plusDays(addDays);
    }

    @Override
    public String toString() {
        return "Tarea [nombre=" + nombre + ", descripcion=" + descripcion + ", fechaVencimiento=" + fechaVencimiento
                + "]";
    }
    

    
    
}
