package com.masterclass.api.b37_fxcustom;

import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio 293 · Componente compuesto: un "campo etiquetado" que COMPONE otros controles.
 *
 * <p>Teoría: {@code teoria/37_JavaFX_Componentes_Canvas.md} (sección 1).
 *
 * <p>La forma más sencilla de crear un control propio es <b>componer</b>: extiendes un layout
 * (p.ej. {@code VBox}) y metes dentro controles que ya existen (un {@code Label} encima de un
 * {@code TextField}, más un {@code Label} de error debajo). El resultado es un "campo etiquetado"
 * reutilizable con su <b>API pública</b> propia: {@code getTexto()}, {@code isValido()},
 * {@code setObligatorio(...)}. Aquí practicamos la mitad testeable y headless: la <b>lógica de la
 * API</b> (qué etiqueta mostrar, si el valor es válido, qué mensaje de error toca) sin montar la
 * ventana. El Playground visual sí ensambla los nodos de verdad.
 */
public final class Ej293CustomControlCompose {

    private Ej293CustomControlCompose() {
    }

    /**
     * Construye el texto de la etiqueta que se ve encima del campo. Si el campo es obligatorio, se
     * le añade un asterisco {@code " *"} (la convención universal de "campo requerido").
     *
     * @param base        texto base de la etiqueta (p.ej. "Email")
     * @param obligatorio si el campo es obligatorio
     * @return la etiqueta a mostrar; {@code ""} sin implementar
     */
    public static String etiquetaMostrada(String base, boolean obligatorio) {
        // TODO 1: si 'base' es null, trátalo como cadena vacía "" (no revientes con NullPointerException).
        // TODO 2: si NO es obligatorio, devuelve la base tal cual.
        // TODO 3: si es obligatorio, devuelve base + " *" (espacio y asterisco).
        // TODO 4: ojo al caso límite del test: base="" y obligatorio=true -> " *".
        // TODO 5: devuelve la cadena resultante.
        return "";
    }

    /**
     * Calcula el estado de validación del campo a partir de su valor y una longitud mínima. Es el
     * "modelo" que el Skin/los estilos usarían para pintar el campo en rojo o verde.
     *
     * @param valor  texto actual del campo (puede ser null)
     * @param minimo longitud mínima exigida (0 = sin mínimo)
     * @return {@code "vacio"}, {@code "corto"} o {@code "valido"}; {@code ""} sin implementar
     */
    public static String estadoCampo(String valor, int minimo) {
        // TODO 6: normaliza el valor: si es null, trátalo como "" (longitud 0).
        // TODO 7: si la longitud es 0, devuelve "vacio".
        // TODO 8: si la longitud es mayor que 0 pero menor que 'minimo', devuelve "corto".
        // TODO 9: en cualquier otro caso (longitud >= minimo), devuelve "valido".
        // TODO 10: cuida el orden de los if (primero vacío, luego corto, luego válido); el test
        //          comprueba valor="ab", minimo=3 -> "corto" y valor="abc", minimo=3 -> "valido".
        return "";
    }

    public static void main(String[] args) {
        System.out.println("Etiqueta obligatoria -> " + etiquetaMostrada("Email", true));
        System.out.println("Etiqueta opcional    -> " + etiquetaMostrada("Apodo", false));
        System.out.println("Estado 'ab' (min 3)  -> " + estadoCampo("ab", 3));
        System.out.println("Estado 'abc' (min 3) -> " + estadoCampo("abc", 3));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Etiqueta con dos puntos.
     * Devuelve la etiqueta base seguida de ":" (estilo formulario clásico).
     */
    public static String etiquetaConDosPuntos(String base) {
        // GUÍA: teoría 1.1 (componer = dar formato uniforme a las etiquetas del control).
        // 1. return base + ":";
        // OJO: el test pasa "Nombre" y espera "Nombre:" exacto (sin espacio antes de los dos puntos).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para etiquetaConDosPuntos");
    }

    /**
     * Reto Extra 2: ¿Se ve el placeholder?
     * El placeholder (texto de ayuda gris) solo se ve cuando el campo está vacío.
     */
    public static boolean placeholderVisible(String valor) {
        // GUÍA: teoría 1.2 (un campo compuesto muestra el promptText solo si no hay valor escrito).
        // 1. return valor == null || valor.isEmpty();
        // OJO: el test prueba "" -> true y "hola" -> false. Trata null como vacío.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para placeholderVisible");
    }

    /**
     * Reto Extra 3: Contador de caracteres.
     * Devuelve el texto "usado/maximo" que se pinta bajo un campo con límite (p.ej. "3/10").
     */
    public static String textoContador(String valor, int maximo) {
        // GUÍA: teoría 1.2 (los componentes compuestos exponen "sub-estados" derivados, como el contador).
        // 1. Calcula la longitud (null -> 0). 2. return longitud + "/" + maximo;
        // OJO: el test prueba valor="abc", maximo=10 -> "3/10"; valor=null -> "0/10".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para textoContador");
    }

    /**
     * Reto Extra 4: Recortar al límite.
     * Si el valor supera el máximo de caracteres, lo corta; si no, lo deja igual.
     */
    public static String recortarAlLimite(String valor, int maximo) {
        // GUÍA: teoría 1.2 (un campo con maxLength recorta lo que se pega de más).
        // 1. Si valor es null, devuelve "". 2. Si su longitud <= maximo, devuélvelo igual.
        // 3. Si no, return valor.substring(0, maximo).
        // PISTA: substring(0, maximo) coge los 'maximo' primeros caracteres.
        // OJO: el caso límite del test es longitud == maximo -> NO se recorta (devuelve igual).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para recortarAlLimite");
    }

    /**
     * Reto Extra 5: Clase CSS según el estado.
     * Traduce el estado del campo (de {@code estadoCampo}) a la clase de estilo que lo pintará.
     */
    public static String claseSegunEstado(String estado) {
        // GUÍA: teoría 1.3 (estado del modelo -> clase CSS, igual que en b36).
        // 1. switch sobre 'estado': "valido"->"campo-ok", "corto"->"campo-aviso", "vacio"->"campo-vacio".
        // 2. cualquier otro -> "campo".
        // PISTA: un switch con flecha (case "valido" -> "campo-ok") es lo más limpio.
        // OJO: el test prueba "valido" y "corto"; no olvides el default.
        // CULTURA: esto enlaza con b36 (selectores y estado->clase): el control compuesto se viste con CSS.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para claseSegunEstado");
    }

    /**
     * Reto Extra 6: Valor normalizado.
     * Limpia el valor de un campo de email: sin espacios al borde y en minúsculas.
     */
    public static String valorNormalizado(String valor) {
        // GUÍA: teoría 1.3 (un control puede exponer el valor "crudo" y el "normalizado").
        // 1. Si valor es null, devuelve "". 2. return valor.trim().toLowerCase();
        // PISTA: trim() quita espacios SOLO en los extremos, no los de en medio.
        // OJO: el test prueba "  Ana@MAIL.com " -> "ana@mail.com".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para valorNormalizado");
    }

    /**
     * Reto Extra 7: Mensaje de error para el usuario.
     * Convierte el estado del campo en una frase clara que se muestra bajo el campo.
     */
    public static String mensajeError(String estado, int minimo) {
        // GUÍA: teoría 1.4 (el control compuesto traduce su estado a un mensaje humano).
        // 1. si estado="vacio" -> "Este campo es obligatorio.".
        // 2. si estado="corto" -> "Mínimo " + minimo + " caracteres.".
        // 3. si estado="valido" -> "" (sin error, no se muestra nada).
        // 4. cualquier otro -> "".
        // OJO: el test prueba "corto" con minimo=3 -> "Mínimo 3 caracteres." y "valido" -> "".
        // CULTURA: mensajes accionables, como el feedback de usabilidad de b36 y el RFC 7807 de b09.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mensajeError");
    }

    /**
     * Reto Extra 8: Id accesible a partir de la etiqueta.
     * Genera un id estable ("slug") para el control: minúsculas, espacios -> guiones, prefijo "campo-".
     */
    public static String idAccesible(String etiqueta) {
        // GUÍA: teoría 1.4 (un id estable conecta el Label con su campo para lectores de pantalla).
        // 1. Pasa a minúsculas y trim. 2. Sustituye espacios por "-" (replace(" ", "-")).
        // 3. Antepón "campo-". Ejemplo: "Correo Electrónico" -> "campo-correo-electrónico".
        // PISTA: usa replace(' ', '-') sobre el texto ya en minúsculas.
        // OJO: el test prueba "Correo Electronico" -> "campo-correo-electronico".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para idAccesible");
    }

    /**
     * Reto Extra 9: Progreso de relleno de un formulario.
     * Dado el estado de varios campos, devuelve el porcentaje (0..100) de campos válidos.
     */
    public static int progresoRelleno(List<String> estados) {
        // GUÍA: teoría 1.5 (componer también es agregar el estado de VARIOS controles en un "formulario").
        // 1. Si la lista está vacía, devuelve 0 (evita dividir por cero).
        // 2. Cuenta cuántos estados son "valido".
        // 3. return validos * 100 / estados.size();  (división entera).
        // OJO: el test prueba ["valido","corto","valido","vacio"] -> 50; lista vacía -> 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para progresoRelleno");
    }

    /**
     * Reto Extra 10: Resumen de errores del formulario.
     * Devuelve la lista de etiquetas de los campos que NO están válidos (para un panel "corrige esto").
     */
    public static List<String> camposConError(List<String> etiquetas, List<String> estados) {
        // GUÍA: teoría 1.5 (la API del control compuesto se compone a su vez en un formulario mayor).
        // 1. Recorre por índice (i de 0 a etiquetas.size()-1).
        // 2. Si estados.get(i) NO es "valido", añade etiquetas.get(i) a la lista resultado.
        // 3. Devuelve la lista (mantén el orden de aparición).
        // PISTA: usa un for clásico con índice para emparejar etiqueta i con estado i.
        // OJO: el caso límite del test es todos "valido" -> List.of() (lista vacía).
        // CULTURA: es el patrón "resumen de validación" de un formulario web; alimenta b38 (informe de errores).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para camposConError");
    }

    /** Helper de demostración: arma una lista mutable de estados. */
    static List<String> estados(String... e) {
        return new ArrayList<>(List.of(e));
    }
}
