package nivel3_intermedio_multicriterio;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import modelos.Aventurero;
import modelos.Mision;

public class Ejercicio08_InversionDeOrdenReversed {

    public static void demostracion() {
        System.out.println("--- DANDO LA VUELTA A LA MAGIA: .reversed() ---");
        System.out.println(
                "La API de Comparator es fluida. Si ya tienes un Comparator gigante y complejo que funciona de la A a la Z...");
        System.out.println(
                "No recodifiques la lógica matemática (-1, 0, 1). Simplemente añádele un `.reversed()` al final.");
        System.out.println("Comparator.comparing( a -> a.getParam() ).reversed();");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 08: INVERSIÓN TOTAL DE CAOS ---");
        List<Mision> tablon = new ArrayList<>();
        tablon.add(new Mision("Arañas", "D", 10, 5));
        tablon.add(new Mision("Bandidos", "C", 50, 15));
        tablon.add(new Mision("Grifo", "A", 300, 40));

        // TODO: Ordena el tablón por el LimiteNivelMinimo.
        // Pero el jefe del gremio ha pedido que las Misiones más difíciles (Nivel más
        // ALTO) salgan las PRIMERAS.
        // Usa '.reversed()' sobre el Comparator.
        tablon.sort(Comparator.comparing((Mision a) -> a.getLimiteNivelMinimo()).reversed());

        // --- VALIDACIÓN ---
        if (tablon.size() == 3 && tablon.get(0).getTitulo().equals("Grifo")
                && tablon.get(2).getTitulo().equals("Arañas")) {
            System.out.println(
                    ">> ¡CORRECTO! Invertiste la pirámide de misiones de nivel alto a bajo elegantemente.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(
                    ">> [ERROR] El tablón debería presentar primero la misión 'Grifo' (nv 40) bajando hasta 'Arañas' (nv 5).");
        }
    }
}
