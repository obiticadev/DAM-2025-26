package com.bootcamp.nivel4_integracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Ej28 — Dos entidades relacionadas, un único Singleton
 * Teoría: teoria/04_Integracion_Escenarios.md
 *
 * Objetivo: gestionar dos entidades (Departamento y Empleado) con
 * dos DAOs distintos que comparten la misma instancia de Conexion.
 * Empleado tiene un campo idDepartamento que referencia a Departamento.
 *
 * La clave: Conexion.getConexion() siempre devuelve el mismo objeto
 * independientemente de desde qué DAO se llame.
 */
public class Ej28_EntidadesRelacionadas {

    // ── Entidades ─────────────────────────────────────────────────────────

    public static class Departamento {
        private final int id;
        private final String nombre;
        private final String ubicacion;

        public Departamento(String nombre, String ubicacion) {
            this(0, nombre, ubicacion);
        }

        public Departamento(int id, String nombre, String ubicacion) {
            this.id = id; this.nombre = nombre; this.ubicacion = ubicacion;
        }

        public int getId()          { return id; }
        public String getNombre()   { return nombre; }
        public String getUbicacion(){ return ubicacion; }

        @Override
        public String toString() { return id + " | " + nombre + " | " + ubicacion; }
    }

    public static class Empleado {
        private final int id;
        private final String nombre;
        private final double salario;
        private final int idDepartamento;

        public Empleado(String nombre, double salario, int idDepartamento) {
            this(0, nombre, salario, idDepartamento);
        }

        public Empleado(int id, String nombre, double salario, int idDepartamento) {
            this.id = id; this.nombre = nombre;
            this.salario = salario; this.idDepartamento = idDepartamento;
        }

        public int getId()              { return id; }
        public String getNombre()       { return nombre; }
        public double getSalario()      { return salario; }
        public int getIdDepartamento()  { return idDepartamento; }

        @Override
        public String toString() {
            return id + " | " + nombre + " | " + salario + " | dpto=" + idDepartamento;
        }
    }

    // ── Singleton compartido ──────────────────────────────────────────────

    private static Connection instance = null;
    private static final String URL = "jdbc:sqlite:bootcamp_ej28.db";

    public static Connection getConexion() {
        try {
            if (instance == null || instance.isClosed())
                instance = DriverManager.getConnection(URL);
        } catch (SQLException e) { System.err.println(e.getMessage()); }
        return instance;
    }

    // ── DAOs ──────────────────────────────────────────────────────────────

    public static class DAODepartamento {

        /**
         * Crea la tabla Departamentos si no existe.
         *
         * @throws SQLException si la sentencia DDL falla
         */
        public void crearTabla() throws SQLException {
            try (Statement s = getConexion().createStatement()) {
                s.execute("""
                        CREATE TABLE IF NOT EXISTS Departamentos (
                            id        INTEGER PRIMARY KEY AUTOINCREMENT,
                            nombre    TEXT NOT NULL,
                            ubicacion TEXT
                        )""");
            }
        }

        /**
         * Inserta un departamento en la tabla.
         *
         * @param d departamento a insertar
         * @return true si fue insertado, false si no
         * @throws SQLException si la inserción falla
         */
        public boolean insertar(Departamento d) throws SQLException {
            String sql = "INSERT INTO Departamentos (nombre, ubicacion) VALUES (?, ?)";
            // TODO 1: PreparedStatement con try-with-resources.
            //         setString(1, d.getNombre()), setString(2, d.getUbicacion()).
            //         Devuelve executeUpdate() > 0.
            return false;
        }

        /**
         * Devuelve todos los departamentos de la tabla.
         *
         * @return lista de departamentos; vacía si no hay ninguno
         * @throws SQLException si la consulta falla
         */
        public List<Departamento> obtenerTodos() throws SQLException {
            List<Departamento> lista = new ArrayList<>();
            String sql = "SELECT id, nombre, ubicacion FROM Departamentos";
            // TODO 2: Statement con try-with-resources.
            //         while(rs.next()) → new Departamento(id, nombre, ubicacion) → lista.add.
            //         Devuelve lista.
            return lista;
        }
    }

    public static class DAOEmpleado {

        /**
         * Crea la tabla Empleados si no existe.
         *
         * @throws SQLException si la sentencia DDL falla
         */
        public void crearTabla() throws SQLException {
            try (Statement s = getConexion().createStatement()) {
                s.execute("""
                        CREATE TABLE IF NOT EXISTS Empleados (
                            id               INTEGER PRIMARY KEY AUTOINCREMENT,
                            nombre           TEXT NOT NULL,
                            salario          REAL,
                            id_departamento  INTEGER
                        )""");
            }
        }

        /**
         * Inserta un empleado vinculado a un departamento.
         *
         * @param e empleado a insertar (con idDepartamento ya asignado)
         * @return true si fue insertado, false si no
         * @throws SQLException si la inserción falla
         */
        public boolean insertar(Empleado e) throws SQLException {
            String sql = "INSERT INTO Empleados (nombre, salario, id_departamento) VALUES (?, ?, ?)";
            // TODO 3: PreparedStatement con try-with-resources.
            //         setString(1, e.getNombre()), setDouble(2, e.getSalario()), setInt(3, e.getIdDepartamento()).
            //         Devuelve executeUpdate() > 0.
            return false;
        }

        /**
         * Devuelve todos los empleados de un departamento concreto.
         *
         * @param idDepartamento id del departamento por el que filtrar
         * @return lista de empleados de ese departamento; vacía si no hay ninguno
         * @throws SQLException si la consulta falla
         */
        public List<Empleado> obtenerPorDepartamento(int idDepartamento) throws SQLException {
            String sql = "SELECT id, nombre, salario, id_departamento FROM Empleados WHERE id_departamento = ?";
            List<Empleado> lista = new ArrayList<>();
            // TODO 4: PreparedStatement con try-with-resources.
            //         setInt(1, idDepartamento).
            //         while(rs.next()) → new Empleado(id, nombre, salario, idDpto) → lista.add.
            //         Devuelve lista.
            return lista;
        }
    }

    // ── Zona de Ejecución Master ──────────────────────────────────────────
    public static void main(String[] args) throws SQLException {
        DAODepartamento daoDpto  = new DAODepartamento();
        DAOEmpleado     daoEmp   = new DAOEmpleado();

        daoDpto.crearTabla();
        daoEmp.crearTabla();

        // Verificar que ambos DAOs usan el mismo objeto Connection
        System.out.println("¿Misma conexión en ambos DAOs? "
                + (getConexion() == getConexion())); // siempre true

        daoDpto.insertar(new Departamento("Desarrollo", "Planta 2"));
        daoDpto.insertar(new Departamento("Marketing", "Planta 1"));

        daoEmp.insertar(new Empleado("Ana García", 32000, 1));
        daoEmp.insertar(new Empleado("Luis Pérez", 28000, 2));
        daoEmp.insertar(new Empleado("Sara López", 35000, 1));

        System.out.println("=== Departamentos ===");
        daoDpto.obtenerTodos().forEach(System.out::println);

        System.out.println("\n=== Empleados de Desarrollo (id=1) ===");
        daoEmp.obtenerPorDepartamento(1).forEach(System.out::println);
    }
}
