package com.masterclass.arrays.nivel08;

/**
 * EJERCICIO 40 — Queue sobre Array (FIFO)
 * ========================================
 * Nivel de Referencia Teórica: teoria/08_Simulacion_Estructuras_Con_Arrays.md
 * 
 * Implementación con punteros head/tail y aritmética circular.
 */
public class Ej40_QueueSobreArray {

    private int[] queue;
    private int head;
    private int tail;
    private int size;

    public Ej40_QueueSobreArray(int capacidad) {
        this.queue = new int[capacidad];
        this.head = 0;
        this.tail = -1;
        this.size = 0;
    }

    // TODO 1: Implementar enqueue(int valor)
    // - Tip: tail = (tail + 1) % queue.length.
    public void enqueue(int valor) {
    }

    // TODO 2: Implementar dequeue()
    // - Tip: head = (head + 1) % queue.length.
    public int dequeue() {
        return 0;
    }

    // TODO 3: Implementar peek()
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

    // TODO 7: Implementar clear()
    public void clear() {
    }

    public static void main(String[] args) {
        System.out.println("=== ESTRUCTURA: QUEUE (FIFO) ===");
    }
}
