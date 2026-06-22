package com.masterclass.api.b34_fxfxml;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Ejercicio 275 · Modelo–Vista–Controlador: dónde vive cada cosa.
 *
 * <p>Teoría: {@code teoria/34_JavaFX_FXML_MVC.md} (sección 3.1).
 *
 * <p>MVC separa tres responsabilidades: la <b>Vista</b> (qué se ve: el {@code .fxml}, los controles)
 * solo muestra y captura; el <b>Modelo</b> (los datos y las reglas de negocio: aquí, el alta de un
 * cliente) decide qué es válido; el <b>Controlador</b> orquesta (lee la vista, pide al modelo,
 * devuelve un mensaje para la vista) pero no contiene reglas de negocio ni pinta. Caso guía: un
 * formulario de alta de cliente que reutiliza la idea del DTO de b07.
 *
 * <p>Clase {@code final} con métodos {@code static}: todo es lógica pura, sin un solo nodo JavaFX.
 */
public final class Ej275MvcSeparation {

    /**
     * El MODELO: un cliente inmutable (como un DTO de b07). Inmutable = sus campos no cambian; para
     * "modificar" se crea uno nuevo (patrón <i>wither</i>).
     */
    public record Cliente(int id, String nombre, String email) {
    }

    private Ej275MvcSeparation() {
    }

    /**
     * (MODELO) Crea un cliente saneando los datos: recorta espacios y rechaza nombre vacío.
     *
     * @return el cliente creado, o {@code null} si el nombre es null/blanco / sin implementar
     */
    public static Cliente crearCliente(int id, String nombre, String email) {
        // TODO 1: si nombre es null o queda en blanco (isBlank), devuelve null (regla del modelo).
        // TODO 2: recorta el nombre con trim() en una variable.
        // TODO 3: calcula un email seguro: si es null, usa ""; si no, recórtalo con trim().
        // TODO 4: construye un new Cliente(id, nombreRecortado, emailSeguro).
        // TODO 5: devuélvelo.
        return null;
    }

    /**
     * (CONTROLADOR) Procesa el alta: valida la entrada (delegando en el modelo) y devuelve el mensaje
     * que la vista mostrará.
     *
     * @return "Cliente creado: <nombre>" si va bien; "Error: nombre requerido" si no; {@code ""} sin implementar
     */
    public static String procesarAlta(String nombre, String email) {
        // TODO 6: pide al modelo crear el cliente: Cliente c = crearCliente(1, nombre, email).
        // TODO 7: si c es null (el modelo lo rechazó), devuelve "Error: nombre requerido".
        // TODO 8: si c no es null, prepara el texto de éxito.
        // TODO 9: el formato es "Cliente creado: " + c.nombre().
        // TODO 10: devuelve ese mensaje (esto es lo que el controlador entrega a la vista; no pinta él).
        return "";
    }

    public static void main(String[] args) {
        System.out.println(procesarAlta("  Ana  ", "ana@correo.com"));
        System.out.println(procesarAlta("", "x"));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: ¿A qué capa pertenece este elemento?
     * Clasifica un concepto en "Vista", "Modelo" o "Controlador".
     */
    public static String capaDe(String elemento) {
        // GUÍA: teoría 3.1 (saber dónde va cada cosa es el 80% de aplicar bien MVC).
        // 1. Pasa 'elemento' a minúsculas. 2. Decide:
        //    - "button","label","textfield","fxml" -> "Vista".
        //    - "cliente","reglanegocio","validaremail","repositorio" -> "Modelo".
        //    - "initialize","onaction","manejaralta" -> "Controlador".
        //    - cualquier otra cosa -> "".
        // OJO: el test prueba "Button"->"Vista", "reglaNegocio"->"Modelo", "initialize"->"Controlador".
        //   Normaliza a minúsculas para que "Button" y "button" caigan igual.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para capaDe");
    }

    /**
     * Reto Extra 2: ¿Es un elemento de la Vista?
     */
    public static boolean esVista(String elemento) {
        // GUÍA: teoría 3.1 (reutiliza la clasificación del reto 1).
        // 1. return "Vista".equals(capaDe(elemento));
        // OJO: el test prueba "Label" (true) y "reglaNegocio" (false).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esVista");
    }

    /**
     * Reto Extra 3: (MODELO) Regla de nombre válido.
     * Indica si un nombre no es null y tiene contenido visible.
     */
    public static boolean nombreValido(String nombre) {
        // GUÍA: teoría 3.2 (las reglas de negocio viven en el MODELO, nunca en la vista).
        // 1. return nombre != null && !nombre.isBlank();
        // OJO: el test prueba "Ana" (true), "" (false) y "   " (false, solo espacios).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para nombreValido");
    }

    /**
     * Reto Extra 4: (MODELO) Regla de email válido.
     * Indica si el email tiene forma algo@algo.dominio.
     */
    public static boolean emailValido(String email) {
        // GUÍA: teoría 3.2 (otra regla de negocio del modelo; la misma de b33/b08 Bean Validation).
        // 1. return email != null && email.matches("[^@\\s]+@[^@\\s]+\\.[^@\\s]+");
        // OJO: el test prueba "a@b.com" (true) y "a@b" (false, sin punto+dominio).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para emailValido");
    }

    /**
     * Reto Extra 5: (CONTROLADOR) Primer mensaje de error de la validación.
     * Devuelve el primer error encontrado, o "" si todo es válido.
     */
    public static String primerError(String nombre, String email) {
        // GUÍA: teoría 3.3 (el controlador consulta al modelo y traduce el resultado a un mensaje).
        // 1. if (!nombreValido(nombre)) return "Error: nombre requerido".
        // 2. if (!emailValido(email)) return "Error: email no válido".
        // 3. return "".
        // OJO: el orden importa (primero nombre); el test prueba nombre vacío, email malo y caso OK.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para primerError");
    }

    /**
     * Reto Extra 6: (VISTA/formato) Resumen mostrable de un cliente.
     * Devuelve "nombre <email>" para pintarlo en una etiqueta.
     */
    public static String resumen(Cliente c) {
        // GUÍA: teoría 3.4 (dar formato para mostrar es responsabilidad de la capa de presentación).
        // 1. return c.nombre() + " <" + c.email() + ">";
        // OJO: el test usa new Cliente(1,"Ana","ana@correo.com") y espera "Ana <ana@correo.com>".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para resumen");
    }

    /**
     * Reto Extra 7: (MODELO) Nombres de todos los clientes.
     * Devuelve la lista de nombres en el mismo orden.
     */
    public static List<String> nombresDe(List<Cliente> clientes) {
        // GUÍA: teoría 3.2 (consultas sobre la colección del modelo; Streams de b01).
        // 1. return clientes.stream().map(Cliente::nombre).toList();
        // OJO: lista vacía -> lista vacía (no null). El test pasa dos clientes y comprueba sus nombres.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para nombresDe");
    }

    /**
     * Reto Extra 8: (MODELO) Buscar cliente por id.
     * Devuelve el cliente con ese id, envuelto en Optional.
     */
    public static Optional<Cliente> buscarPorId(List<Cliente> clientes, int id) {
        // GUÍA: teoría 3.2 (Optional evita el null y obliga a contemplar el "no encontrado", como en b01).
        // 1. return clientes.stream().filter(c -> c.id() == id).findFirst();
        // OJO: el test busca un id existente (Optional con cliente) y uno inexistente (Optional.empty()).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para buscarPorId");
    }

    /**
     * Reto Extra 9: (MODELO) Cambiar el nombre de un cliente inmutable.
     * Devuelve un cliente NUEVO con el nombre cambiado (patrón wither), conservando id y email.
     */
    public static Cliente conNombre(Cliente c, String nuevoNombre) {
        // GUÍA: teoría 3.2 (un record es inmutable: "modificar" = construir otro con el cambio).
        // 1. return new Cliente(c.id(), nuevoNombre, c.email());
        // OJO: el original NO debe cambiar; el test comprueba que el nuevo tiene el nombre cambiado y
        //   el mismo id/email. Esto es exactamente el wither de los DTO de b07.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para conNombre");
    }

    /**
     * Reto Extra 10: (CONTROLADOR) Flujo de alta completo.
     * Si los datos son válidos, crea el cliente y lo añade al repositorio; devuelve el repositorio resultante.
     */
    public static List<Cliente> flujoAlta(List<Cliente> repositorio, String nombre, String email) {
        // GUÍA: teoría 3.3 (el controlador ORQUESTA: valida con el modelo, crea, persiste, responde).
        // 1. Copia defensiva: List<Cliente> nuevo = new ArrayList<>(repositorio).
        // 2. Si !nombreValido(nombre) || !emailValido(email) -> devuelve 'nuevo' SIN tocar (alta rechazada).
        // 3. Calcula el id como nuevo.size() + 1 (id incremental sencillo).
        // 4. Añade crearCliente(id, nombre, email) y devuelve 'nuevo'.
        // OJO: el test parte de una lista vacía: un alta válida -> tamaño 1; un alta inválida -> sigue 0.
        //   No modifiques la lista de entrada (copia defensiva).
        // CULTURA: este reparto (vista pasiva, modelo con reglas, controlador que orquesta) es la misma
        //   arquitectura en capas de b10 (controller -> service -> repository); en b35 el "repositorio"
        //   será una API REST y el modelo, una ObservableList enlazada a una TableView.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para flujoAlta");
    }
}
