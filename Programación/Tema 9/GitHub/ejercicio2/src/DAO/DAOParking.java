package DAO;

import java.time.LocalTime;
import java.util.ArrayList;

import Clases.Coche;
import Clases.Moto;
import Clases.VehiculoAparcado;
import Excepciones.MiExcepcion;
import Interfaz.Lavable;

public class DAOParking {
    private ArrayList<VehiculoAparcado> listaVehiculos;

    public DAOParking() throws MiExcepcion {
        this.listaVehiculos = new ArrayList<>();
        cargarDatos();
    }

    private void cargarDatos() throws MiExcepcion {
        VehiculoAparcado c1 = new Coche("1234ABC", "Ford", "TurboMill", LocalTime.now().minusHours(4), 4, true);
        VehiculoAparcado c2 = new Coche("0000ZZZ", "Seat", "Cordoba", LocalTime.now().minusHours(3).minusMinutes(15), 3,
                false);
        VehiculoAparcado c3 = new Coche("9876XYZ", "Renault", "Clio", LocalTime.now().minusMinutes(45), 5, true);
        VehiculoAparcado c4 = new Coche("4512BTR", "Toyota", "Corolla", LocalTime.now().minusHours(1).minusMinutes(30),
                5, false);
        VehiculoAparcado c5 = new Coche("0931GFT", "Volkswagen", "Golf", LocalTime.now().minusHours(5), 3, true);

        VehiculoAparcado m1 = new Moto("1111BBB", "Honda", "CBR", LocalTime.now().minusHours(1), 600);
        VehiculoAparcado m2 = new Moto("2222CCC", "Yamaha", "MT-07", LocalTime.now().minusMinutes(30), 689);
        VehiculoAparcado m3 = new Moto("3333DDD", "Kawasaki", "Ninja", LocalTime.now().minusHours(2).minusMinutes(15),
                400);
        VehiculoAparcado m4 = new Moto("4444FFF", "Ducati", "Panigale", LocalTime.now().minusHours(4), 1100);
        VehiculoAparcado m5 = new Moto("5555GGG", "BMW", "GS", LocalTime.now().minusMinutes(10), 1250);

        listaVehiculos.add(c1);
        listaVehiculos.add(c2);
        listaVehiculos.add(c3);
        listaVehiculos.add(c4);
        listaVehiculos.add(c5);
        listaVehiculos.add(m1);
        listaVehiculos.add(m2);
        listaVehiculos.add(m3);
        listaVehiculos.add(m4);
        listaVehiculos.add(m5);
    }

    public void agregarVehiculo(VehiculoAparcado v) {
        this.listaVehiculos.add(v);
    }

    public VehiculoAparcado buscarVehiculo(String matricula) {
        for (VehiculoAparcado vehiculo : listaVehiculos) {
            if (vehiculo.getMatricula().equals(matricula)) {
                return vehiculo;
            }
        }
        return null;
    }

    public boolean registrarSalida(String matricula) {
        if (buscarVehiculo(matricula) != null) {
            buscarVehiculo(matricula).setHoraSalida(LocalTime.now());
            return true;
        } else {
            return false;
        }
    }

    public double lavarVehiculo(Lavable v) {
        return v.calcularPrecioLavado();
    }

    public double calcularIngresosParking() {
        double ingresos = 0;
        for (VehiculoAparcado vehiculo : listaVehiculos) {
            if (vehiculo.getHoraSalida() != null) {
                if (vehiculo.isLavado()) {
                    Lavable lavable = (Lavable) vehiculo;
                    ingresos += lavable.calcularPrecioLavado();
                } else {
                    ingresos += vehiculo.calcularPrecioParking();
                }
            }
        }
        return ingresos;
    }

    public int cantidadVehiculosAparcados() {
        int num = 0;
        for (VehiculoAparcado vehiculo : listaVehiculos) {
            if (vehiculo.getHoraSalida() == null) {
                num++;
            }
        }
        return num;
    }

    public ArrayList<VehiculoAparcado> devolverListaCargada() {
        return new ArrayList<>(this.listaVehiculos);
    }

    public ArrayList<VehiculoAparcado> getListaVehiculos() {
        return listaVehiculos;
    }

}
