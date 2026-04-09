package com.masterclass.nivel18_hikaricp_pool;

/**
 * EJERCICIO 69: Benchmarking (El Desafío al Milisegundo)
 * 
 * OBJETIVO: 
 * Repetir matemáticamente la macro-inyección de 500 transacciones del Ejercicio 67
 * pero delegándola enteramente a lo desarrollado durante el Nivel 18.
 */
public class Ejercicio69_HikariBenchmarking {

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- HIKARI CP: DESTRUYENDO A LA MATEMATICA CRUDA ---");
        
        // TODO 1: Evoca a 'Ejercicio68_HikariConfig.proveerCentralDatos()' para retener el generador DataSouce activo asíncrono.
        
        // TODO 2: Inyecta y asienta el long 'System.currentTimeMillis();' base.
        
        // TODO 3: Despliega un For-loop inmenso de 500 vueltas equivalente. 
        // Adentro exige puramente 'var c = factoriaCentralDatosHikariHikariDataSource.getConnection()' .
        // ¡OJO! Esto está robando temporalmente la conexión del Pool pre-iniciado y teñirá tu objeto de verde.
        // Lanza tu SELECT y enciérralo en try con resources para que sea Purgado (Dejado Iddle) en menos de 1 milisegundo al acabar 
        // a traves del 'close()'.

        // TODO 4: Emite tu SysOut total. "Tiepo Hikari: x milis".

        // TODO 5 (PRUEBA): Corrobóralo y abre la mandíbula. Si todo fue impecable, este tiempo frente
        // al Ejercicio 67 será desmesuradamente ínfimo, probando por qué jamás debes obviar esta tecnología 
        // en desarrolladores Spring/Jakarta de un solo nodo.
    }
}
