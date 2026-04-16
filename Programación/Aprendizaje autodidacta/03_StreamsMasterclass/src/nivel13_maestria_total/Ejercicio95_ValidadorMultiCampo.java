package nivel13_maestria_total;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 95 - VALIDADOR MULTI-CAMPO CON REPORTES
 * 
 * SIN GUIA. Crea un sistema de validacion que valide multiples campos
 * y genere un reporte detallado de errores por cada objeto.
 */
public class Ejercicio95_ValidadorMultiCampo {

    public static class Regla<T> {
        private final Predicate<T> condicion;
        private final String mensaje;

        public Regla(Predicate<T> condicion, String mensaje) {
            this.condicion = condicion;
            this.mensaje = mensaje;
        }

        public boolean cumple(T obj) { return condicion.test(obj); }
        public String getMensaje() { return mensaje; }
    }

    public static void demostracion() {
        System.out.println("=== EJERCICIO 95: VALIDADOR MULTI-CAMPO ===\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- EJERCICIO 95: VALIDACION CON REPORTES ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana Garcia", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Luis Perez", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empresa.add(new Empleado("Carlos Ruiz", "Frontend", "JavaScript", 2, 32000, true, null));
        empresa.add(new Empleado("", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empresa.add(new Empleado("Lucia Martin", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empresa.add(new Empleado("Pedro Sanz", "QA", "Java", 3, -5000, false, null));
        empresa.add(new Empleado("Elena Torres", "", "Go", 6, 48000, true, "elena@corp.com"));

        // TODO 1: Crea una List<Regla<Empleado>> con estas reglas de validacion:
        //   a) nombre no vacio: e.getNombre() != null && !e.getNombre().isEmpty()
        //      mensaje: "Nombre es obligatorio"
        //   b) departamento no vacio
        //      mensaje: "Departamento es obligatorio"
        //   c) salario positivo: salario > 0
        //      mensaje: "Salario debe ser positivo"
        //   d) email valido si esta presente: email == null || email.contains("@")
        //      mensaje: "Email invalido"
        //   e) experiencia no negativa: experiencia >= 0
        //      mensaje: "Experiencia no puede ser negativa"
        List<Regla<Empleado>> reglas = new ArrayList<>();
        // <- Anade reglas

        // TODO 2: Para cada empleado, ejecuta TODAS las reglas y recoge los mensajes
        // de las que NO se cumplen. Imprime un reporte:
        //   "nombre (o 'SIN NOMBRE'):
        //     - OK (si pasa todo)
        //     - ERROR: mensaje1
        //     - ERROR: mensaje2"
        System.out.println("[Reporte de validacion]");
        int[] erroresTotal = {0};
        // <- Escribe aqui. Por cada empleado, filtra reglas que no cumple.

        // TODO 3: Crea una Function<List<Empleado>, List<Empleado>> que devuelva
        // SOLO los empleados que pasan TODAS las reglas.
        Function<List<Empleado>, List<Empleado>> filtrarValidos = null;
        List<Empleado> validos = filtrarValidos != null ? filtrarValidos.apply(empresa) : null;

        System.out.println("\n[Empleados validos]");
        if (validos != null) validos.stream().map(Empleado::getNombre).forEach(e -> System.out.println("  OK: " + e));

        // --- VALIDACION ---
        boolean v1 = reglas.size() >= 5;
        boolean v2 = validos != null && validos.size() == 3; // Ana, Luis, Lucia pasan todo

        if (v1 && v2) {
            System.out.println("\n>> CORRECTO: Validador multi-campo dominado. [OK]");
        } else {
            System.err.println("\n>> [ERROR] 5+ reglas, 3 validos (Ana, Luis, Lucia).");
        }
    }
}
