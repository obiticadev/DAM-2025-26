package nivel12_interfaces_funcionales;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import modelos.Empleado;

/**
 * EJERCICIO 87 - TEMPLATE METHOD CON CONSUMER
 * 
 * SIN GUIA. Construye plantillas de informe usando Consumer.andThen.
 * Lee la teoria: teoria/11_Interfaces_Funcionales_Personalizadas.md (seccion 11.6)
 */
public class Ejercicio87_TemplateMethodConsumer {

    public static void demostracion() {
        System.out.println("=== EJERCICIO 87: TEMPLATE METHOD ===\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- EJERCICIO 87: PLANTILLAS DE INFORME ---");
        List<Empleado> empresa = new ArrayList<>();
        empresa.add(new Empleado("Ana Garcia", "Backend", "Java", 7, 55000, true, "ana@corp.com"));
        empresa.add(new Empleado("Luis Perez", "Backend", "Java", 3, 35000, true, "luis@corp.com"));
        empresa.add(new Empleado("Carlos Ruiz", "Frontend", "JavaScript", 2, 32000, true, null));
        empresa.add(new Empleado("Marta Lopez", "Frontend", "JavaScript", 5, 48000, true, "marta@corp.com"));
        empresa.add(new Empleado("Lucia Martin", "Data", "Python", 10, 70000, true, "lucia@corp.com"));
        empresa.add(new Empleado("Marcos Vila", "Backend", "Rust", 8, 65000, true, "marcos@corp.com"));

        int[] informesGenerados = {0};

        // TODO 1: Crea 3 Consumer<List<Empleado>> que formen un template de informe:
        //   cabecera: imprime "====== INFORME EMPRESA ======"
        //   cuerpo: para cada empleado imprime "  - nombre (depto) salarioEUR"
        //   pie: imprime "Total: N empleados | Masa: XEUR" y una linea de cierre
        Consumer<List<Empleado>> cabecera = null; // <- Escribe aqui
        Consumer<List<Empleado>> cuerpo = null;
        Consumer<List<Empleado>> pie = null;

        // TODO 2: Compone cabecera -> cuerpo -> pie con andThen.
        // Aplica el informe compuesto a la lista de empleados.
        Consumer<List<Empleado>> informe = null; // <- Usa andThen
        System.out.println("[Informe completo]");
        if (informe != null) { informe.accept(empresa); informesGenerados[0]++; }

        // TODO 3: Crea un segundo template para "Informe de Seniors" que:
        //   - Cabecera: "====== INFORME SENIORS ======"
        //   - Cuerpo: solo empleados con experiencia >= 5, formateados
        //   - Pie: cuantos seniors hay y su salario medio
        Consumer<List<Empleado>> informeSeniors = null; // <- Escribe aqui
        System.out.println("\n[Informe seniors]");
        if (informeSeniors != null) { informeSeniors.accept(empresa); informesGenerados[0]++; }

        // TODO 4: Crea un Consumer<List<Empleado>> generico "generarRanking" que:
        //   - Ordene por salario DESC
        //   - Imprima posicion, nombre y salario: "1. nombre - salarioEUR"
        //   - Al final: "Mejor pagado: nombre"
        Consumer<List<Empleado>> ranking = null; // <- Escribe aqui
        System.out.println("\n[Ranking salarial]");
        if (ranking != null) { ranking.accept(empresa); informesGenerados[0]++; }

        // --- VALIDACION ---
        if (informesGenerados[0] == 3) {
            System.out.println("\n>> CORRECTO: Template Method con Consumer dominado. [OK]");
        } else {
            System.err.println("\n>> [ERROR] Genera los 3 informes (incrementa informesGenerados).");
        }
    }
}
