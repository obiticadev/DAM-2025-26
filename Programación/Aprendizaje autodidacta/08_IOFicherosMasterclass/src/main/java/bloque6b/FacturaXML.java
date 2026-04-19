package bloque6b;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Clase modelo para los ejercicios de XML con JAXB.
 * Representa una factura con id y total.
 */
@XmlRootElement
public class FacturaXML {

    private int id;
    private double total;

    public FacturaXML() {} // Constructor vacio obligatorio para JAXB

    public FacturaXML(int id, double total) {
        this.id = id;
        this.total = total;
    }

    @XmlElement
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @XmlElement
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    @Override
    public String toString() {
        return String.format("FacturaXML{id=%d, total=%.2f}", id, total);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacturaXML that = (FacturaXML) o;
        return id == that.id && Double.compare(that.total, total) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, total);
    }
}
