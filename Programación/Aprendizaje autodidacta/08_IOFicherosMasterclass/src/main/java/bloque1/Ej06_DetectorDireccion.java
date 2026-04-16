package bloque1;

/**
 * EJERCICIO 06 — Detector de Direccion y Tipo de Flujo
 * Teoria: teoria/01_Flujos_De_Datos.md (secciones 1-6)
 *
 * Contexto: El jefe del almacen quiere un modulo de diagnostico que, dado
 * un escenario descrito como texto, determine automaticamente que tipo de
 * stream se necesita. Este ejercicio es conceptual y refuerza la teoria
 * antes de pasar al siguiente bloque.
 */
public class Ej06_DetectorDireccion {

    /**
     * Dado un escenario, devuelve "INPUT" si el programa va a LEER datos,
     * o "OUTPUT" si va a ESCRIBIR datos.
     *
     * Escenarios validos (case insensitive):
     *   - "leer", "cargar", "importar", "recibir", "abrir" -> "INPUT"
     *   - "escribir", "guardar", "exportar", "enviar", "crear" -> "OUTPUT"
     *
     * @param escenario descripcion del escenario
     * @return "INPUT" o "OUTPUT"
     * @throws IllegalArgumentException si el escenario no coincide con ninguno conocido
     */
    public static String detectarDireccion(String escenario) {
        // TODO 1: Convertir escenario a minusculas con toLowerCase().
        //         Comprobar si contiene alguna de las palabras clave de input o output.
        //         Devolver "INPUT" u "OUTPUT" segun corresponda.
        //         Si no coincide con ninguna, lanzar IllegalArgumentException.
        throw new UnsupportedOperationException("TODO 1 no implementado");
    }

    /**
     * Dada una extension de fichero, devuelve "BYTES" si es binario
     * o "CARACTERES" si es texto.
     *
     * Extensiones de texto: "txt", "csv", "html", "xml", "json", "md"
     * Extensiones binarias: "jpg", "png", "gif", "mp3", "mp4", "dat", "bin", "pdf"
     *
     * @param extension extension sin punto (ej: "txt", "jpg")
     * @return "BYTES" o "CARACTERES"
     * @throws IllegalArgumentException si la extension no es reconocida
     */
    public static String detectarTipo(String extension) {
        // TODO 2: Convertir extension a minusculas.
        //         Comprobar contra las listas conocidas.
        //         Devolver "BYTES" o "CARACTERES".
        //         Si no se reconoce, lanzar IllegalArgumentException.
        throw new UnsupportedOperationException("TODO 2 no implementado");
    }

    /**
     * Dada la direccion ("INPUT"/"OUTPUT") y el tipo ("BYTES"/"CARACTERES"),
     * devuelve el nombre de la clase Java basica recomendada.
     *
     * - INPUT + BYTES -> "FileInputStream"
     * - INPUT + CARACTERES -> "FileReader"
     * - OUTPUT + BYTES -> "FileOutputStream"
     * - OUTPUT + CARACTERES -> "FileWriter"
     *
     * @param direccion "INPUT" o "OUTPUT"
     * @param tipo      "BYTES" o "CARACTERES"
     * @return nombre de la clase recomendada
     * @throws IllegalArgumentException si la combinacion no es valida
     */
    public static String recomendarClase(String direccion, String tipo) {
        // TODO 3: Usar if-else o switch para mapear las 4 combinaciones.
        //         Si la combinacion no es valida, lanzar IllegalArgumentException.
        throw new UnsupportedOperationException("TODO 3 no implementado");
    }

    /**
     * Dado un escenario y una extension, devuelve una recomendacion completa:
     * "Escenario: [escenario] | Fichero: [ext] | Clase: [clase]"
     *
     * @param escenario descripcion del escenario
     * @param extension extension del fichero
     * @return recomendacion formateada
     */
    public static String recomendacionCompleta(String escenario, String extension) {
        // TODO 4: Usar detectarDireccion, detectarTipo y recomendarClase.
        //         Formatear el resultado con String.format.
        return "";
    }

    /**
     * Dado un array de pares {escenario, extension}, devuelve un informe
     * con una linea por par, usando recomendacionCompleta.
     * Las lineas se separan con salto de linea.
     *
     * @param casos array bidimensional donde cada fila es {escenario, extension}
     * @return informe completo
     */
    public static String informeLote(String[][] casos) {
        // TODO 5: Usar StringBuilder. Recorrer el array.
        //         Para cada par, llamar a recomendacionCompleta.
        //         Separar con "\n" entre lineas (no al final).
        return "";
    }

    /**
     * Devuelve true si la extension proporcionada es de texto.
     *
     * @param extension extension sin punto
     * @return true si es texto, false si es binario
     * @throws IllegalArgumentException si la extension no es reconocida
     */
    public static boolean esTexto(String extension) {
        // TODO 6: Reutilizar detectarTipo y comparar con "CARACTERES".
        return false;
    }

    /**
     * Devuelve un String con la jerarquia de clases para una direccion y tipo dados.
     * Formato:
     *   Para INPUT + BYTES: "InputStream (abstracta) -> FileInputStream (concreta)"
     *   Para INPUT + CARACTERES: "Reader (abstracta) -> FileReader (concreta)"
     *   Para OUTPUT + BYTES: "OutputStream (abstracta) -> FileOutputStream (concreta)"
     *   Para OUTPUT + CARACTERES: "Writer (abstracta) -> FileWriter (concreta)"
     *
     * @param direccion "INPUT" o "OUTPUT"
     * @param tipo      "BYTES" o "CARACTERES"
     * @return representacion de la jerarquia
     */
    public static String mostrarJerarquia(String direccion, String tipo) {
        // TODO 7: Mapear cada combinacion a su jerarquia correspondiente.
        //         Usar recomendarClase para obtener la clase concreta.
        //         Construir la cadena con el formato indicado.
        return "";
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 06: Detector de Direccion ===\n");

        System.out.println("Direccion 'leer datos': " + detectarDireccion("leer datos"));
        System.out.println("Direccion 'guardar informe': " + detectarDireccion("guardar informe"));

        System.out.println("Tipo 'txt': " + detectarTipo("txt"));
        System.out.println("Tipo 'jpg': " + detectarTipo("jpg"));

        System.out.println("Clase INPUT+BYTES: " + recomendarClase("INPUT", "BYTES"));
        System.out.println("Clase OUTPUT+CARACTERES: " + recomendarClase("OUTPUT", "CARACTERES"));

        String[][] casos = {
                {"leer inventario", "csv"},
                {"guardar imagen", "jpg"},
                {"importar config", "json"},
                {"exportar backup", "dat"}
        };
        System.out.println("\nInforme por lotes:");
        System.out.println(informeLote(casos));

        System.out.println("\nEs texto 'html': " + esTexto("html"));
        System.out.println("Jerarquia INPUT+BYTES: " + mostrarJerarquia("INPUT", "BYTES"));
    }
}
