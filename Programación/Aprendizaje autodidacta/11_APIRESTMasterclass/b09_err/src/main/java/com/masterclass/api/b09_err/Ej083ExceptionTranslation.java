package com.masterclass.api.b09_err;

import java.sql.SQLException;

/**
 * Ejercicio 083 · Traducción de excepciones de infraestructura a dominio.
 *
 * <p>Teoría: {@code teoria/09_Manejo_de_Errores.md} (sección 9.3).
 *
 * <p>Una SQLException no debe filtrarse a la API: se traduce a una excepción
 * de dominio limpia.
 */
public final class Ej083ExceptionTranslation {

    public static class DatoDuplicadoException extends RuntimeException {
        public DatoDuplicadoException(String m) {
            super(m);
        }
    }

    public static class PersistenciaException extends RuntimeException {
        public PersistenciaException(String m, Throwable causa) {
            super(m, causa);
        }
    }

    private Ej083ExceptionTranslation() {
    }

    /**
     * Traduce una SQLException a una excepción de dominio según su SQLState.
     *
     * <p>SQLState "23505" = clave única duplicada (Postgres).
     *
     * @param ex excepción técnica de JDBC
     * @return la excepción de dominio equivalente (no la lanza, la devuelve)
     * @throws IllegalArgumentException si ex es null
     */
    public static RuntimeException traducir(SQLException ex) {
        // TODO 1: si ex es null -> IllegalArgumentException.
        // TODO 2: obtén el SQLState con ex.getSQLState().
        // TODO 3: si es "23505" -> devuelve DatoDuplicadoException("registro duplicado").
        // TODO 4: el mensaje de dominio NO debe exponer detalles internos de SQL.
        // TODO 5: para cualquier otro SQLState -> PersistenciaException.
        // TODO 6: en PersistenciaException, conserva 'ex' como causa (no la pierdas).
        // TODO 7: el mensaje genérico: "error de persistencia".
        // TODO 8: nunca propagues la SQLException cruda hacia la capa web.
        // TODO 9: preservar la causa permite depurar sin filtrarla al cliente.
        // TODO 10: devuelve la excepción de dominio resultante.
        return null;
    }

    public static void main(String[] args) {
        System.out.println(traducir(new SQLException("dup", "23505")).getClass().getSimpleName());
    }

        /**
     * RETO EXTRA 01: Determina si cumple el patron de claves i18n locales.
     */
    public static boolean esMensajeTraducible(String msg) {
        // GUÍA: una clave i18n es del tipo "grupo.subgrupo" (minúsculas + puntos).
        // 1. null o en blanco → false.
        // 2. PISTA: return msg != null && msg.matches("[a-z]+(\\.[a-z]+)+");
        //    Léelo: una o más letras minúsculas, seguido de "punto + letras" al
        //    menos una vez (así "error.notfound" pasa y "Error" suelto no).
        // OJO: el test pasa "error.notfound" → true. El "." en regex hay que
        // escaparlo ("\\.") o casaría con cualquier carácter.
        // CULTURA: estas claves son las de messages.properties (i18n de Spring);
        // un mensaje ya redactado ("No existe") NO es traducible, una clave sí.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esMensajeTraducible");
    }

    /**
     * RETO EXTRA 02: Comprueba soporte basico (es, en, fr).
     */
    public static boolean esIdiomaSoportado(String lang) {
        // GUÍA: ¿el idioma está en la lista soportada (es, en, fr)?
        // 1. null → false.
        // 2. PISTA: return java.util.Set.of("es", "en", "fr").contains(lang);
        //    (un Set.contains es más limpio que tres equals encadenados.)
        // OJO: el test pasa "es" → true. Si quisieras tolerar "ES"/"En",
        // normaliza con lang.toLowerCase() antes del contains.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esIdiomaSoportado");
    }

    /**
     * RETO EXTRA 03: Mapea clave y lengua a una traduccion de fallback.
     */
    public static String crearMensajeLocalizado(String lang, String key) {
        // GUÍA: prefija el mensaje con el idioma en mayúsculas.
        // PISTA: return lang.toUpperCase() + ": " + key;
        // OJO: el test pasa ("es", "error") y espera EXACTAMENTE "ES: error":
        // idioma en MAYÚSCULAS, separador ": " (dos puntos + espacio).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearMensajeLocalizado");
    }

    /**
     * RETO EXTRA 04: Determina si la clave corresponde a fallos de formulario.
     */
    public static boolean esErrorValidacionMensaje(String key) {
        // GUÍA: las claves de validación viven bajo el prefijo "validation.".
        // PISTA: return key != null && key.startsWith("validation.");
        // OJO: el test pasa "validation.notblank" → true. Filtra por prefijo, no
        // por contenido. Conecta con el payload de validación de Ej080 (9.4).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esErrorValidacionMensaje");
    }

    /**
     * RETO EXTRA 05: Sanea llaves de i18n.
     */
    public static String limpiarClaveLocalizacion(String rawKey) {
        // GUÍA: una línea — return rawKey.trim();
        // OJO: el test pasa " error.notfound " (con espacios) y espera
        // "error.notfound". trim() limpia los extremos. (Defensa: rawKey null →
        // null.) Una clave con espacios no encontraría su entrada en el .properties.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para limpiarClaveLocalizacion");
    }

    /**
     * RETO EXTRA 06: Evalua si el error ya paso por el traductor del negocio.
     */
    public static boolean esExcepcionTraducida(Throwable t) {
        // GUÍA: ¿el error ya fue traducido? (marca por su mensaje).
        // 1. null o sin mensaje → false.
        // 2. return t != null && t.getMessage() != null
        //        && t.getMessage().contains("translated");
        // OJO: el test pasa new IllegalArgumentException("translated") → true. El
        // criterio es por mensaje, no por tipo. La idea: evitar traducir dos veces
        // una excepción que ya pasó por traducir() (9.7).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esExcepcionTraducida");
    }

    /**
     * RETO EXTRA 07: Reemplaza parametros dinamicos en mensajes.
     */
    public static String traducirConArgs(String template, String arg) {
        // GUÍA: sustituye el marcador {0} por el argumento.
        // PISTA: return template.replace("{0}", arg);
        // OJO: el test pasa ("No existe el {0}", "5") y espera "No existe el 5".
        // Usa replace (literal), NO replaceAll (que interpretaría {0} como regex).
        // CULTURA: es el estilo de java.text.MessageFormat / los mensajes de
        // Spring con {0}, {1}... para parámetros.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para traducirConArgs");
    }

    /**
     * RETO EXTRA 08: Resuelve la primera preferencia de lenguaje del header.
     */
    public static String extraerLocaleDeHeader(String acceptLanguage) {
        // GUÍA: saca el idioma preferido de un header Accept-Language.
        // 1. null o vacío → un default ("es") o "".
        // 2. El formato es "es-ES,es;q=0.9,en;q=0.8". Quieres el idioma del PRIMER
        //    item, sin región: corta antes de la primera "," y antes de "-"/";".
        //    PISTA: String first = acceptLanguage.split(",")[0];   // "es-ES"
        //           return first.split("[-;]")[0];                  // "es"
        // OJO: el test espera "es" (no "es-ES"). Por eso hay que quitar la región
        // tras el "-". split("[-;]") corta tanto en "-" como en ";".
        // CULTURA: así resuelve Spring el Locale de la petición (LocaleResolver).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extraerLocaleDeHeader");
    }

    /**
     * RETO EXTRA 09: Verifica si el propio sistema i18n fallo.
     */
    public static boolean esFalloTraductor(Throwable t) {
        // GUÍA: detecta que el propio mecanismo de i18n falló.
        // 1. null → false.
        // 2. La marca de Java es java.util.MissingResourceException (falta el
        //    bundle/clave de traducción):
        //       return t instanceof java.util.MissingResourceException;
        // OJO: el test manda new MissingResourceException("","","") → true.
        // CULTURA: si tu traductor se cae, devuelves el fallback (reto 10) en vez
        // de un 500 — es la degradación controlada de 9.8 aplicada al i18n.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esFalloTraductor");
    }

    /**
     * RETO EXTRA 10: Resuelve clave o devuelve el fallback si no es traducible.
     */
    public static String generarDetalleTraducido(String code, String fallback) {
        // GUÍA: si la clave es traducible, resuélvela; si no, devuelve el fallback.
        // 1. Reutiliza esMensajeTraducible (reto 1) para decidir.
        //    PISTA: if (!esMensajeTraducible(code)) return fallback;
        //           return crearMensajeLocalizado("es", code);  // o tu resolución
        // OJO: el test pasa ("invalido", "fallback"): "invalido" NO encaja con el
        // patrón clave.subclave (no tiene punto) → no es traducible → devuelve
        // "fallback". Ese es justo el assertEquals del test.
        // CULTURA: el patrón "traduce o cae al texto por defecto" evita mostrar al
        // usuario una clave cruda como "error.notfound" cuando falta la traducción.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para generarDetalleTraducido");
    }

}