package com.masterclass.api.b03_core;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Ej034BeanLifecycleTest {

    @Test
    void ordenDeFases() {
        var b = new Ej034BeanLifecycle();
        b.init();
        b.usar();
        b.destroy();
        assertEquals(List.of("init", "uso", "destroy"), b.fases());
    }
}
