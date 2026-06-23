package com.masterclass.api.b41_anim;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Ej324MiniGame2DTest {

    private static final double D = 1e-9;

    @Test
    void velocidadTrasPared() {
        assertEquals(3, Ej324MiniGame2D.velocidadTrasPared(0, -3, 100), D);    // toca el 0 yendo hacia él -> invierte
        assertEquals(3, Ej324MiniGame2D.velocidadTrasPared(50, 3, 100), D);    // en medio -> igual
        assertEquals(-3, Ej324MiniGame2D.velocidadTrasPared(100, 3, 100), D);  // caso límite: toca el borde máximo
    }

    @Test
    void golpeaPala() {
        assertTrue(Ej324MiniGame2D.golpeaPala(25, 20, 20));   // 25 dentro de [20,40]
        assertFalse(Ej324MiniGame2D.golpeaPala(50, 20, 20));  // caso límite: 50 fuera de [20,40]
    }

    @Test
    void hayGanador() {
        assertEquals(1, Ej324MiniGame2D.hayGanador(5, 2, 5));
        assertEquals(2, Ej324MiniGame2D.hayGanador(2, 5, 5));
        assertEquals(0, Ej324MiniGame2D.hayGanador(2, 3, 5)); // caso límite: partida en curso
    }

    @Test
    void retoExtra01_puntuar() {
        assertEquals(15, Ej324MiniGame2D.puntuar(10, 5));
    }

    @Test
    void retoExtra02_vidasTrasFallo() {
        assertEquals(2, Ej324MiniGame2D.vidasTrasFallo(3));
        assertEquals(0, Ej324MiniGame2D.vidasTrasFallo(0)); // no baja de cero
    }

    @Test
    void retoExtra03_juegoTerminado() {
        assertTrue(Ej324MiniGame2D.juegoTerminado(0));
        assertFalse(Ej324MiniGame2D.juegoTerminado(1));
    }

    @Test
    void retoExtra04_velocidadPorNivel() {
        assertEquals(100, Ej324MiniGame2D.velocidadPorNivel(100, 0), D);
        assertEquals(120, Ej324MiniGame2D.velocidadPorNivel(100, 2), 1e-9);
    }

    @Test
    void retoExtra05_clampPala() {
        assertEquals(0, Ej324MiniGame2D.clampPala(-5, 20, 100), D);
        assertEquals(80, Ej324MiniGame2D.clampPala(95, 20, 100), D); // 80+20 = 100, justo el borde
    }

    @Test
    void retoExtra06_efectoRebotePala() {
        assertEquals(0.0, Ej324MiniGame2D.efectoRebotePala(30, 20, 20), D);  // centro
        assertEquals(-1.0, Ej324MiniGame2D.efectoRebotePala(20, 20, 20), D); // borde superior
    }

    @Test
    void retoExtra07_ladrillosRestantes() {
        assertEquals(2, Ej324MiniGame2D.ladrillosRestantes(new boolean[]{true, false, true}));
    }

    @Test
    void retoExtra08_nivelCompletado() {
        assertTrue(Ej324MiniGame2D.nivelCompletado(new boolean[]{false, false}));
        assertFalse(Ej324MiniGame2D.nivelCompletado(new boolean[]{false, true}));
    }

    @Test
    void retoExtra09_indiceLadrillo() {
        assertEquals(13, Ej324MiniGame2D.indiceLadrillo(2, 3, 5));
    }

    @Test
    void retoExtra10_siguienteNivel() {
        assertEquals(2, Ej324MiniGame2D.siguienteNivel(1, true, 3));
        assertEquals(3, Ej324MiniGame2D.siguienteNivel(3, true, 3)); // ya en el máximo
        assertEquals(1, Ej324MiniGame2D.siguienteNivel(1, false, 3)); // nivel no completado
    }
}
