import java.util.ArrayList;

import Clases.EventoAstronomico;
import DAO.EventoAstronomicoDAO;

public class App {
    public static void main(String[] args) throws Exception {
        EventoAstronomicoDAO dao = new EventoAstronomicoDAO();
        ArrayList<EventoAstronomico> eventos = dao.obtenerTodos();

        System.out.println();

    }
}
