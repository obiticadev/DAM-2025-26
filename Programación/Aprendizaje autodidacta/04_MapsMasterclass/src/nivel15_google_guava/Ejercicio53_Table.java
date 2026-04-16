package nivel15_google_guava;

import java.util.Map;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

/**
 * EJERCICIO 53 - TABLE DE GUAVA (Map<Fila, Map<Columna, Valor>>)
 * 
 * Objetivo: Representar matrices complejas condicionales en O(1) con la brutal
 * sintaxis en 2D que el Table de Guava provee.
 */
public class Ejercicio53_Table {

    public static void demostracion() {
        System.out.println("--- DEMO: TABLE BIDI-ESTRUCTURAL ---");
        // Row (Eje Y), Column (Eje X), Value (Celda)
        Table<String, String, Double> pasajes = HashBasedTable.create();
        
        pasajes.put("Madrid", "Londres", 45.5);
        pasajes.put("Madrid", "Roma", 30.0);
        pasajes.put("Barcelona", "Roma", 25.0);

        System.out.println("Coste de Madrid -> Roma: " + pasajes.get("Madrid", "Roma"));
        
        // ¡SUPER PODER DE GUAVA! Podemos aislar una columna completa transversalmente.
        // Nos devuelve un Mapa Normal Vanilla con todo lo que cruzó con Roma (Madrid y Barcelona).
        Map<String, Double> todosHaciaRoma = pasajes.column("Roma");
        System.out.println("Todas las rutas hacia Roma: " + todosHaciaRoma);
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 53: HORARIO ESCOLAR BIDI-ESTRUCTURAL ---");

        // Fila: DíaSemana | Columna: Hora | Valor: Asignatura
        Table<String, Integer, String> horario = HashBasedTable.create();
        horario.put("Lunes", 8, "Matemáticas");
        horario.put("Lunes", 9, "Historia");
        horario.put("Martes", 8, "Literatura");
        horario.put("Martes", 9, "Física");

        // TODO 1: Añade usando el .put de Guava Table ("Miercoles", 8, "Programación").
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        horario.put("Miercoles", 8, "Programación");

        // TODO 2: Quiero saber QUÉ SE IMPARTE EXACTAMENTE A LAS "8" horas CADA DÍA.
        // Vas a extraer TODA la columna "8" usando `horario.column(8)`.
        // Guárdalo en un `Map<String, String>` convencional de Java llamado 'aLasOcho'.
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        Map<String, String> aLasOcho = horario.column(8);

        // TODO 3: Elimina exclusivamente una celda con `.remove(fila, columna)`.
        // Elimina "Martes", hora 9.
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        horario.remove("Martes", 9);

        // --- VALIDACIÓN DETALLADA ---
        boolean ok1 = horario.contains("Miercoles", 8);
        boolean ok2 = aLasOcho != null && aLasOcho.size() == 3 && aLasOcho.containsKey("Martes");
        boolean ok3 = ok1 && !horario.contains("Martes", 9);

        if (!ok1) System.err.println("-> [TODO 1] Falló: No insertaste Programación en el Miercoles a las 8.");
        if (ok1 && !ok2) System.err.println("-> [TODO 2] Falló: La abstracción de columnas falló. El Map 'aLasOcho' debía tener 3 materias.");
        if (ok2 && !ok3) System.err.println("-> [TODO 3] Falló: `.remove(row, col)` no eliminó Física el martes a las 9.");

        if (ok1 && ok2 && ok3) {
            System.out.println(">> PERFECTO: Dile adiós a los farragosos mapas TheNested vanilla para organizar tableros y cuadrículas en tus videojuegos o apps. \033[0;32m [OK]\033[0m");
        }
    }
}
