package com.masterclass.api.b47_pruebas;

import java.util.List;
import java.util.Map;

/**
 * Ejercicio 358 · Regresión: línea base, comparar y detectar desviación (golden master).
 *
 * <p>Teoría: {@code teoria/47_Estrategia_Pruebas.md} (sección 1.2).
 */
public final class Ej358RegressionBaseline {

    private Ej358RegressionBaseline() {
    }

    // ---- Tipos de datos auxiliares -----------------------------------------------

    public record Regresion(String metrica, Object valorBaseline, Object valorActual, String severidad) {
    }

    // ---- Métodos core -------------------------------------------------------------

    /**
     * Compara el mapa {@code actual} con el {@code baseline} dentro de una tolerancia relativa.
     *
     * <p>Para valores numéricos: regresión si {@code |actual - base| / |base| > tolerancia}
     * (severidad "ALTO" si la desviación > tolerancia*2, "BAJO" si > tolerancia).
     * Para valores de texto: regresión si son diferentes (severidad "ALTO").
     * Si una clave existe en {@code actual} pero no en {@code baseline}: severidad "NUEVO".
     * Si una clave existe en {@code baseline} pero no en {@code actual}: severidad "ELIMINADO".
     *
     * @param actual    mapa de métricas actuales; no puede ser null
     * @param baseline  mapa de métricas de referencia; no puede ser null
     * @param tolerancia fracción permitida de desviación (p.ej. 0.05 = 5 %)
     * @return lista de regresiones detectadas, ordenada por severidad (ALTO primero)
     */
    public static List<Regresion> compararConBaseline(Map<String, Object> actual,
                                                       Map<String, Object> baseline,
                                                       double tolerancia) {
        // TODO 1: valida que actual y baseline no sean null; retorna List.of() si alguno es null.
        // TODO 2: crea una lista mutable de Regresion para acumular resultados.
        // TODO 3: recorre las claves de actual; si la clave no está en baseline, añade severidad "NUEVO".
        // TODO 4: para claves en ambos mapas y valores numéricos (instanceof Number), calcula desviación relativa.
        // TODO 5: si la desviación > tolerancia*2, añade severidad "ALTO".
        // TODO 6: si la desviación > tolerancia (y no > tolerancia*2), añade severidad "BAJO".
        // TODO 7: para claves en ambos mapas con valores de texto (!= igual), añade severidad "ALTO".
        // TODO 8: recorre las claves de baseline que NO están en actual y añade severidad "ELIMINADO".
        // TODO 9: ordena la lista: "ALTO" < "ELIMINADO" < "NUEVO" < "BAJO" (usa comparador por severidad).
        // TODO 10: retorna List.copyOf(lista).
        return List.of();
    }

    /**
     * Serializa el mapa {@code baseline} a una cadena de texto reproducible.
     *
     * <p>Formato: una línea por entrada {@code "clave=valor"}, ordenadas por clave.
     * Las líneas se unen con {@code "\n"}.
     *
     * @param baseline mapa a serializar; no puede ser null
     * @return cadena serializada o {@code ""} si baseline es null o vacío
     */
    public static String guardarBaseline(Map<String, Object> baseline) {
        // TODO 11 (reutilizado del bloque como separador visual):
        //   Este método no lleva TODO numerado como parte de los 10 principales;
        //   tiene su propio par de TODO internos indicados a continuación.
        // TODO A: valida que baseline no sea null; retorna "" si es null o vacío.
        // TODO B: ordena las claves, construye "clave=valor" por cada entrada y une con "\n".
        return "";
    }

    public static void main(String[] args) {
        Map<String, Object> base = Map.of("latencia_ms", 120, "errores", 0, "version", "1.0");
        Map<String, Object> actual = Map.of("latencia_ms", 135, "errores", 0, "version", "1.0", "nuevo_kpi", 42);
        List<Regresion> resultado = compararConBaseline(actual, base, 0.10);
        System.out.println("Regresiones: " + resultado);
        System.out.println("Baseline serializado:\n" + guardarBaseline(base));
    }

    // ---- 10 RETOS EXTRA ----------------------------------------------------------

    /**
     * Reto Extra 1: golden master / snapshot testing.
     * Comprueba si {@code salida} coincide EXACTAMENTE con {@code snapshot}.
     * Devuelve true si coincide, false si hay diferencia.
     */
    public static boolean verificarSnapshot(String salida, String snapshot) {
        // GUÍA: teoría 2.1 (golden master testing: la salida completa ES el oráculo).
        // 1. Ambos null → true (coinciden).
        // 2. Uno null y el otro no → false.
        // 3. Compara con .equals().
        // PISTA: Objects.equals(salida, snapshot)
        // OJO: el test pasa cadenas con saltos de línea; usa .equals() no ==.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para verificarSnapshot");
    }

    /**
     * Reto Extra 2: tolerancia por métrica.
     * Aplica tolerancias específicas por nombre de métrica (mapa toleranciasPorMetrica)
     * en lugar de una tolerancia global. Si la métrica no tiene tolerancia específica, usa la global.
     */
    public static List<Regresion> compararConToleranciasPorMetrica(
            Map<String, Object> actual,
            Map<String, Object> baseline,
            Map<String, Double> toleranciasPorMetrica,
            double toleranciaGlobal) {
        // GUÍA: teoría 2.2 (tolerancias por métrica: latencia puede variar 10 % pero errores deben ser 0).
        // 1. Extrae la tolerancia de toleranciasPorMetrica.getOrDefault(clave, toleranciaGlobal).
        // 2. Reutiliza la lógica numérica de compararConBaseline para cada clave.
        // PISTA: llama a compararConBaseline pasando un mapa de una sola clave, o replica la lógica.
        // OJO: si toleranciasPorMetrica es null, usa toleranciaGlobal para todo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para compararConToleranciasPorMetrica");
    }

    /**
     * Reto Extra 3: aprobar cambios intencionados.
     * Dado un conjunto de métricas "aprobadas" (que se sabe que han cambiado intencionalmente),
     * filtra las regresiones eliminando las que afectan a métricas aprobadas.
     */
    public static List<Regresion> filtrarAprobadas(List<Regresion> regresiones, java.util.Set<String> aprobadas) {
        // GUÍA: teoría 2.3 (flujo de aprobación de baseline: cuando un cambio es deliberado).
        // 1. Filtra regresiones cuya métrica NO esté en aprobadas.
        // PISTA: regresiones.stream().filter(r -> !aprobadas.contains(r.metrica())).toList()
        // OJO: aprobadas null → no se filtra nada (devuelve la lista original).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para filtrarAprobadas");
    }

    /**
     * Reto Extra 4: diff legible de dos textos de baseline.
     * Compara línea por línea dos cadenas serializadas. Devuelve las líneas que difieren
     * en formato "+ nueva" / "- eliminada".
     */
    public static List<String> diffBaseline(String baselineAnterior, String baselineNuevo) {
        // GUÍA: teoría 2.4 (diff de baseline: facilita la revisión en PR).
        // 1. Divide cada cadena por "\n".
        // 2. Líneas en nuevo y no en anterior → "+ línea".
        // 3. Líneas en anterior y no en nuevo → "- línea".
        // 4. Devuelve la lista de cambios (primero los "+" luego los "-") o List.of() si no hay cambios.
        // PISTA: usa Set<String> para las líneas anteriores y nuevas.
        // OJO: cadena vacía → array de una sola cadena vacía; filtra líneas vacías.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para diffBaseline");
    }

    /**
     * Reto Extra 5: baseline versionada con git (guion).
     * Devuelve la cadena de comando git para guardar el archivo de baseline como commit.
     * Formato: "git add baseline.txt && git commit -m 'baseline: <etiqueta>'"
     */
    public static String comandoGitBaseline(String etiqueta) {
        // GUÍA: teoría 2.5 (baseline versionada en git: cada baseline es un commit o tag).
        // 1. Valida que etiqueta no sea null ni vacía; devuelve "" si no es válida.
        // 2. Construye el comando con la etiqueta interpolada.
        // PISTA: "git add baseline.txt && git commit -m 'baseline: " + etiqueta + "'"
        // OJO: si etiqueta tiene comillas simples, el comando rompería la shell; el test verifica sin comillas.
        // CULTURA: versionar la baseline en git permite a b23 (CI) comparar cada build contra el commit
        //   anterior de baseline con un solo git diff, detectando regresión de rendimiento automáticamente.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para comandoGitBaseline");
    }

    /**
     * Reto Extra 6: regresión de rendimiento (no solo funcional).
     * Devuelve true si la latencia actual supera la de baseline más la tolerancia absoluta en ms.
     */
    public static boolean hayRegresionLatencia(long latenciaBaseMs, long latenciaActualMs, long toleranciaMs) {
        // GUÍA: teoría 2.6 (regresión de rendimiento: tan importante como la funcional).
        // 1. Hay regresión si latenciaActualMs > latenciaBaseMs + toleranciaMs.
        // PISTA: return latenciaActualMs > latenciaBaseMs + toleranciaMs;
        // OJO: el test verifica el caso límite exacto (latenciaActualMs == base + tolerancia) → NO es regresión.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para hayRegresionLatencia");
    }

    /**
     * Reto Extra 7: approval testing con decisión humana.
     * Simula el flujo: si el diff tiene 0 cambios → APROBADO automáticamente;
     * si tiene cambios → PENDIENTE_REVISION.
     */
    public static String estadoAprobacion(List<String> diff) {
        // GUÍA: teoría 2.7 (approval testing: el humano aprueba el primer golden master).
        // 1. Si diff null o vacío → "APROBADO".
        // 2. Si diff tiene entradas → "PENDIENTE_REVISION".
        // PISTA: (diff == null || diff.isEmpty()) ? "APROBADO" : "PENDIENTE_REVISION"
        // OJO: diff con una sola cadena vacía → también es "PENDIENTE_REVISION" (tiene contenido).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para estadoAprobacion");
    }

    /**
     * Reto Extra 8: datos no deterministas (seed fijo para reproducibilidad).
     * Genera N números "aleatorios" con semilla fija. Devuelve la lista reproducible.
     */
    public static List<Integer> generarConSeed(long seed, int n) {
        // GUÍA: teoría 2.8 (seed fijo: convierte datos no deterministas en deterministas para tests).
        // 1. Crea new java.util.Random(seed).
        // 2. Genera n enteros entre 0 y 999 con nextInt(1000).
        // 3. Devuelve la lista.
        // PISTA: var rng = new java.util.Random(seed); IntStream.range(0,n).map(i -> rng.nextInt(1000)).boxed().toList()
        // OJO: n <= 0 → List.of().
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para generarConSeed");
    }

    /**
     * Reto Extra 9: baseline entre corridas — calcula el delta de una métrica entre dos corridas.
     * Devuelve el delta porcentual: (corrida2 - corrida1) / corrida1 * 100.
     */
    public static double deltaPorcentual(double corrida1, double corrida2) {
        // GUÍA: teoría 2.9 (comparar corridas consecutivas, no solo contra un golden master fijo).
        // 1. Si corrida1 == 0 → retorna 0.0 (evita división por cero).
        // 2. Calcula (corrida2 - corrida1) / corrida1 * 100.
        // PISTA: corrida1 == 0.0 ? 0.0 : (corrida2 - corrida1) / corrida1 * 100.0
        // OJO: el test comprueba con redondeo a 2 decimales → usa assertEquals(esperado, actual, 0.01).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para deltaPorcentual");
    }

    /**
     * Reto Extra 10: enlace con b23 (CI compara contra baseline).
     * Formatea un resumen de regresiones como texto de informe para un pipeline CI.
     * Formato: "REGRESION DETECTADA: N alto, M bajo, K nuevo, J eliminado\nVer: <url>"
     */
    public static String informeCI(List<Regresion> regresiones, String urlBaseline) {
        // GUÍA: teoría 2.10 (integrar la comparación de baseline en el pipeline CI como paso de gate).
        // 1. Cuenta regresiones por severidad.
        // 2. Si no hay regresiones → "SIN_REGRESION".
        // 3. Si hay → "REGRESION DETECTADA: N alto, M bajo, K nuevo, J eliminado\nVer: " + urlBaseline.
        // PISTA: usa Collections.frequency o streams con filter().count().
        // OJO: urlBaseline null → omite la línea "Ver:".
        // CULTURA: en b23 el step de CI llama a compararConBaseline y si hay ALTOs el pipeline falla (exit 1).
        //   Este informe va al bot de Slack/GitHub comment para que el equipo lo vea sin abrir la consola.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para informeCI");
    }
}
