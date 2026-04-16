package Clases;

import java.time.LocalTime;

import Excepciones.MiExcepcion;
import Interfaz.Lavable;

public class Coche extends VehiculoAparcado implements Lavable {
    private final double PRECIO_X_HORA = 2.5;
    private final int PRECIO_LAVADO = 12;

    private int numeroPuertas;
    private boolean esSUV;

    public Coche(String matricula, String marca, String modelo, LocalTime horaEntrada, int numeroPuertas, boolean esSUV)
            throws MiExcepcion {
        super(matricula, marca, modelo, horaEntrada);
        this.numeroPuertas = numeroPuertas;
        this.esSUV = esSUV;
    }

    @Override
    public double calcularPrecioParking() {
        double precio;
        precio = (double) (calcularHorasFacturables() * PRECIO_X_HORA);
        return precio;
    }

    @Override
    public double calcularPrecioLavado() {
        this.lavado = true;
        double precio;
        precio = calcularPrecioParking() + PRECIO_LAVADO;
        return precio;
    }

    public int getNumeroPuertas() {
        return numeroPuertas;
    }

    public boolean isEsSUV() {
        return esSUV;
    }

    @Override
    public void asignarLavado() {
        this.lavado = true;
    }

}
