package com.example.ahorcado.Clases;

import java.util.stream.IntStream;

public class Ahorcado {
    private int estilo;
    private String palabra;
    private int intentos;
    private String palabraJuego;
    private Jugador player;

    public Ahorcado(int estilo, String palabra, String player) {
        this.estilo = estilo;
        this.palabra = palabra;
        this.intentos = 0;
        this.palabraJuego = "_ ".repeat(palabra.length());
        this.player = new Jugador(player);
    }

    public boolean buscarCaracter(char caracter) {
        if (!palabra.contains(String.valueOf(caracter))) {
            intentos++;
            return false;
        }
        return true;
    }

    public void rellenarPalabra(char caracter) {
        int[] posiciones = IntStream.range(0, palabra.length())
                .filter(c -> palabra.charAt(c) == caracter)
                .toArray();
        StringBuilder sb = new StringBuilder(getPalabraJuego());
        for (int i : posiciones) {
            sb.setCharAt(i, caracter);
        }
        setPalabraJuego(sb.toString());
    }

    public int getEstilo() {
        return estilo;
    }

    public String getPalabra() {
        return palabra;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        if (intentos >= 0 && intentos <= 10) {
            this.intentos = intentos;
        }
    }

    public String getPalabraJuego() {
        return palabraJuego;
    }

    public void setPalabraJuego(String palabraJuego) {
        this.palabraJuego = palabraJuego;
    }
}