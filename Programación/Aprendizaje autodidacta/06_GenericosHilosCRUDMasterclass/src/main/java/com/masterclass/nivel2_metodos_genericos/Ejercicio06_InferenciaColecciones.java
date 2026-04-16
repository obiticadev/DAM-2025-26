package com.masterclass.nivel2_metodos_genericos;

import java.util.ArrayList;
import java.util.List;

/**
 * EJERCICIO 6: Inferencia de Tipos y Listas en Runtime
 * 
 * OBJETIVO: 
 * Crear un conversor versátil que opere transversalmente contra el framework de colecciones 
 * de Java, forzándolo a instanciar contenedores seguros según la inferencia de ejecución.
 * 
 * APOYO TEÓRICO: 
 * Revisa el archivo '02_Metodos_Genericos.md' (Sección: "Arquitectura de Invocación")
 */
public class Ejercicio06_InferenciaColecciones {

    // TODO 1: Habilita el mecanismo de genéricos en la firma del método para la clase ajena.
    // TODO 2: Refactoriza los atributos de entrada y salida para erradicar las estructuras crudas (Raw Types).
    public static List arrayToList(Object[] arrayOriginal) {
        
        // TODO 3: Fija la instanciación interna de la estructura de datos utilizando el diamánte estricto.
        List listaNueva = new ArrayList();
        
        // TODO 4: Ajusta la extracción de bucle mejorado (foreach) empleando el comodín de tipo resuelto.
        for (Object elemento : arrayOriginal) {
            listaNueva.add(elemento);
        }
        
        return listaNueva;
    }

    // ==============================================================
    // ZONA DE EJECUCIÓN (PLAYGROUND)
    // ==============================================================
    public static void main(String[] args) {
        System.out.println("--- CONVERSOR ARRAY-TO-LIST ---");
        Boolean[] banderas = {true, false, true};
        
        // TODO 5 (PRUEBA): Al resolver las asignaturas y firmas superiores, esta línea lanzará alertas 
        // de Warning Crudo. Asegura la declaración variable a continuación para extraer el resultado. Ejecuta.
        
        List resultado = arrayToList(banderas);
        System.out.println("Lista Strict Type transformada: " + resultado);
    }
}
