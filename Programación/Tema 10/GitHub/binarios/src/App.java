import java.io.IOException;
import java.util.*;

import AplicacionLectura.AplicacionLectura;
import Clases.EstadisticasVotacion;
import Clases.Mesa;

public class App {
    public static void main(String[] args) {

        List<Mesa> mesas = new ArrayList<>();
        AplicacionLectura app = new AplicacionLectura();
        try {
            mesas.addAll(app.leerArchivoBin());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        EstadisticasVotacion est = new EstadisticasVotacion(mesas);

        System.out.println("Total votos: " + est.totalVotos());

        System.out.println("Votos por partido: " + est.votosPorPartido());

        System.out.println("Partido ganador: " + est.partidoGanador());

        System.out.println("Porcentajes: " + est.porcentajePorPartido());

        System.out.println("Media votos/mesa: " + est.mediaVotosPorMesa());

        Mesa mejor = est.mesaMayorParticipacion();
        System.out.println("Mesa con más participación: " + mejor.getIdMesa());
    }
}