package com.masterclass.collections.modelos.interfaces;

/**
 * Contrato para entidades que pertenecen a una categoría y tienen un tipo.
 * Usada en ejercicios de HashMap (agrupación) e instanceof.
 */
public interface Clasificable {
    String getCategoria();
    String getTipo();
}
