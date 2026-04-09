package com.masterclass.nivel5_avanzados;

/**
 * EJERCICIO 19: Workaround - Arrays de Genéricos
 * 
 * OBJETIVO:
 * Estudiar la prohibición absoluta de `new T[]`. El objetivo es implementar el "hack" arquitectónico 
 * clásico que usa el código base de ArrayList.java internamente para saltarse esta norma.
 * 
 * APOYO TEÓRICO: 
 * Revisa '05_Genericos_Avanzados.md'
 */
// TODO 1: Haz que la clase defina su parámetro genérico estanco en la cabecera.
public class Ejercicio19_ArraysGenericos {

    // TODO 2: Configura el tipo Array para guardar datos paramétricos.
    private Object[] estructura;
    private int limite;

    public Ejercicio19_ArraysGenericos(int limite) {
        this.limite = limite;
        
        // TODO 3: La siguiente línea daría un 'Generic Array Creation Error' si la usas con T
        // this.estructura = new T[limite];
        
        // Implementa el 'hack' nativo: Inicializa el array usando puramente Object[] y 
        // fuérzalo (Cast) al array del tipo genérico. Esto generará un warning crudo.
        this.estructura = new Object[limite]; 
    }

    // TODO 4: Emite el genérico resuelto en esta firma. Considera que tendrás que inyectar
    // (Cast) manualmente sobre el dato crudo almacenado.
    public Object obtenerDato(int indice) {
        return estructura[indice];
    }

    public void meterDato(int indice, Object dato) { // TODO 5: Fija el tipo del argumento
        estructura[indice] = dato;
    }

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- HACKEANDO LA JVM ---");
        
        // TODO 6 (PRUEBA): Cuando el constructor genérico fraudulento compile correctamente, 
        // crea una instancia orientada a Float y prueba sus accesos.
        
        /*
        Ejercicio19_ArraysGenericos<Float> hackData = new Ejercicio19_ArraysGenericos<>(5);
        hackData.meterDato(0, 3.14f);
        System.out.println("Dato rehidratado desde crudo: " + hackData.obtenerDato(0));
        */
    }
}
