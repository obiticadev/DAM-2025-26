package com.masterclass.arrays.nivel08;

/**
 * EJERCICIO 43 — Tabla de Registros
 * =================================
 * Nivel de Referencia Teórica: teoria/08_Simulacion_Estructuras_Con_Arrays.md
 * 
 * Simula una tabla SQL básica usando String[][].
 */
public class Ej43_TablaRegistros {

    private String[][] tabla;
    private int cursor;

    public Ej43_TablaRegistros(int capacidad, int columnas) {
        this.tabla = new String[capacidad][columnas];
        this.cursor = 0;
    }

    // TODO 1: Implementar insertRow(String[] data)
    public void insertRow(String[] data) {
    }

    // TODO 2: Implementar deleteRow(int index)
    // - Desplazar para compactar.
    public void deleteRow(int index) {
    }

    // TODO 3: Implementar findByColumn(int col, String value)
    // - Retorna el primer índice de fila que coincida.
    public int findByColumn(int col, String value) {
        return -1;
    }

    // TODO 4: Implementar updateCell(int row, int col, String newValue)
    public void updateCell(int row, int col, String newValue) {
    }

    // TODO 5: Implementar count()
    public int count() {
        return 0;
    }

    // TODO 6: Implementar sortByColumn(int col)
    // - (Desafío) Usar uno de los ordenamientos previos sobre la matriz.
    public void sortByColumn(int col) {
    }

    // TODO 7: Implementar selectAll()
    // - Retorna copia con datos reales.
    public String[][] selectAll() {
        return null;
    }

    public static void main(String[] args) {
        System.out.println("=== ESTRUCTURA: TABLA REGISTROS ===");
    }
}
