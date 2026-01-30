package Clases;

public class Cybertarjeta extends Tarjeta {

    private double limiteOperacion;

    public Cybertarjeta(double limiteOperacion, String numero, double saldo, String titular) {
        super(numero, saldo, titular);
        this.limiteOperacion = limiteOperacion;
    }

    @Override
    public boolean pagar(double importe) {
        if (saldo > importe) {
            if (importe > limiteOperacion) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public StringBuilder getTarjeta() {
        super.getTarjeta().append("\nLímite de Operaciones: ")
                .append(this.limiteOperacion);
        return super.getTarjeta();
    }

}
