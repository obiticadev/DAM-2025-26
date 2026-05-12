// Paquete al que pertenece esta clase
package gestorarchivos;

// Importaciones necesarias
import java.util.List;  // Para trabajar con listas
import java.util.Map;   // Para trabajar con mapas (pares clave-valor)

/**
 * Clase principal que contiene el método main.
 * Este programa permite:
 * - Leer nombres de archivos desde un directorio
 * - Guardarlos en un archivo binario
 * - Leerlos de nuevo
 * - Procesarlos para contar letras, eliminar duplicados y ordenarlos
 * - Mostrar los resultados por consola
 */
public class Main {
    public static void main(String[] args) {

        // Paso 1: Obtener la lista de nombres de archivos sin extensión desde un directorio
        List<String> nombres = GestorDirectorio.obtenerNombresSinExtension();

        // Paso 2: Guardar esa lista en un archivo binario (serialización de objetos)
        GestorFicheroBinario.guardarLista(nombres, "nombres.bin");

        // Paso 3: Leer nuevamente la lista desde el archivo binario (deserialización)
        List<String> nombresLeidos = GestorFicheroBinario.leerLista("nombres.bin");

        // Paso 4: Procesar la lista:
        // - contar cuántas letras tiene cada nombre (ignorando espacios y símbolos)
        // - eliminar duplicados
        // - ordenar los resultados de mayor a menor cantidad de letras
        Map<String, Integer> resultado = ProcesadorNombres.procesar(nombresLeidos);

        // Paso 5: Mostrar por pantalla los nombres y el número de letras correspondientes
        System.out.println("\nNombres de archivos y número de letras:");
        resultado.forEach((nombre, letras) -> System.out.println(nombre + ", " + letras));
    }
}

