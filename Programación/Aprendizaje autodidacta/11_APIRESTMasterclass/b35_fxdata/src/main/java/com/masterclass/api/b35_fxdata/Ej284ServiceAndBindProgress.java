package com.masterclass.api.b35_fxdata;

import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;

/**
 * Ejercicio 284 · {@code Service<V>}, reutilización y {@code ProgressBar} <i>bindeada</i>.
 *
 * <p>Teoría: {@code teoria/35_JavaFX_Datos_Async.md} (sección 6).
 *
 * <p>Un {@code Task} es de un solo uso (cuando termina, no se puede relanzar). Cuando necesitas
 * REPETIR el mismo trabajo (recargar la tabla cada vez que el usuario pulsa "Actualizar") usas un
 * {@code Service<V>}: tú implementas {@code createTask()} (la fábrica del trabajo) y el Service lo
 * arranca con {@code start()} y lo repite con {@code restart()}. Además, la barra de progreso de la
 * UI no se actualiza a mano: se <i>bindea</i> a la {@code progressProperty} del Service/Task y se
 * mueve sola. La lógica testeable sin pantalla: construir el Service, ejecutarlo y leer su valor, y
 * comprobar el enlace de la barra.
 */
public final class Ej284ServiceAndBindProgress {

    private Ej284ServiceAndBindProgress() {
    }

    /**
     * Crea un {@link Service} reutilizable cuyo trabajo suma 1..n reportando progreso. Cada
     * {@code start()}/{@code restart()} pedirá un {@code Task} nuevo a {@code createTask()}.
     *
     * @param n cota superior de la suma
     * @return el Service listo (sin arrancar); {@code null} sin implementar
     */
    public static Service<Integer> servicioSuma(int n) {
        // TODO 1: devuelve un new Service<Integer>() { @Override protected Task<Integer> createTask() { ... } }.
        // TODO 2: dentro de createTask(), devuelve un new Task<Integer>() { @Override protected Integer call() { ... } }.
        // TODO 3: en call(), suma 1..n con un bucle (int suma += i).
        // TODO 4: reporta progreso en cada paso con updateProgress(i, n).
        // TODO 5: return suma desde call().
        // TODO 6: createTask() se llama de NUEVO en cada restart(): por eso un Service sí se reutiliza.
        return null;
    }

    /**
     * Enlaza ({@code bind}) el progreso de la barra a la property de progreso dada, de modo que la
     * barra se mueva sola cuando la property cambie. La barra queda en solo lectura sobre ese valor.
     *
     * @param barra    la barra de progreso de la UI
     * @param progreso property de progreso (la de un Task/Service, 0..1)
     * @return true si la barra quedó enlazada; {@code false} sin implementar
     */
    public static boolean vincularProgreso(ProgressBar barra, ObservableValue<? extends Number> progreso) {
        // TODO 7: enlaza la barra: barra.progressProperty().bind(progreso).
        // TODO 8: a partir de ahora NO puedes hacer barra.setProgress(...) a mano (lanzaría excepción).
        // TODO 9: cuando 'progreso' cambie, la barra se actualizará sola (eso es bindear la UI al modelo).
        // TODO 10: devuelve barra.progressProperty().isBound() (debe ser true).
        return false;
    }

    public static void main(String[] args) {
        Service<Integer> servicio = servicioSuma(5);
        System.out.println("Estado inicial del servicio: " + (servicio == null ? "?" : servicio.getState()));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Estado inicial.
     * Devuelve el estado del Service recién creado (antes de arrancarlo).
     */
    public static Worker.State estadoInicial(Service<?> servicio) {
        // GUÍA: teoría 6.1 (un Service nace en estado READY; pasa a SCHEDULED/RUNNING al start()).
        // 1. return servicio.getState();
        // OJO: el test crea el Service y espera Worker.State.READY SIN arrancarlo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para estadoInicial");
    }

    /**
     * Reto Extra 2: Barra al 50%.
     * Pon la barra al valor dado (sin bind) y devuelve su progreso leído.
     */
    public static double ponerProgreso(ProgressBar barra, double valor) {
        // GUÍA: teoría 6.2 (setProgress mueve la barra a mano; vale cuando NO está bindeada).
        // 1. barra.setProgress(valor). 2. return barra.getProgress().
        // OJO: el rango útil es 0..1 (0.5 = mitad). Si luego haces bind, ya no podrás usar setProgress.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ponerProgreso");
    }

    /**
     * Reto Extra 3: Barra indeterminada.
     * Pon la barra en modo "indeterminado" (no se sabe cuánto falta) y devuelve su progreso.
     */
    public static double barraIndeterminada(ProgressBar barra) {
        // GUÍA: teoría 6.2 (un progreso negativo activa la animación "no sé cuánto falta").
        // 1. barra.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS). 2. return barra.getProgress().
        // PISTA: INDETERMINATE_PROGRESS vale -1.0 (igual que updateProgress con total<=0, Ej283 reto 8).
        // OJO: el test comprueba que el progreso es < 0. Úsalo mientras no sabes el total (un GET sin Content-Length).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para barraIndeterminada");
    }

    /**
     * Reto Extra 4: Desvincular la barra.
     * Enlaza la barra a la property, luego la desenlaza, y devuelve si quedó libre (no bindeada).
     */
    public static boolean desvincular(ProgressBar barra, ObservableValue<? extends Number> progreso) {
        // GUÍA: teoría 6.2 (unbind libera la barra para volver a controlarla a mano).
        // 1. barra.progressProperty().bind(progreso). 2. barra.progressProperty().unbind().
        // 3. return !barra.progressProperty().isBound().
        // OJO: tras unbind, la barra conserva el último valor pero ya acepta setProgress otra vez. Test: true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desvincular");
    }

    /**
     * Reto Extra 5: Ejecutar el servicio y esperar el resultado.
     * Arranca el Service (en el hilo FX), espera a que termine y devuelve el valor calculado.
     */
    public static <V> V ejecutarServicio(Service<V> servicio) throws Exception {
        // GUÍA: teoría 6.3 (start() DEBE llamarse en el hilo FX; para esperar en un test, usa un latch).
        // 1. java.util.concurrent.atomic.AtomicReference<V> ref = new AtomicReference<>().
        // 2. java.util.concurrent.CountDownLatch listo = new CountDownLatch(1).
        // 3. javafx.application.Platform.runLater(() -> {
        //        servicio.setOnSucceeded(e -> { ref.set(servicio.getValue()); listo.countDown(); });
        //        servicio.setOnFailed(e -> listo.countDown());
        //        servicio.start(); }).
        // 4. listo.await(5, java.util.concurrent.TimeUnit.SECONDS). 5. return ref.get().
        // PISTA: el handler setOnSucceeded corre en el hilo FX; ahí getValue() ya está fijado.
        // OJO: NUNCA bloquees el hilo FX esperando; el await() corre en el hilo del test, no en el FX. El
        //   test añade @Timeout para no colgarse si algo va mal.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ejecutarServicio");
    }

    /**
     * Reto Extra 6: Reutilizar el servicio.
     * Ejecuta el Service, lo relanza con restart() y devuelve el valor de la SEGUNDA ejecución.
     */
    public static <V> V reutilizar(Service<V> servicio) throws Exception {
        // GUÍA: teoría 6.4 (la gracia del Service frente al Task: restart() vuelve a hacer el trabajo).
        // 1. Ejecuta y espera la primera vez (como en el reto 5).
        // 2. Repite el patrón llamando a servicio.restart() en lugar de start() y vuelve a esperar.
        // 3. Devuelve el valor de esa segunda vuelta.
        // PISTA: un Task NO podría: una vez terminado, run() no vuelve a ejecutarse. El Service crea un Task nuevo.
        // OJO: el test comprueba que la segunda ejecución da el MISMO resultado que la primera (trabajo idéntico).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para reutilizar");
    }

    /**
     * Reto Extra 7: Reiniciar a estado limpio.
     * Tras ejecutar el Service, llama a reset() y devuelve el estado resultante.
     */
    public static <V> Worker.State resetear(Service<V> servicio) throws Exception {
        // GUÍA: teoría 6.4 (reset() devuelve el Service a READY; solo se puede si NO está corriendo).
        // 1. Ejecuta y espera (reto 5). 2. En el hilo FX, llama a servicio.reset(). 3. Devuelve servicio.getState().
        // PISTA: reset() debe hacerse en el hilo FX (toca properties); usa Platform.runLater + espera.
        // OJO: si intentas reset() con el Service aún corriendo, lanza IllegalStateException. Test: READY.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para resetear");
    }

    /**
     * Reto Extra 8: Progreso como porcentaje.
     * Convierte un progreso 0..1 en su texto de porcentaje entero ("0%".."100%").
     */
    public static String porcentaje(double progreso) {
        // GUÍA: teoría 6.5 (al lado de la barra suele ir un Label "73%"; aquí calculas ese texto).
        // 1. Redondea: long p = Math.round(progreso * 100). 2. return p + "%".
        // PISTA: en la UI bindearías el Label con service.progressProperty().multiply(100)... ; aquí es el cálculo.
        // OJO: el test usa 0.5 -> "50%" y 1.0 -> "100%". Math.round evita decimales feos (0.736 -> 74%).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para porcentaje");
    }

    /**
     * Reto Extra 9: Service de un valor fijo.
     * Crea un {@link Service} cuyo trabajo devuelve directamente el valor dado.
     */
    public static <V> Service<V> servicioDeValor(V valor) {
        // GUÍA: teoría 6.1 (createTask es la única pieza obligatoria de un Service).
        // 1. return new Service<V>() { @Override protected Task<V> createTask() {
        //        return new Task<V>() { @Override protected V call() { return valor; } }; } };
        // OJO: el test lo ejecuta con ejecutarServicio(...) y comprueba get()==valor.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para servicioDeValor");
    }

    /**
     * Reto Extra 10: Capturar el fallo de un Service.
     * Crea un Service que falla, ejecútalo y devuelve el mensaje de la excepción (vía setOnFailed).
     */
    public static String mensajeDeFalloServicio(String mensaje) throws Exception {
        // GUÍA: teoría 6.6 (un Service avisa de errores por setOnFailed -> getException(), en el hilo FX).
        // 1. Service<Integer> s = new Service<>() { protected Task<Integer> createTask() {
        //        return new Task<>() { protected Integer call() { throw new RuntimeException(mensaje); } }; } }.
        // 2. AtomicReference<String> ref = new AtomicReference<>("").
        // 3. CountDownLatch listo = new CountDownLatch(1).
        // 4. Platform.runLater(() -> { s.setOnFailed(e -> { ref.set(s.getException().getMessage()); listo.countDown(); }); s.start(); }).
        // 5. listo.await(5, SECONDS). 6. return ref.get().
        // PISTA: s.getException() es la excepción que lanzó call() (aquí la RuntimeException con 'mensaje').
        // OJO: el manejo de error de fondo SIEMPRE llega por el hilo FX (onFailed); así puedes mostrar un
        //   Alert sin romper la regla del hilo único (Ej285).
        // CULTURA: setOnSucceeded/onFailed es el equivalente JavaFX de un .then()/.catch() de una Promesa JS,
        //   o del onSuccess/onError de un callback. En Ej286 lo usas para poblar la tabla o mostrar el error de la API.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mensajeDeFalloServicio");
    }
}
