package com.funciones.utilidades;

import java.util.Scanner;

public class Gastos {
    public static int introducirNumGastos(){

        boolean continuar = true;
        int num;

        Scanner numScan = new Scanner(System.in);
        
        do {
            System.out.println("+ Indica el número de facturas que vas a introducir:");
            num = numScan.nextInt();
            if (num > 0) {
                continuar = false;
            }else{
                System.out.println("+ OPCIÓN INCORRECTA +");
            }
            
        } while (continuar == true);
        
        return num;
    }

    public static double introducirGasto(int num){

        boolean continuar2 = true;
        int num2;
        double gasto;
        double gastoTotalFacturas = 0;
        
        
        Scanner numScan2 = new Scanner(System.in);
        
        for (int i = 1; i <= num; i++) {
            double gastoTotal = 0;
            do {
                System.out.println("+ Indica el número de gastos de la factura (" + i + ")");
                num2 = numScan2.nextInt();
                if (num2 > 0) {
                    continuar2 = false;
                }else{
                    System.out.println("+ OPCIÓN INCORRECTA +");
                }
            } while (continuar2 == true);
            for (int j = 1; j <= num2; j++) {
                System.out.println("+ Indica el importe del gasto (" + j + ")");
                gasto = numScan2.nextDouble();
                gastoTotal = gastoTotal + gasto;
            }
             System.out.println("Factura (" + i + "), total importe: " + gastoTotal);
            gastoTotalFacturas = gastoTotalFacturas + gastoTotal;
        }
        return gastoTotalFacturas;
    }
}
