package Clases;

public class Tarjeta extends Cuenta {

    protected String numero;
    protected double saldo;

    public Tarjeta(String numero, double saldo, String titular) {
        super(titular);
        this.numero = numero;
        this.saldo = saldo;
    }

    public boolean pagar(double importe) {
        if (saldo >= importe) {
            saldo -= importe;
            return true;
        }
        return false;
    }

    public double getSaldo() {
        return saldo;
    }

    @Override
    public String toString() {
        return "Tarjeta de " + titular + " | Saldo: " + saldo;
    }
}
