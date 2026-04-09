# Nivel 18: Rendimiento Extremo con Pooling (HikariCP)

Si analizas tu código actual, y tienes a 10.000 clientes concurrentes entrantes consumiendo tu backend...
¡Tu código estaría haciendo 10.000 veces `DriverManager.getConnection(...)`!

Cada vez que invocas una nueva conexión nativa, Java dedica entre 50 y 100 milisegundos en la Handshake (Sincronía) de puerto e inicialización. Si haces esto 10k veces seguidas, la base de datos se ahoga por un `Connection Timeout` o revienta la Memoria por saturación asíncrona de puertos abiertos.

## El Salvador Mundial: El Connection Pooling

La premisa de la industria es idéntica al ThreadPool (Ejecutores) que aprendiste en Nivel 9:
En lugar de crear 1 conexión por usuario, encenderemos unas `10` Conexiones permanentes cuando el servidor arranca. Cuando los 10k usuarios piden datos, se les "presta" una de esas tuberías. Terminado el try-finally, la devuelven al Pool en estado "Iddle" (Libre) para que otro la asimile y exprima. 

```mermaid
flowchart LR
    subgraph Pool de Conexiones [HikariCP]
        C1[Tubería Activa 1]
        C2[Tubería Activa 2]
        C3[Tubería Activa 3]
        Cid[Tubería IDDLE]
    end
    
    A(Usuario) -->|"dame una! (.getConnection)"| Cid
    Cid -->|Se tiñe verde| B(Usuario Hace CRUD)
    B -->|Termina y hace .close()| Cid
    
    style Cid fill:#bb4,stroke:#333
```

> Aclaración Vital: A partir de hoy, en un Pool de Conexiones, cuando haces `.close()` **NO** se apaga el enlace ni se quema hardware. Virtualmente `.close()` es interceptado y redefinido para "devuélvesela limpia a Hikari".

## HikariCP: El Rey Japonés 

Existen decenas (C3P0, Apache DBCP...), pero tú usarás **HikariCP**. Está certificado como el librador pool más violento, minimalista y rápido de toda la tierra. Su latencia de préstamo se mide en nanosegundos (está forjado a mano utilizando variables Atómicas primitivas exclusivas y arrays lock-free).
