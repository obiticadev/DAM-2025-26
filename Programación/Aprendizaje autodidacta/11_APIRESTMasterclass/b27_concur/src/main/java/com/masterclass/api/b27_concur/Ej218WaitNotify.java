package com.masterclass.api.b27_concur;

/**
 * Ejercicio 218 · {@code wait}/{@code notify}: el productor-consumidor clásico.
 *
 * <p>Coordinación de bajo nivel: un hilo espera ({@code wait}) liberando el monitor hasta
 * que otro lo avisa ({@code notify}/{@code notifyAll}). Es la base teórica de las colas
 * bloqueantes que luego usarás ya hechas ({@code BlockingQueue}, Ej223).
 *
 * <p>Teoría: {@code teoria/27_Concurrencia_Multihilo.md} (sección 27.4).
 */
public final class Ej218WaitNotify {

    private Ej218WaitNotify() {
    }

    /**
     * Cola acotada (buffer circular simple) con bloqueo por {@code wait}/{@code notifyAll}.
     * Los métodos {@code put}/{@code take} son el corazón del ejercicio.
     */
    public static final class Cola {
        private final int[] datos;
        private int cuenta;
        private int cabeza;
        private int cola;

        public Cola(int capacidad) {
            this.datos = new int[capacidad];
        }

        /** Inserta un valor; si la cola está llena, espera. */
        public synchronized void put(int valor) throws InterruptedException {
            // TODO 1: mientras (cuenta == datos.length) llama a wait() (cola llena -> esperar hueco).
            // TODO 2: guarda 'valor' en datos[cola], avanza cola = (cola+1) % datos.length y cuenta++.
            // TODO 3: avisa a los consumidores con notifyAll().
            throw new UnsupportedOperationException("TODO put");
        }

        /** Extrae un valor; si la cola está vacía, espera. */
        public synchronized int take() throws InterruptedException {
            // TODO 4: mientras (cuenta == 0) llama a wait() (cola vacía -> esperar dato).
            // TODO 5: lee v = datos[cabeza], avanza cabeza = (cabeza+1) % datos.length y cuenta--.
            // TODO 6: notifyAll() (hay hueco) y devuelve v.
            throw new UnsupportedOperationException("TODO take");
        }

        public synchronized int tamano() {
            return cuenta;
        }
    }

    /**
     * Un productor mete 1..nElementos en la cola; un consumidor los saca y los cuenta.
     *
     * @param nElementos cuántos elementos circulan
     * @param capacidad  tamaño del buffer
     * @return número de elementos consumidos (== nElementos si nada se pierde)
     */
    public static int produceConsume(int nElementos, int capacidad) {
        // TODO 7: crea una Cola(capacidad) y un contenedor int[] consumidos = {0}.
        // TODO 8: hilo productor: for i in 1..nElementos -> cola.put(i) (envuelve InterruptedException).
        // TODO 9: hilo consumidor: for i in 1..nElementos -> cola.take(); consumidos[0]++.
        // TODO 10: arranca ambos, join a ambos y devuelve consumidos[0].
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("produceConsume(1000, 16) = " + produceConsume(1000, 16));
    }

    /**
     * Reto Extra 1: Varios consumidores con notifyAll.
     * @return total consumido entre todos los consumidores (== nElementos)
     */
    public static int variosConsumidores(int nElementos, int capacidad, int nConsumidores) {
        // GUÍA: con varios consumidores DEBES usar notifyAll (notify despertaría solo a uno y
        // podría ser el "equivocado", provocando bloqueos). Reparte nElementos: un productor
        // mete un valor "veneno" (p.ej. -1) por consumidor para que cada uno sepa cuándo parar,
        // o cuenta con un AtomicInteger compartido hasta llegar a nElementos.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para variosConsumidores");
    }

    /**
     * Reto Extra 2: El {@code while} es obligatorio (spurious wakeups).
     * @return true (la condición se reevalúa en bucle, no con un if)
     */
    public static boolean usarWhileNoIf() {
        // GUÍA: wait() puede retornar SIN que nadie haya hecho notify ("spurious wakeup").
        // Por eso la condición SIEMPRE se comprueba con while, nunca con if. Este reto solo
        // documenta el principio: una vez implementada la Cola con while, devuelve true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para usarWhileNoIf");
    }

    /**
     * Reto Extra 3: Varios productores y varios consumidores.
     * @return total consumido (== nProductores * elementosPorProductor)
     */
    public static int variosProductoresYConsumidores(int nProductores, int nConsumidores, int elementosPorProductor) {
        // GUÍA: total = nProductores*elementosPorProductor. Usa un AtomicInteger 'restantes'
        // para que los consumidores sepan cuándo no quedan elementos, o veneno por consumidor.
        // Un AtomicInteger 'consumidos' cuenta el resultado.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para variosProductoresYConsumidores");
    }

    /**
     * Reto Extra 4: El buffer nunca excede su capacidad.
     * @return true si en ningún momento el tamaño de la cola supera 'capacidad'
     */
    public static boolean bufferNuncaExcedeCapacidad(int nElementos, int capacidad) {
        // GUÍA: la propiedad la garantiza el wait() del put. Para "observarla", el consumidor
        // (o un muestreo) verifica cola.tamano() <= capacidad. Si tu put espera bien, jamás se
        // supera. Devuelve true cuando produceConsume corre sin violar la cota.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para bufferNuncaExcedeCapacidad");
    }

    /**
     * Reto Extra 5: Orden FIFO con un único productor y un único consumidor.
     * @return true si los elementos salen en el mismo orden 1..n en que entraron
     */
    public static boolean ordenFIFO(int nElementos, int capacidad) {
        // GUÍA: con 1 productor y 1 consumidor, la cola circular preserva el orden. El consumidor
        // comprueba que take() devuelve 1,2,3,...,n en secuencia. Devuelve true si se cumple.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ordenFIFO");
    }

    /**
     * Reto Extra 6: Suma transferida íntegra.
     * @return suma de los valores consumidos (== n*(n+1)/2 para 1..n)
     */
    public static long sumaTransferida(int nElementos, int capacidad) {
        // GUÍA: el consumidor acumula la suma de lo que saca. Para 1..n debe dar n*(n+1)/2,
        // demostrando que no se pierde ni duplica ningún elemento.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para sumaTransferida");
    }

    /**
     * Reto Extra 7: Productor más rápido que consumidor (cola se llena).
     * @return total consumido (== nElementos) aunque el consumidor sea lento
     */
    public static int consumidorLento(int nElementos, int capacidad) {
        // GUÍA: el consumidor puede tardar un poco (no uses sleeps largos; basta con que la cola
        // se llene). El put bloqueará y se reanudará al haber hueco. El total no se altera.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para consumidorLento");
    }

    /**
     * Reto Extra 8: Productor lento, cola se vacía.
     * @return total consumido (== nElementos) aunque el productor sea lento
     */
    public static int productorLento(int nElementos, int capacidad) {
        // GUÍA: simétrico al anterior: el take bloqueará en cola vacía hasta que llegue dato.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para productorLento");
    }

    /**
     * Reto Extra 9: Capacidad 1 (relevo estricto).
     * @return total consumido (== nElementos) con un buffer de un solo hueco
     */
    public static int capacidadUno(int nElementos) {
        // GUÍA: con capacidad 1, productor y consumidor se turnan en lockstep. Sigue funcionando
        // si put/take usan while correctamente. Llama a produceConsume(nElementos, 1).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para capacidadUno");
    }

    /**
     * Reto Extra 10: notifyAll despierta a todos los que esperan.
     * @return true si tras un notifyAll todos los consumidores en wait pueden progresar
     */
    public static boolean notifyAllDespiertaATodos() {
        // GUÍA: arranca varios consumidores que entran en w() sobre cola vacía; un productor mete
        // tantos elementos como consumidores y todos terminan. Si usaras notify (uno), alguno
        // podría quedarse colgado. Devuelve true si todos progresan (protégelo con @Timeout).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para notifyAllDespiertaATodos");
    }
}
