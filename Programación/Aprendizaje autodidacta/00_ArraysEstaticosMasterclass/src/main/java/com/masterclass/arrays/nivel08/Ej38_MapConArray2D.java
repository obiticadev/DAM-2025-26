package com.masterclass.arrays.nivel08;

/**
 * EJERCICIO 38 — Map con Array 2D
 * ================================
 * Nivel de Referencia Teórica: teoria/08_Simulacion_Estructuras_Con_Arrays.md
 * 
 * Capacidad para almacenar pares llave-valor en una matriz String[n][2].
 */
public class Ej38_MapConArray2D {

    private String[][] data;
    private int size;

    public Ej38_MapConArray2D(int capacidad) {
        this.data = new String[capacidad][2];
        this.size = 0;
    }

    // TODO 1: Implementar put(String key, String value)
    public void put(String key, String value) {
    }

    // TODO 2: Implementar get(String key)
    public String get(String key) {
        return null;
    }

    // TODO 3: Implementar containsKey(String key)
    public boolean containsKey(String key) {
        return false;
    }

    // TODO 4: Implementar remove(String key)
    public void remove(String key) {
    }

    // TODO 5: Implementar size()
    public int size() {
        return 0;
    }

    // TODO 6: Implementar entrySet()
    // - Retorna una copia de la matriz con solo los datos reales.
    public String[][] entrySet() {
        return null;
    }

    // TODO 7: Implementar grow()
    // - Si se llena, doblar la capacidad del array 2D.
    private void grow() {
    }

    public static void main(String[] args) {
        System.out.println("=== ESTRUCTURA: MAP 2D ===");
    }
}
