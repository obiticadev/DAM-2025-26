package com.masterclass.api.b35_fxdata;

import java.util.List;
import java.util.function.Supplier;

import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Ejercicio 285 · {@code Platform.runLater} y la regla del hilo de aplicación de JavaFX.
 *
 * <p>Teoría: {@code teoria/35_JavaFX_Datos_Async.md} (sección 7).
 *
 * <p><b>La regla de oro de JavaFX:</b> SOLO el <i>FX Application Thread</i> puede tocar la interfaz
 * (crear ventanas, leer/escribir propiedades de nodos vivos). Si un hilo de fondo (un {@code Task},
 * Ej283) calcula un resultado y quiere mostrarlo, NO actualiza la UI directamente: programa esa
 * actualización en el hilo FX con {@code Platform.runLater(...)}. Romper esta regla provoca fallos
 * intermitentes y excepciones ("Not on FX application thread"). Aquí lo demostramos de forma
 * determinista, sin pantalla: comprobamos en qué hilo corre cada cosa y vemos qué peta y qué no.
 */
public final class Ej285PlatformRunLater {

    private Ej285PlatformRunLater() {
    }

    /**
     * Ejecuta la acción dada EN el hilo de aplicación de JavaFX y espera a que termine. Devuelve si
     * la acción se ejecutó efectivamente en ese hilo.
     *
     * @param accion trabajo a ejecutar en el hilo FX
     * @return true si corrió en el FX Application Thread; {@code false} sin implementar
     */
    public static boolean ejecutadoEnFx(Runnable accion) {
        // TODO 1: crea un boolean[] enFx = {false} y un CountDownLatch hecho = new CountDownLatch(1).
        // TODO 2: programa el trabajo: javafx.application.Platform.runLater(() -> { ... }).
        // TODO 3: dentro, captura enFx[0] = Platform.isFxApplicationThread() (¿estoy en el hilo FX?).
        // TODO 4: ejecuta accion.run() y, en un finally, haz hecho.countDown().
        // TODO 5: fuera, espera con hecho.await() (envuelve la InterruptedException).
        // TODO 6: devuelve enFx[0] (lo programado por runLater SIEMPRE corre en el hilo FX -> true).
        return false;
    }

    /**
     * Actualiza el texto de una etiqueta de forma SEGURA desde el hilo actual (que puede no ser el
     * FX): programa el cambio en el hilo FX, espera y devuelve el texto resultante.
     *
     * @param etiqueta la etiqueta a actualizar
     * @param texto    el nuevo texto
     * @return el texto que quedó en la etiqueta; {@code null} sin implementar
     */
    public static String actualizarEtiqueta(Label etiqueta, String texto) {
        // TODO 7: usa un CountDownLatch para esperar a que el hilo FX aplique el cambio.
        // TODO 8: Platform.runLater(() -> { etiqueta.setText(texto); latch.countDown(); }).
        // TODO 9: espera el latch (envuelve InterruptedException).
        // TODO 10: devuelve etiqueta.getText() (ya actualizado por el hilo FX).
        return null;
    }

    public static void main(String[] args) {
        System.out.println("¿main en hilo FX? " + javafx.application.Platform.isFxApplicationThread());
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: ¿Estoy en el hilo de la UI?
     * Devuelve si el hilo que llama es el FX Application Thread.
     */
    public static boolean esHiloDeAplicacion() {
        // GUÍA: teoría 7.1 (isFxApplicationThread distingue el hilo UI de cualquier otro).
        // 1. return javafx.application.Platform.isFxApplicationThread();
        // OJO: el test lo llama desde el hilo de prueba (NO el FX) -> false. Es la base para decidir si
        //   puedes tocar la UI directamente o necesitas runLater.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esHiloDeAplicacion");
    }

    /**
     * Reto Extra 2: Comprobar dentro del hilo FX.
     * Programa una comprobación en el hilo FX y devuelve si allí isFxApplicationThread es true.
     */
    public static boolean compruebaDentroDeFx() {
        // GUÍA: teoría 7.1 (lo que entra por runLater SIEMPRE corre en el hilo FX).
        // 1. Reutiliza ejecutadoEnFx(() -> {}) (no hace falta trabajo, solo medir el hilo).
        // 2. return ejecutadoEnFx(() -> { });
        // OJO: contraste con el reto 1: misma pregunta, distinto hilo -> aquí true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para compruebaDentroDeFx");
    }

    /**
     * Reto Extra 3: Ejecutar y esperar.
     * Ejecuta la acción en el hilo FX, espera a que acabe y devuelve true.
     */
    public static boolean ejecutarYEsperar(Runnable accion) {
        // GUÍA: teoría 7.2 (el patrón "programa + espera" con un CountDownLatch).
        // 1. CountDownLatch hecho = new CountDownLatch(1).
        // 2. Platform.runLater(() -> { try { accion.run(); } finally { hecho.countDown(); } }).
        // 3. hecho.await(). 4. return true.
        // OJO: en producción rara vez ESPERAS (bloquearías); aquí esperamos para que el test sea determinista.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ejecutarYEsperar");
    }

    /**
     * Reto Extra 4: Calcular un valor en el hilo FX.
     * Ejecuta el cálculo en el hilo FX y devuelve su resultado al hilo llamante.
     */
    public static <T> T calcularEnFx(Supplier<T> calculo) {
        // GUÍA: teoría 7.2 (a veces necesitas LEER algo de la UI desde fuera: hazlo en el hilo FX y trae el valor).
        // 1. AtomicReference<T> ref = new AtomicReference<>(). 2. CountDownLatch hecho = new CountDownLatch(1).
        // 3. Platform.runLater(() -> { try { ref.set(calculo.get()); } finally { hecho.countDown(); } }).
        // 4. hecho.await(). 5. return ref.get().
        // PISTA: AtomicReference es el "puente" para pasar el valor del hilo FX al hilo que espera.
        // OJO: el test pasa () -> 2 + 3 y espera 5; lo importante es que se calculó en el hilo correcto.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para calcularEnFx");
    }

    /**
     * Reto Extra 5: Orden de ejecución (FIFO).
     * Encola n tareas runLater que apuntan su índice y devuelve el orden en que se ejecutaron.
     */
    public static List<Integer> ordenDeEjecucion(int n) {
        // GUÍA: teoría 7.3 (Platform.runLater respeta el orden de encolado: primero en entrar, primero en correr).
        // 1. List<Integer> orden = java.util.Collections.synchronizedList(new ArrayList<>()).
        // 2. CountDownLatch hecho = new CountDownLatch(n).
        // 3. for i de 0 a n-1: final int idx = i; Platform.runLater(() -> { orden.add(idx); hecho.countDown(); }).
        // 4. hecho.await(). 5. return new ArrayList<>(orden).
        // PISTA: 'i' debe ser efectivamente final dentro de la lambda (copia a idx).
        // OJO: el test espera [0,1,2,...] EXACTO; eso prueba que la cola del hilo FX es FIFO.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ordenDeEjecucion");
    }

    /**
     * Reto Extra 6: Actualizar la UI desde un hilo de fondo.
     * Desde un Thread aparte, actualiza el texto de la etiqueta de forma segura y devuelve el texto final.
     */
    public static String actualizarDesdeOtroHilo(Label etiqueta, String texto) throws InterruptedException {
        // GUÍA: teoría 7.4 (el caso real: un hilo de fondo terminó y quiere mostrar el resultado).
        // 1. Thread hilo = new Thread(() -> actualizarEtiqueta(etiqueta, texto)). 2. hilo.start(). 3. hilo.join().
        // 4. return etiqueta.getText().
        // PISTA: reutiliza el core actualizarEtiqueta: ese ya hace el runLater correcto por dentro.
        // OJO: aunque el cambio se ORDENA desde un hilo de fondo, lo APLICA el hilo FX (gracias a runLater).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para actualizarDesdeOtroHilo");
    }

    /**
     * Reto Extra 7: Tocar la UI fuera del hilo FX PETA.
     * Intenta crear un {@link Stage} desde el hilo actual (no-FX); devuelve true si lanzó IllegalStateException.
     */
    public static boolean crearStageFallaFueraDeFx() {
        // GUÍA: teoría 7.5 (crear una ventana es una operación de UI: exige el hilo FX y si no, peta).
        // 1. try { new Stage(); return false; }
        // 2. catch (IllegalStateException e) { return true; }
        // PISTA: el mensaje de la excepción es "Not on FX application thread; currentThread = ...".
        // OJO: el test llama desde el hilo de prueba (no-FX) y espera true (saltó la excepción). ESTE es el
        //   error que sufres si actualizas la UI desde un Task sin runLater: intermitente y difícil de cazar.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearStageFallaFueraDeFx");
    }

    /**
     * Reto Extra 8: Crear la ventana en el hilo correcto SÍ funciona.
     * Crea un {@link Stage} con el título dado dentro del hilo FX y devuelve su título.
     */
    public static String crearStageEnFx(String titulo) {
        // GUÍA: teoría 7.5 (la misma operación, en el hilo correcto, va perfecta).
        // 1. Usa calcularEnFx(() -> { Stage s = new Stage(); s.setTitle(titulo); return s.getTitle(); }).
        // 2. return ese resultado.
        // OJO: contraste directo con el reto 7: cambia SOLO el hilo y deja de petar. El test espera 'titulo'.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearStageEnFx");
    }

    /**
     * Reto Extra 9: Suma calculada en el hilo FX.
     * Calcula a+b en el hilo FX y devuelve el resultado (refuerza el "traer un valor desde el hilo UI").
     */
    public static int sumarEnFx(int a, int b) {
        // GUÍA: teoría 7.2 (calcularEnFx generaliza esto; aquí lo concretas).
        // 1. return calcularEnFx(() -> a + b);
        // OJO: el test pasa (2,3) y espera 5. Trivial a propósito: lo que importa es DÓNDE se ejecuta.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para sumarEnFx");
    }

    /**
     * Reto Extra 10: El patrón completo fondo→UI.
     * Simula trabajo en un hilo de fondo (calcular n*n) y luego, en el hilo FX, escribe el resultado
     * en la etiqueta. Devuelve el texto final de la etiqueta.
     */
    public static String calcularEnFondoYMostrar(Label etiqueta, int n) throws InterruptedException {
        // GUÍA: teoría 7.6 (la arquitectura correcta: CALCULAR en hilo de fondo, MOSTRAR en hilo FX).
        // 1. int[] resultado = new int[1]. 2. CountDownLatch calculado = new CountDownLatch(1).
        // 3. Thread fondo = new Thread(() -> { resultado[0] = n * n; calculado.countDown(); }); fondo.start(); fondo.join().
        //    (el cálculo NO toca la UI -> puede ir en cualquier hilo).
        // 4. Ahora muestra el resultado en el hilo FX: actualizarEtiqueta(etiqueta, String.valueOf(resultado[0])).
        // 5. return etiqueta.getText().
        // PISTA: separa SIEMPRE las dos fases: cómputo (libre de hilo) y pintado (hilo FX).
        // OJO: el test pasa n=4 y espera "16". Si intentaras etiqueta.setText desde el hilo 'fondo' con la
        //   ventana mostrándose, tendrías el bug del reto 7.
        // CULTURA: esto es EXACTAMENTE lo que hace un Task con setOnSucceeded (Ej283/284) y lo que usarás en
        //   Ej286: el GET a la API corre en segundo plano y, al volver, vuelca los datos en la TableView en el hilo FX.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para calcularEnFondoYMostrar");
    }
}
