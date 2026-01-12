package Utilidades;

public class Funciones {
    public static char[] abecedario() {

        char[] abecedario = new char['Z' - 'A' + 1];
        for (char i = 'A'; i <= 'Z'; i++) {
            abecedario[i - 'A'] = i;
        }

        return abecedario;

    }

    public static char[] desplazamientoDeLetras(char[] abecedario, int desplazamiento) {
        for (int i = 0; i < abecedario.length; i++) {
            if (abecedario[i] + desplazamiento > 255) {
                char ajuste = (char) (abecedario[i] + desplazamiento - 255);
                abecedario[i] = (char) (32 + ajuste - 1);
            } else {
                abecedario[i] += desplazamiento;

            }
        }

        return abecedario;
    }

    public static String cifrarTexto(String entrada, int desplazamiento) {
        String textoCifrado = "";
        String[] palabrasSeparadas = entrada.split(" ");
        StringBuilder resultado = new StringBuilder();
        for (String palabrasSeparada : palabrasSeparadas) {
            char[] arrayPalabra = palabrasSeparada.toCharArray();
            desplazamientoDeLetras(arrayPalabra, desplazamiento);
            String palabraCifradaString = new String(arrayPalabra);
            resultado.append(palabraCifradaString);
        }
        textoCifrado = resultado.toString();

        return textoCifrado;
    }

}
