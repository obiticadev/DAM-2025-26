package Clases;

import java.util.ArrayList;

public class SalaConEquipamiento extends Sala {

    protected ArrayList<String> equipos;

    public SalaConEquipamiento(int id, String nombreSala, ArrayList<String> equipos) {
        super(id, nombreSala);
        this.equipos = equipos;

    }

    public String mostrarEquipos() {
        StringBuilder salida = new StringBuilder();
        if (equipos.size() > 0) {
            salida.append("EQUIPAMIENTO\n------------\n");
            for (int i = 0; i < equipos.size(); i++) {
                salida.append("  ").append(i + 1).append(". ").append(equipos.get(i)).append("\n");
            }
        } else {
            salida.append("No hay equipos");
        }
        return salida.toString();
    }

    public boolean reservarSala(String codPropietario, ArrayList<String> equipos) {
        if (disponibilidad) {
            this.codPropietario = codPropietario;
            this.equipos = equipos;
            this.disponibilidad = false;
            return true;
        } else {
            return false;
        }
    }

}
