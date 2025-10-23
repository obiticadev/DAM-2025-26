package com.funciones.principal;
import java.util.Scanner;
import com.funciones.utilidades.Gastos;

public class ejercicio1 {
    public static void main(String[] args) throws Exception {

        Scanner numScan = new Scanner(System.in);
        
        // Declaración de variables
        int numFacturas;
        
        System.out.println("""
                ************************
                ***GESTOR DE FACTURAS***
                ************************
                """);
        
        int numGastos = Gastos.introducirNumGastos();
        double gastosTotalFacturas = Gastos.introducirGasto(numGastos);
        System.out.println("""
                *****************************************
                ***IMPORTE TOTAL DE TODAS LAS FACTURAS***
                *****************************************
                """);
        System.out.println("+++ " + gastosTotalFacturas);
    }
}
