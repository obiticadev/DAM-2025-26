package com.masterclass.api.b39_fxdeploy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Ej309JpackageInstallerTest {

    @Test
    void tipoInstaladorPara() {
        assertEquals("msi", Ej309JpackageInstaller.tipoInstaladorPara("windows"));
        assertEquals("deb", Ej309JpackageInstaller.tipoInstaladorPara("linux"));
        assertEquals("dmg", Ej309JpackageInstaller.tipoInstaladorPara("mac"));
        assertEquals("app-image", Ej309JpackageInstaller.tipoInstaladorPara("haiku")); // caso límite
    }

    @Test
    void extensionInstalador() {
        assertEquals(".msi", Ej309JpackageInstaller.extensionInstalador("msi"));
        assertEquals(".deb", Ej309JpackageInstaller.extensionInstalador("DEB"));
        assertEquals("", Ej309JpackageInstaller.extensionInstalador("app-image")); // carpeta, sin extensión
    }

    @Test
    void construirComandoJpackage() {
        assertEquals("jpackage --name MiApp --app-version 2.0.0 --type msi",
                Ej309JpackageInstaller.construirComandoJpackage("MiApp", "2.0.0", "msi"));
        assertEquals("jpackage --name App --app-version 1.0.0 --type app-image",
                Ej309JpackageInstaller.construirComandoJpackage(null, null, null)); // valores por defecto
    }

    @Test
    void retoExtra01_extensionIcono() {
        assertEquals(".ico", Ej309JpackageInstaller.extensionIcono("windows"));
        assertEquals(".icns", Ej309JpackageInstaller.extensionIcono("mac"));
        assertEquals(".png", Ej309JpackageInstaller.extensionIcono("linux"));
    }

    @Test
    void retoExtra02_nombreAppValido() {
        assertTrue(Ej309JpackageInstaller.nombreAppValido("Mi App"));
        assertFalse(Ej309JpackageInstaller.nombreAppValido("a/b")); // carácter de ruta
        assertFalse(Ej309JpackageInstaller.nombreAppValido("")); // caso límite
    }

    @Test
    void retoExtra03_argAppVersion() {
        assertEquals("--app-version 2.0.0", Ej309JpackageInstaller.argAppVersion("2.0.0"));
        assertEquals("--app-version 1.0.0", Ej309JpackageInstaller.argAppVersion("2.x")); // inválida -> defecto
    }

    @Test
    void retoExtra04_argVendor() {
        assertEquals("--vendor ACME", Ej309JpackageInstaller.argVendor("ACME"));
        assertEquals("--vendor \"ACME Corp\"", Ej309JpackageInstaller.argVendor("ACME Corp")); // con espacios
        assertEquals("", Ej309JpackageInstaller.argVendor(null)); // caso límite
    }

    @Test
    void retoExtra05_nombrePaquete() {
        assertEquals("MiApp-2.0.0.msi", Ej309JpackageInstaller.nombrePaquete("MiApp", "2.0.0", "msi"));
        assertEquals("MiApp-2.0.0", Ej309JpackageInstaller.nombrePaquete("MiApp", "2.0.0", "app-image")); // sin extensión
    }

    @Test
    void retoExtra06_requiereHerramientaExterna() {
        assertTrue(Ej309JpackageInstaller.requiereHerramientaExterna("msi"));
        assertTrue(Ej309JpackageInstaller.requiereHerramientaExterna("exe"));
        assertFalse(Ej309JpackageInstaller.requiereHerramientaExterna("app-image")); // caso límite
    }

    @Test
    void retoExtra07_argInput() {
        assertEquals("--input target/dist", Ej309JpackageInstaller.argInput("target/dist"));
        assertEquals("--input target", Ej309JpackageInstaller.argInput(null)); // por defecto
    }

    @Test
    void retoExtra08_numeroDeTiposPara() {
        assertEquals(2, Ej309JpackageInstaller.numeroDeTiposPara("windows"));
        assertEquals(2, Ej309JpackageInstaller.numeroDeTiposPara("linux"));
        assertEquals(1, Ej309JpackageInstaller.numeroDeTiposPara("haiku")); // solo app-image
    }

    @Test
    void retoExtra09_tipoValidoEn() {
        assertTrue(Ej309JpackageInstaller.tipoValidoEn("msi", "windows"));
        assertFalse(Ej309JpackageInstaller.tipoValidoEn("deb", "windows")); // deb no es de Windows
        assertTrue(Ej309JpackageInstaller.tipoValidoEn("app-image", "mac")); // vale en cualquier SO
    }

    @Test
    void retoExtra10_comandoCompleto() {
        assertEquals("jpackage --name MiApp --app-version 2.0.0 --type deb --input target --main-jar app.jar",
                Ej309JpackageInstaller.comandoCompleto("MiApp", "2.0.0", "linux", "app.jar"));
    }
}
