package com.masterclass.api.b20_obs;

import java.util.Map;

/**
 * Ejercicio 179 · Endpoints de Actuator (health agregado).
 *
 * <p>Teoria: {@code teoria/20_Observabilidad_Documentacion.md} (seccion 20.3).
 *
 * <p>Spring Boot Actuator expone {@code /actuator/health} agregando el estado
 * de varios componentes (db, disk, ping...). Aqui replicamos esa logica de
 * agregacion como funcion pura: el estado global es UP solo si todos los
 * componentes estan UP; en caso contrario DOWN.
 */
public final class Ej179ActuatorEndpoints {

    private Ej179ActuatorEndpoints() {
    }

    /**
     * Calcula el estado de salud agregado a partir de los componentes.
     *
     * @param componentes mapa nombre-&gt;estado ("UP"/"DOWN"); no null
     * @return "UP" si todos UP y hay al menos uno; "DOWN" en otro caso
     * @throws IllegalArgumentException si componentes es null o algun valor nulo
     */
    public static String estadoAgregado(Map<String, String> componentes) {
        // TODO 1: si componentes es null -> IllegalArgumentException.
        // TODO 2: si el mapa esta vacio, devuelve "UNKNOWN" (no hay datos).
        // TODO 3: recorre todos los valores del mapa.
        // TODO 4: si algun valor es null -> IllegalArgumentException.
        // TODO 5: normaliza cada estado a mayusculas y sin espacios (trim).
        // TODO 6: considera UP solo el literal exacto "UP" tras normalizar.
        // TODO 7: si encuentras cualquier estado distinto de "UP" -> resultado "DOWN".
        // TODO 8: el agregado es estricto: un solo DOWN tumba el conjunto.
        // TODO 9: si todos los componentes son "UP" -> resultado "UP".
        // TODO 10: devuelve el estado agregado calculado.
        return "UNKNOWN";
    }

    public static void main(String[] args) {
        System.out.println(estadoAgregado(Map.of("db", "UP", "disk", "UP")));
    }
}
