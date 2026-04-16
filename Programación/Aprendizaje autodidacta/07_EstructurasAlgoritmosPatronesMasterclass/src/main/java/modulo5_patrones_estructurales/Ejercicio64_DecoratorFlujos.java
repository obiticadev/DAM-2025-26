package modulo5_patrones_estructurales;

/**
 * ============================================================================
 * EJERCICIO 64: Decorator — Flujo de Datos (Cifrado + Compresión)
 * ============================================================================
 * 📚 Teoría: teoria/05_Patrones_Estructurales.md - Sección 5.3
 *
 * CONTEXTO:
 * Un sistema de procesamiento de datos aplica transformaciones en cadena:
 * cifrado, compresión, codificación Base64, logging. Cada transformación
 * es un decorador que envuelve al anterior.
 *
 * Esto es EXACTAMENTE como Java I/O funciona internamente:
 * new BufferedReader(new InputStreamReader(new FileInputStream("file.txt")))
 *
 * Implementa:
 * - Interfaz DataSource: escribir(String dato), String leer().
 * - FileDataSource: lee/escribe de un "archivo" simulado (String interno).
 * - CifradoDecorator: cifra/descifra con Caesar cipher simple.
 * - CompresionDecorator: simula compresión (elimina espacios duplicados).
 * - LoggingDecorator: imprime un log antes/después de cada operación.
 *
 * RESTRICCIONES:
 * - Caesar cipher: desplazar cada carácter N posiciones.
 * - Compresión simulada: reemplazar espacios dobles con uno, trimear.
 * - El orden de los decoradores IMPORTA:
 *   escribir con [Logging → Cifrado → Compresión → File]
 *   leer con [File → Compresión → Cifrado → Logging] (inverso).
 * - Sin java.util.
 * ============================================================================
 */
public class Ejercicio64_DecoratorFlujos {

    // TODO 1: Definir interfaz DataSource:
    //         void escribir(String datos)
    //         String leer()

    // TODO 2: Implementar FileDataSource (componente base):
    //         - Campo String interno que simula un archivo.
    //         - escribir(): almacena el string.
    //         - leer(): retorna el string almacenado.

    // TODO 3: Implementar clase abstracta DataSourceDecorator:
    //         - Implementa DataSource.
    //         - Campo DataSource envuelto.
    //         - Por defecto delega al envuelto.

    // TODO 4: Implementar CifradoDecorator:
    //         - escribir(): cifra el dato con Caesar cipher (shift=3) y delega.
    //         - leer(): lee del envuelto y descifra (shift=-3).
    //         - Método privado cifrar(String dato, int shift) que desplaza cada char.

    // TODO 5: Implementar CompresionDecorator:
    //         - escribir(): "comprime" eliminando vocales (simulación) y delega.
    //         - leer(): lee del envuelto (no se puede descomprimir vocales, es demo).
    //         Alternativa: compresión= eliminar espacios duplicados.

    // TODO 6: Implementar LoggingDecorator:
    //         - escribir(): imprime "[LOG] Escribiendo X bytes..." y delega.
    //         - leer(): delega, imprime "[LOG] Leídos X bytes..." y retorna.

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 64: Decorator Flujos ===\n");

        // TODO 7: Demostrar la cadena de decoradores:
        //
        //         // Sin decoradores
        //         DataSource simple = new FileDataSource();
        //         simple.escribir("Hola Mundo Secreto");
        //         System.out.println("Simple: " + simple.leer());
        //
        //         // Con cifrado
        //         DataSource cifrado = new CifradoDecorator(new FileDataSource());
        //         cifrado.escribir("Hola Mundo Secreto");
        //         System.out.println("Cifrado: " + new FileDataSource internal read);
        //         System.out.println("Descifrado: " + cifrado.leer());
        //
        //         // Cadena completa: Logging → Cifrado → File
        //         DataSource completo = new LoggingDecorator(
        //                                new CifradoDecorator(
        //                                  new FileDataSource()));
        //         completo.escribir("Datos súper secretos del proyecto");
        //         String resultado = completo.leer();
        //         System.out.println("Resultado final: " + resultado);

        System.out.println("(Implementa los TODOs para ver resultados)");
        System.out.println("\n=== FIN EJERCICIO 64 ===");
    }
}
