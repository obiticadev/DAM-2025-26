package com.masterclass.api.b42_mobile;

/**
 * Ejercicio 329 · Sensores: modelo de lectura y filtrado (acelerómetro / GPS / luz).
 *
 * <p>Teoría: {@code teoria/42_Movil_Android.md} (sección 5).
 *
 * <p>Un sensor (acelerómetro, giroscopio, luz, GPS) entrega un torrente de muestras
 * <strong>ruidosas</strong>: si las usas crudas, la UI tiembla. La clave del trabajo con sensores
 * NO es leerlos (eso lo hace Android por ti con un {@code SensorEventListener}), sino
 * <strong>filtrar</strong>: media móvil para suavizar, filtro de paso bajo, umbrales para detectar
 * un gesto (una "sacudida"), y la magnitud del vector de aceleración para separar el movimiento de
 * la gravedad. Todo es matemática pura sobre {@code double[]}: se prueba con JUnit sin sensores.
 */
public final class Ej329SensorsModel {

    private Ej329SensorsModel() {
    }

    /**
     * Suaviza una señal con una media móvil de ventana {@code ventana} (cada salida es el promedio
     * de la muestra actual y las {@code ventana-1} anteriores).
     *
     * @param muestras señal cruda del sensor
     * @param ventana  tamaño de la ventana (si es &lt; 1 se trata como 1)
     * @return señal suavizada del MISMO tamaño; arreglo vacío si {@code muestras} es null/vacío
     */
    public static double[] filtrarLecturaSensor(double[] muestras, int ventana) {
        // TODO 1: si muestras es null o de longitud 0 -> devuelve new double[0].
        // TODO 2: si ventana < 1, trátala como 1 (una media de 1 muestra es la propia muestra).
        // TODO 3: crea el arreglo resultado del mismo tamaño que muestras.
        // TODO 4: para cada índice i, calcula el inicio de la ventana: inicio = Math.max(0, i - ventana + 1).
        // TODO 5: suma muestras[inicio..i] y divide entre el nº de elementos sumados (i - inicio + 1).
        // TODO 6: guarda ese promedio en resultado[i] y, al terminar el bucle, devuelve resultado.
        return new double[0];
    }

    /**
     * Magnitud del vector de aceleración 3D (cuánta fuerza total siente el dispositivo).
     *
     * @return raíz cuadrada de x²+y²+z²; en reposo sobre una mesa ronda 9.81 (la gravedad)
     */
    public static double magnitud(double x, double y, double z) {
        // TODO 7: eleva cada componente al cuadrado (x*x, y*y, z*z).
        // TODO 8: súmalas para obtener el cuadrado de la magnitud.
        // TODO 9: aplica Math.sqrt a esa suma (Math.sqrt(x*x+y*y+z*z); o usa la idea de hypot).
        // TODO 10: devuelve el resultado (con (3,4,0) debe dar 5.0; en reposo (0,0,9.81) -> 9.81).
        return -1;
    }

    public static void main(String[] args) {
        double[] suave = filtrarLecturaSensor(new double[]{2, 4, 6}, 2);
        System.out.print("Media móvil: ");
        for (double v : suave) System.out.print(v + " ");
        System.out.println("\nMagnitud (3,4,0) = " + magnitud(3, 4, 0));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Media aritmética de las muestras.
     */
    public static double media(double[] muestras) {
        // GUÍA: teoría 5.3 (la media es el suavizado más bruto: aplana toda la señal a un número).
        // 1. null o vacío -> 0.
        // 2. suma todas las muestras y divide entre su número.
        // PISTA: un bucle acumulador, o java.util.Arrays.stream(m).average().orElse(0).
        // OJO: el test: {2,4,6} -> 4.0; arreglo vacío -> 0 (no dividir por cero).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para media");
    }

    /**
     * Reto Extra 2: ¿La lectura supera un umbral en valor absoluto? (detección de sacudida).
     */
    public static boolean superaUmbral(double valor, double umbral) {
        // GUÍA: teoría 5.4 (una "sacudida" se detecta cuando |aceleración| pasa de un umbral).
        // 1. devuelve Math.abs(valor) > umbral.
        // OJO: el test: (12, 9.8) -> true; (5, 9.8) -> false; (-12, 9.8) -> true (¡valor absoluto!).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para superaUmbral");
    }

    /**
     * Reto Extra 3: Filtro de paso bajo de un polo (suavizado incremental, sin guardar histórico).
     */
    public static double filtroPasoBajo(double anterior, double actual, double alfa) {
        // GUÍA: teoría 5.5 (alfa pondera: alfa alto = sigue rápido la señal; alfa bajo = más suave/lento).
        // 1. devuelve alfa*actual + (1-alfa)*anterior.
        // OJO: el test: (0, 10, 0.5) -> 5.0; (0, 10, 1.0) -> 10.0 (alfa 1 = sin suavizado).
        // CULTURA: es la misma idea que una media móvil exponencial; en streams (b01) sería un reduce
        //   que acumula estado. Android lo usa para separar gravedad (paso bajo) de movimiento (paso alto).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para filtroPasoBajo");
    }

    /**
     * Reto Extra 4: Aceleración lineal = magnitud menos la gravedad (≈9.81 m/s²).
     */
    public static double aceleracionLineal(double magnitud) {
        // GUÍA: teoría 5.6 (el acelerómetro SIEMPRE mide gravedad + movimiento; réstala para el movimiento).
        // 1. devuelve magnitud - 9.81.
        // OJO: el test: 9.81 -> 0.0 (reposo: sin movimiento real); compara con delta.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para aceleracionLineal");
    }

    /**
     * Reto Extra 5: ¿Está el dispositivo en reposo? (magnitud cerca de la gravedad, ±epsilon).
     */
    public static boolean enReposo(double magnitud, double epsilon) {
        // GUÍA: teoría 5.6 (en reposo, |magnitud - 9.81| es minúscula; en movimiento se dispara).
        // 1. devuelve Math.abs(magnitud - 9.81) <= epsilon.
        // OJO: el test: (9.81, 0.1) -> true; (12.0, 0.1) -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para enReposo");
    }

    /**
     * Reto Extra 6: ¿La lectura está dentro del rango físico válido del sensor?
     */
    public static boolean lecturaValida(double valor, double min, double max) {
        // GUÍA: teoría 5.2 (cada sensor tiene un rango; fuera de él es ruido/error a descartar).
        // 1. devuelve valor >= min && valor <= max.
        // OJO: el test: (50, 0, 100) -> true; (150, 0, 100) -> false.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para lecturaValida");
    }

    /**
     * Reto Extra 7: Normaliza una lectura a [0,1] respecto a un máximo (p.ej. brillo lux→0..1).
     */
    public static double normalizar(double valor, double maximo) {
        // GUÍA: teoría 5.7 (para mapear un sensor a un control —brillo de pantalla— se normaliza a [0,1]).
        // 1. Si maximo <= 0 -> 0 (evita dividir por cero).
        // 2. Calcula valor/maximo y RECÓRTALO a [0,1] con Math.max(0, Math.min(1, ...)).
        // PISTA: Math.max(0.0, Math.min(1.0, valor / maximo)).
        // OJO: el test: (50, 100) -> 0.5; (150, 100) -> 1.0 (se satura, no pasa de 1).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para normalizar");
    }

    /**
     * Reto Extra 8: Cuenta picos por encima de un umbral (modelo de "pasos" del podómetro).
     */
    public static int contarPicos(double[] magnitudes, double umbral) {
        // GUÍA: teoría 5.8 (un paso es un MÁXIMO LOCAL que supera el umbral; no basta con "pasar el umbral").
        // 1. null o longitud < 3 -> 0.
        // 2. recorre i de 1 a n-2; cuenta i si magnitudes[i] > umbral Y es mayor que sus dos vecinos.
        // PISTA: m[i] > umbral && m[i] > m[i-1] && m[i] > m[i+1].
        // OJO: el test: {0,12,0,11,0} con umbral 9.8 -> 2 picos (los dos máximos locales).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para contarPicos");
    }

    /**
     * Reto Extra 9: Convierte grados a radianes (las APIs de orientación trabajan en radianes).
     */
    public static double gradosARadianes(double grados) {
        // GUÍA: teoría 5.9 (la rotación del giroscopio y atan2 usan radianes; el usuario piensa en grados).
        // 1. devuelve grados * Math.PI / 180.0.
        // OJO: el test: 180 -> Math.PI (compara con delta).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para gradosARadianes");
    }

    /**
     * Reto Extra 10: Eje dominante de la gravedad (sobre qué cara reposa el dispositivo).
     */
    public static String ejeDominante(double x, double y, double z) {
        // GUÍA: teoría 5.10 (el eje con MAYOR valor absoluto indica cómo está apoyado el móvil).
        // 1. compara |x|, |y|, |z| y devuelve "X", "Y" o "Z" según cuál sea el mayor.
        // 2. en empates, prioriza X, luego Y (basta con comparaciones >).
        // PISTA: usa Math.abs en cada uno y un par de if encadenados.
        // OJO: el test: (1, 9.8, 2) -> "Y" (boca arriba sobre la mesa: la gravedad cae en Y).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ejeDominante");
    }
}
