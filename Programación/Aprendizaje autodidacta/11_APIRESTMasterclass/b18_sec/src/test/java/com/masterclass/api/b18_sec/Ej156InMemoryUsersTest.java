package com.masterclass.api.b18_sec;

import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class Ej156InMemoryUsersTest {

    private final Map<String, Usuario156> db = Map.of(
            "ana", new Usuario156("ana", "{bcrypt}h", Set.of("ROLE_ADMIN"), true),
            "bob", new Usuario156("bob", "{bcrypt}h", Set.of("ROLE_USER"), false));

    @Test
    void encuentraActivoCaseInsensitive() {
        assertTrue(Ej156InMemoryUsers.buscar(db, "ANA").isPresent());
    }

    @Test
    void deshabilitadoNoAparece() {
        assertTrue(Ej156InMemoryUsers.buscar(db, "bob").isEmpty());
    }

    @Test
    void noExiste() {
        assertTrue(Ej156InMemoryUsers.buscar(db, "zzz").isEmpty());
    }

    @Test
    void roles() {
        Usuario156 ana = new Usuario156("ana", "h", Set.of("ROLE_ADMIN"), true);
        assertTrue(Ej156InMemoryUsers.tieneAlgunRol(ana, Set.of("ROLE_ADMIN")));
        assertFalse(Ej156InMemoryUsers.tieneAlgunRol(ana, Set.of("ROLE_USER")));
    }

    @Test
    void invalidos() {
        assertThrows(IllegalArgumentException.class,
                () -> Ej156InMemoryUsers.buscar(null, "ana"));
    }

    @Test
    void testRetoExtra01_esUsernameSeguro() {
        // Verifica si el nombre de usuario tiene longitud y caracteres correctos.
        assertTrue(Ej156InMemoryUsers.esUsernameSeguro("user123"));
    }

    @Test
    void testRetoExtra02_esRolSoportado() {
        // Comprueba si el rol forma parte de los del sistema (USER, ADMIN).
        assertTrue(Ej156InMemoryUsers.esRolSoportado("USER"));
    }

    @Test
    void testRetoExtra03_crearEntradaUsuarioMemoria() {
        // Genera la linea de configuracion para usuario en memoria.
        assertTrue(Ej156InMemoryUsers.crearEntradaUsuarioMemoria("u", "p", "USER").contains("USER"));
    }

    @Test
    void testRetoExtra04_esPasswordEncriptadaValida() {
        // Comprueba que la contraseña en memoria empiece por el prefijo del codificador.
        assertTrue(Ej156InMemoryUsers.esPasswordEncriptadaValida("{noop}123"));
    }

    @Test
    void testRetoExtra05_esCredencialCorrecta() {
        // Determina si coincide la credencial de forma segura frente a ataques de temporizacion.
        assertTrue(Ej156InMemoryUsers.esCredencialCorrecta("1", "1"));
    }

    @Test
    void testRetoExtra06_tieneRolAdmin() {
        // Verifica si la coleccion contiene el rol ADMIN.
        assertTrue(Ej156InMemoryUsers.tieneRolAdmin(java.util.List.of("ADMIN")));
    }

    @Test
    void testRetoExtra07_esExcepcionDeCredenciales() {
        // Determina si el error apunta a fallo de contrasena o usuario no hallado.
        assertTrue(Ej156InMemoryUsers.esExcepcionDeCredenciales(new SecurityException("Bad credentials")));
    }

    @Test
    void testRetoExtra08_limpiarPrefijoCodificador() {
        // Elimina el prefijo del encoder (ej. {bcrypt}).
        assertEquals("123", Ej156InMemoryUsers.limpiarPrefijoCodificador("{bcrypt}123"));
    }

    @Test
    void testRetoExtra09_generarUsernamePorDefecto() {
        // Genera un nombre a partir del correo.
        assertEquals("ada", Ej156InMemoryUsers.generarUsernamePorDefecto("ada@b.com"));
    }

    @Test
    void testRetoExtra10_esCuentaExpirada() {
        // Determina si la fecha de expiracion de cuenta ya paso.
        assertTrue(Ej156InMemoryUsers.esCuentaExpirada(java.time.LocalDate.now().minusDays(1)));
    }

}