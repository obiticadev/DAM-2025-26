package com.masterclass.nivel17_concurrencia_sql;

/**
 * EJERCICIO 63: Paralización por Candado Subyacente (Row Locking)
 * 
 * OBJETIVO: 
 * Comprobar y forzar el bloqueo en base de datos.
 * 
 * APOYO TEÓRICO: 
 * Revisa el archivo '17_Concurrencia_SQL.md' (Sección: "Bloqueos a nivel de Fila")
 */
// TODO 1: Crea el andamiaje del hilo (ej. "HiloMutador"). Él debe conectarse, hacer setAutoCommit(false), 
// Llenar un Statement haciendo un Update en X tabla, y ECHARSE A DORMIR 3000 ms ANTES del commit().
public class Ejercicio63_BloqueoDeFilas {

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- EL COLAPSO SILENCIOSO DEL DISCO ---");
        
        // TODO 2: Lanza el HiloMutador (arranca = lock).

        // TODO 3: Sin dormar al main, que este código central intente crear SU PROPIA 
        // connection independiente, hacia el mismo archivo de BBDD, e intente 
        // lanzar el MISMO UPDATE sobre la misma fila con 'executeUpdate()'.
        
        // TODO 4 (PRUEBA): Imprime log al final del main de "Main logró mutar SQL". 
        // Ejecuta y comprueba cómo el sysout tardará un mínimo de 3 largos segundos en salir 
        // escupido, pues BBDD lo frenó forzosamente a nivel hardware.
    }
}
