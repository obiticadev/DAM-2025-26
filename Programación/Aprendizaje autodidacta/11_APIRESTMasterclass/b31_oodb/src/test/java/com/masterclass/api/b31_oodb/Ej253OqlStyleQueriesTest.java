package com.masterclass.api.b31_oodb;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Ej253OqlStyleQueriesTest {

    private Connection c;

    @BeforeEach
    void setUp() throws Exception {
        c = DriverManager.getConnection("jdbc:h2:mem:ej253;DB_CLOSE_DELAY=-1", "sa", "");
        var st = c.createStatement();
        st.execute("CREATE TABLE DEPT(id INT PRIMARY KEY, nombre VARCHAR(50))");
        st.execute("CREATE TABLE EMP(id INT PRIMARY KEY, nombre VARCHAR(50), salario INT, dept_id INT)");
        st.execute("INSERT INTO DEPT VALUES (1,'IT'),(2,'RRHH')");
        st.execute("INSERT INTO EMP VALUES (1,'Ana',3000,1),(2,'Beto',2500,1),(3,'Caro',1800,2)");
    }

    @AfterEach
    void tearDown() throws Exception {
        c.createStatement().execute("DROP ALL OBJECTS");
        c.close();
    }

    @Test
    void nombresEmpleadosDe() throws Exception {
        assertEquals(List.of("Ana", "Beto"), Ej253OqlStyleQueries.nombresEmpleadosDe(c, "IT"));
    }

    @Test
    void contarMayoresQue() throws Exception {
        assertEquals(2L, Ej253OqlStyleQueries.contarMayoresQue(c, 2000));
    }

    @Test
    void retoExtra01_salarioMedioDe() throws Exception {
        assertEquals(2750.0, Ej253OqlStyleQueries.salarioMedioDe(c, "IT"), 0.001);
    }

    @Test
    void retoExtra02_empleadoMasCaro() throws Exception {
        assertEquals("Ana", Ej253OqlStyleQueries.empleadoMasCaro(c));
    }

    @Test
    void retoExtra03_departamentosConEmpleados() throws Exception {
        assertEquals(List.of("IT", "RRHH"), Ej253OqlStyleQueries.departamentosConEmpleados(c));
    }

    @Test
    void retoExtra04_contarPorDepartamento() throws Exception {
        assertEquals(2, Ej253OqlStyleQueries.contarPorDepartamento(c, "IT"));
    }

    @Test
    void retoExtra05_nombresPorSalarioDesc() throws Exception {
        assertEquals(List.of("Ana", "Beto", "Caro"), Ej253OqlStyleQueries.nombresPorSalarioDesc(c));
    }

    @Test
    void retoExtra06_hayAlguienQueGanaMasDe() throws Exception {
        assertTrue(Ej253OqlStyleQueries.hayAlguienQueGanaMasDe(c, 2900));
        assertFalse(Ej253OqlStyleQueries.hayAlguienQueGanaMasDe(c, 5000));
    }

    @Test
    void retoExtra07_salarioMaximoDe() throws Exception {
        assertEquals(3000, Ej253OqlStyleQueries.salarioMaximoDe(c, "IT"));
    }

    @Test
    void retoExtra08_empleadosEntre() throws Exception {
        assertEquals(List.of("Ana", "Beto"), Ej253OqlStyleQueries.empleadosEntre(c, 2000, 3000));
    }

    @Test
    void retoExtra09_departamentoDe() throws Exception {
        assertEquals("IT", Ej253OqlStyleQueries.departamentoDe(c, "Ana"));
    }

    @Test
    void retoExtra10_totalEmpleados() throws Exception {
        assertEquals(3, Ej253OqlStyleQueries.totalEmpleados(c));
    }
}
