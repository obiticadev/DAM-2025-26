package Clases;

import java.time.LocalDateTime;

public class TodoList {

    protected final int LONGITUD_MAX = 10;
    protected Tarea[] tarea;
    protected int contador;

    public TodoList() {
        this.tarea = new Tarea[LONGITUD_MAX];
        this.contador = 0;
    }

    public Tarea[] getTarea() {
        return tarea;
    }

    public int getContador() {
        return contador;
    }

    public boolean añadirTarea(Tarea tarea) {
        if (contador != 0) {
            for (Tarea t : this.tarea) {
                if (t.getFechaCompleta().equals(tarea.getFechaCompleta())) {
                    return false;
                } else {
                    this.tarea[contador] = tarea;
                    return true;
                }
            }
        } else {
            this.tarea[contador] = tarea;
            return true;
        }
        return false;
    }

    public boolean validarFechaCompleta(LocalDateTime fechaCompletaEntrada) {
        for (Tarea t : tarea) {
            if (t.getFechaCompleta().equals(fechaCompletaEntrada)) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    public int getLONGITUD_MAX() {
        return LONGITUD_MAX;
    }

}
