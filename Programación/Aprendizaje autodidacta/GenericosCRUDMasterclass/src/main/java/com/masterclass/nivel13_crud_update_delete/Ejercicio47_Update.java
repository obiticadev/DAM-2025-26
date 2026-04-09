package com.masterclass.nivel13_crud_update_delete;

/**
 * EJERCICIO 47: La actualizacion masiva (UPDATE)
 * 
 * OBJETIVO: 
 * Realizar un UPDATE transversal asumiendo el clásico Statement básico para calentar.
 * 
 * APOYO TEÓRICO: 
 * Revisa el archivo '13_CRUD_Update_Delete.md'
 */
public class Ejercicio47_Update {

    // TODO 1: Configurar variable global universal urlJDBC.

    public void darSueldoGlobal() {
        
        // TODO 2: Configura un try-with-resources encapsulador que retenga un Statement ciego primitivo.
        
        // TODO 3: Define el String de mutación "UPDATE usuarios SET sueldo = 2000".
        // Ejecútalo como ya sabes, valiéndote del método restrictor .executeUpdate(cadena).
        // Loguea el Int de filas afectadas devuelto.

    }

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- ACTUALIZANDO DDBB ---");
        
        // TODO 4 (PRUEBA): Implanta una prueba y analiza el log para descubrir el conteo real
        // de cuántos registros han asumido los 2000 pavos en tu sqlite físico.
    }
}
