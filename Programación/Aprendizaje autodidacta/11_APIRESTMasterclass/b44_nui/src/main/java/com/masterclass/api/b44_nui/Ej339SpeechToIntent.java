package com.masterclass.api.b44_nui;

import java.util.List;
import java.util.Optional;

/**
 * Ejercicio 339 · De la transcripción a la acción de UI: confianza, sinónimos y <em>fuzzy match</em>.
 *
 * <p>El motor de voz nunca está 100 % seguro: entrega una transcripción y una <strong>confianza</strong>
 * [0,1]. Una buena NUI descarta lo que no supera un umbral (mejor no hacer nada que hacer lo
 * equivocado) y tolera pequeños errores buscando el comando más parecido por distancia de edición.
 *
 * <p>Teoría: {@code teoria/44_Interfaces_Naturales.md} (sección 3).
 */
public final class Ej339SpeechToIntent {

    private Ej339SpeechToIntent() {
    }

    /** Acciones de UI que la voz puede disparar. */
    public enum AccionUi {
        ABRIR, CERRAR, GUARDAR, BUSCAR, BORRAR, NINGUNA
    }

    /**
     * Convierte una transcripción en una acción de UI, descartándola si la confianza es baja.
     *
     * @param transcripcion texto reconocido por el motor de voz
     * @param confianza     confianza del motor en [0,1]
     * @param umbral        confianza mínima para actuar, en [0,1]
     * @return la acción dentro de un {@link Optional}; vacío si no llega al umbral o no se reconoce
     */
    public static Optional<AccionUi> aAccionUi(String transcripcion, double confianza, double umbral) {
        // TODO 1: si el umbral está fuera de [0,1], es un uso inválido -> devuelve Optional.empty().
        // TODO 2: si la confianza es menor que el umbral, descarta -> Optional.empty().
        // TODO 3: si la transcripción es null/blanca -> Optional.empty().
        // TODO 4: normaliza a minúsculas y mapea por palabra clave: "abrir"->ABRIR, "cerrar"->CERRAR,
        //         "guardar"->GUARDAR, "buscar"->BUSCAR, "borrar"->BORRAR.
        // TODO 5: si no casa ninguna palabra clave -> Optional.empty() (rechazo explícito).
        // TODO 6: devuelve Optional.of(accionReconocida).
        return Optional.empty();
    }

    /**
     * Devuelve el comando del catálogo más parecido a la entrada (mínima distancia de Levenshtein).
     *
     * @param entrada  texto reconocido, quizá con erratas
     * @param comandos catálogo de comandos válidos
     * @return el comando más cercano; "" si la lista está vacía o hay empate al mínimo
     */
    public static String mejorCoincidencia(String entrada, List<String> comandos) {
        // TODO 7: si comandos es null o está vacío, devuelve "" (centinela).
        // TODO 8: calcula la distancia de Levenshtein de 'entrada' a cada comando.
        // TODO 9: quédate con la distancia MÍNIMA; si dos comandos empatan en el mínimo, devuelve ""
        //         (ambiguo: mejor no adivinar).
        // TODO 10: devuelve el comando de menor distancia.
        return "";
    }

    public static void main(String[] args) {
        System.out.println("Acción de 'abrir documento' (conf 0.9): " + aAccionUi("abrir documento", 0.9, 0.5));
        System.out.println("Mejor para 'guardr': " + mejorCoincidencia("guardr", List.of("guardar", "buscar")));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: ¿Es un rechazo por confianza baja?
     * Devuelve si la confianza no alcanza el umbral (debe ignorarse).
     */
    public static boolean esRechazo(double confianza, double umbral) {
        // GUÍA: teoría 3 (rejection: bajo el umbral, NO se actúa).
        // 1. Devuelve confianza < umbral.
        // OJO: el test: (0.3,0.5)->true; (0.7,0.5)->false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esRechazo");
    }

    /**
     * Reto Extra 2: Usar el historial cuando no hay transcripción nueva.
     * Si la actual está vacía, repite la anterior (contexto conversacional).
     */
    public static String usarHistorial(String actual, String previo) {
        // GUÍA: teoría 3 (mantener contexto: "y ahora ciérralo" reutiliza el objeto previo).
        // 1. Si 'actual' es null/blanco -> devuelve 'previo'.
        // 2. Si no -> devuelve 'actual'.
        // OJO: el test: ("","abrir")->"abrir"; ("cerrar","abrir")->"cerrar".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para usarHistorial");
    }

    /**
     * Reto Extra 3: ¿La acción necesita confirmación?
     * Las acciones destructivas piden "¿seguro?" antes de ejecutarse.
     */
    public static boolean necesitaConfirmacion(AccionUi accion) {
        // GUÍA: teoría 3 (confirmación: borrar/cerrar son irreversibles -> doble check).
        // 1. true para BORRAR y CERRAR; false para el resto (incluido null).
        // OJO: el test: BORRAR->true; ABRIR->false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para necesitaConfirmacion");
    }

    /**
     * Reto Extra 4: Distancia de edición (Levenshtein).
     * Número mínimo de inserciones/borrados/sustituciones para pasar de 'a' a 'b'.
     */
    public static int distanciaEdicion(String a, String b) {
        // GUÍA: teoría 3 (el corazón del fuzzy match; lo usa también mejorCoincidencia).
        // 1. Matriz (|a|+1) x (|b|+1); fila/columna 0 = i y j (borrar/insertar todo).
        // 2. dp[i][j] = (a[i-1]==b[j-1]) ? dp[i-1][j-1]
        //               : 1 + min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]).
        // OJO: el test: ("casa","cosa")->1; ("","abc")->3 (insertar 3).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para distanciaEdicion");
    }

    /**
     * Reto Extra 5: Umbral adaptativo según el ruido ambiente.
     * Más ruido → umbral más exigente, pero siempre acotado a [0,1].
     */
    public static double umbralAdaptativo(double base, double ruido) {
        // GUÍA: teoría 3 (en un bar el umbral sube; en silencio baja).
        // 1. Suma base + ruido y RECORTA a [0,1] (clamp).
        // PISTA: Math.max(0, Math.min(1, base + ruido)).
        // OJO: el test: (0.5,0.2)->0.7; (0.9,0.5)->1.0 (saturado, no 1.4).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para umbralAdaptativo");
    }

    /**
     * Reto Extra 6: Interpretar un número hablado.
     * "tres" → 3 (uno..diez); -1 si no es un número conocido.
     */
    public static int interpretarNumero(String palabra) {
        // GUÍA: teoría 3 (slots numéricos: "abre la pestaña tres").
        // 1. Normaliza y mapea uno..diez -> 1..10.
        // 2. Cualquier otra cosa -> -1 (centinela numérico).
        // OJO: el test: "tres"->3; "zzz"->-1.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para interpretarNumero");
    }

    /**
     * Reto Extra 7: Normalización fonética (soundex ligero).
     * Acerca palabras que SUENAN igual: quita la 'h' muda y unifica 'v'→'b'.
     */
    public static String normalizarFonetica(String palabra) {
        // GUÍA: teoría 3 ("vaca"/"baca" suenan igual; el motor puede confundirlas).
        // 1. Pasa a minúsculas. 2. Borra todas las 'h'. 3. Sustituye 'v' por 'b'.
        // PISTA: palabra.toLowerCase().replace("h","").replace('v','b').
        // OJO: el test: "Hola"->"ola"; "vaca"->"baca".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para normalizarFonetica");
    }

    /**
     * Reto Extra 8: Tasa de error de palabras (WER, Word Error Rate).
     * Fracción de palabras de la referencia que la hipótesis NO acierta posición a posición.
     */
    public static double tasaErrorPalabras(List<String> referencia, List<String> hipotesis) {
        // GUÍA: teoría 3 (WER es la métrica estándar de calidad de un reconocedor de voz).
        // 1. Referencia null/vacía -> 0.0 (evita dividir por cero).
        // 2. errores = |tamaños distintos| + nº de posiciones que difieren en el prefijo común.
        // 3. Devuelve errores / referencia.size() (como double).
        // OJO: el test: (["abre","el","menu"], iguales)->0.0; (["abre","menu"],["abre","mano"])->0.5.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para tasaErrorPalabras");
    }

    /**
     * Reto Extra 9: Mejor de N hipótesis (n-best).
     * El motor ofrece varias transcripciones; elige la más cercana a un comando válido.
     */
    public static String mejorDeHipotesis(List<String> hipotesis, String comando) {
        // GUÍA: teoría 3 (n-best: el motor da una LISTA ordenada; reordenamos por nuestra gramática).
        // 1. Hipótesis null/vacía -> "".
        // 2. Devuelve la hipótesis con menor distanciaEdicion(hipotesis, comando).
        // OJO: el test: (["abrir","aprir","cerrar"],"abrir")->"abrir"; lista vacía -> "".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mejorDeHipotesis");
    }

    /**
     * Reto Extra 10: Registrar sin filtrar datos sensibles.
     * Oculta transcripciones que contengan secretos antes de loguearlas (enlaza con b30).
     */
    public static String registrarSinAudio(String transcripcion) {
        // GUÍA: teoría "privacidad" + b30 (NUNCA loguear voz con contraseñas/tarjetas en claro).
        // 1. Normaliza. Si contiene "password", "contrasena" o "tarjeta" -> devuelve "[oculto]".
        // 2. Si no -> devuelve la transcripción tal cual.
        // OJO: el test: "mi password es 123"->"[oculto]"; "abrir menu"->"abrir menu".
        // CULTURA: esto es minimización de datos (RGPD): el log nunca debe filtrar lo biométrico.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para registrarSinAudio");
    }
}
