
import Clases.SalaCine;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        SalaCine taquilla = new SalaCine(10, 2.5);

        Scanner sc = new Scanner(System.in);
        Scanner scNum = new Scanner(System.in);

        boolean continuar = true;
        int respuesta;

        do {
            System.out.println("""
                    MENU
                    ----

                    1) MOSTRAR CUÁNTAS ENTRADAS SE HAN VENDIDO
                    2) MOSTRAR RECAUDACIÓN TOTAL
                    3) MOSTRAR EL NÚMERO DE ENTRADAS DISPONIBLES
                    4) REGISTRAR VENTA DE UNA ENTRADA
                    5) REINICIAR SALA

                    6) SALIR
                    """);
            respuesta = scNum.nextInt();
            switch (respuesta) {
                case 1 -> {
                    System.out.println("Se han vendido " + taquilla.mostrarEntradasVendidas() + " entradas");
                }
                case 2 -> {
                    System.out.println("Se ha recaudado un total de " + taquilla.totalRecaudado() + "€");
                }
                case 3 -> {
                    System.out.println("Quedan disponibles " + taquilla.mostrarEntradasDisponibles() + " entradas");
                }
                case 4 -> {
                    if (taquilla.venderEntrada()) {
                        System.out.println("Se ha registrado la venta de una entrada");
                    } else {
                        System.out.println("Ya no quedan entradas disponibles");
                    }
                }
                case 5 -> {
                    taquilla.reiniciarSala();
                    System.out.println("Sala reiniciada");
                }
                case 6 -> {
                    System.out.println("Saliendo del programa...");
                    continuar = false;
                }

                default -> {
                    System.out.println("Selecciona una opción válida");
                }
            }
        } while (continuar);
    }
}
