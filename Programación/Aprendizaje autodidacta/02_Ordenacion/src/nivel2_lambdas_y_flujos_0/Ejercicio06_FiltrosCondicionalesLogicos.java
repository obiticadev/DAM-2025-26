package nivel2_lambdas_y_flujos_0;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import modelos.Aventurero;

public class Ejercicio06_FiltrosCondicionalesLogicos {

    public static void demostracion() {
        System.out.println("--- ANIDANDO PREGUNTAS LÓGICAS EN EL FILTER ---");
        System.out.println("El Predicate que le pasas a `.filter(...)` no está limitado a 1 pregunta.");
        System.out.println("Puedes usar &&, || y ! dentro de las llaves del Lambda tranquilamente.");
        System.out.println("O puedes poner varios .filter() uno tras otro (encadenando). Es a gusto del consumidor.");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 06: EL PURGADOR DE DÉBILES Y CAÍDOS ---");
        List<Aventurero> asalto = new ArrayList<>();
        asalto.add(new Aventurero("Guerrero A", "Guerrero", 15, 0, true));
        asalto.add(new Aventurero("Muerto B", "Mago", 90, 50, false)); // Muerto
        asalto.add(new Aventurero("Herrero C", "Artesano", 5, 5, true));
        asalto.add(new Aventurero("Healer D", "Clérigo", 30, 10, true)); // Pasa
        asalto.add(new Aventurero("Guerrero E", "Guerrero", 50, 0, true)); // Pasa
        asalto.add(new Aventurero("Cuerpo F", "Guerrero", 99, 999, false)); // Muerto

        // TODO: Acabas de heredar una lista ('asalto') en la que algunos hombres han
        // caído en batalla, y otros son nivel bajo.
        // Re-Colecta en 'equipoSuperviviente' ÚNICAMENTE a aquellos que:
        // 1. Estén Vivos ( isEstadoVivo() == true )
        // Y 2. Tengan un Nivel MAYOR O IGUAL a 20.

        List<Aventurero> equipoSuperviviente = asalto.stream()
                .filter(a -> a.isEstadoVivo())
                .filter(a -> a.getNivel() >= 20)
                .collect(Collectors.toList());
        System.out.println("For original");
        for (Aventurero aventurero : equipoSuperviviente) {
            System.out.println(aventurero.toString());
        }
        System.out.println("For con group");
        Map<Boolean, List<Aventurero>> grupos = asalto.stream()
                .collect(Collectors.groupingBy(a -> a.isEstadoVivo()));
        for (Map.Entry<Boolean, List<Aventurero>> estado : grupos.entrySet()) {
            System.out.println(estado);
        }

        // --- VALIDACIÓN ---
        if (equipoSuperviviente != null && equipoSuperviviente.size() == 2
                && equipoSuperviviente.contains(asalto.get(3))) {
            System.out.println(
                    ">> ¡CORRECTO! Dominas las puertas lógicas booleanas AND y OR dentro del stream.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Tu filtro dejó pasar guerreros muertos o por debajo de nivel 20.");
        }
    }
}
