package bloque5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * EJERCICIO 26 — Serializar Listas de Objetos
 * Teoria: teoria/05_Serializacion.md (seccion 5)
 *
 * Contexto: La tienda necesita guardar el inventario completo (una lista
 * de productos) en un fichero y recuperarlo al arrancar.
 */
public class Ej26_SerializarLista {

    /**
     * Serializa una lista de productos a un fichero.
     *
     * @param ruta      ruta del fichero
     * @param productos lista de productos
     * @throws IOException si hay error de escritura
     */
    public static void serializarLista(String ruta, List<Producto> productos) throws IOException {
        // TODO 1: Crear ObjectOutputStream con try-with-resources.
        //         Escribir la lista con writeObject().
        throw new UnsupportedOperationException("TODO 1 no implementado");
    }

    /**
     * Deserializa una lista de productos desde un fichero.
     *
     * @param ruta ruta del fichero
     * @return lista de productos
     * @throws IOException            si hay error de lectura
     * @throws ClassNotFoundException si la clase no se encuentra
     */
    @SuppressWarnings("unchecked")
    public static List<Producto> deserializarLista(String ruta)
            throws IOException, ClassNotFoundException {
        // TODO 2: Crear ObjectInputStream con try-with-resources.
        //         Leer con readObject() y hacer cast a List<Producto>.
        //         Devolver la lista.
        return new ArrayList<>();
    }

    /**
     * Anade un producto a un fichero que ya contiene una lista serializada.
     * Lee la lista, anade el producto y la vuelve a serializar.
     *
     * @param ruta     ruta del fichero
     * @param producto producto a anadir
     * @throws IOException            si hay error de I/O
     * @throws ClassNotFoundException si la clase no se encuentra
     */
    public static void anadirProducto(String ruta, Producto producto)
            throws IOException, ClassNotFoundException {
        // TODO 3: Deserializar la lista existente.
        //         Anadir el producto a la lista.
        //         Serializar la lista actualizada.
        throw new UnsupportedOperationException("TODO 3 no implementado");
    }

    /**
     * Busca un producto por nombre en una lista serializada.
     *
     * @param ruta   ruta del fichero
     * @param nombre nombre del producto (case insensitive)
     * @return el Producto encontrado o null si no existe
     * @throws IOException            si hay error
     * @throws ClassNotFoundException si la clase no se encuentra
     */
    public static Producto buscarPorNombre(String ruta, String nombre)
            throws IOException, ClassNotFoundException {
        // TODO 4: Deserializar la lista. Buscar el producto cuyo nombre
        //         coincida (case insensitive). Devolver el primero o null.
        return null;
    }

    /**
     * Elimina un producto por nombre y guarda la lista actualizada.
     *
     * @param ruta   ruta del fichero
     * @param nombre nombre del producto a eliminar (case insensitive)
     * @return true si se elimino, false si no existia
     * @throws IOException            si hay error
     * @throws ClassNotFoundException si la clase no se encuentra
     */
    public static boolean eliminarPorNombre(String ruta, String nombre)
            throws IOException, ClassNotFoundException {
        // TODO 5: Deserializar. Buscar y eliminar. Serializar.
        //         Devolver true si se elimino.
        return false;
    }

    /**
     * Cuenta cuantos productos de la lista tienen stock > 0.
     *
     * @param ruta ruta del fichero
     * @return numero de productos con stock positivo
     * @throws IOException            si hay error
     * @throws ClassNotFoundException si la clase no se encuentra
     */
    public static int contarConStock(String ruta) throws IOException, ClassNotFoundException {
        // TODO 6: Deserializar. Recorrer y contar los que tienen stock > 0.
        return 0;
    }

    /**
     * Calcula el valor total del inventario (precio * stock por cada producto).
     *
     * @param ruta ruta del fichero
     * @return valor total
     * @throws IOException            si hay error
     * @throws ClassNotFoundException si la clase no se encuentra
     */
    public static double valorInventario(String ruta) throws IOException, ClassNotFoundException {
        // TODO 7: Deserializar. Sumar precio * stock de cada producto. Devolver total.
        return 0.0;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws Exception {
        System.out.println("=== Ejercicio 26: Serializar Lista ===\n");

        String dir = "temp/bloque5";
        new File(dir).mkdirs();

        List<Producto> inv = new ArrayList<>();
        inv.add(new Producto("Arroz", 1.20, 50));
        inv.add(new Producto("Aceite", 3.75, 20));
        inv.add(new Producto("Sal", 0.80, 0));

        serializarLista(dir + "/inventario.dat", inv);
        List<Producto> leido = deserializarLista(dir + "/inventario.dat");
        System.out.println("Inventario: " + leido);

        anadirProducto(dir + "/inventario.dat", new Producto("Pan", 1.50, 30));
        System.out.println("Buscando 'arroz': " + buscarPorNombre(dir + "/inventario.dat", "arroz"));
        System.out.println("Con stock: " + contarConStock(dir + "/inventario.dat"));
        System.out.printf("Valor inventario: %.2f EUR%n", valorInventario(dir + "/inventario.dat"));
    }
}
