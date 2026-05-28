package Clases;

public class Tarea {
    private String titulo;
    private int prioridad;
    private boolean completada;

    public Tarea(String titulo, int prioridad, boolean completada) {
        this.titulo = titulo;
        this.prioridad = prioridad;
        this.completada = completada;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public boolean isCompletada() {
        return completada;
    }

    @Override
    public String toString() {
        return titulo + " - Prioridad: " + prioridad +
                " - " + (completada ? "Completada" : "Pendiente");
    }
}
