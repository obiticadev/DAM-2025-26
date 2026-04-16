package com.masterclass.nivel15_dao_generico;

/**
 * EJERCICIO 57: La Capa de Implementación Física (`Extends DAO<T>`)
 * 
 * OBJETIVO: 
 * Plasmar tu primera entidad hija real valiéndose de las miles de líneas de código SQL crudo blindado 
 * que residen gratis en las tripas de la clase Padre Genérica.
 * 
 * APOYO TEÓRICO: 
 * Diagrama Mermaid en '15_DAO_Generico.md'.
 */

// Entidad ficticia DB
class ProductoDB { public Long idProd; public String nombreProd; }

// TODO 1: Obliga en la firma a extender de tu DAO Matrix del ejercicio 55 (`extends Ejercicio55_Abstract...`).
// Asegura que en la flecha de instanciación del diamante indiques `<ProductoDB>`.
public class Ejercicio57_DaoEspecifico {

    // TODO 2: Arregla los errores en VS Code usando el bombillo de sugerencia e implementa 
    // el método abstracto puro 'getNombreTabla()' proveniente del padre.
    
    // TODO 3: Modifica el string dictando el retorno como "catalogo_productos" (Tu tabla en HDD real ficticia).

    // TODO 4: Agrega un método local específico a mayores "buscarPorAtributosEspeciales()". 
    // Nota que, al heredar, ganas gratis el "borrar()" y "buscarTodos()" del DAO genérico padre,
    // y aquí solo programas SQL hiper-específico de este caso de uso concreto (Ej. Queries avanzadísimas JOIN).
    
    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- EL GUERRERO GENÉRICO HEREDA LA FUERZA ---");
        
        // TODO 5 (PRUEBA): Instancia en esta capa al ProductoDao. 
        // Comprueba invocándolo cómo `.eliminar(2)` aparece mágicamente accesible.
    }
}
