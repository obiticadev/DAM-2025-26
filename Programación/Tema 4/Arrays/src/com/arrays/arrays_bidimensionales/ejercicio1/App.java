package com.arrays.arrays_bidimensionales.ejercicio1;

import java.util.Arrays;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Inicializamos el Scanner
        Scanner sc = new Scanner(System.in);

        // Declaración de variables
        String[] modulos = { "IPE1", "PR", "BD", "SI", "ED", "LM", "CN" };
        int numAlumnos;
        boolean continuar = true;
        int respuestaMenu;

        // Necesitamos saber el número de alumnos para delimitar nuestro array
        // bidimensional
        System.out.println("Introduce el número de alumnos a registrar");
        numAlumnos = sc.nextInt();
        float[][] arrayAlumnosModulo = new float[numAlumnos][modulos.length];
        float[][] arraySumaXAlumno = new float[numAlumnos][1];
        float[][] arraySumaXModulo = new float[1][modulos.length];
        float promedioGeneral = 0;
        String encabezadoMatriz = "Alumno\\Asig\t|";
        String[] tablaContenidoMatriz = new String[numAlumnos];

        for (int i = 0; i < numAlumnos; i++) {
            for (int j = 0; j < modulos.length; j++) {
                System.out.println("¿Cuál es la nota en " + modulos[j] + " de alumno" + (i + 1) + "?");
                arrayAlumnosModulo[i][j] = sc.nextInt();
                if (arrayAlumnosModulo[i][j] < 0 || arrayAlumnosModulo[i][j] > 10) {
                    System.out.println("Debes introducir una cantidad de entre 0 y 10");
                    j--;
                } else {
                    // Si se cumple la condición de que el número sea válido almacenamos su valor
                    // para la suma de notas por alumno y por módulo
                    arraySumaXAlumno[i][0] = (j == 0) ? (arrayAlumnosModulo[i][j])
                            : (arraySumaXAlumno[i][0] + arrayAlumnosModulo[i][j]);

                    arraySumaXModulo[0][j] = (i == 0) ? (arrayAlumnosModulo[i][j])
                            : (arraySumaXModulo[0][j] + arrayAlumnosModulo[i][j]);
                }
            }
        }

        // Para el promedio general cogemos el array con la suma de modulos por alumno,
        // sumamos los valores de cada uno y
        // lo dividimos
        // entre el número de alumnos
        for (int i = 0; i < numAlumnos; i++) {
            promedioGeneral += (arraySumaXAlumno[i][0] / modulos.length);
        }
        promedioGeneral = promedioGeneral / numAlumnos;

        // Hay que imprimir la matriz ----

        System.out.println("Matriz de notas:");
        for (int i = 0; i < modulos.length; i++) {
            encabezadoMatriz = encabezadoMatriz + "\t" + modulos[i];
        }
        System.out.println(encabezadoMatriz);
        // Sumamos dos para que recoja también las dos columnas de número de alumnos y
        // de "|"
        for (int i = 0; i < modulos.length + 2; i++) {
            System.out.print("------------");
        }
        System.out.println("");

        // Para crear el contenido de la matriz utilizaremos un array de String para
        // guardar las diferentes concatenaciones de cada columna en cada fila y así
        // poder representarlas una vez montadas
        for (int i = 0; i < numAlumnos; i++) {
            for (int j = 0; j < modulos.length; j++) {
                if (j == 0) {
                    tablaContenidoMatriz[i] = "Alumno " + (i + 1) + "\t|\t" + arrayAlumnosModulo[i][j];
                } else {
                    tablaContenidoMatriz[i] = tablaContenidoMatriz[i] + "\t" + arrayAlumnosModulo[i][j];
                }
            }
        }
        for (int i = 0; i < tablaContenidoMatriz.length; i++) {
            System.out.println(tablaContenidoMatriz[i]);
        }

        // Aquí comienza el bucle doWhile para permanecer en el menú hasta que se
        // seleccione la opción 4
        do {
            System.out.println("""
                    MENÚ DE OPCIONES
                    ----------------

                    1) Suma y promedio de cada alumno (fila)
                    2) Suma y promedio de cada módulo (columna)
                    3) Nota media general del grupo
                    4) Salir del programa

                    """);
            respuestaMenu = sc.nextInt();
            switch (respuestaMenu) {
                case 1 -> {
                    System.out.println("\nSuma y promedio por alumno:");
                    for (int i = 0; i < numAlumnos; i++) {
                        System.out.println("Alumno " + i + " - Suma: " + arraySumaXAlumno[i][0] + " Promedio: "
                                + (arraySumaXAlumno[i][0] / modulos.length));
                    }
                }
                case 2 -> {
                    System.out.println("\nSuma y promedio por asignatura:");
                    for (int i = 0; i < modulos.length; i++) {
                        System.out.println(
                                modulos[i] + " - Suma: " + arraySumaXModulo[0][i] + " Promedio: "
                                        + (arraySumaXModulo[0][i] / numAlumnos));
                    }

                }
                case 3 -> {
                    System.out.println("Promedio general del grupo: " + promedioGeneral);

                }
                case 4 -> {
                    System.out.println("Saliendo del programa...");
                    continuar = false;

                }

                default -> {
                    System.out.println("Seleccions uns opción válida");

                }
            }

        } while (continuar);

    }
}
