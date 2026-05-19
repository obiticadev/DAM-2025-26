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
}
