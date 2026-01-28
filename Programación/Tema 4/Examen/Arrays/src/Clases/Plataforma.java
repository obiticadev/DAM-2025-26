package Clases;

import java.util.Arrays;

public class Plataforma {

    protected String[] juegos = new String[] {
            "The Legend of Zelda",
            "Super Mario Bros",
            "Minecraft",
            "Final Fantasy VII",
            "The Witcher 3",
            "God of War",
            "Halo" };
    protected int[] indiceConPuntuacion = new int[juegos.length];
    protected int[] indiceConContador = new int[juegos.length];
    protected double[] indiceValoracionMedia = new double[juegos.length];
    protected final int PUNTUACION_MIN = 0;
    protected final int PUNTUACION_MAX = 10;

    public Plataforma() {
        Arrays.fill(indiceConPuntuacion, 0);
        Arrays.fill(indiceConContador, 0);
        Arrays.fill(indiceValoracionMedia, 0);
    }

    public StringBuilder listar() {
        StringBuilder salida = new StringBuilder();
        for (int i = 0; i < juegos.length; i++) {
            salida.append(i + 1).append(". ").append(juegos[i]).append("\n");
        }
        return salida;
    }

    public StringBuilder listarValoracionMedia() {
        StringBuilder salida = new StringBuilder();
        for (int i = 0; i < juegos.length; i++) {
            salida.append(i + 1).append(". ").append(juegos[i]).append(": ").append(indiceValoracionMedia[i])
                    .append("\n");
        }
        return salida;
    }

    public StringBuilder listarNumeroValoraciones() {
        StringBuilder salida = new StringBuilder();
        for (int i = 0; i < juegos.length; i++) {
            salida.append(i + 1).append(". ").append(juegos[i]).append(": ").append(indiceConContador[i]).append("\n");
        }
        return salida;
    }

    public String mejorJuegoValorado() {
        double maxMedia = -1;
        boolean hayVoto = false;
        for (int i = 0; i < juegos.length; i++) {
            if (indiceConContador[i] > 0) {
                hayVoto = true;
                if (indiceValoracionMedia[i] > maxMedia) {
                    maxMedia = indiceValoracionMedia[i];
                }
            }
        }

        if (!hayVoto) {
            return "Aún no se han realizado valoraciones";
        }

        StringBuilder mejores = new StringBuilder();
        for (int i = 0; i < juegos.length; i++) {
            if (indiceConContador[i] > 0 && indiceValoracionMedia[i] == maxMedia) {
                mejores.append(juegos[i])
                        .append(" con media de: ")
                        .append(indiceValoracionMedia[i])
                        .append("\n");
            }
        }
        return mejores.toString();
    }

    public boolean validarJuegoSeleccionado(int entrada) {
        entrada--;
        return entrada >= 0 && entrada < juegos.length;
    }

    public boolean validarPuntuacion(int entrada) {
        return entrada >= PUNTUACION_MIN && entrada <= PUNTUACION_MAX;
    }

    public void puntuarJuego(int juegoSeleccionado, int puntuacion) {
        juegoSeleccionado--;
        indiceConPuntuacion[juegoSeleccionado] += puntuacion;
        indiceConContador[juegoSeleccionado]++;
        indiceValoracionMedia[juegoSeleccionado] = (double) (indiceConPuntuacion[juegoSeleccionado])
                / (double) (indiceConContador[juegoSeleccionado]);
    }

    public String[] getJuegos() {
        return juegos;
    }

    public int[] getIndiceConPuntuacion() {
        return indiceConPuntuacion;
    }

    public int getPUNTUACION_MIN() {
        return PUNTUACION_MIN;
    }

    public int getPUNTUACION_MAX() {
        return PUNTUACION_MAX;
    }

    public double[] getIndiceValoracionMedia() {
        return indiceValoracionMedia;
    }

    public void setIndiceConPuntuacion(int[] indiceConPuntuacion) {
        this.indiceConPuntuacion = indiceConPuntuacion;
    }

}