package Clases;

public class TarjetaDebito extends Tarjeta {

    private double comision;

    public TarjetaDebito(double comision, String numero, double saldo, String titular) {
        super(numero, saldo, titular);
        this.comision = comision;
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

    @Override
    public StringBuilder getTarjeta() {
        super.getTarjeta().append("\nComisión: ")
                .append(this.comision);
        return super.getTarjeta();
    }

}