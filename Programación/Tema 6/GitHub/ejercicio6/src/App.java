import java.util.Scanner;

import Clases.Sala;

public class App {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        final int NUM_MAX_SALA = 2;
        
        Sala[] sala = new Sala[NUM_MAX_SALA];

        for (int i = 0; i < sala.length; i++) {
            sala[i] = new Sala(2, "Suite", 3);
            if (sala[i] != null) {
                if (sala[i].isDisponible() == true) {
                System.out.println("La sala " + (i+1) + " se encuentra disponible:");
                System.out.println(sala[i].mostrarInformacion());
            } else {
                System.out.println("La sala " + (i+1) + " se encuentra ocupada");
            }
            }
        }

        if (sala[0].reservarSala("342hg")) {
            System.out.println("Operación realizada con éxito");   
        } else {
            System.out.println("Está ocupado");
        }

        if (sala[0].reservarSala("asjghlaks")) {
            System.out.println("Operación realizada con éxito");   
        } else {
            System.out.println("Está ocupado");
        }

        if (sala[0].liberarSala("342hg")) {
            System.out.println("Se ha liberado");
        } else {
            System.out.println("No se puede");
        }

        for (int i = 0; i < sala.length; i++) {
            if (sala[i] != null) {
                if (sala[i].isDisponible() == true) {
                System.out.println("La sala " + (i+1) + " se encuentra disponible:");
                System.out.println(sala[i].mostrarInformacion());
            } else {
                System.out.println("La sala " + (i+1) + " se encuentra ocupada");
            }
            }
        }



        

        


        
    }
}
