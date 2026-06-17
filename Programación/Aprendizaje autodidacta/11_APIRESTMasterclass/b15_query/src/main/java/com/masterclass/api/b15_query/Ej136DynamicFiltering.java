package com.masterclass.api.b15_query;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio 136 · Filtrado dinámico (WHERE construido según parámetros).
 *
 * <p>Teoría: {@code teoria/15_Consultas_Avanzadas.md} (sección 15.2).
 */
public final class Ej136DynamicFiltering {

    private final EntityManager em;

    public Ej136DynamicFiltering(EntityManager em) {
        this.em = em;
    }

    /**
     * Busca coches filtrando solo por los parámetros NO nulos.
     *
     * @param marca    filtro opcional por marca exacta (null = no filtrar)
     * @param precioMax filtro opcional precio máximo (null = no filtrar)
     * @return coches que cumplen los filtros activos
     */
    public List<Coche136> buscar(String marca, Double precioMax) {
        List<String> condiciones = new ArrayList<>();
        // TODO 1: empieza con un JPQL base "select c from Coche136 c".
        // TODO 2: si 'marca' != null, añade la condición "c.marca = :marca" a 'condiciones'.
        // TODO 3: si 'precioMax' != null, añade "c.precio <= :precioMax".
        // TODO 4: si hay condiciones, únelas con " and " y antepón " where ".
        // TODO 5: añade " order by c.id" al final.
        // TODO 6: crea la query tipada con el JPQL resultante.
        // TODO 7: setParameter SOLO de los parámetros que realmente se usaron.
        // TODO 8: usa parámetros con nombre (NUNCA concatenes valores).
        // TODO 9: getResultList().
        // TODO 10: si no hay filtros, devuelve todos (where ausente).
        return List.of();
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }

    /**
     * Reto Extra 1: Comprueba si un filtro es nulo o vacio.
     */
    public static boolean esFiltroVacio(String f) {
        // GUÍA: teoría 15.2. Decidir si un filtro "cuenta" es el primer paso del
        //   WHERE dinámico (TODO 2/3 de buscar()): un filtro vacío = no filtrar.
        // 1. Una línea: return f == null || f.isBlank();
        // OJO: el test pide true para " " (un espacio) → usa isBlank(), NO
        //   isEmpty() (isEmpty(" ") es false). false para "Ropa".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esFiltroVacio");
    }

    /**
     * Reto Extra 2: Comprueba si un filtro numerico es valido (positivo).
     */
    public static boolean esPrecioValido(Double precio) {
        // GUÍA: una línea — return precio != null && precio > 0;
        // OJO: el test pide true para 10.0 y false para -5.0. Comprueba null
        //   ANTES de comparar (precio es Double, podría llegar null).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esPrecioValido");
    }

    /**
     * Reto Extra 3: Genera una clausula select base de JPQL.
     */
    public static String selectBase() {
        // GUÍA: una línea — return "select p from Prod136 p";
        // ⚠ CUIDADO: el test espera literalmente "select p from Prod136 p" (con
        //   "Prod136", aunque la entidad real de este ejercicio sea Coche136).
        //   Devuelve la cadena EXACTA que pide el test, se compara con equals.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para selectBase");
    }

    /**
     * Reto Extra 4: Comprueba si el filtro de precio es nulo.
     */
    public static boolean esPrecioNulo(Double precio) {
        // GUÍA: una línea — return precio == null;
        // El test pide true para null y false para 10.0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esPrecioNulo");
    }

    /**
     * Reto Extra 5: Comprueba si el filtro de categoria es nulo o vacio.
     */
    public static boolean esCategoriaVacia(String cat) {
        // GUÍA: misma lógica que esFiltroVacio (reto 1) — reutilízalo.
        // 1. Una línea: return esFiltroVacio(cat);  (o cat == null || cat.isBlank())
        // El test pide true para " ".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esCategoriaVacia");
    }

    /**
     * Reto Extra 6: Limpia e normaliza un filtro de texto.
     */
    public static String normalizarFiltro(String f) {
        // GUÍA: una línea — return f == null ? null : f.trim().toLowerCase();
        // OJO: el test pasa "  Ropa  " y espera "ropa" (trim + MINÚSCULAS).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para normalizarFiltro");
    }

    /**
     * Reto Extra 7: Comprueba si se debe aplicar algun filtro.
     */
    public static boolean debeFiltrar(String cat, Double maxPrecio) {
        // GUÍA: hay que filtrar si HAY al menos un filtro activo. Reutiliza los
        //   retos 5 y 4.
        // 1. Una línea: return !esCategoriaVacia(cat) || maxPrecio != null;
        // OJO: el test pide true para ("Ropa", null) y false para (null, null).
        //   Es un OR: basta con un filtro presente.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para debeFiltrar");
    }

    /**
     * Reto Extra 8: Retorna un parametro de tipo LIKE formateado (%valor%).
     */
    public static String formatearLike(String f) {
        // GUÍA: envuelve el valor normalizado entre comodines % para un LIKE.
        // PISTA: return "%" + f.trim().toLowerCase() + "%";
        //   (o reutiliza: "%" + normalizarFiltro(f) + "%")
        // OJO: el test pasa "Ropa" y espera "%ropa%" (en minúsculas, con un % a
        //   cada lado). CULTURA: el % busca "contiene"; %valor (sin % final)
        //   sería "termina en"; valor% sería "empieza por".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearLike");
    }

    /**
     * Reto Extra 9: Comprueba si dos filtros de categoria son iguales sin importar mayusculas.
     */
    public static boolean sonCategoriasIguales(String c1, String c2) {
        // GUÍA: una línea — return c1 != null && c1.equalsIgnoreCase(c2);
        // OJO: el test pasa ("Ropa", "ROPA") y espera true. Comprueba c1 != null
        //   primero para no romper si llega null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para sonCategoriasIguales");
    }

    /**
     * Reto Extra 10: Retorna una representacion de texto de los filtros activos.
     */
    public static String formatearFiltrosActivos(String cat, Double maxPrecio) {
        // GUÍA: String.format con la categoría (cruda) y el precio.
        // PISTA: String.format("Filtros[Cat=%s, MaxPrecio=%s]", cat, maxPrecio);
        // OJO: el test espera EXACTAMENTE "Filtros[Cat=Ropa, MaxPrecio=10.0]".
        //   %s sobre un Double 10.0 imprime "10.0". Usa la categoría TAL CUAL
        //   ("Ropa", sin normalizar).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearFiltrosActivos");
    }



}

@Entity
class Coche136 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String marca;
    private double precio;

    public Coche136() {
    }

    public Coche136(String marca, double precio) {
        this.marca = marca;
        this.precio = precio;
    }

    public String getMarca() {
        return marca;
    }

    public double getPrecio() {
        return precio;
    }
}
