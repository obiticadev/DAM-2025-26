package Clases;

import Enum.Genero;

public class Libros {
    private String codigo;
    private String titulo;
    private String autor;
    private Genero genero;

    public Libros(String codigo, String titulo, String autor) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
        Libros other = (Libros) obj;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Libros [codigo=" + codigo + ", titulo=" + titulo + ", autor=" + autor + ", genero=" + genero + "]";
    }

}
