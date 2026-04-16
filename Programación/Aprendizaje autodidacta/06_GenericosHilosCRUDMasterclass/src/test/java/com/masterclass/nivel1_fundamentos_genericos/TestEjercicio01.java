package com.masterclass.nivel1_fundamentos_genericos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class TestEjercicio01 {

    @Test
    @DisplayName("La clase deberia soportar almacenar un String sin casteos (Type Safety)")
    void almacenarStringSeguro() {
        // Si el ejercicio está bien hecho, esto no tirará Error de compilación.
        // Simulamos resolver la clase Box.
        // HACK DE TEST: Como no podemos predecir si el alumno ha puesto o no la
        // etiqueta genérica sin usar reflexiones complejas, invocaremos los métodos asumiendo
        // que pasará la compilación de Maven si lo hace bien.
        
        Ejercicio01_BoxGenerico<String> cajaDeStrings = new Ejercicio01_BoxGenerico<>("Hola Genéricos");
        
        // Assert: El retorno debería ser de tipo String estrictamente.
        String resultado = cajaDeStrings.getContenido();
        
        assertThat(resultado)
            .as("El contenido retornado debe ser el mismo ignorando tipos cast feos.")
            .isEqualTo("Hola Genéricos");
            
        cajaDeStrings.setContenido("Adios Java Clásico");
        assertThat(cajaDeStrings.getContenido()).isEqualTo("Adios Java Clásico");
    }

    @Test
    @DisplayName("La clase deberia soportar almacenar un Integer limpiamente")
    void almacenarInteger() {
        Ejercicio01_BoxGenerico<Integer> cajaDeEnteros = new Ejercicio01_BoxGenerico<>();
        cajaDeEnteros.setContenido(42);
        
        Integer retorno = cajaDeEnteros.getContenido();
        assertThat(retorno).isEqualTo(42);
    }
}
