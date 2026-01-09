package Utilidades;

import java.util.Arrays;

public class Funciones {
    public static char[] abecedario(){

        char[] abecedario = new char['Z'-'A' + 1];
        for (char i = 'A'; i <= 'Z'; i++) {
            abecedario[i -'A'] = i;
        }

        return abecedario;
        
    }

    public static char[] desplazamientoDeLetras(char[] abecedario, int desplazamiento){
        for (int i = 0; i < abecedario.length; i++) {
            if (abecedario[i] + desplazamiento > 'Z') {
                char ajuste = (char) (abecedario[i] + desplazamiento - 'Z');
                abecedario[i] = (char) ('A' + ajuste - 1);
            } else {
                abecedario[i] += desplazamiento;
                
            }
        }

        return abecedario;
    }

    public static String cifrarTexto(String entrada){
        String textoCifrado = "";
        entrada.indexOf(" ");
        return textoCifrado;
    }
    
}
