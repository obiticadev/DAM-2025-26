package nivel2_lambdas_y_flujos_0;

import java.util.ArrayList;
import java.util.List;
import modelos.Mision;

public class Ejercicio04_LambdaSortSintaxis {

    public static void demostracion() {
        System.out.println("--- REFUNDACIÓN: DE CLASE ANÓNIMA A FUNCIÓN FLECHA ---");
        System.out.println("En el ejercicio anterior usaste 6 líneas de aburrida burocracia para crear un Comparator.");
        System.out.println("Aquí tienes el atajo equivalente exacto de Java 8+:");
        System.out.println("List<T>.sort( (m1, m2) -> Double.compare(m1.getOro(), m2.getOro()) );\n");
        System.out.println("El compilador adivina los tipos y asume el Return automáticamente.");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 04: LA PUREZA DE LAS LAMBDAS ---");
        List<Mision> tablon = new ArrayList<>();
        tablon.add(new Mision("Limpiar Ratas", "D", 50, 1));
        tablon.add(new Mision("Matar Dragón", "S", 5000, 99));
        tablon.add(new Mision("Escolta Real", "A", 1200, 40));

        // TODO: Ordena la variable 'tablon'. Pero esta vez debe ser de MAYOR RANGO a
        // MENOR RANGO alfabético
        // y usando pura sintaxis LAMBDA en una sola línea (b.compareTo(a)).
        // Recuerda que 'Rango' es una String (A, B, C, D, S...).

        // TIPO: tablon.sort( (a,b) -> ... ); <-- COMPLÉTALO TÚ
        tablon.sort((m1, m2) -> m2.getTitulo().compareTo(m1.getTitulo()));

        // --- VALIDACIÓN ---
        if (tablon.size() == 3 && tablon.get(0).getRango().equals("S") && tablon.get(2).getRango().equals("A")) {
            System.out.println(
                    ">> ¡CORRECTO! Una línea láser y brillante. Ya estás hablando en dialecto Lambda.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Revisa tu lambda. Debería organizar de la Z a la A por la columna Rango.");
        }
    }
}
