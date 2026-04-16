# Nivel 19: El Trabajo de Fin de Máster (Boss Final)

Si miras atrás, has recorrido 69 ejercicios. Empezaste definiendo Genéricos básicos (`<T>`) para evitar casts, saltaste a Hilos, luego a Monitores Atómicos, y finalmente sumergiste todo en un ecosistema de Bases de Datos SQLite hiper-optimizado por HikariCP y Transacciones Puras.

Es hora de forjar la matriz y empaquetar toda esa destreza en un único caso de uso.

## El Reto Definitivo: E-Commerce Concurrente de Alto Rendimiento

Se te requerirá programar la simulación abstracta de **Black Friday** de una tienda que sufre una entrada abrupta de miles de usuarios.

Tendrás un stock de un producto limitado (ej. 100 PlayStations).
Tendrás 1000 usuarios asíncronos que intentarán comprarlas todas a la vez simulados por una Piscina de Hilos FixedPool (Nivel 9).

La compra consistirá en:
1. Comprobar que queda stock.
2. Si queda, restar 1 cuenta al stock.
3. Actualizar la tabla de la base de datos.
4. Generar un pequeño LOG.

### ¿Dónde fallan los Juniors?
1. Si no bloquean (`synchronized` / `ReentrantLock`), un Hilo A y B leerán "Stock = 1", ambos restarán, y acabarás con Stock = -1.
2. Si un hilo falla tras restar stock localmente, pero crashea en la base de datos... el stock estará corrupto a no ser que lances `.rollback()`.
3. Si utilizas el viejo JDBC en lugar de HikariCP, tu PC colapsará de abrir tantas conexiones bajo peticiones de 1000 Hilos asíncronos y tu código saltará por los aires por Timeout.

Demuestra que eres digno. Completa el Ejercicio 70 y serás, innegablemente, un Maestro de Genéricos, Concurrencia y Bases de Datos de Entornos Java.
