package com.masterclass.nivel1_fundamentos_genericos;

/**
 * EJERCICIO 1: Tu Primera Caja Genérica
 * 
 * OBJETIVO:
 * Esta clase representa una arquitectura antigua y frágil en Java. Tu objetivo
 * es refactorizarla completamente implementando el Paradigma Parametrizado
 * 
 * APOYO TEÓRICO: 
 * Revisa el archivo '01_Fundamentos_Genericos.md' (Sección: "Solución: El Paradigma Parametrizado")
 */
// TODO 1: Configura la firma de la clase para que acepte un Tipo parametrizado genérico universal.
public class Ejercicio01_BoxGenerico {

    // TODO 2: Refactoriza la declaración de este estado interno para vincularlo al parametrismo.
    private Object contenido;

    // TODO 3: Implementa un constructor por defecto para que podamos crear la caja vacía.
    
    // TODO 4: Garantiza, a nivel de firma, que obtener el contenido nunca requiera un "Casteo"
    // devolviendo el tipo que se haya declarado en la instanciación de la clase.
    public Object obtenerContenido() {
        return contenido;
    }

    // TODO 5: Asegura la integridad para que el setter solo admita elementos del tipo declarado.
    public void cambiarContenido(Object contenidoNuevo) {
        this.contenido = contenidoNuevo;
    }

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- PROBANDO BOX GENÉRICO ---");
        
        // TODO 6 (PRUEBA): Cuando hayas estructurado la clase genérica, quita los comentarios inferiores.
        // Utiliza este entorno para crear cajas de 'String', 'Double' e interactuar con ellas
        // usando el botón 'Run' en tu VS Code.
        
        /*
        Ejercicio01_BoxGenerico<String> cajaSecreta = new Ejercicio01_BoxGenerico<>();
        cajaSecreta.cambiarContenido("Palabra Clave");
        System.out.println("Guardado en la caja de Strings: " + cajaSecreta.obtenerContenido());
        
        // Prueba a meter un Int en la caja de Strings y asegúrate de que el compilador te bloquea.
        // cajaSecreta.cambiarContenido(500); 
        */
    }
}
