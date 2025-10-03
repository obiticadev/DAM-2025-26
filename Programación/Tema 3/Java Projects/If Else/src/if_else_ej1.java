import java.util.Scanner;

public class if_else_ej1 {
    public static void main(String[] args) {
        // Declaración de variables
        final String var1 = "Lunes";
        final String var2 = "Martes";
        final String var3 = "Miércoles";
        final String var4 = "Jueves";
        final String var5 = "Viernes";
        final String var6 = "Sábado";
        final String var7 = "Domingo";
        int numSelect;
        int horaSelect;

        Scanner numScanner = new Scanner(System.in);
        System.out.println("Escribe el número del día de la semana que quieres entrar");
        numSelect = numScanner.nextInt();
        System.out.println("Escribe a qué hora quieres entrar, a la 1, a las 2, a las 3 o a las 4");
        horaSelect = numScanner.nextInt();

        if ((numSelect == 1 && horaSelect < 3) || (numSelect == 2 && horaSelect > 2) || (numSelect == 3 && horaSelect > 1 && horaSelect < 4)) {
            System.out.println("Puedes entrar");
        }else{
            System.out.println("No puedes entrar");
        }
        numScanner.close();
        
    }
}
