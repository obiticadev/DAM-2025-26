package com.masterclass.nivel13_crud_update_delete;

/**
 * EJERCICIO 49: El Simulacro Hacker (Inyección SQL)
 * 
 * OBJETIVO: 
 * Experimentar por tus propios medios los peligros del Statement asíncrono concatenado.
 * 
 * APOYO TEÓRICO: 
 * Revisa el archivo '13_CRUD_Update_Delete.md' (Sección: Lado Oscuro)
 */
public class Ejercicio49_InyeccionSQL {

    // TODO 1: Asigna ruta JDBC.

    public void loginInseguro(String inputDelUsuario) {
        
        // TODO 2: Abre la conexión cata-clásica al DB con Statement ciego.
        
        // TODO 3: Monta la directriz vulnerada de esta forma exhaustiva (Copia y pega):
        // String q = "SELECT * FROM usuarios WHERE nombre = '" + inputDelUsuario + "'";
        
        // TODO 4: Imprime el 'q' al completo ANTES de ejecutarlo.
        // Ejecuta el query con el Result Set limitador, y comprueba con '.next()' 
        // y un IF si el usuario logró pasar la autenticación y loggéalo.

    }

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- EL HACKEADOR ---");
        
        Ejercicio49_InyeccionSQL server = new Ejercicio49_InyeccionSQL();
        
        // TODO 5 (PRUEBA): Si mandas el nombre "Ana", no entrará, ya que no existe en BD supongo.
        
        // TODO 6 (EL HACK): Llama a '.loginInseguro("x' OR '1'='1");'
        // Contempla con absoluto pavor cómo el servidor se autoconvence de la autenticación saltando tu sistema.
    }
}
