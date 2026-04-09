package nivel2_streams_basicos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 16 — PIPELINE COMPLETO SIN GUÍA
 * 
 * Objetivo: Combinar filter + map + sorted + limit + collect sin pistas.
 */
public class Ejercicio16_PipelineCompletoSinGuia {

    public static void demostracion() {
        System.out.println("--- PIPELINE COMPLETO: TODO EN UNO ---");
        System.out.println("Ya conoces todas las piezas individuales.");
        System.out.println("Ahora toca combinarlas en un solo pipeline sin ayuda.");
        System.out.println("Piensa en el orden lógico: filtrar primero, transformar después, ordenar, limitar.\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 16: LOS 2 NOMBRES MÁS LARGOS DE SENIORS ACTIVOS ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Carlos Rodríguez", "Frontend", "JavaScript", 1, 25000, true, "carlos@corp.com"));
        empresa.add(new Empleado("Lucía Fernández", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empresa.add(new Empleado("Pedro", "QA", "Java", 3, 30000, false, null));
        empresa.add(new Empleado("Elena Martínez García", "DevOps", "Go", 6, 48000, true, "elena@corp.com"));
        empresa.add(new Empleado("Marcos", "Backend", "Rust", 8, 65000, true, "marcos@corp.com"));

        // TODO: Crea un pipeline que:
        //   1. Filtre empleados activos Y seniors (usa esSenior())
        //   2. Extraiga sus nombres con .map()
        //   3. Ordene por longitud del nombre DESCENDENTE (el más largo primero)
        //   4. Tome solo los 2 primeros con .limit()
        //   5. Recoja en List<String>
        // SIN PISTAS DE SINTAXIS.

        List<String> top2NombresLargos = null; // <- Escribe aquí

        // --- VALIDACIÓN ---
        if (top2NombresLargos != null && top2NombresLargos.size() == 2
                && top2NombresLargos.get(0).equals("Elena Martínez García")
                && top2NombresLargos.get(1).equals("Lucía Fernández")) {
            System.out.println(">> CORRECTO: Pipeline completo de 5 operaciones encadenadas sin ayuda.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Esperado: [Elena Martínez García, Lucía Fernández]. Seniors activos, nombres largos primero, top 2.");
        }
    }
}
