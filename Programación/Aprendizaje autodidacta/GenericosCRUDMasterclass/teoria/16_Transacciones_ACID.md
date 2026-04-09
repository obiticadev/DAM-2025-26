# Nivel 16: Transacciones (Principios A.C.I.D)

Has cruzado genéricos y programación de bases de datos de forma impecable usando sentencias aisladas. Sin embargo, ¿qué pasa si en un bloque de código tu servidor necesita **transferir dinero del Cliente A al Cliente B**?
Eso son dos mutaciones UPDATE en la base de datos (Restar en A, Sumar en B). ¿Qué pasaría si restas en A, y antes de hacer el UPDATE en B el servidor físico se queda sin electricidad y se apaga?
**El dinero se destruiría para siempre en el éter.**

Para resolver esto las Bases de Datos Relacionales (SQL) disponen de las **Transacciones**.

## La Regla Atómica (A.C.I.D)
ACID significa "Atomicidad, Consistencia, Aislamiento y Durabilidad". Centrémonos en la **A - Atomicidad**: "O todo ocurre junto, o nada ocurre".

En JDBC, cada vez que lanzas un comando SQL, Java por defecto le hace `Commit` y lo guarda al instante (*AutoCommit = true*). Para iniciar una transacción multi-paso debes desactivar esta característica en tu `Connection`.

```mermaid
flowchart TD
    A[Inicio Lógica Java] --> B(connection.setAutoCommit(false))
    B --> C[Restar 10€ a Juan.execute()]
    C --> D[Sumar 10€ a Ana.execute()]
    
    D --> E{¿Hubo Excepciones\n en Medio?}
    E -- NO --> F[connection.commit()]
    E -- SÍ (CAÍDA/ERROR) --> G[connection.rollback()]
    
    F --> H(Fin del Transvase)
    G --> H
    
    style F fill:#4f4,stroke:#333
    style G fill:#f44,stroke:#333
```

El método `.rollback()` avisa al Motor SQLite que aplique ingeniería inversa y deje la memoria del disco SSD *literalmente* en el estado íntegro y milimétrico anterior al fallo, deshaciendo cualquier modificación previa intermedia del bloque que no falló.

## Transacciones Avanzadas (Batch)
Si tienes que inyectar 10.000 `INSERTs`, hacerlo uno a uno quemará el rendimiento. Agruparemos todas las sentencias en memoria empleando el método `.addBatch()` y lanzaremos un impacto masivo en bloque con `.executeBatch()`, protegido por una inmensa capa transaccional.
