package bloque5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej25 - Serializar y Deserializar un Objeto")
class Ej25_SerializarObjetoTest {

    @TempDir Path tempDir;
    String dir;

    @BeforeEach void setUp() { dir = tempDir.toString(); }

    @Test @DisplayName("serializar + deserializar: ida y vuelta")
    void idaYVuelta() throws Exception {
        Producto p = new Producto("Arroz", 1.20, 50);
        String ruta = dir + "/p.dat";
        Ej25_SerializarObjeto.serializar(ruta, p);
        Producto p2 = Ej25_SerializarObjeto.deserializar(ruta);
        assertEquals(p, p2);
    }

    @Test @DisplayName("verificarIntegridad: devuelve true")
    void verificarIntegridad() throws Exception {
        assertTrue(Ej25_SerializarObjeto.verificarIntegridad(
                dir + "/int.dat", new Producto("Sal", 0.80, 100)));
    }

    @Test @DisplayName("tamanoSerializado: devuelve bytes > 0")
    void tamanoSerializado() throws Exception {
        long tam = Ej25_SerializarObjeto.tamanoSerializado(
                dir + "/tam.dat", new Producto("Pan", 1.50, 30));
        assertTrue(tam > 0);
    }

    @Test @DisplayName("transientSePierde: devuelve true")
    void transientSePierde() throws Exception {
        assertTrue(Ej25_SerializarObjeto.transientSePierde(dir + "/tr.dat"));
    }

    @Test @DisplayName("intentarDeserializar: fichero vacio devuelve nombre excepcion")
    void intentarDeserializar_vacio() throws Exception {
        String ruta = dir + "/vacio.dat";
        try (FileOutputStream fos = new FileOutputStream(ruta)) { /* vacio */ }
        String r = Ej25_SerializarObjeto.intentarDeserializar(ruta);
        assertNotEquals("OK", r);
        assertFalse(r.isEmpty());
    }

    @Test @DisplayName("intentarDeserializar: fichero valido devuelve OK")
    void intentarDeserializar_valido() throws Exception {
        String ruta = dir + "/ok.dat";
        Ej25_SerializarObjeto.serializar(ruta, new Producto("X", 1, 1));
        assertEquals("OK", Ej25_SerializarObjeto.intentarDeserializar(ruta));
    }

    @Test @DisplayName("esSerializable: devuelve true")
    void esSerializable() {
        assertTrue(Ej25_SerializarObjeto.esSerializable());
    }
}
