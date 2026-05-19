package com.masterclass.api.b10_arch;

/**
 * Ejercicio 091 · Factory y Builder.
 *
 * <p>Teoría: {@code teoria/10_Arquitectura_Patrones.md} (sección 10.2).
 */
public final class Ej091FactoryAndBuilder {

    public record Usuario(String email, String rol, boolean activo) {
    }

    /** Builder fluido para Usuario. */
    public static final class UsuarioBuilder {
        private String email;
        private String rol = "USER";
        private boolean activo = true;

        public UsuarioBuilder email(String e) {
            // TODO 1: asigna 'e' y devuelve this (API fluida).
            return this;
        }

        public UsuarioBuilder rol(String r) {
            // TODO 2: asigna 'r' y devuelve this.
            return this;
        }

        public UsuarioBuilder activo(boolean a) {
            // TODO 3: asigna 'a' y devuelve this.
            return this;
        }

        public Usuario build() {
            // TODO 4: valida que email no sea null/blank (invariante de construcción).
            // TODO 5: construye y devuelve el Usuario con los campos acumulados.
            // TODO 6: si no se tocó rol/activo, deben usarse los valores por defecto.
            return null;
        }
    }

    private Ej091FactoryAndBuilder() {
    }

    /**
     * Factory: crea usuarios "admin" preconfigurados.
     *
     * @param email correo del admin
     * @return Usuario con rol "ADMIN" y activo
     */
    public static Usuario crearAdmin(String email) {
        // TODO 7: usa el UsuarioBuilder (no construyas el record a mano).
        // TODO 8: fija rol "ADMIN" y activo true.
        // TODO 9: la Factory encapsula la "receta" de un tipo concreto de usuario.
        // TODO 10: devuelve el Usuario construido.
        return null;
    }

    public static void main(String[] args) {
        System.out.println(crearAdmin("admin@x.com"));
    }
}
