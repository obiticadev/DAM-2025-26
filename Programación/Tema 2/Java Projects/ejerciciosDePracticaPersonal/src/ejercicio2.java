/*Ejercicio 2: Promoción de Tipos en Expresiones
Objetivo: Entender cómo Java promociona los tipos de datos en operaciones aritméticas.
Declara las siguientes variables:
int num1 = 10;
double num2 = 5.5;
char caracter = 'A'; // Recuerda que su valor numérico es 65
Calcula e imprime el resultado de la suma num1 + num2. ¿De qué tipo es el resultado?
Calcula e imprime el resultado de num1 + caracter. ¿Por qué el resultado es un número y no un carácter?
Punto clave: Java siempre buscará el tipo de dato más grande en la operación para evitar pérdida de información durante el cálculo.*/

public class ejercicio2 {
    public static void main(String[] args) throws Exception {
        int num1 = 10;
        double num2 = 5.5;
        char caracter = 'A'; // Recuerda que su valor numérico es 65
        double suma;
        int suma2;

        
        System.out.println("Calcula e imprime el resultado de la suma num1 + num2. ¿De qué tipo es el resultado?");
        suma = num1 + num2;
        System.out.println(suma + " es double porque tiene que acaparar los dos datos, y en este caso el int no puede contener decimales\nO también se puede hacer narrowing:");
        suma2 = num1 + (int)num2;
        System.out.println(suma2 + " este es un INT\n");

        System.out.println("Calcula e imprime el resultado de num1 + caracter. ¿Por qué el resultado es un número y no un carácter?");
        suma = num1 + caracter;
        System.out.println(suma + " es numérico porque recoge automáticamente el número ASCII para operar\n");



    }
}
