package Clases;

import java.util.Random;

public class Cuenta {
    private static Random rd = new Random();
    protected String titular;
    private final int NUM_TARJETA = 16;
    private final int NUM_MAX = 5;
    private int contador = 0;
    private Tarjeta[] tarjeta;

    public Cuenta(String titular) {
        this.titular = titular;
        this.tarjeta = new Tarjeta[NUM_MAX];
    }

    public StringBuilder numRandom16() {
        StringBuilder numTarjeta = null;
        for (int i = 0; i < NUM_TARJETA; i++) {
            numTarjeta.append(rd.nextInt(10));
        }
        return numTarjeta;
    }

    public void añadirTarjeta(int tipo) {

        if (contador < NUM_MAX) {
            switch (tipo) {
                case 1 -> {
                    tarjeta[contador] = new TarjetaCredito(200, numRandom16().toString(), tipo, titular)
                }

                case 2 -> {
                    tarjeta[contador] = new TarjetaCredito(numRandom16().toString(), 0);
                }

                case 3 -> {
                    tarjeta[contador] = new Cybertarjeta(numRandom16().toString(), 0);
                }

                default -> {

                }
            }
        }
    }

}
