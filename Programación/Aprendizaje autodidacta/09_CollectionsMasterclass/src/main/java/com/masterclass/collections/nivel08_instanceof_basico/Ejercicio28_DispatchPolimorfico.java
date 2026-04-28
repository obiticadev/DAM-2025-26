package com.masterclass.collections.nivel08_instanceof_basico;

import com.masterclass.collections.modelos.Empleado;
import com.masterclass.collections.modelos.Producto;
import com.masterclass.collections.modelos.Pedido;
import com.masterclass.collections.modelos.Evento;
import com.masterclass.collections.modelos.interfaces.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * EJERCICIO 28 — instanceof: Dispatch Polimórfico y Clasificación
 * =================================================================
 * Teoría de referencia: teoria/06_instanceof_Interfaces_Polimorfismo.md  (§ 4, 5)
 *
 * Objetivo: Usar instanceof como mecanismo de dispatch para procesar
 * colecciones heterogéneas de forma diferenciada según el tipo de interfaz.
 *
 * Restricción: Nunca uses getClass() para estas comprobaciones. Usa instanceof.
 */
public class Ejercicio28_DispatchPolimorfico {

    // TODO 1: Implementa `separarPorInterfaz`.
    //   - Recibe un ArrayList<Object>.
    //   - Retorna un HashMap<String, ArrayList<Object>> con las claves:
    //     "identificables", "auditables", "procesables", "clasificables", "desconocidos"
    //   - Un mismo objeto puede aparecer en VARIAS listas si implementa varias interfaces.
    //   - Si no implementa ninguna de las 4, va a "desconocidos".
    //   - Todas las claves deben existir (listas vacías si no hay objetos de ese tipo).
    public static HashMap<String, ArrayList<Object>> separarPorInterfaz(ArrayList<Object> lista) {
        return null;
    }

    // TODO 2: Implementa `procesarTodos`.
    //   - Recibe un ArrayList<Object>.
    //   - Llama a procesar() en todos los que implementen Procesable.
    //   - Retorna cuántos objetos fueron procesados.
    public static int procesarTodos(ArrayList<Object> lista) {
        return -1;
    }

    // TODO 3: Implementa `obtenerResumenAuditoria`.
    //   - Recibe un ArrayList<Object>.
    //   - Para cada Auditable, genera un String:
    //     "AUDIT: creado_por={creadoPor}, fecha={fechaCreacion}"
    //   - Retorna un ArrayList<String> con los resúmenes.
    //   - Para los no Auditables, no añade nada.
    public static ArrayList<String> obtenerResumenAuditoria(ArrayList<Object> lista) {
        return null;
    }

    // TODO 4: Implementa `obtenerCategoriasUnicas`.
    //   - Recibe un ArrayList<Object>.
    //   - Retorna un ArrayList<String> con las categorías únicas de los Clasificables.
    //   - Usa un HashSet interno para asegurar unicidad, luego convierte a ArrayList.
    public static ArrayList<String> obtenerCategoriasUnicas(ArrayList<Object> lista) {
        return null;
    }

    // TODO 5: Implementa `sumarSalariosTotales`.
    //   - Recibe un ArrayList<Object>.
    //   - Para cada objeto que sea instanceof Empleado (la clase concreta),
    //     suma su getSalario().
    //   - Retorna la suma total.
    //   - Si no hay empleados, retorna 0.0.
    public static double sumarSalariosTotales(ArrayList<Object> lista) {
        return 0.0;
    }

    // TODO 6: Implementa `generarInformeCompleto`.
    //   - Recibe un ArrayList<Object>.
    //   - Retorna un HashMap<String, Object> con:
    //     "total_objetos"      → Integer (tamaño de la lista)
    //     "identificables"     → Integer (cuántos implementan Identificable)
    //     "auditables"         → Integer (cuántos implementan Auditable)
    //     "procesables"        → Integer (cuántos implementan Procesable)
    //     "clasificables"      → Integer (cuántos implementan Clasificable)
    //     "suma_salarios"      → Double (suma de salarios de los Empleados)
    public static HashMap<String, Object> generarInformeCompleto(ArrayList<Object> lista) {
        return null;
    }


    // ======================================================================
    //  ZONA DE EJECUCIÓN MASTER — Pulsa ▶ Run
    // ======================================================================
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 28 — Dispatch Polimórfico ===\n");

        ArrayList<Object> lista = new ArrayList<>();
        lista.add(new Empleado("E01", "Ana", "IT", 45000, "admin"));
        lista.add(new Producto("P01", "Teclado", 45.0, "periféricos", "input"));
        lista.add(new Pedido("D01", "C01", 150.0, 3));
        lista.add(new Evento("EV01", "ERROR", "Fallo", 1, "sistema"));
        lista.add("texto plano");
        lista.add(42);

        HashMap<String, ArrayList<Object>> separados = separarPorInterfaz(lista);
        separados.forEach((k, v) -> System.out.println(k + " -> " + v.size()));

        System.out.println("\nProcesados: " + procesarTodos(lista));
        System.out.println("Auditorías: " + obtenerResumenAuditoria(lista));
        System.out.println("Categorías únicas: " + obtenerCategoriasUnicas(lista));
        System.out.println("Suma salarios: " + sumarSalariosTotales(lista));
        System.out.println("Informe: " + generarInformeCompleto(lista));
    }
}
