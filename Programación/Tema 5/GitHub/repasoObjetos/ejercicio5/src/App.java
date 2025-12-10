import java.util.Arrays;
import java.util.Scanner;

import Clases.CajaTienda;

public class App {
    public static void main(String[] args) throws Exception {
        CajaTienda caja = new CajaTienda();
        Scanner scNum = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);


        boolean continuar = true;
        int respuesta;
        String producto;
        double precio;
        
        do {
            try {
                System.out.println("""
                        CAJERO AUTOMÁTICO
                        -----------------
    
                        1) REGISTRA UN PRODUCTO
                        2) MOSTRAR PRODUCTOS
                        3) REINICIAR CAJA
    
                        4) SALIR
                        """);
                respuesta = scNum.nextInt();
                switch (respuesta) {
                    case 1 -> {
                        System.out.print("Introduce el nombre del producto: ");
                        producto = sc.nextLine();
                        System.out.print("Introcue el precio del mismo: ");
                        precio = scNum.nextDouble();
                        caja.registrarProducto(producto, precio);
                        System.out.println("LISTO");
                    }
                    case 2 -> {
                        System.out.println(caja.mostrarProductos());
                    }
                    case 3 -> {
                        caja.reiniciarCaja();
                    }
                    case 4 -> {
                        System.out.println("Saliendo del cajero...");
                        continuar = false;
                    }
                
                    default -> {
                        System.out.println("Selecciona una opción válida dentro del menú");
                    }
                }
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("Introduce un valor correcto");
            }
            
        } while (continuar);
        
    }
}
