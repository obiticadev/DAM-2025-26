package Clases;

public class Servidor implements Comparable<Servidor> {
    private String nombre, ip;

    public Servidor(String nombre, String ip) {
        this.nombre = nombre;
        this.ip = ip;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((ip == null) ? 0 : ip.hashCode());
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
        Servidor other = (Servidor) obj;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (ip == null) {
            if (other.ip != null)
                return false;
        } else if (!ip.equals(other.ip))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Servidor [nombre=" + nombre + ", ip=" + ip + "]";
    }

    @Override
    public int compareTo(Servidor o) {
        return this.ip.compareTo(o.ip);
    }

}
