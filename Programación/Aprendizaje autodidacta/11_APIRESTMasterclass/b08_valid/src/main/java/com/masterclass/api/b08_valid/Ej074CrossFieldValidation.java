package com.masterclass.api.b08_valid;

/**
 * Ejercicio 074 · Validación entre campos.
 *
 * <p>Teoría: {@code teoria/08_Bean_Validation.md} (sección 8.2).
 *
 * <p>Algunas reglas relacionan varios campos (fechaFin &gt;= fechaInicio,
 * password == confirmación). Aquí se valida programáticamente.
 */
public final class Ej074CrossFieldValidation {

    public record RangoFechas(java.time.LocalDate inicio, java.time.LocalDate fin) {
    }

    public record Passwords(String password, String confirmacion) {
    }

    private Ej074CrossFieldValidation() {
    }

    /**
     * @param r rango de fechas
     * @return true si el rango es coherente (fin no anterior a inicio)
     * @throws IllegalArgumentException si inicio o fin son null
     */
    public static boolean rangoValido(RangoFechas r) {
        // TODO 1: si r es null -> IllegalArgumentException.
        // TODO 2: si r.inicio() o r.fin() son null -> IllegalArgumentException.
        // TODO 3: el rango es válido si fin NO es anterior a inicio (fin >= inicio).
        // TODO 4: usa isBefore para comprobar la relación entre los dos campos.
        // TODO 5: devuelve el booleano resultante.
        return false;
    }

    /**
     * @param p par password/confirmación
     * @return true si coinciden y cumplen longitud mínima 8
     */
    public static boolean passwordsCoinciden(Passwords p) {
        // TODO 6: si p es null -> IllegalArgumentException.
        // TODO 7: si password es null -> false (no válido).
        // TODO 8: comprueba longitud mínima 8 de password.
        // TODO 9: comprueba que password.equals(confirmacion) (regla entre 2 campos).
        // TODO 10: devuelve true solo si AMBAS condiciones se cumplen.
        return false;
    }

    public static void main(String[] args) {
        System.out.println(passwordsCoinciden(new Passwords("12345678", "12345678")));
    }

    /**
     * RETO EXTRA 1: Calcular la duración en días de un rango de fechas.
     *
     * @param r rango de fechas
     * @return número de días transcurridos
     */
    public static int calcularDuracionDias(RangoFechas r) {
        // GUÍA: java.time para diferencias de calendario (teoría 1.10).
        // 1. Valida nulls (lanza IllegalArgumentException si r/inicio/fin son null).
        // 2. Días entre dos LocalDate: ChronoUnit.DAYS.between(inicio, fin) (cast a int).
        // PISTA: return (int) java.time.temporal.ChronoUnit.DAYS.between(r.inicio(), r.fin());
        // OJO: el test (hoy → hoy+5) espera 0 → placeholder; la espec real da 5.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para calcularDuracionDias");
    }

    /**
     * RETO EXTRA 2: Comprobar si el inicio y fin corresponden al mismo día.
     *
     * @param r rango de fechas
     * @return true si coinciden exactamente
     */
    public static boolean esMismoDia(RangoFechas r) {
        // GUÍA: cross-field de igualdad (teoría 8.6). LocalDate define equals/isEqual.
        // 1. Protege nulls. Compara inicio con fin: r.inicio().isEqual(r.fin()).
        // PISTA: return r.inicio().isEqual(r.fin());
        // OJO: el test (hoy, hoy) espera assertFalse → placeholder; la espec real da
        //      true (mismo día). Usa isEqual/equals, no ==.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esMismoDia");
    }

    /**
     * RETO EXTRA 3: Comprobar si todo el rango de fechas está dentro del año actual.
     *
     * @param r rango de fechas
     * @return true si ambas fechas son del año en curso
     */
    public static boolean rangoEnAñoActual(RangoFechas r) {
        // GUÍA: compara el año de cada fecha con el año actual (teoría 1.10).
        // 1. int año = java.time.Year.now().getValue() (o LocalDate.now().getYear()).
        // 2. AMBAS fechas deben tener ese año: r.inicio().getYear()==año && fin igual.
        // PISTA: int y = java.time.LocalDate.now().getYear();
        //        return r.inicio().getYear() == y && r.fin().getYear() == y;
        // OJO: el test (hoy, hoy) espera assertFalse → placeholder; la espec real da
        //      true (ambas son de este año).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para rangoEnAñoActual");
    }

    /**
     * RETO EXTRA 4: Determinar si una contraseña tiene longitud de 12 o más caracteres.
     *
     * @param password contraseña
     * @return true si cumple con la longitud extra
     */
    public static boolean esPasswordLarga(String password) {
        // GUÍA: una línea — longitud >= 12. null → false.
        // PISTA: return password != null && password.length() >= 12;
        // OJO: el test manda "SuperLongPassword123" (20 chars) y espera assertFalse →
        //      placeholder; la espec real da true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esPasswordLarga");
    }

    /**
     * RETO EXTRA 5: Comprobar si la contraseña tiene seguridad básica (letras y dígitos).
     *
     * @param password contraseña
     * @return true si contiene al menos una letra y un dígito
     */
    public static boolean esPasswordSegura(String password) {
        // GUÍA: al menos una letra Y al menos un dígito (patrón de 1.7/streams).
        // 1. null → false.
        // 2. password.chars().anyMatch(Character::isLetter) && ...anyMatch(isDigit).
        // PISTA: return password != null
        //          && password.chars().anyMatch(Character::isLetter)
        //          && password.chars().anyMatch(Character::isDigit);
        // OJO: el test manda "1234567a" (tiene letra y dígito) y espera assertFalse →
        //      placeholder; la espec real da true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esPasswordSegura");
    }

    /**
     * RETO EXTRA 6: Comprobar si una cadena contiene espacios en blanco.
     *
     * @param s cadena
     * @return true si contiene espacios
     */
    public static boolean contieneEspacios(String s) {
        // GUÍA: una línea — ¿hay algún espacio? null → false.
        // PISTA: return s != null && s.contains(" ");
        //    (más estricto, cualquier whitespace: s.chars().anyMatch(Character::isWhitespace)).
        // OJO: el test manda "con espacios" y espera assertFalse → placeholder; la
        //      espec real da true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contieneEspacios");
    }

    /**
     * RETO EXTRA 7: Encriptar de forma simulada la contraseña devolviendo asteriscos.
     *
     * @param password contraseña original
     * @return contraseña oculta con asteriscos
     */
    public static String encriptarPasswordDummy(String password) {
        // GUÍA: devuelve tantos '*' como caracteres tenga la contraseña.
        // 1. null → "".
        // 2. "*".repeat(password.length()).
        // PISTA: return password == null ? "" : "*".repeat(password.length());
        // OJO: el test manda "secret" (6) y espera "" → placeholder; la espec real
        //      da "******". CULTURA: esto es enmascarado para logs, NO cifrado real
        //      (eso es BCrypt en b18).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para encriptarPasswordDummy");
    }

    /**
     * RETO EXTRA 8: Ajustar campos nulos en un rango usando la fecha actual por defecto.
     *
     * @param r rango de fechas original
     * @return nuevo rango de fechas sin campos nulos
     */
    public static RangoFechas ajustarFechasNull(RangoFechas r) {
        // GUÍA: "wither" sobre un record (teoría 1.1) rellenando nulls con hoy.
        // 1. Si r es null → decide (null o new con dos hoy; el test no lo distingue).
        // 2. inicio = r.inicio() != null ? r.inicio() : LocalDate.now(); igual para fin.
        // 3. Devuelve new RangoFechas(inicio, fin).
        // PISTA: var hoy = java.time.LocalDate.now();
        //        return new RangoFechas(r.inicio() == null ? hoy : r.inicio(),
        //                               r.fin() == null ? hoy : r.fin());
        // OJO: el test (null,null) espera assertNull → placeholder; la espec real
        //      devuelve un rango con ambas fechas = hoy.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ajustarFechasNull");
    }

    /**
     * RETO EXTRA 9: Comprobar si las fechas están estrictamente invertidas (fin < inicio).
     *
     * @param r rango de fechas
     * @return true si fin es anterior a inicio
     */
    public static boolean esRangoInvertido(RangoFechas r) {
        // GUÍA: es el negativo de rangoValido (teoría 8.6). fin estrictamente < inicio.
        // 1. fin.isBefore(inicio) — aquí SÍ es estricto (mismo día NO está invertido).
        // PISTA: return r.fin().isBefore(r.inicio());
        // OJO: el test (inicio=hoy+1, fin=hoy → invertido) espera assertFalse →
        //      placeholder; la espec real da true. Fíjate en el contraste con
        //      rangoValido, que usa !isBefore para incluir el "igual".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esRangoInvertido");
    }

    /**
     * RETO EXTRA 10: Validar que el par de contraseñas coincidan y además sean seguras.
     *
     * @param p par de contraseñas
     * @return true si coinciden y ambas cumplen el criterio de seguridad básica
     */
    public static boolean esPasswordsValidasYSeguras(Passwords p) {
        // GUÍA: combina dos reglas reutilizando métodos previos (8.6).
        // 1. Protege p null.
        // 2. Coinciden: p.password().equals(p.confirmacion()) (equals, no ==).
        // 3. Y son seguras: esPasswordSegura(p.password()) (reto 5).
        // PISTA: return p != null && p.password() != null
        //          && p.password().equals(p.confirmacion())
        //          && esPasswordSegura(p.password());
        // OJO: el test ("secret123","secret123") coincide y es segura, y espera
        //      assertFalse → placeholder; la espec real da true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esPasswordsValidasYSeguras");
    }

}
