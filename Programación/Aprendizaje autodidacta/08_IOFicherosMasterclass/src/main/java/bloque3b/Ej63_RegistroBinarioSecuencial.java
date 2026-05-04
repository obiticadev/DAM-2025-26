package bloque3b;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * EJERCICIO 63 — Registro Binario Secuencial con EOFException
 * 📋 ENTRA EN EXAMEN — Pilar 2 (Ficheros Binarios) + Pilar 3 (Acceso Secuencial)
 * Teoria: teoria/03B_ArchivosBinarios.md (secciones 3-5)
 *
 * Contexto: Una empresa almacena registros de empleados en un fichero binario.
 * Cada registro contiene: ID (int), salario (double), activo (boolean), nombre (UTF).
 * El programa debe leer TODOS los registros secuencialmente (de principio a fin)
 * usando el patrón while(true) + EOFException, SIN usar seek() ni RandomAccessFile.
 *
 * Este ejercicio refuerza:
 *   - Escritura con DataOutputStream (orden de campos)
 *   - Lectura con DataInputStream (mismo orden OBLIGATORIO)
 *   - Detección de fin de fichero con EOFException
 *   - Descarte selectivo de campos al leer
 */
public class Ej63_RegistroBinarioSecuencial {

    /**
     * Representa un registro de empleado leído del fichero binario.
     */
    public record Empleado(int id, double salario, boolean activo, String nombre) {}

    /**
     * Escribe una lista de empleados en un fichero binario usando DataOutputStream.
     * Formato por registro: writeInt(id) + writeDouble(salario) + writeBoolean(activo) + writeUTF(nombre).
     *
     * @param ruta      ruta del fichero binario
     * @param empleados lista de empleados a escribir
     * @throws IOException si hay error de escritura
     */
    public static void escribirEmpleados(String ruta, List<Empleado> empleados) throws IOException {
        // TODO 1: Crear DataOutputStream(new FileOutputStream(ruta)) con try-with-resources.
        //         Iterar la lista y escribir cada campo EN ORDEN: id, salario, activo, nombre.
        throw new UnsupportedOperationException("TODO 1 no implementado");
    }

    /**
     * Lee TODOS los empleados de un fichero binario secuencialmente.
     * Usa el patrón while(true) + EOFException para detectar el final.
     *
     * @param ruta ruta del fichero binario
     * @return lista de todos los empleados leídos
     * @throws IOException si hay error de lectura (distinto de EOF)
     */
    public static List<Empleado> leerTodosEmpleados(String ruta) throws IOException {
        // TODO 2: Crear DataInputStream(new FileInputStream(ruta)) con try-with-resources.
        //         En un while(true), leer los 4 campos EN EL MISMO ORDEN que se escribieron.
        //         Crear un Empleado y añadirlo a la lista.
        //         Capturar EOFException para terminar el bucle (es normal, no un error).
        throw new UnsupportedOperationException("TODO 2 no implementado");
    }

    /**
     * Lee solo los nombres de todos los empleados, descartando los demás campos.
     * Demuestra el "descarte manual": leer (y no usar) los campos que no interesan.
     *
     * @param ruta ruta del fichero binario
     * @return lista con solo los nombres
     * @throws IOException si hay error
     */
    public static List<String> leerSoloNombres(String ruta) throws IOException {
        // TODO 3: Abrir DataInputStream. En bucle while(true) + EOFException:
        //         - dis.readInt();     // descartar ID
        //         - dis.readDouble();  // descartar salario
        //         - dis.readBoolean(); // descartar activo
        //         - String nombre = dis.readUTF();  // este SÍ lo guardamos
        //         Añadir nombre a la lista.
        throw new UnsupportedOperationException("TODO 3 no implementado");
    }

    /**
     * Cuenta cuántos empleados activos hay en el fichero.
     *
     * @param ruta ruta del fichero binario
     * @return número de empleados con activo == true
     * @throws IOException si hay error
     */
    public static int contarActivos(String ruta) throws IOException {
        // TODO 4: Abrir DataInputStream. Leer secuencialmente todos los registros.
        //         Por cada registro, leer los 4 campos (en orden).
        //         Si activo es true, incrementar un contador.
        //         Usar EOFException para terminar. Devolver el contador.
        return -1;
    }

    /**
     * Calcula el salario medio de todos los empleados del fichero.
     *
     * @param ruta ruta del fichero binario
     * @return salario medio, o 0.0 si el fichero está vacío
     * @throws IOException si hay error
     */
    public static double salarioMedio(String ruta) throws IOException {
        // TODO 5: Leer todos los registros secuencialmente.
        //         Acumular la suma de salarios y contar registros.
        //         Devolver suma / conteo (o 0.0 si conteo == 0).
        return 0.0;
    }

    /**
     * Filtra los empleados activos y los escribe en un nuevo fichero binario.
     * Los empleados inactivos se descartan.
     *
     * @param rutaOrigen  ruta del fichero original
     * @param rutaDestino ruta del fichero filtrado
     * @return número de empleados escritos en el nuevo fichero
     * @throws IOException si hay error
     */
    public static int filtrarActivos(String rutaOrigen, String rutaDestino) throws IOException {
        // TODO 6: Abrir DataInputStream para leer y DataOutputStream para escribir.
        //         Leer cada registro completo. Si activo == true, escribirlo al destino.
        //         Usar EOFException para terminar la lectura. Devolver cuántos se escribieron.
        return -1;
    }

    /**
     * Devuelve el número total de registros en el fichero, sin cargarlos en memoria.
     *
     * @param ruta ruta del fichero binario
     * @return número de registros
     * @throws IOException si hay error
     */
    public static int contarRegistros(String ruta) throws IOException {
        // TODO 7: Abrir DataInputStream. Leer cada registro completo (descartando valores).
        //         Contar cuántos registros se pueden leer antes del EOFException.
        return -1;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCIÓN — Pulsa Run aquí
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 63: Registro Binario Secuencial ===\n");

        String dir = "temp/bloque3b";
        new File(dir).mkdirs();
        String ruta = dir + "/empleados_seq.bin";

        // Crear datos de prueba
        List<Empleado> empleados = List.of(
            new Empleado(1, 2500.00, true,  "Ana García"),
            new Empleado(2, 3200.50, false, "Luis Pérez"),
            new Empleado(3, 1800.75, true,  "María López"),
            new Empleado(4, 4100.00, true,  "Carlos Ruiz"),
            new Empleado(5, 2900.25, false, "Elena Díaz")
        );

        escribirEmpleados(ruta, empleados);
        System.out.println("Escritos: " + empleados.size() + " empleados");

        List<Empleado> leidos = leerTodosEmpleados(ruta);
        System.out.println("Leídos:   " + leidos.size() + " empleados");
        leidos.forEach(e -> System.out.println("  " + e));

        System.out.println("\nSolo nombres: " + leerSoloNombres(ruta));
        System.out.println("Activos:      " + contarActivos(ruta));
        System.out.printf("Salario medio: %.2f%n", salarioMedio(ruta));
        System.out.println("Total registros: " + contarRegistros(ruta));

        String rutaFiltrada = dir + "/activos.bin";
        int filtrados = filtrarActivos(ruta, rutaFiltrada);
        System.out.println("Filtrados (activos): " + filtrados);
    }
}
