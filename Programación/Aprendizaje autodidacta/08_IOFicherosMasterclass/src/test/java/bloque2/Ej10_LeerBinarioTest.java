package bloque2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej10 - Leer Datos Binarios")
class Ej10_LeerBinarioTest {

    @TempDir
    Path tempDir;
    String fichero;

    @BeforeEach
    void setUp() throws IOException {
        fichero = tempDir.resolve("datos.bin").toString();
        try (FileOutputStream fos = new FileOutputStream(fichero)) {
            fos.write(new byte[]{0x43, 0x41, 0x4D, 0x00}); // firma
            fos.write(new byte[]{0x00, 0x00, 0x04, 0x00}); // tamano: 1024
            fos.write(new byte[]{10, 20, 30, 40, 50});       // payload
        }
    }

    @Test @DisplayName("leerFirma: devuelve los 4 primeros bytes")
    void leerFirma_correcto() throws IOException {
        byte[] firma = Ej10_LeerBinario.leerFirma(fichero);
        assertNotNull(firma);
        assertArrayEquals(new byte[]{0x43, 0x41, 0x4D, 0x00}, firma);
    }

    @Test @DisplayName("leerFirma: fichero muy corto lanza IOException")
    void leerFirma_corto() throws IOException {
        String corto = tempDir.resolve("corto.bin").toString();
        try (FileOutputStream fos = new FileOutputStream(corto)) { fos.write(1); }
        assertThrows(IOException.class, () -> Ej10_LeerBinario.leerFirma(corto));
    }

    @Test @DisplayName("leerIntBigEndian: reconstruye 1024 desde posicion 4")
    void leerIntBigEndian_correcto() throws IOException {
        assertEquals(1024, Ej10_LeerBinario.leerIntBigEndian(fichero, 4));
    }

    @Test @DisplayName("leerTodo: devuelve array completo de 13 bytes")
    void leerTodo_completo() throws IOException {
        byte[] todo = Ej10_LeerBinario.leerTodo(fichero);
        assertNotNull(todo);
        assertEquals(13, todo.length);
    }

    @Test @DisplayName("verificarInicio: firma correcta devuelve true")
    void verificarInicio_true() throws IOException {
        assertTrue(Ej10_LeerBinario.verificarInicio(fichero,
                new byte[]{0x43, 0x41, 0x4D, 0x00}));
    }

    @Test @DisplayName("verificarInicio: firma incorrecta devuelve false")
    void verificarInicio_false() throws IOException {
        assertFalse(Ej10_LeerBinario.verificarInicio(fichero, new byte[]{0, 0, 0, 0}));
    }

    @Test @DisplayName("buscarByte: encuentra byte 30 en posicion 10")
    void buscarByte_encuentra() throws IOException {
        assertEquals(10, Ej10_LeerBinario.buscarByte(fichero, 30));
    }

    @Test @DisplayName("buscarByte: byte inexistente devuelve -1")
    void buscarByte_noExiste() throws IOException {
        assertEquals(-1, Ej10_LeerBinario.buscarByte(fichero, 99));
    }

    @Test @DisplayName("extraerRango: extrae payload [8,12]")
    void extraerRango_correcto() throws IOException {
        byte[] rango = Ej10_LeerBinario.extraerRango(fichero, 8, 12);
        assertNotNull(rango);
        assertArrayEquals(new byte[]{10, 20, 30, 40, 50}, rango);
    }

    @Test @DisplayName("extraerRango: desde > hasta lanza excepcion")
    void extraerRango_invalido() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej10_LeerBinario.extraerRango(fichero, 5, 3));
    }

    @Test @DisplayName("checksumFichero: calculo correcto")
    void checksumFichero_correcto() throws IOException {
        // Suma de todos los bytes: 0x43+0x41+0x4D+0+0+0+4+0+10+20+30+40+50
        // = 67+65+77+0+0+0+4+0+10+20+30+40+50 = 363 % 256 = 107
        assertEquals(107, Ej10_LeerBinario.checksumFichero(fichero));
    }

    @Test @DisplayName("checksumFichero: fichero vacio devuelve 0")
    void checksumFichero_vacio() throws IOException {
        String vacio = tempDir.resolve("vacio.bin").toString();
        try (FileOutputStream fos = new FileOutputStream(vacio)) { /* vacio */ }
        assertEquals(0, Ej10_LeerBinario.checksumFichero(vacio));
    }
}
