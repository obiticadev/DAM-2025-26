package com.masterclass.api.b13_rel;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio 118 · Sincronizar los dos lados de una relación bidireccional.
 *
 * <p>Teoría: {@code teoria/13_Relaciones_JPA.md} (sección 13.2).
 *
 * <p>Olvidar sincronizar el lado inverso es EL bug clásico de JPA.
 */
public final class Ej118BidirectionalSync {

    private Ej118BidirectionalSync() {
    }

    public static void main(String[] args) {
        System.out.println("usa el test (no requiere BD)");
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: valida que 'l' no sea null.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: añade 'l' a la lista 'libros' (lado inverso).
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: fija el dueño: l.setAutor(this) (lado propietario de la FK).
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: ambos lados deben quedar consistentes en memoria.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: si solo añades a la lista y olvidas setAutor, la FK queda null al persistir.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: elimina 'l' de la lista.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: rompe el lado dueño: l.setAutor(null).
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: tras esto ningún lado debe seguir referenciando al otro.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: devuelve la lista de libros (lado inverso).
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: asigna el autor (lado dueño de la FK).
    }

}

@Entity
class Autor118 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private List<Libro118> libros = new ArrayList<>();

    /**
     * Añade un libro manteniendo COHERENTES ambos lados de la relación.
     *
     * @param l libro a añadir
     */
    public void addLibro(Libro118 l) {
        // TODO 1: valida que 'l' no sea null.
        // TODO 2: añade 'l' a la lista 'libros' (lado inverso).
        // TODO 3: fija el dueño: l.setAutor(this) (lado propietario de la FK).
        // TODO 4: ambos lados deben quedar consistentes en memoria.
        // TODO 5: si solo añades a la lista y olvidas setAutor, la FK queda null al persistir.
    }

    /**
     * Quita un libro sincronizando ambos lados.
     *
     * @param l libro a quitar
     */
    public void removeLibro(Libro118 l) {
        // TODO 6: elimina 'l' de la lista.
        // TODO 7: rompe el lado dueño: l.setAutor(null).
        // TODO 8: tras esto ningún lado debe seguir referenciando al otro.
    }

    public List<Libro118> getLibros() {
        // TODO 9: devuelve la lista de libros (lado inverso).
        return libros;
    }
}

@Entity
class Libro118 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor118 autor;

    protected Libro118() {
    }

    public Libro118(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(Autor118 a) {
        // TODO 10: asigna el autor (lado dueño de la FK).
        this.autor = a;
    }

    public Autor118 getAutor() {
        return autor;
    }
}
