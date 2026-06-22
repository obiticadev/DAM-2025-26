package com.masterclass.api.b34_fxfxml;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Ejercicio 276 · MVVM: un {@code ViewModel} <i>bindeado</i> y una vista pasiva.
 *
 * <p>Teoría: {@code teoria/34_JavaFX_FXML_MVC.md} (sección 3.5).
 *
 * <p><b>Núcleo del bloque.</b> En MVVM la vista no tiene lógica: solo <i>bindea</i> sus controles a
 * las {@code Property} de un {@code ViewModel}, que concentra el estado (campos del formulario) y los
 * comandos (aceptar, resetear). La validez del formulario es un {@code BooleanBinding} calculado; el
 * botón se habilita solo. <b>Todo el ViewModel se prueba sin abrir ventana</b> (regla de oro §1.6).
 *
 * <p>Extiende {@code Application} para el Playground (por eso no es {@code final} ni tiene ctor
 * privado), pero el {@link ModeloVista} y los métodos {@code static} son lógica pura y testeable.
 */
public class Ej276MvvmViewModel extends Application {

    /**
     * El ViewModel: estado observable del formulario de alta. La vista lo bindea; los tests lo
     * manipulan a pelo. No conoce ni un solo nodo JavaFX (eso es lo que lo hace testeable).
     */
    public static class ModeloVista {
        private final StringProperty nombre = new SimpleStringProperty("");
        private final StringProperty email = new SimpleStringProperty("");
        private final StringProperty mensaje = new SimpleStringProperty("");
        private final IntegerProperty altas = new SimpleIntegerProperty(0);
        private final BooleanProperty cargando = new SimpleBooleanProperty(false);

        public StringProperty nombreProperty() {
            return nombre;
        }

        public StringProperty emailProperty() {
            return email;
        }

        public StringProperty mensajeProperty() {
            return mensaje;
        }

        public IntegerProperty altasProperty() {
            return altas;
        }

        public BooleanProperty cargandoProperty() {
            return cargando;
        }
    }

    /**
     * Regla de validez del formulario como {@link BooleanBinding} VIVO: nombre no vacío Y email con
     * forma válida. Al cambiar las properties, el binding se recalcula solo.
     *
     * @param vm el ViewModel
     * @return el binding de validez, o {@code null} sin implementar
     */
    public static BooleanBinding reglaValidez(ModeloVista vm) {
        // TODO 1: si vm es null, devuelve null.
        // TODO 2: usa Bindings.createBooleanBinding con una lambda que lea vm.nombreProperty().get()
        //         y vm.emailProperty().get().
        // TODO 3: dentro de la lambda, nombre es válido si no es null y !isBlank().
        // TODO 4: dentro de la lambda, email es válido si no es null y casa "[^@\\s]+@[^@\\s]+\\.[^@\\s]+".
        //         Devuelve nombreOk && emailOk.
        // TODO 5: pasa como DEPENDENCIAS vm.nombreProperty() y vm.emailProperty() (último(s) argumento(s))
        //         para que el binding sea reactivo, y devuélvelo.
        return null;
    }

    /**
     * Comando "aceptar": si el formulario es válido, registra el alta (incrementa el contador) y pone
     * un mensaje de éxito; si no, pone un mensaje de revisión. Devuelve el mensaje resultante.
     *
     * @param vm el ViewModel
     * @return el mensaje mostrado, o {@code ""} sin implementar
     */
    public static String aceptar(ModeloVista vm) {
        // TODO 6: si vm es null, devuelve "".
        // TODO 7: evalúa la validez con reglaValidez(vm).get().
        // TODO 8: si NO es válido, pon vm.mensajeProperty().set("Revisa el formulario") y devuélvelo.
        // TODO 9: si es válido, incrementa el contador: vm.altasProperty().set(vm.altasProperty().get() + 1).
        // TODO 10: pon vm.mensajeProperty().set("Alta correcta") y devuelve ese mensaje.
        return "";
    }

    /** Playground visual: la vista solo BINDEA; no hay un solo if de validación en ella. */
    @Override
    public void start(Stage stage) {
        ModeloVista vm = new ModeloVista();

        TextField nombre = new TextField();
        nombre.setPromptText("nombre");
        nombre.textProperty().bindBidirectional(vm.nombreProperty());

        TextField email = new TextField();
        email.setPromptText("email");
        email.textProperty().bindBidirectional(vm.emailProperty());

        Button aceptar = new Button("Dar de alta");
        BooleanBinding valido = reglaValidez(vm);
        if (valido != null) {
            aceptar.disableProperty().bind(valido.not());
        }
        aceptar.setOnAction(e -> aceptar(vm));

        Label mensaje = new Label();
        mensaje.textProperty().bind(vm.mensajeProperty());
        Label contador = new Label();
        contador.textProperty().bind(vm.altasProperty().asString("Altas: %d"));

        stage.setTitle("Ej276 · MVVM (vista pasiva)");
        stage.setScene(new Scene(new VBox(10, nombre, email, aceptar, mensaje, contador), 340, 240));
        stage.show();
    }

    public static void main(String[] args) {
        ModeloVista vm = new ModeloVista();
        vm.nombreProperty().set("Ana");
        vm.emailProperty().set("ana@correo.com");
        System.out.println(aceptar(vm) + " / altas=" + vm.altasProperty().get());
        launch(args);
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Saludo derivado del nombre (binding vivo).
     * Devuelve un StringBinding "Hola, <nombre>" que se actualiza al cambiar el nombre.
     */
    public static StringBinding saludo(ModeloVista vm) {
        // GUÍA: teoría 3.5 + b33 4.x (el texto de una etiqueta DERIVA del estado del VM).
        // 1. return Bindings.concat("Hola, ", vm.nombreProperty()) ... pero concat da StringExpression;
        //    mejor: Bindings.createStringBinding(() -> "Hola, " + vm.nombreProperty().get(), vm.nombreProperty()).
        // OJO: el test lee el saludo, cambia el nombre y vuelve a leer el MISMO binding -> se actualizó.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para saludo");
    }

    /**
     * Reto Extra 2: Validez como booleano (foto del momento).
     * Devuelve si el formulario es válido AHORA.
     */
    public static boolean estaValido(ModeloVista vm) {
        // GUÍA: teoría 3.5 (a veces quieres el valor puntual, no el binding).
        // 1. return reglaValidez(vm).get();
        // OJO: el test prueba un VM vacío (false) y uno con nombre+email correctos (true).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para estaValido");
    }

    /**
     * Reto Extra 3: Comando resetear.
     * Vacía nombre, email y mensaje; devuelve true si el nombre quedó vacío.
     */
    public static boolean resetear(ModeloVista vm) {
        // GUÍA: teoría 3.5 (un "comando" del VM es un método que muta su estado observable).
        // 1. vm.nombreProperty().set(""); vm.emailProperty().set(""); vm.mensajeProperty().set("").
        // 2. return vm.nombreProperty().get().isEmpty().
        // OJO: el test rellena el VM, llama a resetear y espera true (y que la vista, bindeada, se
        //   limpiaría sola). Aquí solo tocamos el VM.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para resetear");
    }

    /**
     * Reto Extra 4: Botón habilitado = válido Y no cargando (binding combinado).
     * Devuelve el BooleanBinding que gobierna la habilitación del botón.
     */
    public static BooleanBinding botonHabilitado(ModeloVista vm) {
        // GUÍA: teoría 3.5 (combinar reglas: la de validez Y "no estoy enviando ahora mismo").
        // 1. return reglaValidez(vm).and(vm.cargandoProperty().not());
        // OJO: el test pone datos válidos (true), luego cargando=true y vuelve a leer el binding -> false,
        //   SIN volver a llamar al método (reactivo). Es el "botón gris mientras envío".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para botonHabilitado");
    }

    /**
     * Reto Extra 5: Mensaje de error dinámico.
     * Devuelve un StringBinding que vale "Revisa el formulario" si NO es válido, o "" si lo es.
     */
    public static StringBinding mensajeError(ModeloVista vm) {
        // GUÍA: teoría 3.5 + b33 2.6 (createStringBinding para un texto que cambia solo).
        // 1. return Bindings.createStringBinding(
        //        () -> reglaValidez(vm).get() ? "" : "Revisa el formulario",
        //        vm.nombreProperty(), vm.emailProperty()).
        // OJO: pasa nombre y email como dependencias; el test lee con VM inválido ("Revisa...") y, tras
        //   rellenarlo bien, lee el mismo binding -> "".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mensajeError");
    }

    /**
     * Reto Extra 6: ¿Hay cambios sin guardar? (dirty checking).
     * Indica si el valor actual de una property difiere del valor original.
     */
    public static boolean hayCambios(String original, StringProperty actual) {
        // GUÍA: teoría 3.6 (un editor pregunta "¿guardar cambios?" comparando con el valor cargado).
        // 1. return !java.util.Objects.equals(original, actual.get());
        // OJO: usa Objects.equals para tolerar null en cualquiera de los dos. El test parte de "Ana",
        //   no cambia (false), luego escribe "Berta" (true).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para hayCambios");
    }

    /**
     * Reto Extra 7: Comando deshacer.
     * Restaura el valor anterior en la property y devuelve el valor restaurado.
     */
    public static String deshacer(StringProperty campo, String valorAnterior) {
        // GUÍA: teoría 3.6 (deshacer = volver a poner el valor previo guardado).
        // 1. campo.set(valorAnterior); 2. return campo.get().
        // OJO: el test cambia el campo a "nuevo", llama deshacer con "viejo" y espera "viejo".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para deshacer");
    }

    /**
     * Reto Extra 8: Cuántas altas válidas se registran en una tanda.
     * Aplica cada par {nombre,email}, ejecuta el comando aceptar y devuelve el contador final de altas.
     */
    public static int procesarTanda(ModeloVista vm, String[][] intentos) {
        // GUÍA: teoría 3.5 (el contador 'altas' del VM acumula los comandos exitosos).
        // 1. Por cada par par[]: vm.nombreProperty().set(par[0]); vm.emailProperty().set(par[1]); aceptar(vm).
        // 2. return vm.altasProperty().get().
        // OJO: el test pasa 3 intentos (2 válidos, 1 inválido) -> espera 2. aceptar ya filtra los malos.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para procesarTanda");
    }

    /**
     * Reto Extra 9: Enlace bidireccional vista <-> ViewModel.
     * Enlaza el texto de un TextField con el nombre del VM; escribe en el campo y devuelve el nombre del VM.
     */
    public static String enlazarCampo(TextField campo, ModeloVista vm) {
        // GUÍA: teoría 3.5 + b33 3.x (bindBidirectional sincroniza control y property en ambos sentidos).
        // 1. campo.textProperty().bindBidirectional(vm.nombreProperty()).
        // 2. campo.setText("Carla"). 3. return vm.nombreProperty().get().
        // OJO: el test espera "Carla": al escribir en la VISTA, el VM se entera solo (y viceversa). Por
        //   eso la vista es "pasiva": no copia datos a mano, los bindea.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para enlazarCampo");
    }

    /**
     * Reto Extra 10: Flujo MVVM completo.
     * Rellena el VM, ejecuta el comando aceptar y devuelve el número de altas registradas.
     */
    public static int flujoMvvm(ModeloVista vm, String nombre, String email) {
        // GUÍA: teoría 3.5 (de principio a fin: estado -> comando -> estado actualizado).
        // 1. vm.nombreProperty().set(nombre); vm.emailProperty().set(email). 2. aceptar(vm).
        // 3. return vm.altasProperty().get().
        // OJO: el test prueba datos válidos (1) y datos inválidos (0). Reutiliza el comando aceptar.
        // CULTURA: "vista pasiva bindeada a un ViewModel con estado y comandos" es EXACTAMENTE MVVM de
        //   WPF/Angular/Jetpack Compose. La gran ventaja: el VM se testea sin UI (justo lo que haces aquí).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para flujoMvvm");
    }
}
