package com.masterclass.nivel4_wildcards_pecs;

import java.util.List;

/**
 * EJERCICIO 13: El Comodín Ilimitado '?'
 * 
 * OBJETIVO:
 * Discernir por qué List<Object> no es el Polimorfismo absoluto que te han vendido.
 * Un 'Object' y un '?' tienen comportamientos drásticamente diferentes ante el Type Erasure.
 * 
 * APOYO TEÓRICO: 
 * Revisa el archivo '04_Wildcards_PECS.md' (Sección: "Unbounded Wildcard <?>")
 */
public class Ejercicio13_WildcardUnbounded {

    // TODO 1: Esta firma exige estrictamente contenedores de Object. Ajusta el parámetro para
    // que funcione como un embudo receptivo de CUALQUIER tipo de lista genérica.
    public static int contarElementosYVaciar(List<Object> misterio) {
        
        int conteo = misterio.size();
        
        // TODO 2: Evalúa qué nivel de seguridad exige Java al vaciar (.clear()) la lista. 
        // ¿Le importa el tipo interno para destruirlo? Deduce por qué esto jamás fallará.
        misterio.clear();
        
        return conteo;
    }

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- EL COMODÍN DESTRUCTOR ---");
        
        // TODO 3 (PRUEBA): Estas instancias nacen con un Genérico de <String>.
        // El método antiguo las rechazaba de pleno. Comprueba en VSC que tras tu parche, funcionan.
        
        /*
        List<String> palabras = new java.util.ArrayList<>(List.of("A", "B", "C"));
        System.out.println("Se purgaron: " + contarElementosYVaciar(palabras) + " elementos.");
        System.out.println("Estado final lista original: " + palabras);
        */
        
        // TODO 4: Intenta descomentar abajo ¿Qué ocurre si intentas usar '.add("nuevo")' 
        // dentro del método 'contarElementosYVaciar' asumiendo que tienes un comodín List<?> ?
    }
}
