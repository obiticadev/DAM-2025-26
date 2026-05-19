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
        return "";
    }

    public static void main(String[] args) {
        System.out.println(toJson(new Precio(9.9)));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: extrae el double con value.valor().
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: formatéalo con 2 decimales usando Locale.US (punto, no coma).
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: añade el sufijo " €" a la cadena formateada.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: escribe esa cadena con gen.writeString(...).
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: NO uses writeNumber: el contrato exige un string JSON.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: crea un SimpleModule.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: registra el serializer con module.addSerializer(Precio.class, new PrecioSerializer()).
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: crea un ObjectMapper y haz mapper.registerModule(module).
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: serializa 'p' con writeValueAsString.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: captura JsonProcessingException y relánzala como RuntimeException.
    }

}
