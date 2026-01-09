import java.util.Arrays;
import java.util.Scanner;

import Utilidades.*;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        
        final int DESPLAZAMIENTO = 2;
        
        char[] abecedario = Funciones.abecedario();
        System.out.println(Arrays.toString(abecedario));


        Funciones.desplazamientoDeLetras(abecedario, DESPLAZAMIENTO);

        System.out.println(Arrays.toString(abecedario));

        System.out.println("Introduce un texto a cifrar:");
        String entrada = sc.nextLine();

        Funciones.cifrarTexto(entrada);


        
        
    }

    
    
}
