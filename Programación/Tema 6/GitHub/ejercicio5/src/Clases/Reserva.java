package Clases;

public class Reserva {
    protected Vuelo vuelo;
    protected Pasajero pasajero;
    
    public Reserva(Vuelo vuelo, Pasajero pasajero) {
        this.vuelo = vuelo;
        this.pasajero = pasajero;
    }

    
    
    @Override
    public String toString() {
        return "Reserva [vuelo=" + vuelo + ", pasajero=" + pasajero + "]";
    }



    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    
}
