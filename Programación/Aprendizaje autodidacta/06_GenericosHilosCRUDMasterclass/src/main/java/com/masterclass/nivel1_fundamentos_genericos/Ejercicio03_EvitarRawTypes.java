package com.masterclass.nivel1_fundamentos_genericos;

import java.util.ArrayList;
import java.util.List;

/**
 * EJERCICIO 3: Evitando Raw Types
 * 
 * OBJETIVO:
 * Identificar y purgar la peor vulnerabilidad posible de las colecciones 
 * en una Pull Request corporativa: el enmascaramiento "Type Erasure" inadvertido.
 * 
 * APOYO TEÓRICO: 
 * Revisa el archivo '01_Fundamentos_Genericos.md' (Sección: "Beneficios contra los Raw Types")
 */
public class Ejercicio03_EvitarRawTypes {

    public List obtenerListaNombresSegura() {
        
        // TODO 1: Ajusta la firma de esta declaración para garantizar inyección estricta de Cadenas de Texto.
        // TODO 2: Configura la instanciación de la derecha empleando el operador Diamante de Java 7+.
        List nombres = new ArrayList();
        
        nombres.add("Ana");
        nombres.add("Juan");
        nombres.add("Maria");
        
        // TODO 3: Comprende por qué la siguiente línea compila ahora y destrúyela una vez
        // hayas aplicado correctamente la regla en TOD0 1 y 2, ya que dejará de compilar.
        nombres.add(42); 

        // TODO 4: Ajusta el tipo de retorno del método superior para reflejar la seguridad ganada.
        return nombres;
    }

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- CAZANDO RAW TYPES ---");
        
        // TODO 5 (PRUEBA): Modifica el tipo devuelto aquí también. Ejecuta para validar.
        Ejercicio03_EvitarRawTypes core = new Ejercicio03_EvitarRawTypes();
        List resultadoRaw = core.obtenerListaNombresSegura();
        
        System.out.println("Nombres extraidos con seguridad: " + resultadoRaw.toString());
    }
}
