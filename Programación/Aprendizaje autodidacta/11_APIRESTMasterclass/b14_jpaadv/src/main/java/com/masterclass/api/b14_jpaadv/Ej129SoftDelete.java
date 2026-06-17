package com.masterclass.api.b14_jpaadv;

import jakarta.persistence.*;
import java.util.List;

/**
 * Ejercicio 129 · Borrado lógico (soft delete).
 *
 * <p>Teoría: {@code teoria/14_JPA_Avanzado.md} (sección 14.3).
 *
 * <p>No se borra la fila: se marca {@code borrado=true} y las consultas la excluyen.
 */
public final class Ej129SoftDelete {

    private final EntityManager em;

    public Ej129SoftDelete(EntityManager em) {
        this.em = em;
    }

    /** Guarda un cliente activo. @param c cliente @return id */
    public Long crear(ClienteSD129 c) {
        // TODO 1: begin tx, persist(c), commit, devuelve c.getId().
        return null;
    }

    /**
     * Borrado lógico: marca el flag, NO ejecuta DELETE.
     *
     * @param id id del cliente
     * @return true si existía y se marcó
     */
    public boolean borrarLogico(Long id) {
        // TODO 2: begin tx.
        // TODO 3: find del cliente; si null -> false (rollback/commit vacío).
        // TODO 4: setBorrado(true) (NO em.remove).
        // TODO 5: commit.
        // TODO 6: devuelve true.
        return false;
    }

    /**
     * Lista solo los NO borrados.
     *
     * @return clientes activos (borrado = false)
     */
    public List<ClienteSD129> listarActivos() {
        // TODO 7: JPQL "select c from ClienteSD129 c where c.borrado = false order by c.id".
        // TODO 8: getResultList().
        // TODO 9: los borrados lógicamente NO deben aparecer aquí.
        // TODO 10: la fila sigue en BD (consultable sin el filtro) — soft, no físico.
        return List.of();
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }

    /**
     * Reto Extra 1: Obtiene el nombre del cliente de forma segura.
     */
    public static String obtenerNombre(ClienteSD129 c) {
        // GUÍA: ⚠ CUIDADO: ClienteSD129 NO tiene getter para 'nombre'. Añádele:
        //     public String getNombre() { return nombre; }
        //   (el reto 8 también lo necesita).
        // 1. Una vez añadido: return c.getNombre();
        // OJO: el test espera "Ana".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerNombre");
    }

    /**
     * Reto Extra 2: Comprueba si el cliente esta marcado como borrado.
     */
    public static boolean estaBorrado(ClienteSD129 c) {
        // GUÍA: una línea — return c.isBorrado();
        // (isBorrado() ya existe.) El test crea un cliente nuevo (borrado=false) y
        // espera false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para estaBorrado");
    }

    /**
     * Reto Extra 3: Crea un nuevo cliente activo.
     */
    public static ClienteSD129 crearCliente(String nombre) {
        // GUÍA: una línea — return new ClienteSD129(nombre);
        // El campo borrado arranca en false (activo). El test solo comprueba no-null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearCliente");
    }

    /**
     * Reto Extra 4: Marca un cliente como borrado (soft delete manual).
     */
    public static void borrarClienteManual(ClienteSD129 c) {
        // GUÍA: teoría 14.7. Soft delete = marcar el flag, NO em.remove.
        // 1. Una línea: c.setBorrado(true);
        // El test luego comprueba c.isBorrado()==true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para borrarClienteManual");
    }

    /**
     * Reto Extra 5: Restaura un cliente borrado logicamente.
     */
    public static void restaurarCliente(ClienteSD129 c) {
        // GUÍA: restaurar = lo contrario de borrar (poner el flag a false).
        // 1. Una línea: c.setBorrado(false);
        // OJO: el test marca borrado=true, llama a restaurar y espera isBorrado()==false.
        // CULTURA: poder "des-borrar" es justo la ventaja del soft delete sobre el
        // DELETE físico (papelera de reciclaje).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para restaurarCliente");
    }

    /**
     * Reto Extra 6: Obtiene el ID del cliente de forma segura.
     */
    public static Long obtenerId(ClienteSD129 c) {
        // GUÍA: una línea — return c.getId();
        // (getId() ya existe.) El test usa un cliente sin persistir y espera null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerId");
    }

    /**
     * Reto Extra 7: Comprueba si el cliente es nuevo (ID nulo).
     */
    public static boolean esNuevo(ClienteSD129 c) {
        // GUÍA: "nuevo" = id null. Espejo del reto 6.
        // 1. Una línea: return c.getId() == null;
        // El test espera true en un cliente recién creado.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esNuevo");
    }

    /**
     * Reto Extra 8: Comprueba si el nombre del cliente contiene una palabra.
     */
    public static boolean nombreContiene(ClienteSD129 c, String palabra) {
        // GUÍA: usa el getNombre() que añadiste en el reto 1.
        // 1. Una línea: return c.getNombre().contains(palabra);
        // OJO: el test pide true para "Lopez" y false para "Marta" sobre "Ana Lopez".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para nombreContiene");
    }

    /**
     * Reto Extra 9: Comprueba si el cliente esta activo (no borrado).
     */
    public static boolean estaActivo(ClienteSD129 c) {
        // GUÍA: "activo" = NO borrado. Es la negación de estaBorrado (reto 2).
        // 1. Una línea: return !c.isBorrado();
        // El test, con un cliente nuevo, espera true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para estaActivo");
    }

    /**
     * Reto Extra 10: Retorna formato del cliente.
     */
    public static String formatearCliente(ClienteSD129 c) {
        // GUÍA: usa getId() y el estado activo (!isBorrado, reto 9).
        // 1. return "Cliente[Id=" + c.getId() + ", Activo=" + !c.isBorrado() + "]";
        // OJO: el test usa un cliente nuevo y espera "Cliente[Id=null, Activo=true]".
        //      Es "Activo", no "Borrado": invierte el flag.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearCliente");
    }



}

@Entity
class ClienteSD129 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private boolean borrado = false;

    protected ClienteSD129() {
    }

    public ClienteSD129(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean b) {
        this.borrado = b;
    }
}
