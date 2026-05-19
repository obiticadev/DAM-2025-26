package com.masterclass.api.b12_jpa;

import jakarta.persistence.EntityManager;
import java.util.List;

/**
 * Ejercicio 108 · SQL nativo (cuando JPQL no basta).
 *
 * <p>Teoría: {@code teoria/12_Spring_Data_JPA.md} (sección 12.4).
 */
public final class Ej108NativeQueries {

    private final EntityManager em;

    public Ej108NativeQueries(EntityManager em) {
        this.em = em;
    }

    /**
     * Cuenta filas con SQL nativo (nombre de TABLA, no de entidad).
     *
     * @return número de filas de la tabla CIUDAD108
     */
    public long contarNativo() {
        // TODO 1: createNativeQuery("SELECT COUNT(*) FROM CIUDAD108").
        // TODO 2: getSingleResult() (devuelve Number/Long según dialecto).
        // TODO 3: convierte a long con ((Number)res).longValue().
        // TODO 4: SQL nativo usa el nombre real de la tabla, no la entidad.
        return -1;
    }

    /**
     * Recupera entidades con SQL nativo mapeado a la entidad.
     *
     * @param paisMin población mínima
     * @return ciudades con población &gt;= paisMin
     */
    @SuppressWarnings("unchecked")
    public List<Ciudad108> grandesNativo(int paisMin) {
        // TODO 5: createNativeQuery("SELECT * FROM CIUDAD108 WHERE poblacion >= ?", Ciudad108.class).
        // TODO 6: setParameter(1, paisMin) (parámetros nativos son posicionales con ?).
        // TODO 7: getResultList() devuelve List de Ciudad108 (mapeo por la 2ª arg).
        // TODO 8: nunca concatenes 'paisMin' en el SQL (inyección).
        // TODO 9: el orden no está garantizado sin ORDER BY (añádelo si el test lo pide).
        // TODO 10: devuelve la lista.
        return List.of();
    }

    public static void main(String[] args) {
        System.out.println("usa el test con EMF aislado");
    }
}

@jakarta.persistence.Entity
@jakarta.persistence.Table(name = "CIUDAD108")
class Ciudad108 {
    @jakarta.persistence.Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private int poblacion;

    protected Ciudad108() {
    }

    public Ciudad108(String nombre, int poblacion) {
        this.nombre = nombre;
        this.poblacion = poblacion;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPoblacion() {
        return poblacion;
    }
}
