import Clases.Producto;
import Clases.Usuario;
import DAO.DaoMemoria;
import Interfaz.Dao;

public class App {
    public static void main(String[] args) throws Exception {
        Dao<Usuario> usuarioDao = new DaoMemoria<>();
        Dao<Producto> producoDao = new DaoMemoria<>();
        usuarioDao.guardar(new Usuario("Oliver"));
        producoDao.guardar(new Producto("Coche"));

        System.out.println(usuarioDao.obtenerTodos());
    }
}
