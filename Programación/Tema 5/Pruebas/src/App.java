import java.util.Arrays;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
        Scanner scNum = new Scanner(System.in);

        int entrada;
        int entradaTemp;
        int contador = 0;

        System.out.println("Introduce un número");
        entrada = scNum.nextInt();
        entradaTemp = entrada;

        while ((entradaTemp / 2) >= 1) {
            entradaTemp = entradaTemp / 2;
            contador++;
        }
        System.out.println(contador);
        int[] array = new int[contador + 1];

        for (int i = 0; i < array.length; i++) {
            if (i == array.length - 1) {
                array[i] = entrada;
            } else {
                array[i] = entrada % 2;
                entrada = entrada / 2;

            }
        }

        System.out.println(Arrays.toString(array));

    }
}
