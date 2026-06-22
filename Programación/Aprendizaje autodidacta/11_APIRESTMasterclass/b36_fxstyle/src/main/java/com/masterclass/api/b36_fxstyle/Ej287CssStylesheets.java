package com.masterclass.api.b36_fxstyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Ejercicio 287 · Hojas de estilo CSS: selectores por tipo/clase/id y {@code getStyleClass}.
 *
 * <p>Teoría: {@code teoria/36_JavaFX_Estilo_Accesibilidad.md} (sección 1).
 *
 * <p>En JavaFX el aspecto NO se escribe a mano en el código: se separa en una hoja {@code .css}
 * (como en la web), y cada nodo decide a qué reglas se acoge mediante su <b>lista de clases de
 * estilo</b> ({@code getStyleClass()}, una {@code ObservableList<String>}). Aquí practicamos la
 * mitad testeable y headless del asunto: la <b>lógica de presentación</b> —dado un estado del
 * modelo, ¿qué clase CSS le toca?— y la construcción de <b>selectores</b> como cadenas. No
 * pintamos nada: devolvemos los nombres de clase y los selectores que luego una hoja real usaría.
 */
public final class Ej287CssStylesheets {

    private Ej287CssStylesheets() {
    }

    /**
     * Decide la clase de estilo de una celda de saldo según su valor. Es el patrón "estado del
     * modelo → clase CSS": la hoja pinta {@code .saldo-negativo} en rojo, {@code .saldo-positivo}
     * en verde, etc., pero esa decisión vive en código testeable.
     *
     * @param saldo importe a clasificar
     * @return {@code "saldo-negativo"}, {@code "saldo-cero"} o {@code "saldo-positivo"};
     *         {@code ""} sin implementar
     */
    public static String claseSegunSaldo(double saldo) {
        // TODO 1: si saldo < 0, devuelve "saldo-negativo".
        // TODO 2: si saldo es exactamente 0, devuelve "saldo-cero" (este es el caso límite del test).
        // TODO 3: en cualquier otro caso (saldo > 0), devuelve "saldo-positivo".
        // TODO 4: cuida el orden de los if: comprueba primero < 0, luego == 0, y deja > 0 al final.
        // TODO 5: devuelve la cadena resultante (no añadas el punto: el punto es del SELECTOR, no de la clase).
        return "";
    }

    /**
     * Calcula la lista de clases de estilo que debe tener una fila según su estado. La primera
     * clase ("fila") es la base que SIEMPRE está; las demás se añaden si el estado lo pide. El
     * orden importa: imita cómo {@code getStyleClass()} acumula clases.
     *
     * @param seleccionada si la fila está seleccionada
     * @param destacada    si la fila está destacada
     * @return lista con "fila" y, en orden, las clases de estado activas; {@code List.of()} sin implementar
     */
    public static List<String> clasesDeFila(boolean seleccionada, boolean destacada) {
        // TODO 6: crea una List<String> mutable (new ArrayList<>()) y añade siempre "fila".
        // TODO 7: si 'seleccionada' es true, añade "fila-seleccionada".
        // TODO 8: si 'destacada' es true, añade "fila-destacada".
        // TODO 9: respeta el orden: primero seleccionada, luego destacada (el test compara la lista exacta).
        // TODO 10: devuelve la lista (el caso límite del test: ambas false -> solo ["fila"]).
        return List.of();
    }

    public static void main(String[] args) {
        System.out.println("Saldo -5  -> " + claseSegunSaldo(-5));
        System.out.println("Saldo  0  -> " + claseSegunSaldo(0));
        System.out.println("Saldo 12  -> " + claseSegunSaldo(12));
        System.out.println("Fila seleccionada+destacada -> " + clasesDeFila(true, true));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Selector por clase.
     * Devuelve el selector CSS de una clase de estilo (el de {@code getStyleClass}).
     */
    public static String selectorDeClase(String clase) {
        // GUÍA: teoría 1.2 (un selector por clase lleva un PUNTO delante: .boton-primario).
        // 1. return "." + clase;
        // OJO: la CLASE se guarda sin punto ("boton-primario"); el punto solo aparece en el SELECTOR
        //   de la hoja .css. Confundirlos es el error nº1 de quien viene de CSS web.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para selectorDeClase");
    }

    /**
     * Reto Extra 2: Selector por id.
     * Devuelve el selector CSS de un id (el de {@code setId}).
     */
    public static String selectorDeId(String id) {
        // GUÍA: teoría 1.2 (un selector por id lleva ALMOHADILLA delante: #boton-guardar).
        // 1. return "#" + id;
        // OJO: el id es ÚNICO en la escena (como en HTML); la clase puede repetirse en muchos nodos.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para selectorDeId");
    }

    /**
     * Reto Extra 3: Selector descendiente.
     * Devuelve el selector que casa un descendiente dentro de un ancestro (separados por espacio).
     */
    public static String selectorDescendiente(String claseAncestro, String claseDescendiente) {
        // GUÍA: teoría 1.3 (el ESPACIO entre dos selectores significa "descendiente de").
        // 1. return "." + claseAncestro + " ." + claseDescendiente;
        // PISTA: ".dialogo .boton" casa cualquier .boton que esté DENTRO de un .dialogo, a cualquier nivel.
        // OJO: el test espera EXACTAMENTE ".dialogo .boton" (con el espacio en medio y los dos puntos).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para selectorDescendiente");
    }

    /**
     * Reto Extra 4: Selector multiclase.
     * Une varias clases en un único selector que exige TODAS a la vez (sin espacios).
     */
    public static String selectorMulticlase(List<String> clases) {
        // GUÍA: teoría 1.3 (clases pegadas, SIN espacio, significan "que tenga todas estas clases").
        // 1. Construye un StringBuilder y, por cada clase, añade "." + clase.
        // 2. Devuelve el resultado: List.of("a","b","c") -> ".a.b.c".
        // PISTA: ".a.b.c" (pegado) != ".a .b .c" (descendiente, reto 3). El espacio lo cambia TODO.
        // OJO: el caso límite del test es la lista vacía -> "" (cadena vacía).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para selectorMulticlase");
    }

    /**
     * Reto Extra 5: Estilo inline.
     * Construye la cadena de estilo inline ({@code setStyle}) a partir de pares propiedad→valor.
     */
    public static String estiloInline(Map<String, String> propiedades) {
        // GUÍA: teoría 1.4 (setStyle aplica CSS directo al nodo: "-fx-text-fill: red; -fx-font-size: 14;").
        // 1. Recorre las entradas y, por cada una, genera "clave: valor;".
        // 2. Únelas separadas por un espacio: "-fx-text-fill: red; -fx-font-size: 14;".
        // PISTA: usa un LinkedHashMap en tu cabeza: el test pasa las claves EN ORDEN y espera ese orden.
        // OJO: cada par termina en ';'. El estilo inline GANA en especificidad a la hoja (es lo más fuerte),
        //   por eso conviene reservarlo para casos puntuales y no abusar de él.
        // CULTURA: es el equivalente al atributo style="..." del HTML; potente pero difícil de mantener.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para estiloInline");
    }

    /**
     * Reto Extra 6: Añadir clase sin duplicar.
     * Devuelve una nueva lista con la clase añadida, salvo que ya estuviera presente.
     */
    public static List<String> agregarClase(List<String> actuales, String nueva) {
        // GUÍA: teoría 1.5 (getStyleClass().add(...) NO impide duplicados; tú debes evitarlos).
        // 1. Copia 'actuales' a una lista mutable. 2. Si no contains(nueva), add(nueva). 3. Devuélvela.
        // OJO: el caso límite del test añade una clase que YA está -> la lista no debe crecer.
        //   Añadir la misma clase dos veces no rompe el render pero ensucia y confunde al depurar.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para agregarClase");
    }

    /**
     * Reto Extra 7: Quitar clase.
     * Devuelve una nueva lista sin la clase indicada (si estaba).
     */
    public static List<String> quitarClase(List<String> actuales, String aQuitar) {
        // GUÍA: teoría 1.5 (getStyleClass().remove(...) desactiva las reglas de esa clase).
        // 1. Copia 'actuales' a una lista mutable. 2. remove(aQuitar). 3. Devuélvela.
        // OJO: si la clase no estaba, remove no hace nada (no falla): la lista vuelve igual.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para quitarClase");
    }

    /**
     * Reto Extra 8: Alternar clase (toggle).
     * Si la clase está, la quita; si no está, la añade. Es el patrón de un botón "activo/inactivo".
     */
    public static List<String> alternarClase(List<String> actuales, String clase) {
        // GUÍA: teoría 1.5 (el "toggle" de clase es el motor de un interruptor visual).
        // 1. Copia 'actuales' a una lista mutable.
        // 2. Si contiene 'clase' -> remove; si no -> add.
        // PISTA: es el equivalente a element.classList.toggle(...) de la web.
        // OJO: el test lo llama dos veces; la segunda debe DESHACER la primera (idempotencia del toggle).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para alternarClase");
    }

    /**
     * Reto Extra 9: Especificidad: qué selector gana.
     * Dados dos selectores, devuelve el MÁS específico (el que la cascada aplica por encima).
     */
    public static String especificidadMayor(String selectorA, String selectorB) {
        // GUÍA: teoría 1.6 (la ESPECIFICIDAD decide qué regla gana cuando dos casan el mismo nodo).
        // 1. Puntúa cada selector: cada '#' (id) vale 100, cada '.' (clase) vale 10.
        // 2. Suma para A y para B (cuenta cuántos '#' y '.' tiene cada uno).
        // 3. Devuelve el selector con MÁS puntos; si empatan, devuelve selectorA.
        // PISTA: para contar, puedes recorrer la cadena o usar (s.length() - s.replace("#","").length()).
        // OJO: el test comprueba "#id" (100) gana a ".clase" (10), y ".a.b" (20) gana a ".c" (10).
        // CULTURA: misma idea que en CSS web; por eso un #id pisa a una .clase aunque vaya antes en la hoja.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para especificidadMayor");
    }

    /**
     * Reto Extra 10: Clase según el nivel de severidad.
     * Mapea un nivel de log/severidad a la clase CSS con la que se pintará el mensaje.
     */
    public static String claseSegunNivel(String nivel) {
        // GUÍA: teoría 1.6 (mismo patrón "estado -> clase" del core, pero con un enum de severidad).
        // 1. Normaliza con un switch sobre 'nivel': "ERROR"->"nivel-error", "WARN"->"nivel-aviso",
        //    "INFO"->"nivel-info".
        // 2. Cualquier otro valor -> "nivel-desconocido" (el caso por defecto, robusto).
        // PISTA: un switch con flecha (case "ERROR" -> ...) es lo más limpio aquí.
        // OJO: el test prueba "ERROR" y "INFO"; no olvides el default para entradas raras.
        // CULTURA: es exactamente cómo una consola colorea logs por nivel, y conecta con el manejo
        //   de errores que viste en b09 (RFC 7807): el "tipo" del problema decide su presentación.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para claseSegunNivel");
    }

    /** Helper de demostración: construye una lista mutable de clases. */
    static List<String> clases(String... cs) {
        return new ArrayList<>(List.of(cs));
    }
}
