package com.arrays.muyFaciles.ejercicio8;

import java.util.Scanner;

/*
 * Tenemos una colección de palabras {"Sol", "Mar", "Cielo", "Campo", "Luna", "Árboles"}. Preguntaremos al usuario una palabra, y debemos comprobar si la palabra es una de nuestras palabras de la categoría "Naturaleza". Debes hacer una función, que busque la palabra que introduce el usuario en el array, y devolverá un booleano. Con el resultado de la función se imprimirá el mensaje: "La palabra xxxx es de la colección "Naturaleza" / "La palabra XXXX no es de nuestra colección"
 */

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] coleccion = { "Sol", "Mar", "Cielo", "Luna", "Árboles" };
        String respuesta;
        boolean encontrado = false;

        System.out.println("Dame una palabra y comprobamos si se encuentra en la colección:");
        respuesta = sc.nextLine();

        for (int i = 0; i < coleccion.length; i++) {
            if (coleccion[i].equals(respuesta)) {

                encontrado = true;

            }
        }
        if (encontrado) {
            System.out.println(respuesta + " se encuentra en la colección");

        } else {
            System.out.println(respuesta + " no se encuentra en la colección");

        }

    }
}
