import java.util.ArrayList;

import Clases.SalaConEquipamiento;

public class App {

    public static void main(String[] args) {
        ArrayList<String> equipos = new ArrayList<>();
        equipos.add("mesas");
        equipos.add("sillas");
        equipos.add("equipo multimedia");

        SalaConEquipamiento salaConEquipamiento = new SalaConEquipamiento(1, "Snow", equipos);
        
        System.out.println(salaConEquipamiento.mostrarInformacion() + salaConEquipamiento.mostrarEquipos());
        
        salaConEquipamiento.reservarSala("546PA");

        System.out.println(salaConEquipamiento.mostrarInformacion() + salaConEquipamiento.mostrarEquipos());

    }

}
