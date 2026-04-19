package bloque3c;

import java.io.*;

/**
 * EJERCICIO 53 — Registros Estructurados de Empleados con RAF
 * 📋 ENTRA EN EXAMEN — Tema 06 (registros de tamano fijo, seek)
 * Teoria: teoria/03C_AccesoAleatorio.md (seccion 9)
 *
 * Contexto: Una base de datos simple almacena empleados con registros
 * de tamano fijo: ID (int, 4 bytes) + Salario (double, 8 bytes) = 12 bytes.
 */
public class Ej53_RegistrosEmpleados {

    public static final int TAM_REGISTRO = Integer.BYTES + Double.BYTES; // 12

    /**
     * Escribe un registro de empleado en la posicion indicada.
     */
    public static void escribirEmpleado(String ruta, int indice, int id, double salario) throws IOException {
        // TODO 1: Abrir "rw". seek(indice * TAM_REGISTRO). writeInt(id). writeDouble(salario).
        throw new UnsupportedOperationException("TODO 1 no implementado");
    }

    /**
     * Lee un empleado de la posicion indicada. Devuelve "ID=id, Salario=salario".
     */
    public static String leerEmpleado(String ruta, int indice) throws IOException {
        // TODO 2: Abrir "r". seek(indice * TAM_REGISTRO). readInt(), readDouble().
        //         Devolver String formateado.
        return "";
    }

    /**
     * Escribe N empleados: ID=i+1, Salario=2000.0+i*500.
     */
    public static void generarEmpleados(String ruta, int n) throws IOException {
        // TODO 3: Abrir "rw". setLength(0). Bucle de 0 a n-1.
        //         writeInt(i+1), writeDouble(2000.0 + i*500).
        throw new UnsupportedOperationException("TODO 3 no implementado");
    }

    /**
     * Devuelve cuantos empleados hay en el fichero.
     */
    public static int contarEmpleados(String ruta) throws IOException {
        // TODO 4: Abrir "r". Devolver (int)(length() / TAM_REGISTRO).
        return 0;
    }

    /**
     * Actualiza el salario de un empleado sin modificar su ID.
     */
    public static void actualizarSalario(String ruta, int indice, double nuevoSalario) throws IOException {
        // TODO 5: Abrir "rw". seek(indice * TAM_REGISTRO + Integer.BYTES) para
        //         saltar el ID y posicionar en el double. writeDouble(nuevoSalario).
        throw new UnsupportedOperationException("TODO 5 no implementado");
    }

    /**
     * Encuentra el indice del empleado con mayor salario.
     */
    public static int indiceMaxSalario(String ruta) throws IOException {
        // TODO 6: Abrir "r". Leer todos los registros. Comparar salarios.
        //         Devolver el indice del mayor.
        return -1;
    }

    /**
     * Lee todos los empleados y los devuelve como array de Strings.
     */
    public static String[] leerTodosEmpleados(String ruta) throws IOException {
        // TODO 7: Abrir "r". Calcular cantidad. Crear array.
        //         seek(0). Bucle: readInt, readDouble. Formatear y guardar.
        return new String[0];
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 53: Registros Empleados RAF ===\n");

        String dir = "temp/bloque3c";
        new File(dir).mkdirs();
        String ruta = dir + "/empleados.dat";

        generarEmpleados(ruta, 5);
        System.out.println("Empleados generados: " + contarEmpleados(ruta));
        for (String e : leerTodosEmpleados(ruta)) System.out.println("  " + e);
        System.out.println("Max salario en indice: " + indiceMaxSalario(ruta));
    }
}
