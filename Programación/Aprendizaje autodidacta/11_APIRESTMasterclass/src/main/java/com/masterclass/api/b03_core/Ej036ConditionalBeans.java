package com.masterclass.api.b03_core;

/**
 * Ejercicio 036 · Beans condicionales por entorno (@Profile/@Conditional).
 *
 * <p>Teoría: {@code teoria/03_Spring_Core_IoC_DI.md} (sección 3.5).
 *
 * <p>Sin Spring: replica la decisión "qué implementación según el perfil activo".
 */
public final class Ej036ConditionalBeans {

    public interface AlmacenFicheros {
        String donde();
    }

    public static class AlmacenLocal implements AlmacenFicheros {
        public String donde() {
            return "disco-local";
        }
    }

    public static class AlmacenS3 implements AlmacenFicheros {
        public String donde() {
            return "aws-s3";
        }
    }

    private Ej036ConditionalBeans() {
    }

    /**
     * Elige la implementación según el perfil.
     *
     * @param perfil "dev", "test" o "prod"
     * @return AlmacenLocal para dev/test; AlmacenS3 para prod
     * @throws IllegalArgumentException si el perfil es desconocido
     */
    public static AlmacenFicheros segunPerfil(String perfil) {
        // TODO 1: si perfil es null, lanza IllegalArgumentException.
        // TODO 2: normaliza el perfil (trim + minúsculas).
        // TODO 3: "dev" debe resolver a almacenamiento local.
        // TODO 4: "test" también a local (mismo comportamiento que dev).
        // TODO 5: agrupa dev/test en la misma rama (no dupliques el new).
        // TODO 6: "prod" debe resolver a AlmacenS3.
        // TODO 7: cualquier otro perfil -> IllegalArgumentException con el valor recibido.
        // TODO 8: devuelve una instancia nueva del almacén correspondiente.
        // TODO 9: el contrato donde() de local es "disco-local".
        // TODO 10: el contrato donde() de prod es "aws-s3".
        return null;
    }

    public static void main(String[] args) {
        System.out.println(segunPerfil("prod").donde());
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: si perfil es null, lanza IllegalArgumentException.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: normaliza el perfil (trim + minúsculas).
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: "dev" debe resolver a almacenamiento local.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: "test" también a local (mismo comportamiento que dev).
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: agrupa dev/test en la misma rama (no dupliques el new).
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: "prod" debe resolver a AlmacenS3.
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: cualquier otro perfil -> IllegalArgumentException con el valor recibido.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: devuelve una instancia nueva del almacén correspondiente.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: el contrato donde() de local es "disco-local".
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: el contrato donde() de prod es "aws-s3".
    }

}
