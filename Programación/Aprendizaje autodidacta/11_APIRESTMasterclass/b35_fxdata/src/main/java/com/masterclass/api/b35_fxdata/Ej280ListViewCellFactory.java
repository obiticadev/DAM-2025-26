package com.masterclass.api.b35_fxdata;

import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

/**
 * Ejercicio 280 · {@code ListView}, {@code cellFactory} y render personalizado de celdas.
 *
 * <p>Teoría: {@code teoria/35_JavaFX_Datos_Async.md} (sección 2).
 *
 * <p>Un {@code ListView} pinta una {@code ObservableList} fila a fila usando una {@code ListCell}.
 * Por defecto la celda muestra {@code toString()}; cuando quieres otra cosa (un formato, un color
 * según el estado, un icono) sustituyes la fábrica de celdas (la {@code cellFactory}) y reescribes
 * {@code updateItem}. La PARTE TESTEABLE de ese render es pura: "dado este dato, ¿qué texto y qué
 * clase de estilo le corresponden?". Eso es lo que probamos aquí, sin pintar; el resto (selección,
 * placeholder, items vivos) se prueba con un {@code ListView} real headless.
 */
public final class Ej280ListViewCellFactory {

    /** Dato de dominio que mostraremos en la lista. */
    public record Producto(String nombre, double precio, int stock) {
    }

    private Ej280ListViewCellFactory() {
    }

    /**
     * Texto que debe mostrar una celda para un producto, replicando la lógica de {@code updateItem}.
     * Una celda vacía (la rejilla pinta más celdas que datos) o sin dato no muestra nada.
     *
     * @param producto el dato de la fila, o {@code null}
     * @param vacia    true si la celda está vacía (sin dato real)
     * @return el texto a mostrar (p.ej. "Café (2.50 €)"), o {@code null} si la celda no tiene dato
     */
    public static String textoCelda(Producto producto, boolean vacia) {
        // TODO 1: si 'vacia' es true O 'producto' es null, devuelve null (la celda se pinta vacía).
        // TODO 2: extrae nombre y precio del producto.
        // TODO 3: formatea el precio con dos decimales: String.format("%.2f", precio).
        // TODO 4: compón el texto "nombre (precio €)".
        // TODO 5: devuelve ese texto.
        return null;
    }

    /**
     * Clase CSS de estado que la celda aplicaría según el stock: agotado si no queda, bajo si quedan
     * menos de 5, disponible en caso contrario. Es la lógica de "colorear la fila según el dato".
     *
     * @param producto el dato de la fila, o {@code null}
     * @return "agotado" / "bajo" / "disponible", o {@code ""} si no hay dato
     */
    public static String claseEstado(Producto producto) {
        // TODO 6: si 'producto' es null, devuelve "" (sin dato, sin clase).
        // TODO 7: si stock == 0, devuelve "agotado".
        // TODO 8: si stock < 5, devuelve "bajo".
        // TODO 9: en otro caso, devuelve "disponible".
        // TODO 10: ojo al orden: agotado (==0) se comprueba ANTES que bajo (<5), porque 0 también es <5.
        return "";
    }

    public static void main(String[] args) {
        System.out.println(textoCelda(new Producto("Café", 2.5, 10), false));
        System.out.println("Clase: " + claseEstado(new Producto("Té", 1.8, 0)));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Texto por defecto.
     * Devuelve la representación por defecto de un elemento (lo que mostraría una celda sin formato).
     */
    public static String textoSimple(Object item) {
        // GUÍA: teoría 2.1 (sin cellFactory, una celda muestra String.valueOf(item)).
        // 1. return String.valueOf(item);
        // OJO: String.valueOf(null) devuelve "null" (texto), no peta. Por eso JavaFX usa empty aparte.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para textoSimple");
    }

    /**
     * Reto Extra 2: Mostrar solo el nombre.
     * Devuelve el nombre del producto (render que ignora el resto de campos).
     */
    public static String textoNombre(Producto producto) {
        // GUÍA: teoría 2.2 (la cellFactory elige QUÉ campo del objeto se ve).
        // 1. return producto.nombre();
        // OJO: aquí decides la "proyección" del dato; con records es directo con el accesor.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para textoNombre");
    }

    /**
     * Reto Extra 3: Etiqueta con precio.
     * Devuelve "nombre - 2.50 €" usando dos decimales.
     */
    public static String textoConPrecio(Producto producto) {
        // GUÍA: teoría 2.2 (formatear el dato para presentación es responsabilidad de la celda).
        // 1. return producto.nombre() + " - " + String.format("%.2f", producto.precio()) + " €";
        // PISTA: %.2f fuerza dos decimales (2.5 -> "2.50"). En tu Locale puede salir coma; el test
        //   usa String.format por defecto, así que sé coherente con lo que el test espera.
        // OJO: el test compara el String exacto; cuidado con el espacio antes de €.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para textoConPrecio");
    }

    /**
     * Reto Extra 4: Render null-safe.
     * Devuelve el nombre del producto, o "" si el producto o la celda están vacíos.
     */
    public static String textoSeguro(Producto producto, boolean vacia) {
        // GUÍA: teoría 2.3 (la regla de oro de updateItem: SIEMPRE tratar empty/null).
        // 1. Si vacia o producto==null -> "". 2. Si no -> producto.nombre().
        // OJO: el error nº1 al escribir una cellFactory es olvidar el caso empty: la celda reusa
        //   texto viejo y aparecen "fantasmas" al hacer scroll. El test prueba vacia=true -> "".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para textoSeguro");
    }

    /**
     * Reto Extra 5: Construir un ListView con datos.
     * Devuelve un {@link ListView} cuyos items son los textos dados.
     */
    public static ListView<String> construirListView(List<String> items) {
        // GUÍA: teoría 2.1 (ListView toma una ObservableList; FXCollections la crea).
        // 1. return new ListView<>(FXCollections.observableArrayList(items));
        // PISTA: importa javafx.collections.FXCollections.
        // OJO: el test comprueba lv.getItems().size(); el ListView en sí no necesita pantalla.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para construirListView");
    }

    /**
     * Reto Extra 6: Seleccionar el primero.
     * Selecciona el primer elemento del {@link ListView} y devuelve el item seleccionado.
     */
    public static String seleccionarPrimero(ListView<String> lista) {
        // GUÍA: teoría 2.4 (el SelectionModel gestiona qué fila está elegida).
        // 1. lista.getSelectionModel().selectFirst(). 2. return lista.getSelectionModel().getSelectedItem().
        // OJO: lista vacía -> getSelectedItem() es null (no peta). El test usa una lista con datos.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para seleccionarPrimero");
    }

    /**
     * Reto Extra 7: Seleccionar por índice.
     * Selecciona el elemento en la posición dada y devuelve el item seleccionado.
     */
    public static String seleccionarIndice(ListView<String> lista, int indice) {
        // GUÍA: teoría 2.4 (select(int) elige por posición; getSelectedIndex() la lee).
        // 1. lista.getSelectionModel().select(indice). 2. return ...getSelectedItem().
        // OJO: un índice fuera de rango NO lanza excepción, deja la selección vacía (getSelectedItem null).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para seleccionarIndice");
    }

    /**
     * Reto Extra 8: Selección múltiple.
     * Pone el {@link ListView} en modo multi-selección, selecciona los índices dados y devuelve cuántos quedan.
     */
    public static int contarSeleccionMultiple(ListView<String> lista, int... indices) {
        // GUÍA: teoría 2.4 (por defecto la selección es SINGLE; hay que activar MULTIPLE).
        // 1. lista.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE).
        // 2. lista.getSelectionModel().selectIndices(indices[0], java.util.Arrays.copyOfRange(indices,1,indices.length))
        //    o más simple: for (int i : indices) lista.getSelectionModel().select(i).
        // 3. return lista.getSelectionModel().getSelectedItems().size().
        // PISTA: en modo SINGLE, seleccionar otro deselecciona el anterior -> contarías 1.
        // OJO: el test selecciona 2 índices y espera 2; demuestra que el modo MULTIPLE acumula.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarSeleccionMultiple");
    }

    /**
     * Reto Extra 9: Texto de lista vacía (placeholder).
     * Pone un placeholder con el texto dado y devuelve el texto que el ListView mostraría sin datos.
     */
    public static String ponerPlaceholder(ListView<String> lista, String texto) {
        // GUÍA: teoría 2.5 (el placeholder es el nodo que se ve cuando la lista no tiene items).
        // 1. lista.setPlaceholder(new Label(texto)).
        // 2. return ((Label) lista.getPlaceholder()).getText().
        // OJO: el placeholder es un Node cualquiera (aquí un Label); úsalo para "No hay resultados".
        //   El test lee el texto de vuelta para confirmar que quedó puesto.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ponerPlaceholder");
    }

    /**
     * Reto Extra 10: Items vivos.
     * Añade un elemento a los items del {@link ListView} (sin tocar la vista) y devuelve el nuevo tamaño.
     */
    public static int agregarItemVivo(ListView<String> lista, String nuevo) {
        // GUÍA: teoría 2.6 (la vista observa su ObservableList: mutar la lista repinta la vista sola).
        // 1. lista.getItems().add(nuevo). 2. return lista.getItems().size().
        // OJO: NUNCA hagas setItems(new ArrayList) para "refrescar"; muta la lista observable que ya
        //   tiene puesta y la UI reacciona. El test añade 1 y comprueba size+1.
        // CULTURA: este enlace lista-observable→vista es justo lo que en Ej286 hace que la TableView se
        //   rellene sola cuando el Task de fondo termina y vuelca los datos de la API en la lista.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para agregarItemVivo");
    }
}
