package com.masterclass.collections.nivel08_instanceof_basico;

import com.masterclass.collections.modelos.Empleado;
import com.masterclass.collections.modelos.Producto;
import com.masterclass.collections.modelos.Pedido;
import com.masterclass.collections.modelos.Evento;
import com.masterclass.collections.modelos.interfaces.Identificable;

import java.util.ArrayList;

/**
 * EJERCICIO 26 — instanceof Básico: Comprobación de Interfaces
 * ==============================================================
 * Teoría de referencia: teoria/06_instanceof_Interfaces_Polimorfismo.md  (§ 1–3)
 *
 * Objetivo: Familiarizarse con instanceof para comprobar si un Object
 * implementa una interfaz concreta (Identificable, Auditable, etc.).
 *
 * Restricción: Usa pattern matching (Java 16+) en todos los checks:
 *              if (obj instanceof Tipo variable) { ... }
 */
public class Ejercicio26_InstanceofBasico {

    // TODO 1: Implementa `esIdentificable`.
    //   - Recibe un Object.
    //   - Retorna true si el objeto implementa la interfaz Identificable, false en caso contrario.
    //   - Usa instanceof.
    //   - Recuerda: null instanceof X es siempre false.
    public static boolean esIdentificable(Object obj) {
        return false;
    }

    // TODO 2: Implementa `esAuditable`.
    //   - Recibe un Object.
    //   - Retorna true si el objeto implementa la interfaz Auditable.
    public static boolean esAuditable(Object obj) {
        return false;
    }

    // TODO 3: Implementa `esProcesable`.
    //   - Recibe un Object.
    //   - Retorna true si el objeto implementa la interfaz Procesable.
    public static boolean esProcesable(Object obj) {
        return false;
    }

    // TODO 4: Implementa `esClasificable`.
    //   - Recibe un Object.
    //   - Retorna true si el objeto implementa la interfaz Clasificable.
    public static boolean esClasificable(Object obj) {
        return false;
    }

    // TODO 5: Implementa `obtenerIdSiIdentificable`.
    //   - Recibe un Object.
    //   - Si el objeto implementa Identificable, retorna su getId().
    //   - Si no, retorna "NO_IDENTIFICABLE".
    //   - Usa pattern matching: if (obj instanceof Identificable id) { ... }
    public static String obtenerIdSiIdentificable(Object obj) {
        return null;
    }

    // TODO 6: Implementa `contarIdentificablesEnLista`.
    //   - Recibe un ArrayList<Object> con objetos heterogéneos.
    //   - Retorna cuántos de ellos implementan Identificable.
    //   - Itera con for-each y usa instanceof.
    public static int contarIdentificablesEnLista(ArrayList<Object> lista) {
        return -1;
    }

    // TODO 7: Implementa `obtenerNombresDeIdentificables`.
    //   - Recibe un ArrayList<Object>.
    //   - Retorna un ArrayList<String> con los getNombre() de todos los que
    //     implementen Identificable.
    //   - Usa pattern matching para el cast.
    public static ArrayList<String> obtenerNombresDeIdentificables(ArrayList<Object> lista) {
        return null;
    }


    // ======================================================================
    //  ZONA DE EJECUCIÓN MASTER — Pulsa ▶ Run
    // ======================================================================
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 26 — instanceof Básico ===\n");

        Empleado emp = new Empleado("E01", "Ana", "IT", 45000, "admin");
        Producto prod = new Producto("P01", "Teclado", 45.0, "periféricos", "input");
        Pedido ped = new Pedido("D01", "C01", 150.0, 3);
        Evento ev = new Evento("EV01", "ERROR", "Fallo en servidor", 1, "sistema");
        String noEntidad = "Hola";

        System.out.println("Empleado es Identificable: " + esIdentificable(emp));
        System.out.println("Empleado es Auditable: " + esAuditable(emp));
        System.out.println("Empleado es Procesable: " + esProcesable(emp));
        System.out.println("Producto es Clasificable: " + esClasificable(prod));
        System.out.println("String es Identificable: " + esIdentificable(noEntidad));
        System.out.println("null es Identificable: " + esIdentificable(null));

        System.out.println("\nId de Empleado: " + obtenerIdSiIdentificable(emp));
        System.out.println("Id de String: " + obtenerIdSiIdentificable(noEntidad));

        ArrayList<Object> lista = new ArrayList<>();
        lista.add(emp); lista.add(prod); lista.add(ped); lista.add(ev); lista.add(noEntidad);
        System.out.println("\nIdentificables en lista: " + contarIdentificablesEnLista(lista));
        System.out.println("Nombres: " + obtenerNombresDeIdentificables(lista));
    }
}
