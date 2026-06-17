package com.masterclass.api.b15_query;

import jakarta.persistence.*;
import java.util.List;

/**
 * Ejercicio 135 · Slice vs Page (Slice no hace COUNT).
 *
 * <p>Teoría: {@code teoria/15_Consultas_Avanzadas.md} (sección 15.1).
 */
public final class Ej135SliceVsPage {

    /** Slice: contenido + nº de página y tamaño + si hay más, SIN total. */
    public record Slice<T>(List<T> contenido, int pagina, int tamano, boolean haySiguiente) {
    }

    private final EntityManager em;

    public Ej135SliceVsPage(EntityManager em) {
        this.em = em;
    }

    /**
     * Devuelve un Slice: pide tamano+1 para SABER si hay siguiente sin un COUNT.
     *
     * @param pagina índice 0-based
     * @param tamano tamaño de página
     * @return Slice (contenido recortado a 'tamano' + flag haySiguiente)
     */
    public Slice<Reg135> slice(int pagina, int tamano) {
        // TODO 1: valida pagina >= 0 y tamano > 0.
        // TODO 2: offset = pagina * tamano.
        // TODO 3: JPQL ordenado por id.
        // TODO 4: setFirstResult(offset).
        // TODO 5: setMaxResults(tamano + 1)  <-- el truco: pide UNO de más.
        // TODO 6: getResultList() -> lista (puede tener tamano+1 elementos).
        // TODO 7: haySiguiente = (lista.size() > tamano).
        // TODO 8: si haySiguiente, recorta la lista a 'tamano' (quita el extra).
        // TODO 9: NO ejecutes ningún COUNT (esa es la ventaja del Slice).
        // TODO 10: devuelve new Slice<>(contenido, pagina, tamano, haySiguiente).
        return null;
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }

    /**
     * Reto Extra 1: Comprueba si un Slice tiene pagina siguiente de forma logica.
     */
    public static boolean tieneSiguiente(Slice<?> s) {
        // GUÍA: teoría 15.1. AQUÍ está la diferencia con una Page: el Slice NO
        //   conoce el total, así que "hay siguiente" es un dato YA precalculado
        //   (el truco del tamano+1 de slice()). No se calcula con totalPaginas.
        // 1. Una línea: return s.haySiguiente();
        // OJO: el test construye el Slice con el flag a true → devuelve ese flag
        //   tal cual; no intentes deducirlo del tamaño del contenido.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneSiguiente");
    }

    /**
     * Reto Extra 2: Comprueba si un Slice tiene pagina anterior.
     */
    public static boolean tieneAnterior(Slice<?> s) {
        // GUÍA: una línea — return s.pagina() > 0;
        // El test pasa pagina=1 → true. (Mismo criterio que Ej133.tieneAnterior;
        //   el Slice SÍ sabe en qué página está, aunque no sepa el total.)
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneAnterior");
    }

    /**
     * Reto Extra 3: Comprueba si es la primera pagina de un Slice.
     */
    public static boolean esPrimera(Slice<?> s) {
        // GUÍA: una línea — return s.pagina() == 0;
        // El test pasa pagina=0 → true (la paginación es 0-based).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esPrimera");
    }

    /**
     * Reto Extra 4: Valida los parametros de Slice.
     */
    public static boolean esValida(int pagina, int tamano) {
        // GUÍA: misma regla que Ej133.esValida.
        // 1. Una línea: return pagina >= 0 && tamano > 0;
        // El test pasa (0, 10) → true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esValida");
    }

    /**
     * Reto Extra 5: Calcula el offset de forma segura.
     */
    public static int calcularOffset(int pagina, int tamano) {
        // GUÍA: idéntico a Ej133.calcularOffset.
        // 1. Una línea: return pagina * tamano;
        // El test pasa (2, 10) → 20.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para calcularOffset");
    }

    /**
     * Reto Extra 6: Retorna el tamaño del contenido actual.
     */
    public static int tamanoContenido(Slice<?> s) {
        // GUÍA: una línea — return s.contenido().size();
        // OJO: es el tamaño REAL de elementos devueltos (el test pasa 1 elemento),
        //   no el tamaño de página solicitado (s.tamano()). No los confundas.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tamanoContenido");
    }

    /**
     * Reto Extra 7: Comprueba si el Slice esta vacio.
     */
    public static boolean estaVacio(Slice<?> s) {
        // GUÍA: una línea — return s.contenido().isEmpty();
        // El test construye el Slice con List.of() → true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para estaVacio");
    }

    /**
     * Reto Extra 8: Obtiene el numero de pagina actual.
     */
    public static int numeroPagina(Slice<?> s) {
        // GUÍA: una línea — return s.pagina();
        // El test construye el Slice con pagina=3 → 3.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para numeroPagina");
    }

    /**
     * Reto Extra 9: Comprueba si el tamano de pagina es correcto.
     */
    public static boolean tamanoEsCorrecto(Slice<?> s, int esperado) {
        // GUÍA: una línea — return s.tamano() == esperado;
        // OJO: aquí SÍ usas s.tamano() (el tamaño de página configurado, 10 en el
        //   test), no el size() del contenido. Compara con 'esperado'.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tamanoEsCorrecto");
    }

    /**
     * Reto Extra 10: Retorna representacion estructurada de texto.
     */
    public static String formatearSlice(Slice<?> s) {
        // GUÍA: String.format con la página y el flag haySiguiente.
        // PISTA: String.format("Slice[Pagina=%d, HasNext=%b]", s.pagina(), s.haySiguiente());
        // OJO: el test espera EXACTAMENTE "Slice[Pagina=0, HasNext=false]".
        //   %b imprime "false"/"true" en minúsculas. Respeta "HasNext" en inglés
        //   con esa capitalización.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearSlice");
    }



}

@Entity
class Reg135 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dato;

    public Reg135() {
    }

    public Reg135(String dato) {
        this.dato = dato;
    }

    public Long getId() {
        return id;
    }
}
