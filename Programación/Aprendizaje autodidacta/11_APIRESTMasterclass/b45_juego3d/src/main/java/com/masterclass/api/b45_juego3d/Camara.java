package com.masterclass.api.b45_juego3d;

/**
 * Cámara en perspectiva, compartida por {@code Ej347PerspectiveCameraScene}.
 *
 * <p>Modela los parámetros mínimos que necesita la proyección perspectiva: la <em>distancia
 * focal</em> (cuánto "acerca" la lente: focal grande = teleobjetivo, focal pequeña = gran angular),
 * el tamaño del <em>viewport</em> (la ventana en píxeles donde se dibuja) y los planos de recorte
 * <em>near</em>/<em>far</em> (lo más cerca y lo más lejos que la cámara ve). Es un {@code record}:
 * inmutable y con {@code equals} por valor.
 *
 * <p>Teoría: {@code teoria/45_Juegos3D_Motores.md} (sección 4).
 *
 * @param focal distancia focal (escala de la proyección)
 * @param ancho ancho del viewport en píxeles
 * @param alto  alto del viewport en píxeles
 * @param near  plano de recorte cercano (z mínima visible)
 * @param far   plano de recorte lejano (z máxima visible)
 */
public record Camara(double focal, double ancho, double alto, double near, double far) {
}
