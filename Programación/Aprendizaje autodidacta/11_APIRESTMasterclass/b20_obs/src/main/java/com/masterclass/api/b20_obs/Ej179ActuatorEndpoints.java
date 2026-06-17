package com.masterclass.api.b20_obs;

import java.util.Map;

/**
 * Ejercicio 179 · Endpoints de Actuator (health agregado).
 *
 * <p>Teoria: {@code teoria/20_Observabilidad_Documentacion.md} (seccion 20.3).
 *
 * <p>Spring Boot Actuator expone {@code /actuator/health} agregando el estado
 * de varios componentes (db, disk, ping...). Aqui replicamos esa logica de
 * agregacion como funcion pura: el estado global es UP solo si todos los
 * componentes estan UP; en caso contrario DOWN.
 */
public final class Ej179ActuatorEndpoints {

    private Ej179ActuatorEndpoints() {
    }

    /**
     * Calcula el estado de salud agregado a partir de los componentes.
     *
     * @param componentes mapa nombre-&gt;estado ("UP"/"DOWN"); no null
     * @return "UP" si todos UP y hay al menos uno; "DOWN" en otro caso
     * @throws IllegalArgumentException si componentes es null o algun valor nulo
     */
    public static String estadoAgregado(Map<String, String> componentes) {
        // TODO 1: si componentes es null -> IllegalArgumentException.
        // TODO 2: si el mapa esta vacio, devuelve "UNKNOWN" (no hay datos).
        // TODO 3: recorre todos los valores del mapa.
        // TODO 4: si algun valor es null -> IllegalArgumentException.
        // TODO 5: normaliza cada estado a mayusculas y sin espacios (trim).
        // TODO 6: considera UP solo el literal exacto "UP" tras normalizar.
        // TODO 7: si encuentras cualquier estado distinto de "UP" -> resultado "DOWN".
        // TODO 8: el agregado es estricto: un solo DOWN tumba el conjunto.
        // TODO 9: si todos los componentes son "UP" -> resultado "UP".
        // TODO 10: devuelve el estado agregado calculado.
        return "UNKNOWN";
    }

    public static void main(String[] args) {
        System.out.println(estadoAgregado(Map.of("db", "UP", "disk", "UP")));
    }

        /**
     * RETO EXTRA 01: Valida si es UP.
     */
    public static boolean esEstadoUp(String estado) {
        // GUÍA: teoría 20.3 (normaliza antes de comparar: "up " cuenta como UP).
        // 1. Si estado es null -> false.
        // 2. trim() + toUpperCase() y compara con "UP".
        // PISTA: return estado != null && "UP".equals(estado.trim().toUpperCase());
        // OJO: el test manda "UP", pero la misma lógica deja pasar "up " (la que
        // usa estadoAgregado del ejercicio base).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esEstadoUp");
    }

    /**
     * RETO EXTRA 02: Valida si es DOWN.
     */
    public static boolean esEstadoDown(String estado) {
        // GUÍA: espejo de esEstadoUp comparando con "DOWN". Reutiliza el patrón.
        // PISTA: return estado != null && "DOWN".equals(estado.trim().toUpperCase());
        // OJO: NO lo definas como !esEstadoUp(estado): "UNKNOWN" no es ni UP ni
        // DOWN, así que la negación daría un falso positivo de DOWN.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esEstadoDown");
    }

    /**
     * RETO EXTRA 03: Total componentes.
     */
    public static int cantidadComponentes(java.util.Map<String, String> comps) {
        // GUÍA: una línea — tamaño del mapa.
        // PISTA: return comps == null ? 0 : comps.size();
        // El test manda un mapa de 2 entradas y espera 2.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cantidadComponentes");
    }

    /**
     * RETO EXTRA 04: Busca existencia.
     */
    public static boolean contieneComponente(java.util.Map<String, String> comps, String c) {
        // GUÍA: una línea — containsKey.
        // PISTA: return comps != null && comps.containsKey(c);
        // El test pregunta por "db" en un mapa que lo tiene (true).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contieneComponente");
    }

    /**
     * RETO EXTRA 05: Obtiene estado.
     */
    public static String obtenerEstadoComponente(java.util.Map<String, String> comps, String c) {
        // GUÍA: una línea — get del mapa.
        // PISTA: return comps == null ? null : comps.get(c);
        // El test pide "db" y espera "UP". (get ya devuelve null si no existe.)
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerEstadoComponente");
    }

    /**
     * RETO EXTRA 06: Crea un mapa limpio.
     */
    public static java.util.Map<String, String> inicializarAgregador() {
        // GUÍA: una línea — mapa mutable vacío al que luego añadir componentes.
        // return new java.util.LinkedHashMap<>();   // o HashMap; orden estable mejor
        // OJO: NO devuelvas Map.of() (inmutable): el reto 07 hará put sobre él.
        // El test solo comprueba assertNotNull.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para inicializarAgregador");
    }

    /**
     * RETO EXTRA 07: Agrega componente.
     */
    public static java.util.Map<String, String> agregarComponente(java.util.Map<String, String> comps, String c, String e) {
        // GUÍA: añade el par (c,e) y DEVUELVE el mismo mapa para encadenar.
        // 1. comps.put(c, e);
        // 2. return comps;
        // OJO: el test pasa un HashMap vacío y espera size()==1 sobre el RETORNO,
        // así que devuelve el mapa (no void). Recibe mapa mutable (no Map.of()).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para agregarComponente");
    }

    /**
     * RETO EXTRA 08: Elimina componente.
     */
    public static java.util.Map<String, String> eliminarComponente(java.util.Map<String, String> comps, String c) {
        // GUÍA: espejo de agregarComponente — quita la clave y devuelve el mapa.
        // 1. comps.remove(c);
        // 2. return comps;
        // El test pasa {db=UP}, elimina "db" y espera size()==0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para eliminarComponente");
    }

    /**
     * RETO EXTRA 09: Verifica homogeneidad.
     */
    public static boolean todosConEstado(java.util.Map<String, String> comps, String e) {
        // GUÍA: ¿TODOS los valores valen e? (teoría 1.3: stream + allMatch).
        // PISTA: return comps.values().stream().allMatch(v -> v.equals(e));
        // OJO: allMatch sobre un mapa vacío devuelve true (no hay contraejemplo);
        // valóralo si quieres exigir al menos uno. El test manda {db=UP,ping=UP}
        // con e="UP" y espera true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para todosConEstado");
    }

    /**
     * RETO EXTRA 10: Verifica si hay algun caso.
     */
    public static boolean algunoConEstado(java.util.Map<String, String> comps, String e) {
        // GUÍA: ¿ALGÚN valor vale e? Espejo de todosConEstado con anyMatch.
        // PISTA: return comps.values().stream().anyMatch(v -> v.equals(e));
        // El test manda {db=DOWN,ping=UP} con e="DOWN" y espera true.
        // CULTURA: allMatch/anyMatch son justo lo que evalúa el health agregado
        // por dentro (todos UP -> UP; alguno DOWN -> DOWN).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para algunoConEstado");
    }

}
