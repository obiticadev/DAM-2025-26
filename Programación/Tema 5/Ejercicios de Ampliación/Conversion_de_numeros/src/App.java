
import Clases.Conver;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Scanner scNum = new Scanner(System.in);
        Conver conversor = new Conver();

        String respuestaMenu;
        String subRespuestaMenu;
        int entrada;
        long entradaGrande;
        String entradaString;

        boolean continuar = true;
        boolean subContinuar;
        do {
            System.out.println("""
                    CONVERSOR DE DATOS
                    ------------------

                    1) A DECIMAL
                    2) A BINARIO
                    3) A OCTAL
                    4) A HEXADECIMAL

                    S) SALIR
                    """);
            respuestaMenu = sc.nextLine().toUpperCase();
            switch (respuestaMenu) {
                case "1" -> {
                    subContinuar = true;
                    do {
                        System.out.println("""
                                HAS SELECCIONADO LA OPCIÓN A DECIMAL
                                ------------------------------------

                                ¿Qué formato quieres entregar?
                                1) Binario
                                2) Octal
                                3) Hexadecimal
                                """);
                        subRespuestaMenu = sc.nextLine();
                        switch (subRespuestaMenu) {
                            case "1" -> {
                                System.out.println("Introduce el valor a convertir:");
                                entradaString = sc.nextLine();
                                System.out.println(conversor.binarioADecimal(entradaString));
                                subContinuar = false;
                            }
                            case "2" -> {
                                System.out.println("Introduce el valor a convertir:");
                                entradaGrande = scNum.nextLong();
                                System.out.println(conversor.octalADecimal(entradaGrande));
                                subContinuar = false;
                            }
                            case "3" -> {
                                System.out.println("Introduce el valor a convertir:");
                                entradaString = sc.nextLine();
                                System.out.println(conversor.hexaADecimal(entradaString));
                                subContinuar = false;
                            }

                            default -> {
                                System.out.println("ERROR\nSelecciona una opción válida del menú de formato...");
                            }
                        }

                    } while (subContinuar);
                }
                case "2" -> {
                    subContinuar = true;
                    do {
                        System.out.println("""
                                HAS SELECCIONADO LA OPCIÓN A BINARIO
                                ------------------------------------

                                ¿Qué formato quieres entregar?
                                1) Decimal
                                2) Octal
                                3) Hexadecimal
                                """);
                        subRespuestaMenu = sc.nextLine();
                        switch (subRespuestaMenu) {
                            case "1" -> {
                                System.out.println("Introduce el valor a convertir:");
                                entrada = scNum.nextInt();
                                System.out.println(conversor.decimalABinario(entrada));
                                subContinuar = false;
                            }
                            case "2" -> {
                                System.out.println("Introduce el valor a convertir:");
                                entradaGrande = scNum.nextInt();
                                System.out.println(conversor.octalABinario(entradaGrande));
                                subContinuar = false;
                            }
                            case "3" -> {
                                System.out.println("Introduce el valor a convertir:");
                                entradaString = sc.nextLine();
                                System.out.println(conversor.hexaABinario(entradaString));
                                subContinuar = false;
                            }

                            default -> {
                                System.out.println("ERROR\nSelecciona una opción válida del menú de formato...");
                            }
                        }

                    } while (subContinuar);
                }
                case "3" -> {
                    subContinuar = true;
                    do {
                        System.out.println("""
                                HAS SELECCIONADO LA OPCIÓN A OCTAL
                                ----------------------------------

                                ¿Qué formato quieres entregar?
                                1) Decimal
                                2) Binario
                                3) Hexadecimal
                                """);
                        subRespuestaMenu = sc.nextLine();
                        switch (subRespuestaMenu) {
                            case "1" -> {
                                System.out.println("Introduce el valor a convertir:");
                                entrada = scNum.nextInt();
                                System.out.println(conversor.decimalAOctal(entrada));
                                subContinuar = false;
                            }
                            case "2" -> {
                                System.out.println("Introduce el valor a convertir:");
                                entradaString = sc.nextLine();
                                System.out.println(conversor.binarioAOctal(entradaString));
                                subContinuar = false;
                            }
                            case "3" -> {
                                System.out.println("Introduce el valor a convertir:");
                                entradaString = sc.nextLine();
                                System.out.println(conversor.hexaAOctal(entradaString));
                                subContinuar = false;
                            }

                            default -> {
                                System.out.println("ERROR\nSelecciona una opción válida del menú de formato...");
                            }
                        }

                    } while (subContinuar);

                }
                case "4" -> {
                    subContinuar = true;
                    do {
                        System.out.println("""
                                HAS SELECCIONADO LA OPCIÓN A HEXADECIMAL
                                ----------------------------------------

                                ¿Qué formato quieres entregar?
                                1) Decimal
                                2) Binario
                                3) Octal
                                """);
                        subRespuestaMenu = sc.nextLine();
                        switch (subRespuestaMenu) {
                            case "1" -> {
                                System.out.println("Introduce el valor a convertir:");
                                entrada = scNum.nextInt();
                                System.out.println(conversor.decimalAHexadecimal(entrada));
                                subContinuar = false;
                            }
                            case "2" -> {
                                System.out.println("Introduce el valor a convertir:");
                                entradaString = sc.nextLine();
                                System.out.println(conversor.binarioAHexa(entradaString));
                                subContinuar = false;
                            }
                            case "3" -> {
                                System.out.println("Introduce el valor a convertir:");
                                entradaGrande = scNum.nextInt();
                                System.out.println(conversor.octalAHexa(entradaGrande));
                                subContinuar = false;
                            }

                            default -> {
                                System.out.println("ERROR\nSelecciona una opción válida del menú de formato...");
                            }
                        }

                    } while (subContinuar);
                }
                case "S" -> {
                    System.out.println("Saliendo del programa...");
                    continuar = false;
                }

                default -> {
                    System.out.println("ERROR\nSelecciona una opción válida...");
                }
            }

        } while (continuar);
    }
}
