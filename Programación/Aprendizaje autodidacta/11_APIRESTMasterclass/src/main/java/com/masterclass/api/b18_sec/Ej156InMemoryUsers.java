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
