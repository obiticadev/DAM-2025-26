package com.masterclass.api.b18_sec;

import java.util.List;
import java.util.Optional;

/**
 * Ejercicio 158 · UserDetailsService (cargar usuario desde "BD").
 *
 * <p>Teoría: {@code teoria/18_Seguridad_JWT.md} (sección 18.4).
 *
 * <p>Simulamos un repositorio: una lista de {@link CuentaBd158}. El método
 * {@code loadUserByUsername} se modela como función pura que devuelve los
 * detalles necesarios para autenticar.
 */
public final class Ej158UserDetailsService {

    private Ej158UserDetailsService() {
    }

    /**
     * Carga los detalles de un usuario por username desde el repositorio.
     *
     * @param repo lista de cuentas (simula la tabla users); no null
     * @param username nombre a buscar; no null/blank
     * @return Optional con la cuenta si existe y está activa
     * @throws IllegalArgumentException si repo o username son inválidos
     */
    public static Optional<CuentaBd158> loadUserByUsername(List<CuentaBd158> repo, String username) {
        // TODO 1: si repo es null -> IllegalArgumentException.
        // TODO 2: si username es null o blank -> IllegalArgumentException.
        // TODO 3: normaliza el username (trim + lower) para comparar.
        // TODO 4: recorre el repo buscando coincidencia exacta normalizada.
        // TODO 5: si no hay coincidencia -> Optional.empty() (NO excepción que filtre info).
        // TODO 6: si la cuenta existe pero locked == true -> Optional.empty().
        // TODO 7: no devuelvas la contraseña hash en logs.
        // TODO 8: documenta que Spring envolvería esto en un UserDetails.
        // TODO 9: usa stream().filter().findFirst() o bucle equivalente.
        // TODO 10: devuelve el Optional resultante.
        return Optional.empty();
    }

    /**
     * Indica si la cuenta puede autenticarse (activa y no bloqueada).
     *
     * @param cuenta cuenta cargada (no null)
     * @return true si enabled y no locked
     */
    public static boolean puedeAutenticar(CuentaBd158 cuenta) {
        // TODO 1: si cuenta es null -> IllegalArgumentException.
        // TODO 2: comprueba el flag enabled.
        // TODO 3: comprueba el flag locked (cuenta bloqueada por intentos fallidos).
        // TODO 4: una cuenta deshabilitada nunca autentica.
        // TODO 5: una cuenta bloqueada nunca autentica aunque la password sea válida.
        // TODO 6: combina ambas condiciones con AND lógico.
        // TODO 7: no consideres aquí la contraseña (eso es el PasswordEncoder).
        // TODO 8: documenta el orden de comprobaciones de Spring (account status checks).
        // TODO 9: evita NPE accediendo a campos primitivos del record.
        // TODO 10: devuelve enabled && !locked.
        return false;
    }

    public static void main(String[] args) {
        List<CuentaBd158> repo = List.of(
                new CuentaBd158("ana", "$2a$10$xxx", "ROLE_ADMIN", true, false));
        System.out.println(loadUserByUsername(repo, "ana"));
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: si repo es null -> IllegalArgumentException.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: si username es null o blank -> IllegalArgumentException.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: normaliza el username (trim + lower) para comparar.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: recorre el repo buscando coincidencia exacta normalizada.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: si no hay coincidencia -> Optional.empty() (NO excepción que filtre info).
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: si la cuenta existe pero locked == true -> Optional.empty().
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: no devuelvas la contraseña hash en logs.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: documenta que Spring envolvería esto en un UserDetails.
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: usa stream().filter().findFirst() o bucle equivalente.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve el Optional resultante.
    }

}

/**
 * Cuenta persistida (simula fila de la tabla users).
 *
 * @param username nombre único
 * @param passwordHash hash BCrypt
 * @param rol rol principal (p.ej. ROLE_USER)
 * @param enabled cuenta activa
 * @param locked cuenta bloqueada
 */
record CuentaBd158(String username, String passwordHash, String rol, boolean enabled, boolean locked) {
}
