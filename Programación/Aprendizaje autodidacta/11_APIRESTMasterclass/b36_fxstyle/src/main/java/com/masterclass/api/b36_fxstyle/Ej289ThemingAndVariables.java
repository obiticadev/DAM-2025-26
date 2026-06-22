package com.masterclass.api.b36_fxstyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Ejercicio 289 · Temas claro/oscuro, colores nombrados ({@code looked-up colors}) y cambio en caliente.
 *
 * <p>Teoría: {@code teoria/36_JavaFX_Estilo_Accesibilidad.md} (sección 3).
 *
 * <p>Un <b>tema</b> es un conjunto coherente de colores y medidas. En JavaFX se implementa con
 * <b>colores nombrados</b> ({@code -color-fondo: #fff;} definidos en {@code .root}) que el resto de
 * reglas <b>referencian por nombre</b>. Cambiar de tema en caliente es tan simple como sustituir la
 * hoja {@code .css}: como nada usa valores fijos, toda la interfaz se repinta sola. Aquí trabajamos
 * la lógica: alternar el tema, resolver qué hoja aplicar y construir las definiciones de color.
 */
public final class Ej289ThemingAndVariables {

    private Ej289ThemingAndVariables() {
    }

    /**
     * Alterna entre el tema claro y el oscuro. Es el corazón del botón "modo noche".
     *
     * @param actual tema vigente: "claro" u "oscuro"
     * @return el tema contrario; ante un valor desconocido, "claro" (estado seguro);
     *         {@code ""} sin implementar
     */
    public static String alternarTema(String actual) {
        // TODO 1: si 'actual' es "oscuro", devuelve "claro".
        // TODO 2: si 'actual' es "claro", devuelve "oscuro".
        // TODO 3: piensa el caso límite: ¿y si llega "raro" o null-equivalente?
        // TODO 4: ante cualquier otro valor, devuelve "claro" (un punto de partida sensato).
        // TODO 5: devuelve la cadena (el test prueba claro->oscuro, oscuro->claro y "raro"->claro).
        return "";
    }

    /**
     * Resuelve la ruta de la hoja de estilo que corresponde a un tema. Esa ruta es la que se pasa a
     * {@code scene.getStylesheets().add(...)}.
     *
     * @param tema "claro" u "oscuro"
     * @return ruta de recurso de la hoja; para temas desconocidos, la del tema claro;
     *         {@code ""} sin implementar
     */
    public static String hojaDeTema(String tema) {
        // TODO 6: si 'tema' es "oscuro", devuelve "/css/tema-oscuro.css".
        // TODO 7: si 'tema' es "claro", devuelve "/css/tema-claro.css".
        // TODO 8: para cualquier otro valor, devuelve "/css/tema-claro.css" (degradación elegante).
        // TODO 9: la ruta empieza por "/" -> es ABSOLUTA desde la raíz del classpath (src/main/resources).
        // TODO 10: devuelve la ruta (esta cadena es la que getStylesheets().add(...) consume).
        return "";
    }

    public static void main(String[] args) {
        String tema = "claro";
        System.out.println("Tema actual: " + tema + " -> hoja " + hojaDeTema(tema));
        tema = alternarTema(tema);
        System.out.println("Tras alternar: " + tema + " -> hoja " + hojaDeTema(tema));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Definir un color nombrado.
     * Devuelve la definición CSS de un looked-up color: {@code nombre: valor;}.
     */
    public static String definirColor(String nombre, String valor) {
        // GUÍA: teoría 3.1 (en .root defines -color-fondo: #fff; y luego lo reutilizas por nombre).
        // 1. return nombre + ": " + valor + ";";
        // OJO: el test espera "-color-fondo: #ffffff;". Por convención los colores propios empiezan por "-".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para definirColor");
    }

    /**
     * Reto Extra 2: Usar un color nombrado.
     * Devuelve una declaración que asigna a una propiedad el valor de un color nombrado.
     */
    public static String usarColor(String propiedad, String nombreColor) {
        // GUÍA: teoría 3.1 (referencias el color por su nombre, no repites el valor hexadecimal).
        // 1. return propiedad + ": " + nombreColor + ";";
        // PISTA: "-fx-background-color: -color-fondo;". Si cambias -color-fondo, esto cambia solo.
        // OJO: la diferencia con el reto 1 es semántica: ahí DEFINES el color, aquí lo USAS.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para usarColor");
    }

    /**
     * Reto Extra 3: Derivar un color.
     * Devuelve la función {@code derive(color, porcentaje%)} que aclara/oscurece un color.
     */
    public static String derivar(String color, int porcentaje) {
        // GUÍA: teoría 3.2 (derive() es una función CSS de JavaFX: +% aclara, -% oscurece).
        // 1. return "derive(" + color + ", " + porcentaje + "%)";
        // OJO: el test espera "derive(-color-acento, -20%)". Con derive evitas definir 5 tonos a mano:
        //   defines uno y generas las variantes (hover más oscuro, deshabilitado más claro...).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para derivar");
    }

    /**
     * Reto Extra 4: Gradiente lineal.
     * Devuelve un {@code linear-gradient} vertical entre dos colores.
     */
    public static String gradienteLineal(String colorArriba, String colorAbajo) {
        // GUÍA: teoría 3.2 (linear-gradient pinta una transición de color, igual que en CSS web).
        // 1. return "linear-gradient(to bottom, " + colorArriba + ", " + colorAbajo + ")";
        // OJO: el test espera "linear-gradient(to bottom, #ffffff, #000000)".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para gradienteLineal");
    }

    /**
     * Reto Extra 5: Siguiente tema en un ciclo de tres.
     * Cicla claro → oscuro → alto-contraste → claro.
     */
    public static String temaSiguiente(String tema) {
        // GUÍA: teoría 3.3 (no solo claro/oscuro: "alto contraste" es un tema de ACCESIBILIDAD).
        // 1. switch (tema): "claro"->"oscuro", "oscuro"->"alto-contraste", "alto-contraste"->"claro".
        // 2. default -> "claro".
        // OJO: el test prueba "oscuro"->"alto-contraste" y "alto-contraste"->"claro" (cierra el ciclo).
        // CULTURA: el tema de alto contraste lo exige la accesibilidad (Ej290) para baja visión.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para temaSiguiente");
    }

    /**
     * Reto Extra 6: Clase del nodo raíz para el tema.
     * Devuelve la clase de estilo que se pone en {@code .root} para marcar el tema activo.
     */
    public static String claseRaizTema(String tema) {
        // GUÍA: teoría 3.3 (otra forma de tematizar: una sola hoja con .root.tema-oscuro { ... }).
        // 1. return "tema-" + tema;
        // OJO: el test espera "tema-oscuro" para "oscuro". Así, en vez de cambiar la HOJA, cambias la
        //   CLASE del root y la misma hoja contiene las reglas de ambos temas (.root.tema-oscuro ...).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para claseRaizTema");
    }

    /**
     * Reto Extra 7: Lista de hojas con el tema al final.
     * Devuelve la lista de hojas a aplicar: las comunes y, AL FINAL, la del tema.
     */
    public static List<String> hojasConTema(List<String> hojasComunes, String tema) {
        // GUÍA: teoría 3.4 (el ORDEN de las hojas importa: la última gana en empates de cascada).
        // 1. Copia 'hojasComunes' a una lista mutable. 2. Añade al final hojaDeTema(tema). 3. Devuélvela.
        // PISTA: puedes reutilizar tu propio core hojaDeTema(tema).
        // OJO: el tema va el ÚLTIMO para poder SOBREESCRIBIR colores de la hoja base. Si lo pusieras
        //   primero, la base lo pisaría. El test comprueba ["/css/base.css", "/css/tema-oscuro.css"].
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para hojasConTema");
    }

    /**
     * Reto Extra 8: Color de texto legible sobre el fondo del tema.
     * Devuelve "white" para fondos oscuros y "black" para fondos claros.
     */
    public static String colorTexto(String tema) {
        // GUÍA: teoría 3.5 (el texto debe CONTRASTAR con el fondo; esto enlaza con WCAG en Ej290).
        // 1. return tema.equals("oscuro") ? "white" : "black";
        // OJO: el test prueba "oscuro"->"white", "claro"->"black". Texto gris sobre gris = ilegible
        //   e inaccesible: el contraste no es estético, es accesibilidad.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para colorTexto");
    }

    /**
     * Reto Extra 9: Tabla de colores nombrados de un tema.
     * Devuelve el mapa de colores ({@code -color-fondo}, {@code -color-texto}) para el tema dado.
     */
    public static Map<String, String> variablesTema(String tema) {
        // GUÍA: teoría 3.5 (cada tema es, en el fondo, un DICCIONARIO de tokens de color).
        // 1. Si 'tema' es "oscuro": Map.of("-color-fondo","#1e1e1e", "-color-texto","#f0f0f0").
        // 2. En otro caso (claro):  Map.of("-color-fondo","#ffffff", "-color-texto","#1c1c1c").
        // OJO: el test compara el Map completo; usa exactamente esos hex. Map.of no garantiza orden,
        //   pero equals de Map compara por contenido, así que da igual.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para variablesTema");
    }

    /**
     * Reto Extra 10: Bloque CSS de {@code .root} para un tema.
     * Construye {@code .root { -color-fondo: #...; ... }} a partir del mapa de variables.
     */
    public static String cssRaizTema(String tema, Map<String, String> variables) {
        // GUÍA: teoría 3.6 (juntas todo: un tema -> su .root con los colores nombrados definidos).
        // 1. Construye el cuerpo: por cada par, "clave: valor;" separados por espacio (reutiliza la idea
        //    de definirColor del reto 1).
        // 2. Devuelve ".root { " + cuerpo + " }".
        // PISTA: con un LinkedHashMap {-color-fondo: #1e1e1e} -> ".root { -color-fondo: #1e1e1e; }".
        // OJO: el test usa LinkedHashMap para fijar el orden; cuida los espacios " { " y " }".
        // CULTURA: estos colores nombrados son los "design tokens" del diseño moderno y el equivalente
        //   a las CSS custom properties (var(--x)) de la web: defines una paleta y la reutilizas.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cssRaizTema");
    }

    /** Helper de demostración: lista mutable. */
    static List<String> hojas(String... h) {
        return new ArrayList<>(List.of(h));
    }
}
