
import java.util.Scanner;

/*
Enunciado:

Crea un programa que pida al usuario su nombre y su año de nacimiento. El programa debe generar un código de usuario único con las siguientes reglas:
Las dos primeras letras del nombre en mayúsculas.
Los dos últimos dígitos del año de nacimiento.
Un carácter especial que corresponde al valor ASCII de la suma de los cuatro dígitos del año de nacimiento.

Ejemplo de Salida:

Ingresa tu primer nombre:
Andres
Ingresa tu año de nacimiento:
1994
Tu código de usuario es: AN94#
*/

public class ejercicio9 {
    public static void main(String[] args) throws Exception {

        String name;
        int date;
        boolean esMinuscula;
        char[] codUser = new char[5];
        String codUserFinal;
        
        Scanner scan = new Scanner(System.in);
        Scanner numScan = new Scanner(System.in);
        
        System.out.println("Ingresa tu primer nombre: ");
        name = scan.nextLine();
        System.out.println("Ingresa tu año de nacimiento:");
        date = numScan.nextInt();

        for (int i = 0 ; i <= 4 ; i ++){
            if (i >= 0 && i <= 1){
                codUser[i] = name.charAt(i);
                if (Character.isLowerCase(codUser[i])){
                    codUser[i] = Character.toUpperCase(codUser[i]);
                }
            }
            if (i > 1 && i <= 3){
                codUser[i] = (String.valueOf(date)).charAt(i);
            }
            if (i == 4){
                codUser[i] = '#';
            }
            
        }
        codUserFinal = new String(codUser);


        // Salida
        System.out.println("Tu código de usuario es: " + codUserFinal);
        
    }
}
