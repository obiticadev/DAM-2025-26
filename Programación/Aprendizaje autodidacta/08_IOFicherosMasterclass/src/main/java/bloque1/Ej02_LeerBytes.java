package bloque1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * EJERCICIO 02 — Leer Bytes de un Fichero
 * Teoria: teoria/01_Flujos_De_Datos.md (secciones 1-3)
 *
 * Contexto: El almacen digital ya tiene ficheros binarios guardados.
 * Ahora necesitas abrir una tuberia de entrada para leer esos datos
 * y procesarlos en tu programa.
 */
public class Ej02_LeerBytes {

    /**
     * Lee todos los bytes de un fichero y los devuelve como array.
     * Usa lectura byte a byte con read().
     *
     * @param ruta ruta del fichero a leer
     * @return array con todos los bytes del fichero
     * @throws IOException si hay error de lectura o el fichero no existe
     */
    public static byte[] leerTodosBytes(String ruta) throws IOException {
        // TODO 1: Abrir FileInputStream. Obtener el tamano del fichero con
        //         new File(ruta).length() para crear el array del tamano exacto.
        //         Leer byte a byte con read() en un bucle hasta que devuelva -1.
        //         Cerrar el stream. Devolver el array.
        return null;
    }

    /**
     * Lee un unico byte en la posicion indicada del fichero (0-indexed).
     * Descarta todos los bytes anteriores leyendolos sin usarlos.
     *
     * @param ruta     ruta del fichero
     * @param posicion indice del byte a leer (0-indexed)
     * @return el byte en esa posicion (0-255)
     * @throws IOException              si hay error de lectura
     * @throws IllegalArgumentException si la posicion es negativa
     */
    public static int leerByteEnPosicion(String ruta, int posicion) throws IOException {
        // TODO 2: Validar que posicion >= 0. Abrir FileInputStream.
        //         Leer y descartar 'posicion' bytes con read() en un bucle.
        //         Leer un byte mas, que es el que queremos.
        //         Si se llega a EOF antes de la posicion, lanzar IOException.
        //         Cerrar stream y devolver el byte.
        return -1;
    }

    /**
     * Cuenta cuantos bytes del fichero tienen un valor concreto.
     *
     * @param ruta       ruta del fichero
     * @param valorBusca byte a buscar (0-255)
     * @return cantidad de veces que aparece ese byte
     * @throws IOException si hay error de lectura
     */
    public static int contarByte(String ruta, int valorBusca) throws IOException {
        // TODO 3: Abrir FileInputStream. Leer byte a byte.
        //         Contar cuantas veces el byte leido coincide con valorBusca.
        //         Cerrar stream y devolver el contador.
        return 0;
    }

    /**
     * Lee los primeros n bytes de un fichero. Si el fichero tiene menos
     * de n bytes, devuelve solo los que haya.
     *
     * @param ruta ruta del fichero
     * @param n    cantidad maxima de bytes a leer
     * @return array con los bytes leidos (puede tener menos de n elementos)
     * @throws IOException              si hay error de lectura
     * @throws IllegalArgumentException si n es negativo
     */
    public static byte[] leerPrimeros(String ruta, int n) throws IOException {
        // TODO 4: Validar n >= 0. Abrir FileInputStream.
        //         Crear buffer temporal de tamano n.
        //         Leer con read(byte[], 0, n) — atencion: puede devolver menos de n.
        //         Crear array resultado del tamano exacto leido con Arrays.copyOf.
        //         Cerrar stream y devolver el array.
        return null;
    }

    /**
     * Compara dos ficheros byte a byte y devuelve true si son identicos.
     *
     * @param ruta1 ruta del primer fichero
     * @param ruta2 ruta del segundo fichero
     * @return true si ambos ficheros tienen el mismo contenido byte a byte
     * @throws IOException si hay error de lectura
     */
    public static boolean ficherosSonIguales(String ruta1, String ruta2) throws IOException {
        // TODO 5: Si los tamanos son distintos, devolver false directamente.
        //         Abrir DOS FileInputStream (uno por fichero).
        //         Leer un byte de cada uno en paralelo y comparar.
        //         Si algun par difiere, cerrar ambos y devolver false.
        //         Si se llega al final sin diferencias, devolver true.
        //         Cerrar ambos streams en todos los casos.
        return false;
    }

    /**
     * Devuelve un String con la representacion hexadecimal de cada byte del fichero,
     * separados por espacio. Ejemplo: "48 6F 6C 61" para un fichero con "Hola".
     *
     * @param ruta ruta del fichero
     * @return representacion hexadecimal del contenido
     * @throws IOException si hay error de lectura
     */
    public static String volcarHexadecimal(String ruta) throws IOException {
        // TODO 6: Abrir FileInputStream. Usar StringBuilder.
        //         Por cada byte leido, formatearlo con String.format("%02X", b).
        //         Separar con espacio ENTRE bytes (no al final).
        //         Cerrar stream y devolver el String.
        return "";
    }

    /**
     * Devuelve true si el fichero existe y tiene al menos 1 byte.
     *
     * @param ruta ruta del fichero
     * @return true si existe y no esta vacio
     */
    public static boolean existeYNoVacio(String ruta) {
        // TODO 7: Crear File con la ruta. Comprobar exists() y length() > 0.
        return false;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 02: Leer Bytes ===\n");

        String dir = "temp/bloque1";
        new File(dir).mkdirs();

        // Crear fichero de prueba
        FileOutputStream fos = new FileOutputStream(dir + "/prueba.bin");
        fos.write(new byte[]{72, 111, 108, 97, 33}); // "Hola!"
        fos.close();

        System.out.println("1. Leer todos los bytes:");
        byte[] todos = leerTodosBytes(dir + "/prueba.bin");
        if (todos != null) {
            for (byte b : todos) System.out.print(b + " ");
            System.out.println();
        }

        System.out.println("2. Byte en posicion 2: " + leerByteEnPosicion(dir + "/prueba.bin", 2));
        System.out.println("3. Contar byte 111: " + contarByte(dir + "/prueba.bin", 111));

        System.out.println("4. Primeros 3 bytes:");
        byte[] primeros = leerPrimeros(dir + "/prueba.bin", 3);
        if (primeros != null) {
            for (byte b : primeros) System.out.print(b + " ");
            System.out.println();
        }

        System.out.println("5. Ficheros iguales: " + ficherosSonIguales(dir + "/prueba.bin", dir + "/prueba.bin"));
        System.out.println("6. Volcado hex: " + volcarHexadecimal(dir + "/prueba.bin"));
        System.out.println("7. Existe y no vacio: " + existeYNoVacio(dir + "/prueba.bin"));
    }
}
