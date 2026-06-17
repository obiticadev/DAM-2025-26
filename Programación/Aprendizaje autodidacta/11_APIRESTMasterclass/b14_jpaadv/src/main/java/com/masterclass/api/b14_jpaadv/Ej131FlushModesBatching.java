package com.masterclass.api.b14_jpaadv;

import jakarta.persistence.*;

/**
 * Ejercicio 131 · Flush explícito y orden de operaciones.
 *
 * <p>Teoría: {@code teoria/14_JPA_Avanzado.md} (sección 14.1).
 */
public final class Ej131FlushModesBatching {

    private Ej131FlushModesBatching() {
    }

    /**
     * Persiste N entidades y hace flush explícito a mitad, devolviendo cuántas
     * filas hay en BD JUSTO tras el flush (antes del commit).
     *
     * @param em EntityManager
     * @param n  número de entidades a crear
     * @return filas visibles en la BD tras el flush (debe ser n)
     */
    public static long persistirYFlush(EntityManager em, int n) {
        // TODO 1: begin tx.
        // TODO 2: bucle: persiste n entidades Item131.
        // TODO 3: llama a em.flush() explícitamente (envía los INSERT a la BD ya).
        // TODO 4: ejecuta un COUNT con SQL nativo
        //         createNativeQuery("SELECT COUNT(*) FROM Item131").
        // TODO 5: tras flush, el COUNT ya ve las n filas (aún dentro de la tx).
        // TODO 6: guarda ese count.
        // TODO 7: commit (confirma definitivamente).
        // TODO 8: convierte el resultado a long (((Number)x).longValue()).
        // TODO 9: flush != commit: flush sincroniza, commit confirma.
        // TODO 10: devuelve el count observado tras el flush.
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }

    /**
     * Reto Extra 1: Obtiene el nombre del item de forma segura.
     */
    public static String obtenerNombre(Item131 i) {
        // GUÍA: ⚠ CUIDADO: Item131 NO tiene getters. Añádele dos:
        //     public Long getId() { return id; }
        //     public String getNombre() { return nombre; }
        //   (los necesitan los retos 3,4,5,7,10 además de este).
        // 1. Una vez añadidos: return i.getNombre();
        // OJO: el test espera "Laptop".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerNombre");
    }

    /**
     * Reto Extra 2: Crea un nuevo item.
     */
    public static Item131 crearItem(String nombre) {
        // GUÍA: una línea — return new Item131(nombre);
        // El test solo comprueba que no es null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearItem");
    }

    /**
     * Reto Extra 3: Comprueba si el item tiene ID.
     */
    public static boolean tieneId(Item131 i) {
        // GUÍA: usa el getId() que añadiste en el reto 1.
        // 1. Una línea: return i.getId() != null;
        // OJO: el test usa un item sin persistir y espera false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneId");
    }

    /**
     * Reto Extra 4: Comprueba si el item es nuevo.
     */
    public static boolean esNuevo(Item131 i) {
        // GUÍA: "nuevo" = id null. Negación del reto 3.
        // 1. Una línea: return i.getId() == null;
        // El test espera true en un item recién creado.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esNuevo");
    }

    /**
     * Reto Extra 5: Comprueba si el nombre del item contiene una palabra.
     */
    public static boolean nombreContiene(Item131 i, String palabra) {
        // GUÍA: usa getNombre() (reto 1).
        // 1. Una línea: return i.getNombre().contains(palabra);
        // OJO: el test pide true para "Dell" y false para "HP" sobre "Laptop Dell".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para nombreContiene");
    }

    /**
     * Reto Extra 6: Valida si el item es valido.
     */
    public static boolean esValido(Item131 i) {
        // GUÍA: "válido" = tiene nombre no nulo y no en blanco.
        // 1. return i.getNombre() != null && !i.getNombre().isBlank();
        // OJO: el test usa "Laptop" y espera true. isBlank() (Java 11+) también
        //      descarta cadenas de solo espacios.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esValido");
    }

    /**
     * Reto Extra 7: Obtiene el ID del item de forma segura.
     */
    public static Long obtenerId(Item131 i) {
        // GUÍA: una línea — return i.getId();
        // El test usa un item sin persistir y espera null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerId");
    }

    /**
     * Reto Extra 8: Comprueba si el FlushModeType es COMMIT.
     */
    public static boolean esFlushModeCommit(FlushModeType mode) {
        // GUÍA: teoría 14.9. FlushModeType.COMMIT = solo hace flush al confirmar.
        // 1. Una línea: return mode == FlushModeType.COMMIT;
        // OJO: el test pide true para COMMIT y false para AUTO.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esFlushModeCommit");
    }

    /**
     * Reto Extra 9: Comprueba si el FlushModeType es AUTO.
     */
    public static boolean esFlushModeAuto(FlushModeType mode) {
        // GUÍA: AUTO es el modo por defecto (flush antes de cada query). Espejo del reto 8.
        // 1. Una línea: return mode == FlushModeType.AUTO;
        // OJO: el test pide true para AUTO y false para COMMIT.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esFlushModeAuto");
    }

    /**
     * Reto Extra 10: Retorna formato del item.
     */
    public static String formatearItem(Item131 i) {
        // GUÍA: usa getId() y getNombre().
        // 1. return "Item[Id=" + i.getId() + ", Nombre=" + i.getNombre() + "]";
        // OJO: el test usa un item nuevo y espera "Item[Id=null, Nombre=Laptop]".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearItem");
    }



}

@Entity
class Item131 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    public Item131() {
    }

    public Item131(String nombre) {
        this.nombre = nombre;
    }
}
