package com.masterclass.api.b40_media;

/**
 * Ejercicio 312 · Filtros por píxel: escala de grises, brillo, umbral y convolución.
 *
 * <p>Teoría: {@code teoria/40_Multimedia.md} (sección 2).
 *
 * <p>Un filtro de imagen no es magia: es recorrer la rejilla de píxeles y calcular un valor nuevo
 * para cada uno. Aquí trabajamos sobre dos representaciones: una imagen en COLOR es un
 * {@code int[][]} de píxeles ARGB ({@code 0xAARRGGBB}); una imagen en ESCALA DE GRISES es un
 * {@code int[][]} con valores 0..255 (0 = negro, 255 = blanco). El core es pura aritmética sobre
 * arrays: se prueba con JUnit sin pantalla.
 *
 * <p>Regla grabada: al sumar o multiplicar valores de canal SIEMPRE hay que recortar (clamp) el
 * resultado al rango 0..255, porque 200 + 100 = 300 no es un color válido.
 */
public final class Ej312ImageFilters {

    private Ej312ImageFilters() {
    }

    /**
     * Convierte una imagen en color (ARGB) a escala de grises usando la fórmula de LUMINANCIA
     * percibida: {@code 0.299*R + 0.587*G + 0.114*B} (el ojo humano percibe más el verde).
     *
     * @param argb matriz de píxeles ARGB (filas x columnas)
     * @return matriz del mismo tamaño con valores 0..255; {@code new int[0][0]} sin implementar
     */
    public static int[][] aGrises(int[][] argb) {
        // TODO 1: si argb es null o está vacío (length 0), devuelve new int[0][0].
        // TODO 2: crea int[][] salida del mismo tamaño (argb.length filas, argb[0].length columnas).
        // TODO 3: recorre cada fila f y cada columna c.
        // TODO 4: extrae r,g,b del píxel: r=(p>>16)&0xFF, g=(p>>8)&0xFF, b=p&0xFF.
        // TODO 5: gris = (int) Math.round(0.299*r + 0.587*g + 0.114*b).
        // TODO 6: guarda gris en salida[f][c] y, al terminar, devuelve salida.
        //         (el test usa rojo puro 0xFFFF0000 -> round(0.299*255)=76).
        return new int[0][0];
    }

    /**
     * Ajusta el brillo de una imagen en grises sumando un delta a cada píxel, recortando a 0..255.
     *
     * @param gris  matriz en escala de grises
     * @param delta cantidad a sumar (positiva aclara, negativa oscurece)
     * @return nueva matriz con el brillo ajustado; {@code new int[0][0]} sin implementar
     */
    public static int[][] ajustarBrillo(int[][] gris, int delta) {
        // TODO 7: si gris es null o vacío, devuelve new int[0][0].
        // TODO 8: crea la matriz de salida del mismo tamaño y recorre todos los píxeles.
        // TODO 9: nuevo = gris[f][c] + delta; recórtalo a 0..255 (usa clamp del reto 3 o Math.max/min).
        // TODO 10: guarda el valor recortado y devuelve la matriz.
        //          (el test usa {{100,200}} con delta +100 -> {{200,255}}: el 200+100 satura a 255).
        return new int[0][0];
    }

    public static void main(String[] args) {
        int[][] color = {{0xFFFF0000, 0xFF00FF00}};
        int[][] g = aGrises(color);
        System.out.println("Grises: " + (g.length == 0 ? "sin implementar" : g[0][0] + ", " + g[0][1]));
        System.out.println("Brillo +50: " + java.util.Arrays.deepToString(ajustarBrillo(new int[][]{{100, 220}}, 50)));
    }

    // ---- 10 RETOS EXTRA, de lo más simple (1) a lo más avanzado (10) ----

    /**
     * Reto Extra 1: Negativo (invertir) de una imagen en grises.
     * Cada píxel v pasa a 255-v (lo claro se vuelve oscuro y viceversa).
     */
    public static int[][] invertir(int[][] gris) {
        // GUÍA: teoría 2.2 (el negativo fotográfico es la inversión más simple).
        // 1. recorre la matriz y guarda 255 - gris[f][c] en una matriz nueva.
        // OJO: el test usa {{0,255,100}} -> {{255,0,155}}. No modifiques la original.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para invertir");
    }

    /**
     * Reto Extra 2: Umbral binario (binarización).
     * Cada píxel >= umbral pasa a 255 (blanco); el resto a 0 (negro).
     */
    public static int[][] umbralBinario(int[][] gris, int umbral) {
        // GUÍA: teoría 2.3 (la binarización es el primer paso de OCR y de la detección de formas).
        // 1. recorre y asigna (gris[f][c] >= umbral) ? 255 : 0.
        // OJO: el test usa {{100,200}} con umbral 150 -> {{0,255}} (100<150 -> 0; 200>=150 -> 255).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para umbralBinario");
    }

    /**
     * Reto Extra 3: Recortar (clamp) un valor al rango válido de canal 0..255.
     */
    public static int clamp(int valor) {
        // GUÍA: teoría 2.1 (el clamp es la operación que evita "colores imposibles" tras sumar/multiplicar).
        // 1. if (valor < 0) return 0; if (valor > 255) return 255; return valor;
        // PISTA: equivale a Math.max(0, Math.min(255, valor)).
        // OJO: el test prueba 300 -> 255, -5 -> 0 y 128 -> 128.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para clamp");
    }

    /**
     * Reto Extra 4: Histograma de una imagen en grises.
     * Devuelve un {@code int[256]} donde la posición i cuenta cuántos píxeles valen i.
     */
    public static int[] histograma(int[][] gris) {
        // GUÍA: teoría 2.4 (el histograma es la base del ajuste automático de contraste y del umbral de Otsu).
        // 1. crea int[256] (todo a 0). 2. recorre la matriz y haz conteo[gris[f][c]]++.
        // 3. devuelve el array.
        // OJO: el test usa {{0,0,255}} -> conteo[0]==2 y conteo[255]==1.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para histograma");
    }

    /**
     * Reto Extra 5: Valor medio (brillo medio) de una imagen en grises.
     */
    public static double valorMedio(int[][] gris) {
        // GUÍA: teoría 2.4 (el brillo medio mide si la foto está sobre/subexpuesta).
        // 1. si la matriz está vacía, devuelve 0. 2. suma todos los valores y divide por el nº de píxeles.
        // PISTA: cuenta el total de píxeles como filas*columnas (cuidado con la división entera: usa double).
        // OJO: el test usa {{0,255}} -> 127.5. Caso límite: matriz vacía -> 0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para valorMedio");
    }

    /**
     * Reto Extra 6: Ajustar contraste alrededor del punto medio (128).
     * nuevo = clamp(round((v - 128) * factor + 128)). factor>1 aumenta contraste, &lt;1 lo reduce.
     */
    public static int[][] ajustarContraste(int[][] gris, double factor) {
        // GUÍA: teoría 2.5 (el contraste "abre o cierra" la distancia de cada píxel respecto al gris medio).
        // 1. recorre y aplica nuevo = (int) Math.round((gris[f][c]-128)*factor + 128).
        // 2. recorta con clamp (reto 3) y guárdalo en la matriz nueva.
        // OJO: el test usa {{128}} con factor 2 -> {{128}} (el punto medio no se mueve) y
        //   {{200}} con factor 1 -> {{200}} (factor 1 no cambia nada).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para ajustarContraste");
    }

    /**
     * Reto Extra 7: Tono sepia de un píxel ARGB (efecto foto antigua).
     * Mantiene el alfa y aplica la matriz sepia estándar a R,G,B, recortando a 255.
     */
    public static int aSepia(int argb) {
        // GUÍA: teoría 2.6 (sepia = mezcla ponderada de los tres canales con la matriz clásica).
        // 1. extrae a,r,g,b. 2. tr=0.393*r+0.769*g+0.189*b; tg=0.349*r+0.686*g+0.168*b; tb=0.272*r+0.534*g+0.131*b.
        // 3. recorta cada uno a 255 (Math.min(255, (int)Math.round(...))) y reempaqueta con el MISMO alfa.
        // PISTA: reutiliza la idea de empaquetar de Ej311 (a<<24)|(r<<16)|(g<<8)|b.
        // OJO: el test comprueba aSepia(0xFF000000) == 0xFF000000 (negro sigue negro) y que el alfa
        //   de 0x80FFFFFF se conserva (canal alfa == 0x80). No olvides recortar: blanco satura.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para aSepia");
    }

    /**
     * Reto Extra 8: Gris por promedio simple de un píxel ARGB.
     * (R+G+B)/3, sin ponderar canales. Más rápido pero menos fiel que la luminancia del core.
     */
    public static int grisPromedio(int argb) {
        // GUÍA: teoría 2.1 (compara este promedio plano con la luminancia ponderada del core aGrises).
        // 1. extrae r,g,b y devuelve (r+g+b)/3 (división entera).
        // OJO: el test usa rojo puro 0xFFFF0000 -> (255+0+0)/3 = 85 (frente a 76 de la luminancia).
        // CULTURA: por eso las cámaras usan luminancia y no promedio: el verde "pesa" más para el ojo.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para grisPromedio");
    }

    /**
     * Reto Extra 9: Corrección gamma de una imagen en grises.
     * nuevo = clamp(round(255 * (v/255)^gamma)). gamma&lt;1 aclara medios tonos; gamma&gt;1 los oscurece.
     */
    public static int[][] aplicarGamma(int[][] gris, double gamma) {
        // GUÍA: teoría 2.5 (la gamma corrige cómo las pantallas representan el brillo — no es lineal).
        // 1. recorre y calcula normal = gris[f][c]/255.0; nuevo = (int)Math.round(255*Math.pow(normal, gamma)).
        // 2. recorta con clamp y guárdalo.
        // OJO: el test usa {{0,255}} (gamma cualquiera deja 0 y 255 fijos) y {{128}} con gamma 1.0 -> {{128}}.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para aplicarGamma");
    }

    /**
     * Reto Extra 10: Convolución 3x3 sobre una imagen en grises (núcleo del desenfoque y los bordes).
     * Aplica un kernel 3x3 a cada píxel; en los bordes se REPLICA el píxel más cercano (clamp de
     * coordenadas) para que la salida tenga el mismo tamaño que la entrada.
     */
    public static int[][] convolucionar(int[][] gris, double[][] kernel) {
        // GUÍA: teoría 2.7 (la convolución es la operación madre: desenfoque, nitidez, Sobel, todo es un kernel).
        // 1. para cada píxel (f,c), suma sobre kf,kc de -1..1: gris[clamp(f+kf)][clamp(c+kc)] * kernel[kf+1][kc+1].
        // 2. la coordenada vecina se recorta al rango válido (replicar borde): nf = Math.max(0, Math.min(filas-1, f+kf)).
        // 3. redondea la suma a int y guárdala (sin clamp de 0..255 para este test; un kernel identidad
        //    o de media devuelve el mismo valor).
        // PISTA: el centro del kernel (kernel[1][1]) multiplica al píxel actual.
        // OJO: el test usa el kernel identidad {{0,0,0},{0,1,0},{0,0,0}} y comprueba que la imagen
        //   {{10,20,30},{40,50,60},{70,80,90}} sale IGUAL.
        // CULTURA: el mismo recorrido vecino-a-vecino lo usa la detección de bordes (Sobel) y, en otro
        //   contexto, las redes neuronales convolucionales (CNN) de visión por computador.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para convolucionar");
    }
}
