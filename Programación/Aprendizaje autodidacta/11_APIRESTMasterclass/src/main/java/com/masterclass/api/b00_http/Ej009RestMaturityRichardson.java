package com.masterclass.api.b00_http;

/**
 * Ejercicio 009 · Modelo de madurez de Richardson.
 *
 * <p>Teoría: {@code teoria/00_Fundamentos_HTTP_Web.md} (sección 0.5).
 */
public final class Ej009RestMaturityRichardson {

    private Ej009RestMaturityRichardson() {
    }

    /**
     * Determina el nivel de madurez REST (0–3) de un diseño.
     *
     * <p>Heurística simplificada del bootcamp:
     * <ul>
     *   <li>Nivel 0: un único endpoint para todo (sin recursos).</li>
     *   <li>Nivel 1: múltiples recursos con URI, pero un solo verbo.</li>
     *   <li>Nivel 2: recursos + varios verbos HTTP + códigos de estado.</li>
     *   <li>Nivel 3: además expone enlaces de hipermedia (HATEOAS).</li>
     * </ul>
     *
     * @param multipleResources true si el diseño usa URIs por recurso
     * @param multipleVerbs     true si usa varios verbos HTTP correctamente
     * @param hypermedia        true si las respuestas incluyen enlaces
     * @return nivel entero de 0 a 3
     */
    public static int level(boolean multipleResources, boolean multipleVerbs, boolean hypermedia) {
        // TODO 1: el nivel es acumulativo: cada peldaño exige cumplir el anterior.
        // TODO 2: si NO hay múltiples recursos, el diseño es RPC plano -> nivel 0.
        // TODO 3: a partir de aquí ya hay recursos (al menos nivel 1).
        // TODO 4: si hay recursos pero NO múltiples verbos -> nivel 1.
        // TODO 5: comprobado que hay verbos, se alcanza al menos nivel 2.
        // TODO 6: si hay recursos + verbos pero NO hipermedia -> nivel 2.
        // TODO 7: la hipermedia (HATEOAS) solo cuenta si ya hay recursos y verbos.
        // TODO 8: si se cumplen los tres requisitos -> nivel 3.
        // TODO 9: no permitas "saltar" niveles (hipermedia sin verbos NO es nivel 3).
        // TODO 10: devuelve el entero calculado (0, 1, 2 o 3).
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("RPC plano      -> " + level(false, false, false));
        System.out.println("REST realista  -> " + level(true, true, false));
        System.out.println("HATEOAS        -> " + level(true, true, true));
    }
}
