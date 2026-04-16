package Clases;

public class Astronauta {
    private int id;
    private String nombre;
    private int experiencia;
    private boolean activo;

    public Astronauta(int id, String nombre, int experiencia) {
        this.id = id;
        this.nombre = nombre;
        this.experiencia = experiencia;
        this.activo = true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Astronauta other = (Astronauta) obj;
        if (id != other.id)
            return false;
        return true;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public boolean isActivo() {
        return activo;
    }

}
