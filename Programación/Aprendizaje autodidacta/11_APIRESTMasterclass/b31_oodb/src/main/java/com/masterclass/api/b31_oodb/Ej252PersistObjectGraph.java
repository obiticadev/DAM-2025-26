package com.masterclass.api.b31_oodb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio 252 · Persistir un grafo de objetos "a mano" (el desfase objeto-relacional).
 *
 * <p>Una BD de objetos guardaría un {@link Departamento} con su lista de {@link Empleado}
 * directamente. En una BD relacional hay que <b>aplanar</b> el grafo a filas (tabla DEPT +
 * tabla EMP con clave foránea) al guardar, y <b>rehidratarlo</b> al cargar. Ese trabajo
 * manual es justo lo que automatiza JPA en b12; aquí lo haces tú para entender qué pasa por
 * debajo.
 *
 * <p>El test crea las tablas DEPT(id AUTO_INCREMENT, nombre) y EMP(id, nombre, salario, dept_id).
 *
 * <p>Teoría: {@code teoria/31_ObjetoRelacional_OO.md} (sección 31.4).
 */
public final class Ej252PersistObjectGraph {

    private Ej252PersistObjectGraph() {
    }

    /**
     * Guarda un departamento y sus empleados, devolviendo el id generado del departamento.
     *
     * @return el id autogenerado del departamento, o -1 si no se ha implementado
     */
    public static int guardar(Connection c, Departamento d) throws SQLException {
        // TODO 1: inserta el departamento pidiendo la clave generada:
        //         c.prepareStatement("INSERT INTO DEPT(nombre) VALUES (?)", Statement.RETURN_GENERATED_KEYS).
        // TODO 2: ps.setString(1, d.getNombre()) y ps.executeUpdate().
        // TODO 3: recupera la clave: try (var gk = ps.getGeneratedKeys()) { gk.next(); idDept = gk.getInt(1); }.
        // TODO 4: prepara "INSERT INTO EMP(nombre, salario, dept_id) VALUES (?,?,?)".
        // TODO 5: por cada Empleado de d.getEmpleados(), fija nombre/salario/idDept y addBatch() (aplanas el grafo).
        // TODO 6: executeBatch() y devuelve idDept (propaga SQLException).
        return -1;
    }

    /**
     * Carga un departamento completo (con sus empleados) reconstruyendo el grafo de objetos.
     *
     * @return el Departamento rehidratado, o {@code null} si no existe o no se ha implementado
     */
    public static Departamento cargar(Connection c, int id) throws SQLException {
        // TODO 7: lee el departamento: SELECT nombre FROM DEPT WHERE id=?; si !rs.next() devuelve null; guarda el nombre.
        // TODO 8: lee sus empleados: SELECT id,nombre,salario FROM EMP WHERE dept_id=? ORDER BY id.
        // TODO 9: por cada fila crea new Empleado(id,nombre,salario) y mételo en una List (rehidratar el grafo).
        // TODO 10: devuelve new Departamento(id, nombre, lista) (try-with-resources; propaga SQLException).
        return null;
    }

    public static void main(String[] args) throws SQLException {
        try (Connection c = DriverManager.getConnection("jdbc:h2:mem:demo252", "sa", "")) {
            var st = c.createStatement();
            st.execute("CREATE TABLE DEPT(id INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(50))");
            st.execute("CREATE TABLE EMP(id INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(50), salario INT, dept_id INT)");
            Departamento it = new Departamento("IT")
                    .addEmpleado(new Empleado("Ana", 3000))
                    .addEmpleado(new Empleado("Beto", 2500));
            int id = guardar(c, it);
            System.out.println("guardar -> id = " + id);
            System.out.println("cargar(" + id + ") = " + cargar(c, id));
        }
    }

    /**
     * Reto Extra 1: cuántos empleados tiene un departamento (COUNT).
     * @return el número de empleados del dept (el test inserta 2 y espera 2)
     */
    public static int contarEmpleados(Connection c, int idDept) throws SQLException {
        // GUÍA: "SELECT COUNT(*) FROM EMP WHERE dept_id=?"; rs.next(); return rs.getInt(1).
        // OJO: el test inserta las filas con SQL directo, así que NO depende de que guardar() esté hecho.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarEmpleados");
    }

    /**
     * Reto Extra 2: el nombre de un departamento por su id.
     * @return el nombre, o null si no existe (el test inserta "IT" y lo espera)
     */
    public static String nombreDepartamento(Connection c, int id) throws SQLException {
        // GUÍA: "SELECT nombre FROM DEPT WHERE id=?"; if(!rs.next()) return null; return rs.getString(1).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para nombreDepartamento");
    }

    /**
     * Reto Extra 3: suma de salarios de un departamento.
     * @return SUM(salario) del dept (el test inserta 3000+2500 y espera 5500)
     */
    public static int salarioTotal(Connection c, int idDept) throws SQLException {
        // GUÍA: "SELECT SUM(salario) FROM EMP WHERE dept_id=?"; rs.next(); return rs.getInt(1).
        // OJO: si no hay empleados SUM devuelve NULL -> getInt da 0; el test usa un dept con empleados.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para salarioTotal");
    }

    /**
     * Reto Extra 4: ¿existe un departamento con ese id?
     * @return true si existe (el test inserta uno y espera true)
     */
    public static boolean existeDepartamento(Connection c, int id) throws SQLException {
        // GUÍA: reutiliza nombreDepartamento(c, id) != null, o consulta COUNT(*) y compáralo con 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para existeDepartamento");
    }

    /**
     * Reto Extra 5: el empleado mejor pagado de un departamento.
     * @return el nombre del de mayor salario (el test espera "Ana" con salario 3000)
     */
    public static String empleadoMejorPagado(Connection c, int idDept) throws SQLException {
        // GUÍA: "SELECT nombre FROM EMP WHERE dept_id=? ORDER BY salario DESC LIMIT 1"; rs.next(); getString(1).
        // PISTA: si no hay empleados, devuelve null (controla !rs.next()).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para empleadoMejorPagado");
    }

    /**
     * Reto Extra 6: guarda un departamento y devuelve cuántos empleados quedaron persistidos.
     * @return contarEmpleados del dept recién guardado (el test guarda un dept con 2 -> espera 2)
     */
    public static int guardarYContar(Connection c, Departamento d) throws SQLException {
        // GUÍA: int id = guardar(c, d); return contarEmpleados(c, id);  // encadena dos métodos del fichero.
        // CUIDADO: depende de que guardar() y contarEmpleados() estén implementados (rojo hasta entonces).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para guardarYContar");
    }

    /**
     * Reto Extra 7: actualiza el salario de un empleado y devuelve las filas afectadas.
     * @return el número de filas modificadas (el test actualiza 1 empleado -> espera 1)
     */
    public static int actualizarSalario(Connection c, int idEmp, int nuevoSalario) throws SQLException {
        // GUÍA: "UPDATE EMP SET salario=? WHERE id=?"; ps.setInt(1,nuevoSalario); ps.setInt(2,idEmp);
        //       return ps.executeUpdate();  // executeUpdate devuelve el nº de filas afectadas.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para actualizarSalario");
    }

    /**
     * Reto Extra 8: borra un departamento y sus empleados (borrado en cascada manual).
     * @return true si el borrado se ejecutó (el test inserta dept+empleados y luego comprueba que desaparecen)
     */
    public static boolean borrarDepartamentoEnCascada(Connection c, int id) throws SQLException {
        // GUÍA: primero "DELETE FROM EMP WHERE dept_id=?" y después "DELETE FROM DEPT WHERE id=?"
        // (el orden importa por la integridad referencial conceptual). Devuelve true al terminar.
        // CULTURA: en JPA esto es cascade=REMOVE; aquí lo haces tú, en el orden hijo->padre.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para borrarDepartamentoEnCascada");
    }

    /**
     * Reto Extra 9: los ids de los empleados de un departamento.
     * @return lista de ids ordenada (el test inserta 2 empleados y espera sus ids)
     */
    public static List<Integer> idsDeEmpleados(Connection c, int idDept) throws SQLException {
        // GUÍA: "SELECT id FROM EMP WHERE dept_id=? ORDER BY id"; recorre y añade rs.getInt(1) a una List<Integer>.
        // PISTA: devuelve lista vacía (no null) si no hay empleados.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para idsDeEmpleados");
    }

    /**
     * Reto Extra 10: guardar un departamento sin empleados y cargarlo: la lista debe venir vacía.
     * @return true si al cargar el dept guardado su lista de empleados está vacía
     */
    public static boolean departamentoVacioSeCargaVacio(Connection c) throws SQLException {
        // GUÍA: int id = guardar(c, new Departamento("Vacio")); Departamento d = cargar(c, id);
        //       return d != null && d.getEmpleados().isEmpty();
        // OJO: un grafo sin hijos debe rehidratarse con lista vacía, NUNCA null (contrato de cargar).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para departamentoVacioSeCargaVacio");
    }
}
