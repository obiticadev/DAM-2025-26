package bloque3b;

import java.io.*;

/**
 * EJERCICIO 45 — Leer Datos Primitivos desde un Archivo Binario
 * 📋 ENTRA EN EXAMEN — Tema 05 (DataInputStream)
 * Teoria: teoria/03B_ArchivosBinarios.md (secciones 4-5)
 *
 * Contexto: Continuando con la gestion de empleados, ahora necesitas leer
 * los datos que guardaste en el ejercicio anterior.
 */
public class Ej45_LeerDatosBinarios {

    /**
     * Lee un entero desde un fichero binario.
     */
    public static int leerEntero(String ruta) throws IOException {
        // TODO 1: Crear DataInputStream envolviendo FileInputStream con try-with-resources.
        //         Leer con readInt() y devolver el valor.
        return 0;
    }

    /**
     * Lee un double desde un fichero binario.
     */
    public static double leerDouble(String ruta) throws IOException {
        // TODO 2: Crear DataInputStream. Leer con readDouble() y devolver.
        return 0.0;
    }

    /**
     * Lee un registro completo (int + double + boolean + String) y lo devuelve como String formateado.
     * Formato: "ID=id, Salario=salario, Activo=activo, Nombre=nombre"
     */
    public static String leerRegistro(String ruta) throws IOException {
        // TODO 3: Crear DataInputStream. Leer en MISMO ORDEN que se escribio:
        //         readInt(), readDouble(), readBoolean(), readUTF().
        //         Devolver String formateado.
        return "";
    }

    /**
     * Cuenta cuantos registros completos hay en un fichero de empleados.
     * Cada registro: int(4) + double(8) + boolean(1) + UTF(variable).
     * Lee hasta que se lance EOFException.
     */
    public static int contarRegistros(String ruta) throws IOException {
        // TODO 4: Crear DataInputStream. En un bucle infinito, leer un registro
        //         completo (readInt, readDouble, readBoolean, readUTF).
        //         Contar cada lectura exitosa. Al llegar EOFException, devolver el contador.
        return 0;
    }

    /**
     * Lee un boolean desde un fichero binario.
     */
    public static boolean leerBoolean(String ruta) throws IOException {
        // TODO 5: Crear DataInputStream. Leer con readBoolean() y devolver.
        return false;
    }

    /**
     * Lee un String UTF desde un fichero binario.
     */
    public static String leerUTF(String ruta) throws IOException {
        // TODO 6: Crear DataInputStream. Leer con readUTF() y devolver.
        return "";
    }

    /**
     * Intenta leer un entero de un fichero vacio o corrupto.
     * Devuelve el nombre de la excepcion que se lanza, o "OK" si funciona.
     */
    public static String intentarLeer(String ruta) {
        // TODO 7: Intentar leer con DataInputStream + readInt().
        //         Si funciona, devolver "OK".
        //         Si lanza Exception, devolver e.getClass().getSimpleName().
        return "";
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 45: Leer Datos Binarios ===\n");

        String dir = "temp/bloque3b";

        System.out.println("Entero: " + leerEntero(dir + "/entero.bin"));
        System.out.println("Double: " + leerDouble(dir + "/decimal.bin"));
        System.out.println("Registro: " + leerRegistro(dir + "/registro.bin"));
        System.out.println("Total registros empleados: " + contarRegistros(dir + "/empleados.bin"));
    }
}
