package com.bootcamp.nivel3_dao;

/**
 * Ej19 — Entidad POJO
 * Teoría: teoria/03_Patron_DAO_Completo.md
 *
 * Objetivo: construir una clase entidad correcta:
 * atributos privados, constructor SIN id (lo asigna la BD),
 * getter para id, getters para el resto. Sin setters, sin lógica.
 *
 * La entidad de este ejercicio es un Videojuego con los campos:
 * id (int), titulo (String), genero (String), anio (int), precio (double).
 */
public class Ej19_EntidadPOJO {

    // TODO 1: Declara los cinco atributos privados de la clase Videojuego:
    //           private int id
    //           private String titulo
    //           private String genero
    //           private int anio
    //           private double precio

    // TODO 2: Escribe el constructor que recibe titulo, genero, anio y precio
    //         (SIN id — la BD lo asigna con AUTOINCREMENT).
    //         Asigna cada parámetro a su atributo con this.

    // TODO 3: Escribe el getter public int getId()
    // TODO 4: Escribe el getter public String getTitulo()
    // TODO 5: Escribe el getter public String getGenero()
    // TODO 6: Escribe el getter public int getAnio()
    // TODO 7: Escribe el getter public double getPrecio()

    // TODO 8: Sobreescribe toString() para que devuelva una cadena con todos los campos.
    //         Ejemplo: "1 | The Witcher 3 | RPG | 2015 | 19.99"

    // ── Zona de Ejecución Master ──────────────────────────────────────────
    public static void main(String[] args) {
        // Cuando implementes la clase aquí dentro, elimina este comentario
        // y crea objetos para verificar que compilan y funcionan:
        //
        // Videojuego j1 = new Videojuego("The Witcher 3", "RPG", 2015, 19.99);
        // System.out.println(j1);
        // System.out.println("Título: " + j1.getTitulo());
        // System.out.println("Precio: " + j1.getPrecio());
        System.out.println("Implementa la clase Videojuego dentro de este archivo.");
    }
}
