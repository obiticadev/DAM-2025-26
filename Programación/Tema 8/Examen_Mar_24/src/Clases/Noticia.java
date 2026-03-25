package Clases;

import java.time.LocalDate;

import Interfaz.Formateable;

public class Noticia implements Formateable, Comparable<Noticia> {
    private String titulo;
    private String contenido;
    private String autor;
    private LocalDate fechaPublicacion;

    public Noticia(String titulo, String contenido, String autor, LocalDate fechaPublicacion) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.autor = autor;
        this.fechaPublicacion = fechaPublicacion;
    }

    @Override
    public String formatear() {
        StringBuilder sb = new StringBuilder();
        sb.append("NOTICIA\n").append("Título: ").append(titulo).append("\ncontenido: ")
                .append(contenido).append("\nAutor: ").append(autor).append("\nFecha de publicación: ")
                .append(fechaPublicacion);
        return sb.toString();
    }

    @Override
    public int compareTo(Noticia o) {
        return this.autor.compareTo(o.autor);
    }

}
