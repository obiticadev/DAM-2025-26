package com.masterclass.nivel16_transacciones_acid;

/**
 * EJERCICIO 60: La Caja Fuerte Antimateria (Rollback)
 * 
 * OBJETIVO: 
 * Programar la estructura completa clásica de defensa ante roturas fatales en el código
 * mientras la base de datos se encontraba a medio procesar.
 * 
 * APOYO TEÓRICO: 
 * Diagrama Mermaid bloque '16_Transacciones_ACID.md'
 */
public class Ejercicio60_Commit_Rollback {

    public void compraProductoFalla() {
        
        // TODO 1: Inicia bloque try (Atento, esta vez NO uses try-with-resources. Usa uno clásico crudo `try{}`).
        // Esto se debe a que la instancia general de 'Connection' deberá compartirse lógicamente con el bloque maestro 'catch'.
        /* Connection con = ... */
        
        // TODO 2: Inyecta '.setAutoCommit(false)'.
        
        // TODO 3: Formula en el bloque Try el Statement 1 (Ej. Quitar dinero al usuario y Updatear).
        
        // TODO 4: Finge un error fatal en la JVM. Obliga estáticamente un 'throw new RuntimeException("ERROR DE RED SEVERO");' 
        // que interrumpa letalmente su ruta hacia el "Statement 2" (entregar el producto físico). 
        // Fíngelo y asegúrate de que, matemáticamente, el flujo escupirá el bloque catch sin haber llegado nunca al 'commit()'.
        
        // TODO 5: En el bloque maestro estructurado `catch (Exception e) { ... }`, atrapa a 'Connection' y exígele letalmente 
        // una llamada abstracta forzosa al comando de retroceso: '.rollback()'. (Ojo: tendrás que comprobar si con != null).
        // Por último en el `finally {}` obliga el '.close()' puro.

    }

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- RESISTENCIA EN CONDICIONES EXTREMAS ---");
        
        // TODO 6 (PRUEBA): Imprime logicas y observa. El System.out.println emitido en el catch de tu capa superior
        // testificará con un SysOut ("Falló la transacción, activando la máquina del tiempo Rollback").
        // Has salvado a un banco de entregar 1 millón de euros gratis por falla de red.
    }
}
