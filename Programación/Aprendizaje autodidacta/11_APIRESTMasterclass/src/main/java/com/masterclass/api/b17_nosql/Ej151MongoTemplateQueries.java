package com.masterclass.api.b17_nosql;

import java.util.List;

/**
 * Ejercicio 151 · Consultas con {@code MongoTemplate} y {@code Criteria}.
 *
 * <p>Teoría: {@code teoria/17_NoSQL_MongoDB.md} (sección 17.3).
 *
 * <p>Construimos un criterio como objeto puro ({@link Criterio151}) y lo
 * aplicamos en memoria sobre una lista de pedidos, imitando lo que haría
 * {@code mongoTemplate.find(new Query(Criteria.where(...)), ...)}.
 */
public final class Ej151MongoTemplateQueries {

    private Ej151MongoTemplateQueries() {
    }

    /**
     * Construye un criterio "campo total &gt;= min Y cliente = cliente".
     *
     * @param cliente valor exacto de cliente (null = sin filtro de cliente)
     * @param totalMinimo umbral inferior inclusivo de total
     * @return criterio inmutable que describe el filtro
     */
    public static Criterio151 criterio(String cliente, double totalMinimo) {
        // TODO 1: el criterio equivale a Criteria.where("total").gte(totalMinimo).
        // TODO 2: si cliente != null se añade and("cliente").is(cliente).
        // TODO 3: si cliente == null el filtro de cliente no aplica (cualquiera).
        // TODO 4: encapsula ambos valores en el record Criterio151.
        // TODO 5: no valides aquí el dataset (el criterio es independiente de los datos).
        // TODO 6: totalMinimo negativo es válido (no filtra nada por abajo).
        // TODO 7: documenta que es el equivalente NoSQL a Criteria API de JPA.
        // TODO 8: el criterio debe ser reutilizable en varias consultas.
        // TODO 9: no devuelvas null.
        // TODO 10: retorna el Criterio151 construido.
        return new Criterio151(null, 0.0);
    }

    /**
     * Aplica el criterio sobre la "colección" en memoria (simula find()).
     *
     * @param coleccion lista de pedidos (la colección Mongo)
     * @param criterio  filtro a aplicar
     * @return pedidos que cumplen el criterio
     * @throws IllegalArgumentException si algún argumento es null
     */
    public static List<Pedido149> find(List<Pedido149> coleccion, Criterio151 criterio) {
        // TODO 1: si coleccion o criterio son null -> IllegalArgumentException.
        // TODO 2: recorre todos los documentos de la colección.
        // TODO 3: descarta los que tengan total < criterio.totalMinimo().
        // TODO 4: si criterio.cliente() != null, exige pedido.cliente().equals(...).
        // TODO 5: si criterio.cliente() == null, no filtres por cliente.
        // TODO 6: combina ambas condiciones con AND (todas deben cumplirse).
        // TODO 7: conserva el orden original de la colección.
        // TODO 8: recoge los que pasan el filtro a una List.
        // TODO 9: si ninguno cumple, devuelve lista vacía (no null).
        // TODO 10: retorna la lista resultante.
        return List.of();
    }

    public static void main(String[] args) {
        List<Pedido149> col = List.of(
                new Pedido149("a", "ana", 120),
                new Pedido149("b", "leo", 50));
        System.out.println(find(col, criterio("ana", 100)));
    }
}

/** Criterio de búsqueda inmutable (equivalente a un {@code Query}). */
record Criterio151(String cliente, double totalMinimo) {
}
