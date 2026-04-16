package ejemploCaracteres;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class ejemploCaracteres {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String respuestaMenu;
        boolean continuar = true;
        while (continuar) {
            menu();
            respuestaMenu = sc.nextLine();
            switch (respuestaMenu) {
                case "1" -> {
                    escribir();
                }
                case "2" -> {

                }
                case "0" -> {

                }

                default -> {

                }
            }
        }

    }

    private static void menu() {
        System.out.print("""
                MENÚ:
                1. Escribir
                2. Leer

                0. Salir

                Selecciona una opción:\s""");
    }

    private static boolean escribir() {
        System.out.println("Dime el nombre del fichero");
        String respuesta = sc.nextLine();
        System.out.println("Qué quieres escribir en el fichero");
        String valor = sc.nextLine();

        File fichero = new File(respuesta);
        try {
            FileWriter fw = new FileWriter(fichero);
            fw.append(valor);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;

    }

}
