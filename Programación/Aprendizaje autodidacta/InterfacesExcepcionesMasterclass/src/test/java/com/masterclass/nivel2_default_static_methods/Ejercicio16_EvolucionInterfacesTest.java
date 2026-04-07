package com.masterclass.nivel2_default_static_methods;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 2 - Ejercicio 16: Evolucion de Interfaces")
class Ejercicio16_EvolucionInterfacesTest {

    @Test
    @DisplayName("16.1 - guardar y obtenerTodos funciona")
    void guardarYObtener() {
        var repo = Ejercicio16_EvolucionInterfaces.crearRepositorioEnMemoria();
        repo.guardar("Hola");
        repo.guardar("Mundo");
        assertThat(repo.obtenerTodos()).containsExactly("Hola", "Mundo");
    }

    @Test
    @DisplayName("16.2 - buscarPorId devuelve el elemento correcto")
    void buscarPorId() {
        var repo = Ejercicio16_EvolucionInterfaces.crearRepositorioEnMemoria();
        repo.guardar("A");
        repo.guardar("B");
        repo.guardar("C");
        assertThat(repo.buscarPorId(1)).isEqualTo("B");
    }

    @Test
    @DisplayName("16.3 - buscarPorId fuera de rango devuelve null")
    void buscarFueraDeRango() {
        var repo = Ejercicio16_EvolucionInterfaces.crearRepositorioEnMemoria();
        repo.guardar("A");
        assertThat(repo.buscarPorId(5)).isNull();
    }

    @Test
    @DisplayName("16.4 - default guardarTodos funciona automaticamente")
    void guardarTodos() {
        var repo = Ejercicio16_EvolucionInterfaces.crearRepositorioEnMemoria();
        repo.guardarTodos(List.of("X", "Y", "Z"));
        assertThat(repo.obtenerTodos()).containsExactly("X", "Y", "Z");
    }

    @Test
    @DisplayName("16.5 - default contar devuelve el numero correcto")
    void contar() {
        var repo = Ejercicio16_EvolucionInterfaces.crearRepositorioEnMemoria();
        repo.guardarTodos(List.of("A", "B", "C", "D"));
        assertThat(repo.contar()).isEqualTo(4);
    }

    @Test
    @DisplayName("16.6 - default existeId funciona correctamente")
    void existeId() {
        var repo = Ejercicio16_EvolucionInterfaces.crearRepositorioEnMemoria();
        repo.guardar("Unico");
        assertThat(repo.existeId(0)).isTrue();
        assertThat(repo.existeId(1)).isFalse();
    }
}
