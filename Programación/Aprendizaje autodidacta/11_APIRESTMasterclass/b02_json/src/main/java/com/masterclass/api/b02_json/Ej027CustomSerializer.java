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
            // GUÍA: teoría 2.5 (un serializer escribe en el JsonGenerator).
            // 1. Si value es null -> gen.writeNull(); y return.
            // 2. Formatea con un DateTimeFormatter de patrón "dd-MM-yyyy" (teoría 1.10):
            //    DateTimeFormatter.ofPattern("dd-MM-yyyy").format(value).
            // 3. Escríbelo como STRING JSON: gen.writeString(textoFormateado).
            // OJO: el test espera "\"21-05-2026\"" (string con comillas) -> writeString, no
            //   writeNumber. El año va al final en este formato (dd-MM-yyyy).
        }
    }

    public static class LocalDateDeserializer extends com.fasterxml.jackson.databind.deser.std.StdDeserializer<java.time.LocalDate> {
        public LocalDateDeserializer() {
            super(java.time.LocalDate.class);
        }

        @Override
        public java.time.LocalDate deserialize(com.fasterxml.jackson.core.JsonParser p, com.fasterxml.jackson.databind.DeserializationContext ctxt) throws IOException {
            // GUÍA: teoría 2.5 (un deserializer lee del JsonParser y devuelve el objeto).
            // 1. Lee el texto: String s = p.getValueAsString();
            // 2. Si s es null o está en blanco -> return null.
            // 3. Parsea con el MISMO patrón que el serializer del reto 1:
            //    LocalDate.parse(s, DateTimeFormatter.ofPattern("dd-MM-yyyy")).
            // 4. Si el parseo lanza DateTimeParseException, envuélvela en IOException
            //    (try/catch) para respetar la firma del método.
            // OJO: el patrón de lectura DEBE coincidir con el de escritura, o no hay round-trip.
            return null;
        }
    }

    public static class TarjetaMaskSerializer extends StdSerializer<TarjetaCredito> {
        public TarjetaMaskSerializer() {
            super(TarjetaCredito.class);
        }

        @Override
        public void serialize(TarjetaCredito value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            // GUÍA: teoría 2.5 (transformar un valor sensible antes de exponerlo).
            // 1. Si value es null o value.numero() es null -> gen.writeNull(); y return.
            // 2. Normaliza: quita espacios y guiones del número:
            //    value.numero().replace("-", "").replace(" ", "")  (o replaceAll("[\\s-]", "")).
            // 3. Si el número limpio tiene MENOS de 4 dígitos -> gen.writeString("****"); return.
            // 4. Si no, toma los últimos 4 (substring(len-4)) y escribe "****-****-****-" + esos4.
            // PISTA: numero.substring(numero.length() - 4)
            // OJO: el test "1234-5678-9012-3456" espera "\"****-****-****-3456\"" y "123" (3
            //   dígitos) espera "\"****\"". CULTURA: enmascarar PAN de tarjeta es requisito PCI-DSS.
        }
    }

    public static class RolDeserializer extends com.fasterxml.jackson.databind.deser.std.StdDeserializer<Rol> {
        public RolDeserializer() {
            super(Rol.class);
        }

        @Override
        public Rol deserialize(com.fasterxml.jackson.core.JsonParser p, com.fasterxml.jackson.databind.DeserializationContext ctxt) throws IOException {
            // GUÍA: teoría 2.5 (deserializer tolerante a variantes de entrada).
            // 1. Lee el texto: String s = p.getValueAsString(); si null -> IOException o null.
            // 2. Recorta y normaliza: s = s.strip().
            // 3. Mapea los numéricos primero: "1"->ADMIN, "2"->USER, "3"->GUEST (switch o ifs).
            // 4. Si no es numérico, prueba el nombre en MAYÚSCULAS: Rol.valueOf(s.toUpperCase()).
            // 5. Si nada encaja (o valueOf lanza IllegalArgumentException) -> lanza IOException.
            // PISTA: Rol.valueOf("admin".toUpperCase()) == Rol.ADMIN.
            // OJO: el test prueba "admin", "User", "GUEST" (distintas mayúsculas) y "1","2","3".
            //   Captura el IllegalArgumentException de valueOf y reconviértelo en IOException.
            return null;
        }
    }

    public static class SiNoBooleanSerializer extends StdSerializer<Boolean> {
        public SiNoBooleanSerializer() {
            super(Boolean.class);
        }

        @Override
        public void serialize(Boolean value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            // GUÍA: teoría 2.5 (mapear un boolean a etiquetas de dominio).
            // 1. Si value es null -> gen.writeNull(); return.
            // 2. gen.writeString(value ? "SI" : "NO");
            // OJO: el test espera "\"SI\"" para true y "\"NO\"" para false (en MAYÚSCULAS,
            //   como string JSON con comillas).
        }
    }

    public static class SiNoBooleanDeserializer extends com.fasterxml.jackson.databind.deser.std.StdDeserializer<Boolean> {
        public SiNoBooleanDeserializer() {
            super(Boolean.class);
        }

        @Override
        public Boolean deserialize(com.fasterxml.jackson.core.JsonParser p, com.fasterxml.jackson.databind.DeserializationContext ctxt) throws IOException {
            // GUÍA: teoría 2.5 (deserializer que acepta varias formas de "sí/no").
            // 1. Lee y normaliza: String s = p.getValueAsString().strip().toUpperCase();
            // 2. Si s es uno de "SI","S","1","TRUE" -> return true.
            // 3. Si s es uno de "NO","N","0","FALSE" -> return false.
            // 4. En cualquier otro caso -> lanza IOException.
            // PISTA: java.util.Set.of("SI","S","1","TRUE").contains(s)  (tras toUpperCase).
            // OJO: el test prueba "SI","s","1","true" (true) y "NO","n","0","false" (false);
            //   por eso normalizas a mayúsculas ANTES de comparar. Es el inverso del reto 5.
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
            // GUÍA: teoría 2.5 (generar estructura a mano con el JsonGenerator).
            // 1. gen.writeStartArray();  // abre [
            // 2. Recorre value.entrySet(): por cada entrada
            //    gen.writeStartObject();                       // {
            //    gen.writeStringField("clave", String.valueOf(e.getKey()));
            //    gen.writeStringField("valor", String.valueOf(e.getValue()));
            //    gen.writeEndObject();                         // }
            // 3. gen.writeEndArray();   // cierra ]
            // OJO: el test comprueba que empieza por "[", termina por "]" y contiene pares
            //   "clave":"color" y "valor":"azul". Es la conversión Map -> array de objetos.
        }
    }

    public static class ArrayToMapDeserializer extends com.fasterxml.jackson.databind.deser.std.StdDeserializer<java.util.Map> {
        public ArrayToMapDeserializer() {
            super(java.util.Map.class);
        }

        @Override
        public java.util.Map<?, ?> deserialize(com.fasterxml.jackson.core.JsonParser p, com.fasterxml.jackson.databind.DeserializationContext ctxt) throws IOException {
            // GUÍA: teoría 2.6 (lo más cómodo: leer el árbol y recorrerlo).
            // 1. Lee el array como árbol: JsonNode arr = p.readValueAsTree(); (o p.getCodec().readTree(p)).
            // 2. Crea un Map<String,String> y recorre cada elemento del array:
            //    para cada nodo -> map.put(nodo.get("clave").asText(), nodo.get("valor").asText()).
            // 3. return ese map.
            // PISTA: for (JsonNode n : arr) map.put(n.get("clave").asText(), n.get("valor").asText());
            // OJO: es el INVERSO del reto 7. El test pasa [{"clave":"host","valor":"localhost"},...]
            //   y espera map.get("host") == "localhost" con size 2.
            return null;
        }
    }

    public static class DineroSerializer extends StdSerializer<Dinero> {
        public DineroSerializer() {
            super(Dinero.class);
        }

        @Override
        public void serialize(Dinero value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            // GUÍA: teoría 2.5 (escribir un objeto con varios campos a mano).
            // 1. Divisa por defecto: String div = (value.divisa()==null ? "EUR" : value.divisa()).toUpperCase();
            // 2. Monto redondeado a 2 decimales: Math.round(value.cantidad()*100)/100.0.
            // 3. gen.writeStartObject();
            //    gen.writeNumberField("monto", monto);          // NÚMERO, sin comillas
            //    gen.writeStringField("codigoDivisa", div);      // STRING
            //    gen.writeStringField("formateado", String.format(Locale.US, "%.2f %s", monto, div));
            //    gen.writeEndObject();
            // OJO: el test espera "monto":150.75 (número, writeNumberField), "codigoDivisa":"USD"
            //   (la entrada era "usd" en minúsculas -> toUpperCase) y "formateado":"150.75 USD".
            //   Usa Locale.US en el format para que el separador decimal sea un punto, no coma.
        }
    }

    // --- MÉTODOS DE RETOS EXTRA ---

    /**
     * Reto Extra 1: Serializa una fecha en formato dd-MM-yyyy.
     */
    public static String serializarFecha(java.time.LocalDate fecha) {
        // GUÍA: teoría 2.5 (registrar el serializer en un módulo y serializar).
        // 1. SimpleModule module = new SimpleModule();
        // 2. module.addSerializer(LocalDate.class, new LocalDateSerializer());  // tu clase de arriba
        // 3. ObjectMapper mapper = new ObjectMapper(); mapper.registerModule(module);
        // 4. dentro de try: return mapper.writeValueAsString(fecha);  catch -> RuntimeException.
        // PISTA: calca la estructura del método toJson(Precio) de este mismo fichero.
        // OJO: el test espera "\"21-05-2026\"". Toda la lógica de formato vive en
        //   LocalDateSerializer (reto 1 de la clase interna); aquí solo lo enchufas.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para serializarFecha");
    }

    /**
     * Reto Extra 2: Deserializa una fecha desde formato dd-MM-yyyy.
     */
    public static java.time.LocalDate deserializarFecha(String json) {
        // GUÍA: teoría 2.5 (registrar el deserializer y leer).
        // 1. SimpleModule con module.addDeserializer(LocalDate.class, new LocalDateDeserializer()).
        // 2. mapper.registerModule(module).
        // 3. dentro de try: return mapper.readValue(json, LocalDate.class);
        // PISTA: addDeserializer (no addSerializer) para el camino JSON -> objeto.
        // OJO: el json del test es "\"21-05-2026\"" (un string JSON, con comillas) y se espera
        //   un LocalDate con año 2026, mes 5, día 21. La lógica de parseo está en
        //   LocalDateDeserializer (reto 2 de la clase interna).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para deserializarFecha");
    }

    /**
     * Reto Extra 3: Serializa una tarjeta enmascarando su número.
     */
    public static String serializarTarjeta(TarjetaCredito tarjeta) {
        // GUÍA: igual que el reto 1 pero con TarjetaMaskSerializer.
        // 1. module.addSerializer(TarjetaCredito.class, new TarjetaMaskSerializer()).
        // 2. registra el módulo y writeValueAsString(tarjeta) dentro de try.
        // OJO: el test espera "\"****-****-****-3456\"" para la tarjeta larga y "\"****\""
        //   para "123". El enmascarado vive en TarjetaMaskSerializer (reto 3 interno).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para serializarTarjeta");
    }

    /**
     * Reto Extra 4: Deserializa un rol tolerando variantes case-insensitive y numéricas.
     */
    public static Rol deserializarRol(String json) {
        // GUÍA: igual patrón pero con addDeserializer(Rol.class, new RolDeserializer()).
        // 1. registra el módulo y dentro de try: return mapper.readValue(json, Rol.class);
        // OJO: el test prueba "\"admin\"", "\"User\"", "\"GUEST\"", "\"1\"", "\"2\"", "\"3\"".
        //   La tolerancia (mayúsculas y números) está en RolDeserializer (reto 4 interno).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para deserializarRol");
    }

    /**
     * Reto Extra 5: Serializa un Boolean como string "SI" / "NO".
     */
    public static String serializarBooleano(Boolean b) {
        // GUÍA: addSerializer(Boolean.class, new SiNoBooleanSerializer()), registra y serializa.
        // 1. dentro de try: return mapper.writeValueAsString(b);
        // OJO: el test espera "\"SI\"" para true y "\"NO\"" para false. Lógica en
        //   SiNoBooleanSerializer (reto 5 interno).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para serializarBooleano");
    }

    /**
     * Reto Extra 6: Deserializa un Boolean desde variantes de "SI" / "NO".
     */
    public static Boolean deserializarBooleano(String json) {
        // GUÍA: addDeserializer(Boolean.class, new SiNoBooleanDeserializer()), registra y lee.
        // 1. dentro de try: return mapper.readValue(json, Boolean.class);
        // OJO: el test acepta "SI","s","1","true" (true) y "NO","n","0","false" (false). La
        //   tolerancia vive en SiNoBooleanDeserializer (reto 6 interno).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para deserializarBooleano");
    }

    /**
     * Reto Extra 7: Serializa un mapa como una lista de objetos clave-valor.
     */
    public static String serializarMapaComoArray(java.util.Map<String, String> map) {
        // GUÍA: addSerializer(Map.class, new MapToArraySerializer()), registra y serializa.
        // 1. dentro de try: return mapper.writeValueAsString(map);
        // OJO: el test comprueba que empieza por "[", acaba en "]" y contiene los pares
        //   "clave"/"valor". La construcción del array está en MapToArraySerializer (reto 7
        //   interno). CUIDADO: registrar un serializer para Map.class afecta a TODOS los maps
        //   de ese mapper -> por eso aquí usas un mapper local, no el global del fichero.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para serializarMapaComoArray");
    }

    /**
     * Reto Extra 8: Deserializa un mapa representado como lista de objetos clave-valor.
     */
    @SuppressWarnings("unchecked")
    public static java.util.Map<String, String> deserializarArrayComoMapa(String json) {
        // GUÍA: addDeserializer(Map.class, new ArrayToMapDeserializer()), registra y lee.
        // 1. dentro de try: return mapper.readValue(json, Map.class);  (cast a Map<String,String>).
        // OJO: el test pasa [{"clave":"host","valor":"localhost"},...] y espera map.get("host")
        //   == "localhost" con size 2. La reconstrucción está en ArrayToMapDeserializer (reto 8
        //   interno). El @SuppressWarnings("unchecked") del método ya está puesto por el cast.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para SuppressWarnings");
    }

    /**
     * Reto Extra 9: Serializa dinero formateando su monto e indicando la divisa.
     */
    public static String serializarDinero(Dinero d) {
        // GUÍA: addSerializer(Dinero.class, new DineroSerializer()), registra y serializa.
        // 1. dentro de try: return mapper.writeValueAsString(d);
        // OJO: el test (Dinero(150.75,"usd")) espera "monto":150.75, "codigoDivisa":"USD",
        //   "formateado":"150.75 USD". Toda la lógica está en DineroSerializer (reto 9 interno).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para serializarDinero");
    }

    /**
     * Reto Extra 10: Configuración dinámica de ObjectMapper con registro selectivo de múltiples serializadores y deserializadores.
     */
    public static ObjectMapper crearMapperConModuloComplejo() {
        // GUÍA: teoría 2.5 (UN módulo puede registrar VARIOS (de)serializers a la vez).
        // 1. SimpleModule module = new SimpleModule();
        // 2. Añade todos los que necesites en ese mismo módulo:
        //    module.addSerializer(LocalDate.class, new LocalDateSerializer());
        //    module.addSerializer(Dinero.class, new DineroSerializer());
        //    (puedes añadir también los deserializers y los demás serializers).
        // 3. ObjectMapper mapper = new ObjectMapper(); mapper.registerModule(module);
        // 4. return mapper;   // este método NO serializa, solo devuelve el mapper configurado.
        // OJO: el test usa el mapper devuelto para serializar un LocalDate (espera
        //   "\"31-12-2026\"") Y un Dinero ("monto":10.5, "codigoDivisa":"EUR") con el MISMO
        //   mapper -> de ahí que ambos serializers vivan en el mismo módulo.
        // CULTURA: esto es justo lo que hace Spring Boot al arrancar: junta todos los módulos
        //   Jackson en un único ObjectMapper que luego inyecta en tus controllers.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearMapperConModuloComplejo");
    }
}
