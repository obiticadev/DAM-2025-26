package bloque2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej12 - Transformaciones Encadenadas")
class Ej12_CadenaTest {

    private final int[][] m3x3 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

    @Test
    @DisplayName("aplicarCadena: un solo comando 90H")
    void aplicarCadena_un90H() {
        int[][] r = Ej12_Cadena.aplicarCadena(m3x3, new String[]{"90H"});
        assertNotNull(r);
        assertArrayEquals(new int[]{7, 4, 1}, r[0]);
        assertArrayEquals(new int[]{8, 5, 2}, r[1]);
        assertArrayEquals(new int[]{9, 6, 3}, r[2]);
    }

    @Test
    @DisplayName("aplicarCadena: cadena vacia devuelve copia original")
    void aplicarCadena_vacia() {
        int[][] r = Ej12_Cadena.aplicarCadena(m3x3, new String[]{});
        assertNotNull(r);
        assertArrayEquals(new int[]{1, 2, 3}, r[0]);
    }

    @Test
    @DisplayName("aplicarCadena: 90H + 90A = original")
    void aplicarCadena_horarioAntihorario() {
        int[][] r = Ej12_Cadena.aplicarCadena(m3x3, new String[]{"90H", "90A"});
        assertNotNull(r);
        assertArrayEquals(new int[]{1, 2, 3}, r[0]);
        assertArrayEquals(new int[]{4, 5, 6}, r[1]);
        assertArrayEquals(new int[]{7, 8, 9}, r[2]);
    }

    @Test
    @DisplayName("aplicarCadena: EH + EV = 180")
    void aplicarCadena_espejos() {
        int[][] r1 = Ej12_Cadena.aplicarCadena(m3x3, new String[]{"EH", "EV"});
        int[][] r2 = Ej12_Cadena.aplicarCadena(m3x3, new String[]{"180"});
        assertNotNull(r1);
        assertNotNull(r2);
        for (int i = 0; i < r1.length; i++) {
            assertArrayEquals(r2[i], r1[i]);
        }
    }

    @Test
    @DisplayName("pintar: formato correcto")
    void pintar() {
        assertEquals("1 2 3\n4 5 6\n7 8 9", Ej12_Cadena.pintar(m3x3));
    }

    @Test
    @DisplayName("secuenciaInversa: invierte orden y swapea 90H<->90A")
    void secuenciaInversa_basico() {
        String[] inv = Ej12_Cadena.secuenciaInversa(new String[]{"90H", "EV", "T"});
        assertNotNull(inv);
        assertEquals(3, inv.length);
        assertEquals("T", inv[0]);
        assertEquals("EV", inv[1]);
        assertEquals("90A", inv[2]);
    }

    @Test
    @DisplayName("cadenaYInversaEsOriginal: true para cadena arbitraria")
    void cadenaInversa() {
        assertTrue(Ej12_Cadena.cadenaYInversaEsOriginal(m3x3, new String[]{"90H", "EV", "T"}));
    }

    @Test
    @DisplayName("contarCambiosDimension: cuenta T, 90H, 90A")
    void contarCambios() {
        assertEquals(3, Ej12_Cadena.contarCambiosDimension(new String[]{"90H", "EV", "T", "180", "90A"}));
    }

    @Test
    @DisplayName("contarCambiosDimension: 0 si solo EH, EV, 180")
    void contarCambios_cero() {
        assertEquals(0, Ej12_Cadena.contarCambiosDimension(new String[]{"EH", "EV", "180"}));
    }

    @Test
    @DisplayName("dimensionesFinales: T intercambia filas y columnas")
    void dimensiones_T() {
        int[] d = Ej12_Cadena.dimensionesFinales(2, 3, new String[]{"T"});
        assertNotNull(d);
        assertArrayEquals(new int[]{3, 2}, d);
    }

    @Test
    @DisplayName("dimensionesFinales: dos T devuelven original")
    void dimensiones_TT() {
        int[] d = Ej12_Cadena.dimensionesFinales(2, 3, new String[]{"T", "T"});
        assertNotNull(d);
        assertArrayEquals(new int[]{2, 3}, d);
    }

    @Test
    @DisplayName("dimensionesFinales: 180 no cambia dimensiones")
    void dimensiones_180() {
        int[] d = Ej12_Cadena.dimensionesFinales(2, 3, new String[]{"180"});
        assertNotNull(d);
        assertArrayEquals(new int[]{2, 3}, d);
    }

    @Test
    @DisplayName("simplificarCadena: dos iguales seguidos se cancelan")
    void simplificar_dobles() {
        String[] r = Ej12_Cadena.simplificarCadena(new String[]{"90H", "90H", "T"});
        assertNotNull(r);
        assertEquals(1, r.length);
        assertEquals("T", r[0]);
    }

    @Test
    @DisplayName("simplificarCadena: nada que simplificar")
    void simplificar_nada() {
        String[] r = Ej12_Cadena.simplificarCadena(new String[]{"90H", "EV", "T"});
        assertNotNull(r);
        assertEquals(3, r.length);
    }

    @Test
    @DisplayName("simplificarCadena: todo se cancela")
    void simplificar_todo() {
        String[] r = Ej12_Cadena.simplificarCadena(new String[]{"EH", "EH"});
        assertNotNull(r);
        assertEquals(0, r.length);
    }
}
