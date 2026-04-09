package modelos;

import java.util.Objects;

/**
 * CLASE MAESTRA: EMPLEADO
 * 
 * POJO pesado para las demostraciones de Streams, Lambdas, Optional y Method References.
 * Simula un sistema de gestión de empleados de una empresa tecnológica.
 */
public class Empleado implements Comparable<Empleado> {

    private String nombre;
    private String departamento; // Ej: "Backend", "Frontend", "DevOps", "QA", "Data"
    private String lenguajePrincipal; // Ej: "Java", "Python", "JavaScript", "Go"
    private int experienciaAnios;
    private double salario;
    private boolean activo;
    private String email; // Puede ser null (dato corrupto)

    public Empleado(String nombre, String departamento, String lenguajePrincipal,
                    int experienciaAnios, double salario, boolean activo, String email) {
        this.nombre = nombre;
        this.departamento = departamento;
        this.lenguajePrincipal = lenguajePrincipal;
        this.experienciaAnios = experienciaAnios;
        this.salario = salario;
        this.activo = activo;
        this.email = email;
    }

    public String getNombre() { return nombre; }
    public String getDepartamento() { return departamento; }
    public String getLenguajePrincipal() { return lenguajePrincipal; }
    public int getExperienciaAnios() { return experienciaAnios; }
    public double getSalario() { return salario; }
    public boolean isActivo() { return activo; }
    public String getEmail() { return email; }

    public void setSalario(double salario) { this.salario = salario; }
    public void desactivar() { this.activo = false; }

    // Helpers para ejercicios avanzados
    public boolean esSenior() { return this.experienciaAnios >= 5 && this.salario > 40000; }
    public boolean esJunior() { return this.experienciaAnios < 2; }
    public String getIniciales() { 
        String[] partes = nombre.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String p : partes) {
            if (!p.isEmpty()) sb.append(p.charAt(0));
        }
        return sb.toString().toUpperCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return Objects.equals(nombre, empleado.nombre) &&
               Objects.equals(departamento, empleado.departamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, departamento);
    }

    @Override
    public String toString() {
        return nombre + " [" + departamento + " | " + lenguajePrincipal + 
               " | " + experienciaAnios + "a | " + salario + "€ | " +
               (activo ? "Activo" : "Inactivo") + "]";
    }

    /**
     * Orden Natural: Por salario DESCENDENTE.
     * Si empatan en salario, desempatar por nombre ASCENDENTE.
     */
    @Override
    public int compareTo(Empleado o) {
        int r = Double.compare(o.salario, this.salario);
        if (r == 0) {
            return this.nombre.compareTo(o.nombre);
        }
        return r;
    }
}
