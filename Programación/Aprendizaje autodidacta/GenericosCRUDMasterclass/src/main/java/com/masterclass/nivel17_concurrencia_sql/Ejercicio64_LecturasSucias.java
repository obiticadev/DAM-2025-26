package com.masterclass.nivel17_concurrencia_sql;

/**
 * EJERCICIO 64: La Falsa Esperanza (Dirty Reads)
 * 
 * OBJETIVO: 
 * Exponer matemáticamente la anomalía de la "Lectura Sucia".
 */
public class Ejercicio64_LecturasSucias {

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- EL FRAUDE DEL DATO CRITICO ---");
        
        // TODO 1: Configura un Thread B (LectorSucio). 
        // Su misión: Despertar un poco tarde (sleep(100)). Leer a la DB libremente el dato, y alucinar de su valor con un SysOut.

        // TODO 2: Configura el Hilo A (Transaccionador). 
        // Su misión: Abrir conexión, setAutoCommit(false). Mutar el dato en Memoria temporal, per ESPERAR (Sleep 500ms).
        // (Durante esta espera, el hilo B robará el dato virtual no guardado).
        // Finalmente el Hilo A despierta, evalúa error, y hace ROLLBACK.

        // TODO 3 (PRUEBA): Analiza en un test como el Hilo B asume, imprime y regala 500 euros a un cliente (o a tu depurador), y 
        // después se revierte falsamente asumiendo unas discrepancias críticas. En tu vida real esto manda empresas al pozo.
    }
}
