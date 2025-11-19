/*
Una estación de servicio organiza el lavado de coches en turnos.

Cada coche recibe un número de turno (entero).

Se quiere determinar a qué pista de lavado le corresponde el coche, según su turno.

La pista se calcula con la operación:

pista = turno % NUM_PISTAS

Teniendo en cuenta que el lavado tiene 3 pistas.

Además, cada pista tiene un código de letra asociado:

0 → 'A'

1 → 'B'

2 → 'C' (puedes calcular el carácter sumando 'A' + pista).

El programa debe pedir al usuario el número de turno (que puede empezar por 0) , calculará la pista y se la mostrará al usuario con su letra.

Introduce tu número de turno: 7

Número de pistas: 3
Tu turno es: 7
Te corresponde la pista número: 1
Identificada con la letra: B
 */

import java.util.Scanner;

public class CalculadoraDeTurnosDeLavadoDeCoches {
    public static void main(String[] args) {
        

        // Declaración de variables
        final int NUM_PISTAS = 3;
        int turno;
        int box;
        final int ASCII_CONVERT = 64;
        char boxAsciiToChar;

        Scanner numScanner = new Scanner(System.in);

        System.out.print("Introduce tu número de turno: ");
        turno = numScanner.nextInt();

        box = turno % NUM_PISTAS + 1;
        boxAsciiToChar = (char)(box + ASCII_CONVERT);

        
        System.out.print("\nNúmero de pistas: " + NUM_PISTAS);
        System.out.print("\nTu turno es: " + turno);
        System.out.print("\nTe corresponde la pista número: " + box);
        System.out.println("\nIdentificada con la letra: " + boxAsciiToChar);
        
    }
}
