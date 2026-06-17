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
        // GUÍA: getImporte() vive en la clase base Pago130, así que sirve para
        // cualquier subtipo (polimorfismo).
        // 1. Una línea: return p.getImporte();
        // El test espera 100.0.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerImporte");
    }

    /**
     * Reto Extra 2: Comprueba si un pago es con tarjeta.
     */
    public static boolean esPagoTarjeta(Pago130 p) {
        // GUÍA: comprueba el subtipo concreto con instanceof.
        // 1. Una línea: return p instanceof PagoTarjeta130;
        // El test pasa un PagoTarjeta130 y espera true.
        // CULTURA: esto es el equivalente en Java al TYPE(p) de JPQL (teoría 14.8).
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esPagoTarjeta");
    }

    /**
     * Reto Extra 3: Comprueba si un pago es por transferencia.
     */
    public static boolean esPagoTransferencia(Pago130 p) {
        // GUÍA: espejo del reto 2 con el otro subtipo.
        // 1. Una línea: return p instanceof PagoTransferencia130;
        // El test pasa un PagoTransferencia130 y espera true.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para esPagoTransferencia");
    }

    /**
     * Reto Extra 4: Crea un pago con tarjeta.
     */
    public static PagoTarjeta130 crearPagoTarjeta(double imp, String pan) {
        // GUÍA: una línea — return new PagoTarjeta130(imp, pan);
        // El constructor recibe (importe, pan). El test solo comprueba no-null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearPagoTarjeta");
    }

    /**
     * Reto Extra 5: Crea un pago por transferencia.
     */
    public static PagoTransferencia130 crearPagoTransferencia(double imp, String iban) {
        // GUÍA: una línea — return new PagoTransferencia130(imp, iban);
        // El constructor recibe (importe, iban). El test solo comprueba no-null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para crearPagoTransferencia");
    }

    /**
     * Reto Extra 6: Comprueba si el importe del pago supera un limite.
     */
    public static boolean importeSupera(Pago130 p, double limite) {
        // GUÍA: una línea — return p.getImporte() > limite;
        // OJO: comparación estricta >. El test pide true con importe 100 y limite 50.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para importeSupera");
    }

    /**
     * Reto Extra 7: Obtiene el ID del pago.
     */
    public static Long obtenerId(Pago130 p) {
        // GUÍA: getId() está en la base Pago130.
        // 1. Una línea: return p.getId();
        // El test usa un pago sin persistir y espera null.
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerId");
    }

    /**
     * Reto Extra 8: Obtiene el PAN de un pago con tarjeta si aplica.
     */
    public static String obtenerPan(Pago130 p) {
        // GUÍA: el PAN solo existe en PagoTarjeta130 (no en la base). Usa
        // instanceof con binding para comprobar el tipo Y castear en un paso.
        // ⚠ CUIDADO: PagoTarjeta130 NO tiene getter para 'pan'. Añádele:
        //     public String getPan() { return pan; }
        // 1. if (p instanceof PagoTarjeta130 t) return t.getPan();
        // 2. return null;   // "si aplica": si no es tarjeta, no hay PAN.
        // OJO: el test pasa una tarjeta con "1234" y espera "1234".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerPan");
    }

    /**
     * Reto Extra 9: Obtiene el IBAN de un pago con transferencia si aplica.
     */
    public static String obtenerIban(Pago130 p) {
        // GUÍA: espejo del reto 8 con PagoTransferencia130.
        // ⚠ CUIDADO: PagoTransferencia130 NO tiene getter para 'iban'. Añádele:
        //     public String getIban() { return iban; }
        // 1. if (p instanceof PagoTransferencia130 t) return t.getIban();
        // 2. return null;
        // OJO: el test pasa una transferencia con "ES12" y espera "ES12".
        throw new UnsupportedOperationException("TODO: Implementar la lógica del reto extra para obtenerIban");
    }

    /**
     * Reto Extra 10: Retorna formato del pago.
     */
    public static String formatearPago(Pago130 p) {
        // GUÍA: el "Tipo" es el valor del discriminador: "TARJETA" o "TRANSFER"
        // (los mismos de @DiscriminatorValue). Derívalo del subtipo.
        // 1. String tipo = (p instanceof PagoTarjeta130) ? "TARJETA" : "TRANSFER";
        //    (o reutiliza esPagoTarjeta del reto 2).
        // 2. return "Pago[Tipo=" + tipo + ", Importe=" + p.getImporte() + "]";
        // OJO: el test pasa una tarjeta de 100 y espera EXACTAMENTE
        //      "Pago[Tipo=TARJETA, Importe=100.0]" (Importe es double → ".0").
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
