package com.masterclass.nivel8_try_with_resources;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================================
 *  EJERCICIO 62 — DECORADOR CON CLOSEABLE (SIN GUIA)
 * ============================================================================
 */
public class Ejercicio62_DecoradorCloseable {

    public interface Logger extends AutoCloseable {
        void log(String msg);
        List<String> getLogs();
    }

    /**
     * TODO: Implementa un Logger que al cerrarse añade "--- LOG CERRADO ---" a los logs.
     */
    public static Logger crearLogger() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Implementa un Logger decorador que convierte todo a MAYUSCULAS y delega al logger base.
     * Al cerrarse, cierra tambien el logger base.
     */
    public static Logger crearLoggerMayusculas(Logger base) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
