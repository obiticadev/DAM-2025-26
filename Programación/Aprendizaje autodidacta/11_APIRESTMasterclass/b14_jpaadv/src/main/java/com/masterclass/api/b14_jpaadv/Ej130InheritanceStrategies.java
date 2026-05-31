package com.masterclass.api.b14_jpaadv;

import jakarta.persistence.*;
import java.util.List;

/**
 * Ejercicio 130 · Herencia de entidades (SINGLE_TABLE).
 *
 * <p>Teoría: {@code teoria/14_JPA_Avanzado.md} (sección 14.3).
 */
public final class Ej130InheritanceStrategies {

    private Ej130InheritanceStrategies() {
    }

    /**
     * Persiste pagos de distinto subtipo y los lista por la clase base.
     *
     * @param em EntityManager
     * @return número de pagos totales (cualquier subtipo)
     */
    public static long guardarYContar(EntityManager em) {
        // TODO 1: begin tx.
        // TODO 2: persiste un PagoTarjeta130 y un PagoTransferencia130.
        // TODO 3: commit.
        // TODO 4: JPQL polimórfica "select count(p) from Pago130 p" (cuenta todos los subtipos).
        // TODO 5: getSingleResult() y devuélvelo como long.
        return -1;
    }

    /**
     * @param em EntityManager
     * @return solo los pagos con tarjeta (filtrando por subtipo con TYPE()).
     */
    public static List<Pago130> soloTarjeta(EntityManager em) {
        // TODO 6: JPQL "select p from Pago130 p where TYPE(p) = PagoTarjeta130".
        // TODO 7: getResultList().
        return List.of();
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }

    /**
     * Reto Extra 1: Obtiene el importe de un pago de forma segura.
     */
    public static double obtenerImporte(Pago130 p) {
        // TODO extra: Reto Extra 1: Obtiene el importe de un pago de forma segura.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerImporte");
    }

    /**
     * Reto Extra 2: Comprueba si un pago es con tarjeta.
     */
    public static boolean esPagoTarjeta(Pago130 p) {
        // TODO extra: Reto Extra 2: Comprueba si un pago es con tarjeta.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esPagoTarjeta");
    }

    /**
     * Reto Extra 3: Comprueba si un pago es por transferencia.
     */
    public static boolean esPagoTransferencia(Pago130 p) {
        // TODO extra: Reto Extra 3: Comprueba si un pago es por transferencia.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esPagoTransferencia");
    }

    /**
     * Reto Extra 4: Crea un pago con tarjeta.
     */
    public static PagoTarjeta130 crearPagoTarjeta(double imp, String pan) {
        // TODO extra: Reto Extra 4: Crea un pago con tarjeta.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearPagoTarjeta");
    }

    /**
     * Reto Extra 5: Crea un pago por transferencia.
     */
    public static PagoTransferencia130 crearPagoTransferencia(double imp, String iban) {
        // TODO extra: Reto Extra 5: Crea un pago por transferencia.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearPagoTransferencia");
    }

    /**
     * Reto Extra 6: Comprueba si el importe del pago supera un limite.
     */
    public static boolean importeSupera(Pago130 p, double limite) {
        // TODO extra: Reto Extra 6: Comprueba si el importe del pago supera un limite.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para importeSupera");
    }

    /**
     * Reto Extra 7: Obtiene el ID del pago.
     */
    public static Long obtenerId(Pago130 p) {
        // TODO extra: Reto Extra 7: Obtiene el ID del pago.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerId");
    }

    /**
     * Reto Extra 8: Obtiene el PAN de un pago con tarjeta si aplica.
     */
    public static String obtenerPan(Pago130 p) {
        // TODO extra: Reto Extra 8: Obtiene el PAN de un pago con tarjeta si aplica.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerPan");
    }

    /**
     * Reto Extra 9: Obtiene el IBAN de un pago con transferencia si aplica.
     */
    public static String obtenerIban(Pago130 p) {
        // TODO extra: Reto Extra 9: Obtiene el IBAN de un pago con transferencia si aplica.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerIban");
    }

    /**
     * Reto Extra 10: Retorna formato del pago.
     */
    public static String formatearPago(Pago130 p) {
        // TODO extra: Reto Extra 10: Retorna formato del pago.
        // 1. Validar exhaustivamente todos los parámetros de entrada y precondiciones del método.
        // 2. Diseñar e implementar el algoritmo principal resolviendo cada regla de negocio paso a paso.
        // 3. Asegurar una cobertura completa de casos límite, valores nulos, vacíos o fuera de rango.
        // 4. Retornar el resultado final procesado de forma limpia y eficiente, sin simplificaciones triviales.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para formatearPago");
    }



}

// TODO 8: anota Pago130 con @Entity y @Inheritance(strategy = InheritanceType.SINGLE_TABLE).
// TODO 9: añade @DiscriminatorColumn(name = "tipo") para distinguir subtipos.
abstract class Pago130 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double importe;

    protected Pago130() {
    }

    protected Pago130(double importe) {
        this.importe = importe;
    }

    public Long getId() {
        return id;
    }

    public double getImporte() {
        return importe;
    }
}

// TODO 10: anota PagoTarjeta130 con @Entity y @DiscriminatorValue("TARJETA").
class PagoTarjeta130 extends Pago130 {
    private String pan;

    protected PagoTarjeta130() {
    }

    public PagoTarjeta130(double importe, String pan) {
        super(importe);
        this.pan = pan;
    }
}

@Entity
@DiscriminatorValue("TRANSFER")
class PagoTransferencia130 extends Pago130 {
    private String iban;

    protected PagoTransferencia130() {
    }

    public PagoTransferencia130(double importe, String iban) {
        super(importe);
        this.iban = iban;
    }
}
