package nivel4_metodos_default_java8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EJERCICIO 13 - EL REY DE LAS LISTAS ANIDADAS: computeIfAbsent
 * 
 * Objetivo: Entender por qué computeIfAbsent() es el método favorito 
 * de todos nosotros cuando tratamos con un Map de Colecciones.
 */
public class Ejercicio13_ComputeIfAbsent {

    public static void demostracion() {
        System.out.println("--- DEMO: computeIfAbsent ---");
        Map<String, List<String>> asignaturas = new HashMap<>();
        
        // Queremos añadir "Matemáticas" al expediente de "Pepe".
        // A LO JUNIOR (Java 7):
        /*
        List<String> materias = asignaturas.get("Pepe");
        if(materias == null) {
            materias = new ArrayList<>();
            asignaturas.put("Pepe", materias);
        }
        materias.add("Matemáticas");
        */

        // A LO SENIOR (Java 8+):
        asignaturas.computeIfAbsent("Pepe", k -> new ArrayList<>()).add("Matemáticas");
        System.out.println(asignaturas);
        System.out.println("");
    }

    public static void ejercicio() {
        System.out.println("\n--- RETO 13: BANDEJA DE CORREOS ---");
        // Representamos una bandeja de entrada donde la Llave es el remitente 
        // y el Valor es una Lista de mensajes que nos ha escrito.
        Map<String, List<String>> bandeja = new HashMap<>();
        
        List<String> mensajesAna = new ArrayList<>();
        mensajesAna.add("Hola, ¿cómo estás?");
        bandeja.put("Ana", mensajesAna);

        // TODO 1: "Ana" nos envía otro mensaje: "¿Lo leíste?".
        // Usa computeIfAbsent para asegurarte de que Ana tiene una lista, devuelve su lista encadenada y añade el String.
        // PISTA: mapa.computeIfAbsent(llave, k -> valorSiNoExiste).add(...)
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 2: Recibimos un mensaje de "Luis": "Reunión cancelada".
        // Luis aún NO existe en nuestro mapa `bandeja`.
        // Usa EXPRESAMENTE la misma instrucción de computeIfAbsent asombrosamente limpia para añadir su mensaje y crear su lista de golpe.
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // TODO 3: Imprime ambas bandejas usando forEach para ver la magia.
        // >>> ESCRIBE TU CÓDIGO AQUÍ <<<
        

        // --- VALIDACIÓN ---
        boolean ok1 = bandeja.containsKey("Ana") && bandeja.get("Ana").size() == 2;
        boolean ok2 = bandeja.containsKey("Luis") && bandeja.get("Luis").size() == 1;

        if (ok1 && ok2) {
            System.out.println(">> CORRECTO: El boilerplating the if-null-then-create-put ha desaparecido de tu vida.\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(">> [ERROR] Revisa las inserciones en la lista. Debería haber 2 de Ana y 1 de Luis.");
        }
    }
}
