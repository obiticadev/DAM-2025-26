package com.masterclass.api.b31_oodb;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

import static org.junit.jupiter.api.Assertions.*;

class Ej249CallableStatementTest {

    private Connection c;

    @BeforeEach
    void setUp() throws Exception {
        c = DriverManager.getConnection("jdbc:h2:mem:ej249;DB_CLOSE_DELAY=-1", "sa", "");
        var st = c.createStatement();
        st.execute("CREATE ALIAS SUMAR FOR \"com.masterclass.api.b31_oodb.ProcsAlmacenados.sumar\"");
        st.execute("CREATE ALIAS SALUDAR FOR \"com.masterclass.api.b31_oodb.ProcsAlmacenados.saludar\"");
        st.execute("CREATE ALIAS FACT FOR \"com.masterclass.api.b31_oodb.ProcsAlmacenados.factorial\"");
    }

    @AfterEach
    void tearDown() throws Exception {
        c.createStatement().execute("DROP ALL OBJECTS");
        c.close();
    }

    @Test
    void llamarSuma() throws Exception {
        assertEquals(7, Ej249CallableStatement.llamarSuma(c, 3, 4));
    }

    @Test
    void llamarSaludo() throws Exception {
        assertEquals("Hola, Ana", Ej249CallableStatement.llamarSaludo(c, "Ana"));
        assertNull(Ej249CallableStatement.llamarSaludo(c, null));
    }

    @Test
    void retoExtra01_sumaConIndices() throws Exception {
        assertEquals(7, Ej249CallableStatement.sumaConIndices(c, 3, 4));
    }

    @Test
    void retoExtra02_sumaConCallStatement() throws Exception {
        assertEquals(5, Ej249CallableStatement.sumaConCallStatement(c));
    }

    @Test
    void retoExtra03_factorialPorFuncion() throws Exception {
        assertEquals(120, Ej249CallableStatement.factorialPorFuncion(c, 5));
    }

    @Test
    void retoExtra04_escapeFuncion() {
        assertEquals("{? = call SUMAR(?, ?)}", Ej249CallableStatement.escapeFuncion());
    }

    @Test
    void retoExtra05_esSintaxisFuncion() {
        assertTrue(Ej249CallableStatement.esSintaxisFuncion("{? = call F(?)}"));
        assertFalse(Ej249CallableStatement.esSintaxisFuncion("{call P(?)}"));
    }

    @Test
    void retoExtra06_tipoSalidaEntera() {
        assertEquals(Types.INTEGER, Ej249CallableStatement.tipoSalidaEntera());
    }

    @Test
    void retoExtra07_saludoEnMayusculas() throws Exception {
        assertEquals("HOLA, ANA", Ej249CallableStatement.saludoEnMayusculas(c, "Ana"));
    }

    @Test
    void retoExtra08_sumaEsCorrecta() throws Exception {
        assertTrue(Ej249CallableStatement.sumaEsCorrecta(c));
    }

    @Test
    void retoExtra09_llamarInexistenteLanza() {
        assertTrue(Ej249CallableStatement.llamarInexistenteLanza(c));
    }

    @Test
    void retoExtra10_longitudSaludo() throws Exception {
        assertEquals(9, Ej249CallableStatement.longitudSaludo(c, "Ana"));
    }
}
