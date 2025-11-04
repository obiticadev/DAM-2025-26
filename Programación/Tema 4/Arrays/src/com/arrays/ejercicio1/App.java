package com.arrays.ejercicio1;
import java.util.Scanner;
import com.arrays.utilidades.*;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);        

        int[] num = new int[5];
        
        System.out.println("ARRAYS");

        for (int i = 0; i < num.length; i++) {
            System.out.print("Introduce un número en la posición " + (i+1) + ": ");
            num[i] = sc.nextInt();
        }
        System.out.println("");
        for (int i = 0; i < num.length; i++) {
            System.out.print("El número " + (i+1) + " introducido es el " + num[i] + "\n");
        }

        /*

        Otra manera de crear el array con for each
        
        for (int n : num){
            System.out.println("Número : " + n);
        }
         */
        
    }
}
