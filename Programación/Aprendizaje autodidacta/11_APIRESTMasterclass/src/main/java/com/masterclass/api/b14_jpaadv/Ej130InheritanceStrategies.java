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
