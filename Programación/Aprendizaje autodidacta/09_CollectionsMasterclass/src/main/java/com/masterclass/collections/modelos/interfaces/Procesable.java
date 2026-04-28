package com.masterclass.collections.modelos.interfaces;

/**
 * Contrato para entidades que pueden ser procesadas con una prioridad asignada.
 * Usada en ejercicios de instanceof, Queue y Boss Final.
 */
public interface Procesable {
    void procesar();
    int getPrioridad();
    boolean isProcesado();
}
