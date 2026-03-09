import Clases.VehiculoAparcado;
import DAO.DAOParking;
import Excepciones.MiExcepcion;
import Herramientas.MetodosExternos;
import java.util.Scanner;

public class App {
    private static DAOParking dao;
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String respuestaMenu;
        boolean continuar = false;

        try {
            dao = DAOParking.getInstance();
        } catch (MiExcepcion e) {
            System.out.println("Revisar la matricula: " + e.getMatricula() + ", con la hora de entrada a las "
                    + e.getHoraEntrada());
        }

        do {
            System.out.println("=== MENU ===");
            System.out.println("1. Registrar entrada");
            System.out.println("2. Registrar salida");
            System.out.println("3. Lavar vehículo");
            System.out.println("4. Mostrar vehículos");
            System.out.println("5. Mostrar ingresos acumulados");
            System.out.println("6. Salir");

            respuestaMenu = sc.nextLine();

            switch (respuestaMenu) {
                case "1" -> {
                    dao.agregarVehiculo(MetodosExternos.registrarEntrada());
                }
                case "2" -> {
                    System.out.println(MetodosExternos.registrarSalida(dao) ? "Hecho" : "Vehículo no encontrado");
                }
                case "3" -> {
                    MetodosExternos.asignarLavado(dao);
                }
                case "4" -> {
                    System.out.println("=== VEHÍCULOS ===");
                    for (VehiculoAparcado vehiculo : dao.getListaVehiculos()) {
                        System.out.println(vehiculo.mostrarInformacion());
                        System.out.println("--------------");
                    }
                }
                case "5" -> {
                    System.out.println("Los ingresos acumulados son de " + dao.calcularIngresosParking() + " €");
                }
                case "6" -> {
                    System.out.println("Saliendo del programa...");
                    continuar = true;
                }

                default -> {
                    System.out.println("Selecciona una opción válida");
                }
            }
        } while (!continuar);

    }
}
