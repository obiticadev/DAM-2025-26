package com.masterclass.api.b45_juego3d;

/**
 * Caja alineada a los ejes (AABB · <em>Axis-Aligned Bounding Box</em>) en 3D, compartida por
 * {@code Ej348Collision3DAABB}.
 *
 * <p>Una AABB es el volumen de colisión más barato que existe: una caja cuyos lados son paralelos a
 * los ejes X, Y y Z. Se define solo con dos esquinas opuestas, {@code min} (la de coordenadas más
 * pequeñas) y {@code max} (la de coordenadas más grandes), ambas arreglos {@code double[3]}.
 * Comprobar si dos AABB chocan es comparar intervalos en cada eje, sin trigonometría: por eso todo
 * motor la usa como primer filtro ("broad phase") antes de colisiones caras.
 *
 * <p>Teoría: {@code teoria/45_Juegos3D_Motores.md} (sección 5).
 *
 * @param min esquina inferior {x, y, z} (coordenadas mínimas)
 * @param max esquina superior {x, y, z} (coordenadas máximas)
 */
public record Caja(double[] min, double[] max) {
}
