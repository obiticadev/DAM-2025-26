package com.masterclass.api.b09_err;

import org.springframework.http.ResponseEntity;

/**
 * Ejercicio 077 · @RestControllerAdvice global.
 *
 * <p>Teoría: {@code teoria/09_Manejo_de_Errores.md} (sección 9.1).
 *
 * <p>El test usa MockMvc standalone con este controller + su advice y comprueba
 * que una excepción de negocio se traduce a 404 (no a 500).
 */
// TODO 1: anota la clase con @RestController y @RequestMapping("/api").
public class Ej077GlobalExceptionHandler {

    public static class RecursoNoEncontrado extends RuntimeException {
        public RecursoNoEncontrado(String m) {
            super(m);
        }
    }

    /**
     * @param id id del recurso
     * @return el recurso o lanza si id no existe (id par = no existe, para el test)
     */
    // TODO 2: anota con @GetMapping("/recurso/{id}") y 'id' con @PathVariable.
    public String obtener(long id) {
        // TODO 3: si id es par, lanza RecursoNoEncontrado("no existe: " + id).
        // TODO 4: si es impar, devuelve "recurso-" + id.
        // TODO 5: NO captures la excepción aquí (que la gestione el advice).
        return "";
    }

    /**
     * Manejador centralizado.
     *
     * @param ex excepción de negocio
     * @return 404 con el mensaje
     */
    // TODO 6: anota con @ExceptionHandler(RecursoNoEncontrado.class).
    public ResponseEntity<String> manejarNoEncontrado(RecursoNoEncontrado ex) {
        // TODO 7: usa ResponseEntity.status(HttpStatus.NOT_FOUND) (404).
        // TODO 8: el cuerpo debe ser ex.getMessage().
        // TODO 9: este handler aplica a TODOS los endpoints del controller (centralizado).
        // TODO 10: devuelve la ResponseEntity 404.
        return null;
    }

    public static void main(String[] args) {
        System.out.println(new Ej077GlobalExceptionHandler().obtener(3));
    }

        /**
     * RETO EXTRA 01: Determina si una excepcion es de negocio y no de Spring o Java standard.
     */
    public static boolean esExcepcionDeNegocio(Throwable t) {
        // GUÍA: teoría 9.3 (qué es un error "de negocio" / de dominio).
        // 1. null → false (defensa: un Throwable nulo no es de negocio).
        // 2. "De negocio" aquí = una de NUESTRAS excepciones de dominio, no una
        //    de Java/Spring. La única que conoce este fichero es RecursoNoEncontrado.
        // 3. return t instanceof RecursoNoEncontrado;
        // OJO: el test solo manda new RecursoNoEncontrado("") y espera true; no
        // confundas con NullPointerException o SQLException (esas NO son de negocio).
        // CULTURA: en b09 las de dominio extienden DominioException (Ej079); el
        // criterio real sería t instanceof DominioException.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esExcepcionDeNegocio");
    }

    /**
     * RETO EXTRA 02: Categoriza la gravedad de fallos críticos del sistema.
     */
    public static boolean esExcepcionGravedadAlta(Throwable t) {
        // GUÍA: clasifica por tipo de fallo (teoría 9.3, fila "técnica → 500").
        // 1. null → false.
        // 2. Gravedad alta = fallos técnicos/de programación, no errores de
        //    negocio. NullPointerException es el ejemplo canónico.
        // 3. Criterio amplio y robusto: cuenta como grave un NPE, un Error de la
        //    JVM o cualquier RuntimeException que NO sea de dominio.
        // PISTA: return t instanceof NullPointerException
        //               || t instanceof Error;
        // OJO: el test manda new NullPointerException() y espera true.
        // CULTURA: estos son los que SÍ acaban en 500 y disparan una alerta.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esExcepcionGravedadAlta");
    }

    /**
     * RETO EXTRA 03: Mapea una excepcion a un codigo alfanumerico predefinido de error.
     */
    public static String obtenerCodigoError(Throwable t) {
        // GUÍA: tabla excepción → código de error (como la de 9.3 pero a String).
        // 1. null → un código por defecto (p.ej. "ERR-DESCONOCIDO").
        // 2. Mapea por tipo con instanceof/switch de patrones:
        //    NullPointerException → "ERR-NULO".
        // 3. Devuelve el código que toque; añade más ramas si quieres.
        // PISTA: if (t instanceof NullPointerException) return "ERR-NULO";
        // OJO: el test exige EXACTAMENTE "ERR-NULO" para un NPE (compara con equals).
        // CULTURA: estos códigos estables son los que el cliente busca en tu
        // catálogo de errores (el campo "type" del ProblemDetail de 9.2).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerCodigoError");
    }

    /**
     * RETO EXTRA 04: Formatea mensajes de error combinandolos con coherencia.
     */
    public static String formatearMensajeError(String defaultMsg, String customMsg) {
        // GUÍA: una línea de formateo — return defaultMsg + ": " + customMsg;
        // OJO: el separador es ": " (dos puntos + espacio). El test compara con
        // "Default: Custom" usando equals, así que el espacio importa.
        // (Defensa opcional: si alguno es null, sustitúyelo por "" antes de unir.)
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearMensajeError");
    }

    /**
     * RETO EXTRA 05: Extrae recursivamente la causa raiz de una excepcion.
     */
    public static String extraerDetalleCausa(Throwable t) {
        // GUÍA: recorre la cadena de causas hasta la raíz (teoría 1.9 + 9.7).
        // 1. null → devuelve null o "" (defensa).
        // 2. Avanza con getCause() mientras no sea null, quedándote con el último:
        //       Throwable raiz = t;
        //       while (raiz.getCause() != null) raiz = raiz.getCause();
        // 3. Devuelve raiz.getMessage().
        // OJO: el test pasa new RuntimeException(new Exception("raiz")) y espera
        // EXACTAMENTE "raiz" — es decir, el mensaje de la causa más profunda, no
        // el del wrapper externo (que es null aquí).
        // CULTURA: esto es lo que haces al loguear un 500: el mensaje del wrapper
        // no dice nada; la raíz sí. Conecta con la traducción de 9.7.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extraerDetalleCausa");
    }

    /**
     * RETO EXTRA 06: Verifica si la excepcion proviene del stack de persistencia.
     */
    public static boolean esErrorBaseDatos(Throwable t) {
        // GUÍA: detecta excepciones del stack de persistencia (teoría 9.7).
        // 1. null → false.
        // 2. La marca técnica de JDBC es java.sql.SQLException:
        //       return t instanceof java.sql.SQLException;
        // OJO: el test manda new java.sql.SQLException() y espera true.
        // CULTURA: justo estas son las que NO deben llegar a la web crudas; en
        // Ej083 las traduces a DatoDuplicado/Persistencia. Aquí solo las detectas.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esErrorBaseDatos");
    }

    /**
     * RETO EXTRA 07: Genera el texto amigable de recurso no hallado.
     */
    public static String crearMensajeInformativo(String recurso, Long id) {
        // GUÍA: compón un mensaje legible con el recurso y el id (como el detail
        // de un ProblemDetail 404, teoría 9.2).
        // PISTA: return recurso + " con id " + id + " no encontrado";
        // OJO: el test solo exige que el resultado .contains("User") cuando
        // recurso="User"; no fija el texto completo, así que tienes libertad de
        // redacción siempre que el nombre del recurso aparezca literal.
        // (Defensa: si recurso es null, usa "recurso".)
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearMensajeInformativo");
    }

    /**
     * RETO EXTRA 08: Comprueba si es un error del analizador o parseador JSON.
     */
    public static boolean esErrorSintaxis(Throwable t) {
        // GUÍA: detecta un error de parseo/sintaxis JSON.
        // 1. null o sin mensaje → false.
        // 2. Criterio del test: el mensaje menciona "json".
        //    PISTA: return t != null && t.getMessage() != null
        //                  && t.getMessage().toLowerCase().contains("json");
        // OJO: el test pasa new IllegalArgumentException("json") y espera true,
        // así que NO filtres por tipo (no es por la clase, es por el mensaje).
        // CULTURA: en Spring el error real es HttpMessageNotReadableException; un
        // @ExceptionHandler suyo devuelve 400 "JSON mal formado".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esErrorSintaxis");
    }

    /**
     * RETO EXTRA 09: Genera el formato estructurado para auditorias en logs.
     */
    public static String construirCuerpoLog(String traceId, String errorMsg) {
        // GUÍA: formatea una línea de log con el traceId delante (teoría 9.6).
        // PISTA: return "[" + traceId + "] " + errorMsg;
        // OJO: el test solo exige que el resultado .contains("T1") cuando
        // traceId="T1"; el formato exacto es tuyo, pero pon el trace al principio
        // para poder grep-earlo. Compáralo con formatearLogConTrace de Ej082.
        // CULTURA: este prefijo [trace] es lo que te deja reconstruir una
        // petición que cruzó varios servicios (observabilidad, b20).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para construirCuerpoLog");
    }

    /**
     * RETO EXTRA 10: Verifica si el problema se debe a conectividad o socket abortado.
     */
    public static boolean esErrorRed(Throwable t) {
        // GUÍA: detecta un fallo de red (conectividad/socket).
        // 1. null → false.
        // 2. El test manda new java.io.IOException("Connection refused"). El
        //    criterio más robusto que lo cubre: es una IOException (toda la
        //    familia de red la extiende: SocketException, ConnectException...).
        //    PISTA: return t instanceof java.io.IOException;
        // ALTERNATIVA por mensaje: t.getMessage() contiene "refused"/"Connection"
        //    — válida, pero el filtro por tipo es más sólido aquí.
        // CULTURA: estos errores son los que disparan el fallback de Ej084 (9.8).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esErrorRed");
    }

}