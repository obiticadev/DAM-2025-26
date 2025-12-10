package Clases;

import java.util.Random;

public class Game {
    private int filas;
    private int columnas;
    private char[][] matriz;
    private char prota;
    private char fantasma;
    private char campo;
    private Random rd = new Random();

    public Game(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.matriz = new char[filas][columnas];
        this.prota = 'P';
        this.fantasma = 'X';
        this.campo = '*';
    }

    public void dibujarMatriz() {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                
                System.out.print(this.matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void resetearMatriz() {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (i == 0 && j == 0) {
                    this.matriz[i][j] = this.prota;
                } if ((i == matriz.length - 1) && (j == matriz[0].length - 1)) {
                    this.matriz[i][j] = this.fantasma;
                } else {
                    this.matriz[i][j] = '*';
                    
                } 
            }
        }
    }

    public void movimientoFantasma(){
        int fila = rd.nextInt(this.matriz.length);
        int columna = rd.nextInt(this.matriz[0].length);
        char temporal;
        for (int i = 0; i < this.matriz.length; i++) {
            for (int j = 0; j < this.matriz[0].length; j++) {
                if (this.matriz[i][j] == this.fantasma) {
                    temporal = this.matriz[fila][columna];
                    this.matriz[fila][columna] = this.matriz[i][j];
                    this.matriz[i][j] = temporal;
                }
            }
        }
    }

    public void setMatriz(char[][] matriz) {
        this.matriz = matriz;
    }

    public char[][] getMatriz() {
        return matriz;
    }

    public char getProta() {
        return prota;
    }

    public char getFantasma() {
        return fantasma;
    }

    public char getCampo() {
        return campo;
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    

}
