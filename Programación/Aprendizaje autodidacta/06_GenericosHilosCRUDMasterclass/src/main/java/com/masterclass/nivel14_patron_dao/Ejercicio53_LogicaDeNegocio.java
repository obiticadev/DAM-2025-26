package com.masterclass.nivel14_patron_dao;

/**
 * EJERCICIO 53: La Capa de Servicio Superior
 * 
 * OBJETIVO: 
 * Implementar el puente con la Interfaz del Usuario. Esta clase aplicará validaciones
 * (Lógica de Negocio) sin tener idea de si usa Oracle, Postgres o SQLite.
 */
// TODO 1: Configurar una capa de Business Logic limpia.
public class Ejercicio53_LogicaDeNegocio {

    // TODO 2: Observa la magia de los patrones.
    // Declaramos estrictamente el uso de la INTERFAZ, y no de la clase cruda inferior SQLiteImpl.
    // private final Ejercicio51_UsuarioDAO repositorio;

    // TODO 3: Crea el constructor e invoca su autoinyección constructiva asimilando
    // la interfaz abstracta provista al motor.
    
    // TODO 4: Desarrolla un método 'procesarRegistroDeUsuario(String nombre)'.
    // Aplica inteligencia superior. Verifica internamente que el string no esté vacío (e.isEmpty).
    // Si la cadena está vacía, arroja una IllegalArgumentException.
    // Si es correcta, empújalo a ciegas instanciando una Entidad y mandándola al 'repositorio.crearUsuario()' local.
    
    // Con este ejercicio has conseguido el santo grial de la programación Backend. Capas limpias.
}
