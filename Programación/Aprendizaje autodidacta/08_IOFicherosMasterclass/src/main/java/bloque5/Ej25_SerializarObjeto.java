package bloque5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * EJERCICIO 25 — Serializar y Deserializar un Objeto
 * Teoria: teoria/05_Serializacion.md (secciones 2-4)
 *
 * Contexto: La tienda de alimentacion necesita guardar datos de sus productos
 * en disco para que persistan entre reinicios de la aplicacion.
 */
public class Ej25_SerializarObjeto {

    /**
     * Serializa un Producto a un fichero .dat.
     *
     * @param ruta     ruta del fichero destino
     * @param producto producto a serializar
     * @throws IOException si hay error de escritura
     */
    public static void serializar(String ruta, Producto producto) throws IOException {
        // TODO 1: Crear ObjectOutputStream envolviendo FileOutputStream con try-with-resources.
        //         Escribir el producto con writeObject().
        throw new UnsupportedOperationException("TODO 1 no implementado");
    }

    /**
     * Deserializa un Producto desde un fichero .dat.
     *
     * @param ruta ruta del fichero
     * @return el Producto deserializado
     * @throws IOException            si hay error de lectura
     * @throws ClassNotFoundException si la clase no se encuentra
     */
    public static Producto deserializar(String ruta) throws IOException, ClassNotFoundException {
        // TODO 2: Crear ObjectInputStream envolviendo FileInputStream con try-with-resources.
        //         Leer con readObject() y hacer cast a Producto.
        //         Devolver el producto.
        return null;
    }

    /**
     * Verifica si un Producto sobrevive al ciclo serializar-deserializar
     * comparando con equals().
     *
     * @param ruta     ruta temporal
     * @param producto producto a verificar
     * @return true si el producto se preserva tras serializar+deserializar
     * @throws IOException            si hay error de I/O
     * @throws ClassNotFoundException si la clase no se encuentra
     */
    public static boolean verificarIntegridad(String ruta, Producto producto)
            throws IOException, ClassNotFoundException {
        // TODO 3: Serializar el producto. Deserializar. Comparar con equals().
        return false;
    }

    /**
     * Serializa un producto y devuelve el tamano del fichero en bytes.
     *
     * @param ruta     ruta del fichero
     * @param producto producto a serializar
     * @return tamano del fichero en bytes
     * @throws IOException si hay error
     */
    public static long tamanoSerializado(String ruta, Producto producto) throws IOException {
        // TODO 4: Serializar. Obtener tamano con new File(ruta).length().
        return 0;
    }

    /**
     * Verifica si el campo transient se pierde al serializar.
     * Serializa un Producto con codigoInterno no null, deserializa y
     * comprueba si codigoInterno es null.
     *
     * @param ruta ruta temporal
     * @return true si el campo transient es null tras deserializar
     * @throws IOException            si hay error
     * @throws ClassNotFoundException si la clase no se encuentra
     */
    public static boolean transientSePierde(String ruta) throws IOException, ClassNotFoundException {
        // TODO 5: Crear Producto con codigoInterno="SEC-001".
        //         Serializar. Deserializar.
        //         Devolver true si getCodigoInterno() es null.
        return false;
    }

    /**
     * Intenta deserializar un fichero vacio o corrupto.
     * Devuelve "OK" si funciona, o el nombre de la excepcion si falla.
     *
     * @param ruta ruta del fichero
     * @return "OK" o nombre de la excepcion
     */
    public static String intentarDeserializar(String ruta) {
        // TODO 6: Intentar deserializar con try-catch.
        //         Si funciona, devolver "OK".
        //         Si lanza cualquier Exception, devolver e.getClass().getSimpleName().
        return "";
    }

    /**
     * Indica si Producto implementa Serializable.
     *
     * @return true si Producto es instancia de Serializable
     */
    public static boolean esSerializable() {
        // TODO 7: Crear un Producto y comprobar con instanceof java.io.Serializable.
        return false;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws Exception {
        System.out.println("=== Ejercicio 25: Serializar Objeto ===\n");

        String dir = "temp/bloque5";
        new File(dir).mkdirs();

        Producto p = new Producto("Arroz", 1.20, 50, "INT-100");
        serializar(dir + "/producto.dat", p);
        Producto p2 = deserializar(dir + "/producto.dat");
        System.out.println("Original: " + p);
        System.out.println("Deserializado: " + p2);
        System.out.println("Integridad: " + verificarIntegridad(dir + "/test.dat", p));
        System.out.println("Tamano: " + tamanoSerializado(dir + "/tam.dat", p) + " bytes");
        System.out.println("Transient se pierde: " + transientSePierde(dir + "/trans.dat"));
        System.out.println("Es Serializable: " + esSerializable());
    }
}
