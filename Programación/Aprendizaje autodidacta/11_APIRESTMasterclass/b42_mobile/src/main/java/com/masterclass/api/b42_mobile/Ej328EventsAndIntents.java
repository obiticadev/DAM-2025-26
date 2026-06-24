package com.masterclass.api.b42_mobile;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Ejercicio 328 · Eventos y navegación por {@code Intent} (modelo testeable).
 *
 * <p>Teoría: {@code teoria/42_Movil_Android.md} (sección 4).
 *
 * <p>En Android no se navega "llamando a otra pantalla": se lanza un <strong>Intent</strong> (una
 * "intención": ir a tal Activity, o pedir al sistema que abra un mapa). El sistema apila las
 * Activities en un <strong>back stack</strong> (pila): abrir una pantalla la empuja, el botón Atrás
 * la saca. Los datos viajan entre pantallas como <strong>extras</strong> (pares clave→valor, como
 * los query params de una URL de la API REST de b05). Aquí modelamos la pila y los extras con
 * estructuras inmutables: cero {@code Activity} real, todo verificable con JUnit.
 */
public final class Ej328EventsAndIntents {

    private Ej328EventsAndIntents() {
    }

    /**
     * Navega a una pantalla: la empuja a la cima del back stack y devuelve una pila NUEVA.
     *
     * @param backStack pila actual (la base es la pantalla más antigua); puede ser {@code null}
     * @param destino   pantalla destino a apilar
     * @return una pila nueva con {@code destino} en la cima; si {@code destino} es null/blank,
     *         devuelve una copia de la pila sin cambios (nunca {@code null})
     */
    public static List<String> navegar(List<String> backStack, String destino) {
        // TODO 1: parte de una copia mutable de backStack (si es null, trátalo como pila vacía).
        // TODO 2: si destino es null o blank -> devuelve esa copia tal cual (navegar a "nada" no hace nada).
        // TODO 3: añade destino al FINAL de la copia (el final de la lista es la CIMA de la pila).
        // TODO 4: devuelve una vista INMUTABLE de la copia (List.copyOf) para no exponer la interna.
        // TODO 5: garantiza que nunca devuelves null (ni siquiera con backStack null y destino válido).
        return List.of();
    }

    /**
     * Lee un extra de tipo texto del Intent (equivalente a {@code getStringExtra}).
     *
     * @param extras mapa de extras del Intent; puede ser {@code null}
     * @param clave  clave del extra buscado
     * @return el valor asociado, o {@code ""} si no existe / entrada inválida (nunca {@code null})
     */
    public static String extraComoTexto(Map<String, String> extras, String clave) {
        // TODO 6: si extras es null -> "" (un Intent sin extras no rompe al leer).
        // TODO 7: si clave es null o blank -> "".
        // TODO 8: si el mapa NO contiene la clave -> "" (getStringExtra devuelve null; aquí "" es el centinela).
        // TODO 9: si la contiene, recupera su valor.
        // TODO 10: si el valor almacenado fuese null, devuelve "" igualmente; en otro caso, el valor.
        return "";
    }

    public static void main(String[] args) {
        List<String> pila = navegar(List.of("Home"), "Detalle");
        System.out.println("Pila tras navegar: " + pila);
        System.out.println("extra 'id' = " + extraComoTexto(Map.of("id", "42"), "id"));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Pantalla en la cima de la pila (la que el usuario VE ahora).
     */
    public static String cima(List<String> backStack) {
        // GUÍA: teoría 4.3 (la Activity visible es siempre la del tope del back stack).
        // 1. Si backStack es null o vacío -> "".
        // 2. Devuelve el ÚLTIMO elemento (la cima).
        // PISTA: backStack.get(backStack.size() - 1).
        // OJO: el test: ["Home","Detalle"] -> "Detalle"; lista vacía -> "".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cima");
    }

    /**
     * Reto Extra 2: ¿Está vacía la pila? (sin pantallas: la app se cerraría).
     */
    public static boolean estaVacia(List<String> backStack) {
        // GUÍA: teoría 4.3 (si el back stack queda vacío, Android cierra la app).
        // 1. true si backStack es null o no tiene elementos.
        // PISTA: backStack == null || backStack.isEmpty().
        // OJO: el test: lista vacía -> true, ["Home"] -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para estaVacia");
    }

    /**
     * Reto Extra 3: Añade un extra al Intent y devuelve un mapa NUEVO (inmutable).
     */
    public static Map<String, String> ponerExtra(Map<String, String> extras, String clave, String valor) {
        // GUÍA: teoría 4.4 (putExtra acumula pares clave-valor que viajan con el Intent).
        // 1. Si clave es null/blank -> IllegalArgumentException.
        // 2. Copia los extras actuales (o un mapa vacío si son null) a un LinkedHashMap (conserva orden).
        // 3. Pon clave->valor (sobrescribe si ya existía) y devuelve Map.copyOf del resultado.
        // PISTA: var m = new LinkedHashMap<String,String>(extras == null ? Map.of() : extras); m.put(...);
        // OJO: el test parte de {"a":"1"}, pone "b"->"2" y espera {"a":"1","b":"2"}; el mapa original NO cambia.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ponerExtra");
    }

    /**
     * Reto Extra 4: Acción implícita estándar para una operación de alto nivel.
     */
    public static String accionImplicita(String operacion) {
        // GUÍA: teoría 4.5 (un Intent implícito dice QUÉ hacer, no a quién; el sistema elige la app).
        // 1. Mapea: "ver" -> "ACTION_VIEW", "compartir" -> "ACTION_SEND", "llamar" -> "ACTION_DIAL".
        // 2. Cualquier otro / null -> "".
        // PISTA: un switch expression sobre la operación.
        // OJO: el test: "compartir" -> "ACTION_SEND", "bailar" -> "".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para accionImplicita");
    }

    /**
     * Reto Extra 5: ¿El código de resultado indica ÉXITO? ({@code RESULT_OK == -1} en Android).
     */
    public static boolean esResultadoOk(int codigo) {
        // GUÍA: teoría 4.6 (¡trampa clásica! RESULT_OK vale -1, RESULT_CANCELED vale 0).
        // 1. true solo si codigo == -1.
        // OJO: el test: -1 -> true, 0 -> false (0 es CANCELADO, no éxito). No confundas con "0 = ok".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esResultadoOk");
    }

    /**
     * Reto Extra 6: ¿Es válido un {@code requestCode} para {@code startActivityForResult}?
     */
    public static boolean requestCodeValido(int codigo) {
        // GUÍA: teoría 4.7 (el requestCode debe caber en 16 bits sin signo: 0..65535).
        // 1. true si codigo está en [0, 65535].
        // PISTA: codigo >= 0 && codigo <= 65535.
        // OJO: el test: 0 -> true, 65535 -> true, -1 -> false, 70000 -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para requestCodeValido");
    }

    /**
     * Reto Extra 7: Botón Atrás: saca la cima del back stack y devuelve una pila nueva.
     */
    public static List<String> volverAtras(List<String> backStack) {
        // GUÍA: teoría 4.3 (el botón Atrás hace pop; si solo queda una pantalla, no la quita).
        // 1. Si backStack es null o tiene 0/1 elementos -> devuelve una copia inmutable igual (no se cierra la app).
        // 2. Si tiene 2+, devuelve una copia SIN el último elemento.
        // PISTA: new ArrayList<>(backStack).subList(0, size-1) -> List.copyOf(...).
        // OJO: el test: ["Home","Detalle"] -> ["Home"]; ["Home"] -> ["Home"] (no se vacía).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para volverAtras");
    }

    /**
     * Reto Extra 8: Número de pantallas apiladas (profundidad de navegación).
     */
    public static int contarPantallas(List<String> backStack) {
        // GUÍA: teoría 4.3 (la profundidad del stack = cuántos "Atrás" hasta salir).
        // 1. null -> 0; en otro caso, el tamaño de la lista.
        // OJO: el test: ["A","B","C"] -> 3, null -> 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarPantallas");
    }

    /**
     * Reto Extra 9: URI para un Intent de llamada telefónica ({@code tel:NUMERO}).
     */
    public static String uriLlamada(String telefono) {
        // GUÍA: teoría 4.5 (ACTION_DIAL + Uri "tel:600123123" abre el marcador con el número puesto).
        // 1. Si telefono es null/blank -> IllegalArgumentException.
        // 2. Devuelve "tel:" + telefono (sin espacios).
        // PISTA: return "tel:" + telefono.trim().replace(" ", "");
        // OJO: el test: "600 123 123" -> "tel:600123123" (quita los espacios).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para uriLlamada");
    }

    /**
     * Reto Extra 10: Extrae el valor de un parámetro de un deep link ({@code app://ruta?clave=valor}).
     */
    public static String parametroDeepLink(String url, String clave) {
        // GUÍA: teoría 4.8 (un deep link abre una pantalla concreta desde fuera; sus query params son extras).
        // 1. Si url o clave son null/blank -> "".
        // 2. Si no hay '?' en la url -> "" (no hay query string).
        // 3. Parte la query por '&' y busca el segmento "clave=valor"; devuelve su valor o "".
        // PISTA: String q = url.substring(url.indexOf('?')+1); for (var par : q.split("&")) { split("=",2)... }
        // OJO: el test: ("app://detalle?id=42&tab=info", "id") -> "42"; clave ausente -> "".
        // CULTURA: esto es el routing + query params de la API REST (b05): /detalle?id=42. Android y la web
        //   resuelven URLs igual; un deep link es una URL que "entra" directamente a una pantalla.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para parametroDeepLink");
    }
}
