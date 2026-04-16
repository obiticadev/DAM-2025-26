package nivel4_metodos_default_java8;

import java.util.HashMap;
import java.util.Map;

/**
 * EJERCICIO 10 - EL GUARDIÁN: putIfAbsent
 * 
 * Objetivo: Entender la diferencia entre put() que sobreescribe ciegamente
 * y putIfAbsent() que protege la información previa.
 */
public class Ejercicio10_PutIfAbsent {

    public static void demostracion() {
        System.out.println("--- DEMO: putIfAbsent ---");
        Map<String, String> configuracion = new HashMap<>();
        configuracion.put("Idioma", "Español");

        // Intentamos poner en Inglés con put (sobrescribe)
        // configuracion.put("Idioma", "Inglés"); 

        // Intentamos poner Inglés si no hay nada configurado:
        String valorAntiguo = configuracion.putIfAbsent("Idioma", "Inglés");
        
        System.out.println("Valor antiguo interceptado: " + valorAntiguo);
        System.out.println("Estado final: " + configuracion); // Sigue siendo Español
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 10: REGISTRO CIVIL ---");
        Map<String, String> dniYNombre = new HashMap<>();
        dniYNombre.put("11111111A", "Juan Palomo");
        dniYNombre.put("22222222B", "María García");

        // TODO 1: Llega un "Juan Palomo" impostor e intenta registrarse con el mismo DNI ("11111111A") 
        // pero con un nombre diferente ("Juan Impostor"). 
        // Utiliza OBLIGATORIAMENTE putIfAbsent para ignorar este intento si el DNI ya existe.
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 2: Alguien con un DNI nuevo ("33333333C") se registra bajo el nombre de "Luis López".
        // Usa también putIfAbsent. En este caso sí debería insertarse.
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 3: Existe un caso especial en putIfAbsent. Si la clave YA existe, PERO su valor es `null`,
        // putIfAbsent SÍ lo va a sobrescribir.
        dniYNombre.put("00000000Z", null); // Un ciudadano cuyo nombre quedó vacío en BBDD
        
        // Ahora, usa putIfAbsent para la clave "00000000Z" dándole el nombre "Paciente Cero".
        // Comprueba si realmente se actualizó leyendo el valor.
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 4: Recorre visualmente el registro con forEach (Solo como lectura libre).
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN ---
        boolean ok1 = "Juan Palomo".equals(dniYNombre.get("11111111A")); // No debió dejar sobreescribir
        boolean ok2 = "Luis López".equals(dniYNombre.get("33333333C")); // Debió insertarlo
        boolean ok3 = "Paciente Cero".equals(dniYNombre.get("00000000Z")); // Debió arreglar el null

        if (ok1 && ok2 && ok3) {
            System.out.println(">> CORRECTO: Sabes proteger tus datos críticos con putIfAbsent.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Alguna de las reglas de putIfAbsent falló. Revisa Juan, Luis o Paciente Cero.");
        }
    }
}
