package nivel13_maestria_total;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 96 - QUERY BUILDER FUNCIONAL
 * 
 * SIN GUIA. Construye un "query builder" estilo SQL usando interfaces funcionales
 * encadenables: WHERE (Predicate), ORDER BY (Comparator), SELECT (Function), LIMIT.
 */
public class Ejercicio96_QueryBuilderFuncional {

    public static void demostracion() {
        System.out.println("=== EJERCICIO 96: QUERY BUILDER FUNCIONAL ===\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- EJERCICIO 96: QUERY BUILDER ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana Garcia", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Luis Perez", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empresa.add(new Empleado("Carlos Ruiz", "Frontend", "JavaScript", 2, 32000, true, null));
        empresa.add(new Empleado("Marta Lopez", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empresa.add(new Empleado("Lucia Martin", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empresa.add(new Empleado("Pedro Sanz", "QA", "Java", 3, 30000, false, null));
        empresa.add(new Empleado("Elena Torres", "DevOps", "Go", 6, 48000, true, "elena@corp.com"));
        empresa.add(new Empleado("Marcos Vila", "Backend", "Rust", 8, 65000, true, "marcos@corp.com"));

        int[] queries = {0};

        // TODO 1: Crea variables que simulen clausulas SQL:
        //   Predicate<Empleado> where = activo AND salario > 40000
        //   Comparator<Empleado> orderBy = salario DESC
        //   Function<Empleado, String> select = "nombre (depto) - salarioEUR"
        //   int limit = 3
        // Ejecuta: stream().filter(where).sorted(orderBy).limit(limit).map(select).forEach(println)
        System.out.println("[Query 1: SELECT nombre FROM empresa WHERE activo AND sal>40k ORDER BY sal DESC LIMIT 3]");
        // <- Escribe aqui
        // queries[0]++;

        // TODO 2: Query 2: Todos los activos de Backend, ordenados por experiencia ASC.
        // SELECT "nombre - expAnios" FROM empresa WHERE activo AND depto=Backend ORDER BY exp ASC
        System.out.println("\n[Query 2: Backend activos por experiencia]");
        // <- Escribe aqui
        // queries[0]++;

        // TODO 3: Query 3: Contar empleados por departamento (simulando GROUP BY + COUNT).
        // SELECT depto, COUNT(*) FROM empresa WHERE activo GROUP BY depto
        System.out.println("\n[Query 3: COUNT por departamento]");
        // <- Escribe aqui con groupingBy + counting + forEach
        // queries[0]++;

        // TODO 4: Query 4: Obtener el salario MAX, MIN y AVG de activos (simulando funciones agregadas).
        // SELECT MAX(salario), MIN(salario), AVG(salario) FROM empresa WHERE activo
        System.out.println("\n[Query 4: MAX, MIN, AVG salario]");
        // <- Escribe aqui con mapToDouble + summaryStatistics o separate queries
        // queries[0]++;

        // TODO 5: Query 5: SELECT DISTINCT lenguaje FROM empresa WHERE activo ORDER BY lenguaje
        System.out.println("\n[Query 5: DISTINCT lenguajes]");
        // <- Escribe aqui
        // queries[0]++;

        // --- VALIDACION ---
        if (queries[0] == 5) {
            System.out.println("\n>> CORRECTO: Query Builder funcional dominado. [OK]");
        } else {
            System.err.println("\n>> [ERROR] Completa las 5 queries.");
        }
    }
}
