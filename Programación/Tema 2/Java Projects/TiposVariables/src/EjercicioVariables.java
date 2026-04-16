import java.util.Scanner;

public class EjercicioVariables {
    public static void main(String[] args) throws Exception {

        // Definición de variables

        // Creamos una constante fija 
        final int future = 2057;

        /*
        Esta será la variable que recoja la edad actual en un int para insertar valores
        enteros sin decimales
        */ 
        int age;

        // Recogemos el año en el que nos encontramos
        int date;

        /*
        Y aquí creamos otra variable para guardar la operación calculada para obtener
        la edad que tendrá en nuestra constante fija
        */ 
        int ageFuture;

        // Inicialización del algoritmo
        System.out.println("CALCULARÉ CUÁNTOS AÑOS TENDRÁS EN EL 2057");

        // Arrancamos la librería Scanner para poder recoger datos de la terminal
        Scanner ageScanner = new Scanner(System.in);
        System.out.print("Dime qué edad tienes: ");
        age = ageScanner.nextInt();
        System.out.print("En qué año estamos: ");
        date = ageScanner.nextInt();

        ageFuture = future - date + age;

        // Y por último mostramos la salida con la información final
        System.out.println("Si ahora tienes " + age + " años,");
        System.out.println("en el año " + future + " tendrás " + ageFuture);
    }
}
