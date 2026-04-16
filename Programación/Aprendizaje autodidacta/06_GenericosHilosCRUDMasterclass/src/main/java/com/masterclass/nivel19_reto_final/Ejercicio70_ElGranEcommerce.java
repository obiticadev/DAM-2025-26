package com.masterclass.nivel19_reto_final;

/**
 * EJERCICIO 70: GRAN PROYECTO FINAL - BATTLE ROYALE E-COMMERCE
 * 
 * OBJETIVO ABSOLUTO: 
 * Programar la estabilidad de stock frente a un ataque masivo de miles de peticiones 
 * asíncronas apoyándote en todo lo aprendido: HikariCP, ThreadPools, Transacciones ACID, y Monitores/Locks.
 * 
 * APOYO TEÓRICO: 
 * Revisa el archivo final '19_Reto_Final.md'
 */
public class Ejercicio70_ElGranEcommerce {

    // ==============================================================
    // CONFIGURACIÓN DE TU INFRAESTRUCTURA (TO-DOs GLOBALES)
    // ==============================================================
    
    // TODO 1: (EL MOTOR DATABASE). Crea un método 'inicializarHikariPool()' que devuelva 
    // tu DataSource configurado a sqlite ('masterclass.db'), con max conexiones a 15.
    
    // TODO 2: (LA TABLA). Prepara una query destructora y creadora que asegure que al iniciarse el método 
    // se lance un 'DROP TABLE IF EXISTS stock' y seguido un 'CREATE TABLE stock(items INTEGER, log TEXT)'.
    // Insertale de salida una sola Fila maestra purista con valor '100' items iniciales. Usando tu DataSource.

    // TODO 3: (EL MOTOR CONURRENTE). Crea la variable estática maestra de Hilos (ExecutorService).
    // Fíjala en `newFixedThreadPool(50)` para acoger un golpe brutal de hilos en ráfaga.

    // TODO 4: (EL MONITOR CEREBRAL). Crea instanciado globalmente tu `ReentrantLock` y bautízalo 'candadoAlmacen'.

    // ==============================================================
    // LA LÓGICA CORE (TRANSACCIONAL Y CONCURRENTE)
    // ==============================================================
    
    // TODO 5: Despliega la función `comprarItem(HikariDataSource db)`. Esta es la que será bombardeada.
    /*
        Pasos del cuerpo estricto interior:
        1. Adquiere el lock (`.lock()`)
        2. Configura un try-with-resources. Coge una conexion del Hikari (db.getConnection())
        3. Configura `setAutoCommit(false)` para salvar tu BBDD.
        
        -- LOGICA SQL --
        4. SELECT items FROM stock.
        5. Si items > 0 -> Haces un UPDATE stock SET items = items - 1. (Imprimes "VENDIDO!")
        6. Si items <= 0 -> Haces rollback si hiciste algo, y Throw Exception "Sin Stock".
        
        -- SALVAGUARDAS --
        7. Pasa a `commit()`
        8. CIERRA ESTRICTAMENTE CON `.unlock()` en un bloque puro final "finally {}" de the method principal.
    */

    // ==============================================================
    // ZONA DE EJECUCIÓN MASTERS (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- INICIANDO BLACK FRIDAY ---");
        
        // TODO 6 (EL ESTRESADOR GLOBAL): 
        // 1. Enciende HikariCP guardado en variable.
        // 2. Ejecuta instanciador de Tabla.
        // 3. Escribe un `for (int i=0; i<300; i++)` (Vas a lanzar 300 peticiones de compra cuando sólo hay 100 items).
        // 4. Dentro del For forjado, lanza un `.submit(() -> { tuMetodoComprarItem(hikariDB) })` a tu Executor.
        // 5. Fuerza un letargo 'shutdown()' final y reza para que la arquitectura compile viva.

        // PRUEBA DEFINITIVA: 
        // Lanza y observa. Verás compras explotar e imprimirse. Cuando el stock llegue a 0 en BBDD, los otros 200 hilos 
        // rebotarán como locos lanzando la Exception "Sin Stock". 
        // ¿El saldo SQL en local de este bombardeo? El Stock bajó de 100 a exactamente 0. Cero inconsistencias.  
        // ¡ENHORABUENA!. ESTÁS OFICIALMENTE CERTIFICADO COMO DESARROLLADOR JAVA ADVANCED MULTIHILO Y DB POR LA COMUNIDAD.
    }
}
