package com.masterclass.nivel3_herencia_interfaces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 3 - Ejercicio 20: Segregacion de Interfaces (ISP)")
class Ejercicio20_SegregacionInterfacesTest {

    @Test
    @DisplayName("20.1 - soloLectura devuelve contenido pero no es Escribible")
    void soloLectura() {
        var obj = Ejercicio20_SegregacionInterfaces.crearSoloLectura("datos");
        assertThat(obj.leer()).isEqualTo("datos");
        assertThat(obj).isNotInstanceOf(Ejercicio20_SegregacionInterfaces.Escribible.class);
    }

    @Test
    @DisplayName("20.2 - lecturaEscritura permite leer y escribir")
    void lecturaEscritura() {
        var obj = Ejercicio20_SegregacionInterfaces.crearLecturaEscritura("inicio");
        assertThat(((Ejercicio20_SegregacionInterfaces.Leible) obj).leer()).isEqualTo("inicio");
        ((Ejercicio20_SegregacionInterfaces.Escribible) obj).escribir("nuevo");
        assertThat(((Ejercicio20_SegregacionInterfaces.Leible) obj).leer()).isEqualTo("nuevo");
    }

    @Test
    @DisplayName("20.3 - completo permite leer, escribir y borrar")
    void completo() {
        var obj = Ejercicio20_SegregacionInterfaces.crearCompleto("datos");
        assertThat(((Ejercicio20_SegregacionInterfaces.Leible) obj).leer()).isEqualTo("datos");
        ((Ejercicio20_SegregacionInterfaces.Borrable) obj).borrar();
        assertThat(((Ejercicio20_SegregacionInterfaces.Leible) obj).leer()).isEqualTo("");
    }

    @Test
    @DisplayName("20.4 - intentarEscribir devuelve true si es Escribible")
    void intentarEscribirOk() {
        var obj = Ejercicio20_SegregacionInterfaces.crearLecturaEscritura("x");
        assertThat(Ejercicio20_SegregacionInterfaces.intentarEscribir(obj, "y")).isTrue();
        assertThat(((Ejercicio20_SegregacionInterfaces.Leible) obj).leer()).isEqualTo("y");
    }

    @Test
    @DisplayName("20.5 - intentarEscribir devuelve false si no es Escribible")
    void intentarEscribirFail() {
        var obj = Ejercicio20_SegregacionInterfaces.crearSoloLectura("x");
        assertThat(Ejercicio20_SegregacionInterfaces.intentarEscribir(obj, "y")).isFalse();
    }
}
