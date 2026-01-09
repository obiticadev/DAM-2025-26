package Utilidades;

import java.util.Arrays;
import java.util.Random;

public class Funciones {

    private static Random rd = new Random();

    public static int[] generarStock(int[] productos) {
        for (int i = 0; i < productos.length; i++) {
            productos[i] = rd.nextInt(51);
        }
        return productos;
    }

    public static void mostrarAlertas(int[] stock, int minimo) {
        for (int i = 0; i < stock.length; i++) {
            if (stock[i] < minimo) {
                System.out.println("Producto " + (i + 1) + ": " + stock[i] + " en almacen");
            }
        }
    }

    public static int[] obtenerTop3(int[] stock) {
        int[] top3 = new int[3];

        int[] indice = new int[stock.length];
        int[] copiaStock = Arrays.copyOf(stock, stock.length);
        int[][] stockIndexado = new int[indice.length][2];

        for (int i = 0; i < stockIndexado.length; i++) {
            for (int j = 0; j < stockIndexado[0].length; j++) {
                if (j == 0) {
                    stockIndexado[i][j] = (i + 1);
                } else {
                    stockIndexado[i][j] = copiaStock[i];
                }
            }
        }

        for (int i = 0; i < copiaStock.length / 2; i++) {
            int temporal = copiaStock[i];
            copiaStock[i] = copiaStock[copiaStock.length - 1 - i];
            copiaStock[copiaStock.length - 1 - i] = temporal;
        }

        for (int i = 0; i < indice.length; i++) {
            for (int j = 0; j < copiaStock.length; j++) {

            }
        }

        System.out.println(Arrays.toString(copiaStock));

        return top3;
    }
}
