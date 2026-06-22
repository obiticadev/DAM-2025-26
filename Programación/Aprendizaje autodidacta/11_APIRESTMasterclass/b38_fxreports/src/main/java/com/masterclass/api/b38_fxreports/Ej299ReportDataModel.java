package com.masterclass.api.b38_fxreports;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Ejercicio 299 · Modelo de datos del informe ({@code JRBeanCollectionDataSource}).
 *
 * <p>Teoría: {@code teoria/38_Informes_PDF.md} (sección 1).
 *
 * <p>Un informe Jasper se alimenta de una <b>fuente de datos</b>. La más cómoda es una
 * {@code JRBeanCollectionDataSource}: le pasas una {@code List} de beans (aquí, facturas) y Jasper
 * recorre la lista generando una fila ("banda detalle") por cada elemento, leyendo cada campo con
 * {@code $F{nombreDelCampo}}. Lo testeable —y lo que de verdad importa antes de pintar nada— es
 * <b>preparar ese modelo</b>: cuántos registros hay, qué campos expone cada bean, y los cálculos
 * derivados (el total con IVA, agrupaciones, ordenaciones). Aquí el core trabaja sobre la lista de
 * facturas con lógica pura; ninguna ventana ni motor de informes está abierto.
 */
public final class Ej299ReportDataModel {

    private Ej299ReportDataModel() {
    }

    /**
     * Una factura como "bean" del informe: cada componente del record es un CAMPO que la plantilla
     * {@code .jrxml} lee con {@code $F{numero}}, {@code $F{cliente}}, etc. {@code base} es la base
     * imponible y {@code iva} la cuota de IVA, ambas en euros.
     */
    public record FacturaDto(String numero, String cliente, double base, double iva) {
    }

    /**
     * Número de registros (filas detalle) que generaría la fuente de datos.
     *
     * @param facturas lista de facturas (puede ser null)
     * @return tamaño de la lista; {@code 0} si es null o vacía
     */
    public static int numeroDeRegistros(List<FacturaDto> facturas) {
        // TODO 1: si facturas es null, devuelve 0 (una fuente vacía no tiene registros).
        // TODO 2: si no, el número de registros es facturas.size().
        // TODO 3: devuelve ese tamaño (el test prueba 3 facturas -> 3; null -> 0).
        return -1;
    }

    /**
     * Total de una factura: base imponible + cuota de IVA. Es el valor que la plantilla muestra en
     * la columna "Total" con una expresión calculada {@code $F{base} + $F{iva}}.
     *
     * @param f factura (puede ser null)
     * @return base + iva; {@code 0.0} si la factura es null
     */
    public static double totalConIva(FacturaDto f) {
        // TODO 4: si f es null, devuelve 0.0 (no hay nada que sumar).
        // TODO 5: el total es f.base() + f.iva().
        // TODO 6: devuelve ese total (el test prueba base 100 + iva 21 -> 121.0).
        return -1;
    }

    /**
     * Nombres de los campos que la plantilla puede referenciar con {@code $F{...}}. Son los cuatro
     * componentes del record MÁS el campo calculado "total" que la columna de la derecha muestra.
     *
     * @return lista en orden: numero, cliente, base, iva, total; {@code List.of()} sin implementar
     */
    public static List<String> camposDisponibles() {
        // TODO 7: crea una List<String> (new ArrayList<>() o List.of(...) ya rellena).
        // TODO 8: añade en orden los campos del bean: "numero", "cliente", "base", "iva".
        // TODO 9: añade además el campo calculado "total" (no es un componente del record, pero la
        //         plantilla lo expone como columna -> el informe lo trata como un campo más).
        // TODO 10: devuelve la lista de 5 nombres (el test comprueba el tamaño y el orden exacto).
        return List.of();
    }

    public static void main(String[] args) {
        List<FacturaDto> datos = List.of(
                new FacturaDto("F-001", "Ana", 100, 21),
                new FacturaDto("F-002", "Luis", 200, 42),
                new FacturaDto("F-003", "Ana", 50, 10.5));
        System.out.println("Registros: " + numeroDeRegistros(datos));
        System.out.println("Total F-001: " + totalConIva(datos.get(0)));
        System.out.println("Campos: " + camposDisponibles());
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Cliente de una factura.
     * Devuelve el nombre del cliente, o cadena vacía si la factura es null.
     */
    public static String clienteDe(FacturaDto f) {
        // GUÍA: teoría 1.2 (acceder a un campo del bean es lo que hace $F{cliente} en la plantilla).
        // 1. Si f es null, devuelve "".
        // 2. Si no, devuelve f.cliente().
        // OJO: el test prueba la factura de "Ana" -> "Ana"; null -> "".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para clienteDe");
    }

    /**
     * Reto Extra 2: Total de toda la lista.
     * Suma el total (base + iva) de todas las facturas: es el "gran total" del informe.
     */
    public static double totalDeLista(List<FacturaDto> facturas) {
        // GUÍA: teoría 1.3 (el campo de resumen $V{totalGeneral} de la banda summary).
        // 1. Si facturas es null o vacía, devuelve 0.0.
        // 2. Acumula en un double recorriendo y sumando totalConIva(f) de cada una.
        // PISTA: reutiliza totalConIva(f).
        // OJO: el test usa base{100,200} iva{21,42} -> 363.0; lista vacía -> 0.0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para totalDeLista");
    }

    /**
     * Reto Extra 3: Filtrar por cliente.
     * Devuelve solo las facturas cuyo cliente coincide exactamente con el dado, en el mismo orden.
     */
    public static List<FacturaDto> filtrarPorCliente(List<FacturaDto> facturas, String cliente) {
        // GUÍA: teoría 1.4 (un informe filtrado: "facturas de Ana"; equivale a un WHERE de b15).
        // 1. Si facturas es null, devuelve List.of().
        // 2. Recorre y añade a una lista las que f.cliente().equals(cliente).
        // PISTA: usa una List<FacturaDto> nueva y .equals para comparar nombres.
        // OJO: el test pide "Ana" sobre 3 facturas (2 de Ana) -> devuelve 2.
        // CULTURA: el WHERE que alimenta el informe lo escribiste en SQL/JPA en b11-b15.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para filtrarPorCliente");
    }

    /**
     * Reto Extra 4: Lista de números de factura.
     * Proyecta solo el campo "numero" de cada factura (una columna del informe).
     */
    public static List<String> numerosDeFactura(List<FacturaDto> facturas) {
        // GUÍA: teoría 1.4 (proyectar un campo = el map de streams de b01).
        // 1. Si facturas es null, devuelve List.of().
        // 2. Recorre y añade f.numero() a una lista (o usa stream().map(FacturaDto::numero).toList()).
        // OJO: el test espera ["F-001","F-002","F-003"] en orden.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para numerosDeFactura");
    }

    /**
     * Reto Extra 5: Factura de mayor total.
     * Devuelve la factura con el total (base+iva) más alto, envuelta en Optional.
     */
    public static Optional<FacturaDto> facturaMayor(List<FacturaDto> facturas) {
        // GUÍA: teoría 1.5 (el "máximo" como Optional, igual que max/min de streams en b01).
        // 1. Si facturas es null o vacía, devuelve Optional.empty().
        // 2. Recorre llevando la de mayor totalConIva; al final Optional.of(mejor).
        // PISTA: facturas.stream().max(Comparator.comparingDouble(Ej299ReportDataModel::totalConIva)).
        // OJO: el test con totales {121,242,60.5} -> la de "F-002" (242); lista vacía -> empty.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para facturaMayor");
    }

    /**
     * Reto Extra 6: Total agrupado por cliente.
     * Suma el total de cada cliente, conservando el orden de primera aparición (la base de un
     * informe agrupado por cliente con subtotales).
     */
    public static Map<String, Double> totalPorCliente(List<FacturaDto> facturas) {
        // GUÍA: teoría 1.6 (esto es el GROUP BY del informe; lo formaliza Jasper en Ej301).
        // 1. Usa un LinkedHashMap<String,Double> (mantiene el orden de aparición de los clientes).
        // 2. Por cada factura, mapa.merge(f.cliente(), totalConIva(f), Double::sum).
        // 3. Devuelve el mapa.
        // PISTA: merge(clave, valor, Double::sum) suma si la clave ya existía o la crea si no.
        // OJO: el test con Ana{121, 60.5} y Luis{242} -> {Ana=181.5, Luis=242.0}.
        // CULTURA: la agrupación con subtotales es el núcleo de Ej301 (GROUP del informe).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para totalPorCliente");
    }

    /**
     * Reto Extra 7: Base imponible total.
     * Suma solo la base (sin IVA) de todas las facturas (lo que Hacienda llama base agregada).
     */
    public static double baseImponibleTotal(List<FacturaDto> facturas) {
        // GUÍA: teoría 1.3 (otra variable de resumen del informe, sobre un campo distinto).
        // 1. Si facturas es null o vacía, devuelve 0.0.
        // 2. Acumula f.base() de cada factura.
        // OJO: el test con bases {100, 200, 50} -> 350.0 (NO sumes el iva aquí).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para baseImponibleTotal");
    }

    /**
     * Reto Extra 8: Validar una factura.
     * Una factura es válida si su número no está vacío y base e iva no son negativos.
     */
    public static boolean facturaValida(FacturaDto f) {
        // GUÍA: teoría 1.7 (limpiar los datos ANTES de meterlos al informe evita filas basura).
        // 1. Si f es null, devuelve false.
        // 2. Devuelve true solo si numero no es null ni vacío (tras trim) y base>=0 y iva>=0.
        // PISTA: f.numero() != null && !f.numero().isBlank() && f.base() >= 0 && f.iva() >= 0.
        // OJO: el test prueba una válida -> true; una con base -5 -> false; número " " -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para facturaValida");
    }

    /**
     * Reto Extra 9: Fila del informe.
     * Convierte una factura en las celdas de su banda detalle, ya formateadas como texto.
     */
    public static String[] aFilaInforme(FacturaDto f) {
        // GUÍA: teoría 1.8 (cada $F{...} de la banda detalle es una celda; aquí las pre-formateas).
        // 1. Si f es null, devuelve un array vacío new String[0].
        // 2. Devuelve {numero, cliente, base, iva, total} como texto.
        //    Para los importes usa String.format(java.util.Locale.US, "%.2f", valor) -> 2 decimales.
        // PISTA: el total es totalConIva(f) ya formateado.
        // OJO: el test prueba F-001/Ana/100/21 -> {"F-001","Ana","100.00","21.00","121.00"}.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para aFilaInforme");
    }

    /**
     * Reto Extra 10: Ordenar por total descendente.
     * Devuelve una NUEVA lista ordenada de mayor a menor total (sin tocar la original).
     */
    public static List<FacturaDto> ordenarPorTotalDesc(List<FacturaDto> facturas) {
        // GUÍA: teoría 1.9 (el "sortField" del informe; ordenar es decidir el orden de las bandas).
        // 1. Si facturas es null, devuelve List.of().
        // 2. Copia a una lista nueva (new ArrayList<>(facturas)) para NO mutar la entrada.
        // 3. Ordena por totalConIva DESCENDENTE y devuelve la copia.
        // PISTA: copia.sort(Comparator.comparingDouble(Ej299ReportDataModel::totalConIva).reversed()).
        // OJO: el test con totales {121,242,60.5} -> primero "F-002" (242), último "F-003" (60.5);
        //   además comprueba que la lista ORIGINAL no cambió de orden.
        // CULTURA: ordenar el dataset antes del informe es lo mismo que el ORDER BY / topN de b37.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ordenarPorTotalDesc");
    }

    /** Helper de demostración: arma una lista mutable de facturas. */
    static List<FacturaDto> facturas(FacturaDto... fs) {
        return new ArrayList<>(List.of(fs));
    }

    /** Helper de demostración: arma un mapa ordenado cliente->total. */
    static Map<String, Double> mapa(String[] claves, double[] valores) {
        Map<String, Double> m = new LinkedHashMap<>();
        for (int i = 0; i < claves.length; i++) {
            m.put(claves[i], valores[i]);
        }
        return m;
    }
}
