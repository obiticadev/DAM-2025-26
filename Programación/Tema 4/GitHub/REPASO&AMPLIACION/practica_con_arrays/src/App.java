import Utilidades.Funciones;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        String[] arrayPreguntas = Funciones.devolverPreguntas();
        int[] arrayRespuestas = new int[arrayPreguntas.length];

        int sumaNotas = 0;
        int contadorNotas = 0;

        System.out.println("ENCUESTA DE EVALUACIÓN DOCENTE\n*****************************");

        for (int i = 0; i < arrayPreguntas.length; i++) {
            try {
                System.out.println("PREGUNTA Nº " + (i + 1));
                System.out.println(arrayPreguntas[i]);
                System.out.println("Introduce una nota del 1 al 5");
                arrayRespuestas[i] = sc.nextInt();
                System.out.println();
                if (arrayRespuestas[i] < 0 || arrayRespuestas[i] > 5) {
                    System.out.println("Hemos guardado 0\n");
                    arrayRespuestas[i] = 0;

                }

            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("Hemos guardado 0\n");
                arrayRespuestas[i] = 0;
                sc.nextLine();
            }
        }

        for (int i = 0; i < arrayRespuestas.length; i++) {
            if (arrayRespuestas[i] >= 1 && arrayRespuestas[i] <= 5) {
                sumaNotas += arrayRespuestas[i];
                contadorNotas++;
            }
        }
        double promedio = (double) (sumaNotas) / (double) (contadorNotas);
        mostrarResultados(arrayPreguntas, arrayRespuestas, promedio);

        int[][] arrayBidimensional = Funciones.getEncuestas();
        double[] arrayMedia = new double[arrayBidimensional[0].length];
        int contador;
        for (int i = 0; i < arrayBidimensional[0].length; i++) {
            contador = 0;
            for (int j = 0; j < arrayBidimensional.length; j++) {
                arrayMedia[i] += arrayBidimensional[j][i];
                contador++;
            }
            arrayMedia[i] = arrayMedia[i] / contador;
        }

        for (int i = 0; i < arrayBidimensional[0].length; i++) {
            System.out.println("La media de la pregunta " + (i + 1) + " es: " + arrayMedia[i]);
        }
    }

    private static void mostrarResultados(String[] arrayPreguntas, int[] arrayRespuestas, double promedio) {
        System.out.println("A CONTINUACIÓN SE MUESTRAN LOS RESULTADOS DE LA ENCUESTA");
        for (int i = 0; i < arrayRespuestas.length; i++) {
            System.out.println(arrayPreguntas[i] + ": " + arrayRespuestas[i]);
        }
        System.out.println("La nota media de la encuesta es: " + promedio);
    }

}
