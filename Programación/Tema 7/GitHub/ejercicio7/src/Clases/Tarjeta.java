package Clases;

public abstract class Tarjeta extends Cuenta {

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
    public StringBuilder getTarjeta() {
        super.getTarjeta().append("\nNúmero: ")
                .append(this.numero)
                .append("\nSaldo: ")
                .append(this.saldo);

        return super.getTarjeta();
    }

}
