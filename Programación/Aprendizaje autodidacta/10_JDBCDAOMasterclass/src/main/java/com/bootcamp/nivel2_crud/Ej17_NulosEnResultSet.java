package com.bootcamp.nivel2_crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * Ej17 — Detección de NULL con rs.wasNull()
 * Teoría: teoria/02_PreparedStatement_ResultSet.md
 *
 * Objetivo: entender por qué rs.getInt() devuelve 0 cuando el valor en BD es NULL
 * y cómo usar rs.wasNull() para distinguir el caso.
 * También practicarás pst.setNull() para insertar null desde Java.
 *
 * Trampa clásica: rs.getInt("edad") devuelve 0 tanto si el valor es 0
 * como si es NULL — sin wasNull() no puedes diferenciarlos.
 */
public class Ej17_NulosEnResultSet {

    public static class Persona {
        public final int id;
        public final String nombre;
        public final Integer edad;      // Integer (objeto) para poder ser null
        public final String telefono;   // null si no hay teléfono

        public Persona(int id, String nombre, Integer edad, String telefono) {
            this.id = id;
            this.nombre = nombre;
            this.edad = edad;
            this.telefono = telefono;
        }

        @Override
        public String toString() {
            return id + " | " + nombre
                    + " | edad=" + (edad == null ? "NULL" : edad)
                    + " | tel=" + (telefono == null ? "NULL" : telefono);
        }
    }

    private static Connection instance = null;
    private static final String URL = "jdbc:sqlite:bootcamp_ej17.db";

    public static Connection getConexion() {
        try {
            if (instance == null || instance.isClosed()) {
                instance = DriverManager.getConnection(URL);
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar: " + e.getMessage());
        }
        return instance;
    }

    /**
     * Prepara la tabla Personas con filas que incluyen valores NULL.
     *
     * @throws SQLException si alguna operación falla
     */
    public static void inicializar() throws SQLException {
        try (Statement s = getConexion().createStatement()) {
            s.execute("""
                    CREATE TABLE IF NOT EXISTS Personas (
                        id       INTEGER PRIMARY KEY AUTOINCREMENT,
                        nombre   TEXT NOT NULL,
                        edad     INTEGER,
                        telefono TEXT
                    )""");
        }
        try (Statement s = getConexion().createStatement();
             ResultSet rs = s.executeQuery("SELECT COUNT(*) FROM Personas")) {
            if (rs.next() && rs.getInt(1) == 0) {
                String ins = "INSERT INTO Personas (nombre, edad, telefono) VALUES (?, ?, ?)";
                // Fila con todos los datos
                try (PreparedStatement pst = getConexion().prepareStatement(ins)) {
                    pst.setString(1, "Ana");
                    pst.setInt(2, 30);
                    pst.setString(3, "600111222");
                    pst.executeUpdate();
                }
                // Fila sin edad ni teléfono (ambos NULL)
                try (PreparedStatement pst = getConexion().prepareStatement(ins)) {
                    pst.setString(1, "Bob");
                    pst.setNull(2, Types.INTEGER);
                    pst.setNull(3, Types.VARCHAR);
                    pst.executeUpdate();
                }
                // Fila sin teléfono
                try (PreparedStatement pst = getConexion().prepareStatement(ins)) {
                    pst.setString(1, "Carmen");
                    pst.setInt(2, 25);
                    pst.setNull(3, Types.VARCHAR);
                    pst.executeUpdate();
                }
            }
        }
    }

    /**
     * Inserta una persona con campos opcionales que pueden ser null.
     * Cuando edad es null usa pst.setNull(pos, Types.INTEGER).
     * Cuando telefono es null usa pst.setNull(pos, Types.VARCHAR).
     *
     * @param nombre   nombre de la persona (nunca null)
     * @param edad     edad de la persona, o null si no se conoce
     * @param telefono teléfono de la persona, o null si no tiene
     * @return true si la inserción fue exitosa, false si no
     * @throws SQLException si la inserción falla
     */
    public static boolean insertarPersona(String nombre, Integer edad, String telefono) throws SQLException {
        String sql = "INSERT INTO Personas (nombre, edad, telefono) VALUES (?, ?, ?)";
        // TODO 1: Abre PreparedStatement con try-with-resources.
        //         Posición 1 → setString para nombre.
        //         Posición 2 → si edad != null usa setInt; si es null usa setNull(2, Types.INTEGER).
        //         Posición 3 → si telefono != null usa setString; si es null usa setNull(3, Types.VARCHAR).
        //         Devuelve afectadas > 0.
        return false;
    }

    /**
     * Lee todas las personas mapeando NULL correctamente a null de Java.
     * Para detectar NULL: lee el valor con getInt/getString y luego comprueba wasNull().
     *
     * @return lista de personas con campos null donde corresponda
     * @throws SQLException si la consulta falla
     */
    public static List<Persona> obtenerTodas() throws SQLException {
        String sql = "SELECT id, nombre, edad, telefono FROM Personas";
        List<Persona> lista = new ArrayList<>();
        // TODO 2: Ejecuta la query con try-with-resources.
        //         En el while(rs.next()):
        //           int id = rs.getInt("id")
        //           String nombre = rs.getString("nombre")
        //
        //           Para edad (INTEGER que puede ser NULL):
        //             int edadRaw = rs.getInt("edad");
        //             Integer edad = rs.wasNull() ? null : edadRaw;
        //
        //           Para telefono (TEXT que puede ser NULL):
        //             String tel = rs.getString("telefono");
        //             (getString ya devuelve null si el valor es NULL en BD, wasNull no es necesario)
        //
        //         Crea new Persona(id, nombre, edad, tel) y añádelo a lista.
        return lista;
    }

    // ── Zona de Ejecución Master ──────────────────────────────────────────
    public static void main(String[] args) throws SQLException {
        inicializar();

        System.out.println("=== Personas con manejo de NULL ===");
        obtenerTodas().forEach(System.out::println);

        System.out.println("\nInsertando persona sin edad ni teléfono:");
        insertarPersona("David", null, null);
        System.out.println("Personas tras inserción:");
        obtenerTodas().forEach(System.out::println);
    }
}
