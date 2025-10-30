package com.funciones.principal;

import java.util.Scanner;

public class ejercicio4 {
    public static void main(String[] args) {
        String pacmanS = ".....P.....";
        StringBuilder pacmanB = new StringBuilder();
        
        int position;
        char movimiento = 0;

        Scanner sc = new Scanner(System.in);
        
        do {
            // Buscamos si hay algún punto en el String
            position = (pacmanS.indexOf("."));
            System.out.println("Hay punto");
            System.out.println("");

        } while (position != -1 || movimiento == 'q');
        
    }

    

}
