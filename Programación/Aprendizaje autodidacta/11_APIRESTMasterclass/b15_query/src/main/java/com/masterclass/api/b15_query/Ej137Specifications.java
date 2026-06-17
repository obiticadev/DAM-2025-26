package com.masterclass.api.b15_query;

import jakarta.persistence.*;
import jakarta.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio 137 · Specifications (predicados componibles con Criteria).
 *
 * <p>Teoría: {@code teoria/15_Consultas_Avanzadas.md} (sección 15.3).
 */
public final class Ej137Specifications {

    private final EntityManager em;

    public Ej137Specifications(EntityManager em) {
        this.em = em;
    }

    /**
     * Busca libros componiendo predicados con CriteriaBuilder.
     *
     * @param generoOpt   filtro opcional por género (null = ignorar)
     * @param paginasMin  filtro opcional páginas mínimas (null = ignorar)
     * @return libros que cumplen TODOS los predicados activos
     */
    public List<Libro137> buscar(String generoOpt, Integer paginasMin) {
        // TODO 1: obtén CriteriaBuilder con em.getCriteriaBuilder().
        // TODO 2: crea CriteriaQuery<Libro137> cq = cb.createQuery(Libro137.class).
        // TODO 3: Root<Libro137> root = cq.from(Libro137.class).
        // TODO 4: crea una List<Predicate> vacía.
        // TODO 5: si generoOpt != null, añade cb.equal(root.get("genero"), generoOpt).
        // TODO 6: si paginasMin != null, añade cb.ge(root.get("paginas"), paginasMin).
        // TODO 7: cq.where(cb.and(predicados.toArray(new Predicate[0]))).
        // TODO 8: cq.orderBy(cb.asc(root.get("id"))).
        // TODO 9: em.createQuery(cq).getResultList().
        // TODO 10: devuelve la lista (predicados combinados con AND).
        return List.of();
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }

    /**
     * Reto Extra 1: Comprueba si un filtro es nulo o vacio.
     */
    public static boolean esFiltroVacio(String f) {
        // GUÍA: teoría 15.3. Cada Specification decide si APORTA un predicado o no;
        //   un filtro vacío equivale a "no añadir condición" (cb.conjunction()).
        // 1. Una línea: return f == null || f.isBlank();
        // OJO: el test pide true para " " → usa isBlank(), no isEmpty(). false
        //   para "Ropa".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esFiltroVacio");
    }

    /**
     * Reto Extra 2: Comprueba si el precio minimo es valido.
     */
    public static boolean esPrecioValido(Double precio) {
        // GUÍA: una línea — return precio != null && precio > 0;
        // El test pide true para 10.0 y false para -5.0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esPrecioValido");
    }

    /**
     * Reto Extra 3: Genera una expresion LIKE de forma segura.
     */
    public static String formatearLike(String f) {
        // GUÍA: igual que en Ej136 — return "%" + f.trim().toLowerCase() + "%";
        // OJO: el test pasa "Ropa" y espera "%ropa%". CULTURA: en una Specification
        //   real esto alimenta a cb.like(cb.lower(root.get("nombre")), patron).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearLike");
    }

    /**
     * Reto Extra 4: Comprueba si el precio maximo es nulo.
     */
    public static boolean esPrecioNulo(Double p) {
        // GUÍA: una línea — return p == null;
        // El test pide true para null, false para 10.0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esPrecioNulo");
    }

    /**
     * Reto Extra 5: Comprueba si la categoria esta vacia.
     */
    public static boolean esCategoriaVacia(String c) {
        // GUÍA: reutiliza esFiltroVacio (reto 1).
        // 1. Una línea: return esFiltroVacio(c);
        // El test pide true para " ".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esCategoriaVacia");
    }

    /**
     * Reto Extra 6: Comprueba si se requiere filtrar por nombre.
     */
    public static boolean requiereFiltroNombre(String nombre) {
        // GUÍA: es el complemento de esFiltroVacio — reutilízalo.
        // 1. Una línea: return !esFiltroVacio(nombre);
        // El test pasa "Laptop" y espera true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para requiereFiltroNombre");
    }

    /**
     * Reto Extra 7: Normaliza el nombre de la categoria.
     */
    public static String normalizarCategoria(String cat) {
        // GUÍA: una línea — return cat == null ? null : cat.trim().toUpperCase();
        // ⚠ OJO: aquí se normaliza a MAYÚSCULAS (el test pasa "  Ropa  " y espera
        //   "ROPA"), al contrario que normalizarFiltro de Ej136 (que era a
        //   minúsculas). Mira siempre el valor exacto que pide el test.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para normalizarCategoria");
    }

    /**
     * Reto Extra 8: Comprueba si hay especificaciones activas.
     */
    public static boolean tieneEspecificaciones(String nombre, Double min, Double max) {
        // GUÍA: hay specs activas si CUALQUIER filtro está presente (OR).
        // 1. Una línea: return !esFiltroVacio(nombre) || min != null || max != null;
        // OJO: el test pide true para ("Laptop", null, null) y false para
        //   (null, null, null). Reutiliza esFiltroVacio para el nombre.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneEspecificaciones");
    }

    /**
     * Reto Extra 9: Compara dos filtros de precio de forma logica.
     */
    public static boolean preciosCoherentes(Double min, Double max) {
        // GUÍA: un rango es coherente si min <= max.
        // 1. Defensa: si min o max es null, considéralo coherente (no hay rango
        //    que contradecir) → return min == null || max == null || min <= max;
        // OJO: el test pide true para (10.0, 20.0) y false para (20.0, 10.0).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para preciosCoherentes");
    }

    /**
     * Reto Extra 10: Retorna representacion de especificaciones activas.
     */
    public static String formatearEspecificacion(String nom, Double min, Double max) {
        // GUÍA: String.format con tres huecos.
        // PISTA: String.format("Specs[Nom=%s, Min=%s, Max=%s]", nom, min, max);
        // OJO: el test espera EXACTAMENTE "Specs[Nom=Ropa, Min=10.0, Max=20.0]".
        //   %s sobre Double imprime "10.0"/"20.0". Usa el nombre tal cual.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearEspecificacion");
    }



}

@Entity
class Libro137 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String genero;
    private int paginas;

    public Libro137() {
    }

    public Libro137(String genero, int paginas) {
        this.genero = genero;
        this.paginas = paginas;
    }

    public String getGenero() {
        return genero;
    }

    public int getPaginas() {
        return paginas;
    }
}
