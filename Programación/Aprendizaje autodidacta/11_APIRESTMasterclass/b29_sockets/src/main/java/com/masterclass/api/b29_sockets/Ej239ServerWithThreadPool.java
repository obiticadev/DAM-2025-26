package com.masterclass.api.b29_sockets;

import java.util.List;

/**
 * Ejercicio 239 · Servidor con pool de hilos: límite de conexiones concurrentes.
 *
 * <p>"Un hilo por conexión" (Ej235) es simple pero peligroso: con 10.000 clientes creas
 * 10.000 hilos y tumbas la máquina. La solución profesional es un {@code ExecutorService}
 * (b27·Ej219): el aceptador hace {@code accept()} y delega cada conexión al pool con
 * {@code submit(...)}. El pool reutiliza un número <b>acotado</b> de hilos; el resto de
 * conexiones esperan en cola. Así el servidor degrada con gracia bajo carga en vez de morir.
 *
 * <p>Es el patrón que usa por debajo un servidor REST como el de Spring/Tomcat (un pool de
 * worker threads). Aquí cruzas sockets (b29) con pools (b27).
 *
 * <p>Teoría: {@code teoria/29_Sockets_Red.md} (sección 29.7).
 */
public final class Ej239ServerWithThreadPool {

    private Ej239ServerWithThreadPool() {
    }

    /**
     * Atiende nClientes con un pool fijo de nHilos y cuenta cuántos se sirvieron.
     *
     * @param nClientes clientes que se conectan
     * @param nHilos    tamaño del pool
     * @return número de clientes atendidos (== nClientes)
     */
    public static int atenderConPoolFijo(int nClientes, int nHilos) {
        // TODO 1: crea ExecutorService pool = Executors.newFixedThreadPool(nHilos) y un AtomicInteger atendidos.
        // TODO 2: abre ServerSocket(0); lanza un hilo aceptador con un bucle for i<nClientes { accept() }.
        // TODO 3: por cada Socket aceptado, pool.submit(() -> { atiende eco una línea; atendidos.incrementAndGet(); cierra; }).
        // TODO 4: lanza nClientes clientes (en paralelo) que conectan, envían una línea y leen el eco; usa un latch.
        // TODO 5: espera el latch; luego pool.shutdown() y pool.awaitTermination(...).
        // TODO 6: cierra el ServerSocket y devuelve atendidos.get().
        return -1;
    }

    /**
     * Cada cliente envía un número; el pool de handlers los suma en un acumulador atómico.
     *
     * @param numeros números enviados (uno por cliente)
     * @return suma total
     */
    public static long sumarConPool(List<Integer> numeros) {
        // TODO 7: pool fijo (p.ej. 4 hilos) + AtomicLong total + ServerSocket(0).
        // TODO 8: el aceptador acepta numeros.size() conexiones y delega cada una al pool.
        // TODO 9: cada handler lee un número y hace total.addAndGet(n); el cliente envía su número.
        // TODO 10: tras coordinar el final con un latch, cierra y devuelve total.get().
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("atenderConPoolFijo(6,2) = " + atenderConPoolFijo(6, 2));
        System.out.println("sumarConPool([1,2,3]) = " + sumarConPool(List.of(1, 2, 3)));
    }

    /**
     * Reto Extra 1: el pool REUTILIZA hilos (menos hilos que conexiones).
     * @return número de hilos distintos que atendieron (debe ser &gt; 0 y &lt;= nHilos)
     */
    public static int poolReutilizaHilos(int nClientes, int nHilos) {
        // GUÍA: teoría 29.7 + b27·Ej219. Cada handler hace usados.add(Thread.currentThread().getName())
        // sobre un Set concurrente. Con un pool de nHilos, usados.size() <= nHilos aunque haya muchos
        // más clientes. CONTRASTE con Ej235.cadaClienteEnHiloDistinto (allí 1 hilo por conexión).
        // OJO: el test llama con (50, 3) y exige 0 < usados <= 3.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para poolReutilizaHilos");
    }

    /**
     * Reto Extra 2: la concurrencia simultánea nunca supera el tamaño del pool.
     * @return el máximo número de handlers activos a la vez observado (debe ser &lt;= nHilos)
     */
    public static int concurrenciaLimitadaAlPool(int nHilos) {
        // GUÍA: mantén un AtomicInteger 'activos'. Cada handler hace activos.incrementAndGet() al entrar
        // y decrementAndGet() al salir; registra el pico con un AtomicInteger pico actualizado con
        // updateAndGet(max). Lanza más clientes que hilos y handlers algo lentos (sleep breve).
        // OJO/CUIDADO: el pico NUNCA puede pasar de nHilos; el test usa nHilos=2 y exige 1 <= pico <= 2.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para concurrenciaLimitadaAlPool");
    }

    /**
     * Reto Extra 3: las tareas que no caben se encolan y aun así terminan todas.
     * @return número de conexiones completadas (== nClientes) con un pool pequeño
     */
    public static int colaAbsorbeTareasExtra(int nClientes) {
        // GUÍA: con un pool de 2 hilos y nClientes=20, las 18 que no caben esperan en la cola interna
        // del executor y se procesan cuando un hilo queda libre. Al final se atienden las 20.
        // PISTA: newFixedThreadPool usa una LinkedBlockingQueue ilimitada por defecto: nada se rechaza.
        // OJO: el test usa 20 y espera 20.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para colaAbsorbeTareasExtra");
    }

    /**
     * Reto Extra 4: con una cola ACOTADA y AbortPolicy, el exceso se rechaza.
     * @return true si saturar un ThreadPoolExecutor de cola acotada lanza RejectedExecutionException
     */
    public static boolean rechazoConColaAcotada() {
        // GUÍA: new ThreadPoolExecutor(1,1,0L,MILLISECONDS, new ArrayBlockingQueue<>(1),
        //        new ThreadPoolExecutor.AbortPolicy()). Lanza una tarea bloqueante que ocupe el hilo,
        // llena la cola (1) y al submit siguiente salta RejectedExecutionException.
        // try { ...submits... ; return false; } catch (RejectedExecutionException e) { return true; }
        // CULTURA: la política de rechazo es cómo defines el "back-pressure" del servidor.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para rechazoConColaAcotada");
    }

    /**
     * Reto Extra 5: un ThreadFactory propio nombra los hilos del pool.
     * @return true si los hilos del pool llevan un nombre que contiene "worker"
     */
    public static boolean threadFactoryNombraHilos() {
        // GUÍA: ThreadFactory tf = r -> new Thread(r, "worker-" + System.nanoTime());
        //   ExecutorService pool = Executors.newFixedThreadPool(2, tf);
        // En el handler comprueba Thread.currentThread().getName().contains("worker").
        // CULTURA: nombrar los worker threads hace los logs y los thread dumps legibles en producción.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para threadFactoryNombraHilos");
    }

    /**
     * Reto Extra 6: un pool cacheado atiende un pico de conexiones cortas.
     * @return número de clientes atendidos con newCachedThreadPool (== nClientes)
     */
    public static int cachedPoolAtiende(int nClientes) {
        // GUÍA: Executors.newCachedThreadPool() crea hilos bajo demanda y los recicla; ideal para
        // ráfagas de tareas cortas. Misma estructura de conteo con AtomicInteger.
        // OJO: el test usa 10 y espera 10.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para cachedPoolAtiende");
    }

    /**
     * Reto Extra 7: una excepción en un handler no mata el pool; los demás siguen.
     * @return número de clientes atendidos con éxito pese a que uno falla (== nOk)
     */
    public static int excepcionEnHandlerNoMataPool(int nOk) {
        // GUÍA: una tarea lanza RuntimeException (el pool la captura: un worker que muere por excepción
        // es reemplazado). Los nOk handlers normales terminan. CONTRASTE con b27·Ej219.futureGet...:
        // con submit, la excepción quedaría en el Future; aquí no lo consultamos, el pool sigue vivo.
        // OJO: el test usa nOk=4 (más un cliente que provoca el fallo) y espera 4.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para excepcionEnHandlerNoMataPool");
    }

    /**
     * Reto Extra 8: shutdown ordenado + awaitTermination dentro de plazo.
     * @return true si tras atender, shutdown() + awaitTermination(timeout) devuelve true
     */
    public static boolean shutdownYAwaitTermination() {
        // GUÍA: atiende unas pocas conexiones, luego pool.shutdown() (no acepta más, deja acabar las
        // pendientes) y return pool.awaitTermination(5, TimeUnit.SECONDS).
        // OJO: shutdown() NO interrumpe las tareas en curso; shutdownNow() sí (ver reto 9).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para shutdownYAwaitTermination");
    }

    /**
     * Reto Extra 9: submit tras shutdown se rechaza.
     * @return true si pool.submit(...) tras shutdown() lanza RejectedExecutionException
     */
    public static boolean submitTrasShutdownRechazado() {
        // GUÍA: pool.shutdown(); try { pool.submit(() -> {}); return false; }
        //   catch (RejectedExecutionException e) { return true; }
        // (mismo concepto que b27·Ej219.rejectedExecutionTrasShutdown, aquí en contexto de servidor).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para submitTrasShutdownRechazado");
    }

    /**
     * Reto Extra 10: el pool usa menos hilos que clientes (la razón de existir del pool).
     * @return true si el nº de hilos distintos usados es estrictamente menor que nClientes
     */
    public static boolean poolUsaMenosHilosQueClientes() {
        // GUÍA: reutiliza poolReutilizaHilos con muchos clientes y pocos hilos (p.ej. 30 clientes,
        // 3 hilos): return poolReutilizaHilos(30, 3) < 30;  (será <= 3 < 30).
        // CULTURA: esta es la moraleja del bloque — acotar recursos para escalar sin reventar la máquina.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para poolUsaMenosHilosQueClientes");
    }
}
