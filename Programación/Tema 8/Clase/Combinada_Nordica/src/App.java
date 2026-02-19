import java.time.LocalTime;
import java.util.ArrayList;

import Clases.Atleta;
import DAO.DAOatletas;

public class App {
    public static void main(String[] args) throws Exception {
        LocalTime horaComienzo = LocalTime.of(15, 0, 0);

        DAOatletas dao = new DAOatletas();
        ArrayList<Atleta> lista = dao.entragarDatos();

        double PuntuacionMAX = lista.get(primerAtleta(lista)).getPuntuacionSalto();

        for (int i = 0; i < lista.size(); i++) {
            LocalTime horaPartidaAtleta = lista.get(i).Gundersen(PuntuacionMAX, horaComienzo);
            lista.get(i).setHoraComienzo(horaPartidaAtleta);
            System.out.println(lista.get(i).toString());
        }

    }

    public static int primerAtleta(ArrayList<Atleta> lista) {
        double puntuacionMax = -1;
        int pos = -1;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getPuntuacionSalto() > puntuacionMax) {
                puntuacionMax = lista.get(i).getPuntuacionSalto();
                pos = i;
            }

        }
        return pos;
    }
}
