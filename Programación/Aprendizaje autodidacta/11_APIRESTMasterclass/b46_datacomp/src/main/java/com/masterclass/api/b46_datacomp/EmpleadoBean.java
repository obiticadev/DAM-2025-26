package com.masterclass.api.b46_datacomp;

/**
 * JavaBean de apoyo que HEREDA de {@link PersonaBean} y añade una propiedad propia
 * ({@code salario}). Sirve para que los retos de {@code Ej351BeanProperties} comprueben que la
 * introspección descubre también las propiedades HEREDADAS, no solo las declaradas en la subclase.
 */
public class EmpleadoBean extends PersonaBean {

    private double salario;

    public EmpleadoBean() {
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
