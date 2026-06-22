package com.masterclass.api.b36_fxstyle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Ejercicio 290 · Accesibilidad (a11y): orden de foco, mnemónicos, atajos y contraste WCAG.
 *
 * <p>Teoría: {@code teoria/36_JavaFX_Estilo_Accesibilidad.md} (sección 4).
 *
 * <p>Una interfaz <b>accesible</b> se puede usar sin ratón (solo teclado), con poca visión (buen
 * contraste) y con lector de pantalla ({@code accessibleText}). JavaFX da soporte directo:
 * <b>orden de foco</b> (Tab recorre los controles), <b>mnemónicos</b> ({@code "_Guardar"} →
 * {@code Alt+G}, con {@code mnemonicParsing}), <b>atajos</b> ({@code Shortcut+S}) y texto accesible.
 * "a11y" es la abreviatura estándar de "accessibility" (a + 11 letras + y). Aquí trabajamos las
 * reglas comprobables sin pantalla: ordenar el foco, extraer el mnemónico, medir el contraste.
 */
public final class Ej290AccessibilityA11y {

    private Ej290AccessibilityA11y() {
    }

    /**
     * Devuelve los nombres de los campos en el ORDEN DE LECTURA correcto: de arriba abajo por filas
     * y, dentro de cada fila, de izquierda a derecha por columnas. Es el orden en que Tab debería
     * mover el foco para que la navegación por teclado sea natural.
     *
     * @param campos campos del formulario (en cualquier orden)
     * @return nombres ordenados por (fila, columna); {@code List.of()} sin implementar
     */
    public static List<String> ordenFoco(List<Campo> campos) {
        // TODO 1: copia 'campos' a una lista mutable (new ArrayList<>(campos)) para no mutar la entrada.
        // TODO 2: ordénala por FILA ascendente (Comparator.comparingInt(c -> c.fila())).
        // TODO 3: a igualdad de fila, desempata por COLUMNA ascendente (.thenComparingInt(c -> c.columna())).
        // TODO 4: recorre la lista ordenada y extrae el nombre de cada Campo a una List<String>.
        // TODO 5: devuelve esa lista de nombres (caso límite: lista vacía -> List.of()).
        return List.of();
    }

    /**
     * Calcula el mnemónico (atajo {@code Alt+letra}) de un texto con marcador de subrayado. En
     * JavaFX el carácter tras un {@code '_'} es la tecla de acceso: {@code "_Guardar"} → {@code Alt+G}.
     *
     * @param textoConGuion texto con un '_' antes de la letra mnemónica
     * @return "Alt+" y la letra en mayúscula; {@code ""} si no hay mnemónico
     */
    public static String mnemonicDe(String textoConGuion) {
        // TODO 6: busca la posición del primer '_' (textoConGuion.indexOf('_')).
        // TODO 7: si no hay '_' (indexOf devuelve -1) o el '_' es el último carácter, devuelve "".
        // TODO 8: toma el carácter SIGUIENTE al '_' (charAt(pos + 1)).
        // TODO 9: pásalo a mayúscula (Character.toUpperCase) para mostrarlo como atajo.
        // TODO 10: devuelve "Alt+" + esa letra (caso límite: "Guardar" sin '_' -> "").
        return "";
    }

    public static void main(String[] args) {
        List<Campo> campos = List.of(
                new Campo("apellidos", 1, 0),
                new Campo("nombre", 0, 0),
                new Campo("email", 0, 1));
        System.out.println("Orden de foco: " + ordenFoco(campos));
        System.out.println("Mnemónico de '_Guardar': " + mnemonicDe("_Guardar"));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Texto visible sin el marcador.
     * Quita el '_' del marcador mnemónico para mostrar el texto limpio.
     */
    public static String textoVisible(String textoConGuion) {
        // GUÍA: teoría 4.2 (con mnemonicParsing=true, el '_' NO se ve: solo subraya la letra siguiente).
        // 1. return textoConGuion.replaceFirst("_", "");
        // OJO: el test espera "_Guardar" -> "Guardar". Usa replaceFirst (solo el primer '_'),
        //   no replace (que borraría todos).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para textoVisible");
    }

    /**
     * Reto Extra 2: Letra del mnemónico.
     * Devuelve solo la letra (en mayúscula) que actúa de mnemónico.
     */
    public static String letraMnemonico(String textoConGuion) {
        // GUÍA: teoría 4.2 (es la letra tras el '_'; aquí la quieres sola, sin "Alt+").
        // 1. Localiza el '_' y toma el carácter siguiente, en mayúscula. 2. Si no hay '_', devuelve "".
        // PISTA: puedes apoyarte en la misma idea del core mnemonicDe y quedarte solo con la letra.
        // OJO: "_Guardar" -> "G". El test no incluye "Alt+", solo la letra.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para letraMnemonico");
    }

    /**
     * Reto Extra 3: Texto accesible con fallback.
     * Devuelve la descripción accesible si existe; si está vacía, el texto visible.
     */
    public static String textoAccesible(String textoVisible, String descripcion) {
        // GUÍA: teoría 4.3 (accessibleText es lo que LEE el lector de pantalla; si no lo pones, lee el texto).
        // 1. return (descripcion == null || descripcion.isBlank()) ? textoVisible : descripcion;
        // OJO: el test prueba ("Guardar", "") -> "Guardar" y ("🖫", "Guardar cambios") -> "Guardar cambios".
        //   Un botón solo con icono SIN accessibleText es invisible para un ciego: el icono no se "lee".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para textoAccesible");
    }

    /**
     * Reto Extra 4: Escapar el guion bajo literal.
     * Convierte el doble guion bajo (escape de mnemónico) en un guion bajo literal.
     */
    public static String escaparMnemonico(String texto) {
        // GUÍA: teoría 4.2 (con mnemonicParsing, "__" significa "un '_' de verdad", no un marcador).
        // 1. return texto.replace("__", "_");
        // OJO: el test pasa "Ctrl __ C" -> "Ctrl _ C". Así muestras un guion bajo sin que se interprete
        //   como mnemónico.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para escaparMnemonico");
    }

    /**
     * Reto Extra 5: Atajo de teclado multiplataforma.
     * Normaliza "Ctrl+..." a "Shortcut+..." (la tecla portable de JavaFX).
     */
    public static String atajoNormalizado(String combinacion) {
        // GUÍA: teoría 4.4 (KeyCombination.SHORTCUT es Ctrl en Windows/Linux y Cmd en macOS).
        // 1. return combinacion.replace("Ctrl", "Shortcut");
        // PISTA: en JavaFX usarías KeyCombination.valueOf("Shortcut+S") para un atajo portable.
        // OJO: el test prueba "Ctrl+S"->"Shortcut+S" y "Ctrl+Shift+S"->"Shortcut+Shift+S" (no toques Shift).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para atajoNormalizado");
    }

    /**
     * Reto Extra 6: Orden de tabulación válido.
     * Indica si una lista de índices de tabulación es estrictamente ascendente (sin saltos raros).
     */
    public static boolean ordenTabValido(List<Integer> indices) {
        // GUÍA: teoría 4.1 (un orden de foco usable avanza siempre hacia delante, sin retrocesos).
        // 1. Recorre desde i=1 y comprueba que indices.get(i) > indices.get(i-1).
        // 2. Si en algún punto NO crece, devuelve false. Si llegas al final, true.
        // OJO: el test prueba [1,2,3]->true y [1,3,2]->false. Una lista de 0 ó 1 elemento es válida (true).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ordenTabValido");
    }

    /**
     * Reto Extra 7: Contraste suficiente (WCAG AA).
     * Indica si una ratio de contraste cumple el mínimo AA para texto normal (4.5:1).
     */
    public static boolean contrasteSuficiente(double ratio) {
        // GUÍA: teoría 4.5 (WCAG mide el contraste texto/fondo como una ratio; AA pide >= 4.5:1).
        // 1. return ratio >= 4.5;
        // OJO: el test prueba 4.5 -> true (la frontera SÍ pasa) y 3.0 -> false. Cuidado con > vs >=.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contrasteSuficiente");
    }

    /**
     * Reto Extra 8: Nivel de contraste WCAG.
     * Clasifica una ratio en "AAA", "AA", "AA-grande" o "insuficiente".
     */
    public static String nivelContraste(double ratio) {
        // GUÍA: teoría 4.5 (umbrales WCAG: 7.0 AAA, 4.5 AA texto normal, 3.0 AA texto grande).
        // 1. if (ratio >= 7) return "AAA";
        // 2. if (ratio >= 4.5) return "AA";
        // 3. if (ratio >= 3) return "AA-grande";
        // 4. return "insuficiente";
        // OJO: el ORDEN de los if importa (de mayor a menor umbral). El test prueba 7.0->"AAA",
        //   4.5->"AA", 2.0->"insuficiente".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para nivelContraste");
    }

    /**
     * Reto Extra 9: Rol accesible de un control.
     * Mapea un tipo de control a su rol de accesibilidad (lo que anuncia el lector de pantalla).
     */
    public static String rolAccesible(String tipoControl) {
        // GUÍA: teoría 4.3 (cada control tiene un AccessibleRole: BUTTON, TEXT, CHECK_BOX...).
        // 1. switch (tipoControl): "boton"->"BUTTON", "etiqueta"->"TEXT", "casilla"->"CHECK_BOX".
        // 2. default -> "NODE".
        // OJO: el test prueba "boton"->"BUTTON" y "casilla"->"CHECK_BOX". El rol es lo que oye el
        //   usuario ciego ("botón Guardar"): por eso un Label usado como botón confunde al lector.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para rolAccesible");
    }

    /**
     * Reto Extra 10: Orden de foco saltando los deshabilitados.
     * Devuelve el orden de foco (fila, columna) pero EXCLUYENDO los campos deshabilitados.
     */
    public static List<String> ordenFocoSaltandoDeshabilitados(List<Campo> campos) {
        // GUÍA: teoría 4.1 (un control deshabilitado NO debe recibir el foco: Tab lo salta).
        // 1. Filtra 'campos' quedándote con los habilitados (c.habilitado()).
        // 2. Ordénalos por (fila, columna) igual que el core ordenFoco.
        // 3. Devuelve sus nombres.
        // PISTA: campos.stream().filter(Campo::habilitado).sorted(comparador).map(Campo::nombre).toList().
        // OJO: el test incluye un campo deshabilitado en medio y espera que NO aparezca en el resultado.
        // CULTURA: si el foco cae en un control que no se puede usar, el usuario de teclado se queda
        //   "atrapado" sin saber por qué; por eso los lectores de pantalla y Tab los ignoran.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ordenFocoSaltandoDeshabilitados");
    }

    /**
     * Campo de formulario para los ejercicios de orden de foco. Posición por (fila, columna) y si
     * está habilitado (los deshabilitados no reciben foco).
     */
    public record Campo(String nombre, int fila, int columna, boolean habilitado) {
        /** Atajo: un campo habilitado por defecto. */
        public Campo(String nombre, int fila, int columna) {
            this(nombre, fila, columna, true);
        }
    }

    /** Comparador de orden de lectura (fila y luego columna), reutilizable por core y retos. */
    static final Comparator<Campo> ORDEN_LECTURA =
            Comparator.comparingInt(Campo::fila).thenComparingInt(Campo::columna);

    /** Helper de demostración: lista mutable de campos. */
    static List<Campo> campos(Campo... cs) {
        return new ArrayList<>(List.of(cs));
    }
}
