package com.masterclass.api.b02_json;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Ejercicio 024 · ObjectMapper: serializar y deserializar.
 *
 * <p>Teoría: {@code teoria/02_JSON_y_Jackson.md} (sección 2.2).
 */
public final class Ej024ObjectMapperBasics {

    /** DTO de ejemplo (los componentes son el contrato). */
    public record Cliente(Long id, String nombre) {
    }

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private Ej024ObjectMapperBasics() {
    }

    /**
     * Serializa un Cliente a JSON.
     *
     * @param c cliente a convertir
     * @return cadena JSON, p.ej. {@code {"id":1,"nombre":"Ana"}}
     * @throws RuntimeException si Jackson falla (envuelve la checked)
     */
    public static String toJson(Cliente c) {
        // TODO 1: abre un try alrededor de la serialización.
        // TODO 2: usa MAPPER.writeValueAsString(c).
        // TODO 3: devuelve la cadena resultante.
        // TODO 4: captura JsonProcessingException.
        // TODO 5: relánzala envuelta en una RuntimeException (no la silencies).
        return "";
    }

    /**
     * Deserializa JSON a un Cliente.
     *
     * @param json texto JSON con id y nombre
     * @return instancia de Cliente
     * @throws RuntimeException si el JSON es inválido
     */
    public static Cliente fromJson(String json) {
        // TODO 6: abre un try alrededor de la deserialización.
        // TODO 7: usa MAPPER.readValue(json, Cliente.class).
        // TODO 8: devuelve el objeto poblado.
        // TODO 9: captura JsonProcessingException.
        // TODO 10: relánzala como RuntimeException con el mensaje original.
        return null;
    }

    public static void main(String[] args) {
        String j = toJson(new Cliente(1L, "Ana"));
        System.out.println(j + " -> " + fromJson(j));
    }

    /**
     * Reto Extra 1: Serialización estética (Pretty Printing).
     * Serializa el cliente con sangrías e identación para que sea legible por humanos.
     *
     * @param c cliente
     * @return JSON formateado estéticamente
     */
    public static String serializarBonito(Cliente c) {
        // GUÍA: teoría 2.2 (pretty-print = JSON indentado con saltos de línea).
        // 1. Abre un try (writeValueAsString lanza JsonProcessingException).
        // 2. Usa el writer con pretty printer por defecto:
        //    MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(c).
        // 3. Captura y relanza como RuntimeException (igual que toJson).
        // PISTA: return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(c);
        // OJO: el test solo comprueba que la salida contiene "\n" o "\r" (saltos de línea);
        // por eso el pretty printer es justo lo que pide.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para serializarBonito");
    }

    /**
     * Reto Extra 2: Deserialización genérica tipo-segura.
     * Deserializa una cadena JSON a cualquier tipo de clase indicado por parámetro.
     *
     * @param json   texto JSON
     * @param clase  clase de destino
     * @param <T>    tipo genérico de retorno
     * @return instancia de la clase de destino deserializada
     */
    public static <T> T deserializarGenerico(String json, Class<T> clase) {
        // GUÍA: teoría 2.2 (readValue acepta un Class<T> y devuelve ya el tipo T).
        // 1. Abre un try.
        // 2. return MAPPER.readValue(json, clase);  ← el parámetro clase es Class<T>,
        //    así Jackson te devuelve directamente un T sin casts.
        // 3. Captura JsonProcessingException y relánzala como RuntimeException.
        // PISTA: return MAPPER.readValue(json, clase);
        // CULTURA: este es el mismo truco de genéricos de la teoría 1.5: un solo método
        // sirve para Cliente, Pedido o lo que sea. El test pasa Cliente.class y espera id 7L.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para deserializarGenerico");
    }

    /**
     * Reto Extra 3: Validación estructural JSON.
     * Valida sintácticamente un texto para verificar si es un JSON estructurado correcto mediante Jackson.
     *
     * @param json cadena de texto
     * @return true si es un JSON sintácticamente correcto, false si está corrupto o es nulo
     */
    public static boolean esJsonValido(String json) {
        // GUÍA: teoría 2.2 (validar = intentar parsear y ver si lanza).
        // 1. Si json es null -> false.
        // 2. Abre un try: MAPPER.readTree(json) parsea sin necesitar un DTO.
        // 3. Si NO lanza -> return true.
        // 4. Captura la excepción (aquí sí la TRAGAS a propósito) -> return false.
        // PISTA: try { MAPPER.readTree(json); return true; } catch (Exception e) { return false; }
        // OJO: este es el ÚNICO caso del bloque donde silenciar la excepción es correcto,
        // porque el propósito del método ES detectar el fallo. El test: "{\"a\":1}" -> true,
        // "{mal-formado}" -> false. Captura Exception (cubre JsonProcessingException).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esJsonValido");
    }

    /**
     * Reto Extra 4: Tolerancia a propiedades desconocidas.
     * Deserializa un JSON a Cliente tolerando propiedades adicionales que no estén presentes en el DTO Cliente
     * sin lanzar excepciones.
     *
     * @param json JSON con campos extra (ej. {"id":1,"nombre":"Ana","email":"ana@correo.com"})
     * @return objeto Cliente deserializado omitiendo los campos sobrantes
     */
    public static Cliente deserializarIgnorandoCamposDesconocidos(String json) {
        // GUÍA: teoría 2.2 (FAIL_ON_UNKNOWN_PROPERTIES y la tabla del mapper).
        // 1. El MAPPER por defecto EXPLOTA si el JSON trae un campo que Cliente no tiene.
        //    Crea un mapper tolerante: new ObjectMapper().configure(
        //      DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).
        // 2. Con ese mapper haz readValue(json, Cliente.class) dentro de un try.
        // 3. Captura JsonProcessingException y relánzala como RuntimeException.
        // PISTA: import com.fasterxml.jackson.databind.DeserializationFeature;
        // OJO: el JSON del test trae "email" además de id y nombre, y espera un Cliente
        // con id=1, nombre="Ana" (el email se descarta sin error).
        // CULTURA: Spring Boot desactiva esta opción por ti -> por eso tus controllers
        // toleran campos extra de fábrica.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para deserializarIgnorandoCamposDesconocidos");
    }

    /**
     * Reto Extra 5: Serialización binaria.
     * Serializa el DTO Cliente a una matriz de bytes en codificación UTF-8.
     *
     * @param c cliente
     * @return array de bytes serializado
     */
    public static byte[] serializarABytes(Cliente c) {
        // GUÍA: teoría 2.2 (writeValueAsBytes = serializar directamente a byte[] UTF-8).
        // 1. Abre un try.
        // 2. return MAPPER.writeValueAsBytes(c);
        // 3. Captura JsonProcessingException -> RuntimeException.
        // PISTA: return MAPPER.writeValueAsBytes(c);
        // OJO: el test solo comprueba que el array no es null y tiene length > 0.
        // CULTURA: enviar bytes en vez de String evita una copia y es lo que viaja de
        // verdad por la red; lo usarás con colas/Kafka.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para serializarABytes");
    }

    /**
     * Reto Extra 6: Deserialización binaria.
     * Deserializa el DTO Cliente a partir de una matriz de bytes.
     *
     * @param bytes array de bytes con codificación UTF-8
     * @return cliente deserializado
     */
    public static Cliente deserializarDesdeBytes(byte[] bytes) {
        // GUÍA: teoría 2.2 (readValue acepta byte[] igual que String).
        // 1. Abre un try.
        // 2. return MAPPER.readValue(bytes, Cliente.class);
        // 3. Captura IOException (readValue sobre byte[] lanza IOException, más amplia que
        //    JsonProcessingException) y relánzala como RuntimeException.
        // PISTA: return MAPPER.readValue(bytes, Cliente.class);  // ojo: catch IOException
        // OJO: el test hace round-trip: serializarABytes -> deserializarDesdeBytes y espera
        // recuperar el mismo Cliente (usa equals del record).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para deserializarDesdeBytes");
    }

    /**
     * Reto Extra 7: Simulación de actualización parcial (Patch).
     * Lee propiedades parciales de un JSON y las mezcla con un objeto Cliente existente,
     * produciendo un nuevo Cliente actualizado (dado que Cliente es un Record inmutable).
     *
     * @param json      JSON con campos parciales (ej. {"nombre":"Ana María"})
     * @param existente cliente actual base
     * @return nueva instancia de Cliente con los datos fusionados
     */
    public static Cliente actualizarConMap(String json, Cliente existente) {
        // GUÍA: teoría 2.2 (convertValue para pasar Cliente <-> Map y fusionar).
        // 1. Convierte 'existente' a un Map mutable de sus campos:
        //    Map<String,Object> base = MAPPER.convertValue(existente, new TypeReference<>(){});
        //    (o convertValue(existente, Map.class) y trabaja con él).
        // 2. Lee el patch JSON como Map: MAPPER.readValue(json, Map.class).
        // 3. Vuelca el patch sobre la base: base.putAll(patch)  (las claves del patch ganan).
        // 4. Reconstruye el Cliente: MAPPER.convertValue(base, Cliente.class).
        // PISTA: el orden importa -> putAll DESPUÉS de tener la base, para que el campo
        //   del patch sobreescriba.
        // OJO: el patch del test solo trae "nombre":"Ana María"; el id (1) debe conservarse
        //   del 'existente'. Cliente es un record INMUTABLE -> no puedes "modificarlo", por
        //   eso construyes uno nuevo (patrón "wither" de la teoría 1.1).
        // CULTURA: esto es exactamente un PATCH de HTTP (actualización parcial), que verás
        //   en el bloque de controllers.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para actualizarConMap");
    }

    /**
     * Reto Extra 8: Conversión de tipos dinámicos de Jackson.
     * Convierte cualquier objeto origen a un tipo destino usando Jackson (simulando mapeo de DTOs).
     *
     * @param origen       objeto de origen (ej. un mapa o DTO compatible)
     * @param claseDestino clase a la que queremos convertirlo
     * @param <T>          tipo de retorno
     * @return objeto convertido
     */
    public static <T> T convertirEntreTipos(Object origen, Class<T> claseDestino) {
        // GUÍA: teoría 2.2 (convertValue: mapea un objeto a otro tipo SIN pasar por texto).
        // 1. Una línea: return MAPPER.convertValue(origen, claseDestino);
        // PISTA: convertValue(origen, claseDestino)
        // OJO: el test pasa un Map.of("id",123,"nombre","Luis") y espera id == 123L. El Map
        //   trae 123 como Integer, pero como Cliente.id es Long, Jackson lo convierte sin
        //   quejarse -> esa es la gracia de convertValue frente a un cast manual.
        // CULTURA: convertValue es como serializar a JSON y volver a leer, pero en memoria.
        //   Es el motor de muchos mapeos DTO<->entidad que verás en bloques de arquitectura.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para convertirEntreTipos");
    }

    /**
     * Reto Extra 9: Verificación de indentación.
     * Serializa el cliente de forma bonita y comprueba si la salida contiene saltos de línea.
     *
     * @param c cliente
     * @return true si la serialización es identada (tiene saltos de línea)
     */
    public static boolean serializarConIdentacionYComprobar(Cliente c) {
        // GUÍA: apóyate en serializarBonito del reto 1.
        // 1. Genera el JSON indentado (reutiliza serializarBonito(c) o el pretty writer).
        // 2. Comprueba si contiene un salto de línea: json.contains("\n").
        // PISTA: return serializarBonito(c).contains("\n");
        // OJO: el test espera true; un JSON con pretty-print SIEMPRE mete saltos de línea.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para serializarConIdentacionYComprobar");
    }

    /**
     * Reto Extra 10: Persistencia temporal en disco de JSON.
     * Escribe el cliente en un archivo temporal en disco y luego lo vuelve a leer para comprobar la integridad del proceso.
     *
     * @param c cliente
     * @return el cliente recuperado desde el archivo
     */
    public static Cliente escribirYLeerDeArchivoTemporal(Cliente c) {
        // GUÍA: teoría 2.2 (writeValue/readValue aceptan un File directamente).
        // 1. Crea un fichero temporal: File f = File.createTempFile("cliente", ".json");
        //    (o Files.createTempFile(...).toFile()).
        // 2. Escribe el cliente: MAPPER.writeValue(f, c).
        // 3. Vuelve a leerlo: return MAPPER.readValue(f, Cliente.class).
        // 4. Todo dentro de un try; captura IOException -> RuntimeException.
        // PISTA: File.createTempFile + MAPPER.writeValue(file, c) + MAPPER.readValue(file, Cliente.class)
        // OJO: el test hace round-trip y espera recuperar el mismo Cliente (equals).
        // Opcional: f.deleteOnExit() para no dejar basura en /tmp.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para escribirYLeerDeArchivoTemporal");
    }

}
