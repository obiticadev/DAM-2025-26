package Clases;

public class Sala {

    protected int id;
    protected String nombreSala;
    protected final int NUM_MAX_SALA = 10;
    protected boolean disponibilidad;
    protected String codPropietario;

    public Sala(int id, String nombreSala) {
        this.id = id;
        this.nombreSala = nombreSala;
        this.disponibilidad = true;
        this.codPropietario = "";
    }


    public boolean reservarSala(String codPropietario) {
        if (disponibilidad) {
            this.codPropietario = codPropietario;
            this.disponibilidad = false;
            return true;
        } else {
            return false;
        }
    }

    public boolean liberarSala(String codPropietario) {
        if (!disponibilidad && this.codPropietario.equals(codPropietario)) {
            this.codPropietario = "";
            this.disponibilidad = true;
            return true;
        } else {
            return false;
        }
    }

    public String mostrarInformacion() {
        StringBuilder salida = new StringBuilder();
        salida.append("ID Sala: ").append(id).append("\n")
                .append("Nombre de la Sala: ").append(nombreSala).append("\n")
                .append("Disponibilidad: ").append(disponibilidad).append("\n")
                .append("Código de propietario: ").append(codPropietario).append("\n");
        return salida.toString();
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public void setCodPropietario(String codPropietario) {
        this.codPropietario = codPropietario;
    }

}
