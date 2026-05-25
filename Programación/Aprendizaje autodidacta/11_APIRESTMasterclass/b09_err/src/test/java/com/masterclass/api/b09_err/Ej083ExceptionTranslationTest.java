package com.masterclass.api.b09_err;

import org.junit.jupiter.api.Test;
import com.masterclass.api.b09_err.Ej083ExceptionTranslation.DatoDuplicadoException;
import com.masterclass.api.b09_err.Ej083ExceptionTranslation.PersistenciaException;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

class Ej083ExceptionTranslationTest {

    @Test
    void duplicado23505() {
        var r = Ej083ExceptionTranslation.traducir(new SQLException("dup", "23505"));
        assertInstanceOf(DatoDuplicadoException.class, r);
    }

    @Test
    void otroSqlStateEsPersistencia() {
        var ex = new SQLException("io", "08006");
        var r = Ej083ExceptionTranslation.traducir(ex);
        assertInstanceOf(PersistenciaException.class, r);
        assertSame(ex, r.getCause(), "debe conservar la causa");
    }

    @Test
    void nullFalla() {
        assertThrows(IllegalArgumentException.class, () -> Ej083ExceptionTranslation.traducir(null));
    }

    @Test
    void testRetoExtra01_esMensajeTraducible() {
        // Determina si cumple el patron de claves i18n locales.
        assertTrue(Ej083ExceptionTranslation.esMensajeTraducible("error.notfound"));
    }

    @Test
    void testRetoExtra02_esIdiomaSoportado() {
        // Comprueba soporte basico (es, en, fr).
        assertTrue(Ej083ExceptionTranslation.esIdiomaSoportado("es"));
    }

    @Test
    void testRetoExtra03_crearMensajeLocalizado() {
        // Mapea clave y lengua a una traduccion de fallback.
        assertEquals("ES: error", Ej083ExceptionTranslation.crearMensajeLocalizado("es", "error"));
    }

    @Test
    void testRetoExtra04_esErrorValidacionMensaje() {
        // Determina si la clave corresponde a fallos de formulario.
        assertTrue(Ej083ExceptionTranslation.esErrorValidacionMensaje("validation.notblank"));
    }

    @Test
    void testRetoExtra05_limpiarClaveLocalizacion() {
        // Sanea llaves de i18n.
        assertEquals("error.notfound", Ej083ExceptionTranslation.limpiarClaveLocalizacion(" error.notfound "));
    }

    @Test
    void testRetoExtra06_esExcepcionTraducida() {
        // Evalua si el error ya paso por el traductor del negocio.
        assertTrue(Ej083ExceptionTranslation.esExcepcionTraducida(new IllegalArgumentException("translated")));
    }

    @Test
    void testRetoExtra07_traducirConArgs() {
        // Reemplaza parametros dinamicos en mensajes.
        assertEquals("No existe el 5", Ej083ExceptionTranslation.traducirConArgs("No existe el {0}", "5"));
    }

    @Test
    void testRetoExtra08_extraerLocaleDeHeader() {
        // Resuelve la primera preferencia de lenguaje del header.
        assertEquals("es", Ej083ExceptionTranslation.extraerLocaleDeHeader("es-ES,es;q=0.9,en;q=0.8"));
    }

    @Test
    void testRetoExtra09_esFalloTraductor() {
        // Verifica si el propio sistema i18n fallo.
        assertTrue(Ej083ExceptionTranslation.esFalloTraductor(new java.util.MissingResourceException("", "", "")));
    }

    @Test
    void testRetoExtra10_generarDetalleTraducido() {
        // Resuelve clave o devuelve el fallback si no es traducible.
        assertEquals("fallback", Ej083ExceptionTranslation.generarDetalleTraducido("invalido", "fallback"));
    }

}