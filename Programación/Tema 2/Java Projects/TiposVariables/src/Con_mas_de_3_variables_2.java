import java.util.Scanner;

public class Con_mas_de_3_variables_2 {


/*
 * Pide al usuario la duración de tres canciones en segundos.
 * Calcula y muestra el total en minutos y segundos.
 */
    public static void main(String[] args) {
        // Declaración de variables
        int song1;
        int song2;
        int song3;
        int suma;
        int min;
        int seg;


        // Arrancamos la librería de Scanner
        Scanner numScanner = new Scanner(System.in);

        System.out.println("TE SUMO LOS SEGUNDOS DE TUS TRES CANCIONES FAVORITAS\nY TE LAS SUMO PASANDO A MINUTOS Y SEGUNDOS");
        
        System.out.println("¿Cuántos segundos tiene tu primera canción?");
        song1 = numScanner.nextInt();
        System.out.println("¿Cuántos segundos tiene tu segunda canción?");
        song2 = numScanner.nextInt();
        System.out.println("¿Cuántos segundos tiene tu tercera canción?");
        song3 = numScanner.nextInt();

        suma = song1 + song2 + song3;
        min = suma / 60;
        seg = suma % 60;

        System.out.println("La suma de " + song1 + "s de la primera canción, " + song2 + "s de la segunda canción, " + song3 + "s de la tercera canción son " + min + "min con " + seg + "seg");
    }
}
