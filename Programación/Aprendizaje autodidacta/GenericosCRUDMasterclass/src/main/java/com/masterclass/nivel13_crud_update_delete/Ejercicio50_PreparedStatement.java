package com.masterclass.nivel13_crud_update_delete;

/**
 * EJERCICIO 50: El Blindaje Absoluto (PreparedStatement)
 * 
 * OBJETIVO: 
 * Purificación del modelo de datos mutador de Java. Evitar cualquier posible Inyección.
 * 
 * APOYO TEÓRICO: 
 * Revisa el archivo '13_CRUD_Update_Delete.md' (Sección: PreparedStatement)
 */
public class Ejercicio50_PreparedStatement {

    // TODO 1: Rutina de constantes.

    public void loginExtremoSeguro(String inputAislado) {
        
        // TODO 2: Cuidado: Ya NO puedes invocar puramente el `Try()` con variables vacías instanciadoras.
        // El nuevo método pre-armado es 'Connection.prepareStatement(StringDuroAQUI)'.
        // Tu string SQL será (Literalmente, con interrogaciones): "SELECT * FROM usuarios WHERE nombre = ?"
        // Inyecta ese String dentro del try `try(C con=..; PreparedStatement ps = con.prepareStatment(Q))`
        
        // TODO 3: Acabas de blindar la Base de Datos. Ahora tienes que rellenar el agujero (?). En la primera línea
        // del Try invoca `ps.setString(1, inputAislado);` para incrustarle asépticamente su parámetro por índice(El ID=1).
        
        // TODO 4: Emite `ps.executeQuery()` (SIN PARÁMETROS porque ya tiene su String acorazado), iterando el ResultSet
        // exacto como el ejercicio previo e indicando un logro o fracaso.

    }

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- EL MURO DE FUEGO ---");
        
        // TODO 5 (PRUEBA): Inyecta exactamente el mismo parámetro hacker del Ej. 49.
        // "x' OR '1'='1"
        // Si lo has hecho bien, Java encapsulará las vulnerabilidades entre comillas simples pasivas,
        // buscando a un ciudadano que, literalmente, se llame  "x' OR '1'='1" y denengándole el acceso.
        // Eres un Ingeniero Backend Seguro oficialmente.
    }
}
