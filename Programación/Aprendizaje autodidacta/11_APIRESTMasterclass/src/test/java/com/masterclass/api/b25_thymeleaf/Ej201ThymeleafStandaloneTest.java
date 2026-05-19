package com.masterclass.api.b25_thymeleaf;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej201ThymeleafStandaloneTest {

    @Test
    void testEjecutar_DebeCompletarse() {
        boolean resultado = Ej201ThymeleafStandalone.ejecutar();
        assertTrue(resultado, "El ejercicio Ej201ThymeleafStandalone aún no está resuelto. Revisa los TODOs.");
    }
}
