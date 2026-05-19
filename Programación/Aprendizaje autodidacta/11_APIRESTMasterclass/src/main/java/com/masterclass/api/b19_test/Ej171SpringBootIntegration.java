package com.masterclass.api.b19_test;

import java.util.List;

/**
 * Ejercicio 171 · {@code @SpringBootTest} e2e (flujo completo como pipeline puro).
 *
 * <p>Teoría: {@code teoria/19_Testing_APIs.md} (sección 19.8).
 *
 * <p>Un test e2e ejercita controller→service→repo. Modelamos ese flujo
 * como un pipeline puro: crear recurso y luego listarlo, verificando que
 * el efecto del primer paso es observable en el segundo, sin Spring.
 */
public final class Ej171SpringBootIntegration {

    private Ej171SpringBootIntegration() {
    }

    /**
     * Flujo e2e: aplica una secuencia de comandos sobre un estado inicial.
     *
     * <p>Comandos: {@code "ADD:<nombre>"} añade; {@code "DEL:<nombre>"}
     * elimina. Devuelve el estado final ordenado (como haría un GET tras
     * varios POST/DELETE).
     *
     * @param inicial lista inicial de recursos (no null, no mutada)
     * @param comandos secuencia de comandos (no null)
     * @return estado final ordenado ascendentemente
     * @throws IllegalArgumentException si algún argumento es null o comando malformado
     */
    public static List<String> ejecutarFlujo(List<String> inicial, List<String> comandos) {
        // TODO 1: si inicial o comandos son null -> IllegalArgumentException.
        // TODO 2: copia 'inicial' a una estructura mutable (no muta la entrada).
        // TODO 3: itera cada comando en orden (simula peticiones HTTP secuenciales).
        // TODO 4: si el comando no contiene ":" -> IllegalArgumentException (malformado).
        // TODO 5: separa en accion y argumento por el primer ":".
        // TODO 6: "ADD" -> añade el argumento al estado.
        // TODO 7: "DEL" -> elimina el argumento si existe (idempotente si no está).
        // TODO 8: acción desconocida -> IllegalArgumentException.
        // TODO 9: al final ordena el estado ascendentemente (respuesta determinista).
        // TODO 10: devuelve el estado final como List.
        return List.of();
    }

    public static void main(String[] args) {
        System.out.println(ejecutarFlujo(List.of("a"), List.of("ADD:b", "DEL:a")));
    }
}
