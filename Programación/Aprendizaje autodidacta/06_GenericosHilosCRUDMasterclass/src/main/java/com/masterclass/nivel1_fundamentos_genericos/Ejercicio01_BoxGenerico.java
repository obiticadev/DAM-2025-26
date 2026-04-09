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
public class Ejercicio01_BoxGenerico<T> {

    private T contenido;

    public Ejercicio01_BoxGenerico() {
    }

    public Ejercicio01_BoxGenerico(T contenido) {
        this.contenido = contenido;
    }

    public T getContenido() {
        return contenido;
    }

    public void setContenido(T contenidoNuevo) {
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
