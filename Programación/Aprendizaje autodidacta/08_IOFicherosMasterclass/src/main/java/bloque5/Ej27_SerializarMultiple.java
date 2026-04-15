package bloque5;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * EJERCICIO 27 — Serializar Multiples Objetos (lectura con EOFException)
 * Teoria: teoria/05_Serializacion.md (secciones 3-4, trampa 4)
 *
 * Contexto: Algunos sistemas escriben objetos de uno en uno (append logico)
 * y luego necesitan leerlos todos. Como no se sabe cuantos hay,
 * se usa el patron de lectura con EOFException.
 */
public class Ej27_SerializarMultiple {

    /**
     * Escribe multiples productos de uno en uno con ObjectOutputStream.
     *
     * @param ruta      ruta del fichero
     * @param productos array de productos
     * @throws IOException si hay error
     */
    public static void escribirUnoAUno(String ruta, Producto[] productos) throws IOException {
        // TODO 1: Crear ObjectOutputStream con try-with-resources.
        //         Escribir cada producto con writeObject() en un bucle.
        throw new UnsupportedOperationException("TODO 1 no implementado");
    }

    /**
     * Lee todos los productos de un fichero usando el patron EOFException.
     *
     * @param ruta ruta del fichero
     * @return lista de productos leidos
     * @throws IOException            si hay error distinto de EOF
     * @throws ClassNotFoundException si la clase no se encuentra
     */
    public static List<Producto> leerTodos(String ruta) throws IOException, ClassNotFoundException {
        // TODO 2: Crear ObjectInputStream. Leer en bucle infinito con readObject().
        //         Capturar EOFException para salir del bucle.
        //         Devolver la lista acumulada.
        return new ArrayList<>();
    }

    /**
     * Cuenta cuantos objetos hay en un fichero serializado.
     *
     * @param ruta ruta del fichero
     * @return numero de objetos
     * @throws IOException            si hay error
     * @throws ClassNotFoundException si la clase no se encuentra
     */
    public static int contarObjetos(String ruta) throws IOException, ClassNotFoundException {
        // TODO 3: Usar leerTodos() y devolver el tamano de la lista.
        return 0;
    }

    /**
     * Filtra productos de un fichero por precio maximo y guarda los filtrados
     * en otro fichero.
     *
     * @param rutaOrigen  fichero origen
     * @param rutaDestino fichero destino
     * @param precioMax   precio maximo
     * @return numero de productos que pasaron el filtro
     * @throws IOException            si hay error
     * @throws ClassNotFoundException si la clase no se encuentra
     */
    public static int filtrarPorPrecio(String rutaOrigen, String rutaDestino, double precioMax)
            throws IOException, ClassNotFoundException {
        // TODO 4: Leer todos los productos. Filtrar los que tienen precio <= precioMax.
        //         Escribir los filtrados en rutaDestino con ObjectOutputStream.
        //         Devolver el numero de filtrados.
        return 0;
    }

    /**
     * Devuelve el producto mas caro de un fichero serializado.
     *
     * @param ruta ruta del fichero
     * @return el producto con mayor precio, o null si el fichero esta vacio
     * @throws IOException            si hay error
     * @throws ClassNotFoundException si la clase no se encuentra
     */
    public static Producto masCaro(String ruta) throws IOException, ClassNotFoundException {
        // TODO 5: Leer todos. Buscar el de mayor precio.
        //         Devolver null si la lista esta vacia.
        return null;
    }

    /**
     * Actualiza el stock de un producto por nombre. Si no existe, no hace nada.
     * Lee la lista, modifica el stock y vuelve a guardar.
     *
     * @param ruta      ruta del fichero
     * @param nombre    nombre del producto (case insensitive)
     * @param nuevoStock nuevo stock
     * @return true si se encontro y actualizo
     * @throws IOException            si hay error
     * @throws ClassNotFoundException si la clase no se encuentra
     */
    public static boolean actualizarStock(String ruta, String nombre, int nuevoStock)
            throws IOException, ClassNotFoundException {
        // TODO 6: Leer todos. Buscar por nombre. Si existe, setStock().
        //         Guardar la lista de nuevo. Devolver true si se modifico.
        return false;
    }

    /**
     * Genera un resumen del fichero serializado:
     * "Productos: [n] | Valor total: [v] | Media precio: [m]"
     *
     * @param ruta ruta del fichero
     * @return resumen formateado
     * @throws IOException            si hay error
     * @throws ClassNotFoundException si la clase no se encuentra
     */
    public static String resumen(String ruta) throws IOException, ClassNotFoundException {
        // TODO 7: Leer todos. Calcular total, valor (precio*stock) y media de precios.
        //         Formatear con String.format.
        return "";
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws Exception {
        System.out.println("=== Ejercicio 27: Serializar Multiple ===\n");

        String dir = "temp/bloque5";
        new File(dir).mkdirs();

        Producto[] ps = {
            new Producto("Arroz", 1.20, 50),
            new Producto("Aceite", 3.75, 20),
            new Producto("Sal", 0.80, 100)
        };
        escribirUnoAUno(dir + "/multi.dat", ps);

        List<Producto> leidos = leerTodos(dir + "/multi.dat");
        System.out.println("Leidos: " + leidos);
        System.out.println("Objetos: " + contarObjetos(dir + "/multi.dat"));
        System.out.println("Mas caro: " + masCaro(dir + "/multi.dat"));
        System.out.println(resumen(dir + "/multi.dat"));
    }
}
