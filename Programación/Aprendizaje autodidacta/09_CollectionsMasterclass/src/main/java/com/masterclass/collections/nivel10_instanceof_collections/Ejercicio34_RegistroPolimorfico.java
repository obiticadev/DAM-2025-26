package com.masterclass.collections.nivel10_instanceof_collections;

import com.masterclass.collections.modelos.*;
import com.masterclass.collections.modelos.interfaces.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

/**
 * EJERCICIO 34 — instanceof + Collections: Registro Polimórfico
 * ===============================================================
 * Teoría de referencia: teoria/06_instanceof_Interfaces_Polimorfismo.md  (§ 5)
 *
 * Objetivo: Combinar instanceof con HashMap, HashSet y ArrayList para construir
 * un sistema de registro que indexa, agrupa y consulta objetos heterogéneos
 * de forma eficiente.
 *
 * Restricción: Usa pattern matching en todo check de tipo.
 */
public class Ejercicio34_RegistroPolimorfico {

    // TODO 1: Implementa `registrar`.
    //   - Recibe un HashMap<String, Object> (el registro) y un Object a registrar.
    //   - Si el objeto es Identificable, lo registra con clave getId().
    //   - Si no es Identificable, lo registra con clave "ANON-{indice}"
    //     donde {indice} es el número actual de entradas con prefijo "ANON-"
    //     en el registro.
    //   - Retorna la clave con la que fue registrado.
    public static String registrar(HashMap<String, Object> registro, Object obj) {
        return null;
    }

    // TODO 2: Implementa `buscarPorId`.
    //   - Busca en el registro un objeto con la clave dada.
    //   - Si existe, retorna el objeto.
    //   - Si no, retorna null.
    public static Object buscarPorId(HashMap<String, Object> registro, String id) {
        return null;
    }

    // TODO 3: Implementa `listarPorInterfaz`.
    //   - Recibe el registro y un nombre de interfaz:
    //     "Identificable", "Auditable", "Clasificable", "Procesable"
    //   - Retorna un ArrayList<String> con las CLAVES del registro cuyos objetos
    //     implementan esa interfaz.
    //   - Si la interfaz no es válida, retorna lista vacía.
    public static ArrayList<String> listarPorInterfaz(HashMap<String, Object> registro,
                                                       String interfaz) {
        return null;
    }

    // TODO 4: Implementa `agruparPorNumeroDeInterfaces`.
    //   - Recibe el registro.
    //   - Retorna un HashMap<Integer, ArrayList<String>> donde la clave es el
    //     número de interfaces (de las 4 conocidas) que implementa el objeto
    //     y el valor es la lista de claves del registro.
    //   - Ejemplo: {2 → ["E01", "P01"], 0 → ["ANON-0"]}
    public static HashMap<Integer, ArrayList<String>> agruparPorNumeroDeInterfaces(
            HashMap<String, Object> registro) {
        return null;
    }

    // TODO 5: Implementa `crearIndiceInvertido`.
    //   - Recibe el registro.
    //   - Retorna un HashMap<String, HashSet<String>> donde:
    //     clave = nombre de interfaz ("Identificable", "Auditable", etc.)
    //     valor = HashSet con las claves del registro que implementan esa interfaz
    //   - Las 4 interfaces siempre están presentes como claves (con sets vacíos si no hay).
    public static HashMap<String, HashSet<String>> crearIndiceInvertido(
            HashMap<String, Object> registro) {
        return null;
    }

    // TODO 6 (desafío): Implementa `busquedaAvanzada`.
    //   - Recibe el registro y un String[] con nombres de interfaces requeridas.
    //   - Retorna un ArrayList<String> con las claves del registro cuyos objetos
    //     implementan TODAS las interfaces indicadas.
    //   - Si el array de interfaces está vacío, retorna todas las claves.
    //   - Usa crearIndiceInvertido() e intersecta los sets correspondientes.
    public static ArrayList<String> busquedaAvanzada(HashMap<String, Object> registro,
                                                      String[] interfacesRequeridas) {
        return null;
    }


    // ======================================================================
    //  ZONA DE EJECUCIÓN MASTER — Pulsa ▶ Run
    // ======================================================================
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 34 — Registro Polimórfico ===\n");

        HashMap<String, Object> registro = new HashMap<>();
        registrar(registro, new Empleado("E01", "Ana", "IT", 45000, "admin"));
        registrar(registro, new Producto("P01", "Teclado", 45.0, "peri", "input"));
        registrar(registro, new Pedido("D01", "C01", 150.0, 3));
        registrar(registro, new Evento("EV01", "ERROR", "Fallo", 1, "sistema"));
        registrar(registro, "texto plano");

        System.out.println("Registro: " + registro.keySet());
        System.out.println("Buscar E01: " + buscarPorId(registro, "E01"));
        System.out.println("Auditables: " + listarPorInterfaz(registro, "Auditable"));

        System.out.println("\nPor nº interfaces: " +
                agruparPorNumeroDeInterfaces(registro));
        System.out.println("Índice invertido: " + crearIndiceInvertido(registro));
        System.out.println("Búsqueda [Identif+Audit]: " +
                busquedaAvanzada(registro, new String[]{"Identificable", "Auditable"}));
    }
}
