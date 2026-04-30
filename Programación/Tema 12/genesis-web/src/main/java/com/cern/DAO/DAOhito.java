package com.cern.DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.cern.Clases.HitoHistorico;

public class DAOhito {

    public DAOhito() {
    }

    public void crearTabla() throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS HitosHistoricos (
                    id               INTEGER PRIMARY KEY AUTOINCREMENT,
                    url              TEXT NOT NULL,
                    nombre           TEXT NOT NULL,
                    autor            TEXT,
                    anio_lanzamiento INTEGER,
                    descripcion      TEXT
                );
                """;
        try (Statement stmt = Conexion.getConexion().createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabla creada correctamente");
        }
    }

    public boolean insertarHito(HitoHistorico hito) throws SQLException {
        String sql = "INSERT INTO HitosHistoricos (url, nombre, autor, anio_lanzamiento, descripcion) VALUES (?, ?, ?, ?, ?);";
        try (PreparedStatement pst = Conexion.getConexion().prepareStatement(sql)) {
            pst.setString(1, hito.getUrl());
            pst.setString(2, hito.getNombre());
            pst.setString(3, hito.getAutor());
            pst.setInt(4, hito.getAnioLanzamiento());
            pst.setString(5, hito.getDescripcion());

            int actualizado = pst.executeUpdate();

            return actualizado > 0;
        }
    }
}
