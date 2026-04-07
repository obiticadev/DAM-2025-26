# Maestría Absoluta: Árboles y Vistas Reactivas

Cuando aprendiste a usar un `TreeMap` en el Nivel 3, descubriste que ordena las llaves automáticamente usando su `.compareTo()` o un `Comparator` inyectado. La interfaz que gobierna todo esto no es `Map`, sino que el `TreeMap` extiende a `NavigableMap`.

## El Secreto Místico: "Las Vistas"

En programación, una "Vista" no es una copia pasiva de la información. Una vista es un "espejo mágico" o una lente que mira fijamente a la estructura original. **Si alteras la Vista, se altera el Original. Y si alteras el Original, la Vista lo refleja al instante.**

### `headMap`, `tailMap`, `subMap` (Los Cuchillos)

Imagina un `TreeMap` que tiene rangos de precios del 1 al 100.
*   Quiero todos los productos que valgan *menos* de $50: `mapa.headMap(50, false);`
*   Quiero todos los productos que valgan *más* de $50: `mapa.tailMap(50, true);`
*   Quiero los de entre $20 y $80: `mapa.subMap(20, true, 80, true);` (Los booleanos indican inclusivo/exclusivo).

Lo bestial de esto es que, si usas `headMap(50)`... ¡Estás recibiendo un `NavigableMap` vivo! Si metes dentro de ese *headMap* el precio $45, **se meterá también en tu mapa maestro**.
¿Y qué pasa si a través del espejo (`headMap`) intentas engañarle insertando el valor $99 que no cumple el filtro? Lanza un `IllegalArgumentException: key out of range`. La Vista está blindada.

---

## Consumo Destructivo (`pollFirstEntry()`)

A veces no quieres iterar una estructura, sino devorarla. Esto pasa mucho en los "Message Brokers" (colas de eventos de servidores como Kafka o RabbitMQ simulados en código).

Un `NavigableMap` tiene la capacidad de arrancar nodos de los bordes del árbol, devolviéndotelos y borrándolos en un solo paso atómico en tiempo de O(log N).

*   `pollFirstEntry()`: Arranca y te duevuelve el nodo **más bajo/pequeño** que exista.
*   `pollLastEntry()`: Arranca y te devuelve el nodo **más alto/grande** que exista.

Si metes 50 facturas pendientes ordenadas por urgencia en un `TreeMap`, puedes crear un hilo que esté continuamente haciendo `pollFirstEntry()` hasta que el mapa sea nulo, consumiéndolas ordenadamente sin bucles complejos.

> [!TIP]
> **PRO TIP ARQUITECTÓNICO:** La diferencia entre una Cola de Prioridad (`PriorityQueue`) y consumir destructivamente un `TreeMap` es que la `PriorityQueue` NO tiene llaves (Keys) ni rechaza duplicados de forma eficiente. Si necesitas indexar tus tareas y asegurarte de que un ID de Tarea solo existe UNA vez en cola, DEBES usar el `pollFirstEntry()` de un `NavigableMap` y NO un Queue.
