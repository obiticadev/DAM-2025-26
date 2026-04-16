package bloque3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej15 - Mapa")
class Ej15_MapaTest {

    private Ej15_Mapa crearMapa() {
        int[][] t = {{0, 1, 1}, {1, 2, 1}, {0, 1, 0}};
        return new Ej15_Mapa(t);
    }

    @Test @DisplayName("Constructor hace copia profunda")
    void constructor_copiaDeep() {
        int[][] t = {{1, 0}, {0, 1}};
        Ej15_Mapa m = new Ej15_Mapa(t);
        t[0][0] = 99;
        int[][] interno = m.getTerreno();
        assertEquals(1, interno[0][0], "Modificar el array original no debe afectar al mapa");
    }

    @Test @DisplayName("buscarPrimero: encuentra primera montaña")
    void buscarPrimero_montana() {
        Ej15_Mapa m = crearMapa();
        int[] pos = m.buscarPrimero(Ej15_Mapa.MONTANA);
        assertNotNull(pos);
        assertArrayEquals(new int[]{1, 1}, pos);
    }

    @Test @DisplayName("buscarPrimero: null si no existe")
    void buscarPrimero_noExiste() {
        int[][] t = {{0, 0}, {0, 0}};
        Ej15_Mapa m = new Ej15_Mapa(t);
        assertNull(m.buscarPrimero(Ej15_Mapa.MONTANA));
    }

    @Test @DisplayName("contarTipo: cuenta tierra")
    void contarTipo_tierra() {
        assertEquals(5, crearMapa().contarTipo(Ej15_Mapa.TIERRA));
    }

    @Test @DisplayName("contarTipo: cuenta agua")
    void contarTipo_agua() {
        assertEquals(3, crearMapa().contarTipo(Ej15_Mapa.AGUA));
    }

    @Test @DisplayName("cambiarTipo: valido")
    void cambiarTipo_valido() {
        Ej15_Mapa m = crearMapa();
        assertTrue(m.cambiarTipo(0, 0, Ej15_Mapa.MONTANA));
        assertEquals(2, m.contarTipo(Ej15_Mapa.MONTANA));
    }

    @Test @DisplayName("cambiarTipo: tipo invalido devuelve false")
    void cambiarTipo_tipoInvalido() {
        assertFalse(crearMapa().cambiarTipo(0, 0, 99));
    }

    @Test @DisplayName("cambiarTipo: fuera rango devuelve false")
    void cambiarTipo_fueraRango() {
        assertFalse(crearMapa().cambiarTipo(9, 0, 0));
    }

    @Test @DisplayName("inundar: cambia celda y vecinos a agua")
    void inundar_basico() {
        Ej15_Mapa m = crearMapa();
        int cambiadas = m.inundar(1, 1);
        assertTrue(cambiadas > 0);
        assertEquals(Ej15_Mapa.AGUA, m.getTerreno()[1][1]);
        assertEquals(Ej15_Mapa.AGUA, m.getTerreno()[0][1]);
        assertEquals(Ej15_Mapa.AGUA, m.getTerreno()[1][0]);
    }

    @Test @DisplayName("mostrar: usa simbolos correctos")
    void mostrar() {
        Ej15_Mapa m = crearMapa();
        String s = m.mostrar();
        assertTrue(s.contains("~")); // agua
        assertTrue(s.contains(".")); // tierra
        assertTrue(s.contains("^")); // montaña
    }

    @Test @DisplayName("getTerreno devuelve copia independiente")
    void getTerreno_copia() {
        Ej15_Mapa m = crearMapa();
        int[][] copia = m.getTerreno();
        copia[0][0] = 99;
        assertNotEquals(99, m.getTerreno()[0][0]);
    }
}
