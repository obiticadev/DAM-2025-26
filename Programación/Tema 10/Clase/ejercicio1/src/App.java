import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import Clase.Objeto;

public class App {
    public static void main(String[] args) throws Exception {
        List<Objeto> miObjeto = new ArrayList<>(
                List.of(
                        new Objeto(15, 4.3, "Uno"),
                        new Objeto(45, 2.5, "Dos"),
                        new Objeto(6, 4.8, "Tres")));
        String archivoBinario = "datosBinarios.bin";
        try (DataOutputStream os = new DataOutputStream(new FileOutputStream(archivoBinario))) {
            for (Objeto objeto : miObjeto) {
                os.writeInt(objeto.getEntero());
                os.writeDouble(objeto.getDecimal());
                os.writeUTF(objeto.getPalabra());
            }
        } catch (Exception e) {
            System.out.println("Error al escribir en el archivo binario: " + e.getMessage());
        }

        List<Objeto> lista = new ArrayList<>();
        try (DataInputStream is = new DataInputStream(new FileInputStream(archivoBinario))) {
            while (is.available() > 0) {
                int num = is.readInt();
                double decimal = is.readDouble();
                String palabra = is.readUTF();

                lista.add(new Objeto(num, decimal, palabra));
            }
        } catch (Exception e) {
            System.out.println("Error al leer en el archibo binario: " + e.getMessage());
        }
        for (Objeto objeto : lista) {
            System.out.println(objeto.toString());
        }
    }
}
