package com.masterclass.nivel4_wildcards_pecs;

import java.util.List;

/**
 * EJERCICIO 15: Escritura Segura ("Consumer Super")
 * 
 * OBJETIVO: 
 * Implementar el modelo Consumer (Consumidor). Operaremos inversamente al caso anterior. 
 * El objetivo es empujar datos desde la clase al mundo exterior con garantía estructural.
 * 
 * APOYO TEÓRICO: 
 * Revisa el archivo '04_Wildcards_PECS.md' (Sección: "Lower Bounded Wildcard")
 */
public class Ejercicio15_WildcardSuper {

    // TODO 1: Esta firma impide que inyectes colecciones generalistas (List<Object>). Corrige el comodín 
    // asumiendo el precepto arquitectónico del "Lower Bound" (Consumidor).
    public void inyectarCadenasSeguras(List<String> almacenConsumer) {
        
        // TODO 2: Evalúa internamente por qué puedes insertar Strings con seguridad absoluta.
        // A nivel memoria, ¿qué ha garantizado tu firma respecto a la lista que alguien le pase en ejecución?
        almacenConsumer.add("Transacción A");
        almacenConsumer.add("Log de Auditoría");
        
        // TODO 3: Intenta extraer un valor utilizando tipado String: String x = almacenConsumer.get(0);
        // ¿Qué ocurre? Identifica la penalización técnica de usar el modelo Consumer en Java.
    }

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- EMPUJANDO DATOS (CONSUMER) ---");
        
        // Base de Datos en crudo que recibe cualquier Objeto (El Padre de los Strings).
        List<Object> baseDatosGlobal = new java.util.ArrayList<>();
        
        // TODO 4 (PRUEBA): Vincula el "Object" inicializado arriba con las limitaciones métodicas que has escrito.
        /*
        Ejercicio15_WildcardSuper ej = new Ejercicio15_WildcardSuper();
        ej.inyectarCadenasSeguras(baseDatosGlobal); 
        System.out.println("BD Object contiene nativamente Strings inyectados: " + baseDatosGlobal);
        */
    }
}
