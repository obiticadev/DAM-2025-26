package com.masterclass.arrays.nivel07;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 07 - Ejercicio 33: Cubo 3D")
class Ej33_Creacion3DRecorridoTest {

    @Test
    void testCrearCubo() {
        int[][][] c = Ej33_Creacion3DRecorrido.crearCubo(2, 3, 4);
        assertThat(c).isNotNull();
        assertThat(c.length).isEqualTo(2);
        assertThat(c[0].length).isEqualTo(3);
        assertThat(c[0][0].length).isEqualTo(4);
    }

    @Test
    void testSumaTotal() {
        int[][][] c = {{{1, 1}, {1, 1}}, {{1, 1}, {1, 1}}};
        assertThat(Ej33_Creacion3DRecorrido.sumaTotal(c)).isEqualTo(8);
    }

    @Test
    void testBuscar() {
        int[][][] c = {{{0, 0}, {0, 5}}, {{0, 0}, {0, 0}}};
        assertThat(Ej33_Creacion3DRecorrido.buscarElemento3D(c, 5)).containsExactly(0, 1, 1);
    }
}
