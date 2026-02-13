package Clases;

public class ProductoElectronico extends Producto {

    protected Integer garantia;

    public ProductoElectronico(String codigo, String nombre, Double precio, int garantia) {
        super(codigo, nombre, precio);
        this.garantia = garantia;
    }

    public ProductoElectronico(String codigo, String nombre, int garantia) {
        super(codigo, nombre);
        this.garantia = garantia;
    }

    public Double calcularDescuento(Double porcentaje, Double descuentoAdicional) {

        Double precioFinal = super.calcularDescuento(porcentaje);
        if (precioFinal != null) {
            precioFinal = precioFinal - descuentoAdicional;
        }
        return precioFinal;

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProductoElectronico other = (ProductoElectronico) obj;
        if (garantia == null) {
            if (other.garantia != null)
                return false;
        } else if (!garantia.equals(other.garantia))
            return false;
        return true;
    }

    public Integer getGarantia() {
        return garantia;
    }

}
