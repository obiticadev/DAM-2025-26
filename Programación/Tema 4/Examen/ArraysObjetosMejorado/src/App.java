import java.util.Scanner;

import Clases.Videojuego;

public class App {
    private static Scanner sc = new Scanner(System.in);
    private static Videojuego[] videojuego = {
        new Videojuego("The Legend of Zelda"),
        new Videojuego("Super Mario Bros"),
        new Videojuego("Minecraft"),
        new Videojuego("Final Fantasy VII"),
        new Videojuego("The Witcher 3"),
        new Videojuego("God of War"),
        new Videojuego("Halo")
        
    };
    public static void main(String[] args) throws Exception {
        

        boolean continuar = true;
        String respuesta;

        do {
            menu();
            switch (sc.nextLine()) {
                case "1" -> {
                    System.out.println("VALORA UN VIDEOJUEGO");
                    System.out.println(listarJuegos());
                    respuesta = sc.nextLine();
                    if (validarEntrada(respuesta)) {
                        int respuestaInt = Integer.parseInt(respuesta);
                        if (validarSeleccionJuego(respuestaInt)) {
                            System.out.print("Introduce la puntuación de " + videojuego[respuestaInt-1].getTitulo() + ": ");
                            if (videojuego[respuestaInt-1].valorarVidejuego(Double.parseDouble(sc.nextLine()))) {
                                System.out.println("Puntuación guardada");
                            } else {
                                System.out.println("Introduce solo puntos entre 0 y 10");
                            }
                        } else {
                            System.out.println("Selecciona un juego de la lista");
                        }
                    } else {
                        System.out.println("Introduce un número válido");
                    }
                }

                case "2" -> {
                    
                }

                case "3" -> {
                    
                }

                case "4" -> {
                    
                }

                case "0" -> {
                    
                }
            
                default -> {

                }
            }
        } while (continuar);

    }

    public static void menu() {
        System.out.print("""
                === PLATAFORMA DE VALORACIÓN DE VIDEOJUEGOS ===
                === MENÚ ===
                1. Valorar un videojuego
                2. Mostrar valoraciones medias
                3. Mostrar número de valoraciones
                4. Mostrar juego mejor valorado

                0. Salir

                Elige una opción: """);
    }

    public static boolean validarEntrada(String entrada){
        try {
            Integer.parseInt(entrada);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean validarSeleccionJuego(int entrada){
        if (entrada > 0 && entrada <= videojuego.length) {
            return true;
        } else {
            return false;
        }
    }
    
    public static String listarJuegos(){
        StringBuilder salida = new StringBuilder();
        for (int i = 0; i < videojuego.length; i++) {
            salida.append(i+1)
                  .append(". ")
                  .append(videojuego[i].getTitulo())
                  .append("\n");
        }
        return salida.toString();
    }


}