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
        if (libro == null || libro.id() == null) {
            throw new IllegalArgumentException("Libro o ID no pueden ser nulos");
        }
    }

    /**
     * TODO extra 2: Guarda el libro en el mapa de memoria.
     */
    public static Libro desafioGuardarEnMemoria(java.util.Map<Long, Libro> db, Libro libro) {
        desafioValidarLibro(libro);
        db.put(libro.id(), libro);
        return libro;
    }

    /**
     * TODO extra 3: Busca un libro por ID en el mapa de memoria.
     */
    public static java.util.Optional<Libro> desafioBuscarPorId(java.util.Map<Long, Libro> db, Long id) {
        return java.util.Optional.ofNullable(db.get(id));
    }

    /**
     * TODO extra 4: Copia de forma segura los valores de una colección de libros.
     */
    public static java.util.List<Libro> desafioCopiarLista(java.util.Collection<Libro> libros) {
        return new java.util.ArrayList<>(libros);
    }

    /**
     * TODO extra 5: Elimina un libro del mapa por ID.
     */
    public static boolean desafioEliminarDeMemoria(java.util.Map<Long, Libro> db, Long id) {
        return db.remove(id) != null;
    }

    /**
     * TODO extra 6: Obtiene el tamaño del mapa de base de datos en memoria.
     */
    public static long desafioObtenerTamaño(java.util.Map<Long, Libro> db) {
        return db.size();
    }

    /**
     * TODO extra 7: Ejecuta el escenario guardando dos libros y borrando el primero.
     */
    public static long desafioEjecutarEscenario(Repository<Libro, Long> repo, Libro l1, Libro l2) {
        repo.save(l1);
        repo.save(l2);
        repo.deleteById(l1.id());
        return repo.count();
    }

    /**
     * TODO extra 8: Verifica si existe un libro por ID en el repositorio.
     */
    public static boolean desafioExisteLibro(Repository<Libro, Long> repo, Long id) {
        return repo.findById(id).isPresent();
    }

    /**
     * TODO extra 9: Guarda varios libros en lote en el repositorio.
     */
    public static void desafioGuardarVarios(Repository<Libro, Long> repo, java.util.List<Libro> libros) {
        for (Libro l : libros) {
            repo.save(l);
        }
    }

    /**
     * TODO extra 10: Obtiene una lista de títulos de todos los libros.
     */
    public static java.util.List<String> desafioObtenerTitulos(Repository<Libro, Long> repo) {
        return repo.findAll().stream().map(Libro::titulo).toList();
    }

}
