# Bloque XIV · JPA avanzado

> Lo que separa "sé JPA" de "sé JPA en producción": transacciones, bloqueos,
> caché, auditoría, herencia y migraciones de esquema.

---

## 14.1 Propagación de transacciones

```mermaid
flowchart TD
    A[metodoA @Transactional] --> B[metodoB REQUIRED]
    A --> C[metodoC REQUIRES_NEW]
    B -->|misma tx que A| TX1[(TX 1)]
    C -->|tx propia| TX2[(TX 2)]
```

## 14.2 Bloqueo optimista vs pesimista

```mermaid
sequenceDiagram
    participant T1
    participant T2
    T1->>DB: lee fila (version=1)
    T2->>DB: lee fila (version=1)
    T1->>DB: update (version 1->2) OK
    T2->>DB: update espera version=1 -> OptimisticLockException
```

- **Optimista** (`@Version`): no bloquea; falla al confirmar si cambió.
- **Pesimista** (`LockModeType`): bloquea la fila al leer.

## 14.3 Auditoría, soft delete, herencia

```mermaid
classDiagram
    Pago <|-- PagoTarjeta
    Pago <|-- PagoTransferencia
    class Pago { <<@Inheritance>> }
```

## 14.4 Migraciones (Flyway)

`V1__init.sql`, `V2__add_col.sql` versionan el esquema. El código no crea tablas
en producción: lo hacen las migraciones.

---

### Qué practicarás

Propagación, aislamiento, lock optimista/pesimista, caché L2, auditoría,
soft delete, herencia y migraciones (concepto).
