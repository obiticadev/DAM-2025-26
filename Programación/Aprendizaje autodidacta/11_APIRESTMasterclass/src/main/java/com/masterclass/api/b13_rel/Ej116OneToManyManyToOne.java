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
}
