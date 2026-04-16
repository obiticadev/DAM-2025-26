package com.masterclass.nivel1_interfaces_basicas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 1 - Ejercicio 03: Polimorfismo con Interfaces")
class Ejercicio03_PolimorfismoConInterfacesTest {

    @Test
    @DisplayName("03.1 - crearCirculo con radio 5 tiene area ~78.54")
    void debeCrearCirculo() {
        var circulo = Ejercicio03_PolimorfismoConInterfaces.crearCirculo(5);
        assertThat(circulo.area()).isCloseTo(Math.PI * 25, within(0.01));
        assertThat(circulo.nombre()).isEqualTo("Circulo");
    }

    @Test
    @DisplayName("03.2 - crearRectangulo 4x6 tiene area 24")
    void debeCrearRectangulo() {
        var rect = Ejercicio03_PolimorfismoConInterfaces.crearRectangulo(4, 6);
        assertThat(rect.area()).isEqualTo(24.0);
        assertThat(rect.nombre()).isEqualTo("Rectangulo");
    }

    @Test
    @DisplayName("03.3 - crearTriangulo base 10, altura 5 tiene area 25")
    void debeCrearTriangulo() {
        var tri = Ejercicio03_PolimorfismoConInterfaces.crearTriangulo(10, 5);
        assertThat(tri.area()).isEqualTo(25.0);
        assertThat(tri.nombre()).isEqualTo("Triangulo");
    }

    @Test
    @DisplayName("03.4 - calcularAreaTotal suma las areas de todas las figuras")
    void debeCalcularAreaTotal() {
        var figuras = List.of(
                Ejercicio03_PolimorfismoConInterfaces.crearRectangulo(4, 6),
                Ejercicio03_PolimorfismoConInterfaces.crearTriangulo(10, 5)
        );
        assertThat(Ejercicio03_PolimorfismoConInterfaces.calcularAreaTotal(figuras))
                .isEqualTo(49.0);
    }

    @Test
    @DisplayName("03.5 - calcularAreaTotal con lista vacia devuelve 0")
    void debeDevolver0ConListaVacia() {
        assertThat(Ejercicio03_PolimorfismoConInterfaces.calcularAreaTotal(List.of()))
                .isEqualTo(0.0);
    }

    @Test
    @DisplayName("03.6 - todas las figuras son instancias de Figura")
    void todasSonFiguras() {
        assertThat(Ejercicio03_PolimorfismoConInterfaces.crearCirculo(1))
                .isInstanceOf(Ejercicio03_PolimorfismoConInterfaces.Figura.class);
        assertThat(Ejercicio03_PolimorfismoConInterfaces.crearRectangulo(1, 1))
                .isInstanceOf(Ejercicio03_PolimorfismoConInterfaces.Figura.class);
        assertThat(Ejercicio03_PolimorfismoConInterfaces.crearTriangulo(1, 1))
                .isInstanceOf(Ejercicio03_PolimorfismoConInterfaces.Figura.class);
    }
}
