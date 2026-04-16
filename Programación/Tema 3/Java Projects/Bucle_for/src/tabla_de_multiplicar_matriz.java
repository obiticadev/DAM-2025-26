import java.util.Scanner;

public class tabla_de_multiplicar_matriz {
    public static void main(String[] args) throws Exception {
        System.out.println("Tabla de multiplicar");

        // Variables
        
        System.out.println("Matriz de la tabla de multiplicar");

        for (int i = 1 ; i <= 10 ; i++){
            for (int j = 1 ; j <= 10 ; j++){
                System.out.print(i * j + "\t");
            }
            System.out.println("");
        }
        }
    }
