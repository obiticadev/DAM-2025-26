# Nivel 7: Sincronización y Monitores

El gran terror de la concurrencia es el **Data Race** (Condición de Carrera). Ocurre cuando dos o más hilos intentan modificar una misma variable en la memoria RAM exactamente al mismo tiempo (o entrelazado).

## La Anatomía de una Condición de Carrera

Imagínate que un Hilo A y un Hilo B llaman simultáneamente a `contador++`.

```mermaid
sequenceDiagram
    participant Memoria as Memoria Principal (contador = 0)
    participant HiloA as Hilo A (Core 1)
    participant HiloB as Hilo B (Core 2)
    
    HiloA->>Memoria: LEE contador (ve 0)
    HiloB->>Memoria: LEE contador (ve 0)
    HiloA->>HiloA: SUMA 1 (resultado 1)
    HiloB->>HiloB: SUMA 1 (resultado 1)
    HiloA->>Memoria: GUARDA (contador = 1)
    HiloB->>Memoria: GUARDA (contador = 1)
    
    Note right of Memoria: ¡ESPERABAS 2, PERO ES 1!<br/>¡Se han machacado los datos!
```

## El Monitor y `synchronized`

Para evitar que los hilos choquen, Java emplea **Monitores** (también llamados Cerrojos Intrínsecos). Todo objeto en Java tiene internamente una llave incrustada a nivel del hardware de la JVM. 

Si pones la palabra `synchronized` en la firma de un método, el Hilo se verá obligado a coger la llave de la clase. Si entra otro hilo, al no estar la llave disponible, será bloqueado y formará cola (Thread State: BLOCKED).

```mermaid
flowchart TD
    A[Hilos A y B llegan al método] --> B{¿Está la llave libre?}
    B -- Sí --> C[Hilo A coge la llave y avanza]
    B -- No --> D[Hilo B es bloqueado]
    
    C --> E[Hilo A termina el método y suelta la llave]
    E --> F[Hilo B es despertado, coge la llave y avanza]
```

### Bloques Sincronizados (Optimización)
Sincronizar métodos enteros es costoso. Es mejor sincronizar sólamente la "Sección Crítica" usando bloques:
`synchronized (this.cerrojo) { // lógica crítica }`.

Prepárate para forzar choques de datos y arreglarlos.
