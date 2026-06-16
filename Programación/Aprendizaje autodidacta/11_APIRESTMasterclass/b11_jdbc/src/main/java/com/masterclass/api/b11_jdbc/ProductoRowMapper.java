package com.masterclass.api.b11_jdbc;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * {@link RowMapper} reutilizable que convierte una fila de PRODUCTO en un
 * {@link Producto} (teoría 11.9). "Una fila → un objeto".
 */
public class ProductoRowMapper implements RowMapper<Producto> {

    @Override
    public Producto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Producto(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getDouble("precio"));
    }
}
