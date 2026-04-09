package com.masterclass.nivel11_jdbc_sqlite;

/**
 * EJERCICIO 42: Abstracción de Estructuras (Statement de Creación DDL)
 * 
 * OBJETIVO: 
 * Transmitir el primer pulso real en sintaxis de Base de Datos para materializar  
 * una tabla ('Entidad') con propiedades que nos permita almacenar nuestros objetos de backend.
 */
// TODO 1: Importa estáticamente toda la API transversal de SQL (java.sql.*).
public class Ejercicio42_CreacionTabla {

    // TODO 2: Establece la URL constante de tu BBDD al archivo del paso previo.

    public void ejecutarEstructuraOriginal() {
        
        // TODO 3: Configura un string multilínea (Las triple comillas """" de Java 15+) guardando tu Query SQL DDL pura.
        // HINT: Una típica es -> CREATE TABLE IF NOT EXISTS usuarios ( id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL );
        
        // TODO 4: Abre un entorno Try/Catch interceptando SQLExceptions.
        
        // TODO 5: Para disparar Queries SQL, necesitas que el 'Connection' inyecte un 'Statement'.
        // Instancia un Statement con la orden (conexion.createStatement()) y usa '.execute(tuQueryString)'.
        // Cierra ambos recursos a mano (.close).

    }

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- FORJANDO TABLAS ---");
        
        // TODO 6 (PRUEBA): Corrobora la ejecución sintáctica exacta para inyectar este Create Statement. 
        // Una vez ejecutados, el archivo de texto .db adquirirá un poco más de peso en bytes.
        // Ejercicio42_CreacionTabla forjador = new Ejercicio42_CreacionTabla();
        // forjador.ejecutarEstructuraOriginal();
    }
}
