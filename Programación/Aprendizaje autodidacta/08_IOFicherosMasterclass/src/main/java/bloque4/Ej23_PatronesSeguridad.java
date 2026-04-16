package bloque4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * EJERCICIO 23 — Patrones de Seguridad en I/O
 * Teoria: teoria/04_Gestion_Segura_Recursos.md (secciones 3, 6, trampas)
 *
 * Contexto: La clinica quiere garantizar que ninguna operacion de fichero
 * deje recursos abiertos, incluso si se producen errores. Implementaras
 * patrones seguros de lectura, escritura y transformacion.
 */
public class Ej23_PatronesSeguridad {

    /**
     * Escribe texto en un fichero de forma segura. Si la escritura falla,
     * el fichero parcial se elimina para no dejar basura.
     *
     * @param ruta  ruta del fichero
     * @param texto texto a escribir
     * @return true si la escritura fue exitosa
     */
    public static boolean escribirOLimpiar(String ruta, String texto) {
        // TODO 1: Usar try-with-resources con BufferedWriter.
        //         Escribir el texto. Devolver true.
        //         En catch, borrar el fichero con new File(ruta).delete().
        //         Devolver false.
        return false;
    }

    /**
     * Lee un fichero de forma segura. Si no existe o hay error, devuelve
     * un valor por defecto en lugar de lanzar excepcion.
     *
     * @param ruta          ruta del fichero
     * @param valorDefecto  valor a devolver si hay error
     * @return contenido del fichero o el valor por defecto
     */
    public static String leerConDefault(String ruta, String valorDefecto) {
        // TODO 2: Intentar leer con try-with-resources y BufferedReader.
        //         Si todo va bien, devolver el contenido.
        //         En catch, devolver valorDefecto.
        return valorDefecto;
    }

    /**
     * Copia un fichero de texto usando un patron "write to temp, then rename":
     * 1. Escribe en un fichero temporal (destino + ".tmp")
     * 2. Si la escritura es exitosa, renombra el temporal al destino
     * 3. Si falla, borra el temporal
     *
     * @param origen  fichero origen
     * @param destino fichero destino
     * @return true si la copia fue exitosa
     */
    public static boolean copiaAtomicaSimple(String origen, String destino) {
        // TODO 3: Definir rutaTmp = destino + ".tmp".
        //         Usar try-with-resources para leer origen y escribir en rutaTmp.
        //         Si todo va bien, renombrar rutaTmp a destino con File.renameTo().
        //         En catch, borrar rutaTmp. Devolver false.
        return false;
    }

    /**
     * Procesa un fichero linea a linea aplicando una transformacion simple
     * (pasar a mayusculas). Si alguna linea es null o vacia, la salta.
     * Escribe el resultado en un fichero destino de forma segura.
     *
     * @param origen  fichero origen
     * @param destino fichero destino
     * @return numero de lineas escritas
     * @throws IOException si hay error irrecuperable
     */
    public static int procesarSeguro(String origen, String destino) throws IOException {
        // TODO 4: Usar try-with-resources con reader y writer.
        //         Leer linea a linea. Si es null o vacia tras trim(), saltar.
        //         Convertir a mayusculas. Escribir con newLine().
        //         Devolver contador.
        return 0;
    }

    /**
     * Intenta leer multiples ficheros y devuelve el contenido del primero
     * que se pueda leer correctamente. Si ninguno funciona, devuelve null.
     *
     * @param rutas array de rutas a intentar
     * @return contenido del primer fichero legible o null
     */
    public static String leerPrimeroDisponible(String[] rutas) {
        // TODO 5: Recorrer rutas. Para cada una, intentar leer con
        //         try-with-resources. Si funciona, devolver contenido.
        //         Si falla (IOException), continuar con la siguiente.
        //         Si ninguna funciona, devolver null.
        return null;
    }

    /**
     * Escribe datos en un fichero y verifica que se escribieron correctamente
     * leyendo el fichero de vuelta y comparando.
     *
     * @param ruta  ruta del fichero
     * @param datos texto a escribir
     * @return true si la verificacion pasa
     */
    public static boolean escribirYVerificar(String ruta, String datos) {
        // TODO 6: Escribir con try-with-resources.
        //         Leer con otro try-with-resources.
        //         Comparar el contenido leido con el dato original.
        //         Si coinciden, devolver true. Si hay error, devolver false.
        return false;
    }

    /**
     * Cuenta cuantos ficheros de un array de rutas existen y son legibles.
     * Usa try-with-resources para intentar abrir cada uno.
     *
     * @param rutas array de rutas
     * @return array de 2: [existentes, noExistentes]
     */
    public static int[] auditarFicheros(String[] rutas) {
        // TODO 7: Recorrer rutas. Para cada una, intentar abrir BufferedReader.
        //         Si se abre sin excepcion, contar como existente.
        //         Si lanza IOException, contar como no existente.
        //         Devolver array {existentes, noExistentes}.
        return new int[]{0, 0};
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 23: Patrones de Seguridad ===\n");

        String dir = "temp/bloque4";
        new File(dir).mkdirs();

        boolean ok = escribirOLimpiar(dir + "/seguro.txt", "Datos seguros");
        System.out.println("Escritura OK: " + ok);

        String leido = leerConDefault(dir + "/noexiste.txt", "VACIO");
        System.out.println("Con default: " + leido);

        escribirOLimpiar(dir + "/orig.txt", "Contenido original");
        boolean copia = copiaAtomicaSimple(dir + "/orig.txt", dir + "/atomica.txt");
        System.out.println("Copia atomica OK: " + copia);

        boolean verificado = escribirYVerificar(dir + "/verif.txt", "Test verificacion");
        System.out.println("Verificado: " + verificado);

        int[] audit = auditarFicheros(new String[]{
                dir + "/seguro.txt", dir + "/noexiste.txt", dir + "/orig.txt"});
        System.out.println("Existentes: " + audit[0] + ", No: " + audit[1]);
    }
}
