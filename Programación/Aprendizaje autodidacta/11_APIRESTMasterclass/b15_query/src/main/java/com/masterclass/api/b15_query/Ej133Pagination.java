package com.masterclass.api.b15_query;

import jakarta.persistence.*;
import java.util.List;

/**
 * Ejercicio 133 · Paginación (offset/limit + total).
 *
 * <p>Teoría: {@code teoria/15_Consultas_Avanzadas.md} (sección 15.1).
 */
public final class Ej133Pagination {

    /** Página: contenido + metadatos. */
    public record Pagina<T>(List<T> contenido, long total, int pagina, int tamano) {
        public int totalPaginas() {
            return tamano == 0 ? 0 : (int) Math.ceil((double) total / tamano);
        }
    }

    private final EntityManager em;

    public Ej133Pagination(EntityManager em) {
        this.em = em;
    }

    /**
     * Devuelve una página de items ordenados por id.
     *
     * @param pagina índice 0-based
     * @param tamano tamaño de página (&gt; 0)
     * @return Pagina con contenido y total global
     * @throws IllegalArgumentException si pagina &lt; 0 o tamano &lt;= 0
     */
    public Pagina<Item133> page(int pagina, int tamano) {
        // TODO 1: valida pagina >= 0 y tamano > 0.
        // TODO 2: calcula el offset = pagina * tamano.
        // TODO 3: query de datos JPQL "select i from Item133 i order by i.id".
        // TODO 4: aplica setFirstResult(offset).
        // TODO 5: aplica setMaxResults(tamano).
        // TODO 6: getResultList() para el contenido.
        // TODO 7: query aparte "select count(i) from Item133 i" para el total.
        // TODO 8: getSingleResult() -> long total.
        // TODO 9: construye la Pagina con contenido, total, pagina, tamano.
        // TODO 10: devuélvela (totalPaginas lo calcula el record).
        return null;
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }

    /**
     * Reto Extra 1: Calcula el offset de paginacion de forma segura.
     */
    public static int calcularOffset(int pagina, int tamano) {
        // GUÍA: teoría 15.1 (offset/limit). Es la cuenta que usa TODO 2 de page().
        // 1. (Opcional) valida pagina >= 0 y tamano > 0 antes de multiplicar.
        // 2. Una línea: return pagina * tamano;
        // OJO: el test pide calcularOffset(2, 10) == 20 (página 2, tamaño 10 →
        //   te saltas las 20 primeras filas). La página es 0-based.
        // CULTURA: esto es lo que Spring traduce a OFFSET en SQL cuando le pasas
        //   un Pageable; con datasets enormes este OFFSET es lento → ver 15.4 (keyset).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para calcularOffset");
    }

    /**
     * Reto Extra 2: Valida los parametros de paginacion.
     */
    public static boolean esValida(int pagina, int tamano) {
        // GUÍA: misma regla que el TODO 1 de page(), pero devolviendo boolean en
        //   vez de lanzar.
        // 1. Una línea: return pagina >= 0 && tamano > 0;
        // OJO: el test exige esValida(0, 10) == true (página 0 ES válida) y
        //   esValida(-1, 10) == false. El tamaño debe ser ESTRICTAMENTE > 0
        //   (un tamaño 0 provocaría división por cero en totalPaginas()).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esValida");
    }

    /**
     * Reto Extra 3: Comprueba si hay una pagina siguiente.
     */
    public static boolean tieneSiguiente(Pagina<?> p) {
        // GUÍA: teoría 15.1. Una Page SÍ sabe el total, así que puede calcular si
        //   quedan páginas por delante (a diferencia de un Slice, ver Ej135).
        // 1. Apóyate en el record: p.totalPaginas() ya hace el ceil(total/tamano).
        // 2. Una línea: return p.pagina() < p.totalPaginas() - 1;
        // OJO: el test usa total=25, pagina=0, tamano=10 → totalPaginas()=3, y
        //   0 < 2 es true. La última página es la de índice totalPaginas()-1.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneSiguiente");
    }

    /**
     * Reto Extra 4: Comprueba si hay una pagina anterior.
     */
    public static boolean tieneAnterior(Pagina<?> p) {
        // GUÍA: hay anterior si no estás en la primera página.
        // 1. Una línea: return p.pagina() > 0;
        // OJO: el test usa pagina=1 → 1 > 0 es true. En la página 0 sería false
        //   (es el complemento de esPrimera, reto 5).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneAnterior");
    }

    /**
     * Reto Extra 5: Comprueba si es la primera pagina.
     */
    public static boolean esPrimera(Pagina<?> p) {
        // GUÍA: una línea — return p.pagina() == 0;
        // OJO: la paginación es 0-based, así que la primera página es la 0
        //   (el test pasa pagina=0 y espera true).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esPrimera");
    }

    /**
     * Reto Extra 6: Comprueba si es la ultima pagina.
     */
    public static boolean esUltima(Pagina<?> p) {
        // GUÍA: es la última si tu índice coincide con totalPaginas()-1. Es el
        //   complemento exacto de tieneSiguiente (reto 3).
        // 1. Una línea: return p.pagina() == p.totalPaginas() - 1;
        // OJO: el test usa total=25, tamano=10, pagina=2 → totalPaginas()=3 y
        //   2 == 2 es true. CUIDADO con el caso total=0 (totalPaginas()=0): ahí
        //   pagina 0 daría 0 == -1 (false); si te importa ese borde, contémplalo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esUltima");
    }

    /**
     * Reto Extra 7: Obtiene el indice de la siguiente pagina.
     */
    public static int indiceSiguiente(Pagina<?> p) {
        // GUÍA: una línea — return p.pagina() + 1;
        // El test pasa pagina=0 y espera 1. (Si quisieras ser estricto, podrías
        //   no pasar de la última, pero el test solo comprueba el +1.)
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para indiceSiguiente");
    }

    /**
     * Reto Extra 8: Obtiene el indice de la anterior pagina.
     */
    public static int indiceAnterior(Pagina<?> p) {
        // GUÍA: una línea — return Math.max(0, p.pagina() - 1);
        // El test pasa pagina=1 y espera 0. El Math.max evita un índice negativo
        //   si alguien lo llama en la página 0 (defensa de borde).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para indiceAnterior");
    }

    /**
     * Reto Extra 9: Comprueba si el contenido de la pagina esta vacio.
     */
    public static boolean estaVacia(Pagina<?> p) {
        // GUÍA: una línea — return p.contenido().isEmpty();
        // OJO: el test construye una Pagina con List.of() (contenido vacío) aunque
        //   total=25: una página puede no tener filas (p.ej. pediste más allá del
        //   final). Mira el CONTENIDO, no el total.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para estaVacia");
    }

    /**
     * Reto Extra 10: Retorna formato descriptivo de la pagina.
     */
    public static String formatearPagina(Pagina<?> p) {
        // GUÍA: usa String.format con tres huecos.
        // PISTA: String.format("Pagina[%d/%d, Contenido=%d]",
        //            p.pagina(), p.totalPaginas(), p.contenido().size());
        // OJO: el test espera EXACTAMENTE "Pagina[0/3, Contenido=1]" (pagina 0,
        //   totalPaginas 3, un elemento). Respeta los corchetes, la barra y la
        //   coma+espacio tal cual; se compara con equals.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearPagina");
    }



}

@Entity
class Item133 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    public Item133() {
    }

    public Item133(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
