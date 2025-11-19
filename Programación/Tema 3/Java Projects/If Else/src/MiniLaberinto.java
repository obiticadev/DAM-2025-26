/* Mini Laberinto con Condicionales

En este ejercicio vamos a construir un mini-laberinto en Java, usando condicionales (if/else).

El objetivo es que el jugador pueda tomar decisiones respondiendo con true o false, y que en función de sus respuestas el programa muestre diferentes finales.

¿Cuál es nuestro objetivo de aprendizaje?

Practicar el uso de condicionales simples y anidados.

Entender cómo una decisión cambia el flujo de un programa.

Familiarizarse con la entrada de datos mediante Scanner. */

import java.util.Scanner;

public class MiniLaberinto {
    public static void main(String[] args) {

        int optionSelect;

        Scanner boolScanner = new Scanner(System.in);
        System.out.println("BIENVENIDO A MI LABERINTO, ¿TE ATREVES A AVENTURARTE?");
        System.out.println("Primera pregunta: ¿Quién fue príncipe de Asgard,\n1) Oliver\n2) Natalia");
        optionSelect = boolScanner.nextInt();

        if (optionSelect == 1) {
            System.out.println("Muy bien, sigamos...");
            System.out.println("\n¿Quén fue ganador del Oscar 2023?\n1) Marta Uruguay\n2) Oliver");
            optionSelect = boolScanner.nextInt();
            if (optionSelect == 2) {
                System.out.println("Muy bien, sigamos...");
                System.out.println(
                        "¿Sabrías decirme si Oliver es más alto que los demás\n1) Claro que sí, porque Oliver es el más alto jeje\n2) No lo sé :(");
                optionSelect = boolScanner.nextInt();
                if (optionSelect == 1) {
                    System.out.println("Felicidades!!! has contestado correctamente a todas las preguntas");
                } else {
                    System.out.println("Casi, no te faltaba nada, inténtalo otra vez");
                }
            } else {
                System.out.println(
                        "Oooh qué pena, inténtalo otra vez\nTienes una última oportunidad para declararte perdedor o superperdedor");
                System.out.println("Quién es el mejor?\n1) Oliver\n2) Otro que no sea Oliver");
                optionSelect = boolScanner.nextInt();
                if (optionSelect == 1) {
                    System.out.println(
                            "Felicidades, has acertado pero te acabas de convertir en perdedor, no superperdedor");
                } else {
                    System.out.println("Oooh qué pena,te acabas de convertir en superperdedor");
                }
            }
        } else {
            System.out.println(
                    "Oooh qué pena, inténtalo otra vez\nTienes una última oportunidad para declararte perdedor o superperdedor");
            System.out.println("Quién es el mejor?\n1) Oliver\n2) Otro que no sea Oliver");
            optionSelect = boolScanner.nextInt();
            if (optionSelect == 1) {
                System.out
                        .println("Felicidades, has acertado pero te acabas de convertir en perdedor, no superperdedor");
            } else {
                System.out.println("Oooh qué pena,te acabas de convertir en superperdedor");
            }
        }
    }
}
