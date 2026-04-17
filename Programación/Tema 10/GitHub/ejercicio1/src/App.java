import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class App {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String respuestaMenu;
        boolean continuar = true;
        while (continuar) {
            menu();
            respuestaMenu = sc.nextLine();
            switch (respuestaMenu) {
                case "1" -> {
                    selectFile();
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
                === GESTOR DE CONTRATOS ===:
                1. Seleccionar carpeta de contratos
                2. Generar resumen de matrículas

                0. Salir

                Selecciona una opción:\s""");
    }

    private static void selectFile() {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setCurrentDirectory(new File("."));
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int resultado = jFileChooser.showOpenDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File f = jFileChooser.getSelectedFile();
            if (f.isFile()) {
                fichero(f);
            } else if (f.isDirectory()) {
                directory(f);
            }

        }
    }

    private static void fichero(File f) {
        try {
            List<String> salida = Files.readAllLines(Paths.get(f.getAbsolutePath()));
            salida.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void directory(File f) {
        System.out.println(f.getAbsolutePath());
    }
}
