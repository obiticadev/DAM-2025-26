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
}
