package com.masterclass.collections.nivel09_instanceof_avanzado;

import com.masterclass.collections.modelos.*;
import com.masterclass.collections.modelos.interfaces.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * EJERCICIO 32 — instanceof Avanzado: Motor de Reglas por Interfaz
 * ==================================================================
 * Teoría de referencia: teoria/06_instanceof_Interfaces_Polimorfismo.md  (§ 4, 5)
 *
 * Objetivo: Implementar un motor de reglas que aplica transformaciones
 * diferentes a cada objeto según las interfaces que implementa,
 * encadenando acciones de forma acumulativa.
 *
 * Restricción: Usa pattern matching (Java 16+). Nunca uses getClass().
 */
public class Ejercicio32_MotorDeReglas {

    // TODO 1: Implementa `aplicarReglasSalario`.
    //   - Recibe un ArrayList<Object>.
    //   - Para cada objeto que sea instanceof Empleado (clase concreta):
    //     * Si el departamento es "IT", aplica un bonus del 10% (salario * 1.10).
    //     * Si el departamento es "RRHH", aplica un bonus del 5% (salario * 1.05).
    //     * Cualquier otro departamento, no modifica.
    //   - Usa setSalario() para aplicar el cambio.
    //   - Retorna el número de empleados modificados.
    public static int aplicarReglasSalario(ArrayList<Object> lista) {
        return -1;
    }

    // TODO 2: Implementa `generarEtiqueta`.
    //   - Recibe un Object y genera un String con una etiqueta descriptiva:
    //   - Construye la etiqueta así:
    //     * Empieza con "[" + nombre_clase + "] "
    //     * Si Identificable: añade "id={id} "
    //     * Si Clasificable: añade "cat={categoria} "
    //     * Si Procesable: añade "prio={prioridad} "
    //     * Si Auditable: añade "by={creadoPor} "
    //   - Retorna el String con trim() al final.
    //   - Si el objeto no implementa ninguna interfaz, retorna "[{clase}] SIN_CONTRATOS".
    public static String generarEtiqueta(Object obj) {
        return null;
    }

    // TODO 3: Implementa `generarEtiquetasMasivas`.
    //   - Recibe un ArrayList<Object>.
    //   - Retorna un ArrayList<String> llamando a generarEtiqueta() para cada objeto.
    public static ArrayList<String> generarEtiquetasMasivas(ArrayList<Object> lista) {
        return null;
    }

    // TODO 4: Implementa `crearRegistroDeCapacidades`.
    //   - Recibe un ArrayList<Object>.
    //   - Retorna un HashMap<String, ArrayList<String>> donde:
    //     clave = nombre simple de clase (getClass().getSimpleName())
    //     valor = ArrayList con los NOMBRES de las interfaces que implementa
    //             de entre: "Identificable", "Auditable", "Clasificable", "Procesable"
    //   - Si hay varios objetos de la misma clase, solo necesitas una entrada
    //     (las interfaces son las mismas para todos los de la misma clase).
    public static HashMap<String, ArrayList<String>> crearRegistroDeCapacidades(
            ArrayList<Object> lista) {
        return null;
    }

    // TODO 5: Implementa `procesarYRecopilarResultados`.
    //   - Recibe un ArrayList<Object>.
    //   - Para cada Procesable que NO esté procesado:
    //     * Llama a procesar()
    //     * Añade al resultado un String: "PROCESADO:{etiqueta}" donde etiqueta
    //       es el resultado de generarEtiqueta() del mismo objeto.
    //   - Para cada Procesable que YA estaba procesado:
    //     * Añade "YA_PROCESADO:{etiqueta}"
    //   - Los no-Procesable se ignoran.
    //   - Retorna el ArrayList<String> con los resultados.
    public static ArrayList<String> procesarYRecopilarResultados(ArrayList<Object> lista) {
        return null;
    }

    // TODO 6 (desafío): Implementa `simularPipelineDeTransformaciones`.
    //   - Recibe un ArrayList<Object> y simula un pipeline de 3 fases:
    //     FASE 1 (Validación): Filtra solo los que son Identificable. Los demás se descartan.
    //     FASE 2 (Enriquecimiento): Para los que también son Auditable,
    //             genera un String "ENRIQUECIDO:{id}:{creadoPor}".
    //             Para los que no son Auditable, genera "BASICO:{id}".
    //     FASE 3 (Procesamiento): Para los de la Fase 1 que también son Procesable,
    //             llama a procesar().
    //   - Retorna un HashMap<String, Object> con:
    //     "resultados"     → ArrayList<String> generada en Fase 2
    //     "procesados"     → Integer (cuántos fueron procesados en Fase 3)
    //     "descartados"    → Integer (cuántos no pasaron la Fase 1)
    public static HashMap<String, Object> simularPipelineDeTransformaciones(
            ArrayList<Object> lista) {
        return null;
    }


    // ======================================================================
    //  ZONA DE EJECUCIÓN MASTER — Pulsa ▶ Run
    // ======================================================================
    public static void main(String[] args) {
        System.out.println("=== Ejercicio 32 — Motor de Reglas por Interfaz ===\n");

        ArrayList<Object> lista = new ArrayList<>();
        lista.add(new Empleado("E01", "Ana",   "IT",   40000, "admin"));
        lista.add(new Empleado("E02", "Luis",  "RRHH", 35000, "admin"));
        lista.add(new Producto("P01", "Teclado", 45.0, "periféricos", "input"));
        lista.add(new Pedido("D01", "C01", 150.0, 3));
        lista.add(new Evento("EV01", "ERROR", "Fallo", 1, "sistema"));
        lista.add("texto plano");

        System.out.println("Empleados modificados: " + aplicarReglasSalario(lista));

        System.out.println("\n--- Etiquetas ---");
        generarEtiquetasMasivas(lista).forEach(System.out::println);

        System.out.println("\n--- Registro de Capacidades ---");
        crearRegistroDeCapacidades(lista).forEach(
                (k, v) -> System.out.println(k + " → " + v));

        System.out.println("\n--- Procesar y Recopilar ---");
        procesarYRecopilarResultados(lista).forEach(System.out::println);

        System.out.println("\n--- Pipeline ---");
        HashMap<String, Object> pipeline = simularPipelineDeTransformaciones(lista);
        System.out.println("Resultados: " + pipeline.get("resultados"));
        System.out.println("Procesados: " + pipeline.get("procesados"));
        System.out.println("Descartados: " + pipeline.get("descartados"));
    }
}
