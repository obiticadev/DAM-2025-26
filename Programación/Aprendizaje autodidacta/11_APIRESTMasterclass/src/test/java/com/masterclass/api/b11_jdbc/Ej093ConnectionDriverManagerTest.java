package com.masterclass.api.b11_jdbc;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Ej093ConnectionDriverManagerTest {

    @Test
    void conectaH2EnMemoria() throws Exception {
        assertTrue(Ej093ConnectionDriverManager.conectaYValida("jdbc:h2:mem:ej093", "sa", ""));
    }
}
