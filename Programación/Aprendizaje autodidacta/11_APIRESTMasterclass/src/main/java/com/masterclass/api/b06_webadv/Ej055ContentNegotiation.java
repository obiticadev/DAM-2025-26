package com.masterclass.api.b06_webadv;

import java.util.List;

/**
 * Ejercicio 055 · Negociación de contenido (resolución de MediaType).
 *
 * <p>Teoría: {@code teoria/06_Request_Response_Avanzado.md} (sección 6.1).
 *
 * <p>Decide el MediaType de salida según Accept y lo que el endpoint produce.
 */
public final class Ej055ContentNegotiation {

    private Ej055ContentNegotiation() {
    }

    /**
     * Resuelve el tipo de respuesta. Devuelve "" si no hay representación aceptable
     * (lo que en una API real sería un 406 Not Acceptable).
     *
     * @param accept   cabecera Accept del cliente
     * @param produces tipos que el endpoint declara producir (orden = preferencia)
     * @return el MediaType elegido o "" si 406
     */
    public static String resolve(String accept, List<String> produces) {
        // TODO 1: si produces es null/vacío, no hay nada que ofrecer -> "".
        // TODO 2: si accept es null/blank, equivale a "*/*" (cliente sin preferencia).
        // TODO 3: separa accept por coma en tokens.
        // TODO 4: recorta espacios de cada token.
        // TODO 5: descarta el parámetro de calidad ";q=" si viene.
        // TODO 6: si algún token es "*/*", devuelve produces.get(0).
        // TODO 7: soporta comodín de subtipo "application/*": casa con cualquier application/x.
        // TODO 8: recorre 'produces' en orden de preferencia del servidor.
        // TODO 9: devuelve el primero que el cliente acepte (exacto o por comodín).
        // TODO 10: si nada casa, devuelve "" (caso 406 Not Acceptable).
        return "";
    }

    public static void main(String[] args) {
        System.out.println(resolve("application/*", List.of("application/json")));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: si produces es null/vacío, no hay nada que ofrecer -> "".
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: si accept es null/blank, equivale a "*/*" (cliente sin preferencia).
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: separa accept por coma en tokens.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: recorta espacios de cada token.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: descarta el parámetro de calidad ";q=" si viene.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: si algún token es "*/*", devuelve produces.get(0).
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: soporta comodín de subtipo "application/*": casa con cualquier application/x.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: recorre 'produces' en orden de preferencia del servidor.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: devuelve el primero que el cliente acepte (exacto o por comodín).
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: si nada casa, devuelve "" (caso 406 Not Acceptable).
    }

}
