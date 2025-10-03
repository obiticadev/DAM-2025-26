package UT02;

import java.time.LocalDate;
import java.time.Period;
import java.util.GregorianCalendar;

/**
 * Ejercicio 2.
 * Crea un programa que a partir de la fecha de nacimiento diga la edad
 * en años, meses y días de la persona.
 * Y los bisiestos? Es muy tedioso! Investiga "java gregorian calendar",
 * e intenta resolver el ejercicio haciendo uso de ello.
 */
public class e3e2 {
    public static void main(String[] args){
        System.out.println("--- Ejemplo 3 - Ejercicio 2 ---");
        
        // Usando gragorian calendar... Boomer...
        System.out.println("Con gregorian calendar:");
        GregorianCalendar fechaNacimiento = new GregorianCalendar(2000, 5, 25);
        GregorianCalendar ahora = new GregorianCalendar(2023, 9, 26);

        long diferenciaMillis = ahora.getTimeInMillis() - fechaNacimiento.getTimeInMillis();
        long diasTotales = diferenciaMillis / (24 * 60 * 60 * 1000);
        int anios = (int)(diasTotales / 365);
        int meses = (int)((diasTotales % 365) / 31); // poco preciso 
        int dias = (int)((diasTotales % 365) % 31);

        System.out.println("Han pasado " + anios + " años, " + meses 
        + " meses, y " + dias + " dias.");

        // Si no fuese un Boomer y hubiese puesto LocalDate en el enunciado...
        System.out.println("Con local date:");
        LocalDate fechaNacimientoLD = LocalDate.of(2000, 5, 25);
        LocalDate ahoraLD = LocalDate.now();
        Period periodo = Period.between(fechaNacimientoLD, ahoraLD);

        int aniosLD = periodo.getYears();
        int mesesLD = periodo.getMonths();
        int diasLD = periodo.getDays();

        System.out.println("Han pasado " + aniosLD + " años, " + mesesLD 
        + " meses y " + diasLD + " desde " + fechaNacimientoLD.toString());

    }
}
