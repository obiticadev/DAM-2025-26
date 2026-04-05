package nivel3_intermedio_multicriterio;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import modelos.Aventurero;

public class Ejercicio07_OrdenacionMulticriterioThenComparing {

    public static void demostracion() {
        System.out.println("--- EL DESEMPATE: .thenComparing() ---");
        System.out.println("Imagina que ordenas por Oro. Pero dos personas tienen 500 de oro. ¿Quién va antes?");
        System.out.println("Java trae la concatenación de Comparators.");
        System.out.println(
                "Comparator<Obj> multiple = Comparator.comparing(a -> a.getOro()).thenComparing(a -> a.getNivel());");
        System.out.println("Puedes encadenar infinitos criterios de desempate.");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 07: CLASIFICACIÓN JERÁRQUICA ---");
        List<Aventurero> gremio = new ArrayList<>();
        gremio.add(new Aventurero("Arthur", "Guerrero", 80, 500, true));
        gremio.add(new Aventurero("Lancelot", "Guerrero", 90, 500, true)); // Mismo oro
        gremio.add(new Aventurero("Merlin", "Mago", 99, 1000, true));
        gremio.add(new Aventurero("Robin", "Arquero", 40, 500, true)); // Mismo oro

        // TODO: Misión de Realeza.
        // Ordena la lista de forma que el criterio PRINCIPAL sea la claseClase
        // (Alfabético).
        // Y en caso de EMPATE de Clase (Como Arthur y Lancelot), ordénalos por NIVEL de
        // menor a mayor.

        /*
         * Comparator<Aventurero> miCriterio1 = Comparator
         * .comparing((Aventurero a) -> a.getClaseClase())
         * .thenComparing(a -> a.getNivel());
         */

        Comparator<Aventurero> miCriterio1 = Comparator.comparing(Aventurero::getClaseClase)
                .thenComparing(Aventurero::getNivel);

        // Aplícalo a la lista gremio
        // gremio.sort(...);
        gremio.sort(miCriterio1);

        // --- VALIDACIÓN ---
        if (gremio.size() == 4 &&
                gremio.get(1).getNombre().equals("Arthur") &&
                gremio.get(2).getNombre().equals("Lancelot")) {
            System.out.println(
                    ">> ¡CORRECTO! Has ordenado alfabéticamente por Clase y desempatado por Nivel.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(
                    ">> [ERROR] Orden incorrecto. Revisa el comparing y thenComparing. Debería ir: Arquero(Robin) -> Guerrero(Arthur) -> Guerrero(Lancelot) -> Mago(Merlin).");
        }
    }
}
