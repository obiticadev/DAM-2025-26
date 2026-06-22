package com.masterclass.api.b35_fxdata;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Ejercicio 286 · Cliente JavaFX que consume la API REST: GET a la API (b05) y poblar una
 * {@code TableView} en segundo plano. <b>Integración estrella del bloque.</b>
 *
 * <p>Teoría: {@code teoria/35_JavaFX_Datos_Async.md} (sección 8).
 *
 * <p>Aquí se juntan todas las piezas del bloque y del proyecto: un {@code HttpClient} (el del JDK,
 * {@code java.net.http}) pide los datos a la API REST que construiste en b05; Jackson (b02) parsea
 * el JSON en {@link ClienteDto}; y todo eso ocurre dentro de un {@code Task} (Ej283) para no
 * congelar la interfaz, que al terminar vuelca los datos en la {@code TableView} (Ej281) desde el
 * hilo FX (Ej285). El método {@code parsearClientes} es lógica pura (testeable sin red);
 * {@code cargarEnSegundoPlano} se prueba contra un servidor HTTP local efímero.
 *
 * <p>Extiende {@code Application} para el Playground; toda la lógica de red/parseo vive en métodos
 * {@code static}.
 */
public class Ej286ConsumeRestApi extends Application {

    /** ObjectMapper reutilizable (crearlo es caro; se comparte, es thread-safe para leer). */
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * Parsea un JSON array de clientes en una lista de {@link ClienteDto}. Lógica pura: ni red ni
     * hilos, solo Jackson. Es el corazón testeable del cliente.
     *
     * @param json texto JSON con un array de clientes (p.ej. {@code [{"id":1,"nombre":"Ana",...}]})
     * @return la lista de DTOs; {@code List.of()} sin implementar
     */
    public static List<ClienteDto> parsearClientes(String json) {
        // TODO 1: rodea todo con try/catch (readValue lanza JsonProcessingException comprobada).
        // TODO 2: usa el MAPPER y un TypeReference para decirle el tipo genérico de la lista:
        //         new TypeReference<List<ClienteDto>>() {}.
        // TODO 3: return MAPPER.readValue(json, ese TypeReference).
        // TODO 4: si el JSON es inválido, captura la excepción y devuelve List.of() (o relánzala envuelta).
        // TODO 5: ojo: para un array JSON NO vale readValue(json, ClienteDto.class); necesitas el TypeReference.
        return List.of();
    }

    /**
     * Crea un {@link Task} que, en segundo plano, hace un GET a la URL dada y devuelve la lista de
     * clientes parseada. Es lo que dispararías al pulsar "Cargar" sin bloquear la UI.
     *
     * @param url URL del endpoint REST (p.ej. {@code http://localhost:8080/api/clientes})
     * @return el Task listo para ejecutarse; {@code null} sin implementar
     */
    public static Task<List<ClienteDto>> cargarEnSegundoPlano(String url) {
        // TODO 6: devuelve un new Task<List<ClienteDto>>() { @Override protected List<ClienteDto> call() throws Exception { ... } }.
        // TODO 7: dentro, crea el cliente y la petición GET:
        //         HttpClient cliente = HttpClient.newHttpClient();
        //         HttpRequest req = HttpRequest.newBuilder(URI.create(url)).GET().build().
        // TODO 8: envía la petición esperando el cuerpo como texto:
        //         HttpResponse<String> resp = cliente.send(req, HttpResponse.BodyHandlers.ofString()).
        // TODO 9: parsea el cuerpo con parsearClientes(resp.body()) y devuélvelo.
        // TODO 10: NO toques la UI aquí: este call() corre en un hilo de fondo (la UI se actualiza en onSucceeded).
        return null;
    }

    /** Playground visual: botón "Cargar" que llena la tabla con los datos de la API sin congelar la UI. */
    @Override
    public void start(Stage stage) {
        TableView<ClienteDto> tabla = new TableView<>();
        TableColumn<ClienteDto, Long> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<ClienteDto, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        TableColumn<ClienteDto, String> colEmail = new TableColumn<>("Email");
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tabla.getColumns().add(colId);
        tabla.getColumns().add(colNombre);
        tabla.getColumns().add(colEmail);

        ProgressIndicator spinner = new ProgressIndicator();
        spinner.setVisible(false);
        Button cargar = new Button("Cargar clientes de la API");

        cargar.setOnAction(e -> {
            Task<List<ClienteDto>> tarea = cargarEnSegundoPlano("http://localhost:8080/api/clientes");
            spinner.visibleProperty().bind(tarea.runningProperty()); // el spinner se ve mientras carga
            cargar.disableProperty().bind(tarea.runningProperty());
            tarea.setOnSucceeded(ev -> tabla.setItems(FXCollections.observableArrayList(tarea.getValue())));
            tarea.setOnFailed(ev -> tabla.setPlaceholder(new javafx.scene.control.Label(
                    "Error: " + tarea.getException().getMessage())));
            Thread hilo = new Thread(tarea);
            hilo.setDaemon(true);
            hilo.start();
        });

        stage.setTitle("Ej286 · Cliente REST con TableView");
        stage.setScene(new Scene(new VBox(10, cargar, spinner, tabla), 480, 360));
        stage.show();
    }

    public static void main(String[] args) {
        System.out.println("Parse demo: " + parsearClientes("[{\"id\":1,\"nombre\":\"Ana\",\"email\":\"ana@correo.com\"}]"));
        launch(args);
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Parsear un solo cliente.
     * Parsea un objeto JSON (no un array) en un único {@link ClienteDto}.
     */
    public static ClienteDto parsearUno(String json) {
        // GUÍA: teoría 8.1 (para un objeto JSON simple sí vale readValue con .class).
        // 1. try { return MAPPER.readValue(json, ClienteDto.class); }
        //    catch (Exception e) { return null; }
        // OJO: aquí SÍ usas ClienteDto.class (es un objeto, no un array). El test parsea {"id":1,...}.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para parsearUno");
    }

    /**
     * Reto Extra 2: Contar clientes del JSON.
     * Devuelve cuántos clientes trae el JSON array.
     */
    public static int contarClientes(String json) {
        // GUÍA: teoría 8.1 (reutiliza el core: parsea y mide).
        // 1. return parsearClientes(json).size();
        // OJO: el test pasa un array de 2 -> 2; un "[]" -> 0 (caso límite, lista vacía sin fallar).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarClientes");
    }

    /**
     * Reto Extra 3: Extraer los nombres.
     * Devuelve la lista de nombres de los clientes dados (proyección típica para un ComboBox/ListView).
     */
    public static List<String> nombresDe(List<ClienteDto> clientes) {
        // GUÍA: teoría 8.2 (un stream map para quedarte con un campo; lo viste en b01).
        // 1. return clientes.stream().map(ClienteDto::nombre).toList();
        // OJO: lista vacía -> lista vacía (no peta). El test comprueba el orden preservado.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para nombresDe");
    }

    /**
     * Reto Extra 4: ¿La respuesta HTTP fue un éxito?
     * Indica si el código de estado está en el rango 2xx (éxito).
     */
    public static boolean esExito(int codigoEstado) {
        // GUÍA: teoría 8.3 (la familia 2xx significa OK; 4xx error del cliente; 5xx error del servidor).
        // 1. return codigoEstado >= 200 && codigoEstado < 300.
        // OJO: el test prueba 200 (true), 201 (true), 404 (false), 500 (false). Esto lo viste en b00 (HTTP).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esExito");
    }

    /**
     * Reto Extra 5: URL paginada.
     * Construye la URL con los parámetros de paginación: base + "?page=P&size=S".
     */
    public static String urlPaginada(String base, int page, int size) {
        // GUÍA: teoría 8.4 (pedir los datos por páginas para no traerlos todos de golpe).
        // 1. return base + "?page=" + page + "&size=" + size.
        // PISTA: base "http://host/api/clientes", page 2, size 10 -> ".../clientes?page=2&size=10".
        // OJO: el test compara el String exacto; respeta el orden page&size y los signos ?/&.
        // CULTURA: estos ?page=&size= son justo los que consume el Pageable de Spring Data (b12): el
        //   cliente de escritorio pagina contra el MISMO endpoint paginado del backend. Mismo idioma.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para urlPaginada");
    }

    /**
     * Reto Extra 6: Construir la petición GET.
     * Crea y devuelve la {@link HttpRequest} GET hacia la URL dada.
     */
    public static HttpRequest peticionGet(String url) {
        // GUÍA: teoría 8.5 (HttpRequest se construye con un builder fluido del JDK).
        // 1. return HttpRequest.newBuilder(URI.create(url)).GET().build().
        // OJO: el test comprueba req.method().equals("GET") y req.uri().toString().equals(url).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para peticionGet");
    }

    /**
     * Reto Extra 7: Petición con timeout.
     * Crea una {@link HttpRequest} GET con un timeout de 'segundos' segundos.
     */
    public static HttpRequest peticionConTimeout(String url, int segundos) {
        // GUÍA: teoría 8.5 (sin timeout, una API caída deja tu cliente colgado para siempre).
        // 1. return HttpRequest.newBuilder(URI.create(url)).timeout(Duration.ofSeconds(segundos)).GET().build().
        // PISTA: si la respuesta tarda más, send() lanza HttpTimeoutException.
        // OJO: el test comprueba que req.timeout() está presente (Optional con el valor). Un timeout sano
        //   es de pocos segundos; nunca infinito.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para peticionConTimeout");
    }

    /**
     * Reto Extra 8: Task que descarga el cuerpo crudo.
     * Crea un {@link Task} que hace GET a la URL y devuelve el cuerpo de la respuesta como texto.
     */
    public static Task<String> tareaDescargaTexto(String url) {
        // GUÍA: teoría 8.6 (el caso más simple de trabajo de red en segundo plano).
        // 1. return new Task<String>() { @Override protected String call() throws Exception {
        //        HttpClient c = HttpClient.newHttpClient();
        //        HttpRequest r = HttpRequest.newBuilder(URI.create(url)).GET().build();
        //        return c.send(r, HttpResponse.BodyHandlers.ofString()).body(); } };
        // OJO: el test arranca un servidor HTTP local en un puerto efímero que responde un texto fijo y
        //   comprueba que el Task lo devuelve. Por eso es determinista (no depende de internet).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tareaDescargaTexto");
    }

    /**
     * Reto Extra 9: Task que descarga y parsea clientes.
     * Crea un {@link Task} que hace GET a la URL y devuelve la lista de {@link ClienteDto} parseada.
     */
    public static Task<List<ClienteDto>> tareaClientes(String url) {
        // GUÍA: teoría 8.6 (es el core cargarEnSegundoPlano; aquí lo reconstruyes para fijarlo).
        // 1. Igual que tareaDescargaTexto, pero en call() devuelve parsearClientes(resp.body()).
        // OJO: el test sirve un JSON de clientes desde el servidor local y comprueba el tamaño/los nombres.
        //   Reutiliza parsearClientes (core): separar "traer bytes" de "interpretar JSON" facilita el test.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tareaClientes");
    }

    /**
     * Reto Extra 10: Task con manejo de error HTTP.
     * Crea un {@link Task} que hace GET; si el código NO es 2xx, lanza una excepción con el código;
     * si lo es, devuelve los clientes parseados.
     */
    public static Task<List<ClienteDto>> tareaConManejoError(String url) {
        // GUÍA: teoría 8.7 (un 404/500 NO lanza excepción por sí solo: send() devuelve la respuesta y TÚ decides).
        // 1. new Task<>() { protected List<ClienteDto> call() throws Exception {
        //        HttpResponse<String> resp = HttpClient.newHttpClient()
        //            .send(HttpRequest.newBuilder(URI.create(url)).GET().build(), HttpResponse.BodyHandlers.ofString());
        //        if (!esExito(resp.statusCode())) throw new RuntimeException("HTTP " + resp.statusCode());
        //        return parsearClientes(resp.body()); } }.
        // PISTA: reutiliza esExito (reto 4) para decidir; lanza con el código en el mensaje.
        // OJO: el test sirve un 404 desde el servidor local; ejecuta el Task y espera que falle con un mensaje
        //   que contiene "404". El éxito y el fallo viajan por caminos distintos (onSucceeded vs onFailed).
        // CULTURA: este "comprueba el status y lanza si no es 2xx" es lo que hacen por ti clientes de alto
        //   nivel (RestTemplate/WebClient de Spring, b06); con el HttpClient del JDK lo gestionas a mano,
        //   y combinado con el retry del Ej283 reto 10 tienes un cliente robusto contra la API de b05.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tareaConManejoError");
    }
}
