# Nivel 3 — El Patrón DAO Completo

---

## ¿Qué problema resuelve el patrón DAO?

Sin DAO, cada vez que necesitas acceder a la base de datos escribes SQL mezclado con lógica de negocio. El código queda acoplado, difícil de cambiar y de testear.

**DAO (Data Access Object)** separa completamente la lógica de acceso a datos del resto del programa. La regla es simple:

> Todo lo que toca SQL va dentro del DAO. El resto del código no sabe que existe una base de datos.

---

## Los tres elementos del patrón

```mermaid
classDiagram
    class Entidad {
        -int id
        -String campo1
        -int campo2
        +Entidad(campo1, campo2)
        +getId() int
        +getCampo1() String
        +getCampo2() int
    }

    class InterfazDAO {
        <<interface>>
        +crearTabla() void
        +insertar(Entidad) boolean
        +obtenerTodos() List~Entidad~
        +obtenerPorId(int) Entidad
        +actualizar(Entidad) boolean
        +eliminar(int) boolean
    }

    class DAOImpl {
        +crearTabla() void
        +insertar(Entidad) boolean
        +obtenerTodos() List~Entidad~
        +obtenerPorId(int) Entidad
        +actualizar(Entidad) boolean
        +eliminar(int) boolean
    }

    class Conexion {
        -static Connection instance$
        -Conexion()
        +static getConexion() Connection$
        +static cerrarConexion() void$
    }

    InterfazDAO <|.. DAOImpl : implements
    DAOImpl --> Conexion : usa
    DAOImpl ..> Entidad : crea / recibe
```

---

## La Entidad — POJO puro

La entidad es un simple contenedor de datos. No tiene lógica, no sabe nada de la BD. Solo:
- Atributos privados
- Constructor con los campos sin `id` (el id lo asigna la BD con AUTOINCREMENT)
- Solo **getters** — sin setters (inmutable por diseño)

```java
public class Producto {
    private int id;
    private String nombre;
    private double precio;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getId()       { return id; }
    public String getNombre(){ return nombre; }
    public double getPrecio(){ return precio; }
}
```

> ¿Por qué sin setters? Una vez construido el objeto, sus datos no deberían cambiar desde fuera. Si quieres "actualizar" un producto, creas uno nuevo con los datos modificados y llamas al DAO.

---

## La Interfaz DAO — el contrato

Define QUÉ puede hacer el DAO, sin decir CÓMO. Esto permite cambiar la implementación (SQLite → MySQL) sin tocar el resto del código.

```java
public interface IProductoDAO {
    void crearTabla() throws SQLException;
    boolean insertar(Producto p) throws SQLException;
    List<Producto> obtenerTodos() throws SQLException;
    Producto obtenerPorId(int id) throws SQLException;
    boolean actualizar(Producto p) throws SQLException;
    boolean eliminar(int id) throws SQLException;
}
```

---

## La Implementación DAO — el cuerpo

Implementa cada método de la interfaz usando PreparedStatement y el Singleton de Conexion.

```mermaid
flowchart TD
    subgraph "Cada método del DAO sigue este esquema"
        A[getConexion] --> B[prepareStatement con SQL con ?]
        B --> C[set* para cada parámetro]
        C --> D{¿Tipo?}
        D -- INSERT/UPDATE/DELETE --> E[executeUpdate → afectadas > 0]
        D -- SELECT todos --> F[executeQuery → while rs.next → lista]
        D -- SELECT por id --> G[executeQuery → if rs.next → objeto o null]
        D -- DDL --> H[Statement.execute sql]
    end
```

---

## Flujo completo de una llamada

```mermaid
sequenceDiagram
    participant Main as Main / App
    participant DAO as DAOImpl
    participant Conn as Conexion (Singleton)
    participant DB as SQLite

    Main->>DAO: insertar(new Producto("Teclado", 49.99))
    DAO->>Conn: getConexion()
    Conn-->>DAO: Connection
    DAO->>DB: prepareStatement("INSERT INTO ... VALUES (?,?)")
    DAO->>DB: pst.setString(1, "Teclado")
    DAO->>DB: pst.setDouble(2, 49.99)
    DAO->>DB: executeUpdate()
    DB-->>DAO: 1 (fila afectada)
    DAO-->>Main: true
```

---

## Diagrama de estado de la tabla gestionada por el DAO

```mermaid
stateDiagram-v2
    [*] --> SinTabla
    SinTabla --> TablaVacia : crearTabla()
    TablaVacia --> ConDatos : insertar()
    ConDatos --> ConDatos : insertar() / actualizar()
    ConDatos --> MenosDatos : eliminar()
    MenosDatos --> TablaVacia : eliminar todos
    ConDatos --> TablaVacia : DROP + recrear
```

---

## Estructura de archivos en el examen

Cuando te entreguen el proyecto en el examen, lo que vas a construir es:

```
MiEntidad.java          ← POJO (5 min)
IMiEntidadDAO.java      ← interface con 6 firmas (3 min)
MiEntidadDAO.java       ← implementación con Singleton (15 min)
Conexion.java           ← Singleton ya conocido (2 min)
Main.java               ← demo de uso
```

Total estimado sabiendo la lógica: **25 minutos**.

---

## El SpeedRun — cómo practicar para el examen

El ejercicio 27 simula exactamente la presión del examen:
1. Tienes una entidad nueva que no has visto antes
2. Sin plantilla, sin andamiaje previo
3. Construyes Entidad + Conexión + DAO completo desde cero
4. Los tests confirman que todo funciona

Si lo completas, el examen es **sota, caballo y rey**.

---

## Ejercicios de este nivel

| Ej | Lo que practicas |
|---|---|
| 19 | Entidad POJO: atributos privados, constructor, solo getters |
| 20 | Interfaz DAO: definir las 6 firmas del contrato |
| 21 | DAO impl: `crearTabla()` con Statement |
| 22 | DAO impl: `insertar(Entidad)` → boolean |
| 23 | DAO impl: `obtenerTodos()` → `List<Entidad>` |
| 24 | DAO impl: `obtenerPorId(int)` → objeto o null |
| 25 | DAO impl: `actualizar(Entidad)` → boolean |
| 26 | DAO impl: `eliminar(int)` → boolean |
| 27 | SpeedRun: Entidad + Singleton + DAO completo desde cero |
