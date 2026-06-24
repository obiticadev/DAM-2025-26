package com.masterclass.api.b44_nui;

import java.util.List;
import java.util.Map;

/**
 * Ejercicio 337 · Panorama de las interfaces naturales: del mundo a la intención.
 *
 * <p>Una NUI (Natural User Interface) traduce señales del mundo físico —voz, gestos, movimiento del
 * cuerpo, marcadores de realidad aumentada, patrones aprendidos— en una <em>intención</em> que la
 * aplicación sabe ejecutar. Sea cual sea la modalidad, todas comparten el mismo
 * <strong>pipeline</strong>: captura → preproceso → reconocimiento → intención → acción.
 *
 * <p>Este ejercicio es de <strong>guion + modelo</strong>: clasifica la modalidad por palabras clave
 * y describe el pipeline; los motores reales (Vosk, MediaPipe, ARCore, Weka) viven fuera del Maven.
 *
 * <p>Teoría: {@code teoria/44_Interfaces_Naturales.md} (sección 1).
 */
public final class Ej337NuiOverview {

    private Ej337NuiOverview() {
    }

    /** Las cinco modalidades naturales que cubre el RA2 de Desarrollo de Interfaces. */
    public enum Modalidad {
        VOZ, GESTO, CUERPO, RA, ML
    }

    /**
     * Clasifica una descripción libre en la modalidad natural a la que pertenece, por palabras clave.
     *
     * @param entrada texto que describe la interacción (p. ej. "comando de voz")
     * @return la {@link Modalidad} detectada, o {@code null} si no reconoce ninguna
     */
    public static Modalidad clasificarModalidad(String entrada) {
        // TODO 1: si 'entrada' es null o en blanco, no hay nada que clasificar -> devuelve null.
        // TODO 2: normaliza a minúsculas para comparar sin importar mayúsculas.
        // TODO 3: VOZ si contiene "voz", "comando" o "dijo"; GESTO si "swipe", "gesto" o "desliza".
        // TODO 4: CUERPO si "postura", "cuerpo" o "brazo"; RA si "marcador" o "realidad";
        //         ML si "modelo" o "prediccion".
        // TODO 5: si no casa ninguna palabra clave, devuelve null (centinela).
        return null;
    }

    /**
     * Devuelve, en orden, las etapas del pipeline de percepción de una NUI.
     *
     * @param m la modalidad (todas comparten el mismo pipeline de 5 etapas)
     * @return etapas ["captura","preproceso","reconocimiento","intencion","accion"]; vacío si m es null
     */
    public static List<String> pipelineDe(Modalidad m) {
        // TODO 6: si m es null, no hay pipeline -> devuelve List.of() (centinela).
        // TODO 7: prepara la lista de etapas en orden de ejecución.
        // TODO 8: añade "captura" (sensor) y "preproceso" (limpiar/normalizar la señal).
        // TODO 9: añade "reconocimiento" (señal -> símbolo) e "intencion" (símbolo -> qué quiere el usuario).
        // TODO 10: añade "accion" (ejecutar en la UI) y devuelve la lista completa.
        return List.of();
    }

    public static void main(String[] args) {
        System.out.println("Modalidad de 'comando de voz': " + clasificarModalidad("comando de voz"));
        System.out.println("Pipeline de VOZ: " + pipelineDe(Modalidad.VOZ));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Latencia total del pipeline.
     * Suma las latencias (ms) de cada etapa para saber cuánto tarda la NUI en responder.
     */
    public static int latenciaTotalMs(List<Integer> latenciasPorEtapa) {
        // GUÍA: teoría 1 (la latencia percibida es la SUMA de las etapas; >100 ms ya se nota).
        // 1. Lista null o vacía -> 0 (no hay etapas, no hay latencia).
        // 2. Recorre y acumula; un stream con mapToInt(...).sum() también vale.
        // OJO: el test prueba lista vacía -> 0 (no debe lanzar excepción).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para latenciaTotalMs");
    }

    /**
     * Reto Extra 2: Modalidad de respaldo.
     * Si la modalidad principal falla (ruido, mala luz), ¿a cuál se cae el sistema?
     */
    public static Modalidad modalidadDeRespaldo(Modalidad principal) {
        // GUÍA: teoría 1 (una NUI robusta SIEMPRE tiene plan B: si la voz falla, gesto; y viceversa).
        // 1. VOZ->GESTO, GESTO->VOZ, CUERPO->GESTO, RA->GESTO, ML->VOZ.
        // 2. null -> null (sin entrada, sin respaldo).
        // OJO: el test comprueba VOZ->GESTO y GESTO->VOZ; null->null es el caso límite.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para modalidadDeRespaldo");
    }

    /**
     * Reto Extra 3: La NUI debe mantener una alternativa clásica.
     * Devuelve si el usuario conserva una vía de entrada tradicional (teclado/ratón).
     */
    public static boolean mantieneAlternativa(boolean nuiActiva, boolean entradaClasicaDisponible) {
        // GUÍA: teoría "Accesibilidad y privacidad" (la NUI es APOYO, nunca el ÚNICO camino: quien
        //   no puede hablar/gesticular debe poder usar la app igual). Enlaza con b36 (a11y).
        // 1. Devuelve directamente entradaClasicaDisponible: sin ella, la app sería inaccesible.
        // OJO: el test exige que (true,false) -> false aunque la NUI esté activa.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mantieneAlternativa");
    }

    /**
     * Reto Extra 4: Modalidades combinadas (multimodal).
     * Devuelve los nombres de las modalidades, sin repetir y ordenados alfabéticamente.
     */
    public static List<String> modalidadesCombinadas(List<Modalidad> modalidades) {
        // GUÍA: teoría 1 (interfaz MULTIMODAL = voz+gesto a la vez, p. ej. "pon ESTO aquí" + señalar).
        // 1. Lista null/vacía -> List.of().
        // 2. stream().map(Enum::name).distinct().sorted().toList().
        // OJO: el test pasa [VOZ,GESTO,VOZ] y espera ["GESTO","VOZ"] (sin duplicado y ordenado).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para modalidadesCombinadas");
    }

    /**
     * Reto Extra 5: ¿Requiere consentimiento biométrico?
     * Voz, cuerpo y RA capturan datos biométricos sensibles; gesto y ML (sobre uso) no.
     */
    public static boolean requiereConsentimientoBiometrico(Modalidad m) {
        // GUÍA: teoría "privacidad" (la voz y la imagen del cuerpo son DATOS BIOMÉTRICOS: RGPD exige
        //   consentimiento explícito). Enlaza con b30 (no almacenar señales sensibles en claro).
        // 1. true para VOZ, CUERPO y RA; false para GESTO y ML; null -> false.
        // OJO: el test comprueba CUERPO->true y GESTO->false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para requiereConsentimientoBiometrico");
    }

    /**
     * Reto Extra 6: Detección de palabra de activación (wake word).
     * Devuelve si la frase empieza por la palabra clave que "despierta" al asistente.
     */
    public static boolean detectaWakeWord(String frase, String wakeWord) {
        // GUÍA: teoría 1 ("Ok Google", "Alexa": hasta oír la wake word el micro NO escucha de verdad).
        // 1. Normaliza ambos a minúsculas y recorta espacios.
        // 2. Devuelve si la frase startsWith la wakeWord.
        // PISTA: frase.strip().toLowerCase().startsWith(wakeWord.strip().toLowerCase()).
        // OJO: el test: ("hola asistente abre","hola asistente")->true; ("abre","hola asistente")->false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para detectaWakeWord");
    }

    /**
     * Reto Extra 7: Activación por confianza (confidence gating).
     * Solo actúa si la confianza del reconocimiento alcanza el umbral.
     */
    public static boolean activaPorConfianza(double confianza, double umbral) {
        // GUÍA: teoría 1 + Ej339 (actuar con confianza baja = falsos positivos; mejor ignorar).
        // 1. Devuelve confianza >= umbral.
        // OJO: el test: (0.8,0.6)->true; (0.5,0.6)->false. El borde (igual al umbral) cuenta como sí.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para activaPorConfianza");
    }

    /**
     * Reto Extra 8: Traducir la intención por diccionario (i18n del comando).
     * Devuelve la traducción del comando, o el propio comando si no está en el diccionario.
     */
    public static String traducirIntencion(String comando, Map<String, String> diccionario) {
        // GUÍA: teoría 1 (el mismo "abrir" se dice "open"/"ouvrir": la INTENCIÓN es la misma).
        // 1. diccionario.getOrDefault(comando, comando).
        // OJO: el test: ("open",{open=abrir})->"abrir"; ("xyz",{open=abrir})->"xyz" (no traducible).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para traducirIntencion");
    }

    /**
     * Reto Extra 9: Modo manos libres.
     * Se activa cuando el usuario no puede usar las manos (conduciendo o con la pantalla bloqueada).
     */
    public static boolean modoManosLibres(boolean enConduccion, boolean pantallaBloqueada) {
        // GUÍA: teoría 1 (la voz brilla cuando las manos están ocupadas: coche, cocina, accesibilidad).
        // 1. Devuelve enConduccion || pantallaBloqueada.
        // OJO: el test: (true,false)->true; (false,false)->false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para modoManosLibres");
    }

    /**
     * Reto Extra 10: Descripción accesible de la modalidad.
     * Devuelve una etiqueta legible para lectores de pantalla (conecta con la a11y de b36).
     */
    public static String descripcionAccesible(Modalidad m) {
        // GUÍA: teoría "accesibilidad" (toda interacción debe tener un nombre accesible; b36 RA3).
        // 1. VOZ->"control por voz", GESTO->"control por gestos", CUERPO->"control corporal",
        //    RA->"realidad aumentada", ML->"asistencia inteligente".
        // 2. null -> "" (centinela de cadena).
        // CULTURA: en una UI real esto alimenta el accessibleText del nodo (b36 Ej290).
        // OJO: el test: VOZ->"control por voz"; null->"".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para descripcionAccesible");
    }
}
