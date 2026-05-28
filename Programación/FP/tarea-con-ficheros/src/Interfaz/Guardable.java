package Interfaz;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Guardable {
    void guardar(String fichero) throws IOException;

    void cargar(String fichero) throws FileNotFoundException, IOException;
}
