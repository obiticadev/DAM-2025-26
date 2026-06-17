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

        /**
     * RETO EXTRA 01: Determina si la query contiene algun operador de modificacion ($set, $push).
     */
    public static boolean esComandoActualizacionValido(String updateDoc) {
        // GUÍA: teoría 17.3 (operadores de actualización: $set, $push, $inc, $unset...).
        // 1. Si updateDoc es null -> false.
        // 2. Comprueba que contenga alguno de los operadores de modificación.
        // PISTA: updateDoc.contains("$set") || updateDoc.contains("$push")
        //    || updateDoc.contains("$inc") || updateDoc.contains("$unset").
        // OJO: el test manda {"$set":{}} y espera true.
        // CULTURA: un Update de Spring (new Update().set(...)) genera estos $.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esComandoActualizacionValido");
    }

    /**
     * RETO EXTRA 02: Determina si es una busqueda directa por identificador primario.
     */
    public static boolean esFiltroDocumentId(String query) {
        // GUÍA: detectar una búsqueda por clave primaria (filtra por "_id").
        // 1. Si query es null -> false.
        // 2. Comprueba que el filtro mencione "_id".
        // PISTA: query.contains("\"_id\"") (o query.contains("_id")).
        // OJO: el test manda {"_id":1} y espera true.
        // CULTURA: buscar por _id usa el índice por defecto (O(1) lógico, 17.2).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esFiltroDocumentId");
    }

    /**
     * RETO EXTRA 03: Une dos asignaciones BSON en un unico bloque de actualizacion.
     */
    public static String combinarActualizaciones(String u1, String u2) {
        // GUÍA: una línea — concatena las dos actualizaciones separadas por coma.
        // PISTA: return u1 + "," + u2;
        // OJO: el test compara EXACTO con "u1,u2" (u1="u1", u2="u2"): una sola
        //      coma, sin espacios, sin llaves alrededor.
        // CULTURA: no confundir con combinarFiltrosY del Ej150 (ese sí lleva $and).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para combinarActualizaciones");
    }

    /**
     * RETO EXTRA 04: Determina si la excepcion corresponde a un error de escritura (DuplicateKey, etc).
     */
    public static boolean esExcepcionEscrituraMongo(Throwable t) {
        // GUÍA: clasificar una excepción de escritura (por mensaje).
        // 1. Si t es null -> false.
        // 2. Criterio: el mensaje contiene "write" (o "duplicate"), ignorando case.
        // PISTA: msg != null && msg.toLowerCase().contains("write");
        //    mismo patrón que esExcepcionMongo (Ej149 reto 8).
        // OJO: el test pasa new IllegalArgumentException("write") y espera true.
        // CULTURA: con el driver real sería DuplicateKeyException (clave única repetida).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esExcepcionEscrituraMongo");
    }

    /**
     * RETO EXTRA 05: Genera un comando de guardado incremental unificado.
     */
    public static String crearUpsertComando(String filter, String update) {
        // GUÍA: un upsert junta un filtro y una actualización con upsert:true.
        // 1. Construye algo como:
        //    {"q":filter,"u":update,"upsert":true}  (formato de update con upsert).
        // PISTA: el test SOLO exige que el resultado .contains("f") (filter="f"),
        //    así que basta con incluir filter y update en la cadena.
        // OJO: incluye el filter literal; con eso el contains("f") pasa.
        // CULTURA: upsert = "actualiza si existe, inserta si no" (save del 17.2).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearUpsertComando");
    }

    /**
     * RETO EXTRA 06: Valida que los paquetes de insercion no saturen la red.
     */
    public static boolean esLimiteSeguroEscritura(int bulkSize) {
        // GUÍA: validar el tamaño de un lote (bulk write) para no saturar la red.
        // 1. bulkSize debe ser positivo (> 0).
        // 2. Y no exceder un tope razonable (Mongo recomienda <= 1000 por lote).
        // PISTA: return bulkSize > 0 && bulkSize <= 1000;
        // OJO: el test manda 500 y espera true (500 cae dentro de [1, 1000]).
        // CULTURA: bulkOps agrupa muchas escrituras en un viaje de red (eficiencia).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esLimiteSeguroEscritura");
    }

    /**
     * RETO EXTRA 07: Extrae la cantidad de registros alterados de un log resumido.
     */
    public static int extraerTotalAfectados(String resultSummary) {
        // GUÍA: extraer el número de un resumen tipo "Modified: 5".
        // 1. Si resultSummary es null o vacío -> devuelve 0.
        // 2. Quédate solo con los dígitos del final (o tras los dos puntos).
        // 3. Truco: resultSummary.replaceAll("\\D+", "") deja solo dígitos;
        //    luego Integer.parseInt sobre el resultado.
        // PISTA: Integer.parseInt(resultSummary.replaceAll("\\D+", ""));
        //    (\\D = "no dígito"). Protege con try/catch o comprobando vacío.
        // OJO: el test manda "Modified: 5" y espera el int 5 (no "5").
        // CULTURA: un UpdateResult real trae getModifiedCount() ya como número.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extraerTotalAfectados");
    }

    /**
     * RETO EXTRA 08: Indica si la edicion afectara a multiples registros.
     */
    public static boolean esOperacionMultiDocumento(String updateCommand) {
        // GUÍA: detectar "multi:true" en un comando de actualización.
        // 1. Si updateCommand es null -> false.
        // 2. Comprueba que contenga "multi:true" (o "multi": true).
        // PISTA: updateCommand.replace(" ", "").contains("multi:true").
        // OJO: el test manda "multi:true" y espera true.
        // CULTURA: por defecto updateOne afecta a UNO; multi:true = updateMany.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esOperacionMultiDocumento");
    }

    /**
     * RETO EXTRA 09: Indica si la edicion afectara a multiples registros.
     */
    public static boolean esMulti(String config) {
        // GUÍA: versión mínima del reto 8 (solo busca la palabra "multi").
        // 1. Si config es null -> false.
        // 2. Devuelve config.contains("multi").
        // PISTA: return config != null && config.contains("multi");
        // OJO: el test manda "multi" (a secas) y espera true; reutiliza la idea
        //      de esOperacionMultiDocumento pero sin exigir ":true".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esMulti");
    }

    /**
     * RETO EXTRA 10: Determina si hay una transaccion abierta en la sesion.
     */
    public static boolean esTransaccionActiva(String sessionState) {
        // GUÍA: una sesión está en transacción si su estado es "ACTIVE".
        // 1. Si sessionState es null -> false.
        // 2. Compara ignorando mayúsculas con "ACTIVE".
        // PISTA: return "ACTIVE".equalsIgnoreCase(sessionState);
        // OJO: el test manda "ACTIVE" y espera true. Usa equalsIgnoreCase para
        //      no caer si llega "active".
        // CULTURA: Mongo soporta transacciones multi-documento desde 4.0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esTransaccionActiva");
    }

    /**
     * RETO EXTRA 11: Indica si el id fue dejado a cargo del driver de Mongo.
     */
    public static boolean esIdAutogenerado(String idField) {
        // GUÍA: teoría 17.1 (si no das id, el driver lo autogenera).
        // 1. El id está "autogenerado" precisamente cuando NO lo aportaste:
        //    idField es null o vacío.
        // PISTA: return idField == null || idField.isBlank();
        // OJO: el test pasa null y espera true (id ausente -> lo genera Mongo).
        // CULTURA: por eso al serializar omitías "_id" cuando era null (Ej149).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esIdAutogenerado");
    }

}

/** Criterio de búsqueda inmutable (equivalente a un {@code Query}). */
record Criterio151(String cliente, double totalMinimo) {
}