package com.masterclass.nivel1_fundamentos_genericos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class TestEjercicio03 {

    @Test
    @DisplayName("La lista devuelta por el método no debe tener advertencias crudas (Raw Types)")
    void testNoRawTypesAndGenericsAreSafe() {
        Ejercicio03_EvitarRawTypes testador = new Ejercicio03_EvitarRawTypes();
        
        // El test fallará hasta que corrijas el retorno para asegurar que obtenemos List<String>
        // Si no has quitado el .add(42), tu código de Test no podrá recuperar 'String'.
        
        // Descomenta y arregla en tu código:
        /*
        List<String> resultado = testador.obtenerListaNombresSegura();
        
        assertThat(resultado.size()).isEqualTo(3);
        assertThat(resultado)
            .containsExactly("Ana", "Juan", "Maria");
        */
    }
}
