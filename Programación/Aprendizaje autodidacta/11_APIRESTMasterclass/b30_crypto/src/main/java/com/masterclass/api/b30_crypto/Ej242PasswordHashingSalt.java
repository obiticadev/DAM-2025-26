package com.masterclass.api.b30_crypto;

/**
 * Ejercicio 242 · Guardar contraseñas bien: salt + hashing lento (PBKDF2).
 *
 * <p>Un SHA-256 desnudo NO vale para contraseñas: es rapidísimo (miles de millones por
 * segundo en GPU) y dos usuarios con la misma contraseña tendrían el mismo hash. La solución:
 * <ol>
 *   <li><b>Salt</b> aleatorio por usuario (rompe las rainbow tables y separa hashes iguales).</li>
 *   <li><b>Hashing lento</b> con muchas iteraciones (PBKDF2/bcrypt/scrypt/argon2) para que la
 *       fuerza bruta sea inviable.</li>
 * </ol>
 * Aquí usas <b>PBKDF2WithHmacSHA256</b>. Es el primo de bajo nivel del {@code BCryptPasswordEncoder}
 * que viste en {@code b18·Ej157}: mismos principios (salt + coste), distinta receta.
 *
 * <p>Formato de almacenamiento de este ejercicio: {@code saltHex:hashHex} (el salt se guarda
 * junto al hash; no es secreto, su función es ser único).
 *
 * <p>Teoría: {@code teoria/30_Criptografia_Seguridad.md} (sección 30.2).
 */
public final class Ej242PasswordHashingSalt {

    private Ej242PasswordHashingSalt() {
    }

    /** Iteraciones de PBKDF2 usadas en el bloque (en producción, decenas/cientos de miles). */
    public static final int ITERACIONES = 100_000;

    /**
     * Deriva un hash de la contraseña con un salt ALEATORIO y devuelve "saltHex:hashHex".
     *
     * @param password contraseña en claro
     * @return cadena "saltHex:hashHex", o {@code null} si no se ha implementado
     */
    public static String hashConSalt(String password) {
        // TODO 1: genera un salt aleatorio de 16 bytes con SecureRandom: new SecureRandom().nextBytes(salt).
        // TODO 2: construye un PBEKeySpec(password.toCharArray(), salt, ITERACIONES, 256) (256 bits de salida).
        // TODO 3: obtén SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256") y llama a generateSecret(spec).
        // TODO 4: extrae los bytes derivados con .getEncoded().
        // TODO 5: codifica salt y hash a hex (HexFormat.of()) y devuélvelos como "saltHex:hashHex".
        // TODO 6: maneja las excepciones (NoSuchAlgorithmException/InvalidKeySpecException) como RuntimeException.
        return null;
    }

    /**
     * Verifica una contraseña contra un "saltHex:hashHex" previamente almacenado.
     *
     * @param password    contraseña a comprobar
     * @param almacenado  cadena "saltHex:hashHex" guardada
     * @return true si la contraseña deriva el mismo hash usando el salt almacenado
     */
    public static boolean passwordCoincide(String password, String almacenado) {
        // TODO 7: separa 'almacenado' en salt y hash con split(":", 2) y decodifica el hex (HexFormat.parseHex).
        // TODO 8: vuelve a derivar el hash de 'password' usando EXACTAMENTE ese salt y las mismas ITERACIONES.
        // TODO 9: compara el hash derivado con el almacenado usando MessageDigest.isEqual (tiempo constante).
        // TODO 10: si algún parámetro es null o el formato es inválido, devuelve false (no lances excepción).
        return false;
    }

    public static void main(String[] args) {
        String h = hashConSalt("secreto");
        System.out.println("hashConSalt(\"secreto\") = " + h);
        System.out.println("passwordCoincide(\"secreto\") = " + passwordCoincide("secreto", h));
    }

    /**
     * Reto Extra 1: la misma contraseña produce hashes DISTINTOS por el salt aleatorio.
     * @return true si dos llamadas a hashConSalt("misma") devuelven cadenas distintas
     */
    public static boolean mismaPasswordDistintoHash() {
        // GUÍA: return !hashConSalt("misma").equals(hashConSalt("misma"));
        // CULTURA: este es EL motivo del salt: sin él, dos usuarios con la misma contraseña tendrían
        // el mismo hash y se vería en una filtración. Enlaza con BCrypt (b18·Ej157), que hace lo mismo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mismaPasswordDistintoHash");
    }

    /**
     * Reto Extra 2: el salt aleatorio mide 16 bytes (128 bits).
     * @return longitud del salt generado con SecureRandom (debe ser 16)
     */
    public static int saltAleatorioDe16Bytes() {
        // GUÍA: byte[] salt = new byte[16]; new SecureRandom().nextBytes(salt); return salt.length;
        // OJO: usa SecureRandom (CSPRNG), NUNCA java.util.Random para material criptográfico (ver Ej246).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para saltAleatorioDe16Bytes");
    }

    /**
     * Reto Extra 3: PBKDF2 con salida de 256 bits produce 32 bytes (64 hex).
     * @return número de bytes del hash derivado (debe ser 32)
     */
    public static int pbkdf2DevuelveTreintaYDosBytes() {
        // GUÍA: en el PBEKeySpec el último parámetro (keyLength) es en BITS: 256 → 32 bytes de salida.
        // Deriva con un salt cualquiera y devuelve getEncoded().length.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pbkdf2DevuelveTreintaYDosBytes");
    }

    /**
     * Reto Extra 4: a más iteraciones, más coste — y resultado distinto para el mismo salt.
     * @return true si derivar con 1000 vs 2000 iteraciones (mismo password y salt) da hashes distintos
     */
    public static boolean masIteracionesCambiaElHash() {
        // GUÍA: deriva pbkdf2(password, saltFijo, 1000) y pbkdf2(password, saltFijo, 2000) y compáralos:
        // son distintos. El número de iteraciones forma parte de la receta.
        // CULTURA: subir iteraciones es cómo el hashing "envejece bien": se aumenta con el hardware.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para masIteracionesCambiaElHash");
    }

    /**
     * Reto Extra 5: una contraseña correcta se verifica como válida.
     * @return true si passwordCoincide acepta la contraseña correcta
     */
    public static boolean aceptaPasswordCorrecta() {
        // GUÍA: String h = hashConSalt("clave-buena"); return passwordCoincide("clave-buena", h);
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para aceptaPasswordCorrecta");
    }

    /**
     * Reto Extra 6: una contraseña incorrecta se rechaza.
     * @return true si passwordCoincide RECHAZA correctamente una contraseña equivocada
     */
    public static boolean rechazaPasswordIncorrecta() {
        // GUÍA: String h = hashConSalt("clave-buena"); return !passwordCoincide("clave-mala", h);
        // OJO: el método devuelve false para la mala; este reto devuelve true cuando ese rechazo ocurre.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para rechazaPasswordIncorrecta");
    }

    /**
     * Reto Extra 7: con salts distintos, la misma contraseña da hashes distintos (control explícito).
     * @return true si pbkdf2(password, salt1) != pbkdf2(password, salt2) con salts diferentes
     */
    public static boolean saltDistintoHashDistinto() {
        // GUÍA: deriva la misma "clave" con dos salts fijos distintos (p.ej. {1..16} y {17..32}) y
        // comprueba que los hashes difieren. Demuestra el papel del salt de forma determinista.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para saltDistintoHashDistinto");
    }

    /**
     * Reto Extra 8: PBKDF2 es determinista con el MISMO password, salt e iteraciones.
     * @return true si dos derivaciones con idénticos parámetros (salt fijo) dan el mismo hash
     */
    public static boolean pbkdf2DeterministaConMismosParametros() {
        // GUÍA: deriva dos veces pbkdf2("clave", saltFijo, ITERACIONES) y compara con MessageDigest.isEqual.
        // CONTRASTE con el reto 1: allí cambia el salt (aleatorio) → cambia; aquí el salt es fijo → igual.
        // Por eso passwordCoincide puede re-derivar y comparar.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pbkdf2DeterministaConMismosParametros");
    }

    /**
     * Reto Extra 9: el formato almacenado "saltHex:hashHex" se parsea en dos partes.
     * @return número de partes al separar el almacenado por ":" (debe ser 2)
     */
    public static int formatoAlmacenadoTieneDosPartes() {
        // GUÍA: String h = hashConSalt("x"); return h.split(":", 2).length;  // salt y hash
        // CULTURA: guardar salt e iteraciones junto al hash (no son secretos) es lo que hace bcrypt
        // con su propio formato $2a$coste$saltyhash. El verificador necesita el salt para re-derivar.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatoAlmacenadoTieneDosPartes");
    }

    /**
     * Reto Extra 10: el salt no es secreto pero sí debe ser único; se puede codificar en Base64.
     * @return true si un salt codificado a Base64 y decodificado vuelve a los bytes originales
     */
    public static boolean saltRoundTripBase64() {
        // GUÍA: byte[] salt = ...; String s = Base64.getEncoder().encodeToString(salt);
        //   byte[] vuelta = Base64.getDecoder().decode(s); return Arrays.equals(salt, vuelta);
        // CULTURA: hex y Base64 son solo formas de TEXTO para bytes; no aportan seguridad, solo
        // portabilidad (guardar en BD/JSON). Enlaza con b02 (JSON) y b26 (encodings).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para saltRoundTripBase64");
    }
}
