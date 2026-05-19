package com.masterclass.api.b13_rel;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio 121 · El problema N+1 y su solución con JOIN FETCH.
 *
 * <p>Teoría: {@code teoria/13_Relaciones_JPA.md} (sección 13.3).
 *
 * <p>Cargar N blogs y luego sus posts uno a uno = N+1 queries. JOIN FETCH lo
 * resuelve trayendo todo en UNA query con las colecciones ya inicializadas.
 */
public final class Ej121NPlusOneProblem {

    private Ej121NPlusOneProblem() {
    }

    /**
     * Trae todos los blogs CON sus posts ya inicializados en una sola query.
     *
     * @param em EntityManager
     * @return lista de blogs; debe poder leerse posts tras em.clear()
     */
    public static List<Blog121> todosConPosts(EntityManager em) {
        // TODO 1: usa JPQL con JOIN FETCH: "select distinct b from Blog121 b join fetch b.posts".
        // TODO 2: 'distinct' evita duplicados de blog por el join.
        // TODO 3: createQuery(jpql, Blog121.class).
        // TODO 4: getResultList().
        // TODO 5: tras esto las colecciones 'posts' YA están inicializadas (no LAZY).
        // TODO 6: el test hará em.clear() y aun así podrá leer posts (prueba del fetch).
        // TODO 7: sin JOIN FETCH, leer posts tras clear lanzaría LazyInitializationException.
        // TODO 8: una sola query en vez de 1 + N (la esencia del problema N+1).
        // TODO 9: devuelve la lista.
        // TODO 10: NO recorras blogs llamando a getPosts() en bucle (eso ES el N+1).
        return List.of();
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: usa JPQL con JOIN FETCH: "select distinct b from Blog121 b join fetch b.posts".
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: 'distinct' evita duplicados de blog por el join.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: createQuery(jpql, Blog121.class).
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: getResultList().
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: tras esto las colecciones 'posts' YA están inicializadas (no LAZY).
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: el test hará em.clear() y aun así podrá leer posts (prueba del fetch).
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: sin JOIN FETCH, leer posts tras clear lanzaría LazyInitializationException.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: una sola query en vez de 1 + N (la esencia del problema N+1).
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: devuelve la lista.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: NO recorras blogs llamando a getPosts() en bucle (eso ES el N+1).
    }

}

@Entity
class Post121 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "blog_id")
    private Blog121 blog;

    protected Post121() {
    }

    public Post121(String titulo) {
        this.titulo = titulo;
    }

    public void setBlog(Blog121 b) {
        this.blog = b;
    }
}

@Entity
class Blog121 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    private List<Post121> posts = new ArrayList<>();

    public Blog121() {
    }

    public Blog121(String nombre) {
        this.nombre = nombre;
    }

    public void add(Post121 p) {
        posts.add(p);
        p.setBlog(this);
    }

    public List<Post121> getPosts() {
        return posts;
    }
}
