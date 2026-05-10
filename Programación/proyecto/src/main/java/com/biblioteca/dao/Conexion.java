package com.biblioteca.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.biblioteca.Enum.Aviso;

public class Conexion {

    private static final String URL = "jdbc:sqlite:biblioteca.db";

    private Conexion() {
    }

    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void close() {
        new Logs("Cierre de la aplicación: liberando recursos de Conexion", Aviso.INFO).guardarLog();
    }
}
