package nivel12_interfaces_funcionales;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 83 - VALIDADOR GENERICO COMPONIBLE
 * 
 * SIN GUIA. Crea un sistema de validacion componible usando interfaces funcionales.
 * Lee la teoria: teoria/11_Interfaces_Funcionales_Personalizadas.md (seccion 11.3)
 */
public class Ejercicio83_ValidadorGenerico {

    @FunctionalInterface
    public interface Validador<T> {
        boolean validar(T objeto);

        default Validador<T> y(Validador<T> otro) {
            return t -> this.validar(t) && otro.validar(t);
        }

        default Validador<T> o(Validador<T> otro) {
            return t -> this.validar(t) || otro.validar(t);
        }

        default Validador<T> negar() {
            return t -> !this.validar(t);
        }

        default Predicate<T> toPredicate() {
            return this::validar;
        }
    }

    public static void demostracion() {
        System.out.println("=== EJERCICIO 83: VALIDADOR GENERICO ===\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- EJERCICIO 83: SISTEMA DE VALIDACION ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana Garcia", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Luis Perez", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empresa.add(new Empleado("Carlos Ruiz", "Frontend", "JavaScript", 2, 32000, true, null));
        empresa.add(new Empleado("Marta Lopez", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empresa.add(new Empleado("Lucia Martin", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empresa.add(new Empleado("Pedro Sanz", "QA", "Java", 3, 30000, false, null));

        // TODO 1: Crea estos Validadores base:
        //   esActivo: valida que isActivo() sea true
        //   esSenior: valida experiencia >= 5
        //   tieneEmail: valida que getEmail() != null
        //   ganaAlto: valida salario >= 50000
        Validador<Empleado> esActivo = null; // <- Escribe aqui
        Validador<Empleado> esSenior = null;
        Validador<Empleado> tieneEmail = null;
        Validador<Empleado> ganaAlto = null;

        // TODO 2: Compone validadores:
        //   elite = activo Y senior Y tieneEmail
        //   candidato = activo Y (senior O ganaAlto)
        //   excluido = negar(activo) O negar(tieneEmail)
        Validador<Empleado> elite = null; // <- Usa .y()
        Validador<Empleado> candidato = null;
        Validador<Empleado> excluido = null;

        // TODO 3: Aplica cada validador compuesto con stream + filter + toPredicate.
        // Imprime los nombres de cada grupo con forEach(System.out::println).
        System.out.println("[Elite]");
        // <- Filtra con elite.toPredicate() y forEach
        List<String> nombresElite = null; // <- Recoge nombres
        System.out.println("\n[Candidatos]");
        // <- Filtra con candidato
        System.out.println("\n[Excluidos]");
        // <- Filtra con excluido

        // --- VALIDACION ---
        boolean v1 = nombresElite != null && nombresElite.size() == 3; // Ana, Marta, Lucia
        boolean v2 = elite != null && !elite.validar(empresa.get(5)); // Pedro no es elite

        if (v1 && v2) {
            System.out.println("\n>> CORRECTO: Validador generico componible dominado. [OK]");
        } else {
            System.err.println("\n>> [ERROR] Elite=3 (Ana,Marta,Lucia), Pedro no es elite.");
        }
    }
}
