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

    /**
     * Reto Extra 1: Cuenta libros de un autor.
     */
    public static int contarLibros(Autor118 a) {
        // GUÍA: teoría 13.4. El lado inverso (libros) es una List.
        // PISTA: return a == null ? 0 : a.getLibros().size();
        // OJO: depende de que addLibro (TODO 1-5 del ejercicio base) añada de verdad a la lista.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarLibros");
    }

    /**
     * Reto Extra 2: Comprueba si el autor tiene libros.
     */
    public static boolean tieneLibros(Autor118 a) {
        // GUÍA: teoría 13.4. Reutiliza contarLibros del reto 1.
        // PISTA: return contarLibros(a) > 0;
        // OJO: vacío → false; tras addLibro → true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneLibros");
    }

    /**
     * Reto Extra 3: Verifica si un libro esta sincronizado con el autor.
     */
    public static boolean esLibroSincronizado(Autor118 a, Libro118 l) {
        // GUÍA: teoría 13.4. Sincronizado = el lado dueño (FK) apunta al autor.
        // PISTA: return a != null && l != null && l.getAutor() == a;
        // OJO: == (misma instancia). Si addLibro olvidó el setAutor, esto sería false
        //      y delataría el bug clásico de la sección 13.4.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esLibroSincronizado");
    }

    /**
     * Reto Extra 4: Comprueba si el autor tiene un libro con determinado titulo.
     */
    public static boolean tieneLibroConTitulo(Autor118 a, String titulo) {
        // GUÍA: teoría 13.4 + streams. Libro118 ya tiene getTitulo().
        // PISTA: a.getLibros().stream().anyMatch(l -> titulo.equals(l.getTitulo()));
        // OJO: "Quijote" true, "Novela" false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneLibroConTitulo");
    }

    /**
     * Reto Extra 5: Cuenta libros con titulo largo.
     */
    public static int contarLibrosTituloLargo(Autor118 a, int len) {
        // GUÍA: teoría 13.4 + filter+count.
        // PISTA: (int) a.getLibros().stream()
        //            .filter(l -> l.getTitulo() != null && l.getTitulo().length() > len).count();
        // OJO: "El Quijote" mide 10; con len=8 pasa → 1. Estrictamente mayor.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarLibrosTituloLargo");
    }

    /**
     * Reto Extra 6: Crea un nuevo libro.
     */
    public static Libro118 crearLibro(String titulo) {
        // GUÍA: una línea — factory simple.
        // PISTA: return new Libro118(titulo);
        // OJO: el test solo comprueba assertNotNull.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearLibro");
    }

    /**
     * Reto Extra 7: Desvincula de forma sincronizada un libro de su autor.
     */
    public static void desvincularLibro(Autor118 a, Libro118 l) {
        // GUÍA: teoría 13.4. Desvincular SINCRONIZADO = romper los dos lados.
        // 1. Quita l de la lista del autor (lado inverso).
        // 2. Pon l.setAutor(null) (lado dueño de la FK).
        // PISTA: reutiliza a.removeLibro(l) si ya implementaste TODO 6/7/8 del base; si no:
        //        a.getLibros().remove(l); l.setAutor(null);
        // OJO: el test exige a.getLibros() SIN l Y l.getAutor() == null. Romper solo un
        //      lado dejaría el otro colgando.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desvincularLibro");
    }

    /**
     * Reto Extra 8: Comprueba si un libro tiene autor.
     */
    public static boolean tieneAutor(Libro118 l) {
        // GUÍA: teoría 13.4. ¿La FK apunta a algún autor?
        // PISTA: return l != null && l.getAutor() != null;
        // OJO: un Libro118 recién creado (sin addLibro) tiene autor null → false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneAutor");
    }

    /**
     * Reto Extra 9: Sincroniza en lote multiples libros.
     */
    public static void vincularEnLote(Autor118 a, java.util.List<Libro118> lista) {
        // GUÍA: teoría 13.4. Itera y usa addLibro (que sincroniza ambos lados).
        // PISTA: lista.forEach(a::addLibro);
        // OJO: el test pasa 2 libros y espera size 2. addLibro debe estar implementado
        //      (TODO 1-5 del base) y rechazar null, como exige addNullFalla.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para vincularEnLote");
    }

    /**
     * Reto Extra 10: Retorna texto del autor.
     */
    public static String formatearAutor(Autor118 a) {
        // GUÍA: formato EXACTO. Aquí NO va el Id, solo el nº de libros.
        // PISTA: return "Autor[Libros=" + a.getLibros().size() + "]";
        // OJO: el test espera literalmente "Autor[Libros=0]" (fíjate: sin "Id=", a
        //      diferencia de los formatos de Ej116/Ej117).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearAutor");
    }



}

@Entity
class Autor118 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private List<Libro118> libros = new ArrayList<>();

    protected Autor118() {
    }

    public Autor118(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

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

    public String getTitulo() {
        return titulo;
    }
}
