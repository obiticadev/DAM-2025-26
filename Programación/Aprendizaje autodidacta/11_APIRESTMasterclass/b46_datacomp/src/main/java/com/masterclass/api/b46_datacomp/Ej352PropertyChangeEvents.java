package com.masterclass.api.b46_datacomp;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

/**
 * Ejercicio 352 · Eventos del componente: {@code PropertyChangeSupport} ("bound") y "vetoable".
 *
 * <p>Teoría: {@code teoria/46_Componentes_Datos.md} (sección 2).
 *
 * <p>Un componente no solo se deja leer (351): además <em>avisa</em> cuando su estado cambia, para
 * que quien lo usa reaccione sin sondearlo. El JDK trae la maquinaria lista:
 * {@link PropertyChangeSupport} registra oyentes y dispara un {@link PropertyChangeEvent} con
 * {@code (nombrePropiedad, valorViejo, valorNuevo)}. Una propiedad <em>bound</em> notifica el
 * cambio; una <em>constrained</em> (con {@link VetoableChangeSupport}) permite además VETARLO.
 */
public final class Ej352PropertyChangeEvents {

    private Ej352PropertyChangeEvents() {
    }

    /**
     * Dispara UN cambio de propiedad sobre un {@link PropertyChangeSupport} y devuelve lo que
     * captura un oyente, en formato {@code "propiedad:viejo->nuevo"}.
     *
     * @return lista con la única notificación capturada, o {@code List.of()} si no se disparó
     *         ninguna (porque viejo y nuevo eran iguales: PCS no notifica cambios "no-cambio")
     */
    public static List<String> notificarCambio(String propiedad, Object viejo, Object nuevo) {
        // TODO 1: crea un PropertyChangeSupport (su "fuente" puede ser un Object cualquiera, p.ej. "comp").
        // TODO 2: prepara una List<String> donde el oyente irá anotando lo que reciba.
        // TODO 3: crea un PropertyChangeListener cuyo propertyChange(evt) añada a la lista
        //         evt.getPropertyName()+":"+evt.getOldValue()+"->"+evt.getNewValue().
        // TODO 4: registra el oyente con addPropertyChangeListener.
        // TODO 5: dispara el cambio con firePropertyChange(propiedad, viejo, nuevo).
        // TODO 6: devuelve la lista (será [] si viejo.equals(nuevo): PCS NO dispara si no cambia).
        return List.of();
    }

    /**
     * Cuenta cuántas notificaciones genera una secuencia de mutaciones sobre un
     * {@link PropertyChangeSupport}.
     *
     * @param mutaciones lambda que recibe el PCS ya con el oyente puesto y lo hace disparar
     * @return número de eventos recibidos por el oyente, o {@code -1} si {@code mutaciones} es null
     */
    public static int contarNotificaciones(Consumer<PropertyChangeSupport> mutaciones) {
        // TODO 7: si mutaciones es null, devuelve -1.
        // TODO 8: crea el PCS y un contador (un int[1] o un AtomicInteger valen).
        // TODO 9: registra un oyente que incremente el contador en cada propertyChange, y luego
        //         ejecuta mutaciones.accept(pcs) para que dispare los cambios.
        // TODO 10: devuelve el valor final del contador.
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(notificarCambio("temperatura", 20, 25)); // [temperatura:20->25]
        System.out.println(notificarCambio("temperatura", 20, 20)); // [] (no cambia)
        System.out.println(contarNotificaciones(pcs -> {
            pcs.firePropertyChange("x", 1, 2);
            pcs.firePropertyChange("x", 2, 3);
        })); // 2
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: dispara un evento "a mano" aunque el valor no cambie (fire manual con evento).
     */
    public static int dispararManual(String propiedad) {
        // GUÍA: teoría 2 (a veces quieres forzar la notificación aunque viejo==nuevo, p.ej. para
        //   refrescar la vista). firePropertyChange(propiedad, valor, valor) NO dispara; pasar un
        //   PropertyChangeEvent ya construido SÍ.
        // 1. crea el PCS y un contador.
        // 2. registra un oyente que incremente el contador.
        // 3. dispara con pcs.firePropertyChange(new PropertyChangeEvent(fuente, propiedad, 7, 7)).
        // 4. devuelve el contador (debe ser 1 pese a que viejo==nuevo).
        // PISTA: pcs.firePropertyChange(new PropertyChangeEvent("c", propiedad, 7, 7));
        // OJO: el test exige 1. Si usaras la sobrecarga firePropertyChange(propiedad,7,7) saldría 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para dispararManual");
    }

    /**
     * Reto Extra 2: registra N oyentes y devuelve cuántas notificaciones llegan en total al disparar una vez.
     */
    public static int notificarAVariosListeners(int n) {
        // GUÍA: teoría 2 (un mismo cambio se reparte a TODOS los oyentes registrados).
        // 1. crea el PCS y un contador compartido.
        // 2. registra n oyentes, cada uno incrementa el contador.
        // 3. dispara UN solo cambio real (p.ej. firePropertyChange("p", 1, 2)).
        // 4. devuelve el contador: será n (un disparo -> n entregas).
        // PISTA: for (int i=0;i<n;i++) pcs.addPropertyChangeListener(e -> cont.incrementAndGet());
        // OJO: el test usa n=3 -> 3. Un único firePropertyChange, no n disparos.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para notificarAVariosListeners");
    }

    /**
     * Reto Extra 3: comprueba que los oyentes se notifican EN ORDEN de registro.
     */
    public static String ordenDeNotificacion() {
        // GUÍA: teoría 2 (PCS notifica en el mismo orden en que registraste los oyentes).
        // 1. crea el PCS y una List<String>.
        // 2. registra tres oyentes que añadan "A", "B" y "C" respectivamente.
        // 3. dispara un cambio y devuelve los tres unidos por comas.
        // PISTA: String.join(",", lista) tras el firePropertyChange.
        // OJO: el test espera exactamente "A,B,C". Si el orden no se respetara, fallaría.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ordenDeNotificacion");
    }

    /**
     * Reto Extra 4: una propiedad "constrained" que VETA el cambio si supera un límite.
     */
    public static boolean vetarCambio(int viejo, int nuevo, int limite) {
        // GUÍA: teoría 2 (propiedad CONSTRAINED con VetoableChangeSupport). Un oyente puede lanzar
        //   PropertyVetoException para RECHAZAR un cambio antes de que se aplique.
        // 1. crea un VetoableChangeSupport y registra un VetoableChangeListener que lance
        //    PropertyVetoException si (Integer)evt.getNewValue() > limite.
        // 2. intenta fireVetoableChange("p", viejo, nuevo) dentro de try/catch.
        // 3. si salta PropertyVetoException -> devuelve true (vetado); si no -> false (permitido).
        // PISTA: try { vcs.fireVetoableChange("p", viejo, nuevo); return false; }
        //        catch (PropertyVetoException e) { return true; }
        // OJO: el test usa (10,200,100) -> true (vetado) y (10,50,100) -> false (permitido).
        // CULTURA: "vetar" es validación previa al cambio; en una API REST es el 400 Bad Request
        //   que rechaza el body ANTES de tocar la BD (b08 validación).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para vetarCambio");
    }

    /**
     * Reto Extra 5: registra un oyente para UNA propiedad concreta y cuenta solo sus eventos.
     */
    public static int escucharSoloPropiedad(String objetivo) {
        // GUÍA: teoría 2 (addPropertyChangeListener(String nombre, listener) filtra por propiedad).
        // 1. crea el PCS y un contador.
        // 2. registra el oyente SOLO para la propiedad 'objetivo'.
        // 3. dispara un cambio en "otra" y otro en 'objetivo'.
        // 4. devuelve el contador: debe valer 1 (solo el de 'objetivo' llega).
        // PISTA: pcs.addPropertyChangeListener(objetivo, e -> cont.incrementAndGet());
        // OJO: el test pasa objetivo="x", dispara "y" y "x" -> espera 1. El de "y" NO debe contar.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para escucharSoloPropiedad");
    }

    /**
     * Reto Extra 6: registra, dispara, quita el oyente y dispara de nuevo; cuenta lo recibido.
     */
    public static int quitarListenerYContar() {
        // GUÍA: teoría 2 (removePropertyChangeListener: dejar de escuchar evita fugas).
        // 1. crea el PCS, un contador y guarda la referencia al oyente en una variable.
        // 2. regístralo, dispara UN cambio (cuenta 1).
        // 3. quítalo con removePropertyChangeListener y dispara OTRO cambio (ya no cuenta).
        // 4. devuelve el contador (1).
        // PISTA: PropertyChangeListener l = e -> cont.incrementAndGet(); ... pcs.removePropertyChangeListener(l);
        // OJO: el test espera 1. Para poder quitarlo, NECESITAS guardar la MISMA referencia que
        //   registraste (una lambda nueva no se puede des-registrar).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para quitarListenerYContar");
    }

    /**
     * Reto Extra 7: dispara un evento INDEXADO y devuelve el índice recibido.
     */
    public static int notificacionIndexada(int indice) {
        // GUÍA: teoría 2 (propiedad indexada: cambia el elemento i de una colección observable).
        // 1. crea el PCS y un int[1] para guardar el índice recibido.
        // 2. registra un oyente que, si el evento es IndexedPropertyChangeEvent, guarde su getIndex().
        // 3. dispara con pcs.fireIndexedPropertyChange("items", indice, "a", "b").
        // 4. devuelve el índice guardado.
        // PISTA: if (e instanceof java.beans.IndexedPropertyChangeEvent ip) capturado[0] = ip.getIndex();
        // OJO: el test pasa indice=2 -> espera 2. fireIndexedPropertyChange exige viejo!=nuevo
        //   ("a" vs "b") para disparar de verdad.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para notificacionIndexada");
    }

    /**
     * Reto Extra 8: simula un "debounce": cuenta solo los cambios REALES de una secuencia.
     */
    public static int debounce(List<Integer> valores) {
        // GUÍA: teoría 2 (PCS ya hace un mini-debounce: no dispara si viejo.equals(nuevo)).
        // 1. crea el PCS y un contador; arranca con un valor "anterior" = null.
        // 2. registra un oyente que incremente el contador.
        // 3. recorre la lista disparando firePropertyChange("v", anterior, actual) y actualiza anterior.
        // 4. devuelve el contador: los duplicados consecutivos no cuentan.
        // PISTA: pcs.firePropertyChange("v", anterior, actual); anterior = actual;
        // OJO: el test pasa [1,1,2,2,3] -> espera 3 (1, 2, 3; los repetidos seguidos se ignoran).
        // CULTURA: el debounce de verdad (UI/teclado) agrupa por TIEMPO; aquí agrupamos por
        //   IGUALDAD, que es lo que PCS te da gratis.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para debounce");
    }

    /**
     * Reto Extra 9: dispara la notificación DESDE OTRO HILO y captura el valor en el principal.
     */
    public static int notificarEntreHilos(int valor) throws InterruptedException {
        // GUÍA: teoría 2 (los eventos de un componente pueden cruzar hilos; hay que sincronizar
        //   la entrega). Enlaza con b27 (concurrencia): usa estructuras thread-safe + un latch.
        // 1. crea el PCS, un AtomicReference<Integer> y un CountDownLatch(1).
        // 2. registra un oyente que guarde (Integer)e.getNewValue() en el AtomicReference y haga latch.countDown().
        // 3. lanza un new Thread(() -> pcs.firePropertyChange("v", 0, valor)).start().
        // 4. espera con latch.await(2, SECONDS) y devuelve el valor capturado.
        // PISTA: latch.await(2, TimeUnit.SECONDS); return ref.get();
        // OJO: el test pasa valor=42 -> espera 42 (con @Timeout para no colgarse). NUNCA uses
        //   Thread.sleep "a ojo": el latch te dice EXACTAMENTE cuándo llegó el evento.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para notificarEntreHilos");
    }

    /**
     * Reto Extra 10: tras una secuencia de cambios, devuelve el ÚLTIMO valor nuevo notificado.
     */
    public static int valorFinalTrasCambios(int... valores) {
        // GUÍA: teoría 2 (un oyente que recuerda el último estado es la semilla de un "binding").
        // 1. crea el PCS y un int[1] que guarde el último newValue recibido.
        // 2. registra un oyente que actualice ese hueco en cada evento.
        // 3. dispara la secuencia (de valores[i-1] a valores[i]) y devuelve el último guardado.
        // PISTA: por cada par consecutivo: pcs.firePropertyChange("v", valores[i-1], valores[i]);
        // OJO: el test pasa (1,2,3) -> espera 3. Asegúrate de disparar cambios REALES (consecutivos
        //   distintos) o PCS no notificará y te quedarás con el valor inicial.
        // CULTURA: "recordar el último valor y reaccionar" es la idea que en JavaFX (b33) se
        //   formaliza como Property + binding: aquel bean BOUND evolucionó hasta esto.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para valorFinalTrasCambios");
    }
}
