package Clases;

import java.time.Duration;
import java.time.LocalTime;

import Excepciones.MiExcepcion;

public abstract class VehiculoAparcado {
    protected String matricula;
    protected String marca;
    protected String modelo;
    protected LocalTime horaEntrada;
    protected LocalTime horaSalida;
    protected boolean lavado;

    public VehiculoAparcado(String matricula, String marca, String modelo, LocalTime horaEntrada) throws MiExcepcion {
        if (!comprobarMatricula(matricula)) {
            throw new MiExcepcion(matricula, horaEntrada);
        }
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.horaEntrada = horaEntrada;
        this.horaSalida = null;
        this.lavado = false;
    }

    private boolean comprobarMatricula(String m) {
        // si la matrícula coincide con el patrón devuelve true
        return m.matches("\\d{4}[A-Z]{3}");
    }

    public String mostrarInformacion() {
        StringBuilder sb = new StringBuilder();
        sb.append("Matrícula: ").append(matricula).append("\n")
                .append("Marca: ").append(marca).append("\n")
                .append("Modelo: ").append(modelo).append("\n")
                .append("Hora de entrada: ").append(horaEntrada).append("\n")
                .append("Hora de salida: ").append(horaSalida).append("\n")
                .append("Lavado: ").append(lavado);
        return sb.toString();
    }

    protected long calcularHorasFacturables() {
        long dif;
        dif = Duration.between(horaEntrada, horaSalida).toHours();
        if (lavado) {
            dif -= 2;
        }
        return dif;
    }

    public abstract double calcularPrecioParking();

}
