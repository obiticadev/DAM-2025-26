package com.masterclass.api.b03_core;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b03_core.Ej032QualifierAndPrimary.EmailNotificador;
import static org.junit.jupiter.api.Assertions.*;

class Ej032QualifierAndPrimaryTest {

    @Test
    void resuelvePorQualifier() {
        assertEquals("sms:hi", Ej032QualifierAndPrimary.resolver("sms").enviar("hi"));
        assertEquals("email:hi", Ej032QualifierAndPrimary.resolver("email").enviar("hi"));
    }

    @Test
    void qualifierInvalido() {
        assertThrows(IllegalArgumentException.class, () -> Ej032QualifierAndPrimary.resolver("fax"));
    }

    @Test
    void primarioEsEmail() {
        assertInstanceOf(EmailNotificador.class, Ej032QualifierAndPrimary.primario());
    }
}
