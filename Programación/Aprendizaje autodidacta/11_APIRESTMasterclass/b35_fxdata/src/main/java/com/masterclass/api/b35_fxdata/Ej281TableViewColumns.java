package com.masterclass.api.b35_fxdata;

import java.util.List;
import java.util.function.Function;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Ejercicio 281 · {@code TableView}, {@code TableColumn} y {@code cellValueFactory}.
 *
 * <p>Teoría: {@code teoria/35_JavaFX_Datos_Async.md} (sección 3).
 *
 * <p>Una {@code TableView} pinta filas (objetos del modelo) y columnas. Cada {@code TableColumn}
 * necesita saber QUÉ valor de la fila mostrar: eso es la {@code cellValueFactory}, que devuelve un
 * {@code ObservableValue}. Hay dos formas: {@code new PropertyValueFactory<>("nombre")} (por
 * reflexión busca {@code nombreProperty()}/{@code getNombre()}) o una lambda explícita
 * {@code cd -> cd.getValue().nombreProperty()}. La PARTE TESTEABLE es: dada una columna y una fila,
 * ¿qué valor extrae? Eso se comprueba sin pintar la tabla, con
 * {@code columna.getCellObservableValue(fila)}.
 */
public final class Ej281TableViewColumns {

    private Ej281TableViewColumns() {
    }

    /**
     * Construye una columna cuya {@code cellValueFactory} es una {@link PropertyValueFactory} sobre
     * la propiedad dada (busca por reflexión {@code <propiedad>Property()} en la fila).
     *
     * @param propiedad nombre de la propiedad del modelo (p.ej. "nombre", "email")
     * @return la columna configurada; {@code null} sin implementar
     */
    public static TableColumn<ClienteFx, String> columnaDe(String propiedad) {
        // TODO 1: crea TableColumn<ClienteFx, String> col = new TableColumn<>(propiedad).
        // TODO 2: crea la fábrica: new PropertyValueFactory<ClienteFx, String>(propiedad).
        // TODO 3: asóciala con col.setCellValueFactory(...).
        // TODO 4: la PropertyValueFactory buscará nombreProperty()/getNombre() por reflexión en la fila.
        // TODO 5: devuelve col.
        return null;
    }

    /**
     * Valor que mostraría una celda: el que la {@code cellValueFactory} de la columna extrae de la
     * fila. Es la lógica de render de la tabla, comprobable sin pantalla.
     *
     * @param columna columna con su cellValueFactory configurada
     * @param fila    la fila (objeto del modelo)
     * @return el valor extraído, o {@code null} si no hay valor; {@code null} sin implementar
     */
    public static <T, V> V valorDeFila(TableColumn<T, V> columna, T fila) {
        // TODO 6: pide a la columna el ObservableValue de esa fila: columna.getCellObservableValue(fila).
        // TODO 7: ese método invoca por dentro la cellValueFactory con la fila dada.
        // TODO 8: si el ObservableValue es null, devuelve null (columna sin factory).
        // TODO 9: si no, devuelve su valor con .getValue().
        // TODO 10: NO necesitas pintar la tabla: la extracción del valor es pura.
        return null;
    }

    public static void main(String[] args) {
        TableColumn<ClienteFx, String> colNombre = columnaDe("nombre");
        ClienteFx ana = new ClienteFx("Ana", "ana@correo.com", 30);
        System.out.println("Valor columna nombre: " + valorDeFila(colNombre, ana));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Crear una tabla con datos.
     * Devuelve una {@link TableView} cuyas filas son los clientes dados.
     */
    public static TableView<ClienteFx> crearTabla(List<ClienteFx> clientes) {
        // GUÍA: teoría 3.1 (TableView toma una ObservableList de objetos del modelo).
        // 1. return new TableView<>(FXCollections.observableArrayList(clientes));
        // OJO: el test comprueba tabla.getItems().size(); aún no hemos puesto columnas (eso es aparte).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearTabla");
    }

    /**
     * Reto Extra 2: Añadir una columna a la tabla.
     * Añade la columna a la tabla y devuelve cuántas columnas tiene.
     */
    public static int agregarColumna(TableView<ClienteFx> tabla, TableColumn<ClienteFx, ?> columna) {
        // GUÍA: teoría 3.1 (las columnas viven en tabla.getColumns(), también observable).
        // 1. tabla.getColumns().add(columna). 2. return tabla.getColumns().size().
        // OJO: filas (getItems) y columnas (getColumns) son listas DISTINTAS; no las confundas.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para agregarColumna");
    }

    /**
     * Reto Extra 3: Columna con título y propiedad.
     * Crea una columna con el título dado y una PropertyValueFactory sobre la propiedad; devuelve su título.
     */
    public static String columnaConTitulo(String titulo, String propiedad) {
        // GUÍA: teoría 3.1 (el título es el texto de cabecera; la propiedad, qué dato muestra).
        // 1. TableColumn<ClienteFx,Object> col = new TableColumn<>(titulo).
        // 2. col.setCellValueFactory(new PropertyValueFactory<>(propiedad)). 3. return col.getText().
        // OJO: título y propiedad NO tienen por qué coincidir ("Correo" como título, "email" como dato).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para columnaConTitulo");
    }

    /**
     * Reto Extra 4: Valor por nombre de propiedad.
     * Devuelve el valor de la propiedad indicada para ese cliente, vía PropertyValueFactory.
     */
    public static Object valorPorPropiedad(ClienteFx cliente, String propiedad) {
        // GUÍA: teoría 3.2 (PropertyValueFactory resuelve la propiedad por reflexión).
        // 1. TableColumn<ClienteFx,Object> col = new TableColumn<>(); col.setCellValueFactory(new PropertyValueFactory<>(propiedad)).
        // 2. return col.getCellObservableValue(cliente).getValue().
        // PISTA: reutiliza valorDeFila si quieres; ambos pasan por getCellObservableValue.
        // OJO: si la propiedad no existe, PropertyValueFactory devuelve null en vez de petar; el test
        //   usa "email" -> el correo del cliente.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para valorPorPropiedad");
    }

    /**
     * Reto Extra 5: cellValueFactory con lambda.
     * Crea una columna cuya factory es una lambda que extrae un valor con la función dada; devuelve ese valor para 'fila'.
     */
    public static <V> V valorConLambda(ClienteFx fila, Function<ClienteFx, V> extractor) {
        // GUÍA: teoría 3.3 (la lambda da control total: cd.getValue() es la fila; devuelves un ObservableValue).
        // 1. TableColumn<ClienteFx,V> col = new TableColumn<>().
        // 2. col.setCellValueFactory(cd -> new SimpleObjectProperty<>(extractor.apply(cd.getValue()))).
        // 3. return col.getCellObservableValue(fila).getValue().
        // PISTA: SimpleObjectProperty<>(valor) envuelve cualquier valor como ObservableValue.
        // OJO: la lambda recibe un CellDataFeatures (cd); cd.getValue() es la FILA, no la celda. El
        //   test usa extractor = ClienteFx::getNombre -> el nombre.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para valorConLambda");
    }

    /**
     * Reto Extra 6: Columna calculada (combina campos).
     * Crea una columna "nombre (edad)" combinando dos campos; devuelve su valor para 'fila'.
     */
    public static String valorCalculado(ClienteFx fila) {
        // GUÍA: teoría 3.3 (una columna puede mostrar un valor DERIVADO, no un campo directo).
        // 1. TableColumn<ClienteFx,String> col = new TableColumn<>("Resumen").
        // 2. col.setCellValueFactory(cd -> new SimpleObjectProperty<>(
        //        cd.getValue().getNombre() + " (" + cd.getValue().getEdad() + ")")).
        // 3. return col.getCellObservableValue(fila).getValue().
        // OJO: PropertyValueFactory NO sirve aquí (no hay una property "resumen"); por eso la lambda.
        //   El test con ("Ana", 30) espera "Ana (30)".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para valorCalculado");
    }

    /**
     * Reto Extra 7: Fila seleccionada.
     * Selecciona la fila en la posición dada y devuelve el objeto fila seleccionado.
     */
    public static ClienteFx filaSeleccionada(TableView<ClienteFx> tabla, int indice) {
        // GUÍA: teoría 3.4 (el SelectionModel de la tabla devuelve el OBJETO de fila, no un índice).
        // 1. tabla.getSelectionModel().select(indice). 2. return tabla.getSelectionModel().getSelectedItem().
        // OJO: getSelectedItem() te da el ClienteFx completo: por eso el maestro-detalle es directo
        //   (seleccionas una fila y rellenas un formulario con ese objeto).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para filaSeleccionada");
    }

    /**
     * Reto Extra 8: Política de redimensión.
     * Aplica la política que reparte el ancho entre columnas y devuelve true si quedó aplicada.
     */
    public static boolean ajustarColumnas(TableView<ClienteFx> tabla) {
        // GUÍA: teoría 3.5 (CONSTRAINED_RESIZE_POLICY hace que las columnas llenen el ancho sin hueco).
        // 1. tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY).
        // 2. return tabla.getColumnResizePolicy() == TableView.CONSTRAINED_RESIZE_POLICY.
        // OJO: la otra opción es UNCONSTRAINED_RESIZE_POLICY (por defecto), que deja hueco a la derecha.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ajustarColumnas");
    }

    /**
     * Reto Extra 9: Ordenar por una columna.
     * Pone la columna en el sortOrder ascendente, ordena la tabla y devuelve el nombre de la primera fila.
     */
    public static String ordenarPor(TableView<ClienteFx> tabla, TableColumn<ClienteFx, ?> columna) {
        // GUÍA: teoría 3.6 (sortOrder define por qué columnas y en qué orden se ordena la tabla).
        // 1. columna.setSortType(TableColumn.SortType.ASCENDING).
        // 2. tabla.getSortOrder().setAll(columna). 3. tabla.sort().
        // 4. return tabla.getItems().get(0).getNombre().
        // PISTA: tabla.sort() reordena tabla.getItems() según el sortOrder.
        // OJO: la columna debe tener cellValueFactory de un tipo Comparable (String, Integer); el
        //   test ordena por nombre y espera el primero alfabéticamente.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ordenarPor");
    }

    /**
     * Reto Extra 10: Celda reactiva.
     * Construye la columna "nombre", lee su valor para la fila, cambia la property del modelo y
     * vuelve a leer el MISMO ObservableValue: devuelve ese segundo valor (debe reflejar el cambio).
     */
    public static String celdaReactiva(ClienteFx fila, String nuevoNombre) {
        // GUÍA: teoría 3.7 (cuando la factory devuelve la PROPERTY real, la celda observa el modelo en vivo).
        // 1. TableColumn<ClienteFx,String> col = new TableColumn<>(); col.setCellValueFactory(cd -> cd.getValue().nombreProperty()).
        // 2. ObservableValue<String> obs = col.getCellObservableValue(fila). 3. lee obs.getValue() (valor viejo).
        // 4. fila.setNombre(nuevoNombre). 5. return obs.getValue() (debe ser el NUEVO).
        // PISTA: la diferencia con el reto 5 (SimpleObjectProperty) es que aquí devuelves la property
        //   VIVA del modelo, no una copia muerta del valor.
        // OJO: si hubieras usado new SimpleObjectProperty<>(getNombre()), el segundo getValue() seguiría
        //   dando el nombre viejo. Por eso la TableView refresca sola al editar (Ej282).
        // CULTURA: esta property viva es la que hace que editar un cliente en otra pantalla actualice la
        //   fila de la tabla al instante, sin recargar de la API (b05).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para celdaReactiva");
    }

    /** Helper de demostración: una lista de clientes de ejemplo. */
    static List<ClienteFx> muestra() {
        return List.of(new ClienteFx("Ana", "ana@correo.com", 30),
                new ClienteFx("Berta", "berta@correo.com", 25));
    }
}
