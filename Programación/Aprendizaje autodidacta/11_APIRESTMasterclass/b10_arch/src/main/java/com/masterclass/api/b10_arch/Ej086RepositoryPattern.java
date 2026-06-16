package com.masterclass.api.b10_arch;

import java.util.List;
import java.util.Optional;

/**
 * Ejercicio 086 · Patrón Repository genérico.
 *
 * <p>Teoría: {@code teoria/10_Arquitectura_Patrones.md} (sección 10.2).
 */
public final class Ej086RepositoryPattern {

    /** Contrato Repository genérico. */
    public interface Repository<T, ID> {
        T save(T entity);
        Optional<T> findById(ID id);
        List<T> findAll();
        boolean deleteById(ID id);
        long count();
    }

    public record Libro(Long id, String titulo) {
    }

    /** Implementación en memoria del Repository de Libro. */
    public static class LibroRepositoryMem implements Repository<Libro, Long> {
        private final java.util.Map<Long, Libro> db = new java.util.LinkedHashMap<>();

        @Override
        public Libro save(Libro entity) {
            // TODO 1: valida entity y entity.id() no null.
            // TODO 2: guarda en 'db' usando el id como clave (upsert).
            // TODO 3: devuelve la entidad guardada.
            return null;
        }

        @Override
        public Optional<Libro> findById(Long id) {
            // TODO 4: devuelve Optional.ofNullable del valor en 'db'.
            return Optional.empty();
        }

        @Override
        public List<Libro> findAll() {
            // TODO 5: devuelve una copia (nueva List) de db.values().
            return List.of();
        }

        @Override
        public boolean deleteById(Long id) {
            // TODO 6: db.remove(id); devuelve si había algo.
            return false;
        }

        @Override
        public long count() {
            // TODO 7: devuelve db.size() como long.
            // TODO 8: count debe reflejar el estado tras saves/deletes.
            return -1;
        }
    }

    private Ej086RepositoryPattern() {
    }

    /**
     * Demuestra que el cliente solo depende de la INTERFAZ, no de la impl.
     *
     * @param repo cualquier Repository de Libro
     * @return número de libros tras guardar dos y borrar uno
     */
    public static long escenario(Repository<Libro, Long> repo) {
        // TODO 9: guarda dos libros (id 1 y 2), borra el 1.
        // TODO 10: devuelve repo.count() (debe ser 1). El cliente no sabe la impl.
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(escenario(new LibroRepositoryMem()));
    }

    /**
     * TODO extra 1: Valida que el libro y su id no sean nulos.
     */
    public static void desafioValidarLibro(Libro libro) {
        // GUÍA: un Repository necesita la clave para indexar, así que valida
        // ambos. OJO: el test exige excepción tanto con null como con
        // new Libro(null, "Test") (id nulo) — por eso son DOS comprobaciones con
        // ||, no solo libro==null.
        if (libro == null || libro.id() == null) {
            throw new IllegalArgumentException("Libro o ID no pueden ser nulos");
        }
    }

    /**
     * TODO extra 2: Guarda el libro en el mapa de memoria.
     */
    public static Libro desafioGuardarEnMemoria(java.util.Map<Long, Libro> db, Libro libro) {
        // GUÍA: reutiliza desafioValidarLibro (reto 1) antes de tocar el mapa.
        // db.put es un UPSERT: la misma clave inserta o reemplaza. El test
        // guarda Libro(1,"Clean Code") y comprueba que db.get(1L) lo devuelve.
        desafioValidarLibro(libro);
        db.put(libro.id(), libro);
        return libro;
    }

    /**
     * TODO extra 3: Busca un libro por ID en el mapa de memoria.
     */
    public static java.util.Optional<Libro> desafioBuscarPorId(java.util.Map<Long, Libro> db, Long id) {
        // GUÍA: estilo Repository (teoría 10.2) — devuelve Optional, NUNCA null.
        // ofNullable convierte el null de db.get(id) en Optional.empty(). El test
        // espera present con 1L (guardado) y empty con 2L. Contrasta con el DAO
        // del Ej087, que devuelve null directamente.
        return java.util.Optional.ofNullable(db.get(id));
    }

    /**
     * TODO extra 4: Copia de forma segura los valores de una colección de libros.
     */
    public static java.util.List<Libro> desafioCopiarLista(java.util.Collection<Libro> libros) {
        // GUÍA: error nº 4 del bloque — findAll nunca devuelve la vista interna
        // del mapa, sino una COPIA, para que quien la reciba no corrompa tu
        // estado. El test exige equals(list) (mismos elementos) Y assertNotSame
        // (objeto distinto): por eso new ArrayList<>(libros), no devolver libros.
        return new java.util.ArrayList<>(libros);
    }

    /**
     * TODO extra 5: Elimina un libro del mapa por ID.
     */
    public static boolean desafioEliminarDeMemoria(java.util.Map<Long, Libro> db, Long id) {
        // GUÍA: Map.remove devuelve el valor previo (o null si no había). Comparar
        // != null te dice si REALMENTE existía. El test borra el 1L (true) y luego
        // intenta el 2L inexistente (false). Truco clásico que evita un
        // containsKey + remove en dos pasos.
        return db.remove(id) != null;
    }

    /**
     * TODO extra 6: Obtiene el tamaño del mapa de base de datos en memoria.
     */
    public static long desafioObtenerTamaño(java.util.Map<Long, Libro> db) {
        // GUÍA: count() del Repository = size() del mapa. db.size() es int y se
        // promociona a long sin más. El test comprueba 0 con el mapa vacío y 1
        // tras un put: el contador debe reflejar el estado real tras cada
        // save/delete.
        return db.size();
    }

    /**
     * TODO extra 7: Ejecuta el escenario guardando dos libros y borrando el primero.
     */
    public static long desafioEjecutarEscenario(Repository<Libro, Long> repo, Libro l1, Libro l2) {
        // GUÍA: la clave del patrón — el cliente programa contra la INTERFAZ
        // Repository, sin saber si detrás hay memoria, JDBC o JPA. Guarda 2,
        // borra 1, cuenta → el test espera 1. Es la misma lógica que el método
        // base escenario(); aquí parametrizada para inyectar los libros.
        repo.save(l1);
        repo.save(l2);
        repo.deleteById(l1.id());
        return repo.count();
    }

    /**
     * TODO extra 8: Verifica si existe un libro por ID en el repositorio.
     */
    public static boolean desafioExisteLibro(Repository<Libro, Long> repo, Long id) {
        // GUÍA: existsById sin método propio — findById(id).isPresent(). Aquí
        // isPresent() SÍ es idiomático: solo quieres saber si hay valor, no
        // usarlo (a diferencia del anti-patrón isPresent()+get()). Test: true
        // para 1L guardado, false para 2L.
        return repo.findById(id).isPresent();
    }

    /**
     * TODO extra 9: Guarda varios libros en lote en el repositorio.
     */
    public static void desafioGuardarVarios(Repository<Libro, Long> repo, java.util.List<Libro> libros) {
        // GUÍA: saveAll a mano — recorre y guarda cada uno. El test mete 2 libros
        // y espera count()==2. (En Spring Data esto sería repo.saveAll(libros)
        // en una sola llamada; el patrón es idéntico.)
        for (Libro l : libros) {
            repo.save(l);
        }
    }

    /**
     * TODO extra 10: Obtiene una lista de títulos de todos los libros.
     */
    public static java.util.List<String> desafioObtenerTitulos(Repository<Libro, Long> repo) {
        // GUÍA: proyección — de entidades a un campo (teoría 1.3). map(Libro::titulo)
        // transforma cada Libro en su título. OJO al ORDEN: el test espera
        // List.of("A","B"), así que la impl debe preservar el orden de inserción
        // (LibroRepositoryMem usa LinkedHashMap precisamente por esto).
        return repo.findAll().stream().map(Libro::titulo).toList();
    }

}
