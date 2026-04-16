package com.masterclass.nivel11_jdbc_sqlite;

/**
 * EJERCICIO 43: El escudo antidesbordamientos (Try-With-Resources)
 * 
 * OBJETIVO: 
 * Erradicar por completo los ".close()" manuales inseguros de los ejercicios previos.
 * En la arquitectura backend, un simple 'throw new Exception()' anterior a tu .close() impediría que se ejecutase,
 * provocando el terrible Resource Leak que tumba servidores permanentemente.
 * 
 * APOYO TEÓRICO: 
 * Revisa el archivo '11_JDBC_SQLite.md' (Sección: "El protocolo de JVM")
 */
public class Ejercicio43_GestorTryCatch {

    // TODO 1: Configurar constantes transversales para la conexión (URL).

    public void verificarSeguridad() {
        
        // TODO 2: Adapta el Try manual antiguo hacia el novedoso 'Try-With-Resources'.
        // Sintaxis: try (ClaseCerrable r1 = factoria1(); ClaseCerrable2 r2 = factoria2()) { ... }
        // HINT: Mete en la cabecera los métodos 'DriverManager.getConnection' apuntados a 'Connection con'.
        
        // TODO 3: Mete un Sysout confirmando dentro. ¿Ves que ya no necesitas poner `con.close()` en el final?
        // La JVM interceptará las Interfaces "AutoCloseable" inyectadas y las purgará automáticamente al salir de la llave }.
        
    }

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- DEGRADANDO RESOURCE LEAKS ---");
        
        // TODO 4 (PRUEBA): Ejecuta el probador con este nuevo formato estandarizado. Toda tu Masterclass en Bases 
        // de Datos utilizará este mecanismo subyacente. Acostúmbrate a las llaves maestras en try().
        // new Ejercicio43_GestorTryCatch().verificarSeguridad();
    }
}
