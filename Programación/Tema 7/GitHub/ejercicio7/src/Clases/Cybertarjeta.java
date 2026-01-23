package Clases;

public class Cybertarjeta extends Tarjeta {

    private double limiteOperacion;

    public Cybertarjeta(String numero, double saldo) {
        super(numero, saldo);
        this.limiteOperacion = 20;
    }

    @Override
    public boolean pagar(double importe) {
        if (saldo > importe) {
            if (importe > limiteOperacion) {
                return false;
            }else {
                return true;
            }
        } else {
            return false;
        }
    }
}
