package com.masterclass.nivel1_interfaces_basicas;

import java.util.List;

/**
 * ============================================================================
 *  EJERCICIO 03 — POLIMORFISMO CON INTERFACES (CON GUIA)
 * ============================================================================
 *
 * CONCEPTO CLAVE: El poder real de las interfaces es el POLIMORFISMO.
 * Puedes escribir un metodo que reciba una interfaz como parametro, y ese
 * metodo funcionara con CUALQUIER clase que la implemente, sin saber
 * cual es la clase concreta.
 *
 *   public static double calcularAreaTotal(List<Figura> figuras) {
 *       // Este metodo funciona con circulos, rectangulos, triangulos...
 *       // cualquier Figura que le pases. No necesita saber el tipo concreto.
 *   }
 *
 * Es como un garaje que acepta "cualquier vehiculo": coches, motos, furgonetas.
 * Al garaje no le importa que tipo de vehiculo sea, solo que SEA un vehiculo.
 *
 * Lee primero: teoria/01_Interfaces_Basicas.md
 */
public class Ejercicio03_PolimorfismoConInterfaces {

    /**
     * Interfaz Figura: contrato para cualquier forma geometrica.
     */
    public interface Figura {
        double area();
        String nombre();
    }

    /**
     * TODO: Devuelve una Figura de tipo "Circulo" con el area = PI * radio * radio.
     * El nombre debe ser "Circulo".
     *
     * PISTA: Math.PI te da el valor de PI.
     */
    public static Figura crearCirculo(double radio) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Devuelve una Figura de tipo "Rectangulo" con area = base * altura.
     * El nombre debe ser "Rectangulo".
     */
    public static Figura crearRectangulo(double base, double altura) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Devuelve una Figura de tipo "Triangulo" con area = (base * altura) / 2.
     * El nombre debe ser "Triangulo".
     */
    public static Figura crearTriangulo(double base, double altura) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Recibe una lista de Figuras y devuelve la SUMA de todas sus areas.
     * Aqui esta el poder del polimorfismo: no necesitas saber si son circulos,
     * rectangulos o triangulos. Solo llamas a .area() en cada una.
     *
     * PISTA: Puedes usar un for-each o un stream.
     */
    public static double calcularAreaTotal(List<Figura> figuras) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
