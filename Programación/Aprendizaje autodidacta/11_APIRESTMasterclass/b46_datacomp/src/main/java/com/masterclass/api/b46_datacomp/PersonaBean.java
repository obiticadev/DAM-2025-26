package com.masterclass.api.b46_datacomp;

/**
 * JavaBean de apoyo (clase fully built, NO hay TODOs aquí).
 *
 * <p>Un <em>JavaBean</em> es una clase que cumple una convención muy concreta para ser
 * "manipulable" por herramientas sin conocer su código: (1) constructor público sin argumentos,
 * (2) propiedades expuestas como pares {@code getX()/setX(...)} (o {@code isX()} para booleanos)
 * y (3) normalmente {@link java.io.Serializable}. Esta clase es el conejillo de indias de
 * {@code Ej351BeanProperties}: tiene tres propiedades de lectura/escritura ({@code nombre},
 * {@code edad}, {@code activo}) y una propiedad <em>de solo lectura</em> ({@code descripcion},
 * calculada, sin setter) para que los retos puedan distinguirlas.
 */
public class PersonaBean {

    private String nombre;
    private int edad;
    private boolean activo;

    /** Constructor público sin argumentos: requisito imprescindible de un JavaBean. */
    public PersonaBean() {
    }

    public PersonaBean(String nombre, int edad, boolean activo) {
        this.nombre = nombre;
        this.edad = edad;
        this.activo = activo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    /** Propiedad booleana: por convención su getter empieza por {@code is}, no por {@code get}. */
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    /**
     * Propiedad CALCULADA y de SOLO LECTURA: tiene getter pero NO setter. Sirve para que los
     * retos comprueben la diferencia entre una propiedad normal y una de solo lectura.
     */
    public String getDescripcion() {
        return nombre + " (" + edad + ")";
    }
}
