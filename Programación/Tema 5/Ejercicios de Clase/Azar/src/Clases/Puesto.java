package Clases;

public class Puesto {
    // Atributos
    private boolean ocupado; // Cambié 'estado' a 'ocupado' para que coincida con tu comentario
    private String tipo; // String normal, NO array. Un puesto es Fijo O Portatil, no los dos a la vez.
    private String nombre;
    private int posicion;

    // Constructor: Solo pedimos los datos necesarios para crear el puesto
    public Puesto(String tipo, String nombre, int posicion) {
        this.ocupado = false; // Por defecto nace libre
        this.tipo = tipo; // Asignamos el tipo que nos pasan (ej: "Fijo")
        this.nombre = nombre; // Asignamos el nombre
        this.posicion = posicion;
    }

    // Constructor vacío (opcional, por si quieres crear uno sin datos al inicio)
    public Puesto() {
        this.ocupado = false;
        this.tipo = "Sin asignar";
        this.nombre = "Sin nombre";
        this.posicion = 0;
    }

    public String devolverDatos() {
        String salida = nombre + "\n" + tipo + "\n" + ocupado;

        return salida;
    }

    // Getters y Setters
    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}