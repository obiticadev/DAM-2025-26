package Clases;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import DAO.EventoAstronomicoDAO;
import Interfaces.EventoRepository;

public class Observatorio implements EventoRepository {
    private EventoAstronomicoDAO dao = new EventoAstronomicoDAO();
    private ArrayList<EventoAstronomico> eventos = dao.obtenerTodos();

    @Override
    public void guardar(EventoAstronomico evento) {
        eventos.add(evento);
    }

    @Override
    public String listarTodos() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < eventos.size(); i++) {
            sb.append(eventos.get(i).getDescripcion()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String proximoEvento() {

        Collections.sort(eventos);
        for (int i = 0; i < eventos.size(); i++) {
            if (Duration.between(LocalDate.now(), eventos.get(i).getFecha()).toDays() > 0) {
                return eventos.get(i).getDescripcion();
            }

        }
        return "Sin eventos próximos";
    }

    @Override
    public ArrayList<Eclipse> eclipsesVisiblesEn(String zona) {

    }

}
