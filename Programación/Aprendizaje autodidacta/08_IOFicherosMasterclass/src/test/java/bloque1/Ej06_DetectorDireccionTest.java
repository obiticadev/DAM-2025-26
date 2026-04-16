package bloque1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej06 - Detector de Direccion y Tipo de Flujo")
class Ej06_DetectorDireccionTest {

    @Test @DisplayName("detectarDireccion: 'leer datos' devuelve INPUT")
    void detectarDireccion_input() {
        assertEquals("INPUT", Ej06_DetectorDireccion.detectarDireccion("leer datos"));
    }

    @Test @DisplayName("detectarDireccion: 'guardar informe' devuelve OUTPUT")
    void detectarDireccion_output() {
        assertEquals("OUTPUT", Ej06_DetectorDireccion.detectarDireccion("guardar informe"));
    }

    @Test @DisplayName("detectarDireccion: escenario desconocido lanza excepcion")
    void detectarDireccion_desconocido() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej06_DetectorDireccion.detectarDireccion("pintar cuadro"));
    }

    @Test @DisplayName("detectarDireccion: case insensitive")
    void detectarDireccion_caseInsensitive() {
        assertEquals("INPUT", Ej06_DetectorDireccion.detectarDireccion("IMPORTAR datos"));
    }

    @Test @DisplayName("detectarTipo: 'txt' devuelve CARACTERES")
    void detectarTipo_texto() {
        assertEquals("CARACTERES", Ej06_DetectorDireccion.detectarTipo("txt"));
    }

    @Test @DisplayName("detectarTipo: 'jpg' devuelve BYTES")
    void detectarTipo_binario() {
        assertEquals("BYTES", Ej06_DetectorDireccion.detectarTipo("jpg"));
    }

    @Test @DisplayName("detectarTipo: extension desconocida lanza excepcion")
    void detectarTipo_desconocida() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej06_DetectorDireccion.detectarTipo("xyz"));
    }

    @Test @DisplayName("recomendarClase: INPUT+BYTES devuelve FileInputStream")
    void recomendarClase_inputBytes() {
        assertEquals("FileInputStream",
                Ej06_DetectorDireccion.recomendarClase("INPUT", "BYTES"));
    }

    @Test @DisplayName("recomendarClase: OUTPUT+CARACTERES devuelve FileWriter")
    void recomendarClase_outputChars() {
        assertEquals("FileWriter",
                Ej06_DetectorDireccion.recomendarClase("OUTPUT", "CARACTERES"));
    }

    @Test @DisplayName("recomendarClase: combinacion invalida lanza excepcion")
    void recomendarClase_invalida() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej06_DetectorDireccion.recomendarClase("LATERAL", "BYTES"));
    }

    @Test @DisplayName("recomendacionCompleta: formato correcto")
    void recomendacionCompleta_formato() {
        String r = Ej06_DetectorDireccion.recomendacionCompleta("leer inventario", "csv");
        assertTrue(r.contains("leer inventario"));
        assertTrue(r.contains("csv"));
        assertTrue(r.contains("FileReader"));
    }

    @Test @DisplayName("informeLote: genera una linea por caso")
    void informeLote_lineas() {
        String[][] casos = {
                {"leer datos", "txt"},
                {"guardar imagen", "jpg"}
        };
        String informe = Ej06_DetectorDireccion.informeLote(casos);
        String[] lineas = informe.split("\n");
        assertEquals(2, lineas.length);
    }

    @Test @DisplayName("informeLote: array vacio devuelve cadena vacia")
    void informeLote_vacio() {
        assertEquals("", Ej06_DetectorDireccion.informeLote(new String[0][0]));
    }

    @Test @DisplayName("esTexto: true para html")
    void esTexto_true() {
        assertTrue(Ej06_DetectorDireccion.esTexto("html"));
    }

    @Test @DisplayName("esTexto: false para png")
    void esTexto_false() {
        assertFalse(Ej06_DetectorDireccion.esTexto("png"));
    }

    @Test @DisplayName("mostrarJerarquia: INPUT+BYTES muestra InputStream -> FileInputStream")
    void mostrarJerarquia_inputBytes() {
        String j = Ej06_DetectorDireccion.mostrarJerarquia("INPUT", "BYTES");
        assertTrue(j.contains("InputStream"));
        assertTrue(j.contains("FileInputStream"));
    }

    @Test @DisplayName("mostrarJerarquia: OUTPUT+CARACTERES muestra Writer -> FileWriter")
    void mostrarJerarquia_outputChars() {
        String j = Ej06_DetectorDireccion.mostrarJerarquia("OUTPUT", "CARACTERES");
        assertTrue(j.contains("Writer"));
        assertTrue(j.contains("FileWriter"));
    }
}
