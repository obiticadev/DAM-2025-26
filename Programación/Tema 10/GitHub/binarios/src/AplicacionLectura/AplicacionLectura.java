package AplicacionLectura;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

import Clases.Mesa;

public class AplicacionLectura {

    public AplicacionLectura() {
        try {
            generarFichero(leerContenidoCarpeta());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Error en algún lugar");
        }
    }

    public List<Mesa> leerContenidoCarpeta() throws IOException {
        File rutaCarpeta = null;
        List<Mesa> listaMesas = new ArrayList<>();
        try {
            rutaCarpeta = Utilidades.seleccionarCarpeta();
        } catch (IOException e) {
            System.out.println("Error en algún lugar: " + e.getMessage());
        }
        File[] nombreFicheros = rutaCarpeta.listFiles();
        for (File fichero : nombreFicheros) {
            listaMesas.add(leerTextoDeArchivoEnVariasLineas(fichero));
        }
        return listaMesas;
    }

    public Mesa leerTextoDeArchivoEnVariasLineas(File f) throws IOException {
        Path ruta = Path.of(f.getAbsolutePath());
        List<String> contenido = Files.readAllLines(ruta);
        String[] array = contenido.get(1).split(";");

        return new Mesa(Integer.parseInt(array[0]), array[1], Integer.parseInt(array[2]), Integer.parseInt(array[3]),
                Integer.parseInt(array[4]), Integer.parseInt(array[5]), Integer.parseInt(array[6]));

    }

    public void generarFichero(List<Mesa> listaMesas) throws IOException {
        try (DataOutputStream os = new DataOutputStream(new FileOutputStream("ficheroMesa.bin"))) {
            for (Mesa mesa : listaMesas) {
                os.writeInt(mesa.getIdMesa());
                os.writeUTF(mesa.getLocalidad());
                os.writeInt(mesa.getVotosA());
                os.writeInt(mesa.getVotosB());
                os.writeInt(mesa.getVotosC());
                os.writeInt(mesa.getBlancos());
                os.writeInt(mesa.getNulos());
            }
        }
    }

    public List<Mesa> leerArchivoBin() throws IOException {
        List<Mesa> listaMesa = new ArrayList<>();
        File fichero = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int selection = fileChooser.showOpenDialog(null);
        if (selection == JFileChooser.APPROVE_OPTION) {
            fichero = fileChooser.getSelectedFile();
        }
        // public Mesa(int idMesa, String localidad, int votosA, int votosB, int votosC,
        // int blancos, int nulos) {
        try (DataInputStream is = new DataInputStream(new FileInputStream(fichero))) {
            while (is.available() > 0) {
                int idMesa = is.readInt();
                String localidad = is.readUTF();
                int votosA = is.readInt();
                int votasB = is.readInt();
                int votosC = is.readInt();
                int blancos = is.readInt();
                int nulos = is.readInt();
                listaMesa.add(new Mesa(idMesa, localidad, votosA, votosA, votosC, blancos, nulos));
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return listaMesa;

    }
}
