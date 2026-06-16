package com.masterclass.api.b12_jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;

/*
 * ============================================================================
 *  FIXTURES DEL BLOQUE 12 · entidades, embeddables, enums y DTOs de apoyo.
 * ----------------------------------------------------------------------------
 *  Estas clases son el "decorado" que los ejercicios y los tests usan como
 *  datos de prueba. En un proyecto Spring real cada una viviría en su propio
 *  fichero; aquí se agrupan para no inundar el paquete. Son package-private:
 *  visibles para los Ej* y para los tests (mismo paquete), invisibles fuera.
 *
 *  Cada entidad ilustra exactamente UNA idea del bloque (mira el comentario).
 * ============================================================================
 */

/** Ej103 · entidad mínima con @Id ASIGNADO a mano (sin @GeneratedValue). */
@Entity
class Producto103 {
    @Id
    private Long id;
    private String nombre;
    private double precio;

    protected Producto103() {
    }

    Producto103(Long id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }
}

/**
 * Ej103 · entidad con mapeo EXPLÍCITO de tabla y columnas. Los tests de
 * reflexión leen estos nombres: tabla {@code EMPLEADO}, columnas {@code EMP_ID}
 * y {@code EMP_NOM} (esta última {@code nullable=false}).
 */
@Entity
@Table(name = "EMPLEADO")
class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMP_ID")
    private Long id;

    @Column(name = "EMP_NOM", nullable = false)
    private String nombre;

    private String departamento;

    public Empleado() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}

/** Ej104 · id autogenerado por IDENTITY (lo crea la BD al insertar). */
@Entity
class Nota104 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String texto;

    protected Nota104() {
    }

    Nota104(String texto) {
        this.texto = texto;
    }

    public Long getId() {
        return id;
    }

    public String getTexto() {
        return texto;
    }
}

/** Ej104 · estrategia SEQUENCE con generador explícito {@code REG_SEQ}. */
@Entity
class RegistroConSecuencia {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "regSeqGen")
    @SequenceGenerator(name = "regSeqGen", sequenceName = "REG_SEQ", allocationSize = 1)
    private Long id;
    private String datos;

    public RegistroConSecuencia() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }
}

/** Ej104 · estrategia IDENTITY (auto-incremento de la BD). */
@Entity
class RegistroConIdentidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String datos;

    public RegistroConIdentidad() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }
}

/** Ej105 · entidad sencilla para el CRUD estilo repositorio. */
@Entity
class Tarea105 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;

    protected Tarea105() {
    }

    Tarea105(String titulo) {
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }
}

/** Ej105/106/114 · entidad de catálogo usada por {@link ProductoRepository}. */
@Entity
class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private double precio;

    public Producto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}

/** Ej106 · filtrado por campos (categoria, precio). */
@Entity
class Articulo106 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoria;
    private double precio;

    protected Articulo106() {
    }

    Articulo106(String categoria, double precio) {
        this.categoria = categoria;
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPrecio() {
        return precio;
    }
}

/** Ej107 · empleado con salario para proyecciones y agregados JPQL. */
@Entity
class Empleado107 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String departamento;
    private double salario;

    protected Empleado107() {
    }

    Empleado107(String nombre, String departamento, double salario) {
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

/** Ej108 · tabla {@code CIUDAD108} para SQL nativo (nombre real de tabla). */
@Entity
@Table(name = "CIUDAD108")
class Ciudad108 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private int poblacion;

    protected Ciudad108() {
    }

    Ciudad108(String nombre, int poblacion) {
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

/** Ej109 · producto con stock para UPDATE/DELETE masivos. */
@Entity
class Prod109 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoria;
    private double precio;
    private int stock;

    protected Prod109() {
    }

    Prod109(String categoria, double precio, int stock) {
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }
}

/** Ej110 · entidad cuyo @PrePersist rellena {@code creadoEn} solo. */
@Entity
class Auditable110 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dato;
    private Instant creadoEn;

    protected Auditable110() {
    }

    Auditable110(String dato) {
        this.dato = dato;
    }

    @PrePersist
    void onCreate() {
        this.creadoEn = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public Instant getCreadoEn() {
        return creadoEn;
    }
}

/**
 * Ej110 · entidad auditada con DOS callbacks: {@code antesDeGuardar}
 * (@PrePersist) y {@code antesDeActualizar} (@PreUpdate). Los tests los
 * localizan por reflexión por su nombre exacto.
 */
@Entity
class Auditado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dato;
    private LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;

    public Auditado() {
    }

    @PrePersist
    void antesDeGuardar() {
        this.creadoEn = LocalDateTime.now();
        this.actualizadoEn = this.creadoEn;
    }

    @PreUpdate
    void antesDeActualizar() {
        this.actualizadoEn = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(LocalDateTime creadoEn) {
        this.creadoEn = creadoEn;
    }

    public LocalDateTime getActualizadoEn() {
        return actualizadoEn;
    }

    public void setActualizadoEn(LocalDateTime actualizadoEn) {
        this.actualizadoEn = actualizadoEn;
    }
}

/** Ej111 · objeto de valor embebido del socio. */
@Embeddable
class Direccion111 {
    private String calle;
    private String ciudad;

    public Direccion111() {
    }

    Direccion111(String calle, String ciudad) {
        this.calle = calle;
        this.ciudad = ciudad;
    }

    public String getCalle() {
        return calle;
    }

    public String getCiudad() {
        return ciudad;
    }
}

/** Ej111 · entidad con enum (@Enumerated) y objeto embebido (@Embedded). */
@Entity
class Socio111 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @Enumerated(EnumType.STRING)
    private Ej111EnumAndEmbeddable.Estado estado;

    @Embedded
    private Direccion111 direccion;

    protected Socio111() {
    }

    Socio111(String nombre, Ej111EnumAndEmbeddable.Estado estado, Direccion111 direccion) {
        this.nombre = nombre;
        this.estado = estado;
        this.direccion = direccion;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Ej111EnumAndEmbeddable.Estado getEstado() {
        return estado;
    }

    public Direccion111 getDireccion() {
        return direccion;
    }
}

/** Ej111 · estados posibles de un pedido (se guardan como texto, no como número). */
enum EstadoPedido {
    NUEVO, PAGADO, ENVIADO, ENTREGADO
}

/** Ej111 · objeto de valor embebido en el pedido. */
@Embeddable
class Direccion {
    private String calle;
    private String ciudad;

    public Direccion() {
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}

/** Ej111 · pedido con enum como STRING y dirección de envío embebida. */
@Entity
@Table(name = "PEDIDO_111")
class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;

    @Embedded
    private Direccion direccionEnvio;

    public Pedido() {
    }

    public Long getId() {
        return id;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public Direccion getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(Direccion direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }
}

/** Ej112 · documento para demostrar dirty checking (managed vs detached). */
@Entity
class Doc112 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    protected Doc112() {
    }

    Doc112(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

/** Ej112 · usuario para probar transiciones del contexto de persistencia. */
@Entity
@Table(name = "USUARIO_112")
class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

/**
 * Ej113 · entidad cuya identidad se basa en una CLAVE DE NEGOCIO ({@code uuid})
 * estable, no en el id de BD. Así el {@code equals} no cambia aunque la entidad
 * reciba su id al persistirse.
 */
@Entity
@Table(name = "CLIENTE_113")
class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private String nombre;

    public Cliente() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cliente cliente = (Cliente) o;
        return Objects.equals(uuid, cliente.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}

/** Ej114 · pedido con total, fuente de la proyección a DTO. */
@Entity
@Table(name = "PEDIDO_114")
class Pedido114 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double total;

    protected Pedido114() {
    }

    Pedido114(double total) {
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public double getTotal() {
        return total;
    }
}

/**
 * Ej114 · DTO de proyección (NO es entidad). Un record encaja perfecto: el
 * JPQL {@code select new ...EmpleadoDto(e.nombre, e.departamento)} usa su
 * constructor canónico.
 */
record EmpleadoDto(String nombre, String departamento) {
}
