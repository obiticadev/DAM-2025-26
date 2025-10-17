/*Ejercicio 3: Conversión entre String y Tipos Numéricos
Objetivo: Practicar el uso de los métodos de las clases envolventes (Integer, Double) para las conversiones.
Declara una variable String con el valor "250".
Conviértela a un tipo int usando Integer.parseInt().
Súmale 50 al número convertido e imprime el resultado para verificar que la operación es correcta.
Ahora, declara un double con el valor 99.99.
Conviértelo a String usando String.valueOf() y concaténale el texto " USD". Imprime el resultado final.
Punto clave: Recuerda que no puedes hacer un casting directo como (int) "250". Siempre debes usar los métodos parse.*/

public class ejercicio3 {
    public static void main(String[] args) throws Exception {
        String numString = "250";
        int numInt = Integer.parseInt(numString);
        System.out.println(numInt);

        double numDouble = 99.99;
        String numToString = String.valueOf(numDouble) + " USD";
        System.out.println(numToString);
    }
}
