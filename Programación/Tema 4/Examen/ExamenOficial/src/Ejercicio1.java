import java.util.Arrays;
import java.util.Scanner;

public class Ejercicio1 {
    private static String[] videojuegos = { "The Legend of Zelda", "Super Mario Bros", "Minecraft", "Final Fantasy VII",
            "The Witcher 3", "God of War", "Halo" };
    private static int[] puntuacionTotal = new int[videojuegos.length];
    private static int[] contador = new int[videojuegos.length];
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {

        boolean continuar = true;
        String respuestaMenu;
        int respuestaMenuInt;
        Arrays.fill(puntuacionTotal, 0);
        Arrays.fill(contador, 0);

        do {
            System.out.print("""
                
                    === PLATAFORMA DE VALORACIÓN DE VIDEOJUEGOS ===
                    === MENÚ ===
                    1. Valorar un videojuego
                    2. Mostrar valoraciones medias
                    3. Mostrar número de valoraciones
                    4. Mostrar juego mejor valorado

                    0. Salir

                    Elige una opción: 
                                        """);
            respuestaMenu = sc.nextLine();
            if (validarEntrada(respuestaMenu)) {
                respuestaMenuInt = Integer.parseInt(respuestaMenu);
                switch (respuestaMenuInt) {
                    case 1 -> {
                        valorarVideojuego();
                    }
                    case 2 -> {
                        mostrarValoracionesMedias();
                    }
                    case 3 -> {
                        mostrarNumeroDeValoraciones();
                    }
                    case 4 -> {
                        mostrarJuegoMejorValorado();
                    }
                    case 0 -> {
                        System.out.println("\nGracias por usar la plataforma. ¡Hasta pronto!");
                        continuar = false;
                    }

                    default -> {
                        System.out.println("Selecciona una opción válida");
                    }
                }
            } else {
                System.out.println("Introduce un numero válido");
            }
        } while (continuar);

    }

    // Esto nos evita tener que aplicar try and catch en cada sitio para verificar si podemos convertirlo o no
    public static boolean validarEntrada(String entrada) {
        int salida;
        try {
            salida = Integer.parseInt(entrada);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }

    // Este método trabaja todo el bloque de la primera opción donde recoge toda clase de excepciones y solo permite recoger puntuaciones enteras entre 1 y 5
    public static void valorarVideojuego(){
        String respuesta;
        int respuestaInt;
        String puntuacion;
        int puntuacionInt;
        
        System.out.println("\nSelecciona un videojuego para valorar:");
        for (int i = 0; i < videojuegos.length; i++) {
            System.out.println((i+1) + ". " + videojuegos[i]);
        }
        System.out.print("\nIntroduce el número del juego: ");
        respuesta = sc.nextLine();
        // Si la entrada se puede formatear con Integer seguimos operando
        if (validarEntrada(respuesta)) {
            respuestaInt = (Integer.parseInt(respuesta));
            // Filtramos entre los valores de 1 al rango de nuestro array que es de 7
            if (respuestaInt > 0 && respuestaInt <= videojuegos.length) {
                System.out.print("Introduce la puntuación de " + videojuegos[respuestaInt - 1] + " (1 a 5): ");
                puntuacion = sc.nextLine();
                if (validarEntrada(puntuacion)) {
                    puntuacionInt = Integer.parseInt(puntuacion);
                    if (puntuacionInt > 0 && puntuacionInt <= 5) {
                        puntuacionTotal[respuestaInt - 1] += puntuacionInt;
                        contador[respuestaInt - 1] += 1;

                        System.out.println("\nValoración guardada correctamente");
                        
                    } else {
                        System.out.println("\nSolo se puede introducir valores enteros del 1 al 5");
                    }
                }
            } else {
                System.out.println("\nSelecciona solo entre 1 y " + videojuegos.length );
            }
        } else {
            System.out.println("Selecciona una opción válida");
        }
    }

    public static void mostrarValoracionesMedias(){
        System.out.println();
        for (int i = 0; i < videojuegos.length; i++) {
            if (contador[i] != 0) {
                double media = (double)(puntuacionTotal[i])/(double)(contador[i]);
                System.out.println(videojuegos[i] + ": " + media);
                
            } else {
                System.out.println(videojuegos[i] + ": Sin valoraciones");
            }
        }
    }

    public static void mostrarNumeroDeValoraciones(){
        System.out.println();
        for (int i = 0; i < videojuegos.length; i++) {
            System.out.println(videojuegos[i] + ": " + contador[i] + " valoraciones");
        }
    }

    // Se valora por quien tiene la puntuación total más grande, de forma que si Halo tiene de media 4.5 porque sus dos puntuaciones son de 4 y de 5 su puntuación total es de 9. Aunque final fantasy tuviera de media 5 puntos con una sola puntuación, halo seguiría estando por encima puesto que su suma total de puntos es superior a la de final fantasy
    public static void mostrarJuegoMejorValorado(){
        System.out.println();
        int topNumValoraciones = contador[0];
        String topJuego = videojuegos[0];
        int topPuntuacion = puntuacionTotal[0];
        double topMedia;

        for (int i = 1; i < videojuegos.length; i++) {
            if (puntuacionTotal[i] > puntuacionTotal[i-1]) {
                topNumValoraciones = contador[i];
                topJuego = videojuegos[i];
                topPuntuacion = puntuacionTotal[i];
            }
        }

        topMedia = (double)(topPuntuacion) / (double)(topNumValoraciones);

        System.out.println(topJuego + " con una media de " + topMedia);
    }
}
