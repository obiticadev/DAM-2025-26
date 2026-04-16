import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        
        
        System.out.print("Introduce una palabra: ");
        StringBuilder respuesta = new StringBuilder(sc.nextLine());
        
        

        for (int i = 0; i < respuesta.length() - 1; i++) {
            if (esNumero(respuesta.charAt(i))) {
                if (!esNumero(respuesta.charAt(i+1))) {
                    respuesta.insert((i+1), "-");
                    i++;
                }
            } else {
                if (esNumero(respuesta.charAt(i+1))) {
                    respuesta.insert((i+1), "-");
                    i++;
                }
            }
        }

        System.out.println(respuesta.toString());
        
    }

    public static boolean esNumero(char caracter){
        if (Character.isDigit(caracter)) {
            return true;
        } else {
            return false;
        }
    }
    
}
