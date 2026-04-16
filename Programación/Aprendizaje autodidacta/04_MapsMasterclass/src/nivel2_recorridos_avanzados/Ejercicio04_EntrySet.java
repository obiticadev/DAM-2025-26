package nivel2_recorridos_avanzados;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * EJERCICIO 04 - EL PODER DE ENTRYSET()
 * 
 * Objetivo: Iterar simultáneamente sobre Llave y Valor, reconociendo que
 * un objeto Map.Entry<K, V> no solo permite leer, sino también modificar.
 * 
 * Lee: teoria/02_Recorridos_Modernos.md
 */
public class Ejercicio04_EntrySet {

    public static void demostracion() {
        System.out.println("--- DEMO: ENTRYSET PARA LLAVE Y VALOR ---");
        Map<String, String> roles = new HashMap<>();
        roles.put("Ana", "Admin");
        roles.put("Luis", "User");
        
        Set<Map.Entry<String, String>> conjuntoEntries = roles.entrySet();
        
        for (Map.Entry<String, String> entrada : conjuntoEntries) {
            System.out.println("Usuario: " + entrada.getKey() + " -> Rol actual: " + entrada.getValue());
            
            // Podemos cambiar el valor durante la iteración!
            if (entrada.getValue().equals("User")) {
                entrada.setValue("PowerUser"); 
            }
        }
        System.out.println("Después de la promoción: " + roles + "\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 04: AUMENTO DE SALARIOS ---");
        Map<String, Double> nominas = new HashMap<>();
        nominas.put("Director", 3000.0);
        nominas.put("Senior", 2200.0);
        nominas.put("Junior", 1500.0);
        nominas.put("Becario", 800.0);

        // TODO 1: Obtén el conjunto de entradas (entrySet) de las nóminas.
        Set<Map.Entry<String, Double>> entradas = null;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 2: Recorre el conjunto 'entradas' con un bucle for tradicional (for-each).
        // Por cada entrada, si el nombre del cargo es "Senior" o "Junior", auméntales el salario (el valor) en 10%.
        // Usa entrada.setValue() y lee el valor actual con entrada.getValue().
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 3: Muestra por consola cómo han quedado los salarios tras aplicarlo
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 4: Recorre de nuevo el entrySet para calcular si el "Director" sigue siendo 
        // el que más cobra (solo como práctica, extrae ambos de la iteración). 
        // Guarda la suma total de las nóminas modificadas en 'presupuestoMensual'.
        double presupuestoMensual = 0.0;
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN ---
        boolean ok1 = nominas.get("Senior") == 2420.0;
        boolean ok2 = nominas.get("Junior") == 1650.0;
        boolean ok3 = nominas.get("Director") == 3000.0;
        boolean ok4 = presupuestoMensual == 7870.0;

        if (ok1 && ok2 && ok3 && ok4) {
            System.out.println(">> CORRECTO: Sabes mutar el Map desde un EntrySet en tiempo de ejecución.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Alguna validación falló.");
            System.err.println("   Salario Senior esperado: 2420.0 -> Tu valor: " + nominas.get("Senior"));
            System.err.println("   Salario Junior esperado: 1650.0 -> Tu valor: " + nominas.get("Junior"));
            System.err.println("   Presupuesto Mensual esperado: 7870.0 -> Tu valor: " + presupuestoMensual);
        }
    }
}
