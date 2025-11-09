package com.string.principal;

import java.util.Scanner;

/*
 * Disponemos de una molécula de ADN formada por dos cadenas complementarias, cada una con 1LONGITUD_ADN bases nitrogenadas.

Queremos comprobar si la molécula está correctamente ordenada (es decir, si las bases se emparejan según las reglas de complementariedad) o si presenta una mutación.

Para ello:

Se pedirá al usuario que introduzca las dos cadenas, carácter a carácter.

El usuario podrá escribir las bases en mayúscula o minúscula (A, T, C, G o a, t, c, g).

El programa comprobará si las dos cadenas son complementarias correctamente:

A ↔ T

T ↔ A

C ↔ G

G ↔ C

Si el apareamiento es correcto, se mostrarán las dos cadenas en mayúsculas, una debajo de la otra.

Si existe alguna mutación (es decir, una base que no cumple la regla de apareamiento), se mostrarán ambas cadenas en mayúsculas, excepto las bases afectadas por la mutación, que se mostrarán en minúsculas.

Ejemplo correcto:

Cadena 1: A T G C A G T T C A T G A C T
Cadena 2: T A C G T C A A G T A C T G A

Ejemplo con mutación:

Cadena 1: A T G C A G T T C A T G A C T
Cadena 2: T A C G T C A A g T A C T G A

SE PUEDE USAR STRING BUILDER***
 */

public class MutaciónGenética {
    public static void main(String[] args) throws Exception {
        Scanner lineScan = new Scanner(System.in);

        // Declaración de variables
        final int LONGITUD_ADN = 15;
        StringBuilder[] cadena = new StringBuilder[2];
        char[][] caracter = new char[2][LONGITUD_ADN];
        String cadena1;
        String cadena2;
        boolean hayMutacion = false;

        System.out.println("""
                ++++++++++++++++++++++++++++++++
                +++ COMPROBACIÓN DE MOLÉCULA +++
                ++++++++++++++++++++++++++++++++\n
                """);
        for (int i = 0; i <= 2; i++) { // Aquí utilizo tres rangos para que después de recorrer las dos cadenas entre en el else para parametrizar los diferentes casos
            if (i != 2) {
                cadena[i] = new StringBuilder();
                for (int j = 0; j < LONGITUD_ADN; j++) { // Recorremos cada uno de los caracteres introducidos en cada cadena
                    System.out.print("Introduce de la cadena (" + (i + 1) + ") el caracter (" + (j + 1) + "):");
                    cadena[i].append(lineScan.next().charAt(0)).append(" "); // Aquí concatenamos el primer caracter recogido del Scanner y lo concatenamos con un espacio

                    caracter[i][j] = cadena[i].toString().replaceAll(" ", "").toUpperCase().charAt(j);// Aquí guardo en el array bidimensional los caracteres en su posición correcta eliminando los espacios y poniendolos en mayuscula previamente para evitar errores

                }
            } else { // Cuando ya ha recorrido las dos cadenas entramos en el tercer paso para parametrizar
                for (int j = 0; j < LONGITUD_ADN; j++) {
                    switch (caracter[0][j]) {
                        case 'A' -> { // Si A no va con T ponemos el caracter de la cadena 2 en minúscula
                            if (caracter[1][j] != 'T') {
                                caracter[1][j] = Character.toLowerCase(caracter[1][j]);
                                hayMutacion = true;
                            }
                        }
                        case 'T' -> { // Si T no va con A ponemos el caracter de la cadena 2 en minúscula
                            if (caracter[1][j] != 'A') {
                                caracter[1][j] = Character.toLowerCase(caracter[1][j]);
                                hayMutacion = true;
                            }
                        }
                        case 'C' -> { // Si C no va con G ponemos el caracter de la cadena 2 en minúscula
                            if (caracter[1][j] != 'G') {
                                caracter[1][j] = Character.toLowerCase(caracter[1][j]);
                                hayMutacion = true;
                            }
                        }
                        case 'G' -> { // Si G no va con C ponemos el caracter de la cadena 2 en minúscula
                            if (caracter[1][j] != 'C') {
                                caracter[1][j] = Character.toLowerCase(caracter[1][j]);
                                hayMutacion = true;
                            }
                        }

                        default -> { // Si en la primera cadena no hay ninguno de los anteriores casos es que hay mutación seguro y por tanto se pone en minúscula tanto el caracter de la cadena 1 como el de la cadena 2 aunque no he contemplado en dejar en la cadena 2 en mayúscula si contiene un caracter correcto. A vistas del programa final la mutación se da si o sí porque la cadena 1 no comienza bien por tanto cumple con el objetivo del ejercicio
                            caracter[0][j] = Character.toLowerCase(caracter[0][j]);
                            caracter[1][j] = Character.toLowerCase(caracter[1][j]);
                            hayMutacion = true;
                        }

                    }

                }

            }
        }

        // Salida
        System.out.print("\nCadena 1: ");
        for (int j = 0; j < LONGITUD_ADN; j++) { // Aquí colocamos los espacios nuevamente con la transformación final de mayúsculas y minúsculas
            System.out.print(caracter[0][j] + " ");
        }

        System.out.print("\nCadena 2: ");
        for (int j = 0; j < LONGITUD_ADN; j++) {
            System.out.print(caracter[1][j] + " ");
        }
        System.out.println(" ");

    }
}
