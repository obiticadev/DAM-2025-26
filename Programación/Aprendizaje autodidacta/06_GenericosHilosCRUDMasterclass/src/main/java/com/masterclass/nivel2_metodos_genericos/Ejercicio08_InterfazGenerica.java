package com.masterclass.nivel2_metodos_genericos;

/**
 * EJERCICIO 8: Interfaces Genéricas Fundamentales (Comparable)
 * 
 * OBJETIVO: 
 * Empalmar la lógica de dominio (Entidad clásica) con las interfaces core de Java 
 * que exigen resolver su parametrismo para permitir el ordenamiento de estructuras de datos.
 * 
 * APOYO TEÓRICO: 
 * Paradigma General de clases (Lógica deductiva de Comparable en Arrays/Collections)
 */
// TODO 1: Implementa la interfaz 'Comparable' a nivel estructural de la clase y tipifica su inyección. 
public class Ejercicio08_InterfazGenerica {

    private String nombre;
    private double salario;

    public Ejercicio08_InterfazGenerica(String nombre, double salario) {
        this.nombre = nombre;
        this.salario = salario;
    }

    public double getSalario() { return salario; }
    public String getNombre() { return nombre; }

    // TODO 2: Sobrescribe el contrato de la interfaz (compareTo).
    // TODO 3: Configura el tipado del argumento 'otro' basándote en la instrucción del paso 1.
    // TODO 4: Desarrolla el algoritmo de retorno (-1, 0, 1) en base a una escala salarial.
    /*
    @Override
    public int compareTo(...) {
        // Tu algoritmo aquí
    }
    */

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- MOTOR DE COMPARACIÓN ---");
        
        // TODO 5 (PRUEBA): Cuando hayas garantizado el contrato y el algoritmo, descomenta
        // el log inferior e invoca a Run. Verifica si el motor identifica apropiadamente > 0.
        
        Ejercicio08_InterfazGenerica jefe = new Ejercicio08_InterfazGenerica("Jefe", 5000);
        Ejercicio08_InterfazGenerica becario = new Ejercicio08_InterfazGenerica("Becario", 1000);

        // System.out.println("¿Jefe cobra más que becario? La respuesta matemática es: " + jefe.compareTo(becario));
    }
}
