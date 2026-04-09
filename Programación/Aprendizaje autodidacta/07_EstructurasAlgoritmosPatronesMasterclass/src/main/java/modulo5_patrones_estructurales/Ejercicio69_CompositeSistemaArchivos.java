package modulo5_patrones_estructurales;

/**
 * ============================================================================
 * EJERCICIO 69: Composite — Sistema de Archivos (Árbol)
 * ============================================================================
 * 📚 Teoría: teoria/05_Patrones_Estructurales.md - Sección 5.6
 *
 * CONTEXTO:
 * Un sistema de archivos es un ejemplo perfecto de Composite: los archivos
 * son hojas, las carpetas son composites que contienen archivos y/o
 * más carpetas. Ambos se tratan uniformemente como "elementos del FS".
 *
 * Implementa:
 * - Interfaz ElementoFS: getNombre(), getTamano(), mostrar(int indent).
 * - Clase Archivo (Leaf): tiene nombre y tamaño fijo.
 * - Clase Carpeta (Composite): contiene ElementoFS[] hijos.
 *   Su tamaño es la SUMA de los tamaños de todos sus hijos (recursivo).
 *
 * RESTRICCIONES:
 * - Carpeta almacena hijos en un array manual (max 20).
 * - mostrar() imprime con indentación para representar jerarquía:
 *   📁 src/
 *     📁 main/
 *       📄 App.java (2048 bytes)
 *       📄 Utils.java (1024 bytes)
 *     📁 test/
 *       📄 AppTest.java (512 bytes)
 * - getTamano() en Carpeta suma recursivamente todos los hijos.
 * - Sin java.util.
 * ============================================================================
 */
public class Ejercicio69_CompositeSistemaArchivos {

    // TODO 1: Definir interfaz ElementoFS:
    //         String getNombre()
    //         long getTamano()       — bytes
    //         void mostrar(int indentacion)
    //         boolean esDirectorio()

    // TODO 2: Implementar Archivo (Leaf):
    //         - Campos: nombre, tamano.
    //         - getTamano(): retorna el tamaño.
    //         - mostrar(): imprime con indentación "📄 nombre (X bytes)"
    //         - esDirectorio(): false.

    // TODO 3: Implementar Carpeta (Composite):
    //         - Campos: nombre, ElementoFS[] hijos, int numHijos.
    //         - agregar(ElementoFS elem): añadir al array de hijos.
    //         - getTamano(): sumar recursivamente getTamano() de cada hijo.
    //         - mostrar(): imprimir "📁 nombre/" y luego mostrar cada hijo
    //           con indentación+2.
    //         - esDirectorio(): true.

    // TODO 4: Implementar buscar(String nombre) en Carpeta:
    //         Búsqueda recursiva DFS que retorna el ElementoFS encontrado o null.

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 69: Composite Sistema Archivos ===\n");

        // TODO 5: Construir la estructura:
        //         Carpeta raiz = new Carpeta("proyecto");
        //         Carpeta src = new Carpeta("src");
        //         Carpeta main = new Carpeta("main");
        //         Carpeta test = new Carpeta("test");
        //
        //         main.agregar(new Archivo("App.java", 2048));
        //         main.agregar(new Archivo("Utils.java", 1024));
        //         main.agregar(new Archivo("Config.java", 512));
        //         test.agregar(new Archivo("AppTest.java", 768));
        //         test.agregar(new Archivo("UtilsTest.java", 512));
        //
        //         src.agregar(main);
        //         src.agregar(test);
        //         raiz.agregar(src);
        //         raiz.agregar(new Archivo("README.md", 256));
        //         raiz.agregar(new Archivo("build.gradle", 128));
        //
        //         raiz.mostrar(0);
        //         System.out.println("\nTamaño total: " + raiz.getTamano() + " bytes");

        System.out.println("(Implementa los TODOs para ver resultados)");
        System.out.println("\n=== FIN EJERCICIO 69 ===");
    }
}
