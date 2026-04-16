import java.util.Scanner;
import nivel1_basico_ordenacion.*;
import nivel2_lambdas_y_flujos_0.*;
import nivel3_intermedio_multicriterio.*;
import nivel4_avanzado_estructuras_inyectadas.*;
import nivel5_profesional_colecciones.*;
import nivel6_experto.RetoFinal_ElSindicatoDeAventureros;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=======================================================");
            System.out.println("  SISTEMA ESTELAR: ORDENACIÓN, LAMBDAS Y STREAMS 100");
            System.out.println("=======================================================");
            System.out.println("  1. NIVEL 1: 01 - Orden Natural String e Integer");
            System.out.println("  2. NIVEL 1: 02 - La Interfaz Comparable");
            System.out.println("  3. NIVEL 1: 03 - Clases Anónimas Antiguas");
            System.out.println("-------------------------------------------------------");
            System.out.println("  4. NIVEL 2: 04 - El salto a la pureza (Sort Lambda)");
            System.out.println("  5. NIVEL 2: 05 - Primer Contacto Stream (.filter)");
            System.out.println("  6. NIVEL 2: 06 - Predicados y Lógica Booleana");
            System.out.println("-------------------------------------------------------");
            System.out.println("  7. NIVEL 3: 07 - Ordenación Multicriterio (ThenComparing)");
            System.out.println("  8. NIVEL 3: 08 - Invirtiendo realidades (.reversed)");
            System.out.println("  9. NIVEL 3: 09 - Mutando el agua (.map)");
            System.out.println("-------------------------------------------------------");
            System.out.println(" 10. NIVEL 4: 10 - Inyectando Lambdas al TreeSet");
            System.out.println(" 11. NIVEL 4: 11 - Inyectando Lambdas al TreeMap");
            System.out.println(" 12. NIVEL 4: 12 - Drenando Set y Queue con Streams");
            System.out.println("-------------------------------------------------------");
            System.out.println(" 13. NIVEL 5: 13 - Agrupado automático masivo (.groupingBy)");
            System.out.println(" 14. NIVEL 5: 14 - Elegancia Suprema :: (Method References)");
            System.out.println("=======================================================");
            System.out.println(" 15. \033[0;31m[NIVEL EXPERTO]: RETO DEL SINDICATO\033[0m");
            System.out.println("  0. Salir");
            System.out.println("=======================================================");
            System.out.print("Selecciona un nivel de entrenamiento: ");

            int opcion = -1;
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Solo admite números.");
                continue;
            }

            if (opcion == 0) {
                System.out.println("Te convertirás en el arquitecto de datos... ¡Adiós!");
                break;
            }

            ejecutarEjercicio(opcion);

            System.out.println("\nPulsa ENTER para volver al índice maestro...");
            scanner.nextLine();
        }
        scanner.close();
    }

    private static void ejecutarEjercicio(int opcion) {
        System.out.println("\n**************************************************************\n");
        switch (opcion) {
            case 1:
                Ejercicio01_OrdenNaturalStringInteger.demostracion();
                Ejercicio01_OrdenNaturalStringInteger.ejercicio();
                break;
            case 2:
                Ejercicio02_ImplementarComparable.demostracion();
                Ejercicio02_ImplementarComparable.ejercicio();
                break;
            case 3:
                Ejercicio03_ClasesAnonimasComparator.demostracion();
                Ejercicio03_ClasesAnonimasComparator.ejercicio();
                break;
            case 4:
                Ejercicio04_LambdaSortSintaxis.demostracion();
                Ejercicio04_LambdaSortSintaxis.ejercicio();
                break;
            case 5:
                Ejercicio05_IntroduccionStreamsYFiltroUnico.demostracion();
                Ejercicio05_IntroduccionStreamsYFiltroUnico.ejercicio();
                break;
            case 6:
                Ejercicio06_FiltrosCondicionalesLogicos.demostracion();
                Ejercicio06_FiltrosCondicionalesLogicos.ejercicio();
                break;
            case 7:
                Ejercicio07_OrdenacionMulticriterioThenComparing.demostracion();
                Ejercicio07_OrdenacionMulticriterioThenComparing.ejercicio();
                break;
            case 8:
                Ejercicio08_InversionDeOrdenReversed.demostracion();
                Ejercicio08_InversionDeOrdenReversed.ejercicio();
                break;
            case 9:
                Ejercicio09_EncadenamientoFiltrosYMapeos.demostracion();
                Ejercicio09_EncadenamientoFiltrosYMapeos.ejercicio();
                break;
            case 10:
                Ejercicio10_InyeccionLambdaTreeSet.demostracion();
                Ejercicio10_InyeccionLambdaTreeSet.ejercicio();
                break;
            case 11:
                Ejercicio11_InyeccionLambdaTreeMap.demostracion();
                Ejercicio11_InyeccionLambdaTreeMap.ejercicio();
                break;
            case 12:
                Ejercicio12_StreamsEnHashSetYQueue.demostracion();
                Ejercicio12_StreamsEnHashSetYQueue.ejercicio();
                break;
            case 13:
                Ejercicio13_CollectAgrupacionYParticion.demostracion();
                Ejercicio13_CollectAgrupacionYParticion.ejercicio();
                break;
            case 14:
                Ejercicio14_MetodosDeReferencia.demostracion();
                Ejercicio14_MetodosDeReferencia.ejercicio();
                break;
            case 15:
                RetoFinal_ElSindicatoDeAventureros.demostracion();
                RetoFinal_ElSindicatoDeAventureros.ejercicio();
                break;
            default:
                System.out.println("Opción no válida en la terminal.");
        }
        System.out.println("\n**************************************************************");
    }
}
