package com.masterclass.nivel16_transacciones_acid;

/**
 * EJERCICIO 59: Rompiendo las Automatizaciones (AutoCommit = false)
 * 
 * OBJETIVO: 
 * Inhibir la guardada directa por orden de JDBC. Tomar el control manual transaccional.
 * 
 * APOYO TEÓRICO: 
 * Revisa archivo '16_Transacciones_ACID.md' (Sección: La Regla Atómica)
 */
public class Ejercicio59_AutoCommitFalse {

    // TODO 1: Configura tus URL de conexión transversal cruda simulada.

    public void transferirBolsaValores() {
        // TODO 2: Inicia un Try con recursos enlazado a un genérico Driver Manager explícito.
        
        // TODO 3: Extrae imperativamente a tu Connection el comando '.setAutoCommit(false)'.
        // Estás declarando el estado de emergencia estanco, a partir de ahora, todo es virtual.
        
        // TODO 4: Emite dos statements `.executeUpdate(...)` fingidos ("UPDATE clientes SET bolsa = 0") en secuencia recta.
        
        // TODO 5: Tras culminar la carga abstracta de las sentencias asincronas simuladas, sella firmemente 
        // la transferencia exigiendo forzosamente la mutación de hardware real mediante 
        // una llamada explícita al comando definitivo: 'conexion.commit()'
    }

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- PRIMERA DECLARACIÓN ACID ---");
        
        // TODO 6 (PRUEBA): Corrobora la lógica transaccional. En esta simulación sin errores todo fluiría al commit asíncrono.
        // Ejercicio59_AutoCommitFalse atom = new Ejercicio59_AutoCommitFalse();
        // try { atom.transferirBolsaValores(); } catch(Exception e){}
    }
}
