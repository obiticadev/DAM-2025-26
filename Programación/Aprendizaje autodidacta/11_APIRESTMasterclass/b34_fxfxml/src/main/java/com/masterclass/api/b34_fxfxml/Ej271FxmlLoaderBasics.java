package com.masterclass.api.b34_fxfxml;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

/**
 * Ejercicio 271 · Cargar una vista declarativa con {@link FXMLLoader}.
 *
 * <p>Teoría: {@code teoria/34_JavaFX_FXML_MVC.md} (sección 1.1).
 *
 * <p>Hasta ahora construías la UI a mano en Java (b32/b33). FXML invierte eso: describes la vista
 * en un {@code .fxml} (lo que genera Scene Builder) y el {@code FXMLLoader} la convierte en un árbol
 * de nodos en tiempo de ejecución. El recurso vive en el classpath
 * ({@code src/main/resources/.../b34_fxfxml/vista271.fxml}); se localiza con
 * {@code getClass().getResource("/ruta/absoluta.fxml")}.
 *
 * <p>Clase {@code final} con métodos {@code static}: aquí no hay {@code Application} porque toda la
 * lógica (cargar y leer el árbol) es headless-testable; el Playground visual vive en
 * {@link Ej276MvvmViewModel}.
 */
public final class Ej271FxmlLoaderBasics {

    /** Ruta del recurso FXML de demostración dentro del classpath. */
    public static final String VISTA_271 = "/com/masterclass/api/b34_fxfxml/vista271.fxml";

    private Ej271FxmlLoaderBasics() {
    }

    /**
     * Carga un {@code .fxml} y devuelve su nodo raíz ya construido.
     *
     * @param ruta ruta absoluta del recurso en el classpath (empieza por {@code /})
     * @return el {@link Parent} raíz del FXML, o {@code null} si la ruta es inválida / sin implementar
     * @throws IOException si el FXML existe pero está mal formado
     */
    public static Parent cargarVista(String ruta) throws IOException {
        // TODO 1: si 'ruta' es null o está en blanco (isBlank), devuelve null.
        // TODO 2: localiza el recurso: URL url = Ej271FxmlLoaderBasics.class.getResource(ruta).
        // TODO 3: si url es null (no existe el recurso), devuelve null.
        // TODO 4: crea el loader: FXMLLoader loader = new FXMLLoader(url).
        // TODO 5: devuelve loader.load() (construye y devuelve el árbol de nodos).
        return null;
    }

    /**
     * Crea (sin cargar todavía) un {@link FXMLLoader} apuntando a un recurso FXML.
     *
     * <p>Separar "crear el loader" de "cargar" permite configurarlo antes (controlador, charset,
     * ResourceBundle…) y luego recuperar el controlador con {@code loader.getController()}.
     *
     * @param ruta ruta absoluta del recurso en el classpath
     * @return un {@link FXMLLoader} listo para {@code load()}, o {@code null} si la ruta es inválida
     */
    public static FXMLLoader crearLoaderDe(String ruta) {
        // TODO 6: si 'ruta' es null o está en blanco, devuelve null.
        // TODO 7: localiza el recurso con getResource(ruta) en una variable URL.
        // TODO 8: si la URL es null, devuelve null (recurso inexistente).
        // TODO 9: crea el FXMLLoader con esa URL.
        // TODO 10: devuelve el loader SIN llamar a load() (lo cargará quien lo use).
        return null;
    }

    public static void main(String[] args) throws IOException {
        Parent raiz = cargarVista(VISTA_271);
        System.out.println("Raíz cargada: " + raiz);
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: ¿Existe el recurso FXML?
     * Indica si la ruta apunta a un recurso real del classpath.
     */
    public static boolean existeRecurso(String ruta) {
        // GUÍA: teoría 1.1 (los FXML se localizan como recursos del classpath, no como ficheros).
        // 1. return Ej271FxmlLoaderBasics.class.getResource(ruta) != null;
        // OJO: la ruta debe ser ABSOLUTA (empezar por '/'); el test prueba VISTA_271 (true) y
        //   "/com/masterclass/api/b34_fxfxml/noexiste.fxml" (false).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para existeRecurso");
    }

    /**
     * Reto Extra 2: Construir la ruta de una vista por su nombre.
     * Devuelve la ruta absoluta del classpath para un nombre de vista (sin extensión).
     */
    public static String rutaDeVista(String nombre) {
        // GUÍA: teoría 1.1 (convención de carpeta: el FXML va junto al paquete, en resources).
        // 1. return "/com/masterclass/api/b34_fxfxml/" + nombre + ".fxml";
        // OJO: el test pide rutaDeVista("vista271") y espera exactamente la constante VISTA_271.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para rutaDeVista");
    }

    /**
     * Reto Extra 3: Cargar y contar los hijos del nodo raíz.
     * Carga el FXML y devuelve cuántos nodos hijos cuelgan directamente de la raíz.
     */
    public static int cargarYContarHijos(String ruta) throws IOException {
        // GUÍA: teoría 1.1 + b32 1.2 (el FXML produce el MISMO scene graph que harías a mano).
        // 1. Parent raiz = cargarVista(ruta). 2. return raiz.getChildrenUnmodifiable().size().
        // PISTA: Parent expone getChildrenUnmodifiable() (lista de solo lectura de sus hijos).
        // OJO: vista271.fxml tiene 2 Labels dentro del VBox -> el test espera 2.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cargarYContarHijos");
    }

    /**
     * Reto Extra 4: Cargar tipando la raíz (genérico).
     * Carga el FXML y devuelve la raíz ya casteada al tipo pedido.
     */
    public static <T extends Parent> T cargarComo(String ruta, Class<T> tipo) throws IOException {
        // GUÍA: teoría 1.1 (a menudo sabes que tu raíz es un VBox/BorderPane y quieres tratarla así).
        // 1. Parent raiz = cargarVista(ruta). 2. return tipo.cast(raiz).
        // PISTA: Class.cast(obj) hace el cast comprobado y devuelve T (mejor que (T) raiz).
        // OJO: el test carga vista271 como VBox.class; la raíz ES un VBox, así que el cast funciona.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cargarComo");
    }

    /**
     * Reto Extra 5: Recuperar el controlador asociado a la vista.
     * Carga el FXML y devuelve la instancia del controlador que el loader creó e inyectó.
     */
    public static Object controladorDe(String ruta) throws IOException {
        // GUÍA: teoría 1.2 (el atributo fx:controller del FXML decide la clase del controlador).
        // 1. FXMLLoader loader = crearLoaderDe(ruta). 2. loader.load(). 3. return loader.getController().
        // PISTA: getController() solo devuelve algo DESPUÉS de load(); antes es null.
        // OJO: vista271 declara fx:controller=Controlador271 -> el test comprueba instanceof Controlador271.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para controladorDe");
    }

    /**
     * Reto Extra 6: Cargar FXML desde un texto en memoria (no desde fichero).
     * Interpreta una cadena de FXML y devuelve su nodo raíz.
     */
    public static Parent cargarDesdeTexto(String fxml) throws IOException {
        // GUÍA: teoría 1.1 (FXMLLoader puede leer de cualquier InputStream, no solo de una URL).
        // 1. byte[] bytes = fxml.getBytes(StandardCharsets.UTF_8).
        // 2. try (var in = new ByteArrayInputStream(bytes)) { return (Parent) new FXMLLoader().load(in); }
        // PISTA: el FXMLLoader().load(InputStream) estático-de-instancia devuelve Object: castea a Parent.
        // OJO: el test pasa un FXML con un único <Label .../> como raíz -> assertInstanceOf(Label...).
        //   Recuerda los <?import ...?> dentro del propio texto, o no resolverá las clases.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cargarDesdeTexto");
    }

    /**
     * Reto Extra 7: Cada carga produce un árbol NUEVO.
     * Carga dos veces el mismo FXML y dice si son instancias distintas.
     */
    public static boolean recargarDistintas(String ruta) throws IOException {
        // GUÍA: teoría 1.1 (un FXML es una PLANTILLA: cada load() instancia nodos frescos).
        // 1. Parent a = cargarVista(ruta). 2. Parent b = cargarVista(ruta). 3. return a != b.
        // OJO: compara con != (identidad de referencia), NO con equals. Un mismo FXMLLoader reutilizado
        //   NO recarga; por eso aquí llamamos a cargarVista() dos veces (loader nuevo cada vez).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para recargarDistintas");
    }

    /**
     * Reto Extra 8: ¿Desde dónde cargó el loader?
     * Devuelve la URL (location) que el loader usará para resolver el FXML.
     */
    public static URL ubicacionDe(String ruta) {
        // GUÍA: teoría 1.1 (el loader guarda su 'location' para resolver rutas relativas e includes).
        // 1. FXMLLoader loader = crearLoaderDe(ruta). 2. return loader.getLocation().
        // OJO: el test comprueba que la URL no es null y termina en "vista271.fxml".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ubicacionDe");
    }

    /**
     * Reto Extra 9: Patrón fx:root (la raíz la aporta el código).
     * Carga un FXML con {@code <fx:root>} usando la raíz que se le pasa, y la devuelve ya rellenada.
     */
    public static Parent cargarConRaiz(String rutaRoot, VBox raizExterna) throws IOException {
        // GUÍA: teoría 1.5 (fx:root: el nodo raíz NO está en el FXML, lo pone el código antes de cargar).
        // 1. URL url = Ej271FxmlLoaderBasics.class.getResource(rutaRoot).
        // 2. FXMLLoader loader = new FXMLLoader(url).
        // 3. loader.setRoot(raizExterna);   // ESTA es la clave del patrón fx:root.
        // 4. loader.load(); 5. return raizExterna (el load rellena la raíz que le diste).
        // OJO: con fx:root, load() devuelve la MISMA raíz que pusiste con setRoot. El test pasa un
        //   VBox vacío y espera recuperarlo con 1 hijo (el Label de vistaRoot271.fxml).
        // CULTURA: así se hacen los "componentes personalizados" reutilizables (una clase que extiende
        //   VBox y en su constructor hace setRoot(this)+setController(this)+load()).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cargarConRaiz");
    }

    /**
     * Reto Extra 10: Inyectar el controlador con una controllerFactory.
     * Carga la vista forzando que el controlador sea la instancia dada, y devuelve el controlador real.
     */
    public static Object cargarConControladorFijo(String ruta, Object controladorFijo) throws IOException {
        // GUÍA: teoría 1.6 (controllerFactory: en vez de que el loader llame al constructor del
        //   controlador, TÚ decides qué instancia usar -> permite inyectar dependencias).
        // 1. FXMLLoader loader = crearLoaderDe(ruta).
        // 2. loader.setControllerFactory(tipo -> controladorFijo);  // ignora 'tipo', devuelve el tuyo.
        // 3. loader.load(); 4. return loader.getController().
        // OJO: el test pasa una instancia concreta de Controlador271 y comprueba con assertSame que
        //   loader.getController() es EXACTAMENTE esa (no una nueva creada por el loader).
        // CULTURA: esto es el puente con Spring (b03): la controllerFactory pide los controladores al
        //   contenedor de Inyección de Dependencias, así tu controlador FXML recibe sus @Autowired.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cargarConControladorFijo");
    }

    /** Texto FXML de demostración para cargarDesdeTexto (usado por el test del reto 6). */
    static String fxmlDeUnLabel() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<?import javafx.scene.control.Label?>\n"
                + "<Label xmlns:fx=\"http://javafx.com/fxml\" text=\"Hola desde texto\"/>";
    }

    /** Evita el aviso de import sin usar mientras los TODO están sin implementar. */
    static ByteArrayInputStream flujoVacio() {
        return new ByteArrayInputStream(new byte[0]);
    }
}
