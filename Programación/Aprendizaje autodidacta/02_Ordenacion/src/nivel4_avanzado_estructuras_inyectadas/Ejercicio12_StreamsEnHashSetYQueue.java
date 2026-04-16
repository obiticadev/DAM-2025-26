package nivel4_avanzado_estructuras_inyectadas;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import modelos.Mision;

public class Ejercicio12_StreamsEnHashSetYQueue {

    public static void demostracion() {
        System.out.println("--- ESTABILIZANDO EL CAOS ---");
        System.out.println("El HashSet es rápido, pero sus datos se guardan en orden Aleatorio Puro (Hashes).");
        System.out.println("Sin embargo, toda Colección tiene `.stream()`.");
        System.out.println(
                "Puedes drenar un HashSet loco, ordenarlo en la tubería y volcarlo en un ArrayList bonito y organizado.");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 12: DE CAOS A ORDEN ---");
        Set<Mision> cajonDesastre = new HashSet<>(Arrays.asList(
                new Mision("Recoger Flores", "F", 5, 1),
                new Mision("Matar Demonio", "SS", 90000, 100),
                new Mision("Salvar Gato", "D", 20, 2)));

        // TODO: Saca un Stream de 'cajonDesastre'.
        // Ya que la clase Mision NO TIENE natural order, usa .sorted( (m1, m2) -> ... )
        // en la tubería
        // Haz que se ordenen por RECOMPENSA_ORO de mayor a menor.
        // Guárdalos tranquilamente usando collect().toList().

        List<Mision> misionesDrenadasYOrdenadas = null; // <- Modifica aquí
        misionesDrenadasYOrdenadas = cajonDesastre.stream()
                .sorted((m1, m2) -> Double.compare(m2.getRecompensaOro(), m1.getRecompensaOro()))
                .peek(m -> System.out.println("Elemento: " + m.getTitulo()))
                .collect(Collectors.toList());

        // --- VALIDACIÓN ---
        if (misionesDrenadasYOrdenadas != null && misionesDrenadasYOrdenadas.size() == 3 &&
                misionesDrenadasYOrdenadas.get(0).getTitulo().equals("Matar Demonio")) {
            System.out.println(
                    ">> ¡CORRECTO! Has demostrado que no importa dónde empieces, Stream siempre domina el caos.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(
                    ">> [ERROR] El flujo resultante debe ser una lista de 3 misiones, la de oro 90000 primera.");
        }
    }
}
