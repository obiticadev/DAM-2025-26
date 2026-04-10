package com.masterclass.arrays.nivel08;

/**
 * EJERCICIO 41 — Set sobre Array
 * ==============================
 * Nivel de Referencia Teórica: teoria/08_Simulacion_Estructuras_Con_Arrays.md
 * 
 * Un Set no permite duplicados.
 */
public class Ej41_SetSobreArray {

    private String[] elements;
    private int size;

    public Ej41_SetSobreArray(int capacidad) {
        this.elements = new String[capacidad];
        this.size = 0;
    }

    // TODO 1: Implementar add(String val)
    // - Solo añadir si no existe.
    public boolean add(String val) {
        return false;
    }

    // TODO 2: Implementar contains(String val)
    public boolean contains(String val) {
        return false;
    }

    // TODO 3: Implementar remove(String val)
    public boolean remove(String val) {
        return false;
    }

    // TODO 4: Implementar size()
    public int size() {
        return 0;
    }

    // TODO 5: Implementar addAll(String[] vals)
    public void addAll(String[] vals) {
    }

    // TODO 6: Implementar intersection(Ej41_SetSobreArray other)
    // - Retorna un nuevo Set con los comunes.
    public Ej41_SetSobreArray intersection(Ej41_SetSobreArray other) {
        return null;
    }

    // TODO 7: Implementar toArray()
    // - Retorna String[] con el contenido real.
    public String[] toArray() {
        return null;
    }

    public static void main(String[] args) {
        System.out.println("=== ESTRUCTURA: SET (SIN DUPLICADOS) ===");
    }
}
