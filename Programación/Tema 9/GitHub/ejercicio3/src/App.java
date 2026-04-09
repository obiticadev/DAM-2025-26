import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

import Clases.Participante;
import DAO.ParticipanteDAO;
import MiExcepcion.MiExcepcion;

public class App {
    private static ParticipanteDAO dao = new ParticipanteDAO();
    private static ArrayList<Participante> registrados = dao.devolverDatos();
    private static HashSet<Participante> asistentesEvento = new HashSet<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        boolean continuar = true;
        String respuestaMenu;
        do {
            menu();
            respuestaMenu = sc.nextLine().toUpperCase();
            switch (respuestaMenu) {
                case "1" -> {
                    registrarParticipante();
                }
                case "2" -> {
                    mostrarParticipantes();
                }
                case "3" -> {
                    confirmarAsistencia();
                }
                case "4" -> {
                    mostrarAsistencia();
                }
                case "5" -> {
                    cancelarRegistro();
                }
                case "6" -> {
                    if (!ordenarAsistentesConfirmados()) {
                        System.out.println("No hay participantes con asistencia confirmada");
                    }
                }

                case "S" -> {
                    System.out.println("Saliendo del programa...");
                    continuar = false;
                }

                default -> {
                    System.out.println("Selecciona una opción válida");
                }
            }
        } while (continuar);
    }

    private static void menu() {
        System.out.print("""
                \n=== MENÚ ===
                1. Registrar participante
                2. Mostrar participantes
                3. Confirmar asistencia al evento
                4. Mostrar asistentes al evento
                5. Cancelar Registro
                6. Mostrar lista de participantes con asistencia confirmada

                S. Salir

                Selecciona una opción:
                """);
    }

    private static String preguntaString(String pregunta) {
        System.out.print(pregunta);
        String respuesta = sc.nextLine();
        return respuesta;
    }

    private static boolean registrarParticipante() {
        String nombre = preguntaString("Introduce un nombre: ");
        String email = preguntaString("Introduce el email: ");
        try {
            dao.agregarRegistro(nombre, email);
            return true;
        } catch (MiExcepcion e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    private static void mostrarParticipantes() {
        if (registrados.size() == 0) {
            System.out.println("No hay participantes registrados");
        } else {
            System.out.println();
            for (int i = 0; i < registrados.size(); i++) {
                System.out.println((i + 1) + ". " + registrados.get(i).toString());
            }
        }
    }

    private static boolean confirmarAsistencia() {
        int num = Integer.valueOf(preguntaString("Introduce un número id: "));
        for (Participante p : registrados) {
            if (p.existeId(num)) {
                if (asistentesEvento.add(p)) {
                    return true;
                }
            }
        }
        return false;

    }

    private static void mostrarAsistencia() {
        if (asistentesEvento.size() == 0) {
            System.out.println("No hay asistentes con confirmación");
        } else {
            for (Participante p : asistentesEvento) {
                System.out.println(p.toString());

            }
        }
    }

    private static void cancelarRegistro() {
        int num = Integer.valueOf(preguntaString("Introduce un número id: "));
        Participante p;
        Iterator<Participante> it = registrados.iterator();
        while (it.hasNext()) {
            p = it.next();
            if (p.getId() == num && !asistentesEvento.contains(p)) {
                it.remove();
            }
        }

    }

    private static boolean ordenarAsistentesConfirmados() {
        if (asistentesEvento.size() == 0) {
            return false;
        }
        ArrayList<Participante> listaOrdenada = new ArrayList<>(asistentesEvento);
        Collections.sort(listaOrdenada);
        for (Participante p : listaOrdenada) {
            System.out.println(p.toString());
        }
        return true;
    }

}
