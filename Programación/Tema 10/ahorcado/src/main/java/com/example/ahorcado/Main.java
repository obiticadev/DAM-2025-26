package com.example.ahorcado;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.example.ahorcado.Clases.Ahorcado;
import com.example.ahorcado.Clases.AhorcadoASCII;
import com.example.ahorcado.Clases.Jugador;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static File palabras = new File("palabras.txt");
    private static File jugadores = new File("jugadores.bin");

    public static void main(String[] args) {
        String respuestaMenu;
        do {
            menu();
            respuestaMenu = sc.nextLine();
            switch (respuestaMenu) {
                case "1" -> {
                    int estilo = option1();
                    if (estilo != -1) {
                        String palabra = palabraElegida();
                        // TODO agregar jugador
                        Ahorcado juego = new Ahorcado(estilo, palabra);
                        juego(juego);
                    }
                }
                case "0" -> {
                    System.out.println("Saliendo del programa...");
                }

                default -> {
                    System.out.println("Selecciona una opción válida");
                }
            }
        } while (!respuestaMenu.equals("0"));
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
                case "1", "2", "3" -> {
                    return Integer.parseInt(respuesta) - 1;
                }
                case "0" -> {
                    return -1;
                }

                default -> {
                    System.out.println("Selecciona una opción válida");
                }
            }
        } while (!respuesta.equals("0"));
        return -1;
    }

    public static void juego(Ahorcado juego) {
        boolean continuar = true;
        do {
            System.out.println(AhorcadoASCII.listaAhorcado().get(juego.getEstilo()).get(juego.getIntentos()));
            System.out.println("\nPalabra actual: " + juego.getPalabraJuego());
            System.out.print("\nIntroduce un carácter:\s");

            String entrada = sc.nextLine();

            if (entrada.isEmpty()) {
                System.out.println("Por favor, introduce al menos un carácter.");
                continue;
            }

            char caracter = entrada.toUpperCase().charAt(0);

            if (!juego.buscarCaracter(caracter)) {
                System.out.println("FALLO");
            } else {
                juego.rellenarPalabra(caracter);
            }

            if (juego.getIntentos() >= 10) {
                System.out.println("Has alcanzado el número máximo de fallos (10). ¡Fin del juego!");
                continuar = false;
            }

        } while (continuar);
    }

    public static String palabraElegida() {
        try (BufferedReader br = new BufferedReader(new FileReader(palabras))) {
            String linea;
            List<String> listaPalabras = new ArrayList<>();
            while ((linea = br.readLine()) != null) {
                listaPalabras.add(linea);
            }
            Random rd = new Random();
            int indiceAleatorio = rd.nextInt(listaPalabras.size());
            return listaPalabras.get(indiceAleatorio).toUpperCase();
        } catch (Exception e) {
            System.out.println("ERROR en la lectura de palabras.txt");
        }
        return null;
    }

    public static void preguntarJugador() {
        System.out.print("Introduce un nombre de jugador:\s");
        String nombre = sc.nextLine().trim().toLowerCase();

    }
}