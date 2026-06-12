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
        // GUÍA: teoría 2.3 (@JsonInclude(NON_NULL), configurado a nivel de mapper).
        // 1. Crea un mapper y dile que omita los campos null:
        //    new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL).
        // 2. Serializa obj con writeValueAsString dentro de un try.
        // 3. Captura JsonProcessingException -> RuntimeException.
        // PISTA: import com.fasterxml.jackson.annotation.JsonInclude;
        //        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // OJO: el test pasa Usuario("ana", null) y comprueba que NO aparece "password".
        // CULTURA: configurar NON_NULL en el mapper aplica a TODOS los campos, sin tener
        // que anotar cada uno; Spring lo expone como spring.jackson.default-property-inclusion.
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
        // GUÍA: teoría 2.3 (NON_EMPTY: omite null Y vacíos -> "", listas/maps vacíos).
        // 1. Igual que el reto 1 pero con JsonInclude.Include.NON_EMPTY.
        // 2. setSerializationInclusion(JsonInclude.Include.NON_EMPTY) + writeValueAsString.
        // PISTA: mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        // OJO: el test envuelve una List<String> VACÍA en el campo "items" y espera que
        // "items" NO aparezca. NON_NULL no bastaría (la lista no es null, está vacía):
        // necesitas NON_EMPTY, que es justo la diferencia que pregunta la autoevaluación.
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
        // GUÍA: teoría 2.3 (ordenar claves alfabéticamente vía MapperFeature).
        // 1. Crea un mapper y activa el orden alfabético:
        //    mapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true).
        // 2. Serializa obj con writeValueAsString.
        // PISTA: import com.fasterxml.jackson.databind.MapperFeature;
        //        mapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
        // OJO: el wrapper del test declara los campos en orden z, a; el test comprueba que
        // en el JSON "a" aparece ANTES que "z" (indexOf("a") < indexOf("z")) -> sin orden
        // alfabético saldrían en orden de declaración y fallaría.
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
        // GUÍA: teoría 2.3 (las fechas necesitan JavaTimeModule, ver la nota del recuadro).
        // 1. Crea el mapper y registra el módulo de java.time:
        //    mapper.registerModule(new JavaTimeModule());  (o mapper.findAndRegisterModules()).
        // 2. Desactiva los timestamps para que la fecha salga como texto ISO, no como array:
        //    mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).
        // 3. Serializa obj con writeValueAsString.
        // PISTA: import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
        //        import com.fasterxml.jackson.databind.SerializationFeature;
        // OJO: el test usa LocalDate.of(2026,5,21) y espera que el JSON contenga "2026-05-21".
        // SIN desactivar WRITE_DATES_AS_TIMESTAMPS, Jackson escribiría [2026,5,21] (array) y
        // el contains fallaría. Ese es el error nº 3 de la tabla del bloque.
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
        // GUÍA: teoría 2.3 (@JsonAlias: el DTO ya trae la anotación, tú solo deserializas).
        // 1. Una línea dentro de try: return MAPPER.readValue(json, clase);
        // PISTA: el trabajo lo hace la anotación @JsonAlias({"nombre_completo","full_name"})
        //   que YA está en DtoConAlias.nombre; readValue normal la respeta.
        // OJO: el test manda {"full_name":"Ana María"} y espera que aterrice en el campo
        //   'nombre'. No tienes que hacer nada especial: @JsonAlias se aplica AL LEER.
        //   (Contrasta con @JsonProperty, que renombra tanto al leer como al escribir.)
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
        // GUÍA: teoría 2.3 (@JsonUnwrapped: el DTO ya lo declara, tú solo deserializas).
        // 1. Una línea dentro de try: return MAPPER.readValue(json, clase);
        // PISTA: DtoConUnwrapped.direccion lleva @JsonUnwrapped, así que Jackson sabe que
        //   "calle" y "ciudad" del nivel raíz pertenecen al objeto anidado direccion.
        // OJO: el test manda {"id":123,"calle":"Mayor 12","ciudad":"Madrid"} (todo PLANO) y
        //   espera que dto.direccion NO sea null y dto.direccion.calle == "Mayor 12".
        //   @JsonUnwrapped hace lo contrario que la anidación normal: aplana en vez de anidar.
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
        // GUÍA: teoría 2.3 (@JsonValue: el objeto se serializa COMO ese único valor).
        // 1. Una línea dentro de try: return MAPPER.writeValueAsString(obj);
        // PISTA: EstadoPedido.codigo() lleva @JsonValue, así que Jackson serializa el enum
        //   como su código en vez de como objeto o nombre de constante.
        // OJO: el test pasa EstadoPedido.ENVIADO y espera EXACTAMENTE "\"E\"" (la cadena E
        //   entre comillas, no "ENVIADO" ni un objeto). El método codigo() devuelve "E".
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
        // GUÍA: reflexión Java (no Jackson en sí: leer una anotación de un campo).
        // 1. Obtén el campo por nombre: clase.getDeclaredField(campo).
        // 2. Pregunta si tiene la anotación: campo.isAnnotationPresent(JsonIgnore.class).
        // 3. getDeclaredField lanza NoSuchFieldException (checked): try/catch -> en el
        //    catch devuelve false (campo inexistente = no ignorado).
        // PISTA: import com.fasterxml.jackson.annotation.JsonIgnore;
        //   return clase.getDeclaredField(campo).isAnnotationPresent(JsonIgnore.class);
        // OJO: el test espera true para "password" y false para "nombre". Esto SOLO funciona
        //   si en el ejercicio base hiciste el TODO 2 (poner @JsonIgnore sobre password). Si
        //   aún no lo has anotado, este reto dará false en password -> hazlo primero.
        // CULTURA: leer anotaciones por reflexión es exactamente cómo Spring descubre tus
        //   @GetMapping, @Autowired, etc. al arrancar.
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
        // GUÍA: teoría 2.3 (@JsonAnySetter: recoge claves no declaradas en un Map).
        // 1. Una línea dentro de try: return MAPPER.readValue(json, clase);
        // PISTA: DtoDinamico ya tiene setExtra anotado con @JsonAnySetter; readValue normal
        //   manda a ese setter cualquier campo que no encaje en una propiedad declarada.
        // OJO: el test manda {"id":1,"campo_extra":"valor_extra"}: "id" va al campo id, y
        //   "campo_extra" no existe como campo -> cae en el Map extra. Espera
        //   dinamico.extra().get("campo_extra") == "valor_extra".
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
        // GUÍA: teoría 2.3 (@JsonAnyGetter: vuelca el Map interno al nivel raíz del JSON).
        // 1. Una línea dentro de try: return MAPPER.writeValueAsString(obj);
        // PISTA: DtoDinamico.extra() lleva @JsonAnyGetter; writeValueAsString normal
        //   "aplana" las entradas del Map como si fueran propiedades del objeto.
        // OJO: el test pone id=1 y extra "color"->"rojo", y espera que el JSON contenga
        //   "color":"rojo" Y "id":1 al MISMO nivel (no anidados bajo "extra").
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para serializarConPropiedadesDinamicas");
    }

}
