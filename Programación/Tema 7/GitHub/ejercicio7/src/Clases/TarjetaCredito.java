package Clases;

public class TarjetaCredito extends Tarjeta {

    private double limiteCredito;

    public TarjetaCredito(String numero, double saldo) {
        super(numero, saldo);
        this.limiteCredito = 200;
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
