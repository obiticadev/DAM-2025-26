package com.masterclass.nivel1_interfaces_basicas;

import java.util.List;

/**
 * ============================================================================
 * EJERCICIO 03 — POLIMORFISMO CON INTERFACES (CON GUIA)
 * ============================================================================
 *
 * CONCEPTO CLAVE: El poder real de las interfaces es el POLIMORFISMO.
 * Puedes escribir un metodo que reciba una interfaz como parametro, y ese
 * metodo funcionara con CUALQUIER clase que la implemente, sin saber
 * cual es la clase concreta.
 *
 * public static double calcularAreaTotal(List<Figura> figuras) {
 * // Este metodo funciona con circulos, rectangulos, triangulos...
 * // cualquier Figura que le pases. No necesita saber el tipo concreto.
 * }
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
        class AreaCirculo implements Figura {

            @Override
            public double area() {
                double area = Math.PI * Math.pow(radio, 2);
                return area;
            }

            @Override
            public String nombre() {
                return "Circulo";
            }

        }
        return new AreaCirculo();
    }

    /**
     * TODO: Devuelve una Figura de tipo "Rectangulo" con area = base * altura.
     * El nombre debe ser "Rectangulo".
     */
    public static Figura crearRectangulo(double base, double altura) {
        class AreaRectangulo implements Figura {

            @Override
            public double area() {
                return base * altura;
            }

            @Override
            public String nombre() {
                return "Rectangulo";
            }

        }
        return new AreaRectangulo();
    }

    /**
     * TODO: Devuelve una Figura de tipo "Triangulo" con area = (base * altura) / 2.
     * El nombre debe ser "Triangulo".
     */
    public static Figura crearTriangulo(double base, double altura) {
        class AreaTriangulo implements Figura {

            @Override
            public double area() {
                return base * altura / 2;
            }

            @Override
            public String nombre() {
                return "Triangulo";
            }

        }
        return new AreaTriangulo();
    }

    /**
     * TODO: Recibe una lista de Figuras y devuelve la SUMA de todas sus areas.
     * Aqui esta el poder del polimorfismo: no necesitas saber si son circulos,
     * rectangulos o triangulos. Solo llamas a .area() en cada una.
     *
     * PISTA: Puedes usar un for-each o un stream.
     */
    public static double calcularAreaTotal(List<Figura> figuras) {
        return figuras.stream()
                .mapToDouble(Figura::area)
                .sum();
    }
}
