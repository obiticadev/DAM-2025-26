package modulo5_patrones_estructurales;

/**
 * ============================================================================
 * EJERCICIO 72: Bridge — Formas y Renderizadores
 * ============================================================================
 * 📚 Teoría: teoria/05_Patrones_Estructurales.md - Sección 5.7
 *
 * CONTEXTO:
 * Un editor gráfico soporta formas (Círculo, Cuadrado, Triángulo)
 * y renderizadores (Vectorial, Raster). Sin Bridge: 3×2 = 6 clases.
 * Con Bridge: 3 formas + 2 renderizadores = 5 clases.
 *
 * Implementa:
 * - Interfaz Renderizador (Implementation): renderizarCirculo(r),
 *   renderizarCuadrado(lado), renderizarTriangulo(base, altura).
 * - RenderizadorVectorial: imprime ecuaciones SVG simuladas.
 * - RenderizadorRaster: imprime arte ASCII de la forma.
 * - Clase abstracta Forma (Abstraction): referencia a Renderizador.
 * - Formas concretas: Circulo, Cuadrado, Triangulo.
 *
 * RESTRICCIONES:
 * - Cada forma delega el dibujo al renderizador.
 * - Los renderizadores producen salida VISUALMENTE distinta.
 * - Sin java.util.
 * ============================================================================
 */
public class Ejercicio72_BridgeFormas {

    // TODO 1: Definir interfaz Renderizador:
    //         void renderizarCirculo(double radio)
    //         void renderizarCuadrado(double lado)
    //         void renderizarTriangulo(double base, double altura)

    // TODO 2: Implementar RenderizadorVectorial:
    //         renderizarCirculo → "[VECTOR] <circle cx='0' cy='0' r='5'/>"
    //         renderizarCuadrado → "[VECTOR] <rect width='10' height='10'/>"
    //         renderizarTriangulo → "[VECTOR] <polygon points='0,0 8,0 4,6'/>"

    // TODO 3: Implementar RenderizadorRaster:
    //         renderizarCirculo → "[RASTER] Dibujando píxeles: ○ (r=5)"
    //         renderizarCuadrado → "[RASTER] ┌──┐ │  │ └──┘"
    //         renderizarTriangulo → "[RASTER]   /\\  /  \\ /____\\"

    // TODO 4: Implementar clase abstracta Forma:
    //         Campo: Renderizador renderizador
    //         Método abstracto: void dibujar()
    //         Método: void setRenderizador(Renderizador r)

    // TODO 5: Implementar Circulo, Cuadrado, Triangulo:
    //         Cada uno almacena sus dimensiones y delega dibujar() al renderizador.
    //         Circulo.dibujar() → renderizador.renderizarCirculo(this.radio)

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 72: Bridge Formas ===\n");

        // TODO 6: Crear formas con diferentes renderizadores:
        //         Renderizador vector = new RenderizadorVectorial();
        //         Renderizador raster = new RenderizadorRaster();
        //
        //         Forma[] formas = {
        //             new Circulo(5, vector),
        //             new Cuadrado(10, vector),
        //             new Triangulo(8, 6, raster),
        //             new Circulo(3, raster)
        //         };
        //
        //         for (Forma f : formas) {
        //             f.dibujar();
        //             System.out.println("---");
        //         }
        //
        //         // Cambiar renderizador en runtime
        //         formas[0].setRenderizador(raster);
        //         formas[0].dibujar();

        System.out.println("(Implementa los TODOs para ver resultados)");
        System.out.println("\n=== FIN EJERCICIO 72 ===");
    }
}
