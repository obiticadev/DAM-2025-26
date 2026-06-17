package com.masterclass.api.b17_nosql;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Ejercicio 150 · CRUD declarativo estilo {@code MongoRepository}.
 *
 * <p>Teoría: {@code teoria/17_NoSQL_MongoDB.md} (sección 17.2).
 *
 * <p>Sin Mongo real, {@link RepoEnMemoria150} simula un
 * {@code MongoRepository<Pedido149, String>} usando un {@code Map} interno:
 * implementa {@code save}, {@code findById}, {@code findAll},
 * {@code deleteById} y un query method derivado.
 */
public final class Ej150MongoRepository {

    private Ej150MongoRepository() {
    }

    public static void main(String[] args) {
        RepoEnMemoria150 repo = new RepoEnMemoria150();
        repo.save(new Pedido149("p1", "ana", 50));
        System.out.println(repo.findById("p1"));
        System.out.println(repo.findByCliente("ana"));
    }

        /**
     * RETO EXTRA 01: Verifica si el patron regex de busqueda es correcto.
     */
    public static boolean esFiltroRegexValido(String regex) {
        // GUÍA: teoría 17.3 (Mongo permite filtrar con $regex; valida que el patrón compile).
        // 1. Si regex es null o vacío -> false.
        // 2. Intenta compilarlo: java.util.regex.Pattern.compile(regex).
        // 3. Envuélvelo en try/catch (PatternSyntaxException): si lanza -> false;
        //    si compila -> true.
        // PISTA: try { Pattern.compile(regex); return true; } catch (Exception e) { return false; }
        // OJO: el test manda "^Ada" (regex válida que ancla al inicio) y espera true.
        // CULTURA: { campo: { $regex: "^Ada" } } busca "empieza por Ada" en Mongo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esFiltroRegexValido");
    }

    /**
     * RETO EXTRA 02: Valida limites de filtro de edad en queries.
     */
    public static boolean esRangoEdadValido(int min, int max) {
        // GUÍA: validar un rango [min, max] para un filtro de edad.
        // 1. min no puede ser negativo (una edad < 0 no existe).
        // 2. max no puede ser absurdo (pon un techo razonable, p.ej. <= 150).
        // 3. min debe ser <= max (rango bien orientado).
        // PISTA: return min >= 0 && max <= 150 && min <= max;
        // OJO: el test manda (18, 99) y espera true; (debería dar false con
        //      min negativo o min > max, aunque el test no lo compruebe).
        // CULTURA: equivaldría a { edad: { $gte: min, $lte: max } } (17.3).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esRangoEdadValido");
    }

    /**
     * RETO EXTRA 03: Une dos condiciones BSON en una operacion $and.
     */
    public static String combinarFiltrosY(String f1, String f2) {
        // GUÍA: teoría 17.3 ($and combina varias condiciones en un array).
        // 1. Construye el JSON {"$and":[f1,f2]} concatenando las dos cadenas.
        // PISTA: return "{\"$and\":[" + f1 + "," + f2 + "]}";
        // OJO: el test compara EXACTO con {"$and":[f1,f2]} (f1="f1", f2="f2");
        //      sin espacios y con las dos cadenas separadas por una sola coma.
        // CULTURA: { $and: [ {a:1}, {b:2} ] } exige que TODAS se cumplan (AND).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para combinarFiltrosY");
    }

    /**
     * RETO EXTRA 04: Determina si la consulta no tiene restricciones (documento vacio).
     */
    public static boolean esFiltroVacio(String filter) {
        // GUÍA: un filtro vacío {} en Mongo significa "trae todo" (sin restricción).
        // 1. Si filter es null -> false (no es un filtro vacío, es ausencia).
        // 2. Quita espacios y compara con "{}": filter.trim().equals("{}").
        // PISTA: return filter != null && filter.trim().equals("{}");
        // OJO: el test manda "{}" y espera true.
        // CULTURA: db.coleccion.find({}) es el equivalente a un SELECT * (17.2).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esFiltroVacio");
    }

    /**
     * RETO EXTRA 05: Obtiene el valor del identificador dentro de un BSON string.
     */
    public static String extraerCampoObjectId(String bsonDoc) {
        // GUÍA: extraer el valor de "_id" de un BSON tipo {"_id":"123"}.
        // 1. Si bsonDoc es null o no contiene "_id" -> devuelve "" o null.
        // 2. Localiza la subcadena tras "_id": el valor va entre las comillas
        //    que siguen a los dos puntos.
        // 3. Truco robusto: parte por "_id" y luego quédate con lo que hay entre
        //    el primer y el segundo '"' posteriores.
        // PISTA: usa indexOf("\"", desde) dos veces y substring entre ambos,
        //    o una regex "\"_id\":\"([^\"]+)\"" con Matcher.group(1).
        // OJO: el test manda {"_id":"123"} y espera EXACTAMENTE "123" (sin comillas).
        // CULTURA: lo que de verdad hace Jackson/BSON al deserializar (17.1).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extraerCampoObjectId");
    }

    /**
     * RETO EXTRA 06: Verifica que el salto y limite cumplan margenes seguros de memoria.
     */
    public static boolean esConsultaPaginadaSegura(int skip, int limit) {
        // GUÍA: validar paginación skip/limit (proteger memoria; conecta con b15).
        // 1. skip no puede ser negativo.
        // 2. limit debe ser positivo y con un techo (p.ej. <= 1000) para no
        //    traer la colección entera de golpe.
        // PISTA: return skip >= 0 && limit > 0 && limit <= 1000;
        // OJO: el test manda (0, 50) y espera true.
        // CULTURA: en Mongo es .skip(n).limit(m); skip grande es CARO (recorre y
        //    descarta). En producción se prefiere keyset pagination (b15, Ej142).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esConsultaPaginadaSegura");
    }

    /**
     * RETO EXTRA 07: Determina si el fallo ocurrio por sintaxis invalida en consulta.
     */
    public static boolean esExcepcionDeQueryMongo(Throwable t) {
        // GUÍA: clasificar una excepción como "error de query" (por mensaje).
        // 1. Si t es null -> false.
        // 2. Criterio: t.getMessage() contiene "query" (ignorando mayúsculas).
        // PISTA: msg != null && msg.toLowerCase().contains("query");
        //    reutiliza el patrón de esExcepcionMongo del Ej149 reto 8.
        // OJO: el test pasa new IllegalArgumentException("query") y espera true:
        //      mira el MENSAJE, no el tipo de la clase.
        // CULTURA: con el driver real sería instanceof MongoQueryException.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esExcepcionDeQueryMongo");
    }

    /**
     * RETO EXTRA 08: Genera la query geo-JSON de cercania.
     */
    public static String crearQueryGeospatial(double lon, double lat, double maxDist) {
        // GUÍA: construir una query geoespacial $near de Mongo.
        // 1. La forma real es:
        //    { "location": { "$near": { "$geometry": { "type":"Point",
        //      "coordinates":[lon,lat] }, "$maxDistance": maxDist } } }
        // 2. Constrúyela con concatenación de String + los tres double.
        // PISTA: el test SOLO exige que el resultado .contains("$near"); con que
        //    incluyas "$near" y los valores cuadra. No te obsesiones con el formato.
        // OJO: el orden GeoJSON es [longitud, latitud] (lon ANTES que lat).
        // CULTURA: requiere un índice 2dsphere sobre el campo de localización.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearQueryGeospatial");
    }

    /**
     * RETO EXTRA 09: Verifica si la cadena es un operador de consulta de Mongo valido.
     */
    public static boolean esOperadorMongoValido(String op) {
        // GUÍA: teoría 17.3 (operadores de consulta: $gt, $lt, $gte, $lte, $in, $eq...).
        // 1. Si op es null -> false.
        // 2. Comprueba que esté en el conjunto de operadores conocidos.
        // PISTA: Set.of("$gt","$lt","$gte","$lte","$eq","$ne","$in","$nin",
        //    "$and","$or").contains(op).
        // OJO: el test manda "$gt" y espera true; un operador debe empezar por '$'.
        // CULTURA: tabla de operadores de la teoría 17.3.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esOperadorMongoValido");
    }

    /**
     * RETO EXTRA 10: Resuelve la direccion del orden (1 asc, -1 desc).
     */
    public static String extraerOrdenacionDeQuery(String sortJson) {
        // GUÍA: en Mongo el sort se expresa { campo: 1 } (asc) o { campo: -1 } (desc).
        // 1. Si sortJson es null -> devuelve "" o lanza (el test siempre da válido).
        // 2. Si contiene "-1" -> devuelve "DESC"; si contiene "1" -> "ASC".
        // 3. Comprueba "-1" ANTES que "1" (porque "-1" también contiene "1").
        // PISTA: return sortJson.contains("-1") ? "DESC" : "ASC";
        // OJO: el test manda {"edad":1} y espera EXACTAMENTE "ASC" (mayúsculas).
        // CULTURA: es lo que traduce Sort.Direction.ASC/DESC de Spring Data (b15).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extraerOrdenacionDeQuery");
    }

}

/** Repositorio en memoria que imita el contrato de {@code MongoRepository}. */
class RepoEnMemoria150 {

    private final Map<String, Pedido149> almacen = new java.util.LinkedHashMap<>();

    /**
     * Inserta o actualiza por id (upsert), como {@code MongoRepository.save}.
     *
     * @param pedido pedido a guardar (no null, id no null)
     * @return el pedido guardado
     */
    Pedido149 save(Pedido149 pedido) {
        // TODO 1: si pedido es null -> IllegalArgumentException.
        // TODO 2: si pedido.id() es null o vacío -> IllegalArgumentException
        //         (en Mongo real Spring generaría el _id; aquí lo exigimos).
        // TODO 3: usa pedido.id() como clave del mapa.
        // TODO 4: si la clave ya existe, save SOBRESCRIBE (upsert), no duplica.
        // TODO 5: inserta/actualiza la entrada en 'almacen'.
        // TODO 6: save devuelve la entidad persistida (la misma instancia aquí).
        // TODO 7: no devuelvas null.
        // TODO 8: documenta que el orden de inserción se conserva (LinkedHashMap).
        // TODO 9: no muta otros documentos.
        // TODO 10: retorna el pedido guardado.
        return pedido;
    }

    /**
     * Busca por id, como {@code findById}.
     *
     * @param id clave a buscar
     * @return Optional con el pedido, o vacío si no existe
     */
    Optional<Pedido149> findById(String id) {
        // TODO 1: si id es null devuelve Optional.empty() (no lances excepción).
        // TODO 2: consulta el mapa 'almacen' por la clave id.
        // TODO 3: si no existe la clave, devuelve Optional.empty().
        // TODO 4: si existe, envuélvelo en Optional.of.
        // TODO 5: usa Optional.ofNullable para cubrir ambos casos en una línea.
        // TODO 6: no expongas el mapa interno.
        // TODO 7: la búsqueda por _id en Mongo usa el índice por defecto (O(1) lógico).
        // TODO 8: no modifiques el almacén al leer.
        // TODO 9: ids inexistentes nunca devuelven null crudo.
        // TODO 10: retorna el Optional.
        return Optional.empty();
    }

    /**
     * Devuelve todos los documentos, como {@code findAll}.
     *
     * @return lista (posiblemente vacía) de todos los pedidos
     */
    List<Pedido149> findAll() {
        // TODO 1: si el almacén está vacío devuelve lista vacía (nunca null).
        // TODO 2: recoge los valores del mapa.
        // TODO 3: respeta el orden de inserción.
        // TODO 4: devuelve una copia (no la vista interna del mapa).
        // TODO 5: la lista devuelta no debe permitir modificar el repo.
        // TODO 6: usa List.copyOf sobre almacen.values().
        // TODO 7: findAll sin filtro escanea toda la colección (coste O(n)).
        // TODO 8: no ordenes por id salvo que se pida (aquí no se pide).
        // TODO 9: no dupliques elementos.
        // TODO 10: retorna la lista.
        return List.of();
    }

    /**
     * Elimina por id, como {@code deleteById}.
     *
     * @param id clave a eliminar
     * @return true si existía y se eliminó, false si no existía
     */
    boolean deleteById(String id) {
        // TODO 1: si id es null devuelve false (nada que borrar).
        // TODO 2: comprueba si la clave existe en el almacén.
        // TODO 3: si no existe, devuelve false sin tocar nada.
        // TODO 4: si existe, elimínala del mapa.
        // TODO 5: Map.remove devuelve el valor previo o null: úsalo.
        // TODO 6: devuelve true si remove devolvió no-null.
        // TODO 7: deleteById en Mongo es idempotente: borrar lo ya borrado no falla.
        // TODO 8: no lances excepción por id inexistente.
        // TODO 9: no afectes a otros documentos.
        // TODO 10: retorna el booleano resultado.
        return false;
    }

    /**
     * Query method derivado: {@code findByCliente}.
     *
     * @param cliente valor exacto del campo cliente
     * @return pedidos cuyo campo cliente coincide exactamente
     */
    List<Pedido149> findByCliente(String cliente) {
        // TODO 1: si cliente es null devuelve lista vacía.
        // TODO 2: recorre todos los valores del almacén.
        // TODO 3: filtra los que tengan pedido.cliente() igual a 'cliente'.
        // TODO 4: usa equals (comparación exacta, no contains).
        // TODO 5: Spring traduciría esto a un filtro { cliente: ? } en Mongo.
        // TODO 6: respeta el orden de inserción en el resultado.
        // TODO 7: recoge a List.
        // TODO 8: si ninguno coincide, devuelve lista vacía (no null).
        // TODO 9: devuelve una lista no modificable.
        // TODO 10: retorna la lista filtrada.
        return List.of();
    }
}