package com.masterclass.api.b31_oodb;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Ej252PersistObjectGraphTest {

    private Connection c;

    @BeforeEach
    void setUp() throws Exception {
        c = DriverManager.getConnection("jdbc:h2:mem:ej252;DB_CLOSE_DELAY=-1", "sa", "");
        var st = c.createStatement();
        st.execute("CREATE TABLE DEPT(id INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(50))");
        st.execute("CREATE TABLE EMP(id INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(50), salario INT, dept_id INT)");
    }

    @AfterEach
    void tearDown() throws Exception {
        c.createStatement().execute("DROP ALL OBJECTS");
        c.close();
    }

    /** Inserta un departamento con dos empleados vía SQL directo (no depende de guardar()). */
    private int insertarDeptIT() throws Exception {
        var st = c.createStatement();
        st.execute("INSERT INTO DEPT(nombre) VALUES ('IT')");
        st.execute("INSERT INTO EMP(nombre, salario, dept_id) VALUES ('Ana',3000,1),('Beto',2500,1)");
        return 1;
    }

    @Test
    void guardar() throws Exception {
        Departamento d = new Departamento("IT")
                .addEmpleado(new Empleado("Ana", 3000))
                .addEmpleado(new Empleado("Beto", 2500));
        int id = Ej252PersistObjectGraph.guardar(c, d);
        assertTrue(id > 0, "debe devolver el id generado del departamento");
        assertEquals(2, Ej252PersistObjectGraph.contarEmpleados(c, id));
    }

    @Test
    void cargar() throws Exception {
        int id = insertarDeptIT();
        Departamento d = Ej252PersistObjectGraph.cargar(c, id);
        assertNotNull(d);
        assertEquals("IT", d.getNombre());
        assertEquals(2, d.getEmpleados().size());
        assertNull(Ej252PersistObjectGraph.cargar(c, 99));
    }

    @Test
    void retoExtra01_contarEmpleados() throws Exception {
        int id = insertarDeptIT();
        assertEquals(2, Ej252PersistObjectGraph.contarEmpleados(c, id));
    }

    @Test
    void retoExtra02_nombreDepartamento() throws Exception {
        int id = insertarDeptIT();
        assertEquals("IT", Ej252PersistObjectGraph.nombreDepartamento(c, id));
    }

    @Test
    void retoExtra03_salarioTotal() throws Exception {
        int id = insertarDeptIT();
        assertEquals(5500, Ej252PersistObjectGraph.salarioTotal(c, id));
    }

    @Test
    void retoExtra04_existeDepartamento() throws Exception {
        int id = insertarDeptIT();
        assertTrue(Ej252PersistObjectGraph.existeDepartamento(c, id));
        assertFalse(Ej252PersistObjectGraph.existeDepartamento(c, 99));
    }

    @Test
    void retoExtra05_empleadoMejorPagado() throws Exception {
        int id = insertarDeptIT();
        assertEquals("Ana", Ej252PersistObjectGraph.empleadoMejorPagado(c, id));
    }

    @Test
    void retoExtra06_guardarYContar() throws Exception {
        Departamento d = new Departamento("Ventas")
                .addEmpleado(new Empleado("Caro", 2000))
                .addEmpleado(new Empleado("Dani", 2200));
        assertEquals(2, Ej252PersistObjectGraph.guardarYContar(c, d));
    }

    @Test
    void retoExtra07_actualizarSalario() throws Exception {
        insertarDeptIT();
        assertEquals(1, Ej252PersistObjectGraph.actualizarSalario(c, 1, 3500));
    }

    @Test
    void retoExtra08_borrarDepartamentoEnCascada() throws Exception {
        int id = insertarDeptIT();
        assertTrue(Ej252PersistObjectGraph.borrarDepartamentoEnCascada(c, id));
        assertEquals(0, Ej252PersistObjectGraph.contarEmpleados(c, id));
    }

    @Test
    void retoExtra09_idsDeEmpleados() throws Exception {
        int id = insertarDeptIT();
        assertEquals(List.of(1, 2), Ej252PersistObjectGraph.idsDeEmpleados(c, id));
    }

    @Test
    void retoExtra10_departamentoVacioSeCargaVacio() throws Exception {
        assertTrue(Ej252PersistObjectGraph.departamentoVacioSeCargaVacio(c));
    }
}
