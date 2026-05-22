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
        // TODO extra: Reto Extra 1: Serialización estética (Pretty Printing).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
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
        // TODO extra: Reto Extra 2: Deserialización genérica tipo-segura.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
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
        // TODO extra: Reto Extra 3: Validación estructural JSON.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
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
        // TODO extra: Reto Extra 4: Tolerancia a propiedades desconocidas.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
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
        // TODO extra: Reto Extra 5: Serialización binaria.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
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
        // TODO extra: Reto Extra 6: Deserialización binaria.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
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
        // TODO extra: Reto Extra 7: Simulación de actualización parcial (Patch).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
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
        // TODO extra: Reto Extra 8: Conversión de tipos dinámicos de Jackson.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
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
        // TODO extra: Reto Extra 9: Verificación de indentación.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
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
        // TODO extra: Reto Extra 10: Persistencia temporal en disco de JSON.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para escribirYLeerDeArchivoTemporal");
    }

}
