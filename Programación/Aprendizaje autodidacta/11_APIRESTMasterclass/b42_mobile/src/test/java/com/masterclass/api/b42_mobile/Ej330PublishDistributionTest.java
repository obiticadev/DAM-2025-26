package com.masterclass.api.b42_mobile;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Ej330PublishDistributionTest {

    @Test
    void esVersionNameValida() {
        assertTrue(Ej330PublishDistribution.esVersionNameValida("1.4.2"));
        assertFalse(Ej330PublishDistribution.esVersionNameValida("1.4"));   // caso límite: faltan partes
        assertFalse(Ej330PublishDistribution.esVersionNameValida("1..0"));  // caso límite: parte vacía
    }

    @Test
    void siguienteVersionCode() {
        assertEquals(8, Ej330PublishDistribution.siguienteVersionCode(7));
        assertThrows(IllegalArgumentException.class, () -> Ej330PublishDistribution.siguienteVersionCode(-1));
        assertThrows(IllegalStateException.class, () -> Ej330PublishDistribution.siguienteVersionCode(Integer.MAX_VALUE));
    }

    @Test
    void retoExtra01_incrementarPatch() {
        assertEquals("1.0.1", Ej330PublishDistribution.incrementarPatch("1.0.0"));
        assertEquals("2.9.10", Ej330PublishDistribution.incrementarPatch("2.9.9"));
        assertThrows(IllegalArgumentException.class, () -> Ej330PublishDistribution.incrementarPatch("1.0"));
    }

    @Test
    void retoExtra02_nombreArtefacto() {
        assertEquals("miapp-1.0.0-release.apk",
                Ej330PublishDistribution.nombreArtefacto("miapp", "1.0.0", "release"));
        assertThrows(IllegalArgumentException.class,
                () -> Ej330PublishDistribution.nombreArtefacto(null, "1.0.0", "release"));
    }

    @Test
    void retoExtra03_esFormatoPublicacion() {
        assertTrue(Ej330PublishDistribution.esFormatoPublicacion("aab"));
        assertTrue(Ej330PublishDistribution.esFormatoPublicacion("AAB"));
        assertFalse(Ej330PublishDistribution.esFormatoPublicacion("exe"));
    }

    @Test
    void retoExtra04_extensionPorFormato() {
        assertEquals(".aab", Ej330PublishDistribution.extensionPorFormato("AAB"));
        assertEquals("", Ej330PublishDistribution.extensionPorFormato("zip"));
    }

    @Test
    void retoExtra05_aliasValido() {
        assertTrue(Ej330PublishDistribution.aliasValido("miclave"));
        assertFalse(Ej330PublishDistribution.aliasValido("mi clave"));
        assertFalse(Ej330PublishDistribution.aliasValido(""));
    }

    @Test
    void retoExtra06_comandoFirma() {
        assertEquals("apksigner sign --ks mi.jks --ks-key-alias clave app.apk",
                Ej330PublishDistribution.comandoFirma("mi.jks", "clave"));
        assertThrows(IllegalArgumentException.class,
                () -> Ej330PublishDistribution.comandoFirma("mi.jks", "mal alias"));
    }

    @Test
    void retoExtra07_esReleaseListo() {
        assertTrue(Ej330PublishDistribution.esReleaseListo(true, true, false));
        assertFalse(Ej330PublishDistribution.esReleaseListo(true, true, true)); // debuggable invalida
    }

    @Test
    void retoExtra08_tamanoLegible() {
        assertEquals("512 B", Ej330PublishDistribution.tamanoLegible(512));
        assertEquals("1.0 MB", Ej330PublishDistribution.tamanoLegible(1048576));
        assertThrows(IllegalArgumentException.class, () -> Ej330PublishDistribution.tamanoLegible(-1));
    }

    @Test
    void retoExtra09_categoriaStoreValida() {
        assertTrue(Ej330PublishDistribution.categoriaStoreValida("tools"));
        assertFalse(Ej330PublishDistribution.categoriaStoreValida("comida"));
    }

    @Test
    void retoExtra10_comandoBundleRelease() {
        assertEquals("./gradlew bundleRelease", Ej330PublishDistribution.comandoBundleRelease());
    }
}
