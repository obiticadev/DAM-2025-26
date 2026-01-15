import Clases.Alumno;
import Clases.Nota;
import Clases.TipoNota;

import java.util.Scanner;

public class App {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        int numAlumnos;
        boolean continuar = true;
        String respuestaMenu;

        System.out.println("LISTADO\n-------");
        try {
            System.out.print("Introduce el número de alumnos que quieres registrar: ");
            numAlumnos = Integer.parseInt(sc.nextLine());

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Has introducido una cantidad incoherente, asignaremos 5 alumnos por defecto");
            numAlumnos = 5;

        }
        Alumno[] registro = new Alumno[numAlumnos];

        for (int i = 0; i < registro.length; i++) {
            System.out.print("Introduce el nombre del alumno " + (i + 1) + ": ");
            String nombre = sc.nextLine();
            registro[i] = new Alumno(nombre, String.valueOf(i));

        }

        System.out.println("\nALUMNOS\n-------");
        for (int i = 0; i < registro.length; i++) {
            System.out.println(registro[i].getNombre() + " [" + registro[i].getNum_matricula() + "]: "
                    + registro[i].mostrarInfo());
        }

        if (registro.length != 0) {
            do {
                System.out.println("\nSELECCIONA 1 ALUMNO\n-------------------");
                for (int i = 0; i < registro.length; i++) {
                    System.out.println(
                            (i + 1) + ") " + registro[i].getNombre() + " [" + registro[i].getNum_matricula() + "]");
                }

                System.out.println("\n0) Salir");
                respuestaMenu = sc.nextLine();
                if (validarEntradaAInt(respuestaMenu)) {
                    int respuestaMenuInt = Integer.parseInt(respuestaMenu) - 1;
                    if (respuestaMenuInt >= 0 && respuestaMenuInt < (registro.length)) {
                        System.out.println("Has seleccionado a " + registro[respuestaMenuInt].getNombre());
                        menuAlumnoSeleccionado(registro[respuestaMenuInt]);
                    } else {
                        System.out.println(
                                "Has seleccionado 0 o cualquier otro valor que no corresponda a un alumno.\nSaliendo del programa...");
                        continuar = false;
                    }
                } else {
                    System.out.println("El valor de entrada no es válido");
                    continuar = false;
                }

            } while (continuar);

        }

    }

    public static boolean validarEntradaAInt(String entrada) {
        try {
            int salida = Integer.parseInt(entrada);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }
    }

    public static void menuAlumnoSeleccionado(Alumno registroEntrada) {
        boolean continuar = true;
        String respuestaMenu;
        String respuestaSubMenu;
        do {
            System.out.println("""
                    MENÚ DE ALUMNO
                    --------------

                    1) Añadir un módulo
                    2) Listar módulos

                    S) Salir
                    """);
            respuestaMenu = String.valueOf(sc.nextLine().charAt(0)).toUpperCase();

            switch (respuestaMenu) {
                case "1" -> {
                    System.out.print("Introduce el nombre del módulo: ");
                    String modulo = sc.nextLine();
                    System.out.print("Introduce el código de referencia: ");
                    String codigo = sc.nextLine();
                    registroEntrada.insertarModulo(modulo, codigo);
                }

                case "2" -> {
                    System.out.println(registroEntrada.mostrarInfo());
                    System.out.print("Selecciona una materia: ");
                    respuestaSubMenu = sc.nextLine();
                    if (validarEntradaAInt(respuestaSubMenu)) {
                        int respuestaSubMenuInt = Integer.parseInt(respuestaSubMenu) - 1;
                        if (respuestaSubMenuInt >= 0
                                && respuestaSubMenuInt < registroEntrada.getContador()) {
                            System.out.println("Introduce el tipo de evaluación: \n" + //
                                    "    PRIMERA,\n" + //
                                    "    SEGUNDA,\n" + //
                                    "    TERCERA,\n" + //
                                    "    ORDINARIA,\n" + //
                                    "    EXTRAORDINARIA");
                            String respuestaEvaluacion = sc.nextLine().toUpperCase();
                            TipoNota tipoSeleccionado = null;

                            // Recorremos todos los valores del Enum (PRIMERA, SEGUNDA...)
                            for (TipoNota t : TipoNota.values()) {
                                if (t.name().equals(respuestaEvaluacion)) {
                                    tipoSeleccionado = t; // Si coincide, lo guardamos
                                    break;
                                }
                            }
                            if (tipoSeleccionado != null) {
                                double calificacion = 0;
                                try {
                                    System.out.println("Introduce una nota: ");
                                    calificacion = Double.parseDouble(sc.nextLine());
                                    
                                } catch (Exception e) {
                                    // TODO: handle exception
                                    System.out.println("No es válida la nota insertada");
                                }
                                registroEntrada.insertarNotaAlModulo(respuestaSubMenuInt, calificacion, tipoSeleccionado);
                            }
                        }
                    } else {
                        System.out.println("Introduce un valor numérico válido");
                    }
                }

                case "S" -> {
                    System.out.println("Saliendo del perfil...");
                    continuar = false;
                }

                default -> {
                    System.out.println("Selecciona una opción válida");
                }
            }

        } while (continuar);
    }

    

}
