import java.util.Scanner;

/**
#Ejercicio

Crea una aplicación que tendrá un menú con las siguientes opciones:
Calcular nota media
Ver el último resultado
Salir
OPCIÓN1.
Se piden por consola los siguientes datos:
¿Cuántos módulos tienes?
Se pedirán las notas de cada uno de los módulos …
¿Qué nota has sacado en el módulo 1?
¿Qué nota has sacado en el módulo 2? ….
Las notas pueden ser mínimo un 1 y máximo un 10, si se introduce cualquier
otra nota se debe mostrar un mensaje “esta nota no es válida” y volver a
pedir al usuario que introduzca la nota.
Al finalizar se mostrará:
La nota más alta introducida
La nota más baja introducida
La nota media en el siguiente formato:
De 0 a 4 No superado
Mayor o igual a 5 y menor que 6 : Aprobado
Mayor que 6 y menor que 8: Notable
Mayor que 8 hasta 10: Sobresaliente
OPCIÓN2.
Mostrará la última nota media calculada.
OPCIÓN3:
Finaliza el programa y se muestra FIN DE PROGRAMA

*/

public class App {
    public static void main(String[] args) throws Exception {

        // Declaración de variables
        int select;
        int modulos;
        double nota;
        boolean selectValido = false;
        
        Scanner numScan = new Scanner(System.in);

        while (selectValido == false) {

        System.out.println("MENU");
        System.out.println("1. Calcular nota media");
        System.out.println("2. Ver el último resultado");
        System.out.println("3. Salir");

        select = numScan.nextInt();

        


            if (select == 1) {
                System.out.println("Has seleccionado la opción 1");
                System.out.println("Introduce el número de módulos:");
                modulos = numScan.nextInt();
                for (int i = 1 ; i <= modulos ; i++){
                    System.out.println("Introduce la nota del módulo " + i + ":");
                    nota = numScan.nextDouble();
                    if (nota < 1 || nota > 10){
                        System.out.println("Esta nota no es válida");
                        i--;
                    }else if (nota >= 1 && nota < 5){
                        System.out.println("No superado");
                    }else if (nota >= 5 && nota < 6){
                        System.out.println("Aprobado");
                    }else if (nota >= 6 && nota < 8){
                        System.out.println("Notable");
                    }else if (nota >= 8 && nota <= 10){
                        System.out.println("Sobresaliente");
                    }

                }

                selectValido = true;
            }else if (select == 2) {

                
                selectValido = true;
            }else if (select == 3) {
                
                
                System.out.println("FIN DEL PROGRAMA");
                selectValido = false;
            }else{
                
                
                System.out.println("\nOpción no válida\n");
                selectValido = false;
            }
        
        }
    }
}
