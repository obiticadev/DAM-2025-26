package com.masterclass.nivel5_excepciones_checked;

import java.util.List;
import java.util.ArrayList;

/**
 * ============================================================================
 *  EJERCICIO 35 — BLOQUE FINALLY (CON GUIA)
 * ============================================================================
 * Lee primero: teoria/05_Excepciones_Checked.md
 */
public class Ejercicio35_FinallyBlock {

    /**
     * Un "recurso" simulado que registra operaciones.
     */
    public static class RecursoSimulado {
        private final List<String> log = new ArrayList<>();
        private boolean abierto = false;

        public void abrir() { abierto = true; log.add("ABIERTO"); }
        public void cerrar() { abierto = false; log.add("CERRADO"); }
        public boolean estaAbierto() { return abierto; }
        public List<String> getLog() { return log; }

        public String operar(String dato) {
            if (dato == null) throw new NullPointerException("dato es null");
            log.add("OPERADO:" + dato);
            return dato.toUpperCase();
        }
    }

    /**
     * TODO: Abre el recurso, opera con el dato, y SIEMPRE cierra el recurso en finally.
     * Devuelve el resultado de operar(), o "ERROR" si hubo excepcion.
     */
    public static String usarRecurso(RecursoSimulado recurso, String dato) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
