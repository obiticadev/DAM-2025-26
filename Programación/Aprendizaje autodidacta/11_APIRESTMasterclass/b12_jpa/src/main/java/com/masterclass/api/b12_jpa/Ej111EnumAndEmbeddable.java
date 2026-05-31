package com.masterclass.api.b12_jpa;

import jakarta.persistence.EntityManager;

/**
 * Ejercicio 111 · @Enumerated y @Embeddable.
 *
 * <p>Teoría: {@code teoria/12_Spring_Data_JPA.md} (sección 12.1).
 */
public final class Ej111EnumAndEmbeddable {

    public enum Estado {
        ACTIVO, BAJA
    }

    private Ej111EnumAndEmbeddable() {
    }

    /**
     * Persiste y relee para comprobar que enum y embebido se mapean bien.
     *
     * @param em EntityManager
     * @param s  socio a guardar
     * @return el socio recuperado por id (fresh desde BD)
     */
    public static Socio111 guardarYRecargar(EntityManager em, Socio111 s) {
        // TODO 1: begin tx, persist(s), commit.
        // TODO 2: em.clear() para forzar lectura desde BD (no caché de 1er nivel).
        // TODO 3: devuelve em.find(Socio111.class, s.getId()).
        return null;
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }

    /**
     * TODO extra 1: Comprueba si un campo específico está anotado con @Enumerated.
     */
    public static boolean desafioTieneEnumerated(Class<?> clase, String campo) {
        try {
            var f = clase.getDeclaredField(campo);
            return f.isAnnotationPresent(jakarta.persistence.Enumerated.class);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * TODO extra 2: Obtiene el tipo deEnumType configurado (STRING vs ORDINAL).
     */
    public static jakarta.persistence.EnumType desafioObtenerEnumType(Class<?> clase, String campo) {
        try {
            var f = clase.getDeclaredField(campo);
            var env = f.getAnnotation(jakarta.persistence.Enumerated.class);
            return env != null ? env.value() : null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * TODO extra 3: Comprueba si un tipo está anotado con @Embeddable.
     */
    public static boolean desafioEsEmbeddable(Class<?> clase) {
        return clase.isAnnotationPresent(jakarta.persistence.Embeddable.class);
    }

    /**
     * TODO extra 4: Comprueba si un campo de una clase está anotado con @Embedded.
     */
    public static boolean desafioTieneEmbedded(Class<?> clase, String campo) {
        try {
            var f = clase.getDeclaredField(campo);
            return f.isAnnotationPresent(jakarta.persistence.Embedded.class);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * TODO extra 5: Crea una instancia rápida del objeto embebido Direccion.
     */
    public static Direccion desafioCrearDireccion(String calle, String ciudad) {
        var d = new Direccion();
        d.setCalle(calle);
        d.setCiudad(ciudad);
        return d;
    }

    /**
     * TODO extra 6: Crea una instancia rápida de Pedido.
     */
    public static Pedido desafioCrearPedido(EstadoPedido estado, Direccion dir) {
        var p = new Pedido();
        p.setEstado(estado);
        p.setDireccionEnvio(dir);
        return p;
    }

    /**
     * TODO extra 7: Lanza una excepción si el pedido a validar no tiene un estado asignado.
     */
    public static void desafioValidarEstadoAsignado(Pedido p) {
        if (p == null || p.getEstado() == null) {
            throw new IllegalArgumentException("Estado no asignado");
        }
    }

    /**
     * TODO extra 8: Comprueba si el pedido tiene asignada una dirección en una ciudad específica.
     */
    public static boolean desafioCoincideCiudad(Pedido p, String ciudad) {
        return p != null && p.getDireccionEnvio() != null && ciudad.equals(p.getDireccionEnvio().getCiudad());
    }

    /**
     * TODO extra 9: Comprueba si el estado del pedido es ENTREGADO de forma coherente.
     */
    public static boolean desafioEsEntregado(Pedido p) {
        return p != null && EstadoPedido.ENTREGADO == p.getEstado();
    }

    /**
     * TODO extra 10: Retorna verdadero si la dirección de envío del pedido no es nula.
     */
    public static boolean desafioTieneDireccion(Pedido p) {
        return p != null && p.getDireccionEnvio() != null;
    }

}
