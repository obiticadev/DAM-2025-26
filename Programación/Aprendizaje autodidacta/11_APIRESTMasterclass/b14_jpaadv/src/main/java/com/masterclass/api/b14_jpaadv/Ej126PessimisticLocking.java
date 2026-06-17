package com.masterclass.api.b14_jpaadv;

import jakarta.persistence.*;

/**
 * Ejercicio 126 · Bloqueo pesimista (LockModeType).
 *
 * <p>Teoría: {@code teoria/14_JPA_Avanzado.md} (sección 14.2).
 */
public final class Ej126PessimisticLocking {

    private Ej126PessimisticLocking() {
    }

    /**
     * Lee una fila con bloqueo de escritura (PESSIMISTIC_WRITE) y resta stock.
     *
     * @param em       EntityManager
     * @param id       id del artículo
     * @param cantidad unidades a descontar
     * @return stock resultante
     * @throws IllegalStateException si no hay stock suficiente
     */
    public static int reservar(EntityManager em, Long id, int cantidad) {
        // TODO 1: begin tx.
        // TODO 2: usa em.find(ArtStock126.class, id, LockModeType.PESSIMISTIC_WRITE)
        //         para bloquear la fila mientras dura la tx.
        // TODO 3: si la entidad es null -> IllegalStateException.
        // TODO 4: comprueba que stock >= cantidad; si no -> IllegalStateException.
        // TODO 5: resta 'cantidad' al stock.
        // TODO 6: commit (libera el lock).
        // TODO 7: el lock pesimista evita que dos reservas concurrentes sobrevendan.
        // TODO 8: devuelve el stock resultante.
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }

    /**
     * Reto Extra 1: Obtiene el stock de un articulo.
     */
    public static int obtenerStock(ArtStock126 a) {
        // GUÍA: una línea — return a.getStock();
        // getStock() ya existe en ArtStock126. El test espera 10.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerStock");
    }

    /**
     * Reto Extra 2: Comprueba si hay stock disponible (mayor que 0).
     */
    public static boolean tieneStockDisponible(ArtStock126 a) {
        // GUÍA: una línea — return a.getStock() > 0;
        // OJO: "disponible" = estrictamente mayor que 0. El test espera true (stock 10).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneStockDisponible");
    }

    /**
     * Reto Extra 3: Comprueba si hay suficiente stock para cubrir una cantidad.
     */
    public static boolean stockSuficiente(ArtStock126 a, int cant) {
        // GUÍA: una línea — return a.getStock() >= cant;
        // OJO: es >= (cubrir EXACTAMENTE el stock cuenta). El test pide true con
        //      cant 5 y false con cant 15 (stock 10). Es la misma comprobación que
        //      hace 'reservar' antes de descontar.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para stockSuficiente");
    }

    /**
     * Reto Extra 4: Crea un nuevo articulo con stock inicial.
     */
    public static ArtStock126 crearArticulo(Long id, int stock) {
        // GUÍA: una línea — return new ArtStock126(id, stock);
        // El constructor público recibe (id, stock). El test solo comprueba no-null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearArticulo");
    }

    /**
     * Reto Extra 5: Incrementa el stock de un articulo.
     */
    public static void incrementarStock(ArtStock126 a, int cant) {
        // GUÍA: suma 'cant' al stock actual con el setter.
        // 1. a.setStock(a.getStock() + cant);
        // OJO: el test suma 5 a 10 y espera 15.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para incrementarStock");
    }

    /**
     * Reto Extra 6: Decrementa el stock si hay suficiente.
     */
    public static boolean decrementarStock(ArtStock126 a, int cant) {
        // GUÍA: solo descuenta si hay stock suficiente; informa con el boolean.
        // 1. Si NO hay suficiente (reutiliza stockSuficiente del reto 3) → return false.
        // 2. Si lo hay: a.setStock(a.getStock() - cant); return true;
        // OJO: el test descuenta 3 de 10, espera true y stock final 7. Este es el
        //      mismo patrón que 'reservar', pero sin lock ni excepción (devuelve bool).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para decrementarStock");
    }

    /**
     * Reto Extra 7: Obtiene el ID del articulo.
     */
    public static Long obtenerId(ArtStock126 a) {
        // GUÍA: ⚠ CUIDADO: ArtStock126 NO tiene getter para 'id'. Añádele:
        //     public Long getId() { return id; }
        //   (el reto 10 también lo necesita).
        // 1. Una vez añadido: return a.getId();
        // OJO: el test crea ArtStock126(99L, 10) y espera 99L.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerId");
    }

    /**
     * Reto Extra 8: Comprueba si el stock es cero.
     */
    public static boolean stockEsCero(ArtStock126 a) {
        // GUÍA: una línea — return a.getStock() == 0;
        // OJO: el test crea un artículo con stock 0 y espera true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para stockEsCero");
    }

    /**
     * Reto Extra 9: Comprueba si el stock es par.
     */
    public static boolean stockEsPar(ArtStock126 a) {
        // GUÍA: par = resto 0 al dividir entre 2.
        // 1. Una línea: return a.getStock() % 2 == 0;
        // OJO: el test usa stock 10 y espera true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para stockEsPar");
    }

    /**
     * Reto Extra 10: Retorna formato de texto del stock.
     */
    public static String formatearStock(ArtStock126 a) {
        // GUÍA: usa el getId() del reto 7 y getStock().
        // 1. return "Art[Id=" + a.getId() + ", Stock=" + a.getStock() + "]";
        // OJO: el test espera EXACTAMENTE "Art[Id=1, Stock=10]" (Id es un Long sin
        //      decimales; respeta el espacio tras la coma).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearStock");
    }



}

@Entity
class ArtStock126 {
    @Id
    private Long id;
    private int stock;

    protected ArtStock126() {
    }

    public ArtStock126(Long id, int stock) {
        // TODO 9: asigna id y stock.
        this.id = id;
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int s) {
        // TODO 10: asigna el nuevo stock.
        this.stock = s;
    }
}
