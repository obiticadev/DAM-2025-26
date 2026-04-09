package nivel13_maestria_total;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 92 - PIPELINE CONFIGURABLE POR USUARIO
 * 
 * SIN GUIA. Construye un sistema de pipeline donde las etapas se pueden
 * intercambiar dinamicamente con interfaces funcionales.
 */
public class Ejercicio92_PipelineConfigurable {

    public static void demostracion() {
        System.out.println("=== EJERCICIO 92: PIPELINE CONFIGURABLE ===\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- EJERCICIO 92: CONFIGURA TU PIPELINE ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana Garcia", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Luis Perez", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empresa.add(new Empleado("Carlos Ruiz", "Frontend", "JavaScript", 2, 32000, true, null));
        empresa.add(new Empleado("Marta Lopez", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empresa.add(new Empleado("Lucia Martin", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empresa.add(new Empleado("Pedro Sanz", "QA", "Java", 3, 30000, false, null));
        empresa.add(new Empleado("Elena Torres", "DevOps", "Go", 6, 48000, true, "elena@corp.com"));
        empresa.add(new Empleado("Marcos Vila", "Backend", "Rust", 8, 65000, true, "marcos@corp.com"));

        int[] configs = {0};

        // TODO 1: Crea un "banco" de componentes reutilizables:
        //   Filtros:
        //     activos, seniors (>=5), juniors (<5), conEmail, backend, frontend
        //   Transformadores (Function<Empleado, String>):
        //     fichaSimple: "nombre (depto)"
        //     fichaCompleta: "nombre | depto | lenguaje | expAnios | salarioEUR"
        //     fichaResumen: "NOMBRE - salarioEUR"
        //   Ordenadores (Comparator<Empleado>):
        //     porSalario, porExperiencia, porNombre
        // <- Define todos aqui

        // TODO 2: Config A: activos + seniors + porSalarioDesc + fichaCompleta
        System.out.println("[Config A: Seniors activos por salario]");
        // <- Escribe aqui
        // configs[0]++;

        // TODO 3: Config B: activos + juniors + porNombreAsc + fichaSimple
        System.out.println("\n[Config B: Juniors activos por nombre]");
        // <- Escribe aqui
        // configs[0]++;

        // TODO 4: Config C: activos + conEmail + backend + porExperienciaDesc + fichaResumen
        System.out.println("\n[Config C: Backend con email por experiencia]");
        // <- Escribe aqui
        // configs[0]++;

        // TODO 5: Crea una Function<List<Empleado>, List<String>> generica
        // que acepte Predicate, Comparator y Function como parametros
        // (capturados por closure). Usala para las 3 configs.
        // <- Escribe un metodo o lambda que lo encapsule

        // --- VALIDACION ---
        if (configs[0] == 3) {
            System.out.println("\n>> CORRECTO: Pipeline configurable dominado. [OK]");
        } else {
            System.err.println("\n>> [ERROR] Completa las 3 configuraciones.");
        }
    }
}
