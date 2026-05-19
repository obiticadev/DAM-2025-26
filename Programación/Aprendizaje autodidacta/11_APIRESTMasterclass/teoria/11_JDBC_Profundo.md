# Bloque XI · JDBC profundo

> Antes de JPA hay que entender qué hace JPA por debajo: JDBC. Acceso a Datos
> (DAM2) empieza aquí (RA2).

---

## 11.1 El flujo JDBC

```mermaid
sequenceDiagram
    participant App
    participant DM as DriverManager
    participant C as Connection
    participant PS as PreparedStatement
    participant RS as ResultSet
    App->>DM: getConnection(url,user,pass)
    DM-->>App: Connection
    App->>C: prepareStatement(sql)
    C-->>PS: PreparedStatement
    App->>PS: setX(...) + executeQuery()
    PS-->>RS: ResultSet
    App->>RS: while next() { getX() }
```

## 11.2 PreparedStatement vs inyección SQL

```mermaid
flowchart LR
    A["Statement + concatenar"] -->|VULNERABLE| X[SQL Injection]
    B["PreparedStatement + ?"] -->|SEGURO| OK[parámetros escapados]
```

## 11.3 Transacciones

```mermaid
stateDiagram-v2
    [*] --> AutoCommitOff: setAutoCommit(false)
    AutoCommitOff --> Commit: todo OK
    AutoCommitOff --> Rollback: excepción
    Commit --> [*]
    Rollback --> [*]
```

## 11.4 Pool y JdbcTemplate

Un pool (HikariCP) reutiliza conexiones. `JdbcTemplate` elimina el boilerplate
de abrir/cerrar y mapear.

---

### Qué practicarás

Connection, PreparedStatement, ResultSet→objeto, DAO CRUD, transacciones,
batch, pool, JdbcTemplate, RowMapper y parámetros con nombre. Los tests usan
**H2 en memoria** (sin ficheros .db en disco).
