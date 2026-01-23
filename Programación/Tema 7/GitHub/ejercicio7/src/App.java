import java.util.Scanner;

public class App {
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        boolean continuar = true;
        
        do {
            System.out.println("""
                    === TARJETAS ===
    
                    1) CRÉDITO
                    2) DÉBITO
                    3) CYBERTARJETA
    
                    S) SALIR
                    """);
                    switch (sc.nextLine()) {
                        case "1" -> {

                        }

                        case "2" -> {

                        }

                        case "3" -> {

                        }
                    
                        default -> {

                        }
                    }
            
        } while (continuar);
    }

    public static void crearInstancia(){
        System.out.println("Introduce cuántas tarjetas quieres crear");
    }
    
}
