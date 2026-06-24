package com.masterclass.api.b42_mobile;

import java.util.List;

/**
 * Ejercicio 326 · Ciclo de vida de una {@code Activity} como máquina de estados.
 *
 * <p>Teoría: {@code teoria/42_Movil_Android.md} (sección 2).
 *
 * <p>Una {@code Activity} (una "pantalla" de Android) atraviesa estados bien definidos según los
 * <em>callbacks</em> que el sistema invoca: {@code onCreate} → {@code onStart} → {@code onResume}
 * (la pantalla pasa a primer plano) y a la inversa {@code onPause} → {@code onStop} →
 * {@code onDestroy}. Si vuelves a ella desde "parada" se reactiva con {@code onRestart}. Modelar
 * esto como una <strong>máquina de estados</strong> verificable enseña el contrato de Android sin
 * abrir un emulador, y es el mismo patrón del ciclo de vida del {@code Stage} de JavaFX (b32) y de
 * los controladores FXML (b34).
 */
public final class Ej326ActivityLifecycle {

    private Ej326ActivityLifecycle() {
    }

    /**
     * Estado siguiente al aplicar un callback. Si el callback no es válido para el estado actual,
     * el estado NO cambia (Android ignora callbacks fuera de orden).
     *
     * @param estado   estado actual: {@code NUEVA}, {@code CREADA}, {@code INICIADA},
     *                 {@code REANUDADA}, {@code PAUSADA}, {@code PARADA} o {@code DESTRUIDA}
     * @param callback callback recibido: {@code onCreate}, {@code onStart}, {@code onResume},
     *                 {@code onPause}, {@code onStop}, {@code onRestart} o {@code onDestroy}
     * @return el nuevo estado, o el mismo {@code estado} si la transición no es válida; nunca {@code null}
     */
    public static String siguienteEstado(String estado, String callback) {
        // TODO 1: si estado o callback son null/blank -> devuelve "" (entrada inválida).
        // TODO 2: arranque hacia primer plano: (NUEVA,onCreate)->CREADA; (CREADA,onStart)->INICIADA; (INICIADA,onResume)->REANUDADA.
        // TODO 3: salida de primer plano: (REANUDADA,onPause)->PAUSADA; (PAUSADA,onStop)->PARADA.
        // TODO 4: volver al foco SIN haber parado: (PAUSADA,onResume)->REANUDADA (multitarea breve).
        // TODO 5: reactivar tras parar: (PARADA,onRestart)->CREADA (luego vendrá onStart de nuevo).
        // TODO 6: destrucción: (PARADA,onDestroy)->DESTRUIDA. Cualquier otra combinación -> devuelve el MISMO estado.
        return "";
    }

    /**
     * Aplica una secuencia de callbacks empezando en {@code NUEVA} y devuelve el estado final.
     *
     * @param callbacks lista ordenada de callbacks recibidos
     * @return el estado tras aplicarlos todos; {@code "NUEVA"} si la lista es vacía
     */
    public static String estadoTrasCallbacks(List<String> callbacks) {
        // TODO 7: arranca en el estado "NUEVA" (la Activity aún no ha recibido onCreate).
        // TODO 8: si callbacks es null o vacía -> devuelve "NUEVA".
        // TODO 9: recorre la lista aplicando siguienteEstado(estadoActual, callback) en cada paso.
        // TODO 10: devuelve el estado acumulado al final (los callbacks inválidos no lo mueven).
        return "";
    }

    public static void main(String[] args) {
        System.out.println("NUEVA + onCreate -> " + siguienteEstado("NUEVA", "onCreate"));
        System.out.println("Arranque completo -> "
                + estadoTrasCallbacks(List.of("onCreate", "onStart", "onResume")));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: ¿El usuario VE la pantalla en este estado? (visible aunque no tenga el foco).
     */
    public static boolean esVisible(String estado) {
        // GUÍA: teoría 2.3 (entre onStart y onStop la Activity es visible; PAUSADA aún se ve detrás de un diálogo).
        // 1. true si estado es "REANUDADA" o "PAUSADA" (o "INICIADA"); false en el resto.
        // PISTA: List.of("INICIADA","REANUDADA","PAUSADA").contains(estado).
        // OJO: el test: "REANUDADA" -> true, "PARADA" -> false (ya no se ve).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esVisible");
    }

    /**
     * Reto Extra 2: ¿La pantalla está en primer plano y ACEPTA interacción del usuario?
     */
    public static boolean esInteractuable(String estado) {
        // GUÍA: teoría 2.3 (solo en REANUDADA la Activity recibe toques; en PAUSADA está "congelada").
        // 1. true solo si estado es exactamente "REANUDADA".
        // PISTA: "REANUDADA".equals(estado).
        // OJO: el test: "REANUDADA" -> true, "PAUSADA" -> false (visible pero no interactiva).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esInteractuable");
    }

    /**
     * Reto Extra 3: ¿Es un estado terminal del que ya no se sale?
     */
    public static boolean esTerminal(String estado) {
        // GUÍA: teoría 2.4 (tras onDestroy la Activity muere; reabrirla crea una NUEVA instancia).
        // 1. true solo si estado es "DESTRUIDA".
        // OJO: el test: "DESTRUIDA" -> true, "PARADA" -> false (de PARADA aún se puede reactivar con onRestart).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esTerminal");
    }

    /**
     * Reto Extra 4: Callback que hace ENTRAR en un estado dado (el inverso del core).
     */
    public static String callbackDeEntrada(String estado) {
        // GUÍA: teoría 2.2 (cada estado se alcanza con un callback concreto).
        // 1. Mapea: CREADA->"onCreate", INICIADA->"onStart", REANUDADA->"onResume",
        //    PAUSADA->"onPause", PARADA->"onStop", DESTRUIDA->"onDestroy".
        // 2. Cualquier otro (NUEVA, null...) -> "".
        // PISTA: un switch expression sobre 'estado'.
        // OJO: el test: "REANUDADA" -> "onResume", "NUEVA" -> "" (no hay callback que cree el estado inicial).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para callbackDeEntrada");
    }

    /**
     * Reto Extra 5: La secuencia EXACTA de callbacks al lanzar la Activity por primera vez.
     */
    public static List<String> secuenciaArranque() {
        // GUÍA: teoría 2.2 (al crear una Activity siempre se invocan estos tres, en este orden).
        // 1. Devuelve List.of("onCreate","onStart","onResume").
        // OJO: el test compara la lista entera y su orden; no añadas onRestart.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para secuenciaArranque");
    }

    /**
     * Reto Extra 6: La secuencia EXACTA de callbacks al cerrar/destruir la Activity.
     */
    public static List<String> secuenciaCierre() {
        // GUÍA: teoría 2.2 (el camino inverso al arranque cuando el sistema la destruye).
        // 1. Devuelve List.of("onPause","onStop","onDestroy").
        // OJO: el test compara la lista y el orden (es el espejo de secuenciaArranque).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para secuenciaCierre");
    }

    /**
     * Reto Extra 7: ¿Es válida una transición concreta? (mueve de estado realmente).
     */
    public static boolean transicionValida(String estado, String callback) {
        // GUÍA: teoría 2.5 (reutiliza el core: una transición es válida si CAMBIA el estado).
        // 1. Calcula siguienteEstado(estado, callback).
        // 2. Es válida si el resultado NO está vacío Y es distinto del estado de partida.
        // PISTA: String s = siguienteEstado(estado, callback); return !s.isBlank() && !s.equals(estado);
        // OJO: el test: ("CREADA","onStart") -> true; ("CREADA","onResume") -> false (no se salta onStart).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para transicionValida");
    }

    /**
     * Reto Extra 8: Cuenta cuántas transiciones de la secuencia fueron VÁLIDAS (cambiaron el estado).
     */
    public static int contarTransicionesValidas(List<String> callbacks) {
        // GUÍA: teoría 2.5 (mide cuántos callbacks "hicieron efecto" arrancando en NUEVA).
        // 1. null/vacía -> 0.
        // 2. Recorre desde "NUEVA"; en cada paso, si el estado cambia, suma 1 y avanza; si no, sigue igual.
        // PISTA: lleva un estado y un contador; usa siguienteEstado y compara con el anterior.
        // OJO: el test mete ["onCreate","onResume","onStart"]: onResume sobre CREADA es inválido,
        //   así que solo onCreate y onStart cuentan -> 2 (el orden importa).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarTransicionesValidas");
    }

    /**
     * Reto Extra 9: ¿En este callback DEBES liberar recursos (cámara, sensores, GPS)?
     */
    public static boolean debeLiberarRecursos(String callback) {
        // GUÍA: teoría 2.6 (regla de oro: lo que tomas en onResume, suéltalo en onPause; lo de onStart, en onStop).
        // 1. true si el callback es "onPause", "onStop" o "onDestroy" (los de salida).
        // PISTA: List.of("onPause","onStop","onDestroy").contains(callback).
        // OJO: el test: "onPause" -> true, "onResume" -> false (ahí ADQUIERES, no liberas).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para debeLiberarRecursos");
    }

    /**
     * Reto Extra 10: Secuencia de callbacks al ROTAR la pantalla (cambio de configuración).
     */
    public static List<String> callbacksRotacion() {
        // GUÍA: teoría 2.7 (al rotar, Android DESTRUYE y RECREA la Activity para recargar recursos -land/-port).
        // 1. Devuelve la cadena completa: onPause, onStop, onDestroy, onCreate, onStart, onResume.
        // PISTA: concatena secuenciaCierre() + secuenciaArranque() (pero sin el onResume->onPause inicial).
        // OJO: el test compara la lista exacta de 6 callbacks en ese orden.
        // CULTURA: por esto se guarda el estado en onSaveInstanceState antes de onStop: la nueva instancia
        //   debe restaurar lo que el usuario tenía. En JavaFX (b32) el Stage NO se recrea al redimensionar,
        //   por eso allí no sufres este problema: es una diferencia clave Android <-> escritorio.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para callbacksRotacion");
    }
}
