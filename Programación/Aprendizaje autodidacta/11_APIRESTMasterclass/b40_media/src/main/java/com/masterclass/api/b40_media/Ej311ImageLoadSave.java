package com.masterclass.api.b40_media;

/**
 * Ejercicio 311 · Cargar y guardar imágenes: formatos y representación del píxel.
 *
 * <p>Teoría: {@code teoria/40_Multimedia.md} (sección 1).
 *
 * <p>Leer los BYTES de un fichero ya lo sabes hacer (b26_io). Lo nuevo aquí es entender QUÉ son
 * esos bytes: una imagen es una rejilla de píxeles, y cada píxel se guarda como un {@code int} con
 * cuatro canales empaquetados — alfa, rojo, verde y azul (formato <b>ARGB</b>:
 * {@code 0xAARRGGBB}). Antes de cargar nada hay que reconocer el FORMATO del fichero por su
 * "número mágico" (los primeros bytes), porque la extensión del nombre miente.
 *
 * <p>El core es lógica pura (detectar formato, empaquetar/desempaquetar canales): se prueba sin
 * abrir ventana. El Playground luego usa {@code ImageIO}/{@code Image} para cargar de verdad.
 */
public final class Ej311ImageLoadSave {

    private Ej311ImageLoadSave() {
    }

    /**
     * Detecta el formato de imagen a partir de los primeros bytes del fichero (su "magic number"),
     * no de la extensión del nombre.
     *
     * <ul>
     *   <li>PNG: empieza por {@code 0x89 'P' 'N' 'G'}.</li>
     *   <li>JPEG: empieza por {@code 0xFF 0xD8 0xFF}.</li>
     *   <li>GIF: empieza por {@code 'G' 'I' 'F'}.</li>
     *   <li>BMP: empieza por {@code 'B' 'M'}.</li>
     * </ul>
     *
     * @param cabecera primeros bytes del fichero (al menos 2-4 bytes)
     * @return "PNG" / "JPEG" / "GIF" / "BMP" / "desconocido"; "" sin implementar
     */
    public static String detectarFormato(byte[] cabecera) {
        // TODO 1: si cabecera es null o tiene menos de 2 bytes, devuelve "desconocido".
        // TODO 2: PNG -> cabecera[0]==(byte)0x89 && [1]=='P' && [2]=='N' && [3]=='G' (revisa que haya >=4).
        // TODO 3: JPEG -> cabecera[0]==(byte)0xFF && [1]==(byte)0xD8 && [2]==(byte)0xFF (>=3).
        // TODO 4: GIF -> [0]=='G' && [1]=='I' && [2]=='F' (>=3).
        // TODO 5: BMP -> [0]=='B' && [1]=='M'.
        // TODO 6: si no casa ninguno, devuelve "desconocido".
        return "";
    }

    /**
     * Empaqueta los cuatro canales (0..255 cada uno) en un único {@code int} con formato ARGB
     * ({@code 0xAARRGGBB}). Es como se guarda internamente un píxel en {@code BufferedImage}.
     *
     * @param a alfa (opacidad)
     * @param r rojo
     * @param g verde
     * @param b azul
     * @return el píxel empaquetado; 0 sin implementar
     */
    public static int empaquetarARGB(int a, int r, int g, int b) {
        // TODO 7: coloca el alfa en los bits 24-31: a << 24.
        // TODO 8: coloca el rojo en los bits 16-23: r << 16.
        // TODO 9: coloca el verde en los bits 8-15 (g << 8) y el azul en los bits 0-7 (b).
        // TODO 10: combínalos con OR (|) y devuélvelo (el test usa a=255,r=16,g=32,b=48 -> 0xFF102030).
        return 0;
    }

    public static void main(String[] args) {
        byte[] png = {(byte) 0x89, 'P', 'N', 'G'};
        System.out.println("Formato detectado: " + detectarFormato(png));
        System.out.printf("Píxel ARGB(255,16,32,48) = 0x%08X%n", empaquetarARGB(255, 16, 32, 48));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Canal alfa de un píxel ARGB.
     * Devuelve la opacidad (0..255) de un píxel empaquetado.
     */
    public static int canalAlfa(int argb) {
        // GUÍA: teoría 1.3 (desempaquetar = desplazar y enmascarar con & 0xFF).
        // 1. return (argb >>> 24) & 0xFF;
        // PISTA: usa >>> (desplazamiento SIN signo), no >> ; el alfa ocupa el bit más alto.
        // OJO: el test usa 0xFF102030 -> 255. Con >> (con signo) saldría -1 mal interpretado.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para canalAlfa");
    }

    /**
     * Reto Extra 2: Canal rojo de un píxel ARGB.
     */
    public static int canalRojo(int argb) {
        // GUÍA: teoría 1.3 (el rojo está en los bits 16-23).
        // 1. return (argb >> 16) & 0xFF;
        // OJO: el test usa 0xFF102030 -> 16 (0x10). El & 0xFF descarta el alfa de arriba.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para canalRojo");
    }

    /**
     * Reto Extra 3: Canal verde de un píxel ARGB.
     */
    public static int canalVerde(int argb) {
        // GUÍA: teoría 1.3 (el verde está en los bits 8-15).
        // 1. return (argb >> 8) & 0xFF;
        // OJO: el test usa 0xFF102030 -> 32 (0x20).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para canalVerde");
    }

    /**
     * Reto Extra 4: Canal azul de un píxel ARGB.
     */
    public static int canalAzul(int argb) {
        // GUÍA: teoría 1.3 (el azul está en los bits 0-7, no hace falta desplazar).
        // 1. return argb & 0xFF;
        // OJO: el test usa 0xFF102030 -> 48 (0x30).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para canalAzul");
    }

    /**
     * Reto Extra 5: Número total de píxeles de una imagen.
     * Multiplica ancho por alto; usa {@code long} porque 4K ya desborda enteros pequeños.
     */
    public static long totalPixeles(int ancho, int alto) {
        // GUÍA: teoría 1.1 (una imagen de WxH tiene W*H píxeles).
        // 1. return (long) ancho * alto;  // el cast a long ANTES de multiplicar evita el overflow.
        // OJO: el test usa 1920x1080 -> 2073600. Caso límite: 0 de ancho -> 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para totalPixeles");
    }

    /**
     * Reto Extra 6: Extensión de archivo recomendada para un formato.
     * "PNG"->".png", "JPEG"->".jpg", "GIF"->".gif", "BMP"->".bmp", otro->"".
     */
    public static String extensionPorFormato(String formato) {
        // GUÍA: teoría 1.2 (cada formato tiene su extensión canónica; JPEG usa .jpg, no .jpeg, por convención).
        // 1. usa un switch sobre el formato y devuelve la extensión con el punto incluido.
        // OJO: el test prueba "JPEG" -> ".jpg" y un formato desconocido -> "".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para extensionPorFormato");
    }

    /**
     * Reto Extra 7: Tipo MIME de un formato de imagen.
     * "PNG"->"image/png", "JPEG"->"image/jpeg", "GIF"->"image/gif", "BMP"->"image/bmp", otro->"".
     */
    public static String mimePorFormato(String formato) {
        // GUÍA: teoría 1.2 (el MIME es lo que va en la cabecera HTTP Content-Type al servir la imagen).
        // 1. switch como en el reto 6 pero devolviendo el tipo MIME.
        // OJO: el test prueba "PNG" -> "image/png".
        // CULTURA: este es el Content-Type que pone tu API REST (b05/b06) al devolver una imagen.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mimePorFormato");
    }

    /**
     * Reto Extra 8: ¿El formato admite transparencia (canal alfa)?
     * PNG y GIF sí; JPEG y BMP no.
     */
    public static boolean soportaTransparencia(String formato) {
        // GUÍA: teoría 1.2 (JPEG comprime con pérdida y NO guarda alfa; por eso un PNG con fondo
        //   transparente guardado como JPG sale con fondo negro o blanco).
        // 1. return "PNG".equals(formato) || "GIF".equals(formato);
        // OJO: el test comprueba que "JPEG" devuelve false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para soportaTransparencia");
    }

    /**
     * Reto Extra 9: Tamaño en bytes de la imagen SIN comprimir (en memoria).
     * Cada píxel ocupa 4 bytes (ARGB), así que son ancho*alto*4 bytes.
     */
    public static long tamanoSinComprimir(int ancho, int alto) {
        // GUÍA: teoría 1.4 (esto es lo que ocupa en RAM un BufferedImage; el fichero PNG pesa menos
        //   porque está comprimido — de ahí los ratios de compresión del Ej318).
        // 1. return totalPixeles(ancho, alto) * 4;   // reutiliza el reto 5.
        // OJO: el test usa 2x3 -> 24 bytes (6 píxeles * 4).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tamanoSinComprimir");
    }

    /**
     * Reto Extra 10: Forzar un píxel a opaco.
     * Recibe un color RGB (sin alfa, {@code 0x00RRGGBB}) y le pone alfa = 255 (totalmente opaco).
     */
    public static int forzarOpaco(int rgb) {
        // GUÍA: teoría 1.3 (al cargar un JPEG, que no tiene alfa, conviene marcar los píxeles opacos).
        // 1. return 0xFF000000 | (rgb & 0x00FFFFFF);
        // PISTA: el 0xFF000000 enciende los 8 bits del alfa; el & 0x00FFFFFF limpia cualquier alfa previo.
        // OJO: el test usa 0x00102030 -> 0xFF102030 (mismo color, ahora opaco).
        // CULTURA: el mismo OR de máscaras lo usarás al componer capas (alpha compositing) en Ej317.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para forzarOpaco");
    }
}
