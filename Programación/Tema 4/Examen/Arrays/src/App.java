import java.util.Scanner;

import Clases.Plataforma;

public class App {
    private static Plataforma consola = new Plataforma();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        boolean continuar = true;
        String respuesta;

        do {
            menu();
            respuesta = sc.nextLine();
            switch (respuesta) {
                case "1" -> {
                    System.out.println("=== VALORAR UN VIDEOJUEGO ===");
                    valorarVideojuego();
                }
                case "2" -> {
                    System.out.println("=== VALORACIONES MEDIAS ===");
                    System.out.println(consola.listarValoracionMedia());
                }
                case "3" -> {
                    System.out.println("=== NÚMERO DE VALORACIONES ===");
                    System.out.println(consola.listarNumeroValoraciones());
                }
                case "4" -> {
                    
                }
                case "5" -> {
                    System.out.println("Saliendo del programa...");
                    continuar = false;
                }

                default -> {

                }
            }
        } while (continuar);

    }

    public static boolean validarEntero(String entrada) {
        try {
            Integer.parseInt(entrada);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public static void menu() {
        System.out.println("""
                === PLATAFORMA DE VALORACIÓN DE VIDEOJUEGOS ===
                === MENÚ ===
                1. Valorar un videojuego
                2. Mostrar valoraciones medias
                3. Mostrar número de valoraciones
                4. Mostrar juego mejor valorado
                0. Salir
                """);
    }

    public static void valorarVideojuego() {
        String error = "Selecciona un juego de la lista";
        String juegoSeleccionado;
        String puntuacionSeleccionada;
        boolean continuar = true;

        System.out.println("Selecciona un juego:");
        do {
            System.out.println(consola.listar());
            juegoSeleccionado = sc.nextLine();
            if (validarEntero(juegoSeleccionado)) {
                if (consola.validarJuegoSeleccionado(Integer.parseInt(juegoSeleccionado))) {
                    System.out.println("Introduce la puntuación del " + consola.getPUNTUACION_MIN() + " al " + consola.getPUNTUACION_MAX() + " para " + consola.getJuegos()[Integer.parseInt(juegoSeleccionado)-1]);
                    puntuacionSeleccionada = sc.nextLine();
                    if (validarEntero(puntuacionSeleccionada)) {
                        if (consola.validarPuntuacion(Integer.parseInt(puntuacionSeleccionada))) {
                            consola.puntuarJuego(Integer.parseInt(juegoSeleccionado), Integer.parseInt(puntuacionSeleccionada));
                            continuar = false;
                        } else {
                            System.out.println(error);
                        }
                    } else {
                        System.out.println(error);
                    }
                } else {
                    System.out.println(error);
                }
            } else {
                System.out.println(error);
            }
        } while (continuar);

    }
}
