package bloque2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * EJERCICIO 12 — Selector de Estrategia de I/O
 * Teoria: teoria/02_Texto_vs_Binario.md (secciones 4-6)
 *
 * Contexto: El hotel quiere un modulo inteligente que, dado un fichero,
 * determine automaticamente la mejor estrategia de lectura/escritura
 * y genere un informe de diagnostico.
 */
public class Ej12_SelectorEstrategia {

    /**
     * Dada una extension de fichero, devuelve la familia de clase recomendada.
     *
     * Texto: "txt", "csv", "html", "xml", "json", "md", "log"
     * Binario: "jpg", "png", "gif", "mp3", "mp4", "dat", "bin", "pdf", "zip"
     *
     * @param extension extension sin punto, en minusculas
     * @return "Reader/Writer" para texto, "InputStream/OutputStream" para binario
     * @throws IllegalArgumentException si la extension no es reconocida
     */
    public static String recomendarFamilia(String extension) {
        // TODO 1: Convertir a minusculas. Comprobar contra las listas.
        //         Devolver el String correspondiente.
        //         Si no reconoce la extension, lanzar IllegalArgumentException.
        throw new UnsupportedOperationException("TODO 1 no implementado");
    }

    /**
     * Dada una extension y una operacion ("leer" o "escribir"),
     * devuelve el nombre exacto de la clase Java recomendada.
     *
     * Ejemplo: ("txt", "leer") -> "FileReader"
     *          ("jpg", "escribir") -> "FileOutputStream"
     *
     * @param extension extension sin punto
     * @param operacion "leer" o "escribir"
     * @return nombre de la clase recomendada
     * @throws IllegalArgumentException si la extension o la operacion no son validas
     */
    public static String recomendarClase(String extension, String operacion) {
        // TODO 2: Determinar si es texto o binario con recomendarFamilia.
        //         Combinar con la operacion para devolver la clase correcta.
        //         Si operacion no es "leer" ni "escribir", lanzar excepcion.
        throw new UnsupportedOperationException("TODO 2 no implementado");
    }

    /**
     * Lee un fichero de texto y devuelve su contenido como String.
     * Este metodo usa FileReader internamente.
     *
     * @param ruta ruta del fichero
     * @return contenido del fichero
     * @throws IOException si hay error de lectura
     */
    public static String leerComoTexto(String ruta) throws IOException {
        // TODO 3: Crear FileReader. Leer caracter a caracter con StringBuilder.
        //         Cerrar reader. Devolver String.
        return "";
    }

    /**
     * Lee un fichero binario y devuelve su contenido como byte[].
     * Este metodo usa FileInputStream internamente.
     *
     * @param ruta ruta del fichero
     * @return contenido como array de bytes
     * @throws IOException si hay error de lectura
     */
    public static byte[] leerComoBinario(String ruta) throws IOException {
        // TODO 4: Obtener tamano con File.length(). Crear byte[].
        //         Abrir FileInputStream. Leer todo con read(byte[]).
        //         Cerrar stream. Devolver array.
        return null;
    }

    /**
     * Copia un fichero eligiendo automaticamente la estrategia correcta
     * segun la extension del fichero origen.
     *
     * @param origen  ruta del fichero origen
     * @param destino ruta del fichero destino
     * @return "TEXTO" si uso Reader/Writer, "BINARIO" si uso streams de bytes
     * @throws IOException si hay error de I/O
     */
    public static String copiarInteligente(String origen, String destino) throws IOException {
        // TODO 5: Extraer la extension del nombre del fichero (despues del ultimo '.').
        //         Usar recomendarFamilia para saber si es texto o binario.
        //         Si es texto, copiar con FileReader/FileWriter.
        //         Si es binario, copiar con FileInputStream/FileOutputStream.
        //         Devolver "TEXTO" o "BINARIO" segun la estrategia usada.
        //         Si no tiene extension o no es reconocida, usar binario por defecto.
        return "";
    }

    /**
     * Genera un informe de diagnostico para un fichero:
     * "Fichero: [nombre]
     * Extension: [ext]
     * Tamano: [tam] bytes
     * Familia: [familia]
     * Clase lectura: [clase]
     * Clase escritura: [clase]"
     *
     * @param ruta ruta del fichero
     * @return informe formateado
     */
    public static String diagnostico(String ruta) {
        // TODO 6: Extraer nombre, extension, tamano.
        //         Obtener familia y clases recomendadas.
        //         Formatear con StringBuilder.
        //         Si la extension no es reconocida, indicar "Desconocida" en familia.
        return "";
    }

    /**
     * Dado un array de rutas de fichero, genera un informe resumido
     * contando cuantos son de texto y cuantos binarios.
     * Formato: "Total: X ficheros | Texto: Y | Binario: Z | Desconocido: W"
     *
     * @param rutas array de rutas de fichero
     * @return resumen
     */
    public static String resumenLote(String[] rutas) {
        // TODO 7: Recorrer las rutas. Para cada una, extraer la extension.
        //         Intentar recomendarFamilia. Si lanza excepcion, contar como desconocido.
        //         Contar texto y binario. Formatear resultado.
        return "";
    }

    // ══════════════════════════════════════════════
    //  ZONA DE EJECUCION — Pulsa Run aqui
    // ══════════════════════════════════════════════
    public static void main(String[] args) throws IOException {
        System.out.println("=== Ejercicio 12: Selector de Estrategia ===\n");

        System.out.println("Familia txt: " + recomendarFamilia("txt"));
        System.out.println("Familia jpg: " + recomendarFamilia("jpg"));
        System.out.println("Clase (csv, leer): " + recomendarClase("csv", "leer"));
        System.out.println("Clase (dat, escribir): " + recomendarClase("dat", "escribir"));

        String dir = "temp/bloque2";
        new File(dir).mkdirs();

        // Crear ficheros de prueba
        try (FileWriter fw = new FileWriter(dir + "/nota.txt")) {
            fw.write("Texto de prueba");
        }
        try (FileOutputStream fos = new FileOutputStream(dir + "/foto.jpg")) {
            fos.write(new byte[]{(byte) 0xFF, (byte) 0xD8, (byte) 0xFF, 0x00});
        }

        System.out.println("\nCopia inteligente nota.txt: " +
                copiarInteligente(dir + "/nota.txt", dir + "/nota_copia.txt"));
        System.out.println("Copia inteligente foto.jpg: " +
                copiarInteligente(dir + "/foto.jpg", dir + "/foto_copia.jpg"));

        System.out.println("\n" + diagnostico(dir + "/nota.txt"));

        String[] rutas = {"informe.csv", "logo.png", "datos.json", "backup.zip", "readme.md"};
        System.out.println("\n" + resumenLote(rutas));
    }
}
