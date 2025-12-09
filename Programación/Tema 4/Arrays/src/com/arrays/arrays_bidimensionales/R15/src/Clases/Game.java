package Clases;

public class Game {
    private int filas;
    private int columnas;
    private char[][] matriz;
    private char prota;
    private char fantasma;
    private char campo;

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
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void resetearMatriz() {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                matriz[i][j] = '*';
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

}
