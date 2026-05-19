package com.masterclass.api.b25_thymeleaf;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej205InternacionalizacionTest {

    @Test
    void testEjecutar_DebeCompletarse() {
        boolean resultado = Ej205Internacionalizacion.ejecutar();
        assertTrue(resultado, "El ejercicio Ej205Internacionalizacion aún no está resuelto. Revisa los TODOs.");
    }
}
