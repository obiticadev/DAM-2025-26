package com.masterclass.arrays.nivel08;

/**
 * EJERCICIO 42 — MultiMap Paralelo
 * ================================
 * Nivel de Referencia Teórica: teoria/08_Simulacion_Estructuras_Con_Arrays.md
 * 
 * Una clave puede estar asociada a múltiples valores (Jagged Value Array).
 */
public class Ej42_MultiMapParalelo {

    private String[] keys;
    private int[][] values; // Jagged
    private int size;

    public Ej42_MultiMapParalelo(int capacidad) {
        this.keys = new String[capacidad];
        this.values = new int[capacidad][];
        this.size = 0;
    }

    // TODO 1: Implementar put(String key, int value)
    // - Si la llave existe, añade el valor a su array (redimensionar fila).
    public void put(String key, int value) {
    }

    // TODO 2: Implementar getAll(String key)
    public int[] getAll(String key) {
        return null;
    }

    // TODO 3: Implementar containsKey(String key)
    public boolean containsKey(String key) {
        return false;
    }

    // TODO 4: Implementar removeKey(String key)
    public void removeKey(String key) {
    }

    // TODO 5: Implementar removeValueFromKey(String key, int value)
    public void removeValueFromKey(String key, int value) {
    }

    // TODO 6: Implementar size()
    // - Número de claves.
    public int size() {
        return 0;
    }

    // TODO 7: Implementar totalValuesCount()
    // - Cuántos valores hay en total sumando todas las listas.
    public int totalValuesCount() {
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("=== ESTRUCTURA: MULTIMAP PARALELO ===");
    }
}
