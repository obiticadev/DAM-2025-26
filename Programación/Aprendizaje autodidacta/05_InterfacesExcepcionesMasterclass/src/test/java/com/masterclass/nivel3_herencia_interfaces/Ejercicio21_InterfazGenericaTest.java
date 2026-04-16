package com.masterclass.nivel3_herencia_interfaces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 3 - Ejercicio 21: Interfaz Generica")
class Ejercicio21_InterfazGenericaTest {

    @Test
    @DisplayName("21.1 - contadorCaracteres cuenta longitud")
    void contadorCaracteres() {
        var c = Ejercicio21_InterfazGenerica.crearContadorCaracteres();
        assertThat(c.convertir("Hola")).isEqualTo(4);
        assertThat(c.convertir("")).isEqualTo(0);
    }

    @Test
    @DisplayName("21.2 - convertidorBinario convierte correctamente")
    void convertidorBinario() {
        var c = Ejercicio21_InterfazGenerica.crearConvertidorBinario();
        assertThat(c.convertir(10)).isEqualTo("1010");
        assertThat(c.convertir(255)).isEqualTo("11111111");
    }

    @Test
    @DisplayName("21.3 - convertidorMayusculas convierte")
    void convertidorMayusculas() {
        var c = Ejercicio21_InterfazGenerica.crearConvertidorMayusculas();
        assertThat(c.convertir("hola")).isEqualTo("HOLA");
    }

    @Test
    @DisplayName("21.4 - convertirLista aplica convertidor a toda la lista")
    void convertirLista() {
        var resultado = Ejercicio21_InterfazGenerica.convertirLista(
                List.of("Hola", "Mundo", "Java"),
                Ejercicio21_InterfazGenerica.crearContadorCaracteres()
        );
        assertThat(resultado).containsExactly(4, 5, 4);
    }

    @Test
    @DisplayName("21.5 - convertirLista con lista vacia devuelve lista vacia")
    void convertirListaVacia() {
        var resultado = Ejercicio21_InterfazGenerica.convertirLista(
                List.of(),
                Ejercicio21_InterfazGenerica.crearConvertidorMayusculas()
        );
        assertThat(resultado).isEmpty();
    }
}
