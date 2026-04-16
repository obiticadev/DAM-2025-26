import Clases.DNI;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        final int MAX_DATOS = 2;
        DNI[] datos = new DNI[MAX_DATOS];

        String numeros = null;
        char letra = 0;

        for (int i = 0; i < datos.length; i++) {
            System.out.print("Introduce los 8 números de tu documento de identidad del sujeto " + (i + 1) + ": ");
            numeros = sc.nextLine();
            System.out.print("Introduce la letra del documento de identidad del sujeto " + (i + 1) + ": ");
            letra = sc.nextLine().charAt(0);

            datos[i] = new DNI(numeros, letra);

            if (datos[i].esValido(numeros, letra)) {
                System.out.println("\nDATOS VÁLIDOS Y GUADADOS 👍\n");
            } else {
                System.out.println("\n❌ DATOS INCORRECTOS, REPITE LA OPERACIÓN\n");
                i--;
            }
        }

        System.out.println("\nLOS DATOS GUARDADOS SON LOS SIGUIENTES\n------------------------------------");
        for (DNI dni : datos) {
            System.out.println(dni.mostrarDni());
        }

    }
}
