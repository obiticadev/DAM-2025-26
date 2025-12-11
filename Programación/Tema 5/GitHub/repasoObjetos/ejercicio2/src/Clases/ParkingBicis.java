package Clases;

import java.util.Arrays;

public class ParkingBicis {
    private final int PARKING_TOTAL = 10;
    private String[] numClave;

    public ParkingBicis(String[] numClave) {
        this.numClave = new String[PARKING_TOTAL];
        Arrays.fill(numClave, "");
    }

    private int buscarPrimero(String[] matriz) {
        int salida = -1;
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[i].equals("")) {
                salida = i;
                return salida;
            }
        }
        return salida;
    }

    public void registrarEntrada(String candado) {
        if (buscarPrimero(this.numClave) != -1) {
            this.numClave[buscarPrimero(this.numClave)] = candado;
            System.out.println("Bicicleta registrada");

        } else {
            System.out.println("Está lleno, no se admiten más bicicletas");
        }
    }

}
