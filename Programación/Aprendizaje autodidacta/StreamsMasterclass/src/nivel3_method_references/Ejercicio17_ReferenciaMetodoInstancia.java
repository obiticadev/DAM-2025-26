package nivel3_method_references;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 17 — METHOD REFERENCE: TIPO 1 - INSTANCIA ARBITRARIA (CON GUÍA)
 * 
 * Objetivo: Sustituir lambdas simples por Clase::metodo
 * Lee primero: teoria/03_Method_References.md
 */
public class Ejercicio17_ReferenciaMetodoInstancia {

    public static void demostracion() {
        System.out.println("--- METHOD REFERENCE TIPO 1: Clase::metodoInstancia ---");
        System.out.println("Cuando tu Lambda SOLO llama a un método del objeto que recibe:");
        System.out.println("  e -> e.getNombre()    se convierte en    Empleado::getNombre");
        System.out.println("  e -> e.isActivo()     se convierte en    Empleado::isActivo");
        System.out.println("El compilador deduce que cada elemento del Stream es un Empleado.\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 17: CONVERTIR LAMBDAS A METHOD REFERENCES ---");
        List<Empleado> equipo = new ArrayList<>();
        equipo.add(new Empleado("Ana", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        equipo.add(new Empleado("Carlos", "Frontend", "JavaScript", 1, 25000, true, "carlos@corp.com"));
        equipo.add(new Empleado("Lucía", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        equipo.add(new Empleado("Pedro", "QA", "Java", 3, 30000, false, null));

        // TODO 1: Extrae los nombres usando Method Reference en lugar de Lambda
        // En vez de: .map(e -> e.getNombre())  usa la sintaxis Clase::metodo
        List<String> nombres = null; // <- Escribe aquí

        // TODO 2: Filtra solo los activos usando Method Reference en .filter()
        List<Empleado> activos = null; // <- Escribe aquí

        // --- VALIDACIÓN ---
        boolean nombresOk = nombres != null && nombres.size() == 4 && nombres.get(0).equals("Ana");
        boolean activosOk = activos != null && activos.size() == 3;
        if (nombresOk && activosOk) {
            System.out.println(">> CORRECTO: Has reemplazado Lambdas por Method References tipo Clase::metodo.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] nombres debe tener 4 elementos, activos debe tener 3.");
        }
    }
}
