import java.time.Period;

import Clases.Puesto;

public class App {

    private static final int FILA = 6;
    private static final int COLUMNA = 4;
    private static int MAX = (FILA * COLUMNA);
    private static final int columnaPortatil = 2;
    private static String[] personas = new String[MAX];
    public static void main(String[] args) throws Exception {

        Puesto[][] puestos = new Puesto[FILA][COLUMNA];

        
        for (int fila = 0; fila < FILA; fila++) {
            for (int columna = 0; columna < COLUMNA; columna++) {
                puestos[fila][columna] = new Puesto();
                if (columna == columnaPortatil) {
                    puestos[fila][columna].setTipo("Portátil");
                } else {
                    puestos[fila][columna].setTipo("Fijo");
                }
            }
        }
        
        crearPersonas();
        asignarPuesto(personas, puestos);

        for (int fila = 0; fila < FILA; fila++) {
            for (int columna = 0; columna < COLUMNA; columna++) {
                System.out.print(puestos[fila][columna].getNombre() + "\t");
            }
            System.out.println();
        }



    }

    private static String[] crearPersonas() {
        for (int i = 0; i < MAX; i++) {
            personas[i] = "Alumno" + (i+1);
        }
        return personas;
    }

    private static void asignarPuesto(String[] personas, Puesto[][] puestos){

        for (int fila = 0; fila < FILA; fila++) {
            for (int columna = 0; columna < COLUMNA; columna++) {
                puestos[fila][columna].setNombre(personas[(fila + 1) * (columna + 1)-1]); 
                puestos[fila][columna].setOcupado(true); 
            }
        }
    }

}
