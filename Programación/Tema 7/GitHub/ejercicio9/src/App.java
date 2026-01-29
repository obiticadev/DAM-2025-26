import java.util.ArrayList;
import java.util.Scanner;

import Clases.GestorHerramientas;
import Clases.Hacha;
import Clases.Herramienta;
import Clases.PicoDiamante;
import Clases.PicoHierro;

public class App {
    private static GestorHerramientas gestorHerramientas;
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        ArrayList<Herramienta> inventario = new ArrayList<>();
        gestorHerramientas = new GestorHerramientas(inventario);

        Herramienta picoDiamante = new PicoDiamante("Pico de diamante", 5, "máxima");
        Herramienta picoHierro = new PicoHierro("Pico de Hierro", 2, "media");
        Herramienta hacha = new Hacha("Hacha", 3, 2.5f);

        gestorHerramientas.addHerramienta(picoDiamante);
        gestorHerramientas.addHerramienta(picoHierro);
        gestorHerramientas.addHerramienta(hacha);

        boolean continuar = true;
        String respuesta;

        do {
            menu();
            respuesta = sc.nextLine().toUpperCase();
            switch (respuesta) {
                case "1" -> {
                    System.out.println(Herramienta.getContador());
                }
                case "2" -> {
                    for (int i = 0; i < gestorHerramientas.getHerramienta().size(); i++) {
                        System.out.println((i+1) + ". " + gestorHerramientas.getHerramienta().get(i).getNombre());
                    }
                }
                case "3" -> {
                    gestorHerramientas.removeLastHerramienta();
                    System.out.println("Listo");
                }
                case "S" -> {
                    continuar = false;
                    System.out.println("Saliendo del programa...");
                }
            
                default -> {
                    System.out.println("Selecciona una opción válida");
                }
            }
        } while (continuar);
        
    }

    public static void menu(){
        System.out.print("""
                MENU
                ----

                1. MOSTRAR NÚMERO DE HERRAMIENTAS
                2. MOSTRAR LA LISTA DE HERRAMINETAS
                3. ELIMINAR ÚLTIMA HERRAMINETA

                S. SALIR
                
                Selecciona una opción:\t""");
    }
}
