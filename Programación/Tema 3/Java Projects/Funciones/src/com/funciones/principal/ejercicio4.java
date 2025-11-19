package com.funciones.principal;

import java.util.Scanner;

public class ejercicio4 {
    public static void main(String[] args) {
        String pacmanS = ".....P.....";
        StringBuilder pacmanB = new StringBuilder();

        final char PACMAN_USER = 'P';

        int positionP;
        char movimiento = 0;

        Scanner sc = new Scanner(System.in);

        int contadorBolasInicial = contadorDeBolas(pacmanS);
        int contadorBolasRestante;

        do {
            System.out.print("\033[H\033[2J");
            // Buscamos si hay algún punto en el String
            contadorBolasRestante = contadorDeBolas(pacmanS);

            if (contadorBolasRestante != 0) {
                

                System.out.println("Tienes " + (contadorBolasInicial - contadorBolasRestante) + " puntos actualmente");
                System.out.println("Quedan " + contadorBolasRestante + " puntos para completar el juego\n");

                // Calculamos la posición actual:
                positionP = pacmanS.indexOf('P');

                System.out.println(pacmanS + "\n");

                // Preguntamos lo que quiere hacer
                System.out.print("\nMover (a/d/q): ");
                movimiento = sc.next().charAt(0);

                switch (movimiento) {
                    case 'a' -> {

                        int izquierda = positionP - 1;

                        // Movemos a pacman comiendo una ubicación
                        pacmanS = izquierda(pacmanS, izquierda, PACMAN_USER);

                    }
                    case 'd' -> {
                        int derecha = positionP + 1;

                        // Movemos a pacman comiendo una ubicación
                        pacmanS = derecha(pacmanS, derecha, PACMAN_USER);

                    }

                    default -> {

                    }

                }

            } else {
                System.out.println(pacmanS + "\n");
                System.out.println("Has conseguido: " + contadorBolasInicial + " puntos en total");
                System.out.println("++ END GAME ++");
            }

        } while (contadorBolasRestante != 0 && movimiento != 'q');

    }

    public static String izquierda(String pacmanS, int izquierda, char newPositionP) {

        int positionActual = izquierda + 1;
        // Movemos a pacman comiendo una ubicación
        StringBuilder newString = new StringBuilder(pacmanS);

        newString.setCharAt(izquierda, newPositionP);
        newString.setCharAt(positionActual, ' ');
        pacmanS = newString.toString();

        return pacmanS;
    }

    public static String derecha(String pacmanS, int derecha, char newPositionP) {

        int positionActual = derecha - 1;
        // Movemos a pacman comiendo una ubicación
        StringBuilder newString = new StringBuilder(pacmanS);

        newString.setCharAt(derecha, newPositionP);
        newString.setCharAt(positionActual, ' ');
        pacmanS = newString.toString();

        return pacmanS;
    }

    public static int contadorDeBolas(String pacmanS) {

        int contador = 0;

        for (int i = 0; i < pacmanS.length(); i++) {
            if (pacmanS.charAt(i) == '.') {
                contador++;
            }
        }
        return contador;
    }

    public ejercicio4() {
    }

}
