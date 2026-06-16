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

    /**
     * Reto Extra 1: Cuenta libros de una biblioteca.
     */
    public static int contarLibros(Biblioteca120 b) {
        // GUÍA: teoría 13.6. OJO conceptual: aquí 'b' está en memoria (no detached),
        //       así que getLibros() NO lanza LazyInitializationException; eso solo pasa
        //       con una entidad detached recién traída de BD (ver cargarYDetach).
        // PISTA: return b == null ? 0 : b.getLibros().size();
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarLibros");
    }

    /**
     * Reto Extra 2: Comprueba si tiene libros.
     */
    public static boolean tieneLibros(Biblioteca120 b) {
        // GUÍA: teoría 13.6. Reutiliza contarLibros.
        // PISTA: return contarLibros(b) > 0;
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneLibros");
    }

    /**
     * Reto Extra 3: Comprueba si un libro esta en biblioteca.
     */
    public static boolean contieneLibro(Biblioteca120 b, LibroLazy120 l) {
        // GUÍA: teoría 13.6. contains por identidad (LibroLazy120 no define equals).
        // PISTA: return b != null && l != null && b.getLibros().contains(l);
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contieneLibro");
    }

    /**
     * Reto Extra 4: Comprueba si algun libro tiene un titulo.
     */
    public static boolean tieneTitulo(Biblioteca120 b, String titulo) {
        // GUÍA: teoría 13.6 + streams. LibroLazy120 ya tiene getTitulo().
        // PISTA: b.getLibros().stream().anyMatch(l -> titulo.equals(l.getTitulo()));
        // OJO: "Quijote" true, "Celestina" false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneTitulo");
    }

    /**
     * Reto Extra 5: Cuenta titulos largos.
     */
    public static int contarLibrosTituloLargo(Biblioteca120 b, int len) {
        // GUÍA: teoría 13.6 + filter+count.
        // PISTA: (int) b.getLibros().stream()
        //            .filter(l -> l.getTitulo() != null && l.getTitulo().length() > len).count();
        // OJO: "Quijote" mide 7; con len=5 → 1. Estrictamente mayor.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarLibrosTituloLargo");
    }

    /**
     * Reto Extra 6: Crea un libro lazy.
     */
    public static LibroLazy120 crearLibro(String titulo) {
        // GUÍA: una línea — factory simple.
        // PISTA: return new LibroLazy120(titulo);
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearLibro");
    }

    /**
     * Reto Extra 7: Remueve el primer libro.
     */
    public static boolean removerPrimerLibro(Biblioteca120 b) {
        // GUÍA: teoría 13.6. Mismo patrón que removerPrimerConcepto (Ej119 reto 7).
        // PISTA: if (b.getLibros().isEmpty()) return false;
        //        b.getLibros().remove(0); return true;
        // OJO: el test espera true y size 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para removerPrimerLibro");
    }

    /**
     * Reto Extra 8: Valida biblioteca no nula.
     */
    public static boolean esValida(Biblioteca120 b) {
        // GUÍA: una línea.
        // PISTA: return b != null;
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esValida");
    }

    /**
     * Reto Extra 9: Vincula libros en lote.
     */
    public static void vincularLibros(Biblioteca120 b, java.util.List<LibroLazy120> lista) {
        // GUÍA: teoría 13.6. Usa b.add (ya implementado: sincroniza el lado dueño).
        // PISTA: lista.forEach(b::add);
        // OJO: el test pasa 2 libros y espera size 2.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para vincularLibros");
    }

    /**
     * Reto Extra 10: Retorna formato de biblioteca.
     */
    public static String formatearBiblioteca(Biblioteca120 b) {
        // GUÍA: formato EXACTO.
        // PISTA: return "Biblioteca[Id=" + b.getId() + ", Libros=" + b.getLibros().size() + "]";
        // OJO: el test espera literalmente "Biblioteca[Id=null, Libros=0]".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearBiblioteca");
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

    public String getTitulo() {
        return titulo;
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
