package Herramientas;

import java.time.LocalTime;
import java.util.Scanner;

import Clases.Coche;
import Clases.VehiculoAparcado;
import DAO.DAOParking;
import Excepciones.MiExcepcion;
import Interfaz.Lavable;

public class MetodosExternos {
    private static Scanner sc = new Scanner(System.in);

    // new Coche("1234ABC", "Ford", "TurboMill", LocalTime.now().minusHours(4), 4,
    // true)
    public static VehiculoAparcado registrarEntrada() {
        boolean continuar = false;
        do {
            String matricula = capturarRespuesta("Matrícula: ").toUpperCase();
            String marca = capturarRespuesta("Marca: ");
            String modelo = capturarRespuesta("Modelo: ");
            LocalTime horaEntrada = LocalTime.now();
            int numPuertas = capturarRespuestaInt("Número de puertas: ");
            Boolean esSuv = capturarRespuestaBool("¿Es suv? responde con 'True' o 'False'");
            try {
                return new Coche(matricula, marca, modelo, horaEntrada, numPuertas, esSuv);
            } catch (MiExcepcion e) {
                System.out.println("La matrícula: " + e.getMatricula() + "registrada a las " + e.getHoraEntrada()
                        + "no cumple con las condiciones de '1234ABC'");
            }
        } while (!continuar);
        return null;

    }

    public static Boolean registrarSalida(DAOParking dao) {
        String matricula = capturarRespuesta("Matrícula del vehícula a registrar salida: ").toUpperCase();
        return dao.registrarSalida(matricula) ? true : false;
    }

    public static void asignarLavado(DAOParking dao) {
        String matricula = capturarRespuesta("Matrícula del vehícula a registrar salida: ").toUpperCase();
        VehiculoAparcado v = dao.buscarVehiculo(matricula);
        if (v == null) {
            System.out.println("Error: Vehículo no encontrado");

        } else if (v instanceof Lavable vehiculoLavable) {
            vehiculoLavable.asignarLavado();
            System.out.println("Lavado asignado correctamente al vehículo: " + matricula);
        } else {
            System.out.println("Este vehículo no permite el servicio de lavado.");
        }
    }

    // MÉTODOS PRIVADOS ============================================================

    private static String capturarRespuesta(String pregunta) {
        String respuesta;

        System.out.print(pregunta);
        respuesta = sc.nextLine().toLowerCase().trim();

        return respuesta;
    }

    private static int capturarRespuestaInt(String pregunta) {
        boolean continuar = false;
        int numPuertas = 0;

        do {
            String respuesta = capturarRespuesta(pregunta);
            try {
                numPuertas = Integer.valueOf(respuesta);
                continuar = true;
            } catch (Exception e) {
                System.out.println("Introduce un número entero");
            }
        } while (!continuar);
        return numPuertas;
    }

    private static Boolean capturarRespuestaBool(String pregunta) {
        Boolean respuestaBool = (Boolean) null;
        Boolean continuar = false;
        do {
            String respuesta = capturarRespuesta(pregunta);
            if (respuesta.equals("true") || respuesta.equals("false")) {
                respuestaBool = Boolean.parseBoolean(respuesta);
                continuar = true;
            } else {
                System.out.println("Introduce 'True' o 'False'");
            }
        } while (!continuar);

        return respuestaBool;

    }

}
