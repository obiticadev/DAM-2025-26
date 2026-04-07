package com.masterclass.nivel8_try_with_resources;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================================
 *  EJERCICIO 57 — AUTOCLOSEABLE BASICO (CON GUIA)
 * ============================================================================
 * Lee primero: teoria/08_Try_With_Resources.md
 */
public class Ejercicio57_AutoCloseableBasico {

    public static class ConexionSimulada implements AutoCloseable {
        private boolean abierta = false;
        private final List<String> log = new ArrayList<>();

        public void abrir() { abierta = true; log.add("ABIERTA"); }
        @Override public void close() { abierta = false; log.add("CERRADA"); }
        public boolean estaAbierta() { return abierta; }
        public List<String> getLog() { return log; }
        public String ejecutar(String sql) {
            if (!abierta) throw new IllegalStateException("Conexion no abierta");
            log.add("SQL:" + sql);
            return "Resultado de: " + sql;
        }
    }

    /**
     * TODO: Usa try-with-resources para abrir la conexion, ejecutar el sql
     * y garantizar que se cierre. Devuelve el resultado del sql.
     */
    public static String ejecutarSQL(ConexionSimulada conexion, String sql) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
