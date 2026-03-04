import java.util.ArrayList;
import java.util.HashSet;

import Clases.Servidor;
import DAO.ServidorDAO;

public class App {
    public static void main(String[] args) throws Exception {
        ServidorDAO dao = new ServidorDAO();

        /*
         * Collection<Servidor> servidores = dao.devolverArrayServidor();
         * servidores.addAll(dao.devolverHashServidor());
         * 
         * for (Servidor s : servidores) {
         * System.out.println(s.toString());
         * }
         */

        ArrayList<Servidor> listServidores = dao.devolverArrayServidor();
        HashSet<Servidor> hashServidores = dao.devolverHashServidor();

        for (Servidor s : listServidores) {
            System.out.println(s.toString());
        }
        System.out.println();
        for (Servidor s : hashServidores) {
            System.out.println(s.toString());
        }

    }
}
