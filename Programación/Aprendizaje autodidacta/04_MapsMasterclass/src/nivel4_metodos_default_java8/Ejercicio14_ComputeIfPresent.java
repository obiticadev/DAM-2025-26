package nivel4_metodos_default_java8;

import java.util.HashMap;
import java.util.Map;

/**
 * EJERCICIO 14 - MODIFICANDO SI SOLO SI EXISTE: computeIfPresent
 * 
 * Objetivo: Al igual que ifAbsent solo actuaba cuando no había nada,
 * ifPresent es el cirujano que solo actúa si la estructura subyacente
 * de esa llave ya está formada.
 */
public class Ejercicio14_ComputeIfPresent {

    public static void demostracion() {
        System.out.println("--- DEMO: computeIfPresent ---");
        Map<String, Double> notas = new HashMap<>();
        notas.put("Examen1", 7.5);
        notas.put("Examen2", 9.0);

        // Alumno intenta subir extrañezamente la nota de un examen que NO hizo
        notas.computeIfPresent("Examen3", (k, v) -> v + 1); // No hace absolutamente nada.
        
        // Profesor sube gentilmente medio punto en el Examen 1
        notas.computeIfPresent("Examen1", (k, v) -> v + 0.5); 

        System.out.println("Notas tras computeIfPresent: " + notas + "\n");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 14: SUBIDA DE SUELDOS EN EMPRESA ---");
        Map<String, Integer> salarios = new HashMap<>();
        salarios.put("Ingeniero", 3000);
        salarios.put("Manager", 4500);

        // TODO 1: Llega un edicto para subir a todos los "Becarios" un % fijo.
        // Resulta que NO hay becarios registrados. Utiliza computeIfPresent para aumentarle
        // 500 al becario SOLO si existe. Observaremos que no arrojará la NullPointerException que
        // sí tiraría `compute` a secas.
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 2: Queremos reducir al "Ingeniero" por recortes 200 euros.
        // Usa computeIfPresent. Aquí SÍ se debe aplicar porque existe.
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 3: Existe una característica que comparte con `compute()`.
        // Si desde la función lambda (BiFunction) tú devuelves `null`, el mapa BORRARÁ ESA ENTRADA.
        // Haz que el "Manager" en computeIfPresent retorne null para simular su despido en vez de su recálculo de sueldo.
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN ---
        boolean ok1 = !salarios.containsKey("Becario");
        boolean ok2 = salarios.get("Ingeniero") != null && salarios.get("Ingeniero") == 2800;
        boolean ok3 = !salarios.containsKey("Manager");

        if (ok1 && ok2 && ok3) {
            System.out.println(">> CORRECTO: Has aprendido a actuar con precisión quirúrgica sin reventar el sistema por nulos.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Revisa el despido del manager (retorno nulo) o el cálculo de recortes.");
        }
    }
}
