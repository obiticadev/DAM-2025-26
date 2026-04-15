package bloque5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * EJERCICIO 30 — Comparar Serializacion Binaria vs Texto
 * Teoria: teoria/05_Serializacion.md (todo el bloque)
 *
 * Contexto: El restaurante evalua si guardar el inventario como
 * serializacion binaria (.dat) o como texto plano (.txt).
 * Comparas ambos enfoques: tamano, legibilidad y velocidad.
 */
public class Ej30_SerializarTextoVsBinario {

    /**
     * Guarda una lista de productos como texto plano.
     * Formato por linea: "nombre;precio;stock"
     *
     * @param ruta      ruta del fichero
     * @param productos lista de productos
     * @throws IOException si hay error
     */
    public static void guardarComoTexto(String ruta, List<Producto> productos) throws IOException {
        // TODO 1: Usar try-with-resources con BufferedWriter.
        //         Escribir cada producto como "nombre;precio;stock" con newLine().
        throw new UnsupportedOperationException("TODO 1 no implementado");
    }

    /**
     * Carga una lista de productos desde texto plano.
     *
     * @param ruta ruta del fichero
     * @return lista de productos
     * @throws IOException si hay error
     */
    public static List<Producto> cargarDesdeTexto(String ruta) throws IOException {
        // TODO 2: Usar try-with-resources con BufferedReader.
        //         Leer linea a linea, hacer split(";").
        //         Crear Producto con los campos parseados.
        //         Devolver la lista.
        return new ArrayList<>();
    }

    /**
     * Guarda una lista de productos como serializacion binaria.
     *
     * @param ruta      ruta del fichero
     * @param productos lista de productos
     * @throws IOException si hay error
     */
    public static void guardarComoBinario(String ruta, List<Producto> productos) throws IOException {
        // TODO 3: Usar ObjectOutputStream con try-with-resources.
        //         Escribir la lista con writeObject().
        throw new UnsupportedOperationException("TODO 3 no implementado");
    }

    /**
     * Carga una lista de productos desde serializacion binaria.
     *
     * @param ruta ruta del fichero
     * @return lista de productos
     * @throws IOException            si hay error
     * @throws ClassNotFoundException si la clase no se encuentra
     */
    @SuppressWarnings("unchecked")
    public static List<Producto> cargarDesdeBinario(String ruta)
            throws IOException, ClassNotFoundException {
        // TODO 4: Usar ObjectInputStream con try-with-resources.
        //         Leer con readObject() y hacer cast.
        return new ArrayList<>();
    }

    /**
     * Compara el tamano en bytes de ambos formatos para la misma lista.
     *
     * @param dir       directorio temporal
     * @param productos lista de productos
     * @return array de 2: [tamanoTexto, tamanoBinario]
     * @throws IOException si hay error
     */
    public static long[] compararTamano(String dir, List<Producto> productos) throws IOException {
        // TODO 5: Guardar como texto en dir+"/comp.txt".
        //         Guardar como binario en dir+"/comp.dat".
        //         Obtener tamanos con File.length().
        //         Devolver array con ambos tamanos.
        return new long[]{0, 0};
    }

    /**
     * Indica si el formato texto es legible para humanos.
     *
     * @return true (siempre, conceptual: el texto CSV es legible)
     */
    public static boolean textoEsLegible() {
        // TODO 6: Devolver true. Conceptual.
        return false;
    }

    /**
     * Genera un informe comparativo entre ambos formatos:
     * "Texto: [t] bytes, legible: SI
     * Binario: [b] bytes, legible: NO
     * Diferencia: [d] bytes"
     *
     * @param tamTexto    tamano del fichero texto
     * @param tamBinario  tamano del fichero binario
     * @return informe formateado
     */
    public static String informeComparativo(long tamTexto, long tamBinario) {
        // TODO 7: Formatear con String.format segun el modelo indicado.
        //         Diferencia = |tamTexto - tamBinario|.
        return "";
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws Exception {
        System.out.println("=== Ejercicio 30: Texto vs Binario ===\n");

        String dir = "temp/bloque5";
        new File(dir).mkdirs();

        List<Producto> inv = new ArrayList<>();
        inv.add(new Producto("Arroz", 1.20, 50));
        inv.add(new Producto("Aceite", 3.75, 20));
        inv.add(new Producto("Sal", 0.80, 100));

        guardarComoTexto(dir + "/inv.txt", inv);
        guardarComoBinario(dir + "/inv.dat", inv);

        List<Producto> dTexto = cargarDesdeTexto(dir + "/inv.txt");
        List<Producto> dBin = cargarDesdeBinario(dir + "/inv.dat");
        System.out.println("Desde texto: " + dTexto);
        System.out.println("Desde binario: " + dBin);

        long[] tam = compararTamano(dir, inv);
        System.out.println(informeComparativo(tam[0], tam[1]));
        System.out.println("Texto legible: " + textoEsLegible());
    }
}
