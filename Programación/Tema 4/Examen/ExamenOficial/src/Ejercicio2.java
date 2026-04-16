
public class Ejercicio2 {
    public static void main(String[] args) {
        String[] juegos = { "Elden Ring", "Minecraft", "Hades" };
        int[][] valoraciones = {
                { 5, 4, 3 },
                { 4, 5, 4 },
                { 3, 4, 5 }
        };
        

        int contadorUsuario;
        int contadorJuego;
        double media;

        System.out.println("Media por juego:");

        for (int i = 0; i < valoraciones[0].length; i++) {
            contadorUsuario = 0;
            for (int j = 0; j < valoraciones.length; j++) {
                
                contadorUsuario += valoraciones[j][i];
            }
            media = (double)(contadorUsuario)/(double)(valoraciones[0].length);
            System.out.println(juegos[i] + ": " + media);
        }

        System.out.println("\nMedia por usuario");

        for (int i = 0; i < valoraciones.length; i++) {
            contadorUsuario = 0;
            for (int j = 0; j < valoraciones[0].length; j++) {
                
                contadorUsuario += valoraciones[i][j];
            }
            media = (double)(contadorUsuario)/(double)(valoraciones[0].length);
            System.out.println("Usuario " + (i+1) + ": " + media);
        }

        

        
        
    }

}
