import java.util.Scanner;

public class tabla_de_multiplicar {
    public static void main(String[] args) throws Exception {
        System.out.println("Tabla de multiplicar");

        // Variables
        int num;
        int operacion;
        
        Scanner numScan = new Scanner(System.in);
        System.out.println("Inserta un número para mostrarte su tabla de multiplicar:");
        num = numScan.nextInt();
        System.out.println("Tabla del " + num + "\n-----------");
        for (int i = 0; i <= 10 ; i++) {
            operacion = num * i;
            System.out.println(num + " x " + i + " = " + operacion);
        }
    }
}
