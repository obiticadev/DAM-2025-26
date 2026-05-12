package jdbc_masterclass.nivel03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class Nivel03_D_UsoDeSetNull {

    /**
     * Misión: Control de valores Nulos en BD
     * Cuando guardamos un libro físico, su 'url_descarga' debe ser nulo expresamente.
     * 
     * @param conn Conexión activa
     * @param titulo Titulo del libro
     * @param esDigital Si es digital tendrá URL, si es físico tendrá Nulo.
     */
    public static void insertarLibroMixto(Connection conn, String titulo, boolean esDigital) throws SQLException {
        String sql = "INSERT INTO libros (titulo, url_descarga) VALUES (?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, titulo);
            
            // TODO: Si esDigital es true, usa pstmt.setString(2, "http://...");
            // TODO: Si esDigital es false, usa pstmt.setNull(2, Types.VARCHAR);
            
            // TODO: executeUpdate
        }
    }
}
