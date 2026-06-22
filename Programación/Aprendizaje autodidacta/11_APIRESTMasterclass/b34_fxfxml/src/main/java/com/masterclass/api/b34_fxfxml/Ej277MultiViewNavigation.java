package com.masterclass.api.b34_fxfxml;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.Parent;

/**
 * Ejercicio 277 · Navegar entre varias vistas FXML y pasar datos entre ellas.
 *
 * <p>Teoría: {@code teoria/34_JavaFX_FXML_MVC.md} (sección 4.1).
 *
 * <p>Una app real tiene varias pantallas. El patrón habitual es un <b>router</b>: un mapa
 * {@code nombre lógico -> ruta del .fxml} que se carga bajo demanda, un <b>historial</b> (pila) para
 * el botón "atrás" y un <b>contexto</b> compartido para llevar datos de una vista a otra (p.ej. el id
 * del elemento seleccionado en una lista hacia su ficha de detalle).
 *
 * <p>Clase {@code final} con métodos {@code static}: el router es lógica pura; cargar el FXML se
 * apoya en {@link Ej271FxmlLoaderBasics}.
 */
public final class Ej277MultiViewNavigation {

    private Ej277MultiViewNavigation() {
    }

    /**
     * Resuelve un nombre lógico contra el router y carga la vista FXML asociada.
     *
     * @param rutas  mapa nombre -> ruta del recurso FXML
     * @param nombre nombre lógico de la pantalla
     * @return el {@link Parent} cargado, o {@code null} si el nombre no está registrado / sin implementar
     * @throws IOException si el FXML está mal formado
     */
    public static Parent irA(Map<String, String> rutas, String nombre) throws IOException {
        // TODO 1: si rutas es null o nombre es null, devuelve null.
        // TODO 2: comprueba si el mapa contiene 'nombre' (containsKey); si no, devuelve null (ruta 404).
        // TODO 3: recupera la ruta del FXML con rutas.get(nombre).
        // TODO 4: carga la vista con Ej271FxmlLoaderBasics.cargarVista(rutaFxml).
        // TODO 5: devuelve la vista cargada.
        return null;
    }

    /**
     * Navega a un destino apilándolo en el historial; devuelve la pantalla actual (la cima).
     *
     * @param historial pila de navegación (la cima es la pantalla visible)
     * @param destino   nombre de la pantalla a la que se va
     * @return la pantalla actual tras navegar, o {@code null} si los argumentos son null / sin implementar
     */
    public static String navegarA(Deque<String> historial, String destino) {
        // TODO 6: si historial es null o destino es null, devuelve null.
        // TODO 7: apila el destino en la cima con historial.push(destino).
        // TODO 8: la "pantalla actual" es ahora la cima del historial.
        // TODO 9: recupérala con historial.peek() (mira la cima sin sacarla).
        // TODO 10: devuélvela.
        return null;
    }

    public static void main(String[] args) throws IOException {
        Map<String, String> rutas = new HashMap<>();
        rutas.put("form", Ej272ControllerInjection.VISTA_272);
        System.out.println("Vista 'form': " + irA(rutas, "form"));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Crear el router (mapa de rutas) vacío y mutable.
     */
    public static Map<String, String> crearRutas() {
        // GUÍA: teoría 4.1 (el router es, en esencia, un Map<nombre, rutaFxml>).
        // 1. return new HashMap<>();
        // OJO: debe ser MUTABLE; el test registra rutas después. Map.of() rompería el reto 2.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearRutas");
    }

    /**
     * Reto Extra 2: Registrar una ruta.
     * Asocia un nombre lógico a una ruta FXML y devuelve el mapa para encadenar.
     */
    public static Map<String, String> registrar(Map<String, String> rutas, String nombre, String rutaFxml) {
        // GUÍA: teoría 4.1 (registrar una pantalla = un put en el router).
        // 1. rutas.put(nombre, rutaFxml); 2. return rutas;
        // OJO: devolver el mapa permite registrar varias en cadena; el test registra dos.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para registrar");
    }

    /**
     * Reto Extra 3: ¿Existe la ruta?
     */
    public static boolean existeRuta(Map<String, String> rutas, String nombre) {
        // GUÍA: teoría 4.1.
        // 1. return rutas != null && rutas.containsKey(nombre);
        // OJO: protégete de rutas null; el test prueba una existente y una que no.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para existeRuta");
    }

    /**
     * Reto Extra 4: Pantallas disponibles, ordenadas.
     * Devuelve los nombres lógicos registrados en orden alfabético (para un menú).
     */
    public static List<String> pantallasDisponibles(Map<String, String> rutas) {
        // GUÍA: teoría 4.1.
        // 1. return rutas.keySet().stream().sorted().toList();
        // OJO: el test registra "inicio" y "form" -> espera ["form", "inicio"] (alfabético).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pantallasDisponibles");
    }

    /**
     * Reto Extra 5: Ruta con valor por defecto.
     * Devuelve la ruta FXML del nombre, o 'rutaPorDefecto' si no está registrado (página 404).
     */
    public static String rutaConDefecto(Map<String, String> rutas, String nombre, String rutaPorDefecto) {
        // GUÍA: teoría 4.1 (si la ruta no existe, navega a una pantalla de respaldo).
        // 1. return rutas.getOrDefault(nombre, rutaPorDefecto);
        // OJO: el test pide un nombre inexistente y espera la ruta por defecto.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para rutaConDefecto");
    }

    /**
     * Reto Extra 6: Volver atrás.
     * Quita la pantalla actual (cima) y devuelve la anterior (nueva cima), o null si no hay anterior.
     */
    public static String volverAtras(Deque<String> historial) {
        // GUÍA: teoría 4.1 (el botón "atrás" = quitar la actual y mirar la de debajo).
        // 1. Si historial.size() <= 1 -> return null (no hay a dónde volver).
        // 2. historial.pop() (descarta la actual). 3. return historial.peek().
        // OJO: con cima "detalle" sobre "lista", volver da "lista"; con una sola pantalla -> null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para volverAtras");
    }

    /**
     * Reto Extra 7: Guardar un dato en el contexto compartido.
     */
    public static Map<String, Object> pasarDato(Map<String, Object> contexto, String clave, Object valor) {
        // GUÍA: teoría 4.2 (al navegar se lleva información: el id seleccionado, un filtro, etc.).
        // 1. contexto.put(clave, valor); 2. return contexto;
        // OJO: el valor es Object (puede ser un id, un DTO de b07...). El test guarda y luego lee.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasarDato");
    }

    /**
     * Reto Extra 8: Recuperar un dato del contexto, ya tipado.
     * Devuelve el valor de la clave casteado al tipo pedido, o null si no está.
     */
    public static <T> T recuperarDato(Map<String, Object> contexto, String clave, Class<T> tipo) {
        // GUÍA: teoría 4.2 (la vista destino lee del contexto lo que dejó la de origen).
        // 1. Object v = contexto.get(clave). 2. si v es null -> null. 3. return tipo.cast(v).
        // PISTA: Class.cast hace el cast comprobado y devuelve T.
        // OJO: el test guarda un String "42" y lo recupera como String.class -> "42".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para recuperarDato");
    }

    /**
     * Reto Extra 9: Recuperar el controlador de una ruta.
     * Resuelve el nombre, carga la vista y devuelve la instancia del controlador asociada.
     */
    public static Object controladorDeRuta(Map<String, String> rutas, String nombre) throws IOException {
        // GUÍA: teoría 4.1 + 1.2 (a veces, tras navegar, quieres hablar con el controlador destino:
        //   pasarle el dato seleccionado por un método setter).
        // 1. Si !existeRuta(rutas, nombre) -> null.
        // 2. var loader = Ej271FxmlLoaderBasics.crearLoaderDe(rutas.get(nombre)).
        // 3. loader.load(); 4. return loader.getController().
        // OJO: el test registra "form" -> VISTA_272 y espera instanceof Controlador272.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para controladorDeRuta");
    }

    /**
     * Reto Extra 10: Flujo maestro-detalle.
     * Guarda el id seleccionado en el contexto y abre la vista de detalle; devuelve la vista cargada.
     */
    public static Parent abrirDetalle(Map<String, String> rutas, Map<String, Object> contexto, String idSeleccionado) throws IOException {
        // GUÍA: teoría 4.2 (patrón maestro-detalle: una lista pasa el id a la ficha de detalle).
        // 1. contexto.put("id", idSeleccionado) (el detalle leerá este id del contexto).
        // 2. return irA(rutas, "detalle") (carga la vista registrada bajo "detalle").
        // OJO: el test registra "detalle" -> un FXML real, pasa el id "42" y espera una vista no null
        //   y que contexto.get("id") sea "42".
        // CULTURA: esto es lo mismo que un GET /clientes/42 tras pulsar una fila: el id viaja de la
        //   vista lista a la vista detalle. En web es la URL; en Android, el "extra" de un Intent.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para abrirDetalle");
    }

    /** Crea un historial nuevo (pila) ya posicionado en una pantalla inicial. */
    static Deque<String> historialEn(String inicial) {
        Deque<String> h = new ArrayDeque<>();
        h.push(inicial);
        return h;
    }
}
