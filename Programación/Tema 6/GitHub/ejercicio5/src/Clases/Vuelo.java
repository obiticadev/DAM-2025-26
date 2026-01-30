package Clases;

import java.time.LocalDate;
import java.time.LocalTime;

import Enum.Estado;

public class Vuelo {
    protected String numeroVuelo;
    protected String origen;
    protected String destino;
    protected LocalDate fecha;
    protected LocalTime hora;
    protected Estado estado;


    public Vuelo(String numeroVuelo, String origen, String destino, LocalDate fecha, LocalTime hora, Estado estado) {
        this.numeroVuelo = numeroVuelo;
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
    }

    

    @Override
    public String toString() {
        return "Vuelo [numeroVuelo=" + numeroVuelo + ", origen=" + origen + ", destino=" + destino + ", fecha=" + fecha
                + ", hora=" + hora + ", estado=" + estado + "]";
    }


    public String getNumeroVuelo() {
        return numeroVuelo;
    }


    public void setNumeroVuelo(String numeroVuelo) {
        this.numeroVuelo = numeroVuelo;
    }


    public String getOrigen() {
        return origen;
    }


    public void setOrigen(String origen) {
        this.origen = origen;
    }


    public String getDestino() {
        return destino;
    }


    public void setDestino(String destino) {
        this.destino = destino;
    }


    public LocalDate getFecha() {
        return fecha;
    }


    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }


    public LocalTime getHora() {
        return hora;
    }


    public void setHora(LocalTime hora) {
        this.hora = hora;
    }


    public Estado getEstado() {
        return estado;
    }


    public void setEstado(Estado estado) {
        this.estado = estado;
    }


    

    
    
}
