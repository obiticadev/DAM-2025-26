package com.masterclass.api.b23_ci;

public final class Ej195GithubActionsPipeline {
    private Ej195GithubActionsPipeline() {}
    public static boolean ejecutar() {
        // TODO 1: crea .github/workflows/ci.yml que reaccione al push en 'main'.
        // TODO 2: define el job 'build-and-test' que corra en 'ubuntu-latest'.
        // TODO 3: usa actions/checkout@v4 para traer el código.
        // TODO 4: usa actions/setup-java@v3 para instalar JDK 21 temurin.
        // TODO 5: cachea las dependencias de Maven para acelerar builds.
        // TODO 6: ejecuta 'mvn -B clean verify' (testea TODO el bootcamp).
        // TODO 7: recoge el XML de surefire y failsafe reports.
        // TODO 8: si algún test falla, el pipeline debe fallar (rojo).
        // TODO 9: publica los reports como Artifacts del workflow.
        // TODO 10: devuelve la validación estructural del pipeline YML.
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Salida final de la ejecución: " + ejecutar());
    }

    public static void pasoExtra01() {
        // TODO extra aislando concepto: crea .github/workflows/ci.yml que reaccione al push en 'main'.
    }

    public static void pasoExtra02() {
        // TODO extra aislando concepto: define el job 'build-and-test' que corra en 'ubuntu-latest'.
    }

    public static void pasoExtra03() {
        // TODO extra aislando concepto: usa actions/checkout@v4 para traer el código.
    }

    public static void pasoExtra04() {
        // TODO extra aislando concepto: usa actions/setup-java@v3 para instalar JDK 21 temurin.
    }

    public static void pasoExtra05() {
        // TODO extra aislando concepto: cachea las dependencias de Maven para acelerar builds.
    }

    public static void pasoExtra06() {
        // TODO extra aislando concepto: ejecuta 'mvn -B clean verify' (testea TODO el bootcamp).
    }

    public static void pasoExtra07() {
        // TODO extra aislando concepto: recoge el XML de surefire y failsafe reports.
    }

    public static void pasoExtra08() {
        // TODO extra aislando concepto: si algún test falla, el pipeline debe fallar (rojo).
    }

    public static void pasoExtra09() {
        // TODO extra aislando concepto: publica los reports como Artifacts del workflow.
    }

    public static void pasoExtra10() {
        // TODO extra aislando concepto: devuelve la validación estructural del pipeline YML.
    }

}
