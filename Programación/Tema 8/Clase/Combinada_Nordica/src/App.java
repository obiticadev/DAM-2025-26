import Clases.Atleta;
import DAO.DAOatletas;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class App {
    public static void main(String[] args) throws Exception {
        LocalTime horaComienzo = LocalTime.of(15, 0, 0);
        final Double RETRASO = 1.5;

        DAOatletas dao = new DAOatletas();
        ArrayList<Atleta> lista = dao.entragarDatos();

        System.out.println("\nOK - " + lista.size() + " atletas cargados corractamente\n");

        System.out.println("Hora base de salida: " + horaComienzo.toString() + "\n");

        Double PuntuacionMAX = lista.get(primerAtleta(lista)).getPuntuacionSalto();
        System.out.println("Puntuación líder detectada: " + PuntuacionMAX + " ("
                + lista.get(primerAtleta(lista)).getNombre() + ")\n");

        System.out.println("Calculando retrasos:\n");

        for (int i = 0; i < lista.size(); i++) {
            LocalTime horaPartidaAtleta = lista.get(i).Gundersen(PuntuacionMAX, horaComienzo);

            lista.get(i).setHoraComienzo(horaPartidaAtleta);

            StringBuilder sb = new StringBuilder();

            Double puntuacionSalto = lista.get(i).getPuntuacionSalto();
            Double diferencia = PuntuacionMAX - puntuacionSalto;

            StringBuilder append = sb.append(lista.get(i).getNombre()).append(":\n")
                    .append("\tDiferencia = ").append(PuntuacionMAX).append(" - ")
                    .append(puntuacionSalto).append(" = ")
                    .append(diferencia).append("\n")
                    .append("\tRetraso = Math.round(").append(diferencia)
                    .append(" * ").append(RETRASO).append(" = ")
                    .append(Math.round(diferencia * RETRASO))
                    .append(" segundos");
            System.out.println(sb.toString());
        }

        parrillaDeSalida(lista);

    }

    public static int primerAtleta(ArrayList<Atleta> lista) {
        Double puntuacionMax = -1.0;
        int pos = -1;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getPuntuacionSalto() > puntuacionMax) {
                puntuacionMax = lista.get(i).getPuntuacionSalto();
                pos = i;
            }

        }
        return pos;
    }

    public static void parrillaDeSalida(ArrayList<Atleta> lista) {
        Collections.sort(lista);
        for (int i = 0; i < lista.size() - 1; i++) {
            if (lista.get(i).getPuntuacionSalto().equals(lista.get(i + 1).getPuntuacionSalto())) {
                throw new UnsupportedOperationException(
                        "Error: 2 Atletas " + lista.get(i).getNombre() + " y " + lista.get(i + 1).getNombre()
                                + " tienen la misma puntuación de " + lista.get(i).getPuntuacionSalto());
            }
        }

        System.out.println("""

                ========================================
                           PARRILLA DE SALIDA
                ========================================
                """);
        for (int i = 0; i < lista.size(); i++) {
            StringBuilder sb = new StringBuilder();

            String nombre = lista.get(i).getNombre();
            Double puntuacionSalto = lista.get(i).getPuntuacionSalto();
            String horaComienzoAtleta = lista.get(i).getHoraComienzo().toString();

            sb.append(i + 1).append(". ").append(nombre).append("\t| ")
                    .append(puntuacionSalto).append(" pts | ")
                    .append(horaComienzoAtleta);
            System.out.println(sb.toString());
        }
        System.out.println("""

                ========================================
                   ¡Listos para la carrera de fondo!
                ========================================
                """);
    }
}
