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

    /**
     * Reto Extra 1: Cuenta posts de un blog.
     */
    public static int contarPosts(Blog121 b) {
        // GUÍA: teoría 13.7. Aquí 'b' está en memoria; en producción contar así sobre N
        //       blogs traídos LAZY es justo lo que dispara el N+1 (una query por getPosts()).
        // PISTA: return b == null ? 0 : b.getPosts().size();
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarPosts");
    }

    /**
     * Reto Extra 2: Comprueba si tiene posts.
     */
    public static boolean tienePosts(Blog121 b) {
        // GUÍA: teoría 13.7. Reutiliza contarPosts.
        // PISTA: return contarPosts(b) > 0;
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tienePosts");
    }

    /**
     * Reto Extra 3: Comprueba si un post esta en blog.
     */
    public static boolean contienePost(Blog121 b, Post121 p) {
        // GUÍA: teoría 13.7. contains por identidad.
        // PISTA: return b != null && p != null && b.getPosts().contains(p);
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contienePost");
    }

    /**
     * Reto Extra 4: Comprueba si algun post tiene un titulo.
     */
    public static boolean tieneTitulo(Blog121 b, String titulo) {
        // GUÍA: teoría 13.7 + streams. Post121 ya tiene getTitulo().
        // PISTA: b.getPosts().stream().anyMatch(p -> titulo.equals(p.getTitulo()));
        // OJO: "JPA" true, "Spring" false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneTitulo");
    }

    /**
     * Reto Extra 5: Cuenta titulos largos.
     */
    public static int contarPostsTituloLargo(Blog121 b, int len) {
        // GUÍA: teoría 13.7 + filter+count.
        // PISTA: (int) b.getPosts().stream()
        //            .filter(p -> p.getTitulo() != null && p.getTitulo().length() > len).count();
        // OJO: "Hibernate" mide 9; con len=5 → 1. Estrictamente mayor.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarPostsTituloLargo");
    }

    /**
     * Reto Extra 6: Crea un post.
     */
    public static Post121 crearPost(String titulo) {
        // GUÍA: una línea — factory simple.
        // PISTA: return new Post121(titulo);
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearPost");
    }

    /**
     * Reto Extra 7: Remueve el primer post.
     */
    public static boolean removerPrimerPost(Blog121 b) {
        // GUÍA: teoría 13.7. Mismo patrón que removerPrimerConcepto/removerPrimerLibro.
        // PISTA: if (b.getPosts().isEmpty()) return false;
        //        b.getPosts().remove(0); return true;
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para removerPrimerPost");
    }

    /**
     * Reto Extra 8: Valida blog no nulo.
     */
    public static boolean esValido(Blog121 b) {
        // GUÍA: una línea.
        // PISTA: return b != null;
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esValido");
    }

    /**
     * Reto Extra 9: Vincula posts en lote.
     */
    public static void vincularPosts(Blog121 b, java.util.List<Post121> lista) {
        // GUÍA: teoría 13.7. Usa b.add (ya implementado: sincroniza el lado dueño).
        // PISTA: lista.forEach(b::add);
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para vincularPosts");
    }

    /**
     * Reto Extra 10: Retorna formato de blog.
     */
    public static String formatearBlog(Blog121 b) {
        // GUÍA: formato EXACTO. Aquí NO va el Id, solo el nº de posts.
        // PISTA: return "Blog[Posts=" + b.getPosts().size() + "]";
        // OJO: el test espera literalmente "Blog[Posts=0]" (sin "Id=").
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearBlog");
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

    public String getTitulo() {
        return titulo;
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
