package bloque4;

/**
 * Clase Producto para los ejercicios del Bloque IV.
 * Cada producto tiene un id, nombre, precio y stock organizado en estanterias.
 */
public class Ej19_Producto {
    private int id;
    private String nombre;
    private double precio;
    private int[][] stock; // [estanteria][posicion]

    // TODO 1: Implementa constructor(int id, String nombre, double precio, int filas, int cols).
    //         Valida: id > 0, nombre no null/vacio, precio >= 0, filas > 0, cols > 0.
    //         Si no, IllegalArgumentException. Inicializa stock a 0.
    public Ej19_Producto(int id, String nombre, double precio, int filas, int cols) {
        throw new UnsupportedOperationException("Implementa el constructor");
    }

    // TODO 2: Implementa getStockTotal() — suma de todos los valores del array.
    public int getStockTotal() { return 0; }

    // TODO 3: Implementa getValorTotal() — stockTotal * precio.
    public double getValorTotal() { return 0.0; }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int[][] getStock() { return stock; }
    public int getFilas() { return stock.length; }
    public int getColumnas() { return stock[0].length; }

    public boolean almacenar(int fila, int col, int cantidad) {
        if (fila < 0 || fila >= stock.length || col < 0 || col >= stock[0].length || cantidad <= 0) return false;
        stock[fila][col] += cantidad;
        return true;
    }

    public boolean retirar(int fila, int col, int cantidad) {
        if (fila < 0 || fila >= stock.length || col < 0 || col >= stock[0].length || cantidad <= 0) return false;
        if (stock[fila][col] < cantidad) return false;
        stock[fila][col] -= cantidad;
        return true;
    }

    @Override
    public String toString() {
        return "Producto{id=" + id + ", nombre='" + nombre + "', precio=" + precio + ", stock=" + getStockTotal() + "}";
    }
}
