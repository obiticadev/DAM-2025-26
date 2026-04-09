package nivel4_avanzado_estructuras_inyectadas;

import java.util.Set;
import java.util.TreeSet;
import modelos.Aventurero;

public class Ejercicio10_InyeccionLambdaTreeSet {

    public static void demostracion() {
        System.out.println("--- EL CONTROL MENTAL DEL TREESET ---");
        System.out.println(
                "Por defecto, un TreeSet usa el orden 'Comparable' inyectado en la clase (El NIVEL, en nuestro Aventurero).");
        System.out.println("Pero si quieres un TreeSet que ordene al vuelo por 'Nombre' y no por 'Nivel',");
        System.out.println("Puedes inyectarle un Comparator LAMBDA en sus mismos paréntesis de nacimiento:");
        System.out.println("Set<Objeto> t = new TreeSet<>( (a,b) -> a.getNombre().compareTo(b.getNombre()) );");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 10: ALTERANDO ESTRUCTURAS FUERTES ---");

        // TODO: Crea un nuevo TreeSet llamado 'realeza', pero en sus paréntesis
        // inyéctale una Lambda.
        // La lambda debe ordenar a los Aventureros por su ORO (de mayor riqueza a menor
        // riqueza).
        Set<Aventurero> realeza = null; // <- Inicializa aquí tu: new TreeSet<>(... LAMBDA ...);
        realeza = new TreeSet<>((a, b) -> Double.compare(b.getOro(), a.getOro()));

        // Se añaden en desorden total.
        if (realeza != null) {
            realeza.add(new Aventurero("Pobreton", "Guerrero", 10, 5, true));
            realeza.add(new Aventurero("Noble", "Comerciante", 2, 10000, true));
            realeza.add(new Aventurero("ClaseMedia", "Artesano", 50, 500, true));
        }

        // --- VALIDACIÓN ---
        if (realeza != null && realeza.size() == 3 && realeza.iterator().next().getNombre().equals("Noble")) {
            System.out.println(
                    ">> ¡CORRECTO! Has hackeado la base de existencia del TreeSet con una Lambda.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(
                    ">> [ERROR] Asegúrate de que realeza esté inicializado con un TreeSet que compare Oro en orden inverso (mayor primero).");
        }
    }
}
