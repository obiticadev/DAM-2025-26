package com.masterclass.nivel15_dao_generico;

/**
 * EJERCICIO 55: El Contrato Abierto Principal (Abstract DAO)
 * 
 * OBJETIVO: 
 * Fusionar Patrón DAO con Genéricos forzando al arquitecto a crear una base sólida
 * que admita Entidades abstractas para delegar todo el código repetitivo en un solo lugar.
 * 
 * APOYO TEÓRICO: 
 * Revisa el archivo '15_DAO_Generico.md' (Sección: "Arquitectura de la Plantilla")
 */
// TODO 1: Configura la aserción paramétrica '<T>' en la asignatura general de la clase.
// Transmuta la clase a formato 'abstract' para obligar al ecosistema a heredarla.
public class Ejercicio55_Abstract_DAO {

    // TODO 2: Declara el contrato limitante visual de puente a hijos. 
    // Redacta un método de firma 'protected abstract String getNombreTabla();'
    // El padre jamás sabrá qué tabla consultar, los hijos le darán este dato en Runtime.
    
    // TODO 3: Implementa la plantilla de borrado generalista (Método 'eliminar(int id)').
    // Hint: Usa el try with resources, el Connection, el PreparedStatement...
    // IMPORTANTE: En el string del SQL inyecta condicionalmente de forma dinámica el método que pusiste en TODO 2:
    // String sql = "DELETE FROM " + getNombreTabla() + " WHERE id = ?";
    
    // TODO 4: Termina el 'eliminar()'.
    // Date cuenta del inmenso poder de lo logrado: Tienes 1 único método capaz de eliminar registros de 
    // cualquier tabla (Usuarios, Productos, Autos) de tu empresa, sin reescribir ni una coma de SQL más.
    
    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- BASE DAO ABSTRACTA CREADA ---");
        
        // TODO 5 (PRUEBA): Implanta el motor y corre este dummy main simplemente
        // para corroborar que tu clase pasa el filtro de compilación sin errores sintácticos.
        // No instanciarás nada aquí (es abstract).
    }
}
