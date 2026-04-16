package com.masterclass.nivel15_dao_generico;

/**
 * EJERCICIO 56: Contratos Complejos (Rehidratación Asíncrona)
 * 
 * OBJETIVO: 
 * Entender por qué leer datos generales (`SELECT *`) asumiendo el genérico abstráctamente `<T>` en SQL
 * requiere del famoso patrón "Mapper" en la firma del padre para evitar el colapso del Type Erasure.
 */
public class Ejercicio56_ReflectionTable {

    // (Obviamos los extends aquí por practicidad ilustrativa)
    
    // TODO 1: Plantea la firma asíncrona general `public List<T> buscarTodos()`. (Implanta el Genérico T global superior en la clase antes).
    
    // TODO 2: Asume la lógica mental de ese 'buscarTodos'. 
    // Haces un `SELECT * FROM " + getNombreTabla()`. 
    // Lanzas el ResultSet (`rs.next()`), pero... ¿cómo creas el objeto Java y le metes los datos de las columnas si T no existe en runtime?
    
    // TODO 3: Modifica la firma 'buscarTodos()'. Para no morir ante Type Erasure, pide como 
    // Argumento funcional al programador en Runtime que te pase la función inyectora de rehidratación:
    // Pide un parámetro generalista: `public List<T> buscarTodos(MapperDeTabla<T> mapper)`.
    
    // TODO 4: Declara debajo de esta clase, una Interfaz Funcional externa limpia `public interface MapperDeTabla<T>` 
    // con un método puro interior `T mapear(ResultSet rs);`.

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- RESOLVIENDO TYPE ERASURE EN DAO READS ---");
        
        // TODO 5 (PRUEBA): Corrobora la compilación de la lógica delegativa en SQL.
        // El usuario inyectará la logica de instanciación manual hacia tu DAO padre por lambdas salvando el ecosistema!
    }
}
