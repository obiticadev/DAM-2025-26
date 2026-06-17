package com.masterclass.api.b15_query;

import jakarta.persistence.*;
import jakarta.persistence.criteria.*;

/**
 * Ejercicio 138 · Criteria API tipada (agregado).
 *
 * <p>Teoría: {@code teoria/15_Consultas_Avanzadas.md} (sección 15.3).
 */
public final class Ej138CriteriaApi {

    private final EntityManager em;

    public Ej138CriteriaApi(EntityManager em) {
        this.em = em;
    }

    /**
     * Suma el importe de las ventas de una región usando Criteria.
     *
     * @param region región a filtrar
     * @return suma de importes (0.0 si no hay)
     */
    public double totalPorRegion(String region) {
        // TODO 1: CriteriaBuilder cb = em.getCriteriaBuilder().
        // TODO 2: CriteriaQuery<Double> cq = cb.createQuery(Double.class).
        // TODO 3: Root<Venta138> root = cq.from(Venta138.class).
        // TODO 4: cq.select(cb.sum(root.get("importe"))) (agregado tipado).
        // TODO 5: cq.where(cb.equal(root.get("region"), region)).
        // TODO 6: ejecuta em.createQuery(cq).getSingleResult().
        // TODO 7: el resultado puede ser null si no hay filas.
        // TODO 8: si es null, devuelve 0.0.
        // TODO 9: si no, devuelve el double.
        // TODO 10: Criteria es type-safe: errores de campo se ven antes (vs JPQL string).
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }

    /**
     * Reto Extra 1: Comprueba si un filtro es nulo o vacio.
     */
    public static boolean esFiltroVacio(String f) {
        // GUÍA: teoría 15.3 (Criteria type-safe). Mismo helper que en Ej136/137.
        // 1. Una línea: return f == null || f.isBlank();
        // El test pide true para " ", false para "Ropa".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esFiltroVacio");
    }

    /**
     * Reto Extra 2: Comprueba si un valor es positivo.
     */
    public static boolean esPrecioValido(Double precio) {
        // GUÍA: una línea — return precio != null && precio > 0;
        // El test pide true para 10.0, false para -5.0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esPrecioValido");
    }

    /**
     * Reto Extra 3: Retorna expresion LIKE normalizada.
     */
    public static String formatearLike(String f) {
        // GUÍA: igual que en Ej136/137 — return "%" + f.trim().toLowerCase() + "%";
        // El test pasa "Ropa" y espera "%ropa%".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearLike");
    }

    /**
     * Reto Extra 4: Comprueba si la categoria esta vacia.
     */
    public static boolean esCategoriaVacia(String cat) {
        // GUÍA: reutiliza esFiltroVacio — return esFiltroVacio(cat);
        // El test pide true para " ".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esCategoriaVacia");
    }

    /**
     * Reto Extra 5: Comprueba si el ID es valido (no nulo).
     */
    public static boolean esIdValido(Long id) {
        // GUÍA: una línea — return id != null;
        // El test pide true para 1L, false para null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esIdValido");
    }

    /**
     * Reto Extra 6: Comprueba si la lista de ordenacion es segura.
     */
    public static boolean esOrdenacionValida(String campo) {
        // GUÍA: misma idea de whitelist que Ej134, pero aquí no hay constante:
        //   defínela en línea.
        // 1. Una línea: return java.util.Set.of("id", "nombre", "precio").contains(campo);
        // OJO: el test pide true para "nombre" y false para "password". La lista
        //   blanca es la única defensa contra inyección al ordenar por Criteria.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esOrdenacionValida");
    }

    /**
     * Reto Extra 7: Normaliza el nombre de un campo.
     */
    public static String normalizarCampo(String c) {
        // GUÍA: una línea — return c == null ? null : c.trim().toLowerCase();
        // El test pasa "  Nombre  " y espera "nombre" (trim + minúsculas).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para normalizarCampo");
    }

    /**
     * Reto Extra 8: Comprueba si se debe filtrar por precio.
     */
    public static boolean debeFiltrarPrecio(Double precio) {
        // GUÍA: una línea — return precio != null;
        // El test pasa 10.0 y espera true (basta con que el filtro esté presente).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para debeFiltrarPrecio");
    }

    /**
     * Reto Extra 9: Compara de forma logica dos precios.
     */
    public static boolean rangoPreciosValido(Double min, Double max) {
        // GUÍA: igual que preciosCoherentes de Ej137.
        // 1. Una línea: return min == null || max == null || min <= max;
        // El test pide true para (10.0, 20.0) y false para (20.0, 10.0).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para rangoPreciosValido");
    }

    /**
     * Reto Extra 10: Retorna formato de criteria.
     */
    public static String formatearCriteria(String cat, Double maxPrecio) {
        // GUÍA: String.format con dos huecos.
        // PISTA: String.format("Criteria[Cat=%s, MaxPrecio=%s]", cat, maxPrecio);
        // OJO: el test espera EXACTAMENTE "Criteria[Cat=Ropa, MaxPrecio=10.0]".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearCriteria");
    }



}

@Entity
class Venta138 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String region;
    private double importe;

    public Venta138() {
    }

    public Venta138(String region, double importe) {
        this.region = region;
        this.importe = importe;
    }
}
