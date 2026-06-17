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

        /**
     * RETO EXTRA 01: Comprueba que no contenga espacios ni caracteres prohibidos.
     */
    public static boolean esNombreUsuarioValido(String username) {
        // GUÍA: username sin espacios ni caracteres peligrosos (regex).
        // 1. Si username es null -> false.
        // 2. Permite letras, dígitos y unos pocos separadores seguros.
        // PISTA: return username != null && username.matches("[a-zA-Z0-9._-]+");
        // OJO: el test pasa "ada" y espera true. La clase [..]+ ya excluye espacios;
        // restringir el alfabeto evita inyección y suplantación (parecido a Ej156-01).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esNombreUsuarioValido");
    }

    /**
     * RETO EXTRA 02: Comprueba si la cuenta esta marcada como bloqueada o suspendida.
     */
    public static boolean esUsuarioBloqueado(String status) {
        // GUÍA: estado de cuenta contra un conjunto de estados "no operativos".
        // 1. Si status es null -> false.
        // 2. true si el estado es de bloqueo/suspensión.
        // PISTA: Set.of("BLOCKED","SUSPENDED","LOCKED").contains(status.toUpperCase());
        // OJO: el test pasa "BLOCKED" y espera true. Normaliza a mayúsculas para
        // tolerar "blocked"/"Blocked"; relaciona con el flag locked de teoría 18.4.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esUsuarioBloqueado");
    }

    /**
     * RETO EXTRA 03: Determina si es de tipo UsernameNotFoundException.
     */
    public static boolean esExcepcionUserNotFound(Throwable t) {
        // GUÍA: detectar "usuario no encontrado" por el mensaje.
        // 1. Si t o t.getMessage() son null -> false.
        // 2. En Spring sería UsernameNotFoundException; aquí va por mensaje.
        // PISTA: t != null && t.getMessage() != null
        //        && t.getMessage().toLowerCase().contains("notfound");
        // OJO: el test pasa IllegalArgumentException("notfound") y espera true.
        // CUIDADO: de cara al cliente esto NO debe distinguirse de "clave mala"
        // (user enumeration, teoría 18.4); el detalle se queda en logs internos.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esExcepcionUserNotFound");
    }

    /**
     * RETO EXTRA 04: Genera la ficha formateada del usuario cargado.
     */
    public static String crearUserDetailsString(String u, String roles) {
        // GUÍA: representación legible del UserDetails cargado.
        // 1. Combina username y roles en una cadena descriptiva.
        // PISTA: return String.format("User(%s, roles=[%s])", u, roles);
        // OJO: el test pasa ("u","USER") y solo exige .contains("USER"); el formato
        // es libre mientras incluya los roles. NUNCA incluyas aquí el passwordHash.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearUserDetailsString");
    }

    /**
     * RETO EXTRA 05: Determina si la cuenta es apta para loguearse.
     */
    public static boolean esCuentaHabilitada(boolean active, boolean blocked) {
        // GUÍA: una línea — misma regla que puedeAutenticar (teoría 18.4).
        // return active && !blocked;
        // OJO: el test pasa (true, false) y espera true. Una cuenta activa pero
        // bloqueada NO puede loguearse: el AND con la negación de blocked es la clave.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esCuentaHabilitada");
    }

    /**
     * RETO EXTRA 06: Separa roles por comas.
     */
    public static java.util.List<String> extraerRolesDeString(String authList) {
        // GUÍA: CSV de roles -> List<String> (streams, teoría 1.3).
        // 1. Si authList es null/blank -> List.of() (lista vacía, no null).
        // 2. Parte por comas, recorta espacios y descarta vacíos.
        // PISTA: java.util.Arrays.stream(authList.split(","))
        //        .map(String::trim).filter(s -> !s.isEmpty()).toList();
        // OJO: el test pasa "USER" (sin comas) y espera List.of("USER"); el split
        // de una cadena sin separador devuelve la cadena entera en un elemento.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extraerRolesDeString");
    }

    /**
     * RETO EXTRA 07: Verifica que la contrasena de base de datos no sea nula ni vacia.
     */
    public static boolean esPasswordValidaDb(String dbHash) {
        // GUÍA: una línea — el hash de BD debe existir y no estar vacío.
        // return dbHash != null && !dbHash.isBlank();
        // OJO: el test pasa "hash" y espera true. Un usuario sin hash en BD nunca
        // debería poder autenticar (cuenta mal aprovisionada).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esPasswordValidaDb");
    }

    /**
     * RETO EXTRA 08: Determina si no se supero el limite de intentos de acceso.
     */
    public static boolean esIntentoLoginPermitido(int fallidos, int max) {
        // GUÍA: una línea — control de intentos fallidos.
        // return fallidos < max;
        // OJO: el test pasa (2, 5) y espera true. Decide el límite: aquí permitir
        // mientras fallidos < max; al llegar a max se bloquea (lleva al flag locked).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esIntentoLoginPermitido");
    }

    /**
     * RETO EXTRA 09: Genera el texto de log de auditoria de login.
     */
    public static String generarResumenAuditoria(String u, boolean success) {
        // GUÍA: línea de auditoría con el resultado del login.
        // 1. Mapea el booleano a un literal: success ? "SUCCESS" : "FAILURE".
        // PISTA: return String.format("login %s user=%s", success ? "SUCCESS" : "FAILURE", u);
        // OJO: el test pasa ("u", true) y exige que el resultado .contains("SUCCESS").
        // El literal debe ir en MAYÚSCULAS exactas. No registres la contraseña.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para generarResumenAuditoria");
    }

    /**
     * RETO EXTRA 10: Determina si el error ocurrio por caida de la conexion con el backend.
     */
    public static boolean esFalloServicioUser(Throwable t) {
        // GUÍA: distinguir fallo de INFRAESTRUCTURA de fallo de credenciales.
        // 1. Si t es null -> false.
        // 2. Un fallo de conexión con el backend es de tipo I/O.
        // PISTA: return t instanceof java.io.IOException;
        // OJO: el test pasa new IOException() y espera true. Importa la distinción:
        // un IOException es un 500 (problema nuestro), no un 401 (credenciales).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esFalloServicioUser");
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