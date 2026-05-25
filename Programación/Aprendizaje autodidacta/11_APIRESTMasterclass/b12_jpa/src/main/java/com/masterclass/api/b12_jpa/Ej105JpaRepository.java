package com.masterclass.api.b12_jpa;

import jakarta.persistence.EntityManager;
import java.util.List;

/**
 * Ejercicio 105 · CRUD estilo repositorio (lo que Spring Data hace por dentro).
 *
 * <p>Teoría: {@code teoria/12_Spring_Data_JPA.md} (sección 12.3).
 */
public final class Ej105JpaRepository {

    private final EntityManager em;

    public Ej105JpaRepository(EntityManager em) {
        this.em = em;
    }

    /**
     * @param t entidad a guardar (insert si id null, update si ya existe)
     * @return la entidad gestionada
     */
    public Tarea105 save(Tarea105 t) {
        // TODO 1: begin transaction.
        // TODO 2: si t.getId() == null -> em.persist(t); si no -> t = em.merge(t).
        // TODO 3: commit.
        // TODO 4: devuelve la entidad (la gestionada en caso de merge).
        return null;
    }

    /** @return entidad por id o null */
    public Tarea105 findById(Long id) {
        // TODO 5: em.find(Tarea105.class, id).
        return null;
    }

    /** @return todas, ordenadas por id (JPQL) */
    public List<Tarea105> findAll() {
        // TODO 6: em.createQuery("select t from Tarea105 t order by t.id", Tarea105.class)
        // TODO 7: .getResultList().
        return List.of();
    }

    /** @param id id a borrar; @return true si existía */
    public boolean deleteById(Long id) {
        // TODO 8: busca la entidad; si es null devuelve false.
        // TODO 9: begin tx, em.remove(entidad), commit.
        // TODO 10: devuelve true (se borró).
        return false;
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }

    /**
     * TODO extra 1: Comprueba si un repositorio JPA de tipo ProductoRepository está instanciado.
     */
    public static boolean desafioRepositoryActivo(ProductoRepository repo) {
        return repo != null;
    }

    /**
     * TODO extra 2: Guarda un producto en la base de datos usando el repositorio.
     */
    public static Producto desafioGuardarProducto(ProductoRepository repo, Producto p) {
        return repo.save(p);
    }

    /**
     * TODO extra 3: Busca un producto por su identificador primario.
     */
    public static java.util.Optional<Producto> desafioBuscarPorId(ProductoRepository repo, Long id) {
        return repo.findById(id);
    }

    /**
     * TODO extra 4: Retorna una lista con todos los productos guardados.
     */
    public static java.util.List<Producto> desafioBuscarTodos(ProductoRepository repo) {
        return repo.findAll();
    }

    /**
     * TODO extra 5: Comprueba si existe un producto en base de datos dado su ID.
     */
    public static boolean desafioExistePorId(ProductoRepository repo, Long id) {
        return repo.existsById(id);
    }

    /**
     * TODO extra 6: Cuenta la cantidad total de productos persistidos.
     */
    public static long desafioContarProductos(ProductoRepository repo) {
        return repo.count();
    }

    /**
     * TODO extra 7: Elimina un producto de la base de datos por su ID.
     */
    public static void desafioEliminarPorId(ProductoRepository repo, Long id) {
        repo.deleteById(id);
    }

    /**
     * TODO extra 8: Valida que un producto no sea nulo antes de guardarlo.
     */
    public static void desafioValidarParaGuardar(Producto p) {
        if (p == null || p.getNombre() == null) {
            throw new IllegalArgumentException("Producto no válido");
        }
    }

    /**
     * TODO extra 9: Crea una instancia rápida de Producto con los valores básicos.
     */
    public static Producto desafioCrearInstanciaProducto(String nombre, double precio) {
        var p = new Producto();
        p.setNombre(nombre);
        p.setPrecio(precio);
        return p;
    }

    /**
     * TODO extra 10: Retorna verdadero si una lista de productos contiene al menos un elemento.
     */
    public static boolean desafioTieneElementos(java.util.List<Producto> productos) {
        return productos != null && !productos.isEmpty();
    }

}
