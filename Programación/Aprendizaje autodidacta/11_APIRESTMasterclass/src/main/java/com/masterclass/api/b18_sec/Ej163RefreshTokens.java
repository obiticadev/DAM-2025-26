package com.masterclass.api.b18_sec;

import java.util.Map;
import java.util.Optional;

/**
 * Ejercicio 163 · Refresh tokens (rotación, lógica pura).
 *
 * <p>Teoría: {@code teoria/18_Seguridad_JWT.md} (sección 18.9).
 *
 * <p>El access token es corto; el refresh token es largo y se canjea por uno
 * nuevo. Modelamos el almacén de refresh tokens y la rotación sin Spring.
 */
public final class Ej163RefreshTokens {

    private Ej163RefreshTokens() {
    }

    /**
     * Verifica que un refresh token es válido y no ha sido revocado/caducado.
     *
     * @param almacen      mapa refreshToken -&gt; {@link RefreshInfo163} (no null)
     * @param refreshToken token presentado por el cliente (no null/blank)
     * @param ahoraMillis  instante actual epoch millis
     * @return Optional con la info si es válido (existe, no revocado, no caducado)
     * @throws IllegalArgumentException si argumentos inválidos
     */
    public static Optional<RefreshInfo163> verificar(Map<String, RefreshInfo163> almacen,
                                                     String refreshToken, long ahoraMillis) {
        // TODO 1: si almacen es null -> IllegalArgumentException.
        // TODO 2: si refreshToken es null o blank -> IllegalArgumentException.
        // TODO 3: busca la info en el almacén por el token.
        // TODO 4: si no existe -> Optional.empty() (token desconocido o ya rotado).
        // TODO 5: si info.revoked == true -> Optional.empty().
        // TODO 6: si info.expiraMillis <= ahoraMillis -> Optional.empty() (caducado).
        // TODO 7: NO loguees el token completo.
        // TODO 8: documenta que detectar reuso de un token rotado = posible robo.
        // TODO 9: envuelve la info válida en Optional.
        // TODO 10: devuelve el Optional.
        return Optional.empty();
    }

    /**
     * Rota el refresh token: revoca el antiguo y registra el nuevo.
     *
     * @param almacen        almacén mutable (no null)
     * @param tokenAntiguo   token consumido (debe existir)
     * @param tokenNuevo     token recién emitido (no null/blank)
     * @param subject        usuario dueño
     * @param expiraMillis   caducidad del nuevo token (epoch millis)
     * @return el tokenNuevo registrado
     * @throws IllegalArgumentException si argumentos inválidos
     */
    public static String rotar(Map<String, RefreshInfo163> almacen, String tokenAntiguo,
                               String tokenNuevo, String subject, long expiraMillis) {
        // TODO 1: si almacen es null -> IllegalArgumentException.
        // TODO 2: si tokenAntiguo es null o blank -> IllegalArgumentException.
        // TODO 3: si tokenNuevo es null o blank -> IllegalArgumentException.
        // TODO 4: marca el tokenAntiguo como revoked en el almacén (si existe).
        // TODO 5: rotación = single-use: el antiguo nunca vuelve a valer.
        // TODO 6: crea un RefreshInfo163 para el nuevo (subject, expiraMillis, revoked=false).
        // TODO 7: registra el tokenNuevo en el almacén.
        // TODO 8: si el antiguo no existía, considera no emitir (posible ataque) o documentar.
        // TODO 9: documenta el patrón "refresh token rotation" de OWASP.
        // TODO 10: devuelve el tokenNuevo.
        return "";
    }

    public static void main(String[] args) {
        System.out.println("refresh token rotation playground");
    }
}

/**
 * Info de un refresh token almacenado.
 *
 * @param subject usuario dueño
 * @param expiraMillis caducidad epoch millis
 * @param revoked true si fue revocado/rotado
 */
record RefreshInfo163(String subject, long expiraMillis, boolean revoked) {
}
