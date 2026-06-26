package com.masterclass.api.b47_pruebas;

import java.util.List;

/**
 * Ejercicio 357 · Plan de pruebas: niveles, pirámide, cobertura y criterio de salida.
 *
 * <p>Teoría: {@code teoria/47_Estrategia_Pruebas.md} (sección 1.1).
 */
public final class Ej357TestStrategyPlan {

    private Ej357TestStrategyPlan() {
    }

    // ---- Tipos de datos auxiliares -----------------------------------------------

    public enum NivelPrueba { UNITARIA, INTEGRACION, SISTEMA, ACEPTACION }

    public record Modulo(String nombre, int riesgo, int numClases) {
    }

    public record PlanPruebas(List<NivelPrueba> niveles, int numCasos, String criterioSalida) {
    }

    public record Resultados(int criticosEncontrados, double coberturaAlcanzada) {
    }

    // ---- Métodos core -------------------------------------------------------------

    /**
     * Genera un {@link PlanPruebas} para el módulo dado.
     *
     * <p>Niveles incluidos: siempre UNITARIA e INTEGRACION; SISTEMA si riesgo >= 5;
     * ACEPTACION si riesgo >= 8. El número de casos es {@code riesgo * numClases * 3}.
     * El criterio de salida es {@code "0 críticos y cobertura >= " + (60 + riesgo * 2) + "%"}.
     *
     * @param modulo módulo a planificar; no puede ser null
     * @return plan de pruebas o null si modulo es null
     */
    public static PlanPruebas planDePruebas(Modulo modulo) {
        // TODO 1: valida que modulo no sea null; retorna null si lo es.
        // TODO 2: crea una lista mutable e incluye siempre UNITARIA e INTEGRACION.
        // TODO 3: añade SISTEMA si modulo.riesgo() >= 5.
        // TODO 4: añade ACEPTACION si modulo.riesgo() >= 8.
        // TODO 5: calcula numCasos = riesgo * numClases * 3 (si resultado < 1, usa 1).
        // TODO 6: calcula el porcentaje objetivo de cobertura: 60 + riesgo * 2 (máx 100).
        // TODO 7: construye el String criterioSalida: "0 críticos y cobertura >= X%".
        // TODO 8: retorna new PlanPruebas(List.copyOf(niveles), numCasos, criterioSalida).
        return null;
    }

    /**
     * Evalúa si los resultados cumplen el criterio de salida del plan.
     *
     * <p>El criterio falla si {@code resultados.criticosEncontrados() > 0} o si
     * {@code resultados.coberturaAlcanzada()} es menor que el porcentaje extraído
     * del {@code criterioSalida} del plan (busca el número antes de {@code %}).
     *
     * @param plan      plan de pruebas con criterio; no puede ser null
     * @param resultados resultados de la ejecución; no puede ser null
     * @return {@code true} si los resultados pasan el criterio, {@code false} si no
     */
    public static boolean cumpleCriterioSalida(PlanPruebas plan, Resultados resultados) {
        // TODO 9: valida que plan y resultados no sean null; retorna false si alguno es null.
        // TODO 10: extrae el porcentaje mínimo del criterioSalida (busca número antes de '%'),
        //   retorna false si hay críticos o si coberturaAlcanzada < porcentaje mínimo.
        return false;
    }

    public static void main(String[] args) {
        Modulo modulo = new Modulo("AuthService", 7, 12);
        PlanPruebas plan = planDePruebas(modulo);
        System.out.println("Plan: " + plan);

        Resultados r = new Resultados(0, 85.0);
        System.out.println("¿Cumple criterio? " + cumpleCriterioSalida(plan, r));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) -------------

    /**
     * Reto Extra 1: pirámide vs trofeo de pruebas.
     * Dados el número de pruebas unitarias, de integración y E2E, determina
     * si forman una pirámide (unit > integracion > e2e) o un trofeo (unit ≈ integracion > e2e).
     */
    public static String clasificarEstrategia(int unitarias, int integracion, int e2e) {
        // GUÍA: teoría 1.2 (pirámide vs trofeo).
        // 1. Si unitarias > integracion > e2e → "PIRAMIDE".
        // 2. Si unitarias <= integracion y integracion > e2e → "TROFEO".
        // 3. Cualquier otro caso → "SIN_FORMA".
        // PISTA: comparaciones simples encadenadas con &&.
        // OJO: el test pasa exactamente iguales en algún umbral → usa < no <=.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para clasificarEstrategia");
    }

    /**
     * Reto Extra 2: análisis de riesgo por módulo.
     * Devuelve los módulos cuyo riesgo supera el umbral dado, ordenados de mayor a menor riesgo.
     */
    public static List<Modulo> modulosPorRiesgo(List<Modulo> modulos, int umbral) {
        // GUÍA: teoría 1.3 (análisis de riesgo).
        // 1. Filtra los módulos con riesgo > umbral.
        // 2. Ordénalos de mayor a menor con Comparator.comparingInt(Modulo::riesgo).reversed().
        // 3. Devuelve la lista resultante (.toList()).
        // PISTA: modulos.stream().filter(...).sorted(...).toList();
        // OJO: lista null → List.of() sin excepción.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para modulosPorRiesgo");
    }

    /**
     * Reto Extra 3: matriz de trazabilidad RA → número de pruebas.
     * Dado un mapa de resultado-de-aprendizaje → casos de prueba, verifica que cada RA
     * tiene al menos {@code minCasos} casos asignados. Devuelve la lista de RA sin cobertura.
     */
    public static List<String> rasSinCobertura(java.util.Map<String, Integer> matriz, int minCasos) {
        // GUÍA: teoría 1.4 (trazabilidad).
        // 1. Recorre las entradas del mapa.
        // 2. Filtra las que tienen valor < minCasos.
        // 3. Recoge las claves (los RA) en una lista ordenada.
        // PISTA: matriz.entrySet().stream().filter(e -> e.getValue() < minCasos).map(Map.Entry::getKey).sorted().toList()
        // OJO: el test comprueba con minCasos=0 → ningún RA debería faltar.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para rasSinCobertura");
    }

    /**
     * Reto Extra 4: mapa de cuándo usar cada tipo de test double.
     * Devuelve la descripción del test double apropiado para el escenario dado.
     * Escenarios: "sin_efectos_secundarios"→"STUB", "verificar_interaccion"→"MOCK",
     * "sustitucion_compleja"→"FAKE", "registro_llamadas"→"SPY", otro→"DUMMY".
     */
    public static String tipoTestDouble(String escenario) {
        // GUÍA: teoría 1.5 (test doubles: stub, mock, fake, spy, dummy).
        // 1. Usa un switch con los cinco escenarios clave.
        // 2. Default → "DUMMY" (objeto vacío que satisface la firma).
        // PISTA: return switch(escenario) { case "sin_efectos_secundarios" -> "STUB"; ... }
        // OJO: el test pasa null → devuelve "DUMMY" sin NullPointerException.
        // CULTURA: en b19 usaste Mockito.mock() → es un MOCK; cuando usaste @InjectMocks con
        //   un objeto real → era un SPY parcial. Ahora sabes el nombre de cada herramienta.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tipoTestDouble");
    }

    /**
     * Reto Extra 5: priorización de pruebas por impacto.
     * Ordena una lista de nombres de prueba según el impacto asociado (mapa separado),
     * de mayor a menor. Si una prueba no está en el mapa de impactos, su impacto es 0.
     */
    public static List<String> priorizarPruebas(List<String> pruebas, java.util.Map<String, Integer> impactos) {
        // GUÍA: teoría 1.3 (priorización por riesgo/impacto).
        // 1. Ordena comparando por impacto descendente.
        // 2. Usa impactos.getOrDefault(nombre, 0) para las que no están en el mapa.
        // PISTA: pruebas.stream().sorted(Comparator.comparingInt((String n) -> impactos.getOrDefault(n,0)).reversed()).toList()
        // OJO: lista null → List.of().
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para priorizarPruebas");
    }

    /**
     * Reto Extra 6: identificación de pruebas inestables (flaky tests).
     * Una prueba es flaky si en las últimas N ejecuciones tiene tanto éxitos como fallos.
     * Devuelve los nombres de pruebas cuyo historial contiene al menos un true Y un false.
     */
    public static List<String> detectarFlaky(java.util.Map<String, List<Boolean>> historial) {
        // GUÍA: teoría 1.6 (flaky tests: causas, detección, cuarentena).
        // 1. Recorre las entradas del historial.
        // 2. Una prueba es flaky si su lista contiene true Y también false.
        // 3. Devuelve los nombres ordenados.
        // PISTA: e.getValue().contains(true) && e.getValue().contains(false)
        // OJO: lista de historial vacía → no es flaky (necesita al menos un dato de cada tipo).
        // CULTURA: los tests flaky destruyen la confianza en la suite (b19). La solución estándar
        //   es ponerlos en cuarentena (@Disabled + issue) hasta aislar la causa.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para detectarFlaky");
    }

    /**
     * Reto Extra 7: criterios de entrada (precondiciones para empezar a probar).
     * Dado un entorno descrito por flags, indica si se cumplen todos los criterios de entrada:
     * compilación OK, base de datos disponible, artefactos desplegados.
     */
    public static boolean criteriosEntradaCumplidos(boolean compilacionOk, boolean bdDisponible, boolean artefactosDesplegados) {
        // GUÍA: teoría 1.7 (plan de pruebas: criterios de entrada vs. salida).
        // 1. Solo devuelve true cuando LOS TRES flags son true.
        // PISTA: return compilacionOk && bdDisponible && artefactosDesplegados;
        // OJO: el test verifica el caso donde solo uno falla → resultado false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para criteriosEntradaCumplidos");
    }

    /**
     * Reto Extra 8: estimación de esfuerzo de pruebas (en horas).
     * Estima horas = numCasos * horasPorCaso + numModulos * overhead.
     * Redondea al entero superior (Math.ceil).
     */
    public static int estimarEsfuerzo(int numCasos, double horasPorCaso, int numModulos, double overhead) {
        // GUÍA: teoría 1.8 (estimación de esfuerzo de QA).
        // 1. Calcula total = numCasos * horasPorCaso + numModulos * overhead.
        // 2. Redondea con (int) Math.ceil(total).
        // 3. Si algún parámetro es negativo, devuelve -1.
        // PISTA: (int) Math.ceil(numCasos * horasPorCaso + numModulos * overhead)
        // OJO: el test pasa numCasos=0 → resultado es 0 (solo el overhead de módulos).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para estimarEsfuerzo");
    }

    /**
     * Reto Extra 9: definition of done de QA.
     * Una feature está lista desde el punto de vista de QA si: plan existe,
     * cobertura alcanzada >= cobertura objetivo, y no quedan críticos abiertos.
     */
    public static boolean featureListaParaQA(boolean planExiste, double coberturaAlcanzada,
                                              double coberturaObjetivo, int criticosAbiertos) {
        // GUÍA: teoría 1.9 (Definition of Done orientada a pruebas).
        // 1. planExiste debe ser true.
        // 2. coberturaAlcanzada >= coberturaObjetivo.
        // 3. criticosAbiertos == 0.
        // PISTA: return planExiste && coberturaAlcanzada >= coberturaObjetivo && criticosAbiertos == 0;
        // OJO: test con coberturaAlcanzada == coberturaObjetivo (exactamente igual) → true.
        // CULTURA: en b23 (CI/CD) el quality gate de SonarQube implementa exactamente esta DoD
        //   de forma automática en cada PR → nadie merge sin verde.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para featureListaParaQA");
    }

    /**
     * Reto Extra 10: enlace con b19 (suite) y b23 (quality gate).
     * Dado el nombre de un bloque de la masterclass, devuelve qué herramienta de prueba
     * se usa principalmente: "b19"→"JUnit5+Mockito", "b23"→"SonarQube+Jacoco",
     * "b47"→"EstrategiaFormal", otro→"Desconocido".
     */
    public static String herramientaPrincipal(String bloque) {
        // GUÍA: teoría 1.10 (mapa de herramientas de testing a lo largo de la masterclass).
        // 1. Usa un switch expression con los tres bloques conocidos.
        // 2. Default → "Desconocido".
        // PISTA: return switch(bloque) { case "b19" -> "JUnit5+Mockito"; ... default -> "Desconocido"; };
        // OJO: null → "Desconocido" sin NPE (usa Objects.toString o guarda null con Objects.requireNonNullElse).
        // CULTURA: esta skill cierra el círculo: b19 enseña CÓMO escribir tests; b23 los ejecuta en CI;
        //   b47 enseña QUÉ probar y cómo documentarlo → ninguno puede reemplazar al otro.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para herramientaPrincipal");
    }
}
