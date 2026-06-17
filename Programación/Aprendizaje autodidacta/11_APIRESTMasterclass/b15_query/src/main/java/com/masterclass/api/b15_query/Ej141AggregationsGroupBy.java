package com.masterclass.api.b15_query;

import jakarta.persistence.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Ejercicio 141 · Agregaciones y GROUP BY.
 *
 * <p>Teoría: {@code teoria/15_Consultas_Avanzadas.md} (sección 15.2).
 */
public final class Ej141AggregationsGroupBy {

    private final EntityManager em;

    public Ej141AggregationsGroupBy(EntityManager em) {
        this.em = em;
    }

    /**
     * Cuenta ventas agrupadas por categoría.
     *
     * @return mapa categoría → nº de ventas, ordenado por categoría
     */
    public Map<String, Long> conteoPorCategoria() {
        Map<String, Long> out = new LinkedHashMap<>();
        // TODO 1: JPQL "select v.categoria, count(v) from Venta141 v
        //         group by v.categoria order by v.categoria".
        // TODO 2: el resultado es List<Object[]> (cada fila: [categoria, count]).
        // TODO 3: createQuery(jpql, Object[].class).getResultList().
        // TODO 4: recorre cada Object[].
        // TODO 5: fila[0] es la categoría (String).
        // TODO 6: fila[1] es el count (Long).
        // TODO 7: mete cada par en 'out' (LinkedHashMap conserva el orden).
        // TODO 8: GROUP BY agrupa filas; count() cuenta por grupo.
        // TODO 9: si no hay ventas, devuelve mapa vacío.
        // TODO 10: devuelve el mapa.
        return out;
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }

    /**
     * Reto Extra 1: Obtiene el nombre del item de forma segura.
     */
    public static String obtenerNombre(Item141 i) {
        // GUÍA: teoría 15.2 (agregaciones). Acceso seguro a un campo del item.
        // 1. Una línea: return i == null ? null : i.getNombre();
        // El test crea new Item141("Laptop", "Tech", 100) y espera "Laptop".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerNombre");
    }

    /**
     * Reto Extra 2: Obtiene la categoria de forma segura.
     */
    public static String obtenerCategoria(Item141 i) {
        // GUÍA: una línea — return i == null ? null : i.getCategoria();
        // El test espera "Tech". CULTURA: la categoría es justo el campo por el
        //   que agrupa conteoPorCategoria() (el GROUP BY del ejercicio base).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerCategoria");
    }

    /**
     * Reto Extra 3: Obtiene el precio de forma segura.
     */
    public static double obtenerPrecio(Item141 i) {
        // GUÍA: una línea — return i == null ? 0.0 : i.getPrecio();
        // El test crea el item con precio 100.0 y espera 100.0 (delta 0.001).
        //   getPrecio() es double primitivo: si i es null, devuelve 0.0 por defensa.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerPrecio");
    }

    /**
     * Reto Extra 4: Crea un nuevo item.
     */
    public static Item141 crearItem(String nombre, String categoria, double precio) {
        // GUÍA: una línea — return new Item141(nombre, categoria, precio);
        // El test llama crearItem("PC", "Tech", 200.0) y comprueba assertNotNull.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearItem");
    }

    /**
     * Reto Extra 5: Comprueba si el item tiene ID.
     */
    public static boolean tieneId(Item141 i) {
        // GUÍA: una línea — return i != null && i.getId() != null;
        // OJO: el test espera FALSE (item recién creado, sin id).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneId");
    }

    /**
     * Reto Extra 6: Obtiene el ID del item de forma segura.
     */
    public static Long obtenerId(Item141 i) {
        // GUÍA: una línea — return i == null ? null : i.getId();
        // El test espera null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerId");
    }

    /**
     * Reto Extra 7: Normaliza el texto de los campos.
     */
    public static String normalizarTexto(String s) {
        // GUÍA: una línea — return s == null ? null : s.trim().toLowerCase();
        // El test pasa "  Laptop  " y espera "laptop".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para normalizarTexto");
    }

    /**
     * Reto Extra 8: Comprueba si el item es nuevo (ID nulo).
     */
    public static boolean esNuevo(Item141 i) {
        // GUÍA: una línea — return i != null && i.getId() == null;
        // El test crea el item sin id → true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esNuevo");
    }

    /**
     * Reto Extra 9: Comprueba si la categoria contiene una palabra clave.
     */
    public static boolean categoriaContiene(Item141 i, String keyword) {
        // GUÍA: igual que en Ej140 — comparación "contiene" en minúsculas.
        // 1. return i.getCategoria().toLowerCase().contains(keyword.toLowerCase());
        // El test: categoría "Tech", keyword "ch" → true. Defiende los nulos antes.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para categoriaContiene");
    }

    /**
     * Reto Extra 10: Retorna formato del item.
     */
    public static String formatearItem(Item141 i) {
        // GUÍA: String.format con nombre, categoría y precio.
        // PISTA: String.format("Item[Nombre=%s, Cat=%s, Precio=%s]",
        //            i.getNombre(), i.getCategoria(), i.getPrecio());
        // OJO: el test espera EXACTAMENTE "Item[Nombre=Laptop, Cat=Tech, Precio=100.0]".
        //   %s sobre un double 100.0 imprime "100.0".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearItem");
    }



}

/** Item para los retos extra (POJO, no entidad). El id queda null. */
class Item141 {
    private Long id;
    private String nombre;
    private String categoria;
    private double precio;

    public Item141(String nombre, String categoria, double precio) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPrecio() {
        return precio;
    }
}

@Entity
class Venta141 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoria;

    public Venta141() {
    }

    public Venta141(String categoria) {
        this.categoria = categoria;
    }
}
