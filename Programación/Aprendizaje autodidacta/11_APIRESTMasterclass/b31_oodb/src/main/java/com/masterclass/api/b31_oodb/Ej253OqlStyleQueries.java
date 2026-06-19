package com.masterclass.api.b31_oodb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Ejercicio 253 · Consultas estilo OQL sobre objetos persistidos (didáctico).
 *
 * <p>OQL (Object Query Language, del estándar ODMG) consulta <b>navegando objetos</b>:
 * {@code SELECT e.nombre FROM Departamento d, d.empleados e WHERE d.nombre = "IT"}. En una BD
 * relacional ese "navegar la relación" se traduce en un <b>JOIN</b> entre DEPT y EMP. Aquí
 * escribes el SQL equivalente para ver el contraste: lo que en OO es seguir una referencia, en
 * SQL es reunir dos tablas por su clave.
 *
 * <p>El test crea DEPT y EMP con datos: deptos "IT" y "RRHH" con varios empleados.
 *
 * <p>Teoría: {@code teoria/31_ObjetoRelacional_OO.md} (sección 31.5).
 */
public final class Ej253OqlStyleQueries {

    private Ej253OqlStyleQueries() {
    }

    /**
     * "Navega" del departamento a sus empleados: nombres de los empleados de un departamento.
     *
     * @return lista de nombres (nunca null), vacía si no se ha implementado
     */
    public static List<String> nombresEmpleadosDe(Connection c, String depto) throws SQLException {
        // TODO 1: el SQL equivale a "d.empleados.nombre WHERE d.nombre = depto" (OQL): un JOIN.
        // TODO 2: "SELECT e.nombre FROM EMP e JOIN DEPT d ON e.dept_id = d.id WHERE d.nombre = ? ORDER BY e.nombre".
        // TODO 3: prepara el statement y ps.setString(1, depto).
        // TODO 4: recorre el ResultSet y añade rs.getString("nombre") (o getString(1)) a una List.
        // TODO 5: devuelve la lista; vacía (no null) si el departamento no tiene empleados (propaga SQLException).
        return List.of();
    }

    /**
     * Cuenta los empleados que ganan más que un salario dado (filtro estilo OQL sobre objetos).
     *
     * @return número de empleados con salario mayor, o -1 si no se ha implementado
     */
    public static long contarMayoresQue(Connection c, int salario) throws SQLException {
        // TODO 6: "SELECT COUNT(*) FROM EMP WHERE salario > ?" (el "WHERE e.salario > x" de OQL).
        // TODO 7: ps.setInt(1, salario).
        // TODO 8: ResultSet rs = ps.executeQuery(); rs.next().
        // TODO 9: lee el conteo con rs.getLong(1).
        // TODO 10: devuélvelo (try-with-resources; propaga SQLException).
        return -1;
    }

    public static void main(String[] args) throws SQLException {
        try (Connection c = DriverManager.getConnection("jdbc:h2:mem:demo253", "sa", "")) {
            crearDatosDemo(c);
            System.out.println("nombresEmpleadosDe(IT) = " + nombresEmpleadosDe(c, "IT"));
            System.out.println("contarMayoresQue(2000) = " + contarMayoresQue(c, 2000));
        }
    }

    /** Utilidad de demostración para el main (el test crea sus propios datos). */
    private static void crearDatosDemo(Connection c) throws SQLException {
        var st = c.createStatement();
        st.execute("CREATE TABLE DEPT(id INT PRIMARY KEY, nombre VARCHAR(50))");
        st.execute("CREATE TABLE EMP(id INT PRIMARY KEY, nombre VARCHAR(50), salario INT, dept_id INT)");
        st.execute("INSERT INTO DEPT VALUES (1,'IT'),(2,'RRHH')");
        st.execute("INSERT INTO EMP VALUES (1,'Ana',3000,1),(2,'Beto',2500,1),(3,'Caro',1800,2)");
    }

    /**
     * Reto Extra 1: salario medio de un departamento (AVG).
     * @return el salario medio (el test usa IT con 3000 y 2500 -> 2750)
     */
    public static double salarioMedioDe(Connection c, String depto) throws SQLException {
        // GUÍA: JOIN como en el core pero con AVG(e.salario); rs.next(); return rs.getDouble(1).
        // OJO: AVG devuelve decimal; el test compara 2750.0 (usa assertEquals con delta o exacto).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para salarioMedioDe");
    }

    /**
     * Reto Extra 2: el empleado mejor pagado de toda la empresa.
     * @return su nombre (el test espera "Ana", salario 3000)
     */
    public static String empleadoMasCaro(Connection c) throws SQLException {
        // GUÍA: "SELECT nombre FROM EMP ORDER BY salario DESC LIMIT 1"; rs.next(); getString(1).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para empleadoMasCaro");
    }

    /**
     * Reto Extra 3: nombres de departamentos que tienen al menos un empleado (DISTINCT/JOIN).
     * @return lista de nombres de dept con empleados (el test espera [IT, RRHH])
     */
    public static List<String> departamentosConEmpleados(Connection c) throws SQLException {
        // GUÍA: "SELECT DISTINCT d.nombre FROM DEPT d JOIN EMP e ON e.dept_id = d.id ORDER BY d.nombre".
        // CULTURA: el JOIN solo deja deptos que "tienen referencia entrante" desde EMP.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para departamentosConEmpleados");
    }

    /**
     * Reto Extra 4: cuántos empleados hay en un departamento.
     * @return el conteo (el test pregunta por "IT" y espera 2)
     */
    public static int contarPorDepartamento(Connection c, String depto) throws SQLException {
        // GUÍA: reutiliza nombresEmpleadosDe(c, depto).size(), o COUNT(*) con el JOIN.
        // PISTA: reutilizar el core evita repetir el JOIN.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarPorDepartamento");
    }

    /**
     * Reto Extra 5: nombres de todos los empleados ordenados por salario descendente.
     * @return lista ordenada (el test espera [Ana, Beto, Caro])
     */
    public static List<String> nombresPorSalarioDesc(Connection c) throws SQLException {
        // GUÍA: "SELECT nombre FROM EMP ORDER BY salario DESC"; recorre y acumula en una List.
        // OJO: 3000(Ana) > 2500(Beto) > 1800(Caro).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para nombresPorSalarioDesc");
    }

    /**
     * Reto Extra 6: ¿hay alguien que gane más de un umbral? (EXISTS)
     * @return true si existe (el test pregunta por 2900 -> true por Ana=3000)
     */
    public static boolean hayAlguienQueGanaMasDe(Connection c, int salario) throws SQLException {
        // GUÍA: reutiliza contarMayoresQue(c, salario) > 0, o "SELECT EXISTS(SELECT 1 FROM EMP WHERE salario > ?)".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para hayAlguienQueGanaMasDe");
    }

    /**
     * Reto Extra 7: salario máximo de un departamento.
     * @return MAX(salario) del dept (el test usa IT -> 3000)
     */
    public static int salarioMaximoDe(Connection c, String depto) throws SQLException {
        // GUÍA: JOIN + MAX(e.salario) WHERE d.nombre = ?; rs.next(); return rs.getInt(1).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para salarioMaximoDe");
    }

    /**
     * Reto Extra 8: nombres de empleados con salario en un rango [min, max].
     * @return lista de nombres (el test pide [2000,3000] -> [Ana, Beto])
     */
    public static List<String> empleadosEntre(Connection c, int min, int max) throws SQLException {
        // GUÍA: "SELECT nombre FROM EMP WHERE salario BETWEEN ? AND ? ORDER BY nombre".
        // OJO: BETWEEN es inclusivo en ambos extremos; Caro(1800) queda fuera de [2000,3000].
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para empleadosEntre");
    }

    /**
     * Reto Extra 9: el departamento de un empleado (navegación inversa: empleado -> departamento).
     * @return el nombre del dept (el test pregunta por "Ana" -> "IT")
     */
    public static String departamentoDe(Connection c, String empleado) throws SQLException {
        // GUÍA: "SELECT d.nombre FROM DEPT d JOIN EMP e ON e.dept_id = d.id WHERE e.nombre = ?".
        // CULTURA: en OO seguirías la referencia e.departamento; en SQL es el JOIN en sentido inverso.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para departamentoDe");
    }

    /**
     * Reto Extra 10: total de empleados de la empresa.
     * @return COUNT(*) de EMP (el test inserta 3 -> espera 3)
     */
    public static int totalEmpleados(Connection c) throws SQLException {
        // GUÍA: "SELECT COUNT(*) FROM EMP"; rs.next(); return rs.getInt(1).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para totalEmpleados");
    }
}
