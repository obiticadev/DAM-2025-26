import java.util.Scanner;

public class cuadrado_for {
    public static void main(String[] args) throws Exception {
        int columnas;
        int filas;
        char caracter;

        System.out.println("Cuadrado con for");

        Scanner numScan = new Scanner(System.in);
        Scanner charScan = new Scanner(System.in);
        
        System.out.println("¿Cuántas columnas quieres?");
        columnas = numScan.nextInt();
        System.out.println("¿Cuántas filas quieres?");
        filas = numScan.nextInt();
        System.out.println("¿Qué caracter quieres usar?");
        caracter = charScan.nextLine().charAt(0);

        for (int i = 1 ; i <= filas ; i++){
            for(int j = 1 ; j <= columnas ; j++){
                System.out.print(caracter);
            }
            System.out.println("");
        }
    }
}
