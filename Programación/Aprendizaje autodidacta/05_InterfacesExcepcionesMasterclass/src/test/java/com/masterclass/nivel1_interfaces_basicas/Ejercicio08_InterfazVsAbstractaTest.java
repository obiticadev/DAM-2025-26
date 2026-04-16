package com.masterclass.nivel1_interfaces_basicas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 1 - Ejercicio 08: Interfaz vs Clase Abstracta")
class Ejercicio08_InterfazVsAbstractaTest {

    @Test
    @DisplayName("08.1 - el pato es un Animal")
    void patoEsAnimal() {
        var pato = Ejercicio08_InterfazVsAbstracta.crearPato("Donald");
        assertThat(pato).isInstanceOf(Ejercicio08_InterfazVsAbstracta.Animal.class);
    }

    @Test
    @DisplayName("08.2 - el pato puede volar y nadar")
    void patoPuedeVolarYNadar() {
        var pato = Ejercicio08_InterfazVsAbstracta.crearPato("Donald");
        assertThat(Ejercicio08_InterfazVsAbstracta.puedeVolar(pato)).isTrue();
        assertThat(Ejercicio08_InterfazVsAbstracta.puedeNadar(pato)).isTrue();
    }

    @Test
    @DisplayName("08.3 - el pato hace Cuack!")
    void patoHaceSonido() {
        var pato = Ejercicio08_InterfazVsAbstracta.crearPato("Donald");
        assertThat(pato.hacerSonido()).isEqualTo("Cuack!");
    }

    @Test
    @DisplayName("08.4 - el pato vuela y nada con su nombre")
    void patoAcciones() {
        var pato = Ejercicio08_InterfazVsAbstracta.crearPato("Donald");
        assertThat(((Ejercicio08_InterfazVsAbstracta.Volador) pato).volar())
                .isEqualTo("Donald vuela por el cielo");
        assertThat(((Ejercicio08_InterfazVsAbstracta.Nadador) pato).nadar())
                .isEqualTo("Donald nada en el lago");
    }

    @Test
    @DisplayName("08.5 - el pez puede nadar pero NO volar")
    void pezSoloNada() {
        var pez = Ejercicio08_InterfazVsAbstracta.crearPez("Nemo");
        assertThat(Ejercicio08_InterfazVsAbstracta.puedeNadar(pez)).isTrue();
        assertThat(Ejercicio08_InterfazVsAbstracta.puedeVolar(pez)).isFalse();
    }

    @Test
    @DisplayName("08.6 - el pez hace Blub!")
    void pezHaceSonido() {
        var pez = Ejercicio08_InterfazVsAbstracta.crearPez("Nemo");
        assertThat(pez.hacerSonido()).isEqualTo("Blub!");
    }

    @Test
    @DisplayName("08.7 - el pez nada en el oceano")
    void pezNada() {
        var pez = Ejercicio08_InterfazVsAbstracta.crearPez("Nemo");
        assertThat(((Ejercicio08_InterfazVsAbstracta.Nadador) pez).nadar())
                .isEqualTo("Nemo nada en el oceano");
    }

    @Test
    @DisplayName("08.8 - presentarse usa el nombre y tipo del animal")
    void presentarse() {
        var pato = Ejercicio08_InterfazVsAbstracta.crearPato("Donald");
        assertThat(pato.presentarse()).isEqualTo("Soy Donald, un Pato");
    }
}
