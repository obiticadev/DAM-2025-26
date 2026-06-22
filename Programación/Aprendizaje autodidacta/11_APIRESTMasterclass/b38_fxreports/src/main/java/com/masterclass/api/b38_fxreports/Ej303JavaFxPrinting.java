package com.masterclass.api.b38_fxreports;

/**
 * Ejercicio 303 · {@code PrinterJob} de JavaFX: imprimir un nodo/escena.
 *
 * <p>Teoría: {@code teoria/38_Informes_PDF.md} (sección 5).
 *
 * <p>JavaFX imprime cualquier nodo con tres líneas: {@code PrinterJob job = PrinterJob.createPrinterJob();}
 * {@code job.printPage(nodo);} {@code job.endJob();}. El problema real no es esa llamada (que abre
 * un diálogo y necesita impresora), sino la <b>geometría del encaje</b>: un nodo grande no cabe en
 * un A4, así que hay que <b>escalarlo</b> (un {@code Scale} sobre el nodo) y saber <b>cuántas
 * páginas</b> ocupará y cuál es el <b>área imprimible</b> (la hoja menos los márgenes). Eso es
 * matemática pura, determinista y testeable sin impresora. Aquí el core la calcula; la llamada a
 * {@code PrinterJob} se enseña en la teoría y en el Playground. Las medidas van en <b>puntos</b>
 * (1 punto = 1/72 de pulgada), la unidad del sistema de impresión.
 */
public final class Ej303JavaFxPrinting {

    private Ej303JavaFxPrinting() {
    }

    /**
     * Factor de escala para que un nodo quepa dentro de una página: el menor de los dos cocientes
     * (ancho y alto), pero sin AGRANDAR (máximo 1.0: si ya cabe, no lo estiramos).
     *
     * @param nodoAncho ancho del nodo (puntos)
     * @param nodoAlto  alto del nodo (puntos)
     * @param pagAncho  ancho útil de la página (puntos)
     * @param pagAlto   alto útil de la página (puntos)
     * @return factor en (0, 1]; {@code 0} si alguna dimensión del nodo es <= 0
     */
    public static double escalaParaCaber(double nodoAncho, double nodoAlto, double pagAncho, double pagAlto) {
        // TODO 1: si nodoAncho <= 0 o nodoAlto <= 0, devuelve 0 (no hay nodo que escalar).
        // TODO 2: calcula el cociente horizontal: pagAncho / nodoAncho.
        // TODO 3: calcula el cociente vertical: pagAlto / nodoAlto.
        // TODO 4: el factor que hace caber es el MENOR de los dos (Math.min) -> el lado más justo manda.
        // TODO 5: no agrandes: devuelve Math.min(factor, 1.0).
        //         (el test: nodo 200x400 en pág 100x100 -> min(0.5,0.25)=0.25; nodo 50x50 en 100x100 -> 1.0).
        return -1;
    }

    /**
     * Número de páginas necesarias para imprimir un contenido alto en hojas de alto fijo.
     *
     * @param contenidoAlto alto total del contenido (puntos)
     * @param pagAlto       alto útil de cada página (puntos)
     * @return páginas (mínimo 1); redondeo hacia ARRIBA
     */
    public static int paginasNecesarias(double contenidoAlto, double pagAlto) {
        // TODO 6: si pagAlto <= 0, devuelve 1 (no dividas por cero; siempre hay al menos una hoja).
        // TODO 7: páginas = (int) Math.ceil(contenidoAlto / pagAlto) -> ceil redondea SIEMPRE arriba.
        // TODO 8: devuelve Math.max(1, páginas) (un contenido vacío sigue ocupando 1 hoja).
        //         (el test: 250 de contenido en hojas de 100 -> 3; 100 en 100 -> 1).
        return -1;
    }

    /**
     * Área imprimible: la hoja menos los márgenes (mismo margen en los 4 lados).
     *
     * @param pagAncho ancho de la hoja (puntos)
     * @param pagAlto  alto de la hoja (puntos)
     * @param margen   margen en cada lado (puntos)
     * @return {@code double[]{anchoUtil, altoUtil}}, nunca negativos; {@code null} sin implementar
     */
    public static double[] areaImprimible(double pagAncho, double pagAlto, double margen) {
        // TODO 9: el ancho útil es pagAncho - 2*margen; el alto útil pagAlto - 2*margen (margen a cada lado).
        // TODO 10: no permitas negativos: usa Math.max(0, ...) en cada uno y devuelve new double[]{w, h}.
        //          (el test: hoja 600x800 con margen 50 -> {500, 700}).
        return null;
    }

    public static void main(String[] args) {
        System.out.println("Escala 200x400 en 100x100: " + escalaParaCaber(200, 400, 100, 100));
        System.out.println("Páginas de 250 en hojas de 100: " + paginasNecesarias(250, 100));
        System.out.println("Área de 600x800 con margen 50: " + java.util.Arrays.toString(areaImprimible(600, 800, 50)));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: ¿Cabe sin escalar?
     * ¿El nodo entra tal cual en la página (ancho y alto)?
     */
    public static boolean cabeEnPagina(double nodoAncho, double nodoAlto, double pagAncho, double pagAlto) {
        // GUÍA: teoría 5.1 (si cabe, te ahorras el Scale; printPage(nodo) directo).
        // 1. Devuelve true solo si nodoAncho <= pagAncho Y nodoAlto <= pagAlto.
        // OJO: el test: 50x50 en 100x100 -> true; 200x50 en 100x100 -> false (se sale de ancho).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cabeEnPagina");
    }

    /**
     * Reto Extra 2: Orientación de la página.
     * "horizontal" (apaisada) si ancho > alto, "vertical" si alto > ancho, "cuadrado" si iguales.
     */
    public static String orientacion(double pagAncho, double pagAlto) {
        // GUÍA: teoría 5.2 (PageOrientation.LANDSCAPE/PORTRAIT según la forma de la hoja).
        // 1. Compara pagAncho con pagAlto: mayor -> "horizontal"; menor -> "vertical"; igual -> "cuadrado".
        // OJO: el test: 800x600 -> "horizontal"; 600x800 -> "vertical"; 500x500 -> "cuadrado".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para orientacion");
    }

    /**
     * Reto Extra 3: Escala en porcentaje.
     * Convierte un factor (0..1) en porcentaje entero redondeado ("75%").
     */
    public static int escalaPorcentaje(double factor) {
        // GUÍA: teoría 5.1 (la UI muestra "Ajustado al 25%" tras encajar el nodo).
        // 1. Devuelve (int) Math.round(factor * 100).
        // OJO: el test: 0.25 -> 25; 0.5 -> 50; 1.0 -> 100.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para escalaPorcentaje");
    }

    /**
     * Reto Extra 4: Desplazamiento para centrar en horizontal.
     * Cuántos puntos hay que mover el nodo a la derecha para centrarlo en la página.
     */
    public static double centrarX(double nodoAncho, double pagAncho) {
        // GUÍA: teoría 5.3 (un Translate de (pagAncho-nodoAncho)/2 centra el nodo en la hoja).
        // 1. Devuelve (pagAncho - nodoAncho) / 2.0.
        // OJO: el test: nodo 100 en página 300 -> 100.0 (queda con 100 de margen a cada lado).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para centrarX");
    }

    /**
     * Reto Extra 5: Milímetros a puntos.
     * Convierte una medida en mm a puntos de impresión (1 pulgada = 25.4 mm = 72 puntos).
     */
    public static double mmAPuntos(double mm) {
        // GUÍA: teoría 5.4 (los márgenes se piensan en mm pero el sistema imprime en puntos).
        // 1. puntos = mm * 72.0 / 25.4.
        // OJO: el test: 25.4 mm -> 72.0 puntos (tolerancia 1e-6).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mmAPuntos");
    }

    /**
     * Reto Extra 6: Puntos a milímetros.
     * La conversión inversa: de puntos de impresión a milímetros.
     */
    public static double puntosAMm(double puntos) {
        // GUÍA: teoría 5.4 (para mostrar "margen: 20 mm" partiendo de los puntos internos).
        // 1. mm = puntos * 25.4 / 72.0.
        // OJO: el test: 72 puntos -> 25.4 mm (tolerancia 1e-6).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para puntosAMm");
    }

    /**
     * Reto Extra 7: Dimensión ya escalada.
     * Aplica un factor de escala a un tamaño de nodo y devuelve {ancho, alto} resultantes.
     */
    public static double[] dimensionEscalada(double nodoAncho, double nodoAlto, double factor) {
        // GUÍA: teoría 5.1 (tras escalar, el nodo "ocupa" nodo * factor; útil para recolocarlo).
        // 1. Devuelve new double[]{ nodoAncho * factor, nodoAlto * factor }.
        // OJO: el test: 200x400 con factor 0.25 -> {50.0, 100.0}.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para dimensionEscalada");
    }

    /**
     * Reto Extra 8: Margen a partir del área imprimible.
     * Deduce el margen (simétrico) sabiendo la hoja y su área útil.
     */
    public static double margenDeducido(double pagAncho, double areaAncho) {
        // GUÍA: teoría 5.5 (al revés que areaImprimible: de la hoja y el área, saca el margen).
        // 1. Devuelve (pagAncho - areaAncho) / 2.0.
        // OJO: el test: hoja 600, área 500 -> 50.0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para margenDeducido");
    }

    /**
     * Reto Extra 9: Páginas de una tabla por filas.
     * Cuántas hojas ocupa una tabla de N filas si en cada hoja caben 'filasPorPagina'.
     */
    public static int paginasDeTabla(int totalFilas, int filasPorPagina) {
        // GUÍA: teoría 5.6 (paginar una TableView larga: tantas hojas como bloques de filas).
        // 1. Si filasPorPagina <= 0, devuelve 0 (configuración inválida).
        // 2. Si totalFilas <= 0, devuelve 0 (nada que imprimir).
        // 3. Devuelve (int) Math.ceil((double) totalFilas / filasPorPagina).
        // PISTA: castea a double ANTES de dividir o la división entera te miente.
        // OJO: el test: 25 filas / 10 por hoja -> 3; 20/10 -> 2; 0 filas -> 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para paginasDeTabla");
    }

    /**
     * Reto Extra 10: Rango de filas de una página.
     * Las filas [inicio, fin) que se imprimen en la página dada (1-based), recortando al total.
     */
    public static int[] rangoDePagina(int pagina, int filasPorPagina, int totalFilas) {
        // GUÍA: teoría 5.6 (qué filas van en la hoja 2; misma idea que la paginación de una API).
        // 1. inicio = (pagina - 1) * filasPorPagina  (página 1 empieza en la fila 0).
        // 2. fin = Math.min(inicio + filasPorPagina, totalFilas)  (la última hoja puede ir a medias).
        // 3. Devuelve new int[]{inicio, fin}.
        // OJO: el test: página 1, 10/hoja, 25 filas -> {0,10}; página 3 -> {20,25} (solo 5 filas).
        // CULTURA: inicio = (page-1)*size es EXACTAMENTE el offset de Pageable en Spring Data (b12)
        //   y el skip(offset).limit(size) de los streams en b01: la paginación es siempre lo mismo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para rangoDePagina");
    }
}
