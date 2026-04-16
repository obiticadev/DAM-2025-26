package com.masterclass.nivel2_metodos_genericos;

/**
 * EJERCICIO 7: Constructores Genéricos en Clases Estáticas
 * 
 * OBJETIVO: 
 * Inyectar polimorfismo de tipos directamente en el bloque inicializador (constructor) 
 * de una entidad, sin comprometer el tipado global de la clase en el sistema.
 * 
 * APOYO TEÓRICO: 
 * Revisa el archivo '02_Metodos_Genericos.md' (Sección: "Constructores Genéricos")
 */
public class Ejercicio07_ConstructorGenerico {
    
    private String logFinal;

    // TODO 1: Configura la firma de acceso del constructor para invocar la abstracción métodica de genéricos.
    // TODO 2: Asegúrate de que el argumento puente que inicializa la clase acate la restricción del paso 1.
    public Ejercicio07_ConstructorGenerico(Object cargaDeAtaque) {
        
        // TODO 3: Altere la inyección lógica inferior. Intenta explorar otros metodos de tu objeto
        // si lograste configurar un tipo seguro en la firma superior.
        this.logFinal = "[" + cargaDeAtaque.getClass().getSimpleName() + "] - " + cargaDeAtaque.toString();
        System.out.println("Trace generado con éxito: " + this.logFinal);
    }

    // TODO 4: Verifica internamente que los identificadores de acceso no requieren Genéricos 
    // si el propósito es solo lectura del 'estado final stringificado'.
    public String getLogFinal() {
        return logFinal;
    }

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- LOGGING INFERENCE SYSTEM ---");
        
        // TODO 5 (PRUEBA): Modifica los datos inyectados libremente y comprueba mediante ejecución (Run) 
        // que Java resuelve el tipado en runtime perfectamente sin alterar la clase.

        System.out.println("1. Abriendo log String...");
        new Ejercicio07_ConstructorGenerico("Server is down!");

        System.out.println("\n2. Abriendo log Number...");
        new Ejercicio07_ConstructorGenerico(404);

        System.out.println("\n3. Abriendo log Memoria...");
        new Ejercicio07_ConstructorGenerico(new byte[]{1,2,3,4});
    }
}
