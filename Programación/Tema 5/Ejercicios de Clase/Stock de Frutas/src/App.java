import java.util.Scanner;

import Clases.Articulo;

public class App {
    public static void main(String[] args) throws Exception {
        Articulo[] productos = Articulo.devolverArticulos();
        Scanner sc = new Scanner(System.in);

        int stockTotal = 0;
        boolean continuar = true;
        int select;

        do {
            
            System.out.println("""
                MENU
                --------------------------------
                
                1) SABER EL STOCK TOTAL DEL ALMACEN
                
                2) SABER EL STOCK DE UN ARTÍCULO
                
                
            """);
            select = sc.nextInt();
            
            for (int i = 0; i < productos.length; i++) {
                stockTotal += productos[i].getStockInicial();
            }
            
        } while (continuar);

        
    }
}

// saber el stock total del almacén
// saber el stock de un artículo (mostrando todos los nombres de los arículos que tenemos en el almacen)