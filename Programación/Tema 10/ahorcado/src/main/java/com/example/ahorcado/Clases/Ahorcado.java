package com.example.ahorcado.Clases;

public class Ahorcado {
    private int estilo;
    private String palabra;
    private int intentos;
    private String palabraJuego;

    public Ahorcado(int estilo, String palabra) {
        this.estilo = estilo;
        this.palabra = palabra;
        this.intentos = 0;
        this.palabraJuego = "_ ".repeat(palabra.length());
    }

    public void buscarCaracter(char caracter) {
        if (palabra.contains(String.valueOf(caracter))) {
            // TODO por aquí me he quedado
        }
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