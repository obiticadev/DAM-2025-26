package com.masterclass.nivel1_fundamentos_genericos;

/**
 * EJERCICIO 2: Tupla Multi-Tipo
 * 
 * OBJETIVO:
 * Trascender el uso de un solo parámetro genérico. Existen mapeos arquitectónicos 
 * donde combinamos identificadores con valores dispares (ej. Integer como ID, String como Entidad).
 * 
 * APOYO TEÓRICO: 
 * Revisa el archivo '01_Fundamentos_Genericos.md' (Sección: "Convenciones Universales y Múltiples Comodines")
 */
// TODO 1: Ajusta la firma de la clase para admitir la "Convención Universal de Mapas" vista en teoría.
public class Ejercicio02_TuplaKVP {

    // TODO 2: Refactoriza los atributos para que reflejen estrictamente dicha convención de identificadores.
    private Object clave;
    private Object valor;

    // TODO 3: Refactoriza la firma del constructor para enlazar los argumentos inyectados a tu convención genérica.
    public Ejercicio02_TuplaKVP(Object clave, Object valor) {
        this.clave = clave;
        this.valor = valor;
    }

    // TODO 4: Adapta todos los Getters y Setters subsiguientes eliminando los tipos base Object.
    public Object getClave() {
        return clave;
    }

    public void setClave(Object clave) {
        this.clave = clave;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- PROBANDO TUPLAS ---");
        
        // TODO 5 (PRUEBA): Descomenta y ejecuta usando el botón Run de tu IDE. 
        // Genera también tus propias instancias.
        
        /*
        Ejercicio02_TuplaKVP<Integer, String> empleado = new Ejercicio02_TuplaKVP<>(101, "Ingeniero Senior");
        System.out.println("Clave ID: " + empleado.getClave());
        System.out.println("Valor: " + empleado.getValor());
        */
    }
}
