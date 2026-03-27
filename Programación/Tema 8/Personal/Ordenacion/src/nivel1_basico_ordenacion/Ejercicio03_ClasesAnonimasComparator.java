package nivel1_basico_ordenacion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import modelos.Mision;

public class Ejercicio03_ClasesAnonimasComparator {

    public static void demostracion() {
        System.out.println("--- EL PROBLEMA DE ORDENAR SIN 'COMPARABLE' ---");
        System.out.println("A diferencia de Aventurero, la clase 'Mision' NO implementa 'Comparable'. ES UN OBJETO DUMMY.");
        System.out.println("Si haces Collections.sort(misiones), dará un error grave.");
        System.out.println("Para solucionarlo a la antigua, programamos una Clase Anónima 'Comparator' en medio del sort.");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 03: CLASES ANÓNIMAS OBSOLETAS ---");
        List<Mision> tablon = new ArrayList<>();
        tablon.add(new Mision("Limpiar Ratas", "D", 50, 1));
        tablon.add(new Mision("Matar Dragón", "S", 5000, 99));
        tablon.add(new Mision("Escolta Real", "A", 1200, 40));
        
        // TODO: Tienes que ordenar "tablon" por el ORO (Recompensa) de MENOR a MAYOR.
        // Escribe una clase anónima pesada con Comparator<Mision> e inyéctala en Collections.sort o tablon.sort( ... );
        // Ej: tablon.sort( new Comparator<Mision>() { ... } );
        // Usa Double.compare(mision1, mision2); para que sea más fácil la matemática interna en lugar de (m1 - m2).

        
        
        
        // --- VALIDACIÓN ---
        if (tablon.size() == 3 && tablon.get(0).getTitulo().equals("Limpiar Ratas") && tablon.get(2).getTitulo().equals("Matar Dragón")) {
            System.out.println(">> ¡CORRECTO! Has usado la arcaica clase anónima. Te ha llevado unas 6 líneas. Prepárate para el Nivel 2: Lambdas.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Revisa tu clase anónima Comparator. El de menor oro debe ir primero.");
        }
    }
}
