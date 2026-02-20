import Clases.Atleta;
import DAO.DAOatletas;
import java.time.LocalTime;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        LocalTime horaComienzo = LocalTime.of(15, 0, 0);
        final double RETRASO = 1.5;

        DAOatletas dao = new DAOatletas();
        ArrayList<Atleta> lista = dao.entragarDatos();

        System.out.println("\nOK - " + lista.size() + " atletas cargados corractamente\n");

        System.out.println("Hora base de salida: " + horaComienzo.toString() + "\n");

        double PuntuacionMAX = lista.get(primerAtleta(lista)).getPuntuacionSalto();
        System.out.println("Puntuación líder detectada: " + PuntuacionMAX + " ("
                + lista.get(primerAtleta(lista)).getNombre() + ")\n");

        System.out.println("Calculando retrasos:\n");

        for (int i = 0; i < lista.size(); i++) {
            LocalTime horaPartidaAtleta = lista.get(i).Gundersen(PuntuacionMAX, horaComienzo);
            lista.get(i).setHoraComienzo(horaPartidaAtleta);
            StringBuilder sb = new StringBuilder();
            sb.append(lista.get(i).getNombre()).append(":\n")
                    .append("\tDiferencia = ").append(PuntuacionMAX).append(" - ")
                    .append(lista.get(i).getPuntuacionSalto()).append(" = ")
                    .append(PuntuacionMAX - lista.get(i).getPuntuacionSalto()).append("\n")
                    .append("\tRetraso = Math.round(").append(PuntuacionMAX - lista.get(i).getPuntuacionSalto())
                    .append(" * ").append(RETRASO).append(" = ")
                    .append(Math.round((PuntuacionMAX - lista.get(i).getPuntuacionSalto()) * RETRASO))
                    .append(" segundos");
            System.out.println(sb.toString());
        }

        System.out.println("""

                ========================================
                           PARRILLA DE SALIDA
                ========================================
                """);
        for (int i = 0; i < lista.size(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(i + 1).append(". ").append(lista.get(i).getNombre()).append("\t| ")
                    .append(lista.get(i).getPuntuacionSalto()).append(" pts | ")
                    .append(lista.get(i).getHoraComienzo().toString());
            System.out.println(sb.toString());
        }
        System.out.println("""

                ========================================
                   ¡Listos para la carrera de fondo!
                ========================================
                """);

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
