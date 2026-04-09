package com.masterclass.nivel13_crud_update_delete;

/**
 * EJERCICIO 48: Purgando Registros (DELETE)
 * 
 * OBJETIVO: 
 * Purga de base de datos asumiendo filtrados.  
 */
public class Ejercicio48_Delete {

    // TODO 1: Configurar variable global universal urlJDBC.

    public void eliminarPorID(int idAExpulsar) {
        
        // TODO 2: Altere la inyección genérica abriendo tu Connection y tu Statement.
        
        // TODO 3: Concatena (A la vieja y peligrosa usanza) una directriz SQL que dicte:
        // DELETE FROM usuarios WHERE id = " lo inyectado ".
        
        // TODO 4: Lanza executeUpdate. Acuerdate de loguear la repuesta entera para debug.

    }

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- DELETE SQL ---");
        
        // TODO 5 (PRUEBA): Elimina el Root con id 1. Intenta arrancar este proceso 3 veces seguidas.
        // ¿Verás como la segunda y la tercera el update te avisa tirando un CERO matemático? (Ninguna
        // fila modificada porque ya no existe).
    }
}
