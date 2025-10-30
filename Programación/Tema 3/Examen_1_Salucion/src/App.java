import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        
        Scanner sc = new Scanner(System.in);

        final int NUM_PLAZAS = 25;
        boolean esCoche = false;
        int numAdultos = 0;
        int numMenores = 0;
        int totalAdultos = 0, totalMenores = 0; // Acumuladores
        int edad;
        int edadAcumulada = 0;
        int numCoches = 0;
        double mediaEdad;
        



        System.out.println("ESTADÍSTICAS DEL PARKING");
        for (int i = 1; i < NUM_PLAZAS; i++) {
            // Para cada plaza comprobaremos si es coche
            System.out.println("¿Es un coche?");
            esCoche = sc.nextBoolean();
            
            if (esCoche) {
                numCoches ++;
                // Se piden los datos para estadísticas
                System.out.println("¿Cuántos adultos hay en el coche?");
                numAdultos = sc.nextInt();
                totalAdultos += numAdultos;
                // totalAdultos = totalAdultos + numAdultos;
                
                System.out.println("¿Cuántos menores hay en el coche?");
                numMenores = sc.nextInt();
                totalMenores += numMenores;

                for (int j = 1; j <= numAdultos; j++) {
                    System.out.println("Dime la edad");
                    edad = devolverEdadValida();
                    edadAcumulada += edad;
                }
                
            } // No es necesario el else
            
        }
        

        mediaEdad = edadAcumulada/numAdultos;
        
        // Mostrar estadísticas

        System.out.println("Total de coches: " + numCoches);
        System.out.println("Porcentaje del total de las plazas ocupadas por coches: " + ((numCoches/NUM_PLAZAS)*100) + "%");
        System.out.println("Total de personas: " + (numAdultos + numMenores));
        System.out.println("Total de adultos: " + numAdultos);
        System.out.println("Total de menores: " + numMenores);
        System.out.println("Media edad: " + mediaEdad);
        if (((numCoches/NUM_PLAZAS)*100) >= 50 || (totalAdultos + totalMenores) > 25) {
            System.out.println("PARKING CON MUCHA AFLUENCIA");
            
        }

        
        
    }

    public static int devolverEdadValida(){

        int edad;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Introduce tu edad");
            System.out.println("El valor tiene que ser entre 0 y 100");
            edad = sc.nextInt();
        } while (edad <= 0 && edad >= 100);


        return edad;
    }
}
