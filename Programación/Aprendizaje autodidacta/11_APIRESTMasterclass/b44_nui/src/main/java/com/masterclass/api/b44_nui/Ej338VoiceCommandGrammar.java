package com.masterclass.api.b44_nui;

import java.util.List;
import java.util.Map;

/**
 * Ejercicio 338 · Gramática de comandos de voz: frase → intención + <em>slots</em>.
 *
 * <p>Reconocer voz tiene dos partes: el motor acústico (audio → texto, fuera de este Maven) y la
 * <strong>gramática</strong> que convierte ese texto en una <em>intención</em> ejecutable: una
 * acción ("abrir") más unos huecos o <em>slots</em> con datos ("el fichero ventas"). Esta segunda
 * parte es lógica pura y es la que se construye aquí.
 *
 * <p>Teoría: {@code teoria/44_Interfaces_Naturales.md} (sección 2).
 */
public final class Ej338VoiceCommandGrammar {

    private Ej338VoiceCommandGrammar() {
    }

    /**
     * Intención reconocida: una acción y sus slots (clave → valor). Inmutable (record).
     */
    public record Intencion(String accion, Map<String, String> slots) {
        /** Intención centinela cuando no se reconoce el comando. */
        public static Intencion desconocida() {
            return new Intencion("desconocida", Map.of());
        }
    }

    /**
     * Interpreta una frase hablada (ya transcrita) y devuelve la intención con sus slots.
     *
     * @param frase texto de la orden, p. ej. "abre el fichero ventas"
     * @return la {@link Intencion} reconocida; {@link Intencion#desconocida()} si no encaja
     */
    public static Intencion interpretarComando(String frase) {
        // TODO 1: si la frase es null o en blanco, devuelve Intencion.desconocida().
        // TODO 2: normaliza a minúsculas y recorta espacios.
        // TODO 3: parte la frase en tokens por espacios (String.split("\\s+")).
        // TODO 4: traduce el PRIMER token (el verbo) a una acción con accionDeVerbo(...).
        // TODO 5: si la acción es "" (verbo no reconocido), devuelve Intencion.desconocida().
        // TODO 6: busca un artículo ("el","la","los","las") y toma como slot "objeto" lo que va DETRÁS.
        // TODO 7: construye y devuelve new Intencion(accion, Map.of("objeto", resto)) — o sin slot si no hay objeto.
        return Intencion.desconocida();
    }

    /**
     * Traduce un verbo hablado a la acción canónica de la aplicación.
     *
     * @param verbo verbo en imperativo, p. ej. "abre"
     * @return la acción ("abrir", "cerrar", ...); cadena vacía si el verbo no se reconoce
     */
    public static String accionDeVerbo(String verbo) {
        // TODO 8: si el verbo es null/blanco, devuelve "".
        // TODO 9: mapea abre->abrir, cierra->cerrar, guarda->guardar, muestra->mostrar,
        //         borra->borrar, ordena->ordenar (usa un switch sobre el verbo normalizado).
        // TODO 10: cualquier otro verbo -> "" (centinela: el comando no pertenece a la gramática).
        return "";
    }

    public static void main(String[] args) {
        System.out.println("Intención de 'abre el fichero ventas': " + interpretarComando("abre el fichero ventas"));
        System.out.println("Acción de 'guarda': " + accionDeVerbo("guarda"));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Sinónimos de "abrir".
     * Varios verbos significan lo mismo: "abre", "muestra", "despliega" → todos abren.
     */
    public static boolean esSinonimoDeAbrir(String verbo) {
        // GUÍA: teoría 2 (la gramática mapea MUCHAS formas habladas a UNA intención).
        // 1. Normaliza el verbo y comprueba si está en {abre, muestra, despliega}.
        // PISTA: List.of("abre","muestra","despliega").contains(verbo.strip().toLowerCase()).
        // OJO: el test: "muestra"->true; "cierra"->false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esSinonimoDeAbrir");
    }

    /**
     * Reto Extra 2: Eliminar palabras vacías (stop words).
     * Quita artículos y preposiciones que no aportan a la intención.
     */
    public static List<String> eliminarPalabrasVacias(List<String> tokens) {
        // GUÍA: teoría 2 (las stop words {el,la,los,las,de,un,una} solo son relleno gramatical).
        // 1. Lista null -> List.of().
        // 2. Filtra los tokens que NO estén en el conjunto de stop words.
        // PISTA: Set.of("el","la","los","las","de","un","una"); stream().filter(...).toList().
        // OJO: el test: ["abre","el","fichero"]->["abre","fichero"]; ["de","la"]->[].
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para eliminarPalabrasVacias");
    }

    /**
     * Reto Extra 3: Comando compuesto.
     * "abre y cierra" son dos órdenes: devuelve la acción de cada parte separada por " y ".
     */
    public static List<String> interpretarComandoCompuesto(String frase) {
        // GUÍA: teoría 2 (un comando compuesto encadena intenciones; se parte por el conector " y ").
        // 1. Frase null/blanco -> List.of().
        // 2. Parte por " y "; de cada trozo toma el PRIMER token y mapea con accionDeVerbo(...).
        // PISTA: frase.split(" y "); por trozo, trozo.strip().split("\\s+")[0].
        // OJO: el test: "abre y cierra"->["abrir","cerrar"]; "" -> [].
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para interpretarComandoCompuesto");
    }

    /**
     * Reto Extra 4: Detectar el idioma del comando.
     * "es" si usa verbos en español; "en" si usa verbos en inglés (open/close).
     */
    public static String detectarIdioma(String frase) {
        // GUÍA: teoría 2 (la misma intención en dos idiomas: hay que elegir la gramática correcta).
        // 1. Normaliza. Si contiene "open" o "close" -> "en".
        // 2. En cualquier otro caso -> "es" (idioma por defecto del sistema).
        // OJO: el test: "open file"->"en"; "abre fichero"->"es".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para detectarIdioma");
    }

    /**
     * Reto Extra 5: Corregir un typo por distancia de edición.
     * Devuelve la palabra del vocabulario más parecida a la dada (mínima distancia de Levenshtein).
     */
    public static String corregirTypo(String palabra, List<String> vocabulario) {
        // GUÍA: teoría 2 + Ej339 (el motor de voz comete errores; se corrige al término más cercano).
        // 1. Vocabulario null/vacío -> "".
        // 2. Calcula la distancia de Levenshtein a cada palabra y quédate con la MENOR.
        // PISTA: implementa Levenshtein con una matriz (i,j) = min(borrar, insertar, sustituir).
        // OJO: el test: ("abre",["abre","cierra"])->"abre" (distancia 0); vocabulario vacío -> "".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para corregirTypo");
    }

    /**
     * Reto Extra 6: Slot opcional con valor por defecto.
     * Si el comando no trae el slot, se usa un valor por defecto.
     */
    public static String slotOpcionalConDefecto(Map<String, String> slots, String clave, String defecto) {
        // GUÍA: teoría 2 ("guarda" sin destino -> usa la carpeta por defecto).
        // 1. slots.getOrDefault(clave, defecto). Cuida slots null (trátalo como sin valor).
        // OJO: el test: ({objeto=x},"objeto","def")->"x"; ({},"objeto","def")->"def".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para slotOpcionalConDefecto");
    }

    /**
     * Reto Extra 7: La gramática como TABLA DE DATOS, no como cadena de ifs.
     * Resuelve la acción consultando un mapa verbo→acción configurable.
     */
    public static String accionDesdeGramatica(String verbo, Map<String, String> gramatica) {
        // GUÍA: teoría 2 (gramática guiada por datos = se amplía sin tocar el código; patrón tabla).
        // 1. gramatica.getOrDefault(verbo, "").
        // OJO: el test: ("abre",{abre=abrir})->"abrir"; ("zzz",{})->"".
        // CULTURA: así funcionan los frameworks de intents reales: un fichero declara la gramática.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para accionDesdeGramatica");
    }

    /**
     * Reto Extra 8: Prioridad del comando.
     * Acciones destructivas tienen más prioridad para confirmarse antes.
     */
    public static int prioridadComando(String accion) {
        // GUÍA: teoría 2 (no todas las órdenes pesan igual: "borrar" pide más cautela que "mostrar").
        // 1. "borrar"->3, "guardar"->2, "" (sin acción)->0, cualquier otra->1.
        // OJO: el test: "borrar"->3; ""->0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para prioridadComando");
    }

    /**
     * Reto Extra 9: Comando contextual.
     * La misma acción significa cosas distintas según la vista activa: se etiqueta "accion@vista".
     */
    public static String comandoContextual(String accion, String vistaActiva) {
        // GUÍA: teoría 2 ("guardar" en el editor != "guardar" en ajustes: el contexto desambigua).
        // 1. Si vistaActiva es null/blanco -> devuelve solo accion.
        // 2. Si no -> accion + "@" + vistaActiva.
        // OJO: el test: ("guardar","editor")->"guardar@editor"; ("guardar","")->"guardar".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para comandoContextual");
    }

    /**
     * Reto Extra 10: La intención dispara un evento de UI.
     * Traduce la intención al nombre del evento que la vista escuchará (enlaza con b34).
     */
    public static String disparaEventoUi(Intencion intencion) {
        // GUÍA: teoría 2 + b34 (eventos): la intención reconocida acaba en un onAction de JavaFX.
        // 1. Según intencion.accion(): "abrir"->"onOpen", "cerrar"->"onClose", "guardar"->"onSave".
        // 2. Cualquier otra (incl. "desconocida") -> "".
        // PISTA: usa un switch sobre intencion.accion().
        // OJO: el test: Intencion("abrir",{})->"onOpen"; Intencion.desconocida()->"".
        // CULTURA: esto es justo el puente NUI->UI: la voz acaba disparando el mismo EventHandler
        //   que pulsar un botón (b34 Ej273).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para disparaEventoUi");
    }
}
