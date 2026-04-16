package com.masterclass.nivel1_interfaces_basicas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 1 - Ejercicio 01: Tu Primera Interfaz")
class Ejercicio01_PrimeraInterfazTest {

    @Test
    @DisplayName("01.1 - crearSaludador devuelve una instancia no nula")
    void debeCrearSaludadorNoNulo() {
        Ejercicio01_PrimeraInterfaz.Saludable saludador = Ejercicio01_PrimeraInterfaz.crearSaludador();
        assertThat(saludador).isNotNull();
    }

    @Test
    @DisplayName("01.2 - saludar('Mundo') devuelve 'Hola, Mundo!'")
    void debeSaludarMundo() {
        Ejercicio01_PrimeraInterfaz.Saludable saludador = Ejercicio01_PrimeraInterfaz.crearSaludador();
        assertThat(saludador.saludar("Mundo")).isEqualTo("Hola, Mundo!");
    }

    @Test
    @DisplayName("01.3 - saludar('Java') devuelve 'Hola, Java!'")
    void debeSaludarJava() {
        Ejercicio01_PrimeraInterfaz.Saludable saludador = Ejercicio01_PrimeraInterfaz.crearSaludador();
        assertThat(saludador.saludar("Java")).isEqualTo("Hola, Java!");
    }

    @Test
    @DisplayName("01.4 - saludar con string vacio devuelve 'Hola, !'")
    void debeSaludarVacio() {
        Ejercicio01_PrimeraInterfaz.Saludable saludador = Ejercicio01_PrimeraInterfaz.crearSaludador();
        assertThat(saludador.saludar("")).isEqualTo("Hola, !");
    }

    @Test
    @DisplayName("01.5 - el saludador implementa la interfaz Saludable")
    void debeImplementarInterfaz() {
        Object saludador = Ejercicio01_PrimeraInterfaz.crearSaludador();
        assertThat(saludador).isInstanceOf(Ejercicio01_PrimeraInterfaz.Saludable.class);
    }
}
