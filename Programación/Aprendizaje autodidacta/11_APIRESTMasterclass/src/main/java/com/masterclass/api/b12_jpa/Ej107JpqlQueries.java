package com.masterclass.api.b12_jpa;

import jakarta.persistence.EntityManager;
import java.util.List;

/**
 * Ejercicio 107 · JPQL (consultas sobre entidades, no tablas).
 *
 * <p>Teoría: {@code teoria/12_Spring_Data_JPA.md} (sección 12.4).
 */
public final class Ej107JpqlQueries {

    private final EntityManager em;

    public Ej107JpqlQueries(EntityManager em) {
        this.em = em;
    }

    /**
     * Nombres de empleados de un departamento, en mayúsculas, ordenados.
     *
     * @param dep departamento
     * @return lista de nombres (String), no entidades
     */
    public List<String> nombresPorDepartamento(String dep) {
        // TODO 1: JPQL que SELECCIONA un campo: "select e.nombre from Empleado107 e
        //         where e.departamento = :d order by e.nombre".
        // TODO 2: el tipo de resultado es String.class (proyección de columna).
        // TODO 3: setParameter("d", dep).
        // TODO 4: getResultList().
        return List.of();
    }

    /**
     * Salario medio del departamento.
     *
     * @param dep departamento
     * @return media (0.0 si no hay empleados)
     */
    public double salarioMedio(String dep) {
        // TODO 5: JPQL de agregación "select avg(e.salario) from Empleado107 e where e.departamento=:d".
        // TODO 6: getSingleResult() devuelve Double (o null si no hay filas).
        // TODO 7: si es null, devuelve 0.0 (caso límite: departamento vacío).
        // TODO 8: si no, devuelve el valor.
        return -1;
    }

    /**
     * Empleados cuyo nombre contiene un fragmento (LIKE).
     *
     * @param fragmento subcadena a buscar
     * @return entidades coincidentes
     */
    public List<Empleado107> buscarPorNombreLike(String fragmento) {
        // TODO 9: JPQL "... where e.nombre like :patron"; patrón = "%" + fragmento + "%".
        // TODO 10: setParameter y getResultList.
        return List.of();
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }
}

@jakarta.persistence.Entity
class Empleado107 {
    @jakarta.persistence.Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String departamento;
    private double salario;

    protected Empleado107() {
    }

    public Empleado107(String nombre, String departamento, double salario) {
        this.nombre = nombre;
        this.departamento = departamento;
        this.salario = salario;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDepartamento() {
        return departamento;
    }

    public double getSalario() {
        return salario;
    }
}
