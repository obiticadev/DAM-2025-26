package com.masterclass.arrays.nivel08;

/**
 * EJERCICIO 37 — Map con Arrays Paralelos
 * =======================================
 * Nivel de Referencia Teórica: teoria/08_Simulacion_Estructuras_Con_Arrays.md
 * 
 * Simula un Map<String, Integer> usando dos arrays coordinados por índice.
 */
public class Ej37_MapConArraysParalelos {

    private String[] keys;
    private int[] values;
    private int size;

    public Ej37_MapConArraysParalelos(int capacidad) {
        this.keys = new String[capacidad];
        this.values = new int[capacidad];
        this.size = 0;
    }

    // TODO 1: Implementar put(String key, int value)
    // - Si la llave existe, actualiza. Si no, añade.
    public void put(String key, int value) {
    }

    // TODO 2: Implementar get(String key)
    // - Retorna el valor o lanza exception si no existe.
    public int get(String key) {
        return 0;
    }

    // TODO 3: Implementar containsKey(String key)
    public boolean containsKey(String key) {
        return false;
    }

    // TODO 4: Implementar remove(String key)
    // - Eliminar y desplazar elementos para no dejar huecos.
    public void remove(String key) {
    }

    // TODO 5: Implementar size()
    public int size() {
        return 0;
    }

    // TODO 6: Implementar keys()
    // - Retorna un String[] con todas las llaves actuales.
    public String[] keys() {
        return null;
    }

    // TODO 7: Implementar clear()
    public void clear() {
    }

    public static void main(String[] args) {
        System.out.println("=== ESTRUCTURA: MAP PARALELO ===");
    }
}
