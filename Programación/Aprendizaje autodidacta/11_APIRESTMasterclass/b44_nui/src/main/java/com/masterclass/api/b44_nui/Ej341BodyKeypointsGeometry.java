package com.masterclass.api.b44_nui;

import java.util.List;
import java.util.Map;

/**
 * Ejercicio 341 · Geometría de los keypoints del cuerpo: ángulos, posturas y distancias.
 *
 * <p>Un motor de pose (MediaPipe, OpenPose) entrega <em>keypoints</em>: puntos 2D de las
 * articulaciones (hombro, codo, muñeca...). A partir de ellos, la geometría calcula ángulos
 * (¿el brazo está estirado?), distancias y posturas. Esa matemática es pura y testeable; el motor
 * que detecta los puntos en la imagen es "guion".
 *
 * <p>Teoría: {@code teoria/44_Interfaces_Naturales.md} (sección 5).
 */
public final class Ej341BodyKeypointsGeometry {

    private Ej341BodyKeypointsGeometry() {
    }

    /**
     * Ángulo (en grados) de la articulación situada en {@code b}, formado por los segmentos B→A y B→C.
     *
     * @param a primer extremo (p. ej. hombro)
     * @param b vértice de la articulación (p. ej. codo)
     * @param c segundo extremo (p. ej. muñeca)
     * @return el ángulo en grados [0,180]; -1 si algún segmento tiene longitud cero (degenerado)
     */
    public static double anguloArticulacion(Punto a, Punto b, Punto c) {
        // TODO 1: si a, b o c son null -> -1.
        // TODO 2: vector BA = (a.x-b.x, a.y-b.y); vector BC = (c.x-b.x, c.y-b.y).
        // TODO 3: producto escalar dot = BA.x*BC.x + BA.y*BC.y.
        // TODO 4: magnitudes |BA| y |BC| con Math.hypot; si alguna es 0 -> -1 (degenerado).
        // TODO 5: coseno = dot/(|BA|*|BC|); recórtalo a [-1,1] y devuelve Math.toDegrees(Math.acos(coseno)).
        return -1;
    }

    /**
     * Distancia euclídea entre dos keypoints nombrados.
     *
     * @param keypoints mapa nombre→punto
     * @param a         nombre del primer keypoint
     * @param b         nombre del segundo keypoint
     * @return la distancia; -1 si falta alguno de los dos keypoints
     */
    public static double distanciaArticulaciones(Map<String, Punto> keypoints, String a, String b) {
        // TODO 6: si keypoints es null -> -1.
        // TODO 7: si no contiene la clave 'a' o la clave 'b' -> -1 (keypoint ausente/ocluido).
        // TODO 8: recupera los dos puntos del mapa.
        // TODO 9: calcula Math.hypot(pa.x-pb.x, pa.y-pb.y).
        // TODO 10: devuelve la distancia.
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("Ángulo (0,1)-(0,0)-(1,0): "
                + anguloArticulacion(new Punto(0, 1), new Punto(0, 0), new Punto(1, 0)));
        System.out.println("Distancia hombro-mano: " + distanciaArticulaciones(
                Map.of("hombro", new Punto(0, 0), "mano", new Punto(0, 5)), "hombro", "mano"));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: ¿Es simétrico?
     * Compara el ángulo del lado izquierdo y el derecho dentro de una tolerancia.
     */
    public static boolean esSimetrico(double anguloIzq, double anguloDer, double tolerancia) {
        // GUÍA: teoría 5 (postura simétrica: ambos brazos al mismo ángulo, p. ej. en yoga/fitness).
        // 1. Devuelve Math.abs(anguloIzq - anguloDer) <= tolerancia.
        // OJO: el test: (90,92,5)->true; (90,100,5)->false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esSimetrico");
    }

    /**
     * Reto Extra 2: Centro de masa de los keypoints.
     * Promedio de todos los puntos: aproxima el centro del cuerpo.
     */
    public static Punto centroDeMasa(List<Punto> keypoints) {
        // GUÍA: teoría 5 (el centro de masa sirve para detectar equilibrio/caída — ver reto 4).
        // 1. Lista null/vacía -> new Punto(0,0).
        // 2. Promedia las x y las y por separado y devuelve new Punto(mediaX, mediaY).
        // OJO: el test: [(0,0),(2,2)] -> (1,1); lista vacía -> (0,0).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para centroDeMasa");
    }

    /**
     * Reto Extra 3: Contar repeticiones (p. ej. sentadillas).
     * Cuenta los ciclos en que el ángulo baja de un umbral y vuelve a subir por encima del otro.
     */
    public static int contarRepeticiones(List<Double> angulos, double umbralBajo, double umbralAlto) {
        // GUÍA: teoría 5 (un contador de ejercicio: cada rep = bajar (flexión) y volver a estirar).
        // 1. Empieza en estado "arriba". Recorre la serie.
        // 2. Si el ángulo baja de umbralBajo -> estado "abajo". Si estabas "abajo" y supera umbralAlto
        //    -> cuenta una repetición y vuelves a "arriba".
        // OJO: el test: [170,90,170,90,170] con (100,160) -> 2 repeticiones; lista vacía -> 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarRepeticiones");
    }

    /**
     * Reto Extra 4: Detectar caída.
     * Si la altura de la cabeza cae por debajo de un umbral, se considera caída.
     */
    public static boolean detectaCaida(double alturaCabeza, double umbral) {
        // GUÍA: teoría 5 (aplicación real: detección de caídas en asistencia a mayores).
        // 1. Devuelve alturaCabeza < umbral.
        // OJO: el test: (0.3,0.5)->true; (0.8,0.5)->false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para detectaCaida");
    }

    /**
     * Reto Extra 5: Filtro de jitter (temblor).
     * Si el cambio respecto al valor anterior es demasiado brusco, se descarta y se mantiene el previo.
     */
    public static double filtrarJitter(double actual, double anterior, double maxCambio) {
        // GUÍA: teoría 5 (los keypoints "saltan" entre frames; un salto enorme suele ser ruido).
        // 1. Si Math.abs(actual - anterior) > maxCambio -> devuelve anterior.
        // 2. Si no -> devuelve actual.
        // OJO: el test: (100,90,5)->90 (salto grande, se ignora); (92,90,5)->92.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para filtrarJitter");
    }

    /**
     * Reto Extra 6: Confianza media de la detección.
     * Promedio de la confianza de cada keypoint.
     */
    public static double confianzaPromedio(List<Double> confianzas) {
        // GUÍA: teoría 5 (cada keypoint trae su confianza; la media indica si la pose es fiable).
        // 1. Lista null/vacía -> 0 (evita dividir por cero).
        // 2. Devuelve la media.
        // OJO: el test: [0.8,0.6]->0.7; lista vacía -> 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para confianzaPromedio");
    }

    /**
     * Reto Extra 7: ¿Hay oclusión?
     * Devuelve true si falta algún keypoint requerido (tapado por otra parte del cuerpo).
     */
    public static boolean hayOclusion(Map<String, Punto> keypoints, List<String> requeridos) {
        // GUÍA: teoría 5 (oclusión: una mano tapa la otra y el motor pierde keypoints).
        // 1. keypoints null -> true (no hay nada).
        // 2. Devuelve true si ALGÚN nombre de 'requeridos' no está en el mapa.
        // PISTA: requeridos.stream().anyMatch(r -> !keypoints.containsKey(r)).
        // OJO: el test: ({mano},[mano,pie])->true (falta pie); ({mano,pie},[mano])->false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para hayOclusion");
    }

    /**
     * Reto Extra 8: Normalizar por la altura del cuerpo.
     * Divide una distancia por la altura para que sea invariante a lo lejos que esté la persona.
     */
    public static double normalizarPorAltura(double valor, double altura) {
        // GUÍA: teoría 5 (escala invariante: una persona lejos da puntos pequeños; se normaliza).
        // 1. Si altura <= 0 -> 0 (evita dividir por cero).
        // 2. Devuelve valor / altura.
        // OJO: el test: (50,100)->0.5; (50,0)->0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para normalizarPorAltura");
    }

    /**
     * Reto Extra 9: Parecido entre dos secuencias de ángulos (DTW simplificado).
     * Para series de igual longitud, suma las diferencias absolutas posición a posición.
     */
    public static double parecidoPosturas(List<Double> a, List<Double> b) {
        // GUÍA: teoría 5 (comparar una pose con la "pose objetivo": menor valor = más parecidas).
        // 1. Si alguna lista es null o difieren en tamaño -> -1 (no comparables).
        // 2. Suma Math.abs(a[i]-b[i]) para todo i.
        // OJO: el test: ([1,2,3] iguales)->0; ([1,2],[2,2])->1; tamaños distintos -> -1.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para parecidoPosturas");
    }

    /**
     * Reto Extra 10: ¿El esqueleto está completo?
     * Cada "hueso" conecta dos keypoints; el esqueleto es válido si ambos extremos de cada hueso existen.
     */
    public static boolean esEsqueletoCompleto(Map<String, Punto> keypoints, List<String[]> huesos) {
        // GUÍA: teoría 5 (el esqueleto es un GRAFO: nodos = keypoints, aristas = huesos; enlaza b40).
        // 1. keypoints null -> false.
        // 2. Para cada hueso (par [origen,destino]), comprueba que el mapa contiene AMBOS nombres.
        // 3. Devuelve true solo si todos los huesos cumplen.
        // OJO: el test: kps {a,b}, huesos [[a,b]] -> true; huesos [[a,c]] -> false (falta c).
        // CULTURA: estos keypoints vienen de procesar la imagen píxel a píxel en b40.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esEsqueletoCompleto");
    }
}
