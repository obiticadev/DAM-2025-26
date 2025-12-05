
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class App {
    public static void main(String[] args) throws Exception {
        LocalDate hoy = LocalDate.now();
        int dia = hoy.getDayOfMonth();
        System.out.println(dia);
    }
}
