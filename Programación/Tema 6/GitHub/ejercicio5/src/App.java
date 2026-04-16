import java.util.Scanner;

import Clases.Pasajero;
import Clases.Vuelo;
import DAO.DAOpasajeros;
import DAO.DAOvuelos;

public class App {
    private static Scanner sc = new Scanner(System.in);
    private static Vuelo[] vuelo = DAOvuelos.listaVuelos();
    private static Pasajero[] pasajero = DAOpasajeros.listaPasajeros();
    public static void main(String[] args) throws Exception {

        boolean continuar = true;
        String respuesta;
        do {
            menu();
            respuesta = sc.nextLine().toUpperCase();
            switch (respuesta) {
                case "1" -> {
                    for (int i = 0; i < vuelo.length; i++) {
                        System.out.println((i+1) + ". " + vuelo[i].toString());
                    }
                }
                case "2" -> {
                    for (int i = 0; i < pasajero.length; i++) {
                        System.out.println((i+1) + ". " + pasajero[i].toString());
                    }
                }
                case "3" -> {

                }
                case "4" -> {

                }
                case "S" -> {

                }
            
                default -> {

                }
            }
        } while (continuar);
        
    }

    public static void menu(){
        System.out.print("""
                === MENU ===

                1. Listar vuelos disponibles
                2. Listar pasajeros disponibles
                3. Realizar una reserva
                4. Mostrar reservas realizadas

                S. Salir

                Selecciona una opción:\t""");
    }
}
