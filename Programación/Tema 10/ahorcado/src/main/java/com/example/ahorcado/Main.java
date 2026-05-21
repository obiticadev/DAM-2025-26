package com.example.ahorcado;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.example.ahorcado.Clases.Ahorcado;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static File palabras = new File("palabras.txt");

    public static void main(String[] args) {
        String respuestaMenu;
        do {
            menu();
            respuestaMenu = sc.nextLine();
            switch (respuestaMenu) {
                case "1" -> {
                    int estilo = option1();
                    List<String> ahorcado = Ahorcado.listaAhorcado().get(estilo);
                }
                case "0" -> {
                    System.out.println("Saliendo del programa...");
                }

                default -> {
                    System.out.println("Selecciona una opción válida");
                }
            }
        } while (respuestaMenu != "0");
    }

    public static void menu() {
        System.out.print("""
                AHORCADO
                --------

                1) Jugar

                0) Salir

                Selecciona una opción:\t""");
    }

    public static int option1() {
        String respuesta;
        do {
            System.out.print("""
                    --- ESTILO DE AHORCADO ---

                    1) Estilo pirata
                    2) Estilo moderno
                    3) Estilo robótico

                    0) Atrás

                    Selecciona una opción:\t""");
            respuesta = sc.nextLine();
            switch (respuesta) {
                case "1" -> {
                    return (Integer.parseInt(respuesta)) - 1;
                }
                case "2" -> {
                    return (Integer.parseInt(respuesta)) - 1;
                }
                case "3" -> {
                    return (Integer.parseInt(respuesta)) - 1;
                }

                default -> {
                    System.out.println("Selecciona una opción válida");
                }
            }
        } while (respuesta != "0");
        return -1;

    }

    public static String palabraElegida() {
        try (BufferedReader br = new BufferedReader(new FileReader(palabras))) {
            String linea;
            List<String> listaPalabras = new ArrayList<>();
            if ((linea = br.readLine()) != null) {
                listaPalabras.add(linea);
            }
            Random rd = new Random();
            int indiceAleatorio = rd.nextInt(listaPalabras.size());
            return listaPalabras.get(indiceAleatorio);
        } catch (Exception e) {
            System.out.println("ERROR en la lectura de palabras.txt");
        }
        return null;
    }

    public static void juego(int estilo, String palabra) {
        boolean continuar = true;
        int intentos = 0;
        String palabraJuego = "_ ".repeat(palabra.length());
        do {
            System.out.println(Ahorcado.listaAhorcado().get(estilo).get(intentos));
            System.out.print("\nIntroduce un carácter:\s");
            char caracter = sc.nextLine().toUpperCase().charAt(1);
        } while (continuar);
    }

    public static void buscarCaracter(String palabra, char caracter) {
        if (palabra.contains(String.valueOf(caracter))) {
            // TODO por aquí me he quedado
        }
    }

}