package com.masterclass.nivel1_interfaces_basicas;

/**
 * ============================================================================
 * EJERCICIO 01 — TU PRIMERA INTERFAZ (CON GUIA)
 * ============================================================================
 *
 * CONCEPTO CLAVE: Una interfaz es un CONTRATO. Define QUE debe hacer una clase,
 * pero no COMO lo hace. Es como un enchufe: cualquier aparato con la forma
 * correcta puede conectarse. Al enchufe no le importa si es una tostadora o un
 * PC.
 *
 * SINTAXIS:
 * public interface NombreInterfaz {
 * TipoRetorno metodo(parametros); // Sin cuerpo, solo la firma
 * }
 *
 * public class MiClase implements NombreInterfaz {
 * 
 * @Override
 *           public TipoRetorno metodo(parametros) {
 *           // Aqui va la implementacion real
 *           }
 *           }
 *
 *           Lee primero: teoria/01_Interfaces_Basicas.md
 */
public class Ejercicio01_PrimeraInterfaz {

    /**
     * Esta es la interfaz que debes implementar.
     * Tiene un unico metodo abstracto: saludar(String nombre).
     * Debe devolver un saludo con el formato: "Hola, {nombre}!"
     */
    public interface Saludable {
        String saludar(String nombre);
    }

    /**
     * TODO: Devuelve una instancia de Saludable que al llamar a saludar("Mundo")
     * retorne "Hola, Mundo!".
     *
     * PISTA: Puedes hacerlo de 3 formas (todas validas):
     *
     * 1) Clase interna:
     * class MiSaludador implements Saludable {
     * public String saludar(String nombre) { return ...; }
     * }
     * return new MiSaludador();
     *
     * 2) Clase anonima:
     * return new Saludable() {
     * public String saludar(String nombre) { return ...; }
     * };
     *
     * 3) Lambda (porque Saludable tiene un solo metodo):
     * return nombre -> ...;
     */
    public static Saludable crearSaludador() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
