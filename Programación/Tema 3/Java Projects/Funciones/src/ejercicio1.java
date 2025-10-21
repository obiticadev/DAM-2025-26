import java.util.Scanner;

public class ejercicio1 {
    public static void main(String[] args) throws Exception {
        
        System.out.println("FUNCIÓN SUMAR");

        // Declaración de variables
        int num1;
        int num2;
        int resultado;
        boolean continuar = true;
        int peticion;

        Scanner numScan = new Scanner(System.in);

        do {
            System.out.print("\033[H\033[2J");
            System.out.println("""
                CALCULADORA
                -----------
                1) SUMA
                2) RESTA
                3) MULTPLICACIÓN
                4) DIVISIÓN

                5) SALIR
                """);
                peticion = numScan.nextInt();
                
                switch (peticion) {
                    case 1 -> {
                        System.out.print("\033[H\033[2J");
                        System.out.println("Has seleccionado la SUMA\n");

                        System.out.println("Introduce el primer parámetro");
                        num1 = numScan.nextInt();
                        System.out.println("Introduce el segundo parámetro");
                        num2 = numScan.nextInt();

                        resultado = suma(num1, num2);
                        System.out.println("La suma de " + num1 + " + " + num2 + " = " + resultado);
                        System.out.println("\nPulsa cualquier tecla para continuar...");
                        String clear2 = numScan.nextLine();
                        String clear = numScan.nextLine();
                    }
                    case 2 -> {
                        System.out.print("\033[H\033[2J");
                        System.out.println("Has seleccionado la RESTA\n");

                        System.out.println("Introduce el primer parámetro");
                        num1 = numScan.nextInt();
                        System.out.println("Introduce el segundo parámetro");
                        num2 = numScan.nextInt();

                        resultado = resta(num1, num2);
                        System.out.println("La resta de " + num1 + " - " + num2 + " = " + resultado);
                        System.out.println("\nPulsa cualquier tecla para continuar...");
                        String clear2 = numScan.nextLine();
                        String clear = numScan.nextLine();
                        
                    }
                    case 3 -> {
                        System.out.print("\033[H\033[2J");
                        System.out.println("Has seleccionado la MULTIPLICACIÓN\n");

                        System.out.println("Introduce el primer parámetro");
                        num1 = numScan.nextInt();
                        System.out.println("Introduce el segundo parámetro");
                        num2 = numScan.nextInt();

                        resultado = multiplicar(num1, num2);
                        System.out.println("La multiplicación de " + num1 + " x " + num2 + " = " + resultado);
                        System.out.println("\nPulsa cualquier tecla para continuar...");
                        String clear2 = numScan.nextLine();
                        String clear = numScan.nextLine();
                        
                    }
                    case 4 -> {
                        System.out.print("\033[H\033[2J");
                        System.out.println("Has seleccionado la DIVISIÓN\n");

                        System.out.println("Introduce el primer parámetro");
                        num1 = numScan.nextInt();
                        System.out.println("Introduce el segundo parámetro");
                        num2 = numScan.nextInt();

                        resultado = dividir(num1, num2);
                        System.out.println("La división de " + num1 + " / " + num2 + " = " + resultado);
                        System.out.println("\nPulsa cualquier tecla para continuar...");
                        String clear2 = numScan.nextLine();
                        String clear = numScan.nextLine();
                        
                    }
                    case 5 -> {
                        System.out.print("\033[H\033[2J");
                        System.out.println("Saliendo de la calculadora...");
                        continuar = false;
                        
                    }
                
                    default -> {
                        System.out.print("\033[H\033[2J");
                        System.out.println("Introduce una opción válida\n");

                    }
                }
        } while (continuar);
        

        
        
        
        


        
    }
    public static int suma(int a, int b){
        return a + b;
    }
    public static int resta(int a, int b){
        return a - b;
    }
    public static int multiplicar(int a, int b){
        return a * b;
    }
    public static int dividir(int a, int b){
        return a / b;
    }

}
