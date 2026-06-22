package com.masterclass.api.b33_fxcontrols;

import java.util.List;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Ejercicio 270 · Validación reactiva en vivo: habilitar el botón según el estado del formulario.
 *
 * <p>Teoría: {@code teoria/33_JavaFX_Controles_Binding.md} (sección 4.1).
 *
 * <p>La culminación del bloque: un ViewModel de login cuyo "¿puedo enviar?" es un
 * {@code BooleanBinding} calculado a partir de las properties del formulario. La vista solo
 * <i>bindea</i> el {@code disableProperty} del botón a ese binding; nunca habilita/deshabilita a
 * mano. <b>Es 100% testeable sin abrir ventana</b> (regla de oro del addendum §1.6).
 *
 * <p>Extiende {@code Application} para el Playground (por eso no es {@code final} ni tiene ctor
 * privado), pero toda la lógica vive en métodos {@code static} puros.
 */
public class Ej270FormValidationLive extends Application {

    /**
     * ¿Puede enviarse el login? Regla: usuario NO vacío Y clave de más de 7 caracteres,
     * expresado como un {@link BooleanBinding} sobre las properties.
     *
     * @param usuario texto del campo usuario
     * @param clave   texto del campo clave
     * @return true si cumple ambas reglas; {@code false} sin implementar
     */
    public static boolean puedeEnviar(String usuario, String clave) {
        // TODO 1: crea StringProperty u = new SimpleStringProperty(usuario).
        // TODO 2: crea StringProperty c = new SimpleStringProperty(clave).
        // TODO 3: construye el binding: u.isNotEmpty().and(c.length().greaterThan(7)).
        // TODO 4: fíjate: NO usas if; describes la regla como expresión observable.
        // TODO 5: devuelve ese binding con .get().
        return false;
    }

    /**
     * Mensaje de validación del formulario (el primer error que encuentra), en orden de prioridad.
     *
     * @param usuario texto del campo usuario
     * @param clave   texto del campo clave
     * @return "Usuario requerido" / "Clave demasiado corta" / "" si todo OK; {@code ""} sin implementar
     */
    public static String mensajeValidacion(String usuario, String clave) {
        // TODO 6: si usuario es null o está en blanco (isBlank), devuelve "Usuario requerido".
        // TODO 7: si la clave es null o tiene menos de 8 caracteres, devuelve "Clave demasiado corta".
        // TODO 8: el orden importa: primero usuario, luego clave (un error a la vez).
        // TODO 9: si todo está bien, devuelve "".
        // TODO 10: cuidado con el null antes de llamar a isBlank()/length() (evita NullPointerException).
        return "";
    }

    /** Playground visual: el botón "Entrar" se habilita SOLO cuando el formulario es válido. */
    @Override
    public void start(Stage stage) {
        TextField usuario = new TextField();
        usuario.setPromptText("usuario");
        PasswordField clave = new PasswordField();
        clave.setPromptText("clave (más de 7 caracteres)");
        Button entrar = new Button("Entrar");

        // El binding reactivo: la vista NO habilita a mano, solo describe la regla.
        BooleanBinding formularioValido = usuario.textProperty().isNotEmpty()
                .and(clave.textProperty().length().greaterThan(7));
        entrar.disableProperty().bind(formularioValido.not());

        Label estado = new Label();
        estado.textProperty().bind(Bindings.when(formularioValido).then("Listo para enviar").otherwise("Completa el formulario"));

        stage.setTitle("Ej270 · Validación en vivo");
        stage.setScene(new Scene(new VBox(10, usuario, clave, entrar, estado), 320, 220));
        stage.show();
    }

    public static void main(String[] args) {
        System.out.println("puedeEnviar(ana, 12345678): " + puedeEnviar("ana", "12345678"));
        launch(args);
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Campo no vacío (observable).
     * Indica si la property tiene texto, usando isNotEmpty().
     */
    public static boolean noVacio(StringProperty campo) {
        // GUÍA: teoría 4.1 (isNotEmpty es la regla de validación más común).
        // 1. return campo.isNotEmpty().get();
        // OJO: isNotEmpty mira longitud > 0; "   " (espacios) cuenta como NO vacío (ojo, distinto de isBlank).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para noVacio");
    }

    /**
     * Reto Extra 2: Longitud mínima (observable).
     * Indica si el texto de la property tiene al menos 'minimo' caracteres.
     */
    public static boolean longitudMinima(StringProperty campo, int minimo) {
        // GUÍA: teoría 4.1 (length() da un IntegerBinding sobre el que comparas).
        // 1. return campo.length().greaterThanOrEqualTo(minimo).get();
        // OJO: greaterThanOrEqualTo incluye el límite (>=); el test usa minimo=8 con clave de 8 -> true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para longitudMinima");
    }

    /**
     * Reto Extra 3: ¿Coinciden dos claves?
     * Indica si las dos properties tienen el mismo texto, con isEqualTo.
     */
    public static boolean coincidenClaves(StringProperty clave, StringProperty repetir) {
        // GUÍA: teoría 4.1 (isEqualTo compara dos properties de String y da un BooleanBinding).
        // 1. return clave.isEqualTo(repetir).get();
        // OJO: es la validación clásica "repite la contraseña"; compara CONTENIDO, no referencias.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para coincidenClaves");
    }

    /**
     * Reto Extra 4: Email con formato válido.
     * Indica si el texto parece un email (algo@algo.dominio) mediante una expresión regular.
     */
    public static boolean emailValido(String email) {
        // GUÍA: teoría 4.2 (validación por patrón; aquí imperativa, las anteriores eran observables).
        // 1. Si email es null -> false. 2. return email.matches("[^@\\s]+@[^@\\s]+\\.[^@\\s]+").
        // PISTA: el patrón pide: texto, @, texto, ., texto, sin espacios ni @ dentro.
        // OJO: el test prueba "ana@correo.com" (true) y "ana@malo" (false, sin punto+dominio).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para emailValido");
    }

    /**
     * Reto Extra 5: Contador de caracteres (observable).
     * Devuelve la longitud actual del texto, como haría un contador "x/100".
     */
    public static int contadorCaracteres(StringProperty campo) {
        // GUÍA: teoría 4.1 (length() es un binding; aquí solo leemos su valor).
        // 1. return campo.length().get();
        // OJO: en la UI bindearías un Label a campo.length().asString() para verlo en vivo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contadorCaracteres");
    }

    /**
     * Reto Extra 6: Habilitación reactiva (DEVUELVE el binding).
     * Devuelve el {@link BooleanBinding} "usuario no vacío Y clave > 7", para comprobar que es VIVO.
     */
    public static BooleanBinding habilitarReactivo(StringProperty usuario, StringProperty clave) {
        // GUÍA: teoría 4.1 (la gracia del binding es que NO recalculas: cambias la property y listo).
        // 1. return usuario.isNotEmpty().and(clave.length().greaterThan(7));
        // OJO: el test obtiene el binding con properties vacías (false), luego las rellena con
        //   valores válidos y vuelve a leer el binding -> true, SIN volver a llamar al método.
        //   Eso demuestra que la validación es reactiva ("en vivo"), no una foto.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para habilitarReactivo");
    }

    /**
     * Reto Extra 7: Mensaje de error dinámico (DEVUELVE el binding).
     * Devuelve un {@link StringBinding} que vale "Usuario requerido" si está vacío, o "" si no.
     */
    public static StringBinding mensajeDinamico(StringProperty usuario) {
        // GUÍA: teoría 4.1 + 2.6 (createStringBinding para texto de error que se actualiza solo).
        // 1. return Bindings.createStringBinding(
        //        () -> usuario.get() == null || usuario.get().isBlank() ? "Usuario requerido" : "",
        //        usuario);
        // PISTA: no olvides pasar 'usuario' como dependencia (último argumento).
        // OJO: el test lee el mensaje con el campo vacío ("Usuario requerido"), luego escribe un
        //   nombre y vuelve a leer el MISMO binding -> "" (el mensaje desaparece solo).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mensajeDinamico");
    }

    /**
     * Reto Extra 8: Todos los campos completos.
     * Indica si TODAS las properties de la lista tienen texto no vacío.
     */
    public static boolean todosCompletos(List<StringProperty> campos) {
        // GUÍA: teoría 4.1 (combinar varias reglas: aquí "y" lógico de todos los isNotEmpty).
        // 1. Empieza con un BooleanBinding "siempre verdadero" o combina sobre la marcha.
        // 2. Por cada campo, encadénalo con .and(campo.isNotEmpty()).
        // 3. Devuelve el .get() del binding combinado.
        // PISTA: BooleanBinding acc = Bindings.createBooleanBinding(() -> true);
        //   for (campo : campos) acc = acc.and(campo.isNotEmpty());
        // OJO: lista vacía -> true (no hay campos incompletos). El test mezcla campos con y sin texto.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para todosCompletos");
    }

    /**
     * Reto Extra 9: Forzar mayúsculas con un listener (efecto secundario).
     * Pone un listener que pasa a MAYÚSCULAS lo que se escribe; escribe 'entrada' y devuelve el valor final.
     */
    public static String forzarMayusculas(StringProperty campo, String entrada) {
        // GUÍA: teoría 4.3 (a veces no quieres CALCULAR otro valor sino MODIFICAR el mismo: usa un listener).
        // 1. Añade un ChangeListener: (obs, viejo, nuevo) -> if (nuevo != null) campo.set(nuevo.toUpperCase()).
        // 2. Haz campo.set(entrada). 3. Devuelve campo.get().
        // PISTA: al hacer set dentro del listener se vuelve a disparar, pero "ABC".toUpperCase() ya es "ABC"
        //   y como no cambia, NO entra en bucle infinito (set de mismo valor no notifica, Ej265).
        // OJO: esto es un EFECTO SECUNDARIO, no un binding: modificas la property observada. Úsalo con
        //   cuidado. El test escribe "abc" y espera "ABC".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para forzarMayusculas");
    }

    /**
     * Reto Extra 10: ViewModel completo de registro.
     * Indica si un registro es válido: usuario no vacío, email válido, clave>=8 y clave==repetir.
     */
    public static boolean registroValido(String usuario, String email, String clave, String repetir) {
        // GUÍA: teoría 4.4 (el ViewModel reúne TODAS las reglas en un único binding "formularioValido").
        // 1. Mete cada dato en una StringProperty.
        // 2. Combina: usuario.isNotEmpty()
        //        .and(Bindings.createBooleanBinding(() -> emailValido(emailProp.get()), emailProp))
        //        .and(clave.length().greaterThanOrEqualTo(8))
        //        .and(clave.isEqualTo(repetir)).
        // 3. Devuelve el .get() del binding combinado.
        // OJO: el test prueba un registro perfecto (true) y varios con UN fallo cada uno (false):
        //   email sin punto, claves que no coinciden, clave corta. Reutiliza emailValido (reto 4).
        // CULTURA: este "formularioValido = reglaA AND reglaB AND ..." bindeado al botón es el patrón
        //   de los formularios reactivos modernos (Angular Reactive Forms, React Hook Form, Formik):
        //   el estado de validez es DERIVADO del estado de los campos, nunca se calcula a mano.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para registroValido");
    }

    /** Helper de demostración: crea una StringProperty con un valor inicial. */
    static StringProperty campoCon(String valor) {
        return new SimpleStringProperty(valor);
    }
}
