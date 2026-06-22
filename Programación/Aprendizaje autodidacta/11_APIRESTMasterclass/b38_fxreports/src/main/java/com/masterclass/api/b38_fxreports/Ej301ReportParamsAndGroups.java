package com.masterclass.api.b38_fxreports;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Ejercicio 301 · Parámetros, agrupaciones y totales/subtotales.
 *
 * <p>Teoría: {@code teoria/38_Informes_PDF.md} (sección 3).
 *
 * <p>Un informe profesional no es solo una tabla: lleva <b>parámetros</b> que le pasas desde fuera
 * (el título, la fecha de emisión) que la plantilla lee con {@code $P{titulo}}, y <b>grupos</b> que
 * parten el detalle en secciones con su <b>subtotal</b> ({@code GROUP BY}) más un <b>gran total</b>
 * al final ({@code $V{total}} en la banda <i>summary</i>). Lo testeable —y la parte que Jasper te
 * deja calcular a ti— es justo esa aritmética: resolver un parámetro con su valor por defecto y
 * sumar subtotales por grupo y el total general. Aquí el core lo hace con lógica pura sobre las
 * líneas del informe.
 */
public final class Ej301ReportParamsAndGroups {

    private Ej301ReportParamsAndGroups() {
    }

    /**
     * Una línea del informe: {@code grupo} es el campo por el que se agrupa (p.ej. la categoría),
     * {@code concepto} el texto de la fila e {@code importe} su valor en euros.
     */
    public record LineaDto(String grupo, String concepto, double importe) {
    }

    /**
     * Resuelve un parámetro del informe ({@code $P{clave}}) con un valor por defecto: si el mapa de
     * parámetros no trae la clave (o trae null), se usa el valor por defecto.
     *
     * @param params     mapa de parámetros (puede ser null)
     * @param clave       nombre del parámetro, p.ej. {@code "titulo"}
     * @param porDefecto valor a usar si la clave falta o es null
     * @return el valor del parámetro como texto, o {@code porDefecto}; {@code ""} sin implementar
     */
    public static String parametro(Map<String, Object> params, String clave, String porDefecto) {
        // TODO 1: si params es null o no contiene la clave (o su valor es null), devuelve porDefecto.
        // TODO 2: si existe, conviértelo a texto con String.valueOf(valor) (o valor.toString()).
        // TODO 3: devuelve ese texto (el test: {titulo="Ventas"} clave "titulo" -> "Ventas";
        //         clave ausente "fecha" con defecto "hoy" -> "hoy").
        return "";
    }

    /**
     * Subtotal por grupo: suma de los importes de cada grupo, en el orden en que aparecen los
     * grupos. Es lo que la plantilla muestra al cerrar cada grupo ({@code <subtotal>}).
     *
     * @param lineas líneas del informe (puede ser null)
     * @return mapa grupo -> suma de importes; {@code Map.of()} sin implementar
     */
    public static Map<String, Double> subtotalPorGrupo(List<LineaDto> lineas) {
        // TODO 4: si lineas es null o vacía, devuelve Map.of().
        // TODO 5: crea un LinkedHashMap<String,Double> (conserva el orden de aparición de los grupos).
        // TODO 6: por cada línea, mapa.merge(l.grupo(), l.importe(), Double::sum).
        // TODO 7: devuelve el mapa (el test: grupo A{10,20}, B{5} -> {A=30.0, B=5.0}).
        return Map.of();
    }

    /**
     * Gran total: suma de TODOS los importes (la cifra final del informe, banda <i>summary</i>).
     *
     * @param lineas líneas del informe (puede ser null)
     * @return suma de todos los importes; {@code 0.0} si no hay líneas
     */
    public static double granTotal(List<LineaDto> lineas) {
        // TODO 8: si lineas es null o vacía, devuelve 0.0.
        // TODO 9: acumula en un double el importe de cada línea.
        // TODO 10: devuelve el total (el test: {10,20,5} -> 35.0).
        return -1;
    }

    public static void main(String[] args) {
        List<LineaDto> lineas = List.of(
                new LineaDto("Bebidas", "Agua", 10),
                new LineaDto("Bebidas", "Zumo", 20),
                new LineaDto("Comida", "Pan", 5));
        System.out.println("Título: " + parametro(Map.of("titulo", "Ventas"), "titulo", "Informe"));
        System.out.println("Subtotales: " + subtotalPorGrupo(lineas));
        System.out.println("Gran total: " + granTotal(lineas));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Título del informe.
     * Lee el parámetro "titulo"; si no viene, usa "Informe sin título".
     */
    public static String tituloDelInforme(Map<String, Object> params) {
        // GUÍA: teoría 3.1 ($P{titulo} con un valor por defecto sensato).
        // 1. Devuelve parametro(params, "titulo", "Informe sin título").
        // PISTA: reutiliza el core parametro(...).
        // OJO: el test prueba {titulo="Q1"} -> "Q1"; mapa vacío -> "Informe sin título".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tituloDelInforme");
    }

    /**
     * Reto Extra 2: Número de grupos distintos.
     * Cuántas secciones tendrá el informe (grupos distintos).
     */
    public static int numeroDeGrupos(List<LineaDto> lineas) {
        // GUÍA: teoría 3.2 (cada grupo distinto es una sección con su cabecera y su subtotal).
        // 1. Si lineas es null, devuelve 0.
        // 2. Mete cada l.grupo() en un Set y devuelve set.size() (o subtotalPorGrupo(lineas).size()).
        // OJO: el test con grupos [A,A,B] -> 2.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para numeroDeGrupos");
    }

    /**
     * Reto Extra 3: Líneas de un grupo.
     * Devuelve solo las líneas cuyo grupo coincide (lo que entra en una sección).
     */
    public static List<LineaDto> lineasDeGrupo(List<LineaDto> lineas, String grupo) {
        // GUÍA: teoría 3.2 (el subconjunto que pinta la banda detalle DENTRO de un grupo).
        // 1. Si lineas es null, devuelve List.of().
        // 2. Filtra las que l.grupo().equals(grupo) a una lista nueva.
        // OJO: el test con grupo "A" sobre [A,A,B] -> 2 líneas.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para lineasDeGrupo");
    }

    /**
     * Reto Extra 4: Subtotal de un grupo concreto.
     * La suma de importes de un único grupo (0 si el grupo no existe).
     */
    public static double subtotalDe(List<LineaDto> lineas, String grupo) {
        // GUÍA: teoría 3.3 (el número que aparece en la línea "Subtotal Bebidas: ...").
        // 1. Mira subtotalPorGrupo(lineas).
        // 2. Devuelve el valor de 'grupo' con getOrDefault(grupo, 0.0).
        // PISTA: reutiliza subtotalPorGrupo(lineas).getOrDefault(grupo, 0.0).
        // OJO: el test: grupo A{10,20} -> 30.0; grupo inexistente "Z" -> 0.0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para subtotalDe");
    }

    /**
     * Reto Extra 5: Recuento de líneas por grupo.
     * Cuántas filas tiene cada sección (para la etiqueta "(3 artículos)").
     */
    public static Map<String, Integer> contarPorGrupo(List<LineaDto> lineas) {
        // GUÍA: teoría 3.3 (junto al subtotal se suele mostrar el nº de filas del grupo).
        // 1. Si lineas es null o vacía, devuelve Map.of().
        // 2. LinkedHashMap<String,Integer>; por cada línea mapa.merge(grupo, 1, Integer::sum).
        // OJO: el test con [A,A,B] -> {A=2, B=1}.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarPorGrupo");
    }

    /**
     * Reto Extra 6: Media de importe por grupo.
     * El importe medio de cada sección (subtotal / nº de líneas del grupo).
     */
    public static Map<String, Double> mediaPorGrupo(List<LineaDto> lineas) {
        // GUÍA: teoría 3.4 (un campo de grupo con calculación AVERAGE en Jasper).
        // 1. Calcula subtotalPorGrupo(lineas) y contarPorGrupo(lineas).
        // 2. Para cada grupo, media = subtotal / cuenta; mételo en un LinkedHashMap.
        // PISTA: recorre las claves de subtotales y divide por el conteo del mismo grupo.
        // OJO: el test con A{10,20} B{5} -> {A=15.0, B=5.0}.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mediaPorGrupo");
    }

    /**
     * Reto Extra 7: Grupo con mayor subtotal.
     * Qué sección suma más (para destacarla), envuelto en Optional.
     */
    public static Optional<String> grupoMayorSubtotal(List<LineaDto> lineas) {
        // GUÍA: teoría 3.4 (el "grupo estrella"; máximo sobre el mapa de subtotales).
        // 1. Calcula subtotalPorGrupo(lineas). Si está vacío, Optional.empty().
        // 2. Halla la entrada de mayor valor y devuelve Optional.of(su clave).
        // PISTA: mapa.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).
        // OJO: el test con A=30, B=5 -> Optional[A]; sin líneas -> empty.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para grupoMayorSubtotal");
    }

    /**
     * Reto Extra 8: Porcentaje que pesa un grupo sobre el total.
     * Qué tanto por ciento del gran total representa la sección (para una columna "% s/total").
     */
    public static double porcentajeDeGrupo(List<LineaDto> lineas, String grupo) {
        // GUÍA: teoría 3.5 (el peso relativo de cada grupo, como los porcentajes de tarta de b37).
        // 1. Calcula el gran total; si es 0, devuelve 0.0 (evita dividir por cero).
        // 2. Devuelve subtotalDe(lineas, grupo) / granTotal(lineas) * 100.
        // PISTA: reutiliza subtotalDe y granTotal.
        // OJO: el test con A=30 sobre total 35 -> 85.714...; comprueba con tolerancia 1e-6.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para porcentajeDeGrupo");
    }

    /**
     * Reto Extra 9: Parámetro entero con valor por defecto.
     * Lee un parámetro numérico ($P{copias}); si falta o no es un número, usa el valor por defecto.
     */
    public static int parametroEntero(Map<String, Object> params, String clave, int porDefecto) {
        // GUÍA: teoría 3.6 (los $P{} tienen TIPO; un parámetro Integer mal pasado debe degradar bien).
        // 1. Resuelve el texto con parametro(params, clave, String.valueOf(porDefecto)).
        // 2. Intenta Integer.parseInt(texto.trim()) dentro de try/catch; si peta, devuelve porDefecto.
        // PISTA: envuelve el parseInt en try { ... } catch (NumberFormatException e) { return porDefecto; }.
        // OJO: el test: {copias="3"} -> 3; clave ausente con defecto 1 -> 1; {copias="x"} -> defecto.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para parametroEntero");
    }

    /**
     * Reto Extra 10: Línea de resumen del informe.
     * Una frase para la banda summary: "N líneas en M grupos. Total: T.TT €".
     */
    public static String lineaResumen(List<LineaDto> lineas) {
        // GUÍA: teoría 3.7 (el pie del informe que cierra todo: recuento + total general).
        // 1. n = tamaño de la lista (0 si null); m = numeroDeGrupos(lineas); t = granTotal(lineas).
        // 2. Formatea el total con 2 decimales: String.format(java.util.Locale.US, "%.2f", t).
        // 3. Devuelve n + " líneas en " + m + " grupos. Total: " + totalFmt + " €".
        // OJO: el test con 3 líneas / 2 grupos / 35.0 -> "3 líneas en 2 grupos. Total: 35.00 €".
        // CULTURA: esta línea de cierre es el equivalente textual del KPI/tendencia de b37.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para lineaResumen");
    }

    /** Helper de demostración: arma una lista mutable de líneas. */
    static List<LineaDto> lineas(LineaDto... ls) {
        return new ArrayList<>(List.of(ls));
    }

    /** Helper de demostración: arma un mapa ordenado grupo->valor. */
    static Map<String, Double> mapa(String[] claves, double[] valores) {
        Map<String, Double> m = new LinkedHashMap<>();
        for (int i = 0; i < claves.length; i++) {
            m.put(claves[i], valores[i]);
        }
        return m;
    }
}
