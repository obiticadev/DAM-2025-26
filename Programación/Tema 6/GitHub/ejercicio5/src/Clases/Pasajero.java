package Clases;

import Enum.Categoria;

public class Pasajero {
    protected String nombre;
    protected String documentoIdentidad;
    protected String asiento;
    protected Categoria categoria;


    public Pasajero(String nombre, String documentoIdentidad, String asiento, Categoria categoria) {
        this.nombre = nombre;
        this.documentoIdentidad = documentoIdentidad;
        this.asiento = asiento;
        this.categoria = categoria;
    }

    

    @Override
    public String toString() {
        return "Pasajero [nombre=" + nombre + ", documentoIdentidad=" + documentoIdentidad + ", asiento=" + asiento
                + ", categoria=" + categoria + "]";
    }



    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }


    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }


    public String getAsiento() {
        return asiento;
    }


    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }


    public Categoria getCategoria() {
        return categoria;
    }


    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    
}
