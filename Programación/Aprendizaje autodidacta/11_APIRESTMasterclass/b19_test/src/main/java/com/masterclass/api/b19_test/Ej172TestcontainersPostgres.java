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

        /**
     * RETO EXTRA 01: Extrae el host de la URL JDBC.
     */
    public static String obtenerHostJdbc(String url) {
        // GUÍA: teoría 19.9 — operación inversa a jdbcUrl: parsea la URL.
        // 1. Quita el esquema fijo:
        //       String resto = url.substring("jdbc:postgresql://".length()); // "localhost:5432/db"
        // 2. El host es lo que va antes del primer ":":
        //       return resto.split(":")[0];
        // El test ("jdbc:postgresql://localhost:5432/db") espera "localhost".
        // PISTA: reutiliza este "resto" mentalmente en los retos 2 y 3.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerHostJdbc");
    }

    /**
     * RETO EXTRA 02: Extrae el puerto de la URL JDBC.
     */
    public static int obtenerPuertoJdbc(String url) {
        // GUÍA: teoría 19.9 — el puerto está entre el ":" y el "/".
        // String resto = url.substring("jdbc:postgresql://".length()); // "localhost:5432/db"
        // String puerto = resto.split(":")[1].split("/")[0];           // "5432"
        // return Integer.parseInt(puerto);
        // El test espera 5432 (int). OJO: hay que parsear a int (Integer.parseInt);
        // el split deja un String, y el test compara con un entero.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerPuertoJdbc");
    }

    /**
     * RETO EXTRA 03: Extrae la BD de la URL JDBC.
     */
    public static String obtenerBaseDatosJdbc(String url) {
        // GUÍA: teoría 19.9 — la BD es lo que va tras el último "/".
        // return url.substring(url.lastIndexOf('/') + 1);
        // El test ("...localhost:5432/db") espera "db". PISTA: lastIndexOf('/')
        // localiza la última barra; +1 salta sobre ella hasta el inicio del
        // nombre de BD.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerBaseDatosJdbc");
    }

    /**
     * RETO EXTRA 04: Comprueba si es host local.
     */
    public static boolean esHostLocal(String host) {
        // GUÍA: teoría 19.9 — local = "localhost" o la IP de loopback.
        // return "localhost".equals(host) || "127.0.0.1".equals(host);
        // El test ("localhost") espera true. Cubre las dos formas de referirse a
        // la propia máquina (Testcontainers mapea sus puertos a localhost).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esHostLocal");
    }

    /**
     * RETO EXTRA 05: Valida el rango del puerto.
     */
    public static boolean esPuertoValido(int port) {
        // GUÍA: teoría 19.9 — rango TCP válido [1, 65535].
        // return port >= 1 && port <= 65535;
        // El test (8080) espera true. Es la misma validación que jdbcUrl aplica
        // al puerto antes de construir la URL.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esPuertoValido");
    }

    /**
     * RETO EXTRA 06: Extrae el tag de una imagen.
     */
    public static String extraerTagImagen(String img) {
        // GUÍA: teoría 19.9 — el tag va tras el ":".
        // return img.split(":")[1];
        // El test ("postgres:16") espera "16". (Una imagen sin ":" no tendría
        // índice [1]; aquí el test siempre da una con tag, por reproducibilidad.)
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extraerTagImagen");
    }

    /**
     * RETO EXTRA 07: Valida si es imagen Postgres.
     */
    public static boolean esImagenPostgres(String img) {
        // GUÍA: teoría 19.9 — familia Postgres = empieza por "postgres:".
        // return img.startsWith("postgres:");
        // El test ("postgres:16") espera true. Es la primera comprobación de
        // imagenValida (familia correcta); con el ":" exiges que tenga tag.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esImagenPostgres");
    }

    /**
     * RETO EXTRA 08: Comprueba si es variante Alpine.
     */
    public static boolean esImagenAlpine(String img) {
        // GUÍA: teoría 19.9 — Alpine = el tag termina en "-alpine".
        // return img.endsWith("-alpine");
        // El test ("postgres:16-alpine") espera true. CULTURA: las variantes
        // -alpine usan una distro mínima → imágenes mucho más ligeras, ideales
        // para tests rápidos en CI.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esImagenAlpine");
    }

    /**
     * RETO EXTRA 09: Construye URL minima sin BD.
     */
    public static String construirUrlMinima(String host, int port) {
        // GUÍA: teoría 19.9 — como jdbcUrl pero SIN la parte "/baseDatos".
        // return "jdbc:postgresql://" + host + ":" + port;
        // El test ("localhost", 5432) espera "jdbc:postgresql://localhost:5432".
        // OJO: no añadas "/" al final; el test compara la cadena exacta.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para construirUrlMinima");
    }

    /**
     * RETO EXTRA 10: Formatea comando de ejecucion docker.
     */
    public static String formatearDockerCommand(String img) {
        // GUÍA: teoría 19.9 — una línea: return "docker run -d " + img;
        // El test ("postgres:16") espera "docker run -d postgres:16" (un solo
        // espacio antes de la imagen). -d = modo detached (en segundo plano),
        // como haría Testcontainers por debajo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearDockerCommand");
    }

}
