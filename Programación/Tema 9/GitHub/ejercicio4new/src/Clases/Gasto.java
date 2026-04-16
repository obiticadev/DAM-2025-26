package Clases;

import Enum.Categoria;
import Excepciones.GastoInvalidoException;
import Interfaces.Transaccion;

public class Gasto implements Transaccion {
    private double importeGasto;
    private Categoria categoria;
    private String detalle;

    public Gasto(double importeGasto, Categoria categoria, String detalle) throws GastoInvalidoException {
        if (importeGasto < 0) {
            throw new GastoInvalidoException("Número negativo, tiene que ser positivo");
        }
        this.importeGasto = importeGasto;
        this.categoria = categoria;
        this.detalle = detalle;
    }

    @Override
    public double getImport() {
        return this.importeGasto;
    }

    // ---------------------- GETTERS Y SETTERS

    public double getImporteGasto() {
        return importeGasto;
    }

    public void setImporteGasto(double importeGasto) throws GastoInvalidoException {
        if (importeGasto < 0) {
            throw new GastoInvalidoException("Número negativo, tiene que ser positivo");
        }
        this.importeGasto = importeGasto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

}
