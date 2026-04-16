package com.masterclass.nivel14_patron_dao;

/**
 * EJERCICIO 52: La Implementación Sudorosa 
 * 
 * OBJETIVO: 
 * Escribir todo el PreparedStatement blindado ensuciándonos 
 * las manos sin que la capa superior se entere.
 */
// TODO 1: Exígele a esta clase que acate rigurosamente el contrato que forzaste (`implements Ejercicio51_UsuarioDAO`).
public class Ejercicio52_UsuarioDAOImpl {

    // TODO 2: Configura la URI constante hacia tu masterclass.db para no perderte.

    // TODO 3: Al hacer implements, VSCode te dará error. Haz clic en "Añadir métodos no implementados".
    
    // TODO 4: De las 5 funciones volcadas, sólo exijo que te machaques redactando dos aquí (por tiempo de tu estudio):
    // El 'buscarPorId' (Necesitarás un Select y un PreparedStatement limitando a WHERE id = ?)
    // Y el 'crearUsuario' (El INSERT INTO clásico con paramétricos ? y el executeUpdate).

    // Deja el resto de métodos sobreescritos vacíos por ahora devolviendo null.

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- AISLAMIENTO DE DAO ---");
        
        // TODO 5 (PRUEBA): Instancia el DAO en una variable de su interfaz general.
        // Ej: Ejercicio51_UsuarioDAO repo = new Ejercicio52...()
        // Ejecuta repo.crear(...) mandando una entidad artificial nueva.
    }
}
