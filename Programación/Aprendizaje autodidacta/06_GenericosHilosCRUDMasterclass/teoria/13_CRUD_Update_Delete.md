# Nivel 13: El Resto del CRUD y la Maldición de la Inyección SQL

Hemos Creado (Insert) y hemos Leído (Select). Completaremos el ecosistema ejecutando actualizaciones de campos (UPDATE) y purgando sistemas (DELETE).

Ambas operaciones en JDBC comparten exactamente la misma naturaleza que el INSERT: Se instancian en un `Statement` y se disparan mediante el método atómico `.executeUpdate()`.

## El Lado Oscuro de concatenar Strings: Inyección SQL

Hasta ahora has lanzado 'Statements' básicos utilizando Strings pegados con el '+' de Java. Ej:
`"SELECT * FROM usuarios WHERE nombre = '" + nombreInput + "'"`

Si un Hacker introduce el siguiente nombre en tu input frontend:
`"' OR 1=1; DROP TABLE usuarios; --"`

Tu servidor Backend ejecutará ciegamente la directriz. Su sesión se validará, y acto seguido tu base de datos será **borrada irremediablemente**. Has sufrido una de las vulnerabilidades más críticas y desastrosas de la red: **Inyección SQL**.

```mermaid
flowchart TD
    A[Usuario Input Creado] --> B(Hacker Pone: \n'; DROP TABLE clientes;)
    B --> C[Java Concatena String Simple]
    C --> D[Base de Datos]
    D -->|¡Comando Letal Reconocido!| E[🔥 CAIDA DEL SERVIDOR]
```

## El Escudo Blindado: PreparedStatement

Para arreglar la Inyección SQL usarás la versión hiper-dopada del objeto 'Statement': el **PreparedStatement**.
Su magia radica en que pre-pactas la query con Huecos de Interrogación `?` intocables. La Base de Datos recibe primero el armazón, y *después* le inyectas los valores a esos huecos de forma segura, garantizando que todo lo inyectado se interpreta estrictamente como datos inertes, y jamás como código.

```mermaid
sequenceDiagram
    participant Java as Aplicacion
    participant BBDD as Base de Datos Segura
    
    Java->>BBDD: Envía plantilla: UPDATE usuarios SET stock = ? WHERE id = ?
    Note right of BBDD: BBDD compila el armazón<br/>y reserva huecos.
    Java->>Java: Asigna: .setInt(1, 55)<br/>.setInt(2, hackerString)
    Java->>BBDD: Envía carga suelta (.executeUpdate)
    Note right of BBDD: Ejecuta con éxito. El hack no servirá de nada.
```

Prepárate, a partir de hoy NUNCA MAS utilizarás `Statement`. Tu religión se basará estrictamente en `PreparedStatement`.
