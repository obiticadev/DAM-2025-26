package com.masterclass.arrays.nivel04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Ej19 - Búsqueda Binaria Variantes")
class Ej19_BusquedaBinariaVariantesTest {

    private final int[] CON_DUPLICADOS = {1, 3, 3, 3, 3, 5, 7, 9};

    @Nested @DisplayName("primeraOcurrencia")
    class PrimeraOcurrencia {
        @Test @DisplayName("Primera ocurrencia de 3 → índice 1")
        void primera() {
            assertThat(Ej19_BusquedaBinariaVariantes.primeraOcurrencia(CON_DUPLICADOS, 3)).isEqualTo(1);
        }
        @Test @DisplayName("Elemento único")
        void unico() {
            assertThat(Ej19_BusquedaBinariaVariantes.primeraOcurrencia(CON_DUPLICADOS, 5)).isEqualTo(5);
        }
        @Test @DisplayName("No existe → -1")
        void noExiste() {
            assertThat(Ej19_BusquedaBinariaVariantes.primeraOcurrencia(CON_DUPLICADOS, 4)).isEqualTo(-1);
        }
        @Test @DisplayName("Primer elemento del array")
        void primero() {
            assertThat(Ej19_BusquedaBinariaVariantes.primeraOcurrencia(CON_DUPLICADOS, 1)).isEqualTo(0);
        }
    }

    @Nested @DisplayName("ultimaOcurrencia")
    class UltimaOcurrencia {
        @Test @DisplayName("Última ocurrencia de 3 → índice 4")
        void ultima() {
            assertThat(Ej19_BusquedaBinariaVariantes.ultimaOcurrencia(CON_DUPLICADOS, 3)).isEqualTo(4);
        }
        @Test @DisplayName("No existe → -1")
        void noExiste() {
            assertThat(Ej19_BusquedaBinariaVariantes.ultimaOcurrencia(CON_DUPLICADOS, 6)).isEqualTo(-1);
        }
    }

    @Nested @DisplayName("contarOcurrencias")
    class ContarOcurrencias {
        @Test @DisplayName("Contar 3s: 4 ocurrencias")
        void contar() {
            assertThat(Ej19_BusquedaBinariaVariantes.contarOcurrencias(CON_DUPLICADOS, 3)).isEqualTo(4);
        }
        @Test @DisplayName("No existe → 0")
        void noExiste() {
            assertThat(Ej19_BusquedaBinariaVariantes.contarOcurrencias(CON_DUPLICADOS, 4)).isEqualTo(0);
        }
        @Test @DisplayName("Elemento único → 1")
        void unico() {
            assertThat(Ej19_BusquedaBinariaVariantes.contarOcurrencias(CON_DUPLICADOS, 9)).isEqualTo(1);
        }
    }

    @Nested @DisplayName("lowerBound")
    class LowerBound {
        @Test @DisplayName("Lower bound de 3 → 1")
        void lb3() {
            assertThat(Ej19_BusquedaBinariaVariantes.lowerBound(CON_DUPLICADOS, 3)).isEqualTo(1);
        }
        @Test @DisplayName("Lower bound de 4 → 5 (primer >=4 es 5)")
        void lb4() {
            assertThat(Ej19_BusquedaBinariaVariantes.lowerBound(CON_DUPLICADOS, 4)).isEqualTo(5);
        }
        @Test @DisplayName("Lower bound de 0 → 0")
        void lb0() {
            assertThat(Ej19_BusquedaBinariaVariantes.lowerBound(CON_DUPLICADOS, 0)).isEqualTo(0);
        }
        @Test @DisplayName("Lower bound de 10 → length (mayor que todos)")
        void lbMayor() {
            assertThat(Ej19_BusquedaBinariaVariantes.lowerBound(CON_DUPLICADOS, 10)).isEqualTo(CON_DUPLICADOS.length);
        }
    }

    @Nested @DisplayName("upperBound")
    class UpperBound {
        @Test @DisplayName("Upper bound de 3 → 5 (primer >3 es 5)")
        void ub3() {
            assertThat(Ej19_BusquedaBinariaVariantes.upperBound(CON_DUPLICADOS, 3)).isEqualTo(5);
        }
        @Test @DisplayName("Upper bound de 5 → 6 (primer >5 es 7)")
        void ub5() {
            assertThat(Ej19_BusquedaBinariaVariantes.upperBound(CON_DUPLICADOS, 5)).isEqualTo(6);
        }
        @Test @DisplayName("Upper bound de 9 → length")
        void ub9() {
            assertThat(Ej19_BusquedaBinariaVariantes.upperBound(CON_DUPLICADOS, 9)).isEqualTo(CON_DUPLICADOS.length);
        }
    }

    @Nested @DisplayName("rangoDeValor")
    class RangoDeValor {
        @Test @DisplayName("Rango de 3 → {1, 4}")
        void rango3() {
            int[] r = Ej19_BusquedaBinariaVariantes.rangoDeValor(CON_DUPLICADOS, 3);
            assertThat(r).containsExactly(1, 4);
        }
        @Test @DisplayName("No existe → {-1, -1}")
        void noExiste() {
            int[] r = Ej19_BusquedaBinariaVariantes.rangoDeValor(CON_DUPLICADOS, 4);
            assertThat(r).containsExactly(-1, -1);
        }
    }

    @Nested @DisplayName("buscarMasCercano")
    class BuscarMasCercano {
        @Test @DisplayName("Target exacto → devuelve el mismo")
        void exacto() {
            assertThat(Ej19_BusquedaBinariaVariantes.buscarMasCercano(CON_DUPLICADOS, 5)).isEqualTo(5);
        }
        @Test @DisplayName("Entre dos valores → devuelve el más cercano")
        void entreDos() {
            // 6 está entre 5 y 7. Distancia a 5=1, distancia a 7=1 → empate → devolver el menor = 5
            assertThat(Ej19_BusquedaBinariaVariantes.buscarMasCercano(CON_DUPLICADOS, 6)).isEqualTo(5);
        }
        @Test @DisplayName("Mejor cercano asimétrico")
        void asimetrico() {
            // 8 está entre 7 y 9. Distancia a 7=1, distancia a 9=1 → empate → devolver 7
            assertThat(Ej19_BusquedaBinariaVariantes.buscarMasCercano(CON_DUPLICADOS, 8)).isEqualTo(7);
        }
        @Test @DisplayName("Menor que todos → primero")
        void menorQueTodos() {
            assertThat(Ej19_BusquedaBinariaVariantes.buscarMasCercano(CON_DUPLICADOS, -5)).isEqualTo(1);
        }
        @Test @DisplayName("Mayor que todos → último")
        void mayorQueTodos() {
            assertThat(Ej19_BusquedaBinariaVariantes.buscarMasCercano(CON_DUPLICADOS, 100)).isEqualTo(9);
        }
    }
}
