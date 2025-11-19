import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        // Declaración de variables
        final int PATAS_HORMIGAS = 6;
        final int PATAS_ARANAS = 8;
        final int PATAS_COCHINILLAS = 14;
        
        String pregunta;
        int numBichos = 0;
        int contadorPatas = 0;

        // Iniciamos Scanner ya que será necesario para recibir el número de insectos
        // capturados por el usuario
        Scanner sc = new Scanner(System.in);

        System.out.println("Contador de patas");

        // Primera pregunta usando función. Para más pregutnas solo hay que duplicar esta sección
        pregunta = "Número de hormigas capturadas";
        numBichos = FuncionPregunta(pregunta);
        contadorPatas = contadorPatas + numBichos * PATAS_HORMIGAS;

        pregunta = "Número de arañas capturadas";
        numBichos = FuncionPregunta(pregunta);
        contadorPatas = contadorPatas + numBichos * PATAS_ARANAS;

        pregunta = "Número de cochinillas capturadas";
        numBichos = FuncionPregunta(pregunta);
        contadorPatas = contadorPatas + numBichos * PATAS_COCHINILLAS;

        // Y sacamos por terminal el acumulador final

        System.out.println("El número de patas totales son de: " + contadorPatas);



        
    }

    public static int FuncionPregunta(String pregunta) {
        boolean continuar = false;
        int num = 0;
        Scanner sc = new Scanner(System.in);

        do {
            try {
                System.out.println(pregunta);
                num = sc.nextInt();
                continuar = true;

            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("Introduce un valor numérico");
                sc.nextLine();
            }

        } while (continuar == false);

        return num;

    }
}
