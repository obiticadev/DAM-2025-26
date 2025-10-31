import java.util.Scanner;

public class ejercicioRepaso1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num;
        int factorial = 1;

        System.out.println("Introduce un número para calcular su factorial: ");
        num = sc.nextInt();

        System.out.print("Factorial de " + num + "! = ");
        for (int i = num; i > 0; i--) {
            if (i == 1) {
                System.out.print(i);
                factorial = factorial * i;
            } else {

                System.out.print(i + " x ");
                factorial = factorial * i;
            }
        }
        System.out.println("= " + factorial);
    }
}
