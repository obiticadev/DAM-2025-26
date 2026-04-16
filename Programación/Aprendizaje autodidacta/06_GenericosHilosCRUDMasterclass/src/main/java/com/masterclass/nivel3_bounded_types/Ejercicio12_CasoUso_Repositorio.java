package com.masterclass.nivel3_bounded_types;

/**
 * EJERCICIO 12: Arquitectura del Real-World Repositorio Estático
 * 
 * OBJETIVO: 
 * Empalmar genéricos dentro de un dominio DDBB funcional. Restringiremos a T 
 * a la estructura de clases del ecosistema local (ej. que T sea exclusivamente EntidadDDBB).
 * 
 * APOYO TEÓRICO: 
 * Unifica e instaura el principio lógico de límite superior (<T extends UpperBound>) aplicándolo sobre EntidadDDBB.
 */

// Las abstracciones de las Bases de Datos simuladas
class EntidadDDBB {
    public Long id;
}

class Usuario extends EntidadDDBB {
    public String correo;
}

class Coche {
    public String motor; // No pertenece al dominio relacional 'EntidadDDBB' local
}

// TODO 1: Configura la directiva paramétrica para que T limite todo su árbol en contra de 'EntidadDDBB' en exclusiva.
public class Ejercicio12_CasoUso_Repositorio {

    // TODO 2: Transfiere el tipo de parámetro a tu configuración restringida paramétrica.
    public void guardarEnBaseDeDatos(Object entidad) {
        
        // TODO 3: Quema la alerta de casteo y suprime los errores. Extrae el atributo '.id' directamente desde entidad, 
        // aprovechando la certeza que te ha brindado implementar exitosamente el TODO 1.
        
        // System.out.println("Ejecutando COMMIT SQL... ID: " + entidad.id);
    }

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- PRUEBA REAL DAO ARCHITECTURE ---");
        
        // TODO 4 (PRUEBA): Evalua cómo el sistema absorbe exitosamente al usuario dado que hereda del límite,
        // pero rechaza fuertemente al coche al no pertenecer al ecosistema (Entity Base).
        
        /*
        Ejercicio12_CasoUso_Repositorio<Usuario> repoUsuarios = new Ejercicio12_CasoUso_Repositorio<>();
        Usuario u = new Usuario();
        u.id = 777L;
        u.correo = "admin@mail.com";
        repoUsuarios.guardarEnBaseDeDatos(u);
        */

        // Intenta descomentar lo siguiente y observar los problemas en VS Code:
        // Ejercicio12_CasoUso_Repositorio<Coche> repoErrores = new Ejercicio12_CasoUso_Repositorio<>();
    }
}
