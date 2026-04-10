package com.masterclass.arrays.nivel10;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

@DisplayName("Nivel 10 - Ejercicio 50: Boss Final DB")
class Ej50_BossFinalDBTest {

    @Test
    void testBossDB() {
        Ej50_BossFinalDB db = new Ej50_BossFinalDB(10);
        db.insert("Producto 1", "CAT A", 100);
        db.insert("Producto 2", "CAT B", 200);
        
        assertThat(db.selectById("1")).isNotNull();
        // Lógica de tests finales
    }
}
