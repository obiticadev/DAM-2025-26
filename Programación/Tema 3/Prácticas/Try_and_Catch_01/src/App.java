import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scNum = new Scanner(System.in);
        
        final int MAX = 5;
        int[] array = new int[MAX];

        System.out.println();

        for (int i = 0; i < array.length; i++) {
            try {
                System.out.print("Introduce el número " + (i+1) + ": ");
                array[i] = scNum.nextInt();
                
            } catch (InputMismatchException e) {
                System.out.println(e.getClass().getName());
                System.out.println("Error: Al no introducir un valor entero se ha asignado en el array con un -1");
                array[i] = -1;
                scNum.next();
            }
        }
        System.out.println(Arrays.toString(array));
        scNum.close();
        
    }
}
