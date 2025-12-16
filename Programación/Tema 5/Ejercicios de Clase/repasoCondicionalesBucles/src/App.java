import java.util.Arrays;
import java.util.Scanner;

public class App {
    /*
     * CREA UN MENÚ - LAS OPCIONES DEL MENÚ SE DEBEN CAPTURAR COMO STRING
     * SOLAMENTE SALDRÁ DEL MENÚ EN LA OPCIÓN 5
     * SEGUIRÁ EN EL MENÚ - MIENTRAS NO SE PULSE 5
     * SI SE PULSA CUALQUIER OTRA TECLA
     * 
     * MENÚ DE OPCIONES
     * 
     * 1. INTRODUCIR 10 NÚMEROS ENTEROS
     * 2. INTRODUCIR 10 CARACTERES
     * 3. INTRODUCIR 10 NOMBRES
     * 4. INTRODUCIR 10 NÚMEROS DECIMALES
     * 5. SALIR
     */
private static Scanner scNum = new Scanner(System.in);
private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        

        boolean continuar = true;
        String respuestaMenu;
        int respuestaInterna;

        do {
            System.out.println("""
                    MENÚ DE OPCIONES

                    1. INTRODUCIR 10 NÚMEROS ENTEROS
                    2. INTRODUCIR 10 CARACTERES
                    3. INTRODUCIR 10 NOMBRES
                    4. INTRODUCIR 10 NÚMEROS DECIMALES
                    5. SALIR
                                    """);
            respuestaMenu = sc.nextLine();
            switch (respuestaMenu) {
                case "1" -> {
                    System.out.print("Introduce la cantidad de números que quieres guardar: ");
                    respuestaInterna = scNum.nextInt();
                    int[] arrayEnteros = devolverEnteros(respuestaInterna);
                    System.out.println(Arrays.toString(arrayEnteros));
                }
                case "2" -> {
                    System.out.print("Introduce la cantidad de caracteres que quieres guardar: ");
                    respuestaInterna = scNum.nextInt();
                    char[] arrayCaracteres = devolverCaracteres(respuestaInterna);
                    System.out.println(Arrays.toString(arrayCaracteres));
                }
                case "3" -> {
                    System.out.print("Introduce la cantidad de nombres que quieres guardar: ");
                    respuestaInterna = scNum.nextInt();
                    String[] arrayNombres = devolverNombres(respuestaInterna);
                    System.out.println(Arrays.toString(arrayNombres));
                }
                case "4" -> {
                    System.out.print("Introduce la cantidad de decimales que quieres guardar: ");
                    respuestaInterna = scNum.nextInt();
                    double[] arrayDecimales = devolverDecimales(respuestaInterna);
                    System.out.println(Arrays.toString(arrayDecimales));
                }
                case "5" -> {
                    System.out.println("Saliendo del programa...");
                    continuar = false;
                }

                default -> {
                    System.out.println("Selecciona una opción válida dentro del MENÚ...");
                }
            }
        } while (continuar);
    }

    private static int[] devolverEnteros(int num){
        int[] array = new int[num];

        for (int i = 0; i < array.length; i++) {
            System.out.print("Introduce el número " + (i+1) + ": ");
            array[i] = scNum.nextInt();
        }
        
        return array;
    }

    private static char[] devolverCaracteres(int num){
        char[] array = new char[num];

        for (int i = 0; i < array.length; i++) {
            System.out.print("Introduce el caracter " + (i+1) + ": ");
            array[i] = sc.nextLine().charAt(0);
        }

        return array;
    }

    private static String[] devolverNombres(int num){
        String[] array = new String[num];

        for (int i = 0; i < array.length; i++) {
            System.out.print("Introduce el nombre " + (i+1) + ": ");
            array[i] = sc.nextLine();
        }

        return array;
    }

    private static double[] devolverDecimales(int num){
        double[] array = new double[num];

        for (int i = 0; i < array.length; i++) {
            System.out.print("Introduce el decimal " + (i+1) + ": ");
            array[i] = scNum.nextDouble();
        }

        return array;
    }
}
