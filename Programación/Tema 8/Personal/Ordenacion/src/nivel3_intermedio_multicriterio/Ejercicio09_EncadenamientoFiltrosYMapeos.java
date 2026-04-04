package nivel3_intermedio_multicriterio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import modelos.Aventurero;

public class Ejercicio09_EncadenamientoFiltrosYMapeos {

    public static void demostracion() {
        System.out.println("--- EL MUTADOR: .map() DENTRO DE STREAMS ---");
        System.out.println("En un pipeline de Streams, no solo filtras el agua. Puedes MUTARLA por el camino.");
        System.out.println(
                "Con el comando intermedio `.map( Función )` puedes extraer, por ejemplo, los DNIs de una lista de Personas.");
        System.out.println(
                "Tu tubería entra como Stream<Persona> y, cruzando el map(), se convierte en un Stream<String>.");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 09: LISTADO DE NOMBRES ---");
        List<Aventurero> nomina = new ArrayList<>();
        nomina.add(new Aventurero("Arthur", "Guerrero", 80, 500, true));
        nomina.add(new Aventurero("Tomy", "Ladron", 5, 0, false));
        nomina.add(new Aventurero("Lancelot", "Guerrero", 90, 500, true));
        nomina.add(new Aventurero("Merlin", "Mago", 99, 1000, true));

        // TODO: El contable del gremio NO necesita los objetos 'Aventurero' que pesan
        // en memoria.
        // Solo quiere un simple ArrayList<String> con los NOMBRES.
        // Condición 1: Filtra solo a los que estén VIVOS.
        // Condición 2: Usa .map( a -> a.getNombre() ) para mutar el stream a tipo
        // String.
        // Condición 3: Ordénalos alfabéticamente llamando a .sorted() (Como ya son
        // Strings, usan orden natural solos).
        // Recolectores: toList().

        List<String> nombresVivosOrdenados = nomina.stream()
                .filter((Aventurero a) -> a.isEstadoVivo())
                .map((Aventurero a) -> a.getNombre())
                .sorted()
                .collect(Collectors.toList());

        // --- VALIDACIÓN ---
        if (nombresVivosOrdenados != null && nombresVivosOrdenados.size() == 3 &&
                nombresVivosOrdenados.get(0).equals("Arthur") &&
                nombresVivosOrdenados.get(2).equals("Merlin") &&
                !nombresVivosOrdenados.contains("Tomy")) {
            System.out.println(
                    ">> ¡CORRECTO! Has extraído, mutado, ordenado y finalizado una base de datos en 1 sola línea maestra.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(
                    ">> [ERROR] Revisa el Pipeline. Debe haber 3 Nombres (Strings) ordenados de la A a la Z, excluyendo al ladrón muerto Tomy.");
        }
    }
}
