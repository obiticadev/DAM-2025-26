package com.masterclass.api.b19_test;

/**
 * Ejercicio 172 · Testcontainers/Postgres (config de contenedor como valor puro).
 *
 * <p>Teoría: {@code teoria/19_Testing_APIs.md} (sección 19.9).
 *
 * <p>Testcontainers levantaría un Postgres real efímero. Aquí modelamos
 * la decisión PURA que precede a eso: construir la JDBC URL del contenedor
 * y validar la imagen, sin arrancar Docker.
 */
public final class Ej172TestcontainersPostgres {

    private Ej172TestcontainersPostgres() {
    }

    /**
     * Construye la JDBC URL que Testcontainers expondría para Postgres.
     *
     * @param host     host mapeado (no null/blank)
     * @param puerto   puerto mapeado en el host (1..65535)
     * @param baseDatos nombre de la BD (no null/blank)
     * @return URL "jdbc:postgresql://host:puerto/baseDatos"
     * @throws IllegalArgumentException si algún argumento es inválido
     */
    public static String jdbcUrl(String host, int puerto, String baseDatos) {
        // TODO 1: si host es null o blank -> IllegalArgumentException.
        // TODO 2: si puerto < 1 o > 65535 -> IllegalArgumentException.
        // TODO 3: si baseDatos es null o blank -> IllegalArgumentException.
        // TODO 4: el esquema fijo es "jdbc:postgresql://".
        // TODO 5: concatena host + ":" + puerto.
        // TODO 6: añade "/" + baseDatos.
        // TODO 7: no añadas query params por defecto (mantén la URL mínima).
        // TODO 8: la URL es determinista para los mismos inputs.
        // TODO 9: no normalices el host a minúsculas (puede ser una IP exacta).
        // TODO 10: devuelve la URL completa.
        return null;
    }

    /**
     * Valida que la imagen Docker solicitada sea un Postgres soportado.
     *
     * @param imagen imagen Docker, p.ej. "postgres:16-alpine" (no null)
     * @return true si empieza por "postgres:" y declara un tag
     * @throws IllegalArgumentException si imagen es null/blank
     */
    public static boolean imagenValida(String imagen) {
        // TODO 1: si imagen es null o blank -> IllegalArgumentException.
        // TODO 2: la imagen debe comenzar por "postgres:" (familia soportada).
        // TODO 3: debe contener exactamente un ":" separando repo y tag.
        // TODO 4: el tag (tras ":") no puede estar vacío (evita "latest" implícito).
        // TODO 5: rechaza "postgres" sin tag (reproducibilidad del test).
        // TODO 6: no aceptes imágenes de otras familias (mysql:, mariadb:...).
        // TODO 7: la comprobación es puramente sintáctica (no contacta un registry).
        // TODO 8: no muta la cadena de entrada.
        // TODO 9: devuelve false (no excepción) si la familia no casa.
        // TODO 10: devuelve el booleano de validez.
        return false;
    }

    public static void main(String[] args) {
        System.out.println(jdbcUrl("localhost", 54321, "test"));
        System.out.println(imagenValida("postgres:16-alpine"));
    }
}
