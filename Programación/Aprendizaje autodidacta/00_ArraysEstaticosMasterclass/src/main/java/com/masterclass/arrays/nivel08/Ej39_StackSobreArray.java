package com.masterclass.arrays.nivel08;

/**
 * EJERCICIO 39 — Stack sobre Array (LIFO)
 * ========================================
 * Nivel de Referencia Teórica: teoria/08_Simulacion_Estructuras_Con_Arrays.md
 */
public class Ej39_StackSobreArray {

    private int[] stack;
    private int top;

    public Ej39_StackSobreArray(int capacidad) {
        this.stack = new int[capacidad];
        this.top = -1;
    }

    // TODO 1: Implementar push(int valor)
    // - Añadir arriba. Lanzar exception si está lleno.
    public void push(int valor) {
    }

    // TODO 2: Implementar pop()
    // - Sacar el de arriba.
    public int pop() {
        return 0;
    }

    // TODO 3: Implementar peek()
    // - Ver el de arriba sin sacar.
    public int peek() {
        return 0;
    }

    // TODO 4: Implementar isEmpty()
    public boolean isEmpty() {
        return false;
    }

    // TODO 5: Implementar isFull()
    public boolean isFull() {
        return false;
    }

    // TODO 6: Implementar size()
    public int size() {
        return 0;
    }

    // TODO 7: Implementar search(int valor)
    // - Retorna la distancia desde el tope (1-based) o -1.
    public int search(int valor) {
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("=== ESTRUCTURA: STACK (LIFO) ===");
    }
}
