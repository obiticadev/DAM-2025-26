package com.arrays.muyFaciles.ejercicio6;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] numArray;
        int numN;
        int numM;
        String line = "";

        System.out.print("Introduce el valor de N: ");
        numN = sc.nextInt();
        System.out.print("Introduce el valor de M: ");
        numM = sc.nextInt();

        numArray = new int[numN];

        for (int i = 0; i < numArray.length; i++) {
            if (i != (numArray.length - 1)) {
                line = line + numM + ", ";
            }else{
                line = line + numM;
            } 
            
        }
        System.out.println(line);

        
    }
}
