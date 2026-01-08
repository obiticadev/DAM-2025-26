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

    public String decimalAHexadecimal(int entero) {
        String binario = decimalABinario(entero);

        int hexa = 0;

        while (binario.length() % 4 != 0) {
            binario = "0" + binario;
        }
        int[] arrayBinario = new int[binario.length()];
        if (binario.length() <= 4) {
            hexa = 1;
        } else {
            hexa = (binario.length() / 4);
        }

        String[] arrayHexa = new String[hexa];
        for (int i = 0; i < hexa; i++) {
            int hexaIndividual = 0;
            String hexaIndividualString;
            for (int j = 0; j < 4; j++) {
                int operacion = ((int) Math.pow(2, j))
                        * Character.getNumericValue(binario.charAt(arrayBinario.length - 1 - ((i * 4) + j)));
                hexaIndividual += operacion;
            }

            switch (hexaIndividual) {
                case 10 -> {
                    hexaIndividualString = "A";
                }
                case 11 -> {
                    hexaIndividualString = "B";
                }
                case 12 -> {
                    hexaIndividualString = "C";
                }
                case 13 -> {
                    hexaIndividualString = "D";
                }
                case 14 -> {
                    hexaIndividualString = "E";
                }
                case 15 -> {
                    hexaIndividualString = "F";
                }
                default -> {
                    hexaIndividualString = Integer.toString(hexaIndividual);
                }
            }
            arrayHexa[hexa - 1 - i] = hexaIndividualString;
        }
        String hexadecimal = "";
        for (int i = 0; i < arrayHexa.length; i++) {
            hexadecimal += arrayHexa[i];
        }

        return hexadecimal;
    }

    public String binarioADecimal(long entero) {
        String binario = Long.toString(entero);
        int sumaOperacion = 0;
        for (int i = 0; i < binario.length(); i++) {
            int operacion = ((int) Math.pow(2, i))
                    * Character.getNumericValue(binario.charAt(binario.length() - 1 - i));
            sumaOperacion += operacion;
        }
        binario = Integer.toString(sumaOperacion);
        return binario;
    }
}
