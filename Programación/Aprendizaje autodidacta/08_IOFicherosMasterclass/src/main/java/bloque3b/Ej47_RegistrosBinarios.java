package bloque3b;

import java.io.*;

/**
 * EJERCICIO 47 — Registros Binarios de Estructura Fija
 * 📋 ENTRA EN EXAMEN — Tema 05 (DataOutputStream + DataInputStream, orden estricto)
 * Teoria: teoria/03B_ArchivosBinarios.md (secciones 5, 8)
 *
 * Contexto: Un sistema de puntuaciones de videojuegos guarda los scores en formato
 * binario para maximizar la eficiencia. Cada registro tiene: nombre(UTF), puntos(int), nivel(short).
 */
public class Ej47_RegistrosBinarios {

    /**
     * Escribe un registro de puntuacion: nombre(UTF) + puntos(int) + nivel(short).
     */
    public static void escribirPuntuacion(String ruta, String nombre, int puntos, short nivel) throws IOException {
        // TODO 1: Crear DataOutputStream (modo append: new FileOutputStream(ruta, true)).
        //         Escribir writeUTF(nombre), writeInt(puntos), writeShort(nivel).
        throw new UnsupportedOperationException("TODO 1 no implementado");
    }

    /**
     * Lee todas las puntuaciones del fichero y las devuelve como array de Strings.
     * Formato de cada String: "nombre: puntos pts (nivel N)"
     */
    public static String[] leerTodasPuntuaciones(String ruta) throws IOException {
        // TODO 2: Crear DataInputStream. Usar un ArrayList para acumular.
        //         En bucle: readUTF, readInt, readShort. Formatear y anadir al ArrayList.
        //         Capturar EOFException para terminar. Convertir a array con toArray().
        return new String[0];
    }

    /**
     * Devuelve la puntuacion mas alta del fichero.
     */
    public static int puntuacionMaxima(String ruta) throws IOException {
        // TODO 3: Leer todos los registros. Comparar puntos (readInt) con un maximo.
        //         Devolver el maximo encontrado.
        return 0;
    }

    /**
     * Cuenta cuantos registros hay en el fichero.
     */
    public static int contarRegistros(String ruta) throws IOException {
        // TODO 4: Leer registros hasta EOFException. Contar cada lectura exitosa.
        return 0;
    }

    /**
     * Crea un fichero nuevo (no append) con N puntuaciones generadas.
     * Nombre: "Jugador-i", Puntos: i*100, Nivel: (short)(i+1)
     */
    public static void generarPuntuaciones(String ruta, int n) throws IOException {
        // TODO 5: Crear DataOutputStream (sin append). Bucle de 0 a n-1.
        //         Escribir cada registro con los datos indicados.
        throw new UnsupportedOperationException("TODO 5 no implementado");
    }

    /**
     * Busca si existe un jugador con el nombre dado. Devuelve true si lo encuentra.
     */
    public static boolean existeJugador(String ruta, String nombre) throws IOException {
        // TODO 6: Leer registros con DataInputStream. Para cada registro,
        //         comparar el nombre leido con readUTF() usando equals(). Si coincide, true.
        //         Si llega EOFException sin encontrarlo, false.
        return false;
    }

    /**
     * Devuelve el tamano total del fichero en bytes.
     */
    public static long tamanoFichero(String ruta) {
        // TODO 7: Crear File. Si existe, devolver length(). Si no, -1.
        return -1;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 47: Registros Binarios ===\n");

        String dir = "temp/bloque3b";
        new File(dir).mkdirs();
        String ruta = dir + "/scores.bin";

        generarPuntuaciones(ruta, 5);
        String[] scores = leerTodasPuntuaciones(ruta);
        for (String s : scores) System.out.println("  " + s);
        System.out.println("Maxima: " + puntuacionMaxima(ruta));
        System.out.println("Total registros: " + contarRegistros(ruta));
        System.out.println("Existe Jugador-2: " + existeJugador(ruta, "Jugador-2"));
    }
}
