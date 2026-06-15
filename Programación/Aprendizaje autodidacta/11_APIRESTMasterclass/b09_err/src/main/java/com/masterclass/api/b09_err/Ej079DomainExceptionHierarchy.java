package com.masterclass.api.b09_err;

/**
 * Ejercicio 079 · Jerarquía de excepciones de dominio → status HTTP.
 *
 * <p>Teoría: {@code teoria/09_Manejo_de_Errores.md} (sección 9.3).
 */
public final class Ej079DomainExceptionHierarchy {

    /** Raíz de las excepciones de negocio. */
    public static class DominioException extends RuntimeException {
        public DominioException(String m) {
            super(m);
        }
    }

    public static class NoEncontradoException extends DominioException {
        public NoEncontradoException(String m) {
            super(m);
        }
    }

    public static class ConflictoException extends DominioException {
        public ConflictoException(String m) {
            super(m);
        }
    }

    public static class NoAutorizadoException extends DominioException {
        public NoAutorizadoException(String m) {
            super(m);
        }
    }

    private Ej079DomainExceptionHierarchy() {
    }

    /**
     * Mapea una excepción de dominio a su código HTTP.
     *
     * @param ex excepción lanzada por la capa de negocio
     * @return el status HTTP correspondiente
     */
    public static int aStatus(DominioException ex) {
        // TODO 1: si ex es null -> IllegalArgumentException.
        // TODO 2: usa instanceof / switch de patrones sobre el tipo concreto.
        // TODO 3: NoEncontradoException -> 404.
        // TODO 4: ConflictoException -> 409.
        // TODO 5: NoAutorizadoException -> 403.
        // TODO 6: cualquier otra DominioException (genérica) -> 400.
        // TODO 7: comprueba los subtipos ANTES que la clase base (orden importa).
        // TODO 8: no devuelvas 500 para errores de dominio (500 es fallo del servidor).
        // TODO 9: el mapeo debe ser exhaustivo para los 3 subtipos + base.
        // TODO 10: devuelve el entero del status.
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(aStatus(new ConflictoException("dup")));
    }

        /**
     * RETO EXTRA 01: Verifica si es una excepcion por conflicto en el estado actual.
     */
    public static boolean esConflictoDatos(Throwable t) {
        // GUÍA: detecta un conflicto de estado (el 409 de 9.3/9.5).
        // 1. null o sin mensaje → false.
        // 2. El test manda new RuntimeException("conflict"): NO es nuestra
        //    ConflictoException, así que el criterio es por MENSAJE, no por tipo.
        //    PISTA: return t != null && t.getMessage() != null
        //                  && t.getMessage().toLowerCase().contains("conflict");
        // OJO: no uses instanceof ConflictoException aquí — fallaría el test.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esConflictoDatos");
    }

    /**
     * RETO EXTRA 02: Resuelve si el cliente acepta el JSON estructurado de error.
     */
    public static String obtenerTipoMimeError(String acceptHeader) {
        // GUÍA: el content-type oficial de los ProblemDetail (RFC 7807, 9.2) es
        // "application/problem+json".
        // PISTA: si el cliente acepta JSON, devuélvelo:
        //    return "application/problem+json";
        // OJO: el test pasa "application/json" y espera EXACTAMENTE
        // "application/problem+json" (con equals). Aquí basta devolverlo siempre;
        // un diseño más fino comprobaría que acceptHeader contiene "json".
        // CULTURA: ese +json es lo que distingue un error estándar de un JSON
        // cualquiera para los clientes que entienden el RFC.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerTipoMimeError");
    }

    /**
     * RETO EXTRA 03: Determina si la excepcion compromete accesos (credenciales, permisos).
     */
    public static boolean esExcepcionDeSeguridad(Throwable t) {
        // GUÍA: detecta una excepción de seguridad (el 401/403 de 9.3).
        // 1. null → false.
        // 2. La marca de Java es java.lang.SecurityException:
        //       return t instanceof SecurityException;
        // OJO: el test manda new SecurityException() → true. También entrarían
        // aquí NoAutorizadoException (nuestra) o las de Spring Security (b18).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esExcepcionDeSeguridad");
    }

    /**
     * RETO EXTRA 04: Genera el objeto de contingencia de error de infraestructura.
     */
    public static String crearRespuestaErrorInterno(String msg) {
        // GUÍA: genera un cuerpo de error 500 que incluya el mensaje recibido.
        // PISTA: return "{\"error\":\"" + msg + "\"}"; (o cualquier formato que
        //    contenga msg).
        // OJO: el test solo exige que el resultado .contains("db") cuando msg="db";
        // el formato exacto es tuyo. (Defensa: si msg es null, usa "error interno".)
        // CULTURA: este es el cuerpo genérico de un 500 — nunca lleva detalles
        // técnicos del fallo real (eso va al log con su traceId, 9.6).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearRespuestaErrorInterno");
    }

    /**
     * RETO EXTRA 05: Determina si detiene procesos transaccionales importantes.
     */
    public static boolean esFalloNegocioCritico(Throwable t) {
        // GUÍA: detecta un fallo de negocio que corta una transacción.
        // 1. null → false.
        // 2. El test manda new IllegalStateException("Stock vacio"). Lo más
        //    robusto que lo cubre: es una IllegalStateException (estado inválido
        //    para la operación).
        //    PISTA: return t instanceof IllegalStateException;
        // OJO: el test espera true; no filtres por el texto "Stock".
        // CULTURA: un estado inválido (stock 0, pedido ya cerrado) suele mapear a
        // 409 Conflict (9.5), no a 500: es el cliente intentando algo imposible.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esFalloNegocioCritico");
    }

    /**
     * RETO EXTRA 06: Extrae las primeras palabras descriptivas del fallo.
     */
    public static String extraerMensajeCorto(Throwable t) {
        // GUÍA: devuelve solo la PRIMERA palabra del mensaje.
        // 1. null o mensaje null/vacío → devuelve "".
        // 2. Parte el mensaje por espacios y coge el primer trozo:
        //       return t.getMessage().split(" ")[0];
        // PISTA: split(" ") basta; el primer elemento es la primera palabra.
        // OJO: el test pasa "Fallo de conexion de datos" y espera EXACTAMENTE
        // "Fallo" (la primera palabra, sin más).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extraerMensajeCorto");
    }

    /**
     * RETO EXTRA 07: Verifica si corresponde a un fallo de validacion del modelo.
     */
    public static boolean esErrorValidacionEntidad(Throwable t) {
        // GUÍA: detecta un error de validación (el 400 de 9.3/9.4).
        // 1. null → false.
        // 2. El test manda new IllegalArgumentException("validar"). La marca
        //    estándar de "argumento inválido" es IllegalArgumentException:
        //       return t instanceof IllegalArgumentException;
        // OJO: en Bean Validation (b08) el tipo real es ConstraintViolationException,
        // pero aquí el test usa IllegalArgumentException → filtra por ese tipo.
        // CUIDADO: este criterio solapa con esErrorDeNegocioPuro (reto 9), que
        // usa el MISMO tipo; cada test mira un método distinto, no pasa nada.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esErrorValidacionEntidad");
    }

    /**
     * RETO EXTRA 08: Genera una firma hash unica para indexar logs.
     */
    public static String generarHashError(String traceId, Throwable t) {
        // GUÍA: produce una firma estable para indexar el error en los logs.
        // 1. Combina traceId + el tipo de la excepción y haz un hash:
        //       String base = traceId + ":" + t.getClass().getName();
        //       return Integer.toHexString(base.hashCode());
        // OJO: el test solo exige assertNotNull(...) — no fija el valor. Lo único
        // imprescindible es devolver algo no-null (cuida el caso t/traceId null).
        // CULTURA: agrupar errores "iguales" por firma evita 10.000 alertas del
        // mismo fallo; es lo que hacen Sentry/Datadog (observabilidad, b20).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para generarHashError");
    }

    /**
     * RETO EXTRA 09: Comprueba si es una excepcion de dominio propia.
     */
    public static boolean esErrorDeNegocioPuro(Throwable t) {
        // GUÍA: detecta un error de negocio "puro" (no técnico, no de la JVM).
        // 1. null → false.
        // 2. El test manda new IllegalArgumentException() → true. Criterio que lo
        //    cubre: es una RuntimeException pero NO un Error de la JVM.
        //    PISTA: return t instanceof RuntimeException;
        //    (o, más estricto: t instanceof DominioException — pero el test usa
        //     IllegalArgumentException, así que filtra por RuntimeException.)
        // OJO: no devuelvas true para un OutOfMemoryError (eso es fatal, reto 10).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esErrorDeNegocioPuro");
    }

    /**
     * RETO EXTRA 10: Filtra si es un error fatal de memoria o virtual machine.
     */
    public static boolean esExcepcionFatal(Throwable t) {
        // GUÍA: detecta un fallo fatal de la JVM (no recuperable).
        // 1. null → false.
        // 2. OutOfMemoryError, StackOverflowError... todos extienden
        //    java.lang.Error (o el más específico VirtualMachineError):
        //       return t instanceof Error;
        // OJO: el test manda new OutOfMemoryError() → true. NO captures estos en
        // un catch normal: con la memoria agotada no puedes ni construir la
        // respuesta. Por eso el fallback de 9.8 atrapa RuntimeException, no Error.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esExcepcionFatal");
    }

}