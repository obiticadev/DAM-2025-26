package com.masterclass.api.b12_jpa;

import java.util.Objects;

/**
 * Ejercicio 113 · Identidad de entidades (equals/hashCode con id).
 *
 * <p>Teoría: {@code teoria/12_Spring_Data_JPA.md} (sección 12.1).
 *
 * <p>En entidades JPA, equals/hashCode mal hechos rompen Sets y relaciones.
 * Regla pragmática: igualdad por id de negocio cuando existe.
 */
public final class Ej113EqualsHashCodeEntities {

    private Ej113EqualsHashCodeEntities() {
    }

    @jakarta.persistence.Entity
    public static class Cuenta113 {
        @jakarta.persistence.Id
        private String iban;

        private double saldo;

        protected Cuenta113() {
        }

        public Cuenta113(String iban, double saldo) {
            this.iban = iban;
            this.saldo = saldo;
        }

        public String getIban() {
            return iban;
        }

        /**
         * @param o objeto a comparar
         * @return true si es Cuenta113 con el mismo IBAN (identidad de negocio)
         */
        @Override
        public boolean equals(Object o) {
            // TODO 1: si o == this -> true.
            // TODO 2: si o == null -> false.
            // TODO 3: usa getClass() != o.getClass() -> false
            //         (cuidado con proxies de Hibernate; aquí basta getClass).
            // TODO 4: castea a Cuenta113.
            // TODO 5: compara SOLO 'iban' con Objects.equals.
            // TODO 6: devuelve el resultado.
            return false;
        }

        /**
         * @return hash basado en el id de negocio (iban)
         */
        @Override
        public int hashCode() {
            // TODO 7: basa el hash en 'iban' (mismo campo que equals).
            // TODO 8: usa Objects.hash(iban) o iban.hashCode() con control de null.
            // TODO 9: dos cuentas con mismo iban -> mismo hash (contrato).
            // TODO 10: NO uses 'saldo' en equals/hashCode (cambia y rompería el contrato).
            return 0;
        }
    }

    public static void main(String[] args) {
        var a = new Cuenta113("ES1", 100);
        var b = new Cuenta113("ES1", 999);
        System.out.println(a.equals(b));
    }

    /**
     * TODO extra 1: Comprueba que una instancia de Cliente sea igual a sí misma (reflexividad).
     */
    public static boolean desafioReflexivo(Cliente c) {
        return c != null && c.equals(c);
    }

    /**
     * TODO extra 2: Comprueba la simetría de equals entre dos instancias de Cliente.
     */
    public static boolean desafioSimetrico(Cliente c1, Cliente c2) {
        if (c1 == null || c2 == null) return false;
        return c1.equals(c2) == c2.equals(c1);
    }

    /**
     * TODO extra 3: Comprueba que equals devuelva falso al comparar un Cliente con nulo.
     */
    public static boolean desafioNuloFalso(Cliente c) {
        return c != null && !c.equals(null);
    }

    /**
     * TODO extra 4: Comprueba que equals devuelva falso al comparar un Cliente con otro tipo de objeto.
     */
    public static boolean desafioClaseDiferenteFalso(Cliente c) {
        return c != null && !c.equals(new Object());
    }

    /**
     * TODO extra 5: Comprueba si dos clientes con el mismo código de negocio (business key: uuid) son iguales.
     */
    public static boolean desafioMismoUuidIguales(Cliente c1, Cliente c2) {
        return c1 != null && c1.equals(c2);
    }

    /**
     * TODO extra 6: Comprueba si dos clientes con diferente código de negocio (business key: uuid) no son iguales.
     */
    public static boolean desafioDiferenteUuidNoIguales(Cliente c1, Cliente c2) {
        return c1 != null && !c1.equals(c2);
    }

    /**
     * TODO extra 7: Comprueba que el hashCode de dos clientes iguales sea el mismo de forma coherente.
     */
    public static boolean desafioHashCodeCoherente(Cliente c1, Cliente c2) {
        if (c1 == null || c2 == null) return false;
        if (c1.equals(c2)) {
            return c1.hashCode() == c2.hashCode();
        }
        return true;
    }

    /**
     * TODO extra 8: Crea un Cliente con el uuid especificado.
     */
    public static Cliente desafioCrearClienteConUuid(String uuid, String nombre) {
        var c = new Cliente();
        c.setUuid(uuid);
        c.setNombre(nombre);
        return c;
    }

    /**
     * TODO extra 9: Comprueba que el equals siga funcionando correctamente cuando las entidades cambian su ID de base de datos (invariancia de business key).
     */
    public static boolean desafioInvarianteIdDb(Cliente c1, Cliente c2, Long nuevoId) {
        boolean antes = c1.equals(c2);
        c1.setId(nuevoId);
        boolean despues = c1.equals(c2);
        return antes == despues;
    }

    /**
     * TODO extra 10: Retorna verdadero si el UUID de un cliente no es nulo ni vacío.
     */
    public static boolean desafioTieneUuidValido(Cliente c) {
        return c != null && c.getUuid() != null && !c.getUuid().isBlank();
    }

}
