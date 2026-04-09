package com.masterclass.nivel17_concurrencia_sql;

/**
 * EJERCICIO 65: El Escudo de Aislamiento (Isolation Levels)
 * 
 * OBJETIVO: 
 * Blindar los entornos ante DirtyReads y Non-RepeatableReads valiéndose 
 * de la arquitectura intrínseca relacional.
 * 
 * APOYO TEÓRICO: 
 * Revisa el archivo '17_Concurrencia_SQL.md' (Sección Isolation ClassDiagram)
 */
public class Ejercicio65_NivelAislamiento {

    public void configurarTransaccionPura() {
        // TODO 1: Extrae paramétricamente la conexión rutinaria.
        // TODO 2: Antes de mutarla a `setAutoCommit(false)`, inyéctale la directriz global de blindaje atómico:
        // Usa '.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE)' para sellarla.
        // TODO 3: Implementa una simulación transaccional con Commit.
    }

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- APLICANDO BARRERAS CLÍNICAS AISLANTES ---");
        
        // TODO 4 (PRUEBA): Arranca el sistema para testear que el Driver de SQLite/JDBC acepta la serialización agresiva.
        // Dato: Algunos motores ligeros como SQLite sufren o mutan ciertos aislamientos a nivel hardware. No te asustes 
        // si ves Warnings de soporte en ciertas máquinas, PostgreSQL en la vida real lo aguantará perfectamente.
    }
}
