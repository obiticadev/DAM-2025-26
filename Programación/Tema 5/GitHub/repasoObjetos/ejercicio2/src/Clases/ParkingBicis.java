package Clases;

import java.util.Arrays;

public class ParkingBicis {
    private final int PARKING_TOTAL = 10;
    private String[] numClave;

    public ParkingBicis() {
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

    // Método auxiliar para comprobar si la bicicleta ya está registrada (Mejora)
    private boolean bicicletaYaExiste(String candado) {
        for (String clave : this.numClave) {
            if (clave.equals(candado)) {
                return true;
            }
        }
        return false;
    }

    public boolean registrarEntrada(String candado) {
        int indiceLibre = buscarPrimero(this.numClave); // Llama solo una vez

        if (bicicletaYaExiste(candado)) {
            return false; // Error: Ya existe una bici con ese código
        }

        if (indiceLibre != -1) {
            this.numClave[indiceLibre] = candado; // Usa el índice guardado
            return true;
        } else {
            return false; // Parking lleno
        }
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
        return salida; // Bici no encontrada
    }

    public String mostrarBicicletas() {
        StringBuilder sb = new StringBuilder(); // Se utiliza StringBuilder para manejo eficiente de Strings
        boolean primeraBici = true;

        for (String clave : this.numClave) {
            if (!clave.equals("")) { // Solo procesa las que no están vacías
                if (!primeraBici) {
                    sb.append(", "); // Agrega la coma ANTES de la siguiente bicicleta
                }
                sb.append(clave);
                primeraBici = false;
            }
        }

        if (sb.length() == 0) {
            return "El parking está vacío.";
        }
        return sb.toString();
    }

    public int totalBicicletas() {
        int contador = 0;
        // Lógica corregida: itera sobre todo el array y cuenta los espacios NO vacíos.
        for (String clave : this.numClave) {
            if (!clave.equals("")) {
                contador++;
            }
        }
        return contador;
    }

}