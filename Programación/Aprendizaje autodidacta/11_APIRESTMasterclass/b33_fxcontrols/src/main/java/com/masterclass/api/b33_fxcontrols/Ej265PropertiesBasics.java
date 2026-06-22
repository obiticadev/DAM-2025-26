package com.masterclass.api.b33_fxcontrols;

import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Ejercicio 265 · Properties observables: StringProperty/IntegerProperty, get/set y listeners.
 *
 * <p>Teoría: {@code teoria/33_JavaFX_Controles_Binding.md} (sección 2.1).
 *
 * <p>Una {@code Property} es un "valor que avisa cuando cambia". Es el ladrillo del modelo reactivo
 * de JavaFX: sobre él se construyen los bindings (Ej266+) y el enlace con los controles. Aquí todo
 * es lógica pura (no toca pantalla), así que vuelve el molde estándar: clase {@code final},
 * constructor privado y métodos {@code static}.
 */
public final class Ej265PropertiesBasics {

    private Ej265PropertiesBasics() {
    }

    /**
     * Registra, en orden, cada nuevo valor que toma una {@link StringProperty} al asignarle la
     * secuencia dada. Demuestra que un {@code ChangeListener} recibe el valor nuevo en cada cambio.
     *
     * @param valores secuencia de valores que se irán asignando a la property
     * @return la lista de valores nuevos notificados; {@code List.of()} sin implementar
     */
    public static List<String> cambiosRegistrados(List<String> valores) {
        // TODO 1: crea una StringProperty con valor inicial "" (new SimpleStringProperty("")).
        // TODO 2: crea una List<String> 'registro' donde apuntar las notificaciones.
        // TODO 3: añade un ChangeListener: (obs, viejo, nuevo) -> registro.add(nuevo).
        // TODO 4: recorre 'valores' y haz property.set(v) con cada uno.
        // TODO 5: devuelve 'registro'.
        return List.of();
    }

    /**
     * Cuenta cuántas notificaciones de cambio emite una {@link IntegerProperty} al asignarle la
     * secuencia dada. Sirve para ver que asignar el MISMO valor NO dispara el listener.
     *
     * @param valores secuencia de enteros a asignar (la property arranca en 0)
     * @return número de cambios notificados; {@code -1} sin implementar
     */
    public static int contarNotificaciones(List<Integer> valores) {
        // TODO 6: crea una IntegerProperty inicial 0 (new SimpleIntegerProperty(0)).
        // TODO 7: usa un int[] contador = {0} (truco para mutar desde la lambda).
        // TODO 8: añade un ChangeListener que haga contador[0]++ en cada cambio.
        // TODO 9: recorre 'valores' y haz property.set(v).
        // TODO 10: devuelve contador[0] (ojo: asignar el valor que ya tenía NO cuenta).
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("Cambios: " + cambiosRegistrados(List.of("a", "b", "c")));
        System.out.println("Notificaciones: " + contarNotificaciones(List.of(1, 1, 2)));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Leer el valor de una property.
     * Devuelve el valor actual de la {@link StringProperty}.
     */
    public static String leerValor(StringProperty property) {
        // GUÍA: teoría 2.1 (getValue()/get() leen el valor envuelto).
        // 1. return property.getValue();
        // OJO: getValue() (de ObservableValue) y get() (específico) devuelven lo mismo aquí.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para leerValor");
    }

    /**
     * Reto Extra 2: Escribir y releer.
     * Asigna el valor a la property y devuelve lo que queda guardado.
     */
    public static String escribirValor(StringProperty property, String valor) {
        // GUÍA: teoría 2.1 (set() escribe; get() relee).
        // 1. property.set(valor). 2. return property.get().
        // OJO: el test verifica que set seguido de get devuelve lo escrito.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para escribirValor");
    }

    /**
     * Reto Extra 3: Property con metadatos (bean y nombre).
     * Crea una property asociada a un bean y un nombre, y devuelve su nombre.
     */
    public static String propiedadConNombre(Object bean, String nombre) {
        // GUÍA: teoría 2.1 (una property puede llevar referencia a su 'bean' dueño y su 'name').
        // 1. Crea new SimpleStringProperty(bean, nombre, ""). 2. Devuelve property.getName().
        // PISTA: el constructor de 3 args es (bean, name, valorInicial).
        // OJO: estos metadatos los usa el binding por reflexión y el FXML; getName() devuelve 'nombre'.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para propiedadConNombre");
    }

    /**
     * Reto Extra 4: Último valor tras una secuencia.
     * Asigna en orden todos los enteros y devuelve el valor final de la property.
     */
    public static int ultimoValor(IntegerProperty property, List<Integer> valores) {
        // GUÍA: teoría 2.1 (cada set pisa al anterior; la property guarda solo el último).
        // 1. Recorre 'valores' con set. 2. Devuelve property.get().
        // OJO: lista vacía -> devuelve el valor que ya tuviera la property (no falla).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ultimoValor");
    }

    /**
     * Reto Extra 5: Contar invalidaciones.
     * Cuenta cuántas veces se invalida una {@link IntegerProperty} con un {@code InvalidationListener}.
     */
    public static int invalidacionesContadas(List<Integer> valores) {
        // GUÍA: teoría 2.2 (InvalidationListener: avisa "algo cambió" SIN darte el valor nuevo).
        // 1. IntegerProperty p inicial 0. 2. int[] c={0}. 3. p.addListener(obs -> c[0]++) (firma de UN solo arg).
        // 4. Asigna cada valor. 5. Devuelve c[0].
        // PISTA: el InvalidationListener tiene firma (Observable obs); el ChangeListener tiene (obs, viejo, nuevo).
        // OJO: diferencia clave -> InvalidationListener NO recibe viejo/nuevo (es más barato); úsalo
        //   cuando solo quieras "marcar sucio" sin leer el valor. El test usa valores distintos -> 3.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para invalidacionesContadas");
    }

    /**
     * Reto Extra 6: ¿Asignar este valor dispara un cambio?
     * Indica si, partiendo de 'inicial', asignar 'nuevo' notifica un cambio.
     */
    public static boolean desencadenaCambio(int inicial, int nuevo) {
        // GUÍA: teoría 2.1 (set() solo notifica si el valor REALMENTE cambia).
        // 1. IntegerProperty p con 'inicial'. 2. boolean[] disparo={false}. 3. addListener -> disparo[0]=true.
        // 4. p.set(nuevo). 5. Devuelve disparo[0].
        // OJO: el test prueba inicial==nuevo (false, no notifica) e inicial!=nuevo (true).
        //   Esto es la razón de que los listeners no se disparen "de más".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para desencadenaCambio");
    }

    /**
     * Reto Extra 7: Quitar un listener.
     * Añade un listener, lo quita y comprueba que ya NO se dispara: devuelve el nº de disparos.
     */
    public static int quitarListener(List<Integer> valores) {
        // GUÍA: teoría 2.2 (removeListener libera el listener; evita fugas de memoria).
        // 1. IntegerProperty p inicial 0. 2. int[] c={0}. 3. Crea un ChangeListener en una variable.
        // 4. addListener(l). 5. removeListener(l) INMEDIATAMENTE. 6. Asigna los valores. 7. Devuelve c[0].
        // PISTA: guarda el listener en una variable (ChangeListener<Number> l = ...) para poder quitarlo.
        // OJO: como lo quitas antes de asignar, el resultado debe ser 0 aunque cambies el valor.
        //   Olvidar removeListener en apps reales = fuga de memoria (el listener retiene objetos).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para quitarListener");
    }

    /**
     * Reto Extra 8: Leer una property null-safe.
     * Devuelve el valor de la {@link StringProperty}, o "" si su valor es null.
     */
    public static String valorOTextoVacio(StringProperty property) {
        // GUÍA: teoría 2.1 (una StringProperty PUEDE contener null, a diferencia de getText()).
        // 1. Si property.getValue() es null -> "". Si no -> ese valor.
        // PISTA: java.util.Objects.requireNonNullElse(property.getValue(), "");
        // OJO: el test crea una property con valor null explícito; no debe lanzar NullPointerException.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para valorOTextoVacio");
    }

    /**
     * Reto Extra 9: Conmutar una property booleana.
     * Invierte el valor de una {@link BooleanProperty} y devuelve el nuevo valor.
     */
    public static boolean conmutar(BooleanProperty property) {
        // GUÍA: teoría 2.1 (BooleanProperty es la base de 'visible', 'disable', 'selected'…).
        // 1. property.set(!property.get()). 2. Devuelve property.get().
        // OJO: el test parte de false; tras conmutar debe ser true. Estas properties booleanas
        //   son las que luego 'bindeas' a la UI (Ej270: botón habilitado/deshabilitado).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para conmutar");
    }

    /**
     * Reto Extra 10: Property dentro de un POJO (patrón JavaFX bean).
     * Cambia el nombre de la {@link Persona} a través de su property y devuelve lo que lee el getter.
     */
    public static String renombrarPersona(Persona persona, String nuevoNombre) {
        // GUÍA: teoría 2.3 (el idioma "JavaFX bean": campo Property privado + getX()/setX()/xProperty()).
        // 1. Cambia el valor vía la PROPERTY: persona.nombreProperty().set(nuevoNombre).
        // 2. Devuelve persona.getNombre() (el getter lee la MISMA property).
        // OJO: getter/setter y la property comparten estado; tocar uno se ve en el otro.
        // CULTURA: este patrón nombreProperty() es justo lo que necesita TableView en b35 para
        //   refrescar una celda sola cuando cambia ese campo (cellValueFactory lee la property).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para renombrarPersona");
    }

    /**
     * POJO de ejemplo con el patrón "JavaFX bean": el estado vive en una {@link StringProperty}
     * y se expone con getter, setter y el accesor {@code xProperty()}.
     */
    public static final class Persona {
        private final StringProperty nombre = new SimpleStringProperty(this, "nombre", "");

        public Persona(String nombre) {
            this.nombre.set(nombre);
        }

        public String getNombre() {
            return nombre.get();
        }

        public void setNombre(String valor) {
            nombre.set(valor);
        }

        public StringProperty nombreProperty() {
            return nombre;
        }
    }
}
