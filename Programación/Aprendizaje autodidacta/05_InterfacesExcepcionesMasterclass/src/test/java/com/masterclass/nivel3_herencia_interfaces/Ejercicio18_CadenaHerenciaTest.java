package com.masterclass.nivel3_herencia_interfaces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 3 - Ejercicio 18: Cadena de Herencia")
class Ejercicio18_CadenaHerenciaTest {

    @Test
    @DisplayName("18.1 - crearProducto devuelve Presentable con todos los datos")
    void debeCrearProducto() {
        var p = Ejercicio18_CadenaHerencia.crearProducto("P01", "Laptop", "Portatil gaming");
        assertThat(p.getId()).isEqualTo("P01");
        assertThat(p.getNombre()).isEqualTo("Laptop");
        assertThat(p.getDescripcion()).isEqualTo("Portatil gaming");
    }

    @Test
    @DisplayName("18.2 - default presentar() formatea correctamente")
    void presentarFormateaCorrectamente() {
        var p = Ejercicio18_CadenaHerencia.crearProducto("P01", "Laptop", "Portatil gaming");
        assertThat(p.presentar()).isEqualTo("[P01] Laptop - Portatil gaming");
    }

    @Test
    @DisplayName("18.3 - Presentable es tambien Identificable, Nombrable y Describible")
    void presentableEsTodas() {
        var p = Ejercicio18_CadenaHerencia.crearProducto("X", "Y", "Z");
        assertThat(p).isInstanceOf(Ejercicio18_CadenaHerencia.Identificable.class);
        assertThat(p).isInstanceOf(Ejercicio18_CadenaHerencia.Nombrable.class);
        assertThat(p).isInstanceOf(Ejercicio18_CadenaHerencia.Describible.class);
    }

    @Test
    @DisplayName("18.4 - esDescribible detecta correctamente")
    void esDescribible() {
        var p = Ejercicio18_CadenaHerencia.crearProducto("X", "Y", "Z");
        assertThat(Ejercicio18_CadenaHerencia.esDescribible(p)).isTrue();
    }
}
