package com.masterclass.api.b31_oodb;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Ej250StoredFunctionResultTest {

    private Connection c;

    @BeforeEach
    void setUp() throws Exception {
        c = DriverManager.getConnection("jdbc:h2:mem:ej250;DB_CLOSE_DELAY=-1", "sa", "");
        var st = c.createStatement();
        st.execute("CREATE TABLE EMP(id INT, nombre VARCHAR(50))");
        st.execute("INSERT INTO EMP VALUES (1,'Ana'),(2,'Beto'),(3,'Caro')");
        st.execute("CREATE ALIAS FACT FOR \"com.masterclass.api.b31_oodb.ProcsAlmacenados.factorial\"");
        st.execute("CREATE ALIAS EMPLEADOS FOR \"com.masterclass.api.b31_oodb.ProcsAlmacenados.empleados\"");
    }

    @AfterEach
    void tearDown() throws Exception {
        c.createStatement().execute("DROP ALL OBJECTS");
        c.close();
    }

    @Test
    void valorFuncion() throws Exception {
        assertEquals(120, Ej250StoredFunctionResult.valorFuncion(c, 5));
    }

    @Test
    void filasFuncion() throws Exception {
        assertEquals(List.of("Ana", "Beto", "Caro"), Ej250StoredFunctionResult.filasFuncion(c));
    }

    @Test
    void retoExtra01_factorialDeCero() throws Exception {
        assertEquals(1, Ej250StoredFunctionResult.factorialDeCero(c));
    }

    @Test
    void retoExtra02_sumaFactoriales() throws Exception {
        assertEquals(30, Ej250StoredFunctionResult.sumaFactoriales(c));
    }

    @Test
    void retoExtra03_nombresEnMayusculas() throws Exception {
        assertEquals(List.of("ANA", "BETO", "CARO"), Ej250StoredFunctionResult.nombresEnMayusculas(c));
    }

    @Test
    void retoExtra04_cuantosEmpleados() throws Exception {
        assertEquals(3, Ej250StoredFunctionResult.cuantosEmpleados(c));
    }

    @Test
    void retoExtra05_contieneEmpleado() throws Exception {
        assertTrue(Ej250StoredFunctionResult.contieneEmpleado(c, "Beto"));
        assertFalse(Ej250StoredFunctionResult.contieneEmpleado(c, "Zoe"));
    }

    @Test
    void retoExtra06_escapeProcedimiento() {
        assertEquals("{call EMPLEADOS()}", Ej250StoredFunctionResult.escapeProcedimiento());
    }

    @Test
    void retoExtra07_tipoTexto() {
        assertEquals(Types.VARCHAR, Ej250StoredFunctionResult.tipoTexto());
    }

    @Test
    void retoExtra08_factorialNegativo() throws Exception {
        assertEquals(1, Ej250StoredFunctionResult.factorialNegativo(c));
    }

    @Test
    void retoExtra09_primerEmpleado() throws Exception {
        assertEquals("Ana", Ej250StoredFunctionResult.primerEmpleado(c));
    }

    @Test
    void retoExtra10_concatenarNombres() throws Exception {
        assertEquals("Ana,Beto,Caro", Ej250StoredFunctionResult.concatenarNombres(c));
    }
}
