package com.masterclass.api.b02_json;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Ejercicio 025 · Anotaciones Jackson: @JsonProperty / @JsonIgnore.
 *
 * <p>Teoría: {@code teoria/02_JSON_y_Jackson.md} (sección 2.3).
 */
public final class Ej025JsonAnnotations {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * Usuario que en JSON debe exponer "user_name" y NUNCA la contraseña.
     */
    public static class Usuario {
        // TODO 1: anota 'nombre' con @JsonProperty("user_name") para renombrar la clave JSON.
        public String nombre;
        // TODO 2: anota 'password' con @JsonIgnore para que NUNCA se serialice.
        public String password;

        public Usuario() {
        }

        public Usuario(String nombre, String password) {
            this.nombre = nombre;
            this.password = password;
        }
    }

    private Ej025JsonAnnotations() {
    }

    /**
     * Serializa el usuario aplicando las anotaciones.
     *
     * @param u usuario
     * @return JSON con clave "user_name" y sin "password"
     */
    public static String toJson(Usuario u) {
        // TODO 3: abre try alrededor de la serialización.
        // TODO 4: usa MAPPER.writeValueAsString(u).
        // TODO 5: verifica mentalmente que la clave sea "user_name" (la da @JsonProperty).
        // TODO 6: verifica que "password" NO aparezca (lo evita @JsonIgnore).
        // TODO 7: devuelve la cadena.
        // TODO 8: captura JsonProcessingException.
        // TODO 9: relánzala como RuntimeException.
        // TODO 10: nunca devuelvas null silenciosamente ante un fallo.
        return "";
    }

    public static void main(String[] args) {
        System.out.println(toJson(new Usuario("ana", "secreto")));
    }

    /** DTO para probar @JsonAlias */
    public static class DtoConAlias {
        @com.fasterxml.jackson.annotation.JsonAlias({"nombre_completo", "full_name"})
        public String nombre;
    }

    /** DTO para probar @JsonUnwrapped */
    public static class DtoConUnwrapped {
        public int id;
        @com.fasterxml.jackson.annotation.JsonUnwrapped
        public Direccion direccion;
    }

    public static class Direccion {
        public String calle;
        public String ciudad;
    }

    /** DTO para probar @JsonValue */
    public static enum EstadoPedido {
        PENDIENTE("P"),
        ENVIADO("E");

        private final String codigo;

        EstadoPedido(String codigo) {
            this.codigo = codigo;
        }

        @com.fasterxml.jackson.annotation.JsonValue
        public String codigo() {
            return codigo;
        }
    }

    /** DTO para probar @JsonAnySetter y @JsonAnyGetter */
    public static class DtoDinamico {
        public int id;
        private java.util.Map<String, Object> extra = new java.util.HashMap<>();

        @com.fasterxml.jackson.annotation.JsonAnyGetter
        public java.util.Map<String, Object> extra() {
            return extra;
        }

        @com.fasterxml.jackson.annotation.JsonAnySetter
        public void setExtra(String clave, Object valor) {
            extra.put(clave, valor);
        }
    }

    /**
     * Reto Extra 1: Exclusión de valores nulos.
     * Serializa un objeto configurando la exclusión de propiedades con valor null en el JSON final.
     *
     * @param obj objeto a serializar
     * @return JSON sin propiedades nulas
     */
    public static String serializarExcluyendoNulos(Object obj) {
        // TODO extra: Reto Extra 1: Exclusión de valores nulos.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para serializarExcluyendoNulos");
    }

    /**
     * Reto Extra 2: Exclusión de valores vacíos.
     * Serializa un objeto configurando la exclusión de propiedades vacías (cadenas vacías, colecciones vacías) en el JSON final.
     *
     * @param obj objeto a serializar
     * @return JSON sin propiedades vacías
     */
    public static String serializarConInclusionNoVacio(Object obj) {
        // TODO extra: Reto Extra 2: Exclusión de valores vacíos.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para serializarConInclusionNoVacio");
    }

    /**
     * Reto Extra 3: Orden de propiedades.
     * Serializa el DTO forzando un orden alfabético estricto de las claves del JSON resultante.
     *
     * @param obj objeto a serializar
     * @return JSON con claves ordenadas alfabéticamente
     */
    public static String serializarConOrdenEspecifico(Object obj) {
        // TODO extra: Reto Extra 3: Orden de propiedades.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para serializarConOrdenEspecifico");
    }

    /**
     * Reto Extra 4: Formateador de fechas personalizado.
     * Serializa un objeto con fechas utilizando un módulo local de JavaTimeModule registrado en Jackson.
     *
     * @param obj objeto con campos de fecha
     * @return JSON con representación de fecha estructurada
     */
    public static String serializarConFormatoFecha(Object obj) {
        // TODO extra: Reto Extra 4: Formateador de fechas personalizado.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para serializarConFormatoFecha");
    }

    /**
     * Reto Extra 5: Compatibilidad con nombres alternativos (Alias).
     * Deserializa un JSON a una clase destino, permitiendo el uso de alias definidos por anotaciones.
     *
     * @param json  cadena JSON
     * @param clase clase de destino
     * @param <T>   tipo del resultado
     * @return objeto deserializado
     */
    public static <T> T deserializarConAlias(String json, Class<T> clase) {
        // TODO extra: Reto Extra 5: Compatibilidad con nombres alternativos (Alias).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para deserializarConAlias");
    }

    /**
     * Reto Extra 6: Aplanamiento de campos aninados (Unwrapped).
     * Deserializa un JSON donde los campos del objeto anidado están en el nivel raíz del JSON.
     *
     * @param json  cadena JSON
     * @param clase clase con campo anotado con @JsonUnwrapped
     * @param <T>   tipo de destino
     * @return objeto deserializado con su campo anidado debidamente poblado
     */
    public static <T> T deserializarConCamposUnwrapped(String json, Class<T> clase) {
        // TODO extra: Reto Extra 6: Aplanamiento de campos aninados (Unwrapped).
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para deserializarConCamposUnwrapped");
    }

    /**
     * Reto Extra 7: Serialización de valor único.
     * Serializa un objeto a un único tipo primitivo JSON (String, Number, etc.) según su anotación @JsonValue.
     *
     * @param obj objeto anotado con @JsonValue
     * @return representación en JSON del valor primitivo
     */
    public static String serializarValorUnico(Object obj) {
        // TODO extra: Reto Extra 7: Serialización de valor único.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para serializarValorUnico");
    }

    /**
     * Reto Extra 8: Inspección reflectiva de exclusión de Jackson.
     * Comprueba mediante reflexión si un campo específico de una clase posee la anotación @JsonIgnore de Jackson.
     *
     * @param clase clase a evaluar
     * @param campo nombre del campo de la clase
     * @return true si el campo posee la anotación @JsonIgnore
     */
    public static boolean esCampoIgnorado(Class<?> clase, String campo) {
        // TODO extra: Reto Extra 8: Inspección reflectiva de exclusión de Jackson.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esCampoIgnorado");
    }

    /**
     * Reto Extra 9: Deserialización de propiedades dinámicas.
     * Deserializa un JSON dinámico a una clase que recolecta atributos desconocidos en un Map interno
     * anotado con @JsonAnySetter.
     *
     * @param json  JSON con atributos dinámicos
     * @param clase clase de destino con @JsonAnySetter
     * @param <T>   tipo de retorno
     * @return objeto deserializado
     */
    public static <T> T deserializarConPropiedadesDinamicas(String json, Class<T> clase) {
        // TODO extra: Reto Extra 9: Deserialización de propiedades dinámicas.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para deserializarConPropiedadesDinamicas");
    }

    /**
     * Reto Extra 10: Serialización de propiedades dinámicas.
     * Serializa un objeto inyectando en el JSON final las propiedades dinámicas de su mapa interno anotado con @JsonAnyGetter.
     *
     * @param obj objeto con @JsonAnyGetter
     * @return JSON serializado con las propiedades del mapa expuestas en el nivel raíz
     */
    public static String serializarConPropiedadesDinamicas(Object obj) {
        // TODO extra: Reto Extra 10: Serialización de propiedades dinámicas.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para serializarConPropiedadesDinamicas");
    }

}
