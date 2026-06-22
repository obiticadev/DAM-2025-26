package com.masterclass.api.b32_fxbase;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * Ejercicio 259 · {@link BorderPane} (5 zonas) y {@link GridPane} (filas/columnas, colspan).
 *
 * <p>Teoría: {@code teoria/32_JavaFX_Base.md} (sección 1.5).
 */
public final class Ej259BorderGridPane {

    private Ej259BorderGridPane() {
    }

    /**
     * Coloca un nodo en una de las 5 zonas de un {@link BorderPane} (top/bottom/left/right/center).
     *
     * @param zona  nombre de la zona en minúsculas
     * @param nodo  nodo a colocar
     * @return el BorderPane con el nodo colocado; {@code null} sin implementar o zona inválida
     */
    public static BorderPane colocarEnBorde(String zona, Node nodo) {
        // TODO 1: crea un BorderPane vacío.
        // TODO 2: según 'zona', llama al setter correspondiente:
        //         "top"->setTop, "bottom"->setBottom, "left"->setLeft, "right"->setRight, "center"->setCenter.
        // TODO 3: usa un switch sobre zona (recuerda romper/return en cada caso).
        // TODO 4: si la zona no es ninguna de las 5, devuelve null (entrada inválida).
        // TODO 5: devuelve el BorderPane.
        return null;
    }

    /**
     * Índice lineal de una celda del grid leída por filas: fila*numColumnas + columna.
     *
     * @return el índice (>=0); -1 sin implementar o entrada inválida
     */
    public static int indiceLineal(int fila, int columna, int numColumnas) {
        // TODO 6: valida: numColumnas <= 0 -> devuelve -1.
        // TODO 7: valida: fila < 0 o columna < 0 -> devuelve -1.
        // TODO 8: valida: columna >= numColumnas -> devuelve -1 (se sale de la fila).
        // TODO 9: calcula fila*numColumnas + columna.
        // TODO 10: devuelve ese índice.
        return -1;
    }

    public static void main(String[] args) {
        BorderPane bp = colocarEnBorde("center", new Label("contenido"));
        System.out.println("Center: " + (bp == null ? "null" : bp.getCenter()));
        System.out.println("Celda (1,2) con 3 columnas: " + indiceLineal(1, 2, 3));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Zona opuesta.
     * Devuelve la zona contraria: top↔bottom, left↔right, center→center.
     */
    public static String zonaOpuesta(String zona) {
        // GUÍA: teoría 1.5 (las 5 zonas del BorderPane y su simetría).
        // 1. switch: "top"->"bottom", "bottom"->"top", "left"->"right", "right"->"left", "center"->"center".
        // OJO: para una zona desconocida devuelve null; el test prueba "top" y "left".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para zonaOpuesta");
    }

    /**
     * Reto Extra 2: Colocar en una celda concreta del GridPane.
     * Añade el nodo en (columna, fila) y devuelve el GridPane.
     */
    public static GridPane colocarEnGrid(GridPane grid, Node nodo, int columna, int fila) {
        // GUÍA: teoría 1.5 (en GridPane el orden es COLUMNA, FILA — al revés que una matriz).
        // 1. grid.add(nodo, columna, fila); 2. devuelve grid.
        // PISTA: grid.add(nodo, col, fila) fija ColumnIndex y RowIndex de golpe.
        // OJO: el test lee GridPane.getColumnIndex(nodo) == columna; cuidado con el orden de args.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para colocarEnGrid");
    }

    /**
     * Reto Extra 3: Aplicar colspan a un nodo.
     * Hace que el nodo ocupe 'span' columnas y devuelve el GridPane.
     */
    public static GridPane aplicarColspan(GridPane grid, Node nodo, int span) {
        // GUÍA: teoría 1.5 (un nodo puede abarcar varias columnas, como colspan en HTML).
        // 1. GridPane.setColumnSpan(nodo, span); 2. devuelve grid.
        // OJO: el nodo debe estar YA en el grid; el test lo añade antes. Lee getColumnSpan(nodo).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para aplicarColspan");
    }

    /**
     * Reto Extra 4: Leer el nodo de una zona.
     * Devuelve el nodo que hay en esa zona del BorderPane (o null si está vacía).
     */
    public static Node nodoEnZona(BorderPane bp, String zona) {
        // GUÍA: teoría 1.5 (cada zona tiene su getter: getTop, getCenter…).
        // 1. switch sobre zona devolviendo bp.getTop()/getBottom()/getLeft()/getRight()/getCenter().
        // OJO: zona inválida o vacía -> null; el test coloca algo en "right" y lo recupera.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para nodoEnZona");
    }

    /**
     * Reto Extra 5: Contar zonas ocupadas.
     * Cuenta cuántas de las 5 zonas del BorderPane tienen un nodo.
     */
    public static int contarZonasOcupadas(BorderPane bp) {
        // GUÍA: teoría 1.5. Revisa los 5 getters y suma 1 por cada uno no nulo.
        // PISTA: un método auxiliar que cuente "no null" de top/bottom/left/right/center.
        // OJO: el test pone 2 zonas y deja 3 vacías -> espera 2.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarZonasOcupadas");
    }

    /**
     * Reto Extra 6: Coordenadas (fila, columna) desde un índice lineal.
     * Operación inversa de indiceLineal.
     */
    public static int[] coordenadasDesdeIndice(int indice, int numColumnas) {
        // GUÍA: teoría 1.5 (de índice plano a rejilla: división y resto).
        // 1. fila = indice / numColumnas; columna = indice % numColumnas.
        // 2. Devuelve new int[]{fila, columna}.
        // OJO: si numColumnas <= 0 o indice < 0 devuelve null; el test: indice 5, 3 columnas -> {1,2}.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para coordenadasDesdeIndice");
    }

    /**
     * Reto Extra 7: Total de celdas de una rejilla.
     * Devuelve filas * columnas.
     */
    public static int totalCeldas(int filas, int columnas) {
        // GUÍA: teoría 1.5. Aritmética simple con guarda.
        // 1. Si filas <= 0 o columnas <= 0 -> 0. 2. Si no, filas*columnas.
        // OJO: el test prueba 3x4 = 12.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para totalCeldas");
    }

    /**
     * Reto Extra 8: Celdas que ocupa un nodo con span.
     * Devuelve colspan * rowspan (área que abarca una celda fusionada).
     */
    public static int celdasOcupadas(int colspan, int rowspan) {
        // GUÍA: teoría 1.5 (un nodo con colspan/rowspan tapa un rectángulo de celdas).
        // 1. Si alguno < 1 trátalo como 1 (un span mínimo es 1). 2. Devuelve colspan*rowspan.
        // OJO: span 0 no tiene sentido; el test usa 2x3 = 6 y 1x1 = 1.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para celdasOcupadas");
    }

    /**
     * Reto Extra 9: ¿Es una zona válida de BorderPane?
     * Indica si 'zona' es una de las 5 zonas reconocidas.
     */
    public static boolean esZonaValida(String zona) {
        // GUÍA: teoría 1.5. Comprueba pertenencia al conjunto de zonas.
        // 1. return Set.of("top","bottom","left","right","center").contains(zona);
        // OJO: null y "middle" -> false; usa el Set para no encadenar 5 equals.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esZonaValida");
    }

    /**
     * Reto Extra 10: Construir una rejilla de etiquetas.
     * Crea un {@link GridPane} con filas*columnas {@link Label} colocadas en su celda.
     */
    public static GridPane construirGrid(int filas, int columnas) {
        // GUÍA: teoría 1.5 (rellenar un grid recorriendo fila a fila, columna a columna).
        // 1. new GridPane(). 2. Doble bucle for(f<filas){ for(c<columnas){ add(new Label(f+","+c), c, f) } }.
        // PISTA: recuerda el orden (columna, fila) de grid.add.
        // OJO: el test comprueba que getChildren().size() == filas*columnas (3x2 -> 6).
        // CULTURA: este patrón "doble bucle que rellena celdas" es la base de un calendario,
        //   un tablero de juego (b41) o una hoja de cálculo. Lo verás de nuevo con TableView en b35.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para construirGrid");
    }
}
