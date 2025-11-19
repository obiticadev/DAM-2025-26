
import java.nio.channels.Pipe.SourceChannel;
import java.util.Scanner;

/*
 * OBLIGATORIO, solo se puede usar Scanner.nextline
 * deberás convertir los datos al tipo de dato que necesites
 * 
 * Cajero automático simulado
 * Empieza con saldo 0
 * Muestra un menú:
 * 1. Depositar
 * 2. Retirar
 * 3. Consultar
 * 4. Salir
 * Evita saldos negativos, no se podrá retirar si el saldo es negativo
 */

public class ejercicio7 {
    public static void main(String[] args) throws Exception {


        // Declaración de variables
        Boolean continua = true;
        String lectura;
        int num;
        String deposito;
        String retirar;
        String consultar;
        String salir;
        Scanner lineScan = new Scanner(System.in);
        int saldoCajero = 0;

        do{
            System.out.println("""
                    MOSTRAR MENÚ
                    ------------
                    1. Depositar
                    2. Retirar
                    3. Consultar
                    4. Salir

                    Evita saldos negativos, no se podrá retirar si el saldo es negativo
                    """);
            lectura = lineScan.nextLine();
            num = Integer.parseInt(lectura);
            switch (num) {
                case 1 -> {
                    System.out.println("Introduce la cantidad a depositar: ");
                    deposito = lineScan.nextLine();
                    System.out.println("Has ingresado " + Integer.parseInt(deposito));
                    saldoCajero = saldoCajero + (Integer.parseInt(deposito));
                }
                case 2 -> {
                    System.out.println("Introduce la cantidad a retirar");
                    retirar = lineScan.nextLine();
                    System.out.println("Has retirado " + Integer.parseInt(retirar));
                    saldoCajero = saldoCajero - (Integer.parseInt(retirar));
                    
                }
                case 3 -> {
                    System.out.println("SALDO ACTUAL: " + saldoCajero);
                }
                case 4 -> {
                    System.out.println("SALIENDO DEL MENÚ...");
                    continua = false;
                }
                    
                    
            
                default -> {
                    System.out.println("OPCIÓN NO VÁLIDA");
                }
                    
            }
            
        }while (continua);
        
    }
}
