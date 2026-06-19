package com.masterclass.api.b31_oodb;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Ej251ObjectRelationalTypesTest {

    private Connection c;

    @BeforeEach
    void setUp() throws Exception {
        c = DriverManager.getConnection("jdbc:h2:mem:ej251;DB_CLOSE_DELAY=-1", "sa", "");
        var st = c.createStatement();
        st.execute("CREATE TABLE PROD(id INT, etiquetas VARCHAR ARRAY)");
        st.execute("INSERT INTO PROD VALUES (1, ARRAY['java','sql','jdbc'])");
    }

    @AfterEach
    void tearDown() throws Exception {
        c.createStatement().execute("DROP ALL OBJECTS");
        c.close();
    }

    @Test
    void leerEtiquetas() throws Exception {
        Object[] datos = Ej251ObjectRelationalTypes.leerEtiquetas(c, 1);
        assertNotNull(datos);
        assertEquals(3, datos.length);
        assertEquals("java", datos[0]);
        assertNull(Ej251ObjectRelationalTypes.leerEtiquetas(c, 99));
    }

    @Test
    void contarEtiquetas() throws Exception {
        assertEquals(3, Ej251ObjectRelationalTypes.contarEtiquetas(c, 1));
        assertEquals(0, Ej251ObjectRelationalTypes.contarEtiquetas(c, 99));
    }

    @Test
    void retoExtra01_primeraEtiqueta() throws Exception {
        assertEquals("java", Ej251ObjectRelationalTypes.primeraEtiqueta(c, 1));
    }

    @Test
    void retoExtra02_contieneEtiqueta() throws Exception {
        assertTrue(Ej251ObjectRelationalTypes.contieneEtiqueta(c, 1, "sql"));
        assertFalse(Ej251ObjectRelationalTypes.contieneEtiqueta(c, 1, "python"));
    }

    @Test
    void retoExtra03_etiquetasEnMayusculas() throws Exception {
        assertEquals(List.of("JAVA", "SQL", "JDBC"), Ej251ObjectRelationalTypes.etiquetasEnMayusculas(c, 1));
    }

    @Test
    void retoExtra04_crearArrayDesdeJava() throws Exception {
        assertEquals(2, Ej251ObjectRelationalTypes.crearArrayDesdeJava(c));
    }

    @Test
    void retoExtra05_tamanoArrayLiteral() throws Exception {
        assertEquals(4, Ej251ObjectRelationalTypes.tamanoArrayLiteral(c));
    }

    @Test
    void retoExtra06_unirEtiquetas() throws Exception {
        assertEquals("java,sql,jdbc", Ej251ObjectRelationalTypes.unirEtiquetas(c, 1));
    }

    @Test
    void retoExtra07_tipoArray() {
        assertEquals(Types.ARRAY, Ej251ObjectRelationalTypes.tipoArray());
    }

    @Test
    void retoExtra08_indiceDeEtiqueta() throws Exception {
        assertEquals(2, Ej251ObjectRelationalTypes.indiceDeEtiqueta(c, 1, "jdbc"));
    }

    @Test
    void retoExtra09_ultimaEtiqueta() throws Exception {
        assertEquals("jdbc", Ej251ObjectRelationalTypes.ultimaEtiqueta(c, 1));
    }

    @Test
    void retoExtra10_tieneEtiquetas() throws Exception {
        assertTrue(Ej251ObjectRelationalTypes.tieneEtiquetas(c, 1));
    }
}
