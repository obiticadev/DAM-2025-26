package com.masterclass.api.b11_jdbc;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.dao.DataAccessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link ResultSetExtractor} reutilizable que recibe el {@link ResultSet}
 * COMPLETO y extrae la lista de {@link Producto} (teoría 11.9). A diferencia de
 * un {@code RowMapper}, ve todas las filas a la vez.
 */
public class ProductoExtractor implements ResultSetExtractor<List<Producto>> {

    @Override
    public List<Producto> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Producto> out = new ArrayList<>();
        while (rs.next()) {
            out.add(new Producto(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("precio")));
        }
        return out;
    }
}
