package com.masterclass.api.b42_mobile;

import java.util.List;
import java.util.Map;

/**
 * Ejercicio 327 · Layouts y vistas: Android vs JavaFX y unidades dp/sp.
 *
 * <p>Teoría: {@code teoria/42_Movil_Android.md} (sección 3).
 *
 * <p>Una pantalla Android es un <strong>árbol de vistas</strong> (un {@code ViewGroup} que contiene
 * {@code View}s), igual que una escena JavaFX es un árbol de {@code Node}s (b32). Los contenedores
 * tienen equivalentes casi 1:1 ({@code LinearLayout} vertical ≈ {@code VBox}). Y lo más importante
 * para no romper la UI en pantallas distintas: Android no mide en píxeles crudos sino en
 * <strong>dp</strong> (density-independent pixels): {@code px = dp × dpi/160}. Aquí modelamos el
 * mapa de equivalencias y la conversión de unidades como funciones puras; {@code findViewById} se
 * simula como un lookup en un {@code Map}, hermano del {@code lookup("#id")} de FXML (b34).
 */
public final class Ej327LayoutsAndViews {

    private Ej327LayoutsAndViews() {
    }

    /**
     * Equivalente JavaFX de un contenedor de layout Android.
     *
     * @param layoutAndroid nombre del contenedor Android (p.ej. {@code "LinearLayoutVertical"})
     * @return el panel JavaFX equivalente, o {@code ""} si no se reconoce
     */
    public static String equivalenteJavaFx(String layoutAndroid) {
        // TODO 1: si layoutAndroid es null/blank -> "".
        // TODO 2: "LinearLayoutVertical" -> "VBox"; "LinearLayoutHorizontal" -> "HBox" (apila en un eje).
        // TODO 3: "FrameLayout" -> "StackPane" (apila hijos uno encima de otro, z-order).
        // TODO 4: "RelativeLayout" y "ConstraintLayout" -> "AnchorPane" (posicionar por anclas/relaciones).
        // TODO 5: "GridLayout" -> "GridPane"; "ScrollView" -> "ScrollPane"; cualquier otro -> "".
        return "";
    }

    /**
     * Convierte dp (density-independent pixels) a píxeles físicos para una densidad dada.
     *
     * @param dp  medida en dp (no negativa)
     * @param dpi densidad de la pantalla en puntos por pulgada (160 = densidad base "mdpi")
     * @return píxeles físicos redondeados al entero más cercano; 0 si dp es 0
     */
    public static int dpAPx(double dp, double dpi) {
        // TODO 6: la densidad BASE de Android es 160 dpi: a 160 dpi, 1 dp = 1 px (punto de anclaje).
        // TODO 7: el factor de escala es dpi/160 (a 320 dpi un dp ocupa 2 px).
        // TODO 8: multiplica dp por ese factor para obtener los píxeles físicos.
        // TODO 9: redondea al entero más cercano con Math.round (px es siempre entero).
        // TODO 10: devuelve el resultado como int (ojo: Math.round(double) da long -> castéalo a int).
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("LinearLayoutVertical -> " + equivalenteJavaFx("LinearLayoutVertical"));
        System.out.println("16dp a 320dpi -> " + dpAPx(16, 320) + " px");
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Conversión inversa: píxeles físicos a dp.
     */
    public static double pxADp(double px, double dpi) {
        // GUÍA: teoría 3.4 (la fórmula al revés: dp = px × 160/dpi).
        // 1. devuelve px * 160.0 / dpi.
        // OJO: el test: (32, 320) -> 16.0 (a doble densidad, 32 px son 16 dp). Compara con delta.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pxADp");
    }

    /**
     * Reto Extra 2: Convierte sp (scale-independent pixels, para texto) a px.
     */
    public static int spAPx(double sp, double dpi, double escalaFuente) {
        // GUÍA: teoría 3.5 (sp es como dp pero ADEMÁS multiplica por la preferencia de tamaño de fuente).
        // 1. px = sp × dpi/160 × escalaFuente.
        // 2. redondea con Math.round y castea a int.
        // PISTA: (int) Math.round(sp * dpi / 160.0 * escalaFuente).
        // OJO: el test: (16, 160, 1.0) -> 16; (16, 160, 1.5) -> 24 (accesibilidad: fuente grande).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para spAPx");
    }

    /**
     * Reto Extra 3: ¿Es un contenedor (ViewGroup) y no una vista hoja?
     */
    public static boolean esContenedor(String vistaAndroid) {
        // GUÍA: teoría 3.2 (un ViewGroup contiene hijos; un Button/TextView es una hoja).
        // 1. null -> false.
        // 2. Es contenedor si equivalenteJavaFx(vistaAndroid) NO es "" (todos los layouts son contenedores).
        // PISTA: !equivalenteJavaFx(vistaAndroid).isEmpty().
        // OJO: el test: "GridLayout" -> true, "Button" -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esContenedor");
    }

    /**
     * Reto Extra 4: Número total de vistas en un árbol (lista aplanada de tipos).
     */
    public static int contarVistas(List<String> arbol) {
        // GUÍA: teoría 3.3 (el coste de inflar un layout crece con el nº de vistas; aplánalo si puedes).
        // 1. null -> 0.
        // 2. devuelve el tamaño de la lista.
        // OJO: el test pasa una lista de 4 elementos -> 4; null -> 0 (no NullPointerException).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarVistas");
    }

    /**
     * Reto Extra 5: Simula {@code findViewById}: busca el tipo de vista por su id en el árbol.
     */
    public static String buscarVistaPorId(Map<String, String> arbol, String id) {
        // GUÍA: teoría 3.6 (findViewById recorre el árbol y devuelve la View con ese id, o null).
        // 1. Si arbol o id son null -> "".
        // 2. Devuelve el valor asociado a 'id', o "" si no está (equivalente a un null seguro).
        // PISTA: arbol.getOrDefault(id, "");  (cuida el caso arbol == null antes).
        // OJO: el test: con {"btn_ok":"Button"} -> buscar "btn_ok" da "Button"; buscar "btn_no" da "".
        // CULTURA: esto es EXACTAMENTE el lookup("#btn_ok") de FXML en b34. El id @+id/btn_ok de
        //   Android es el fx:id="btn_ok" de JavaFX: ambos enlazan XML con código.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para buscarVistaPorId");
    }

    /**
     * Reto Extra 6: Orientación de la pantalla según sus dimensiones.
     */
    public static String orientacionPantalla(int ancho, int alto) {
        // GUÍA: teoría 3.7 (portrait = más alto que ancho; landscape = más ancho que alto).
        // 1. Si alto >= ancho -> "vertical" (portrait); si no -> "horizontal" (landscape).
        // PISTA: return alto >= ancho ? "vertical" : "horizontal";
        // OJO: el test: (1080, 1920) -> "vertical"; (1920, 1080) -> "horizontal".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para orientacionPantalla");
    }

    /**
     * Reto Extra 7: ¿Cabe un elemento de {@code anchoDp} en una pantalla de {@code pantallaDp}?
     */
    public static boolean cabeEnPantalla(int anchoDp, int pantallaDp) {
        // GUÍA: teoría 3.8 (diseñar en dp permite razonar el "cabe o no" independiente de la densidad).
        // 1. devuelve anchoDp <= pantallaDp.
        // OJO: el test: (360, 360) -> true (cabe justo); (400, 360) -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cabeEnPantalla");
    }

    /**
     * Reto Extra 8: Ancho que recibe una vista con {@code layout_weight} dentro de un LinearLayout.
     */
    public static double anchoConPeso(double anchoTotal, double peso, double sumaPesos) {
        // GUÍA: teoría 3.9 (layout_weight reparte el espacio sobrante en proporción al peso; ≈ HBox.setHgrow).
        // 1. Si sumaPesos <= 0 -> 0 (evita dividir por cero).
        // 2. devuelve anchoTotal × (peso / sumaPesos).
        // PISTA: return sumaPesos <= 0 ? 0 : anchoTotal * (peso / sumaPesos);
        // OJO: el test: (300, 1, 3) -> 100.0; (300, 0, 0) -> 0 (sin pesos, nada que repartir).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para anchoConPeso");
    }

    /**
     * Reto Extra 9: ¿Es válida la sintaxis de un id de recurso ({@code @+id/nombre} o {@code @id/nombre})?
     */
    public static boolean esIdValido(String id) {
        // GUÍA: teoría 3.6 ("@+id/x" DECLARA un id nuevo; "@id/x" REFERENCIA uno existente).
        // 1. null/blank -> false.
        // 2. Debe empezar por "@+id/" o "@id/" y tener algo no vacío después de la barra.
        // PISTA: comprueba el prefijo y que id.substring(prefijo.length()) no esté vacío.
        // OJO: el test: "@+id/boton" -> true, "@id/boton" -> true, "boton" -> false, "@+id/" -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esIdValido");
    }

    /**
     * Reto Extra 10: Gravedad (alineación) opuesta a la dada.
     */
    public static String gravedadOpuesta(String gravedad) {
        // GUÍA: teoría 3.10 (android:gravity alinea el contenido; el opuesto es útil para reflejar un layout).
        // 1. Mapea: "start"<->"end", "left"<->"right", "top"<->"bottom"; "center" -> "center".
        // 2. Desconocido o null -> "".
        // PISTA: un switch expression devolviendo el contrario.
        // OJO: el test: "start" -> "end", "top" -> "bottom", "center" -> "center", "raro" -> "".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para gravedadOpuesta");
    }
}
