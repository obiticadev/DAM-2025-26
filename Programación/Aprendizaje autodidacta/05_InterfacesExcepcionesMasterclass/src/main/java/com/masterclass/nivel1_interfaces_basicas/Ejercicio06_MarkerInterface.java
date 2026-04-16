package com.masterclass.nivel1_interfaces_basicas;

import java.util.List;

/**
 * ============================================================================
 *  EJERCICIO 06 — MARKER INTERFACE (SIN GUIA)
 * ============================================================================
 *
 * CONCEPTO CLAVE: Una "marker interface" (interfaz marcadora) es una interfaz
 * SIN metodos. Sirve como una "etiqueta" para clasificar objetos.
 *
 * Ejemplo real en Java:
 *   - Serializable: marca que un objeto se puede serializar
 *   - Cloneable: marca que un objeto se puede clonar
 *   - RandomAccess: marca que una lista soporta acceso aleatorio rapido
 *
 * Tu puedes crear tus propias marker interfaces para clasificar objetos:
 *   public interface Priorizable {}
 *   if (tarea instanceof Priorizable) { // tiene prioridad }
 *
 * Lee primero: teoria/01_Interfaces_Basicas.md
 */
public class Ejercicio06_MarkerInterface {

    /**
     * Marker interface: marca un objeto como "urgente".
     * No tiene metodos; solo sirve para etiquetar.
     */
    public interface Urgente {
    }

    /**
     * Interfaz base para cualquier tarea.
     */
    public interface Tarea {
        String getDescripcion();
    }

    /**
     * TODO: Crea una Tarea normal (que NO es Urgente).
     * Solo debe implementar Tarea con la descripcion dada.
     */
    public static Tarea crearTareaNormal(String descripcion) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Crea una Tarea que TAMBIEN sea Urgente.
     * Debe implementar AMBAS interfaces: Tarea y Urgente.
     * El objeto devuelto debe cumplir: (objeto instanceof Urgente) == true
     */
    public static Tarea crearTareaUrgente(String descripcion) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Filtra una lista de Tareas y devuelve solo las que son Urgentes.
     * Usa instanceof para comprobar.
     */
    public static List<Tarea> filtrarUrgentes(List<Tarea> tareas) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Cuenta cuantas tareas en la lista NO son urgentes.
     */
    public static long contarNoUrgentes(List<Tarea> tareas) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
