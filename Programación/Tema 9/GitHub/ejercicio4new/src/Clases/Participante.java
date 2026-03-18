package Clases;

import java.util.LinkedList;
import java.util.List;

import Excepciones.ImportePositivoException;
import Interfaces.Transaccion;

public class Participante extends Persona {
    protected List<Transaccion> listaTransacciones;

    public Participante(String nombre, String apellido, String dni) {
        super(nombre, apellido, dni);
        this.listaTransacciones = new LinkedList<>();
    }

    public double consultarImporte() throws ImportePositivoException {
        double ingresos = 0;
        double gastos = 0;
        for (Transaccion transaccion : listaTransacciones) {
            if (transaccion instanceof Ingreso i) {
                ingresos += i.getImport();
            } else if (transaccion instanceof Gasto g) {
                gastos += g.getImport();
            }
        }
        if ((ingresos - gastos) > 0) {
            throw new ImportePositivoException("Hay más importe que de gastos para el participante:\n" + toString());
        }
        return ingresos - gastos;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + "\nApellido: " + apellido + "\nDNI: " + dni;
    }

}
