package com.masterclass.api.b13_rel;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio 120 · FetchType LAZY vs EAGER.
 *
 * <p>Teoría: {@code teoria/13_Relaciones_JPA.md} (sección 13.3).
 *
 * <p>Una colección LAZY no se carga hasta tocarla; si el EM ya se cerró →
 * LazyInitializationException. Una asociación EAGER se carga siempre.
 */
public final class Ej120FetchLazyEager {

    private Ej120FetchLazyEager() {
    }

    /**
     * Carga la biblioteca por id, cierra el contexto y DEVUELVE la entidad detached.
     *
     * @param em EntityManager (se hará clear/detach dentro)
     * @param id id de la biblioteca
     * @return la entidad detached (su colección LAZY NO estará inicializada)
     */
    public static Biblioteca120 cargarYDetach(EntityManager em, Long id) {
        // TODO 1: recupera la entidad con em.find.
        // TODO 2: NO toques la colección lazy aquí (no la inicialices).
        // TODO 3: em.detach(entidad) para dejarla fuera del contexto.
        // TODO 4: devuelve la entidad detached.
        // TODO 5: acceder luego a getLibros() debe lanzar LazyInitializationException
        //         (eso lo comprueba el test; tú solo dejas la colección LAZY).
        return null;
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }
}

@Entity
class LibroLazy120 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "bib_id")
    private Biblioteca120 biblioteca;

    protected LibroLazy120() {
    }

    public LibroLazy120(String titulo) {
        this.titulo = titulo;
    }

    public void setBiblioteca(Biblioteca120 b) {
        this.biblioteca = b;
    }
}

@Entity
class Biblioteca120 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    // TODO 6: anota 'libros' con @OneToMany(mappedBy="biblioteca").
    // TODO 7: fuerza fetch = FetchType.LAZY (es el default en *ToMany, pero hazlo explícito).
    // TODO 8: cascade = CascadeType.ALL para persistir los libros con la biblioteca.
    private List<LibroLazy120> libros = new ArrayList<>();

    public Biblioteca120() {
    }

    public Biblioteca120(String nombre) {
        this.nombre = nombre;
    }

    public void add(LibroLazy120 l) {
        // TODO 9: añade a la lista y sincroniza l.setBiblioteca(this).
        libros.add(l);
        l.setBiblioteca(this);
    }

    public Long getId() {
        return id;
    }

    public List<LibroLazy120> getLibros() {
        // TODO 10: devuelve la colección (si está detached y LAZY -> excepción al iterarla).
        return libros;
    }
}
