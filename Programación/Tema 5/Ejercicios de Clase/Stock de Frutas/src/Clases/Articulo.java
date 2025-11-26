package Clases;

public class Articulo {
    private String codigo;
    private String nombre;
    private double precioSinIva;
    private final double IVA = 0.21;
    private int stockInicial;
    private int stockRestante;

    public Articulo(String codigo, String nombre, double precioSinIva, int stockInicial) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioSinIva = precioSinIva;
        this.stockInicial = stockInicial;
    }

    
    
    public Articulo(String nombre, double precioSinIva) {
        this.nombre = nombre;
        this.precioSinIva = precioSinIva;
    }

    


    public Articulo(String nombre, double precioSinIva, int stockInicial) {
        this.nombre = nombre;
        this.precioSinIva = precioSinIva;
        this.stockInicial = stockInicial;
    }



    public String devolverDatos(){

        int ivaFormateado = (int) (IVA * 100);
        double pvp = (1 + IVA) * precioSinIva;
        
        String salida = nombre +  " - Precio: " + precioSinIva + "€ - IVA: " + ivaFormateado + "% - PVP: " + String.format("%.2f", pvp) + "€";
        
        return salida;
    }

    public static Articulo[] devolverArticulos() {

        Articulo[] articulos = new Articulo[5]; 
        articulos[0] = new Articulo("tomates", 2);
        articulos[1] = new Articulo("pimientos", 2.36, 30); 
        articulos[2] = new Articulo("pijamas", 24, 3); 
        articulos[3] = new Articulo("pantalones", 44, 30); 
        articulos[4] = new Articulo("zapatos", 22, 30);      
        return articulos; 
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioSinIva() {
        return precioSinIva;
    }

    public void setPrecioSinIva(double precioSinIva) {
        this.precioSinIva = precioSinIva;
    }

    public double getIVA() {
        return IVA;
    }

    public int getStockInicial() {
        return stockInicial;
    }

    public void setStockInicial(int stockInicial) {
        this.stockInicial = stockInicial;
    }

    public int getStockRestante() {
        return stockRestante;
    }

    public void setStockRestante(int stockRestante) {
        this.stockRestante = stockRestante;
    }

}
