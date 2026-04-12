package bloque3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej16 - Visualizar")
class Ej16_VisualizarTest {

    private final int[][] datos = {{1, 5, 10}, {3, 7, 2}};
    private final Ej16_Visualizar p = new Ej16_Visualizar(datos);

    @Test @DisplayName("mostrarSimple: formato basico")
    void simple() {
        assertEquals("1 5 10\n3 7 2", p.mostrarSimple());
    }

    @Test @DisplayName("mostrarConBorde: contiene + en esquinas")
    void conBorde() {
        String r = p.mostrarConBorde();
        assertTrue(r.startsWith("+"));
        assertTrue(r.endsWith("+"));
    }

    @Test @DisplayName("mostrarConIndices: contiene numeros de fila")
    void conIndices() {
        String r = p.mostrarConIndices();
        assertTrue(r.contains("1"));
        assertTrue(r.contains("2"));
    }

    @Test @DisplayName("mostrarConSumaFila: muestra sumas correctas")
    void conSumaFila() {
        String r = p.mostrarConSumaFila();
        assertTrue(r.contains("16")); // 1+5+10
        assertTrue(r.contains("12")); // 3+7+2
    }

    @Test @DisplayName("mostrarHeatmap: usa simbolos de densidad")
    void heatmap() {
        int[][] d = {{0, 1, 5, 10}};
        Ej16_Visualizar v = new Ej16_Visualizar(d);
        String r = v.mostrarHeatmap();
        assertNotNull(r);
        assertFalse(r.isEmpty());
    }

    @Test @DisplayName("mostrarTranspuesto: muestra la transpuesta")
    void transpuesto() {
        String r = p.mostrarTranspuesto();
        assertNotNull(r);
        String[] lineas = r.split("\n");
        assertEquals(3, lineas.length); // 3 filas (original tiene 3 columnas)
    }

    @Test @DisplayName("getDatos: devuelve copia profunda")
    void getDatos_copia() {
        int[][] copia = p.getDatos();
        assertNotNull(copia);
        copia[0][0] = 999;
        assertEquals(1, p.getDatos()[0][0]);
    }
}
