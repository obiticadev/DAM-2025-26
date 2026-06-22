package com.masterclass.api.b36_fxstyle;

import java.util.List;

/**
 * Ejercicio 291 · Usabilidad y feedback: mensajes de error, estados (deshabilitado/cargando), tooltips.
 *
 * <p>Teoría: {@code teoria/36_JavaFX_Estilo_Accesibilidad.md} (sección 5).
 *
 * <p>La <b>usabilidad</b> es que la app se entienda y se deje usar sin frustración: el usuario
 * siempre debe saber <i>qué pasa</i> (¿está cargando?), <i>qué puede hacer</i> (¿el botón está
 * activo?) y <i>qué ha fallado y por qué</i> (un mensaje de error claro, no un volcado técnico).
 * Aquí construimos la lógica de ese feedback: redactar mensajes, decidir el estado de un botón,
 * generar tooltips y resúmenes. Todo son cadenas/booleanos comprobables sin pantalla.
 */
public final class Ej291UsabilityFeedback {

    private Ej291UsabilityFeedback() {
    }

    /**
     * Redacta un mensaje de error orientado al USUARIO (no al programador) a partir del campo y el
     * tipo de error. Nada de "NullPointerException": frases que la persona entienda y pueda actuar.
     *
     * @param campo nombre legible del campo, p.ej. "email"
     * @param tipo  tipo de error: "requerido", "formato", "longitud"…
     * @return mensaje listo para mostrar; {@code ""} sin implementar
     */
    public static String mensajeError(String campo, String tipo) {
        // TODO 1: usa un switch sobre 'tipo'.
        // TODO 2: "requerido" -> "El campo '" + campo + "' es obligatorio."
        // TODO 3: "formato"   -> "El campo '" + campo + "' no tiene un formato válido."
        // TODO 4: "longitud"  -> "El campo '" + campo + "' es demasiado largo."
        // TODO 5: default (tipo desconocido) -> "El campo '" + campo + "' no es válido." y devuélvelo.
        return "";
    }

    /**
     * Decide el estado visual de un botón de envío según si hay una operación en curso y si el
     * formulario es válido. La prioridad es: cargando manda sobre todo.
     *
     * @param cargando hay una operación en curso (p.ej. guardando)
     * @param valido   el formulario es válido
     * @return "cargando", "deshabilitado" o "activo"; {@code ""} sin implementar
     */
    public static String estadoBoton(boolean cargando, boolean valido) {
        // TODO 6: si 'cargando' es true, devuelve "cargando" (mientras se guarda, no se puede reenviar).
        // TODO 7: si NO está cargando pero el formulario NO es válido, devuelve "deshabilitado".
        // TODO 8: si no está cargando y es válido, devuelve "activo".
        // TODO 9: cuida el ORDEN: primero cargando, luego validez (el test prueba las tres ramas).
        // TODO 10: devuelve la cadena resultante.
        return "";
    }

    public static void main(String[] args) {
        System.out.println(mensajeError("email", "formato"));
        System.out.println("Botón (cargando=false, valido=true) -> " + estadoBoton(false, true));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Valor para disableProperty.
     * Devuelve si el control debe deshabilitarse según una condición (lo que ata un bind).
     */
    public static boolean estaDeshabilitado(boolean condicion) {
        // GUÍA: teoría 5.2 (boton.disableProperty().bind(condicion): el botón se apaga solo).
        // 1. return condicion;
        // OJO: parece trivial, pero la clave es el PATRÓN: no haces setDisable a mano en 10 sitios;
        //   atas disableProperty a una expresión booleana (b33) y la UI se sincroniza sola.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para estaDeshabilitado");
    }

    /**
     * Reto Extra 2: Texto del botón mientras carga.
     * Devuelve "Cargando…" si está cargando; si no, el texto normal.
     */
    public static String textoCargando(String textoNormal, boolean cargando) {
        // GUÍA: teoría 5.3 (feedback de progreso: el botón cambia de texto mientras trabaja).
        // 1. return cargando ? "Cargando…" : textoNormal;
        // OJO: el test usa el carácter "…" (puntos suspensivos, U+2026), NO tres puntos "...".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para textoCargando");
    }

    /**
     * Reto Extra 3: Tooltip de ayuda de un campo.
     * Devuelve el texto de ayuda (tooltip) asociado a un campo; "" si no tiene.
     */
    public static String tooltipDe(String campo) {
        // GUÍA: teoría 5.4 (un Tooltip explica un control al pasar el ratón, sin ocupar sitio fijo).
        // 1. switch (campo): "email"->"Introduce un correo válido, p.ej. ana@mail.com",
        //    "password"->"Mínimo 8 caracteres".
        // 2. default -> "" (no todos los campos necesitan tooltip).
        // OJO: el test prueba "email" y "desconocido"->"". El tooltip AYUDA antes del error; no lo
        //   uses para información imprescindible (no todos lo descubren).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tooltipDe");
    }

    /**
     * Reto Extra 4: Texto con plural correcto.
     * Devuelve "1 elemento" o "N elementos" según la cantidad.
     */
    public static String plural(int n) {
        // GUÍA: teoría 5.5 (un buen mensaje concuerda en número: "1 elemento", no "1 elementos").
        // 1. return n == 1 ? "1 elemento" : n + " elementos";
        // OJO: el test prueba 1 -> "1 elemento" y 0 -> "0 elementos" (el cero va en plural en español).
        // CULTURA: la pluralización correcta depende del idioma; en Ej292 lo verás con i18n real.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para plural");
    }

    /**
     * Reto Extra 5: Truncar texto largo con elipsis.
     * Corta el texto a 'max' caracteres y añade "…" si se pasó.
     */
    public static String truncar(String texto, int max) {
        // GUÍA: teoría 5.4 (en tooltips/etiquetas, un texto demasiado largo se recorta con elipsis).
        // 1. Si texto.length() <= max, devuélvelo tal cual.
        // 2. Si no, devuelve texto.substring(0, max) + "…".
        // OJO: el test prueba ("Hola mundo", 4) -> "Hola…" y ("Hi", 4) -> "Hi" (no se toca si cabe).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para truncar");
    }

    /**
     * Reto Extra 6: Clase CSS de feedback.
     * Mapea un tipo de feedback a la clase de estilo que lo pinta.
     */
    public static String claseFeedback(String tipo) {
        // GUÍA: teoría 5.6 (el feedback también es visual: borde rojo en error, verde en ok...).
        // 1. switch (tipo): "error"->"campo-error", "ok"->"campo-ok", "aviso"->"campo-aviso".
        // 2. default -> "".
        // OJO: el test prueba "error"->"campo-error". Conecta con Ej287: esta clase la pinta la hoja.css.
        // CULTURA: nunca uses SOLO color para indicar error (un daltónico no lo ve): acompáñalo de
        //   icono o texto. El color es refuerzo, no el único canal (regla WCAG, Ej290).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para claseFeedback");
    }

    /**
     * Reto Extra 7: Resumen de errores del formulario.
     * Devuelve un resumen legible de la lista de errores, o "Todo correcto" si no hay.
     */
    public static String resumenErrores(List<String> errores) {
        // GUÍA: teoría 5.5 (un formulario muestra un resumen arriba: "Hay 2 errores: ...").
        // 1. Si la lista está vacía, devuelve "Todo correcto".
        // 2. Si no, n = errores.size(); construye "Hay " + n + " error" + (n == 1 ? "" : "es") + ": "
        //    + String.join(", ", errores).
        // PISTA: String.join(", ", lista) une con comas.
        // OJO: el test prueba ["a","b"] -> "Hay 2 errores: a, b" y [] -> "Todo correcto".
        //   Cuida el plural "error"/"errores" (reto 4 otra vez).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para resumenErrores");
    }

    /**
     * Reto Extra 8: Porcentaje de progreso como texto.
     * Convierte una fracción 0.0–1.0 en un porcentaje entero con '%'.
     */
    public static String porcentaje(double fraccion) {
        // GUÍA: teoría 5.3 (una barra de progreso suele acompañarse del texto "50%").
        // 1. return Math.round(fraccion * 100) + "%";
        // OJO: el test prueba 0.5 -> "50%" y 1.0 -> "100%". Math.round devuelve long: el +"%" lo
        //   convierte a texto sin problema.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para porcentaje");
    }

    /**
     * Reto Extra 9: ¿Se puede enviar el formulario?
     * Solo si es válido, NO está cargando y hay cambios sin guardar.
     */
    public static boolean puedeEnviar(boolean valido, boolean cargando, boolean hayCambios) {
        // GUÍA: teoría 5.2 (combinas varias condiciones; en JavaFX sería un Bindings.and(...)).
        // 1. return valido && !cargando && hayCambios;
        // PISTA: en la UI real: enviarBtn.disableProperty().bind(valido.not().or(cargando).or(hayCambios.not())).
        // OJO: el test prueba (true,false,true)->true y (true,true,true)->false (cargando bloquea).
        //   "hayCambios" evita reenviar lo mismo: el botón "Guardar" se apaga si no tocaste nada.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para puedeEnviar");
    }

    /**
     * Reto Extra 10: Mensaje de error accionable.
     * Convierte un tipo de fallo técnico en un mensaje que DICE AL USUARIO QUÉ HACER.
     */
    public static String mensajeAccionable(String tipoFallo) {
        // GUÍA: teoría 5.7 (un buen error no solo describe: propone una ACCIÓN concreta).
        // 1. switch (tipoFallo):
        //    "conexion" -> "No hay conexión. Revisa tu red e inténtalo de nuevo."
        //    "sesion"   -> "Tu sesión ha caducado. Vuelve a iniciar sesión."
        // 2. default -> "Ha ocurrido un error. Inténtalo más tarde."
        // OJO: el test prueba "conexion" y un valor desconocido (-> el mensaje por defecto).
        // CULTURA: esto es la cara amable de los errores que diseñaste en b09 (RFC 7807, application/
        //   problem+json): el backend manda un "type/title/detail" y la UI lo traduce a algo accionable.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mensajeAccionable");
    }
}
