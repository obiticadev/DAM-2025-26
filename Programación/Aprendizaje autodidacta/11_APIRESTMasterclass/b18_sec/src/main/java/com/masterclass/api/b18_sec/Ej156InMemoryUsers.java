package com.masterclass.api.b18_sec;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Ejercicio 156 · Usuarios en memoria (InMemoryUserDetailsManager simulado).
 *
 * <p>Teoría: {@code teoria/18_Seguridad_JWT.md} (sección 18.2).
 *
 * <p>Modelamos un almacén de usuarios en memoria como un mapa puro
 * usuario -&gt; {@link Usuario156}, sin levantar Spring.
 */
public final class Ej156InMemoryUsers {

    private Ej156InMemoryUsers() {
    }

    /**
     * Busca un usuario por su nombre en el almacén en memoria.
     *
     * @param almacen mapa username -&gt; Usuario156 (no null)
     * @param username nombre a buscar (no null/blank)
     * @return Optional con el usuario, o vacío si no existe / está deshabilitado
     * @throws IllegalArgumentException si almacen o username son inválidos
     */
    public static Optional<Usuario156> buscar(Map<String, Usuario156> almacen, String username) {
        // TODO 1: si almacen es null -> IllegalArgumentException.
        // TODO 2: si username es null o blank -> IllegalArgumentException.
        // TODO 3: la búsqueda de username debe ser case-insensitive (normaliza).
        // TODO 4: recupera el usuario del mapa por la clave normalizada.
        // TODO 5: si no existe -> Optional.empty().
        // TODO 6: si existe pero enabled == false -> Optional.empty().
        // TODO 7: nunca lances excepción por usuario no encontrado (evita user enumeration).
        // TODO 8: no devuelvas la contraseña en logs.
        // TODO 9: envuelve el resultado en Optional.
        // TODO 10: devuelve el Optional.
        return Optional.empty();
    }

    /**
     * Verifica que el usuario tenga al menos uno de los roles requeridos.
     *
     * @param usuario usuario encontrado (no null)
     * @param rolesRequeridos roles aceptados (no null, no vacío)
     * @return true si la intersección de roles no es vacía
     */
    public static boolean tieneAlgunRol(Usuario156 usuario, Set<String> rolesRequeridos) {
        // TODO 1: si usuario es null -> IllegalArgumentException.
        // TODO 2: si rolesRequeridos es null o vacío -> IllegalArgumentException.
        // TODO 3: obtén los roles del usuario (puede ser conjunto vacío).
        // TODO 4: normaliza el formato de rol (con/sin prefijo ROLE_) de forma consistente.
        // TODO 5: calcula la intersección entre roles del usuario y los requeridos.
        // TODO 6: si la intersección es no vacía -> true.
        // TODO 7: si el usuario no tiene roles -> false.
        // TODO 8: no modifiques los conjuntos de entrada (inmutabilidad).
        // TODO 9: documenta que esto es autorización, no autenticación.
        // TODO 10: devuelve el booleano.
        return false;
    }

    public static void main(String[] args) {
        Map<String, Usuario156> db = Map.of(
                "ana", new Usuario156("ana", "{bcrypt}xxx", Set.of("ROLE_ADMIN"), true));
        System.out.println(buscar(db, "ANA"));
    }

        /**
     * RETO EXTRA 01: Verifica si el nombre de usuario tiene longitud y caracteres correctos.
     */
    public static boolean esUsernameSeguro(String user) {
        // GUÍA: teoría 18.2 (validación de username) — una regex.
        // 1. Si user es null -> false.
        // 2. Define la política: letras, dígitos y guion bajo, longitud razonable.
        // PISTA: return user != null && user.matches("[a-zA-Z0-9_]{3,20}");
        // OJO: el test pasa "user123" (7 chars, alfanumérico) y espera true. Evita
        // espacios y símbolos que faciliten inyección o suplantación.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esUsernameSeguro");
    }

    /**
     * RETO EXTRA 02: Comprueba si el rol forma parte de los del sistema (USER, ADMIN).
     */
    public static boolean esRolSoportado(String rol) {
        // GUÍA: pertenencia a un conjunto cerrado de roles del sistema.
        // 1. Si rol es null -> false.
        // 2. Define el catálogo de roles válidos y comprueba la pertenencia.
        // PISTA: return Set.of("USER", "ADMIN").contains(rol);
        // OJO: el test pasa "USER" (sin prefijo ROLE_) y espera true. Decide UN
        // formato: aquí se trabaja con el rol "pelado" (ver teoría 18.2).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esRolSoportado");
    }

    /**
     * RETO EXTRA 03: Genera la linea de configuracion para usuario en memoria.
     */
    public static String crearEntradaUsuarioMemoria(String u, String encPwd, String rol) {
        // GUÍA: formatea una entrada estilo properties de usuario en memoria.
        // 1. Devuelve una cadena que combine usuario, password codificada y rol.
        // PISTA: return String.format("%s=%s,%s", u, encPwd, rol);
        //        (recuerda el clásico user.password / user.roles de Spring).
        // OJO: el test solo exige que el resultado .contains("USER") (el rol que
        // se pasa); el formato exacto es libre mientras incluya el rol.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearEntradaUsuarioMemoria");
    }

    /**
     * RETO EXTRA 04: Comprueba que la contraseña en memoria empiece por el prefijo del codificador.
     */
    public static boolean esPasswordEncriptadaValida(String pwd) {
        // GUÍA: teoría 18.3 (DelegatingPasswordEncoder marca el algoritmo con {id}).
        // 1. Si pwd es null -> false.
        // 2. Una password "almacenable" lleva el prefijo del codificador entre
        //    llaves: {bcrypt}, {noop}, {argon2}...
        // PISTA: return pwd != null && pwd.startsWith("{") && pwd.contains("}");
        // OJO: el test pasa "{noop}123" y espera true. {noop} = sin cifrar (solo
        // para demos); en producción jamás uses {noop}.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esPasswordEncriptadaValida");
    }

    /**
     * RETO EXTRA 05: Determina si coincide la credencial de forma segura frente a ataques de temporizacion.
     */
    public static boolean esCredencialCorrecta(String input, String expected) {
        // GUÍA: comparación resistente a timing attacks (teoría 18.3).
        // 1. Si input o expected son null -> false.
        // 2. Compara en tiempo CONSTANTE, sin cortocircuitar en el primer byte
        //    distinto (un equals normal revela por el tiempo cuántos chars coinciden).
        // PISTA: java.security.MessageDigest.isEqual(
        //            input.getBytes(StandardCharsets.UTF_8),
        //            expected.getBytes(StandardCharsets.UTF_8));
        // OJO: el test pasa ("1","1") y espera true; un equals normal pasaría el
        // test, pero el objetivo pedagógico es usar isEqual (constant-time).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esCredencialCorrecta");
    }

    /**
     * RETO EXTRA 06: Verifica si la coleccion contiene el rol ADMIN.
     */
    public static boolean tieneRolAdmin(java.util.List<String> roles) {
        // GUÍA: pertenencia, contemplando ambos formatos de rol.
        // 1. Si roles es null -> false.
        // 2. true si la lista contiene "ADMIN" o "ROLE_ADMIN".
        // PISTA: return roles != null
        //        && (roles.contains("ADMIN") || roles.contains("ROLE_ADMIN"));
        // OJO: el test pasa List.of("ADMIN") y espera true. Aceptar ambos formatos
        // te ahorra bugs cuando una capa usa prefijo y otra no (teoría 18.2).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tieneRolAdmin");
    }

    /**
     * RETO EXTRA 07: Determina si el error apunta a fallo de contrasena o usuario no hallado.
     */
    public static boolean esExcepcionDeCredenciales(Throwable t) {
        // GUÍA: detectar el fallo de credenciales (tipo + mensaje).
        // 1. Si t es null -> false.
        // 2. En Spring sería BadCredentialsException; aquí lo modelamos con una
        //    excepción cuyo mensaje delata el motivo.
        // PISTA: t != null && t.getMessage() != null
        //        && t.getMessage().toLowerCase().contains("credential");
        // OJO: el test pasa new SecurityException("Bad credentials") y espera true.
        // Recuerda que NO debes distinguir "usuario no existe" de "clave mala"
        // de cara al cliente (user enumeration, teoría 18.4).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esExcepcionDeCredenciales");
    }

    /**
     * RETO EXTRA 08: Elimina el prefijo del encoder (ej. {bcrypt}).
     */
    public static String limpiarPrefijoCodificador(String raw) {
        // GUÍA: inversa del reto 04 — quita el prefijo {id} inicial.
        // 1. Si raw es null -> null.
        // 2. Elimina el bloque {...} del principio si lo hay; si no, devuélvelo igual.
        // PISTA: return raw == null ? null : raw.replaceFirst("^\\{[^}]+\\}", "");
        // OJO: el test pasa "{bcrypt}123" y espera EXACTAMENTE "123". Ancla la regex
        // al inicio (^) para no tocar llaves que aparezcan dentro del hash.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para limpiarPrefijoCodificador");
    }

    /**
     * RETO EXTRA 09: Genera un nombre a partir del correo.
     */
    public static String generarUsernamePorDefecto(String email) {
        // GUÍA: la parte local del correo (antes de la @).
        // 1. Si email es null/blank o no tiene "@" -> decide un default (o lánzalo).
        // 2. Devuelve la subcadena anterior al primer "@".
        // PISTA: return email.split("@", 2)[0];   // limita a 2 trozos
        // OJO: el test pasa "ada@b.com" y espera EXACTAMENTE "ada". El split con
        // límite 2 evita problemas si hubiera más de una @ (no debería en un email).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para generarUsernamePorDefecto");
    }

    /**
     * RETO EXTRA 10: Determina si la fecha de expiracion de cuenta ya paso.
     */
    public static boolean esCuentaExpirada(java.time.LocalDate exp) {
        // GUÍA: comparación de fechas con java.time (teoría 1.10).
        // 1. Si exp es null -> decide (false = "sin caducidad" es razonable).
        // 2. Está expirada si la fecha es anterior a hoy.
        // PISTA: return exp != null && exp.isBefore(java.time.LocalDate.now());
        // OJO: el test pasa LocalDate.now().minusDays(1) y espera true. Usa
        // isBefore, no compareTo manual; LocalDate es inmutable (no muta now()).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esCuentaExpirada");
    }

}

/**
 * Usuario en memoria: nombre, hash de contraseña, roles y flag de activación.
 *
 * @param username nombre único
 * @param passwordHash hash BCrypt de la contraseña (nunca en claro)
 * @param roles roles asignados (p.ej. ROLE_ADMIN)
 * @param enabled true si la cuenta está activa
 */
record Usuario156(String username, String passwordHash, Set<String> roles, boolean enabled) {
}