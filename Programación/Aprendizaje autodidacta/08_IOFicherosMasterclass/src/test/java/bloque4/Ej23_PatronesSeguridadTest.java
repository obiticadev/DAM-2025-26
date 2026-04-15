package bloque4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej23 - Patrones de Seguridad en I/O")
class Ej23_PatronesSeguridadTest {

    @TempDir Path tempDir;
    String dir;

    @BeforeEach void setUp() { dir = tempDir.toString(); }

    private void crear(String ruta, String contenido) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {
            bw.write(contenido);
        }
    }

    @Test @DisplayName("escribirOLimpiar: devuelve true si escritura OK")
    void escribirOLimpiar_ok() {
        assertTrue(Ej23_PatronesSeguridad.escribirOLimpiar(dir + "/s.txt", "Hola"));
    }

    @Test @DisplayName("leerConDefault: devuelve contenido si existe")
    void leerConDefault_existe() throws IOException {
        crear(dir + "/d.txt", "Dato");
        assertTrue(Ej23_PatronesSeguridad.leerConDefault(dir + "/d.txt", "X").contains("Dato"));
    }

    @Test @DisplayName("leerConDefault: devuelve default si no existe")
    void leerConDefault_noExiste() {
        assertEquals("VACIO", Ej23_PatronesSeguridad.leerConDefault(dir + "/nope.txt", "VACIO"));
    }

    @Test @DisplayName("copiaAtomicaSimple: copia correctamente")
    void copiaAtomica_ok() throws IOException {
        crear(dir + "/orig.txt", "Contenido");
        assertTrue(Ej23_PatronesSeguridad.copiaAtomicaSimple(dir + "/orig.txt", dir + "/at.txt"));
        assertTrue(Ej23_PatronesSeguridad.leerConDefault(dir + "/at.txt", "").contains("Contenido"));
    }

    @Test @DisplayName("procesarSeguro: convierte a mayusculas")
    void procesarSeguro_mayusculas() throws IOException {
        crear(dir + "/proc.txt", "hola\n\nmundo");
        int n = Ej23_PatronesSeguridad.procesarSeguro(dir + "/proc.txt", dir + "/proc_out.txt");
        assertEquals(2, n); // linea vacia se salta
    }

    @Test @DisplayName("leerPrimeroDisponible: lee el primer fichero valido")
    void leerPrimeroDisponible_primero() throws IOException {
        crear(dir + "/ok.txt", "Encontrado");
        String r = Ej23_PatronesSeguridad.leerPrimeroDisponible(
                new String[]{dir + "/nope1.txt", dir + "/ok.txt"});
        assertNotNull(r);
        assertTrue(r.contains("Encontrado"));
    }

    @Test @DisplayName("leerPrimeroDisponible: devuelve null si ninguno existe")
    void leerPrimeroDisponible_null() {
        assertNull(Ej23_PatronesSeguridad.leerPrimeroDisponible(
                new String[]{dir + "/a.txt", dir + "/b.txt"}));
    }

    @Test @DisplayName("escribirYVerificar: devuelve true si datos coinciden")
    void escribirYVerificar_ok() {
        assertTrue(Ej23_PatronesSeguridad.escribirYVerificar(dir + "/v.txt", "Test"));
    }

    @Test @DisplayName("auditarFicheros: cuenta existentes y no existentes")
    void auditarFicheros_conteo() throws IOException {
        crear(dir + "/e1.txt", "a");
        crear(dir + "/e2.txt", "b");
        int[] r = Ej23_PatronesSeguridad.auditarFicheros(
                new String[]{dir + "/e1.txt", dir + "/noex.txt", dir + "/e2.txt"});
        assertEquals(2, r[0]);
        assertEquals(1, r[1]);
    }

    @Test @DisplayName("auditarFicheros: array vacio devuelve {0, 0}")
    void auditarFicheros_vacio() {
        int[] r = Ej23_PatronesSeguridad.auditarFicheros(new String[0]);
        assertEquals(0, r[0]);
        assertEquals(0, r[1]);
    }
}
