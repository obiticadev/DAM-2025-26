package Clases;

public class Sala {

    private int tipo;
    private String nomSala;
    private int capacidad;
    private boolean disponible;
    private String numReferenciaReserva;

    public Sala(int tipo, String nomSala, int capacidad) {
        this.tipo = tipo;
        this.nomSala = nomSala;
        this.capacidad = capacidad;
        this.disponible = true;
        this.numReferenciaReserva = "";
    }

    public boolean reservarSala(String numReferenciaReserva) {
        if (this.disponible == true) {
            this.numReferenciaReserva = numReferenciaReserva;
            this.disponible = false;
            return true;
        } else {
            return false;
        }
    }

    public boolean liberarSala(String numReferenciaReserva) {
        if (this.numReferenciaReserva.equals(numReferenciaReserva)) {
            if (this.disponible == false) {
                this.numReferenciaReserva = "";
                this.disponible = true;
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public String mostrarInformacion() {
        StringBuilder salida = new StringBuilder();

        salida.append("Tipo de sala: ").append(tipo).append("\n")
                .append("Nombre de la sala: ").append(nomSala).append("\n")
                .append("Capacidad: ").append(capacidad).append("\n")
                .append("¿Está disponible? ").append(disponible).append("\n")
                .append("Nombre del propietario: ").append(numReferenciaReserva).append("\n");

        return salida.toString();
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public int getTipo() {
        return tipo;
    }

    public String getNomSala() {
        return nomSala;
    }// duda

    public int getCapacidad() {
        return capacidad;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public String getnumReferenciaReserva() {
        return numReferenciaReserva;
    }

}
