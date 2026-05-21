package com.example.ahorcado.Clases;

import java.io.Serializable;

public class Jugador implements Serializable {
    private String nombre;
    private int puntuacion;

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

}
