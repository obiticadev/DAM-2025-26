package Herramientas;

import Clases.Coche;
import Clases.Moto;
import Clases.VehiculoAparcado;
import Excepciones.MiExcepcion;
import java.time.LocalTime;

public class VehiculoFactory {

    // Método estático que actúa como fábrica
    public static VehiculoAparcado crearVehiculo(int tipo, String matricula, String marca,
            String modelo, LocalTime horaEntrada,
            int param1, boolean param2) throws MiExcepcion {

        switch (tipo) {
            case 1: // 1 = Coche
                // param1 = puertas, param2 = esSUV
                return new Coche(matricula, marca, modelo, horaEntrada, param1, param2);
            case 2: // 2 = Moto
                // param1 = cilindrada, param2 se ignora
                return new Moto(matricula, marca, modelo, horaEntrada, param1);
            default:
                throw new IllegalArgumentException("Tipo de vehículo no soportado");
        }
    }
}