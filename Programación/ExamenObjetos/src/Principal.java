import java.util.Scanner;

import Clases.Publicacion;

public class Principal {
    private static Scanner sc = new Scanner(System.in);
    private static Publicacion publicacion1 = new Publicacion(1, "Día soleado");
    private static Publicacion publicacion2 = new Publicacion(2);
    public static void main(String[] args) throws Exception {
        boolean continuar =  true;
        String respuestaMenu;


        System.out.println("Crear instancia Publicacion que tenga contenido HECHO");
        
        System.out.println("Crear instancia Publicacion que no tenga contenido HECHO");

        
        do {
            System.out.print("""
                    \n=== MENÚ ===
                    1. Agregar contenido a la primera instancia
                    2. Agregar contenido a la segunda instancia
                    3. Eliminar el último contenido de la primera instancia
                    4. Eliminar el último contenido de la segunda instancia
                    5. Mostrar el contenido usando mostrarFeeds()
                    6. Comprobar equals

                    S. Salir

                    Selecciona una opción:\t""");
                    respuestaMenu = sc.nextLine().toUpperCase();
                    switch (respuestaMenu) {
                        case "1" -> {
                            agregarContenido(publicacion1);
                        }
                        case "2" -> {
                            agregarContenido(publicacion2);
                        }
                        case "3" -> {
                            eliminarUltimoContenido(publicacion1);
                        }
                        case "4" -> {
                            eliminarUltimoContenido(publicacion2);
                        }
                        case "5" -> {
                            mostrarContenido();
                        }
                        case "6" -> {
                            System.out.println("\n" + comprobarEquals());
                        }
                        case "S" -> {
                            continuar = false;
                            System.out.println("\nSaliendo del programa...\n");
                        }
                    
                        default -> {
                            System.out.println("\nSelecciona una opción válida\n");
                        }
                    }
        } while (continuar);
    }

    public static void agregarContenido(Publicacion post){
        System.out.print("\nIntroduce el contenido que quieres guardar: ");
        String respuestaContenido = sc.nextLine();
        if (post.agregarContenido(respuestaContenido)) {
            System.out.println("\nAgregado con éxito\n");
        } else {
            System.out.println("\nLleno o sin valor en la entrada, introduce una entrada correcta o borra un elemento\n");
        }
        
    }
    public static void eliminarUltimoContenido(Publicacion post){
        if (post.eliminarUltimoContenido()) {
            System.out.println("\nElimnado con éxito\n");
        } else {
            System.out.println("\nNo hay nada que borrar, agrega un contenido\n");
        }
    }
    public static void mostrarContenido(){
        System.out.println("\n--------------------\n");
        System.out.println(publicacion1.mostrarFeeds());
        System.out.println("\n--------------------\n");
        System.out.println(publicacion2.mostrarFeeds());
        System.out.println("\n--------------------\n");
    }
    public static boolean comprobarEquals(){
        return publicacion1.equals(publicacion2);
    }
}
