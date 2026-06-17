package com.masterclass.api.b20_obs;

import java.util.Map;

/**
 * Ejercicio 181 · Logging estructurado con MDC.
 *
 * <p>Teoria: {@code teoria/20_Observabilidad_Documentacion.md} (seccion 20.5).
 *
 * <p>El {@code MDC} (Mapped Diagnostic Context) de SLF4J adjunta campos
 * (userId, traceId...) a cada log. Aqui formateamos una linea de log
 * estructurada en JSON usando Jackson (ya en el pom) a partir del nivel, el
 * mensaje y el mapa de campos MDC.
 */
public final class Ej181StructuredLoggingMdc {

    private Ej181StructuredLoggingMdc() {
    }

    /**
     * Formatea una linea de log estructurado como JSON.
     *
     * @param nivel  nivel de log (INFO, WARN, ERROR...); no en blanco
     * @param mensaje mensaje del log; no null
     * @param mdc    campos MDC a incluir; no null (puede estar vacio)
     * @return cadena JSON con claves "level", "message" y las del MDC
     * @throws IllegalArgumentException si nivel en blanco, mensaje o mdc null
     */
    public static String formatear(String nivel, String mensaje, Map<String, String> mdc) {
        // TODO 1: si nivel es null o en blanco -> IllegalArgumentException.
        // TODO 2: si mensaje es null -> IllegalArgumentException.
        // TODO 3: si mdc es null -> IllegalArgumentException.
        // TODO 4: crea un LinkedHashMap para preservar orden de las claves.
        // TODO 5: pon "level" con el nivel normalizado a mayusculas (trim).
        // TODO 6: pon "message" con el mensaje tal cual.
        // TODO 7: copia todos los pares del mdc al mapa (no sobrescribas level/message).
        // TODO 8: crea un com.fasterxml.jackson.databind.ObjectMapper.
        // TODO 9: serializa el mapa a JSON (writeValueAsString); envuelve la
        //         JsonProcessingException en IllegalStateException.
        // TODO 10: devuelve la cadena JSON resultante.
        return "{}";
    }

    public static void main(String[] args) {
        System.out.println(formatear("INFO", "ok", Map.of("traceId", "abc")));
    }

        /**
     * RETO EXTRA 01: Valida si el nivel no es blanco.
     */
    public static boolean esNivelValido(String nivel) {
        // GUÍA: nivel válido = presente y no en blanco (teoría 20.5: isBlank cubre " ").
        // PISTA: return nivel != null && !nivel.isBlank();
        // El test manda "INFO" -> true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esNivelValido");
    }

    /**
     * RETO EXTRA 02: Valida mensaje no nulo.
     */
    public static boolean esMensajeValido(String mensaje) {
        // GUÍA: el mensaje solo debe NO ser null (un mensaje vacío "" es válido,
        // a diferencia del nivel). Mira formatear(): allí solo se exige != null.
        // PISTA: return mensaje != null;
        // El test manda "ok" -> true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esMensajeValido");
    }

    /**
     * RETO EXTRA 03: Valida mdc no nulo.
     */
    public static boolean esMdcValido(java.util.Map<String, String> mdc) {
        // GUÍA: el mdc solo debe NO ser null (un mapa vacío SÍ es válido).
        // PISTA: return mdc != null;
        // OJO: el test manda Map.of() (vacío) y espera true; no exijas !isEmpty().
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esMdcValido");
    }

    /**
     * RETO EXTRA 04: Limpia y capitaliza nivel.
     */
    public static String normalizarNivel(String nivel) {
        // GUÍA: trim + mayúsculas (la misma normalización que usa formatear() en TODO 5).
        // PISTA: return nivel.trim().toUpperCase();
        // OJO: el test manda " info " (con espacios y minúscula) y espera "INFO".
        // "Capitaliza" aquí significa pasar TODO a mayúsculas, no solo la inicial.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para normalizarNivel");
    }

    /**
     * RETO EXTRA 05: Crea un LinkedHashMap para preservar orden.
     */
    public static java.util.Map<String, String> crearMdcMap() {
        // GUÍA: una línea — mapa que preserva orden de inserción (teoría 20.5).
        // return new java.util.LinkedHashMap<>();
        // OJO: LinkedHashMap (no HashMap) para que en el JSON las claves salgan
        // en el orden en que las metiste. El test solo comprueba assertNotNull.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearMdcMap");
    }

    /**
     * RETO EXTRA 06: Inserta clave-valor en mdc.
     */
    public static java.util.Map<String, String> insertarMdc(java.util.Map<String, String> mdc, String k, String v) {
        // GUÍA: put y devuelve el mismo mapa (patrón fluido, como en Ej179).
        // 1. mdc.put(k, v);
        // 2. return mdc;
        // OJO: el test pasa un HashMap mutable y espera size()==1 sobre el RETORNO.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para insertarMdc");
    }

    /**
     * RETO EXTRA 07: Limpia todas las claves del mdc.
     */
    public static java.util.Map<String, String> limpiarMdc(java.util.Map<String, String> mdc) {
        // GUÍA: vacía el mapa y devuélvelo. Espejo de insertarMdc.
        // 1. mdc.clear();
        // 2. return mdc;
        // El test pasa {a=b} y espera size()==0 tras limpiar.
        // CULTURA: en producción esto es MDC.clear() al terminar la petición, para
        // que el traceId de una no se filtre al hilo reciclado por la siguiente.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para limpiarMdc");
    }

    /**
     * RETO EXTRA 08: Verifica si contiene traceId.
     */
    public static boolean contieneTrazaMdc(java.util.Map<String, String> mdc) {
        // GUÍA: una línea — ¿el mdc tiene la clave "traceId"? (la del bloque 20.6).
        // PISTA: return mdc != null && mdc.containsKey("traceId");
        // OJO: clave EXACTA "traceId" (camelCase). El test manda {traceId=123}.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contieneTrazaMdc");
    }

    /**
     * RETO EXTRA 09: Serializa un log vacio.
     */
    public static String serializarVacio() {
        // GUÍA: serializa un mapa vacío con Jackson -> "{}".
        // PISTA: new ObjectMapper().writeValueAsString(new LinkedHashMap<>());
        // OJO: writeValueAsString lanza JsonProcessingException (checked);
        // envuélvela en try/catch -> IllegalStateException (igual que formatear()).
        // El test espera EXACTAMENTE "{}".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para serializarVacio");
    }

    /**
     * RETO EXTRA 10: Obtiene un campo textual del JSON.
     */
    public static String obtenerDeJson(String json, String k) {
        // GUÍA: parsea el JSON y extrae el campo de texto k (operación inversa a formatear).
        // 1. JsonNode raiz = new ObjectMapper().readTree(json);
        // 2. return raiz.get(k).asText();   // asText() devuelve el String sin comillas
        // OJO: readTree lanza JsonProcessingException (checked) -> envuélvela en
        // IllegalStateException, como en formatear(). El test pasa
        // {"level":"INFO"} con k="level" y espera "INFO".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerDeJson");
    }

}
