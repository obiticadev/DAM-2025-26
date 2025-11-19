import java.util.Scanner;

public class Con_mas_de_3_variables_5 {


/*
 * Cálculo de comisiones Un vendedor recibe un sueldo base mas un 10% extra por comisión de sus ventas,
 * el vendedor desea saber cuanto dinero obtendrá por concepto de comisiones por las tres ventas que realiza en el mes
 * y el total que recibirá en el mes tomando en cuenta su sueldo base y comisiones.
 */

    public static void main(String[] args) {
        // Declaración de variables
        float sueldoBase;
        float venta1;
        float venta2;
        float venta3;
        float sumaVentas;
        final float COMISIONES = 0.1f;
        float sueldoExtra;
        float sueldoTotal; 
        


        // Arrancamos la librería de Scanner
        Scanner numScanner = new Scanner(System.in);

        System.out.println("DAME UN SUELDO BASE Y TE DEVOLVERÉ JUNTO CON TUS COMISIONES EL SALARIO COMPLETO (COMISIÓN 10%)");
        
        System.out.println("¿Cuál es tu salario base?");
        sueldoBase = numScanner.nextFloat();
        System.out.println("¿Cuánto fue la venta por el primer producto?");
        venta1 = numScanner.nextFloat();
        System.out.println("¿Cuánto fue la venta por el segundo producto?");
        venta2 = numScanner.nextFloat();
        System.out.println("¿Cuánto fue la venta por el tercer producto?");
        venta3 = numScanner.nextFloat();

        sumaVentas = venta1 + venta2 + venta3;
        sueldoExtra = sumaVentas * COMISIONES;
        sueldoTotal = sueldoBase + sueldoExtra;

        System.out.println("El salario final con " + sueldoBase + "€ de sueldo base y con " + sumaVentas + "€ en ventas.\nTu comisión del " + COMISIONES * 100 + "% te deja un extra de " + sueldoExtra + "€,\ncon un total de " + sueldoTotal + "€");
    }
}
