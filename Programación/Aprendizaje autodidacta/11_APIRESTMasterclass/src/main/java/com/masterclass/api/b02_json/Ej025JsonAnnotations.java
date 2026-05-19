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
}
