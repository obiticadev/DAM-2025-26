package com.masterclass.nivel12_crud_create_read;

import java.util.ArrayList;
import java.util.List;

/**
 * EJERCICIO 46: Deserialización Entidad Integral (Mapeo a Domain)
 * 
 * OBJETIVO: 
 * Jamás trabajaremos a novel primitivo volcando la DB al usuario. Un programador real extrae el 'ResultSet' 
 * y re-instancia Entidades Orientadas a Objetos completas y fuertemente tipificadas y controladas, agrupándolas.
 */

// Entidad ficticia del domino.
class UsuarioDTO {
    public int id;
    public String name;
    
    // Hint: Constructor opcional / Override toString para la belleza en el backend de depuración.
    @Override
    public String toString() { return "[" + id + "] " + name; }
}

public class Ejercicio46_MapeoEntidad {

    // TODO 1: Configuración rutinaria persistente.

    // TODO 2: Ajusta radicalmente la asignatura para que este método DEVUELVA estéticamente una tipada `List<UsuarioDTO>` en lugar de void.
    public void extraerMapaIntegro() {
        
        // TODO 3: Inyecta el contenedor paramétrico abstracto genérico interno (List de nuestro tipo T).
        List almacenFinal = new ArrayList();

        // TODO 4: Repite todo el armazón ResultSet seguro iterativo clásico del Try-With-Resources.
        
        // TODO 5: Modifica internamente el `while`. En lugar de Sysouts tontos extrae las variables a
        // letalidad cruda (`int idx = rs.get...; String nom = rs.get...;`). Haz un "new UsuarioDTO", carga datos crudos, e inyéctalos
        // al 'almacenFinal' creado en TODO 3. Retórnalo.

    }

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- EL ORM CASERO NATIVO ---");
        
        // TODO 6 (PRUEBA): Modifica el main e implementa una recolección puramente estática llamando tu nuevo método.
        // Itera la lista paramétrica devuelta. Estarás programando tu primera capa 'repository'.
        
        /* 
        Ejercicio46_MapeoEntidad orm = new Ejercicio46_MapeoEntidad();
        List<UsuarioDTO> listaMagica = orm.extraerMapaIntegro();
        for (UsuarioDTO item : listaMagica) {
            System.out.println("Capa Entidad Mapeada Correctamente: " + item);
        }
        */
    }
}
