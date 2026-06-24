package com.masterclass.api.b44_nui;

/**
 * Ejercicio 344 · Machine Learning para la UI: features → modelo → predicción.
 *
 * <p>Detrás de cada NUI hay un clasificador: convierte señales en <em>features</em> (números),
 * las normaliza, y un modelo entrenado predice la clase (¿es este gesto un "swipe"? ¿este audio un
 * "sí"?). Aquí se construye el <strong>pipeline de inferencia</strong> con un modelo lineal/logístico
 * sencillo y sus métricas; el entrenamiento real con Weka/DL4J queda en "guion".
 *
 * <p>Teoría: {@code teoria/44_Interfaces_Naturales.md} (sección 8).
 */
public final class Ej344MlForUiPipeline {

    private Ej344MlForUiPipeline() {
    }

    /**
     * Normaliza cada feature al rango [0,1] con escalado min-max y recorte.
     *
     * @param crudo valores sin escalar
     * @param min   mínimo de cada feature (misma longitud que crudo)
     * @param max   máximo de cada feature (misma longitud que crudo)
     * @return arreglo escalado a [0,1]; {@code null} si las longitudes no coinciden
     */
    public static double[] normalizarFeatures(double[] crudo, double[] min, double[] max) {
        // TODO 1: si crudo, min o max son null o de distinta longitud -> null.
        // TODO 2: crea el arreglo resultado del mismo tamaño.
        // TODO 3: para cada i, si max[i]==min[i] -> 0 (feature constante, sin información).
        // TODO 4: si no, escala (crudo[i]-min[i])/(max[i]-min[i]) y RECÓRTALO a [0,1] (clamp).
        // TODO 5: devuelve el arreglo normalizado.
        return null;
    }

    /**
     * Predice la clase binaria con un modelo logístico: sigmoide(features·pesos + sesgo) ≥ 0.5.
     *
     * @param features vector de entrada normalizado
     * @param pesos    pesos del modelo (misma longitud que features)
     * @param sesgo    término independiente (bias)
     * @return clase 0 o 1; -1 si features y pesos no tienen la misma longitud
     */
    public static int predecir(double[] features, double[] pesos, double sesgo) {
        // TODO 6: si features o pesos son null o de distinta longitud -> -1.
        // TODO 7: calcula el producto punto z = Σ features[i]*pesos[i].
        // TODO 8: súmale el sesgo (z += sesgo).
        // TODO 9: aplica la sigmoide: s = 1 / (1 + e^(-z)).
        // TODO 10: devuelve 1 si s >= 0.5, o 0 en caso contrario.
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("Normalizar 5 en [0,10]: "
                + java.util.Arrays.toString(normalizarFeatures(new double[]{5}, new double[]{0}, new double[]{10})));
        System.out.println("Predicción de (1,1) pesos (1,1) sesgo -1: "
                + predecir(new double[]{1, 1}, new double[]{1, 1}, -1));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Producto punto de dos vectores.
     * El núcleo de cualquier modelo lineal.
     */
    public static double productoPunto(double[] a, double[] b) {
        // GUÍA: teoría 8 (z = features·pesos es un producto punto).
        // 1. Si a o b son null o de distinta longitud -> 0.
        // 2. Suma a[i]*b[i] para todo i.
        // OJO: el test: {1,2,3}·{1,1,1}->6; vectores vacíos -> 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para productoPunto");
    }

    /**
     * Reto Extra 2: Función sigmoide.
     * Aplasta cualquier número real al rango (0,1): la "probabilidad" del modelo logístico.
     */
    public static double sigmoide(double z) {
        // GUÍA: teoría 8 (sigmoide(z) = 1/(1+e^-z); en z=0 vale 0.5, el punto de decisión).
        // 1. Devuelve 1.0 / (1.0 + Math.exp(-z)).
        // OJO: el test: sigmoide(0)->0.5; con z grande positivo tiende a 1.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para sigmoide");
    }

    /**
     * Reto Extra 3: Recortar a [0,1].
     * Mantiene una probabilidad o feature dentro del rango válido.
     */
    public static double clamp01(double v) {
        // GUÍA: teoría 8 (clamp evita features fuera de rango tras escalar).
        // 1. Devuelve Math.max(0, Math.min(1, v)).
        // OJO: el test: 1.5->1.0; -0.2->0.0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para clamp01");
    }

    /**
     * Reto Extra 4: Codificación one-hot.
     * Convierte una categoría (índice) en un vector con un 1 en su posición y 0 en el resto.
     */
    public static double[] unHot(int indice, int n) {
        // GUÍA: teoría 8 (las categóricas —"gesto A/B/C"— se vuelven vectores one-hot para el modelo).
        // 1. Si n <= 0 -> new double[0].
        // 2. Crea un arreglo de n ceros; si 0<=indice<n, pon un 1 en esa posición.
        // OJO: el test: (1,3)->{0,1,0}; índice fuera de rango (5,3)->{0,0,0}.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para unHot");
    }

    /**
     * Reto Extra 5: Precisión (precision).
     * De lo que el modelo marcó como positivo, qué fracción acertó: vp/(vp+fp).
     */
    public static double precision(int verdaderosPositivos, int falsosPositivos) {
        // GUÍA: teoría 8 (precision penaliza los falsos positivos: "cuando dice sí, ¿acierta?").
        // 1. Si vp+fp == 0 -> 0 (evita dividir por cero).
        // 2. Devuelve vp / (vp + fp) como double.
        // OJO: el test: (8,2)->0.8; (0,0)->0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para precision");
    }

    /**
     * Reto Extra 6: Exhaustividad (recall).
     * De todos los positivos reales, qué fracción detectó: vp/(vp+fn).
     */
    public static double recall(int verdaderosPositivos, int falsosNegativos) {
        // GUÍA: teoría 8 (recall penaliza los falsos negativos: "de los que eran sí, ¿cuántos pilló?").
        // 1. Si vp+fn == 0 -> 0.
        // 2. Devuelve vp / (vp + fn) como double.
        // OJO: el test: (8,2)->0.8; (0,0)->0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para recall");
    }

    /**
     * Reto Extra 7: Matriz de confusión binaria.
     * Cuenta {VP, FP, FN, VN} comparando etiquetas reales y predichas (0/1).
     */
    public static int[] matrizConfusion(int[] real, int[] predicho) {
        // GUÍA: teoría 8 (la matriz de confusión es la base de precision/recall/exactitud).
        // 1. Si real o predicho son null o de distinta longitud -> new int[0].
        // 2. Recorre: real=1&pred=1->VP; real=0&pred=1->FP; real=1&pred=0->FN; real=0&pred=0->VN.
        // 3. Devuelve {VP, FP, FN, VN} en ese orden.
        // OJO: el test: real {1,0,1,0}, pred {1,1,1,0} -> {2,1,0,1}.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para matrizConfusion");
    }

    /**
     * Reto Extra 8: Exactitud (accuracy).
     * Fracción de aciertos sobre el total.
     */
    public static double exactitud(int correctos, int total) {
        // GUÍA: teoría 8 (accuracy = aciertos/total; engaña con clases desbalanceadas —ver precision/recall).
        // 1. Si total <= 0 -> 0.
        // 2. Devuelve correctos / total como double.
        // OJO: el test: (9,10)->0.9; (0,0)->0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para exactitud");
    }

    /**
     * Reto Extra 9: Actualizar un peso (online learning).
     * Regla del descenso de gradiente: peso += tasa · error · entrada.
     */
    public static double actualizarPeso(double peso, double error, double entrada, double tasa) {
        // GUÍA: teoría 8 (aprendizaje incremental: el modelo se ajusta con cada ejemplo nuevo).
        // 1. Devuelve peso + tasa * error * entrada.
        // OJO: el test: (0.5,1.0,2.0,0.1)->0.7; con tasa 0 el peso no cambia (0.5).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para actualizarPeso");
    }

    /**
     * Reto Extra 10: Exportar los pesos del modelo a texto.
     * Serializa los pesos como CSV para guardarlos/cargarlos (enlaza con b46).
     */
    public static String exportarPesos(double[] pesos) {
        // GUÍA: teoría 8 (un modelo entrenado se persiste; b46 lo envuelve en un componente serializable).
        // 1. pesos null/vacío -> "".
        // 2. Une los valores separados por comas (sin espacios).
        // PISTA: un StringBuilder o un stream con String.join sobre los valores convertidos a String.
        // OJO: el test: {1.0,2.5} -> "1.0,2.5"; arreglo vacío -> "".
        // CULTURA: persistir el modelo aquí enlaza con la serialización de componentes de b46.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para exportarPesos");
    }
}
