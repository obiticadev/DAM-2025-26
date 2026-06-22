package com.masterclass.api.b35_fxdata;

import java.util.Comparator;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 * Ejercicio 282 · Edición <i>in-cell</i>, {@code FilteredList} y {@code SortedList} sobre una tabla.
 *
 * <p>Teoría: {@code teoria/35_JavaFX_Datos_Async.md} (sección 4).
 *
 * <p>El patrón profesional de una tabla "buscable y ordenable" es una CADENA de vistas:
 * {@code ObservableList} (fuente real) → {@code FilteredList} (lo que pasa el filtro) →
 * {@code SortedList} (ordenado) → {@code table.setItems(sortedList)}. Filtrar es cambiar el
 * predicado de la {@code FilteredList}; ordenar al pulsar una cabecera funciona si enlazas
 * ({@code bind}) el comparador de la {@code SortedList} al de la tabla. Y editar una celda
 * ({@code setEditable(true)} + {@code TextFieldTableCell}) vuelca el valor al modelo en el
 * {@code onEditCommit}. La lógica testeable sin pantalla es el filtro, el orden y el commit.
 */
public final class Ej282TableEditSortFilter {

    private Ej282TableEditSortFilter() {
    }

    /**
     * Devuelve una {@link FilteredList} sobre la fuente que solo muestra los clientes cuyo nombre
     * contiene el texto dado (ignorando mayúsculas). Texto vacío muestra todos.
     *
     * @param fuente lista observable de clientes
     * @param texto  texto a buscar en el nombre
     * @return la vista filtrada (viva); {@code null} sin implementar
     */
    public static FilteredList<ClienteFx> aplicarFiltro(ObservableList<ClienteFx> fuente, String texto) {
        // TODO 1: normaliza el texto: t = (texto == null) ? "" : texto.toLowerCase().
        // TODO 2: crea el predicado: c -> c.getNombre().toLowerCase().contains(t).
        // TODO 3: con t == "" el contains siempre es true -> se ven todos (caso límite del test).
        // TODO 4: crea new FilteredList<>(fuente, predicado).
        // TODO 5: devuelve la FilteredList (es una vista viva sobre la fuente).
        return null;
    }

    /**
     * Aplica una edición de celda al modelo: escribe el valor en el campo indicado del cliente y
     * devuelve el valor que queda guardado en ese campo. Replica lo que hace el {@code onEditCommit}.
     *
     * @param fila  el cliente editado
     * @param campo "nombre" o "email"
     * @param valor nuevo valor escrito en la celda
     * @return el valor guardado en ese campo, o {@code null} si el campo no se reconoce; {@code null} sin implementar
     */
    public static String commitEdicion(ClienteFx fila, String campo, String valor) {
        // TODO 6: si campo es "nombre" -> fila.setNombre(valor) y devuelve fila.getNombre().
        // TODO 7: si campo es "email" -> fila.setEmail(valor) y devuelve fila.getEmail().
        // TODO 8: usa un switch o if/else sobre 'campo'.
        // TODO 9: si el campo no es ninguno de los dos, devuelve null (no toques el modelo).
        // TODO 10: al escribir vía setter, la property cambia y la tabla (que la observa) refresca sola.
        return null;
    }

    public static void main(String[] args) {
        ObservableList<ClienteFx> fuente = javafx.collections.FXCollections.observableArrayList(
                new ClienteFx("Ana", "ana@correo.com", 30),
                new ClienteFx("Bruno", "bruno@correo.com", 40));
        System.out.println("Filtro 'an': " + aplicarFiltro(fuente, "an").size());
        ClienteFx ana = fuente.get(0);
        System.out.println("Commit: " + commitEdicion(ana, "nombre", "Anabel"));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Contar resultados del filtro.
     * Devuelve cuántos clientes cumplen el filtro por nombre.
     */
    public static int contarFiltrados(ObservableList<ClienteFx> fuente, String texto) {
        // GUÍA: teoría 4.1 (una FilteredList expone size() = nº de visibles).
        // 1. return aplicarFiltro(fuente, texto).size();
        // OJO: reutiliza el core. El test filtra "an" sobre Ana/Bruno -> 1.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarFiltrados");
    }

    /**
     * Reto Extra 2: Filtro vacío muestra todo.
     * Devuelve cuántos se ven con un filtro vacío (debe ser todos).
     */
    public static int filtroVacio(ObservableList<ClienteFx> fuente) {
        // GUÍA: teoría 4.1 (un buscador vacío no debe esconder nada).
        // 1. return aplicarFiltro(fuente, "").size();
        // OJO: el test comprueba que size() == fuente.size(); por eso el predicado con "" es siempre true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para filtroVacio");
    }

    /**
     * Reto Extra 3: Re-filtrar en vivo.
     * Sobre una FilteredList ya creada, cambia el predicado a "empieza por la letra dada" y devuelve el tamaño.
     */
    public static int refiltrarPorInicial(ObservableList<ClienteFx> fuente, char inicial) {
        // GUÍA: teoría 4.1 (setPredicate re-evalúa la lista al instante: así reacciona el buscador al teclear).
        // 1. FilteredList<ClienteFx> f = new FilteredList<>(fuente, c -> true).
        // 2. f.setPredicate(c -> !c.getNombre().isEmpty() && Character.toLowerCase(c.getNombre().charAt(0)) == Character.toLowerCase(inicial)).
        // 3. return f.size().
        // OJO: protege el charAt(0) contra nombres vacíos. El test usa 'a' sobre Ana/Bruno -> 1.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para refiltrarPorInicial");
    }

    /**
     * Reto Extra 4: Ordenar por nombre (vista).
     * Devuelve el nombre del primer cliente al ordenar la fuente por nombre con una SortedList.
     */
    public static String primeroOrdenadoPorNombre(ObservableList<ClienteFx> fuente) {
        // GUÍA: teoría 4.2 (SortedList ordena para mostrar sin tocar la fuente).
        // 1. SortedList<ClienteFx> s = new SortedList<>(fuente, Comparator.comparing(ClienteFx::getNombre)).
        // 2. return s.get(0).getNombre().
        // OJO: la fuente NO se reordena; solo la vista. Comparator.comparing extrae la clave de orden.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para primeroOrdenadoPorNombre");
    }

    /**
     * Reto Extra 5: Encadenar filtro y orden.
     * Filtra por texto y ordena el resultado por edad ascendente; devuelve el nombre del primero.
     */
    public static String filtrarYOrdenarPorEdad(ObservableList<ClienteFx> fuente, String texto) {
        // GUÍA: teoría 4.3 (la cadena real: FilteredList -> SortedList; cada una observa la anterior).
        // 1. FilteredList<ClienteFx> f = aplicarFiltro(fuente, texto).
        // 2. SortedList<ClienteFx> s = new SortedList<>(f, Comparator.comparingInt(ClienteFx::getEdad)).
        // 3. return s.get(0).getNombre().
        // PISTA: encadenas vistas: la SortedList ordena SOLO lo que el filtro deja pasar.
        // OJO: si el filtro deja 0 elementos, s.get(0) peta; el test usa un filtro que deja >=1.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para filtrarYOrdenarPorEdad");
    }

    /**
     * Reto Extra 6: Enlazar el comparador de la tabla.
     * Monta la cadena fuente→FilteredList→SortedList, pone la SortedList en la tabla y enlaza el
     * comparador de la SortedList al de la tabla. Devuelve true si el bind quedó hecho.
     */
    public static boolean enlazarComparador(TableView<ClienteFx> tabla, ObservableList<ClienteFx> fuente) {
        // GUÍA: teoría 4.4 (el truco para que pulsar una cabecera ordene de verdad cuando usas SortedList).
        // 1. SortedList<ClienteFx> s = new SortedList<>(new FilteredList<>(fuente)).
        // 2. s.comparatorProperty().bind(tabla.comparatorProperty()).
        // 3. tabla.setItems(s).
        // 4. return s.comparatorProperty().isBound().
        // PISTA: sin este bind, la flecha de la cabecera no ordena nada (la SortedList ignora la tabla).
        // OJO: new FilteredList<>(fuente) sin predicado deja pasar todo. El test comprueba isBound()==true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para enlazarComparador");
    }

    /**
     * Reto Extra 7: Hacer la tabla editable.
     * Marca la tabla y la columna como editables y devuelve true si ambas lo están.
     */
    public static boolean hacerEditable(TableView<ClienteFx> tabla, TableColumn<ClienteFx, String> columna) {
        // GUÍA: teoría 4.5 (para editar en celda hacen falta DOS interruptores: tabla y columna).
        // 1. tabla.setEditable(true). 2. columna.setEditable(true).
        // 3. return tabla.isEditable() && columna.isEditable().
        // OJO: si olvidas cualquiera de los dos, el doble clic no abre el editor. El test exige ambos true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para hacerEditable");
    }

    /**
     * Reto Extra 8: Editor de texto en celda.
     * Pone en la columna una cellFactory de tipo TextFieldTableCell y devuelve true si quedó puesta.
     */
    public static boolean ponerEditorTexto(TableColumn<ClienteFx, String> columna) {
        // GUÍA: teoría 4.6 (TextFieldTableCell convierte la celda en un campo de texto al editar).
        // 1. columna.setCellFactory(TextFieldTableCell.forTableColumn()).
        // 2. return columna.getCellFactory() != null.
        // PISTA: para columnas no-String necesitas un StringConverter (IntegerStringConverter para edad).
        // OJO: forTableColumn() para String no pide converter; el test solo comprueba que la factory existe.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ponerEditorTexto");
    }

    /**
     * Reto Extra 9: El commit refresca la vista.
     * Aplica un commit de edición del nombre y devuelve el nombre que la fuente observable muestra
     * para esa fila (debe ser el nuevo, sin recargar nada).
     */
    public static String commitRefrescaFuente(ObservableList<ClienteFx> fuente, int indice, String nuevoNombre) {
        // GUÍA: teoría 4.7 (editar el modelo a través del setter cambia la property; la lista observa).
        // 1. ClienteFx fila = fuente.get(indice). 2. commitEdicion(fila, "nombre", nuevoNombre).
        // 3. return fuente.get(indice).getNombre().
        // OJO: es el MISMO objeto en la lista; al cambiar su property, cualquier vista (tabla) lo refleja.
        //   El test edita la fila 0 y comprueba que la fuente ya muestra el nombre nuevo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para commitRefrescaFuente");
    }

    /**
     * Reto Extra 10: Editar saca del filtro.
     * Con un filtro "empieza por A" activo (FilteredList viva), edita la fila para que su nombre ya
     * NO empiece por A y devuelve cuántos elementos quedan VISIBLES en la vista filtrada.
     */
    public static int editarSacaDelFiltro(ObservableList<ClienteFx> fuente, String nuevoNombre) {
        // GUÍA: teoría 4.8 (lo potente de la cadena viva: editar el modelo re-evalúa el filtro solo).
        // 1. Necesitas que la FilteredList se entere de cambios de property -> la fuente debe tener
        //    EXTRACTOR sobre nombreProperty (como en Ej279 reto 10). Crea:
        //    ObservableList<ClienteFx> conExtractor = FXCollections.observableArrayList(c -> new Observable[]{ c.nombreProperty() });
        //    conExtractor.setAll(fuente).
        // 2. FilteredList<ClienteFx> f = new FilteredList<>(conExtractor, c -> c.getNombre().startsWith("A")).
        // 3. Edita un elemento que empezaba por A: pon su nombre a 'nuevoNombre' (que NO empieza por A).
        // 4. return f.size().
        // PISTA: sin extractor, la FilteredList NO re-evalúa al cambiar una property y el resultado sería
        //   incorrecto. El extractor es lo que conecta "edito un campo" con "la vista se recalcula".
        // OJO: el test parte de [Ana, Bruno] (1 con A), edita Ana -> "Zoe": la vista filtrada queda en 0.
        // CULTURA: esta reactividad en cadena (modelo→filtro→orden→tabla) es el equivalente de escritorio
        //   a una grid reactiva web (ag-Grid, TanStack Table): editas un dato y la vista se recompone sola.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para editarSacaDelFiltro");
    }

    /** Helper de demostración: dos clientes de ejemplo en una lista observable. */
    static ObservableList<ClienteFx> muestra() {
        return javafx.collections.FXCollections.observableArrayList(
                new ClienteFx("Ana", "ana@correo.com", 30),
                new ClienteFx("Bruno", "bruno@correo.com", 40));
    }

    /** Helper: comparador por nombre, reutilizable en demos. */
    static Comparator<ClienteFx> porNombre() {
        return Comparator.comparing(ClienteFx::getNombre);
    }

    /** Helper: vista ordenada por nombre de una fuente dada. */
    static SortedList<ClienteFx> ordenadaPorNombre(ObservableList<ClienteFx> fuente) {
        return new SortedList<>(fuente, porNombre());
    }
}
