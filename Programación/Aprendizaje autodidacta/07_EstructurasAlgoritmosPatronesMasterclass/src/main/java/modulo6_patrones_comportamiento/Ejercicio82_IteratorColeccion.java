package modulo6_patrones_comportamiento;

/**
 * ============================================================================
 * EJERCICIO 82: Iterator — Iterador sobre Colección Manual
 * ============================================================================
 * 📚 Teoría: teoria/06_Patrones_Comportamiento.md - Sección 6.5
 *
 * CONTEXTO:
 * Tienes una colección personalizada (MiLista) y necesitas recorrerla
 * sin exponer su estructura interna. El Iterator proporciona una forma
 * uniforme de recorrer la colección.
 *
 * Implementa:
 * - Interfaz Iterador: hasNext(), next(), reset().
 * - Interfaz Iterable: crearIterador().
 * - MiLista: colección basada en array con su iterador interno.
 * - Iterador con filtro: IteradorPares (solo retorna pares).
 *
 * RESTRICCIONES:
 * - MiLista almacena ints en un array manual (max 100).
 * - El iterador mantiene la posición actual.
 * - Crear un iterador inverso (ReverseIterador) que recorre de fin a inicio.
 * - Sin java.util.Iterator ni Iterable.
 * ============================================================================
 */
public class Ejercicio82_IteratorColeccion {

    interface Iterador {
        // TODO 1: boolean hasNext(), int next(), void reset()
    }

    interface ColeccionIterable {
        // TODO 2: Iterador crearIterador()
    }

    // TODO 3: Implementar MiLista:
    //         - int[] datos, int size.
    //         - agregar(int val), get(int idx), size().
    //         - crearIterador(): retorna nuevo Iterador sobre esta lista.
    //         - crearIteradorInverso(): recorre de fin a inicio.

    // TODO 4: Implementar MiListaIterador (clase interna o separada):
    //         - Referencia a MiLista, int posicion = 0.
    //         - hasNext(): posicion < lista.size().
    //         - next(): retorna lista.get(posicion++).
    //         - reset(): posicion = 0.

    // TODO 5: Implementar IteradorPares (decorador/filtro):
    //         - Envuelve un Iterador.
    //         - next() salta los impares y retorna solo pares.

    // ========================================================================
    //  ZONA DE PRUEBAS
    // ========================================================================
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 82: Iterator Colección ===\n");

        // TODO 6:
        //         MiLista lista = new MiLista();
        //         for (int i = 1; i <= 10; i++) lista.agregar(i);
        //
        //         System.out.print("Normal: ");
        //         Iterador it = lista.crearIterador();
        //         while (it.hasNext()) System.out.print(it.next() + " ");
        //
        //         System.out.print("\nInverso: ");
        //         Iterador rev = lista.crearIteradorInverso();
        //         while (rev.hasNext()) System.out.print(rev.next() + " ");
        //
        //         System.out.print("\nSolo pares: ");
        //         Iterador pares = new IteradorPares(lista.crearIterador());
        //         while (pares.hasNext()) System.out.print(pares.next() + " ");

        System.out.println("(Implementa los TODOs para ver resultados)");
        System.out.println("\n=== FIN EJERCICIO 82 ===");
    }
}
