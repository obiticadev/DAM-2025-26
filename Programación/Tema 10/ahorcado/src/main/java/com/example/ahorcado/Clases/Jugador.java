package com.example.ahorcado.Clases;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;

public class Jugador implements Serializable {
    private String nombre;
    private int puntuacion;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.puntuacion = 0;
    }

    public void sumarPuntos(int intentos) {
        this.puntuacion += 20 - (intentos * 2);
    }

    public void guardarDatosJugador(File url) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(url))) {
            oos.writeObject(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

}
