package com.masterclass.api.b45_juego3d;

import com.masterclass.api.b45_juego3d.Ej349GameEngineArchitecture.Mundo;
import com.masterclass.api.b45_juego3d.Ej349GameEngineArchitecture.Posicion;
import com.masterclass.api.b45_juego3d.Ej349GameEngineArchitecture.Salud;
import com.masterclass.api.b45_juego3d.Ej349GameEngineArchitecture.Velocidad;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Ej349GameEngineArchitectureTest {

    @Test
    void tick() {
        Mundo m = new Mundo();
        int movil = m.crearEntidad();
        m.agregar(movil, new Posicion(0, 0, 0));
        m.agregar(movil, new Velocidad(1, 2, 3));
        int quieto = m.crearEntidad();
        m.agregar(quieto, new Posicion(5, 5, 5)); // sin Velocidad: no debe moverse

        Ej349GameEngineArchitecture.tick(m, 1.0);

        assertEquals(new Posicion(1, 2, 3), m.obtener(movil, Posicion.class));
        assertEquals(new Posicion(5, 5, 5), m.obtener(quieto, Posicion.class)); // caso límite: sin velocidad
    }

    @Test
    void entidadesCon() {
        Mundo m = new Mundo();
        int conVel = m.crearEntidad();
        m.agregar(conVel, new Velocidad(1, 0, 0));
        int sinVel = m.crearEntidad();
        m.agregar(sinVel, new Posicion(0, 0, 0));

        assertEquals(List.of(conVel), Ej349GameEngineArchitecture.entidadesCon(m, Velocidad.class));
        assertEquals(List.of(), Ej349GameEngineArchitecture.entidadesCon(m, Salud.class)); // caso límite: ninguna
    }

    @Test
    void retoExtra01_contarEntidades() {
        Mundo m = new Mundo();
        m.crearEntidad();
        m.crearEntidad();
        assertEquals(2, Ej349GameEngineArchitecture.contarEntidades(m));
        assertEquals(0, Ej349GameEngineArchitecture.contarEntidades(new Mundo())); // caso límite
    }

    @Test
    void retoExtra02_entidadesConDos() {
        Mundo m = new Mundo();
        int ambos = m.crearEntidad();
        m.agregar(ambos, new Posicion(0, 0, 0));
        m.agregar(ambos, new Velocidad(0, 0, 0));
        int uno = m.crearEntidad();
        m.agregar(uno, new Posicion(0, 0, 0));

        assertEquals(List.of(ambos),
                Ej349GameEngineArchitecture.entidadesConDos(m, Posicion.class, Velocidad.class));
    }

    @Test
    void retoExtra03_clonarPosicion() {
        Posicion p = new Posicion(1, 2, 3);
        assertEquals(p, Ej349GameEngineArchitecture.clonarPosicion(p));
        assertNull(Ej349GameEngineArchitecture.clonarPosicion(null)); // caso límite
    }

    @Test
    void retoExtra04_aplicarGravedad() {
        assertEquals(new Velocidad(0, -5, 0),
                Ej349GameEngineArchitecture.aplicarGravedad(new Velocidad(0, 0, 0), -10, 0.5));
    }

    @Test
    void retoExtra05_integrar() {
        assertEquals(new Posicion(2, 4, 6),
                Ej349GameEngineArchitecture.integrar(new Posicion(0, 0, 0), new Velocidad(1, 2, 3), 2));
    }

    @Test
    void retoExtra06_ordenarSistemas() {
        assertEquals(List.of("input", "fisica", "render"),
                Ej349GameEngineArchitecture.ordenarSistemas(
                        List.of("render", "fisica", "input"),
                        List.of("input", "fisica", "render")));
    }

    @Test
    void retoExtra07_pasoFijo() {
        assertEquals(2, Ej349GameEngineArchitecture.pasoFijo(0.05, 0.02));
        assertEquals(0, Ej349GameEngineArchitecture.pasoFijo(0.05, 0)); // caso límite: paso 0
    }

    @Test
    void retoExtra08_restanteTrasPasos() {
        assertEquals(0.01, Ej349GameEngineArchitecture.restanteTrasPasos(0.05, 0.02), 1e-9);
    }

    @Test
    void retoExtra09_serializarMundo() {
        Mundo m = new Mundo();
        m.crearEntidad();
        m.crearEntidad();
        assertEquals("entidades=2", Ej349GameEngineArchitecture.serializarMundo(m));
        assertEquals("entidades=0", Ej349GameEngineArchitecture.serializarMundo(new Mundo()));
    }

    @Test
    void retoExtra10_motorPorLenguaje() {
        assertEquals("Unity", Ej349GameEngineArchitecture.motorPorLenguaje("C#"));
        assertEquals("jMonkeyEngine", Ej349GameEngineArchitecture.motorPorLenguaje("Java"));
        assertEquals("Desconocido", Ej349GameEngineArchitecture.motorPorLenguaje("Cobol")); // caso límite
    }
}
