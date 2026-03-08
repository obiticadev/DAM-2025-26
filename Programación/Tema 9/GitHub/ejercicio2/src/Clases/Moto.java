package Clases;

import java.time.LocalTime;

import Excepciones.MiExcepcion;
import Interfaz.Lavable;

public class Moto extends VehiculoAparcado implements Lavable {

    private final double PRECIO_X_HORA = 1.5;
    private final int PRECIO_LAVADO = 5;

    private int cilindrada;

    public Moto(String matricula, String marca, String modelo, LocalTime horaEntrada, int cilindrada)
            throws MiExcepcion {
        super(matricula, marca, modelo, horaEntrada);
        this.cilindrada = cilindrada;
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

    public int getCilindrada() {
        return cilindrada;
    }

    @Override
    public void asignarLavado() {
        this.lavado = true;
    }

}
