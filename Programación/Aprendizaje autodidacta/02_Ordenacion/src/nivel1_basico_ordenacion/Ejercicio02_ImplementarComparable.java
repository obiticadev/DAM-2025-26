package nivel1_basico_ordenacion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import modelos.Aventurero;

public class Ejercicio02_ImplementarComparable {

    public static void demostracion() {
        System.out.println("--- ENSEÑANDO A ORDENAR LA CLASE AVENTURERO ---");
        System.out.println("Lee primero el esquema: '01_Comparable_vs_Comparator_Arquitectura.md'.");
        System.out.println("Y luego inspecciona el archivo: 'Aventurero.java'.");
        System.out.println(
                "\nComo habrás visto, Aventurero implementa Comparable<Aventurero> y expone un método oculto 'compareTo'.");
        System.out
                .println("Al hacer esto, Collections.sort(listaAventureros) dejará de dar error, pues sabe qué usar.");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 02: EXAMINANDO EL ORDEN NATURAL ---");
        List<Aventurero> gremio = new ArrayList<>();
        gremio.add(new Aventurero("Legolas", "Arquero", 75, 500, true));
        gremio.add(new Aventurero("Gimli", "Guerrero", 70, 400, true));
        gremio.add(new Aventurero("Aragorn", "Guerrero", 80, 0, true));
        gremio.add(new Aventurero("Frodo", "Ladrón", 15, 1000, true));

        // TODO: En Aventurero se fijó que el orden natural debe ser por Nivel
        // DESCENDENTE.
        // Es decir, Aragorn(80) debería ir primero y Frodo(15) al final.
        // Ejecuta la ordenación base y comprueba si funciona con 1 sola línea de código
        // estática:
        Collections.sort(gremio);

        // --- VALIDACIÓN ---
        if (gremio.size() == 4 && gremio.get(0).getNombre().equals("Aragorn")
                && gremio.get(3).getNombre().equals("Frodo")) {
            System.out.println(
                    ">> ¡CORRECTO! Haz aprovechado el ADN Comparable de tu clase pesada.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(
                    ">> [ERROR] El gremio no está ordenado por Nivel Descendente. ¿Llamaste a Collections.sort?");
        }
    }
}
