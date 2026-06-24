package com.masterclass.api.b45_juego3d;

import java.util.Arrays;

/**
 * Ejercicio 347 · Cámara en perspectiva, proyección 3D→2D, materiales e iluminación.
 *
 * <p>La cámara es lo que convierte un mundo 3D en píxeles de la pantalla. La idea central es la
 * <strong>proyección en perspectiva</strong>: lo lejano se ve pequeño porque se <em>divide entre la
 * profundidad z</em>. A partir de ahí se mapea el resultado al <em>viewport</em> (la ventana en
 * píxeles) y se descartan los puntos fuera del <em>frustum</em> (la pirámide visible entre los
 * planos near y far). Esta clase modela la matemática (testeable); el {@code Playground3D} monta una
 * escena JavaFX real con {@code PerspectiveCamera}, {@code Box}, {@code PhongMaterial} y
 * {@code PointLight}.
 *
 * <p>Teoría: {@code teoria/45_Juegos3D_Motores.md} (sección 4).
 */
public final class Ej347PerspectiveCameraScene {

    private Ej347PerspectiveCameraScene() {
    }

    /**
     * Proyecta un punto 3D (en espacio de cámara) a coordenadas de pantalla en píxeles.
     *
     * @param punto3D punto {x, y, z} con z = profundidad (debe ser &gt; 0 para verse)
     * @param cam     cámara con focal y tamaño de viewport
     * @return {x_pantalla, y_pantalla}; {@code null} si el punto no es 3D o z &lt;= 0 (detrás/sobre la cámara)
     */
    public static double[] posicionEnPantalla(double[] punto3D, Camara cam) {
        // TODO 1: si punto3D es null o no mide 3, o cam es null -> null.
        // TODO 2: extrae x, y, z; si z <= 0 -> null (el punto está detrás de la cámara o sobre el plano).
        // TODO 3: proyecta: px = cam.focal() * x / z ; py = cam.focal() * y / z.
        // TODO 4: mapea al viewport: sx = cam.ancho()/2 + px ; sy = cam.alto()/2 - py
        //         (en pantalla, la Y "crece hacia abajo", por eso se RESTA py).
        // TODO 5: devuelve new double[]{sx, sy}.
        return null;
    }

    /**
     * ¿Está el punto dentro del frustum por profundidad (entre los planos near y far)?
     *
     * @param punto3D punto {x, y, z}
     * @param cam     cámara con near/far
     * @return {@code true} si {@code near <= z <= far}; {@code false} si está fuera o el punto no es válido
     */
    public static boolean dentroDelFrustum(double[] punto3D, Camara cam) {
        // TODO 6: si punto3D es null o no mide 3, o cam es null -> false.
        // TODO 7: extrae z (la 3.ª componente).
        // TODO 8: comprueba z >= cam.near().
        // TODO 9: comprueba z <= cam.far().
        // TODO 10: devuelve el AND de ambas (dentro del rango de profundidad visible).
        return false;
    }

    public static void main(String[] args) {
        Camara cam = new Camara(10, 200, 100, 1, 100);
        System.out.println("posición en pantalla de (0,0,5): " + Arrays.toString(posicionEnPantalla(new double[]{0, 0, 5}, cam)));
        System.out.println("(0,0,5) dentro del frustum: " + dentroDelFrustum(new double[]{0, 0, 5}, cam));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Relación de aspecto del viewport.
     * Ancho dividido entre alto; deformar esto "estira" la imagen.
     */
    public static double aspecto(double ancho, double alto) {
        // GUÍA: teoría 4 (el aspect ratio debe coincidir con la ventana o todo se ve achatado).
        // 1. Si alto == 0 -> -1 (división indefinida).
        // 2. Devuelve ancho / alto.
        // OJO: el test: 200/100 -> 2.0; alto 0 -> -1.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para aspecto");
    }

    /**
     * Reto Extra 2: Distancia focal a partir del campo de visión (FOV) vertical en grados.
     * focal = (alto/2) / tan(fov/2).
     */
    public static double focalDesdeFov(double fovGrados, double alto) {
        // GUÍA: teoría 4 (FOV grande = gran angular = focal pequeña; FOV pequeño = teleobjetivo).
        // 1. Si fovGrados <= 0 o >= 180 -> -1 (fuera de rango físico).
        // 2. rad = toRadians(fovGrados / 2); devuelve (alto / 2) / Math.tan(rad).
        // OJO: el test: fov 90°, alto 200 -> (100)/tan(45°)=100/1=100.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para focalDesdeFov");
    }

    /**
     * Reto Extra 3: ¿El punto está delante del plano near?
     * Solo lo que está más allá del near se dibuja.
     */
    public static boolean estaDelante(double z, double near) {
        // GUÍA: teoría 4 (near clipping: lo demasiado cerca se recorta para no dividir por ~0).
        // 1. Devuelve z > near.
        // OJO: el test: z=5, near=1 -> true; z=1, near=1 -> false (justo en el plano no cuenta).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para estaDelante");
    }

    /**
     * Reto Extra 4: Profundidad normalizada a [0, 1] entre near y far.
     * 0 = pegado al near; 1 = pegado al far. Es la base del z-buffer.
     */
    public static double profundidadNormalizada(double z, double near, double far) {
        // GUÍA: teoría 4 (el depth buffer guarda esta fracción para decidir qué tapa a qué).
        // 1. Si far == near -> -1.
        // 2. valor = (z - near) / (far - near).
        // 3. RECORTA a [0, 1] (z fuera del rango se satura).
        // OJO: el test: z=50, near=0, far=100 -> 0.5; z=200 (recortado) -> 1.0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para profundidadNormalizada");
    }

    /**
     * Reto Extra 5: Mapear una coordenada NDC ([-1, 1]) a un píxel del viewport ([0, tam]).
     * Es el último paso del pipeline gráfico: NDC -> pantalla.
     */
    public static double mapearAViewport(double ndc, double tam) {
        // GUÍA: teoría 4 (NDC = Normalized Device Coordinates; -1 es el borde izq, +1 el borde der).
        // 1. Devuelve (ndc + 1) / 2 * tam.
        // OJO: el test: ndc 0 con tam 200 -> 100 (centro); ndc 1 -> 200; ndc -1 -> 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para mapearAViewport");
    }

    /**
     * Reto Extra 6: Cámara orbital — coordenadas esféricas a cartesianas.
     * Dado un radio y dos ángulos (en grados), calcula la posición de la cámara.
     */
    public static double[] coordenadasEsfericas(double radio, double azimutGrados, double elevacionGrados) {
        // GUÍA: teoría 4 (orbitar = mover la cámara sobre una esfera mirando al centro; típico en visores 3D).
        // 1. a = toRadians(azimut); e = toRadians(elevacion).
        // 2. x = radio * cos(e) * cos(a) ; y = radio * sin(e) ; z = radio * cos(e) * sin(a).
        // 3. Devuelve {x, y, z}.
        // OJO: el test: radio 1, azimut 0, elevación 0 -> {1, 0, 0} (con tolerancia).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para coordenadasEsfericas");
    }

    /**
     * Reto Extra 7: Tamaño aparente de un objeto según su distancia.
     * tamaño_en_pantalla = focal * tamaño_real / z (lo lejano se ve pequeño).
     */
    public static double escalaPorDistancia(double tamReal, double z, double focal) {
        // GUÍA: teoría 4 (es la misma división por z que la proyección, aplicada a un tamaño).
        // 1. Si z <= 0 -> -1.
        // 2. Devuelve focal * tamReal / z.
        // OJO: el test: tamReal 2, z 5, focal 10 -> 4.0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para escalaPorDistancia");
    }

    /**
     * Reto Extra 8: ¿La coordenada de pantalla cae dentro del viewport visible?
     * Sirve para descartar (cull) objetos fuera de la ventana.
     */
    public static boolean visibleEnPantalla(double[] pantalla, double ancho, double alto) {
        // GUÍA: teoría 4 (culling 2D: no se pinta lo que cae fuera del rectángulo de la ventana).
        // 1. Si pantalla es null o no mide 2 -> false.
        // 2. Devuelve 0 <= x <= ancho && 0 <= y <= alto.
        // OJO: el test: {100,50} en 200×100 -> true; {-5,50} -> false (x negativo, fuera).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para visibleEnPantalla");
    }

    /**
     * Reto Extra 9: Intensidad de una luz puntual según la distancia (ley del inverso del cuadrado).
     * intensidad / distancia² (una bombilla ilumina menos cuanto más lejos).
     */
    public static double intensidadLuzPuntual(double[] posLuz, double[] posPunto, double intensidad) {
        // GUÍA: teoría 4 (PointLight de JavaFX: la luz cae con el cuadrado de la distancia).
        // 1. Si los puntos no miden 3 -> -1.
        // 2. d2 = distancia al cuadrado entre posLuz y posPunto (suma de diferencias al cuadrado).
        // 3. Si d2 == 0 -> intensidad (en el mismo punto, sin atenuar).
        // 4. Devuelve intensidad / d2.
        // OJO: el test: luz en {0,0,0}, punto en {0,0,2}, intensidad 16 -> 16/4 = 4.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para intensidadLuzPuntual");
    }

    /**
     * Reto Extra 10: Iluminación difusa (Lambert) — cuánto ilumina una luz a una cara.
     * max(0, normal·luz), con ambos vectores normalizados.
     */
    public static double iluminacionDifusa(double[] normal, double[] dirLuz) {
        // GUÍA: teoría 4 (modelo de Lambert: una cara de frente a la luz brilla; de espaldas, nada).
        // 1. Si normal o dirLuz no miden 3 -> -1.
        // 2. Normaliza ambos (usa Ej345.normalizar) y calcula su producto escalar (Ej345.productoEscalar).
        // 3. Devuelve Math.max(0, dot) (un dot negativo = cara de espaldas = sin luz).
        // OJO: el test: normal {0,0,1}, luz {0,0,1} -> 1 (de frente); luz {0,0,-1} -> 0 (de espaldas).
        // CULTURA: PhongMaterial combina esta componente difusa con la especular (brillo) y la ambiente.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para iluminacionDifusa");
    }
}
