package bloque3b;

import java.io.*;

/**
 * EJERCICIO 44 — Escribir Datos Primitivos en un Archivo Binario
 * 📋 ENTRA EN EXAMEN — Tema 05 (DataOutputStream)
 * Teoria: teoria/03B_ArchivosBinarios.md (secciones 3-4)
 *
 * Contexto: Una aplicacion de gestion de empleados necesita guardar datos
 * compactos (nombre, salario, activo) en un fichero binario para que
 * ocupen menos espacio que en texto.
 */
public class Ej44_EscribirDatosBinarios {

    /**
     * Escribe un entero en un fichero binario.
     *
     * @param ruta  ruta del fichero destino
     * @param valor entero a escribir
     * @throws IOException si hay error de escritura
     */
    public static void escribirEntero(String ruta, int valor) throws IOException {
        // TODO 1: Crear DataOutputStream envolviendo FileOutputStream con try-with-resources.
        //         Escribir el valor con writeInt().
        throw new UnsupportedOperationException("TODO 1 no implementado");
    }

    /**
     * Escribe un double en un fichero binario.
     *
     * @param ruta  ruta del fichero destino
     * @param valor double a escribir
     * @throws IOException si hay error de escritura
     */
    public static void escribirDouble(String ruta, double valor) throws IOException {
        // TODO 2: Crear DataOutputStream envolviendo FileOutputStream.
        //         Escribir el valor con writeDouble().
        throw new UnsupportedOperationException("TODO 2 no implementado");
    }

    /**
     * Escribe un registro completo: int + double + boolean + String (UTF).
     *
     * @param ruta    ruta del fichero
     * @param id      identificador entero
     * @param salario salario como double
     * @param activo  estado como boolean
     * @param nombre  nombre como String (UTF)
     * @throws IOException si hay error
     */
    public static void escribirRegistro(String ruta, int id, double salario,
                                         boolean activo, String nombre) throws IOException {
        // TODO 3: Crear DataOutputStream. Escribir en orden:
        //         writeInt(id), writeDouble(salario), writeBoolean(activo), writeUTF(nombre).
        throw new UnsupportedOperationException("TODO 3 no implementado");
    }

    /**
     * Escribe N registros de empleados con datos generados.
     * Cada registro: writeInt(i) + writeDouble(1000.0 + i*500) + writeBoolean(i%2==0) + writeUTF("Emp-"+i)
     *
     * @param ruta ruta del fichero
     * @param n    cantidad de registros
     * @throws IOException si hay error
     */
    public static void escribirMultiplesRegistros(String ruta, int n) throws IOException {
        // TODO 4: Crear DataOutputStream. En un bucle de 0 a n-1, escribir cada registro
        //         con los datos indicados en el Javadoc.
        throw new UnsupportedOperationException("TODO 4 no implementado");
    }

    /**
     * Escribe un boolean en un fichero binario.
     *
     * @param ruta  ruta del fichero
     * @param valor boolean a escribir
     * @throws IOException si hay error
     */
    public static void escribirBoolean(String ruta, boolean valor) throws IOException {
        // TODO 5: Crear DataOutputStream. Escribir con writeBoolean().
        throw new UnsupportedOperationException("TODO 5 no implementado");
    }

    /**
     * Escribe un String en formato UTF en un fichero binario.
     *
     * @param ruta  ruta del fichero
     * @param texto String a escribir
     * @throws IOException si hay error
     */
    public static void escribirUTF(String ruta, String texto) throws IOException {
        // TODO 6: Crear DataOutputStream. Escribir con writeUTF().
        throw new UnsupportedOperationException("TODO 6 no implementado");
    }

    /**
     * Devuelve el tamano en bytes del fichero.
     *
     * @param ruta ruta del fichero
     * @return tamano en bytes, o -1 si no existe
     */
    public static long obtenerTamano(String ruta) {
        // TODO 7: Crear File con la ruta. Si existe, devolver length(). Si no, -1.
        return -1;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 44: Escribir Datos Binarios ===\n");

        String dir = "temp/bloque3b";
        new File(dir).mkdirs();

        escribirEntero(dir + "/entero.bin", 42);
        System.out.println("Entero escrito. Tamano: " + obtenerTamano(dir + "/entero.bin") + " bytes");

        escribirDouble(dir + "/decimal.bin", 3.14159);
        System.out.println("Double escrito. Tamano: " + obtenerTamano(dir + "/decimal.bin") + " bytes");

        escribirRegistro(dir + "/registro.bin", 1, 2500.50, true, "Ana Garcia");
        System.out.println("Registro escrito. Tamano: " + obtenerTamano(dir + "/registro.bin") + " bytes");

        escribirMultiplesRegistros(dir + "/empleados.bin", 5);
        System.out.println("5 registros escritos. Tamano: " + obtenerTamano(dir + "/empleados.bin") + " bytes");
    }
}
