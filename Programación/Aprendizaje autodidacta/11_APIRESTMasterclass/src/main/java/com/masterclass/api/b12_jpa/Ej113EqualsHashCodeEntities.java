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
}
