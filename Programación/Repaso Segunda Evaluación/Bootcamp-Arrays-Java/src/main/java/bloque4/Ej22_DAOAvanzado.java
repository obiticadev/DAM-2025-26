package bloque4;

import java.util.ArrayList;

/**
 * EJERCICIO 22 — DAO avanzado con busquedas complejas
 * Teoria: teoria/04_DAO_Menu.md (seccion 1)
 *
 * Extiende el concepto de DAO con filtros, ordenacion y consultas agregadas.
 */
public class Ej22_DAOAvanzado {

    private ArrayList<Ej19_Producto> productos;

    public Ej22_DAOAvanzado() {
        this.productos = new ArrayList<>();
    }

    public void agregar(Ej19_Producto p) { productos.add(p); }

    // TODO 1: Implementa buscarPorNombre(String nombre) — devuelve una lista con
    //         todos los productos cuyo nombre CONTIENE el texto (case insensitive).
    public ArrayList<Ej19_Producto> buscarPorNombre(String nombre) { return new ArrayList<>(); }

    // TODO 2: Implementa buscarConStockMinimo(int minimo) — devuelve lista de productos
    //         cuyo stockTotal >= minimo.
    public ArrayList<Ej19_Producto> buscarConStockMinimo(int minimo) { return new ArrayList<>(); }

    // TODO 3: Implementa productoMasCaro() — devuelve el producto con mayor precio.
    //         Si la lista esta vacia, devuelve null.
    public Ej19_Producto productoMasCaro() { return null; }

    // TODO 4: Implementa productoConMasStock() — devuelve el producto con mayor stockTotal.
    public Ej19_Producto productoConMasStock() { return null; }

    // TODO 5: Implementa precioMedio() — media de precios de todos los productos.
    //         Si lista vacia, devuelve 0.
    public double precioMedio() { return 0.0; }

    // TODO 6: Implementa buscarPorRangoPrecio(double min, double max) — lista de productos
    //         con precio entre min y max (inclusive).
    public ArrayList<Ej19_Producto> buscarPorRangoPrecio(double min, double max) { return new ArrayList<>(); }

    // TODO 7: Implementa existeProducto(int id) — true si existe un producto con ese id.
    public boolean existeProducto(int id) { return false; }

    public ArrayList<Ej19_Producto> listar() { return new ArrayList<>(productos); }
    public int size() { return productos.size(); }

    public static void main(String[] args) {
        System.out.println("=== Ejercicio 22: DAO Avanzado ===\n");
        Ej22_DAOAvanzado dao = new Ej22_DAOAvanzado();
        dao.agregar(new Ej19_Producto(1, "Laptop", 999.99, 2, 3));
        dao.agregar(new Ej19_Producto(2, "Mouse", 19.99, 1, 2));
        dao.agregar(new Ej19_Producto(3, "Laptop Pro", 1499.99, 2, 3));
        System.out.println("Buscar 'laptop': " + dao.buscarPorNombre("laptop").size());
        System.out.println("Mas caro: " + dao.productoMasCaro());
        System.out.println("Precio medio: " + dao.precioMedio());
    }
}
