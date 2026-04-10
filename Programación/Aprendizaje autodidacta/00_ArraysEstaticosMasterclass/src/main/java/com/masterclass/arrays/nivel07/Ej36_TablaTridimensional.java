package com.masterclass.arrays.nivel07;

/**
 * EJERCICIO 36 — Caso Práctico: Temperaturas
 * ===========================================
 * Nivel de Referencia Teórica: teoria/07_Arrays_Tridimensionales.md
 * 
 * Modelado del mundo real: int[12 meses][31 dias][24 horas].
 */
public class Ej36_TablaTridimensional {

    // TODO 1: Implementar inicializarCero(int[][][] temps)
    public static void inicializarCero(int[][][] temps) {
    }

    // TODO 2: Implementar mediaMensual(int[][][] temps, int mes)
    public static double mediaMensual(int[][][] temps, int mes) {
        return 0.0;
    }

    // TODO 3: Implementar temperaturaMaxAnual(int[][][] temps)
    // - Retorna int[] {mes, dia, hora, valorMax}.
    public static int[] temperaturaMaxAnual(int[][][] temps) {
        return null;
    }

    // TODO 4: Implementar totalesMeses(int[][][] temps)
    // - Retorna array de 12 posiciones con la suma de cada mes.
    public static int[] totalesMeses(int[][][] temps) {
        return null;
    }

    // TODO 5: Implementar corregirLectura(int[][][] temps, int m, int d, int h, int valor)
    public static void corregirLectura(int[][][] temps, int m, int d, int h, int valor) {
    }

    // TODO 6: Implementar mediaHoraEspecifica(int[][][] temps, int hora)
    // - ¿Cuál es la media de temperatura a las 'hora' durante todo el año?
    public static double mediaHoraEspecifica(int[][][] temps, int hora) {
        return 0.0;
    }

    // TODO 7: Implementar mesConMasCalor(int[][][] temps)
    // - Mes con la media más alta.
    public static int mesConMasCalor(int[][][] temps) {
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("=== LECTURA DE SENSORES 3D ===");
    }
}
