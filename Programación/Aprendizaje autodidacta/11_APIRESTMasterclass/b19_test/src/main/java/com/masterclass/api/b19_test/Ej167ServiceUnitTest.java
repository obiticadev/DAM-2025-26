package com.masterclass.api.b19_test;

/**
 * Ejercicio 167 · Test unitario de servicio (orquestación con colaboradores).
 *
 * <p>Teoría: {@code teoria/19_Testing_APIs.md} (sección 19.4).
 *
 * <p>Un servicio de transferencia que orquesta un repositorio de saldos
 * (stub). El test verifica la lógica de negocio sin BD ni Spring.
 */
public final class Ej167ServiceUnitTest {

    private Ej167ServiceUnitTest() {
    }

    /**
     * Transfiere un importe entre dos cuentas usando un repositorio de saldos.
     *
     * @param saldos repositorio de saldos (stub en test) no null
     * @param origen  id cuenta origen
     * @param destino id cuenta destino (distinto de origen)
     * @param importe importe &gt; 0
     * @return nuevo saldo de la cuenta origen tras la transferencia
     * @throws IllegalArgumentException entradas inválidas
     * @throws IllegalStateException si el saldo de origen es insuficiente
     */
    public static double transferir(SaldoRepo167 saldos, String origen, String destino, double importe) {
        // TODO 1: si saldos es null -> IllegalArgumentException.
        // TODO 2: si origen o destino son null/blank -> IllegalArgumentException.
        // TODO 3: si origen.equals(destino) -> IllegalArgumentException (no a sí mismo).
        // TODO 4: si importe <= 0 -> IllegalArgumentException.
        // TODO 5: lee saldoOrigen = saldos.saldoDe(origen).
        // TODO 6: si saldoOrigen < importe -> IllegalStateException (fondos insuficientes).
        // TODO 7: calcula nuevoSaldoOrigen = saldoOrigen - importe.
        // TODO 8: la regla de negocio no escribe BD aquí: el repo es un doble.
        // TODO 9: no debe mutar el repo de entrada (lógica pura verificable).
        // TODO 10: devuelve nuevoSaldoOrigen.
        return 0.0;
    }

    public static void main(String[] args) {
        SaldoRepo167 repo = c -> "A".equals(c) ? 100.0 : 0.0;
        System.out.println(transferir(repo, "A", "B", 30.0));
    }

        /**
     * RETO EXTRA 01: Formatea el saldo de la cuenta.
     */
    public static String formatearSaldo(SaldoRepo167 repo, String c) {
        // TODO extra: RETO EXTRA 01: Formatea el saldo de la cuenta.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearSaldo");
    }

    /**
     * RETO EXTRA 02: Comprueba si el saldo califica como Premium.
     */
    public static boolean esCuentaPremium(SaldoRepo167 repo, String c) {
        // TODO extra: RETO EXTRA 02: Comprueba si el saldo califica como Premium.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esCuentaPremium");
    }

    /**
     * RETO EXTRA 03: Comprueba si el saldo es positivo.
     */
    public static boolean esSaldoPositivo(SaldoRepo167 repo, String c) {
        // TODO extra: RETO EXTRA 03: Comprueba si el saldo es positivo.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esSaldoPositivo");
    }

    /**
     * RETO EXTRA 04: Comprueba si una transferencia es viable.
     */
    public static boolean transferenciaPosible(SaldoRepo167 repo, String o, double imp) {
        // TODO extra: RETO EXTRA 04: Comprueba si una transferencia es viable.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para transferenciaPosible");
    }

    /**
     * RETO EXTRA 05: Calcula el interes anual estimado.
     */
    public static double calcularInteresAnual(SaldoRepo167 repo, String c, double tasa) {
        // TODO extra: RETO EXTRA 05: Calcula el interes anual estimado.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para calcularInteresAnual");
    }

    /**
     * RETO EXTRA 06: Devuelve el saldo duplicado.
     */
    public static double saldoDuplicado(SaldoRepo167 repo, String c) {
        // TODO extra: RETO EXTRA 06: Devuelve el saldo duplicado.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para saldoDuplicado");
    }

    /**
     * RETO EXTRA 07: Calcula la diferencia de saldo entre dos cuentas.
     */
    public static double obtenerDiferenciaSaldos(SaldoRepo167 repo, String c1, String c2) {
        // TODO extra: RETO EXTRA 07: Calcula la diferencia de saldo entre dos cuentas.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerDiferenciaSaldos");
    }

    /**
     * RETO EXTRA 08: Suma los saldos de dos cuentas.
     */
    public static double sumarSaldos(SaldoRepo167 repo, String c1, String c2) {
        // TODO extra: RETO EXTRA 08: Suma los saldos de dos cuentas.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para sumarSaldos");
    }

    /**
     * RETO EXTRA 09: Comprueba si el saldo no es negativo.
     */
    public static boolean saldoValido(SaldoRepo167 repo, String c) {
        // TODO extra: RETO EXTRA 09: Comprueba si el saldo no es negativo.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para saldoValido");
    }

    /**
     * RETO EXTRA 10: Retorna el saldo o lanza si es negativo.
     */
    public static double saldoOExcepcion(SaldoRepo167 repo, String c) {
        // TODO extra: RETO EXTRA 10: Retorna el saldo o lanza si es negativo.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para saldoOExcepcion");
    }

}

/** Colaborador del servicio: repositorio de saldos (doble de prueba). */
@FunctionalInterface
interface SaldoRepo167 {
    /** @param cuenta id @return saldo actual de la cuenta */
    double saldoDe(String cuenta);
}
