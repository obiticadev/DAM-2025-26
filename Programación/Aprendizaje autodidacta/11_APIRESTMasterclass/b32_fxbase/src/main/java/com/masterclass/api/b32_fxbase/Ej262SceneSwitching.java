package com.masterclass.api.b32_fxbase;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Map;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Ejercicio 262 · Cambiar de vista: raíz dinámica de la {@link Scene} y mini-router por nombre.
 *
 * <p>Teoría: {@code teoria/32_JavaFX_Base.md} (sección 1.7).
 *
 * <p>Una app de escritorio típica reutiliza UNA ventana y le cambia el contenido (la raíz de la
 * escena) para navegar entre pantallas. Aquí practicas ese cambio y un router que resuelve vistas
 * por nombre: la semilla de la navegación multi-pantalla que ampliarás con FXML en b34.
 */
public final class Ej262SceneSwitching {

    private Ej262SceneSwitching() {
    }

    /**
     * Sustituye la raíz de una escena por otra y devuelve la nueva raíz instalada.
     *
     * @return la nueva raíz si se instaló; {@code null} si algún argumento es null o sin implementar
     */
    public static Parent cambiarRaiz(Scene escena, Parent nuevaRaiz) {
        // TODO 1: si escena es null, devuelve null (no hay dónde instalar).
        // TODO 2: si nuevaRaiz es null, devuelve null (no cambies a una raíz nula).
        // TODO 3: instala la nueva raíz con escena.setRoot(nuevaRaiz).
        // TODO 4: recupera la raíz actual con escena.getRoot() (para confirmar que se instaló).
        // TODO 5: devuelve esa raíz.
        return null;
    }

    /**
     * Resuelve una vista por su nombre en el mapa de rutas (mini-router).
     *
     * @return la vista asociada al nombre, o {@code null} si no existe / sin implementar
     */
    public static Parent navegar(Map<String, Parent> rutas, String nombre) {
        // TODO 6: si rutas es null o nombre es null, devuelve null.
        // TODO 7: comprueba si el mapa contiene ese nombre (containsKey).
        // TODO 8: si NO existe, devuelve null (ruta desconocida).
        // TODO 9: si existe, recupera la vista con rutas.get(nombre).
        // TODO 10: devuelve la vista.
        return null;
    }

    public static void main(String[] args) {
        Map<String, Parent> rutas = Map.of("inicio", new VBox(new Label("Pantalla de inicio")));
        System.out.println("Vista 'inicio': " + navegar(rutas, "inicio"));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Crear el mapa de rutas vacío.
     * Devuelve un mapa mutable (modificable) y vacío para registrar vistas.
     */
    public static Map<String, Parent> crearRutas() {
        // GUÍA: teoría 1.7 (el router es, en esencia, un Map<nombre, vista>).
        // 1. return new java.util.HashMap<>();
        // OJO: debe ser MUTABLE (Map.of() es inmutable y rompería el reto 2). El test mete claves luego.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearRutas");
    }

    /**
     * Reto Extra 2: Registrar una vista.
     * Añade (nombre -> vista) al mapa y devuelve el mapa para poder encadenar.
     */
    public static Map<String, Parent> registrar(Map<String, Parent> rutas, String nombre, Parent vista) {
        // GUÍA: teoría 1.7. Registrar una ruta es un simple put.
        // 1. rutas.put(nombre, vista); 2. return rutas;
        // OJO: devolver el mapa permite registrar varias en cadena; el test encadena dos.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para registrar");
    }

    /**
     * Reto Extra 3: ¿Existe la ruta?
     */
    public static boolean existeRuta(Map<String, Parent> rutas, String nombre) {
        // GUÍA: teoría 1.7.
        // 1. return rutas != null && rutas.containsKey(nombre);
        // OJO: protégete de rutas null; el test prueba una existente y una que no.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para existeRuta");
    }

    /**
     * Reto Extra 4: Rutas disponibles, ordenadas.
     * Devuelve los nombres de ruta registrados en orden alfabético.
     */
    public static List<String> rutasDisponibles(Map<String, Parent> rutas) {
        // GUÍA: teoría 1.7 (útil para un menú de navegación).
        // 1. rutas.keySet().stream().sorted().toList();
        // OJO: el test registra "inicio" y "ajustes" -> espera ["ajustes", "inicio"] (alfabético).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para rutasDisponibles");
    }

    /**
     * Reto Extra 5: Navegar con vista por defecto.
     * Devuelve la vista del nombre, o 'porDefecto' si la ruta no existe.
     */
    public static Parent navegarConDefecto(Map<String, Parent> rutas, String nombre, Parent porDefecto) {
        // GUÍA: teoría 1.7 (página 404: si la ruta no existe, muestra algo razonable).
        // 1. return rutas.getOrDefault(nombre, porDefecto);
        // OJO: getOrDefault hace exactamente esto en una línea; el test pide una ruta inexistente.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para navegarConDefecto");
    }

    /**
     * Reto Extra 6: Apilar una vista en el historial.
     * Empuja el nombre en la pila de historial y devuelve la pila.
     */
    public static Deque<String> historialPush(Deque<String> historial, String nombre) {
        // GUÍA: teoría 1.7 (el botón "atrás" se modela con una pila LIFO).
        // 1. historial.push(nombre); 2. return historial;
        // OJO: push mete por la CABEZA; peek() te daría el último visitado.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para historialPush");
    }

    /**
     * Reto Extra 7: Volver atrás.
     * Quita la pantalla actual (cima) y devuelve la ANTERIOR (la nueva cima), o null si no hay.
     */
    public static String volverAtras(Deque<String> historial) {
        // GUÍA: teoría 1.7 (volver = quitar la actual y mirar la que queda debajo).
        // 1. Si el historial tiene <= 1 elemento, no hay "atrás": devuelve null.
        // 2. historial.pop() (descarta la actual). 3. return historial.peek() (la anterior).
        // OJO: con ["c","b","a"] (c en la cima) volver da "b"; con 1 sola pantalla -> null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para volverAtras");
    }

    /**
     * Reto Extra 8: Reproducir una secuencia de navegación.
     * Aplica una lista de nombres como navegaciones y devuelve la pantalla final (cima del historial).
     */
    public static String pantallaFinal(List<String> secuencia) {
        // GUÍA: teoría 1.7. Recorre la secuencia haciendo push de cada nombre.
        // 1. Crea un ArrayDeque. 2. Por cada nombre, historialPush(...). 3. devuelve peek().
        // OJO: si la secuencia está vacía -> null. El último de la lista es la cima.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pantallaFinal");
    }

    /**
     * Reto Extra 9: Pasar datos entre vistas.
     * Guarda (clave -> valor) en un contexto compartido y devuelve el contexto.
     */
    public static Map<String, Object> pasarDatos(Map<String, Object> contexto, String clave, Object valor) {
        // GUÍA: teoría 1.7 (al navegar, a menudo hay que llevar datos: el id seleccionado, etc.).
        // 1. contexto.put(clave, valor); 2. return contexto;
        // OJO: el valor es Object (puede ser cualquier cosa: un DTO de b07, un id…). Test: lee la clave.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para pasarDatos");
    }

    /**
     * Reto Extra 10: Resolver una secuencia de rutas contra el router.
     * Para cada nombre, devuelve su vista si existe o {@code null} si la ruta es desconocida.
     */
    public static List<Parent> resolverSecuencia(Map<String, Parent> rutas, List<String> nombres) {
        // GUÍA: teoría 1.7 (un router resuelve cada destino; los huecos son rutas rotas).
        // 1. Recorre 'nombres'. 2. Por cada uno añade navegar(rutas, nombre) (puede ser null).
        // PISTA: usa una lista que admita null (ArrayList), no List.of (no acepta null).
        // OJO: si un nombre no está registrado, su hueco es null; el test mezcla 2 válidas y 1 rota.
        // CULTURA: esto es justo lo que hace un Router web (o el de b34 con FXML): mapear una URL/
        //   nombre lógico a la vista a montar; una ruta sin handler es el 404.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para resolverSecuencia");
    }
}
