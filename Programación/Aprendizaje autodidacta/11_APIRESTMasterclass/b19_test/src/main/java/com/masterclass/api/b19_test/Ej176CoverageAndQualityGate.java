package com.masterclass.api.b19_test;

/**
 * Ejercicio 176 · Cobertura y quality gate (cálculo como función pura).
 *
 * <p>Teoría: {@code teoria/19_Testing_APIs.md} (sección 19.13).
 *
 * <p>JaCoCo mide líneas ejecutadas / total y un quality gate falla el
 * build si baja de un umbral. Modelamos ambos cálculos como funciones
 * puras, validables con JUnit sin agente de cobertura.
 */
public final class Ej176CoverageAndQualityGate {

    private Ej176CoverageAndQualityGate() {
    }

    /**
     * Calcula el porcentaje de cobertura de líneas.
     *
     * @param lineasCubiertas líneas ejecutadas por los tests (&gt;= 0)
     * @param lineasTotales   líneas instrumentadas (&gt; 0)
     * @return porcentaje [0,100] redondeado a 2 decimales
     * @throws IllegalArgumentException si cubiertas &lt; 0, totales &lt;= 0 o cubiertas &gt; totales
     */
    public static double porcentajeCobertura(int lineasCubiertas, int lineasTotales) {
        // TODO 1: si lineasTotales <= 0 -> IllegalArgumentException (no divides por 0).
        // TODO 2: si lineasCubiertas < 0 -> IllegalArgumentException.
        // TODO 3: si lineasCubiertas > lineasTotales -> IllegalArgumentException (incoherente).
        // TODO 4: calcula la fracción cubiertas/totales en punto flotante (cast a double).
        // TODO 5: multiplica por 100 para obtener porcentaje.
        // TODO 6: redondea a 2 decimales (Math.round(x*100)/100.0).
        // TODO 7: 0 cubiertas -> 0.0; todas cubiertas -> 100.0 (casos límite).
        // TODO 8: el resultado siempre cae en [0,100].
        // TODO 9: función determinista y sin efectos secundarios.
        // TODO 10: devuelve el porcentaje.
        return 0.0;
    }

    /**
     * Quality gate: decide si la cobertura alcanza el umbral mínimo.
     *
     * @param cobertura porcentaje actual [0,100]
     * @param umbral    mínimo exigido [0,100]
     * @return true si cobertura &gt;= umbral (build pasa); false si no (build falla)
     * @throws IllegalArgumentException si cobertura o umbral fuera de [0,100]
     */
    public static boolean pasaQualityGate(double cobertura, double umbral) {
        // TODO 1: si cobertura < 0 o > 100 -> IllegalArgumentException.
        // TODO 2: si umbral < 0 o > 100 -> IllegalArgumentException.
        // TODO 3: el gate pasa cuando cobertura >= umbral (>= no estricto).
        // TODO 4: cobertura exactamente igual al umbral -> pasa (caso límite).
        // TODO 5: cobertura por debajo del umbral -> no pasa (build rojo).
        // TODO 6: umbral 0 -> siempre pasa (gate desactivado de facto).
        // TODO 7: umbral 100 -> solo pasa con cobertura total.
        // TODO 8: no redondees aquí: compara los valores recibidos tal cual.
        // TODO 9: función pura, sin estado ni efectos.
        // TODO 10: devuelve el booleano de aprobación del gate.
        return false;
    }

    public static void main(String[] args) {
        double c = porcentajeCobertura(80, 100);
        System.out.println(c + " -> " + pasaQualityGate(c, 70.0));
    }

        /**
     * RETO EXTRA 01: Valida si es 100%.
     */
    public static boolean esCoberturaTotal(double cob) {
        // GUÍA: teoría 19.13 — una línea: return cob == 100.0;
        // El test (100.0) espera true. (Comparar double con == funciona aquí
        // porque 100.0 es exacto en binario; en cálculos derivados no fíes la
        // igualdad a == — usa un epsilon.)
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esCoberturaTotal");
    }

    /**
     * RETO EXTRA 02: Valida si es 0%.
     */
    public static boolean esCoberturaNula(double cob) {
        // GUÍA: teoría 19.13 — una línea: return cob == 0.0;
        // El test (0.0) espera true. Simétrico a esCoberturaTotal.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esCoberturaNula");
    }

    /**
     * RETO EXTRA 03: Umbral estándar (80%).
     */
    public static double umbralPorDefecto() {
        // GUÍA: teoría 19.13 — una línea: return 80.0;
        // El test espera 80.0. 80% es el umbral "razonable por defecto" que se
        // suele poner en una rule de JaCoCo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para umbralPorDefecto");
    }

    /**
     * RETO EXTRA 04: Umbral exigente (100%).
     */
    public static double umbralEstricto() {
        // GUÍA: teoría 19.13 — una línea: return 100.0;
        // El test espera 100.0. Un gate al 100% solo pasa con cobertura total
        // (raro y a menudo contraproducente: invita a tests-teatro sin valor).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para umbralEstricto");
    }

    /**
     * RETO EXTRA 05: Valida si esta en [0,100].
     */
    public static boolean esUmbralValido(double u) {
        // GUÍA: teoría 19.13 — una línea: return u >= 0 && u <= 100;
        // El test (50.0) espera true. Es la validación de rango que
        // pasaQualityGate aplica a cobertura y umbral antes de comparar.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esUmbralValido");
    }

    /**
     * RETO EXTRA 06: Calcula diferencia absoluta.
     */
    public static double diferenciaCobertura(double a, double b) {
        // GUÍA: teoría 19.13 — una línea: return Math.abs(a - b);
        // El test (90.0, 80.0) espera 10.0. "Diferencia" → valor absoluto, igual
        // que en obtenerDiferenciaSaldos (Ej167 reto 7).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para diferenciaCobertura");
    }

    /**
     * RETO EXTRA 07: Valida si es >= 80%.
     */
    public static boolean esCoberturaAceptable(double cob) {
        // GUÍA: teoría 19.13 — una línea: return cob >= 80.0;
        // El test (85.0) espera true. Es pasaQualityGate(cob, 80.0) hecho atajo;
        // podrías incluso devolver pasaQualityGate(cob, umbralPorDefecto()).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esCoberturaAceptable");
    }

    /**
     * RETO EXTRA 08: Valida si es < 50%.
     */
    public static boolean esCritica(double cob) {
        // GUÍA: teoría 19.13 — una línea: return cob < 50.0;
        // El test (45.0) espera true. OJO: aquí es < ESTRICTO (50 NO es crítica),
        // a diferencia del >= de esCoberturaAceptable.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esCritica");
    }

    /**
     * RETO EXTRA 09: Calculo rapido sin redondeo.
     */
    public static double calcularPorcentajeRapido(int c, int t) {
        // GUÍA: teoría 19.13 — porcentaje sin la validación ni el redondeo.
        // return (double) c / t * 100;
        // El test (5, 10) espera 50.0. ⚠ CUIDADO con la división entera: si
        // escribes c / t SIN el (double), 5/10 = 0 (int) y daría 0.0. El cast a
        // double ANTES de dividir es lo que evita ese clásico error.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para calcularPorcentajeRapido");
    }

    /**
     * RETO EXTRA 10: Formatea con simbolo de porcentaje.
     */
    public static String formatearCobertura(double cob) {
        // GUÍA: teoría 19.13 — una línea: return cob + "%";
        // El test (85.5) espera "85.5%". La concatenación double + String usa
        // Double.toString → "85.5", y le pegas el "%". No metas espacios ni
        // formatees decimales: el test compara la cadena exacta.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearCobertura");
    }

}
