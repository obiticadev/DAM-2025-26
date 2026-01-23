package Clases;

public class TarjetaDebito extends Tarjeta {

    private double comision;

    public TarjetaDebito(String numero, double saldo) {
        super(numero, saldo);
        this.comision = 1;
    }

    @Override
    public boolean pagar(double importe) {
        double total = importe + comision;

        if (saldo >= total) {
            saldo -= total;
            return true;
        }
        return false;
    }
}