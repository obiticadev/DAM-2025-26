package com.masterclass.api.b35_fxdata;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

/**
 * Ejercicio 279 · Colecciones observables: {@code ObservableList}, {@code FXCollections} y escuchar
 * cambios.
 *
 * <p>Teoría: {@code teoria/35_JavaFX_Datos_Async.md} (sección 1.1).
 *
 * <p>Una {@code ObservableList} es "una lista que avisa cuando cambia": añadir, quitar o reemplazar
 * un elemento dispara una notificación. Es el ladrillo de las tablas y listas de JavaFX (Ej280+):
 * el control se suscribe a la lista del modelo y se repinta solo. Aquí todo es lógica pura sobre
 * colecciones (no toca pantalla), pero como construir y mutar estas listas conviene hacerlo con el
 * toolkit vivo, el test lo arranca con {@code IniciadorFx}.
 */
public final class Ej279ObservableCollections {

    private Ej279ObservableCollections() {
    }

    /**
     * Devuelve, en orden, los elementos que un {@code ListChangeListener} reporta como AÑADIDOS al
     * ir agregando la secuencia dada a la lista observable. Demuestra que la lista notifica cada
     * inserción con detalle (qué se añadió).
     *
     * @param lista  lista observable de partida (puede traer elementos)
     * @param nuevos elementos que se irán añadiendo, uno a uno
     * @return la lista de elementos notificados como añadidos; {@code List.of()} sin implementar
     */
    public static List<String> agregadosNotificados(ObservableList<String> lista, List<String> nuevos) {
        // TODO 1: crea una List<String> 'reporte' donde apuntar lo que el listener marque como añadido.
        // TODO 2: añade un ListChangeListener a 'lista' (lista.addListener(cambio -> { ... })).
        // TODO 3: dentro, recorre los bloques con while (cambio.next()) y, si cambio.wasAdded(),
        //         vuelca cambio.getAddedSubList() en 'reporte' (reporte.addAll(...)).
        // TODO 4: recorre 'nuevos' y haz lista.add(n) con cada uno (cada add dispara el listener).
        // TODO 5: devuelve 'reporte'.
        return List.of();
    }

    /**
     * Envuelve la lista observable en una {@link FilteredList} con el predicado dado. La vista
     * resultante es VIVA: si después se muta la lista fuente, la filtrada se actualiza sola.
     *
     * @param fuente    lista observable original
     * @param predicado condición que deben cumplir los elementos visibles
     * @return la {@code FilteredList} enlazada a la fuente; {@code null} sin implementar
     */
    public static FilteredList<String> filtrarObservable(ObservableList<String> fuente, Predicate<String> predicado) {
        // TODO 6: una FilteredList recibe la fuente y un java.util.function.Predicate en el constructor.
        // TODO 7: crea new FilteredList<>(fuente, predicado).
        // TODO 8: NO copies los elementos a mano: la FilteredList observa la fuente por ti.
        // TODO 9: si 'fuente' cambia luego (add/remove), el tamaño de la filtrada cambia solo.
        // TODO 10: devuelve la FilteredList (el test añadirá a la fuente y comprobará que crece).
        return null;
    }

    public static void main(String[] args) {
        ObservableList<String> lista = FXCollections.observableArrayList("Ana");
        System.out.println("Añadidos: " + agregadosNotificados(lista, List.of("Berta", "Caro")));

        ObservableList<String> fuente = FXCollections.observableArrayList("a", "bb", "ccc");
        FilteredList<String> filtrada = filtrarObservable(fuente, s -> s.length() > 1);
        System.out.println("Filtrada (>1): " + filtrada);
        fuente.add("dddd");
        System.out.println("Tras añadir 'dddd': " + filtrada);
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Crear una lista observable.
     * Devuelve una {@link ObservableList} construida con los elementos dados.
     */
    public static ObservableList<String> crearObservable(List<String> items) {
        // GUÍA: teoría 1.1 (FXCollections es la fábrica de colecciones observables).
        // 1. return FXCollections.observableArrayList(items);
        // OJO: NO uses new ArrayList<>: ese no notifica. Una lista normal no sirve para una tabla.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearObservable");
    }

    /**
     * Reto Extra 2: Tamaño tras añadir.
     * Añade el elemento a la lista observable y devuelve su tamaño resultante.
     */
    public static int tamanoTrasAgregar(ObservableList<String> lista, String elemento) {
        // GUÍA: teoría 1.1 (una ObservableList se usa como una List normal: add/remove/size).
        // 1. lista.add(elemento). 2. return lista.size().
        // OJO: la gracia no es el tamaño, es que ese add ya ha avisado a quien la observe (Ej280/281).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tamanoTrasAgregar");
    }

    /**
     * Reto Extra 3: Vista de solo lectura.
     * Devuelve una vista inmodificable de la lista (intentar mutarla debe lanzar excepción).
     */
    public static ObservableList<String> soloLectura(ObservableList<String> lista) {
        // GUÍA: teoría 1.1 (unmodifiableObservableList protege tu lista del exterior).
        // 1. return FXCollections.unmodifiableObservableList(lista);
        // PISTA: sigue siendo observable (notifica cambios de la original) pero rechaza add/remove.
        // OJO: el test hace add() sobre la vista y espera UnsupportedOperationException.
        //   Patrón típico: expones la lista al exterior en solo lectura y la mutas tú por dentro.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para soloLectura");
    }

    /**
     * Reto Extra 4: Ordenar en el sitio.
     * Ordena la lista observable de forma natural (in-place) y la devuelve.
     */
    public static ObservableList<String> ordenarEnSitio(ObservableList<String> lista) {
        // GUÍA: teoría 1.1 (FXCollections.sort ordena la MISMA lista y emite un solo cambio).
        // 1. FXCollections.sort(lista). 2. return lista.
        // OJO: ordena la lista original (mutación), no devuelve una copia. El test pasa ["c","a","b"]
        //   y espera ["a","b","c"]. Para ordenar SIN tocar la fuente, usa SortedList (reto 9).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ordenarEnSitio");
    }

    /**
     * Reto Extra 5: Invertir el orden.
     * Invierte la lista observable in-place y la devuelve.
     */
    public static ObservableList<String> invertir(ObservableList<String> lista) {
        // GUÍA: teoría 1.1 (FXCollections tiene utilidades estilo Collections: reverse, fill, replaceAll).
        // 1. FXCollections.reverse(lista). 2. return lista.
        // OJO: el test pasa ["a","b","c"] y espera ["c","b","a"].
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para invertir");
    }

    /**
     * Reto Extra 6: Contar eliminaciones.
     * Cuenta cuántos elementos reporta el listener como ELIMINADOS al quitar la secuencia dada.
     */
    public static int contarEliminaciones(ObservableList<String> lista, List<String> aQuitar) {
        // GUÍA: teoría 1.2 (ListChangeListener distingue altas de bajas: wasAdded vs wasRemoved).
        // 1. int[] c = {0}. 2. lista.addListener((ListChangeListener<String>) cambio -> {
        //        while (cambio.next()) if (cambio.wasRemoved()) c[0] += cambio.getRemovedSize(); }).
        // 3. Por cada x en aQuitar: lista.remove(x). 4. return c[0].
        // PISTA: cambio.getRemovedSize() te da cuántos se borraron en ese bloque.
        // OJO: el test quita 2 elementos presentes -> 2. wasAdded y wasRemoved pueden darse a la vez
        //   en un replace; aquí solo borramos, así que cuenta limpio.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarEliminaciones");
    }

    /**
     * Reto Extra 7: Filtrar y contar (vista viva).
     * Devuelve cuántos elementos de la fuente cumplen el predicado, usando una {@link FilteredList}.
     */
    public static int contarFiltrados(ObservableList<String> fuente, Predicate<String> predicado) {
        // GUÍA: teoría 1.3 (FilteredList es una VISTA: no copia, refleja la fuente filtrada).
        // 1. FilteredList<String> f = new FilteredList<>(fuente, predicado). 2. return f.size().
        // OJO: f.size() cuenta solo los visibles. El test filtra longitud>2 sobre ["a","bb","ccc"] -> 1.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarFiltrados");
    }

    /**
     * Reto Extra 8: Cambiar el predicado en caliente.
     * Sobre una {@link FilteredList} ya creada, cambia su predicado y devuelve su nuevo tamaño.
     */
    public static int recontarTrasPredicado(ObservableList<String> fuente, Predicate<String> nuevoPredicado) {
        // GUÍA: teoría 1.3 (setPredicate re-filtra al vuelo; así funciona un buscador en vivo).
        // 1. FilteredList<String> f = new FilteredList<>(fuente, s -> true) (al principio: todos).
        // 2. f.setPredicate(nuevoPredicado). 3. return f.size().
        // PISTA: el predicado de una FilteredList es una Property; cambiarlo re-evalúa la lista.
        // OJO: esto es EXACTAMENTE lo que hace una caja de búsqueda (Ej282): al teclear, llamas a
        //   setPredicate con el filtro nuevo y la tabla se re-filtra sola.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para recontarTrasPredicado");
    }

    /**
     * Reto Extra 9: Orden ligado sin tocar la fuente.
     * Devuelve una {@link SortedList} que muestra la fuente ordenada, dejando la fuente intacta.
     */
    public static SortedList<String> ordenarLigado(ObservableList<String> fuente) {
        // GUÍA: teoría 1.3 (SortedList es otra VISTA: ordena para mostrar, sin mutar el modelo).
        // 1. return new SortedList<>(fuente, java.util.Comparator.naturalOrder());
        // PISTA: el primer elemento de la SortedList es el menor, aunque en la fuente no esté primero.
        // OJO: el test comprueba que sorted.get(0) es el menor PERO que fuente.get(0) sigue siendo el
        //   original (la fuente NO se reordena). FilteredList + SortedList se encadenan en Ej282.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ordenarLigado");
    }

    /**
     * Reto Extra 10: Lista con extractor (notifica cambios de propiedad).
     * Construye una lista observable con extractor sobre {@link Fila} y cuenta las notificaciones
     * que produce cambiar la property de un elemento YA contenido.
     */
    public static int notificacionesConExtractor(List<Fila> filas, int indice, String nuevoValor) {
        // GUÍA: teoría 1.4 (sin extractor, la lista solo avisa de add/remove; con extractor, también
        //   cuando cambia una PROPERTY de un elemento que ya está dentro).
        // 1. Define el extractor: Callback<Fila, Observable[]> ext = f -> new Observable[]{ f.textoProperty() }.
        // 2. ObservableList<Fila> lista = FXCollections.observableArrayList(ext); lista.addAll(filas).
        // 3. int[] c = {0}; lista.addListener((ListChangeListener<Fila>) ch -> { while (ch.next())
        //        if (ch.wasUpdated()) c[0]++; }).
        // 4. Cambia la property: lista.get(indice).textoProperty().set(nuevoValor). 5. return c[0].
        // PISTA: el bloque de cambio de un extractor es wasUpdated() (¡no wasAdded!).
        // OJO: sin extractor, c[0] sería 0 (la lista no se entera de cambios internos). Con extractor
        //   es 1. El test cambia un valor real -> 1.
        // CULTURA: esto es lo que permite que una TableView (Ej281) refresque UNA celda cuando cambia
        //   ese campo del modelo, sin recargar toda la tabla. Es el motor del "edita y se ve al instante".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para notificacionesConExtractor");
    }

    /** Fila de ejemplo con una property observable, para el reto del extractor. */
    public static final class Fila {
        private final StringProperty texto = new SimpleStringProperty(this, "texto", "");

        public Fila(String texto) {
            this.texto.set(texto);
        }

        public String getTexto() {
            return texto.get();
        }

        public StringProperty textoProperty() {
            return texto;
        }
    }

    /** Helper de demostración: lista observable a partir de varios elementos. */
    static ObservableList<String> de(String... items) {
        return FXCollections.observableArrayList(new ArrayList<>(List.of(items)));
    }
}
