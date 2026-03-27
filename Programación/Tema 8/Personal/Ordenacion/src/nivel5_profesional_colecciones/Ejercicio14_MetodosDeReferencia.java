package nivel5_profesional_colecciones;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import modelos.Aventurero;

public class Ejercicio14_MetodosDeReferencia {

    public static void demostracion() {
        System.out.println("--- MÉTODOS DE REFERENCIA (CLASS::METHOD) ---");
        System.out.println("Cuando tu lambda '((Aventurero a) -> a.getNombre())' es TAN sencilla que literalmente NO hace");
        System.out.println("ninguna operación salvo coger un objeto y pasarlo de largo llamando a un método suyo, los puristas");
        System.out.println("inventaron los :: (Method References).");
        System.out.println("Transformación brutal visual: a -> a.getNombre() PASA A SER -> Aventurero::getNombre");
        System.out.println("System.out.println(a) PASA A SER -> System.out::println");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 14: ESTÉTICA PURA ---");
        List<Aventurero> nomina = new ArrayList<>();
        nomina.add(new Aventurero("Arthur", "Guerrero", 80, 500, true));
        nomina.add(new Aventurero("Merlin", "Mago", 99, 1000, true));
        
        // TODO: Crea un Stream desde nomina.
        // Usa '.map( Aventurero::getNombre )' usando el Método de Referencia :: para extraer sólo el Nombre de cada uno.
        // Guárdalo en una List usando '.toList()' directo o 'collect(Collectors.toList())'.
        
        List<String> nombresLimpios = null; // <- Escribe aquí
        
        
        // --- VALIDACIÓN ---
        if (nombresLimpios != null && nombresLimpios.size() == 2 && nombresLimpios.get(0).equals("Arthur")) {
            System.out.println(">> ¡CORRECTO! Has subido al peldaño visual definitivo del código Senior con los Métodos de Referencia.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Tu variable está vacía o mal mapeada usando los ::.");
        }
    }
}
