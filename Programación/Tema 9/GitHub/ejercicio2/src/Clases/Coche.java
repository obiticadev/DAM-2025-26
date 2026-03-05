package Clases;

import java.time.LocalTime;

import Excepciones.MiExcepcion;

public class Coche extends VehiculoAparcado {
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

        precio = (double) (calcularHorasFacturables() * 2.5);
    }

}
