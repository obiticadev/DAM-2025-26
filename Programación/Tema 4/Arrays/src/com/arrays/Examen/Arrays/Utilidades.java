package com.arrays.Examen.Arrays;

public class Utilidades {

    private static String[] preguntas = new String[10];

    public static String[] devolverPreguntas() {

        preguntas[0] = "Al comienzo de cada unidad la profesora presenta lo que se va a trabajar en la misma";
        preguntas[1] = "La profesora utiliza diferentes recursos para explicar los contenidos";
        preguntas[2] = "Las explicaciones son claras y ordenadas";
        preguntas[3] = "La profesora responde a las preguntas de los estudiantes sobre la materia";
        preguntas[4] = "La profesora utiliza ejemplos prácticos para facilitar el aprendizaje";
        preguntas[5] = "La profesora fomenta la participación activa de los estudiantes";
        preguntas[6] = "La profesora proporciona retroalimentación constructiva";
        preguntas[7] = "La profesora utiliza evaluaciones para medir el progreso";
        preguntas[8] = "La profesora adapta las actividades a las necesidades del grupo";
        preguntas[9] = "La profesora crea un ambiente de aprendizaje positivo";
        return preguntas;

    }

    public static int[][] getEncuestas() {
        if (preguntas == null)
            devolverPreguntas();

        int[][] notas = {
                { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
                { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
                { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
                { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
                { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
                { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
                { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
                { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
                { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
                { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }
        };
        return notas;

    }

}