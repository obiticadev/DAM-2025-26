package Clases;

public class Matriz {
    private String name;
    private boolean estado;
    
    public Matriz(String name, boolean estado) {
        this.name = name;
        this.estado = estado;
    }

    public String getName() {
        return name;
    }
    public boolean getEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
}

