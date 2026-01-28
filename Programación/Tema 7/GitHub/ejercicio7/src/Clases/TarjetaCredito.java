package Clases;

public class TarjetaCredito extends Tarjeta {

    private double limiteCredito;

    public TarjetaCredito(double limiteCredito, String numero, double saldo, String titular) {
        super(numero, saldo, titular);
        this.limiteCredito = limiteCredito;
    }

    @Override
    public boolean pagar(double importe) {
        if (saldo + limiteCredito >= importe) {
            saldo -= importe;
            return true;
        }
        return false;
    }
}
