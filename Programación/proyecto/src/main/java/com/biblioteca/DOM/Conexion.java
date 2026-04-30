package com.biblioteca.DOM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private final String URL = "jdbc:sqlite:biblioteca.db";

    public Conexion() {
    }

    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
