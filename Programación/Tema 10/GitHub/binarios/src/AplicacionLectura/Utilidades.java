package AplicacionLectura;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

public class Utilidades {
    public static File seleccionarCarpeta() throws IOException {
        System.out.println("INFORMACIÓN SOBRE EL FICHERO");

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));

        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int seleccion = fileChooser.showOpenDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        throw new IOException();
    }
}
