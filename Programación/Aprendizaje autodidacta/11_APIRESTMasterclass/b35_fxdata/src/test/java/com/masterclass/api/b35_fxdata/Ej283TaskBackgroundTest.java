package com.masterclass.api.b35_fxdata;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javafx.concurrent.Task;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(10)
class Ej283TaskBackgroundTest {

    @BeforeAll
    static void init() {
        IniciadorFx.iniciar();
    }

    /** Ejecuta el Task en el hilo FX (así updateProgress fija el progreso al instante) y recoge los avances. */
    private static List<Double> ejecutarYCapturarProgreso(Task<?> task) {
        List<Double> progresos = Collections.synchronizedList(new ArrayList<>());
        task.progressProperty().addListener((o, a, b) -> progresos.add(b.doubleValue()));
        IniciadorFx.enFx(task::run);
        return progresos;
    }

    private static boolean monotono(List<Double> xs) {
        for (int i = 1; i < xs.size(); i++) {
            if (xs.get(i) < xs.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    @Test
    void tareaSuma() throws Exception {
        Task<Integer> t = Ej283TaskBackground.tareaSuma(5);
        List<Double> progresos = ejecutarYCapturarProgreso(t);
        assertEquals(15, t.get());
        assertFalse(progresos.isEmpty());
        assertTrue(monotono(progresos), "el progreso no debe retroceder");
        assertEquals(1.0, progresos.get(progresos.size() - 1), 1e-9); // termina al 100%

        Task<Integer> limite = Ej283TaskBackground.tareaSuma(1); // caso límite: n=1 -> suma 1
        limite.run();
        assertEquals(1, limite.get());
    }

    @Test
    void tareaQueFalla() {
        Task<Integer> t = Ej283TaskBackground.tareaQueFalla();
        t.run();
        ExecutionException ex = assertThrows(ExecutionException.class, t::get);
        assertEquals("fallo controlado", ex.getCause().getMessage());
    }

    @Test
    void retoExtra01_obtenerValor() throws Exception {
        Task<Integer> t = new Task<>() {
            @Override
            protected Integer call() {
                return 42;
            }
        };
        assertEquals(42, Ej283TaskBackground.obtenerValor(t));
    }

    @Test
    void retoExtra02_mensajeDelFallo() {
        Task<Integer> t = new Task<>() {
            @Override
            protected Integer call() {
                throw new RuntimeException("ups");
            }
        };
        assertEquals("ups", Ej283TaskBackground.mensajeDelFallo(t));
    }

    @Test
    void retoExtra03_tareaDeValor() throws Exception {
        Task<String> t = Ej283TaskBackground.tareaDeValor("hola");
        t.run();
        assertEquals("hola", t.get());
    }

    @Test
    void retoExtra04_tareaDeError() {
        Task<Integer> t = Ej283TaskBackground.tareaDeError("boom");
        t.run();
        ExecutionException ex = assertThrows(ExecutionException.class, t::get);
        assertEquals("boom", ex.getCause().getMessage());
    }

    @Test
    void retoExtra05_ejecutarEnHilo() throws Exception {
        Task<Integer> t = new Task<>() {
            @Override
            protected Integer call() {
                return 7;
            }
        };
        assertEquals(7, Ej283TaskBackground.ejecutarEnHilo(t));
    }

    @Test
    void retoExtra06_cancelar() {
        Task<Integer> t = new Task<>() {
            @Override
            protected Integer call() {
                return 1;
            }
        };
        assertTrue(Ej283TaskBackground.cancelar(t));
    }

    @Test
    void retoExtra07_esMonotono() {
        assertTrue(Ej283TaskBackground.esMonotono(List.of(0.0, 0.5, 1.0)));
        assertFalse(Ej283TaskBackground.esMonotono(List.of(0.5, 0.2))); // retrocede
    }

    @Test
    void retoExtra08_fraccionProgreso() {
        assertEquals(0.5, Ej283TaskBackground.fraccionProgreso(5, 10), 1e-9);
        assertEquals(-1.0, Ej283TaskBackground.fraccionProgreso(0, 0), 1e-9); // indeterminado
    }

    @Test
    void retoExtra09_tareaConProgreso() throws Exception {
        Task<Integer> t = Ej283TaskBackground.tareaConProgreso(4);
        List<Double> progresos = ejecutarYCapturarProgreso(t);
        assertEquals(4, t.get());
        assertTrue(monotono(progresos));
        assertEquals(1.0, progresos.get(progresos.size() - 1), 1e-9);
    }

    @Test
    void retoExtra10_tareaConReintento() throws Exception {
        int[] intentos = {0};
        Task<Integer> t = Ej283TaskBackground.tareaConReintento(() -> {
            intentos[0]++;
            if (intentos[0] < 3) {
                throw new RuntimeException("aún no");
            }
            return 99;
        }, 3);
        t.run();
        assertEquals(99, t.get());
    }
}
