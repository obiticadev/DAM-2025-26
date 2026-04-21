package AplicacionLectura;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class AplicacionLectura {
    public static void main(String[] args) {
        AplicacionLectura aplicacionLectura = new AplicacionLectura();
        aplicacionLectura.leerContenidoCarpeta();
    }

    public void leerContenidoCarpeta() {
        File rutaCarpeta = null;
        try {
            rutaCarpeta = Utilidades.seleccionarCarpeta();
        } catch (IOException e) {
            System.out.println("Error en algún lugar: " + e.getMessage());
        }
        String[] nombreFicheros = rutaCarpeta.list();
        for (String ficheroString : nombreFicheros) {
            leerTextoDeArchivoEnVariasLineas(ficheroString);
        }
    }

    public void leerTextoDeArchivoEnVariasLineas(String ruta) {
        Path rutaArchivo = Path.of(ruta);
        try {
            List<String> contenido = Files.readAllLines(rutaArchivo);
            System.out.println(contenido);
        } catch (IOException e) {
            System.out.println("Error en el archivo: " + e.getMessage());
        }
    }
}
