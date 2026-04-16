package com.masterclass.nivel12_crud_create_read;

/**
 * EJERCICIO 44: Sembrando el CAOS (El primer CREATE)
 * 
 * OBJETIVO: 
 * Realizar la primera inyección constructiva transversal ('INSERT INTO') hacia el archivo 
 * físico SQLite utilizando sentencias estáticas y el retorno de ejecución puro.
 * 
 * APOYO TEÓRICO: 
 * Revisa el archivo '12_CRUD_Create_Read.md' (Sección: "Insertando la Carga")
 */
// TODO 1: Configuración habitual. Inyecta todo SQL y configura tu constante URL base para el try().
public class Ejercicio44_InsertBasico {

    public void insertarUsuarioCiego() {
        // TODO 2: Predefine internamente el string crudo multilínea que vas a lanzar.
        // HINT: 'INSERT INTO usuarios (nombre) VALUES ("AdminSupremo");'
        
        // TODO 3: Despliega un bloque 'try-with-resources' requiriendo únicamente el 'Connection'.
        
        // TODO 4: Dentro del try seguro, insta a la clase Connection a parir un 'Statement' ciego, 
        // y ejecuta violentamente a este la carga con `.executeUpdate(...)`.
        
        // TODO 5: Guarda la respuesta de éxito atómica (el famoso int numModificadas) y lóguealo (sysout).
        
    }

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- PROYECTIL DE INYECCIÓN ---");
        
        // TODO 6 (PRUEBA): Corre el sistema. Comprobarás que si logueas "Filas Modificadas: 1", significa
        // que tu comando SQL fue impecable. Y lo más impactante, si cierras el programa y lo vuelves a ejecutar,
        // ¡te imprimirá otra fila nueva persistiendo la historia por siempre!.
        
        // new Ejercicio44_InsertBasico().insertarUsuarioCiego();
    }
}
