package nivel5_profesional_colecciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import modelos.Aventurero;

public class Ejercicio13_CollectAgrupacionYParticion {

    public static void demostracion() {
        System.out.println("--- EL PODER MASIVO DE GROUPING-BY ---");
        System.out.println("Los Streams no solo filtran agua. Tienen inteligencia de empaquetado.");
        System.out.println(
                "¿Tienes 1000 aventureros desordenados y te piden separarlos por 'Clase' (Mago, Guerrero...) en listas independientes?");
        System.out.println(
                "Puedes ordenarlo a Java y creará un Diccionario donde las Claves son la Clase, y los Valores son Sub-Listas con sus pjs.");
        System.out.println(
                "Map<Tipos, List<Obj>> m = lista.stream().collect( Collectors.groupingBy( a -> a.getParam() ) );");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 13: EL REPARTO CLASISTA ---");
        List<Aventurero> multitiud = new ArrayList<>();
        multitiud.add(new Aventurero("Arthur", "Guerrero", 80, 500, true));
        multitiud.add(new Aventurero("Lancelot", "Guerrero", 90, 500, true));
        multitiud.add(new Aventurero("Merlin", "Mago", 99, 1000, true));
        multitiud.add(new Aventurero("Vivi", "Mago", 45, 1200, true));
        multitiud.add(new Aventurero("Robin", "Arquero", 40, 500, true));

        // TODO: Extrae el stream de la multitud y haz que caiga en el recolector
        // groupingBy.
        // Agrupa los jugadores en listas usando su 'claseClase' como Llave separadora
        // (e.g. "Mago", "Guerrero").

        Map<String, List<Aventurero>> escuadrones = null; // <- Aplica el multitiud.stream().collect(...)
        escuadrones = multitiud.stream()
                .collect(Collectors.groupingBy(Aventurero::getClaseClase));
        escuadrones.forEach((clase, lista) -> {
            System.out.println("Escuadrón: " + clase.toUpperCase());
            lista.forEach(System.out::println);

        });

        // --- VALIDACIÓN ---
        if (escuadrones != null && escuadrones.size() == 3 && escuadrones.get("Guerrero").size() == 2
                && escuadrones.get("Mago").size() == 2) {
            System.out.println(
                    ">> ¡CORRECTO! Una operación de agrupación que costaría 15 líneas en Java <= 7 ejecutada limpiamente en 1.\033[0;32m [OK]\033[0m");
            System.out.println("Tiembla SQL, llegan los Streams de Java.");
        } else {
            System.err.println(">> [ERROR] Asegúrate de agrupar usando el getter de su claseClase.");
        }
    }
}
