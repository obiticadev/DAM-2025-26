import java.util.Scanner;

public class if_else {
    public static void main(String[] args) throws Exception {
        

        // Declaración de variables
        final int NUMERO = 3;
        int num;
        Scanner sc = new Scanner(System.in);
        System.out.println("Dime un número");
        num = sc.nextInt();

        // if(condición)
        if (num > NUMERO) {
            System.out.println("Te has pasado");

        }else if (num < NUMERO) {
            System.out.println("Demasiado poco");

        }else{
            System.out.println("FELICIDADES, HAS ACERTADO");
            
        }
        
    }
}
