package com.masterclass.api.b25_thymeleaf;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej206HtmlToPdfGeneratorTest {

    @Test
    void testEjecutar_DebeCompletarse() {
        boolean resultado = Ej206HtmlToPdfGenerator.ejecutar();
        assertTrue(resultado, "El ejercicio Ej206HtmlToPdfGenerator aún no está resuelto. Revisa los TODOs.");
    }
}
