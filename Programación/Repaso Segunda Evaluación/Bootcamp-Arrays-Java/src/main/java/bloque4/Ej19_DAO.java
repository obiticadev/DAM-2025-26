package bloque4;

import java.util.ArrayList;

/**
 * EJERCICIO 19 — DAO basico con ArrayList
 * Teoria: teoria/04_DAO_Menu.md (seccion 1)
 *
 * DAO que gestiona una lista de Ej19_Producto.
 */
public class Ej19_DAO {

    private ArrayList<Ej19_Producto> productos;

    // TODO 1: Implementa el constructor. Inicializa la lista vacia.
    public Ej19_DAO() {
        throw new UnsupportedOperationException("Implementa el constructor");
    }

    // TODO 2: Implementa cargarDatos() — agrega al menos 3 productos de ejemplo.
    public void cargarDatos() { }

    // TODO 3: Implementa agregar(Ej19_Producto p) — anade a la lista si no existe uno
    //         con el mismo id. Devuelve boolean.
    public boolean agregar(Ej19_Producto p) { return false; }

    // TODO 4: Implementa buscarPorId(int id) — devuelve el producto o null.
    public Ej19_Producto buscarPorId(int id) { return null; }

    // TODO 5: Implementa eliminar(int id) — elimina el producto de la lista. Devuelve boolean.
    public boolean eliminar(int id) { return false; }

    // TODO 6: Implementa listar() — devuelve una COPIA de la lista (new ArrayList<>(productos)).
    public ArrayList<Ej19_Producto> listar() { return null; }

    // TODO 7: Implementa valorTotalInventario() — suma getValorTotal() de todos los productos.
    public double valorTotalInventario() { return 0.0; }

    public int size() { return productos != null ? productos.size() : 0; }

    public static void main(String[] args) {
        System.out.println("=== Ejercicio 19: DAO Basico ===\n");
        Ej19_DAO dao = new Ej19_DAO();
        dao.cargarDatos();
        for (Ej19_Producto p : dao.listar()) {
            System.out.println(p);
        }
        System.out.println("Valor inventario: " + dao.valorTotalInventario());
    }
}
