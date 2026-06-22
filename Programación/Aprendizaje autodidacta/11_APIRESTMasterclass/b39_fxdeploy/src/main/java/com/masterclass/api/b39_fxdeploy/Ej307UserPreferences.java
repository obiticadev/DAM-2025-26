package com.masterclass.api.b39_fxdeploy;

import java.util.Arrays;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * Ejercicio 307 · Persistir los ajustes del usuario con la API {@code java.util.prefs.Preferences}.
 *
 * <p>Teoría: {@code teoria/39_Distribucion_Instaladores.md} (sección 3).
 *
 * <p>Cuando el usuario cambia el tema a oscuro o el tamaño de la ventana, ¿dónde se guarda eso para
 * la próxima vez? No en un fichero que tengas que gestionar tú: Java ofrece {@link Preferences}, un
 * almacén clave→valor por usuario que el SO persiste solo (en el registro de Windows, en
 * {@code ~/.java} en Linux). Pides un NODO (una "carpeta" de ajustes) con
 * {@code Preferences.userRoot().node("ruta")} y lees/escribes con {@code get/put}.
 *
 * <p>Estos cores reciben el {@link Preferences} como parámetro, así que el test crea un nodo
 * TEMPORAL, lo usa y lo borra: determinista y sin tocar los ajustes reales de tu máquina.
 */
public final class Ej307UserPreferences {

    private Ej307UserPreferences() {
    }

    /**
     * Guarda un valor de texto bajo una clave.
     *
     * @param nodo  nodo de preferencias (puede ser null)
     * @param clave clave (puede ser null)
     * @param valor valor a guardar
     * @return true si se guardó; false si nodo o clave eran null
     */
    public static boolean guardarPreferencia(Preferences nodo, String clave, String valor) {
        // TODO 1: si nodo o clave son null, devuelve false (no hay dónde/qué guardar).
        // TODO 2: llama a nodo.put(clave, valor) (put guarda el par clave→valor).
        // TODO 3: devuelve true.
        //         (el test guarda "tema"->"oscuro" en un nodo temporal y luego lo lee).
        return false;
    }

    /**
     * Lee un valor de texto; si la clave no existe, devuelve el valor por defecto.
     *
     * @param nodo       nodo de preferencias (puede ser null)
     * @param clave      clave a leer
     * @param porDefecto valor a devolver si no existe
     * @return el valor guardado o {@code porDefecto}
     */
    public static String leerPreferencia(Preferences nodo, String clave, String porDefecto) {
        // TODO 4: si nodo o clave son null, devuelve porDefecto.
        // TODO 5: devuelve nodo.get(clave, porDefecto) (get YA aplica el valor por defecto si falta).
        // TODO 6: (no hace falta más: get nunca lanza por clave inexistente, devuelve porDefecto).
        //         (el test: leer "tema" guardado -> "oscuro"; leer "inexistente" -> el por defecto).
        return porDefecto;
    }

    /**
     * Borra una clave del nodo.
     *
     * @param nodo  nodo de preferencias (puede ser null)
     * @param clave clave a borrar
     * @return true si se intentó borrar (nodo y clave no null); false en otro caso
     */
    public static boolean borrarPreferencia(Preferences nodo, String clave) {
        // TODO 7: si nodo es null, devuelve false.
        // TODO 8: si clave es null, devuelve false.
        // TODO 9: llama a nodo.remove(clave) (borra el par; si no existía, no pasa nada).
        // TODO 10: devuelve true.
        //          (el test: guarda una clave, la borra y comprueba que leerla devuelve el por defecto).
        return false;
    }

    public static void main(String[] args) {
        Preferences nodo = Preferences.userRoot().node("masterclass/b39/demo");
        guardarPreferencia(nodo, "tema", "oscuro");
        System.out.println("Tema: " + leerPreferencia(nodo, "tema", "claro"));
        borrarPreferencia(nodo, "tema");
        System.out.println("Tras borrar: " + leerPreferencia(nodo, "tema", "claro"));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Guardar un entero.
     * {@code Preferences} guarda números con {@code putInt}.
     */
    public static boolean guardarEntero(Preferences nodo, String clave, int valor) {
        // GUÍA: teoría 3.2 (putInt/getInt evitan que tú conviertas a String a mano).
        // 1. Si nodo o clave son null, devuelve false.
        // 2. nodo.putInt(clave, valor); devuelve true.
        // OJO: el test guarda 800 en "ancho" y luego lo lee con getInt.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para guardarEntero");
    }

    /**
     * Reto Extra 2: Leer un entero.
     * Devuelve el entero guardado o el valor por defecto.
     */
    public static int leerEntero(Preferences nodo, String clave, int porDefecto) {
        // GUÍA: teoría 3.2 (getInt parsea solo; si el valor no es número, también cae al por defecto).
        // 1. Si nodo o clave son null, devuelve porDefecto.
        // 2. Devuelve nodo.getInt(clave, porDefecto).
        // OJO: el test: leer "ancho" guardado a 800 -> 800; "inexistente" -> el por defecto.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para leerEntero");
    }

    /**
     * Reto Extra 3: Guardar un booleano.
     * Para flags como "modo oscuro" o "mostrar ayuda al iniciar".
     */
    public static boolean guardarBooleano(Preferences nodo, String clave, boolean valor) {
        // GUÍA: teoría 3.2 (putBoolean guarda true/false como texto y getBoolean lo reinterpreta).
        // 1. Si nodo o clave son null, devuelve false.
        // 2. nodo.putBoolean(clave, valor); devuelve true.
        // OJO: el test guarda true en "oscuro" y luego lo lee.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para guardarBooleano");
    }

    /**
     * Reto Extra 4: Leer un booleano.
     * Devuelve el flag guardado o el valor por defecto.
     */
    public static boolean leerBooleano(Preferences nodo, String clave, boolean porDefecto) {
        // GUÍA: teoría 3.2 (getBoolean).
        // 1. Si nodo o clave son null, devuelve porDefecto.
        // 2. Devuelve nodo.getBoolean(clave, porDefecto).
        // OJO: el test: leer "oscuro" guardado a true -> true; "inexistente" -> el por defecto.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para leerBooleano");
    }

    /**
     * Reto Extra 5: ¿Es una clave válida?
     * {@code Preferences} limita las claves a {@code Preferences.MAX_KEY_LENGTH} (80) caracteres.
     */
    public static boolean claveValida(String clave) {
        // GUÍA: teoría 3.3 (si pasas una clave demasiado larga, put lanza IllegalArgumentException).
        // 1. Si clave es null o vacía, devuelve false.
        // 2. Devuelve clave.length() <= Preferences.MAX_KEY_LENGTH.
        // PISTA: Preferences.MAX_KEY_LENGTH vale 80; no lo escribas a mano, usa la constante.
        // OJO: el test: "tema" -> true; una cadena de 100 caracteres -> false; "" -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para claveValida");
    }

    /**
     * Reto Extra 6: ¿Existe la clave?
     * true si el nodo tiene un valor guardado bajo esa clave.
     */
    public static boolean existeClave(Preferences nodo, String clave) {
        // GUÍA: teoría 3.4 (no hay un "contains"; se mira la lista de claves keys()).
        // 1. Si nodo o clave son null, devuelve false.
        // 2. Recorre nodo.keys() (un String[]) y devuelve true si alguna es igual a la clave.
        // PISTA: Arrays.asList(nodo.keys()).contains(clave) (keys() puede lanzar BackingStoreException:
        //   captúrala con try/catch y devuelve false en ese caso).
        // OJO: el test: tras guardar "tema", existeClave(...,"tema") -> true; "otra" -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para existeClave");
    }

    /**
     * Reto Extra 7: Listar las claves ordenadas.
     * Todas las claves del nodo, en orden alfabético.
     */
    public static List<String> listarClaves(Preferences nodo) {
        // GUÍA: teoría 3.4 (keys() devuelve un array en orden arbitrario; lo ordenamos para que sea estable).
        // 1. Si nodo es null, devuelve List.of().
        // 2. Lee String[] claves = nodo.keys(), ordénalas (Arrays.sort) y devuélvelas como lista.
        //    (envuelve en try/catch BackingStoreException -> List.of()).
        // PISTA: String[] k = nodo.keys(); Arrays.sort(k); return List.of(k);
        // OJO: el test guarda "b" y "a" y espera ["a","b"] (ordenadas, no en orden de inserción).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para listarClaves");
    }

    /**
     * Reto Extra 8: Limpiar el nodo.
     * Borra TODAS las claves del nodo (resetear ajustes a fábrica).
     */
    public static boolean limpiarNodo(Preferences nodo) {
        // GUÍA: teoría 3.4 (clear() borra todas las claves de ESTE nodo, no las de sus hijos).
        // 1. Si nodo es null, devuelve false.
        // 2. Llama a nodo.clear() (try/catch BackingStoreException -> devuelve false).
        // 3. Devuelve true si no hubo error.
        // OJO: el test guarda 2 claves, llama a limpiarNodo y comprueba que listarClaves queda vacía.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para limpiarNodo");
    }

    /**
     * Reto Extra 9: Leer un double.
     * Para ajustes como el volumen o el factor de zoom.
     */
    public static double leerDoble(Preferences nodo, String clave, double porDefecto) {
        // GUÍA: teoría 3.2 (getDouble, el hermano decimal de getInt).
        // 1. Si nodo o clave son null, devuelve porDefecto.
        // 2. Devuelve nodo.getDouble(clave, porDefecto).
        // OJO: el test guarda 0.8 con putDouble y lo lee -> 0.8; "inexistente" -> el por defecto.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para leerDoble");
    }

    /**
     * Reto Extra 10: Ruta absoluta del nodo.
     * La "ruta" del nodo dentro del árbol de preferencias (como una ruta de carpetas).
     */
    public static String rutaNodo(Preferences nodo) {
        // GUÍA: teoría 3.1 (los nodos forman un árbol; absolutePath() es su ruta desde la raíz).
        // 1. Si nodo es null, devuelve "".
        // 2. Devuelve nodo.absolutePath().
        // OJO: el test crea un nodo "masterclass/b39/test" y comprueba que la ruta empieza por "/".
        // CULTURA: este árbol jerárquico de nodos es el mismo modelo del registro de Windows
        //   (HKEY_CURRENT_USER\...) y de los .plist de macOS: Preferences te abstrae de cada SO.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para rutaNodo");
    }

    /** Helper de demostración: lista (sin ordenar) las claves de un nodo, tragándose la excepción. */
    static List<String> clavesCrudas(Preferences nodo) {
        try {
            return Arrays.asList(nodo.keys());
        } catch (BackingStoreException e) {
            return List.of();
        }
    }
}
