package bloque5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej28 - Transient y serialVersionUID")
class Ej28_TransientYVersionTest {

    @TempDir Path tempDir;
    String dir;

    @BeforeEach void setUp() { dir = tempDir.toString(); }

    @Test @DisplayName("guardarUsuario + cargarUsuario: ida y vuelta")
    void idaYVuelta() throws Exception {
        String ruta = dir + "/u.dat";
        Ej28_TransientYVersion.Usuario u = new Ej28_TransientYVersion.Usuario("Ana", "ana@m.com", "x");
        Ej28_TransientYVersion.guardarUsuario(ruta, u);
        Ej28_TransientYVersion.Usuario u2 = Ej28_TransientYVersion.cargarUsuario(ruta);
        assertNotNull(u2);
        assertEquals("Ana", u2.getNombre());
    }

    @Test @DisplayName("passwordSePierde: devuelve true")
    void passwordSePierde() throws Exception {
        assertTrue(Ej28_TransientYVersion.passwordSePierde(dir + "/pw.dat"));
    }

    @Test @DisplayName("datosSePreservan: devuelve true")
    void datosSePreservan() throws Exception {
        assertTrue(Ej28_TransientYVersion.datosSePreservan(dir + "/dp.dat"));
    }

    @Test @DisplayName("contadorSePreserva: devuelve true")
    void contadorSePreserva() throws Exception {
        assertTrue(Ej28_TransientYVersion.contadorSePreserva(dir + "/cnt.dat"));
    }

    @Test @DisplayName("obtenerSerialVersionUID: devuelve 1")
    void obtenerSerialVersionUID() {
        assertEquals(1L, Ej28_TransientYVersion.obtenerSerialVersionUID());
    }

    @Test @DisplayName("informeTransient: contiene password")
    void informeTransient() {
        assertTrue(Ej28_TransientYVersion.informeTransient().contains("password"));
    }

    @Test @DisplayName("Usuario: password transient es null tras deserializar, email no")
    void transientVsNormal() throws Exception {
        String ruta = dir + "/mix.dat";
        Ej28_TransientYVersion.Usuario u = new Ej28_TransientYVersion.Usuario("X", "x@m.com", "sec");
        Ej28_TransientYVersion.guardarUsuario(ruta, u);
        Ej28_TransientYVersion.Usuario u2 = Ej28_TransientYVersion.cargarUsuario(ruta);
        assertNull(u2.getPassword());
        assertEquals("x@m.com", u2.getEmail());
    }
}
