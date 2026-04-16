package com.masterclass.nivel2_default_static_methods;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 2 - Ejercicio 09: Primer Metodo Default")
class Ejercicio09_PrimerMetodoDefaultTest {

    @Test
    @DisplayName("09.1 - formateador mayusculas transforma correctamente")
    void debeTransformarMayusculas() {
        var f = Ejercicio09_PrimerMetodoDefault.crearFormateadorMayusculas();
        assertThat(f.transformar("hola")).isEqualTo("HOLA");
    }

    @Test
    @DisplayName("09.2 - default transformarConPrefijo funciona sin sobreescribir")
    void defaultConPrefijoFunciona() {
        var f = Ejercicio09_PrimerMetodoDefault.crearFormateadorMayusculas();
        assertThat(f.transformarConPrefijo("hola", "INFO")).isEqualTo("INFO: HOLA");
    }

    @Test
    @DisplayName("09.3 - default transformarYRecortar recorta si excede maxLong")
    void defaultRecortarFunciona() {
        var f = Ejercicio09_PrimerMetodoDefault.crearFormateadorMayusculas();
        assertThat(f.transformarYRecortar("hola mundo", 4)).isEqualTo("HOLA...");
    }

    @Test
    @DisplayName("09.4 - transformarYRecortar NO recorta si no excede")
    void defaultNoRecortaSiCabe() {
        var f = Ejercicio09_PrimerMetodoDefault.crearFormateadorMayusculas();
        assertThat(f.transformarYRecortar("hola", 10)).isEqualTo("HOLA");
    }

    @Test
    @DisplayName("09.5 - formateador corchetes envuelve en []")
    void debeTransformarCorchetes() {
        var f = Ejercicio09_PrimerMetodoDefault.crearFormateadorCorchetes();
        assertThat(f.transformar("dato")).isEqualTo("[dato]");
    }

    @Test
    @DisplayName("09.6 - corchetes con default transformarConPrefijo")
    void corchetesConPrefijo() {
        var f = Ejercicio09_PrimerMetodoDefault.crearFormateadorCorchetes();
        assertThat(f.transformarConPrefijo("dato", "LOG")).isEqualTo("LOG: [dato]");
    }
}
