package com.masterclass.api.b19_test;

import java.util.List;

/**
 * Ejercicio 171 · {@code @SpringBootTest} e2e (flujo completo como pipeline puro).
 *
 * <p>Teoría: {@code teoria/19_Testing_APIs.md} (sección 19.8).
 *
 * <p>Un test e2e ejercita controller→service→repo. Modelamos ese flujo
 * como un pipeline puro: crear recurso y luego listarlo, verificando que
 * el efecto del primer paso es observable en el segundo, sin Spring.
 */
public final class Ej171SpringBootIntegration {

    private Ej171SpringBootIntegration() {
    }

    /**
     * Flujo e2e: aplica una secuencia de comandos sobre un estado inicial.
     *
     * <p>Comandos: {@code "ADD:<nombre>"} añade; {@code "DEL:<nombre>"}
     * elimina. Devuelve el estado final ordenado (como haría un GET tras
     * varios POST/DELETE).
     *
     * @param inicial lista inicial de recursos (no null, no mutada)
     * @param comandos secuencia de comandos (no null)
     * @return estado final ordenado ascendentemente
     * @throws IllegalArgumentException si algún argumento es null o comando malformado
     */
    public static List<String> ejecutarFlujo(List<String> inicial, List<String> comandos) {
        // TODO 1: si inicial o comandos son null -> IllegalArgumentException.
        // TODO 2: copia 'inicial' a una estructura mutable (no muta la entrada).
        // TODO 3: itera cada comando en orden (simula peticiones HTTP secuenciales).
        // TODO 4: si el comando no contiene ":" -> IllegalArgumentException (malformado).
        // TODO 5: separa en accion y argumento por el primer ":".
        // TODO 6: "ADD" -> añade el argumento al estado.
        // TODO 7: "DEL" -> elimina el argumento si existe (idempotente si no está).
        // TODO 8: acción desconocida -> IllegalArgumentException.
        // TODO 9: al final ordena el estado ascendentemente (respuesta determinista).
        // TODO 10: devuelve el estado final como List.
        return List.of();
    }

    public static void main(String[] args) {
        System.out.println(ejecutarFlujo(List.of("a"), List.of("ADD:b", "DEL:a")));
    }

        /**
     * RETO EXTRA 01: Crea comando de agregacion.
     */
    public static String crearComandoAdd(String arg) {
        // GUÍA: teoría 19.8 — una línea: return "ADD:" + arg;
        // El test ("x") espera "ADD:x". Es el inverso de extraerAccion/Argumento
        // (retos 3 y 4): construye lo que aquellos descomponen.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearComandoAdd");
    }

    /**
     * RETO EXTRA 02: Crea comando de eliminacion.
     */
    public static String crearComandoDel(String arg) {
        // GUÍA: teoría 19.8 — una línea: return "DEL:" + arg;
        // El test ("x") espera "DEL:x". Simétrico a crearComandoAdd.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearComandoDel");
    }

    /**
     * RETO EXTRA 03: Extrae la accion del comando.
     */
    public static String extraerAccion(String cmd) {
        // GUÍA: teoría 19.8 — parte por el primer ":" y toma la izquierda.
        // return cmd.split(":", 2)[0];
        // El test ("ADD:x") espera "ADD". PISTA: el límite 2 en split(":", 2) es
        // clave: parte solo en el PRIMER ":", para que un argumento con más ":"
        // (p.ej. "ADD:a:b") no se rompa. Es el mismo split que usa ejecutarFlujo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extraerAccion");
    }

    /**
     * RETO EXTRA 04: Extrae el argumento del comando.
     */
    public static String extraerArgumento(String cmd) {
        // GUÍA: teoría 19.8 — toma la parte derecha del split.
        // return cmd.split(":", 2)[1];
        // El test ("ADD:x") espera "x". Mismo split que extraerAccion, pero
        // índice [1]. Con límite 2, [1] contiene TODO lo que va tras el primer ":".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extraerArgumento");
    }

    /**
     * RETO EXTRA 05: Comprueba si es un comando estructurado.
     */
    public static boolean esComandoValido(String cmd) {
        // GUÍA: teoría 19.8 — válido = tiene el separador ":".
        // return cmd != null && cmd.contains(":");
        // El test ("ADD:x") espera true. Es la comprobación que ejecutarFlujo
        // hace antes de lanzar IllegalArgumentException por comando malformado
        // (un "BADCMD" sin ":" sería inválido).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esComandoValido");
    }

    /**
     * RETO EXTRA 06: Obtiene tamaño de la lista.
     */
    public static int tamanioInicial(java.util.List<String> list) {
        // GUÍA: teoría 19.8 — una línea: return list.size();
        // El test (List.of("a","b")) espera 2.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tamanioInicial");
    }

    /**
     * RETO EXTRA 07: Verifica si la lista contiene el valor.
     */
    public static boolean listaContiene(java.util.List<String> list, String val) {
        // GUÍA: teoría 19.8 — una línea: return list.contains(val);
        // El test (List.of("a"), "a") espera true. Es lo que verificaría un e2e
        // tras un POST: "¿aparece el recurso creado al listar?".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para listaContiene");
    }

    /**
     * RETO EXTRA 08: Combina dos listas de comandos.
     */
    public static java.util.List<String> combinarComandos(java.util.List<String> a, java.util.List<String> b) {
        // GUÍA: teoría 19.8 — concatena las dos listas en una nueva.
        // List<String> r = new ArrayList<>(a);
        // r.addAll(b);
        // return r;
        // El test (["1"], ["2"]) espera size()==2. OJO: crea una lista NUEVA; no
        // hagas a.addAll(b) porque mutarías la lista de entrada (y List.of es
        // inmutable: lanzaría UnsupportedOperationException). Alternativa stream:
        // Stream.concat(a.stream(), b.stream()).toList().
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para combinarComandos");
    }

    /**
     * RETO EXTRA 09: Filtra comandos ADD.
     */
    public static java.util.List<String> filtrarComandosAdd(java.util.List<String> cmds) {
        // GUÍA: teoría 19.8 — filtra por prefijo "ADD:".
        // return cmds.stream().filter(c -> c.startsWith("ADD:")).toList();
        // El test (["ADD:a", "DEL:b"]) espera size()==1 (solo "ADD:a"). PISTA:
        // usa startsWith("ADD:") con los dos puntos para no confundir con un
        // hipotético "ADDED:" — sé estricto con el prefijo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para filtrarComandosAdd");
    }

    /**
     * RETO EXTRA 10: Filtra comandos DEL.
     */
    public static java.util.List<String> filtrarComandosDel(java.util.List<String> cmds) {
        // GUÍA: teoría 19.8 — simétrico al reto 9, prefijo "DEL:".
        // return cmds.stream().filter(c -> c.startsWith("DEL:")).toList();
        // El test (["ADD:a", "DEL:b"]) espera size()==1 (solo "DEL:b").
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para filtrarComandosDel");
    }

}
