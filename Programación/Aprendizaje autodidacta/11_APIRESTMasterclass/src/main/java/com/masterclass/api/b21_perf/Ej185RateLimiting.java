package com.masterclass.api.b21_perf;

/**
 * Ejercicio 185 · Rate limiting (token bucket).
 *
 * <p>Teoria: {@code teoria/21_Rendimiento_Resiliencia.md} (seccion 21.3).
 *
 * <p>Limitar peticiones protege la API de abusos. Modelamos un token-bucket
 * como funcion PURA: dado el estado (tokens disponibles, ultimo instante de
 * recarga) y el instante actual, decide si la peticion se permite.
 */
public final class Ej185RateLimiting {

    private Ej185RateLimiting() {
    }

    /**
     * Decide si una peticion en el instante {@code ahora} se permite con un token-bucket.
     *
     * <p>El estado es {@code estado[0]} = tokens disponibles y
     * {@code estado[1]} = instante (ms) de la ultima recarga; ambos se actualizan in-place.
     *
     * @param estado     array de 2 longs: [tokensDisponibles, ultimaRecargaMs] (no null)
     * @param capacidad  capacidad maxima del bucket (&gt; 0)
     * @param refillMs   cada cuantos ms se anade 1 token (&gt; 0)
     * @param ahora      instante actual en ms (&gt;= estado[1])
     * @return true si habia token y la peticion se acepta; false si se rechaza (429)
     * @throws IllegalArgumentException si estado no tiene tamano 2, capacidad/refillMs &lt;=0
     *                                  o ahora &lt; estado[1]
     */
    public static boolean permitido(long[] estado, long capacidad, long refillMs, long ahora) {
        // TODO 1: si estado es null o estado.length != 2 -> IllegalArgumentException.
        // TODO 2: si capacidad <= 0 o refillMs <= 0 -> IllegalArgumentException.
        // TODO 3: si ahora < estado[1] (reloj hacia atras) -> IllegalArgumentException.
        // TODO 4: calcula transcurrido = ahora - estado[1] (ms desde ultima recarga).
        // TODO 5: tokensARecargar = transcurrido / refillMs (division entera).
        // TODO 6: si tokensARecargar > 0, suma esos tokens limitando a 'capacidad' (cap).
        // TODO 7: avanza estado[1] solo por los ms consumidos: + tokensARecargar*refillMs.
        // TODO 8: si tokens disponibles >= 1, consume uno (resta 1) y la peticion se permite.
        // TODO 9: si no hay tokens, NO restes nada y rechaza la peticion.
        // TODO 10: actualiza estado[0] y devuelve true (permitida) o false (rechazada).
        return false;
    }

    public static void main(String[] args) {
        long[] est = {2, 0};
        System.out.println(permitido(est, 5, 1000, 0));
    }
}
