package com.masterclass.nivel1_interfaces_basicas;

/**
 * ============================================================================
 *  EJERCICIO 07 — CONSTANTES EN INTERFACES (CON GUIA)
 * ============================================================================
 *
 * CONCEPTO CLAVE: Todos los campos declarados en una interfaz son
 * automaticamente public, static y final (constantes).
 *
 *   public interface Config {
 *       int MAX = 100;  // Es public static final aunque no lo escribas
 *   }
 *
 * Se acceden con: Config.MAX (como cualquier constante estatica).
 *
 * NOTA: Usar interfaces solo para constantes NO es una buena practica moderna.
 * Es mejor usar una clase final con constructor privado, o un enum.
 * Pero debes ENTENDER como funcionan porque las encontraras en codigo legacy.
 *
 * Lee primero: teoria/01_Interfaces_Basicas.md
 */
public class Ejercicio07_ConstantesEnInterfaces {

    /**
     * Interfaz con constantes de configuracion de una aplicacion.
     * Todos estos campos son public static final automaticamente.
     */
    public interface ConfiguracionApp {
        int MAX_REINTENTOS = 3;
        int TIMEOUT_MS = 5000;
        String VERSION = "2.1.0";
        double FACTOR_DESCUENTO = 0.15;
    }

    /**
     * TODO: Devuelve el valor de MAX_REINTENTOS de la interfaz ConfiguracionApp.
     */
    public static int obtenerMaxReintentos() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Devuelve el TIMEOUT_MS en segundos (dividir entre 1000.0).
     */
    public static double obtenerTimeoutEnSegundos() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Dado un precio, aplica el FACTOR_DESCUENTO y devuelve el precio con descuento.
     * Formula: precio - (precio * FACTOR_DESCUENTO)
     */
    public static double aplicarDescuento(double precio) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }

    /**
     * TODO: Devuelve un String con formato:
     * "App v{VERSION} | Timeout: {TIMEOUT_MS}ms | Reintentos: {MAX_REINTENTOS}"
     */
    public static String obtenerInfoConfiguracion() {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
