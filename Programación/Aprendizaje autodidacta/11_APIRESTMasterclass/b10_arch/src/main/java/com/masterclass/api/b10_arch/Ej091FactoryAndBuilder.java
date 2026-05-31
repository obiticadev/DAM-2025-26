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

    /**
     * TODO extra 1: Asigna un email en el builder y lo devuelve.
     */
    public static UsuarioBuilder desafioFijarEmail(UsuarioBuilder builder, String email) {
        return builder.email(email);
    }

    /**
     * TODO extra 2: Asigna un rol en el builder y lo devuelve.
     */
    public static UsuarioBuilder desafioFijarRol(UsuarioBuilder builder, String rol) {
        return builder.rol(rol);
    }

    /**
     * TODO extra 3: Asigna el estado activo en el builder y lo devuelve.
     */
    public static UsuarioBuilder desafioFijarActivo(UsuarioBuilder builder, boolean activo) {
        return builder.activo(activo);
    }

    /**
     * TODO extra 4: Valida que el email no sea nulo o vacío para construir.
     */
    public static void desafioValidarEmailConstruccion(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email no válido");
        }
    }

    /**
     * TODO extra 5: Construye un Usuario a partir del builder.
     */
    public static Usuario desafioConstruirUsuario(UsuarioBuilder builder) {
        return builder.build();
    }

    /**
     * TODO extra 6: Comprueba si un Usuario tiene los valores por defecto.
     */
    public static boolean desafioVerificarValoresPorDefecto(Usuario u) {
        return "USER".equals(u.rol()) && u.activo();
    }

    /**
     * TODO extra 7: Instancia un nuevo builder vacío.
     */
    public static UsuarioBuilder desafioInstanciarBuilder() {
        return new UsuarioBuilder();
    }

    /**
     * TODO extra 8: Preconfigura el builder con los datos de un administrador.
     */
    public static UsuarioBuilder desafioFijarRecetaAdmin(UsuarioBuilder builder) {
        return builder.rol("ADMIN").activo(true);
    }

    /**
     * TODO extra 9: Retorna una descripción textual de lo que encapsula una Factory.
     */
    public static String desafioConceptoFactory() {
        return "Encapsula la creacion compleja de objetos preconfigurados";
    }

    /**
     * TODO extra 10: Retorna un Usuario administrador construido de forma completa.
     */
    public static Usuario desafioObtenerUsuarioAdminFinal(String email) {
        desafioValidarEmailConstruccion(email);
        return desafioConstruirUsuario(desafioFijarEmail(desafioFijarRecetaAdmin(desafioInstanciarBuilder()), email));
    }

}
