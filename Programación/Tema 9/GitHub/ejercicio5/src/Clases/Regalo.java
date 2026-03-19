package Clases;

public class Regalo {
    private String nombre;
    private String motivo;

    public Regalo(String nombre, String motivo) {
        this.nombre = nombre;
        this.motivo = motivo;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + "\nMotivo: " + motivo + "\n";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
        Regalo other = (Regalo) obj;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        return true;
    }

}
