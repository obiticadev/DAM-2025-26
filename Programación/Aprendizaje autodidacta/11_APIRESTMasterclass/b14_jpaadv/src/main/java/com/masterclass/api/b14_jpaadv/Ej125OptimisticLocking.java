package com.masterclass.api.b14_jpaadv;

import jakarta.persistence.*;

/**
 * Ejercicio 125 · Bloqueo optimista con @Version.
 *
 * <p>Teoría: {@code teoria/14_JPA_Avanzado.md} (sección 14.2).
 */
public final class Ej125OptimisticLocking {

    private Ej125OptimisticLocking() {
    }

    /**
     * Persiste un producto y devuelve su id.
     *
     * @param em EntityManager
     * @param p  producto
     * @return id generado
     */
    public static Long guardar(EntityManager em, ProdVer125 p) {
        // TODO 1: begin tx, persist(p), commit.
        // TODO 2: devuelve p.getId().
        return null;
    }

    /**
     * Actualiza el precio en una transacción independiente.
     *
     * @param em      EntityManager
     * @param id      id del producto
     * @param precio  nuevo precio
     */
    public static void actualizarPrecio(EntityManager em, Long id, double precio) {
        // TODO 3: begin tx.
        // TODO 4: find del producto (queda managed con su version actual).
        // TODO 5: cambia el precio con el setter.
        // TODO 6: commit -> Hibernate incrementa la columna @Version.
        // TODO 7: si otra tx ya cambió la version, aquí saltaría OptimisticLockException
        //         (el test provoca ese escenario con dos EntityManager).
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }

    /**
     * Reto Extra 1: Obtiene el precio de un producto.
     */
    public static double obtenerPrecio(ProdVer125 p) {
        // GUÍA: una línea — return p.getPrecio();
        // El getter getPrecio() ya existe en ProdVer125. El test espera 100.0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerPrecio");
    }

    /**
     * Reto Extra 2: Obtiene la version de un producto.
     */
    public static long obtenerVersion(ProdVer125 p) {
        // GUÍA: teoría 14.3. La versión es 0 al crear (Hibernate la sube en cada UPDATE).
        // ⚠ CUIDADO: ProdVer125 NO tiene aún getter para 'version'. Añádele:
        //     public long getVersion() { return version; }
        //   (los retos 4 y 10 también lo necesitan). NO toques @Version ni la subas a mano.
        // 1. Una vez añadido el getter: return p.getVersion();
        // OJO: el test espera 0 en un producto recién creado.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerVersion");
    }

    /**
     * Reto Extra 3: Comprueba si el precio es mayor que un limite.
     */
    public static boolean precioEsMayor(ProdVer125 p, double limite) {
        // GUÍA: una línea — return p.getPrecio() > limite;
        // OJO: el test pide true con limite 50 y false con limite 150 (precio 100).
        // Es comparación estricta >. Reutiliza obtenerPrecio (reto 1) si prefieres.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para precioEsMayor");
    }

    /**
     * Reto Extra 4: Comprueba si la version es inicial (0).
     */
    public static boolean esVersionInicial(ProdVer125 p) {
        // GUÍA: usa el getVersion() que añadiste en el reto 2.
        // 1. Una línea: return p.getVersion() == 0;  (o reutiliza obtenerVersion).
        // OJO: el test crea un producto nuevo y espera true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esVersionInicial");
    }

    /**
     * Reto Extra 5: Incrementa el precio en base a un porcentaje.
     */
    public static void incrementarPrecioPorcentaje(ProdVer125 p, double pct) {
        // GUÍA: aplica el porcentaje sobre el precio actual y guárdalo con el setter.
        // 1. nuevo = precio * (1 + pct/100). Con pct=10 → 100 * 1.10 = 110.
        // 2. p.setPrecio(p.getPrecio() * (1 + pct / 100));
        // ⚠ CUIDADO: pct es double, así que pct/100 NO sufre división entera
        //    (10/100 = 0.1, no 0). El test espera 110.0 exactos.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para incrementarPrecioPorcentaje");
    }

    /**
     * Reto Extra 6: Crea un nuevo producto.
     */
    public static ProdVer125 crearProducto(double precio) {
        // GUÍA: una línea — return new ProdVer125(precio);
        // El constructor público recibe el precio. El test solo comprueba que no es null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearProducto");
    }

    /**
     * Reto Extra 7: Descuenta un valor del precio.
     */
    public static void aplicarDescuento(ProdVer125 p, double desc) {
        // GUÍA: resta 'desc' (valor absoluto, NO porcentaje) al precio actual.
        // 1. p.setPrecio(p.getPrecio() - desc);
        // OJO: el test resta 20 a 100 y espera 80.0 (no confundir con el reto 5,
        //      que era porcentual).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para aplicarDescuento");
    }

    /**
     * Reto Extra 8: Comprueba si el producto tiene ID.
     */
    public static boolean tieneId(ProdVer125 p) {
        // GUÍA: el id solo se asigna al persistir (@GeneratedValue). Un producto
        // recién creado en memoria tiene id null.
        // 1. Una línea: return p.getId() != null;
        // OJO: el test crea un producto sin guardar y espera false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneId");
    }

    /**
     * Reto Extra 9: Valida si el precio es positivo.
     */
    public static boolean precioEsValido(ProdVer125 p) {
        // GUÍA: "válido" = precio estrictamente positivo.
        // 1. Una línea: return p.getPrecio() > 0;
        // OJO: el test usa precio 100 y espera true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para precioEsValido");
    }

    /**
     * Reto Extra 10: Retorna formato de texto del producto.
     */
    public static String formatearProducto(ProdVer125 p) {
        // GUÍA: usa getPrecio() y el getVersion() del reto 2.
        // 1. return "Prod[Precio=" + p.getPrecio() + ", Ver=" + p.getVersion() + "]";
        // OJO: el test espera EXACTAMENTE "Prod[Precio=100.0, Ver=0]". Fíjate en
        //      que 100.0 lleva el ".0" (es un double) y en los espacios tras la coma.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearProducto");
    }



}

@Entity
class ProdVer125 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double precio;

    // TODO 8: anota 'version' con @jakarta.persistence.Version.
    // TODO 9: el tipo long/Integer es válido para @Version; aquí usamos long.
    private long version;

    protected ProdVer125() {
    }

    public ProdVer125(double precio) {
        // TODO 10: asigna el precio inicial.
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double p) {
        this.precio = p;
    }
}
