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
