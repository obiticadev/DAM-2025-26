import java.util.Scanner;

import Clases.Matriz;

public class App {
    /*
     * 
     * Crearemos la matriz de la clase
     * asumimos el número de pcs, por tanto 6 filas y 3 columnas
     * Debemos hacer una función para hacer un sorteo, nos devolverá el
     * alumno al que le ha tocado el sorteo
     * Crea una función que recibe el nombre de un alumno, y devuelve
     * el true/false, dependiendo de si está o no
     * Crea una función que pasará lista a todos los alumnos, de forma que mostrará
     * el nombre de cada uno de los alumnos, y dependiendo de la respuesta
     * ("si"/"no")
     * mantendrá el nombre del alumno, o lo dejará vacío si la respuesta es "NO"
     */
    public static void main(String[] args) throws Exception {
        String[][] alumnos = alumnos();
        Matriz[][] matrizAlumnos = new Matriz[alumnos.length][alumnos[0].length];
        Scanner sc = new Scanner(System.in);
        int respuesta;
        for (int i = 0; i < alumnos.length; i++) {
            for (int j = 0; j < alumnos[0].length; j++) {
                matrizAlumnos[i][j] = new Matriz(alumnos[i][j], false);
                System.out.println("¿Está sentad@ " + matrizAlumnos[i][j].getName() + "?\nResponde con\n1) Sí\n2) No");
                respuesta = sc.nextInt();
                if (respuesta == 1) {
                    matrizAlumnos[i][j].setEstado(true);
                }
                
            }
            System.out.println();
        }
        for (int i = 0; i < alumnos.length; i++) {
            for (int j = 0; j < alumnos[0].length; j++) {
                if (matrizAlumnos[i][j].getEstado() == true) {
                    System.out.print(matrizAlumnos[i][j].getName() + "\t");
                    
                } else {
                    System.out.print("-----\t");
                }
            }
            System.out.println();
        }
    }

    private static String[][] alumnos() {
    return new String[][] {
        {"Ana", "Luis", "María"},
        {"Jorge", "Clara", "Pablo"},
        {"Sara", "Iván", "Lucía"},
        {"Raúl", "Laura", "Diego"},
        {"Nora", "Hugo", "Elena"},
        {"Marco", "Iris", "David"}
    };
}

}
