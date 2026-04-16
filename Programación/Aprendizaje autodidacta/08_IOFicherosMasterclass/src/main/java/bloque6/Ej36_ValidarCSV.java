package bloque6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * EJERCICIO 36 — Validar y Limpiar un CSV
 * Teoria: teoria/06_Procesamiento_CSV.md (secciones 6, trampas)
 *
 * Contexto: Los CSVs que recibe el supermercado a veces vienen con errores:
 * lineas con campos de mas/menos, valores no numericos, duplicados, etc.
 * Necesitas un validador que separe registros validos de invalidos.
 */
public class Ej36_ValidarCSV {

    /**
     * Valida cada linea de un CSV y separa en validos e invalidos.
     * Una linea es valida si tiene exactamente el mismo numero de campos
     * que la cabecera.
     *
     * @param rutaOrigen    CSV a validar
     * @param rutaValidos   CSV con registros validos
     * @param rutaInvalidos CSV con registros invalidos
     * @return array [validos, invalidos]
     * @throws IOException si hay error
     */
    public static int[] separarValidosInvalidos(String rutaOrigen, String rutaValidos,
                                                  String rutaInvalidos) throws IOException {
        // TODO 1: Leer cabecera. Contar columnas esperadas.
        //         Escribir cabecera en rutaValidos.
        //         Para cada linea: si tiene las columnas correctas -> validos.
        //         Si no -> invalidos. Devolver conteos.
        return new int[]{0, 0};
    }

    /**
     * Valida que los campos numericos de un CSV de productos (nombre;precio;stock)
     * son realmente numeros. Devuelve las lineas con errores de parseo.
     *
     * @param ruta ruta del CSV
     * @return lista de lineas con errores numericos
     * @throws IOException si hay error
     */
    public static List<String> lineasConErrorNumerico(String ruta) throws IOException {
        // TODO 2: Leer cada linea de datos. split(";").
        //         Intentar parsear campo[1] como double y campo[2] como int.
        //         Si falla (NumberFormatException), anadir la linea a la lista.
        return new ArrayList<>();
    }

    /**
     * Elimina lineas duplicadas de un CSV (por primer campo, case insensitive).
     * Mantiene la primera aparicion.
     *
     * @param rutaOrigen  CSV original
     * @param rutaDestino CSV sin duplicados
     * @return numero de duplicados eliminados
     * @throws IOException si hay error
     */
    public static int eliminarDuplicados(String rutaOrigen, String rutaDestino) throws IOException {
        // TODO 3: Leer cabecera, escribirla en destino.
        //         Usar un Set para rastrear claves ya vistas.
        //         Si la clave ya existe, incrementar contador.
        //         Si no, escribir la linea y anadir al Set.
        return 0;
    }

    /**
     * Limpia un CSV: hace trim() de todos los campos y elimina lineas vacias.
     *
     * @param rutaOrigen  CSV original
     * @param rutaDestino CSV limpio
     * @return numero de lineas en el resultado
     * @throws IOException si hay error
     */
    public static int limpiarCSV(String rutaOrigen, String rutaDestino) throws IOException {
        // TODO 4: Leer cada linea. Si esta vacia tras trim(), saltar.
        //         Si no, split(";"), trim() de cada campo.
        //         Unir con ";" y escribir.
        return 0;
    }

    /**
     * Comprueba que un CSV tiene cabecera con los nombres esperados.
     *
     * @param ruta             ruta del CSV
     * @param cabeceraEsperada array de nombres esperados
     * @param delimitador      delimitador del CSV
     * @return true si la cabecera coincide (trim + case insensitive)
     * @throws IOException si hay error
     */
    public static boolean verificarCabecera(String ruta, String[] cabeceraEsperada,
                                             String delimitador) throws IOException {
        // TODO 5: Leer primera linea. split(delimitador).
        //         Comparar cada campo (trim, toLowerCase) con la cabecera esperada.
        return false;
    }

    /**
     * Genera un informe de validacion del CSV.
     * Formato:
     * "Total lineas: [n]
     * Validas: [v]
     * Invalidas: [i]
     * Errores numericos: [e]"
     *
     * @param ruta ruta del CSV
     * @return informe
     * @throws IOException si hay error
     */
    public static String informeValidacion(String ruta) throws IOException {
        // TODO 6: Contar total de lineas (sin cabecera).
        //         Contar lineas con errores numericos.
        //         Calcular validas = total - invalidas.
        //         Formatear el informe.
        return "";
    }

    /**
     * Indica si una cadena es un numero valido (double).
     *
     * @param s cadena a verificar
     * @return true si se puede parsear como double
     */
    public static boolean esNumero(String s) {
        // TODO 7: Intentar Double.parseDouble(s.trim()).
        //         Si funciona, devolver true. Si lanza NumberFormatException, devolver false.
        return false;
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 36: Validar CSV ===\n");

        String dir = "temp/bloque6";
        new File(dir).mkdirs();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dir + "/dirty.csv"))) {
            bw.write("nombre;precio;stock\n");
            bw.write("Arroz;1.20;50\n");
            bw.write("Aceite;xxx;20\n");
            bw.write("Sal;0.80\n");           // falta un campo
            bw.write("Pan;1.50;30\n");
            bw.write("Arroz;1.20;50\n");       // duplicado
        }

        int[] sep = separarValidosInvalidos(dir + "/dirty.csv", dir + "/ok.csv", dir + "/bad.csv");
        System.out.println("Validos: " + sep[0] + ", Invalidos: " + sep[1]);

        System.out.println("Errores numericos: " + lineasConErrorNumerico(dir + "/dirty.csv"));
        System.out.println("Duplicados: " + eliminarDuplicados(dir + "/dirty.csv", dir + "/uniq.csv"));
        System.out.println("Es numero '3.14': " + esNumero("3.14"));
        System.out.println("Es numero 'abc': " + esNumero("abc"));
    }
}
