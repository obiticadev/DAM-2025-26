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

    public boolean registrarEntrada(String candado) {
        boolean registro;
        if (buscarPrimero(this.numClave) != -1) {
            this.numClave[buscarPrimero(this.numClave)] = candado;
            registro = true;
        } else {
            registro = false;
        }
        return registro;
    }

    public boolean registrarSalida(String candado) {
        boolean salida = false;

        for (int i = 0; i < this.numClave.length; i++) {
            if (this.numClave[i].equals(candado)) {
                this.numClave[i] = "";
                salida = true;
                return salida;
            }
        }
        return salida;
    }

    public String mostrarBicicletas(){
        String bicicletas = "";

        for (int i = 0; i < this.numClave.length; i++) {
            if (this.numClave[i] != "") {
                if (i == 0) {
                    bicicletas = this.numClave[i] + ", ";
                } if (i == this.numClave.length-1) {
                    bicicletas += this.numClave[i];
                } else {
                    bicicletas += this.numClave[i] + ", ";
                }
            }
        }
        return bicicletas;
    }

}
