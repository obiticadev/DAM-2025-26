package Clases;

import java.time.LocalDate;

import Interfaz.Formateable;

public abstract class Carta implements Formateable, Comparable<Carta> {
    protected String remitente;
    protected String destinatario;
    protected LocalDate fecha;
    protected String contenido;

    public Carta(String remitente, String destinatario, LocalDate fecha, String contenido) {
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.fecha = fecha;
        this.contenido = contenido;
    }
}
