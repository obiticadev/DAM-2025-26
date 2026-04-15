package bloque1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ej02 - Leer Bytes de un Fichero")
class Ej02_LeerBytesTest {

    @TempDir
    Path tempDir;
    String fichero;

    @BeforeEach
    void setUp() throws IOException {
        fichero = tempDir.resolve("datos.bin").toString();
        try (FileOutputStream fos = new FileOutputStream(fichero)) {
            fos.write(new byte[]{72, 111, 108, 97, 33}); // "Hola!"
        }
    }

    @Test @DisplayName("leerTodosBytes: devuelve array completo")
    void leerTodosBytes_completo() throws IOException {
        byte[] resultado = Ej02_LeerBytes.leerTodosBytes(fichero);
        assertNotNull(resultado);
        assertArrayEquals(new byte[]{72, 111, 108, 97, 33}, resultado);
    }

    @Test @DisplayName("leerTodosBytes: fichero vacio devuelve array vacio")
    void leerTodosBytes_vacio() throws IOException {
        String vacio = tempDir.resolve("vacio.bin").toString();
        try (FileOutputStream fos = new FileOutputStream(vacio)) { /* vacio */ }
        byte[] resultado = Ej02_LeerBytes.leerTodosBytes(vacio);
        assertNotNull(resultado);
        assertEquals(0, resultado.length);
    }

    @Test @DisplayName("leerByteEnPosicion: posicion 0 devuelve primer byte")
    void leerByteEnPosicion_primero() throws IOException {
        assertEquals(72, Ej02_LeerBytes.leerByteEnPosicion(fichero, 0));
    }

    @Test @DisplayName("leerByteEnPosicion: posicion negativa lanza excepcion")
    void leerByteEnPosicion_negativa() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej02_LeerBytes.leerByteEnPosicion(fichero, -1));
    }

    @Test @DisplayName("contarByte: cuenta correctamente el byte 111")
    void contarByte_encuentra() throws IOException {
        assertEquals(1, Ej02_LeerBytes.contarByte(fichero, 111));
    }

    @Test @DisplayName("contarByte: devuelve 0 si el byte no existe")
    void contarByte_noExiste() throws IOException {
        assertEquals(0, Ej02_LeerBytes.contarByte(fichero, 255));
    }

    @Test @DisplayName("leerPrimeros: lee los 3 primeros bytes")
    void leerPrimeros_tres() throws IOException {
        byte[] resultado = Ej02_LeerBytes.leerPrimeros(fichero, 3);
        assertNotNull(resultado);
        assertArrayEquals(new byte[]{72, 111, 108}, resultado);
    }

    @Test @DisplayName("leerPrimeros: pide mas bytes de los que hay")
    void leerPrimeros_masDeLoQueHay() throws IOException {
        byte[] resultado = Ej02_LeerBytes.leerPrimeros(fichero, 100);
        assertNotNull(resultado);
        assertEquals(5, resultado.length);
    }

    @Test @DisplayName("ficherosSonIguales: ficheros identicos")
    void ficherosSonIguales_true() throws IOException {
        String copia = tempDir.resolve("copia.bin").toString();
        try (FileOutputStream fos = new FileOutputStream(copia)) {
            fos.write(new byte[]{72, 111, 108, 97, 33});
        }
        assertTrue(Ej02_LeerBytes.ficherosSonIguales(fichero, copia));
    }

    @Test @DisplayName("ficherosSonIguales: ficheros diferentes")
    void ficherosSonIguales_false() throws IOException {
        String otro = tempDir.resolve("otro.bin").toString();
        try (FileOutputStream fos = new FileOutputStream(otro)) {
            fos.write(new byte[]{1, 2, 3});
        }
        assertFalse(Ej02_LeerBytes.ficherosSonIguales(fichero, otro));
    }

    @Test @DisplayName("volcarHexadecimal: formato correcto")
    void volcarHexadecimal_formato() throws IOException {
        assertEquals("48 6F 6C 61 21", Ej02_LeerBytes.volcarHexadecimal(fichero));
    }

    @Test @DisplayName("volcarHexadecimal: fichero vacio devuelve cadena vacia")
    void volcarHexadecimal_vacio() throws IOException {
        String vacio = tempDir.resolve("vacio2.bin").toString();
        try (FileOutputStream fos = new FileOutputStream(vacio)) { /* vacio */ }
        assertEquals("", Ej02_LeerBytes.volcarHexadecimal(vacio));
    }

    @Test @DisplayName("existeYNoVacio: true si existe y tiene contenido")
    void existeYNoVacio_true() {
        assertTrue(Ej02_LeerBytes.existeYNoVacio(fichero));
    }

    @Test @DisplayName("existeYNoVacio: false si no existe")
    void existeYNoVacio_noExiste() {
        assertFalse(Ej02_LeerBytes.existeYNoVacio(tempDir.resolve("inexistente").toString()));
    }
}
