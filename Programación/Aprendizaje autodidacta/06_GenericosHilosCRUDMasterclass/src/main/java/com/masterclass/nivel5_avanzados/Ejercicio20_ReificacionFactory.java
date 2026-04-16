package com.masterclass.nivel5_avanzados;

/**
 * EJERCICIO 20: Metaprogramación con Object Factory (Reificación)
 * 
 * OBJETIVO:
 * Exponer el patrón "Reification Class Token". Como Type Erasure destruye a <T>,
 * obligaremos al usuario a pasarnos explícitamente el '.class' de la clase si requiere  
 * que construyamos una para él usando 'new T()'.
 * 
 * APOYO TEÓRICO: 
 * Diagrama de la Factoria Segura en '05_Genericos_Avanzados.md'
 */
// TODO 1: Configura la aserción genérica pura de la clase matriz.
public class Ejercicio20_ReificacionFactory {

    // TODO 2: Implementa el parámetro clave "Class<T>" que actuará de Token reificador.
    private Class tipoReales;

    public Ejercicio20_ReificacionFactory(Class tipoInyectado) { // TODO 3: Refinala con genéricos strictos.
        this.tipoReales = tipoInyectado;
    }

    // TODO 4: Actualiza el retorno y las llamadas.
    // Usaremos Reflection para hacer un 'new' a costa del .class
    public Object construirNuevaInstancia() {
        try {
            // Este es el equivalente arquitectónico a hacer `new T();`
            return tipoReales.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Fallo genérico reconstructivo", e);
        }
    }

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- PATRÓN REIFICACIÓN DE FÁBRICA ---");
        
        // TODO 5 (PRUEBA): Modifica los String y prueba la instanciación automática.
        /*
        Ejercicio20_ReificacionFactory<String> fabricaStrings = new Ejercicio20_ReificacionFactory<>(String.class);
        String autogenerado = fabricaStrings.construirNuevaInstancia();
        System.out.println("¿Es un String instanciado dinámicamente?: " + (autogenerado instanceof String));
        */
    }
}
