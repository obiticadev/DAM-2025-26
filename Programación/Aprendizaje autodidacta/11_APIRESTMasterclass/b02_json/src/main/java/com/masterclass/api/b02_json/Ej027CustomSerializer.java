package com.masterclass.api.b02_json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

/**
 * Ejercicio 027 · Serializer personalizado.
 *
 * <p>Teoría: {@code teoria/02_JSON_y_Jackson.md} (sección 2.3).
 *
 * <p>El dinero debe salir como cadena con 2 decimales y símbolo €, p.ej. "9.90 €".
 */
public final class Ej027CustomSerializer {

    public record Precio(double valor) {
    }

    /** Serializer que convierte un Precio en una cadena "X.XX €". */
    public static class PrecioSerializer extends StdSerializer<Precio> {
        public PrecioSerializer() {
            super(Precio.class);
        }

        /**
         * Escribe el precio como string formateado.
         *
         * @param value      precio
         * @param gen        generador JSON
         * @param provider   contexto de serialización
         * @throws IOException si falla la escritura
         */
        @Override
        public void serialize(Precio value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            // TODO 1: extrae el double con value.valor().
            // TODO 2: formatéalo con 2 decimales usando Locale.US (punto, no coma).
            // TODO 3: añade el sufijo " €" a la cadena formateada.
            // TODO 4: escribe esa cadena con gen.writeString(...).
            // TODO 5: NO uses writeNumber: el contrato exige un string JSON.
            double val = value.valor();
            String formatted = String.format(java.util.Locale.US, "%.2f €", val);
            gen.writeString(formatted);
        }
    }

    private Ej027CustomSerializer() {
    }

    /**
     * Construye un ObjectMapper que registra el PrecioSerializer y serializa el precio.
     *
     * @param p precio
     * @return JSON, p.ej. {@code "9.90 €"} (con comillas, es un string JSON)
     */
    public static String toJson(Precio p) {
        // TODO 6: crea un SimpleModule.
        // TODO 7: registra el serializer con module.addSerializer(Precio.class, new PrecioSerializer()).
        // TODO 8: crea un ObjectMapper y haz mapper.registerModule(module).
        // TODO 9: serializa 'p' con writeValueAsString.
        // TODO 10: captura JsonProcessingException y relánzala como RuntimeException.
        try {
            SimpleModule module = new SimpleModule();
            module.addSerializer(Precio.class, new PrecioSerializer());
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(module);
            return mapper.writeValueAsString(p);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            throw new RuntimeException("Error al serializar precio con custom serializer", e);
        }
    }

    // --- CLASES Y RECORDS AUXILIARES PARA RETOS EXTRA ---

    public record TarjetaCredito(String numero) {}
    public enum Rol { ADMIN, USER, GUEST }
    public record Dinero(double cantidad, String divisa) {}

    // --- IMPLEMENTACIÓN DE SERIALIZADORES Y DESERIALIZADORES PERSONALIZADOS ---

    public static class LocalDateSerializer extends StdSerializer<java.time.LocalDate> {
        public LocalDateSerializer() {
            super(java.time.LocalDate.class);
        }

        @Override
        public void serialize(java.time.LocalDate value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            // TODO extra (Reto 1): Serializa la fecha en formato "dd-MM-yyyy" (usa DateTimeFormatter).
            // Si el valor es null, escribe null.
        }
    }

    public static class LocalDateDeserializer extends com.fasterxml.jackson.databind.deser.std.StdDeserializer<java.time.LocalDate> {
        public LocalDateDeserializer() {
            super(java.time.LocalDate.class);
        }

        @Override
        public java.time.LocalDate deserialize(com.fasterxml.jackson.core.JsonParser p, com.fasterxml.jackson.databind.DeserializationContext ctxt) throws IOException {
            // TODO extra (Reto 2): Deserializa la fecha desde el formato "dd-MM-yyyy" a un LocalDate.
            // Si es nulo o vacío, devuelve null. Si falla el parsing, lanza IOException.
            return null;
        }
    }

    public static class TarjetaMaskSerializer extends StdSerializer<TarjetaCredito> {
        public TarjetaMaskSerializer() {
            super(TarjetaCredito.class);
        }

        @Override
        public void serialize(TarjetaCredito value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            // TODO extra (Reto 3): Serializa enmascarando el número de tarjeta a "****-****-****-XXXX".
            // XXXX deben ser los últimos 4 dígitos. Si es null o no tiene número, escribe null.
            // Si es más corto de 4 dígitos, escribe "****". Elimina espacios/guiones para enmascarar.
        }
    }

    public static class RolDeserializer extends com.fasterxml.jackson.databind.deser.std.StdDeserializer<Rol> {
        public RolDeserializer() {
            super(Rol.class);
        }

        @Override
        public Rol deserialize(com.fasterxml.jackson.core.JsonParser p, com.fasterxml.jackson.databind.DeserializationContext ctxt) throws IOException {
            // TODO extra (Reto 4): Deserializa el rol tolerando case-insensitive ("admin", "USER") y 
            // valores numéricos en String ("1"->ADMIN, "2"->USER, "3"->GUEST).
            // Lanza IOException si no es un rol válido.
            return null;
        }
    }

    public static class SiNoBooleanSerializer extends StdSerializer<Boolean> {
        public SiNoBooleanSerializer() {
            super(Boolean.class);
        }

        @Override
        public void serialize(Boolean value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            // TODO extra (Reto 5): Serializa el booleano a "SI" o "NO" (cadenas JSON).
            // Si el valor es null, escribe null.
        }
    }

    public static class SiNoBooleanDeserializer extends com.fasterxml.jackson.databind.deser.std.StdDeserializer<Boolean> {
        public SiNoBooleanDeserializer() {
            super(Boolean.class);
        }

        @Override
        public Boolean deserialize(com.fasterxml.jackson.core.JsonParser p, com.fasterxml.jackson.databind.DeserializationContext ctxt) throws IOException {
            // TODO extra (Reto 6): Deserializa a Boolean desde "SI"/"S"/"1"/"true" (true) o 
            // "NO"/"N"/"0"/"false" (false). Ignora mayúsculas/minúsculas y espacios.
            // Lanza IOException si no coincide con ninguno.
            return null;
        }
    }

    public static class MapToArraySerializer extends StdSerializer<java.util.Map> {
        public MapToArraySerializer() {
            super(java.util.Map.class);
        }

        @Override
        @SuppressWarnings("unchecked")
        public void serialize(java.util.Map value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            // TODO extra (Reto 7): Serializa un Map como un array JSON de objetos con estructura:
            // [{"clave": "...", "valor": "..."}]
        }
    }

    public static class ArrayToMapDeserializer extends com.fasterxml.jackson.databind.deser.std.StdDeserializer<java.util.Map> {
        public ArrayToMapDeserializer() {
            super(java.util.Map.class);
        }

        @Override
        public java.util.Map<?, ?> deserialize(com.fasterxml.jackson.core.JsonParser p, com.fasterxml.jackson.databind.DeserializationContext ctxt) throws IOException {
            // TODO extra (Reto 8): Deserializa un array JSON de tipo [{"clave":"...", "valor":"..."}]
            // a un Map<String, String>.
            return null;
        }
    }

    public static class DineroSerializer extends StdSerializer<Dinero> {
        public DineroSerializer() {
            super(Dinero.class);
        }

        @Override
        public void serialize(Dinero value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            // TODO extra (Reto 9): Serializa el Dinero como un objeto JSON con campos:
            // - "monto": cantidad numérica redondeada a 2 decimales.
            // - "codigoDivisa": divisa en mayúsculas (por defecto "EUR" si es null).
            // - "formateado": cadena "monto divisa" (ej. "150.75 USD").
        }
    }

    // --- MÉTODOS DE RETOS EXTRA ---

    /**
     * Reto Extra 1: Serializa una fecha en formato dd-MM-yyyy.
     */
    public static String serializarFecha(java.time.LocalDate fecha) {
        // TODO extra: Reto Extra 1: Serializa una fecha en formato dd-MM-yyyy.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para serializarFecha");
    }

    /**
     * Reto Extra 2: Deserializa una fecha desde formato dd-MM-yyyy.
     */
    public static java.time.LocalDate deserializarFecha(String json) {
        // TODO extra: Reto Extra 2: Deserializa una fecha desde formato dd-MM-yyyy.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para deserializarFecha");
    }

    /**
     * Reto Extra 3: Serializa una tarjeta enmascarando su número.
     */
    public static String serializarTarjeta(TarjetaCredito tarjeta) {
        // TODO extra: Reto Extra 3: Serializa una tarjeta enmascarando su número.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para serializarTarjeta");
    }

    /**
     * Reto Extra 4: Deserializa un rol tolerando variantes case-insensitive y numéricas.
     */
    public static Rol deserializarRol(String json) {
        // TODO extra: Reto Extra 4: Deserializa un rol tolerando variantes case-insensitive y numéricas.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para deserializarRol");
    }

    /**
     * Reto Extra 5: Serializa un Boolean como string "SI" / "NO".
     */
    public static String serializarBooleano(Boolean b) {
        // TODO extra: Reto Extra 5: Serializa un Boolean como string "SI" / "NO".
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para serializarBooleano");
    }

    /**
     * Reto Extra 6: Deserializa un Boolean desde variantes de "SI" / "NO".
     */
    public static Boolean deserializarBooleano(String json) {
        // TODO extra: Reto Extra 6: Deserializa un Boolean desde variantes de "SI" / "NO".
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para deserializarBooleano");
    }

    /**
     * Reto Extra 7: Serializa un mapa como una lista de objetos clave-valor.
     */
    public static String serializarMapaComoArray(java.util.Map<String, String> map) {
        // TODO extra: Reto Extra 7: Serializa un mapa como una lista de objetos clave-valor.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para serializarMapaComoArray");
    }

    /**
     * Reto Extra 8: Deserializa un mapa representado como lista de objetos clave-valor.
     */
    @SuppressWarnings("unchecked")
    public static java.util.Map<String, String> deserializarArrayComoMapa(String json) {
        // TODO extra: Reto Extra 8: Deserializa un mapa representado como lista de objetos clave-valor.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para SuppressWarnings");
    }

    /**
     * Reto Extra 9: Serializa dinero formateando su monto e indicando la divisa.
     */
    public static String serializarDinero(Dinero d) {
        // TODO extra: Reto Extra 9: Serializa dinero formateando su monto e indicando la divisa.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para serializarDinero");
    }

    /**
     * Reto Extra 10: Configuración dinámica de ObjectMapper con registro selectivo de múltiples serializadores y deserializadores.
     */
    public static ObjectMapper crearMapperConModuloComplejo() {
        // TODO extra: Reto Extra 10: Configuración dinámica de ObjectMapper con registro selectivo de múltiples serializadores y deserializadores.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearMapperConModuloComplejo");
    }
}
