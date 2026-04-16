package com.masterclass.nivel1_interfaces_basicas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 1 - Ejercicio 05: Multiples Interfaces")
class Ejercicio05_MultiplesInterfacesTest {

    @Test
    @DisplayName("05.1 - crearDocumento devuelve un DocumentoCompleto no nulo")
    void debeCrearDocumento() {
        var doc = Ejercicio05_MultiplesInterfaces.crearDocumento("Hola");
        assertThat(doc).isNotNull();
    }

    @Test
    @DisplayName("05.2 - imprimir() formatea con cabecera y pie")
    void debeImprimir() {
        var doc = Ejercicio05_MultiplesInterfaces.crearDocumento("Mi contenido");
        assertThat(doc.imprimir()).isEqualTo("=== DOCUMENTO ===\nMi contenido\n================");
    }

    @Test
    @DisplayName("05.3 - exportar('txt') devuelve contenido tal cual")
    void debeExportarTxt() {
        var doc = Ejercicio05_MultiplesInterfaces.crearDocumento("datos");
        assertThat(doc.exportar("txt")).isEqualTo("datos");
    }

    @Test
    @DisplayName("05.4 - exportar('html') envuelve en etiquetas HTML")
    void debeExportarHtml() {
        var doc = Ejercicio05_MultiplesInterfaces.crearDocumento("datos");
        assertThat(doc.exportar("html")).isEqualTo("<html><body>datos</body></html>");
    }

    @Test
    @DisplayName("05.5 - exportar('csv') reemplaza espacios por comas")
    void debeExportarCsv() {
        var doc = Ejercicio05_MultiplesInterfaces.crearDocumento("uno dos tres");
        assertThat(doc.exportar("csv")).isEqualTo("uno,dos,tres");
    }

    @Test
    @DisplayName("05.6 - exportar formato desconocido devuelve mensaje")
    void debeIndicarFormatoNoSoportado() {
        var doc = Ejercicio05_MultiplesInterfaces.crearDocumento("datos");
        assertThat(doc.exportar("xml")).isEqualTo("Formato no soportado: xml");
    }

    @Test
    @DisplayName("05.7 - el objeto es Imprimible Y Exportable")
    void debeImplementarAmbas() {
        var doc = Ejercicio05_MultiplesInterfaces.crearDocumento("test");
        assertThat(doc).isInstanceOf(Ejercicio05_MultiplesInterfaces.Imprimible.class);
        assertThat(doc).isInstanceOf(Ejercicio05_MultiplesInterfaces.Exportable.class);
    }
}
