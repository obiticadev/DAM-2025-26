package bloque3c;

import java.io.*;

/**
 * EJERCICIO 55 — Mostrar Todos los Registros Secuencialmente
 * 📋 ENTRA EN EXAMEN — Tema 06 (recorrido secuencial con RAF)
 * Teoria: teoria/03C_AccesoAleatorio.md (secciones 4-6, 9)
 *
 * Contexto: Una herramienta de auditoria necesita mostrar todos los registros
 * de un fichero binario con formato legible.
 */
public class Ej55_MostrarTodos {

    /**
     * Muestra todos los enteros del fichero como String "indice: valor".
     */
    public static String[] mostrarEnteros(String ruta) throws IOException {
        // TODO 1: Abrir "r". Calcular cantidad. Crear array de Strings.
        //         seek(0). Bucle: readInt. Formatear "i: valor". Guardar en array.
        return new String[0];
    }

    /**
     * Muestra todos los registros de empleados (ID int + Salario double) formateados.
     */
    public static String[] mostrarEmpleados(String ruta) throws IOException {
        // TODO 2: Abrir "r". Calcular cantidad (length() / 12).
        //         Bucle: readInt, readDouble. Formatear "ID=id, Salario=salario".
        return new String[0];
    }

    /**
     * Devuelve la suma de todos los enteros del fichero.
     */
    public static long sumaEnteros(String ruta) throws IOException {
        // TODO 3: Abrir "r". seek(0). Leer todos los enteros y sumar.
        return 0;
    }

    /**
     * Devuelve la media de todos los enteros del fichero.
     */
    public static double mediaEnteros(String ruta) throws IOException {
        // TODO 4: Calcular suma y cantidad. Devolver suma / cantidad.
        return 0.0;
    }

    /**
     * Busca el valor minimo entre los enteros del fichero.
     */
    public static int minimoEntero(String ruta) throws IOException {
        // TODO 5: Abrir "r". Leer primer entero como minimo.
        //         Recorrer el resto comparando. Devolver el minimo.
        return 0;
    }

    /**
     * Busca el valor maximo entre los enteros del fichero.
     */
    public static int maximoEntero(String ruta) throws IOException {
        // TODO 6: Igual que TODO 5 pero buscando el maximo.
        return 0;
    }

    /**
     * Devuelve cuantos registros del fichero tienen un valor mayor al umbral dado.
     */
    public static int contarMayores(String ruta, int umbral) throws IOException {
        // TODO 7: Abrir "r". Recorrer todos. Contar los que sean > umbral.
        return 0;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 55: Mostrar Todos ===\n");

        String dir = "temp/bloque3c";
        String ruta = dir + "/enteros.dat";

        System.out.println("Enteros:");
        for (String s : mostrarEnteros(ruta)) System.out.println("  " + s);
        System.out.println("Suma: " + sumaEnteros(ruta));
        System.out.println("Media: " + mediaEnteros(ruta));
        System.out.println("Minimo: " + minimoEntero(ruta));
        System.out.println("Maximo: " + maximoEntero(ruta));
        System.out.println("Mayores que 50: " + contarMayores(ruta, 50));
    }
}
