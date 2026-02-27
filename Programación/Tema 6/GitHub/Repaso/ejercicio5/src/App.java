import java.util.Scanner;

public class App {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        boolean continuar = false;

        System.out.println("=== BIBLIOTECA ===");
        do {
            System.out.println("""
                    MENÚ
                    (1) => Registrar libro
                    (2) => Buscar libro
                    (3) => Modificar libro
                    (4) => Listar libros
                    (5) => Registrar reserva
                    (6) => Buscar reserva
                    (7) => Modificar reserva
                    (8) => Listar reservas
                        """);

        } while (!continuar);
    }
}
