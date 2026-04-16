package com.masterclass.nivel1_fundamentos_genericos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class TestEjercicio02 {

    @Test
    @DisplayName("Debe permitir almacenar una Clave Integer y un Valor String")
    void testTuplaIntegerString() {
        // Descomenta estas líneas cuando metas <K,V> a la clase Tupla
        
        /*
        Ejercicio02_TuplaKVP<Integer, String> empleado = new Ejercicio02_TuplaKVP<>(1, "Paco");
        
        Integer id = empleado.getClave();
        String nombre = empleado.getValor();
        
        assertThat(id).isEqualTo(1);
        assertThat(nombre).isEqualTo("Paco");
        */
    }

    @Test
    @DisplayName("Debe permitir almacenar String, String como Key-Value")
    void testTuplaStringString() {
        // Descomenta estas líneas cuando metas <K,V> a la clase Tupla

        /*
        Ejercicio02_TuplaKVP<String, String> credenciales = new Ejercicio02_TuplaKVP<>("admin", "1234");
        
        assertThat(credenciales.getClave()).isEqualTo("admin");
        assertThat(credenciales.getValor()).isEqualTo("1234");
        */
    }
}
