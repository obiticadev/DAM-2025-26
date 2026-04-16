package nivel12_interfaces_funcionales;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 84 - PATRON STRATEGY CON LAMBDAS
 * 
 * SIN GUIA. Implementa el patron Strategy usando Function en vez de clases.
 * Lee la teoria: teoria/11_Interfaces_Funcionales_Personalizadas.md (seccion 11.5)
 */
public class Ejercicio84_PatronStrategy {

    public static void demostracion() {
        System.out.println("=== EJERCICIO 84: PATRON STRATEGY ===\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- EJERCICIO 84: STRATEGIES FUNCIONALES ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana Garcia", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Luis Perez", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empresa.add(new Empleado("Carlos Ruiz", "Frontend", "JavaScript", 2, 32000, true, null));
        empresa.add(new Empleado("Marta Lopez", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empresa.add(new Empleado("Lucia Martin", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empresa.add(new Empleado("Marcos Vila", "Backend", "Rust", 8, 65000, true, "marcos@corp.com"));

        // TODO 1: Crea 3 estrategias de ordenacion como Function<List<Empleado>, List<Empleado>>:
        //   a) porSalarioDesc: ordena por salario descendente
        //   b) porExperienciaDesc: ordena por experiencia descendente
        //   c) porNombreAsc: ordena por nombre ascendente
        Function<List<Empleado>, List<Empleado>> porSalarioDesc = null; // <- Escribe aqui
        Function<List<Empleado>, List<Empleado>> porExperienciaDesc = null;
        Function<List<Empleado>, List<Empleado>> porNombreAsc = null;

        // TODO 2: Crea un metodo que aplique una estrategia y muestre resultados.
        // Aplica las 3 estrategias y muestra los nombres con forEach.
        System.out.println("[Strategy: Por salario DESC]");
        // <- Aplica porSalarioDesc y muestra nombres con forEach
        System.out.println("\n[Strategy: Por experiencia DESC]");
        // <- Aplica porExperienciaDesc
        System.out.println("\n[Strategy: Por nombre ASC]");
        // <- Aplica porNombreAsc

        // TODO 3: Crea una estrategia de filtrado como Function<List<Empleado>, List<Empleado>>:
        //   soloSeniors: filtra solo experiencia >= 5
        // Compone: primero filtrar soloSeniors, luego ordenar porSalarioDesc.
        // Usa andThen para componer las dos estrategias.
        Function<List<Empleado>, List<Empleado>> soloSeniors = null;
        Function<List<Empleado>, List<Empleado>> seniorsOrdenados = null; // <- Usa andThen

        System.out.println("\n[Compuesto: seniors por salario]");
        List<Empleado> resultado = null; // <- Aplica seniorsOrdenados
        if (resultado != null) resultado.stream().map(Empleado::getNombre).forEach(System.out::println);

        // --- VALIDACION ---
        boolean v1 = porSalarioDesc != null
                && porSalarioDesc.apply(empresa).get(0).getNombre().equals("Lucia Martin");
        boolean v2 = resultado != null && resultado.size() == 4; // Ana, Marta, Lucia, Marcos

        if (v1 && v2) {
            System.out.println("\n>> CORRECTO: Patron Strategy funcional dominado. [OK]");
        } else {
            System.err.println("\n>> [ERROR] Lucia primera por salario, 4 seniors.");
        }
    }
}
