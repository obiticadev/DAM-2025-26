package com.masterclass.api.b44_nui;

/**
 * Punto 2D compartido por los ejercicios de gestos (Ej340), keypoints del cuerpo (Ej341) y
 * geometría de marcadores (Ej343).
 *
 * <p>Es un {@code record}: inmutable y con {@code equals}/{@code hashCode} por valor (de ahí que
 * los tests puedan comparar puntos directamente). La 3.ª dimensión, cuando hace falta (RA, 3D),
 * se modela con {@code double[]} de longitud 3, no con este tipo.
 *
 * <p>Teoría: {@code teoria/44_Interfaces_Naturales.md} (sección 4 y 5).
 */
public record Punto(double x, double y) {
}
