package com.masterclass.api.b36_fxstyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Ejercicio 288 · Pseudo-clases y estados: {@code :hover}, {@code :focused} y {@code PseudoClass} propia.
 *
 * <p>Teoría: {@code teoria/36_JavaFX_Estilo_Accesibilidad.md} (sección 2).
 *
 * <p>Una <b>pseudo-clase</b> es un estado que el control activa/desactiva solo: {@code :hover}
 * (ratón encima), {@code :focused} (tiene el foco), {@code :pressed} (pulsado), {@code :disabled}…
 * En la hoja escribes {@code .boton:hover { ... }} y JavaFX aplica esas reglas mientras el estado
 * esté activo. También puedes crear pseudo-clases <b>propias</b>
 * ({@code PseudoClass.getPseudoClass("error")}) y activarlas tú. Aquí trabajamos la lógica pura:
 * qué pseudo-clases están activas dado un estado, y cómo se escribe el selector correspondiente.
 */
public final class Ej288PseudoClassesStates {

    private Ej288PseudoClassesStates() {
    }

    /**
     * Devuelve, en orden de prioridad fijo, las pseudo-clases activas de un control según su
     * estado. Modela lo que JavaFX hace por dentro: ir marcando los estados que se cumplen.
     *
     * @param hover    el ratón está encima
     * @param focused  el control tiene el foco
     * @param disabled el control está deshabilitado
     * @return lista con ":hover", ":focused", ":disabled" para los activos; {@code List.of()} sin implementar
     */
    public static List<String> pseudoClasesActivas(boolean hover, boolean focused, boolean disabled) {
        // TODO 1: crea una List<String> mutable (new ArrayList<>()).
        // TODO 2: si 'hover' es true, añade ":hover" (con los dos puntos delante).
        // TODO 3: si 'focused' es true, añade ":focused".
        // TODO 4: si 'disabled' es true, añade ":disabled". Respeta este orden (lo comprueba el test).
        // TODO 5: devuelve la lista (caso límite: todo false -> lista vacía).
        return List.of();
    }

    /**
     * Construye el selector de una clase de estilo combinada con una pseudo-clase. Tolera que la
     * pseudo-clase venga ya con los dos puntos delante (los normaliza).
     *
     * @param styleClass clase base, p.ej. ".boton"
     * @param pseudo     pseudo-clase, p.ej. "hover" o ":hover"
     * @return el selector combinado, p.ej. ".boton:hover"; {@code ""} sin implementar
     */
    public static String selectorConPseudo(String styleClass, String pseudo) {
        // TODO 6: si 'pseudo' empieza por ":", quítale ese primer carácter (pseudo = pseudo.substring(1)).
        // TODO 7: así da igual que te lo pasen como "hover" o como ":hover" (lo normalizas a "hover").
        // TODO 8: concatena styleClass + ":" + pseudo.
        // TODO 9: NO metas espacios: ".boton:hover" va todo pegado (un espacio sería "descendiente").
        // TODO 10: devuelve el selector (caso límite del test: pseudo ya trae ":" -> mismo resultado).
        return "";
    }

    public static void main(String[] args) {
        System.out.println("Estado hover+focused -> " + pseudoClasesActivas(true, true, false));
        System.out.println("Selector .boton:hover -> " + selectorConPseudo(".boton", "hover"));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Añadir :hover.
     * Devuelve el selector con la pseudo-clase {@code :hover} añadida.
     */
    public static String conHover(String selector) {
        // GUÍA: teoría 2.1 (:hover = "mientras el ratón esté encima").
        // 1. return selector + ":hover";
        // OJO: el test espera ".boton:hover". :hover no necesita que actives nada tú: JavaFX lo gestiona.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para conHover");
    }

    /**
     * Reto Extra 2: Añadir un estado cualquiera.
     * Devuelve el selector con la pseudo-clase indicada (sin los dos puntos) añadida.
     */
    public static String conEstado(String selector, String estado) {
        // GUÍA: teoría 2.1 (estados estándar: hover, focused, pressed, selected, disabled...).
        // 1. return selector + ":" + estado;
        // OJO: el test usa "pressed" -> ".boton:pressed". :pressed = "mientras se mantiene pulsado".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para conEstado");
    }

    /**
     * Reto Extra 3: Normalizar el nombre de una pseudo-clase propia.
     * Convierte un texto libre en un nombre válido: minúsculas y espacios por guiones.
     */
    public static String nombrePseudoClasePropia(String texto) {
        // GUÍA: teoría 2.3 (PseudoClass.getPseudoClass(nombre) exige un nombre "css-válido").
        // 1. return texto.trim().toLowerCase().replace(" ", "-");
        // PISTA: "En Error" -> "en-error". Luego harías PseudoClass.getPseudoClass("en-error").
        // OJO: el caso límite del test ("Ya") debe quedar "ya": una sola palabra, sin guiones.
        //   Espacios o mayúsculas en el nombre rompen el selector en la hoja.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para nombrePseudoClasePropia");
    }

    /**
     * Reto Extra 4: Pseudo-clase propia activada o no.
     * Simula {@code pseudoClassStateChanged}: devuelve el nombre si está activa, "" si no.
     */
    public static String estaActiva(String nombre, boolean activa) {
        // GUÍA: teoría 2.3 (activar una pseudo-clase propia: node.pseudoClassStateChanged(pc, true)).
        // 1. return activa ? nombre : "";
        // PISTA: en JavaFX real harías: pseudoClassStateChanged(PseudoClass.getPseudoClass(nombre), activa).
        // OJO: el test comprueba ("error", true) -> "error" y ("error", false) -> "".
        //   Cuando 'activa' pasa a true, la hoja empieza a aplicar .campo:error al instante.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para estaActiva");
    }

    /**
     * Reto Extra 5: Pseudo-estado de un campo de formulario.
     * Devuelve "vacio", "valido" o "invalido" según el estado del campo (precedencia: vacío primero).
     */
    public static String pseudoEstadoCampo(boolean valido, boolean vacio) {
        // GUÍA: teoría 2.2 (un campo tiene varios estados a la vez; decides cuál manda).
        // 1. Si 'vacio' es true -> "vacio" (un campo vacío aún no se juzga válido/ inválido).
        // 2. Si no, devuelve "valido" o "invalido" según 'valido'.
        // OJO: el test prueba (true,false)->"valido", (false,false)->"invalido" y (cualquiera,true)->"vacio".
        // CULTURA: con esto Ej291 pinta el borde rojo solo cuando el usuario ya escribió algo erróneo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pseudoEstadoCampo");
    }

    /**
     * Reto Extra 6: Selector negado (:not).
     * Devuelve el selector funcional {@code :not(...)} sobre el selector dado.
     */
    public static String selectorNegado(String selector) {
        // GUÍA: teoría 2.4 (pseudo-clase funcional :not() = "todo menos lo que case esto").
        // 1. return ":not(" + selector + ")";
        // OJO: el test espera ":not(.boton)". Útil para "todos los botones que NO son primarios", etc.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para selectorNegado");
    }

    /**
     * Reto Extra 7: Encadenar varias pseudo-clases.
     * Pega al selector todas las pseudo-clases dadas, en orden, sin espacios.
     */
    public static String combinarPseudo(String selector, List<String> pseudos) {
        // GUÍA: teoría 2.4 (pseudo-clases encadenadas = "todas a la vez": .boton:hover:focused).
        // 1. Parte de 'selector' y, por cada p en pseudos, añade ":" + p.
        // 2. Devuelve el resultado.
        // PISTA: List.of("hover","focused") sobre ".boton" -> ".boton:hover:focused".
        // OJO: el caso límite es la lista vacía -> el selector tal cual, sin tocar.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para combinarPseudo");
    }

    /**
     * Reto Extra 8: Estado visual prioritario.
     * De entre los estados activos, devuelve el que "gana" visualmente (pressed > hover > focused).
     */
    public static String estadoPrioritario(Set<String> activos) {
        // GUÍA: teoría 2.2 (cuando varios estados coexisten, hay un orden de prioridad visual).
        // 1. Define la prioridad: {"pressed", "hover", "focused"} (de más a menos prioritario).
        // 2. Recórrela en ese orden y devuelve el PRIMERO que esté en 'activos'.
        // 3. Si no hay ninguno, devuelve "" (estado normal).
        // PISTA: for (String e : List.of("pressed","hover","focused")) if (activos.contains(e)) return e;
        // OJO: el test pasa {"hover","focused"} -> "hover" (hover gana a focused), y {} -> "".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para estadoPrioritario");
    }

    /**
     * Reto Extra 9: Transición de estado válida.
     * Indica si pasar del estado 'de' al estado 'a' es una transición permitida.
     */
    public static boolean transicionValida(String de, String a) {
        // GUÍA: teoría 2.5 (los estados de un control forman una máquina: no todo salto es legal).
        // 1. Define las transiciones permitidas (un Map<String, Set<String>>):
        //    "normal" -> {"hover"}, "hover" -> {"pressed","normal"}, "pressed" -> {"hover"}.
        // 2. Devuelve true si el conjunto de 'de' contiene 'a' (usa getOrDefault con Set.of()).
        // PISTA: return transiciones.getOrDefault(de, Set.of()).contains(a);
        // OJO: el test comprueba ("hover","pressed")->true y ("normal","pressed")->false
        //   (no puedes "pulsar" sin pasar antes por encima con el ratón).
        // CULTURA: esto es un stateDiagram; la verás formalizada en máquinas de estado y en animaciones.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para transicionValida");
    }

    /**
     * Reto Extra 10: Regla CSS de una pseudo-clase propia.
     * Genera la regla completa {@code .clase:pseudo { prop: valor; }} a partir de sus partes.
     */
    public static String cssPseudoClasePropia(String clase, String pseudo, Map<String, String> propiedades) {
        // GUÍA: teoría 2.3 (la pseudo-clase propia solo "pinta" si hay una regla en la hoja para ella).
        // 1. Construye el cuerpo: por cada par, "clave: valor;" separados por espacio.
        // 2. Devuelve clase + ":" + pseudo + " { " + cuerpo + " }".
        // PISTA: con .clase=".campo", pseudo="error", {-fx-border-color: red}
        //   -> ".campo:error { -fx-border-color: red; }". Respeta los espacios alrededor de las llaves.
        // OJO: el test usa un LinkedHashMap para fijar el orden; cuida los espacios " { " y " }".
        // CULTURA: activas la pseudo-clase desde Java (reto 4) y la PINTAS desde la hoja (este reto):
        //   las dos mitades del mismo mecanismo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cssPseudoClasePropia");
    }

    /** Helper de demostración: conjunto mutable de estados. */
    static Set<String> estados(String... e) {
        return new java.util.HashSet<>(List.of(e));
    }

    /** Helper de demostración: lista mutable. */
    static List<String> lista(String... e) {
        return new ArrayList<>(List.of(e));
    }
}
