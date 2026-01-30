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

    public boolean añadirTarjeta(int tipo) {

        if (contador < NUM_MAX) {
            switch (tipo) {
                case 1 -> {
                    /*
                     * public TarjetaCredito(double limiteCredito, String numero, double saldo,
                     * String titular)
                     */
                    tarjeta[contador] = new TarjetaCredito(200, numRandom16().toString(), tipo, titular);
                }

                case 2 -> {
                    /*
                     * public TarjetaDebito(double comision, String numero, double saldo, String
                     * titular)
                     */
                    tarjeta[contador] = new TarjetaDebito(10, numRandom16().toString(), 0, this.titular);
                }

                case 3 -> {
                    /*
                     * public Cybertarjeta(double limiteOperacion, String numero, double saldo,
                     * String titular)
                     */
                    tarjeta[contador] = new Cybertarjeta(5, numRandom16().toString(), 0, this.titular);
                }

                default -> {

                }
            }
            this.contador++;
            return true;
        } else
            return false;
    }

    public StringBuilder getTarjeta() {
        StringBuilder tarjeta = new StringBuilder();
        tarjeta.append("Titular: ")
                .append(this.titular);

        return tarjeta;
    }

    public int getContador() {
        return contador;
    }

    public Tarjeta[] getTarjetaArray() {
        return this.tarjeta;
    }

}
