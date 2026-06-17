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

        /**
     * RETO EXTRA 01: Valida nomenclatura de coleccion en Mongo (minusculas, sin caracteres prohibidos).
     */
    public static boolean esNombreColeccionValido(String name) {
        // GUÍA: teoría 17.1 (nombres de colección).
        // 1. Si name es null o vacío -> false.
        // 2. Mongo prohíbe: el carácter '$', el byte nulo, empezar por "system."
        //    y (por convención de este reto) las mayúsculas.
        // 3. Comprueba que no contenga '$' (name.contains("$")) ni espacios.
        // 4. Comprueba que sea todo minúsculas: name.equals(name.toLowerCase()).
        // 5. Devuelve true solo si pasa TODAS las comprobaciones.
        // PISTA: && encadenando las condiciones; name.toLowerCase().equals(name).
        // OJO: el test solo manda "users" (minúsculas, limpio) y espera true;
        //      no te pases validando hasta romper ese caso feliz.
        // CULTURA: una colección mal nombrada peta en runtime, no al compilar.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esNombreColeccionValido");
    }

    /**
     * RETO EXTRA 02: Valida que el string cumpla con la estructura de un ObjectId (24 hex chars).
     */
    public static boolean esIdMongoValido(String id) {
        // GUÍA: teoría 17.1 (un ObjectId son 24 caracteres hexadecimales).
        // 1. Si id es null -> false.
        // 2. Debe medir EXACTAMENTE 24 caracteres (id.length() == 24).
        // 3. Todos deben ser hex: dígitos 0-9 o letras a-f (mayúsc/minúsc).
        // 4. La forma más limpia es una regex: id.matches("[0-9a-fA-F]{24}").
        // PISTA: "[0-9a-fA-F]{24}" con String.matches (ancla toda la cadena sola).
        // OJO: el test pasa "507f1f77bcf86cd799439011" (24 hex) y espera true.
        // CULTURA: esos 24 hex son 12 bytes: timestamp+máquina+contador (17.1).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esIdMongoValido");
    }

    /**
     * RETO EXTRA 03: Genera una representacion ObjectId aleatoria.
     */
    public static String crearIdMongoNuevo() {
        // GUÍA: teoría 17.1 (genera un id de 24 hex como haría Mongo).
        // 1. Necesitas 24 caracteres hexadecimales aleatorios.
        // 2. Truco simple: new java.util.Random(), y en un bucle de 24 vueltas
        //    concatena Integer.toHexString(r.nextInt(16)) a un StringBuilder.
        // 3. Alternativa elegante: UUID.randomUUID().toString().replace("-","").substring(0,24).
        // PISTA: cualquiera de las dos sirve; el test solo exige assertNotNull.
        // OJO: que sea reutilizable con esIdMongoValido del reto 2 (24 hex).
        // CULTURA: en producción no lo generas tú; lo hace el driver de Mongo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearIdMongoNuevo");
    }

    /**
     * RETO EXTRA 04: Valida la definicion de indice compuesto.
     */
    public static boolean esIndiceCompuestoValido(String def) {
        // GUÍA: teoría 17.1/17.2 (un índice se define como un documento JSON
        //       { "campo": 1, "otro": -1 } donde 1=asc y -1=desc).
        // 1. Si def es null o vacío -> false.
        // 2. Debe parecer un objeto JSON: empezar por '{' y acabar por '}'.
        // 3. Comprueba def.trim().startsWith("{") && def.trim().endsWith("}").
        // PISTA: def.trim() para tolerar espacios; startsWith/endsWith.
        // OJO: el test manda "{\"a\":1}" (un índice de un campo) y espera true.
        // CULTURA: sin índice, una query escanea TODA la colección (17.5).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esIndiceCompuestoValido");
    }

    /**
     * RETO EXTRA 05: Determina si el valor mapeado empieza por vocal.
     */
    public static boolean esCampoVocal(String val) {
        // GUÍA: utilidad de cadenas (empieza por vocal).
        // 1. Si val es null o vacío -> false.
        // 2. Coge el primer carácter en minúscula: Character.toLowerCase(val.charAt(0)).
        // 3. Comprueba si está en "aeiou".
        // PISTA: "aeiou".indexOf(Character.toLowerCase(val.charAt(0))) >= 0.
        // OJO: el test manda "ada" y espera true (empieza por 'a').
        // CULTURA: trivial, pero te obliga a no olvidar el guardia de null/vacío.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esCampoVocal");
    }

    /**
     * RETO EXTRA 06: Verifica si la representacion cumple la sintaxis JSON estandar.
     */
    public static boolean esDocumentoJsonValido(String doc) {
        // GUÍA: validación ligera de JSON (un documento BSON es JSON).
        // 1. Si doc es null -> false.
        // 2. Versión sencilla: comprueba que empieza por '{' y acaba por '}'
        //    (objeto) tras un trim. "{}" cumple.
        // 3. (Opcional avanzado) cuenta llaves abiertas vs cerradas para que
        //    estén balanceadas.
        // PISTA: doc.trim().startsWith("{") && doc.trim().endsWith("}").
        // OJO: el test manda "{}" (objeto vacío) y espera true.
        // CULTURA: reutiliza la idea del reto 4; ambos validan "forma de objeto".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esDocumentoJsonValido");
    }

    /**
     * RETO EXTRA 07: Genera un BSON/JSON simple a mano.
     */
    public static String crearDocumentoSimple(String k, String v) {
        // GUÍA: construir un JSON {"clave":"valor"} a mano.
        // 1. Devuelve la cadena "{" + comillas + k + comillas + ":" + comillas + v + comillas + "}".
        // 2. Las comillas dobles dentro de un String Java se escriben \".
        // PISTA: return "{\"" + k + "\":\"" + v + "\"}";
        // OJO: el test compara con equals EXACTO contra {"k":"v"} (k="k", v="v").
        //      Ni un espacio de más: nada de "{ \"k\": \"v\" }".
        // CULTURA: así de literal es el BSON; en real lo serializa Jackson (b02/b07).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearDocumentoSimple");
    }

    /**
     * RETO EXTRA 08: Determina si la excepcion pertenece a la jerarquia del driver de Mongo.
     */
    public static boolean esExcepcionMongo(Throwable t) {
        // GUÍA: clasificar una excepción como "de Mongo".
        // 1. Si t es null -> false.
        // 2. Aquí no tenemos el driver real, así que el criterio es por mensaje:
        //    el texto contiene "mongo" (ignorando mayúsculas).
        // 3. Lee t.getMessage(); si es null trátalo como cadena vacía.
        // PISTA: msg != null && msg.toLowerCase().contains("mongo").
        // OJO: el test pasa new IllegalArgumentException("mongo") y espera true:
        //      NO mires el tipo de la clase, mira el MENSAJE.
        // CULTURA: con el driver real comprobarías instanceof MongoException.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esExcepcionMongo");
    }

    /**
     * RETO EXTRA 09: Indica si es un campo reservado de Mongo (como _id o _class).
     */
    public static boolean esPropiedadReservada(String prop) {
        // GUÍA: teoría 17.1 (campos reservados que Mongo/Spring gestionan solos).
        // 1. Si prop es null -> false.
        // 2. Los reservados típicos: "_id" (clave) y "_class" (el que añade
        //    Spring Data para saber a qué clase deserializar).
        // 3. Devuelve prop.equals("_id") || prop.equals("_class").
        // PISTA: o usa Set.of("_id","_class").contains(prop).
        // OJO: el test manda "_id" y espera true.
        // CULTURA: por eso @Id viaja como "_id" (17.1) y no debes pisar ese nombre.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esPropiedadReservada");
    }

    /**
     * RETO EXTRA 10: Genera la representacion BSON Date standard.
     */
    public static String formatearFechaMongo(java.time.Instant instant) {
        // GUÍA: BSON representa fechas como { "$date": "<ISO-8601>" } (Extended JSON).
        // 1. Si instant es null -> IllegalArgumentException (o devuelve "" según prefieras,
        //    pero el test siempre pasa un Instant.now() válido).
        // 2. instant.toString() ya da el ISO-8601 con 'Z' (java.time, teoría 1.10).
        // 3. Envuélvelo: "{\"$date\":\"" + instant + "\"}".
        // PISTA: el test solo exige que el resultado .contains("$date").
        // OJO: usa Instant directamente en el +; su toString() es ISO-8601 UTC.
        // CULTURA: este formato { $date } es el "Extended JSON" de Mongo (mongoexport).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearFechaMongo");
    }

}

/** Documento de pedido mapeado a la colección {@code pedidos}. */
@Document(collection = "pedidos")
record Pedido149(@Id String id, String cliente, double total) {
}