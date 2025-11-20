import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        // Declaración de variables
        int num;
        int cociente;
        int resto;
        String binario = "";
        String hexadecimal = "";
        String basuraHexadecimal = "";
        String octal = "";
        String basuraOctal = "";

        System.out.print("Introduce un número decimal: ");
        num = sc.nextInt();


        // A binario
        do {
            cociente = num/2;
            resto = num%2;

            binario = resto + binario;
            
            num = cociente;
        } while (num != 1 && num != 0);

        binario = num + binario;

        // A Hexadecimal

        int i = binario.length()-4;
        do {
            if (i < 0) {
                int distanciaCero = Math.abs(i);
                int distanciaHastaNum;
                distanciaHastaNum = i+4;
                i = distanciaCero + i;
                basuraHexadecimal = binario.substring(i, distanciaHastaNum);
                hexadecimal = basuraHexadecimal + hexadecimal;
                basuraHexadecimal = "";
                i -= 4;
    
            }
            if (i > 0) {
                basuraHexadecimal = binario.substring(i, i+4);
                hexadecimal = basuraHexadecimal + hexadecimal;
                basuraHexadecimal = "";
                i -= 4;
            }
            
        } while (i != 0);


        
        // Salida
        System.out.println("Binario: " + binario);
        System.out.println("Hexadecimal: " + hexadecimal);

        
    }
}
