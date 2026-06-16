package com.masterclass.api.b12_jpa;

import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/*
 * ============================================================================
 *  REPOSITORIOS DEL BLOQUE 12 (interfaces + implementación de apoyo).
 * ----------------------------------------------------------------------------
 *  En un proyecto Spring real estas interfaces extenderían
 *  JpaRepository<T, ID> y Spring generaría la implementación SOLO: tú no
 *  escribirías ni una línea de cuerpo. Como los tests de este bloque corren en
 *  JUnit puro (sin contexto de Spring), aquí declaramos la interfaz con la MISMA
 *  forma que tendría en Spring Data y aportamos una implementación mínima
 *  respaldada por EntityManager para que los retos puedan ejecutarse.
 *
 *  Lee las interfaces como "lo que pedirías"; las clases *Em como "lo que
 *  Spring te regala". Las anotaciones @Query/@Modifying son las que Spring leería
 *  (y las que un test localiza por reflexión).
 * ============================================================================
 */

/** Repositorio de productos: el CRUD que Spring Data ofrece de serie. */
interface ProductoRepository {
    Producto save(Producto p);

    Optional<Producto> findById(Long id);

    List<Producto> findAll();

    boolean existsById(Long id);

    long count();

    void deleteById(Long id);

    void deleteAll();
}

/** Repositorio de empleados: CRUD + query methods + JPQL/nativa + @Modifying. */
interface EmpleadoRepository {
    Empleado save(Empleado e);

    long count();

    void deleteAll();

    // Query methods: Spring deriva el JPQL del NOMBRE del método.
    List<Empleado> findByNombre(String nombre);

    List<Empleado> findByDepartamento(String departamento);

    List<Empleado> findByDepartamentoAndNombre(String departamento, String nombre);

    long countByDepartamento(String departamento);

    // @Query explícita en JPQL (orientada a entidades).
    @Query("select e from Empleado e where e.departamento = :dep")
    List<Empleado> buscarPorDepartamentoJpql(String dep);

    // @Query nativa (SQL real contra la tabla EMPLEADO).
    @Query(value = "SELECT * FROM EMPLEADO WHERE DEPARTAMENTO = :dep", nativeQuery = true)
    List<Empleado> buscarPorDepartamentoNativa(String dep);

    // Modificación masiva: requiere @Modifying para que Spring use executeUpdate().
    @Modifying
    @Query("update Empleado e set e.departamento = :nuevo where e.departamento = :viejo")
    int actualizarDepartamento(String nuevo, String viejo);

    // Proyección por constructor a un DTO de solo lectura.
    List<EmpleadoDto> buscarTodosProyeccion();
}

/** Implementación de apoyo de {@link ProductoRepository} sobre EntityManager. */
class ProductoRepositoryEm implements ProductoRepository {

    private final EntityManager em;

    ProductoRepositoryEm(EntityManager em) {
        this.em = em;
    }

    private void enTx(Runnable accion) {
        var tx = em.getTransaction();
        boolean propietario = !tx.isActive();
        if (propietario) {
            tx.begin();
        }
        try {
            accion.run();
            if (propietario) {
                tx.commit();
            }
        } catch (RuntimeException ex) {
            if (propietario && tx.isActive()) {
                tx.rollback();
            }
            throw ex;
        }
    }

    @Override
    public Producto save(Producto p) {
        enTx(() -> {
            if (p.getId() == null) {
                em.persist(p);
            } else {
                em.merge(p);
            }
        });
        return p;
    }

    @Override
    public Optional<Producto> findById(Long id) {
        return Optional.ofNullable(em.find(Producto.class, id));
    }

    @Override
    public List<Producto> findAll() {
        return em.createQuery("select p from Producto p order by p.id", Producto.class).getResultList();
    }

    @Override
    public boolean existsById(Long id) {
        return em.find(Producto.class, id) != null;
    }

    @Override
    public long count() {
        return em.createQuery("select count(p) from Producto p", Long.class).getSingleResult();
    }

    @Override
    public void deleteById(Long id) {
        enTx(() -> {
            var p = em.find(Producto.class, id);
            if (p != null) {
                em.remove(p);
            }
        });
    }

    @Override
    public void deleteAll() {
        enTx(() -> em.createQuery("delete from Producto p").executeUpdate());
    }
}

/** Implementación de apoyo de {@link EmpleadoRepository} sobre EntityManager. */
class EmpleadoRepositoryEm implements EmpleadoRepository {

    private final EntityManager em;

    EmpleadoRepositoryEm(EntityManager em) {
        this.em = em;
    }

    private void enTx(Runnable accion) {
        var tx = em.getTransaction();
        boolean propietario = !tx.isActive();
        if (propietario) {
            tx.begin();
        }
        try {
            accion.run();
            if (propietario) {
                tx.commit();
            }
        } catch (RuntimeException ex) {
            if (propietario && tx.isActive()) {
                tx.rollback();
            }
            throw ex;
        }
    }

    @Override
    public Empleado save(Empleado e) {
        enTx(() -> {
            if (e.getId() == null) {
                em.persist(e);
            } else {
                em.merge(e);
            }
        });
        return e;
    }

    @Override
    public long count() {
        return em.createQuery("select count(e) from Empleado e", Long.class).getSingleResult();
    }

    @Override
    public void deleteAll() {
        enTx(() -> em.createQuery("delete from Empleado e").executeUpdate());
    }

    @Override
    public List<Empleado> findByNombre(String nombre) {
        return em.createQuery("select e from Empleado e where e.nombre = :n", Empleado.class)
                .setParameter("n", nombre).getResultList();
    }

    @Override
    public List<Empleado> findByDepartamento(String departamento) {
        return em.createQuery("select e from Empleado e where e.departamento = :d", Empleado.class)
                .setParameter("d", departamento).getResultList();
    }

    @Override
    public List<Empleado> findByDepartamentoAndNombre(String departamento, String nombre) {
        return em.createQuery(
                        "select e from Empleado e where e.departamento = :d and e.nombre = :n", Empleado.class)
                .setParameter("d", departamento).setParameter("n", nombre).getResultList();
    }

    @Override
    public long countByDepartamento(String departamento) {
        return em.createQuery("select count(e) from Empleado e where e.departamento = :d", Long.class)
                .setParameter("d", departamento).getSingleResult();
    }

    @Override
    public List<Empleado> buscarPorDepartamentoJpql(String dep) {
        return em.createQuery("select e from Empleado e where e.departamento = :dep", Empleado.class)
                .setParameter("dep", dep).getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Empleado> buscarPorDepartamentoNativa(String dep) {
        return em.createNativeQuery("SELECT * FROM EMPLEADO WHERE DEPARTAMENTO = ?", Empleado.class)
                .setParameter(1, dep).getResultList();
    }

    @Override
    public int actualizarDepartamento(String nuevo, String viejo) {
        int[] filas = new int[1];
        enTx(() -> filas[0] = em.createQuery(
                        "update Empleado e set e.departamento = :nuevo where e.departamento = :viejo")
                .setParameter("nuevo", nuevo).setParameter("viejo", viejo).executeUpdate());
        return filas[0];
    }

    @Override
    public List<EmpleadoDto> buscarTodosProyeccion() {
        return em.createQuery(
                "select new com.masterclass.api.b12_jpa.EmpleadoDto(e.nombre, e.departamento) from Empleado e",
                EmpleadoDto.class).getResultList();
    }
}
