package com.bootcamp.nivel13;

/**
 * EJERCICIO 13.03 — Refactor con jdtls
 *
 * TODOs (ejecuta cada uno con el cursor sobre el símbolo indicado):
 *
 *   TODO 1 (con pista): Renombra la variable 'precioOriginal' a 'precio'
 *     usando <leader>cr desde su definición. Esto actualiza la definición
 *     Y todas las referencias dentro de aplicarDescuento.
 *
 *   TODO 2 (con pista): Renombra el método 'aplicarDescuento' a 'descontar'
 *     usando <leader>cr. Cambia la definición Y la llamada en el main.
 *
 *   TODO 3 (con pista): Selecciona en Visual la expresión
 *     'precio - (precio * descuento)' (dentro del return), y pulsa <leader>cR
 *     (extract method) — pídele que extraiga un nuevo método 'calcular'.
 *     Resultado esperado: el cuerpo de descontar pasa a llamar a calcular(...).
 *
 *   TODO 4 (LIBRE): Formatea el archivo con <leader>cf (google-java-format).
 *
 *   TODO 5 (LIBRE): Guarda y sal.
 */
public class Refactor {

  public double descontar(double precio, double descuento) {
    if (descuento < 0 || descuento > 1) {
      throw new IllegalArgumentException("Descuento fuera de rango");
    }
    return calcular(precio, descuento);
  }

  private double calcular(double precio, double descuento) {
    return precio - (precio * descuento);
  }

  public static void main(String[] args) {
    Refactor r = new Refactor();
    System.out.println(r.descontar(100.0, 0.1));
    System.out.println(r.descontar(250.0, 0.25));
  }
}
