package com.masterclass.api.b18_sec;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Ejercicio 157 · PasswordEncoder con BCrypt (lógica pura testeable).
 *
 * <p>Teoría: {@code teoria/18_Seguridad_JWT.md} (sección 18.3).
 *
 * <p>Usa {@link BCryptPasswordEncoder} (en classpath, no necesita Spring).
 * Nunca se guarda la contraseña en claro: solo su hash con salt aleatorio.
 */
public final class Ej157PasswordEncoder {

    private Ej157PasswordEncoder() {
    }

    /**
     * Genera el hash BCrypt de una contraseña en claro.
     *
     * @param raw contraseña en claro (no null, longitud &gt;= 8)
     * @return hash BCrypt (empieza por {@code $2a$} / {@code $2b$})
     * @throws IllegalArgumentException si raw es null o demasiado corta
     */
    public static String hash(String raw) {
        // TODO 1: si raw es null -> IllegalArgumentException.
        // TODO 2: si raw.length() < 8 -> IllegalArgumentException (política mínima).
        // TODO 3: crea un BCryptPasswordEncoder (strength por defecto 10 está bien).
        // TODO 4: invoca encode(raw) para obtener el hash con salt embebido.
        // TODO 5: NO almacenes ni loguees la contraseña en claro.
        // TODO 6: el mismo input produce hashes DISTINTOS (salt aleatorio): es normal.
        // TODO 7: no truncar el hash; es de longitud fija (~60 chars).
        // TODO 8: documenta que BCrypt es lento a propósito (resistencia a fuerza bruta).
        // TODO 9: devuelve el hash resultante.
        // TODO 10: el verificador (matches) recuperará el salt del propio hash.
        return "";
    }

    /**
     * Verifica que una contraseña en claro corresponde a un hash BCrypt.
     *
     * @param raw  contraseña en claro a comprobar (no null)
     * @param hash hash BCrypt previamente generado (no null/blank)
     * @return true si la contraseña coincide
     * @throws IllegalArgumentException si raw o hash son inválidos
     */
    public static boolean verifica(String raw, String hash) {
        // TODO 1: si raw es null -> IllegalArgumentException.
        // TODO 2: si hash es null o blank -> IllegalArgumentException.
        // TODO 3: crea un BCryptPasswordEncoder (cualquier instancia sirve para matches).
        // TODO 4: invoca matches(raw, hash); el salt sale del propio hash.
        // TODO 5: NO compares strings con equals (eso fallaría por el salt).
        // TODO 6: la comparación interna es resistente a timing attacks: no la reimplementes.
        // TODO 7: si el hash tiene formato inválido, matches devuelve false sin lanzar.
        // TODO 8: no loguees la contraseña ni el hash completo.
        // TODO 9: devuelve el booleano de matches.
        // TODO 10: documenta que aquí decides login OK/KO.
        return false;
    }

    public static void main(String[] args) {
        String h = hash("supersecreta1");
        System.out.println(verifica("supersecreta1", h));
    }
}
