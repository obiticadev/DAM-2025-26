package com.masterclass.collections.nivel08_instanceof_basico;

import com.masterclass.collections.modelos.Empleado;
import com.masterclass.collections.modelos.Producto;
import com.masterclass.collections.modelos.Pedido;
import com.masterclass.collections.modelos.Evento;
import com.masterclass.collections.modelos.interfaces.Auditable;
import com.masterclass.collections.modelos.interfaces.Identificable;
import com.masterclass.collections.modelos.interfaces.Procesable;

import java.util.ArrayList;

/**
 * EJERCICIO 27 — instanceof: Pattern Matching Multi-Interfaz
 * ============================================================
 * Teoría de referencia: teoria/06_instanceof_Interfaces_Polimorfismo.md  (§ 4)
 *
 * Objetivo: Usar instanceof con pattern matching para realizar múltiples
 * comprobaciones de interfaz sobre el mismo objeto y extraer datos de
 * cada contrato que implemente.
 *
 * Restricción: Usa pattern matching (Java 16+) en todo momento.
 */
public class Ejercicio27_PatternMatchingMultiInterfaz {

    // TODO 1: Implementa `describirObjeto`.
    //   - Recibe un Object.
    //   - Construye un String descriptivo basado en las interfaces que implementa:
    //     * Si es Identificable: añade "ID:{id} NOMBRE:{nombre} "
    //     * Si es Auditable: añade "CREADO_POR:{creadoPor} "
    //     * Si es Procesable: añade "PRIORIDAD:{prioridad} PROCESADO:{procesado} "
    //   - Un objeto puede cumplir varias interfaces, así que el String puede
    //     contener múltiples partes.
    //   - Si no implementa ninguna, retorna "OBJETO_DESCONOCIDO".
    //   - Usa trim() antes de retornar para eliminar espacios sobrantes.
    public static String describirObjeto(Object obj) {
        return null;
    }

    // TODO 2: Implementa `procesarSiProcesable`.
    //   - Recibe un Object.
    //   - Si implementa Procesable, llama a procesar() y retorna true.
    //   - Si no, retorna false.
    public static boolean procesarSiProcesable(Object obj) {
        return false;
    }

    // TODO 3: Implementa `extraerCreadoPorSiAuditable`.
    //   - Recibe un Object.
    //   - Si implementa Auditable, retorna getCreadoPor().
    //   - Si no, retorna "N/A".
    public static String extraerCreadoPorSiAuditable(Object obj) {
        return null;
    }

    // TODO 4: Implementa `clasificarPorInterfaces`.
    //   - Recibe un ArrayList<Object>.
    //   - Retorna un ArrayList<String> donde cada elemento es una descripción:
    //     "{nombre_clase} -> [{interfaces}]"
    //   - Las interfaces se listan como: "Identificable", "Auditable", "Clasificable", "Procesable"
    //   - Ejemplo: "Empleado -> [Identificable, Auditable]"
    //   - Si el objeto no implementa ninguna interfaz conocida: "{nombre_clase} -> [ninguna]"
    //   - Usa obj.getClass().getSimpleName() para el nombre de la clase.
    public static ArrayList<String> clasificarPorInterfaces(ArrayList<Object> lista) {
        return null;
    }

    // TODO 5: Implementa `filtrarPorDobleInterfaz`.
    //   - Recibe un ArrayList<Object>.
    //   - Retorna un ArrayList<Object> con solo los objetos que implementan
    //     TANTO Identificable COMO Auditable simultáneamente.
    //   - Comprueba ambos instanceof por separado.
    public static ArrayList<Object> filtrarPorDobleInterfaz(ArrayList<Object> lista) {
        return null;
    }

    // TODO 6: Implementa `contarPorTipoDeInterfaz`.
    //   - Recibe un ArrayList<Object>.
    //   - Retorna un int[] de 4 posiciones:
    //     [0] = cuántos implementan Identificable
    //     [1] = cuántos implementan Auditable
    //     [2] = cuántos implementan Clasificable
    //     [3] = cuántos implementan Procesable
    //   - Un mismo objeto puede incrementar VARIOS contadores.
    public static int[] contarPorTipoDeInterfaz(ArrayList<Object> lista) {
        return null;
    }


    // ======================================================================
    //  ZONA DE EJECUCIÓN MASTER — Pulsa ▶ Run
    // ======================================================================
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 27 — Pattern Matching Multi-Interfaz ===\n");

        Empleado emp = new Empleado("E01", "Ana", "IT", 45000, "admin");
        Producto prod = new Producto("P01", "Teclado", 45.0, "periféricos", "input");
        Pedido ped = new Pedido("D01", "C01", 150.0, 3);
        Evento ev = new Evento("EV01", "ERROR", "Fallo", 1, "sistema");

        System.out.println("Empleado: " + describirObjeto(emp));
        System.out.println("Producto: " + describirObjeto(prod));
        System.out.println("Pedido: " + describirObjeto(ped));
        System.out.println("Evento: " + describirObjeto(ev));
        System.out.println("String: " + describirObjeto("texto"));

        System.out.println("\nProcesar Pedido: " + procesarSiProcesable(ped));
        System.out.println("Procesar String: " + procesarSiProcesable("texto"));

        System.out.println("CreadoPor de Empleado: " + extraerCreadoPorSiAuditable(emp));

        ArrayList<Object> lista = new ArrayList<>();
        lista.add(emp); lista.add(prod); lista.add(ped); lista.add(ev); lista.add("texto");

        System.out.println("\nClasificación:");
        clasificarPorInterfaces(lista).forEach(System.out::println);

        System.out.println("\nDoble interfaz (Identif + Audit): " +
                filtrarPorDobleInterfaz(lista).size());

        int[] conteo = contarPorTipoDeInterfaz(lista);
        System.out.println("\nConteo: Identif=" + conteo[0] + " Audit=" + conteo[1] +
                " Clasif=" + conteo[2] + " Proces=" + conteo[3]);
    }
}
