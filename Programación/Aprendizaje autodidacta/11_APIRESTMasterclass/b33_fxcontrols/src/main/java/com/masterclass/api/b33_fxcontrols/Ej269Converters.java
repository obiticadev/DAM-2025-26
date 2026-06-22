package com.masterclass.api.b33_fxcontrols;

import java.time.LocalDate;

import javafx.scene.control.DatePicker;
import javafx.util.StringConverter;

/**
 * Ejercicio 269 · Conversores: {@link StringConverter}, formateo y parsing en controles.
 *
 * <p>Teoría: {@code teoria/33_JavaFX_Controles_Binding.md} (sección 3.1).
 *
 * <p>Los controles muestran TEXTO pero su valor es un OBJETO (LocalDate, Integer, tu propio tipo).
 * Un {@link StringConverter} es el puente: {@code toString(objeto)} para pintar y
 * {@code fromString(texto)} para leer lo tecleado. Es el mismo papel que un serializador (Jackson,
 * b02) pero para la UI. Cores de lógica pura: molde estándar.
 */
public final class Ej269Converters {

    private Ej269Converters() {
    }

    /**
     * Formatea una fecha al patrón español {@code dd/MM/yyyy}.
     *
     * @param fecha fecha a formatear
     * @return el texto en formato dd/MM/yyyy; {@code ""} sin implementar
     */
    public static String fechaATexto(LocalDate fecha) {
        // TODO 1: crea un DateTimeFormatter con el patrón "dd/MM/yyyy".
        // TODO 2: usa DateTimeFormatter.ofPattern("dd/MM/yyyy").
        // TODO 3: aplica el formato a la fecha con fecha.format(formatter).
        // TODO 4: recuerda que dd = día con dos dígitos, MM = mes, yyyy = año (M mayúscula es mes).
        // TODO 5: devuelve ese texto.
        return "";
    }

    /**
     * Convierte un texto {@code dd/MM/yyyy} a {@link LocalDate}.
     *
     * @param texto texto en formato dd/MM/yyyy
     * @return la fecha parseada; {@code null} sin implementar
     */
    public static LocalDate textoAFecha(String texto) {
        // TODO 6: crea el mismo DateTimeFormatter "dd/MM/yyyy" (debe coincidir con fechaATexto).
        // TODO 7: usa LocalDate.parse(texto, formatter).
        // TODO 8: si el patrón no coincide, parse lanza DateTimeParseException (lo ves en el reto 9).
        // TODO 9: el resultado es un LocalDate (java.time), no un String.
        // TODO 10: devuélvelo.
        return null;
    }

    public static void main(String[] args) {
        LocalDate hoy = LocalDate.of(2024, 6, 22);
        System.out.println("Fecha a texto: " + fechaATexto(hoy));
        System.out.println("Texto a fecha: " + textoAFecha("22/06/2024"));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Conversor de fechas reutilizable.
     * Devuelve un {@link StringConverter} de {@link LocalDate} con formato dd/MM/yyyy.
     */
    public static StringConverter<LocalDate> conversorFecha() {
        // GUÍA: teoría 3.1 (un StringConverter encapsula toString + fromString juntos).
        // 1. Devuelve un new StringConverter<LocalDate>() { toString(d){...} fromString(s){...} }.
        // 2. En toString reaprovecha fechaATexto; en fromString, textoAFecha.
        // PISTA: una clase anónima que implemente los dos métodos; toString(null) -> "".
        // OJO: el test hace ida y vuelta: fromString(toString(fecha)) debe devolver la misma fecha.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para conversorFecha");
    }

    /**
     * Reto Extra 2: Número a texto con conversor estándar.
     * Devuelve la representación textual de un double con {@code DoubleStringConverter}.
     */
    public static String numeroATexto(double valor) {
        // GUÍA: teoría 3.2 (JavaFX trae conversores hechos en javafx.util.converter).
        // 1. return new javafx.util.converter.DoubleStringConverter().toString(valor);
        // OJO: DoubleStringConverter usa Double.toString (punto decimal, independiente del idioma);
        //   por eso 3.14 da "3.14" y no "3,14". Para formato con idioma usarías NumberStringConverter.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para numeroATexto");
    }

    /**
     * Reto Extra 3: Texto a número con conversor estándar.
     * Devuelve el double parseado de un texto con {@code DoubleStringConverter}.
     */
    public static double textoANumero(String texto) {
        // GUÍA: teoría 3.2 (fromString hace el camino inverso).
        // 1. return new javafx.util.converter.DoubleStringConverter().fromString(texto);
        // OJO: el texto debe usar punto decimal ("2.5"); con coma fallaría.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para textoANumero");
    }

    /**
     * Reto Extra 4: Texto a entero.
     * Devuelve el int parseado con {@code IntegerStringConverter}.
     */
    public static int enteroDesdeTexto(String texto) {
        // GUÍA: teoría 3.2 (hay un conversor por tipo: Integer, Long, Double, Boolean, LocalDate…).
        // 1. return new javafx.util.converter.IntegerStringConverter().fromString(texto);
        // OJO: estos conversores son los que se enchufan a un Spinner o a una columna editable (b35).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para enteroDesdeTexto");
    }

    /**
     * Reto Extra 5: Ida y vuelta genérica (round-trip).
     * Indica si convertir a texto y volver al objeto devuelve un valor igual al original.
     */
    public static <T> boolean idaYVuelta(StringConverter<T> conversor, T valor) {
        // GUÍA: teoría 3.1 (un buen conversor cumple fromString(toString(x)).equals(x)).
        // 1. String t = conversor.toString(valor). 2. T vuelta = conversor.fromString(t).
        // 3. return java.util.Objects.equals(valor, vuelta).
        // PISTA: usa Objects.equals para tolerar nulls sin NullPointerException.
        // OJO: esto es una "propiedad" que TODO conversor debería cumplir; es un test de calidad.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para idaYVuelta");
    }

    /**
     * Reto Extra 6: Conversor booleano "Sí/No".
     * Devuelve un {@link StringConverter} de {@link Boolean} que pinta true->"Sí" y false->"No".
     */
    public static StringConverter<Boolean> conversorBooleano() {
        // GUÍA: teoría 3.1 (un conversor personalizado mapea tu dominio a texto legible).
        // 1. Clase anónima: toString(b) -> b ? "Sí" : "No"; fromString(s) -> "Sí".equals(s).
        // OJO: el test prueba toString(true)=="Sí" y fromString("Sí")==true; cuida null en toString.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para conversorBooleano");
    }

    /**
     * Reto Extra 7: Conversor que normaliza mayúsculas.
     * Devuelve un {@link StringConverter} de {@link String} que muestra en MAYÚSCULAS y guarda en minúsculas.
     */
    public static StringConverter<String> conversorMayusculas() {
        // GUÍA: teoría 3.1 (toString = cómo se VE; fromString = cómo se GUARDA; pueden diferir).
        // 1. toString(s) -> s.toUpperCase(); fromString(s) -> s.toLowerCase().
        // OJO: el test mete "Hola" -> toString "HOLA" y fromString("HOLA") -> "hola".
        //   Útil para normalizar entradas (emails, códigos) según se teclean.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para conversorMayusculas");
    }

    /**
     * Reto Extra 8: Enchufar un conversor a un DatePicker.
     * Pone el conversor y la fecha en el {@link DatePicker} y devuelve el texto que mostraría.
     */
    public static String aplicarADatePicker(DatePicker picker, StringConverter<LocalDate> conversor, LocalDate fecha) {
        // GUÍA: teoría 3.3 (los controles tienen setConverter para personalizar el texto que muestran).
        // 1. picker.setConverter(conversor). 2. picker.setValue(fecha).
        // 3. return picker.getConverter().toString(picker.getValue()).
        // PISTA: el DatePicker guarda un LocalDate y usa el converter para pintar/leer su editor.
        // OJO: este es el enganche real: el conversor de los retos 1/6/7 se conecta así a un control.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para aplicarADatePicker");
    }

    /**
     * Reto Extra 9: Parseo seguro con valor por defecto.
     * Intenta convertir el texto; si falla, devuelve 'porDefecto' en vez de lanzar excepción.
     */
    public static <T> T parseSeguro(StringConverter<T> conversor, String texto, T porDefecto) {
        // GUÍA: teoría 3.4 (fromString puede lanzar; en UI conviene degradar con elegancia).
        // 1. try { return conversor.fromString(texto); } catch (RuntimeException e) { return porDefecto; }
        // OJO: el test pasa un texto inválido para un conversor de fecha -> debe caer en porDefecto.
        //   Nunca dejes que un parse roto reviente toda la pantalla.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para parseSeguro");
    }

    /**
     * Reto Extra 10: Conversor de un objeto de dominio.
     * Devuelve un {@link StringConverter} de {@link Producto} con formato "nombre|precio".
     */
    public static StringConverter<Producto> conversorProducto() {
        // GUÍA: teoría 3.1 (un conversor sabe serializar TU tipo, no solo los básicos).
        // 1. toString(p) -> p.nombre() + "|" + p.precio().
        // 2. fromString(s) -> parte por "|" y reconstruye new Producto(trozo0, Double.parseDouble(trozo1)).
        // PISTA: String[] partes = s.split("\\|");  (la barra | hay que escaparla en la regex).
        // OJO: el test hace round-trip Producto -> "Café|1.5" -> Producto. equals lo da el record.
        // CULTURA: esto es exactamente lo que hace un serializador (Jackson de b02, JDBC de b11):
        //   objeto <-> texto. Un StringConverter es un serializador de UN campo para la capa de UI.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para conversorProducto");
    }

    /** Objeto de dominio mínimo para el reto 10 (record -> equals/hashCode automáticos). */
    public record Producto(String nombre, double precio) {
    }
}
