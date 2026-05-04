package com.cern.Clases;

public class HitoHistorico {
    private int id;
    private String url;
    private String nombre;
    private String autor;
    private int anioLanzamiento;
    private String descripcion;

    public HitoHistorico(String url, String nombre, String autor, int anioLanzamiento, String descripcion) {
        this.url = url;
        this.nombre = nombre;
        this.autor = autor;
        this.anioLanzamiento = anioLanzamiento;
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnioLanzamiento() {
        return anioLanzamiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
