package com.masterclass.api.b42_mobile;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Ej325MobileEnvOverviewTest {

    @Test
    void esConfiguracionSdkValida() {
        assertTrue(Ej325MobileEnvOverview.esConfiguracionSdkValida(21, 33, 34));
        assertFalse(Ej325MobileEnvOverview.esConfiguracionSdkValida(34, 33, 34)); // caso límite: min > target
        assertFalse(Ej325MobileEnvOverview.esConfiguracionSdkValida(0, 33, 34));  // caso límite: API 0 no existe
    }

    @Test
    void clasificarFichero() {
        assertEquals("manifest", Ej325MobileEnvOverview.clasificarFichero("app/src/main/AndroidManifest.xml"));
        assertEquals("gradle", Ej325MobileEnvOverview.clasificarFichero("app/build.gradle"));
        assertEquals("recurso", Ej325MobileEnvOverview.clasificarFichero("app/src/main/res/layout/activity_main.xml"));
        assertEquals("codigo", Ej325MobileEnvOverview.clasificarFichero("MainActivity.java"));
        assertEquals("desconocido", Ej325MobileEnvOverview.clasificarFichero("")); // caso límite: vacío
    }

    @Test
    void retoExtra01_esNombrePaqueteValido() {
        assertTrue(Ej325MobileEnvOverview.esNombrePaqueteValido("com.empresa.app"));
        assertFalse(Ej325MobileEnvOverview.esNombrePaqueteValido("app"));
        assertFalse(Ej325MobileEnvOverview.esNombrePaqueteValido("Com.Empresa"));
        assertFalse(Ej325MobileEnvOverview.esNombrePaqueteValido("com.2fast"));
    }

    @Test
    void retoExtra02_rutaRecurso() {
        assertEquals("res/layout/activity_main.xml",
                Ej325MobileEnvOverview.rutaRecurso("layout", "activity_main.xml"));
        assertThrows(IllegalArgumentException.class, () -> Ej325MobileEnvOverview.rutaRecurso(null, "x"));
    }

    @Test
    void retoExtra03_nombreAvd() {
        assertEquals("Pixel_6_API_33", Ej325MobileEnvOverview.nombreAvd("Pixel 6", 33));
        assertThrows(IllegalArgumentException.class, () -> Ej325MobileEnvOverview.nombreAvd(" ", 33));
    }

    @Test
    void retoExtra04_nombreVersionAndroid() {
        assertEquals("Android 13", Ej325MobileEnvOverview.nombreVersionAndroid(33));
        assertEquals("Desconocido", Ej325MobileEnvOverview.nombreVersionAndroid(99));
    }

    @Test
    void retoExtra05_carpetaDensidad() {
        assertEquals("drawable-xhdpi", Ej325MobileEnvOverview.carpetaDensidad("XHDPI"));
        assertEquals("", Ej325MobileEnvOverview.carpetaDensidad(""));
    }

    @Test
    void retoExtra06_contarPorCategoria() {
        List<String> ficheros = List.of(
                "app/src/main/AndroidManifest.xml",
                "MainActivity.java",
                "DetailActivity.java",
                "app/build.gradle");
        assertEquals(2, Ej325MobileEnvOverview.contarPorCategoria(ficheros, "codigo"));
        assertEquals(0, Ej325MobileEnvOverview.contarPorCategoria(ficheros, "recurso"));
    }

    @Test
    void retoExtra07_permisoCompleto() {
        assertEquals("android.permission.CAMERA", Ej325MobileEnvOverview.permisoCompleto("camera"));
        assertEquals("android.permission.CAMERA", Ej325MobileEnvOverview.permisoCompleto("android.permission.CAMERA"));
    }

    @Test
    void retoExtra08_esBuildTypeValido() {
        assertTrue(Ej325MobileEnvOverview.esBuildTypeValido("release"));
        assertFalse(Ej325MobileEnvOverview.esBuildTypeValido("staging"));
    }

    @Test
    void retoExtra09_esRecursoApaisado() {
        assertTrue(Ej325MobileEnvOverview.esRecursoApaisado("layout-land"));
        assertFalse(Ej325MobileEnvOverview.esRecursoApaisado("layout"));
    }

    @Test
    void retoExtra10_comandoGradle() {
        assertEquals("./gradlew assembleDebug", Ej325MobileEnvOverview.comandoGradle("assembleDebug"));
        assertThrows(IllegalArgumentException.class, () -> Ej325MobileEnvOverview.comandoGradle(" "));
    }
}
