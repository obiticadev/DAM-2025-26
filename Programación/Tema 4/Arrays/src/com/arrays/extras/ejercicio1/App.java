package com.arrays.extras.ejercicio1;

public class App {
    public static void main(String[] args) {
        final int FILAS = 6;
        final int COLUMNAS = 3;
        int contador = 0;
        int sorteoFilas;
        int sorteoColumnas;
        
        String[][] clase = new String[FILAS][COLUMNAS];

        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                contador++;
                clase[i][j] = "Alumno"+ contador;
            }
        }
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                System.out.print(clase[i][j] + " "); 
            }
            System.out.println();
        }
        


        System.out.println("El ganador es " + ganadorSorteo(clase) + "!!");
        
    }

    // Generamos el sorteo por filas y por columnas
    public static String ganadorSorteo(String[][] clase){
        int sorteoFilas = (int) (Math.random() * clase.length);
        int sorteoColumnas = (int) (Math.random() * clase[0].length);
        return clase[sorteoFilas][sorteoColumnas];
    }
}
