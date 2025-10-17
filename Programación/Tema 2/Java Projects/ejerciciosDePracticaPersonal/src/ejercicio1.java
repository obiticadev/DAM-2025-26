
/*Ejercicio 1: Conversiones Básicas (Widening y Narrowing)

Objetivo: Practicar la conversión implícita y explícita entre tipos numéricos.
Declara una variable int con el valor 120.

Realiza una conversión implícita (widening) de esta variable a una de tipo long,
luego a float
y finalmente a double.
Imprime cada resultado.

Ahora, declara una variable double con el valor 9.78.
Realiza una conversión explícita (narrowing) a int. Imprime el resultado.
Finalmente, convierte ese int resultante a un char. Imprime el carácter.

Punto clave: Observa cómo se pierde la parte decimal en la conversión de double a int y cómo el valor numérico se interpreta como un carácter de la tabla ASCII/Unicode.*/


public class ejercicio1 {
    public static void main(String[] args) throws Exception {
        System.out.println("Ejercicio 1: Conversiones Básicas (Widening y Narrowing)");
        
        int numInt = 120;
        long numLong;
        float numFloat;
        double numDouble;

        double numDouble_2 = 9.78;
        int numInt_2;
        char numChar_2;
        
        System.out.println(numInt + " Este es INT");

        numLong = numInt;
        System.out.println(numLong + " Este es un número LONG");

        numFloat = numLong;
        System.out.println(numFloat + " Este es un número FLOAT");

        numDouble = numFloat;
        System.out.println(numDouble + " Este es un número DOUBLE");


        System.out.println("Segunda parte del ejercicio:\n");

        System.out.println(numDouble_2 + " Este es un DOUBLE que pasará a INT");
        numInt_2 = (int) numDouble_2;
        System.out.println(numInt_2 + " Este es un INT usando narrowing");

        numChar_2 = (char) numInt_2;
        System.out.println(numChar_2 + " Este es el ASCII de " + numInt_2 + ", en este caso es un tabulador");

    }
}
