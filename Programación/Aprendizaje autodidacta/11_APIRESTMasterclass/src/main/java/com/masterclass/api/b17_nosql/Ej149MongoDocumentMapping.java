package com.masterclass.api.b17_nosql;

import java.util.Map;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Ejercicio 149 · Mapeo objeto↔documento con {@code @Document} y {@code @Id}.
 *
 * <p>Teoría: {@code teoria/17_NoSQL_MongoDB.md} (sección 17.1).
 *
 * <p>Sin un Mongo real, simulamos la serialización: un {@link Pedido149} se
 * convierte en un {@code Map} (el "documento BSON") respetando que {@code @Id}
 * viaja como la clave {@code _id}, y se reconstruye desde ese mapa.
 */
public final class Ej149MongoDocumentMapping {

    private Ej149MongoDocumentMapping() {
    }

    /**
     * Serializa un pedido al "documento" Mongo (mapa con clave {@code _id}).
     *
     * @param pedido pedido a mapear (no null)
     * @return mapa con claves {@code _id}, {@code cliente}, {@code total}
     * @throws IllegalArgumentException si pedido es null
     */
    public static Map<String, Object> aDocumento(Pedido149 pedido) {
        // TODO 1: si pedido es null -> IllegalArgumentException.
        // TODO 2: crea un LinkedHashMap para preservar el orden de claves.
        // TODO 3: el campo anotado con @Id se serializa como "_id" (NO como "id").
        // TODO 4: pon pedido.id() bajo la clave "_id".
        // TODO 5: pon pedido.cliente() bajo la clave "cliente".
        // TODO 6: pon pedido.total() bajo la clave "total".
        // TODO 7: NO incluyas claves con valor null si el id es null (Mongo lo generaría).
        // TODO 8: el nombre de colección sería "pedidos" (de @Document) -> documéntalo, no afecta al mapa.
        // TODO 9: devuelve un mapa inmodificable (Map.copyOf) para evitar mutación externa.
        // TODO 10: retorna el documento resultante.
        return Map.of();
    }

    /**
     * Reconstruye un pedido desde un "documento" Mongo.
     *
     * @param doc mapa con claves {@code _id}, {@code cliente}, {@code total}
     * @return pedido reconstruido
     * @throws IllegalArgumentException si doc es null o le falta {@code _id}
     */
    public static Pedido149 desdeDocumento(Map<String, Object> doc) {
        // TODO 1: si doc es null -> IllegalArgumentException.
        // TODO 2: si no contiene la clave "_id" -> IllegalArgumentException.
        // TODO 3: lee "_id" y conviértelo a String (el id del pedido).
        // TODO 4: lee "cliente" como String.
        // TODO 5: lee "total"; puede venir como Number -> usa doubleValue().
        // TODO 6: si "total" es null trátalo como 0.0.
        // TODO 7: valida que cliente no sea null ni vacío.
        // TODO 8: construye el record Pedido149 con los valores leídos.
        // TODO 9: el mapeo debe ser simétrico: desdeDocumento(aDocumento(p)).equals(p).
        // TODO 10: retorna el pedido reconstruido.
        return new Pedido149("", "", 0.0);
    }

    public static void main(String[] args) {
        Pedido149 p = new Pedido149("p1", "ana", 99.5);
        System.out.println(aDocumento(p));
        System.out.println(desdeDocumento(aDocumento(p)));
    }
}

/** Documento de pedido mapeado a la colección {@code pedidos}. */
@Document(collection = "pedidos")
record Pedido149(@Id String id, String cliente, double total) {
}
