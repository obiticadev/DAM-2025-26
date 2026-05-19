package com.masterclass.api.b00_http;

/**
 * Ejercicio 010 · Statelessness y caché con ETag.
 *
 * <p>Teoría: {@code teoria/00_Fundamentos_HTTP_Web.md} (sección 0.6).
 */
public final class Ej010StatelessAndCache {

    private Ej010StatelessAndCache() {
    }

    /**
     * Calcula un ETag determinista a partir del contenido del recurso.
     *
     * @param content representación serializada del recurso
     * @return ETag entre comillas dobles, p.ej. {@code "\"a1b2\""}; nunca null
     */
    public static String etag(String content) {
        // TODO 1: si content es null, trátalo como cadena vacía (no lances NPE).
        // TODO 2: deriva una huella estable del contenido (p.ej. content.hashCode()).
        // TODO 3: la huella debe ser DETERMINISTA: mismo content -> mismo valor siempre.
        // TODO 4: conviértela a una representación compacta (p.ej. hex).
        // TODO 5: envuélvela entre comillas dobles según el formato HTTP de ETag.
        // TODO 6: garantiza que dos contenidos distintos den ETags distintos (en la práctica).
        return "";
    }

    /**
     * Decide el código de respuesta para una petición condicional GET.
     *
     * @param currentContent contenido actual del recurso en el servidor
     * @param ifNoneMatch    valor de la cabecera If-None-Match enviada por el cliente (puede ser null)
     * @return 304 si el ETag coincide (no ha cambiado); 200 en caso contrario
     */
    public static int conditionalGetStatus(String currentContent, String ifNoneMatch) {
        // TODO 7: calcula el ETag actual del recurso con etag(currentContent).
        // TODO 8: si ifNoneMatch es null, el cliente no tiene copia -> 200.
        // TODO 9: si ifNoneMatch es igual al ETag actual, el recurso no cambió -> 304.
        // TODO 10: en cualquier otro caso (ETag distinto) -> 200 con el recurso nuevo.
        return 0;
    }

    public static void main(String[] args) {
        String c = "{\"id\":1}";
        String tag = etag(c);
        System.out.println("ETag=" + tag);
        System.out.println("Repetido -> " + conditionalGetStatus(c, tag));
        System.out.println("Cambiado -> " + conditionalGetStatus("{\"id\":2}", tag));
    }
}
