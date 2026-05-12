package jdbc_masterclass.nivel03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Nivel03_B_UpdateCalculado {

    /**
     * Misión: Aplicar un descuento del 20% en BBDD
     * No hay que extraer los datos y multiplicarlos en Java. 
     * Hay que delegar la multiplicación al motor SQL usando: precio = precio * 0.8
     *
     * @param conn Conexión activa
     * @return Cantidad de productos rebajados
     */
    public static int rebajarPreciosUnVeintePorciento(Connection conn) {
        String sql = "UPDATE productos SET precio = precio * 0.8";
        
        // TODO: Abre el Statement
        // TODO: Usa executeUpdate y devuelve su valor de retorno directamente
        
        return 0;
    }
}
