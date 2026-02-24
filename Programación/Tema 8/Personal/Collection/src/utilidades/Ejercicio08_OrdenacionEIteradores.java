package utilidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * MÓDULO 5: LA CLASE COLLECTIONS Y LOS ITERADORES
 * 
 * TEORÍA CLASE COLLECTIONS:
 * `Collections` (con "s" final) es una clase "hija" que contiene EXCLUSIVAMENTE
 * MÉTODOS ESTÁTICOS.
 * Sirve para operar, ordenar o buscar dentro de colecciones instanciadas de
 * cualquier tipo
 * (Listas o Sets, principalmente Listas).
 * Puedes pensar en ella como el "cuchillo suizo" de las Colecciones.
 * 
 * TEORÍA ITERATORS (E Invalidez del For-Each):
 * Seguramente sabrás la forma en la que borramos un elemento condicionalmente
 * en un For-Each clásico:
 * for(String coche : listaCoches) { if (coche.equals("Ford"))
 * listaCoches.remove(coche); }
 * ¡ESO DA UN ERROR MASIVO EN JAVA! Lanzará ConcurrentModificationException.
 * NO puedes modificar el TAMAÑO de una colección por la que estás ciclando
 * dentro de un For-Each.
 * 
 * ¿SOLUCIÓN?
 * El `Iterator`. Es un objeto especial que recorre la colección poco a poco
 * mediante un bucle While,
 * y él es el único con "permiso" para borrar (`.remove()`) el elemento en el
 * que se encuentra aparcado
 * de de forma sumamente segura mientras recorre.
 */
public class Ejercicio08_OrdenacionEIteradores {

    public static void demostracion() {
        System.out.println("\n--- DEMOSTRACIÓN 1: CLASE UTILITIES COLLECTIONS ---");

        List<Integer> loteria = new ArrayList<>(List.of(15, 6, 88, 3, 40));
        System.out.println("Lista original (desordenada): " + loteria);

        // .sort() de la clase Collections: Las ordena de menor a mayor de inmediato
        Collections.sort(loteria);
        System.out.println("Lista tras Collections.sort(): " + loteria);

        // .reverse() revierte el orden actual que tenga la lista
        Collections.reverse(loteria);
        System.out.println("Lista tras Collections.reverse() (quedó de mayor a menor): " + loteria);

        // .shuffle() mezcla los elementos como una baraja
        Collections.shuffle(loteria);
        System.out.println("Lista tras Collections.shuffle() (mezclada aleatoriamente): " + loteria);

        System.out.println("\n--- DEMOSTRACIÓN 2: EL USO DE ITERATORS ---");
        List<String> frutas = new ArrayList<>();
        frutas.add("Manzana");
        frutas.add("Pera");
        frutas.add("Gusano"); // ¡Tenemos que eliminarlo!
        frutas.add("Fresa");

        Iterator<String> viajero = frutas.iterator(); // Creamos el iterador asociado a la colección

        // "Mientras el iterador tenga otro objeto al que saltar más adelante..."
        while (viajero.hasNext()) {
            // "Da el salto al siguiente elemento y guárdalo en variable para comprobarlo"
            String f = viajero.next();

            if (f.equals("Gusano")) {
                // AQUÍ USAMOS el .remove() DEL ITERADOR, NUNCA el frutas.remove(). ¡Ojo con el
                // detalle!
                viajero.remove();
                System.out.println("He matado al gusano de forma segura en plena iteración de memoria.");
            }
        }
        System.out.println("Frutas restantes, desinfectadas: " + frutas);
        System.out.println("--------------------------------\n");
    }

    /**
     * EJERCICIO:
     * Vas a ordenar una lista numerada de IDs caóticas primero, y luedo buscar en
     * otra lista
     * de empleados todos aquellos que se llamen "Despedido" de forma segura con un
     * Iterator.
     */
    public static void ejercicio() {
        System.out.println("\n--- EJERCICIO 8: UTILITIES E ITERATE ---");

        // PARTE 1 - Collections
        List<Integer> ids = new ArrayList<>(List.of(99, 1, 45, 2, 7));

        // TODO: Utilizando la clase estática Collections con su método sort(), ordena
        // los IDS de la variable 'ids'

        // PARTE 2 - Iterator
        List<String> plantilla = new ArrayList<>();
        plantilla.add("Alberto");
        plantilla.add("Despedido"); // ¡Echar!
        plantilla.add("Carmen");
        plantilla.add("Despedido"); // ¡Echar!

        // TODO: 1. Instancia el iterador de la plantilla (plantilla.iterator())
        // guardándolo en la variable it.
        Iterator<String> it = null;

        // TODO: 2. Haz un bucle while(it.hasNext()), almacena en un String el next()
        // del iterador.
        // Si (if) iguala (equals) a la palabra "Despedido", ejecuta it.remove()

        // --- CÓDIGO DE COMPROBACIÓN (NO MODIFICAR) ---
        if (ids.get(0) == 1 && ids.get(4) == 99 && plantilla.size() == 2 && plantilla.contains("Alberto")
                && plantilla.contains("Carmen")) {
            System.out.println(
                    ">> ¡CORRECTO! Sabes usar sort y limpiar cosas con el Iterator sin que explote. ¡NIVEL PRO!\033[0;32m [OK]\033[0m");
        } else {
            System.err.println(
                    ">> ALGO FALLA. \033[0;31m [ERROR]\033[0m Lista IDS: " + ids + " -- Plantilla: " + plantilla);
        }
        System.out.println("-------------------------------\n");
    }
}
