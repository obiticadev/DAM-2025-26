package nivel2_lambdas_y_flujos_0;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import modelos.Aventurero;

public class Ejercicio05_IntroduccionStreamsYFiltroUnico {

    public static void demostracion() {
        System.out.println("--- PRIMER VIAJE POR UNA TUBERÍA: .stream() ---");
        System.out.println("Lee antes el esquema '03_Anatomia_de_los_Streams.md'.");
        System.out.println("Toda Colección puede volverse líquida llamando a `.stream()`.");
        System.out.println(
                "Una vez líquida, usaremos `.filter( Predicado Lambda )` para colar el agua a nuestro gusto.\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 05: EL COLADOR DE MAGOS ---");
        List<Aventurero> gremio = new ArrayList<>();
        gremio.add(new Aventurero("Legolas", "Arquero", 75, 500, true));
        gremio.add(new Aventurero("Gandalf", "Mago", 99, 0, true));
        gremio.add(new Aventurero("Vivi", "Mago", 45, 1200, true));
        gremio.add(new Aventurero("Conan", "Guerrero", 80, 50, true));

        // TODO: En lugar de crear un for(), usa gremio.stream() para recolectar SÓLO
        // LOS MAGOS.
        // Guárdalos en la variable mágica de abajo.
        // ESTRUCTURA GUÍA (¡Recuerda que .collect lo solidifica!):
        // List<Aventurero> magos = gremio.stream().filter( a -> CONDICIÓN
        // ).collect(Collectors.toList());

        List<Aventurero> soloMagos = gremio.stream()
                .filter(a -> a.getClaseClase().equals("Mago"))
                .collect(Collectors.toList());
        System.out.println("=== LISTA DE MAGOS ===");
        for (String magoString : soloMagos.stream()
                .map(mapper -> mapper.getNombre() + ": (" + mapper.getNivel() + ") de nivel")
                .collect(Collectors.toList())) {
            System.out.println(magoString);
        }

        // --- VALIDACIÓN ---
        if (soloMagos != null && soloMagos.size() == 2 && soloMagos.get(0).getNombre().equals("Gandalf")) {
            System.out.println(
                    ">> ¡CORRECTO! Has creado tu primer Pipeline y filtrado la turba por Profesión.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(
                    ">> [ERROR] Debes recolectar únicamente a Aventureros cuya clase (getClaseClase) equivalga a 'Mago'.");
        }
    }
}
