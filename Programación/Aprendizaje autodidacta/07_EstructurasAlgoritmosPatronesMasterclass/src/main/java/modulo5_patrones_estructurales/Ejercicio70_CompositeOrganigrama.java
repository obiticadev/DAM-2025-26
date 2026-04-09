package modulo5_patrones_estructurales;

/**
 * ============================================================================
 * EJERCICIO 70: Composite — Organigrama Empresarial
 * ============================================================================
 * 📚 Teoría: teoria/05_Patrones_Estructurales.md - Sección 5.6
 *
 * CONTEXTO:
 * Una empresa tiene una jerarquía: el CEO tiene bajo su cargo directores,
 * cada director tiene gerentes, cada gerente tiene empleados.
 * Composite permite calcular el salario total de cualquier subárbol,
 * contar empleados, y buscar personas, todo recursivamente.
 *
 * Implementa:
 * - Interfaz Empleado: getNombre(), getCargo(), getSalario(),
 *   mostrar(int indent), contarPersonas().
 * - EmpleadoIndividual (Leaf): trabajador sin subordinados.
 * - Gerente (Composite): tiene subordinados (empleados o más gerentes).
 *
 * RESTRICCIONES:
 * - Gerente.getSalario(): su salario + sum(subordinados.getSalario()).
 * - contarPersonas(): 1 + sum(subordinados.contarPersonas()).
 * - Array de subordinados manual (max 20).
 * - Sin java.util.
 * ============================================================================
 */
public class Ejercicio70_CompositeOrganigrama {

    // TODO 1: Definir interfaz Empleado con los métodos descritos.

    // TODO 2: Implementar EmpleadoIndividual (Leaf):
    //         nombre, cargo, salario.
    //         getSalario() retorna su salario propio.
    //         contarPersonas() retorna 1.
    //         mostrar() imprime con indentación: "  👤 Juan - Desarrollador ($3000)"

    // TODO 3: Implementar Gerente (Composite):
    //         nombre, cargo, salarioPropio, Empleado[] subordinados, int numSubs.
    //         agregar(Empleado e): añadir subordinado.
    //         getSalario(): salarioPropio + suma recursiva de subordinados.
    //         contarPersonas(): 1 + suma recursiva.
    //         mostrar(): imprime "👔 María - Directora ($5000)" y luego cada subordinado.

    // TODO 4: Implementar buscar(String nombre) en Gerente:
    //         Buscar recursivamente por nombre.

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 70: Composite Organigrama ===\n");

        // TODO 5: Construir el organigrama:
        //         Gerente ceo = new Gerente("Ana", "CEO", 10000);
        //
        //         Gerente dirTech = new Gerente("Carlos", "Director Tech", 7000);
        //         dirTech.agregar(new Gerente("María", "Lead Dev", 5000) {{
        //             agregar(new EmpleadoIndividual("Juan", "Dev Senior", 3500));
        //             agregar(new EmpleadoIndividual("Laura", "Dev Junior", 2500));
        //         }});
        //         dirTech.agregar(new EmpleadoIndividual("Pedro", "DevOps", 4000));
        //
        //         Gerente dirRRHH = new Gerente("Elena", "Directora RRHH", 6500);
        //         dirRRHH.agregar(new EmpleadoIndividual("Sara", "Recruiter", 2800));
        //
        //         ceo.agregar(dirTech);
        //         ceo.agregar(dirRRHH);
        //
        //         ceo.mostrar(0);
        //         System.out.println("\nTotal personas: " + ceo.contarPersonas());
        //         System.out.println("Masa salarial: $" + ceo.getSalario());

        System.out.println("(Implementa los TODOs para ver resultados)");
        System.out.println("\n=== FIN EJERCICIO 70 ===");
    }
}
