package Clases;

public class Producto {
    protected String codigo;
    protected String nombre;
    protected Double precio;
    public Producto(String codigo, String nombre, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }
    public Producto(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = null;
    }

    public Double calcularDescuento(Double porcentaje){
        Double precioFinal = precio;
        if (precio != null) {
            precioFinal = precioFinal*(100-porcentaje);
        }
        return precioFinal;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Producto other = (Producto) obj;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        return true;
    }
    
    
    
    
    
    

    

    
}
