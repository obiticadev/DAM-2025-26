package nivel12_interfaces_funcionales;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 88 - PROCESADOR GENERICO MULTI-PASO
 * 
 * SIN GUIA. Construye un procesador que combine Predicate, Function y Consumer
 * para crear pipelines de procesamiento configurables.
 */
public class Ejercicio88_ProcesadorMultiPaso {

    public static void demostracion() {
        System.out.println("=== EJERCICIO 88: PROCESADOR MULTI-PASO ===\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- EJERCICIO 88: PIPELINE CONFIGURABLE ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana Garcia", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Luis Perez", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empresa.add(new Empleado("Carlos Ruiz", "Frontend", "JavaScript", 2, 32000, true, null));
        empresa.add(new Empleado("Marta Lopez", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empresa.add(new Empleado("Lucia Martin", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empresa.add(new Empleado("Pedro Sanz", "QA", "Java", 3, 30000, false, null));
        empresa.add(new Empleado("Marcos Vila", "Backend", "Rust", 8, 65000, true, "marcos@corp.com"));

        int[] pasos = {0};

        // TODO 1: Define las piezas del procesador:
        //   filtro: Predicate<Empleado> -> activos con experiencia >= 3
        //   transformador: Function<Empleado, String> -> "NOMBRE | depto | salarioEUR"
        //   ordenador: Comparator<Empleado> -> por salario DESC
        //   salida: Consumer<String> -> imprime con "  >> " delante
        Predicate<Empleado> filtro = null;
        Function<Empleado, String> transformador = null;
        Comparator<Empleado> ordenador = null;
        Consumer<String> salida = null;

        // TODO 2: Ensambla el pipeline: filtra, ordena, transforma, consume.
        // Usa stream().filter(filtro).sorted(ordenador).map(transformador).forEach(salida)
        System.out.println("[Pipeline 1: activos exp>=3 por salario]");
        // <- Escribe aqui
        // pasos[0]++;

        // TODO 3: Cambia SOLO el filtro a "salario > 40000" y reutiliza el resto.
        // Demuestra que las piezas son intercambiables.
        Predicate<Empleado> filtro2 = null;
        System.out.println("\n[Pipeline 2: salario > 40000]");
        // <- Escribe aqui con filtro2 y el resto igual
        // pasos[0]++;

        // TODO 4: Crea una Function<List<Empleado>, List<String>> que encapsule
        // todo el pipeline (filtrar + ordenar + transformar) y devuelva la lista de strings.
        // Luego aplica la salida (Consumer) sobre el resultado.
        Function<List<Empleado>, List<String>> pipelineCompleto = null;
        System.out.println("\n[Pipeline encapsulado]");
        // <- Aplica y forEach con salida
        // pasos[0]++;

        // --- VALIDACION ---
        if (pasos[0] == 3) {
            System.out.println("\n>> CORRECTO: Procesador multi-paso dominado. [OK]");
        } else {
            System.err.println("\n>> [ERROR] Completa los 3 pipelines.");
        }
    }
}
