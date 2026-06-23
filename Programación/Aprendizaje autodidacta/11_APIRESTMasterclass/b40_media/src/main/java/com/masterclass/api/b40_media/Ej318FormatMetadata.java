package com.masterclass.api.b40_media;

/**
 * Ejercicio 318 · Formatos multimedia, metadatos, conversión y compresión básica.
 *
 * <p>Teoría: {@code teoria/40_Multimedia.md} (sección 8).
 *
 * <p>Cierre del bloque: identificar un fichero multimedia por su "número mágico" (igual que las
 * imágenes en Ej311, pero ahora audio/vídeo: MP3, WAV, MP4, OGG), entender la COMPRESIÓN (ratio,
 * % de ahorro, bitrate) y calcular METADATOS derivados (duración a partir de muestras, fotogramas
 * a partir de fps, tamaño legible). Todo es aritmética y bytes: puro y testeable.
 */
public final class Ej318FormatMetadata {

    private Ej318FormatMetadata() {
    }

    /**
     * Ratio de compresión: cuántas veces es más pequeño el fichero comprimido respecto al original.
     * {@code original / comprimido} (un ratio 4.0 significa "ocupa la cuarta parte").
     *
     * @param original   tamaño sin comprimir (bytes)
     * @param comprimido tamaño comprimido (bytes)
     * @return el ratio; -1 sin implementar
     */
    public static double ratioCompresion(long original, long comprimido) {
        // TODO 1: si comprimido <= 0 devuelve 0 (evita dividir por cero / valores absurdos).
        // TODO 2: return (double) original / comprimido.
        // TODO 3: (el cast a double es CLAVE: sin él, 1000/250 daría una división entera).
        // TODO 4: (un ratio < 1 significaría que el "comprimido" es mayor que el original).
        // TODO 5: el test usa (1000, 250) -> 4.0 y comprimido 0 -> 0.
        return -1;
    }

    /**
     * Detecta el formato multimedia por su cabecera (magic number).
     *
     * <ul>
     *   <li>MP3: empieza por {@code 'I' 'D' '3'} (etiqueta ID3).</li>
     *   <li>WAV: empieza por {@code 'R' 'I' 'F' 'F'}.</li>
     *   <li>OGG: empieza por {@code 'O' 'g' 'g' 'S'}.</li>
     *   <li>MP4: los bytes 4..7 son {@code 'f' 't' 'y' 'p'}.</li>
     * </ul>
     *
     * @param cabecera primeros bytes del fichero
     * @return "MP3" / "WAV" / "OGG" / "MP4" / "desconocido"; "" sin implementar
     */
    public static String detectarFormatoMedia(byte[] cabecera) {
        // TODO 6: si cabecera es null o tiene menos de 4 bytes, devuelve "desconocido".
        // TODO 7: MP3 -> cabecera[0]=='I' && [1]=='D' && [2]=='3'.
        // TODO 8: WAV -> [0]=='R'&&[1]=='I'&&[2]=='F'&&[3]=='F'; OGG -> 'O','g','g','S'.
        // TODO 9: MP4 -> hay >=8 bytes y cabecera[4]=='f'&&[5]=='t'&&[6]=='y'&&[7]=='p'.
        // TODO 10: si no casa nada, "desconocido". (el test prueba "ID3.." -> "MP3", "RIFF" -> "WAV").
        return "";
    }

    public static void main(String[] args) {
        System.out.println("Ratio 1000/250 -> " + ratioCompresion(1000, 250));
        byte[] wav = {'R', 'I', 'F', 'F'};
        System.out.println("Formato -> " + detectarFormatoMedia(wav));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Porcentaje de ahorro de la compresión (0..100, entero).
     */
    public static int porcentajeAhorro(long original, long comprimido) {
        // GUÍA: teoría 8.4 ("este ZIP ahorra un 75%").
        // 1. si original <= 0 devuelve 0. 2. return (int) Math.round((1.0 - (double)comprimido/original) * 100).
        // OJO: el test usa (1000, 250) -> 75. Caso límite original 0 -> 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para porcentajeAhorro");
    }

    /**
     * Reto Extra 2: Extensión de archivo de un formato multimedia.
     * "MP3"->".mp3", "WAV"->".wav", "OGG"->".ogg", "MP4"->".mp4", otro->"".
     */
    public static String extensionMedia(String formato) {
        // GUÍA: teoría 8.2 (cada formato, su extensión).
        // 1. switch sobre el formato devolviendo la extensión con punto.
        // OJO: el test prueba "MP4" -> ".mp4" y desconocido -> "".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extensionMedia");
    }

    /**
     * Reto Extra 3: ¿Es un formato de audio? (MP3, WAV, OGG).
     */
    public static boolean esAudio(String formato) {
        // GUÍA: teoría 8.2 (clasificar audio vs vídeo para elegir MediaView o solo MediaPlayer).
        // 1. return "MP3".equals(formato) || "WAV".equals(formato) || "OGG".equals(formato);
        // OJO: el test comprueba "MP3" -> true y "MP4" -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esAudio");
    }

    /**
     * Reto Extra 4: ¿Es un formato de vídeo? (MP4).
     */
    public static boolean esVideo(String formato) {
        // GUÍA: teoría 8.2 (en este bloque el único vídeo es MP4).
        // 1. return "MP4".equals(formato);
        // OJO: el test comprueba "MP4" -> true y "WAV" -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esVideo");
    }

    /**
     * Reto Extra 5: Tipo MIME de un formato multimedia.
     * "MP3"->"audio/mpeg", "WAV"->"audio/wav", "OGG"->"audio/ogg", "MP4"->"video/mp4", otro->"".
     */
    public static String mimeMedia(String formato) {
        // GUÍA: teoría 8.2 (el Content-Type que necesita el navegador para reproducir en <audio>/<video>).
        // 1. switch sobre el formato. OJO: MP3 es "audio/mpeg" (no "audio/mp3").
        // OJO: el test prueba "MP3" -> "audio/mpeg" y "MP4" -> "video/mp4".
        // CULTURA: este MIME es el que pondría tu API REST (b05/b06) al hacer streaming de un audio.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mimeMedia");
    }

    /**
     * Reto Extra 6: Bitrate en kbps a partir del tamaño en bytes y la duración en segundos.
     */
    public static int bitrateKbps(long bytes, double segundos) {
        // GUÍA: teoría 8.5 (bitrate = bits por segundo; un MP3 típico son 128 o 320 kbps).
        // 1. si segundos <= 0 devuelve 0.
        // 2. bits = bytes * 8; return (int) Math.round(bits / segundos / 1000.0);
        // OJO: el test usa 1_000_000 bytes en 50 s -> 160 kbps (1_000_000*8/50/1000).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para bitrateKbps");
    }

    /**
     * Reto Extra 7: Tamaño estimado en bytes dado un bitrate (kbps) y una duración (s).
     */
    public static long tamanoEstimado(double bitrateKbps, double segundos) {
        // GUÍA: teoría 8.5 (inverso del reto 6: cuánto pesará un audio de X kbps y Y segundos).
        // 1. bitsPorSegundo = bitrateKbps * 1000; bitsTotales = bitsPorSegundo * segundos.
        // 2. return Math.round(bitsTotales / 8.0);  // de bits a bytes.
        // OJO: el test usa 160 kbps durante 50 s -> 1_000_000 bytes.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tamanoEstimado");
    }

    /**
     * Reto Extra 8: Duración en segundos a partir del número de muestras y la frecuencia (Hz).
     */
    public static double duracionDesdeMuestras(long muestras, int frecuencia) {
        // GUÍA: teoría 8.6 (un WAV a 44100 Hz tiene 44100 muestras por segundo de audio).
        // 1. si frecuencia <= 0 devuelve 0. 2. return (double) muestras / frecuencia.
        // OJO: el test usa 44100 muestras a 44100 Hz -> 1.0 s.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para duracionDesdeMuestras");
    }

    /**
     * Reto Extra 9: Número total de fotogramas de un vídeo (fps * segundos, redondeado).
     */
    public static long framesTotales(double fps, double segundos) {
        // GUÍA: teoría 8.7 (un vídeo a 30 fps de 10 s tiene 300 fotogramas).
        // 1. return Math.round(fps * segundos);
        // OJO: el test usa 30 fps durante 10 s -> 300.
        // CULTURA: ese "fps" es la base del game loop de b41 (60 actualizaciones por segundo).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para framesTotales");
    }

    /**
     * Reto Extra 10: Tamaño legible para humanos (B / KB / MB) con un decimal.
     * &lt; 1024 -&gt; "N B"; &lt; 1024² -&gt; "X.X KB"; resto -&gt; "X.X MB".
     */
    public static String formatearTamano(long bytes) {
        // GUÍA: teoría 8.4 (lo que muestra el explorador de archivos: "1.5 MB").
        // 1. si bytes < 1024 -> return bytes + " B".
        // 2. si bytes < 1024*1024 -> return String.format(java.util.Locale.US, "%.1f KB", bytes/1024.0).
        // 3. si no -> "%.1f MB" con bytes/(1024.0*1024.0).
        // PISTA: usa Locale.US para que el separador decimal sea el PUNTO (no la coma española).
        // OJO: el test usa 512 -> "512 B", 1024 -> "1.0 KB" y 1048576 -> "1.0 MB".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearTamano");
    }
}
