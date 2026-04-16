package nivel13_maestria_total;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import modelos.Empleado;

/**
 * EJERCICIO 94 - SISTEMA DE EVENTOS CON CONSUMER
 * 
 * SIN GUIA. Implementa un sistema de eventos (observer) usando Consumer.
 * Los "listeners" son Consumer que se registran y ejecutan cuando ocurre un evento.
 */
public class Ejercicio94_SistemaEventosConsumer {

    public static void demostracion() {
        System.out.println("=== EJERCICIO 94: SISTEMA DE EVENTOS ===\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- EJERCICIO 94: EVENTOS CON CONSUMER ---");

        // TODO 1: Crea un Map<String, List<Consumer<Empleado>>> como "bus de eventos".
        // Las claves son nombres de evento: "ALTA", "BAJA", "PROMOCION".
        // Cada evento tiene una lista de listeners (Consumer<Empleado>).
        Map<String, List<Consumer<Empleado>>> eventBus = new HashMap<>();
        eventBus.put("ALTA", new ArrayList<>());
        eventBus.put("BAJA", new ArrayList<>());
        eventBus.put("PROMOCION", new ArrayList<>());

        // TODO 2: Registra listeners para "ALTA":
        //   a) Consumer que imprime "  [LOG] Alta de: nombre"
        //   b) Consumer que imprime "  [EMAIL] Bienvenido nombre a depto"
        //   c) Consumer que imprime "  [RRHH] Registrado nombre con salarioEUR"
        // <- Anade a eventBus.get("ALTA")

        // TODO 3: Registra listeners para "BAJA":
        //   a) Consumer que imprime "  [LOG] Baja de: nombre"
        //   b) Consumer que imprime "  [EMAIL] Adios nombre"
        // <- Anade a eventBus.get("BAJA")

        // TODO 4: Registra listeners para "PROMOCION":
        //   a) Consumer que imprime "  [LOG] Promocion de: nombre"
        //   b) Consumer que imprime "  [SALARIO] nombre pasa a salario*1.15 EUR"
        //   c) Consumer que imprime "  [CELEBRACION] Felicidades nombre!"
        // <- Anade a eventBus.get("PROMOCION")

        // TODO 5: Crea un metodo "emitirEvento" que dado un nombre de evento
        // y un Empleado, ejecute todos los Consumer registrados para ese evento.
        // Puedes usar: eventBus.getOrDefault(evento, List.of()).forEach(c -> c.accept(emp))

        Empleado ana = new Empleado("Ana Garcia", "Backend", "Java", 7, 55000, true, "ana@corp.com");
        Empleado nuevo = new Empleado("Sara Diaz", "Data", "Python", 0, 28000, true, "sara@corp.com");
        Empleado sale = new Empleado("Pedro Sanz", "QA", "Java", 3, 30000, false, null);

        System.out.println("[Evento ALTA]");
        // <- Emite evento "ALTA" con nuevo
        System.out.println("\n[Evento PROMOCION]");
        // <- Emite evento "PROMOCION" con ana
        System.out.println("\n[Evento BAJA]");
        // <- Emite evento "BAJA" con sale

        // --- VALIDACION ---
        boolean v1 = eventBus.get("ALTA").size() >= 3;
        boolean v2 = eventBus.get("BAJA").size() >= 2;
        boolean v3 = eventBus.get("PROMOCION").size() >= 3;

        if (v1 && v2 && v3) {
            System.out.println("\n>> CORRECTO: Sistema de eventos con Consumer dominado. [OK]");
        } else {
            System.err.println("\n>> [ERROR] ALTA>=3 listeners, BAJA>=2, PROMOCION>=3.");
        }
    }
}
