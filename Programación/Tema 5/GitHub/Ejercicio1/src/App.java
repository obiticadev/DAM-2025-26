
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import Clases.Calendario;
import Clases.Utilidades;

public class App {
    public static void main(String[] args) throws Exception {
        LocalDate hoy = LocalDate.now();
        int dia = hoy.getDayOfMonth();
        System.out.println(dia);

        Calendario[] dias = new Calendario[24];
        String[] sorpresas = Utilidades.getSorpresas();
        for (int i = 0; i < dias.length; i++) {
            if (i < dia) {
                dias[i] = new Calendario(i, sorpresas[i], true);
                System.out.println("En el día número " + dias[i].getNumDia() + ", el regalo es " + dias[i].getSorpresa()
                        + ", y se encuentra " + dias[i].getEstado());
            } else {
                dias[i] = new Calendario(i, "por descubrir", false);
                System.out.println("En el día número " + dias[i].getNumDia() + ", el regalo es " + dias[i].getSorpresa()
                        + ", y se encuentra " + dias[i].getEstado());

            }
        }

    }
}
