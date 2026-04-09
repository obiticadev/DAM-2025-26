package com.masterclass.nivel3_bounded_types;

/**
 * EJERCICIO 9: Restricción Exclusiva
 * 
 * OBJETIVO: 
 * Migrar una clase ineficiente que exige múltiples casteos a un ecosistema paramétrico cerrado
 * donde solamente se evalúen elementos numéricos.
 * 
 * APOYO TEÓRICO: 
 * Revisa el archivo '03_Bounded_Types.md' (Sección: "Upper Bounds")
 */
// TODO 1: Configura la firma base de la clase indicando un parámetro de tipo universal.
// TODO 2: Refina la firma del parámetro para que, como hemos visto, no permita inyecciones que no hereden de Number.
public class Ejercicio09_Calculadora {

    // TODO 3: Destruye el tipado base rudimentario e implementa tu tipo limitado (T)
    private Object n1;
    private Object n2;

    public Ejercicio09_Calculadora(Object n1, Object n2) {
        this.n1 = n1;
        this.n2 = n2;
    }

    public double calcularSuma() {
        // TODO 4: Remueve los moldes de forzado de tipos ((Number)) aprovechando la inteligencia
        // que Java adquirirá tras resolver los dos primeros 'todos'. T ya será considerado un Number sin intermediarios.
        return ((Number)n1).doubleValue() + ((Number)n2).doubleValue();
    }

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- CALCULADORA RESTRINGIDA ---");
        
        // TODO 5 (PRUEBA): Modifica instanciaciones. Al tener resuelta la firma deberías poder crear
        // calculadoras de <Double> o <Integer>. Intenta crear una calculadora de <String> y observa a Java rugir.
        
        /*
        Ejercicio09_Calculadora<Double> calcD = new Ejercicio09_Calculadora<>(10.5, 5.2);
        System.out.println("Suma Doble: " + calcD.calcularSuma());
        */
    }
}
