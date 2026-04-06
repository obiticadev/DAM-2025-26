package nivel12_interfaces_funcionales;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 82 - INTERFAZ FUNCIONAL PERSONALIZADA
 * 
 * Aprende a crear tus propias interfaces funcionales con @FunctionalInterface.
 * Lee la teoria: teoria/11_Interfaces_Funcionales_Personalizadas.md (secciones 11.1-11.3)
 */
public class Ejercicio82_InterfazFuncionalCustom {

    // Interfaz funcional custom ya definida para la demo
    @FunctionalInterface
    public interface Transformador<T, R> {
        R transformar(T entrada);
    }

    // Interfaz funcional con metodo default
    @FunctionalInterface
    public interface Formateador<T> {
        String formatear(T objeto);

        default Formateador<T> conPrefijo(String prefijo) {
            return t -> prefijo + formatear(t);
        }

        default Formateador<T> conSufijo(String sufijo) {
            return t -> formatear(t) + sufijo;
        }
    }

    public static void demostracion() {
        System.out.println("=== EJERCICIO 82: INTERFAZ FUNCIONAL CUSTOM ===\n");

        // DEMO: Usar Transformador con lambda
        Transformador<String, Integer> longitud = String::length;
        System.out.println("Longitud de 'Hola': " + longitud.transformar("Hola"));

        // DEMO: Formateador con metodos default
        Formateador<Empleado> fmt = e -> e.getNombre() + " (" + e.getDepartamento() + ")";
        Formateador<Empleado> fmtDecorado = fmt.conPrefijo(">> ").conSufijo(" <<");

        Empleado demo = new Empleado("Ana Garcia", "Backend", "Java", 7, 55000, true, "ana@corp.com");
        System.out.println(fmtDecorado.formatear(demo));

        System.out.println("\nAhora completa los TODOs.\n");
    }

    // TODO A: Define aqui tu propia interfaz funcional "Evaluador<T>"
    // que tenga un metodo: double evaluar(T objeto)
    // y metodos default: Evaluador<T> sumar(Evaluador<T> otro) y Evaluador<T> multiplicarPor(double factor)
    // <- Define la interfaz aqui

    public static void ejercicio() {
        System.out.println("\n--- EJERCICIO 82: CREA TUS INTERFACES ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana Garcia", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Luis Perez", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empresa.add(new Empleado("Lucia Martin", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empresa.add(new Empleado("Marcos Vila", "Backend", "Rust", 8, 65000, true, "marcos@corp.com"));

        // TODO 1: Usa Transformador<Empleado, String> para crear un transformador
        // que convierta un empleado a "NOMBRE - depto" (nombre en mayusculas).
        // Aplica con map + forEach(System.out::println).
        Transformador<Empleado, String> ficha = null; // <- Escribe aqui
        System.out.println("[Fichas con Transformador custom]");
        // <- Aplica aqui

        // TODO 2: Crea un Formateador<Empleado> que formatee como:
        // "nombre | depto | salarioEUR"
        // Decoralo con conPrefijo("  -> ") y aplica a todos con forEach.
        Formateador<Empleado> fmt = null; // <- Escribe aqui
        System.out.println("\n[Formateador decorado]");
        // <- Aplica aqui

        // TODO 3: Si definiste Evaluador<T>, crea:
        //   evaluarSalario: devuelve el salario
        //   evaluarExperiencia: devuelve la experiencia * 1000
        //   evaluarTotal: sumar ambos
        // Aplica evaluarTotal a cada empleado y muestra con forEach.
        System.out.println("\n[Evaluacion compuesta]");
        // <- Escribe aqui

        // --- VALIDACION ---
        boolean v1 = ficha != null && ficha.transformar(empresa.get(0)).contains("ANA");
        boolean v2 = fmt != null && fmt.formatear(empresa.get(0)).contains("55000");

        if (v1 && v2) {
            System.out.println("\n>> CORRECTO: Interfaces funcionales custom dominadas. [OK]");
        } else {
            System.err.println("\n>> [ERROR] Transformador con NOMBRE, Formateador con salario.");
        }
    }
}
