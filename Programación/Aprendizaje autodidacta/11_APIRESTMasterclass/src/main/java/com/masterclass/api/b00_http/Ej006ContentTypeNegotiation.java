package com.masterclass.api.b00_http;

import java.util.List;

/**
 * Ejercicio 006 · Negociación de contenido por la cabecera Accept.
 *
 * <p>Teoría: {@code teoria/00_Fundamentos_HTTP_Web.md} (sección 0.1) y bloque VI.
 */
public final class Ej006ContentTypeNegotiation {

    private Ej006ContentTypeNegotiation() {
    }

    /**
     * Elige el mejor tipo soportado a partir de la cabecera Accept.
     *
     * <p>Reglas: el cliente lista tipos separados por coma; el servidor declara
     * los que sabe producir, en orden de preferencia. Si el cliente acepta
     * {@code "*​/*"} se devuelve el primer tipo soportado.
     *
     * @param accept    valor de la cabecera Accept (p.ej. "application/xml, application/json")
     * @param supported tipos que el servidor sabe producir, en orden de preferencia
     * @return el tipo elegido, o cadena vacía si no hay ninguno compatible
     */
    public static String negotiate(String accept, List<String> supported) {
        // TODO 1: si supported es null o vacío, devuelve "" (no hay nada que ofrecer).
        // TODO 2: si accept es null o en blanco, trátalo como "*/*" (cliente no exige).
        // TODO 3: separa 'accept' por coma en una lista de tokens.
        // TODO 4: recorta espacios de cada token.
        // TODO 5: descarta los parámetros de calidad ";q=..." si vinieran (quédate con el tipo).
        // TODO 6: si algún token es exactamente "*/*", devuelve supported.get(0).
        // TODO 7: recorre 'supported' EN ORDEN (la preferencia del servidor manda).
        // TODO 8: para cada soportado, comprueba si está en la lista del cliente.
        // TODO 9: devuelve el primer soportado que el cliente acepte.
        // TODO 10: si tras recorrer todo no hay coincidencia, devuelve "".
        return "";
    }

    public static void main(String[] args) {
        System.out.println(negotiate("application/xml, application/json",
                List.of("application/json", "application/xml")));
    }
}
