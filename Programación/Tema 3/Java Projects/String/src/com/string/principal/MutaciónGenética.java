package com.string.principal;

import java.util.Scanner;

/*
 * Disponemos de una molécula de ADN formada por dos cadenas complementarias, cada una con 15 bases nitrogenadas.

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
        StringBuilder[] cadena = new StringBuilder[2];
        char[][] caracter = new char[2][5];
        String cadena1;
        String cadena2;
        boolean hayMutacion = false;
        
    
        System.out.println("""
                ++++++++++++++++++++++++++++++++
                +++ COMPROBACIÓN DE MOLÉCULA +++
                ++++++++++++++++++++++++++++++++\n
                """);
                for (int i = 0; i <= 2; i++) {
                    if(i != 2){
                        cadena[i] = new StringBuilder();
                        for (int j = 0; j < 5; j++) {
                            System.out.print("Introduce de la cadena (" + (i+1) +") el caracter (" + (j+1) + "):");
                            cadena[i].append(lineScan.next().charAt(0)).append(" ");
                            
                            caracter[i][j] = cadena[i].toString().replaceAll(" ", "").toUpperCase().charAt(j);
                        
                            }
                        }else{
                            for (int j = 0; j < 5; j++) {
                               switch (caracter[0][j]) {
                                case 'A' -> {
                                    if (caracter[1][j] != 'T'){
                                        hayMutacion = true;
                                    }
                                }
                                    
                               
                                default -> {

                                }
                                    
                               }
                            }
                            
                        }
                }

                
                
                
                
                // Salida
                System.out.println("\nCadena1: " + cadena[0] + "\nCadena2: " + cadena[1]);

        
    }
}
