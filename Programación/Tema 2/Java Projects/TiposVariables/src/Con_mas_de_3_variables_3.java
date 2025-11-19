import java.util.Scanner;

public class Con_mas_de_3_variables_3 {


/*
 * Pide al usuario el precio de 3 productos. Usa una constante para el porcentaje de descuento (por ejemplo, 10%)
 * y muestra el precio total sin descuento y con descuento.
 */
    public static void main(String[] args) {
        // Declaración de variables
        float product1;
        float product2;
        float product3;
        final float DESCUENTO = 0.1f;
        float conDesc1;
        float conDesc2;
        float conDesc3;


        // Arrancamos la librería de Scanner
        Scanner numScanner = new Scanner(System.in);

        System.out.println("TE CALCULO EL DESCUENTO DE TRES PRODUCTOS CON EL 10%");
        
        System.out.println("¿Precio de tu primer producto?");
        product1 = numScanner.nextFloat();
        System.out.println("¿Precio de tu segundo producto?");
        product2 = numScanner.nextFloat();
        System.out.println("¿Precio de tu tercer?");
        product3 = numScanner.nextFloat();

        conDesc1 = product1 * (1 - DESCUENTO);
        conDesc2 = product2 * (1 - DESCUENTO);
        conDesc3 = product3 * (1 - DESCUENTO);

        System.out.println("Precios:");
        System.out.println(product1 + "€ pasan a ser " + conDesc1 + "€");
        System.out.println(product2 + "€ pasan a ser " + conDesc2 + "€");
        System.out.println(product3 + "€ pasan a ser " + conDesc3 + "€");

    }
}
