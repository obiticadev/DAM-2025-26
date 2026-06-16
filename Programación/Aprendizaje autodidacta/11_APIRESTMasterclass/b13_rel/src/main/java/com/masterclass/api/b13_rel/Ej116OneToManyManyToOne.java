package com.masterclass.api.b13_rel;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio 116 · @OneToMany / @ManyToOne.
 *
 * <p>Teoría: {@code teoria/13_Relaciones_JPA.md} (sección 13.1).
 */
public final class Ej116OneToManyManyToOne {

    private Ej116OneToManyManyToOne() {
    }

    /**
     * Guarda un pedido con sus líneas y cuenta las líneas tras recargar.
     *
     * @param em EntityManager
     * @param p  pedido con líneas añadidas
     * @return número de líneas persistidas
     */
    public static int guardarYContarLineas(EntityManager em, Pedido116 p) {
        // TODO 1: begin tx, persist(p), commit (cascade persiste las líneas).
        // TODO 2: em.clear().
        // TODO 3: recarga el pedido por id.
        // TODO 4: devuelve el tamaño de su lista de líneas.
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }

    /**
     * Reto Extra 1: Cuenta el numero de lineas de un pedido.
     */
    public static int contarLineas(Pedido116 p) {
        // GUÍA: teoría 13.2 (el lado inverso @OneToMany es una colección normal).
        // 1. Protege p null (devuelve 0).
        // 2. Devuelve el tamaño de la colección de líneas.
        // PISTA: return p == null ? 0 : p.getLineas().size();
        // OJO: el test crea un pedido vacío y espera 0; tras addLinea, 1.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarLineas");
    }

    /**
     * Reto Extra 2: Comprueba si el pedido tiene al menos una linea.
     */
    public static boolean tieneLineas(Pedido116 p) {
        // GUÍA: teoría 13.2. Reutiliza contarLineas del reto 1.
        // 1. Devuelve true si hay al menos una línea.
        // PISTA: return contarLineas(p) > 0;   // o !p.getLineas().isEmpty()
        // OJO: depende de que addLinea (TODO 6/7 del ejercicio base) añada de verdad
        //      a la lista; si addLinea sigue vacío, este test no cambiará a true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneLineas");
    }

    /**
     * Reto Extra 3: Verifica si una linea tiene asociado el pedido correcto.
     */
    public static boolean esLineaSincronizada(Pedido116 p, Linea116 l) {
        // GUÍA: teoría 13.4 (sincronización: el lado dueño debe apuntar al padre).
        // 1. Protege los null.
        // 2. Comprueba que la FK del lado dueño apunta a este pedido: l.getPedido() == p.
        // PISTA: return p != null && l != null && l.getPedido() == p;
        // OJO: == aquí es correcto (misma instancia en memoria), no equals. El test añade
        //      la línea con addLinea, que debe hacer l.setPedido(this).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esLineaSincronizada");
    }

    /**
     * Reto Extra 4: Comprueba si el pedido contiene un producto por nombre.
     */
    public static boolean contieneProducto(Pedido116 p, String producto) {
        // GUÍA: teoría 13.2 + streams (1.3). Recorre las líneas buscando el producto.
        // 1. Protege p null.
        // 2. Usa anyMatch comparando el producto de cada línea con equals.
        // PISTA: p.getLineas().stream().anyMatch(l -> producto.equals(l.getProducto()));
        // OJO: cada Linea116 ya tiene getProducto(). El test espera "Laptop" true, "PC" false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contieneProducto");
    }

    /**
     * Reto Extra 5: Cuenta cuantos productos tienen un nombre mas largo que un valor.
     */
    public static int contarProductosConNombreLargo(Pedido116 p, int len) {
        // GUÍA: teoría 13.2 + streams con filter+count (1.3).
        // 1. Recorre las líneas, quédate con las de producto.length() > len.
        // 2. Devuelve cuántas quedan (int).
        // PISTA: (int) p.getLineas().stream()
        //            .filter(l -> l.getProducto() != null && l.getProducto().length() > len).count();
        // OJO: "Mouse"(5) y "Monitor"(7) con len=6 → solo "Monitor" pasa → 1. Es ESTRICTAMENTE mayor.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarProductosConNombreLargo");
    }

    /**
     * Reto Extra 6: Crea una linea de pedido.
     */
    public static Linea116 crearLinea(String producto) {
        // GUÍA: una línea — factory simple.
        // PISTA: return new Linea116(producto);
        // OJO: el test solo comprueba assertNotNull; no necesita más.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearLinea");
    }

    /**
     * Reto Extra 7: Obtiene el primer producto del pedido o null.
     */
    public static String obtenerPrimerProducto(Pedido116 p) {
        // GUÍA: teoría 13.2 + Optional/streams (1.2-1.3). "Primero o null".
        // 1. Si no hay líneas, devuelve null (no lances IndexOutOfBounds).
        // 2. Devuelve el producto de la primera línea.
        // PISTA: p.getLineas().stream().map(Linea116::getProducto).findFirst().orElse(null);
        // OJO: el test añade "Mouse" y espera "Mouse".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerPrimerProducto");
    }

    /**
     * Reto Extra 8: Comprueba si todos los productos no son nulos ni vacios.
     */
    public static boolean todosProductosValidos(Pedido116 p) {
        // GUÍA: teoría 13.2 + allMatch (1.3).
        // 1. Devuelve true si TODA línea tiene producto no nulo y no en blanco.
        // PISTA: p.getLineas().stream()
        //            .allMatch(l -> l.getProducto() != null && !l.getProducto().isBlank());
        // OJO: allMatch sobre lista vacía devuelve true (no es un fallo; el test usa 1 línea válida).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para todosProductosValidos");
    }

    /**
     * Reto Extra 9: Sincroniza bidireccionalmente un pedido y una linea.
     */
    public static void vincularLinea(Pedido116 p, Linea116 l) {
        // GUÍA: teoría 13.4 (sincronizar AMBOS lados). Es lo mismo que addLinea.
        // 1. Añade l a la lista del pedido (lado inverso).
        // 2. Fija el dueño: l.setPedido(p) (lado de la FK).
        // PISTA: reutiliza p.addLinea(l) si ya lo implementaste (TODO 6/7); si no:
        //        p.getLineas().add(l); l.setPedido(p);
        // OJO: el test comprueba l.getPedido() == p, así que el setPedido NO es opcional.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para vincularLinea");
    }

    /**
     * Reto Extra 10: Retorna una representacion de texto del pedido.
     */
    public static String formatearPedido(Pedido116 p) {
        // GUÍA: formateo con el formato EXACTO que compara el test.
        // 1. Compón "Pedido[Id=" + id + ", Lineas=" + nº de líneas + "]".
        // PISTA: return "Pedido[Id=" + p.getId() + ", Lineas=" + p.getLineas().size() + "]";
        // OJO: el test espera literalmente "Pedido[Id=null, Lineas=0]" (id null se imprime
        //      como "null"). Respeta mayúsculas, comas y espacios al pie de la letra.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearPedido");
    }



}

@Entity
class Pedido116 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO 5: anota 'lineas' con @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL).
    private List<Linea116> lineas = new ArrayList<>();

    public Pedido116() {
    }

    public void addLinea(Linea116 l) {
        // TODO 6: añade 'l' a la lista.
        // TODO 7: sincroniza el lado inverso: l.setPedido(this).
    }

    public Long getId() {
        return id;
    }

    public List<Linea116> getLineas() {
        return lineas;
    }
}

@Entity
class Linea116 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String producto;

    // TODO 8: anota 'pedido' con @ManyToOne (este es el lado DUEÑO de la FK).
    // TODO 9: añade @JoinColumn(name = "pedido_id").
    private Pedido116 pedido;

    protected Linea116() {
    }

    public Linea116(String producto) {
        // TODO 10: asigna el producto.
        this.producto = producto;
    }

    public void setPedido(Pedido116 p) {
        this.pedido = p;
    }

    public Pedido116 getPedido() {
        return pedido;
    }

    public String getProducto() {
        return producto;
    }
}
