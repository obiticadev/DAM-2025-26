import java.util.Arrays;
import java.util.Scanner;

import Clases.Astronauta;
import Clases.DiaMision;
import Clases.Mision;
import Enum.Evento;
import Excepciones.DiaRepetidoOInferior;
import Excepciones.DistanciaNegativaException;
import Excepciones.IdRepetido;

public class App {
    private static Scanner sc = new Scanner(System.in);
    private static Mision artemisII = new Mision("Artemis II", 24000);

    public static void main(String[] args) {

        boolean continuar = true;
        String respuestaMenu;
        while (continuar) {
            menu();
            respuestaMenu = sc.nextLine();
            try {
                switch (respuestaMenu) {
                    case "1" -> {
                        agregarAstronauta();
                    }
                    case "2" -> {
                        if (!artemisII.isMisionFinalizada()) {
                            agregarDiaMision();
                        } else {
                            System.out.println("Misión completada, no se puede avanzar más");
                        }
                    }
                    case "3" -> {

                    }
                    case "4" -> {

                    }
                    case "5" -> {

                    }
                    case "0" -> {
                        System.out.println("Saliendo del programa...");
                        continuar = false;
                    }

                    default -> {
                        System.out.println("Selecciona una opción válida");
                    }
                }

            } catch (IdRepetido e) {
                System.out.println(e.getMessage());
            } catch (DistanciaNegativaException e) {
                System.out.println(e.getMessage());
            } catch (DiaRepetidoOInferior e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Algo malo ha sucedido");
            }
        }
    }

    private static void menu() {
        System.out.println("""
                MENÚ DEL PROGRAMA

                1) Añadir astronauta
                2) Registrar día de misión
                3) Mostrar progreso
                4) Registrar evento
                5) Finalizar misión

                0) Salir
                """);
    }

    private static String pruguntaString(String pregunta) {
        System.out.print(pregunta);
        return sc.nextLine();
    }

    private static double preguntaDouble(String pregunta) {
        boolean continuar = true;
        do {
            System.out.print(pregunta);
            try {
                double respuestaDouble = Double.parseDouble(sc.nextLine());
                return respuestaDouble;
            } catch (Exception e) {
                System.out.println("Introduce un entero positivo");
            }

        } while (continuar);
        return -1;
    }

    private static Evento preguntaEvento(String pregunta) {
        boolean continuar = true;
        String respuesta;
        Evento respuestaEvento = Evento.DESPEGUE;
        do {
            try {
                System.out.print(pregunta);
                respuesta = sc.nextLine().toUpperCase().trim().replaceAll("\\s+", "_");
                respuestaEvento = Evento.valueOf(respuesta);
                return respuestaEvento;

            } catch (Exception e) {
                System.out.println("Selecciona una opción válida: " + Arrays.toString(Evento.values()));
            }

        } while (continuar);
        return respuestaEvento;

    }

    // public Astronauta(int id, String nombre, int experiencia)
    private static void agregarAstronauta() throws Exception {
        int id = (int) (preguntaDouble("Introduce un id: "));
        String nombre = pruguntaString("Introduce un nombre: ");
        int experiencia = (int) (preguntaDouble("Intruduce los años de experiencia: "));

        if (id < 0 || experiencia < 0) {
            throw new Exception("ERROR: id o experiencia negativa");
        }

        artemisII.agregarAstronauta(new Astronauta(id, nombre, experiencia));
    }

    // public DiaMision(int numeroDia, double distanciaRecorrida, Evento evento)
    // throws DistanciaNegativaException
    private static boolean agregarDiaMision() throws DistanciaNegativaException, DiaRepetidoOInferior {
        int numeroDia = (int) (preguntaDouble("Introduce el número del día: "));
        double distanciaRecorrida = preguntaDouble("Distancia recorrida: ");
        Evento evento = preguntaEvento(
                "Introduce el evento que está ocurriendo (" + Arrays.toString(Evento.values()) + "): ");
        artemisII.agregarDiaMision(new DiaMision(numeroDia, distanciaRecorrida, evento));
        return artemisII.isMisionFinalizada();
    }

    private static void mostrarProgreso() {

    }

}
