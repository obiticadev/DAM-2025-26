package com.masterclass.nivel1_interfaces_basicas;

/**
 * ============================================================================
 *  EJERCICIO 05 — IMPLEMENTAR MULTIPLES INTERFACES (CON GUIA)
 * ============================================================================
 *
 * CONCEPTO CLAVE: Una clase puede implementar VARIAS interfaces a la vez.
 * Esto es la "herencia multiple de comportamiento" de Java.
 *
 *   public class Pajaro implements Volador, Cantante, Animal { ... }
 *
 * A diferencia de las clases (solo puedes extender UNA), con interfaces
 * puedes implementar TODAS las que necesites. Cada interfaz anade un
 * "contrato" que la clase debe cumplir.
 *
 * Lee primero: teoria/01_Interfaces_Basicas.md
 */
public class Ejercicio05_MultiplesInterfaces {

    /**
     * Interfaz para objetos que se pueden imprimir como texto.
     */
    public interface Imprimible {
        String imprimir();
    }

    /**
     * Interfaz para objetos que se pueden exportar a diferentes formatos.
     */
    public interface Exportable {
        String exportar(String formato);
    }

    /**
     * Interfaz combinada: un documento que es imprimible Y exportable.
     * Al extender ambas, quien implemente DocumentoCompleto debe implementar
     * tanto imprimir() como exportar().
     */
    public interface DocumentoCompleto extends Imprimible, Exportable {
    }

    /**
     * TODO: Crea un DocumentoCompleto con el contenido dado.
     *
     * - imprimir() debe devolver: "=== DOCUMENTO ===\n{contenido}\n================"
     * - exportar(formato) debe funcionar asi:
     *     - "txt" -> devuelve el contenido tal cual
     *     - "html" -> devuelve "<html><body>{contenido}</body></html>"
     *     - "csv" -> devuelve el contenido reemplazando espacios por ","
     *     - cualquier otro formato -> devuelve "Formato no soportado: {formato}"
     *
     * PISTA: Puedes crear una clase interna privada que implemente DocumentoCompleto,
     * o usar una clase anonima.
     */
    public static DocumentoCompleto crearDocumento(String contenido) {
        throw new UnsupportedOperationException("¡Implementa tu solución aquí!");
    }
}
