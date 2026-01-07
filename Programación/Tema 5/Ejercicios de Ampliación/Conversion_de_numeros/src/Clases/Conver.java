package Clases;

/*
DATOS NECESARIOS:
- 
*/

public class Conver {

    public Conver() {
    }

    public String decimalABinario(int entero) {
        String binario = "";
        int enteroTemp = entero;
        int contador = 0;

        while ((enteroTemp / 2) >= 1) {
            enteroTemp = enteroTemp / 2;
            contador++;
        }

        int[] array = new int[contador + 1];

        for (int i = 0; i < array.length; i++) {
            if (i == array.length - 1) {
                array[i] = entero;
            } else {
                array[i] = entero % 2;
                entero = entero / 2;

            }
        }

        for (int i = 0; i < array.length; i++) {
            binario += array[array.length - 1 - i];
        }

        return binario;
    }

    public String decimalAOctal(int entero) {
        String binario = decimalABinario(entero);
        int octales = 0;

        while (binario.length() % 3 != 0) {
            binario = "0" + binario;
        }
        int[] arrayBinario = new int[binario.length()];
        if (binario.length() <= 3) {
            octales = 1;
        } else {
            octales = (binario.length() / 3);
        }

        int[] arrayOctal = new int[octales];
        for (int i = 0; i < octales; i++) {
            int octalIndividual = 0;
            for (int j = 0; j < 3; j++) {
                int operacion = ((int) Math.pow(2, j))
                        * Character.getNumericValue(binario.charAt(arrayBinario.length - 1 - ((i * 3) + j)));
                octalIndividual += operacion;
            }
            arrayOctal[octales - 1 - i] = octalIndividual;
        }
        String octal = "";
        for (int i = 0; i < arrayOctal.length; i++) {
            octal += arrayOctal[i];
        }
        return octal;
    }
}
