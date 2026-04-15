package bloque3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * EJERCICIO 15 — PrintWriter: Escritura Formateada
 * Teoria: teoria/03_Bufferizacion.md (seccion 5)
 *
 * Contexto: El restaurante necesita generar informes y logs formateados
 * que incluyan fechas, numeros decimales y tablas alineadas.
 * PrintWriter facilita esta tarea con println() y printf().
 */
public class Ej15_PrintWriterFormateado {

    /**
     * Escribe un log de operaciones usando PrintWriter.
     * Cada entrada tiene formato: "[timestamp] [nivel] [mensaje]"
     *
     * @param ruta      ruta del fichero
     * @param mensajes  array de mensajes
     * @param niveles   array de niveles ("INFO", "WARN", "ERROR")
     * @throws IOException              si hay error de escritura
     * @throws IllegalArgumentException si los arrays tienen distinta longitud
     */
    public static void escribirLog(String ruta, String[] mensajes, String[] niveles) throws IOException {
        // TODO 1: Validar longitudes. Crear PrintWriter envolviendo BufferedWriter+FileWriter.
        //         Para cada mensaje, usar printf con formato:
        //         "[%03d] %-5s %s%n" donde 03d es el indice (1-indexed).
        //         Cerrar writer.
        throw new UnsupportedOperationException("TODO 1 no implementado");
    }

    /**
     * Genera una tabla de inventario formateada con PrintWriter.
     * Cabecera: "| %-20s | %6s | %10s |"
     * Filas:    "| %-20s | %6d | %10.2f |"
     *
     * @param ruta       ruta del fichero
     * @param productos  nombres de productos
     * @param cantidades cantidades en stock
     * @param precios    precios unitarios
     * @throws IOException              si hay error de escritura
     * @throws IllegalArgumentException si los arrays tienen distintas longitudes
     */
    public static void generarInventario(String ruta, String[] productos,
                                          int[] cantidades, double[] precios) throws IOException {
        // TODO 2: Validar longitudes. Crear PrintWriter.
        //         Escribir cabecera con "Producto", "Uds", "Precio".
        //         Escribir separador con "-".
        //         Escribir cada fila con printf.
        //         Escribir separador final. Cerrar writer.
        throw new UnsupportedOperationException("TODO 2 no implementado");
    }

    /**
     * Escribe un fichero usando println() para cada linea del array.
     *
     * @param ruta   ruta del fichero
     * @param lineas array de lineas
     * @throws IOException si hay error de escritura
     */
    public static void escribirConPrintln(String ruta, String[] lineas) throws IOException {
        // TODO 3: Crear PrintWriter. Usar println() para cada linea.
        //         Cerrar writer.
        throw new UnsupportedOperationException("TODO 3 no implementado");
    }

    /**
     * Genera un informe de ventas diarias.
     * Formato:
     * === INFORME DE VENTAS ===
     * Fecha: [fecha]
     * Articulos vendidos: [total]
     * Ingreso total: [suma] EUR
     * Ticket medio: [media] EUR
     *
     * @param ruta    ruta del fichero
     * @param fecha   fecha como String
     * @param ventas  array de importes de cada venta
     * @throws IOException si hay error de escritura
     */
    public static void informeVentas(String ruta, String fecha, double[] ventas) throws IOException {
        // TODO 4: Crear PrintWriter. Calcular total e ingreso.
        //         Calcular ticket medio (ingreso/total, o 0 si no hay ventas).
        //         Usar printf/println para escribir con el formato indicado.
        //         Cerrar writer.
        throw new UnsupportedOperationException("TODO 4 no implementado");
    }

    /**
     * Anade entradas al log en modo append, sin borrar el contenido anterior.
     *
     * @param ruta     ruta del fichero
     * @param mensajes nuevos mensajes a anadir
     * @throws IOException si hay error de escritura
     */
    public static void anadirAlLog(String ruta, String[] mensajes) throws IOException {
        // TODO 5: Crear PrintWriter con FileWriter en modo append.
        //         Usar println() para cada mensaje.
        //         Cerrar writer.
        throw new UnsupportedOperationException("TODO 5 no implementado");
    }

    /**
     * Genera un fichero con una tabla de multiplicar (del numero dado, del 1 al 10).
     * Formato por linea: "%d x %2d = %3d"
     *
     * @param ruta   ruta del fichero
     * @param numero numero base
     * @throws IOException si hay error de escritura
     */
    public static void tablaMultiplicar(String ruta, int numero) throws IOException {
        // TODO 6: Crear PrintWriter. Bucle de 1 a 10.
        //         Usar printf con el formato indicado.
        //         Cerrar writer.
        throw new UnsupportedOperationException("TODO 6 no implementado");
    }

    /**
     * Devuelve el numero de lineas que contendria un log con N mensajes,
     * teniendo en cuenta que cada mensaje es una linea.
     *
     * @param numMensajes numero de mensajes
     * @return numero de lineas (igual al numero de mensajes)
     */
    public static int lineasEsperadas(int numMensajes) {
        // TODO 7: Devolver el numero de mensajes (cada mensaje = 1 linea con println).
        return 0;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 15: PrintWriter Formateado ===\n");

        String dir = "temp/bloque3";
        new File(dir).mkdirs();

        escribirLog(dir + "/log.txt",
                new String[]{"Servidor iniciado", "Conexion recibida", "Error de BD"},
                new String[]{"INFO", "INFO", "ERROR"});
        System.out.println("Log escrito.");

        generarInventario(dir + "/inventario.txt",
                new String[]{"Arroz", "Aceite", "Sal"},
                new int[]{50, 20, 100},
                new double[]{1.20, 3.75, 0.80});
        System.out.println("Inventario generado.");

        informeVentas(dir + "/ventas.txt", "2024-01-15",
                new double[]{12.50, 8.00, 15.75, 22.00});
        System.out.println("Informe de ventas generado.");

        tablaMultiplicar(dir + "/tabla7.txt", 7);
        System.out.println("Tabla del 7 generada.");

        System.out.println("Lineas esperadas para 5 mensajes: " + lineasEsperadas(5));
    }
}
