package modelos;

public class Producto {
    private String id;
    private String nombre;
    private double precio;
    private String categoria;
    private boolean enStock;

    public Producto(String id, String nombre, double precio, String categoria, boolean enStock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.enStock = enStock;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public String getCategoria() { return categoria; }
    public boolean isEnStock() { return enStock; }

    @Override
    public String toString() {
        return String.format("Producto{id='%s', nombre='%s', precio=%.2f, categoria='%s', stock=%b}", 
                id, nombre, precio, categoria, enStock);
    }
}
