package com.masterclass.api.b03_core;

import org.junit.jupiter.api.Test;
import java.time.Clock;
import static org.junit.jupiter.api.Assertions.*;

class Ej035JavaConfigBeansTest {

    @Test
    void elContextoEntregaElClock() {
        Clock c = Ej035JavaConfigBeans.obtenerClock();
        assertNotNull(c, "el @Bean clock() debe estar definido y registrado");
        assertNotNull(c.instant());
    }
}
