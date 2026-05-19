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
}
