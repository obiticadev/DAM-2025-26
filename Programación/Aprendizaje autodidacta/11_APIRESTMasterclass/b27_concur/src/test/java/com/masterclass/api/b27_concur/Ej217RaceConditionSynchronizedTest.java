package com.masterclass.api.b27_concur;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(value = 10, unit = TimeUnit.SECONDS)
class Ej217RaceConditionSynchronizedTest {

    private static final int HILOS = 4;
    private static final int ITER = 100_000;
    private static final int TOTAL = HILOS * ITER; // 400000

    @Test
    void contadorSinSync_nuncaSuperaElTotal() {
        int r = Ej217RaceConditionSynchronized.contadorSinSync(HILOS, ITER);
        assertTrue(r > 0, "debe contar algo");
        assertTrue(r <= TOTAL, "una carrera pierde incrementos, nunca los inventa");
    }

    @Test
    void contadorConSync_esExacto() {
        assertEquals(TOTAL, Ej217RaceConditionSynchronized.contadorConSync(HILOS, ITER));
    }

    @Test
    void retoExtra01_conSynchronizedMetodo() {
        assertEquals(TOTAL, Ej217RaceConditionSynchronized.conSynchronizedMetodo(HILOS, ITER));
    }

    @Test
    void retoExtra02_conAtomicInteger() {
        assertEquals(TOTAL, Ej217RaceConditionSynchronized.conAtomicInteger(HILOS, ITER));
    }

    @Test
    void retoExtra03_conReentrantLock() {
        assertEquals(TOTAL, Ej217RaceConditionSynchronized.conReentrantLock(HILOS, ITER));
    }

    @Test
    void retoExtra04_sincronizarLoMinimo() {
        assertEquals(TOTAL, Ej217RaceConditionSynchronized.sincronizarLoMinimo(HILOS, ITER));
    }

    @Test
    void retoExtra05_volatileNoBastaParaIncrementar() {
        int r = Ej217RaceConditionSynchronized.volatileNoBastaParaIncrementar(HILOS, ITER);
        assertTrue(r > 0 && r <= TOTAL, "volatile no hace atómico el i++");
    }

    @Test
    void retoExtra06_sumaParticionadaSync() {
        long[] valores = new long[1000];
        long esperado = 0;
        for (int i = 0; i < valores.length; i++) {
            valores[i] = i + 1;
            esperado += valores[i];
        }
        assertEquals(esperado, Ej217RaceConditionSynchronized.sumaParticionadaSync(valores, HILOS));
    }

    @Test
    void retoExtra07_locksDistintosNoProtegen() {
        int r = Ej217RaceConditionSynchronized.locksDistintosNoProtegen(ITER);
        assertTrue(r > 0 && r <= 2 * ITER, "cerrojos distintos no se excluyen entre sí");
    }

    @Test
    void retoExtra08_conObjetoDedicado() {
        assertEquals(TOTAL, Ej217RaceConditionSynchronized.conObjetoDedicado(HILOS, ITER));
    }

    @Test
    void retoExtra09_sinSyncNuncaSuperaElTotal() {
        assertTrue(Ej217RaceConditionSynchronized.sinSyncNuncaSuperaElTotal(HILOS, ITER));
    }

    @Test
    void retoExtra10_solucionCanonica() {
        assertEquals(TOTAL, Ej217RaceConditionSynchronized.solucionCanonica(HILOS, ITER));
    }
}
