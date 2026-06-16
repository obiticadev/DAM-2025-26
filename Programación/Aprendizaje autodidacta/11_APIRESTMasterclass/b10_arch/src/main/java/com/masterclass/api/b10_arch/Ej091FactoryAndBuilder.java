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
        // GUÍA: la fluidez del Builder (teoría 10.7) — email(e) devuelve el propio
        // builder, así que el test comprueba assertEquals(b, desafioFijarEmail(b,...)):
        // el objeto retornado ES el mismo. CUIDADO: esto solo se cumple si el
        // método base email() hace `return this` (TODO 1 del ejercicio base).
        return builder.email(email);
    }

    /**
     * TODO extra 2: Asigna un rol en el builder y lo devuelve.
     */
    public static UsuarioBuilder desafioFijarRol(UsuarioBuilder builder, String rol) {
        // GUÍA: igual que el reto 1 pero para rol. El test exige que devuelva el
        // mismo builder (encadenamiento). Depende de que rol() base haga return this.
        return builder.rol(rol);
    }

    /**
     * TODO extra 3: Asigna el estado activo en el builder y lo devuelve.
     */
    public static UsuarioBuilder desafioFijarActivo(UsuarioBuilder builder, boolean activo) {
        // GUÍA: tercer setter fluido. El test lo invoca con false y espera el
        // mismo builder de vuelta. Patrón idéntico a los retos 1 y 2.
        return builder.activo(activo);
    }

    /**
     * TODO extra 4: Valida que el email no sea nulo o vacío para construir.
     */
    public static void desafioValidarEmailConstruccion(String email) {
        // GUÍA: la invariante que build() comprueba antes de crear el Usuario:
        // email obligatorio. isBlank() rechaza null, "" y "   ". El test pasa null
        // y " " (espacio) y espera IllegalArgumentException en ambos.
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email no válido");
        }
    }

    /**
     * TODO extra 5: Construye un Usuario a partir del builder.
     */
    public static Usuario desafioConstruirUsuario(UsuarioBuilder builder) {
        // GUÍA: dispara la construcción final. El test pasa un builder con email
        // y espera assertNotNull(resultado). CUIDADO: esto solo funciona cuando el
        // método base build() esté implementado (TODO 4-6); hasta entonces devuelve
        // null y este reto falla en ejecución (no en compilación).
        return builder.build();
    }

    /**
     * TODO extra 6: Comprueba si un Usuario tiene los valores por defecto.
     */
    public static boolean desafioVerificarValoresPorDefecto(Usuario u) {
        // GUÍA: comprueba que un Usuario "de fábrica" tiene los defaults del
        // builder: rol "USER" y activo true. CUIDADO con el orden de equals:
        // "USER".equals(u.rol()) (literal primero) para no romper si rol fuese
        // null. El test construye new Usuario("a@b.com","USER",true) y espera true.
        return "USER".equals(u.rol()) && u.activo();
    }

    /**
     * TODO extra 7: Instancia un nuevo builder vacío.
     */
    public static UsuarioBuilder desafioInstanciarBuilder() {
        // GUÍA: punto de partida del patrón — un builder nuevo con sus defaults
        // (rol "USER", activo true) ya puestos en la declaración de campos. El
        // test solo comprueba assertNotNull.
        return new UsuarioBuilder();
    }

    /**
     * TODO extra 8: Preconfigura el builder con los datos de un administrador.
     */
    public static UsuarioBuilder desafioFijarRecetaAdmin(UsuarioBuilder builder) {
        // GUÍA: la "receta" de la Factory — encadena rol("ADMIN").activo(true)
        // gracias a la fluidez. El test luego pone el email y comprueba que
        // build().rol() es "ADMIN". Es justo lo que crearAdmin() (método base)
        // encapsula para no repetir esta configuración por todas partes.
        return builder.rol("ADMIN").activo(true);
    }

    /**
     * TODO extra 9: Retorna una descripción textual de lo que encapsula una Factory.
     */
    public static String desafioConceptoFactory() {
        // GUÍA: método "testigo" — describe en una frase qué hace una Factory. El
        // test solo comprueba assertNotNull (no el texto exacto).
        return "Encapsula la creacion compleja de objetos preconfigurados";
    }

    /**
     * TODO extra 10: Retorna un Usuario administrador construido de forma completa.
     */
    public static Usuario desafioObtenerUsuarioAdminFinal(String email) {
        // GUÍA: integra TODA la cadena Factory+Builder — instancia → receta admin
        // → email → valida → build. Se lee de dentro afuera. El test espera un
        // Usuario con rol "ADMIN" y activo true. CUIDADO: depende del método base
        // build() (TODO 4-6); hasta implementarlo, devolverá null en ejecución.
        desafioValidarEmailConstruccion(email);
        return desafioConstruirUsuario(desafioFijarEmail(desafioFijarRecetaAdmin(desafioInstanciarBuilder()), email));
    }

}
