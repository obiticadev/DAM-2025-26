package com.masterclass.nivel11_jdbc_sqlite;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * EJERCICIO 41: Primer Contacto con la Matrix (El Driver JDBC)
 * 
 * OBJETIVO: 
 * Forzar el alta arquitectónica del motor subyacente. Descubrirás que el IDE puede
 * generar archivos reales en tu disco a raíz de peticiones abstractas.
 * 
 * APOYO TEÓRICO: 
 * Revisa el archivo '11_JDBC_SQLite.md' (Sección: "La Conexión Base")
 */
// TODO 1: Esta clase de servicio requiere una dirección de puntero o ruta de URL para saber a dónde apuntar.
public class Ejercicio41_ConexionDriver {

    // TODO 2: Declara el string constante URL apuntando al protocolo JDBC, al protocolo SQLite, y 
    // bautiza al archivo como 'masterclass.db'.
    private static final String MI_URL = "ruta...";

    // TODO 3: Empaqueta la arquitectura arriesgada con the obligatory Try y Catch cazando 'SQLException'.
    // HINT: En su interior invoca a 'DriverManager.getConnection(...)' y asignalo a un objeto genérico 'Connection'.

    public void probarEnlace() {
        // ... (Tu bloque Try aquí)
        // Imprime un Sysout con "Enlace establecido y BBDD generada."
        // CIERRA MANUALMENTE obligatoriamente con '.close()' la variable connection antes de acabar.
    }

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- PRUEBA DE TOMA DE CONTACTO JDBC ---");
        
        // TODO 4 (PRUEBA): Instancia tu probador y ejecuta el método. Si tu URL JDBC está bien escrita,
        // no solo verás la respuesta en la consola. Un archivo llamado 'masterclass.db' va a materializarse 
        // mágicamente en la solapa de archivos de tu VS Code a la izquierda. Eso es persistencia real.
        /* 
        Ejercicio41_ConexionDriver enlace = new Ejercicio41_ConexionDriver();
        enlace.probarEnlace();
        */
    }
}
