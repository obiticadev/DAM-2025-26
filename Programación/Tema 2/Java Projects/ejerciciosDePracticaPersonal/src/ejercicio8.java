
import java.util.Scanner;

/*
Enunciado:

Escribe un programa que solicite al usuario ingresar una cantidad de dinero en formato String (por ejemplo, "123.45"). Tu programa debe separar esa cantidad en dos partes: la cantidad de euros (la parte entera) y la cantidad de céntimos (la parte decimal). Para obtener los céntimos, debes realizar una operación matemática que evite problemas de precisión con los double.

Ejemplo de Salida:

Ingresa una cantidad de dinero (ej: 45.67):
87.68
Desglose:
Euros: 87
Céntimos: 68
*/



public class ejercicio8 {
    public static void main(String[] args) throws Exception {

        String num;
        double numConvert;
        int numEntero;
        double decimal;
        int decimalToInt;

        Scanner scan = new Scanner(System.in);
        
        System.out.println("Ingresa una cantidad de dinero (ej: 45.67):");
        num = scan.nextLine();
        numConvert = Double.parseDouble(num);
        numEntero = (int) numConvert;
        decimal = (numConvert - numEntero) * 100;
        decimalToInt = (int) decimal;

        // Salida
        System.out.println("Desglose:");
        System.out.println("Euros: " + numEntero);
        System.out.println("Céntimos: " + decimalToInt);

        
    }
}
