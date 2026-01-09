package Clases;

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

    public String binarioADecimal(String entero) {
        String decimal = entero;
        int sumaOperacion = 0;
        for (int i = 0; i < decimal.length(); i++) {
            int operacion = ((int) Math.pow(2, i))
                    * Character.getNumericValue(decimal.charAt(decimal.length() - 1 - i));
            sumaOperacion += operacion;
        }
        decimal = Integer.toString(sumaOperacion);
        return decimal;
    }

    public String octalABinario(long entero) {
        String binario = "";
        String octal = String.valueOf(entero);
        for (int i = 0; i < octal.length(); i++) {
            String binarioIndividual = decimalABinario(Character.getNumericValue(octal.charAt(octal.length() - 1 - i)));
            binario = binarioIndividual + binario;
        }

        return binario;
    }

    public String hexaABinario(String entero) {
        String binario = "";
        String hexa = entero;
        for (int i = 0; i < hexa.length(); i++) {
            switch ((hexa.charAt(hexa.length() - 1 - i))) {
                case 'A' -> {
                    String binarioIndividual = "1010";
                    binario = binarioIndividual + binario;
                }
                case 'B' -> {
                    String binarioIndividual = "1011";
                    binario = binarioIndividual + binario;
                }
                case 'C' -> {
                    String binarioIndividual = "1100";
                    binario = binarioIndividual + binario;
                }
                case 'D' -> {
                    String binarioIndividual = "1101";
                    binario = binarioIndividual + binario;
                }
                case 'E' -> {
                    String binarioIndividual = "1110";
                    binario = binarioIndividual + binario;
                }
                case 'F' -> {
                    String binarioIndividual = "1111";
                    binario = binarioIndividual + binario;
                }

                default -> {
                    String binarioIndividual = decimalABinario(
                            Character.getNumericValue(hexa.charAt(hexa.length() - 1 - i)));
                    while (binarioIndividual.length() % 4 != 0) {
                        binarioIndividual = "0" + binarioIndividual;
                    }
                    binario = binarioIndividual + binario;
                }
            }
        }

        return binario;
    }

    public String octalADecimal(long entrada) {
        String decimal = binarioADecimal(octalABinario(entrada));
        return decimal;
    }

    public String hexaADecimal(String entrada) {
        String decimal = binarioADecimal(hexaABinario(entrada));
        return decimal;
    }

    public String binarioAOctal(String entrada) {
        String octal = decimalAOctal(Integer.parseInt(binarioADecimal(entrada)));
        return octal;
    }

    public String hexaAOctal(String entrada) {
        String hexa = decimalAOctal(Integer.parseInt(hexaADecimal(entrada)));
        return hexa;
    }

    public String binarioAHexa(String entrada) {
        String hexa = decimalAHexadecimal(Integer.parseInt(binarioADecimal(entrada)));
        return hexa;
    }

    public String octalAHexa(long entrada) {
        String hexa = decimalAHexadecimal(Integer.parseInt(octalADecimal(entrada)));
        return hexa;
    }
}
